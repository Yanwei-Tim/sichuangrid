<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<script type="text/javascript">

</script>

<form id="maintainForm" method="post" action="${path}/plugin/dataManage/${tempClassName}Manage/updateDustbinTempBase.action">
	<input id="districtOrgId" type="hidden" value="${(population.claimDetail.districtOrgId)! }" />
	<#include "*/${updatePage}">  
</form>
