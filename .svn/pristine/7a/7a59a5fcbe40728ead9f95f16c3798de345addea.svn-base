package com.tianque.gis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.domain.BuildingData;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.gis.dao.BuildingDataDao;
import com.tianque.gis.service.BuildingDataService;
import com.tianque.gis.service.BuildingService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.validate.impl.BuildingValidatorImpl;

@Service("buildingDataService")
@Transactional
public class BuildingDataServiceImpl extends LogableService implements
		BuildingDataService {
	private BuildingValidatorImpl buildingValidatorImpl;
	@Autowired
	private BuildingDataDao buildingDataDao;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private BuildingService buildingService;

	@Override
	public void deleteBuildingDataById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		buildingDataDao.deleteBuildingDataById(id);
	}

	@Override
	public List<Long> deleteBuildingsByIds(List<Long> personIds) {
		if (personIds == null) {
			throw new BusinessValidationException("id没有获得");
		}
		List<Long> delPersonIdList = new ArrayList<Long>();
		delPersonIdList.clear();
		delPersonIdList.addAll(personIds);
		try {
			buildingDataDao.deleteBuildingDataByIds(personIds);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类BuildingDataServiceImpl的deleteBuildingsByIds方法出现异常，原因：",
					"根据ID删除楼宇信息出现错误", e);
		}
		return delPersonIdList;
	}

	@Override
	public BuildingData getBuildingDataByBuildingId(Long buildingId) {
		if (null == buildingId) {
			throw new BusinessValidationException("楼宇buildingId没有获得");
		}
		return buildingDataDao.getBuildingDataByBuildingId(buildingId);
	}

	@Override
	public Integer countHouseByBuildingId(Long buildingId) {
		return buildingService.countHouseByBuildingId(buildingId);
	}

	@Override
	public BuildingData updateBuildingData(BuildingData buildingData) {
		if (buildingData.getId() == null) {
			throw new BusinessValidationException("id没有获得");
		}
		validate(buildingData);
		autoFilled(buildingData);
		buildingData = buildingDataDao.updateBuildingData(buildingData);
		return buildingData;
	}

	private void validate(BuildingData buildingData) {
		buildingValidatorImpl = new BuildingValidatorImpl();
		buildingValidatorImpl.setValidateHelper(validateHelper);
		/*
		 * ValidateResult buildingValidator = buildingValidatorImpl
		 * .validate(buildingData); if (buildingValidator.hasError()) { throw
		 * new
		 * BusinessValidationException(buildingValidator.getErrorMessages()); }
		 */
	}

	private void autoFilled(BuildingData buildingData) {
		autoFillOrganizationInternalCode(buildingData);
		autoFillChinesePinyin(buildingData);
	}

	private void autoFillOrganizationInternalCode(BuildingData buildingData) {
		Organization org = organizationDubboService.getSimpleOrgById(buildingData
				.getOrganization().getId());
		if (org == null) {
			throw new BusinessValidationException("找不到指定的网格");
		}
		buildingData.setOrgInternalCode(org.getOrgInternalCode());
	}

	private void autoFillChinesePinyin(BuildingData buildingData) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(buildingData.getBuildingName());
		buildingData.setSimplePinyin(pinyin.get("simplePinyin"));
		buildingData.setFullPinyin(pinyin.get("fullPinyin"));
	}

}
