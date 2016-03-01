<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="maintainForm" method="post" enctype="multipart/form-data" action="${path }/baseinfo/villageProfile/updateOrSaveVillageProfileImgUrl.action">
	<input type="hidden" name="villageProfile.id" value="${villageProfile.id }" />
	<input type="hidden" name="villageProfile.organization.id" value="${villageProfile.organization.id }" />

	<div class="zt_0705_1">
		<p>你可以上传JPG、GIF文件。</p>
		<p>图片上传：<input id="fileToUpload" type="file" value="" name="upload" /></p>
	</div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var fileLength = document;
	jQuery.validator.addMethod("checkFullFile", function(value, element){
		if($("#fileToUpload").val() == null || $("#fileToUpload").val()==""){
			return false;
		 }
		return true;
	});
	jQuery.validator.addMethod("checkFileExtName", function(value, element){
		if($("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length) != "jpg"
			&& $("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length) != "JPG"
			&& $("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length)!="gif"
			&& $("#fileToUpload").val().substring($("#fileToUpload").val().lastIndexOf(".")+1,$("#fileToUpload").val().length) != "GIF"){
			return false;
		 }
		return true;
	});

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					var jsonData=eval("("+data+")");
					if(!jsonData.id){
						$.messageBox({message:"上传失败，请上传正确格式的图片",level: "error"});
						$("#editPhotos_${caseImageUpload.id }").dialog("close");
						return;
					}
					if(!jsonData.success){
						$.messageBox({message:"上传文件大于2M上传失败！",level: "error"});
						$("#editPhotos_${caseImageUpload.id }").dialog("close");
						return;
					}
					$("#villageInfoId").val(jsonData.id);
					$("#image").empty().attr("src",function(){return "${path}"+"/"+jsonData.imgUrl});
					$.messageBox({message:"图片上传成功"});
					$("#villageProfileDialog").dialog("close");
				},
				error:function(){
					$.messageBox({message:"图片上传失败，请选择非空文件",level:'error'});
				}
			});
		},
        rules:{
			"upload":{
			checkFullFile:true,
			checkFileExtName:true
		   }
		},
		messages:{
			"upload":{
			checkFullFile:"图片为空不能上传",
			checkFileExtName:"图片只能为 jpg,gif格式的"
		   }
		}
	});
});
</script>