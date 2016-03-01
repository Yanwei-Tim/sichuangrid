<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<script type="text/javascript">
	var
	 columns = [
			{name:"orgName",caption:"区域",width:120,mode:"string"},
			{name:"amount",caption:"数量",width:80,mode:"number",align:'center',format:"#"},
			{name:"general",caption:"总况",children:[
				{name:"baseinfoStatisticDetailVo[index].typeName",caption:"组织类别",width:100,mode:"string"},
				{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",align:'center',format:"#"}
			]}
			<#--
			,
			{name:"general",caption:"<@s.property  value="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@groupMap.get(@com.tianque.core.util.BaseInfoTables@NEWSOCIETYORGANIZATIONS_DISPLAYNAME)[2]"/>",children:[
				{name:"baseinfoStatisticDetailVo[index].helped",caption:"<@s.property  value="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@groupMap.get(@com.tianque.core.util.BaseInfoTables@NEWSOCIETYORGANIZATIONS_DISPLAYNAME)[0]"/>",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].noHelp",caption:"<@s.property  value="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@groupMap.get(@com.tianque.core.util.BaseInfoTables@NEWSOCIETYORGANIZATIONS_DISPLAYNAME)[1]"/>",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].helped/baseinfoStatisticDetailVo[index].sum",caption:"<@s.property  value="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@groupMap.get(@com.tianque.core.util.BaseInfoTables@NEWSOCIETYORGANIZATIONS_DISPLAYNAME)[1]"/>",width:80,align:'center',mode:"number",format:"#.00%"}
				
			]}-->
		];

</script>
<@s.include value="${(path)!}/statAnalyseNew/baseInfoStat/common/commonStatisticList.ftl">
	<@s.param  name="type">
		<@s.property value="@com.tianque.service.util.PopulationType@NEW_SOCIETY"/>
	</@s.param>
</@s.include>