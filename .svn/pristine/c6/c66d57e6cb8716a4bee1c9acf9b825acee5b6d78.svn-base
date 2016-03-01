<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div id="nav" class="ui-corner-all">
		<label style="float:left;padding:0 10px;line-height:25px;">统计类型：</label>
		<select id="issueTendecyType" style="float:left;">
	   		<option value="issueCount">事件量趋势</option>
	   		<option value="comparedSame">同比情况</option>
	   		<option value="sequential">环比情况</option>
		</select>
		<label style="float:left;padding:0 10px;line-height:25px;">统计时间：</label>
		<select style="float:left;" name="searchType" id="searchType">
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_MONTH'/>">按月份</option>
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_QUARTER'/>">按季度</option>
			<option value="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_YEAR'/>">按年度</option>
		</select>
		<div id="yearDiv">
			<select name="queryYear" id="cyear" onchange="" style="float:left;">
	        </select>
		    <label style="float:left;padding:0 10px;line-height:25px;">年</label>
	    </div>
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
<div id="issueLine" class="SigmaReport"></div>

<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
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
	$.gridboxHeight();
	$("#cyear").change(function(){
		$("#cmonth").empty();
		$("#quarter").empty();
		getmonth();
		getQuarter();
	});
	
	$("#searchType").change(function(){
		if($("#issueTendecyType").val()!="issueCount"&&$("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_MONTH'/>"){
			$("#yearDiv").show();
			$("#quarterDiv").hide();
			$("#monthDiv").show();
		}else if($("#issueTendecyType").val()!="issueCount"&&$("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_QUARTER'/>"){
			$("#yearDiv").show();
			$("#quarterDiv").show();
			$("#monthDiv").hide();
		}else if($("#issueTendecyType").val()!="issueCount"&&$("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_YEAR'/>"){
			$("#yearDiv").show();
			$("#quarterDiv").hide();
			$("#monthDiv").hide();
		}
	});
	$("#issueTendecyType").change(function(){
		if($("#issueTendecyType").val()=="issueCount"){
			$("#yearDiv").hide();
			$("#monthDiv").hide();
			$("#quarterDiv").hide();
			$("#searchType option:last").show();
		}else{
			$("#searchType").trigger("change");	
			if($("#issueTendecyType").val()=="comparedSame"){
				if($("#searchType option:last").attr("selected")=="selected"){
					$("#searchType option:first").attr("selected",true);
					$("#searchType").trigger("change");	
				}
				$("#searchType option:last").hide();
			}
			if($("#issueTendecyType").val()=="sequential"){
				$("#searchType option:last").show();
			}
		}
	});
	$("#issueTendecyType").trigger("change");
	onOrgChanged();
});

function onOrgChanged(){
	if($("#issueTendecyType").val()=="comparedSame" || $("#issueTendecyType").val()=="sequential"){
		if($("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_MONTH'/>"){
			if($("#cyear").val()==$("#cyear option:first").val()&&$("#cmonth").val()==$("#cmonth option:first").val()){
				$.messageBox({level:'warn',message:"本月数据还未生成,您可以查看其它月份"});
				return;
			}
		}
		if($("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_QUARTER'/>"){
			if($("#cyear").val()==$("#cyear option:first").val()&&$("#quarter").val()==$("#quarter option:first").val()){
				$.messageBox({level:'warn',message:"本季度数据还未生成,您可以查看其它季度"});
				return;
			}
		}
		if($("#searchType").val()=="<s:property value='@com.tianque.statAnalyse.issueManage.issueAnalysisChartManage.constants.IssueAnalysisSearchType@SEARCH_BY_YEAR'/>"){
			if($("#cyear").val()==$("#cyear option:first").val()){
				$.messageBox({level:'warn',message:"本年度数据还未生成,您可以查看其它年度"});
				return;
			}
		}
	}
	var issueLineUrl;
	if($("#issueTendecyType").val()=="issueCount"){	
		issueLineUrl="${path}/issueAnalysisChartManage/getIssueTendencyChart.action?orgId="+getCurrentOrgId()+"&searchType="+$("#searchType").val();
	}
	if($("#issueTendecyType").val()=="comparedSame"){			
		issueLineUrl="${path}/issueAnalysisChartManage/getIssueComparedSameChart.action?orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val()+"&searchType="+$("#searchType").val()+"&quarter="+$("#quarter").val();
	}
	if($("#issueTendecyType").val()=="sequential"){
		issueLineUrl="${path}/issueAnalysisChartManage/getIssueSequentialChart.action?orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val()+"&searchType="+$("#searchType").val()+"&quarter="+$("#quarter").val();
	}
	$("#issueLine").lineChart({
		url: issueLineUrl,
		moduleName:"",
		isIssue:true//同比和环比的趋势图允许为负数
	});
	enableOrDisableColumn(3);
}

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#cyear").val(),
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