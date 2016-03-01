<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.rowtitle{color:#444;font:bold 14px/35px "" !important;}
.rowtitle span{color:#999;font-size:14px !important;}
.rowtitleShow{font-size:12px;font-weight:normal;color:#888;margin-bottom:10px;margin-top:5px;}
</style>
<div id="dialog-form" title="事件处理打分详情" >
	<div style="width: 100%;margin-bottom:10px;">
		<div style="width: 98%;margin-bottom:30px;">
			<h3 class="rowtitle"><span>事件名称：</span>${issue.subject}</h3>
			<hr/>
			<h3 class="rowtitleShow">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>评价类型：</span>
			<c:if test="${issueEvaluate.evaluateType==3}">满意</c:if>
			<c:if test="${issueEvaluate.evaluateType==2}">基本满意</c:if>
			<c:if test="${issueEvaluate.evaluateType==1}">不满意</c:if>
			</h3>
			<div class="clear"></div>
			<h3 class="rowtitleShow">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>评价简述：</span>${issueEvaluate.evaluateContent}</h3>
			<div class="clear"></div>
		</div>
		<div style="width: 98%;margin-top:30px">
			<table id="issueGradeHistoryList"></table>
			<div id="issueGradeHistoryListPager"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	 function regradedOrgFormatter(el,options,rowData){
		var regradedOrgName = rowData.regradedOrg.orgName;
		if(regradedOrgName=='中国'){
			return '系统消息';
		}
		return regradedOrgName;
	} 
	 function regradedUserFormatter(el,options,rowData){
		 var regradedUser = rowData.regradedUserRealName;
		 if(regradedUser=='超级管理员' || regradedUser=='admin' ){
				return '系统消息';
			}
			return regradedUser;
		} 
	 function regradedTypeFormatter(el,options,rowData){
			var regradedType = rowData.regradedType;
			if(regradedType=='byPerson'){
				return '手动打分';
			}
			return '自动打分';
	 }
	$("#issueGradeHistoryList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"targeOrg.orgName",label:"被考核单位",width:80},
	        {name:'pointType',label:'打分类型', width:60, formatter: function (val){ return val == 1 ? '加分' : '减分'},align:"center"},
	        {name:'points',label:'分值',width:40,align:"center"},
	        {name:'content',label:'打分原因',width:190},
	        {name:'regradedOrg.orgName',label:'考核单位',width:80,formatter:regradedOrgFormatter},
	        {name:'regradedUserRealName',label:'考核用户',width:80,align:"center",formatter:regradedUserFormatter},
	        {name:'regradedDate',label:'打分时间',width:120,align:"center"},
	        {name:'regradedType',label:'打分类型',width:120,align:"center",formatter:regradedTypeFormatter}
		],
		height:230,
		showColModelButton:false
	});
	$("#issueGradeHistoryList").setGridParam({
		url:"${path}/issues/issueManage/issueGradeHistory.action",
		sortname:"id",
		sortorder:"asc",
		datatype: "json",
		page:1
	});
	$("#issueGradeHistoryList").setPostData({
		"keyId": '<s:property value="#parameters.keyId[0]"/>',
		"viewType":$("#jurisdictionsViewType").val()
   	});
	$("#issueGradeHistoryList").trigger("reloadGrid");
	$("#issueGradeHistoryList").jqGrid('setFrozenColumns');
});
</script>