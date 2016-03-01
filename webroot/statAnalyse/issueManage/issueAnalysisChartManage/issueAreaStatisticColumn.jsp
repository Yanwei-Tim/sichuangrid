<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="nav" class="ui-corner-all">
		<label style="float:left;padding:0 10px;line-height:25px;">统计时间：</label>
		<select style="float:left;" name="searchType" id="searchType">
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_MONTH'/>">按月份</option>
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_QUARTER'/>">按季度</option>
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_YEAR'/>">按年度</option>
		</select>
		<select name="queryYear" id="cyear" onchange="" style="float:left;">
        </select>
	    <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <div id="monthDiv">
	        <select style="float:left;"  id="cmonth" onchange="">
	        </select>
	        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
	    </div>
	    <div id="quarterDiv">
	        <select style="float:left;"  id="quarter" onchange="">
	        </select>
	    </div>
		<a id="csearch" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="issueColumn" class="SigmaReport" style="overflow:auto;"></div>

<script type="text/javascript">
function onOrgChanged(){
	if(!isGrid()){
	var chartCount=$("#issueColumn").columnChart({
		url: "${path}/issueAnalysisChartManage/getStatisticColumn.action?orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val()+"&searchType="+$("#searchType").val()+"&quarter="+$("#quarter").val(),
		moduleName:"事件处理统计",
		textx:-150,
		quantity:'个数'
	});
	}
	//chartCount.series[2].hide();
	//chartCount.series[4].hide();

	if(document.title=='总况'){
		enableOrDisableColumn(1);
	}else {
	  enableOrDisableColumn(2);
	}

}
$(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
			getQuarter();
		}
	});
	$("#quarterDiv").hide();
	$("#isNowYear").val($("#cyear").val());
	$("#isNowMonth").val($("#cmonth").val());
	$("#csearch").click(function(){
		onOrgChanged();
	});
	$("#cyear").change(function(){
		$("#cmonth").empty();
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
	
	var sigmaReportHeight=$(".ui-layout-center").outerHeight() - $("#chartsTabs .ui-tabs-nav").outerHeight() - $("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-50
	$(".SigmaReport").height(sigmaReportHeight);
	if(isGrid()){
		enableOrDisableColumn(1);
		enableOrDisableColumn(2);
	}else {
	    onOrgChanged();
	}
});


function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#quarter").append("<option value='"+responseData[i]+"'>第"+responseData[i]+"季度</option>"); 
			}
		}
	});
}


</script>