<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div  class="container container_24">
	<form id="maintainForm" method="post" action="">
	  <input type="hidden" id="isCloseWindow" value="true"/>
	  <@pop.token />
	  	<input type="hidden" name="mode" value="${mode!}">
		<input type="hidden" name="textMessage.ToUserName" value="${inbox.fromUserName!}"><!-- 发送给谁 -->
		<input type="hidden" name="inbox.inboxId" value="${inbox.inboxId!}"><!-- 用天存储 每条消息对应的回复记录  -->
		<input type="hidden" name="inbox.createUser" value="${inbox.createUser!}"><!-- 存的是粉丝名，区别于系统中的ccuu字段  用于存储对应的回复消息记录列表-->
		<input type="hidden" id="weChatUserIdByInbox" name="inbox.toUserName" value="${inbox.toUserName!}"><!-- 用于获取微信号 -->				
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
		<span style="color:red">*图片支持:</span>	<span  style="line-height: 30px; padding-left: 5px;font-style:normal;font-size:13px">jpg/1M</span>
		</div>
	</div>
	  
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
	$("#maintainForm").attr("action","/weChat/inbox/replyMoreFortyEightImageMessage.action")
	
	//提交
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {	     
		if($("#attachFileNames").val()==""||$("#attachFileNames").val()==null){			 
			$.messageBox({message:"请上传回复的图片！",level: "warn"});			
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


