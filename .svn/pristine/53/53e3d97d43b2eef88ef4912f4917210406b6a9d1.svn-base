/**
 * 
 */
package com.tianque.mongodb.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.WriteConcern;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.base.service.AddressInfoService;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.householdStaff.dao.HouseholdStaffDao;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.domain.vo.HouseSimpleInfoForSearch;
import com.tianque.mongodb.dao.HouseholdStaffMongoDao;
import com.tianque.service.HousePopulationMaintanceService;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.JobMonitorService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * @since
 * @author 曾利民
 * @version 1.0.0[2014年12月18日]
 */
@Component("householdStaffMoveMongoDbJob")
public class HouseholdStaffMoveMongoDb {

	private static final Logger LOG = LoggerFactory
			.getLogger(HouseholdStaffMoveMongoDb.class);

	@Autowired
	private JobMonitorService jobMonitorService;
	@Autowired
	private HouseholdStaffDao householdStaffDao;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private AddressInfoService addressInfoService;
	@Autowired
	private HousePopulationMaintanceService housePopulationMaintanceService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private HouseholdStaffMongoDao householdStaffMongoDao;
	@Autowired
	private ShardConversion shardConversion;

	private static boolean isExecute;

	public void execute(Integer beginId) {
		if (isExecute) {
			LOG.info("户籍人口mongodb数据上次同步还未结束");
			return;
		}
		isExecute = true;
		Long id = 0l;
		try {
			id = jobMonitorService.addJobMonitor(this.getClass().getName());
			LOG.info("户籍人口mongodb数据同步开始");
			long startTime = System.currentTimeMillis();
			executeMove(beginId);
			jobMonitorService.updateJobMonitor(id,
					"花了" + (System.currentTimeMillis() - startTime)
							+ "户籍人口mongodb数据同步Job", true);
		} catch (Exception e) {
			jobMonitorService.updateJobMonitor(id,
					"户籍人口mongodb数据同步开始：" + e.getMessage(), false);
		} finally {
			isExecute = false;
		}
		LOG.info("户籍人口mongodb数据同步结束");
	}

