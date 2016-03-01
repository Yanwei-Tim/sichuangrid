<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="${path }/statAnalyseNew/baseInfoStat/common/commonStatisticColumn.ftl" >
	<@s.param name="type" >
		<@s.property value="@com.tianque.service.util.PopulationType@AIDSPOPULATIONS"/>
	</@s.param>
</@s.include>
