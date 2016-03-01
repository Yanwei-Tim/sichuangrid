<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/common/orgSelectedComponent.jsp"/>    
<div class="zt_tabs_style">
	<div id="chartsTabs">
		<ul>
			<li><a href='${path }/statAnalyse/baseInfoStat/newSocietyFederation/newSocietyFederationColumn.jsp?keyTpe=<@s.property value="#parameters.keyTpe"/>'>区域分布图</a></li>
			<li><a href="${path }/statAnalyse/baseInfoStat/newSocietyFederation/newSocietyFederationPie.jsp">类型分布图</a></li>
			<li><a href='${path }/statAnalyse/baseInfoStat/newSocietyFederation/newSocietyFederationList.jsp?keyTpe=<@s.property value="#parameters.keyTpe"/>'>列表信息</a></li>
			<li><a href="${path }/statAnalyse/baseInfoStat/allTendencyChart.jsp?tableNameKey=
				<@s.property value="#parameters.keyTpe"/>">趋势图</a></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );

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