<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="${(path)!}/statAnalyseNew/baseInfoStat/common/commonStatisticColumn.ftl">
	<@s.param  name="type">
		<@s.property value="@com.tianque.service.util.PopulationType@NEW_SOCIETY"/>
	</@s.param>
</@s.include>