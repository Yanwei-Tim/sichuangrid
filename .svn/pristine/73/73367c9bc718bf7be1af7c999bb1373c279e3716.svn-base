<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<form id="maintainForm" method="post" action="${path}/interactive/inboxPlatformMessageManage/replyPlatformMessage.action">
		<input type="hidden" name="platformMessage.replyMessageId"  value="${platformMessage.id }"/>
		<div class="grid_4 lable-right">
			<label class="form-lbl">收件人：</label>
		</div>
		<div class="grid_18"  >
			${platformMessage.sender.name}
			<input type="hidden" name="platformMessage.receiverId"  value="${platformMessage.sender.id}"/>
			<input type="hidden" name="platformMessage.receiverNames"  value="${platformMessage.sender.name}"/>
		</div>

		<div id="attachFilesDiv"></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">标题：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="platformMessage.title"  maxlength="50" value="回复:${platformMessage.title}" class="form-txt" />
		</div>
		<div class="grid_1"><em class="form-req">*</em></div>
		<div class="grid_4  heightAuto  lable-right">回复内容：</div>
		<div class="grid_19">
			<textarea rows="5" cols="50"  class="form-txt" name="platformMessage.content"></textarea>
			<textarea rows="5" class="form-txt" style="width:97%;height:140px;margin:0px;float:left;" disabled>
       发件人：${platformMessage.sender.name}
       发件人所属部门:${platformMessage.sender.organization.orgName}
       发送时间:<fmt:formatDate value="${platformMessage.sendDate}" type="date" pattern="yyyy-MM-dd HH:mm:SS" />
   ----------------------------------------
       消息内容：${platformMessage.content}
			</textarea>
		</div>
		<div class="grid_1"><em class="form-req">*</em></div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="display:none"></select>
	</form>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="filePanel" style="margin-top: 235px;">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div id="attachFilesDiv" class="grid_20 heightAuto">
			<div id="custom-queue"></div>
			<input id="custom_file_upload" name="uploadFile" type="file" />
		</div>
	</div>
	
</div>


<script type="text/javascript">
$(document).ready(function(){
	//附件上传
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});
	
	$("#attachFileNames").empty();
	
	
	
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	     $(form).ajaxSubmit({
	         success: function(data){
	                 if(data==null||!data.id){
	                	$.messageBox({message:data,level: "error"});
	                 	return;
	                 }
					 $.messageBox({message:"成功回复消息!"});
					 //工作台平台收件箱未读消息的回复
					 if($("#inboxPlatformMessageDialog").length==0){
						 $("#workBenchplatformMessageDialog").dialog("close");
						 $("#workBenchplatformMessageList").trigger("reloadGrid");
						 document.title = $("#pmInboxManagement").text();
					 }else{
						 $("#inboxPlatformMessageDialog").dialog("close");
						 reloadInboxPlatformMessage();
						 document.title = $("#pmInboxManagement").text();
					 }
				     getMessageByUser();
	  	   },
	  	   error: function(XMLHttpRequest, textStatus, errorThrown){
	  	      alert("提交错误");
	  	   }
	  	  });
		},
		rules:{
			"platformMessage.title":{
					required:true,
					minlength:2,
					maxlength:50
			},
			"platformMessage.content":{
					required:true,
					minlength:2,
					maxlength:5000
			}
		},
		messages:{
			"platformMessage.title":{
				required:"请输入主题",
				minlength:$.format("主题至少需要输入{0}个字符"),
				maxlength:$.format("主题最多输入{0}个字符")
			},
			"platformMessage.content":{
					required:"请输入内容",
					minlength:$.format("回复内容最少只能输入{0}个字符"),
					maxlength:$.format("回复内容最多只能输入{0}个字符")
			}
		}
	})
});

</script>
