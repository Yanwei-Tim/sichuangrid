<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/baseinfo/villageProfile/updateOrAddVillageProfile.action?mode=economyGeography">
	<input type="hidden" name="villageProfile.id" value="${villageProfile.id }" />
	<input type="hidden" name="villageProfile.organization.id" value="${villageProfile.organization.id }" />

	<div>
		<textarea name="villageProfile.economyGeography" id="economyGeography" rows="18" cols="78" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${villageProfile.economyGeography}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">
jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#economyGeography").val().length>=2000){
		return false;
		}
	return true
});
$(document).ready(function(){
	$('#economyGeography').richImg();
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

					$("#economyGeography_p").empty().append(data.economyGeography);
					$.messageBox({message:"已成功编辑-经济发展情况和发展思路信息"});
					$("#villageProfileDialog").dialog("close");
				}
			});
			
		},
		rules:{
			"villageProfile.economyGeography":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"villageProfile.economyGeography":{
				maxlengthIntroduction:$.format("包括文本格式在内最多只能输入2000个字符")
			}
		}
	});
})
</script>