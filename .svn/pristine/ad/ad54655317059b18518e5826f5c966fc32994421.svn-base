<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
	<pop:token></pop:token>
	<div class="grid_6 lable-right">
		<label>组织：</label>
	</div>
	<div class="grid_15">
		<input type="text"  value="${smsAccount.org.orgName}" readonly="readonly" class="form-txt"  /> 
	</div>
	<div class="grid_6 lable-right">
		<label>短信账户名：</label>
	</div>
	<div class="grid_15">
		<input type="text" name="smsAccount.name" value="${smsAccount.name}" class="form-txt {required:true,maxlength:30,messages:{required:'请填写账户名',maxlength:'项目名称最多30个字符'}}"  /> 
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right">
		<label>短信密码：</label>
	</div>
	<div class="grid_15">
		<input type="text" name="smsAccount.pwd" value="${smsAccount.pwd}" class="form-txt {required:true,maxlength:30,messages:{required:'请填写密码',maxlength:'项目名称最多30个字符'}}"  />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right">
		<label>状态通知回调密码：</label>
	</div>
	<div class="grid_15">
		<input type="text" name="smsAccount.callbackPwd" value="${smsAccount.callbackPwd}" class="form-txt"  readonly="readonly"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_6 lable-right">
		<label>短信内容后缀：</label>
	</div>
	<div class="grid_15">
		<input type="text" name="smsAccount.contentSuffix" value="${smsAccount.contentSuffix}" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<input type="hidden" name="smsAccount.id" value="${smsAccount.id}">
	<input type="hidden" id="mode" value="${mode}">
  </form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var mode = "${mode}";
	if("update"==mode){
		$("#maintainForm").attr("action", "${path}/smsAccountManage/updateSmsAccount.action");
	}else if("add"==mode){
		$("#maintainForm").attr("action", "${path}/smsAccountManage/addSmsAccount.action");
	}
	//提交
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				success : function(data) {
					if (data ==true||data=="true") {
						$("#smsAccountMaintanceDialog").dialog("close");
						$.messageBox({message : "提交成功!"});
						$("#smsAccountList").trigger("reloadGrid");
					} else {
						$.messageBox({
							message : data,
							level : "error"
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				}
			});
			return false;
		}
	});
});
</script>