<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/common/orgSelectedComponent.jsp"/>
<div id="chartsTabs">
	<ul>
		<li><a href="${path }/statAnalyseNew/baseInfoStat/population/commonPopylationColumn.ftl">区域分布图</a></li>
		<li><a href="${path }/statAnalyseNew/baseInfoStat/population/statisticsPopulationPie.ftl">类型分布图</a></li>
		<li><a href="${path }/statAnalyseNew/baseInfoStat/population/populationList.ftl">列表信息</a></li>
		<li><a href="${path }/statAnalyseNew/baseInfoStat/population/tendencyChartForPopulation.ftl?tableNameKey=
			<@s.property value="@com.tianque.core.util.BaseInfoTables@POPULATION_KEY"/>">趋势图</a></li>
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


function setOptionsWhenShowInfo(name, orgId){
	if(name.indexOf("流动人员") != -1){
		title='流动人员';
		url = '${path}/baseinfo/floatingPopulation/floatingPopulationStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}else if(name.indexOf("户籍人员") != -1){
		title='户籍人员';
		url = '${path}/baseinfo/householdPopulation/householdStaffStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}else if(name.indexOf("未落户人员") != -1){
		title='未落户人员';
		url = '${path}/baseinfo/unsettledPopulation/unsettledPopulationStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
	}else if(name.indexOf("境外人员") != -1){
		title='境外人员';
		url = '${path}/baseinfo/overseaPersonnel/overseaPersonnelListForStatistics.jsp?isSearch=1&orgIdForStat='+orgId;
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