<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" class="container container_12">
	<form id="maintainForm" method="post"
		action="${path}/sysadmin/jobMonitor/updateAddressJob.action">
		<div class="grid_3 lable-right">
			<label class="form-lb1">表名：</label>
		</div>
		<div class="grid_6">
			<input name="tableName" type="text">
		</div>
	</form>
</div>

<script type="text/javascript">
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			$(form).ajaxSubmit({
				success : function(data) {
					$.messageBox({
						message : "开始清洗地址信息!"
					});
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageBox({
						level:"error",
						message : "开始清洗地址信息!"
					});
				}
			});
		},
		rules : {"tableName":{required:true}},
		messages : {"tableName":{required: "请输入表名"}}
	});
</script>
