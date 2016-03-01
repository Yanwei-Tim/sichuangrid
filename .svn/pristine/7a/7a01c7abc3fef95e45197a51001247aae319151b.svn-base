package com.tianque.plugin.dataManage.base.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.unitils.database.annotations.Transactional;

import com.tianque.baseInfo.base.domain.People;
import com.tianque.baseInfo.base.service.SourcesStateService;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.baseInfo.handicapped.service.HandicappedService;
import com.tianque.baseInfo.overseaPersonnel.domain.OverseaPersonnel;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.dao.AbstractDataManageDao;
import com.tianque.plugin.dataManage.base.vo.ClaimDetail;
import com.tianque.plugin.dataManage.base.vo.ClaimResult;
import com.tianque.plugin.dataManage.base.vo.ClaimResultList;
import com.tianque.plugin.dataManage.base.vo.ClaimState;
import com.tianque.plugin.dataManage.base.vo.SearchVo;
import com.tianque.plugin.dataManage.location.builddatasTemp.domain.BuilddatasTemp;
import com.tianque.plugin.dataManage.location.enterpriseTemp.domain.EnterpriseTemp;
import com.tianque.plugin.dataManage.population.aidsPopulationsTemp.domain.AidspopulationsTemp;
import com.tianque.plugin.dataManage.population.floatingPopulationTemp.domain.FloatingPopulationTemp;
import com.tianque.plugin.dataManage.population.handicappedTemp.domain.HandicappedTemp;
import com.tianque.plugin.dataManage.population.householdStaffTemp.domain.HouseholdStaffTemp;
import com.tianque.plugin.dataManage.population.idleYouthTemp.domain.IdleYouthTemp;
import com.tianque.plugin.dataManage.util.Constants;
import com.tianque.plugin.dataManage.util.Constants.ClaimErrorType;
import com.tianque.plugin.dataManage.util.Constants.ClaimHasRepeatActualPopu;
import com.tianque.plugin.dataManage.util.DataManageBaseInfo;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoTypes;
import com.tianque.plugin.dataManage.util.DataManageBaseInfoUtil;
import com.tianque.service.vo.IsEmphasis;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Transactional
public abstract class AbstractDataManageService<T extends BaseDomain, Target extends BaseDomain> {

	private DomainValidator<T> validator;
	@Autowired
	@Qualifier("abstractDataManageDao")
	private AbstractDataManageDao abstractDataManageDao;
	@Autowired
	@Qualifier("handicappedService")
	private HandicappedService handicappedService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ShardConversion shardConversion;

	private Logger logger = Logger.getLogger(getClass());

	/** 列表显示，分页查询 */
	public PageInfo findTemps(Class t, SearchVo searchVo, Integer page,
			Integer rows, String sidx, String sord) {
		searchVo = autoFilledVo(searchVo, sidx, sord);
		// searchVo.getMode().equals(anObject)
		PageInfo pageInfo = abstractDataManageDao.findTemps(t, searchVo, page,
				rows);
		return autoFilledClaimDetail(pageInfo);
	}

