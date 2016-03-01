package com.tianque.working.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.systemLog.util.OperatorType;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.ObjectToJSON;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.domain.vo.SignificantIssuedealVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.impl.LogableService;
import com.tianque.state.KeyAreasOfInvestigationInfoState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ParametersConvertUtil;
import com.tianque.working.dao.SignificantIssuedealDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.SignificantIssuedealAttachFiles;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.SignificantIssuedealAttachFileService;
import com.tianque.working.service.SignificantIssuedealService;

@Transactional
@Service("significantIssuedealService")
public class SignificantIssuedealServiceImpl extends LogableService implements
		SignificantIssuedealService {
	@Autowired
	private SignificantIssuedealDao significantIssuedealDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	private SignificantIssuedealAttachFileService disputeAttachFileService;

	@Override
	public PageInfo<SignificantIssuedeal> findSignificantIssuedealsForPageByOrgIdAndDirectoryId(
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
		PageInfo<SignificantIssuedeal> pageInfo = significantIssuedealDao
				.findSignificantIssuedealsByOrgIdAndDirectoryId(organizationId,
						allDailyDirectoryId, page, rows, sidx, sord);
		return pageInfo;
	}

	@Override
	public PageInfo<SignificantIssuedeal> findSignificantIssuedealsForPageByOrgIdAndDirectoryParentId(
			Long organizationId, Long directoryId, Integer page, Integer rows,
			String sidx, String sord) {
		if (organizationId == null || organizationId == 0L
				|| directoryId == null || directoryId == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		return significantIssuedealDao
				.findSignificantIssuedealsForPageByOrgIdAndDirectoryParentId(
						organizationId, directoryId, page, rows, sidx, sord);
	}

	@Override
	public SignificantIssuedeal getSignificantIssuedealById(Long id) {
		if (id == null || id == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		return significantIssuedealDao.getSignificantIssuedealById(id);
	}

	@Override
	public SignificantIssuedeal addSignificantIssuedeal(
			SignificantIssuedeal significantIssuedeal) {
		if (!vidateInput(significantIssuedeal)
				|| significantIssuedeal.getOrganization() == null
				|| significantIssuedeal.getOrganization().getId() == null
				|| significantIssuedeal.getDailyDirectory() == null
				|| significantIssuedeal.getDailyDirectory().getId() == null) {
			throw new BusinessValidationException("参数不正确");
		}
		significantIssuedeal.setOrgInternalCode(organizationDubboService
				.getSimpleOrgById(
						significantIssuedeal.getOrganization().getId())
				.getOrgInternalCode());
		significantIssuedeal.setInvestigationOrg(significantIssuedeal
				.getOrganization());
		significantIssuedeal
				.setStatus(KeyAreasOfInvestigationInfoState.UNREPORTED);
		this.log(significantIssuedeal, OperatorType.toString(OperatorType.ADD),
				OperatorType.ADD);
		return significantIssuedealDao
				.addSignificantIssuedeal(significantIssuedeal);
	}

	private void log(SignificantIssuedeal significantIssuedeal,
			String operation, Integer operationType) {
		DailyDirectory dailyDirectory = dailyDirectoryService
				.getFullDailyDirectoryById(significantIssuedeal
						.getDailyDirectory().getId());
		DailyDirectory parentDailyDirectory = dailyDirectoryService
				.getSimpleDailyDirectoryById(dailyDirectory
						.getParentDailyDirectory().getId());
		super.log(WARN, WORKINGRECORD + parentDailyDirectory.getShortName()
				+ "->" + dailyDirectory.getShortName(),
				ThreadVariable.getSession().getUserName() + operation + "名称为"
						+ significantIssuedeal.getAddress() + "的"
						+ dailyDirectory.getShortName(), operationType,
				ObjectToJSON.convertJSON(significantIssuedeal));
	}

	@Override
	public boolean deleteSignificantIssuedealById(Long id) {
		if (id == null || id == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		this.log(getSignificantIssuedealById(id),
				OperatorType.toString(OperatorType.DELETE), OperatorType.DELETE);
		return significantIssuedealDao.deleteSignificantIssuedealById(id);
	}

	@Override
	public SignificantIssuedeal reportedSignificantIssuedealById(Long id,
			Long orgId, Long expiredEntering) {
		if (id == null
				|| id == 0L
				|| orgId == null
				|| orgId == 0L
				|| KeyAreasOfInvestigationInfoState.REPORTED
						.equals(significantIssuedealDao
								.getSignificantIssuedealById(id).getStatus())) {
			throw new BusinessValidationException("参数不正确");
		}

		SignificantIssuedeal SignificantIssuedeal = significantIssuedealDao
				.updateSignificantIssuedealForStautsAndReportTimeById(id,
						KeyAreasOfInvestigationInfoState.REPORTED,
						CalendarUtil.now("yyyy-MM-dd HH:mm:ss"),
						expiredEntering);
		SignificantIssuedeal.setSubOrgid(orgId);
		addInfoWhenReorted(SignificantIssuedeal, orgId);
		return this.getSignificantIssuedealById(id);
	}

	@Override
	public boolean backSignificantIssuedeal(Long id) {
		if (id == null || id == 0L) {
			throw new BusinessValidationException("参数不正确");
		}
		SignificantIssuedeal significantIssuedeal = significantIssuedealDao
				.getSignificantIssuedealById(id);
		disputeAttachFileService
				.deleteSignificantIssuedealAttachFilesByDisputeIssuedealId(id);
		significantIssuedealDao
				.deleteSignificantIssuedealById(significantIssuedeal.getId());
		significantIssuedealDao
				.updateSignificantIssuedealForStautsAndReportTimeById(
						significantIssuedeal
								.getFromKeyAreasOfInvestigationInfo().getId(),
						KeyAreasOfInvestigationInfoState.BACKED, null,
						significantIssuedeal.getExpiredEntering());

		return true;
	}

	private void addInfoWhenReorted(
			SignificantIssuedeal formSignificantIssuedeal, Long orgId) {

		SignificantIssuedeal significantIssuedeal = createNewSignificantIssuedeal(
				formSignificantIssuedeal, orgId);

		if (significantIssuedeal.getDailyDirectory() != null) {
			significantIssuedeal = significantIssuedealDao
					.addSignificantIssuedeal(significantIssuedeal);
			dealSignificantIssuedealAttachFiles(formSignificantIssuedeal,
					significantIssuedeal);
		}
	}

	private SignificantIssuedeal createNewSignificantIssuedeal(
			SignificantIssuedeal formSignificantIssuedeal, Long orgId) {
		SignificantIssuedeal significantIssuedeal = new SignificantIssuedeal();

		BeanUtils
				.copyProperties(formSignificantIssuedeal, significantIssuedeal);

		Organization organization = getParentOrganizationById(orgId);

		significantIssuedeal.setOrganization(organization);
		significantIssuedeal.setOrgInternalCode(organization
				.getOrgInternalCode());

		significantIssuedeal.setDailyDirectory(getParentOrgDirectory(
				organization, formSignificantIssuedeal.getDailyDirectory()
						.getId()));
		significantIssuedeal
				.setStatus(KeyAreasOfInvestigationInfoState.UNREPORTED);
		significantIssuedeal.setReportedDate(null);
		significantIssuedeal
				.setFromKeyAreasOfInvestigationInfo(formSignificantIssuedeal);
		return significantIssuedeal;
	}

	private void dealSignificantIssuedealAttachFiles(
			SignificantIssuedeal formSignificantIssuedeal,
			SignificantIssuedeal toSignificantIssuedeal) {
		List<SignificantIssuedealAttachFiles> significantIssuedealAttachFiles = disputeAttachFileService
				.getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(formSignificantIssuedeal
						.getId());
		for (SignificantIssuedealAttachFiles significantIssuedealAttachFile : significantIssuedealAttachFiles) {
			significantIssuedealAttachFile
					.setSignificantIssuedealWorkingRecord(toSignificantIssuedeal);
			disputeAttachFileService
					.addSignificantIssuedealAttachFiles(significantIssuedealAttachFile);
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
	public SignificantIssuedeal updateSignificantIssuedeal(
			SignificantIssuedeal SignificantIssuedeal) {
		if (!vidateInput(SignificantIssuedeal)
				|| SignificantIssuedeal.getId() == null
				|| SignificantIssuedeal.getId().longValue() == 0L) {
			throw new BusinessValidationException("参数不正确");
		}

		return significantIssuedealDao
				.updateSignificantIssuedeal(SignificantIssuedeal);
	}

	public boolean vidateInput(SignificantIssuedeal SignificantIssuedeal) {
		boolean bool = true;
		if (SignificantIssuedeal == null
				|| SignificantIssuedeal.getInvestigationDate() == null
				|| SignificantIssuedeal.getAddress() == null
				|| SignificantIssuedeal.getAccountabilityLeading() == null
				|| SignificantIssuedeal.getAccountabilityUnit() == null) {
			return false;
		}
		return bool;
	}

	@Override
	public PageInfo<SignificantIssuedeal> getSignificantIssuedealsByInvestigationDate(
			SignificantIssuedealVo significantIssuedealVo, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		return significantIssuedealDao
				.getSignificantIssuedealsByInvestigationDate(
						significantIssuedealVo, pageNum, pageSize, sortField,
						order);
	}

	@Override
	public List<SignificantIssuedeal> findSignificantIssuedealsForPrint(
			Long orgId, String[] selectids) {
		List<SignificantIssuedeal> result = new ArrayList<SignificantIssuedeal>();
		SimpleDateFormat fmat = new SimpleDateFormat("yyyy-MM-dd");
		String allDailyDirectoryId = "";
		for (int i = 0; i < selectids.length; i++) {
			if (i == 0) {
				allDailyDirectoryId = "'" + selectids[i] + "'";
			} else {
				allDailyDirectoryId = allDailyDirectoryId + ",'" + selectids[i]
						+ "'";
			}
		}
		List<SignificantIssuedeal> list = significantIssuedealDao
				.findSignificantIssuedealsForPrint(orgId, allDailyDirectoryId);
		for (int i = 0; i < list.size(); i++) {
			SignificantIssuedeal domain = list.get(i);
			Organization org = new Organization();
			if (domain.getInvestigationOrg() != null) {
				org = organizationDubboService.getSimpleOrgById(domain
						.getInvestigationOrg().getId());
			}
			domain.setId((long) i + 1);
			domain.setInvestigationOrg(org);
			domain.setStrInvestigationDate(fmat.format(domain
					.getInvestigationDate()));
			result.add(domain);
		}
		return result;
	}

	@Override
	public List<SignificantIssuedeal> findSignificantIssuedeals(Long orgId,
			Long directoryId) {

		return significantIssuedealDao.findSignificantIssuedeals(orgId,
				directoryId);
	}

	@Override
	public PageInfo<SignificantIssuedeal> searchSignificantIssuedealWorkingRecord(
			SignificantIssuedealVo searchActivityWorkingRecord,
			Integer pageNum, Integer pageSize, String sortField, String order,
			String allDailyDirectoryId) {
		if (!StringUtil.isStringAvaliable(allDailyDirectoryId)
				|| null == searchActivityWorkingRecord) {
			throw new BusinessValidationException("参数不正确");
		}
		if (searchActivityWorkingRecord.getInvestigationOrg()!=null
				&& StringUtil.isStringAvaliable(searchActivityWorkingRecord.getInvestigationOrg().getOrgName())) {
			OrganizationVo organizationVo = new OrganizationVo();
			organizationVo.setOrgName(searchActivityWorkingRecord.getInvestigationOrg().getOrgName());
			List<Long> orgIdList = organizationDubboService.findOrgIdsBySearchVo(organizationVo);
			searchActivityWorkingRecord.setOrgIdsList(ParametersConvertUtil.convertToListString(orgIdList));
		}
		return significantIssuedealDao.searchSignificantIssuedealWorkingRecord(
				searchActivityWorkingRecord, pageNum, pageSize, sortField,
				order, allDailyDirectoryId);
	}

}
