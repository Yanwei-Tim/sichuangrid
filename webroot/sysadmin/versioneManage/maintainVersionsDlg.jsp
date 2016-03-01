<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
<form id="versionsForm" method="post" action="/sysadmin/versionsManage/maintainVersions.action">
	<input id="mode" type="hidden" name="mode" value="${mode}" /> 
	<input name="oldVersionsId" id="oldVersionsId" type="hidden" value="${versions.versionId}">
    <input name="isSubmit" id="isSubmit" type="hidden" value="">
    
	
	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">版本号：</label></div>
	<div class="grid_19">
		<input  type="text" id="versionId" maxlength="100"
			name="versions.versionId" style="width: 97%"
			value="${versions.versionId}" title="请输入版本号"
			class='form-txt {required:true,maxlength:100,messages:{required:"请输入版本号" , maxlength:$.format("版本号最多输入{0}个字符")}}' />
	</div>

	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">版本信息：</label></div>
	<div class="grid_19 heightAuto">
	<textarea id="versionContent" rows="8" cols="77"
		name="versions.versionContent" 
		title="请输入内容"
		class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}">${versions.versionContent}</textarea>
   </div>
</form>
<div class="clear"></div>



</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#versionId").focus();
	$('#versionContent').richImg();
	$("#versionsForm").formValidate({
		showErrors : showErrorsForTab,
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			$("#versionsForm").ajaxSubmit({
				success : function(data) {
					if (!data.versionId) {
						$.messageBox({
							message : data,
							level : "error"
						});
					return;
					}
					if ("add" == $("#mode").val()) {
						$("#versionsList").addRowData(data.versionId,data,"first");
						$("#versionsList").setSelection(data.versionId);
						 
						if ($("#isSubmit").val() == "true"){
							$("#versionsDialog").dialog("close");
						} else {
								emptyObject();
								}
						
						$.messageBox({message : "版本信息添加成功"
						});

						$("#versionsList").trigger("reloadGrid");
					}
					if ("edit" == $("#mode").val()) {
						$("#versionsList").setRowData(data.versionId,data);
						$.messageBox({message : "版本信息修改成功！"
						});
					$("#versionsList").setSelection(data.versionId);
					}
					$("#versionsDialog").dialog("close");
					$("#refresh").click();

				},
				error : function(
						XMLHttpRequest,
						textStatus,
						errorThrown){
						alert("提交错误");
						}
			});
		},
		rules : {

				},
		messages : {}
	});
	
	function emptyObject() {
		$("#versionsForm")[0].reset();
	}
						
						
});

	
</script>