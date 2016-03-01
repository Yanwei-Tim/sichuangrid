<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/leaderView/house/commonStatistics.ftl">
	<@s.param name="moduleName">actualHouse</@s.param>
</@s.include>