package com.tianque.eventSource.service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.vo.SearchIssueVoNew;
import com.tianque.issue.vo.IssueViewObject;

public interface SearchCommandCenterService {

	public PageInfo<IssueViewObject> searchCommandCenterIssueForPageBySearchIssueVoNew(
			SearchIssueVoNew searchIssueVo, Integer page, Integer rows, String sidx, String sord,
			PropertyDict propertyDict);
}
