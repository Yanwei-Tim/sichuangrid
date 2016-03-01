package com.tianque.mobile.sysadmin.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.domain.PropertyDict;
import com.tianque.issue.state.IssueState;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.sysadmin.PropertyDictMobileAdapter;
import com.tianque.sysadmin.service.PropertyDictService;

/*
 * 手机端：系统属性字典控制类
 */
@SuppressWarnings("serial")
@Transactional
@Scope("prototype")
@Controller("propertyDictMobileAdapter")
public class PropertyDictMobileAdapterImpl extends BaseMobileAction implements
		PropertyDictMobileAdapter {
	@Autowired
	public PropertyDictService propertyDictService;

	public List<PropertyDict> propertyDicts;
	public Map<String, Object> propertyDictMap = new HashMap<String, Object>();

	/**
	 * 批量查询数据字典
	 */
	public String findPropertyDictByDomainNames() throws Exception {
		String domainNames = ServletActionContext.getRequest().getParameter(
				"domainNames");
		String separator = ServletActionContext.getRequest().getParameter(
				"separator");
		String[] domainNameArray = null;
		if (domainNames.contains(separator)) {
			domainNameArray = domainNames.split(separator);
			for (String dictName : domainNameArray) {
				if ("事件状态".equals(dictName)) {
					propertyDicts = new ArrayList<PropertyDict>();
					issueState(propertyDicts);
					propertyDictMap.put(dictName, propertyDicts);
				} else {
					propertyDicts = propertyDictService
							.findPropertyDictByDomainName(dictName);
					propertyDictMap.put(dictName, propertyDicts);
				}
			}
		} else {
			if ("事件状态".equals(domainNames)) {
				propertyDicts = new ArrayList<PropertyDict>();
				issueState(propertyDicts);
				propertyDictMap.put(domainNames, propertyDicts);
			} else {
				propertyDicts = propertyDictService
						.findPropertyDictByDomainName(domainNames);
				propertyDictMap.put(domainNames, propertyDicts);
			}
		}
		return SUCCESS;
	}

	private void issueState(List<PropertyDict> list) {
		getIssueState(list, "办理中", IssueState.DEALING_CODE);
		getIssueState(list, "待受理", IssueState.UNCONCEPTED_CODE);
		getIssueState(list, "待阅读", IssueState.UNREAD_CODE);
		getIssueState(list, "已办", IssueState.STEPCOMPLETE_CODE);
		getIssueState(list, "已完成", IssueState.ISSUECOMPLETE_CODE);
	}

	private void getIssueState(List<PropertyDict> list, String name,
			int issueState) {
		PropertyDict mobilePropertyDict = new PropertyDict();
		mobilePropertyDict.setId(Long.parseLong(issueState + ""));
		mobilePropertyDict.setDisplayName(name);
		list.add(mobilePropertyDict);
	}

	public List<PropertyDict> getPropertyDicts() {
		return propertyDicts;
	}

	public void setPropertyDicts(List<PropertyDict> propertyDicts) {
		this.propertyDicts = propertyDicts;
	}

	public Map<String, Object> getPropertyDictMap() {
		return propertyDictMap;
	}

	public void setPropertyDictMap(Map<String, Object> propertyDictMap) {
		this.propertyDictMap = propertyDictMap;
	}

}
