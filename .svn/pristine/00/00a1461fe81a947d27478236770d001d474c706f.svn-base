<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<style type="text/css">
.messageHeightCon{height:465px;height:450px\9;background:#fff;overflow:hidden;position:relative;}
.sendMsgBox{height:300px;overflow-y:auto;margin:10px 0 0;border:1px solid #ccc;position:relative;}
.contactWay{height:30px;font:12px/30px '';text-indent:40px;background:#BFDFFF;position:relative;}
.contactWay .mark{width:23px;height:22px;display:block;background:url(/resource/system/images/icon_personMark.png) no-repeat;position:absolute;left:10px;top:4px;}
.sendMsgCon{min-height:25px;max-width:245px;margin:10px 20px;padding:10px 10px 0 10px;border:1px solid #FFCC99;border-radius:5px;word-break:break-all;word-wrap:break-word;position:relative;}
.sendMsgConRight{float:left;background:#FFFBEC;}
.sendMsgConLeft{float:right;background:#FFEFBF;}
.sendMsgCon .sendMark{width:17px;height:16px;display:block;position:absolute;top:15px;}
.sendMsgCon .sendMarkLeft{background:url(/resource/system/images/icon_sendMsgRight.jpg) no-repeat;right:-17px;}
.sendMsgCon .sendMarkRight{background:url(/resource/system/images/icon_sendMsgLeft.jpg) no-repeat;left:-15px;}
.sendMsgCon .timer{font:normal 12px/25px "\5b8b\4f53";color:#A6A59E;}
#nav .nav-dropdownBtn span{padding-right:20px;}
.recordMsgCon .tit{margin:5px 10px;border-bottom:1px dashed #ccc;}
.recordMsgCon .tit a{float:right;height:25px;display:block;font:12px/25px "\5b8b\4f53";color:#FF7F00;}
.container_24 input.chatSendBtn{float:right;width: 66px;height: 24px;color:#333;background: url(/resource/system/images/icon_sendBtn.png) no-repeat -3px -3px!important;border:1px solid #999;border-radius:2px;cursor:pointer;}
</style>


<div id="dialog-form" title="回复短信" class="container container_24">
	<form id="maintainForm" method="post" action="${path}/smsUplinkManage/returnSms.action">
		<input type="hidden" value="${param.sender }" id="sender" name="smsUplink.receiverMobile" /> 
		<div id="div_send_record" style="width:99%;">
			<div>
				<div class="sendMsgBox" style='background-color:#fff;' id="sendMsgBox">
					<div class="content" id="td_send_data" ></div>
				</div>
			</div>
				<div class='clearLine'>&nbsp;</div>
		 	<div class="grid_4 lable-right">
				<label class="form-lbl">优先级：</label>
	 		</div>
	 		<div class="grid_4">
	 			<select name="smsUplink.smsLevel" class="form-select">
					<option value="2">普通</option>
					<option value="1">紧急</option>
				</select>
	 		</div>	
	 		<div class="grid_10">
		 		<label>&nbsp;&nbsp;(根据发送情况选择优先级)</label>
		 	</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">已输入字符：</label>
		 	</div>
		 	<div class="grid_3">
		 		<input type="text" id="numberCounts" readonly class="form-txt" style="text-align: center;color: #ff6600;" >
		 	</div>
		 	<div class="grid_16">
		 		<label>&nbsp;&nbsp;(每条短信70字符，超过70字符将拆分成多条发送。)</label>
		 	</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="clearfix">
				<textarea rows="2" name="smsUplink.content" id="contents" class="form-txt {required:true,messages:{required:'请输入消息内容'}}" style="width:98%;height:50px;background:#F9F9F9;border:1px solid #E5E5E5;border-left:1px solid #999;border-top:1px solid #999;"></textarea>
			</div>
			<input type="submit" class="chatSendBtn"  onmousemove="" value="发送" />
		</div>		
	</form>
</div>


<script type="text/javascript">

$(document).ready(function(){
	showRecord();
	
	$("#contents").keyup(function(){
		var counts = $(this).val().length;
		$("#numberCounts").val(counts+"/"+(parseInt(counts/70)+1));
	});
	
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(data!=true){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}
					$.messageBox({message:"短信发送成功!"});
					$("#contents").val("");
					showRecord();
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
	
});


function showRecord(){
	var sender=$("#sender").val();
	var status="";
	$.post("${path}/smsdownlinkManage/findSmsContentBySender.action",{"sender":sender},function(data){
		$("#td_send_data").html("");
		var content = "<div class='recordMsgCon' style='position:relative;'>";
		$.each(data,function(i,comm){
			
			if(comm.status=='<s:property value="@com.tianque.sms.util.SmsGlobalValue@FAILURE" />'){
				status="发送失败!";
			}
			if(comm.receiverMobile==$("#sender").val()){
				content +="<div class='clearfix'>"
							+"<div class='sendMsgCon sendMsgConRight '>"
								+"<div class='msg'>"+comm.content+"</div>"
								+"<div class='timer' style='text-align:right;'>"+comm.sendTime+"</div>"
								+"<strong class='sendMark sendMarkRight '></strong>"
							+"</div>"
						+"</div>";
				if(status!=""){
					content+="<div>"+status+"</div>";
				}
			}else{
				content +="<div class='clearfix'>"
							+"<div class='sendMsgCon sendMsgConLeft '>"
								+"<div class='msg'>"+comm.content+"</div>"
								+"<div class='timer' style='text-align:right;'>"+comm.sendTime+"</div>"
								+"<strong class='sendMark sendMarkLeft '></strong>"
							+"</div>"
						+"</div>";
			}
		});
		content += "</div>";
		$("#td_send_data").html(content);
		$("#sendMsgBox").scrollTop($("#td_send_data").height());
	},"json");
}

</script>


