<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/unsettledPopulation/maintainUnsettledPopulationDlg.jsp" >
	<@s.param name="fromClaim">true</@s.param>
</@s.include>
<script type="text/javascript">
	$("#maintainForm").attr("action","${path}/plugin/dataManage/unsettledPopulationTempManage/maintainUnsettledPopulationInfo.action");
	$("#unsettledPopulation-idCardNo").removeAttr("readonly");
</script>

