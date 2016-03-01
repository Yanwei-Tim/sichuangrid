<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	String type = request.getParameter("type");
	request.setAttribute("type",type);
%>
<style type="text/css">
	.SigmaReport{overflow:hidden;overflow-x:auto;width:99%;position:relative;}
</style>
<div id="nav" class="ui-corner-all">
		<select name="queryYear" id="cyear" onchange="" style="float:left;">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <select style="float:left;"  id="cmonth" onchange="">
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">月</label>
		<a id="csearch" href="javascript:void(0)"><span>查询</span></a>
		<div class="ui-slider-input" id="R0"></div>
		<div class="ui-slider-input" id="R1"></div> 
</div> 
<div id="${type}Column" class="SigmaReport"></div>
<script type="text/javascript">
var chartCount;
function onOrgChanged(){
	if(!isGrid()){
	chartCount=$("#${type}Column").columnChart({
		url: "${path}/baseInfo/primaryOrganizationStatNew/primaryOrgnizationColmunData.action?type=primaryOrganizations&orgId="+getCurrentOrgId()+"&year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		moduleName:document.title,
		textx:-150,
		height: document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216),
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
		url: "${path }/stat/currentTimeNew/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#isNowYear").val($("#cyear").val());
	$("#isNowMonth").val($("#cmonth").val());
	$("#csearch").click(function(){
		onOrgChanged();
	});
	$("#cyear").change(function(){
		$("#cmonth").empty();
		getmonth();
	});
	
	$.gridboxHeight();
	if(isGrid()){
		enableOrDisableColumn(1);
		enableOrDisableColumn(2);
	}else {
	    onOrgChanged();
	}
});

$("#R1").slider({
	min: 0,
	max: 50,
	value: 0,
	slide: function( event, ui ) {
		chartCount.options.chart.options3d.alpha =ui.value;
        showValues();
        chartCount.redraw(false);
	}
});
$("#R0").slider({ min: 0,
				  max: 50,
				  value: 0,
				  slide: function( event, ui ) {
				 	chartCount.options.chart.options3d.beta = ui.value;
			        showValues();
			        chartCount.redraw(false);
	}
});

function showValues() {
        $('#R0-value').html(chartCount.options.chart.options3d.alpha);
        $('#R1-value').html(chartCount.options.chart.options3d.beta);
    }

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTimeNew/getCurrentTimeForMonthToSpecial.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
}



</script>