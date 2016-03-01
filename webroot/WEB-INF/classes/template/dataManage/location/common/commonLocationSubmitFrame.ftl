<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<script type="text/javascript">
	jQuery.validator.addMethod("exsistedName", function(value, element){
	var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:$('#ajaxUrl').val(),
		   	data:{
				"uniqueValue":$('#name').val(),
				"districtOrgId":$('#districtOrgId').val(),
				"tempId":$('#locationId').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
});
</script>

<form id="maintainForm" method="post" action="${path}/plugin/dataManage/${tempClassName}Manage/updateLocationTempBase.action">
	<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="${path}/plugin/dataManage/${tempClassName}Manage/hasDuplicate.action" />
	<input id="districtOrgId" type="hidden"  value="${(location.claimDetail.districtOrgId)! }" />
	<#include "*/${updatePage}">  
</form>
