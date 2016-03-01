package com.tianque.baseInfo.base.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.UpdateIdcardNoLog;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.service.PermanentAddressService;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.PeopleHelper;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Session;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.IdCardNoNativeAddress;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.IllegalOperationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.service.IdCardNoNativeAddressService;
import com.tianque.service.util.PopulationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.systemOperateLog.domain.SystemOperateLog;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.ContrastState;
import com.tianque.systemOperateLog.util.SystemOperateType;
import com.tianque.util.IdCardUtil;
import com.tianque.util.PropertyUtil;

/**
 * 
 * @Description:户籍、流动、未落户修改身份证号码service接口实现
 * @author zhangyouwei@hztian.com
 * @date: 2014-7-1 上午12:27:36
 */
@Service("baseInfoIdCardChangeSerivce")
@Transactional
public class BaseInfoIdCardChangeSerivceImpl implements
		BaseInfoIdCardChangeSerivce {

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
			.setVersion(2.2).create();
	private static Map<String, String> baseInfoIdCardChangeProcessorServiceMap = new HashMap<String, String>();
	private static final String HOUSEHOLD_STAFF = "householdStaffService";
	private static final String FLOATING_POPULATION = "floatingPopulationService";
	private static final String UNSETTLED_POPULATION = "unsettledPopulationService";

	static {
		baseInfoIdCardChangeProcessorServiceMap.put(
				PopulationType.HOUSEHOLD_STAFF, HOUSEHOLD_STAFF);
		baseInfoIdCardChangeProcessorServiceMap.put(
				PopulationType.FLOATING_POPULATION, FLOATING_POPULATION);
		baseInfoIdCardChangeProcessorServiceMap.put(
				PopulationType.UNSETTLED_POPULATION, UNSETTLED_POPULATION);
	}

	@Autowired
	@Qualifier("peopleHelper")
	PeopleHelper peopleHelper;
	@Autowired
	private IdCardNoNativeAddressService idCardNoNativeAddressService;
	@Autowired
	private Map<String, BaseInfoIdCardChangeProcessorService> baseInfoIdCardChangeProcessorService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private UpdateIdcardNoLogService updateIdcardNoLogService;
	@Autowired
	private PermanentAddressService permanentAddressService;

	@Override
	public Countrymen existBaseInfo(String actualPopulationType,
			String idCardNo, Long orgId) {
		if (StringUtils.isBlank(idCardNo)
				|| StringUtils.isBlank(actualPopulationType) || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {

			return baseInfoIdCardChangeProcessorService.get(
					baseInfoIdCardChangeProcessorServiceMap
							.get(actualPopulationType)).existBaseInfo(
					actualPopulationType, idCardNo, orgId);
		} catch (Exception e) {
			throw new ServiceValidationException("根据身份证号判断是否存在错误", e);
		}
	}

	@Override
	public Countrymen updateBaseInfoIdCardNo(Countrymen countrymen,
			String idCardNo) {
		if (countrymen == null
				|| !StringUtils.isNotBlank(idCardNo)
				|| !StringUtils.isNotBlank(countrymen.getIdCardNo())
				|| !StringUtils
						.isNotBlank(countrymen.getActualPopulationType())
				|| countrymen.getId() == null
				|| countrymen.getOrganization() == null
				|| countrymen.getOrganization().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			countrymen.setOrganization(organizationDubboService
					.getSimpleOrgById(countrymen.getOrganization().getId()));
			fillNativeLocation(countrymen);
			// 加入轨迹信息（户籍、流动、未落户）【业务信息在对应的删除操作中加入】
			changeActualPopulationAddLog(countrymen, idCardNo);
			// 修改身份证号码日志
			UpdateIdcardNoLog updateIdcardNoLog = fillUpdateIdcardNoLog(
					countrymen, idCardNo);
			Countrymen result = baseInfoIdCardChangeProcessorService.get(
					baseInfoIdCardChangeProcessorServiceMap.get(countrymen
							.getActualPopulationType()))
					.updateBaseInfoIdCardNo(countrymen, idCardNo);

			updateIdcardNoLogService.addUpdateIdcardNoLog(updateIdcardNoLog);
			return result;
		} catch (Exception e) {
			throw new ServiceValidationException("修改身份证号码错误", e);
		}

	}

	/**
	 * 填充出修改身份证的日志
	 * 
	 * @param countrymen
	 * @param idCardNo
	 * @return
	 */
	private UpdateIdcardNoLog fillUpdateIdcardNoLog(Countrymen countrymen,
			String idCardNo) {
		UpdateIdcardNoLog updateIdcardNoLog = new UpdateIdcardNoLog();
		updateIdcardNoLog.setDataOrg(countrymen.getOrganization());
		updateIdcardNoLog.setDataId(countrymen.getId());
		updateIdcardNoLog.setDataType(countrymen.getActualPopulationType());
		updateIdcardNoLog.setDataAfterOperate(countrymen.getIdCardNo());
		updateIdcardNoLog.setDataBeforeOperate(idCardNo);
		return updateIdcardNoLog;
	}

	/**
	 * 给修改身份证号加入轨迹信息（户籍、流动、未落户） 【业务信息在对应的删除操作中加入】
	 * 
	 * @param afterCountrymen
	 * @param idCardNo
	 */
	private void changeActualPopulationAddLog(Countrymen afterCountrymen,
			String idCardNo) throws Exception {

		Countrymen beforeCountrymen = new Countrymen();
		PropertyUtil.copyAllPropertiesWithRecursion(Countrymen.class,
				beforeCountrymen, afterCountrymen);
		beforeCountrymen.setIdCardNo(idCardNo);
		beforeCountrymen = fillNativeLocation(beforeCountrymen);
		SystemOperateLog systemOperateLog = initSystemOperateLog(
				afterCountrymen, beforeCountrymen);
		systemOperateLogService.addSystemOperateLog(systemOperateLog);

	}

	/**
	 * 填充log日志
	 * 
	 * @param afterCountrymen
	 *            修改以后的
	 * @param beforeCountrymen
	 *            修改以前的
	 * @return
	 * @throws Exception
	 */
	private SystemOperateLog initSystemOperateLog(Countrymen afterCountrymen,
			Countrymen beforeCountrymen) throws Exception {
		Session session = ThreadVariable.getSession();
		if (session == null || session.getUserName() == null) {
			throw new IllegalOperationException("Session不存在，系统不能正常工作!");
		}
		SystemOperateLog systemOperateLog = new SystemOperateLog();
		// 操作时间
		systemOperateLog.setOperateDate(session.getAccessTime());
		// 操作人
		systemOperateLog.setOperatePerson(session.getUserName());
		// 模块类型
		systemOperateLog.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
		// 业务模块类型
		systemOperateLog
				.setBusinessType(NewBaseInfoTables.UNSETTLEDPOPULATION_KEY
						.equals(afterCountrymen.getActualPopulationType()) ? NewBaseInfoTables.UNSETTLEDPOPULATION_KEY
						: NewBaseInfoTables.BASEINFO_KEY);
		// 操作类型
		systemOperateLog.setOperateType(SystemOperateType.UPDATE);
		// 数据关键字
		systemOperateLog.setDataKeyword(afterCountrymen.getIdCardNo());
		// 数据所属网格
		systemOperateLog.setDataOrgId(afterCountrymen.getOrganization());
		systemOperateLog.setDataOrgCode(afterCountrymen.getOrganization()
				.getOrgInternalCode());
		// 数据操作前所属网格
		systemOperateLog.setDataBeforeOrgId(beforeCountrymen.getOrganization());
		// 数据操作之前
		systemOperateLog.setDataBeforeOperate(gson.toJson(beforeCountrymen));
		// 数据操作之后
		systemOperateLog.setDataAfterOperate(gson.toJson(afterCountrymen));
		// 具体操作
		// 数据操作前后对比状态
		systemOperateLog.setContrastState(ContrastState.UNKNOWN);
		// 数据名（人的名字）
		// 数据操作源（在哪个模块被操作）
		systemOperateLog.setOperateSource(afterCountrymen
				.getActualPopulationType());
		// 数据源id
		systemOperateLog.setDataId(afterCountrymen.getId());
		return systemOperateLog;
	}

	/**
	 * 填充户籍地址、性别、出生日期等
	 * 
	 * @param countrymen
	 */
	private Countrymen fillNativeLocation(Countrymen countrymen) {
		autoFillBirthday(countrymen);
		autoFillGender(countrymen);
		autoFillIdCardNoNativeAddress(countrymen);

		return countrymen;
	}

	/**
	 * 填充出生日期
	 * 
	 * @param countrymen
	 */
	private void autoFillBirthday(Countrymen countrymen) {
		if (StringUtils.isNotBlank(countrymen.getIdCardNo())) {
			countrymen.setBirthday(IdCardUtil.parseBirthday(countrymen
					.getIdCardNo()));
		}
	}

	/**
	 * 填充性别
	 * 
	 * @param householdStaff
	 */
	private void autoFillGender(Countrymen countrymen) {
		if (StringUtils.isNotBlank(countrymen.getIdCardNo())) {
			countrymen.setGender(peopleHelper.autoFillGender(
					PropertyTypes.GENDER, countrymen.getIdCardNo()));
		}

	}

	/**
	 * 填充户籍地省、市、县
	 * 
	 * @param countrymen
	 */
	private void autoFillIdCardNoNativeAddress(Countrymen countrymen) {
		if (StringUtils.isNotBlank(countrymen.getIdCardNo())) {
			IdCardNoNativeAddress idCardNoNativeAddress = new IdCardNoNativeAddress();
			Map<String, IdCardNoNativeAddress> map = idCardNoNativeAddress
					.getIdCardNoNativeAddressByIdCardNo(countrymen
							.getIdCardNo());
			IdCardNoNativeAddress province = map.get("province");
			IdCardNoNativeAddress city = map.get("city");
			IdCardNoNativeAddress district = map.get("district");

			PermanentAddress getProvince = permanentAddressService
					.getPermanentAddressByAddressNo(province.getSixthIdCardNo());
			PermanentAddress getCity = permanentAddressService
					.getPermanentAddressByAddressNo(city.getSixthIdCardNo());
			PermanentAddress getDistrict = permanentAddressService
					.getPermanentAddressByAddressNo(district.getSixthIdCardNo());

			// IdCardNoNativeAddress getProvince = idCardNoNativeAddressService
			// .getIdCardNoNativeAddressBySixthIdCardNo(province);
			// IdCardNoNativeAddress getCity = idCardNoNativeAddressService
			// .getIdCardNoNativeAddressBySixthIdCardNo(city);
			// IdCardNoNativeAddress getDistrict = idCardNoNativeAddressService
			// .getIdCardNoNativeAddressBySixthIdCardNo(district);

			// countrymen
			// .setProvince(getProvince == null ? null
			// : getProvince
			// .getAddressName()
			// .substring(
			// 0,
			// idCardNoNativeAddressService
			// .getIdCardNoNativeAddressBySixthIdCardNo(
			// province)
			// .getAddress().length() - 1));
			countrymen.setProvince(getProvince == null ? null : getProvince
					.getAddressName());
			countrymen.setCity(getCity == null ? null : getCity
					.getAddressName());
			countrymen.setDistrict(getDistrict == null ? null : getDistrict
					.getAddressName());
		}
	}
}
