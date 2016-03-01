package com.tianque.plugin.taskList.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tianque.domain.Organization;
import com.tianque.plugin.taskList.constant.TaskConstant;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.tinygroup.commons.tools.StringUtil;

import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.positiveInfo.service.PositiveInfoService;
import com.tianque.controller.ControllerHelper;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.plugin.taskList.domain.PositiveInfoRecord;
import com.tianque.plugin.taskList.domain.PositiveInfoRecordVo;
import com.tianque.plugin.taskList.service.PositiveInfoRecordService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Scope("request")
@Namespace("/plugin/taskListManage/positiveInfoRecord")
@Controller("positiveInfoRecordController")
public class PositiveInfoRecordController extends BaseAction{
	@Autowired
	private PositiveInfoRecordService positiveInfoRecordService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PositiveInfoService positiveInfoService;
	
	/** 社区服刑人员记录domain **/
	private PositiveInfoRecord positiveInfoRecord;
	/** 社区服刑人员记录查询domain **/
	private PositiveInfoRecordVo positiveInfoRecordVo;
	/** id字符串  **/
	private String ids;
	/** 组织id **/
	private Long orgId;
	/** 标识是否是司法部门的账号访问  **/
	private Boolean flag;
	/** 社区服刑人员记集合 **/
	private List<PositiveInfoRecord> positiveInfoRecords;
	
	private Map<String,Object> map;	
	
	private PositiveInfo population;
	/***
	 * 人口信息刑释人员id
	 */
	private Long positiveInfoId;
	
	private String addFlag;
	// 只查有异常的
	private Boolean onlyHasException;

	@Action(value = "dispatch", results = {
			@Result(name = "success", location = "/template/task/maintainPositiveInfoRecordDlg.ftl"),
			@Result(name = "search", location = "/template/task/searchPositiveInfoRecordDlg.ftl") })
	public String maintainServiceMemberForPopulation() throws Exception {
		if (DialogMode.ADD_MODE.equals(mode)) {
			if(positiveInfoId!=null){
			  population = positiveInfoService.getPositiveInfoById(positiveInfoId);
			}
			fillNowDateAndSignDate();
			return SUCCESS;
		}else if (DialogMode.SEARCH_MODE.equals(mode)) {
			return "search";
		}else if (DialogMode.SIGN.equals(mode)){
			positiveInfoRecord = positiveInfoRecordService.getPositiveInfoRecordById(id);
			return SUCCESS;
		}
		return SUCCESS;
	}
	
