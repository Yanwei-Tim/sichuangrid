<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/baseinfo/villageProfile/updateOrAddVillageProfile.action?mode=editIntroduction">
	<input type="hidden" name="villageProfile.id" value="${villageProfile.id }" />
	<input type="hidden" name="villageProfile.organization.id" value="${villageProfile.organization.id }" />

	<div>
		<textarea name="villageProfile.introduction" id="communityGeography" rows="18" cols="78" 
		class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${villageProfile.introduction}</textarea>
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
					$("#maintainIntroduction_p").empty().append(data.introduction);
					$.messageBox({message:"已成功编辑-辖区信息"});
					$("#villageProfileDialog").dialog("close");
				}
			});
		},
		rules:{
			"villageProfile.introduction":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"villageProfile.introduction":{
			maxlengthIntroduction:"您所输入的超过字数的限制!"
			}
		}

	});
})
</script>