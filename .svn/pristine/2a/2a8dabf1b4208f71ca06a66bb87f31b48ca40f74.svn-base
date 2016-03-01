package com.tianque.baseInfo.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.core.base.AbstractBaseDao;

@Repository("baseInfoDao")
public class BaseInfoDaoImpl extends AbstractBaseDao implements BaseInfoDao {

	@Override
	public Countrymen get(Long id) {
		return (Countrymen) getSqlMapClientTemplate().queryForObject(
				"baseInfo.getBaseInfoById", id);
	}

	@Override
	public Countrymen add(Countrymen countrymen) {
		Long id = (Long) getSqlMapClientTemplate().insert(
				"baseInfo.addBaseInfo", countrymen);
		return get(id);
	}

	@Override
	public Long save(Countrymen countrymen) {
		getSqlMapClientTemplate().update("baseInfo.updateBaseInfo", countrymen);
		Long id = countrymen.getId();
		return id;
	}

	@Override
	public Countrymen update(Countrymen countrymen) {
		getSqlMapClientTemplate().update("baseInfo.updateBaseInfo", countrymen);
		return get(countrymen.getBaseInfoId());
	}

	@Override
	public void delete(Long id) {
		getSqlMapClientTemplate().delete("baseInfo.deleteBaseInfo", id);

	}

	@Override
	public void updateBaseInfoHouseState(Countrymen countrymen) {

		getSqlMapClientTemplate().update("baseInfo.updateBaseInfoHouseState",
				countrymen);
	}

	@Override
	public void cancelDeath(Long baseId, Boolean death) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", baseId);
		map.put("death", death);
		getSqlMapClientTemplate().update("baseInfo.cancelDeath", map);

	}

	@Override
	public Countrymen existBaseInfo(String idCardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		return (Countrymen) getSqlMapClientTemplate().queryForObject(
				"baseInfo.existBaseInfo", map);
	}

	@Override
	public void updateDeathStateById(Map<String, Object> map) {
		getSqlMapClientTemplate().update("baseInfo.cancelDeath", map);
	}

	@Override
	public List<Countrymen> getBaseInfosByIdcardno(String idcardno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcardno", idcardno);
		return (List) getSqlMapClientTemplate().queryForList(
				"baseInfo.selectBaseInfoByIdcardno", map);
	}

	@Override
	public Countrymen getBaseInfoByIdCardNo(String idCardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		return (Countrymen) getSqlMapClientTemplate().queryForObject(
				"baseInfo.getBaseInfoByIdCardNo", map);
	}

	@Override
	public List<Countrymen> getAddressInfoByIdForList(List<Long> baseInfoIds) {
		return getSqlMapClientTemplate().queryForList(
				"baseInfo.getBaseInfoByIdForList", baseInfoIds);
	}

	@Override
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		map.put("countrymen", countrymen);
		getSqlMapClientTemplate()
				.update("baseInfo.updateBaseInfoIdCardNo", map);
		return getBaseInfoByIdCardNo(countrymen.getIdCardNo());
	}

	@Override
	public DuplicatePeople getUniquenessDuplicatePeople() {

		return (DuplicatePeople) getSqlMapClientTemplate().queryForObject(
				"baseInfo.getUniquenessDuplicatePeople");
	}

	@Override
	public List<DuplicatePeople> getDuplicatePeoplesByIdcardno(String idCardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idCardNo", idCardNo);
		return getSqlMapClientTemplate().queryForList(
				"baseInfo.getDuplicatePeoplesByIdcardno", map);
	}

	@Override
	public List<DuplicatePeople> getAllBaseInfoDuplicatePeople() {

		return getSqlMapClientTemplate().queryForList(
				"baseInfo.getAllBaseInfoDuplicatePeople");
	}

}
