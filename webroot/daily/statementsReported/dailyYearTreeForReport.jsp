<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />


<div class="center-left">
	<div class="propertyDomainList" style="overflow:hidden;width:186px;" >
		请选择年度：
		<select id="dailyYear" name="dailyYear">
		<s:iterator value="dailyYears">
			<option value="${id}">${name}</option>
		</s:iterator>
		</select>
	</div>
	<div class="orgTree">
		<div id="dailyPortionDirectoryTree"></div>
	</div>
	<input type="hidden" id="dailyDirectoryId" value="" />
	<input type="hidden" id="currentOrganizationId" value="${currentOrganization.id}">
	<input type="hidden" id="type" value="" />
</div>
<div class="center-right" id="dailyLogListContainer">


</div>
<script type="text/javascript">

var dailyDirectoryType; 

$(document).ready(function(){
	var centerHeight=$("div.ui-layout-center").height();
	$(".orgTree").height(centerHeight-80);
	$(".center-right").height(centerHeight-50);
	if($("#dailyYear").val() == null || $("#dailyYear").val() == ""){
			$("#dailyPortionDirectoryTree").append("没有可以显示的目录树！");
		}else{
		var tree=$("#dailyPortionDirectoryTree").initDailydirectoryTree({dailyYearId:$("#dailyYear").val(),isLoadDailyLogs:true});
		$.addClick(tree,afterChangNode);
	}
	$("#dailyYear").change(function(){
		changeYear($("#dailyYear").val());
	});
});

function loadDailyLogList(menuType,url){
	$.loadingComp("open");
	$("#baseLine").nextAll(".ui-dialog").remove();
	$("#dailyLogListContainer").load(url,function(){});
}

function afterChangNode(node){
	var workingRecordmenuType = 'myWorkingRecord';
	var typeInternalId = node.attributes.internalId;
	var reportTypeInternalId = node.attributes.reportTypeInternalId;
	var textValue = node.attributes.text;
	if(textValue == '台账目录树' || textValue == '报表上报'){
		return ;
	}
	$("#dailyDirectoryId").val(node.attributes.id);
	$("#type").val(textValue);
	loadDailyLogList(workingRecordmenuType,"${path}/daily/statementsReportedManage/findStatementsReportedDetailList.action?typeInternalId="+typeInternalId+"&reportTypeInternalId="+reportTypeInternalId);
	
}

function changeYear(){
	var yearId = $("#dailyYear").val();
	$(".x-tree-root-ct:first",$("#dailyPortionDirectoryTree")).remove(); //删除根目录
	if(yearId == null || yearId == ""){
		$("#dailyPortionDirectoryTree").append("没有可以显示的目录树！");
		$.loadingComp("close");
	}else{
		var tree=$("#dailyPortionDirectoryTree").initDailydirectoryTree({dailyYearId:yearId});
		$.addClick(tree,afterChangNode);
	}
}


</script>