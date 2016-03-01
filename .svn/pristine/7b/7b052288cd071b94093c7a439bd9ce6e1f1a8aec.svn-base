<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="queryYear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;" name="queryMonth" id="queryMonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="newSocietyFederationPie" class="SigmaReport" style="height:400px;width:100%;"></div>

<script type="text/javascript">

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#queryYear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryMonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}
$(document).ready(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#queryYear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			//setTimeout('getmonth()',350);
			getmonth();
		}
	});
	onOrgChanged();
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#queryYear").change(function(){
		$("#queryMonth").empty();
		getmonth();
	});
	$.gridboxHeight();
});

function onOrgChanged(){
	$("#newSocietyFederationPie").pieChart({
		url:"${path}/baseinfo/statAnalysePlace/getStatisticsNewSocietyFederationPie.action?orgId="+getCurrentOrgId()+"&year="+$("#queryYear").val()+"&month="+$("#queryMonth").val()+"&typeTableName=newSocietyFederations",
		moduleName:"社会组织"
	});
	enableOrDisableColumn(1);
}
</script>