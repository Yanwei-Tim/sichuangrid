<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/supervisionAdministrationManage/maintainSupervisionAdministration.action">
	<input type="hidden" name="supervisionAdministration.id" value="${supervisionAdministration.id }" />
	<input type="hidden" name="supervisionAdministration.orgId" value="${supervisionAdministration.orgId }" />

	<div>
		<textarea name="supervisionAdministration.content" id="communityGeography" rows="18" cols="78" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${supervisionAdministration.content}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">

jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#communityGeography").val().length>=8000){
		return false;
		}
	return true
});

$(document).ready(function(){
	$('#communityGeography').richImg();
	$('#communityGeography').focus();
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
						$.messageBox({
							message: data,
							level: "error"
						});
						return;
					}
					$("#maintainIntroduction_p").empty().append(data.content);
					$.messageBox({message:"已成功编辑-日常监督管理"});
					$("#supervisionAdministrationDialog").dialog("close");
				}
			});
			
		},
		rules:{
			"supervisionAdministration.content":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"supervisionAdministration.content":{
			maxlengthIntroduction:"您所输入的超过字数的限制!"
			}
		}
	});
})
</script>