<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container container_24">
<form id="maintainForm" method="post" action="">
	<c:if test='${mode=="add"}'>
			<pop:token/>
	</c:if>
	<input id="mode" type="hidden" name="mode" value="${mode}" /> 
	<input name="upgradeContent.id" id="id" type="hidden" value="${upgradeContent.id}">
	<input name="upgradeContent.isUpgrad" id="isUpgrad" type="hidden" value="0">
    <input name="isSubmit" id="isSubmit" type="hidden" value="">
    
	
	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">升级时间：</label></div>
	<div class="grid_19">
		<input type="text" name="upgradeContent.upgradeDate" readonly="readonly" maxlength="50" style="width: 99%" value="<s:date name='upgradeContent.upgradeDate' format='yyyy-MM-dd'/>" 
		class='form-txt {required:true,messages:{required:"请选择升级时间"}}' />
	</div>

	<div class="grid_4 lable-right" ><em class="form-req">*&nbsp;</em><label class="form-lb1">升级内容：</label></div>
	<div class="grid_19 heightAuto">
	<textarea id="upgradeContent" rows="8" cols="77"
		name="upgradeContent.upgradeContent" 
		title="请输入升级内容"
		class="form-txt {required:true,isLawful:true,messages:{required:'请输入升级内容',isLawful:'您输入了非法脚本，请重新输入'}}">${upgradeContent.upgradeContent}</textarea>
   </div>
</form>
<div class="clear"></div>


</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm input[name='upgradeContent.upgradeDate']").attr("id","upgradeDate");

	$('#upgradeDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd'
		});
	
	$("#id").focus();
	$('#upgradeContent').richImg();
	
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
					if(data == null){
						$.messageBox({
							message:"升级内容保存失败!",
							level: "error"
						});						
					}
					$("#upgradeContentManageDialog").dialog("close");
					$("#upgradeContentList").trigger("reloadGrid");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
	rules:{
	},
	messages:{
	}
	});
	$("#maintainForm").attr("action","${path}/sysadmin/upgradeContentManage/maintainUpgradeContent.action");
});

	
</script>