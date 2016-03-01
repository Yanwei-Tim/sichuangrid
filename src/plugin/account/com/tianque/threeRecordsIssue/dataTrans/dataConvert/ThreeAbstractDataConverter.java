package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.UserImportHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;
import com.tianque.exception.base.OperationFailedException;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.util.PopulationType;
import com.tianque.threeRecordsIssue.dataTrans.dataImport.ExcelDataImport;
import com.tianque.threeRecordsIssue.dataTrans.dataImport.ThreeExcelImportHelper;
import com.tianque.util.IdCardUtil;
import com.tianque.util.PropertyUtil;

public abstract class ThreeAbstractDataConverter<T> implements
		ThreeDataConvert<T> {
	@Autowired
	protected ActualHouseService actualHouseService;
	@Autowired
	protected BaseInfoService baseInfoService;
	@Autowired
	protected ThreeValidateHelper validateHelper;
	@Autowired
	protected GlobalSettingService globalSettingService;
	@Autowired
	private ThreeDataConvertionHelper convertionHelper;
	@Autowired
	private CacheService cacheService;
	@Autowired
	protected ActualPopulationProcessorService actualPopulationProcessorService;
	private static ThreadLocal<Organization> uploadOrgThreadLocal = new ThreadLocal<Organization>();
	private int checkOrgOrNot = UserImportHelper.SELF;
	protected final static Logger logger = LoggerFactory
			.getLogger(ThreeAbstractDataConverter.class);

	private ThreadLocal<Organization> headerOrg = new ThreadLocal<Organization>();

	@Override
	public Organization getUploadOrganization() {
		return uploadOrgThreadLocal.get();
	}

	@Override
	public void setUploadOrganization(Organization organization) {
		uploadOrgThreadLocal.set(organization);
	}

	@Override
	public boolean isRepeatData(T domain) {
		if (domain instanceof BaseDomain) {
			return null != ((BaseDomain) domain).getId();
		} else
			return false;
	}

	@Override
	public int getCheckOrgOrNot() {
		return checkOrgOrNot;
	}

	@Override
	public void setCheckOrgOrNot(int checkOrgOrNotValue) {
		checkOrgOrNot = checkOrgOrNotValue;
	}

	protected void convertActualPopulationToAttentionPopulation(
			ActualPopulation actualPopulation,
			AttentionPopulation attentionPopulation) {
		try {
			PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
					attentionPopulation, actualPopulation, new String[] { "id",
							"attentionPopulationType" });
		} catch (Exception e) {
			logger.error("copy属性错误", e);
		}
	}

	protected void convertBaseInfoToAttentionPopulation(
			Countrymen actualPopulation, AttentionPopulation attentionPopulation) {
		try {
			PropertyUtil.copyPropertiesWithRecursion(Countrymen.class,
					attentionPopulation, actualPopulation, new String[] { "id",
							"attentionPopulationType", "actualPopulationType",
							"organization" });
		} catch (Exception e) {
			logger.error("copy属性错误", e);
		}
	}

	protected void fillDefaultBirthday(People people) {
		if (StringUtil.isStringAvaliable(people.getIdCardNo())) {
			people.setBirthday(IdCardUtil.parseBirthday(people.getIdCardNo()));
		}
	}

	public void persistenceDomain(List<T> data) {
	}

	public void updateDomain(List<T> data) {
	}

	protected void autoFillAddress(Countrymen domain) {
		/*
		 * if (domain != null || domain == null) { return; } if (null !=
		 * domain.getIsHaveHouse() && null != domain.getHouseCode() &&
		 * !domain.getHouseCode().isEmpty()) { HouseInfo houseInfo =
		 * actualHouseService
		 * .getHouseInfoByHouseCodeAndOrgId(domain.getHouseCode(),
		 * domain.getOrganization().getId());
		 * domain.setHouseId(houseInfo.getId());
		 * domain.setCurrentAddressType(houseInfo.getAddressType());
		 * domain.setCommunity(houseInfo.getCommunity());
		 * domain.setBlock(houseInfo.getBlock());
		 * domain.setUnit(houseInfo.getUnit());
		 * domain.setRoom(houseInfo.getRoom()); StringBuffer sb = new
		 * StringBuffer(); if
		 * (!validateHelper.emptyString(domain.getCommunity())) {
		 * sb.append(domain.getCommunity()).append("小区"); } if
		 * (!validateHelper.emptyString(domain.getBlock())) {
		 * sb.append(domain.getBlock()).append("幢"); } if
		 * (!validateHelper.emptyString(domain.getUnit())) {
		 * sb.append(domain.getUnit()).append("单元"); } if
		 * (!validateHelper.emptyString(domain.getRoom())) {
		 * sb.append(domain.getRoom()).append("室"); }
		 * domain.setCurrentAddress(sb.toString());
		 * domain.setNoHouseReason(null); }
		 */
	}

	protected void checkBusinessLogic(AttentionPopulation domain) {
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			try {
				convertActualPopulationToAttentionPopulation(
						actualPopulationProcessorService
								.getActualPopulationbyOrgIdAndIdCardNo(domain
										.getOrganization().getId(), domain
										.getIdCardNo()), domain);

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		autoFillAddress(domain);
	}

	protected void convertBaseInfoReferencePersistence(
			AttentionPopulation domain) {
		try {
			Countrymen temp = actualPopulationProcessorService
					.getActualPopulationbyOrgIdAndIdCardNo(domain
							.getOrganization().getId(), domain.getIdCardNo());
			if (temp != null) {
				convertActualPopulationToAttentionPopulation(
						(ActualPopulation) temp, domain);
			} else {
				temp = baseInfoService.existBaseInfo(domain.getIdCardNo());
				if (temp != null) {
					convertBaseInfoToAttentionPopulation(temp, domain);
					domain.setBaseInfoId(temp.getId());
					domain.setId(null);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		autoFillAddress(domain);
	}

	protected ValidateResult validateIsDeathAndEmphis(Countrymen population,
			int realRow) {
		ThreeExcelImportHelper.realRow.set(realRow);
		ValidateResult result = new ValidateResult();
		Countrymen exist = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNoIncludeLogout(population
						.getOrganization().getId(), population.getIdCardNo());
		if (exist != null) {
			String populationType = PopulationType.getCnameByActualType(exist
					.getActualPopulationType());
			if (exist.isDeath()) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "在人口信息库中存在已死亡数据，请核实！");
			} else if (1L == ((ActualPopulation) exist).getLogOut()) {
				result.addErrorMessage(getColumNo("idCardNo") + "在本网格下的"
						+ populationType + "中已注销，请核实！");
			} else if (population instanceof ActualPopulation) {
				result.addErrorMessage(getColumNo("idCardNo") + "在本网格下的"
						+ populationType + "中已存在，请核实！");
			}
		} else {
			exist = baseInfoService.existBaseInfo(population.getIdCardNo());
			if (null != exist && exist.isDeath()) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "在人口库中存在已死亡数据，请核实！");
			}
		}
		return result;
	}

	protected ValidateResult validateIsDeathAndEmphisForAttention(
			Countrymen population, int realRow) {
		ThreeExcelImportHelper.realRow.set(realRow);
		ValidateResult result = new ValidateResult();
		Countrymen exist = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNoIncludeLogout(population
						.getOrganization().getId(), population.getIdCardNo());
		if (exist != null) {
			String populationType = PopulationType.getCnameByActualType(exist
					.getActualPopulationType());
			if (exist.isDeath()) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "在人口信息库中存在已死亡数据，请核实！");
			} else if (1L == ((ActualPopulation) exist).getLogOut()) {
				result.addErrorMessage(getColumNo("idCardNo") + "在本网格下的"
						+ populationType + "中已注销，请核实！");
			} else if (!exist.getActualPopulationType().equals(
					population.getActualPopulationType())) {
				result.addErrorMessage(getColumNo("idCardNo") + "在本网格下的"
						+ populationType + "中已存在，请核实！");
			}
		} else {
			exist = baseInfoService.existBaseInfo(population.getIdCardNo());
			if (null != exist && exist.isDeath()) {
				result.addErrorMessage(getColumNo("idCardNo")
						+ "在人口库中存在已死亡数据，请核实！");
			}
		}
		return result;
	}

	// FIXME 这个Convert为什么有validate
	protected ValidateResult validate(AbstractCountrymenValidator valitor,
			AttentionPopulation population, int realRow) {
		ThreeExcelImportHelper.realRow.set(realRow);
		Countrymen domain = population;
		ValidateResult result = new ValidateResult();
		Map<String, String> mapMessages = new HashMap<String, String>();
		try {
			if (GlobalSetting.NOT_ADD_POPULATION
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {

				ActualPopulation actualPopulation = actualPopulationProcessorService
						.getActualPopulationbyOrgIdAndIdCardNo(domain
								.getOrganization().getId(), domain
								.getIdCardNo());
				if (null == actualPopulation) {
					// result.addErrorMessage("身份证为" + domain.getIdCardNo()
					// + "在该网络的实有人口中不存在");
					/*
					 * mapMessages.put(String.valueOf(Integer
					 * .parseInt(ThreeExcelImportHelper
					 * .getDataColumMap("idCardNo")) + 1), "身份证为" +
					 * domain.getIdCardNo() + "在该网络的实有人口中不存在");
					 */
					setPopulationType(domain, headerOrg.get());
				}

				if (domain.getAttentionPopulationType() != null
						&& null != actualPopulation) {
					try {
						PropertyUtil.copyPropertiesWithRecursion(
								Countrymen.class, domain, actualPopulation,
								new String[] { "id" });
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
				result.setMapMessages(mapMessages);
			} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {

				setPopulationType(domain, headerOrg.get());
				result.setMapMessages(mapMessages);
				result.addAllErrorMessage(valitor.validateBaseInfo(domain));
				result.addAllErrorMessage(validateIsDeathAndEmphisForAttention(
						population, realRow));
				valitor.validateIsHaveHouse(domain, result, convertionHelper,
						actualHouseService);
			} else {
				result.addAllErrorMessage(valitor.validateBaseInfo(domain));
				valitor
						.validateCurrentAddress(domain, result,
								convertionHelper);
			}
		} catch (Exception e) {
			if (GlobalSetting.SYNC_ACTUAL_POPULATION
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				result.addAllErrorMessage(valitor.validateBaseInfo(domain));
				valitor.validateIsHaveHouse(domain, result, convertionHelper,
						actualHouseService);
			} else if (GlobalSetting.NOT_DEPENDENT
					.equals(globalSettingService
							.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
				result.addAllErrorMessage(valitor.validateBaseInfo(domain));
				valitor
						.validateCurrentAddress(domain, result,
								convertionHelper);
			}
		}

		fillDefaultBirthday(domain);
		result.addAllErrorMessage(valitor.validateSpecializedInfo(domain));
		return result;
	}

	private void setPopulationType(Countrymen actualPopulation,
			Organization headerOrg2) {

		if (!validateHelper.emptyString(actualPopulation
				.getActualPopulationType())) {

			if (BaseInfoTables.FLOATINGPOPULATION_DISPLAYNAME
					.equals(actualPopulation.getActualPopulationType())) {
				actualPopulation
						.setActualPopulationType(PopulationType.FLOATING_POPULATION);
			} else if (BaseInfoTables.HOUSEHOLD_STAFFNAME
					.equals(actualPopulation.getActualPopulationType())) {
				actualPopulation
						.setActualPopulationType(PopulationType.HOUSEHOLD_STAFF);
			} else {
				// fateson add 方便验证的时候判断
				// actualPopulation.setActualPopulationType(null);
				return;
			}

		} else {
			// BY JEFFREY ZH. // 实口类型必须填写，不再基于省市县的匹配判断填充
			return;
			/*
			 * // 省份 和 headerOrg2 都来自excel 前面有验证 String province =
			 * actualPopulation.getProvince(); String city =
			 * actualPopulation.getCity(); String district =
			 * actualPopulation.getDistrict();
			 * 
			 * if (headerOrg2 == null || StringUtils.isEmpty(province) ||
			 * StringUtils.isEmpty(city) || StringUtils.isEmpty(district)) { //
			 * 说明填报单位出错 ,无法自动判断 流动 还是 户籍人口 // AbstractCountrymenValidator
			 * validateActualPopulationType // 跳过该验证
			 * actualPopulation.setActualPopulationType("fateson"); return;
			 * 
			 * }
			 * 
			 * String fullOrgName = headerOrg2.getFullOrgName(); String[]
			 * orgStrings = fullOrgName.split("->"); if (orgStrings.length >= 4)
			 * { String excelHeadProvince = orgStrings[1]; String excelHeadCity
			 * = orgStrings[2]; String excelHeadDistrict = orgStrings[3]; if
			 * (excelHeadProvince.startsWith(province) &&
			 * excelHeadCity.equals(city) && excelHeadDistrict.equals(district))
			 * { // 如果如何 为 户籍人口 actualPopulation
			 * .setActualPopulationType(PopulationType.HOUSEHOLD_STAFF); } else
			 * { actualPopulation
			 * .setActualPopulationType(PopulationType.FLOATING_POPULATION); } }
			 */
		}

	}

	private Class<T> getRuntimeType() {
		Class<T> entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();

			entityClass = (Class<T>) parameterizedType[0];
		}
		return entityClass;
	}

	@Override
	public T convertToDomain(String[] cellValues, ValidateResult result,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		T returnObject = null;
		try {
			returnObject = getRuntimeType().newInstance();
			ThreeExcelImportHelper.newProcImportDate(beanDatas, cellValues,
					getUploadOrganization(), returnObject, result);
		} catch (Exception e) {
			ExcelDataImport.setThreadLocal("error_msg", e.getMessage());
			throw new OperationFailedException("转换失败", e);
		}

		return returnObject;
	}

	@Override
	public void setheaderOrg(Organization headerOrg) {
		this.headerOrg.set(headerOrg);
	}

	@Override
	public ValidateResult validate(String[] cellValues, int realRow) {
		return null;
	}

	@Override
	public T convertToDomain(String[] cellValues) {
		return null;
	}

	public String getColumNo(String key) {
		StringBuffer bf = new StringBuffer();
		if (!StringUtils.isEmpty(ThreeExcelImportHelper.getDataColumMap(key))
				&& ThreeExcelImportHelper.realRow.get() != null) {
			bf.append("第[").append(ThreeExcelImportHelper.realRow.get())
					.append("]行");
			bf.append("第[").append(
					Integer
							.valueOf(ThreeExcelImportHelper
									.getDataColumMap(key)) + 1).append("]列");
		} else {
			bf.append("");
		}
		return bf.toString();
	}

	public boolean checkDataExitInCache(String modulType, String modulKey,
			String orgId) {
		String key = modulType + "_" + orgId + "_" + modulKey + "_state";
		if (cacheService.add(key, 300, modulType)) {
			return false;
		} else {
			return true;
		}
	}

	public void cleanDataInCache(String modulType, String modulKey, String orgId) {
		cacheService
				.remove(modulType + "_" + orgId + "_" + modulKey + "_state");
	}

	public void registerProcess(T domain) {

	}
}
