package com.tianque.baseInfo.companyPlace.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.baseInfo.companyPlace.constant.IsKeyType;
import com.tianque.baseInfo.companyPlace.constant.KeyPlaceType;
import com.tianque.baseInfo.companyPlace.dao.CompanyPlaceBaseDAO;
import com.tianque.baseInfo.companyPlace.dao.CompanyPlaceDAO;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBase;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceBusiness;
import com.tianque.baseInfo.companyPlace.domain.CompanyPlaceConstants;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceBusinessVO;
import com.tianque.baseInfo.companyPlace.domain.vo.CompanyPlaceVo;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBusinessService;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceService;
import com.tianque.controller.ControllerHelper;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.systemLog.service.SystemLogService;
import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.DefaultSortAndPage;
import com.tianque.domain.KeyPlace;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.openLayersMap.domian.vo.KeyPlaceInfoVo;
import com.tianque.plugin.analyzing.domain.StatisticsBaseInfoAddCountVo;
import com.tianque.plugin.analyzing.service.CompanyPlaceLeaderViewService;
import com.tianque.plugin.serviceTeam.router.vo.ServiceMemberVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.dao.ServiceTeamMemberDao;
import com.tianque.plugin.serviceTeam.util.Constants;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.recoverDatas.service.RecoverDatasService;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("companyPlaceBaseService")
@Transactional
public class CompanyPlaceBaseServiceImpl extends LogableService implements
		CompanyPlaceBaseSevice {

	@Autowired
	private CompanyPlaceBaseDAO companyPlaceBaseDao;
	@Autowired
	private CompanyPlaceDAO companyPlaceDao;
	@Autowired
	private CompanyPlaceService companyPlaceService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private KeyPlaceService keyPlaceService;
	@Autowired
	private RecoverDatasService recoverDatasService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private CompanyPlaceLeaderViewService companyPlaceLeaderViewService;

	@Autowired
	private CompanyPlaceBusinessService companyPlaceBusinessService;
	@Autowired
	private ServiceTeamMemberDao serviceTeamMemberDao;
	@Qualifier("companyPlaceValidator")
	@Autowired
	private DomainValidator<CompanyPlace> domainValidator;

	private final static String CACHE_COMPANYPLACE = "cache_companyplace";

	@Override
	public PageResult<CompanyPlace> queryCompanyPlaceForPageResult(
			CompanyPlaceVo companyPlaceVo, String modulKey,
			DefaultSortAndPage defaultSortAndPage) {
		if (null == companyPlaceVo) {
			companyPlaceVo = new CompanyPlaceVo();
		}
		companyPlaceVo.setSortField(defaultSortAndPage.getSidx());
		companyPlaceVo.setOrder(defaultSortAndPage.getSord());
		companyPlaceVo = setCompanyPlaceVoData(companyPlaceVo, modulKey);
		PageResult<CompanyPlace> companyPlaceVoList = companyPlaceBaseDao
				.queryCompanyPlaceForPageResult(companyPlaceVo,
						defaultSortAndPage.getPage(),
						defaultSortAndPage.getRows());
		return putIntoNewDateAndRentalHouse(companyPlaceVoList);
	}

	// 把有无治安负责人放进去
	public PageResult<CompanyPlace> putIntoNewDateAndRentalHouse(
			PageResult<CompanyPlace> companyPlaceVoList) {
		if (companyPlaceVoList == null
				|| companyPlaceVoList.getResultList().size() == 0) {
			return companyPlaceVoList;
		}
		List<CompanyPlace> pageResultList = companyPlaceVoList.getResultList();
		List<CompanyPlace> newPageResultList = new ArrayList<CompanyPlace>();
		for (CompanyPlace temp : pageResultList) {
			ServiceMemberVo serviceMemberVo = new ServiceMemberVo();
			serviceMemberVo.setObjectType(temp.getClassifiCationEn());
			serviceMemberVo.setObjectId(temp.getCid());
			serviceMemberVo.setOnDuty(Constants.OnDudy.ONDUDY);
			if (serviceTeamMemberDao
					.findServiceMembersNumByServiceMemberVo(serviceMemberVo) > 0) {
				temp.setHasServiceTeamMember(CompanyPlaceConstants.HASSERVICETEAMMEMBER_IS);
			} else {
				temp.setHasServiceTeamMember(CompanyPlaceConstants.HASSERVICETEAMMEMBER_NO);
			}
			Date lastRecordTime = companyPlaceBaseDao.getLastRecordTime(temp);
			if (lastRecordTime != null) {
				temp.setLastRecordTime(lastRecordTime);
			}
			temp.setPollution(judgeCompanyIsPollution(temp.getCid()));
			newPageResultList.add(temp);
		}
		companyPlaceVoList.setResultList(newPageResultList);
		return companyPlaceVoList;
	}

	// 判断是否是污染源
	private boolean judgeCompanyIsPollution(Long cid) {
		List<CompanyPlaceBusiness> cbList = companyPlaceBusinessService
				.queryCompanyPlaceBusinessByCompanyPlaceIdForList(cid);
		if (cbList != null && cbList.size() > 0) {
			for (CompanyPlaceBusiness companyPlaceBusiness : cbList) {
				if (IsKeyType.POLLUTION_SOURCE_TYPE.equals(companyPlaceBusiness
						.getIsKeyType())) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	// 设置分类查询的条件
	private CompanyPlaceVo setCompanyPlaceVoData(CompanyPlaceVo companyPlaceVo,
			String modulKey) {
		if (companyPlaceVo == null || companyPlaceVo.getOrg() == null
				|| companyPlaceVo.getOrg().getId() == null) {
			throw new BusinessValidationException("参数错误！");
		}
		Organization org = organizationDubboService
				.getSimpleOrgById(companyPlaceVo.getOrg().getId());
		companyPlaceVo.setOrg(org);
		companyPlaceVo.setOrginternalcode(org.getOrgInternalCode());
		if (StringUtil.isStringAvaliable(modulKey)) {
			// 规上规下
			if (ModulTypes.isSizedEnterp(modulKey)) {
				PropertyDict modul = propertyDictService
						.findPropertyDictByDomainNameAndDictDisplayName(
								PropertyTypes.ENTERPRISE_TYPE,
								ModulTypes.SIZED_ENTERPRISE.get(modulKey));
				companyPlaceVo.setScaleType(modul);
			}
			// 安全生产重点、消防安全重点、治安重点、污染源
			else if (ModulTypes.isModulKey(modulKey)) {
				companyPlaceVo.setConditionsValue(ModulTypes.mapModulKey
						.get(modulKey));
			} else {
				companyPlaceVo.setClassifiCationEn(modulKey);
			}
		}
		return companyPlaceVo;
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.COMPANYPLACE_KEY)
	public void delteCompanyPlaceBaseByIds(String baseIds, String modulKey) {
		if (!StringUtil.isStringAvaliable(baseIds)) {
			throw new BusinessValidationException("数据传输错误!");
		}
		String[] baseIdsArray = baseIds.split(",");
		List<Long> idList = new ArrayList<Long>();
		for (String baseId : baseIdsArray) {
			idList.add(Long.valueOf(baseId));
		}
		if (idList.size() == 0) {
			throw new BusinessValidationException("数据传输错误!");
		}
		try {
			// 由于有批量删除，业务比较特殊，在删除之前添加删除日志
			saveDeleteCompanyPlaceLog(idList, modulKey);
			if (StringUtils.isNotBlank(modulKey)
					&& (modulKey.equals(ModulTypes.ENTERPRISE_KEY) || modulKey
							.equals(ModulTypes.ENTERPRISEDOWN_KEY))) {
				// 规上 规下update
				deleteCompanyPlaceBusinessByEnterprise(idList);
			} else if (StringUtils.isNotBlank(modulKey)
					&& ModulTypes.isModulKey(modulKey)) {
				deleteCompanyPlaceBusinessByIdAndIsKeyTypeByIds(idList,
						ModulTypes.mapModulKey.get(modulKey), modulKey);
			} else {
				// 备份数据
				toSaveCompanyInfoRecoverData(idList);
				// 删除company中的数据
				batchDeleteCompanyPlaceBaseByIds(idList);
				companyPlaceService.batchDeleteCompanyPlace(idList);
				// 删除对应业务的数据
				deleteCompanyPlaceBusinessByIds(idList);
			}
			// 删除 keyPlaces
			deleteKeyPlaceByIds(idList, modulKey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"执行CompanyPlaceBaseServiceImpl中的delteCompanyPlaceBaseByIds方法出现错误：",
					"删除出现错误！", e);
		}
	}

	// 删除业务信息(无类型删除)
	private void deleteCompanyPlaceBusinessByIds(List<Long> ids) {
		for (Long id : ids) {
			companyPlaceBusinessService
					.deleteCompanyPlaceBusinessByCompanyPlaceID(id);
		}
	}

	// 删除业务信息（带类型删除）
	private void deleteCompanyPlaceBusinessByIdAndIsKeyTypeByIds(
			List<Long> ids, Long keyType, String modulKey) {
		for (Long cid : ids) {
			companyPlaceBusinessService
					.deleteCompanyPlaceBusinessByIdAndIsKeyType(cid, keyType,
							modulKey);
		}
	}

	// 删除规上业务信息
	private void deleteCompanyPlaceBusinessByEnterprise(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		companyPlaceBaseDao.updateCompanyPlaceScaleTypeByCids(map);
	}

	// 删除keyplace记录
	private void deleteKeyPlaceByIds(List<Long> ids, String type) {
		if (StringUtils.isNotBlank(type) && ModulTypes.isMapKeyplace(type)) {
			type = "'" + type + "'";
		} else {
			type = KeyPlaceType.mapTypes;
		}
		// for (Long cid : ids) {
		// keyPlaceService.deleteKeyPlaceByIdAndInType(cid, type);
		// }
		keyPlaceService.deleteKeyPlaceByIdsAndInType(ids, type);
	}

	private void deleteKeyPlaceByIds(CompanyPlace companyPlace, String type) {
		keyPlaceService
				.deleteKeyPlaceByIdAndInType(companyPlace.getCid(), type);
	}

	@Override
	public CompanyPlace addCompanyPlaceBase(CompanyPlace companyPlace,
			String modulKey, Long id) {
		if (StringUtils.isNotBlank(modulKey)) {
			companyPlace.setCompanyPlaceSource(modulKey);
		}
		if (null == companyPlace || null == companyPlace.getOrg()
				|| null == companyPlace.getOrg().getId()) {
			throw new BusinessValidationException("参数传递错误");
		}
		if (companyPlace.getPcOrMobile() == null) {
			companyPlace.setPcOrMobile(ModulTypes.PCORMOBILE_PC);
		}
		try {
			CompanyPlaceBaseValidator(companyPlace);
			Organization org = organizationDubboService
					.getSimpleOrgById(companyPlace.getOrg().getId());
			companyPlace.setOrg(org);
			companyPlace.setOrginternalcode(org.getOrgInternalCode());
			// 检查缓存中是否存在记录
			if (checkDataExitInCache(companyPlace)) {
				return companyPlace;
			}

			if (!checkCompanyPlaceHas(companyPlace)) {
				throw new BusinessValidationException("该网格下已存在同类型且名称为"
						+ companyPlace.getName() + "的单位场所！");
			}
			Long companyPlaceBaseKey = null;
			companyPlaceBaseKey = companyPlaceBaseDao
					.addCompanyPlaceBase(companyPlace);
			// 1 add CompanyPlace
			if (null == companyPlaceBaseKey) {
				throw new BusinessValidationException("新增基本信息出错!");
			}
			companyPlace.setBaseId(companyPlaceBaseKey);
			Long cid = companyPlaceService.addCompanyPlaceToBase(companyPlace);
			if (null == cid) {
				throw new BusinessValidationException("新增单位场所信息出错!");
			}
			companyPlace = getCompanyPlaceInfoByCid(cid);

			// 2 add business
			if (null != modulKey && !"".equals(modulKey)) {
				CompanyPlaceBusinessVO businessVO = new CompanyPlaceBusinessVO();
				if (ModulTypes.isModulKey(modulKey)) {
					businessVO = setBusinessData(businessVO,
							ModulTypes.mapModulKey.get(modulKey));
				} else if (ModulTypes.isSizedEnterp(modulKey)) {
					manageKeyPlace(null, companyPlace, modulKey, null, null);
				}
				companyPlaceBusinessService.addCompanyPlaceBusiness(businessVO,
						cid, modulKey);
			}

			addEnterPrise("add", modulKey, companyPlace, null, null);

			manageKeyPlace(null, companyPlace,
					companyPlace.getClassifiCationEn(), null, null);
			// 新增日志
			saveAddComanyPlaceLog(companyPlace, modulKey,
					ModulTypes.PCORMOBILE_PC);
		} catch (BusinessValidationException e) {
			throw new BusinessValidationException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的addCompanyPlaceBase方法出现异常，原因：",
					"新增出现错误！", e);
		} finally {
			cleanDataInCache(companyPlace);
		}
		return companyPlace;
	}

	@Override
	public CompanyPlace addCompanyPlaceBaseForMobile(CompanyPlace companyPlace,
			String modulKey, Long id) {
		if (StringUtils.isNotBlank(modulKey)) {
			companyPlace.setCompanyPlaceSource(modulKey);
		}
		if (null == companyPlace || null == companyPlace.getOrg()
				|| null == companyPlace.getOrg().getId()) {
			throw new BusinessValidationException("参数传递错误");
		}
		if (null == companyPlace.getPcOrMobile()) {
			companyPlace.setPcOrMobile(ModulTypes.PCORMOBILE_MO);
		}
		try {
			CompanyPlaceBaseValidator(companyPlace);
			Organization org = organizationDubboService
					.getFullOrgById(companyPlace.getOrg().getId());
			companyPlace.setOrg(org);
			companyPlace.setOrginternalcode(org.getOrgInternalCode());
			if (checkDataExitInCache(companyPlace)) {
				return companyPlace;
			}
			// 点击的是上一步，执行更新操作
			if (null != id) {
				updateCompanyPalceForAll(companyPlace, modulKey);
				return getCompanyPlaceInfoByCid(id);
			}

			if (!checkCompanyPlaceHas(companyPlace)) {
				throw new BusinessValidationException("同一网格已存在名称为"
						+ companyPlace.getName() + "的单位！");
			}

			Long companyPlaceBaseKey = null;
			companyPlaceBaseKey = companyPlaceBaseDao
					.addCompanyPlaceBase(companyPlace);
			// 1 add CompanyPlace
			if (null == companyPlaceBaseKey) {
				throw new BusinessValidationException("新增基本信息出错!");
			}
			companyPlace.setBaseId(companyPlaceBaseKey);
			Long companyPlaceKey = companyPlaceService
					.addCompanyPlaceToBase(companyPlace);

			addEnterPrise("add", modulKey, companyPlace, null, null);

			manageKeyPlace(null, companyPlace,
					companyPlace.getClassifiCationEn(), null, null);

			companyPlace = getCompanyPlaceInfoByCid(companyPlaceKey);
			saveAddComanyPlaceLog(companyPlace, modulKey,
					ModulTypes.PCORMOBILE_MO);
		} catch (BusinessValidationException e) {
			throw new BusinessValidationException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的addCompanyPlaceBase方法出现异常，原因：",
					"新增出现错误！", e);
		} finally {
			cleanDataInCache(companyPlace);
		}
		return companyPlace;
	}

	// 添加业务信息
	private CompanyPlaceBusinessVO setBusinessData(
			CompanyPlaceBusinessVO businessVO, Long mudulType) {
		CompanyPlaceBusiness business = new CompanyPlaceBusiness();
		business.setIsKeyType(mudulType);
		if (IsKeyType.PRODUCTION_KEY_TYPE.equals(mudulType)) {
			businessVO.setProKeyCompanyPlaceBusiness(business);
		} else if (IsKeyType.FIRESAFETY_KEY_TYPE.equals(mudulType)) {
			businessVO.setFireSafetyKeyCompanyPlaceBusiness(business);
		} else if (IsKeyType.SECURITY_KEY_TYPE.equals(mudulType)) {
			businessVO.setSecurityKeyCompanyPlaceBusiness(business);
		} else if (IsKeyType.POLLUTION_SOURCE_TYPE.equals(mudulType)) {
			businessVO.setPollSourceCompanyPlaceBusiness(business);
		}
		return businessVO;
	}

	private void addEnterPrise(String mode, String modulKey,
			CompanyPlace companyPlace, List<KeyPlace> list, KeyPlace oldKeyPlace) {
		// 3 add keyPlace
		if (companyPlace.getScaleType() != null
				&& companyPlace.getScaleType().getId() != null) {
			PropertyDict modul = propertyDictService
					.getPropertyDictById(companyPlace.getScaleType().getId());
			if (modul != null
					&& modul.getDisplayName().equals(ModulTypes.Enterprise)) {
				manageKeyPlace(null, companyPlace, ModulTypes.ENTERPRISE_KEY,
						null, null);
			} else {
				manageKeyPlace(null, companyPlace,
						ModulTypes.ENTERPRISEDOWN_KEY, null, null);
			}
		}
		if (DialogMode.EDIT_MODE.equals(mode)) {
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					KeyPlace keyPlace = list.get(i);
					manageKeyPlace(keyPlace, companyPlace,
							companyPlace.getClassifiCationEn(),
							DialogMode.EDIT_MODE, oldKeyPlace);
				}
			}
		}
	}

	private void manageKeyPlace(KeyPlace keyPlace, CompanyPlace companyPlace,
			String type, String mode, KeyPlace oldKeyPlace) {
		if (mode != null && DialogMode.EDIT_MODE.equals(mode)) {
			if (companyPlace == null || companyPlace.getCid() == null
					|| StringUtils.isBlank(companyPlace.getName())
					|| StringUtils.isBlank(companyPlace.getOrginternalcode())) {
				throw new BusinessValidationException("参数错误!");
			}
		}
		if (keyPlace != null) {
			if (mode != null && DialogMode.EDIT_MODE.equals(mode)) {
				addKeyPlace(mode, companyPlace, type, keyPlace, oldKeyPlace);
			} else {
				updateKeyPlace(companyPlace, type, keyPlace);
			}
		} else {
			addKeyPlace(mode, companyPlace, type, keyPlace, oldKeyPlace);
		}
	}

	private void addKeyPlace(String mode, CompanyPlace companyPlace,
			String type, KeyPlace keyPlace, KeyPlace oldKeyPlace) {
		KeyPlace newKeyPlace = new KeyPlace();
		newKeyPlace.setId(companyPlace.getCid());
		newKeyPlace.setOrgId(companyPlace.getOrg().getId());
		newKeyPlace.setName(companyPlace.getName());
		newKeyPlace.setOrgInternalCode(companyPlace.getOrginternalcode());
		newKeyPlace.setType(type);
		newKeyPlace.setAddress(companyPlace.getAddress());
		newKeyPlace.setChargePerson(companyPlace.getUserName());
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(companyPlace.getName());
		newKeyPlace.setFullPinyin((String) pinyin.get("fullPinyin"));
		newKeyPlace.setSimplePinyin((String) pinyin.get("simplePinyin"));
		if (mode != null && DialogMode.EDIT_MODE.equals(mode)) {
			newKeyPlace.setId(keyPlace.getId());
			newKeyPlace.setOrgId(keyPlace.getOrgId());
			newKeyPlace.setType(keyPlace.getType());
			newKeyPlace.setOrgInternalCode(keyPlace.getOrgInternalCode());
		}
		if (oldKeyPlace != null && oldKeyPlace.getOpenLayersMapInfo() != null
				&& oldKeyPlace.getBuildDatasId() != null) {
			newKeyPlace
					.setOpenLayersMapInfo(oldKeyPlace.getOpenLayersMapInfo());
			newKeyPlace.setBuildDatasId(oldKeyPlace.getBuildDatasId());
		}
		keyPlaceService.addKeyPlace(newKeyPlace);
		updateTwoDimensionMap(newKeyPlace);
	}

	private void updateKeyPlace(CompanyPlace companyPlace, String type,
			KeyPlace keyPlace) {
		keyPlace.setName(companyPlace.getName());
		keyPlace.setType(type);
		keyPlace.setAddress(companyPlace.getAddress());
		keyPlace.setChargePerson(companyPlace.getUserName());
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(companyPlace.getName());
		keyPlace.setFullPinyin((String) pinyin.get("fullPinyin"));
		keyPlace.setSimplePinyin((String) pinyin.get("simplePinyin"));
		keyPlaceService.updateKeyPlace(keyPlace);
	}

	@Override
	public CompanyPlace getCompanyPlaceInfoByCid(Long cid) {
		if (cid == null) {
			throw new BusinessValidationException("查询出错!");
		}
		CompanyPlace companyPlace = companyPlaceBaseDao
				.getCompanyPlaceInfoByCid(cid);
		companyPlace.getOrg().setOrgName(
				ControllerHelper.getOrganizationRelativeName(companyPlace
						.getOrg().getId(), organizationDubboService));
		return companyPlace;
	}

	@Override
	public void updateCompanyPlaceBaseById(CompanyPlace companyPlace) {
		try {
			companyPlaceBaseDao.updateCompanyPlaceBaseById(companyPlace);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的updateCompanyPlaceBaseById方法出现异常，原因：",
					"更新出现错误！", e);
		}

	}

	@Override
	public void updateCompanyPalceForAll(CompanyPlace companyPlace,
			String modulKey) {
		try {
			if (companyPlace == null || companyPlace.getCid() == null) {
				throw new BusinessValidationException("参数错误！");
			}
			if (StringUtils.isNotBlank(modulKey)) {
				companyPlace.setCompanyPlaceSource(modulKey);
			}
			CompanyPlace beforeCompanyPlace = getCompanyPlaceInfoByCid(companyPlace
					.getCid());
			List<KeyPlace> list = keyPlaceService.getKeyPlaceByIdInType(
					companyPlace.getCid(), KeyPlaceType.businessTypes);
			KeyPlace oldKeyPlace = keyPlaceService.getKeyPlaceByIdAndType(
					companyPlace.getCid(), companyPlace.getClassifiCationEn());

			// 在删除之前查询出keysplaces中，该场所对应的归上规、业务信息
			deleteKeyPlaceByIds(companyPlace, KeyPlaceType.mapTypes);

			addEnterPrise(DialogMode.EDIT_MODE, modulKey, companyPlace, list,
					oldKeyPlace);

			KeyPlace keyPlace = keyPlaceService.getKeyPlaceByIdAndType(
					companyPlace.getCid(), companyPlace.getClassifiCationEn());

			manageKeyPlace(keyPlace, companyPlace,
					companyPlace.getClassifiCationEn(), null, oldKeyPlace);

			CompanyPlaceBaseValidator(companyPlace);
			companyPlaceBaseDao.updateCompanyPlaceBaseById(companyPlace);
			companyPlaceService.updateCompanyPlaceByCid(companyPlace);
			// 修改日志
			saveUpdateCompanyPlaceLog(companyPlace, modulKey,
					beforeCompanyPlace);
		} catch (BusinessValidationException e) {
			throw new BusinessValidationException(e.getMessage());
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的updatgeCompanyPalceForAll方法出现异常，原因：",
					"更新出现错误！", e);
		}
	}

	@Override
	public void updateCompanyPlaceIsEmphasis(List<Long> ids,
			CompanyPlace companyPlace, String modulKey) {
		if (companyPlace == null) {
			throw new BusinessValidationException("参数错误！");
		}
		try {
			for (Long id : ids) {
				CompanyPlace companyPlaceTemp = companyPlaceBaseDao
						.getCompanyPlaceInfoByCid(id);
				if (companyPlace.getIsEmphasis() == null) {
					companyPlace.setIsEmphasis(ModulTypes.ISEMPHASIS);
				}
				companyPlaceTemp.setIsEmphasis(companyPlace.getIsEmphasis());
				companyPlaceTemp.setIsEmphasisDate(companyPlace
						.getIsEmphasisDate());
				companyPlaceTemp.setIsEmphasisReason(companyPlace
						.getIsEmphasisReason());
				companyPlaceTemp.setCompanyPlaceSource(modulKey);

				companyPlaceBaseDao
						.updateCompanyPlaceBaseEmphasisById(companyPlaceTemp);
				// 保存日志
				saveEmphasisCompanyPlaceLog(companyPlaceTemp, modulKey);
			}
			updateKeyPlaceIsEmphasis(ids,
					Long.valueOf(companyPlace.getIsEmphasis()), modulKey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的updateCompanyPlaceIsEmphasis方法出现异常，原因：",
					"设置关注状态出现错误", e);
		}
	}

	// 取消关注/关注 KeyPlaces
	private void updateKeyPlaceIsEmphasis(List<Long> ids, Long isEmphasis,
			String modulKey) {
		if (ids == null) {
			throw new BusinessValidationException("批量取消关注出现错误!");
		}
		keyPlaceService.emphasisKeyPlacesByIdsAndTypes(ids,
				KeyPlaceType.mapTypes, isEmphasis);
	}

	@Override
	public List<CompanyPlace> queryCompanyPlaceForList(
			CompanyPlaceVo companyPlaceVo, String modulKey) {
		companyPlaceVo = setCompanyPlaceVoData(companyPlaceVo, modulKey);
		List<CompanyPlace> companyPlaceVoList = companyPlaceBaseDao
				.queryCompanyPlaceForList(companyPlaceVo);
		return companyPlaceVoList;
	}

	@Override
	public String getCompanyPlaceBaseIdByNameAndOrgid(CompanyPlace companyPlace) {
		return companyPlaceBaseDao
				.getCompanyPlaceBaseIdByNameAndOrgid(companyPlace);
	}

	@Override
	public void transferValidate(Long id, Long toOrgId, Long orgId,
			String type, List<ErrorMessageVo> errorList, List<Long> errorIdList) {
		Organization toOrg = organizationDubboService.getSimpleOrgById(toOrgId);
		CompanyPlace companyPlace = getCompanyPlaceInfoByCid(id);
		companyPlace.setOrg(toOrg);
		if (!checkCompanyPlaceHas(companyPlace)) {
			// 已经存在该记录
			String message = "目标网格已经存在同类型的同名称的单位场所";
			ErrorMessageVo errorMessageVo = new ErrorMessageVo(
					companyPlace.getName(), type, toOrg.getOrgName(), null,
					message);
			errorList.add(errorMessageVo);
			errorIdList.add(id);
		}
	}

	@Override
	public void updateCompanyPlaceBaseOrgById(CompanyPlace companyPlace) {
		try {
			companyPlaceBaseDao.updateCompanyPlaceBaseOrgById(companyPlace);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的updateCompanyPlaceBaseOrgById方法出现异常，原因：",
					"更新出现错误！", e);
		}
	}

	@Override
	public List<CompanyPlaceBase> queryCompanyplaceInfoLikeNameForList(
			CompanyPlace companyPlace) {
		if (null == companyPlace) {
			throw new BusinessValidationException("参数出错!");
		}
		return companyPlaceBaseDao
				.queryCompanyplaceInfoLikeNameForList(companyPlace);
	}

	@Override
	public void batchDeleteCompanyPlaceBaseByIds(List<Long> ids) {
		try {
			companyPlaceBaseDao.batchDeleteCompanyPlaceBaseByIds(ids);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的batchDeleteCompanyPlaceBaseByIds方法出现异常，原因：",
					"删除出错!", e);
		}
	}

	private void CompanyPlaceBaseValidator(CompanyPlace companyPlace) {
		ValidateResult baseDataValidator = domainValidator
				.validate(companyPlace);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public boolean checkCompanyPlaceHas(CompanyPlace companyPlace) {
		if (companyPlace == null || companyPlace.getName() == null) {
			throw new BusinessValidationException("参数错误");
		}
		if (companyPlace.getOrg() == null
				|| companyPlace.getOrg().getId() == null) {
			throw new BusinessValidationException("参数错误");
		}
		return companyPlaceBaseDao
				.getCompanyPlaceBaseIdExcIdByNameAndOrgid(companyPlace) > 0 ? false
				: true;
	}

	@Override
	public CompanyPlace hasDuplicateCompanyPlace(Long orgId, String placeName,
			Long typeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("placeName", placeName);
		map.put("typeId", typeId);
		return companyPlaceDao.getPlaceByPlaceName(map);
	}

	// 从单位，场所中删除数据时备份数据到数据中心
	private void toSaveCompanyInfoRecoverData(List<Long> idList) {
		if (idList == null || idList.size() < 1) {
			throw new BusinessValidationException("没有要备份的数据");
		}
		try {
			for (Long cid : idList) {
				CompanyPlace recoverInfo = getCompanyPlaceInfoByCid(cid);
				if (recoverInfo != null) {
					recoverDatasService.deleteCompanyPlace(recoverInfo);
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException("备份单位场所信息到数据中心出错", e);
		}
	}

	@Override
	public void deleteCompanyPlaceBaseById(Long id) {
		try {
			companyPlaceBaseDao.deleteCompanyPlaceBaseById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("删除单位信息出错", e);
		}
	}

	// 检查缓存
	private boolean checkDataExitInCache(CompanyPlace companyPlace) {
		String key = MemCacheConstant.getCompanyPlaceKey(
				MemCacheConstant.CACHE_COMPANYPLACE, companyPlace.getName(),
				companyPlace.getClassifiCation().getId(), companyPlace.getOrg()
						.getId());
		return !cacheService.add(MemCacheConstant.COMPANYPLACE_NAMESPACE, key,
				300, "CACHE_COMPANYPLACE");

	}

	// 清除缓存
	public void cleanDataInCache(CompanyPlace companyPlace) {
		String key = MemCacheConstant.getCompanyPlaceKey(
				MemCacheConstant.CACHE_COMPANYPLACE, companyPlace.getName(),
				companyPlace.getClassifiCation().getId(), companyPlace.getOrg()
						.getId());
		cacheService.remove(MemCacheConstant.COMPANYPLACE_NAMESPACE, key);
	}

	/**
	 * 领导视图 统计
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsBaseInfo(Long orgId,
			String moduleKey) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return companyPlaceLeaderViewService.statisticsBaseInfo(orgId,
					moduleKey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的statisticsBaseInfo方法出现异常，原因：",
					"查询领导视图统计图出错!", e);
		}
	}

	/**
	 * 领导视图 线形图
	 */
	public List<StatisticsBaseInfoAddCountVo> statisticsSummary(Long orgId,
			String moduleKey) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			return companyPlaceLeaderViewService.statisticsSummary(orgId,
					moduleKey);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类CompanyPlaceBaseServiceImpl的statisticsSummary方法出现异常，原因：",
					"查询领导视图线性图出错!", e);
		}
	}

	// 删除日志
	private void saveDeleteCompanyPlaceLog(List<Long> idList, String modulKey) {
		try {
			String type = ModulTypes.getLogType(modulKey);
			for (Long cid : idList) {
				CompanyPlace temp = getCompanyPlaceInfoByCid(cid);
				systemLogService.log(
						INFO,
						type,
						OperatorType.DELETE,
						ThreadVariable.getSession().getUserName()
								+ OperatorType.toString(OperatorType.DELETE)
								+ ControllerHelper.getOrganizationRelativeName(
										temp.getOrg().getId(),
										organizationDubboService) + "下的" + type
								+ ":" + temp.getName(), ObjectToJSON
								.convertJSON(temp), temp.getCid() + "", "",
						temp.getName(), "");
			}
		} catch (Exception e) {
			throw new ServiceValidationException("保存系统日志出现错误", e);
		}
	}

	// 新增日志
	private void saveAddComanyPlaceLog(CompanyPlace companyPlace,
			String modulKey, Long pcOrMobile) {
		try {
			String type = ModulTypes.getLogType(modulKey);
			systemLogService
					.log(INFO,
							type,
							OperatorType.ADD,
							ThreadVariable.getSession().getUserName()
									+ "从"
									+ (pcOrMobile
											.equals(ModulTypes.PCORMOBILE_MO) ? "手机端"
											: "PC端")
									+ OperatorType.toString(OperatorType.ADD)
									+ "到"
									+ ControllerHelper
											.getOrganizationRelativeName(
													companyPlace.getOrg()
															.getId(),
													organizationDubboService)
									+ "的" + type + ":" + companyPlace.getName(),
							ObjectToJSON.convertJSON(companyPlace));
		} catch (Exception e) {
			throw new ServiceValidationException("保存系统日志出现错误", e);
		}
	}

	// 修改日志
	private void saveUpdateCompanyPlaceLog(CompanyPlace companyPlace,
			String modulKey, CompanyPlace beforeCompanyPlace) {
		try {
			String type = ModulTypes.getLogType(modulKey);
			systemLogService.log(
					INFO,
					type,
					OperatorType.UPDATE,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(OperatorType.UPDATE)
							+ ControllerHelper.getOrganizationRelativeName(
									beforeCompanyPlace.getOrg().getId(),
									organizationDubboService)
							+ "下的"
							+ ModulTypes.getCnNameByKey(modulKey)
							+ ":"
							+ (beforeCompanyPlace.getName().equals(
									companyPlace.getName()) ? companyPlace
									.getName() : beforeCompanyPlace.getName()
									+ "->" + companyPlace.getName()),
					ObjectToJSON.convertJSON(companyPlace),
					beforeCompanyPlace.getCid() + "", companyPlace.getCid()
							+ "", beforeCompanyPlace.getName(), companyPlace
							.getName());
		} catch (Exception e) {
			throw new ServiceValidationException("保存系统日志出现错误", e);
		}
	}

	// 关注日志
	private void saveEmphasisCompanyPlaceLog(CompanyPlace companyPlace,
			String modulKey) {
		try {
			String type = ModulTypes.getLogType(modulKey);
			Integer Operator = companyPlace.getIsEmphasis().equals(
					ModulTypes.ISEMPHASIS) ? OperatorType.EMPHASISE
					: OperatorType.CANCELEMPHASISE;
			systemLogService.log(
					INFO,
					type,
					Operator,
					ThreadVariable.getSession().getUserName()
							+ OperatorType.toString(Operator)
							+ ControllerHelper.getOrganizationRelativeName(
									companyPlace.getOrg().getId(),
									organizationDubboService) + "下的"
							+ ModulTypes.getCnNameByKey(modulKey) + ":", null);
		} catch (Exception e) {
			throw new ServiceValidationException("保存系统日志出现错误", e);
		}
	}

	// 污染源地图数据更改
	private void updateTwoDimensionMap(KeyPlace keyPlace) {
		KeyPlaceInfoVo keyPlaceInfoVo = new KeyPlaceInfoVo();
		if (keyPlace != null && keyPlace.getOpenLayersMapInfo() != null) {
			keyPlaceInfoVo.setLat(keyPlace.getOpenLayersMapInfo()
					.getCenterLat());
			keyPlaceInfoVo.setLon(keyPlace.getOpenLayersMapInfo()
					.getCenterLon());
			keyPlaceInfoVo.setLat2(keyPlace.getOpenLayersMapInfo()
					.getCenterLat2().equals("0.0") ? "" : keyPlace
					.getOpenLayersMapInfo().getCenterLat2());
			keyPlaceInfoVo.setLon2(keyPlace.getOpenLayersMapInfo()
					.getCenterLon2().equals("0.0") ? "" : keyPlace
					.getOpenLayersMapInfo().getCenterLon2());
			keyPlaceInfoVo.setBuildDataId(Long.valueOf(keyPlace
					.getBuildDatasId()));
			keyPlaceInfoVo.setId(keyPlace.getId());
			keyPlaceInfoVo.setType(keyPlace.getType());
			keyPlaceService.updateDomainByKeyplaces(keyPlaceInfoVo);
		}
	}
}
