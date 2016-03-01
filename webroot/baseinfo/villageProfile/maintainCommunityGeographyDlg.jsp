<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/baseinfo/villageProfile/updateOrAddVillageProfile.action?mode=communityGeography">
	<input type="hidden" name="villageProfile.id" value="${villageProfile.id }" />
	<input type="hidden" name="villageProfile.organization.id" value="${villageProfile.organization.id }" />

	<div>
		<textarea name="villageProfile.communityGeography" id="communityGeography" rows="18" cols="78" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${villageProfile.communityGeography}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">
jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#communityGeography").val().length>=2000){
		return false;
		}
	return true
});
$(document).ready(function(){
	$('#communityGeography').richImg();
	$('#communityGeography').focus();
	$('#economyGeography').richText();
	$('#economyGeography').focus();
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
					$("#communityGeography_p").empty().append(data.communityGeography);
					$.messageBox({message:"已成功编辑-社会发展情况信息"});
					$("#communityGeography").xheditor(false);
					$("#villageProfileDialog").dialog("close");
				}
			});
		},
		rules:{
			"villageProfile.communityGeography":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"villageProfile.communityGeography":{
				maxlengthIntroduction:$.format("包括文本格式在内最多只能输入2000个字符")
			}
		}
	});
})
</script>