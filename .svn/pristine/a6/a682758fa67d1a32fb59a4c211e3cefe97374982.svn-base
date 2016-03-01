<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<div class="zt_tabs_style">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<div id="chartsTabs">
		<ul>
			<li><a href="${path }/statAnalyse/companyPlaceStanalsManage/commonPopylationColumn.jsp?moduleType=<s:property value='#parameters.moduleType[0]'/>">区域分布图</a></li>
			<li><a href="${path }/statAnalyse/companyPlaceStanalsManage/statisticsCompanyPlacePie.jsp?moduleType=<s:property value='#parameters.moduleType[0]'/>">类型分布图</a></li>
			<li><a href="${path }/statAnalyse/companyPlaceStanalsManage/companyPlaceList.jsp?moduleType=<s:property value='#parameters.moduleType[0]'/>">列表信息</a></li>
			<li><a href="${path }/statAnalyse/companyPlaceStanalsManage/tendencyChartForCompanyPlace.jsp?moduleType=<s:property value='#parameters.moduleType[0]'/>">趋势图</a></li>
		</ul>
	</div>
	<input id="isNowYear"  type="hidden"/>
		<input id="isNowMonth"  type="hidden"/>
	<div id="infoList"></div>
</div>

<script type="text/javascript">
var url = '';
var title = '';
var width = 900;
var height = 600;
$(document).ready(function(){
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );

	$.loadingComp("close");
})

function enableOrDisableColumn(i){
	if(isGrid()){
		$("#chartsTabs").tabs("select", i);
		$("#chartsTabs").tabs("disable", 0);
		$("#chartsTabs").tabs("disable", 2);
	}else{
		$("#chartsTabs").tabs("enable", 0);
		$("#chartsTabs").tabs("enable", 1);
	}
}
function setOptionsWhenShowInfo(name, orgId){
	if(name.indexOf("单位") != -1){
		title='单位';
		//url = '${path}/baseinfo/floatingPopulation/floatingPopulationStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}else if(name.indexOf("场所") != -1){
		title='场所';
		//url = '${path}/baseinfo/householdPopulation/householdStaffStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}else if(name.indexOf("重点单位场所") != -1){
		title='重点单位场所';
		//url = '${path}/baseinfo/unsettledPopulation/unsettledPopulationStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}else if(name.indexOf("规模企业") != -1){
		title='规模企业';
		//url = '${path}/baseinfo/overseaPersonnel/overseaPersonnelListForStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}
}
function showInfo(url, title, width, height,year,month){
	if(year==$("#isNowYear").val() && month==$("#isNowMonth").val()){
		$("#infoList").createDialog({
			width: width,
			height: height,
			title: title+'信息',
			modal:true,
			url: url,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   			}
				}
			});
	}
}
function shwoInfoInDialog(name, orgId){
	setOptionsWhenShowInfo(name, orgId);
	showInfo(url, title, width, height,$("#year").val(),$("#month").val());
}
</script>