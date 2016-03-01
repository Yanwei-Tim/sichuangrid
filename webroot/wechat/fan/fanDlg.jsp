<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token></pop:token>
		<div class="grid_4 lable-right">
			<label>微信号：</label>
		</div>
		<div class="grid_19">
			<input type="text" readonly id="weChatUserId" name="fan.weChatUserId"
				value="${fan.weChatUserId}" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label>所属分组：</label>
		</div>
		<div class="grid_19">
			<input type="text" readonly value="${fan.groupName }" class='form-txt ' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label>粉丝昵称：</label>
		</div>
		<div class="grid_19">
			<input type="text" readonly value="${fan.name }" class='form-txt ' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label>备注名：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="fan.nickName" value="${fan.nickName}" maxlength="20" 
				class='form-txt {maxlength:20,messages:{maxlength:$.format("关键词描述最多可以输入{0}个字符")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<input type="hidden" id="mode" name="mode" value="${mode}" /> 
		<input type="hidden" value="${fan.groupId}" /> 
		<input type="hidden" id="fanId"name="fan.fanId" value="${fan.fanId}" />
	</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	 $("#maintainForm").attr("action", "${path}/fanManage/updateFan.action");
	//提交
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				success : function(data) {
					if (data ==true||data=="true") {
						$("#fanMaintanceDialog").dialog("close");
						$.messageBox({message : "修改成功!"});
						$("#fanList").trigger("reloadGrid");
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
		}
	});
});
</script>