	public T addTemp(Class t, T temp) {
		Long id = null;
		// 残疾人类型特殊处理
		if (temp instanceof HandicappedTemp) {
			HandicappedTemp handicapped = (HandicappedTemp) temp;
			List<PropertyDict> disabilityTypes = handicapped
					.getDisabilityTypes();
			List<PropertyDict> disabilitys = new ArrayList<PropertyDict>();
			if (disabilityTypes != null) {
				List<Long> disabilityTypeIds = new ArrayList<Long>();
				for (PropertyDict pd : disabilityTypes) {
					if (handicapped.getDisabilityIntellect() != null
							&& handicapped.getDisabilityIntellect()
									.getDisplayName()
									.indexOf(pd.getDisplayName()) != -1) {
						disabilityTypeIds.add(pd.getId());
						disabilitys.add(handicapped.getDisabilityIntellect());
					} else if (handicapped.getDisabilitysLimbs() != null
							&& handicapped.getDisabilitysLimbs()
									.getDisplayName()
									.indexOf(pd.getDisplayName()) != -1) {
						disabilityTypeIds.add(pd.getId());
						disabilitys.add(handicapped.getDisabilitysLimbs());
					} else if (handicapped.getDisabilitysMental() != null
							&& handicapped.getDisabilitysMental()
									.getDisplayName()
									.indexOf(pd.getDisplayName()) != -1) {
						disabilityTypeIds.add(pd.getId());
						disabilitys.add(handicapped.getDisabilitysMental());
					} else if (handicapped.getDisabilitysHearing() != null
							&& handicapped.getDisabilitysHearing()
									.getDisplayName()
									.indexOf(pd.getDisplayName()) != -1) {
						disabilityTypeIds.add(pd.getId());
						disabilitys.add(handicapped.getDisabilitysHearing());
					} else if (handicapped.getDisabilitysSpeech() != null
							&& handicapped.getDisabilitysSpeech()
									.getDisplayName()
									.indexOf(pd.getDisplayName()) != -1) {
						disabilityTypeIds.add(pd.getId());
						disabilitys.add(handicapped.getDisabilitysSpeech());
					} else if (handicapped.getDisabilitysVision() != null
							&& handicapped.getDisabilitysVision()
									.getDisplayName()
									.indexOf(pd.getDisplayName()) != -1) {
						disabilityTypeIds.add(pd.getId());
						disabilitys.add(handicapped.getDisabilitysVision());
					}
				}
				handicapped.setDisabilityTypeIds(disabilityTypeIds);
			}
			List<Long> disabilityLevelIds = new ArrayList<Long>();
			for (PropertyDict pd : disabilitys) {
				disabilityLevelIds.add(pd.getId());
			}
			handicapped.setDisabilityLevelIds(disabilityLevelIds);
			List<HandicappedSdisabilityType> handicappedSdisabilityTypeList = handicappedService
					.editHandicapped(handicapped, true);
			id = abstractDataManageDao.addTemp(handicapped);
			handicappedService.saveHandicappedList(
					handicappedSdisabilityTypeList, handicapped.getId());
		} else {
			id = abstractDataManageDao.addTemp(temp);
		}
		return getTempById(t, id);
	}

	/** 根据传入id批量认领，返回成功或失败的认领结果队列 */
	public ClaimResultList claimTempByIds(Class t, Long[] tempIds,
			Long targertOrgId) throws Exception {
		ClaimResultList returnList;
		try {
			List<ClaimResult> successList = new ArrayList<ClaimResult>();
			List<ClaimResult> errorList = new ArrayList<ClaimResult>();
			returnList = new ClaimResultList();
			ClaimResult claimResult;

			for (Long id : tempIds) {
				T temp = getTempById(t, id);
				temp.setShardCode(shardConversion.getShardCode(targertOrgId));
				Organization org = ReflectionUtil.getTargetOrganization(temp);
				if (org != null) {
					org.setId(targertOrgId);
				}
				ReflectionUtil.setTargetOrganization(temp, org);

				claimResult = updateAndClaim(temp, null);

				if (claimResult.getClaimState().successful) {
					successList.add(claimResult);
					updateTempDataOrgId(temp, id, targertOrgId);
				} else {
					errorList.add(claimResult);
				}
			}
			returnList.setSuccessList(successList);
			returnList.setErrorList(errorList);
			returnList.setOrgId(targertOrgId);
		} catch (Exception e) {
			throw new ServiceValidationException("认领失败,刷新页面重试", e);
		}
		return returnList;
	}

	private void updateTempDataOrgId(T temp, Long id, Long targetOrgId) {
		DataManageBaseInfo dbInfo = DataManageBaseInfoUtil
				.getDataManageInfoByType(temp.getClass().getSimpleName());
		Map map = new HashMap();
		map.put("tableName", dbInfo.getTable());
		map.put("id", id);
		map.put("orgId", targetOrgId);
		abstractDataManageDao.updateTempDataOrgId(map);
	}

