<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div class="container container_24">
	<form id="replyForm" method="post" action="/weChat/inbox/replyMessage.action">
	<@pop.token />
		<input type="hidden" name="textSendMessage.touser" value="${(inbox.fromUserName)?if_exists}">
		<input type="hidden" name="inbox.inboxId" value="${(inbox.inboxId)?if_exists}">
		<input type="hidden" name="inbox.createUser" value="${(inbox.createUser)?if_exists}">
		<input type="hidden" name="inbox.toUserName" value="${(inbox.toUserName)?if_exists}">
		<div class="grid_4 lable-right">
			<label>收件人：</label>
		</div>	
		<div class="grid_20">
			${(inbox.createUser)?if_exists}
		</div>
		<div style="clear: both;" />
		<div class="grid_4 lable-right">
			<label>回复内容：</label>
		</div>	
		<div class="grid_20 heightAuto">
			<textarea name="textSendMessage.text.content" id="content" style="height:70px;width: 495px" class='form-txt {required:true,maxlength:64,messages:{required:"请输入回复内容",maxlength:$.format("回复内容最多可以输入{0}个字符")}}'></textarea>
		</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	//表单验证
	$("#replyForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
	         	success: function(data){
	         		if(data==0){
	         			$("#inboxDialog").dialog("close");
						$.messageBox({message:"发送成功!"});
						$("#inboxList").trigger("reloadGrid"); 
	         		}else{
	         			$.messageBox({message:"发送失败，错误代码"+data,level: "error"});
	         		}
		  	   	},
		      	error: function(XMLHttpRequest, textStatus, errorThrown){
	         		$.messageBox({message:"提交错误",level: "error"});
		      	}
	      	});
		}
	});
});
</script>