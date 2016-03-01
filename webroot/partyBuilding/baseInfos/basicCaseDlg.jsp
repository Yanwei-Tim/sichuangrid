<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/basicCaseManage/addOrUpdateBasicCase.action?mode=editBasicCase">
	<input type="hidden" name="basicCase.id" value="${basicCase.id }" />
	<input type="hidden" name="basicCase.organization.id" value="${basicCase.organization.id }" />
	<input type="hidden" name="basicCase.baseInfoType" value="${baseInfoType }" />

	<div>
		<textarea name="basicCase.baseCase" id="baseCase" rows="18" cols="78" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${basicCase.baseCase}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">

jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#baseCase").val().length>10000){ //BUG单 #31327 需求中党建基本情况的输入字符在300字以内，实际系统中的600个字
		return false;
		}
	return true
});

$(document).ready(function(){
	$('#baseCase').richImg();
	$('#baseCase').focus();
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
					$("#maintainBaseCase_p").empty().append(data.baseCase);
					$.messageBox({message:"已成功编辑-机关党建情况"});
					$("#basicCaseDialog").dialog("close");
				}
			});
			
		},
		rules:{
			"basicCase.baseCase":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"basicCase.baseCase":{
			maxlengthIntroduction:"您所输入的超过字数的最大限制!"
			}
		}

	});
})
</script>