	/** 根据传入id批量认领，返回成功或失败的认领结果队列 */
	public Integer stepClaimTempByIds(Class t, Long[] tempIds, Long targertOrgId)
			throws Exception {

		String table = DataManageBaseInfoUtil.getDataManageInfoByType(
				StringUtil.firstCharLowCase(t.getSimpleName())).getTable();
		Integer result = 0;
		String orgCode = organizationDubboService.getSimpleOrgById(targertOrgId)
				.getOrgInternalCode();
		result = abstractDataManageDao.stepClaimTempByIds(table, tempIds,
				targertOrgId, orgCode);
		return result;

	}

	public void deleteTempByIds(Class t, Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("传入参数为空");
		}
		for (int i = 0; i < ids.length; i++) {
			if (ids[i] == null || ids[i] < 0L) {
				throw new BusinessValidationException("传入参数为空");
			}
			if (!deleteAvailable(t, ids[i])) {
				throw new BusinessValidationException("请选择一条或多条可删除记录，再进行删除！");
			} else {
				if (t.getName().equals(HandicappedTemp.class.getName())) {
					HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
					handicappedSdisabilityType.setId(ids[i]);
					handicappedSdisabilityType.setDataManage(true);
					handicappedService
							.deleteDisbilityType(handicappedSdisabilityType);
				}
				abstractDataManageDao.deleteTempById(t, ids[i]);
			}
		}
	}

	/** 可否删除验证 */
	private boolean deleteAvailable(Class t, Long deleteId) {
		BaseDomain deleteTemp = abstractDataManageDao.getTempById(t, deleteId);
		Method method;
		ClaimDetail claimDetail = new ClaimDetail();
		try {
			method = t.getDeclaredMethod("getClaimDetail");
			claimDetail = (ClaimDetail) method.invoke(deleteTemp);
		} catch (Exception e) {
			throw new ServiceValidationException("删除数据出错", e);
		}
		if (claimDetail == null || claimDetail.getClaimState() > 1) {
			return false;
		}
		return true;
	}

	/** 单个进行相关验证后进行认领 */
	public final ClaimResult<T> updateAndClaim(T temp, Boolean fromRepeat)
			throws Exception {
		ClaimResult<T> vo = new ClaimResult();
		vo.setTemp(temp);

		Integer claimState = ReflectionUtil.getTargetClaimDetail(temp)
				.getClaimState();
		// Long targetOrgId = ((Organization) ReflectionUtil
		// .executeTargetGetMethod(temp, "getOrganization")).getId();
		// 已经认领
		if (claimState != null
				&& Constants.ClaimState.CLAIMED.equals(claimState)) {
			ClaimState state = new ClaimState();
			state.setSuccessful(false);
			state.setErrorType(Constants.ClaimErrorType.REPEAT_CLAIM);
			state.setErrorInfo(Constants.ClaimErrorType
					.getDisplayName(Constants.ClaimErrorType.REPEAT_CLAIM));
			vo.setClaimState(state);
			return vo;

		}
		try {
			validCanClaim(temp);

			validExistTarget(temp, fromRepeat);

			validDataInput(temp);
		} catch (Exception e) {
			ClaimState state = new ClaimState();
			state.setSuccessful(false);
			state.setErrorType(Integer.parseInt(e.getMessage()));
			state.setErrorInfo(ClaimErrorType.getDisplayName(Integer.parseInt(e
					.getMessage())));
			vo.setClaimState(state);
			return vo;
		}
		asynchronousProcess(temp);

		// updateClaimTempInfo(temp);
		Long olderId = temp.getId();
		Long inId = claimToTarget(temp); // 1.修改被认领数据的
		temp.setId(olderId);
		try {
			ClaimDetail claim = getClaimDetail(inId);
			this.updateClaimDetail(temp, claim);

			// temp = this.getTempById(temp.getClass(), temp.getId());

			this.updateTheSameDataPassiveClaim(temp);
		} catch (Exception e) {
			throw new ServiceValidationException("认领数据出错", e);
		}
		ClaimState state = new ClaimState();
		state.setSuccessful(true);
		vo.setClaimState(state);

		return vo;
	}

	/**
	 * 更新被认领数据的认领状态
	 * 
	 * @param temp
	 * @param passiveClaimDetail
	 */
	public void updateTheSameDataPassiveClaim(T temp) {
		ClaimDetail passiveClaimDetail = ReflectionUtil
				.getTargetClaimDetail(this.getTempById(temp.getClass(),
						temp.getId()));
		passiveClaimDetail.setDispose(Constants.DisposeState.DISPOSED);
		abstractDataManageDao.updateTheSamePopulationPassiveClaim(temp,
				this.getPassiveClaimDetail(passiveClaimDetail));
	}

	/**
	 * 更新认领状态
	 * 
	 * @param temp
	 * @param claim
	 */
	public void updateClaimDetail(T temp, ClaimDetail claim) {
		abstractDataManageDao.updateClaimDetail(temp, claim);

	}

	/**
	 * 通过认领状态获取被认领的数据的认领状态
	 * 
	 * @param claim
	 * @return
	 */
	private ClaimDetail getPassiveClaimDetail(ClaimDetail claim) {
		Integer claimState = claim.getClaimState();
		if (Constants.ClaimState.CLAIMED.equals(claimState)) {
			claim.setClaimState(Constants.ClaimState.BECLAIMED);
		}
		if (Constants.ClaimState.UNCLAIM.equals(claimState)) {
			claim.setClaimState(Constants.ClaimState.BEUNCLAIM);
		}
		return claim;
	}

	/** 根据id和类型获取对象 */
	public T getTempById(Class t, Long id) {
		return (T) abstractDataManageDao.getTempById(t, id);
	}

	/** 修改人员基础信息 */
	public T updateTempBase(Class t, T temp) {
		abstractDataManageDao.updateTempBase(temp);
		return getTempById(t, temp.getId());
	}

	/** 修改人员业务信息 */
	public T updateTempBusiness(Class t, T temp) {
		// 残疾人类型特殊处理
		if (temp instanceof HandicappedTemp) {
			HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
			handicappedSdisabilityType.setId(temp.getId());
			handicappedSdisabilityType.setDataManage(true);
			List<Long> typeIds = ((Handicapped) temp).getDisabilityTypeIds();
			List<Long> levelIds = ((Handicapped) temp).getDisabilityLevelIds();
			if (typeIds != null && typeIds.size() != 0 && levelIds != null
					&& levelIds.size() == typeIds.size()) {
				handicappedService
						.deleteDisbilityType(handicappedSdisabilityType);
				handicappedService.saveHandicappedList(handicappedService
						.editHandicapped((Handicapped) temp, true), temp
						.getId());
			}
		}
		if (temp instanceof IdleYouthTemp) {
			IdleYouthTemp idleYouthTemp = (IdleYouthTemp) temp;
			if (!idleYouthTemp.isStayInSchool()) {
				idleYouthTemp.setSchoolName(null);
			}
			temp = (T) idleYouthTemp;
		}
		abstractDataManageDao.updateTempBusiness(temp);
		return getTempById(t, temp.getId());
	}

	/** 修改场所信息 */
	public T updateLocationTempBase(Class t, T temp) {
		abstractDataManageDao.updateLocationTempBase(temp);
		return getTempById(t, temp.getId());
	}

	/** 修改部件信息 */
	public T updateDustbinTempBase(Class t, T temp) {
		abstractDataManageDao.updateDustbinTempBase(temp);
		return getTempById(t, temp.getId());
	}

	/** 修改楼宇信息 */
	public T updateBuilddatasTempBase(Class t, T temp) {
		abstractDataManageDao.updateBuilddatasTempBase(temp);
		return getTempById(t, temp.getId());
	}

	public void unDoClaimTempByIds(Class t, Long[] ids) {
		String table = DataManageBaseInfoUtil.getTableByType(t.getSimpleName());
		// /List<Long> targetIds = new ArrayList<Long>();
		T temp = null;
		for (Long id : ids) {
			temp = this.getTempById(t, id);
			ClaimDetail claim = ReflectionUtil.getTargetClaimDetail(temp);
			if (Constants.ClaimState.CLAIMED.equals(claim.getClaimState())) {
				Long inId = claim.getInId();
				// targetIds.add(inId);
				this.updateTheSameDataPassiveClaim(temp);
				unDoClaimDetailByTableAndIds(table, new Long[] { id }, claim);
				deleteTargetById(new Long[] { inId }, temp);
			}

		}

	}

	private void unDoClaimDetailByTableAndIds(String table, Long[] ids,
			ClaimDetail claim) {
		ClaimDetail detail = new ClaimDetail();
		detail.setClaimUser(new User());
		detail.setClaimDate(null);
		detail.setClaimState(Constants.ClaimState.UNCLAIM);
		detail.setClaimUserName(null);
		detail.setClaimOrg(new Organization());
		detail.setInId(null);
		detail.setDistrictOrgId(claim.getDistrictOrgId());

		abstractDataManageDao.unDoClaimDetailByTableAndIds(table, ids, detail);

	}

	private void deleteTargetById(Long[] ids, T t) {
		String methodName = "delete"
				+ ReflectionUtil.getClassNameRemoveTempSuffix(t) + "ByIds";
		Object object = SpringBeanUtil
				.getBeanFromSpringByBeanName(ReflectionUtil
						.getServiceBeanName(t));
		
		try {
			Organization org = ReflectionUtil.getTargetOrganization(t);
			if (null != org && null != org.getId()) {
				String shardCode = shardConversion.getShardCode(org.getId());
				t.setShardCode(shardCode);
			}
			if ("Builddatas".equals(ReflectionUtil
					.getClassNameRemoveTempSuffix(t))) {
				Method method = object.getClass().getMethod(methodName,
						Long[].class, String.class);
				method.invoke(object, (Object) ids, t.getShardCode());
			} else {
				Method method = object.getClass().getMethod(methodName,
						Long[].class);
				method.invoke(object, (Object) ids);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("删除目标数据出错"
					+ t.getClass().getSimpleName(), e);
		}

	}

	/** vo查询数据的属性赋值 */
	private SearchVo autoFilledVo(SearchVo searchVo, String sidx, String sord) {
		if (null == searchVo) {
			searchVo = new SearchVo();
		}
		// 认领的列表，查询所有一条线的记录，并且已经处理过的，可以认领的数据
		if (Constants.ListMode.CLAIM_LIST.equals(searchVo.getMode())) {
			searchVo.setTargetOrg(ThreadVariable.getOrganization());
			searchVo.setDispose(Constants.DisposeState.DISPOSED);
			searchVo.setClaimAvailable(Constants.ClaimAvailable.CLAIMAVAILABLE_CAN);
		}
		// 导入的列表，查询所有同部门导入者的数据
		if (Constants.ListMode.IMPORT_LIST.equals(searchVo.getMode())) {
			searchVo.setImportDepartment(ThreadVariable.getOrganization());
		}
		searchVo.setSortField(sidx);
		searchVo.setOrder(sord);
		return searchVo;
	}

	/** 获得一个认领明细对象 */
	private ClaimDetail getClaimDetail(Long inId) {
		ClaimDetail result = new ClaimDetail();
		result.setClaimDate(new Date());
		result.setClaimOrg(ThreadVariable.getOrganization());
		result.setClaimState(Constants.ClaimState.CLAIMED);
		result.setClaimUser(ThreadVariable.getUser());
		result.setInId(inId);
		return result;
	}

	/** 修改数据 */
	private Long claimToTarget(T temp) throws Exception {
		TargetDataVo vo = getTargetDataVo(temp);
		DataManageBaseInfo dbInfo = DataManageBaseInfoUtil
				.getDataManageInfoByType(ReflectionUtil
						.getClassNameSuffix(temp));
		Long id;
		SourcesStateService sourcesStateService = (SourcesStateService) SpringBeanUtil
				.getBeanFromSpringByBeanName("sourcesStateServiceImpl");
		if (null != vo) {
			temp.setId(vo.getId());
			if (temp instanceof HouseholdStaffTemp) {
				if (null == temp.getClass().getMethod("getLogOut").invoke(temp)) {
					temp.getClass().getMethod("setLogOut", Long.class)
							.invoke(temp, 0L);
				}
				id = executeSetMethod(temp, "updateHouseholdStaffBaseInfo")
						.getId();
				sourcesStateService.updateSourcesStateById(
						dbInfo.getBaseTable(), id,
						ConstantsProduct.SourcesState.VERIFY);
				id = executeSetMethod(temp, "updateHousePopulationBusinessInfo")
						.getId();
			} else {
				id = executeSetMethod(
						temp,
						"update"
								+ ReflectionUtil
										.getClassNameRemoveTempSuffix(temp))
						.getId();
			}
			sourcesStateService.updateSourcesStateById(dbInfo.getBaseTable(),
					id, ConstantsProduct.SourcesState.VERIFY);

		} else {
			id = executeSetMethod(temp,
					"add" + ReflectionUtil.getClassNameRemoveTempSuffix(temp))
					.getId();
			if (temp instanceof HandicappedTemp) {
				HandicappedSdisabilityType handicappedSdisabilityType = new HandicappedSdisabilityType();
				handicappedSdisabilityType.setId(temp.getId());
				handicappedSdisabilityType.setDisId(id);
				handicappedSdisabilityType.setDataManage(true);
				handicappedService
						.saveHandicappedSdisabilityTypeToReal(handicappedSdisabilityType);
			}
			if (!(temp instanceof BuilddatasTemp)) {
				sourcesStateService.updateSourcesStateById(
						dbInfo.getBaseTable(), id,
						ConstantsProduct.SourcesState.CLAIM);
			}
		}

		return id;
	}

	/** 执行基础库里的增改方法 */
	private BaseDomain executeSetMethod(T population, String methodName)
			throws Exception {
		String beanName = "";
		if (population instanceof OverseaPersonnel) {
			beanName = "overseaStaff";
		} else if (population instanceof EnterpriseTemp) {
			if (population.getClass().getSimpleName()
					.equals("FireSafetyEnterpriseTemp")) {
				beanName = getServiceBeanRemoveTempSuffix(population);
			} else {
				beanName = "enterprise";
			}
		} else if (population instanceof AidspopulationsTemp) {
			beanName = "aidspopulation";
		} else {
			beanName = getServiceBeanRemoveTempSuffix(population);
		}

		// safetyProductionService
		Object object = SpringBeanUtil.getBeanFromSpringByBeanName(beanName
				+ "Service");
		Method method = null;
		BaseDomain superInstance = null;
		if (!(population instanceof People)) {
			Class location = Class.forName(DataManageBaseInfoUtil
					.getDomainName(population.getClass().getSimpleName()));
			method = object.getClass().getMethod(methodName, location);
			return (BaseDomain) method.invoke(object,
					dataConvertForLocation(population));
		} else {
			method = object.getClass().getMethod(methodName,
					population.getClass().getSuperclass());
			superInstance = (BaseDomain) population.getClass().getSuperclass()
					.newInstance();
			PropertyUtil.copyAllPropertiesWithRecursion(population.getClass()
					.getSuperclass(), superInstance, population);
			superInstance.setSourcesState(ConstantsProduct.SourcesState.CLAIM);
			return (BaseDomain) method.invoke(object, superInstance);
		}

	}

	private String getServiceBeanRemoveTempSuffix(T population) {
		return changeFirstLower(population).substring(0,
				population.getClass().getSimpleName().lastIndexOf("Temp"));
	}

	/**
	 * 获取首字母小写的简单类名
	 * 
	 * @param population
	 * @return
	 */
	private String changeFirstLower(T population) {
		String className = population.getClass().getSimpleName();
		return className.substring(0, 1).toLowerCase() + className.substring(1);
	}

	/** 认领明细相关赋值 */
	private PageInfo autoFilledClaimDetail(PageInfo pageInfo) {
		for (Object obj : pageInfo.getResult()) {
			Method method;
			try {
				method = obj.getClass().getMethod("getClaimDetail");
				ClaimDetail claimDetail = (ClaimDetail) method.invoke(obj);
				// // ControllerHelper.proccessRelativeOrgNameByOrgClaimDetail(
				// claimDetail, claimDetail.getClaimOrg(),
				// organizationService);
			} catch (Exception e) {
				throw new ServiceValidationException("认领数据出错", e);
			}
		}
		return pageInfo;
	}

	public Boolean hasDuplicate(Class t, Long id, Long districtOrgId,
			String uniqueValue) {
		DataManageBaseInfo baseInfo = DataManageBaseInfoUtil
				.getDataManageInfoByType(StringUtil.firstCharLowCase(t
						.getSimpleName()));
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("tableName", baseInfo.getTable());
		if (DataManageBaseInfoTypes.POPULATION.equals(baseInfo.getMode())) {
			queryMap.put("uniqueColumn", "idCardNo");
		}
		// else if (DataManageBaseInfoTypes.HOUSE.equals(baseInfo.getMode())) {
		// queryMap.put("uniqueColumn", "address");
		// } 房屋唯一性判断暂时不做
		else if (DataManageBaseInfoTypes.LOCATION.equals(baseInfo.getMode())
				|| DataManageBaseInfoTypes.COMPANYPLACE.equals(baseInfo
						.getMode())) {
			queryMap.put("uniqueColumn", "name");
		} else {
			throw new BusinessValidationException("所属大类有误！");
		}
		queryMap.put("uniqueValue", uniqueValue);
		queryMap.put("orgValue", districtOrgId);
		queryMap.put("id", id);
		queryMap.put("claimAvailable",
				Constants.ClaimAvailable.CLAIMAVAILABLE_CAN);
		Map<String, Object> repeatData = abstractDataManageDao
				.hasDuplicate(queryMap);

		return null == repeatData ? false : true;

	}

	/**
	 * 验证是否可以认领，这个方法只在业务人口的认领中需要，并且只会发生在第二种模式下
	 * 
	 * @param temp
	 */
	public abstract void validCanClaim(T population) throws Exception;

	/**
	 * 不同的类型根据不同的方式去搜索是否有重复数据（人口：身份证，场所：场所名） 搜索为基础库中的对象
	 * 
	 * @param temp
	 */
	@SuppressWarnings("hiding")
	public abstract <T> TargetDataVo getTargetDataVo(T population)
			throws Exception;

	/**
	 * 验证是否有重复数据。 如果有重复数据，并且基本库中数据是注销的抛出注销数据的异常。
	 * 如果有重复数据，并且数据不是从比对重复数据过来的则抛出重复数据的异常
	 * 
	 * @param temp
	 * @param fromRepeat
	 * @throws Exception
	 */
	private void validExistTarget(T population, Boolean fromRepeat)
			throws Exception {
		if (fromRepeat != null && fromRepeat) {
			return;
		}
		TargetDataVo vo = getTargetDataVo(population);
		if (null != vo) {
			if (null != vo.getHasRepeatActualPopu()) {
				if (ClaimHasRepeatActualPopu.EXISTEDFOROTHERPEOPLE == vo
						.getHasRepeatActualPopu()) {
					throw new BusinessValidationException(
							String.valueOf(ClaimErrorType.NOTCLAIMFORATOTHERPEOPLE));
				} else if (ClaimHasRepeatActualPopu.DEATHFOROTHERORG == vo
						.getHasRepeatActualPopu()) {
					throw new BusinessValidationException(
							String.valueOf(ClaimErrorType.NOTCLAIMDEATHFORATOTHERORG));
				}
			}
			if (IsEmphasis.IsNotEmphasis.equals(vo.getLogout())) {
				int claimErrorType = ClaimErrorType.LOGOUT_DATA;
				if (population instanceof HouseholdStaffTemp) {
					claimErrorType = ClaimErrorType.LOGOUT_DATA_FORHOUSEHOLDSTAFF;
				} else if (population instanceof FloatingPopulationTemp) {
					claimErrorType = ClaimErrorType.LOGOUT_DATA_FORFLOAT;
				}
				throw new BusinessValidationException(
						String.valueOf(claimErrorType));
			}
			if (null == fromRepeat || !Boolean.TRUE.equals(fromRepeat)) {
				throw new BusinessValidationException(
						String.valueOf(ClaimErrorType.REPEAT));
			}
		}

	}

	/**
	 * 验证输入的数据是否符合要认领数据的规范
	 * 
	 * @param temp
	 */
	private void validDataInput(T population) {

		String beanName = "";
		// 此版本中安全生产重点在基础库中已无自己特有的验证类，因此和治安重点公用了enterPriseValidator来验证
		if (population.getClass().getSimpleName()
				.equals(DataManageBaseInfoTypes.SECURITY_ENTERPRISE_TEMP)
				|| population.getClass().getSimpleName()
						.equals(DataManageBaseInfoTypes.SAFETY_PRODUCTION_TEMP)
				|| population.getClass().getSimpleName()
						.equals(DataManageBaseInfoTypes.ENTERPRISEKEY_TEMP)
				|| population.getClass().getSimpleName()
						.equals(DataManageBaseInfoTypes.ENTERPRISEDOWNKEY_TEMP)) {
			beanName = "enterPriseValidator";
		} else if (population.getClass().getSimpleName()
				.equals(DataManageBaseInfoTypes.OVERSEA_PERSONNEL_TEMP)) {
			beanName = "overseaValidator";
		}
		// else if(){
		// beanName = "enterPriseValidator";
		// }
		else {
			beanName = getTargetValidatorBeanName(population);
		}
		// //进行判断,如果是场 所和房屋,对象类进行转换
		if (!(population instanceof People)) {
			population = dataConvertForLocation(population);
		}
		validator = (DomainValidator<T>) SpringBeanUtil
				.getBeanFromSpringByBeanName(beanName);
		ValidateResult result;
		if (population.getClass().getSimpleName()
				.equals(DataManageBaseInfoTypes.OVERSEA_PERSONNEL_TEMP)) {
			result = ((AbstractCountrymenValidator) validator)
					.validateSpecializedInfo(population);
		} else {
			population.setSourcesState(ConstantsProduct.SourcesState.CLAIM);
			result = validator.validate(population);
		}
		if (result.hasError()) {
			throw new BusinessValidationException(
					String.valueOf(ClaimErrorType.VALIDATE));
		}

	}

	public T dataConvertForLocation(T population) {
		return null;
	}

	/**
	 * 获取认领目录的validator
	 * 
	 * @param population
	 * @return
	 */
	private String getTargetValidatorBeanName(T population) {
		// 为消防安全重点而新写的条件判断
		if (population.getClass().getSimpleName()
				.equals("FireSafetyEnterpriseTemp")) {
			return getServiceBeanRemoveTempSuffix(population) + "Validate";
		} else {
			return getServiceBeanRemoveTempSuffix(population) + "Validator";
		}
	}

	/**
	 * 异步处理其他数据，只有业务人员需要重写
	 * 
	 * @param temp
	 */
	protected abstract void asynchronousProcess(T temp) throws Exception;

	/**
	 * 通过orgid和name来判断一个场所是否已经存在
	 * 
	 * @param orgId
	 * @param placeName
	 * @return
	 */
	public Boolean hasDuplicateLocation(T population, Long exceptedId) {
		T Location = (T) abstractDataManageDao
				.getLocationByNameAndOrgId(population);
		if (Location != null && !Location.getId().equals(exceptedId)) {
			return true;
		}
		return false;
	}

	public void setAbstractDataManageDao(
			AbstractDataManageDao abstractDataManageDao) {
		this.abstractDataManageDao = abstractDataManageDao;
	}
}
