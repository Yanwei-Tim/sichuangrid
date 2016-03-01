<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>

<%	
	Long orgId = ThreadVariable.getSession().getOrganization().getId();
%>
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="year" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="month" id="month" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="searchPlace" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="importantPlacePie" class="SigmaReport" style="height:270px;width:100%;"></div>
<script type="text/javascript">
if(<%=AnalyseUtilNew.IS_NEWANALYSE_JOB%>){
	var data_table_url = "${path}/baseInfoStat/statisticsPlaceNew/getStatisticsImportantPlacePie.action?orgId=";
	var data_month_url = "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear=";
	var data_year_url = "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action";
}else{
	var data_table_url = "${path}/baseInfoStat/statisticsPlace/getStatisticsImportantPlacePie.action?orgId=";
	var data_month_url = "${path }/stat/currentTime/getCurrentTimeForMonthToSpecial.action?currenYear=";
	var data_year_url = "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action";
}
function getmonth(){
	$.ajax({
		async: false,
		url: data_month_url+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
$(document).ready(function() {
	$.ajax({
		async: false,
		url: data_year_url,
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#year").val());
	$("#isNowMonth").val($("#month").val());
	onOrgChanged();
	$("#searchPlace").click(function(){
		onOrgChanged();
	});
	$("#year").change(function(){
		$("#month").empty();
		getmonth();
	});
});

function onOrgChanged(){
	$("#importantPlacePie").pieChart({
		url:data_table_url+<%=orgId%>+"&year="+$("#year").val()+"&month="+$("#month").val()+"&typeTableName=IMPORTANTPLACE",
		moduleName:"重点场所"
	});
}


</script>