<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="${path }/task/statistics/commonStatisticsColumn.ftl">
	<@s.param  name="type">
		<@s.property value="@com.tianque.domain.property.PropertyTypes@EXCEPTION_SITUATION_TYPE"/>
	</@s.param>
</@s.include>
