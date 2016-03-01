package com.tianque.eventSource.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.eventSource.dao.CommandCenterDao;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.domain.IssueNew;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("commandCenterService")
@Transactional
public class CommandCenterServiceImpl extends AbstractBaseService implements CommandCenterService {
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private CommandCenterDao commandCenterDao;

	@Override
	public PageInfo<IssueViewObject> findCommandCenterIssueForPageByTargeOrgIdAndDealState(
			Long targeOrgId, Long dealState, Integer page, Integer rows, String sidx, String sord) {
		PageInfo<IssueViewObject> pageInfo = commandCenterDao
				.findCommandCenterIssueForPageByTargeOrgIdAndDealState(targeOrgId, dealState, page,
						rows, sidx, sord, getSourceKindByDomainName(PropertyTypes.SOURCE_KIND));
		for (int i = 0; i < pageInfo.getResult().size(); i++) {
			IssueViewObject temp = pageInfo.getResult().get(i);

			List<String> domains = commandCenterDao.getIssueType(temp.getIssueId());
			temp.setDomainName(getDomain(domains));

		}
		return pageInfo;
	}

	@Override
	public PageInfo<IssueViewObject> findMyDone(Long orgId, IssueNew issue, Integer page,
			Integer rows, String sidx, String sord) {
		if (orgId == null) {
			return createEmptyIssueVoPageInfo(rows, 0);
		}
		return commandCenterDao.findMyDone(orgId, issue, page, rows, sidx, sord);
	}

	private String getDomain(List<String> domains) {
		String domainname = "";
		for (int i = 0; i < domains.size(); i++) {
			domainname = domainname + "," + domains.get(i);
		}
		return domainname.substring(1);
	}

	private List<PropertyDict> getSourceKindByDomainName(String domainName) {
		List<PropertyDict> newList = new ArrayList<PropertyDict>();
		List<PropertyDict> list = propertyDictService.findPropertyDictByDomainName(domainName);
		for (PropertyDict propertyDict : list) {
			if (propertyDict.getDisplayName().equals(IssueConstants.SMS_INPUT)
					|| propertyDict.getDisplayName().equals(IssueConstants.WEB_INPUT)
					|| propertyDict.getDisplayName().equals(IssueConstants.TELEPHONE_HOTLINE)) {
				propertyDict = propertyDictService.findPropertyDictByDomainNameAndDictDisplayName(
						PropertyTypes.SOURCE_KIND, propertyDict.getDisplayName());
				newList.add(propertyDict);
			}
		}
		return newList;
	}

	private PageInfo<IssueViewObject> createEmptyIssueVoPageInfo(int pageSize, int pageIndex) {
		PageInfo<IssueViewObject> result = new PageInfo<IssueViewObject>();
		result.setTotalRowSize(0);
		result.setCurrentPage(pageIndex);
		result.setPerPageSize(pageSize);
		return result;
	}

}
