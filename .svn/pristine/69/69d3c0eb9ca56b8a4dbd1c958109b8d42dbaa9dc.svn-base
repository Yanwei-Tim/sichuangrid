<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" action="${path }/districtBasiccaseManage/addOrUpdateDistrictBasiccase.action?mode=editPartyOrgsAndMembersCase">
	<input type="hidden" name="districtBasiccase.id" value="${districtBasiccase.id }" />
	<input type="hidden" name="districtBasiccase.organization.id" value="${districtBasiccase.organization.id }" />

	<div>
		<textarea name="districtBasiccase.partyOrgsAndMembersCase" id="partyOrgsAndMembersCase" rows="15" cols="78" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${districtBasiccase.partyOrgsAndMembersCase}</textarea>
	</div>
</form>
</div>

<script type="text/javascript">

jQuery.validator.addMethod("maxlengthIntroduction", function(value, element){
	if($("#partyOrgsAndMembersCase").val().length>=10000){
		return false;
		}
	return true
});

$(document).ready(function(){
	$('#partyOrgsAndMembersCase').richImg();
	$('#partyOrgsAndMembersCase').focus();
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
					$("#maintainPartyOrgsAndMembers_p").empty().append(data.partyOrgsAndMembersCase);
					$.messageBox({message:"已成功编辑-全区党组织及党员情况"});
					$("#districtBasicCaseDialog").dialog("close");
				}
			});
			
		},
		rules:{
			"districtBasiccase.partyOrgsAndMembersCase":{
				maxlengthIntroduction:true
			}
		},
		messages:{
			"districtBasiccase.partyOrgsAndMembersCase":{
			maxlengthIntroduction:"您所输入的超过字数的最大限制!"
			}
		}

	});
})
</script>