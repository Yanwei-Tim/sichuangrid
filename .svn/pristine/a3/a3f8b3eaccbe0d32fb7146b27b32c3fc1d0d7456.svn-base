<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
		<form id="searchSmsSubForm" method="post" action="javascript:(0);">
			<input type="hidden" name="searchSmsUplinkVo.parentId" value='<s:property value="#parameters.id"/>' >
		 	<div class="grid_7 lable-right">
				<label class="form-lbl">接收者手机：</label>
		 	</div>
			<div class="grid_15">
				<input type="text" name="searchSmsUplinkVo.receiverMobile" maxlength="11" class="form-txt" />
		 	</div>
	 		<div class='clearLine'>&nbsp;</div>
			<div class="grid_7 lable-right">
				<label class="form-lbl">发送状态：</label>
	 		</div>
	 		<div class="grid_15">
	 			<select id="status" name="searchSmsUplinkVo.status" class="form-select">
	 				<option value=""></option>
					<option value="1">待处理</option>
					<option value="2">已处理</option>
					<option value="3">待发送</option>
					<option value="4">发送成功</option>
					<option value="5">发送失败</option>
				</select>
	 		</div>
		</form>
</div>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#searchSmsSubForm").formValidate({
		submitHandler: function(form) {
			var data=$(form).serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
				dataJson[data[i].name]=data[i].value;
			}
			$("#smsSubUplinkList").setGridParam({
				url:'${path}/smsUplinkManage/findSubSmsUplinksBySearchVo.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#smsSubUplinkList").setPostData(dataJson);
			$("#smsSubUplinkList").trigger("reloadGrid");
			$("#smsUplinkDialog").dialog("close");
		}
	});
	
});

</script>
