<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="editWechatInboxVoicePromptForm" method="post" action="${path}/wechatInboxVoicePromptManage/updateWechatInboxVoicePromptById.action">
		<pop:token></pop:token>
		
		<input type="hidden" id="wechatInboxVoicePromptId" name="wechatInboxVoicePrompt.id" value="${newwechatInboxVoicePrompt.id}" />
		<div class="grid_4 lable-right">
			<label>微信号：</label>
		</div>
		<div class="grid_10">
			<input type="text" readonly id="toUserName" name="wechatInboxVoicePrompt.toUserName"
				value="${newwechatInboxVoicePrompt.toUserName}" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label>语音状态：</label>
		</div>
		<div class="grid_10">	
		  <select name="wechatInboxVoicePrompt.voicePromptStatus.id" class="form-txt" id="voicePromptStatus" >
		      <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WECHAT_INBOX_VOICE_PROMPT_TYPE" defaultValue="${newwechatInboxVoicePrompt.voicePromptStatus.id}" />
		  </select>	  
		</div>

		<select id="attachFileNames" name="wechatInboxVoicePrompt.voiceUrl" multiple="multiple"
			style="display: none"></select>
		
	</form>
	<div class='clearLine'>&nbsp;</div>
	    <div class="filePanel">
		    <div class="grid_4 lable-right">
			     <label class="form-lbl">附件上传：</label>
		    </div>
		    <div id="attachFilesDiv" class="grid_20 heightAuto">
			     <div id="custom-queue"></div>
			     <input id="custom_file_upload" name="uploadFile" type="file" />
		    </div>
		    <div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				  <label class="form-lbl"></label>
			</div>
			<div class="grid_20 heightAuto">
			      <span style="color:red">*语音支持:</span>	<span  style="line-height: 30px; padding-left: 5px;font-style:normal;font-size:13px">mp3/4MB</span>
			</div>
	    </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
   
	//附件上传(图片)
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames",
		multi:false,
		maxFileUpload:1,
		queueSizeLimit :1,
		removeAction:"${path}/weChat/inbox/delelteAttachFile.action",
		fileExt        : "*.mp3",
		fileDesc       : '语音*.mp3;',
		sizeLimit:4194304
	});
	
	$("#editWechatInboxVoicePromptForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			
			alert($("#attachFileNames").val());
			$(form).ajaxSubmit({
				async : false,
				success:function(data){
					
         			if(data==true||data=="true"){
         				$("#wechatInboxVoicePromptDialog").dialog("close");
						$.messageBox({message:"成功修改微信语音提示!"});
						$("#wechatInboxVoicePromptList").trigger("reloadGrid");						
						initfindWechatInboxVoicePrompt();
         			}else{
         				$.messageBox({
							level: "error",
							message : data
			 			});
            			return;
         			}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					$.messageBox({message:"提交数据时发生错误"});
	   		    }
			});
		},
		rules:{
		},
		messages:{
		}		
	});

}); 
</script>