	public void executeMove(Integer beginId) {
		Long count = householdStaffDao.findAllcount();
		LOG.info("count:" + count);
		final ArrayBlockingQueue<List<HouseholdStaff>> queue = new ArrayBlockingQueue<List<HouseholdStaff>>(
				10);
		Thread moveThread = new Thread() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					try {
						List<HouseholdStaff> temp = queue.take();
						long start = System.currentTimeMillis();
						householdStaffMongoDao.batchSave(temp,
								WriteConcern.UNACKNOWLEDGED);
						count += temp.size();
						LOG.info("size:" + temp.size() + " count:" + count
								+ " cost:"
								+ (System.currentTimeMillis() - start));
					} catch (InterruptedException e) {
						LOG.info("moveThread is Interrupted");
					}
				}
			}
		};
		moveThread.start();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		int index = 1;

		long total = count - beginId;
		if (total > 0) {
			long num = total / 1000;
			if (count % 1000 != 0) {
				num += 1;
			}
			final CountDownLatch latch = new CountDownLatch((int) num);
			for (int i = (beginId == null ? 0 : beginId); i < count; i += 1000) {
				final int start = i;
				if (index % 200 == 0)
					try {
						Thread.sleep(30 * 1000);
					} catch (InterruptedException e1) {
					}
				index++;
				Runnable runnable = new Runnable() {
					public void run() {
						try {
							LOG.info("begind:" + start);
							long startTime = System.currentTimeMillis();
							List<HouseholdStaff> result = householdStaffDao
									.findAllHouseholdStaffList(start,
											start + 999);
							List<Long> baseInfoIds = new ArrayList<Long>();
							List<Long> houseInfoIds = new ArrayList<Long>();
							for (HouseholdStaff householdStaff : result) {
								houseInfoIds.add(householdStaff
										.getAddressInfoId());
								baseInfoIds.add(householdStaff.getBaseInfoId());
							}
							/*List<Countrymen> houseList = addressInfoService
									.getAddressInfoByIdForList(houseInfoIds);
							List<Countrymen> baseInfoList = baseInfoService
									.getBaseInfoByIdForList(baseInfoIds);*/
							for (HouseholdStaff householdStaff : result) {
								/*for (Countrymen countrymen : houseList) {
									if (countrymen.getId().equals(
											householdStaff.getAddressInfoId())) {
										PopulationCopyUtil
												.copyAddressInfoMessage(
														countrymen,
														householdStaff);
									}
								}
								for (Countrymen countrymen : baseInfoList) {
									if (countrymen.getId().equals(
											householdStaff.getBaseInfoId())) {
										PopulationCopyUtil.copyBaseInfoMessage(
												countrymen, householdStaff);
									}
								}*/
								loadLivingHouseIfNecc(
										PopulationCatalog.HOUSEHOULD_POPULATION,
										householdStaff);
							}
							try {
								queue.put(result);
							} catch (InterruptedException e) {
								LOG.error(e.getMessage(), e);
							}
							LOG.info("end:" + start + " size:" + result.size()
									+ " cost:"
									+ (System.currentTimeMillis() - startTime));
						} finally {
							latch.countDown();
						}
					};
				};
				pool.execute(runnable);
			}

			try {
				latch.await();
			} catch (InterruptedException e) {
				LOG.error(e.getMessage(), e);
			}

			if (!queue.isEmpty()) {
				try {
					Thread.sleep(30 * 1000);
				} catch (InterruptedException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		moveThread.interrupt();
	}

	/*
	 * public void executeDelte() { Long count =
	 * householdStaffDao.findAllcount(); Long start =
	 * System.currentTimeMillis(); ExecutorService pool =
	 * Executors.newFixedThreadPool(10); final AtomicInteger num = new
	 * AtomicInteger(); for (Long i = 0l; i < count; i += 1000000) { final long
	 * index = i; Runnable delete = new Runnable() { public void run() {
	 * LOG.info("begind " + index); long start = System.currentTimeMillis(); int
	 * count = 0; for (long j = index; j < index + 99999; j++) { if
	 * (householdStaffMongoDao.getById(j) != null) { if
	 * (householdStaffDao.existsHouseholdStaff(j) == null) {
	 * householdStaffMongoDao.delete(j); count++; } } } num.addAndGet(count);
	 * LOG.info("end " + index + " delete count:" + count + " cost:" +
	 * (System.currentTimeMillis() - start)); }; }; pool.execute(delete); }
	 * LOG.info("end executeDelte, delete count:" + num + " cost:" +
	 * (System.currentTimeMillis() - start)); }
	 */

	public void singletonExecute() {
		Long count = householdStaffDao.findAllcount();
		List<HouseholdStaff> result;
		List<Long> baseInfoIds, houseInfoIds;
		List<Countrymen> houseList, baseInfoList;
		int total = 0;
		boolean stop = false;
		for (int i = 0; i < count; i += 1000) {
			LOG.info("begind:" + i);
			// stop = true;
			if (stop)
				break;
			long startTime = System.currentTimeMillis();
			result = householdStaffDao.findAllHouseholdStaffList(i, i + 999);
			baseInfoIds = new ArrayList<Long>();
			houseInfoIds = new ArrayList<Long>();
			for (HouseholdStaff householdStaff : result) {
				houseInfoIds.add(householdStaff.getAddressInfoId());
				baseInfoIds.add(householdStaff.getBaseInfoId());
			}
			/*houseList = addressInfoService
					.getAddressInfoByIdForList(houseInfoIds);
			baseInfoList = baseInfoService.getBaseInfoByIdForList(baseInfoIds);*/
			for (HouseholdStaff householdStaff : result) {
				/*for (Countrymen countrymen : houseList) {
					if (countrymen.getId().equals(
							householdStaff.getAddressInfoId())) {
						PopulationCopyUtil.copyAddressInfoMessage(countrymen,
								householdStaff);
					}
				}
				for (Countrymen countrymen : baseInfoList) {
					if (countrymen.getId().equals(
							householdStaff.getBaseInfoId())) {
						PopulationCopyUtil.copyBaseInfoMessage(countrymen,
								householdStaff);
					}
				}*/
				loadLivingHouseIfNecc(PopulationCatalog.HOUSEHOULD_POPULATION,
						householdStaff);
				householdStaff.setOrgInternalCode(householdStaff
						.getOrgInternalCode().replace(".", "@"));

			}
			householdStaffMongoDao.batchSave(result,
					WriteConcern.UNACKNOWLEDGED);
			total += result.size();
			LOG.info("end:" + i + " size:" + result.size() + " count:" + total
					+ " cost:" + (System.currentTimeMillis() - startTime));
		}
	}

	public void loadLivingHouseIfNecc(PopulationCatalog peopleType,
			People people) {
		if (people != null) {
			HouseSimpleInfoForSearch house = housePopulationMaintanceService
					.getPopulationLivingHouseInfo(peopleType, people.getId(),
							shardConversion.getShardCode(people
									.getOrganization()));

			if (house != null && house.getMaxId() != null) {
				people.setHouseId(house.getMaxId());
				people.setHouseCode(house.getHouseCode());
				people.setCommunity(house.getComnunity());
				people.setBlock(house.getBlock());
				people.setUnit(house.getUnit());
				people.setRoom(house.getRoom());
				people.setCurrentAddress(house.getAddress());
				people.setHouseCode(house.getHouseCode());
				if (house.getAddressType() != null) {
					people.setCurrentAddressType(propertyDictService
							.getPropertyDictById(house.getAddressType().getId()));
				}
			}
		}
	}

	/*
	 * public void updateOrgCode() { ExecutorService pool =
	 * Executors.newFixedThreadPool(20); int count = 0; for (long i = 2540000; i
	 * < 17746000; i += 10000) { final long index = i;
	 * 
	 * if (count++ % 20 == 0) { try { Thread.sleep(60 * 1000); } catch
	 * (InterruptedException e) { e.printStackTrace(); } } Runnable runnable =
	 * new Runnable() { public void run() { List<HouseholdStaff> temp = new
	 * ArrayList<HouseholdStaff>(); for (long j = index; j < index + 10000; j++)
	 * { HouseholdStaff householdStaff = householdStaffMongoDao .getById(j); if
	 * (householdStaff != null) {
	 * householdStaff.setOrgInternalCode(householdStaff
	 * .getOrgInternalCode().replace(".", "@")); temp.add(householdStaff); } }
	 * LOG.info("count:" + (index + 10000));
	 * householdStaffMongoDao.batchSave(temp, WriteConcern.UNACKNOWLEDGED); } };
	 * pool.execute(runnable); System.out.println(index + 10000); } }
	 * 
	 * public void singletonUpdateOrgCode() { List<HouseholdStaff> temp = new
	 * ArrayList<HouseholdStaff>(20000); for (long i = 2540000; i < 17746000;
	 * i++) { HouseholdStaff householdStaff = householdStaffMongoDao.getById(i);
	 * if (householdStaff != null) { temp.add(householdStaff); if (temp.size()
	 * == 20000) { householdStaffMongoDao.batchSave(temp,
	 * WriteConcern.UNACKNOWLEDGED); temp = new
	 * ArrayList<HouseholdStaff>(20000); LOG.info("count:" + i); } } } }
	 */
}
