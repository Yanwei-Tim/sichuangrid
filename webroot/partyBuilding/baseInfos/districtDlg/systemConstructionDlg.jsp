<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/districtBasiccaseManage/addOrUpdateDistrictBasiccase.action?mode=editSystemConstruction">
	<input type="hidden" name="districtBasiccase.id" value="${districtBasiccase.id }" />
	<input type="hidden" name="districtBasiccase.organization.id" value="${districtBasiccase.organization.id }" />

	<div>
		<textarea name="districtBasiccase.systemConstruction" id="systemConstruction" rows="15" cols="78" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${districtBasiccase.systemConstruction}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">

jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#systemConstruction").val().length>=10000){
		return false;
		}
	return true
});

$(document).ready(function(){
	$('#systemConstruction').richImg();
	$('#systemConstruction').focus();
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
					$("#systemConstruction_p").empty().append(data.systemConstruction);
					$.messageBox({message:"已成功编辑-系统党建情况"});
					$("#districtBasicCaseDialog").dialog("close");
				}
			});
			
		},
		rules:{
			"districtBasiccase.systemConstruction":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"districtBasiccase.systemConstruction":{
			maxlengthIntroduction:"您所输入的超过字数的最大限制!"
			}
		}

	});
})
</script>