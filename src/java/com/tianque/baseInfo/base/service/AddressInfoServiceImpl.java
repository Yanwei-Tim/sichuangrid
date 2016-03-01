package com.tianque.baseInfo.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.dao.AddressInfoDao;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.shard.util.ShardConversion;

@Transactional
@Service("addressInfoService")
public class AddressInfoServiceImpl implements AddressInfoService {
	private final static Logger LOG = LoggerFactory
			.getLogger(AddressInfoServiceImpl.class);
	@Autowired
	private AddressInfoDao addressInfoDao;
	@Autowired
	private FloatingPopulationService floatingPopulationService;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private ShardConversion shardConversion;

	private final static int PAGE_SIZE = 100000;

	private final static int GROUP_SIZE = 100;

	private final static int POOL_SIZE = 10;

	@Override
	public Countrymen add(Countrymen entity) {
		return addressInfoDao.add(entity);
	}

	@Override
	public Countrymen update(Countrymen entity) {
		return addressInfoDao.update(entity);
	}

	@Override
	public Countrymen get(Long id) {
		return addressInfoDao.get(id);
	}

	@Override
	public void delete(Long id) {
		addressInfoDao.delete(id);
	}

	@Override
	public void updateAddressByPopulationTypeAndPopulationId(
			String populationType, Long populationId, String houseAddress) {
		try {
			if ("".equals(populationType) || populationType == null
					|| populationId == null) {
				return;
			}
			addressInfoDao.updateAddressByPopulationTypeAndPopulationId(
					populationType, populationId, houseAddress);
		} catch (Exception e) {
			throw new ServiceValidationException("修改房屋信息同步关联人口的地址时出错", e);
		}
	}

	@Override
	public List<Countrymen> getAddressInfoByIdForList(List<Long> houseInfoIds) {
		return addressInfoDao.getAddressInfoByIdForList(houseInfoIds);
	}

	@Override
	public int[] updateAddressOrg(final Long maxId) {
		Integer count = addressInfoDao.getAddressCount(maxId);
		Integer page = count / PAGE_SIZE;
		if (count % PAGE_SIZE > 0)
			page += 1;
		Integer group = page / GROUP_SIZE;
		if (page % GROUP_SIZE > 0)
			group += 1;
		final List<String> all = shardConversion.getAllShardCode();
		final AtomicInteger unknowCount = new AtomicInteger(0);
		for (int i = 0; i < group; i++) {
			int startPage = i * GROUP_SIZE;
			int endPage = startPage + GROUP_SIZE;
			if (endPage > page) {
				endPage = page;
			}
			int total = endPage - startPage;
			final CountDownLatch latch = new CountDownLatch(total);
			ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
			for (int j = startPage; j < endPage; j++) {
				final int startTemp = j;
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						try {
							List<Long> addressIds = addressInfoDao
									.getAddressIds(PAGE_SIZE, startTemp, maxId);
							List<Map> datas = new ArrayList<Map>(
									addressIds.size());
							for (Long addressId : addressIds) {
								Organization org = householdStaffService
										.findOrgByAddress(all, addressId);
								if (org == null) {
									List<Organization> orgs = floatingPopulationService
											.findOrgByAddress(addressId);
									if (orgs.size() > 0) {
										org = orgs.get(0);
									}
								}
								Map map = new HashMap(4);
								if (org != null) {
									map.put("id", addressId);
									map.put("orgId", org.getId());
									map.put("orgCode", org.getOrgInternalCode());
									/*addressInfoDao.updateAddressOrgById(
											addressId, org.getId(),
											org.getOrgInternalCode());*/
								} else {
									map.put("id", addressId);
									map.put("orgId", -1L);
									map.put("orgCode", "#");
									unknowCount.incrementAndGet();
								}
								datas.add(map);
								if (datas.size() == 500) {
									addressInfoDao
											.batchUpdateAddressOrgById(datas);
									datas.clear();
								}
							}
							addressInfoDao.batchUpdateAddressOrgById(datas);
						} catch (Exception e) {
							LOG.error("", e);
						} finally {
							latch.countDown();
						}
					}
				};
				pool.execute(runnable);
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				LOG.error("", e);
			}
		}
		return new int[] { count, unknowCount.intValue() };
	}

	@Override
	public void updateAddressOrg(String tableName) {
		if ("all".equals(tableName)) {//清洗所有address
			List<String> all = shardConversion.getAllShardCode();
			addressInfoDao.updateAddressByTable("floatingpopulations");
			for (int index = 0; index < all.size(); index++) {
				addressInfoDao.updateAddressByTable("householdstaffs_"
						+ all.get(index));
			}
		} else if ("other".equals(tableName)) {//清洗所有org信息为空的的address
			updateAddressOrg(0L);
		} else if (StringUtil.isStringAvaliable(tableName)) {//清洗指定户籍分表或者流动人口的地址
			addressInfoDao.updateAddressByTable(tableName);
		}
	}
}
