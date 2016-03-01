<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<form id="maintainForm" method="post" action="${path}/interaction/sms/inbox/processSmsReceived.action">
		<input type="hidden" name="smsReceivedBox.smsContent" value="${smsReceivedBox.smsContent}" />
		<input type="hidden" name="smsReceivedBox.sourceMobile" value="${smsReceivedBox.sourceMobile}" />
		<input type="hidden" name="smsReceivedBox.receiverTime" 
			value="<s:date name="smsReceivedBox.receiverTime" format="yyyy-MM-dd HH:mm:ss"/>" />
		<input type="hidden" name="smsReceivedBox.id" value="${smsReceivedBox.id}" />
		<div class="grid_4 lable-right">
			<label class="form-lbl">处理人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="smsReceivedBox.handlePerson" value="${user.name}" class="form-txt" readonly />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_8">
	    	<input type="text" name="smsReceivedBox.handleMobile" value="${user.mobile}" class="form-txt" readonly />
		</div>
		<div class="grid_1"></div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right"">
			<label class="form-lbl">处理意见：</label>
		</div>
		<div class="grid_19">
			<textarea rows="8" cols="" name="smsReceivedBox.disposition" class="form-txt" maxlength="300"></textarea>
		</div>
		<div class="grid_1"><em class="form-req">*</em></div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft", 
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							message:data,
							evel: "error"							
			 			});
            			return;
					}
		 			$("#smsReceivedList").setRowData(data.id,data);
					$.messageBox({message:"短信处理成功!"});
					$("#smsReceivedDialog").dialog("close");
		    		$("#smsReceivedList").setSelection(data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"smsReceivedBox.disposition":{
				required:true,
				minlength:2,
				maxlength:300
			}
		},
		messages:{
			"smsReceivedBox.disposition":{
				required:"请输入处理意见",
				minlength:$.format("处理意见至少需要输入{0}个字符"),
				maxlength:$.format("处理意见最多只能输入{0}个字符")
			}
		}
	});
});
</script>