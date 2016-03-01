package com.tianque.baseInfo.actualCompany.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.actualCompany.dao.ActualCompanyDao;
import com.tianque.baseInfo.actualCompany.domain.ActualCompany;
import com.tianque.baseInfo.actualCompany.domain.ActualCompanyPractitioners;
import com.tianque.baseInfo.orgLocationTrack.domain.LocationTracksEntity;
import com.tianque.baseInfo.orgLocationTrack.service.OrgLocationTracksService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.GisInfo;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.service.KeyPlaceService;
import com.tianque.service.impl.LogableService;
import com.tianque.solr.domain.DocumentType;
import com.tianque.state.OrgLocationInitType;
import com.tianque.state.OrgLocationTracksOperationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.PluginServiceHelpUtil;

@Service("actualCompanyService")
@Transactional
public class ActualCompanyServiceImpl extends LogableService implements
		ActualCompanyService {
	@Autowired
	@Qualifier("actualCompanyValidator")
	private DomainValidator<ActualCompany> domainValidator;
	@Autowired
	private OrganizationDubboService orgnizationService;
	@Autowired
	private ActualCompanyDao actualCompanyDao;
	@Autowired
	private OrgLocationTracksService orgLocationTracksService;
	@Autowired
	private KeyPlaceService placeService;

	@Override
	public ActualCompany addActualCompany(ActualCompany actualCompany) {
		try {
			evaluationForIsEmphasisAndIsKey(actualCompany, "add");
			ValidateResult idleValidator = domainValidator
					.validate(actualCompany);
			if (!ExcelImportHelper.isImport.get()) {
				if (idleValidator.hasError()) {
					throw new BusinessValidationException(
							idleValidator.getErrorMessages());
				} else if (hasDuplicateActualCompany(actualCompany
						.getOrganization().getId(),
						actualCompany.getCompanyName(), actualCompany.getId())) {
					throw new BusinessValidationException("该网格下已存在相同单位");
				}
			}

			actualCompany.setOrgInternalCode(orgnizationService
					.getSimpleOrgById(actualCompany.getOrganization().getId())
					.getOrgInternalCode());
			autoFillChinesePinyin(actualCompany);
			actualCompany = actualCompanyDao.addActualCompany(actualCompany);
			// 实有单位新增的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(actualCompany, actualCompany
							.getCompanyName(), actualCompany
							.getOrgInternalCode()),
					BaseInfoTables.ACTUALCOMPANY_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.ADDT, "实有单位新增");
			placeService.execute(DocumentType.ACTUALCOMPANY,
					OperateMode.ADD.toString(), actualCompany);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("新增实有单位信息出现错误", e);
			} else {
				return null;
			}
		}
		return actualCompany;
	}

	@Override
	public void deleteActualCompany(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("删除实有单位时idList不能为空");
		}
		for (Long id : idList) {
			if (null == id || id < 0L) {
				throw new BusinessValidationException("删除实有单位时id不合法");
			}
			ActualCompany domain = getActualCompanyById(id);
			// 实有单位删除的轨迹
			orgLocationTracksService.addLocationTracks(
					new LocationTracksEntity(domain, domain.getCompanyName(),
							domain.getOrgInternalCode()),
					BaseInfoTables.ACTUALCOMPANY_KEY,
					OrgLocationInitType.IMPORT, null,
					OrgLocationTracksOperationType.DELETE, "实有单位删除");
			actualCompanyDao.deleteActualCompany(id);
			placeService.execute(DocumentType.ACTUALCOMPANY,
					OperateMode.DELETE.toString(), domain);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.ACTUALCOMPANY_KEY, id);
				/** 删除时对关联的事件和服务记录进行orgId和idCardNo赋值 */
				PluginServiceHelpUtil.doService("routerService",
						"setOrgIdAndCardNoOrName", new Class[] { Long.class,
								String.class, String.class, Long.class },
						domain.getOrganization().getId(),
						domain.getCompanyName(),
						BaseInfoTables.ACTUALCOMPANY_KEY, id);
			} catch (Exception e) {
				throw new ServiceValidationException("删除实有单位信息出现错误", e);
			}
		}
	}

	@Override
	public PageInfo<ActualCompany> findActualCompanyForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sortField,
			String sord, Boolean logOut) {
		return actualCompanyDao
				.findActualCompanyForPageByOrgInternalCode(orgnizationService
						.getSimpleOrgById(orgId).getOrgInternalCode(), pageNum,
						pageSize, sortField, sord, logOut);
	}

	@Override
	public ActualCompany getActualCompanyById(Long id) {
		if (null == id || id < 0L) {
			throw new BusinessValidationException("删除实有单位时id不合法");
		}
		return actualCompanyDao.getActualCompanyById(id);
	}

	@Override
	public ActualCompany updateActualCompany(ActualCompany actualCompany) {
		ActualCompany company = null;
		try {
			evaluationForIsEmphasisAndIsKey(actualCompany, "update");
			ValidateResult idleValidator = domainValidator
					.validate(actualCompany);
			if (!ExcelImportHelper.isImport.get()) {
				if (idleValidator.hasError()) {
					throw new BusinessValidationException(
							idleValidator.getErrorMessages());
				} else if (hasDuplicateActualCompany(actualCompany
						.getOrganization().getId(),
						actualCompany.getCompanyName(), actualCompany.getId())) {
					throw new BusinessValidationException("该网格下已存在相同单位");
				}
			}
			actualCompany.setOrgInternalCode(orgnizationService
					.getSimpleOrgById(actualCompany.getOrganization().getId())
					.getOrgInternalCode());
			autoFillChinesePinyin(actualCompany);
			company = actualCompanyDao.updateActualCompany(actualCompany);
			if (actualCompany.getIsEmphasis()) {
				// 实有单位取消关注的轨迹
				orgLocationTracksService
						.addLocationTracks(
								new LocationTracksEntity(company, company
										.getCompanyName(), company
										.getOrgInternalCode()),
								BaseInfoTables.ACTUALCOMPANY_KEY,
								OrgLocationInitType.TRANSFOR_DOOM,
								null,
								OrgLocationTracksOperationType.CANCEL_ATTENTION,
								"实有单位取消关注");
			} else {
				// 实有单位重新关注的轨迹
				orgLocationTracksService
						.addLocationTracks(
								new LocationTracksEntity(company, company
										.getCompanyName(), company
										.getOrgInternalCode()),
								BaseInfoTables.ACTUALCOMPANY_KEY,
								OrgLocationInitType.TRANSFOR_DOOM, null,
								OrgLocationTracksOperationType.AGAIN_ATTENTION,
								"实有单位重新关注");

			}
			Long emphasis = 0L;
			if (actualCompany.getIsEmphasis()) {
				emphasis = 1L;
			}
			placeService.execute(DocumentType.ACTUALCOMPANY,
					OperateMode.EDIT.toString(), actualCompany);
			placeService.emphasisAndNotEmphasis(company.getId(),
					DocumentType.ACTUALCOMPANY.toString(), emphasis);
		} catch (Exception e) {
			if (!ExcelImportHelper.isImport.get()) {
				throw new ServiceValidationException("实有单位信息出现错误", e);
			} else {
				return null;
			}
		}

		return company;
	}

	@Override
	public ActualCompany updateKeyCrucialPosition(ActualCompany actualCompany) {
		return actualCompanyDao.updateKeyCrucialPosition(actualCompany);
	}

	@Override
	public ActualCompany updatePreventionFacilities(ActualCompany actualCompany) {
		return actualCompanyDao.updatePreventionFacilities(actualCompany);
	}

	@Override
	public boolean hasDuplicateActualCompany(Long orgId, String companyName,
			Long exceptedId) {
		ActualCompany exsited = actualCompanyDao
				.getActualCompanyByCompanyNameAndOrganizationId(companyName,
						orgId);
		return exceptedId == null ? exsited != null
				: (exsited != null && !exceptedId.equals(exsited.getId()));
	}

	private void autoFillChinesePinyin(ActualCompany domain) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(domain
				.getCompanyName());
		domain.setFullPinyin((String) pinyin.get("fullPinyin"));
		domain.setSimplePinyin((String) pinyin.get("simplePinyin"));
	}

	@Override
	public List<ActualCompany> updateEmphasisByIdList(List<Long> idList,
			ActualCompany domain) {
		validatorIdList(idList);
		List<ActualCompany> actualCompanyList = new ArrayList<ActualCompany>();
		for (Long id : idList) {
			ActualCompany actualCompany = actualCompanyDao
					.getActualCompanyById(id);
			actualCompany.setIsEmphasis(domain.getIsEmphasis());
			actualCompany.setLogOutReason(domain.getLogOutReason());
			actualCompany.setLogOutTime(domain.getLogOutTime());
			actualCompany = updateActualCompany(actualCompany);
			actualCompanyList.add(actualCompany);
			// true时表示要进行注销操作
			Long isEmphasis = 0l;
			if (actualCompany.getIsEmphasis()) {
				isEmphasis = 1l;
			}
			try {
				PluginServiceHelpUtil.doService("routerService",
						"updateServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class, Long.class },
						BaseInfoTables.ACTUALCOMPANY_KEY, id, isEmphasis);
			} catch (Exception e) {
				throw new ServiceValidationException("注销实有单位失败", e);
			}
		}

		return actualCompanyList;
	}

	public List<Long> validatorIdList(List<Long> idList) {
		if (null == idList) {
			throw new BusinessValidationException("实有单位idList不能为空");
		}
		for (Long id : idList) {
			if (null == id || id < 0L) {
				throw new BusinessValidationException("实有单位id不合法");
			}
		}
		return idList;
	}

	@Override
	public ActualCompany getActualCompanyByCompanyName(String companyName,
			Long orgId) {

		return actualCompanyDao.getActualCompanyByCompanyNameAndOrganizationId(
				companyName, orgId);
	}

	/** 为实有单位的注销状态和是否重点单位赋值 */
	private ActualCompany evaluationForIsEmphasisAndIsKey(ActualCompany domain,
			String mode) {
		if (mode.equals("add")) {
			domain.setIsEmphasis(false);
		} else if (mode.equals("update")) {
			ActualCompany actualCompany = actualCompanyDao
					.getActualCompanyById(domain.getId());
			if (domain.getIsEmphasis() == null) {
				domain.setIsEmphasis(actualCompany.getIsEmphasis());
			}
		}
		if (domain.getIsKey() == null) {
			domain.setIsKey(false);
		}
		return domain;
	}

	@Override
	public List<ActualCompany> countActualCompanyByOrgId(Long orgId) {
		if (orgId == null) {
			return new ArrayList<ActualCompany>();
		} else {
			Organization org = orgnizationService.getSimpleOrgById(orgId);
			return actualCompanyDao.countActualCompanyByorgInternalCode(org
					.getOrgInternalCode());
		}
	}

	@Override
	public PageInfo<ActualCompany> searchActualUnitforGisByorgIdAndQueryStr(
			String queryStr, Long orgId, Integer page, Integer rows,
			String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误，orgId不能为空！");
		} else {
			Organization org = orgnizationService.getSimpleOrgById(orgId);
			return actualCompanyDao.searchActualUnitforGisByorgIdAndQueryStr(
					queryStr, org.getOrgInternalCode(), page, rows, sidx, sord);
		}

	}

	/**
	 * 在地图上进行单位绑定
	 */
	@Override
	public ActualCompany updateactualCompanyInfoForGis(Long id, GisInfo gisInfo) {
		return actualCompanyDao
				.updateactualCompanyInfoForGis(getUnitIdAndGisInfo(id, gisInfo));
	}

	private ActualCompany getUnitIdAndGisInfo(Long id, GisInfo gisInfo) {
		ActualCompany actualCompany = new ActualCompany();
		if (id == null) {
			throw new BusinessValidationException(
					"getUnitIdAndGisInfo方法出错，Id为空");
		} else {
			actualCompany.setId(id);
			actualCompany.setGisInfo(gisInfo);
			return actualCompany;
		}
	}

	/**
	 * 在地图上进行单位解绑
	 */
	@Override
	public ActualCompany unBindActualCompanyOnMap(Long unitId) {
		return actualCompanyDao.unBindActualCompanyOnMap(unitId);
	}

	@Override
	public ActualCompany getActualUnitDatialInfoByUnitId(Long unitId, Long orgId) {
		if (unitId == null) {
			throw new BusinessValidationException("获取实有单位出错，ID获取失败。");
		} else {
			return actualCompanyDao.getActualUnitDatialInfoByUnitId(unitId,
					orgId);
		}
	}

	@Override
	public PageInfo<ActualCompany> searchKeyUnitListSearchByOrgId(Long orgId,
			Integer page, Integer rows, String sidx, String sord) {
		if (orgId == null || orgId <= 0L) {
			throw new BusinessValidationException("获取gis是有房屋列表数据出错,orgId未能获取！");
		} else {
			Organization org = orgnizationService.getSimpleOrgById(orgId);
			return actualCompanyDao.searchKeyUnitListSearchByOrgInternalCode(
					org.getOrgInternalCode(), page, rows, sidx, sord);
		}
	}

	@Override
	public List<ActualCompany> findAllBindingActualUnitByOrgInternalCodeForGis(
			Long orgId) {
		if (null == orgId || orgId < 0L) {
			return new ArrayList<ActualCompany>();
		} else {
			Organization org = orgnizationService.getSimpleOrgById(orgId);
			return actualCompanyDao
					.findAllBindingActualUnitByOrgInternalCodeForGis(org
							.getOrgInternalCode());
		}
	}

	@Override
	public int countBindingActualUnitBybuildingIdForGis(Long buildingId) {
		if (null == buildingId || buildingId < 1L) {
			return 0;
		} else
			return actualCompanyDao
					.countBindingActualUnitBybuildingIdForGis(buildingId);
	}

	@Override
	public List<ActualCompany> searchAllRoundCompany(GisInfo minOption,
			GisInfo maxOption) {
		return actualCompanyDao.searchAllRoundCompany(minOption, maxOption);
	}

	@Override
	public PageInfo<ActualCompany> findActualCompanyByBuilddatasIdForPage(
			Long builddatasId, Integer pageNum, Integer pageSize, String sidx,
			String sord) {
		return actualCompanyDao.findActualCompanyByBuilddatasIdForPage(
				builddatasId, pageNum, pageSize, sidx, sord);
	}

	@Override
	public PageInfo<ActualCompany> findUnBoundedByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord,
			String queryStr) {
		return actualCompanyDao
				.findunBoundActualCompanyListForPage(orgnizationService
						.getSimpleOrgById(orgId).getOrgInternalCode(), pageNum,
						pageSize, sidx, sord, queryStr);
	}

	@Override
	public void boundActualCompany(List<Long> houseIds, Long builddatasId) {
		actualCompanyDao.boundActualCompany(houseIds, builddatasId);

	}

	@Override
	public void unboundActualCompany(List<Long> houseIds) {
		actualCompanyDao.unboundActualCompany(houseIds);

	}

	@Override
	public List<ActualCompany> findActualCompanyListByBuildingId(
			Long builddatasId) {
		if (builddatasId == null) {
			throw new BusinessValidationException("楼宇编号为空");
		}
		return actualCompanyDao.findActualCompanyListByBuildingId(builddatasId);
	}

	@Override
	public ActualCompany hasDuplicateActualCompany(Long orgId, String name) {
		return actualCompanyDao.getActualCompanyByCompanyNameAndOrganizationId(
				name, orgId);
	}

	@Override
	public void deleteActualCompanyByIds(Long[] idList) {
		for (Long id : idList) {
			if (null == id || id < 0L) {
				throw new BusinessValidationException("删除实有单位时id不合法");
			}
			actualCompanyDao.deleteActualCompany(id);
			try {
				PluginServiceHelpUtil.doService("routerService",
						"deleteServiceTeamHasObjectsAndServiceMemberHasObject",
						new Class[] { String.class, Long.class },
						BaseInfoTables.ACTUALCOMPANY_KEY, id);
			} catch (Exception e) {
				throw new ServiceValidationException("删除实有单位失败", e);
			}
		}

	}

	@Override
	public PageInfo findActualCompanysPractitioners(ActualCompany location,
			Integer pageNum, Integer pageSize, String sortField, String sord) {
		if (location == null || location.getId() == null) {
			throw new BusinessValidationException("缺少实有单位id");
		}
		return actualCompanyDao.findActualCompanysPractitioners(location,
				pageNum, pageSize, sortField, sord);
	}

	@Override
	public PageInfo findActualCompanysAddPractitionersForList(
			ActualCompany location, Integer pageNum, Integer pageSize,
			String sortField, String sord) {
		if (location == null || location.getId() == null) {
			throw new BusinessValidationException("缺少实有单位id");
		}
		return actualCompanyDao.findActualCompanysAddPractitionersForList(
				location, pageNum, pageSize, sortField, sord);
	}

	@Override
	public void saveActualCompanysPractitioners(Long id, String locationIds) {
		if (locationIds == null || id == null || id == 0) {
			throw new BusinessValidationException("保存失败");
		}
		actualCompanyDao.deleteActualCompanyPractitioners(id);
		String locationIdArry[] = locationIds.split(",");
		for (String locationId : locationIdArry) {
			if (locationId.length() < 2) {
				continue;
			}
			Long personId = 0L;
			try {
				personId = Long.parseLong(locationId.substring(1));
			} catch (Exception e) {
				continue;
			}
			String perPersonType = locationId.substring(0, 1);
			if (!"H".equals(perPersonType) && !"L".equals(perPersonType)
					&& !"W".equals(perPersonType)) {
				continue;
			}
			String personType = "H".equals(perPersonType) ? "户籍人口" : ("L"
					.equals(perPersonType) ? "流动人口" : "未落户人口");
			ActualCompanyPractitioners acp = new ActualCompanyPractitioners();
			acp.setActualCompanyId(id);
			acp.setPersonId(personId);
			acp.setPersonType(personType);
			actualCompanyDao.saveActualCompanyPractitioners(acp);
		}
	}

	@Override
	public String getActualCompanysAddPractitionersById(Long id) {
		List<ActualCompanyPractitioners> actList = actualCompanyDao
				.queryActualCompanysAddPractitionersById(id);
		String returnStr = "";
		for (ActualCompanyPractitioners act : actList) {
			returnStr += ("户籍人口".equals(act.getPersonType()) ? "H" : ("流动人口"
					.equals(act.getPersonType()) ? "L" : "W"))
					+ act.getPersonId() + ",";
		}
		return returnStr;
	}

	@Override
	public void delPersonForPractitionerst(Long id, String locationIds) {
		if (locationIds == null || id == null || id == 0) {
			throw new BusinessValidationException("删除失败");
		}
		String _locationIds[] = locationIds.split(",");
		for (String locationId : _locationIds) {
			ActualCompanyPractitioners acp = new ActualCompanyPractitioners();
			acp.setActualCompanyId(id);
			acp.setPersonId(Long.parseLong(locationId));
			actualCompanyDao.delPersonForPractitionerst(acp);
		}
	}
}
