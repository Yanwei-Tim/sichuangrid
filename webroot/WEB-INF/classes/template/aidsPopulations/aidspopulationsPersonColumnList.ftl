<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/statAnalyse/baseInfoStat/common/commonStatisticColumn.jsp" >
	<@s.param name="type" ><@s.property value="@com.tianque.service.util.PopulationType@AIDSPOPULATIONS"/></@s.param>
</@s.include>
