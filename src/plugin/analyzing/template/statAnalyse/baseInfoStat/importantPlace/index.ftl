<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/common/orgSelectedComponent.jsp"/>    
<div class="zt_tabs_style">
	<div id="chartsTabs">
		<ul>
			<li><a href="${path }/statAnalyse/baseInfoStat/importantPlace/statisticsImportantPlaceColumn.ftl">区域分布图</a></li>
			<li><a href="${path }/statAnalyse/baseInfoStat/importantPlace/statisticsImportantPlacePie.ftl">类型分布图</a></li>
			<li><a href="${path }/statAnalyse/baseInfoStat/importantPlace/statisticsImportantPlaceList.ftl">列表信息</a></li>
			<li><a href="${path }/statAnalyse/baseInfoStat/allTendencyChart.ftl?tableNameKey=
				<@s.property value="@com.tianque.core.util.BaseInfoTables@IMPORTANTPLACE_KEY"/>">趋势图</a></li>
		</ul>
					<input id="isNowYear"  type="hidden"/>
		<input id="isNowMonth"  type="hidden"/>
		<div id="infoList" ></div>
	</div>
</div>

<script type="text/javascript">
var url = '';
var title = '';
var width = 900;
var height = 600;


$(document).ready(function() {
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	$.loadingComp("close");
});


function setOptionsWhenShowInfo(name, orgId){
	if(name.indexOf("安全生产重点") != -1){
		title='安全生产重点';
		url = '${path}/baseinfo/siteinfo/enterprise/enterpriseListForStatistics.ftl?keyType=safetyProductionKey&orgIdForStat='+orgId;
	}else if(name.indexOf("消防安全重点") != -1){
		title='消防安全重点';
		url = '${path}/baseinfo/siteinfo/enterprise/enterpriseListForStatistics.ftl?keyType=fireSafetyKey&orgIdForStat='+orgId;
	}else if(name.indexOf("治安重点") != -1){
		title='治安重点';
		url = '${path}/baseinfo/siteinfo/enterprise/enterpriseListForStatistics.ftl?keyType=securityKey&orgIdForStat='+orgId;
	}else if(name.indexOf("学校") != -1){
		title='学校we';
		url = '${path}/baseinfo/siteinfo/school/schoolListForStatistics.ftl?orgIdForStat='+orgId;
	}else if(name.indexOf("其他场所") != -1){
		title='其他场所1';
		url = '${path}/baseinfo/siteinfo/otherLocale/otherLocaleListForStatistics.ftl?orgIdForStat='+orgId;
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
	showInfo(url, title, 900, 600,$("#queryYear").val(),$("#queryMonth").val());
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