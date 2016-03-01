package com.tianque.web.api.cms.callcenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.web.api.base.BaseWebApi;

/**
 * @ClassName: OrganizationWebService
 * @Description: 组织机构WEBAPI
 * @author wangxiaohu wsmalltiger@163.com
 * @date 2014年10月22日 下午3:53:30
 */
@Namespace("/webApi/org")
public class OrganizationWebService extends BaseWebApi {
	Logger logger = LoggerFactory.getLogger(OrganizationWebService.class);
	@Autowired
	public OrganizationDubboService organizationDubboService;
	private String departmentNo;
	private Long orgId;
	private String orgName;
	public Long parentId;
	private String mode;

	/**
	 * @Description: 根据组织机构编码获得坐席的组织机构
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "getOrganizationByDepartmentNo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String getOrganizationByDepartmentNo() {
		if (departmentNo != null) {
			try {
				Organization org = organizationDubboService
						.getOrgByDepartmentNo(departmentNo);
				head.setResultCode("1");
				head.setResultMsg("获取组织机构成功");
				body.setResult(org);
				return SUCCESS;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				head.setResultCode("0");
				head.setResultMsg("获取组织机构失败");
				body.setResult(false);
				return ERROR;
			}
		}
		head.setResultCode("0");
		head.setResultMsg("获取组织机构失败");
		body.setResult(false);
		return ERROR;
	}

	/**
	 * @Description: CMS获取组织机构树 当parentId为null时，取root节点
	 *               mode=2时，包含行政部门和职能部门,其它情况只返回行政部门
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "getOrgByParentId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String getOrgByParentId() {
		List<Organization> orgList = null;
		try {
			if (parentId != null) {
				if (!"2".equals(mode)) {
					List<PropertyDict> dictList = propertyDictService
							.findPropertyDictByDomainNameAndInternalId(
									PropertyTypes.ORGANIZATION_TYPE,
									OrganizationType.ADMINISTRATIVE_REGION);
					orgList = organizationDubboService
							.findOrganizationByParentIdAndOrgType(parentId,
									dictList.get(0).getId());
				} else {
					orgList = organizationDubboService
							.findOrganizationsByParentId(parentId);
				}

			} else {
				orgList = new ArrayList<Organization>();
				Organization root = organizationDubboService
						.getRootOrganization();
				orgList.add(root);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (null == orgList || orgList.size() < 1) {
			head.setResultCode("0");
			head.setResultMsg("获取辖区列表失败");
			body.setResult(new ArrayList());
			return ERROR;
		}

		List<Map> orgMapList = new ArrayList<Map>();
		head.setResultCode("1");
		head.setResultMsg("获取辖区列表成功");
		for (Organization organization : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", organization.getId());
			map.put("orgName", organization.getOrgName());
			map.put("subCount", organization.getSubCount());
			map.put("orgInternalCode", organization.getOrgInternalCode());
			orgMapList.add(map);
		}
		body.setResult(orgMapList);
		return SUCCESS;
	}

	/**
	 * @Description:根据org名称，查询社管org拼音
	 * @author wangxiaohu wsmalltiger@163.com
	 * @return
	 * @throws
	 */
	@Action(value = "getOrgChainByOrgName", results = {
			@Result(name = "success", type = "json", params = { "root",
					"response" }),
			@Result(name = "error", type = "json", params = { "root",
					"response" }) })
	public String getOrgChainByOrgName() {
		List<Long> orgIdList = new ArrayList<Long>();
		if (orgId == null) {
			head.setResultCode("0");
			head.setResultMsg("参数错误");
			body.setResult(new ArrayList());
			return ERROR;
		} else {
			try {
				Organization ancestor = organizationDubboService
						.getSimpleOrgById(orgId);
				if (ancestor != null && StringUtil.isStringAvaliable(orgName)) {
					List<Organization> orgListByName = organizationDubboService
							.findOrgByOrgNameAndInternalCode(orgName + "%",
									ancestor.getOrgInternalCode() + "%", 1, 1);
					if (orgListByName != null && orgListByName.size() > 0) {
						Organization orgBottom = orgListByName.get(0);
						if (orgBottom != null && orgBottom.getId() != null
								&& !orgBottom.getId().equals(orgId)) {
							orgIdList.add(orgBottom.getId());
							getParentIntoList(orgBottom.getId(), orgId,
									orgIdList);
						}
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		if (orgIdList.size() > 0 && orgIdList.contains(orgId)) {
			head.setResultCode("1");
			head.setResultMsg("查询成功");
			body.setResult(orgIdList);
		} else {
			head.setResultCode("0");
			head.setResultMsg("查询失败");
			body.setResult(new ArrayList());
			return ERROR;
		}
		return SUCCESS;
	}

	private void getParentIntoList(Long orgId, Long ancestorId,
			List<Long> idList) {
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org != null) {
			Organization pOrg = org.getParentOrg();
			if (pOrg != null && pOrg.getId() != null) {
				idList.add(pOrg.getId());
				if (pOrg.getId().longValue() != ancestorId) {
					getParentIntoList(pOrg.getId(), ancestorId, idList);
				}
			}
		}
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
