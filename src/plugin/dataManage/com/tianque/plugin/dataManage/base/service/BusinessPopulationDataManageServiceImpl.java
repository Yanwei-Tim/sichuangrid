package com.tianque.plugin.dataManage.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.unitils.database.annotations.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.floatingPopulation.service.FloatingPopulationService;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.baseInfo.householdStaff.service.HouseholdStaffService;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.util.Constants;
import com.tianque.plugin.dataManage.util.Constants.ClaimHasRepeatActualPopu;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.util.PropertyUtil;

@Component("businessPopulationDataManageService")
@Transactional
public class BusinessPopulationDataManageServiceImpl<T extends AttentionPopulation> extends
		AbstractDataManageService {
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private FloatingPopulationService floatingPopulationService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private HouseholdStaffService householdStaffService;
	@Autowired
	private BaseInfoService baseInfoService;

	@Override
	public TargetDataVo getTargetDataVo(Object population) throws Exception {
		AttentionPopulation attention = (AttentionPopulation) ReflectionUtil
				.executePopulationHasDuplicateMethod(population);
		String idCardNo = (String) ReflectionUtil.executeTargetGetMethod(population, "getIdCardNo");
		TargetDataVo vo = new TargetDataVo();
		if (null != attention) {
			vo.setLogout(attention.getIsEmphasis());
			vo.setId(attention.getId());
			return vo;
		} else {
			Countrymen temp = baseInfoService.getBaseInfoByIdCardNo(idCardNo);
			if (temp != null && temp.isDeath()) {
				vo.setHasRepeatActualPopu(ClaimHasRepeatActualPopu.DEATHFOROTHERORG);
				return vo;
			}
		}
		return null;
	}

	/**
	 * 更新实口 因为任务人员的新增和修改都会同步所有的人口信息，所有这里只要保证信息实口就可以了
	 * 
	 * @param population
	 * @param convertFloatingPopulation
	 * @param convertHouseholdStaff
	 * @param houseMarchTypeList
	 * @throws Exception
	 */
	@Override
	protected void asynchronousProcess(BaseDomain population) throws Exception {
		String mode = globalSettingService
				.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION);
		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(((Organization) ReflectionUtil
						.executeTargetGetMethod(population, "getOrganization")).getId(),
						(String) ReflectionUtil.executeTargetGetMethod(population, "getIdCardNo"));
		if (GlobalSetting.SYNC_ACTUAL_POPULATION.equals(mode) && actualPopulation == null) {
			// 保存实口
			String actualType = (String) ReflectionUtil.executeTargetGetMethod(population,
					"getActualPopulationType");
			if (BaseInfoTables.FLOATINGPOPULATION_KEY.equals(actualType)) {
				FloatingPopulation floatingPopulation = new FloatingPopulation();
				PropertyUtil.copyPropertiesWithRecursion(Countrymen.class, floatingPopulation,
						population);
				floatingPopulation.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
				floatingPopulationService.addFloatingPopulationBaseInfo(floatingPopulation);
			} else if (BaseInfoTables.HOUSEHOLDSTAFF_KEY.equals(actualType)) {
				HouseholdStaff householdStaff = new HouseholdStaff();
				PropertyUtil.copyPropertiesWithRecursion(Countrymen.class, householdStaff,
						population);
				householdStaff.setSourcesState(ConstantsProduct.SourcesState.SYNCHRO);
				householdStaffService.addHouseholdStaffBaseInfo(householdStaff);

			}
		}
	}

	/**
	 * 验证是否可以认领，这个方法只在业务人口的认领中需要，并且只会发生在第二种模式下
	 * 
	 * @param temp
	 */
	@Override
	public void validCanClaim(BaseDomain population) throws Exception {
		String actualType = (String) ReflectionUtil.executeTargetGetMethod(population,
				"getActualPopulationType");
		String mode = globalSettingService
				.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION);
		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(
						ReflectionUtil.getTargetOrganization(population).getId(),
						(String) ReflectionUtil.executeTargetGetMethod(population, "getIdCardNo"));
		if (GlobalSetting.NOT_ADD_POPULATION.equals(mode) && actualPopulation == null
				&& null == actualType) {
			throw new BusinessValidationException(
					String.valueOf(Constants.ClaimErrorType.NOT_ADD_ACTUAL));
		}
		if (actualPopulation == null && GlobalSetting.SYNC_ACTUAL_POPULATION.equals(mode)
				&& null == actualType) {
			throw new BusinessValidationException(String.valueOf(Constants.ClaimErrorType.VALIDATE));
		}
		if (null != actualPopulation
				&& IsEmphasis.IsNotEmphasis.equals(actualPopulation.getLogOut())) {
			throw new BusinessValidationException(
					String.valueOf(Constants.ClaimErrorType.ACTUAL_NOT_EMPHASIS));
		}
		if (actualType == null && actualPopulation != null
				&& actualPopulation.getActualPopulationType() != null) {
			ReflectionUtil.setActualPopulationType(population,
					actualPopulation.getActualPopulationType());
		}
	}
}
