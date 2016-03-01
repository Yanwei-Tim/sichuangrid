package com.tianque.recoverDatas.service;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.base.domain.AttentionPopulation;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBusinessService;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceService;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.recoverDatas.dao.RecoverDatasDao;
import com.tianque.recoverDatas.domain.RecoverDatas;
import com.tianque.recoverDatas.vo.RecoverDatasVo;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.service.HouseInfoService;
import com.tianque.service.helper.ManagePopulationByHouseHelper;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.systemOperateLog.service.SystemOperateLogService;
import com.tianque.systemOperateLog.util.SystemOperateType;

@Service("recoverDatasService")
@Transactional
public class RecoverDatasServiceImpl extends AbstractBaseService implements
		RecoverDatasService {

	@Autowired
	private RecoverDatasDao recoverDatasDao;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SystemOperateLogService systemOperateLogService;
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseSevice;
	@Autowired
	private CompanyPlaceService companyPlaceService;
	@Autowired
	private CompanyPlaceBusinessService companyPlaceBusinessService;
	@Autowired
	private ActualHouseService actualHouseService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private HouseInfoService houseInfoService;
	@Autowired
	private ManagePopulationByHouseHelper managePopulationByHouseHelper;


	@Override
	public RecoverDatas addRecoverDatas(RecoverDatas recoverDatas) {
		try {
			recoverDatas = recoverDatasDao.addRecoverDatas(recoverDatas);
			return recoverDatas;
		} catch (Exception e) {
			return dealException(this, "addRecoverDatas", "新增数据中心错误", e);
		}
	}

	@Override
	public PageInfo<RecoverDatas> findRecoverDatasForPage(
			RecoverDatasVo recoverDatasVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		try {
			recoverDatasVo.getOrganization().setOrgInternalCode(
					organizationDubboService.getOrgInternalCodeById(recoverDatasVo
							.getOrganization().getId()));
			return recoverDatasDao.findRecoverDatasForPage(recoverDatasVo,
					pageNum, pageSize, sidx, sord);
		} catch (Exception e) {
			return dealException(this, "findRecoverDatasForPage", "查询数据中心错误", e);
		}
	}

	@Override
	public RecoverDatas getRecoverDataById(Long id) {
		try {
			return recoverDatasDao.getRecoverDatasById(id);
		} catch (Exception e) {
			return dealException(this, "getRecoverDataById", "查询数据中心错误", e);
		}
	}

	@Override
	public void deleteRecoverDatasByIds(Long[] ids) {
		for (int i = 0; null != ids && i < ids.length; i++) {
			deleteRecoverDatasById(ids[i]);
		}
	}

	@Override
	public void deleteRecoverDatasById(Long id) {
		try {
			RecoverDatas recoverDatas = recoverDatasDao.getRecoverDatasById(id);
			if (recoverDatas != null && recoverDatas.getBusinessType() != null
					&& !"".equals(recoverDatas.getBusinessType())) {
				if (recoverDatas.getBusinessType().equals(
						NewBaseInfoTables.COMPANYPLACEKEY)) {
					// 数据恢复功能目前不开放
					// deleteRecoverDatasByIdForCompanyplace(recoverDatas);
				}
			}
			recoverDatasDao.deleteRecoverDatasById(id);
		} catch (Exception e) {
			dealException(this, "deleteRecoverDatasById", "删除数据中心信息出现错误", e);
		}
	}

	private void deleteRecoverDatasByIdForCompanyplace(RecoverDatas recoverDatas) {
		if (recoverDatas != null && recoverDatas.getBusinessType() != null
				&& !"".equals(recoverDatas.getBusinessType())) {
			if (recoverDatas.getBusinessType().equals(
					NewBaseInfoTables.COMPANYPLACEKEY)) {
				Gson gson = new GsonBuilder().serializeNulls()
						.setPrettyPrinting().setVersion(2.2).create();
				CompanyPlace companyPlace = (CompanyPlace) gson.fromJson(
						recoverDatas.getContents(), CompanyPlace.class);
				if (companyPlace == null) {
					throw new BusinessValidationException("需要恢复的数据为空");
				}
				companyPlaceBusinessService
						.deleteCompanyPlaceBusinessByCompanyPlaceID(companyPlace
								.getCid());
				companyPlaceBaseSevice.deleteCompanyPlaceBaseById(companyPlace
						.getBaseId());
			}
		}
	}

	@Override
	public void deleteActualPopulation(Countrymen countrymen) {
		try {
			if (countrymen.getOrganization() == null) {
				throw new BusinessValidationException();
			}
			RecoverDatas recoverdatas = new RecoverDatas();
			Organization organization = new Organization();
			recoverdatas.setModuleType(NewBaseInfoTables.PEOPLE_KEY);
			if (countrymen.getAttentionPopulationType() == null) {
				recoverdatas.setBusinessType(countrymen
						.getActualPopulationType());
			} else {
				recoverdatas.setBusinessType(countrymen
						.getAttentionPopulationType());
			}
			recoverdatas.setBusinessId(countrymen.getId());
			organization.setId(countrymen.getOrganization().getId());
			if (countrymen.getOrganization().getOrgInternalCode() != null
					&& !"".equals(countrymen.getOrganization()
							.getOrgInternalCode())) {
				organization.setOrgInternalCode(countrymen.getOrganization()
						.getOrgInternalCode());
			} else {
				organizationDubboService.getOrgInternalCodeById(countrymen
						.getOrganization().getId());
				organization.setOrgInternalCode(organizationDubboService
						.getOrgInternalCodeById(countrymen.getOrganization()
								.getId()));
			}
			systemOperateLogService.addSystemOperateLog(
					recoverdatas.getBusinessType(), countrymen.getIdCardNo(),
					organization, organization.getOrgInternalCode(),
					recoverdatas.getBusinessType(), SystemOperateType.DELETE,
					null, null);
			recoverdatas.setOrganization(organization);
			recoverdatas.setName(countrymen.getName());
			recoverdatas.setBaseInfoId(countrymen.getBaseInfoId());
			recoverdatas.setIdCardNo(countrymen.getIdCardNo());
			recoverdatas.setFullPinyin(countrymen.getFullPinyin());
			recoverdatas.setSimplePinyin(countrymen.getSimplePinyin());
			Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
					.setVersion(2.2).create();
			recoverdatas.setContents(gson.toJson(countrymen));
			addRecoverDatas(recoverdatas);
		} catch (Exception e) {
			dealException(this, "deleteActualPopulation", "新增数据中心错误", e);
		}
	}

	@Override
	public void recoverActualPopulation(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id不能为空");
		}
		recoverActualPopulation(getRecoverDataById(id));
	}

	private void recoverActualPopulation(RecoverDatas recoverData) {
		if (recoverData == null) {
			throw new BusinessValidationException("找不到当前数据");
		}
		String businessType = recoverData.getBusinessType();
		if (businessType == null || businessType == "") {
			throw new BusinessValidationException("无法确定当前数据所属模块");
		}
		String className = NewBaseInfoTables.classTypeMap.get(businessType);
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
				.setVersion(2.2).create();
		// 在这里区分类别
		try {
			if (businessType.equals(NewBaseInfoTables.COMPANYPLACEKEY)) {
				// 单位场所
				CompanyPlace companyPlace = (CompanyPlace) Class.forName(
						className).newInstance();
				companyPlace = (CompanyPlace) gson.fromJson(
						recoverData.getContents(), Class.forName(className));
				// 恢复业务
				recoverDatasDao.deleteRecoverDatasById(recoverData.getId());
				forwardToRecover(companyPlace);
			} else {
				Countrymen countrymen;

				countrymen = (Countrymen) Class.forName(className)
						.newInstance();

				countrymen = (Countrymen) gson.fromJson(
						recoverData.getContents(), Class.forName(className));
				if (countrymen == null) {
					throw new BusinessValidationException("Gson转换失败");
				}
				deleteRecoverDatasById(recoverData.getId());
				forwardToRecover(countrymen, businessType);
			}
		} catch (Exception e) {
			throw new BusinessValidationException("数据恢复失败: " + e.getMessage());
		}
	}

	private void forwardToRecover(Countrymen countrymen,
			String actualPopulationType) {
		Countrymen exist = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(countrymen
						.getOrganization().getId(), countrymen.getIdCardNo());
		if (NewBaseInfoTables.FLOATINGPOPULATION_KEY
				.equals(actualPopulationType)
				|| NewBaseInfoTables.HOUSEHOLDSTAFF_KEY
						.equals(actualPopulationType)) {
			if (exist != null) {
				throw new BusinessValidationException("该人员在人口信息中已存在，不能恢复该人口信息！");
			}
			//当原本房屋信息不存在时，清空房屋编号以便重新创建房屋
			if(countrymen.getHouseId()!=null&&actualHouseService.getActualHouseById(countrymen.getHouseId())==null){
				countrymen.setHouseId(null);
			}
			actualPopulationAdd(countrymen, actualPopulationType);
			attentionDependenceActualAdd(countrymen);
		} else {
			if (exist == null) {
				throw new BusinessValidationException("该人员在人口信息中不存在，请先恢复人口信息！");
			}
			loadAddressInfo(countrymen);
			attentionPopulationAdds(countrymen, actualPopulationType);
		}
	}

	private void forwardToRecover(CompanyPlace companyPlace) {
		CompanyPlace exist = companyPlaceService.readeCompanyPlace(companyPlace
				.getCid());
		if (exist != null) {
			throw new BusinessValidationException("该单位场所信息已存在，不能恢复单位场所信息");
		}
		try {
			companyPlaceService.recoverCompanyPlaceForRecover(companyPlace);
		} catch (Exception e) {
			throw new BusinessValidationException("恢复单位场所信息出错");
		}
	}

	private Countrymen actualPopulationAdd(Countrymen countrymen,
			String actualPopulationType) {
		countrymen.setAddressInfoId(null);
		Object object = SpringBeanUtil
				.getBeanFromSpringByBeanName(actualPopulationType + "Service");
		String methodName = "add"
				+ actualPopulationType.substring(0, 1).toUpperCase()
				+ actualPopulationType.substring(1) + "BaseInfo";
		try {
			Method method = object.getClass().getMethod(methodName,
					countrymen.getClass());
			return (Countrymen) method.invoke(object,
					new Object[] { countrymen });
		} catch (Exception e) {
			logger.error("人口关系维护出错:" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	private void attentionDependenceActualAdd(Countrymen countrymen) {
		RecoverDatasVo recoverDatasVo = new RecoverDatasVo();
		Organization organization = new Organization();
		organization.setId(countrymen.getOrganization().getId());
		recoverDatasVo.setOrganization(organization);
		recoverDatasVo.setIdCardNo(countrymen.getIdCardNo());
		List<RecoverDatas> recoverList = recoverDatasDao
				.findRecoverDatasForRecover(recoverDatasVo);
		Set<String> bussinessTypes = new HashSet<String>();
		Iterator<PopulationTypeBean> typeBeans = ((ActualPopulation) countrymen)
				.getPopulationTypes().iterator();
		while (typeBeans.hasNext()) {
			bussinessTypes.add(typeBeans.next().getPopulationType());
		}
		for (RecoverDatas recoverData : recoverList) {
			if (bussinessTypes.contains(recoverData.getBusinessType())) {
				recoverActualPopulation(recoverData);
			}
		}
	}

	private Countrymen attentionPopulationAdds(Countrymen countrymen,
			String actualPopulationType) {
		countrymen.setActualPopulationType(((AttentionPopulation) countrymen)
				.getPopulationTypeBean().getActualType());
		Object object = SpringBeanUtil
				.getBeanFromSpringByBeanName(actualPopulationType + "Service");
		String methodName = "add"
				+ actualPopulationType.substring(0, 1).toUpperCase()
				+ actualPopulationType.substring(1) + "BaseInfo";
		try {
			Method method = object.getClass().getMethod(methodName,
					countrymen.getClass());
			return (Countrymen) method.invoke(object,
					new Object[] { countrymen });
		} catch (Exception e) {
			return dealException(this, "attentionPopulationAdds", "人口关系维护出错", e);
		}
	}

	@Override
	public void deleteActualHouse(List<HouseInfo> actualHouses) {
		if (actualHouses == null || actualHouses.size() < 1) {
			throw new BusinessValidationException("房屋信息为空");
		}
		try {
			for (HouseInfo actualHouse : actualHouses) {
				RecoverDatas recoverdatas = new RecoverDatas();
				Organization organization = new Organization();
				actualHouse.getOrganization();
				recoverdatas.setModuleType(NewBaseInfoTables.HOUSE_KEY);
				if (actualHouse.getIsRentalHouse()) {
					recoverdatas
							.setBusinessType(NewBaseInfoTables.RENTALHOUSE_KEY);
				} else {
					recoverdatas
							.setBusinessType(NewBaseInfoTables.ACTUALHOUSE_KEY);
				}
				recoverdatas.setBusinessId(actualHouse.getId());
				organization.setId(actualHouse.getOrganization().getId());
				if (actualHouse.getOrganization().getOrgInternalCode() != null
						&& !"".equals(actualHouse.getOrganization()
								.getOrgInternalCode())) {
					organization.setOrgInternalCode(actualHouse
							.getOrganization().getOrgInternalCode());
				} else {
					organizationDubboService.getOrgInternalCodeById(actualHouse
							.getOrganization().getId());
					organization.setOrgInternalCode(organizationDubboService
							.getOrgInternalCodeById(actualHouse
									.getOrganization().getId()));
				}
				recoverdatas.setOrganization(organization);
				recoverdatas.setName(actualHouse.getAddress());
				Gson gson = new GsonBuilder().serializeNulls()
						.setPrettyPrinting().setVersion(2.2).create();
				recoverdatas.setContents(gson.toJson(actualHouse));
				addRecoverDatas(recoverdatas);
			}
		} catch (Exception e) {
			dealException(this, "deleteActualHouse", "新增数据中心错误", e);
		}

	}

	@Override
	public List<RecoverDatas> findRecoverdatainfosOneDay(int pageNum,
			int pageSize) {
		try {
			return recoverDatasDao
					.findRecoverdatainfosOneDay(pageNum, pageSize);
		} catch (Exception e) {
			throw new BusinessValidationException("在获取数据恢复中心时出错:"
					+ e.getMessage());
		}
	}

	@Override
	public Integer countRecoverdatainfosOneDay() {
		try {
			return recoverDatasDao.countRecoverdatainfosOneDay();
		} catch (Exception e) {
			throw new BusinessValidationException("在统计数据恢复中心数量时出错"
					+ e.getMessage());
		}
	}

	@Override
	public void deleteCompanyPlace(CompanyPlace companyPlace) {
		if (companyPlace == null || companyPlace.getOrg() == null
				|| companyPlace.getOrg().getId() == null) {
			throw new BusinessValidationException("单位场所信息为空");
		}
		try {
			RecoverDatas recoverdatas = new RecoverDatas();
			Organization organization = new Organization();
			if (companyPlace != null) {
				recoverdatas.setModuleType(NewBaseInfoTables.COMPANYPLACEKEY);
				organization = organizationDubboService.getFullOrgById(companyPlace
						.getOrg().getId());
				recoverdatas.setOrganization(organization);
				recoverdatas.setName(companyPlace.getName());
				recoverdatas.setBaseInfoId(companyPlace.getBaseId());
				recoverdatas.setBusinessId(companyPlace.getId());
				recoverdatas
						.setBusinessType(companyPlace.getClassifiCationEn());
				// recoverdatas.setIdCardNo(recoverInfo.getIdCardNo());
				// recoverdatas.setFullPinyin(recoverInfo.getFullPinyin());
				// recoverdatas.setSimplePinyin(recoverInfo.getSimplePinyin());
				Gson gson = new GsonBuilder().serializeNulls()
						.setPrettyPrinting().setVersion(2.2).create();
				recoverdatas.setContents(gson.toJson(companyPlace));
				addRecoverDatas(recoverdatas);
			}
		} catch (Exception e) {
			dealException(this, "deleteCompanyPlace", "备份单位场所信息到数据中心出错", e);
		}

	}
	private void loadAddressInfo(Countrymen population) {
		if (!GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))
				&& !GlobalSetting.SYNC_ACTUAL_POPULATION
						.equals(globalSettingService
								.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return;
		}

		ActualPopulation actualPopulation = actualPopulationProcessorService
				.getActualPopulationbyOrgIdAndIdCardNo(population
						.getOrganization().getId(), population.getIdCardNo());
		if (actualPopulation != null) {
			population.setActualPopulationType(actualPopulation
					.getActualPopulationType());
			population.setHouseId(actualPopulation.getHouseId());
		}

		if (null != population.getIsHaveHouse()
				&& population.getIsHaveHouse() == true) {
			Long houseId;
			if (null != population.getHouseId()) {
				houseId = population.getHouseId();
			} else {
				houseId = managePopulationByHouseHelper
						.getPopulationLivingHouseId(
								PopulationCatalog.parse(population
										.getAttentionPopulationType()),
								population.getId());

			}
			if(houseId == null){
				return;
			}
			HouseInfo house = houseInfoService.getSimpleHouseInfoById(houseId);
			if (house != null) {
				population.setIsHaveHouse(population.getIsHaveHouse());
				population.setCurrentAddressType(house.getAddressType());
				population.setCommunity(house.getCommunity());
				population.setRoom(house.getRoom());
				population.setUnit(house.getUnit());
				population.setBlock(house.getBlock());
				population.setCurrentAddress(house.getAddress());
				population.setHouseCode(house.getHouseCode());
				population.setHouseId(house.getId());
			}
		}

	}
}
