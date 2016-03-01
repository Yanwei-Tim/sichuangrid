package com.tianque.working.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.property.WorkDiaryTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.issue.domain.IssueLogNew;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.service.IssueService;
import com.tianque.issue.uitl.IssueToCMSUtil;
import com.tianque.service.vo.WorkDiaryVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.dao.WorkDiaryDao;
import com.tianque.working.domain.WorkDiary;
import com.tianque.working.service.WorkDiaryService;

@Service("workDiaryService")
@Transactional
public class WorkDiaryServiceImpl extends AbstractBaseService implements
		WorkDiaryService {

	@Autowired
	private WorkDiaryDao workDiaryDao;

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private IssueService issueService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public WorkDiary addWorkDiary(WorkDiary workDiary) {
		if (workDiary.getObjectId() == null)
			workDiary.setObjectId(0L);
		if (workDiary.getObjectType() == null)
			workDiary.setObjectType(WorkDiaryTypes.TYPE_OTHER);

		if (validateInput(workDiary)) {
			throw new BusinessValidationException("参数错误");
		}

		workDiary.setOrgInternalCode(organizationDubboService.getSimpleOrgById(
				workDiary.getOrganization().getId()).getOrgInternalCode());

		return workDiaryDao.addWorkDiary(workDiary);
	}

	@Override
	public void addWorkDiary(PropertyDict propertyDict, String objectType,
			Long objectId, String workContent, String workPlace,
			String workUserName, Date workTime) {
		// WorkDiary workDiary = new WorkDiary();
		// workDiary.setDiaryType(propertyDict);
		// workDiary.setOrganization(permissionService.getFullUserById(
		// ThreadVariable.getSession().getUserId()).getOrganization());
		// workDiary.setWorkContent(workContent);
		// workDiary.setWorkPlace(workPlace);
		// workDiary.setWorkTime(workTime);
		// workDiary.setWorkUserName(workUserName);
		// workDiary.setObjectType(objectType);
		// workDiary.setObjectId(objectId);
		// addWorkDiary(workDiary);
	}

	@Override
	public WorkDiary getWorkDiaryById(Long id) {
		return workDiaryDao.getWorkDiaryById(id);
	}

	private boolean validateInput(WorkDiary workDiary) {
		if (workDiary == null || workDiary.getDiaryType() == null
				|| workDiary.getOrganization() == null
				|| workDiary.getOrganization().getId() == null
				|| workDiary.getOrganization().getId().longValue() == 0L
				|| StringUtil.isStringAvaliable(workDiary.getWorkContent())
				|| StringUtil.isStringAvaliable(workDiary.getWorkPlace())
				|| StringUtil.isStringAvaliable(workDiary.getWorkUserName())
				|| StringUtil.isStringAvaliable(workDiary.getObjectType())
				|| workDiary.getObjectId() == 0L) {
			return false;
		}
		return true;
	}

	@Override
	public PageInfo<WorkDiary> findWorkDiarysForPage(Long orgId, Integer rows,
			Integer page, String sidx, String sord, boolean searchChild) {
		if (orgId == null) {
			return constructEmptyPageInfo();
		}
		if (searchChild) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(orgId);
			return workDiaryDao.findWorkDiarysForPageByOrgInternalCode(
					organization.getOrgInternalCode(), rows, page, sidx, sord);
		}
		return workDiaryDao.findWorkDiarysForPageByOrgId(orgId, rows, page,
				sidx, sord);
	}

	private PageInfo<WorkDiary> constructEmptyPageInfo() {
		PageInfo<WorkDiary> result = new PageInfo<WorkDiary>();
		result.setResult(new ArrayList<WorkDiary>());
		return result;
	}

	@Override
	public WorkDiary updateWorkDiary(WorkDiary workDiary) {
		if (validateInput(workDiary)) {
			throw new BusinessValidationException("参数错误");
		}
		return workDiaryDao.updateWorkDiary(workDiary);
	}

	@Override
	public Long deleteWorkDiaryById(List<Long> ids) {
		if (ids == null) {
			throw new BusinessValidationException("参数错误");
		}
		String[] idstr = new String[ids.size()];
		for (int i = 0; i < ids.size(); i++) {
			idstr[i] = ids.get(i).toString();
		}
		Long count = 0L;
		try{
			count = (long) workDiaryDao.deleteWorkDiaryByIds(idstr);
		}catch(Exception e){
			throw new ServiceValidationException("删除失败",e);
		}
		// for (Long id : ids) {
		// int deleteCount = workDiaryDao.deleteWorkDiaryById(id);
		// count += deleteCount;
		// }
		return count;
	}

	@Override
	public String assemblingContent(String name, String operatType,
			String content, String workDiaryType, String modeType,
			String objectType) {
		StringBuffer returnContent = new StringBuffer("对 ");
		if (StringUtil.isStringAvaliable(workDiaryType)) {
			if (!WorkDiaryTypes.ISSUE_DEAL.equals(workDiaryType)
					|| !WorkDiaryTypes.OTHER.equals(workDiaryType)) {
				if (StringUtil.isStringAvaliable(modeType)) {
					returnContent.append(modeType).append(": ");
				}
			} else {
				returnContent.append("事件: ");
			}
		}
		returnContent.append(name);
		returnContent.append("\n");
		if (WorkDiaryTypes.ISSUE_DEAL.equals(workDiaryType)) {
			returnContent.append(operatType).append("并填写留言:");
		} else {
			returnContent.append(WorkDiaryVo.map.get(objectType) + "并填写"
					+ WorkDiaryVo.map.get(objectType) + "情况：");
		}
		if (StringUtil.isStringAvaliable(content)) {
			returnContent.append(content);
		}
		return returnContent.toString();
	}

	@Override
	public void updateWorkDiary(String objectType, Long objectId,
			String workContent, String workPlace, String workUserName, Date time) {
		List<WorkDiary> workDiarys = workDiaryDao
				.getWorkDiaryByObjectTypeAndObjectId(objectType, objectId);
		for (WorkDiary workDiary : workDiarys) {
			workDiary.setWorkContent(workContent);
			workDiary.setWorkPlace(workPlace);
			workDiary.setWorkTime(time);
			workDiary.setWorkUserName(workUserName);
			workDiary.setObjectType(objectType);
			workDiary.setObjectId(objectId);
			if (validateInput(workDiary)) {
				throw new BusinessValidationException("参数错误");
			}
			workDiaryDao.updateWorkDiary(workDiary);
		}

	}

	@Override
	public void addWorkDiary(Long issueId, IssueLogNew issueLogNew) {
		WorkDiary workDiary = getWorkDiary(issueId, issueLogNew);
		this.addWorkDiary(workDiary);
	}

	private WorkDiary getWorkDiary(Long issueId, IssueLogNew issueLogNew) {
		WorkDiary workDiary = new WorkDiary();
		IssueNew issueNew = issueService.getFullIssueByIssueId(issueId);
		Long orgId = issueLogNew.getDealOrg().getId();
		Organization org;
		if (orgId < 0) {//呼叫中心
			Organization cmsOrganization = IssueToCMSUtil
					.getLocationOrgNameByLocationId(orgId);
			org = organizationDubboService.getOrgByDepartmentNo(cmsOrganization
					.getDepartmentNo());
		} else {
			org = organizationDubboService.getSimpleOrgById(issueLogNew.getDealOrg()
					.getId());
		}
		if (org == null) {
			throw new BusinessValidationException("层级不存在");
		}
		workDiary.setDiaryType(propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.WORKDIARY_DIARY_TYPE, "事件调解类"));
		workDiary.setOrganization(org);
		workDiary.setOrgInternalCode(org.getOrgInternalCode());
		workDiary.setWorkTime(issueLogNew.getDealTime());

		workDiary.setWorkPlace(issueNew.getOccurLocation());

		workDiary.setWorkContent(issueLogNew.getContent());
		workDiary.setWorkUserName(issueLogNew.getDealUserName());
		return workDiary;
	}
}