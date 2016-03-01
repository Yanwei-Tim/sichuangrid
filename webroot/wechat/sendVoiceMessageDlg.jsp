<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
.jp-jplayer{width:0 !important;height:0 !important;}
.jp-audio{background:url(/resource/system/images/jplayer/bg.png) no-repeat;overflow:hidden;width:90px;height:29px;line-height:27px;padding-left:15px;margin: 3px auto 0;}
.jp-audio a{float:left;}
.jp-pause{display:none;}
.jp-time{float:right;color:#333;}
.jp-current-time{display:none;}
</style>

<div  class="container container_24">
	<form id="maintainForm" method="post" action="">
	 <input type="hidden" id="isCloseWindow" value="true"/>
      <pop:token></pop:token>
		<input type="hidden" name="textMessage.ToUserName" value="${inbox.fromUserName}"><!-- 发送给谁 -->
		<input type="hidden" name="inbox.inboxId" value="${inbox.inboxId}"><!-- 用天存储 每条消息对应的回复记录  -->
		<input type="hidden" name="inbox.createUser" value="${inbox.createUser}"><!-- 存的是粉丝名，区别于系统中的ccuu字段  用于存储对应的回复消息记录列表-->
		<input type="hidden" id="weChatUserIdByInbox"  name="inbox.toUserName" value="${inbox.toUserName}"><!-- 用于获取微信号 -->
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
			<div class="grid_20 heightAuto">
				<input type="text" name="openId" readonly="readonly"	id="userReceivers" class="form-txt"/>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<br/>
			<div style="margin-left:150px;">
			 <div id="jp_container_1" class="jp-audio">
			        <a href="javascript:;" class="jp-play" data-type="${inboxAttachment.extFileName}" data-media="${inboxAttachment.fileActualUrl}">
			           <img src="/resource/system/images/jplayer/play.png" />
			        </a>
			        <a href="javascript:;" class="jp-pause" tabindex="1">
			           <img src="/resource/system/images/jplayer/stop.gif" />
			        </a>
			        <div class="jp-time">
				          <div class="jp-current-time">
				          </div>
				        <div class="jp-duration">
				        </div>
			        </div>
			    </div>

			   <input type="hidden" name="inboxAttachment.fileActualUrl" value="${inboxAttachment.fileActualUrl}" class="form-txt"/>
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
						<span style="color:red">*语音支持:</span>	<span  style="line-height: 30px; padding-left: 5px;font-style:normal;font-size:13px">mp3/256KB</span>
						</div>
				</div>
	     </s:if>
	
</div>
<div id="selectPersonDialog"></div>
<div id="jquery_jplayer_1" class="jp-jplayer"></div>
<script type="text/javascript">
$(document).ready(function(){
	//附件上传(图片)
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames",
		multi:false,
		maxFileUpload:1,
		queueSizeLimit :1,
		removeAction:"${path}/weChat/inbox/delelteAttachFile.action",
		fileExt        : "*.mp3;*.amr",
		fileDesc       : '语音*.mp3;*.amr',
		sizeLimit:262144
	});
	$("#attachFileNames").empty();
	 <s:if test='"replyVoiceMessage".equals(mode)'>
	   $("#maintainForm").attr("action","/weChat/inbox/replyVoiceMessage.action");
	</s:if>
	<s:else>
	 $("#maintainForm").attr("action","/weChat/inbox/retransmissionVoiceMessage.action");
	</s:else>
	//收件人选择窗口
	$("#selectPersonBtn").click(function(){
		$(".tip-error").remove();
		$("#selectPersonDialog").createDialog({
			width: 650,
			height: 400,
			title:'选择粉丝',
			url:'${path}/fanManage/dispatch.action?mode=selectFanOrGroup&tencentUser.weChatUserId='+$("#weChatUserIdByInbox").val(),
			buttons: {
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
	////	postDataFunction: function(){return {orgId:''};},
	///	select: function(data){},
	//	itemClose: function(data){
		//}
	});
	$("#jquery_jplayer_1").jPlayer({
		ready: function () {
			
		},
		swfPath: "/resource/external/jPlayer/js",
		solution: "flash, html",
		supplied: "mp3,m4a,flv",
		wmode: "window",
		keyEnabled: true
	});
	$(".jp-play").click(function(){
		var media=$(this).data("media");
		var type=$(this).data("type");
		$("#jquery_jplayer_1").jPlayer( "clearMedia" );
		var box=$(this).closest(".jp-audio");
		$(this).hide().next().show();
		$("#jquery_jplayer_1").jPlayer("setMedia", {
			"mp3": media
		}).jPlayer("play");
	})
	$(".jp-pause").click(function(){
		$(this).hide().prev().show();
		$("#jquery_jplayer_1").jPlayer("stop");
	})
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
	     <s:if test='"replyVoiceMessage".equals(mode)'>
			if($("#attachFileNames").val()==""||$("#attachFileNames").val()==null){
				 <s:if test='"replyImageMessage".equals(mode)'>
				    $.messageBox({message:"请上传回复的语音！",level: "warn"});
				 </s:if>
				<s:else>
				 $.messageBox({message:"请上传发送的语音！",level: "warn"});
				 </s:else>
				 return ;
			}
		</s:if>
	    $(form).ajaxSubmit({
	         success: function(data){
	        	 if(data==null||data=="null"){
	        		 <s:if test='"replyVoiceMessage".equals(mode)'>
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


