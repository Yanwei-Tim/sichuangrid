<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<script type="text/javascript">
	var   columns = [
			{name:"orgName",caption:"区域",width:120,mode:"string"},
			{name:"amount",caption:"人数",width:80,mode:"number",align:'center',format:"#"},
			{name:"general",caption:"总况",children:[
				{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",width:100,mode:"string"},
				{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",align:'center',format:"#"}
				
			]},
			{name:"general",caption:"<@s.property  value="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@groupMap.get(@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_DISPLAYNAME)[2]"/>",children:[
				{name:"baseinfoStatisticDetailVo[index].helped",caption:"<@s.property  value="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@groupMap.get(@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_DISPLAYNAME)[0]"/>",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].noHelp",caption:"<@s.property  value="@com.tianque.plugin.analysisNew.util.AnalyseUtilNew@groupMap.get(@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_DISPLAYNAME)[1]"/>",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].helped/baseinfoStatisticDetailVo[index].sum",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@POSITIVEINFO_DISPLAYNAME)[3]"/>",width:80,align:'center',mode:"number",format:"#.00%"}
				
			]},
			{name:"general",caption:"安置情况",children:[
				{name:"baseinfoStatisticDetailVo[index].resited",caption:"已安置",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].noResite",caption:"未安置",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].resited/baseinfoStatisticDetailVo[index].sum",caption:"安置率",width:80,align:'center',mode:"number",format:"#.00%"}
			]},
			{name:"countField",caption:"本年度重新犯罪情况",children:[
			                            	{name:"baseinfoStatisticDetailVo[index].recidivism",caption:"本年度重新犯罪人数",width:80,mode:"number",align:'center',format:"#"},
			                            	{name:"baseinfoStatisticDetailVo[index].recidivism/baseinfoStatisticDetailVo[index].sum",caption:"重新犯罪率",width:80,align:'center',mode:"number",format:"#.00%"}
			                       ]}

		];
	
	
</script>
<@s.include value="${path }/statAnalyseNew/baseInfoStat/common/commonStatisticList.ftl">
	<@s.param  name="type">
		<@s.property value="@com.tianque.service.util.PopulationType@POSITIVE_INFO"/>
	</@s.param>
</@s.include>
