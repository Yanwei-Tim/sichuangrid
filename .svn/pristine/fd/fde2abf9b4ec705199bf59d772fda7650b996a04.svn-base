<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="pyear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;"  id="pmonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
</div> 
<div id="importantPlacePie" class="SigmaReport" style="height:400px;width:100%;"></div>
<script type="text/javascript">

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#pyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#pmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
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
				$("#pyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#pyear").val());
	$("#isNowMonth").val($("#pmonth").val());
	onOrgChanged();
	$("#search").click(function(){
		onOrgChanged();
	});
	$("#pyear").change(function(){
		$("#pmonth").empty();
		getmonth();
	});
	$.gridboxHeight();
});

function onOrgChanged(){
	var populationType='${param.populationType}';
	$("#importantPlacePie").pieChart({
		url:"${path}/baseInfoStat/statisticsPopulation/getStatisticsPopulationPie.action?orgId="+getCurrentOrgId()+"&year="+$("#pyear").val()+"&month="+$("#pmonth").val()+"&populationType="+populationType,
		moduleName:document.title,
		onClick:function(event){
			setOptionsWhenShowInfo(event.point.name,getCurrentOrgId());
			showInfo(url, title, 900, 600,$("#pyear").val(),$("#pmonth").val());
		}
	});
	enableOrDisableColumn(1);
}


</script>