	@Action(value = "addPositiveInfoRecord", results = {
			@Result(name = "success", type = "json", params = { "root",
					"positiveInfoRecord", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPositiveInfoRecord() throws Exception {
		if (positiveInfoRecord.getPositiveInfoId()!=null&&positiveInfoRecord.getHasException()==0) {
			positiveInfoRecord.setStatus(1L);
			positiveInfoRecord.setSignDate(new Date());
			positiveInfoRecord.setSignMemberName("系统管理员");
		}else{
			positiveInfoRecord.setStatus(0L);
		}
		positiveInfoRecord = positiveInfoRecordService.addPositiveInfoRecord(positiveInfoRecord);
		return SUCCESS;
	}
	
	@Action(value = "updatePositiveInfoRecord", results = {
			@Result(name = "success", type = "json", params = { "root",
					"positiveInfoRecord", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updatePositiveInfoRecord() throws Exception {
		positiveInfoRecord = positiveInfoRecordService
				.updatePositiveInfoRecord(positiveInfoRecord);
		return SUCCESS;
	}
	
	@Action(value = "deletePositiveInfoRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"true", "ignoreHierarchy", "false" }),
			@Result(type = "json", name = "error", params = { "root",
					"errorMessage" }) })
	public String deletePositiveInfoRecords() throws Exception {
		positiveInfoRecordService.deletePositiveInfoRecords(analyzeIds(ids));
		return SUCCESS;
	}
	
	@Action(value = "findPositiveInfoRecords", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findPositiveInfoRecords() throws Exception {
		if (onlyHasException != null && onlyHasException) {
			if (positiveInfoRecordVo.getOrganization() != null) {
				Organization org = organizationDubboService.getFullOrgById(positiveInfoRecordVo.getOrganization().getId());
				if (org != null && org.getFullOrgName().contains(TaskConstant.GUANG_AN)) {
					positiveInfoRecordVo.setHasException(1);
				}
			}
		}
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				positiveInfoRecordService.findPositiveInfoRecords(positiveInfoRecordVo,
						page, rows,sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}
	
	@Action(value = "findInterViewPositiveInfos", results = {
			@Result(type = "json", name = "success", params = { "root",
					"gridPage", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String findInterViewPositiveInfos() throws Exception {
		gridPage = new GridPage(ControllerHelper.processAllOrgRelativeName(
				positiveInfoRecordService.findPositiveInfoRecords(positiveInfoRecordVo,
						page, rows,sidx, sord), organizationDubboService,
				new String[] { "organization" }, orgId));
		return SUCCESS;
	}
	
	@Action(value = "viewPositiveInfoRecord", results = {
			@Result(name = "success", location = "/template/task/viewPositiveInfoRecordDlg.ftl") })
	public String viewPositiveInfoRecord() throws Exception {
		positiveInfoRecord = positiveInfoRecordService.getPositiveInfoRecordById(id);
		return SUCCESS;
	}
	
	@Action(value = "viewInterViewPositiveInfo", results = {
			@Result(name = "success", location = "/template/task/viewInterViewPositiveInfoDlg.ftl") })
	public String viewInterViewPositiveInfo() throws Exception {
		positiveInfoRecord = positiveInfoRecordService.getPositiveInfoRecordById(id);
		return SUCCESS;
	}
	
	public String findPositiveInfoRecordsByNameForMobile() throws Exception {
		positiveInfoRecords = positiveInfoRecordService.findPositiveInfoRecordsByName(positiveInfoRecordVo);
		return SUCCESS;
	}
	
	private List<Long> analyzeIds(String idStr){
		if(idStr == null){
			return null;
		}
		String[] deleteId = idStr.split(",");
		List<Long> idList = new ArrayList<Long>();
		if(StringUtil.isEmpty(deleteId[0])){
			return null;
		}else {
			for(int i=0;i<deleteId.length;i++){
				idList.add(Long.parseLong(deleteId[i]));
			}
		}
		return idList;
	}

	private void fillNowDateAndSignDate(){
		if(positiveInfoRecord == null){
			positiveInfoRecord = new PositiveInfoRecord();
		}
		positiveInfoRecord.setRecordDate(CalendarUtil.now());
		positiveInfoRecord.setSignDate(CalendarUtil.now());
	}
	
	public PositiveInfoRecordVo getPositiveInfoRecordVo() {
		return positiveInfoRecordVo;
	}

	public void setPositiveInfoRecordVo(PositiveInfoRecordVo positiveInfoRecordVo) {
		this.positiveInfoRecordVo = positiveInfoRecordVo;
	}

	public PositiveInfoRecord getPositiveInfoRecord() {
		return positiveInfoRecord;
	}

	public void setPositiveInfoRecord(PositiveInfoRecord positiveInfoRecord) {
		this.positiveInfoRecord = positiveInfoRecord;
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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<PositiveInfoRecord> getPositiveInfoRecords() {
		return positiveInfoRecords;
	}

	public void setPositiveInfoRecords(List<PositiveInfoRecord> positiveInfoRecords) {
		this.positiveInfoRecords = positiveInfoRecords;
	}

	public Long getPositiveInfoId() {
		return positiveInfoId;
	}

	public void setPositiveInfoId(Long positiveInfoId) {
		this.positiveInfoId = positiveInfoId;
	}

	public PositiveInfo getPopulation() {
		return population;
	}

	public void setPopulation(PositiveInfo population) {
		this.population = population;
	}

	public String getAddFlag() {
		return addFlag;
	}

	public void setAddFlag(String addFlag) {
		this.addFlag = addFlag;
	}

	public Boolean getOnlyHasException() {
		return onlyHasException;
	}

	public void setOnlyHasException(Boolean onlyHasException) {
		this.onlyHasException = onlyHasException;
	}
}
