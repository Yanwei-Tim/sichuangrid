<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div id="chartsTabs">
	<ul>
		<li><a href="${path }/statAnalyse/companyPlaceStanalsManageNew/commonPopylationColumn.jsp">区域分布图</a></li>
		<li><a href="${path }/statAnalyse/companyPlaceStanalsManageNew/statisticsCompanyPlacePie.jsp">类型分布图</a></li>
		<li><a href="${path }/statAnalyse/companyPlaceStanalsManageNew/companyPlaceList.jsp">列表信息</a></li>
		<li><a href="${path }/statAnalyse/companyPlaceStanalsManageNew/tendencyChartForCompanyPlace.jsp">趋势图</a></li>
	</ul>
</div>
<input id="isNowYear"  type="hidden"/>
<input id="isNowMonth"  type="hidden"/>
<div id="infoList"></div>

<script type="text/javascript">

var url = '';
var title = '';
var width = 900;
var height = 600;

$(document).ready(function() {

	$("#chartsTabs").tabs();

	// 列表信息 和 柱图 饼图   外层容器计算高度
	$.sigmaReportLayout();

	$.loadingComp("close");
});

function enableOrDisableColumn(i){
	if(isGrid()){
		$("#chartsTabs").tabs("select", i);
		$("#chartsTabs").tabs("disable", 0);
		$("#chartsTabs").tabs("disable", 2);
	}else{
		$("#chartsTabs").tabs("enable", 0);
		$("#chartsTabs").tabs("enable", 2);
	}
}
</script>