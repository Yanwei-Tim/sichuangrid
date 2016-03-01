<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags"%>
<div class="zt_tabs_style">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<div id="chartsTabs">
		<ul>
			<li><a href="${path }/statAnalyse/baseInfoStat/population/commonPopylationColumn.jsp?populationType=<s:property value='#parameters.populationType[0]'/>">区域分布图</a></li>
			<li><a href="${path }/statAnalyse/baseInfoStat/population/populationList.jsp?populationType=<s:property value='#parameters.populationType[0]'/>">列表信息</a></li>
			<li><a href="${path }/statAnalyse/baseInfoStat/population/tendencyChartForPopulation.jsp?tableNameKey=
				<s:property value='#parameters.populationType[0]'/>">趋势图</a></li>
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
		$("#chartsTabs").tabs("disable", 1);
	}else{
		$("#chartsTabs").tabs("enable", 0);
		$("#chartsTabs").tabs("enable", 1);
	}
}
function setOptionsWhenShowInfo(name, orgId){
	if(name.indexOf("流动人口") != -1){
		title='流动人口';
		url = '${path}/baseinfo/floatingPopulation/floatingPopulationStatistics.jsp?orgIdForStat='+orgId;
	}else if(name.indexOf("户籍人口") != -1){
		title='户籍人口';
		url = '${path}/baseinfo/householdPopulation/householdStaffStatistics.jsp?orgIdForStat='+orgId;
	}else if(name.indexOf("未落户人口") != -1){
		title='未落户人口';
		url = '${path}/baseinfo/unsettledPopulation/unsettledPopulationStatistics.jsp?orgIdForStat='+orgId;
	}else if(name.indexOf("境外人员") != -1){
		title='境外人员';
		url = '${path}/baseinfo/overseaPersonnel/overseaPersonnelListForStatistics.jsp?orgIdForStat='+orgId;
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