package com.tianque.baseInfo.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.dao.BaseInfoDao;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.DuplicatePeople;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("baseInfoService")
@Transactional
public class BaseInfoServiceImpl implements BaseInfoService {
	@Autowired
	private BaseInfoDao baseInfoDao;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public Countrymen add(Countrymen entity) {
		return baseInfoDao.add(entity);
	}

	@Override
	public Long save(Countrymen countrymen) {
		return baseInfoDao.save(countrymen);
	}

	@Override
	public Countrymen update(Countrymen entity) {
		if (entity != null
				&& entity.getSourcesState() != null
				&& ConstantsProduct.SourcesState.JOB.equals(entity
						.getSourcesState())) {
			return entity;
		}
		return baseInfoDao.update(entity);
	}

	@Override
	public Countrymen getBaseInfoById(Long id) {
		return baseInfoDao.get(id);
	}

	@Override
	public void delete(Long id) {
		baseInfoDao.delete(id);

	}

	@Override
	public void delete(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("Id不能为空");
		}
		for (Long id : ids) {
			delete(id);
		}

	}

	@Override
	public void updateBaseInfoHouseState(Countrymen countrymen) {
		baseInfoDao.updateBaseInfoHouseState(countrymen);
	}

	@Override
	public void cancelDeath(Long baseId, Boolean death) {
		baseInfoDao.cancelDeath(baseId, death);

	}

	/***
	 * 该方法中江三本台账有调用
	 */
	@Override
	public Countrymen existBaseInfo(String idCardNo) {
		Countrymen countrymen = baseInfoDao.existBaseInfo(idCardNo);
		if (null != countrymen && null != countrymen.getGender()
				&& null != countrymen.getGender().getId())
			countrymen.setGender(propertyDictService
					.getPropertyDictById(countrymen.getGender().getId()));
		return countrymen;
	}

	@Override
	@SolrServerIndex(mode = OperateMode.CANCEL_DEATH, value = NewBaseInfoTables.BASEINFO_KEY)
	public void updateDeathStateById(Long baseInfoId, Boolean death,
			String idCardNo, Long orgId, String orgCode, String operateScource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", baseInfoId);
		map.put("death", death);
		map.put("idCardNo", idCardNo);
		map.put("orgId", orgId);
		map.put("orgCode", orgCode);
		map.put("operateScource", operateScource);
		baseInfoDao.updateDeathStateById(map);
	}

	@Override
	public List<Countrymen> getBaseInfosByIdcardno(String idcardno) {
		if (idcardno == null || "".equals(idcardno)) {
			throw new BusinessValidationException("身份证不能为空");
		}
		return baseInfoDao.getBaseInfosByIdcardno(idcardno);
	}

	@Override
	public Countrymen getBaseInfoByIdCardNo(String idCardNo) {
		Countrymen countrymen = baseInfoDao.getBaseInfoByIdCardNo(idCardNo);
		loadMaritalStateAndSchooling(countrymen);
		return countrymen;
	}

	@Override
	public List<Countrymen> getBaseInfoByIdForList(List<Long> baseInfoIds) {
		return baseInfoDao.getAddressInfoByIdForList(baseInfoIds);
	}

	@Override
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo) {
		if (idCardNo == null || countrymen.getIdCardNo() == null
				|| "".equals(idCardNo) || "".equals(countrymen.getIdCardNo())) {
			throw new BusinessValidationException("身份证不能为空");
		}
		return baseInfoDao.updateBaseInfoIdCardNo(countrymen, idCardNo);
	}

	@Override
	public List<DuplicatePeople> getDuplicatePeopleInBaseInfo() {
		DuplicatePeople duplicatePeople = baseInfoDao
				.getUniquenessDuplicatePeople();

		List<DuplicatePeople> results = null;
		if (duplicatePeople != null && duplicatePeople.getIdCardNo() != null
				&& !"".equals(duplicatePeople.getIdCardNo())) {
			results = baseInfoDao.getDuplicatePeoplesByIdcardno(duplicatePeople
					.getIdCardNo());

		}
		return results;

	}

	@Override
	public List<DuplicatePeople> getAllBaseInfoDuplicatePeople() {
		return baseInfoDao.getAllBaseInfoDuplicatePeople();
	}

	private void loadMaritalStateAndSchooling(Countrymen countrymen) {
		if (countrymen == null) {
			return;
		}
		if (countrymen.getMaritalState() != null
				&& countrymen.getMaritalState().getId() != null) {
			countrymen.setMaritalState(propertyDictService
					.getPropertyDictById(countrymen.getMaritalState().getId()));
		}
		if (countrymen.getSchooling() != null
				&& countrymen.getSchooling().getId() != null) {
			countrymen.setSchooling(propertyDictService
					.getPropertyDictById(countrymen.getSchooling().getId()));
		}
	}

}
