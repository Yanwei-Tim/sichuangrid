<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="nav" class="ui-corner-all">
	<select id="type" style="float:right;">
		<option value="1">各区域办理情况</option>
		<option value="2">各层级办理情况</option>
		<option value="3">各层级办理情况1</option>
		<option value="4">各层级办理情况2</option>
		<!-- 新事件统计修改 -->	
		<!--<option value="1">各层级办理情况</option>
		<option value="2">各区域办理情况</option>
		<option value="3">各层级分类统计</option>
		<option value="4">各区域分类统计</option>
		<option value="5">按流程统计</option>
		<option value="6">环比情况</option>
		<option value="7">同比情况</option> -->
	</select>
	<select name="reoprtDateType" id="reoprtDateType" style="float:left;">
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@REPORT_DATE_TYPE" notNull="true"/>
	</select>
	<select name="queryYear" id="cyear" onchange="" style="float:left;">
    </select>
    <label style="float:left;padding:0 10px;line-height:25px;">年</label>
    <select style="float:left;" id="cmonth" onchange="">
    </select>
    <label id="month" style="float:left;padding:0 10px;line-height:25px;">月</label>
	<a id="csearch" href="javascript:void(0)"><span>查询</span></a>
</div>
<div id="chartsTabs">
	<div id="gridboxType" style="overflow:auto;">
	</div>
</div>
<div id="issuePrintDlg"></div>
<script type="text/javascript">
var grid = null;
var context = {};
var columns;
function reload() {
	switch($("#type").val()){
	case '1':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMangeNew/issueAreaDealStatNew.jsp");
		break;
	case '2':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMangeNew/issueForOrgLevelDealStatNew.jsp");
		break;
	case '3':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMangeNew/issueForOrgLevelDealStatNew.jsp");
		break;
	case '4':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMangeNew/issueForOrgLevelDealStatNew.jsp");
		break;
	/* 新事件统计修改 */
	/*case '1':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueForOrgLevelDealStat.jsp");
		break;
	case '2':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueAreaDealStat.jsp");
		break;
	 case '3':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueForOrgLevelTypeStat.jsp");
		break;
	case '4':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueAreaTypeStat.jsp");
		break;
	case '5':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueStepStat.jsp");
		break;
	case '6':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueMomStat.jsp");
		break;
	case '7':
		$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueYoyStat.jsp");
		break; */
	}
}
function onTypeChanged() {
	$("#type").change(function(){
		reload();
	});
}

function autoFillYearAndMonth() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

$(document).ready(function(){
	autoFillYearAndMonth();
	initMonthVal();
	$("#gridboxType").load("${path}/statAnalyse/issueManage/listMangeNew/issueAreaDealStatNew.jsp");
	/* 新事件统计修改 */
	//$("#gridboxType").load("${path}/statAnalyse/issueManage/listMange/issueForOrgLevelDealStat.jsp");
	$("#gridboxType").height($(".ui-layout-center").outerHeight() - $("#chartsTabs .ui-tabs-nav").outerHeight() - $("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-42)
	onTypeChanged();
	$("#csearch").click(function(){
		reload();
	});
	
	$("#reoprtDateType").change(function(){
		$("#cmonth").empty();
		/* 新事件统计修改 */
		/* $("#type").find("option[value='7']").remove();
		if($("#reoprtDateType").find("option:selected").text()!='按年度统计') {
			$("#type").append('<option value="7">同比情况</option>');
		} */
		initMonthVal();
	});
	
	$("#cyear").change(function(){
		initMonthVal();
	});
	
});

function autoAdaptation() {
	// 列表信息 和 柱图 饼图   外层容器计算高度
	$(".SigmaReport").height(
		$(".ui-layout-center").outerHeight() - $("").outerHeight() - $(".submenu").outerHeight() - $("ui-tabs-nav").outerHeight() - 10
	);
	$(".SigmaReport").width(
			$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -25
	);
	//列表信息
	$(".ui-tabs-panel").height(
		$(".ui-layout-center").outerHeight() - $("").outerHeight() - $(".submenu").outerHeight() - $("ui-tabs-nav").outerHeight() - 10
	);

	$(".ui-tabs-panel").width(
		$(".ui-layout-center").outerWidth()-$("#chartsTabs ul:eq(0)").width() -25
	);
}

function initMonthVal() {
	$.ajax({
		async: false,
		url: "${path }/statAnalyse/statRegradedPointManage/getMonthTypeList.action?reoprtDateType.id="+$("#reoprtDateType").val()+"&year="+$("#cyear").val(),
		success:function(responseData){
			$("#cmonth").empty();
			if(!responseData.length) {
				$("#cmonth").append("<option value='年'>1</option>");
				$("#cmonth").hide();
				$("#month").hide();
			} else if(isNaN(responseData[0])){
				$("#cmonth").show();
				$("#month").hide();
			} else {
				$("#cmonth").show();
				$("#month").show();
				responseData.sort(sortNumber);
			}
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
	$("#cyear").val(${nowYear});
	$("#cmonth").val(${nowMonth});
}

function sortNumber(a,b) {
	return b - a;
}

function getMonthValue() {
	if ($("#cmonth").val() == "第1季度" || $("#cmonth").val() == "年") {
		return 1;
	} else if ($("#cmonth").val() == "第2季度") {
		return 4;
	} else if ($("#cmonth").val() == "第3季度") {
		return 7;
	} else if ($("#cmonth").val() == "第4季度") {
		return 10;
	} else {
		return $("#cmonth").val()
	};
}

function print(){
	$("#issuePrint").printArea();
	$("#issuePrintDlg").dialog("close");
}
</script>