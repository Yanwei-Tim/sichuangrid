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
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.eventSource.dao.CommandCenterDao;
import com.tianque.eventSource.dao.SearchCommandCenterDao;
import com.tianque.issue.constants.IssueConstants;
import com.tianque.issue.vo.IssueViewObject;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("searchCommandCenterService")
@Transactional
public class SearchCommandCenterServiceImpl extends AbstractBaseService implements
		SearchCommandCenterService {

	@Autowired
	private SearchCommandCenterDao searchCommandCenterDao;
	@Autowired
	private CommandCenterDao commandCenterDao;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public PageInfo<IssueViewObject> searchCommandCenterIssueForPageBySearchIssueVoNew(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx, String sord,
			PropertyDict propertyDict) {
		List<PropertyDict> list = null;

		if (propertyDict.getId() == null) {
			list = getSourceKindByDomainName(PropertyTypes.SOURCE_KIND);
		} else {
			list = new ArrayList<PropertyDict>();
			list.add(propertyDict);
		}
		searchIssueVo.setSourcekind(list);
		PageInfo<IssueViewObject> pageInfo = searchCommandCenterDao
				.searchCommandCenterIssueForPageBySearchIssueVoNew(searchIssueVo, page, rows, sidx,
						sord);
		for (int i = 0; i < pageInfo.getResult().size(); i++) {
			IssueViewObject temp = pageInfo.getResult().get(i);

			List<String> domains = commandCenterDao.getIssueType(temp.getIssueId());
			temp.setDomainName(getDomain(domains));

		}
		return pageInfo;
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

}
