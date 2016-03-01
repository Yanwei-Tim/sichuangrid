<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.messageTypes{padding:10px;height:200px;overflow:auto;}
	.messageTypes li{width:49%;float:left;line-height:26px;}
</style>
<form name="loginform"  method="post" action="${path }/sysadmin/userManage/updateUserHasPlatformMessageType.action" id="userHasPlatformMessageTypeForm">
	  <ul class="messageTypes">
	  	<li>
	  		<label>
	  			<input type="checkbox" id="selectAll" <s:if test="canSelectMessageTypes.size()==0">checked</s:if>  />
	  			全选
	  		</label>
	  	</li>
		<s:iterator value="messageTypes" var="messageType">
			<li id="messageTypes_${messageType}">
				<label>
					<input type="checkbox" name="messageTypes" value="${messageType}" checked   />
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@MESSAGE_TYPE.get(#messageType)"/>
				</label>
			</li>
		</s:iterator>
		<s:iterator value="canSelectMessageTypes" var="canSelectMessageType">
			<li id="messageTypes_${canSelectMessageType}">
				<label>
					<input type="checkbox" name="messageTypes" value="${canSelectMessageType}" />
					<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@MESSAGE_TYPE.get(#canSelectMessageType)"/>
				</label>
			</li>
		</s:iterator>
	</ul>
</form>
<script type="text/javascript"> 
$(document).ready(function(){
	//人工发送消息默认为必选,需隐藏
	var id ="messageTypes_"+ <s:property value='@com.tianque.platformMessage.constants.PlatformMessageType@GENERAL_MESSAGE' /> ;
	$("#"+id).hide();
	
	$("#selectAll").click(function(){
		if($(this).attr('checked')){
			$("form ul li:gt(0)[id!="+id+"] input").each(function(){
				if(!$(this).attr('checked')){
					$(this).attr('checked',true);
				}
			});
		}else{
			$("form ul li:gt(0)[id!="+id+"] input").attr('checked',false);
		}
	});

	$("form ul li:gt(0)[id!="+id+"] input").click(function(){
		if($(this).attr('checked')){
			isSelcetAll();
		}else{
			$("#selectAll").attr('checked',false);
		}
	});


	function isSelcetAll(){
		var isChecked=true;
		$("form ul li:gt(0)[id!="+id+"] input").each(function(){
			if(!$(this).attr('checked')){
				isChecked=false;
				return;
			}
		});
		$("#selectAll").attr("checked",isChecked);
	}
	
	$("#userHasPlatformMessageTypeForm").formValidate({
		promptPosition :"bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success:function(data){
					if(data==true){
						 $.messageBox({message:"成功保存用户订阅平台消息!"});
						 $("#settingDialog").dialog("close");
					}else{
						 $.messageBox({level: "error",message:data});
					}
				}
			});
		}
	});
});
</script> 
