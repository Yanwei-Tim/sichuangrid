<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<div  class="container container_24">
	<form id="maintainForm" method="post" action="">
	  <input type="hidden" id="isCloseWindow" value="true"/>
	  <pop:token></pop:token>
	  	<input type="hidden" name="mode" value="${mode }">
		<input type="hidden" name="textMessage.ToUserName" value="${inbox.fromUserName}"><!-- 发送给谁 -->
		<input type="hidden" name="inbox.inboxId" value="${inbox.inboxId}"><!-- 用天存储 每条消息对应的回复记录  -->
		<input type="hidden" name="inbox.createUser" value="${inbox.createUser}"><!-- 存的是粉丝名，区别于系统中的ccuu字段  用于存储对应的回复消息记录列表-->
		<input type="hidden" id="weChatUserIdByInbox" name="inbox.toUserName" value="${inbox.toUserName}"><!-- 用于获取微信号 -->
		<input type="hidden" id="weChatUserId" name="tencentUser.weChatUserId" value="${tencentUser.weChatUserId}"/>
		<s:if test="#request.sendOrReply=='reply'">
			<div class="grid_4 lable-right">
				<label>收件人：</label>
			</div>	
			<div class="grid_20">
				${inbox.createUser}
			</div>
			<div class='clearLine'>&nbsp;</div>
		  	<select id="attachFileNames" name="attachFiles" multiple="multiple" style="display:none"></select>
		</s:if>
		<s:if test="#request.sendOrReply=='retransmission'">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><input type="button" value="选择粉丝" class="defaultBtn" id="selectPersonBtn"/>
			</div>
			<div class="grid_19 heightAuto">
				<input type="text" name="openId" readonly="readonly"	id="userReceivers" class="form-txt"/>
			</div>
					<div class='clearLine'>&nbsp;</div>
					<br/>
					<div style="margin-left:150px;">
					<input type="hidden" name="inboxAttachment.fileActualUrl" value="${inboxAttachment.fileActualUrl}" class="form-txt"/>
	                    <img  src="${inboxAttachment.fileActualUrl}" height="350" width="500" /></img>
                    </div>
		</s:if>

	</form>
		<s:if test="#request.sendOrReply=='reply'">
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
				<span style="color:red">*图片支持:</span>	<span  style="line-height: 30px; padding-left: 5px;font-style:normal;font-size:13px">jpg/1MB</span>
				</div>
			</div>
	  </s:if>
</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
$(document).ready(function(){
	//附件上传(图片)
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames",
		maxFileUpload:3,
		queueSizeLimit :3,
		removeAction:"${path}/weChat/inbox/delelteAttachFile.action",
		fileExt        : "*.jpg",
		fileDesc       : '图片',
		sizeLimit:1048576
	});
	$("#attachFileNames").empty();
	 <s:if test='"replyImageMessage".equals(mode)'>
	   $("#maintainForm").attr("action","/weChat/inbox/replyImageMessage.action");
	</s:if>
	<s:else>
	 $("#maintainForm").attr("action","/weChat/inbox/retransmissionImageMessage.action");
	</s:else>
	//收件人选择窗口
	$("#selectPersonBtn").click(function(){
		$(".tip-error").remove();
		$("#selectPersonDialog").createDialog({
			width: 650,
			height: 400,
			title:'选择粉丝',
			url:'${path}/fanManage/dispatch.action?mode=selectFanOrGroup&tencentUser.weChatUserId='+$("#weChatUserIdByInbox").val(),
			buttons:{
				"确定" : function(event){
					  $(this).dialog("close");
			    },
			    "关闭" : function(){
			    	$(this).dialog("close");
			    }
			}
		});
	});
	//收件人联想补全
	$("#userReceivers").personnelComplete({
		//url: "${path}/weChat/inbox/searchContacterForAutoComplete.action",
		//multiple: true,
		//postDataFunction: function(){return {orgId:''};},
		//select: function(data){},
		//itemClose: function(data){
		//}
	});
	//提交
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
		<s:if test='"retransmissionMsg".equals(mode)'>
			if($("#userReceivers").val()==''){
				 $.messageBox({message:"粉丝用户不能为空！",level: "warn"});
				 return ;
			}
	     </s:if>
	     <s:if test='"replyImageMessage".equals(mode)'>
				if($("#attachFileNames").val()==""||$("#attachFileNames").val()==null){
					 <s:if test='"replyImageMessage".equals(mode)'>
					    $.messageBox({message:"请上传回复的图片！",level: "warn"});
					 </s:if>
					<s:else>
					 $.messageBox({message:"请上传发送的图片！",level: "warn"});
					 </s:else>
					 return ;
				} 
		 </s:if>
	    $(form).ajaxSubmit({
	         success: function(data){
	        	   if(data==null||data=="null"){
	        		   <s:if test='"replyImageMessage".equals(mode)'>
			        		 if($("#isCloseWindow").val()=='true'){
			        		   $("#replyMessageDlg").dialog("close");
			        		 }else{
			        				$("#attachFileNames").html('');
									$('#custom_file_upload').uploadifyClearQueue();
									$("#isCloseWindow").val("true");
			        		  }
						  		$.messageBox({message:"回复成功!"});
							</s:if>
							<s:else>
							   $("#replyMessageDlg").dialog("close");
						   		$.messageBox({message:"转发成功!"});
							</s:else>
						$("#inboxList").trigger("reloadGrid"); 
	              }else{
	         			$.messageBox({message:data,level: "error"});
	         	  }
					
	  	   },
	  	   error: function(XMLHttpRequest, textStatus, errorThrown){
	  	      alert("提交错误");
	  	   }
	  	  });
		}
	});
	
});

</script>


