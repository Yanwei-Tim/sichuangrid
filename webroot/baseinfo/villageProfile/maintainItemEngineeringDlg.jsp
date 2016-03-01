<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/baseinfo/villageProfile/updateOrAddVillageProfile.action?mode=itemEngineering">
	<input type="hidden" name="villageProfile.id" value="${villageProfile.id }" />
	<input type="hidden" name="villageProfile.organization.id" value="${villageProfile.organization.id }" />

	<div>
		<textarea name="villageProfile.itemEngineering" id="itemEngineering" rows="18" cols="78">${villageProfile.itemEngineering}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">
jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#itemEngineering").val().length>=2000){
		return false;
		}
	return true
});
$(document).ready(function(){
	$('#itemEngineering').richText();
	$('#itemEngineering').focus();
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

					$("#itemEngineering_p").empty().append(data.itemEngineering);
					$.messageBox({message:"已成功编辑-重点工程项目信息"});
				}
			});
			$("#villageProfileDialog").dialog("close");
		},
		rules:{
			"villageProfile.itemEngineering":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"villageProfile.itemEngineering":{
				maxlengthIntroduction:$.format("包括文本格式在内最多只能输入2000个字符")
			}
		}
	});
})
</script>