<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueDelayList"> </table>
		<div id="issueDelayListPager"></div>
	</div>
</div>

<script type="text/javascript">
function onLoadDelay() {
	$("#issueDelayList").setGridParam({
		url:"${path}/issues/issueApplyDelay/findIssueDelayList.action?issueStepId=<s:property value='#parameters.issueStepId[0]'/>",
		datatype: "json",
		page:1
	});
	$("#issueDelayList").trigger("reloadGrid");
}
function auditStateFormatter(el,options,rowData) {
	if(rowData.auditState != null){
		if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@NOT_APPLY"/>' == rowData.auditState){
			return '';
		}else if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPIYING"/>' == rowData.auditState){
			return '<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPIYING_NAME"/>';
		}else if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_PASSING"/>' == rowData.auditState){
			return '<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_PASSING_NAME"/>';
		}else if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_NOT_PASSING"/>' == rowData.auditState){
			return '<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_NOT_PASSING_NAME"/>';
		}
	} else {
		return "";
	}
}
$(document).ready(function(){
	$("#issueDelayList").jqGridFunction({
		height:300,
		datatype: "local",
		colModel:[
			{name:'id',hidden:true,hidedlg:true},
			{name:'issueStepId',hidden:true,key:true,hidedlg:true},
			{name:'applyDate',label:'申请时间',index:'applyDate',width:90},
			{name:'applyUser.name',label:'申请人',width:90},
			{name:'delayInfo',label:'延期原因',width:240},
			{name:'auditDate',label:'审核时间',index:'auditDate',width:90},
			{name:'auditUser.name',label:'审核人',width:90},
			{name:'auditState',label:'审核情况',width:80,formatter:auditStateFormatter},
			{name:'permitDelayWorkday',label:'延期工作日',index:'permitDelayWorkday',width:70}
		],
		multiselect:false
	});
	onLoadDelay();
});
</script>