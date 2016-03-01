<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="nav" class="ui-corner-all">
		<label style="float:left;padding:0 10px;line-height:25px;">统计类型：</label>
		<select id="issueDomain" style="float:left;" name="issueDomain" >
	   		<pop:IssueTypeReleationSelectTag name=
	   		"contradiction,resolveTheContradictions,securityPrecautions,specialPopulations,socialConditions,policiesAndLaws,emergencies,otherManage" 
	   		defaultValue="${issueHasType.issueTypeDomain.id}" 
	   		reletionId="issueTypeId" isOperateDiv="0" id="issueDomain" defaultTypeValue="${issueHasType.id }"/>
		</select>
		<label style="float:left;padding:0 10px;line-height:25px;">统计时间：</label>
		<select style="float:left;" name="searchType" id="searchType">
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_MONTH'/>">按月份</option>
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_QUARTER'/>">按季度</option>
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_YEAR'/>">按年度</option>
		</select>
		<select name="queryYear" id="queryYear"  style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
	    <div id="monthDiv">
	        <select style="float:left;" name="queryMonth" id="queryMonth">
	        </select>
	        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
	    </div>
	     <div id="quarterDiv">
	        <select style="float:left;"  id="quarter" onchange="">
	        </select>
	    </div>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="positiveinfoPie" class="SigmaReport" style="height:400px;width:100%;"></div>

<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
			getQuarter();
		}
	});
	$("#issueDomain option:first").text("全部分类");
	$("#issueDomain option:first").val("0");
	$("#quarterDiv").hide();
	$("#isNowYear").val($("#queryYear").val());
	$("#isNowMonth").val($("#queryMonth").val());
	onOrgChanged();
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#queryYear").change(function(){
		$("#queryMonth").empty();
		$("#quarter").empty();
		getmonth();
		getQuarter();
	});
	$("#searchType").change(function(){
		if($("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_MONTH'/>"){
			$("#quarterDiv").hide();
			$("#monthDiv").show();
		}else if($("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_QUARTER'/>"){
			$("#quarterDiv").show();
			$("#monthDiv").hide();
		}else if($("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_YEAR'/>"){
			$("#quarterDiv").hide();
			$("#monthDiv").hide();
		}
	});
	$.gridboxHeight();
});

function onOrgChanged(){
	$("#positiveinfoPie").pieChart({
		url:"${path}/issueAnalysisChartManage/getStatisticPie.action?orgId="+getCurrentOrgId()+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&searchType="+$("#searchType").val()+"&quarter="+$("#quarter").val()+"&issueTypeDomainId="+$("#issueDomain").val(),
		moduleName:"事件处理统计",
		onClick:function(event){
			setOptionsWhenShowInfo(event.point.name,getCurrentOrgId());
			showInfo(url, title, width, height,$("#queryYear").val(),$("#queryMonth").val());
		}
	});
	enableOrDisableColumn(1);
}
function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#queryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#queryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#quarter").append("<option value='"+responseData[i]+"'>第"+responseData[i]+"季度</option>"); 
			}
		}
	});
}
</script>