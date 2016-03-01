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
			<label>移动至：</label>
		</div>
		<div class="grid_19">
			<select class='form-txt' id='groupId'  name="fan.groupId" style="width: 510px" readonly>
			<option value="请选择分组">请选择分组</option>
			<s:iterator value="listWeChatGroup">
				<option value="${groupId}">${name}</option>
			</s:iterator>
		</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<input type="hidden" id="fanIds"name="fanIds" value="${fanIds}" />
	</form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	 $("#maintainForm").attr("action", "${path}/fanManage/removeFan.action");
	//提交
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			if($("#groupId").val()=="请选择分组"||$("#groupId").val()==""){
				$.messageBox({level: "warn",message : "请选择分组"});
				return false;
			}
			$(form).ajaxSubmit({
				success : function(data) {
					if (data ==true||data=="true") {
						$("#fanMaintanceDialog").dialog("close");
						$.messageBox({message : "移动成功!"});
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