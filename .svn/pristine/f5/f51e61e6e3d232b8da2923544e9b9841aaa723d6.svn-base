package com.tianque.working.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.KeyAreasOfInvestigationInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.KeyAreasOfInvestigationInfoVo;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.impl.LogableService;
import com.tianque.state.KeyAreasOfInvestigationInfoState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.working.dao.KeyAreasOfInvestigationInfoDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.KeyAreasOfInvestigationInfoService;

@Transactional
@Service("keyAreasOfInvestigationInfoService")
public class KeyAreasOfInvestigationInfoServiceImpl extends LogableService
		implements KeyAreasOfInvestigationInfoService {
	@Autowired
	private KeyAreasOfInvestigationInfoDao keyAreasOfInvestigationInfoDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryId(
			Long organizationId, Long directoryId, Integer page, Integer rows,
			String sidx, String sord) {
		if (organizationId == null || organizationId == 0L
				|| directoryId == null || directoryId == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		String allDailyDirectoryId = "";
		List<DailyDirectory> dailyDirectories = dailyDirectoryService
				.findDailySubDirectoryByParentId(directoryId);
		if (dailyDirectories.size() == 0) {
			allDailyDirectoryId = "'" + directoryId + "'";
		} else {
			for (int i = 0; i < dailyDirectories.size(); i++) {
				if (i == 0) {
					allDailyDirectoryId = "'" + dailyDirectories.get(i).getId()
							+ "'";
				} else {
					allDailyDirectoryId = allDailyDirectoryId + ",'"
							+ dailyDirectories.get(i).getId() + "'";
				}
			}
		}

		return keyAreasOfInvestigationInfoDao
				.findKeyAreasOfInvestigationInfosByOrgIdAndDirectoryId(
						organizationId, allDailyDirectoryId, page, rows, sidx,
						sord);
	}

	@Override
	public List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPrint(
			Long orgId, String[] selectids) {
		List<KeyAreasOfInvestigationInfo> result = new ArrayList<KeyAreasOfInvestigationInfo>();
		String allDailyDirectoryId = "";
		SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < selectids.length; i++) {
			if (i == 0) {
				allDailyDirectoryId = "'" + selectids[i] + "'";
			} else {
				allDailyDirectoryId = allDailyDirectoryId + ",'" + selectids[i]
						+ "'";
			}
		}
		List<KeyAreasOfInvestigationInfo> list = keyAreasOfInvestigationInfoDao
				.findKeyAreasOfInvestigationInfosForPrint(orgId,
						allDailyDirectoryId);
		for (int i = 0; i < list.size(); i++) {
			KeyAreasOfInvestigationInfo domain = list.get(i);
			Organization org = new Organization();
			if (domain.getInvestigationOrg() != null) {
				org = organizationDubboService.getSimpleOrgById(domain
						.getInvestigationOrg().getId());
			}
			domain.setId((long) i + 1);
			domain.setInvestigationOrg(org);
			domain.setInvestigatOrgDate(fmat.format(domain
					.getInvestigationDate()));
			result.add(domain);
		}

		return result;
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryParentId(
			Long organizationId, Long directoryId, Integer page, Integer rows,
			String sidx, String sord) {
		if (organizationId == null || organizationId == 0L
				|| directoryId == null || directoryId == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		return keyAreasOfInvestigationInfoDao
				.findKeyAreasOfInvestigationInfosForPageByOrgIdAndDirectoryParentId(
						organizationId, directoryId, page, rows, sidx, sord);
	}

	@Override
	public KeyAreasOfInvestigationInfo getKeyAreasOfInvestigationInfoById(
			Long id) {
		if (id == null || id == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		return keyAreasOfInvestigationInfoDao
				.getKeyAreasOfInvestigationInfoById(id);
	}

	@Override
	public KeyAreasOfInvestigationInfo addKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo) {
		if (!vidateInput(keyAreasOfInvestigationInfo)
				|| keyAreasOfInvestigationInfo.getOrganization() == null
				|| keyAreasOfInvestigationInfo.getOrganization().getId() == null
				|| keyAreasOfInvestigationInfo.getDailyDirectory() == null
				|| keyAreasOfInvestigationInfo.getDailyDirectory().getId() == null) {
			throw new BusinessValidationException("参数不正确");
		}
		keyAreasOfInvestigationInfo.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(
						keyAreasOfInvestigationInfo.getOrganization().getId())
				.getOrgInternalCode());
		keyAreasOfInvestigationInfo
				.setInvestigationOrg(keyAreasOfInvestigationInfo
						.getOrganization());
		keyAreasOfInvestigationInfo
				.setStatus(KeyAreasOfInvestigationInfoState.UNREPORTED);
		this.log(keyAreasOfInvestigationInfo,
				OperatorType.toString(OperatorType.ADD), OperatorType.ADD);
		return keyAreasOfInvestigationInfoDao
				.addKeyAreasOfInvestigationInfo(keyAreasOfInvestigationInfo);
	}

	private void log(KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo,
			String operation, Integer operationType) {
		DailyDirectory dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(keyAreasOfInvestigationInfo
						.getDailyDirectory().getId());
		DailyDirectory parentDailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory
						.getParentDailyDirectory().getId());
		super.log(WARN, WORKINGRECORD + parentDailyDirectory.getShortName()
				+ "->" + dailyDirectory.getShortName(), ThreadVariable
				.getSession().getUserName()
				+ operation
				+ "名称为"
				+ keyAreasOfInvestigationInfo.getAreaName()
				+ "的"
				+ dailyDirectory.getShortName(), operationType, ObjectToJSON
				.convertJSON(keyAreasOfInvestigationInfo));
	}

	@Override
	public boolean deleteKeyAreasOfInvestigationInfoById(Long id) {
		if (id == null || id == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		this.log(getKeyAreasOfInvestigationInfoById(id),
				OperatorType.toString(OperatorType.DELETE), OperatorType.DELETE);
		return keyAreasOfInvestigationInfoDao
				.deleteKeyAreasOfInvestigationInfoById(id);
	}

	@Override
	public KeyAreasOfInvestigationInfo reportedKeyAreasOfInvestigationInfoById(
			Long id, Long orgId, Long expiredEntering) {
		if (id == null
				|| id == 0L
				|| orgId == null
				|| orgId == 0L
				|| KeyAreasOfInvestigationInfoState.REPORTED
						.equals(keyAreasOfInvestigationInfoDao
								.getKeyAreasOfInvestigationInfoById(id)
								.getStatus())) {
			throw new BusinessValidationException("参数不正确");
		}

		KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoDao
				.updateKeyAreasOfInvestigationInfoForStautsAndReportTimeById(
						id, KeyAreasOfInvestigationInfoState.REPORTED,
						CalendarUtil.now("yyyy-MM-dd HH:mm:ss"),
						expiredEntering);
		keyAreasOfInvestigationInfo.setSubOrgid(orgId);
		addInfoWhenReorted(keyAreasOfInvestigationInfo, orgId);
		return this.getKeyAreasOfInvestigationInfoById(id);
	}

	@Override
	public boolean backKeyAreasOfInvestigationInfo(Long id) {
		if (id == null || id == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo = keyAreasOfInvestigationInfoDao
				.getKeyAreasOfInvestigationInfoById(id);
		keyAreasOfInvestigationInfoDao
				.deleteKeyAreasOfInvestigationInfoById(keyAreasOfInvestigationInfo
						.getId());
		keyAreasOfInvestigationInfoDao
				.updateKeyAreasOfInvestigationInfoForStautsAndReportTimeById(
						keyAreasOfInvestigationInfo
								.getFromKeyAreasOfInvestigationInfo().getId(),
						KeyAreasOfInvestigationInfoState.BACKED, null,
						keyAreasOfInvestigationInfo.getExpiredEntering());

		return true;
	}

	private void addInfoWhenReorted(
			KeyAreasOfInvestigationInfo formkeyAreasOfInvestigationInfo,
			Long orgId) {
		KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo = new KeyAreasOfInvestigationInfo();
		keyAreasOfInvestigationInfo
				.setFromKeyAreasOfInvestigationInfo(formkeyAreasOfInvestigationInfo);

		Organization organization = getParentOrganizationById(orgId);
		keyAreasOfInvestigationInfo.setOrganization(organization);
		keyAreasOfInvestigationInfo.setOrgInternalCode(organization
				.getOrgInternalCode());
		keyAreasOfInvestigationInfo.setDailyDirectory(getParentOrgDirectory(
				organization, formkeyAreasOfInvestigationInfo
						.getDailyDirectory().getId()));
		keyAreasOfInvestigationInfo
				.setInvestigationOrg(formkeyAreasOfInvestigationInfo
						.getInvestigationOrg());
		keyAreasOfInvestigationInfo
				.setInvestigationDate(formkeyAreasOfInvestigationInfo
						.getInvestigationDate());
		keyAreasOfInvestigationInfo.setAreaName(formkeyAreasOfInvestigationInfo
				.getAreaName());
		keyAreasOfInvestigationInfo
				.setPoliciesAndMeasures(formkeyAreasOfInvestigationInfo
						.getPoliciesAndMeasures());
		keyAreasOfInvestigationInfo.setSubOrgid(formkeyAreasOfInvestigationInfo
				.getSubOrgid());
		keyAreasOfInvestigationInfo
				.setStatus(KeyAreasOfInvestigationInfoState.UNREPORTED);
		keyAreasOfInvestigationInfo.setRemark(formkeyAreasOfInvestigationInfo
				.getRemark());
		keyAreasOfInvestigationInfo.setReportedDate(null);
		keyAreasOfInvestigationInfo
				.setWarningOrListing(formkeyAreasOfInvestigationInfo
						.getWarningOrListing());
		keyAreasOfInvestigationInfo
				.setMainProblem(formkeyAreasOfInvestigationInfo
						.getMainProblem());
		if (keyAreasOfInvestigationInfo.getDailyDirectory() != null) {
			keyAreasOfInvestigationInfoDao
					.addKeyAreasOfInvestigationInfo(keyAreasOfInvestigationInfo);
		}
	}

	private DailyDirectory getParentOrgDirectory(Organization organization,
			Long directoryId) {
		if (organization == null) {
			throw new BusinessValidationException("参数错误");
		}
		Long makeOrgId = organization.getId();
		if (organization.getParentOrg() != null) {
			makeOrgId = organization.getParentOrg().getId();
		}
		DailyDirectory dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(directoryId);
		Long reportType = null;
		if (null != dailyDirectory.getDirectoryReportType()) {
			reportType = dailyDirectory.getDirectoryReportType().getId();
		}
		return dailyDirectoryService
				.getDirectoryByOrgIdAndTypeIdAndReportTypeIdAndDirectoryId(
						makeOrgId, dailyDirectory.getType().getId(),
						reportType, directoryId);
	}

	private Organization getParentOrganizationById(Long orgId) {
		Organization organization = organizationDubboService.getSimpleOrgById(orgId);
		if (organization == null || organization.getParentOrg() == null) {
			throw new BusinessValidationException("参数错误!");
		}
		return organizationDubboService.getSimpleOrgById(organization.getParentOrg()
				.getId());
	}

	@Override
	public KeyAreasOfInvestigationInfo updateKeyAreasOfInvestigationInfo(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo) {
		if (!vidateInput(keyAreasOfInvestigationInfo)
				|| keyAreasOfInvestigationInfo.getId() == null
				|| keyAreasOfInvestigationInfo.getId().longValue() == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		return keyAreasOfInvestigationInfoDao
				.updateKeyAreasOfInvestigationInfo(keyAreasOfInvestigationInfo);
	}

	public boolean vidateInput(
			KeyAreasOfInvestigationInfo keyAreasOfInvestigationInfo) {
		boolean bool = true;
		if (keyAreasOfInvestigationInfo == null
				|| keyAreasOfInvestigationInfo.getInvestigationDate() == null
				|| keyAreasOfInvestigationInfo.getMainProblem() == null
				|| keyAreasOfInvestigationInfo.getPoliciesAndMeasures() == null
				|| keyAreasOfInvestigationInfo.getAreaName() == null) {
			return false;
		}
		return bool;
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> getKeyAreasOfInvestigationInfosByInvestigationDate(
			KeyAreasOfInvestigationInfoVo keyAreasOfInvestigationInfoVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {
		return keyAreasOfInvestigationInfoDao
				.getKeyAreasOfInvestigationInfosByInvestigationDate(
						keyAreasOfInvestigationInfoVo, pageNum, pageSize,
						sortField, order);
	}

	@Override
	public List<KeyAreasOfInvestigationInfo> findKeyAreasOfInvestigationInfos(
			Long orgId, Long directoryId) {
		return keyAreasOfInvestigationInfoDao.findKeyAreasOfInvestigationInfos(
				orgId, directoryId);
	}

	@Override
	public PageInfo<KeyAreasOfInvestigationInfo> searchKeyAreasOfInvestigationInfoWorkingRecord(
			KeyAreasOfInvestigationInfoVo searchVo,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String allDailyDirectoryId) {
		if (searchVo.getInvestigationOrg()!=null && StringUtil.isStringAvaliable(searchVo
				.getInvestigationOrg().getOrgName())) {
			OrganizationVo orgVo = new OrganizationVo();
			orgVo.setOrgName(searchVo.getInvestigationOrg().getOrgName());
			List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(orgVo);
			if(orgIdList!=null && orgIdList.size()>0){
				searchVo.setOrgIdsList(ParametersConvertUtil.convertToListString(orgIdList));
			}else{
				searchVo.setOrgIdsList(ParametersConvertUtil.nullStringList());
			}
		}
		return keyAreasOfInvestigationInfoDao
				.searchKeyAreasOfInvestigationInfoWorkingRecord(
						searchVo, pageNum, pageSize,
						sortField, order, allDailyDirectoryId);
	}

}
