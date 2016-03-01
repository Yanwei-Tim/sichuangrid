<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<div class="zt_tabs_style">
<@s.include value="/common/orgSelectedComponent.jsp"/>
	<div id="chartsTabs">
		<ul>
			<li><a href="${path }/statAnalyseNew/baseInfoStat/population/commonPopylationColumn.ftl?populationType=<@s.property value='#parameters.populationType[0]'/>">区域分布图</a></li>
			<@s.if test='#parameters.populationType[0]=="householdStaff"'>
			<li><a href="${path }/statAnalyseNew/baseInfoStat/population/statisticsPopulationInfoPie.ftl?populationType=<@s.property value='#parameters.populationType[0]'/>">类型分布图</a></li>
			</@s.if>
			<li><a href="${path }/statAnalyseNew/baseInfoStat/population/populationList.ftl?populationType=<@s.property value='#parameters.populationType[0]'/>">列表信息</a></li>
			<li><a href="${path }/statAnalyseNew/baseInfoStat/population/tendencyChartForPopulation.ftl?tableNameKey=
				<@s.property value='#parameters.populationType[0]'/>">趋势图</a></li>
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
		if('householdStaff'=='<@s.property value='#parameters.populationType[0]'/>')
			$("#chartsTabs").tabs("disable", 2);
		else	
		$("#chartsTabs").tabs("disable", 1);
	}else{
		$("#chartsTabs").tabs("enable", 0);
		$("#chartsTabs").tabs("enable", 1);
	}
}
function setOptionsWhenShowInfo(name, orgId){
	if(name.indexOf("流动人员") != -1){
		title='流动人员';
		url = '${path}/baseinfo/floatingPopulation/floatingPopulationStatistics.ftl?orgIdForStat='+orgId;
	}else if(name.indexOf("户籍人员") != -1){
		title='户籍人员';
		url = '${path}/baseinfo/householdPopulation/householdStaffStatistics.ftl?orgIdForStat='+orgId;
	}else if(name.indexOf("未落户人员") != -1){
		title='未落户人员';
		url = '${path}/baseinfo/unsettledPopulation/unsettledPopulationStatistics.ftl?orgIdForStat='+orgId;
	}else if(name.indexOf("境外人员") != -1){
		title='境外人员';
		url = '${path}/baseinfo/overseaPersonnel/overseaPersonnelListForStatistics.ftl?orgIdForStat='+orgId;
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