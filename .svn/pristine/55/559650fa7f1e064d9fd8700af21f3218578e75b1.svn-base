<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="事件处理打分详情" class="container container_24">
	<div style="width: 100%;margin-top:6px">
		<table id="issueGradeHistoryList"></table>
		<div id="issueGradeHistoryListPager"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#issueGradeHistoryList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"targeOrg.orgName",label:"被考核单位",width:120},
	        {name:'pointType',label:'打分类型', width:60, formatter: function (val){ return val == 1 ? '加分' : '减分'},align:"center"},
	        {name:'points',label:'分值',width:40,align:"center"},
	        {name:'content',label:'打分原因',width:190},
	        {name:'regradedOrg.orgName',label:'考核单位',width:120},
	        {name:'regradedUserRealName',label:'考核用户',width:80,align:"center"},
	        {name:'regradedDate',label:'打分时间',width:120,align:"center"}
		],
		height:230,
		showColModelButton:false
	});
	$("#issueGradeHistoryList").setGridParam({
		url:"${path}/fourTeamsIssueManage/issueGradeHistory.action",
		sortname:"id",
		sortorder:"asc",
		datatype: "json",
		page:1
	});
	$("#issueGradeHistoryList").setPostData({
		"keyId": '<s:property value="#parameters.keyId[0]"/>'
   	});
	$("#issueGradeHistoryList").trigger("reloadGrid");
	$("#issueGradeHistoryList").jqGrid('setFrozenColumns');
});
</script>