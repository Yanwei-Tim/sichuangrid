<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div  class="container container_24">
	<form id="maintainForm" method="post" action="">
      <@pop.token />
	    <input type="hidden" name="templateType" value="<@s.property value='@com.tianque.plugin.weChat.constant.MessageTemplateConstant@NOTICE_TO_BE_PROCESSED_VALUE'/>"><!-- 消息模板类型 -->
	    <input type="hidden" id="weChatUserIdByInbox" name="messageTemplate.weChatUserId" value="${(inbox.toUserName)!''}">
		<div class="grid_4 lable-right">
				<em class="form-req">*</em><input type="button" value="选择粉丝" class="defaultBtn" id="selectPersonBtn"/>
		</div>
		<div class="grid_20 heightAuto">
			<input type="text" name="openIdStr" id="userReceivers" class="form-txt"  readonly="readonly"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="title">
			<div class="grid_4 lable-right">
				<label class="form-lbl">标题：</label>
			</div>
			<div class="grid_20" id="titleDiv">
				待处理通知
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label><em class="form-req">*</em>&nbsp;&nbsp;&nbsp;&nbsp;</label>
		</div>	
		<div class="grid_20">
			<input type="text" name="messageTemplate.firstWord" maxlength="50"  class='form-txt {required:true,messages:{required:"此项必填"}}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label>通知内容：</label>
		</div>	
		<div class="grid_20">
			<input type="text" name="messageTemplate.keywordOne" maxlength="20" class='form-txt {required:true,messages:{required:"请输入通知内容"}}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label>通知人：</label>
		</div>	
		<div class="grid_20">
			<input type="text" name="messageTemplate.keywordTow" maxlength="20"  value="${USER_ORG_NAME!}" readonly  class='form-txt {}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label>通知时间：</label>
		</div>	
		<div class="grid_6">
			<input type="text" name="messageTemplate.keywordThree" maxlength="20" id="keywordThree_date_id" readonly value=""  class='form-txt {required:true,messages:{required:"请选择通知时间"}}'  />
		</div>		
		<div class='clearLine'>&nbsp;</div>
		<div id="content">
			<div class="grid_4 lable-right">
				<label class="form-lbl"></label>
			</div>
			<div class="grid_20 heightAuto">
		    	<textarea name="messageTemplate.remark"  maxlength="100"  style='height:70px;width: 97%' class='defaultColor form-txt {}'></textarea>
			</div>
		</div>
		<div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">详情链接：</label>
			</div>
			<div class="grid_20">
	                <input type="text" name="messageTemplate.url" maxlength="50"  class='form-txt {isUrl:true,maxlength:50,messages:{isUrl:"请输入正确的网址",maxlength:$.format("链接地址最多可以输入{0}个字符")}}'  />
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">	
$(document).ready(function(){
	$("#maintainForm").attr("action","/messageTemplateManage/sendTemplateMessage.action");
	//提交
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {		
	    $(form).ajaxSubmit({
	        success: function(data){
	          if(data == true || data == "true"){
					$.messageBox({message:"发送成功!"});				
					$("#replyMessageDlg").dialog("close");	
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
//收件人选择窗口
$("#selectPersonBtn").click(function(){
	$(".tip-error").remove();
	$("#selectPersonDialog").createDialog({
		width: 650,
		height: 400,
		title:'选择粉丝',
		url:'${path}/messageTemplateManage/dispatch.action?mode=selectFanOrGroup&tencentUser.weChatUserId='+$("#weChatUserIdByInbox").val(),
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
});
$('#keywordThree_date_id').datePicker({
	yearRange: '1900:2030',
	dateFormat:'yy-mm-dd',
    maxDate:'+0d',
    onSelect: function(dateText, inst) {
	if(dateText!=null&&dateText!=''){
		var dateUnit=dateText.split('-');
		var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
	}
}});

$("#keywordThree_date_id").datepicker("setDate",new Date());
//验证网址
jQuery.validator.addMethod("isUrl", function(value, element) {
	var patrn=/[a-zA-z]+:\/\/[^\s]+/;
	if (!patrn.exec(value) && value!="") return false;
	return true
});
</script>


