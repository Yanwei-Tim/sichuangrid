package com.tianque.mobile.task.impl;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.task.ExceptionalSituationRecordMobileAdapter;
import com.tianque.plugin.taskList.controller.ExceptionalSituationRecordController;
import com.tianque.plugin.taskList.domain.ExceptionalSituationRecord;
import com.tianque.plugin.taskList.domain.ExceptionalSituationRecordVo;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Controller("exceptionalSituationRecordAdapter")
@Namespace("/mobile/exceptionalSituationRecordManage")
@Scope("request")
public class ExceptionalSituationRecordMobileAdapterImpl extends BaseMobileAction implements
		ExceptionalSituationRecordMobileAdapter {

	@Autowired
	private ExceptionalSituationRecordController exceptionalSituationRecordController;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	/** 社区服刑人员记录domain **/
	private ExceptionalSituationRecord exceptionalSituationRecord;
	/** 社区服刑人员记录查询domain **/
	private ExceptionalSituationRecordVo exceptionalSituationRecordVo;
	/** id字符串  **/
	private String ids;
	/** 组织id **/
	private Long orgId;
	/** 社区服刑人员记录集合 **/
	private List<ExceptionalSituationRecord> exceptionalSituationRecords;
	/** 附件名 **/
	/* 一个参数  */
	private String[] attachFiles;
	/* 多个参数  */
	private String[] attachFile;
	/** 附件名字 */
	private String[] attachFileNames;

	@Override
	@Action(value = "addExceptionalSituationRecordForMobile", results = {
			@Result(name = "success", type = "json", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String addExceptionalSituationRecord() throws Exception {
		exceptionalSituationRecordController
				.setExceptionalSituationRecord(exceptionalSituationRecord);
		exceptionalSituationRecordController.setAttachFile(attachFile);
		exceptionalSituationRecordController.setAttachFiles(attachFiles);
		exceptionalSituationRecordController.setAttachFileNames(attachFileNames);
		exceptionalSituationRecordController.addExceptionalSituationRecord();
		return SUCCESS;
	}

	@Override
	@Action(value = "deleteExceptionalSituationRecordsForMobile", results = {
			@Result(type = "json", name = "success", params = { "root", "true", "ignoreHierarchy",
					"false" }),
			@Result(type = "json", name = "error", params = { "root", "errorMessage" }) })
	public String deleteExceptionalSituationRecordsForMobile() throws Exception {
		exceptionalSituationRecordController.setIds(ids);
		exceptionalSituationRecordController.deleteExceptionalSituationRecords();
		return SUCCESS;
	}

	@Override
	@Action(value = "findExceptionalSituationRecordsForMobile", results = {
			@Result(type = "json", name = "success", params = { "root", "gridPage",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String findExceptionalSituationRecords() throws Exception {
//		fillExceptionalSituationRecordVo(exceptionalSituationRecordVo);
		if(getTqmobile()!=null){
			exceptionalSituationRecordVo.setMode(getTqmobile());
		}
		exceptionalSituationRecordController
				.setExceptionalSituationRecordVo(exceptionalSituationRecordVo);
		exceptionalSituationRecordController.setPage(page);
		exceptionalSituationRecordController.setRows(rows);
		exceptionalSituationRecordController.setSidx(sidx);
		exceptionalSituationRecordController.setSord(sord);
		exceptionalSituationRecordController.setOrgId(orgId);
		exceptionalSituationRecordController.findExceptionalSituationRecords();
		gridPage = exceptionalSituationRecordController.getGridPage();
		return SUCCESS;
	}

	@Override
	@Action(value = "viewExceptionalSituationRecordForMobile", results = {
			@Result(type = "json", name = "success", params = { "root",
					"exceptionalSituationRecord", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root", "errorMessage" }) })
	public String viewExceptionalSituationRecord() throws Exception {
		exceptionalSituationRecordController.setId(exceptionalSituationRecordVo.getId());
		exceptionalSituationRecordController.viewExceptionalSituationRecord();
		exceptionalSituationRecord = exceptionalSituationRecordController
				.getExceptionalSituationRecord();
		return SUCCESS;
	}

	private void fillExceptionalSituationRecordVo(
			ExceptionalSituationRecordVo exceptionalSituationRecordVo) {
		/** 获取组织机构 网格类型--职能部门 属性字典信息 **/
		PropertyDict orgTypeDict = propertyDictService
				.findPropertyDictByDomainNameAndDictDisplayName(PropertyTypes.ORGANIZATION_TYPE,
						OrganizationType.FUNCTION_KEY);
		/** 获取当前用户信息 **/
		User user = ThreadVariable.getUser();
		/** 获取当前用户网格类型  **/
		Long orgTypeDictDictId = 0L;
		if (user != null && user.getOrganization() != null
				&& user.getOrganization().getOrgType() != null) {
			orgTypeDictDictId = user.getOrganization().getOrgType().getId();
		}
		/** 如果是职能部门获取父网格  **/
		if (orgTypeDict != null && orgTypeDict.getId() != null
				&& orgTypeDict.getId().equals(orgTypeDictDictId)) {
			if (exceptionalSituationRecordVo != null
					&& exceptionalSituationRecordVo.getOrganization() != null) {
				Organization organization = organizationDubboService
						.getParentOrgByOrgTypeAndChildOrgId(exceptionalSituationRecordVo
								.getOrganization().getId(), OrganizationLevel.TOWN);
				exceptionalSituationRecordVo.setOrganization(organization);
			}
		}
	}

	public ExceptionalSituationRecord getExceptionalSituationRecord() {
		return exceptionalSituationRecord;
	}

	public void setExceptionalSituationRecord(ExceptionalSituationRecord exceptionalSituationRecord) {
		this.exceptionalSituationRecord = exceptionalSituationRecord;
	}

	public ExceptionalSituationRecordVo getExceptionalSituationRecordVo() {
		return exceptionalSituationRecordVo;
	}

	public void setExceptionalSituationRecordVo(
			ExceptionalSituationRecordVo exceptionalSituationRecordVo) {
		this.exceptionalSituationRecordVo = exceptionalSituationRecordVo;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public List<ExceptionalSituationRecord> getExceptionalSituationRecords() {
		return exceptionalSituationRecords;
	}

	public void setExceptionalSituationRecords(
			List<ExceptionalSituationRecord> exceptionalSituationRecords) {
		this.exceptionalSituationRecords = exceptionalSituationRecords;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String[] getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(String[] attachFile) {
		this.attachFile = attachFile;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

}
