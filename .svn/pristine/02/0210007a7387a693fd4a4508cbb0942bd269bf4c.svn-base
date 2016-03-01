package com.tianque.mobile.sysadmin.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.sysadmin.OrganizationMobileAdapter;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * 手机端：（下辖）网格信息
 */
@Transactional
@Scope("request")
@Controller("organizationMobileAdapter")
public class OrganizationMobileAdapterImpl extends BaseMobileAction implements
		OrganizationMobileAdapter {

	@Autowired
	private OrganizationDubboService organizationDubboService;

	private List<Organization> organizationList;
	private Organization organization;
	private Map<String, Object> organizationMap = new HashMap<String, Object>();
	private Long orgId;

	/**
	 * 为污染源改造提供的根据网格获取街道
	 */
	public String findTownOrgInfo() throws Exception {
		if (orgId == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		organization = organizationDubboService.findTownOrgInfoByOrgId(orgId);
		return SUCCESS;
	}

	/**
	 * 获得当前登录的用户所属网格信息。
	 */
	public String getOrgByUserId() throws Exception {
		organization = organizationDubboService.getFullOrgById(ThreadVariable
				.getUser().getOrganization().getId());
		return SUCCESS;
	}

	/**
	 * 获得当前登录的用户的当前网格和下辖网格信息
	 * 
	 * @return
	 */
	public String findOrganizationsByParent() throws Exception {
		Long parentId = ThreadVariable.getUser().getOrganization().getId();
		if (parentId == null) {
			organizationList = new ArrayList<Organization>();
		} else {
			organization = organizationDubboService.getFullOrgById(parentId);
			organizationList = organizationDubboService
					.findOrgsByParentIdAndOrgTypeInternalIds(
							parentId,
							new Long[] {
									Long.valueOf(OrganizationType.ADMINISTRATIVE_REGION),
									Long.valueOf(OrganizationType.OTHER) });
			organizationMap.put("parentOrg", organization);
			organizationMap.put("childOrgList", organizationList);
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: findOrganizationsByParentId
	 * @Description: TODO根据传入id查询当前网格和下辖网格信息
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @author wanggz
	 * @date 2014-8-5 下午03:01:46
	 */
	public String findOrganizationsByParentId() throws Exception {
		if (orgId == null) {
			errorMessage = "请确认必填参数是否正确完善";
			return ERROR;
		}
		organization = organizationDubboService.getFullOrgById(orgId);
		organizationList = organizationDubboService
				.findOrgsByParentIdAndOrgTypeInternalIds(orgId, new Long[] {
						Long.valueOf(OrganizationType.ADMINISTRATIVE_REGION),
						Long.valueOf(OrganizationType.OTHER) });
		organizationMap.put("parentOrg", organization);
		organizationMap.put("childOrgList", organizationList);
		return SUCCESS;
	}

	/* get set方法 */
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Map<String, Object> getOrganizationMap() {
		return organizationMap;
	}

	public void setOrganizationMap(Map<String, Object> organizationMap) {
		this.organizationMap = organizationMap;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
}