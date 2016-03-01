<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
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
      <@pop.token />
		<input type="hidden" name="textMessage.ToUserName" value="${inbox.fromUserName!}"><!-- 发送给谁 -->
		<input type="hidden" name="inbox.inboxId" value="${inbox.inboxId!}"><!-- 用天存储 每条消息对应的回复记录  -->
		<input type="hidden" name="inbox.createUser" value="${inbox.createUser!}"><!-- 存的是粉丝名，区别于系统中的ccuu字段  用于存储对应的回复消息记录列表-->
		<input type="hidden" id="weChatUserIdByInbox"  name="inbox.toUserName" value="${inbox.toUserName!}"><!-- 用于获取微信号 -->		
		<div class="grid_4 lable-right">
			<label>收件人：</label>
		</div>	
		<div class="grid_20">
			${inbox.createUser}
		</div>
		<div class='clearLine'>&nbsp;</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="display:none"></select>
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
				<span style="color:red">*语音支持:</span>	<span  style="line-height: 30px; padding-left: 5px;font-style:normal;font-size:13px">mp3/256KB</span>
				</div>
		</div>
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
    $("#maintainForm").attr("action","/weChat/inbox/replyMoreFortyEightVoiceMessage.action");		
	
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
			if($("#attachFileNames").val()==""||$("#attachFileNames").val()==null){				 
				$.messageBox({message:"请上传回复的语音！",level: "warn"});				
				 return ;
			}
	    $(form).ajaxSubmit({
	         success: function(data){
	        	 if(data==null||data=="null"){
		        		 if($("#isCloseWindow").val()=='true'){
		        		   $("#replyMessageDlg").dialog("close");
		        		 }else{
		        				$("#attachFileNames").html('');
								$('#custom_file_upload').uploadifyClearQueue();
								$("#isCloseWindow").val("true");
		        		  }
					$.messageBox({message:"回复成功!"});						
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


