<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/baseinfo/basicInfomationManage/updateBasicInfomationbasicIntroduction.action">
	<input type="hidden" name="basicInfoMation.id" value="${basicInfoMation.id }" />
	<input type="hidden" name="organization.id" value="${organization.id }" />
	<input type="hidden" name="basicInfoMation.organization.id" value="${basicInfoMation.organization.id }" />

	<div>
		<textarea name="basicInfoMation.basicIntroduction" maxlength="2000" id="basicIntroduction" rows="18" cols="78" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${basicInfoMation.basicIntroduction}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">
jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#basicIntroduction").val().length>=2000){
		return false;
		}
	return true
});
$(document).ready(function(){
	$('#basicIntroduction').richImg();
	$('#basicIntroduction').focus();
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
					$("#basicInfoMationId").val(data.id);
					$("#natureGeography_p").empty().append(data.basicIntroduction);
					$.messageBox({message:"已成功编辑"});
					$("#villageProfileDialog").dialog("close");
				}
			});
			
		},
		rules:{
			"basicInfoMation.basicIntroduction":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"basicInfoMation.basicIntroduction":{
				maxlengthIntroduction:$.format("包括文本格式在内最多只能输入2000个字符")
			}
		}
	});
	if($("#basicIntroduction").val().length==0||$.trim($("#basicIntroduction").val()).length==0){
		$("#basicIntroduction").val("  xx 村地处 xx，是典型的  xxxx（平原、丘陵、山区等），距 xx 乡（镇）xx公里。全村幅员面积  xx  平方公里，整个地形呈 xx 形，地势 xx ，是集 xxxxxx 为一体的村庄。")
	}
})
</script>