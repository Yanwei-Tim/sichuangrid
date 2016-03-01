<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<script type="text/javascript">
	var columns = [		
			{name:"orgName",caption:"区域",width:120,mode:"string"},	
			{name:"amount",caption:"人数",width:80,mode:"number",align:'center',format:"#"},
			{name:"general",caption:"总况",children:[
				{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",width:100,mode:"string"},
				{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",align:'center',format:"#"},
				{name:"baseinfoStatisticDetailVo[index].amountPercentage",caption:"百分比",width:80,mode:"number",align:'center',format:"#.00%"}
			]},
   			{name:"general",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@AIDSPOPULATIONS_DISPLAYNAME)[2]"/>",children:[
			{name:"baseinfoStatisticDetailVo[index].helped",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@AIDSPOPULATIONS_DISPLAYNAME)[0]"/>",width:80,mode:"number",align:'center',format:"#"},
			{name:"baseinfoStatisticDetailVo[index].noHelp",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@AIDSPOPULATIONS_DISPLAYNAME)[1]"/>",width:80,mode:"number",align:'center',format:"#"},
			{name:"baseinfoStatisticDetailVo[index].helped/baseinfoStatisticDetailVo[index].sum",caption:"<@s.property  value="@com.tianque.plugin.analyzing.util.AnalyseUtil@groupMap.get(@com.tianque.core.util.BaseInfoTables@AIDSPOPULATIONS_DISPLAYNAME)[3]"/>",width:80,align:'center',mode:"number",format:"#.00%"}
			]}
			
		];
</script>
<@s.include value="${path }/statAnalyse/baseInfoStat/common/commonStatisticList.ftl" >
	<@s.param name="type" ><@s.property value="@com.tianque.service.util.PopulationType@AIDSPOPULATIONS"/></@s.param>
</@s.include>
