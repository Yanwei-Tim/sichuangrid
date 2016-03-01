package com.tianque.baseInfo.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.shard.util.IdConversionShardUtil;

@Repository("addressInfoDao")
public class AddressInfoDaoImpl extends AbstractBaseDao implements
		AddressInfoDao {

	@Override
	public Countrymen get(Long id) {
		return (Countrymen) getSqlMapClientTemplate().queryForObject(
				"addressInfo.getAddressInfoById", id);
	}

	@Override
	public Countrymen add(Countrymen countrymen) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"addressInfo.addAddressInfo", countrymen);
		return get(id);
	}

	@Override
	public Countrymen update(Countrymen countrymen) {
		getSqlMapClientTemplate().update("addressInfo.updateAddressInfo",
				countrymen);
		return get(countrymen.getAddressInfoId());
	}

	@Override
	public void delete(Long id) {
		getSqlMapClientTemplate().delete("addressInfo.deleteAddressInfo", id);

	}

	@Override
	public void updateAddressByPopulationTypeAndPopulationId(
			String populationType, Long populationId, String houseAddress) {
		Map map = new HashMap();
		map.put("populationType", populationType);
		map.put("populationId", populationId);
		map.put("houseAddress", houseAddress);
		if ("householdStaff".equals(populationType)) {
			String shardCode = IdConversionShardUtil
					.getShardCodeById(populationId);
			map.put("shardCode", shardCode);
		}
		getSqlMapClientTemplate()
				.update("addressInfo.updateAddressByPopulationTypeAndPopulationId",
						map);

	}

	@Override
	public List<Countrymen> getAddressInfoByIdForList(List<Long> houseInfoIds) {
		return getSqlMapClientTemplate().queryForList(
				"addressInfo.getAddressInfoByIdForList", houseInfoIds);
	}

	@Override
	public Integer getAddressCount(Long maxId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"addressInfo.getAddressCount", maxId);
	}

	@Override
	public List<Long> getAddressIds(int size, int page, Long maxId) {
		Map map = new HashMap();
		map.put("start", size * page);
		map.put("end", (page + 1) * size);
		map.put("maxId", maxId);
		return getSqlMapClientTemplate().queryForList(
				"addressInfo.getAddressIds", map);
	}

	@Override
	public void updateAddressOrgById(Long id, Long orgId, String orgCode) {
		Map map = new HashMap();
		map.put("id", id);
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		getSqlMapClientTemplate().update("addressInfo.updateAddressOrgById",
				map);
	}

	@Override
	public void batchUpdateAddressOrgById(List datas) {
		batchUpdateDate(datas, "addressInfo.updateAddressOrgById");
	}

	@Override
	public void updateAddressByTable(String tableName) {
		Map map = new HashMap();
		map.put("tableName", tableName);
		getSqlMapClientTemplate().update("addressInfo.updateAddressByTable",
				map);
	}
}
