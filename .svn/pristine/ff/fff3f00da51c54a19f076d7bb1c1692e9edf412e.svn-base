<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div  class="container container_24">
	<form id="maintainForm" method="post" action="${path}/weChat/inbox/replyMoreFortyEightTextMessage.action">
	 <input type="hidden" id="isCloseWindow" value="true"/>
     <@pop.token />
			<input type="hidden" name="mode" value="${mode!}">
		<input type="hidden" name="inbox.inboxId" value="${inbox.inboxId!}"><!-- 用天存储 每条消息对应的回复记录  -->
		<input type="hidden" name="inbox.createUser" value="${inbox.createUser!}"><!-- 存的是粉丝名，区别于系统中的ccuu字段  用于存储对应的回复消息记录列表-->
		<input type="hidden" id="weChatUserIdByInbox" name="inbox.toUserName" value="${inbox.toUserName!}"><!-- 用于获取微信号 -->
		<input type="hidden" name="textMessage.ToUserName" value="${inbox.fromUserName!}"><!-- 发送给谁 -->
		<div class="grid_4 lable-right">
			<label>收件人：</label>
		</div>	
		<div class="grid_20">
			${inbox.createUser!}
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="content">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em><label class="form-lbl">内容：</label>
			</div>
			<div class="grid_20 heightAuto">
		    	  <textarea  id="textareaContent" name="textMessage.content" onkeyup="charlength(value)" style='height:70px;width: 97%' class='form-txt {required:true,maxlength:140,messages:{required:"请输入回复内容",maxlength:$.format("回复内容最多可以输入{0}个字符")}}'></textarea>
			</div>
		    <div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">已输入：</label>
			</div>
			<div class="grid_20 heightAuto">
				<span id="counter" style="line-height: 30px; padding-left: 5px;font-style:normal;">0</span>个字（注意：回复内容不要超过140字。)
			</div>
		</div>
	</form>
	
</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
//计算文字长度
function charlength(value){
	var a=value.length;
	document.getElementById("counter").innerText=a;
 }
$(document).ready(function(){
	//提交
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	    $(form).ajaxSubmit({
	         success: function(data){
	        	   if(data==null||data=="null"){
		        		 if($("#isCloseWindow").val()=='true'){
		        		   $("#replyMessageDlg").dialog("close");
		        		 }else{
		        				$("#textareaContent").val("");
		        				document.getElementById("counter").innerText=0;
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


