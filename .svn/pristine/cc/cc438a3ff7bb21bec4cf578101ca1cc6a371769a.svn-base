package com.tianque.mobile.serviceRecord.impl;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.AutoCompleteData;
import com.tianque.mobile.serviceRecord.ServiceTeamMobileAdapter;
import com.tianque.plugin.serviceTeam.serviceRecord.controller.ServiceRecordController;
import com.tianque.plugin.serviceTeam.serviceRecord.domain.ServiceRecord;
import com.tianque.plugin.serviceTeam.serviceRecord.vo.ServiceRecordVo;
import com.tianque.plugin.serviceTeam.serviceTeamMember.vo.ServiceTeamMemberVo;

/**
 * @Description:服务记录相关
 * @author zhangyouwei@hztianque.com
 * @date: 2015-4-17 下午03:30:47
 */
@Scope("request")
@Controller("serviceTeamMobileAdapter")
@Namespace("/baseinfo/serviceRecordManage")
public class ServiceTeamMobileAdapterImpl extends BaseAction implements
		ServiceTeamMobileAdapter {
	/** 记录所属人查询List */
	private List<AutoCompleteData> serviceTeamMembers;
	/** 记录所属人搜索条件vo */
	private ServiceTeamMemberVo serviceTeamMemberVo;
	/** 组织机构ID */
	private Long orgId;
	/** 对象人员类型 */
	private String populationType;
	/** 对象id */
	private String objectIds;
	/** 显示记录 */
	private ServiceRecordVo serviceRecordVo;
	/** 存储记录 */
	private ServiceRecord serviceRecord;
	/** 记录id */
	private String recordIds;
	/** 删除的行数 */
	private int deleteCount;
	@Autowired
	private ServiceRecordController serviceRecordController;

	/** 获取成员列表 */
	@Action(value = "findServiceMembers", results = {
			@Result(type = "json", name = "success", params = { "root",
					"serviceTeamMembers", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String findServiceMembers() throws Exception {
		if (serviceTeamMemberVo == null || serviceTeamMemberVo.getOrg() == null
				|| serviceTeamMemberVo.getOrg().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		serviceRecordController.setServiceTeamMemberVo(serviceTeamMemberVo);
		serviceRecordController.findServiceMembers();
		serviceTeamMembers = serviceRecordController.getServiceTeamMembers();

		return SUCCESS;
	}

	/**
	 * 根据人员ID获取服务记录
	 */
	@Action(value = "findServiceRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	@Override
	public String findServiceRecords() throws Exception {
		if (!StringUtil.isStringAvaliable(objectIds)
				|| !StringUtil.isStringAvaliable(populationType)
				|| serviceRecordVo == null
				|| serviceRecordVo.getOrganization() == null
				|| serviceRecordVo.getOrganization().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		serviceRecordController.setObjectIds(objectIds);
		serviceRecordController.setPopulationType(populationType);
		serviceRecordController.setServiceRecordVo(serviceRecordVo);
		serviceRecordController.setOrgId(orgId);
		serviceRecordController.setPage(page);
		serviceRecordController.setRows(rows);
		serviceRecordController.setSidx(sidx);
		serviceRecordController.setSord(sord);
		serviceRecordController.findServiceRecords();
		gridPage = serviceRecordController.getGridPage();

		return SUCCESS;
	}

	/**
	 * 新增服务记录
	 */
	@Action(value = "saveServiceRecordForObject", results = { @Result(type = "json", name = "success", params = {
			"root", "serviceRecordVo", "ignoreHierarchy", "false" }) })
	@Override
	public String saveServiceRecordForObject() throws Exception {

		if (serviceRecord == null || serviceRecord.getOrganization() == null
				|| serviceRecord.getOrganization().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		serviceRecordController.setServiceRecord(serviceRecord);
		serviceRecordController.addServiceRecordForMobileForPeople();
		serviceRecordVo = serviceRecordController.getServiceRecordVo();
		return SUCCESS;
	}

	/**
	 * 删除
	 */
	@Action(value = "deleteServiceRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"deleteCount", "ignoreHierarchy", "false" }) })
	@Override
	public String deleteServiceRecords() throws Exception {
		if (!StringUtil.isStringAvaliable(recordIds)) {
			errorMessage = "参数错误";
			return ERROR;
		}
		serviceRecordController.setRecordIds(recordIds);
		serviceRecordController.deleteServiceRecordsForMobile();
		deleteCount = serviceRecordController.getDeleteCount();
		return SUCCESS;
	}

	/**
	 * 查看
	 */
	@Action(value = "viewSeriviceRecord", results = { @Result(type = "json", name = "success", params = {
			"root", "serviceRecordVo", "ignoreHierarchy", "false" }) })
	@Override
	public String viewSeriviceRecord() throws Exception {
		if (serviceRecord == null || serviceRecord.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		serviceRecordController.setServiceRecord(serviceRecord);
		serviceRecordController.viewServiceTeamForMobile();
		serviceRecordVo = serviceRecordController.getServiceRecordVo();
		return SUCCESS;
	}

	/**
	 * 修改
	 */
	@Action(value = "editServiceRecord", results = { @Result(type = "json", name = "success", params = {
			"root", "serviceRecordVo", "ignoreHierarchy", "false" }) })
	@Override
	public String editServiceRecord() throws Exception {
		if (serviceRecord == null || serviceRecord.getId() == null
				|| serviceRecord.getOrganization() == null
				|| serviceRecord.getOrganization().getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		serviceRecordController.setServiceRecord(serviceRecord);
		serviceRecordController.updateServiceRecordForMobile();
		serviceRecordVo = serviceRecordController.getServiceRecordVo();
		return SUCCESS;
	}

	public List<AutoCompleteData> getServiceTeamMembers() {
		return serviceTeamMembers;
	}

	public void setServiceTeamMembers(List<AutoCompleteData> serviceTeamMembers) {
		this.serviceTeamMembers = serviceTeamMembers;
	}

	public ServiceTeamMemberVo getServiceTeamMemberVo() {
		return serviceTeamMemberVo;
	}

	public void setServiceTeamMemberVo(ServiceTeamMemberVo serviceTeamMemberVo) {
		this.serviceTeamMemberVo = serviceTeamMemberVo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getPopulationType() {
		return populationType;
	}

	public void setPopulationType(String populationType) {
		this.populationType = populationType;
	}

	public String getObjectIds() {
		return objectIds;
	}

	public void setObjectIds(String objectIds) {
		this.objectIds = objectIds;
	}

	public ServiceRecordVo getServiceRecordVo() {
		return serviceRecordVo;
	}

	public void setServiceRecordVo(ServiceRecordVo serviceRecordVo) {
		this.serviceRecordVo = serviceRecordVo;
	}

	public ServiceRecord getServiceRecord() {
		return serviceRecord;
	}

	public void setServiceRecord(ServiceRecord serviceRecord) {
		this.serviceRecord = serviceRecord;
	}

	public String getRecordIds() {
		return recordIds;
	}

	public void setRecordIds(String recordIds) {
		this.recordIds = recordIds;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

}
