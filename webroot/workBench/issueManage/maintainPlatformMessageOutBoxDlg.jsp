<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
#Grid_List_10{height:50px;overflow:hidden;overflow-y:auto;}
</style>
<div  class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token/>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName()=='admin'">
		<input type="hidden" name="platformMessage.messageType" value="<s:property value='@com.tianque.platformMessage.constants.PlatformMessageType@SYSTEM_MESSAGE'/>">
		<input type="hidden" name="platformMessage.sendType" value="<s:property value='@com.tianque.platformMessage.constants.PlatformMessageSendType@SYSTERM_SNED'/>">
		</s:if>
		<s:else>
		<input type="hidden" name="platformMessage.messageType" value="<s:property value='@com.tianque.platformMessage.constants.PlatformMessageType@GENERAL_MESSAGE'/>">
		<input type="hidden" name="platformMessage.sendType" value="<s:property value='@com.tianque.platformMessage.constants.PlatformMessageSendType@MANUAL_SNED'/>">
		</s:else>
		<input type="hidden" id="isCloseWindow" value="true"/>
		<input type="hidden" name="platformMessage.originatorsName" value="${platformMessage.originatorsName }"/>
		<input type="hidden" name="platformMessage.originatorsDate" value="<s:date name="platformMessage.originatorsDate" format="yyyy-MM-dd HH:mm:ss" ></s:date>"/>
		<input type="hidden" id="isDraft" name="platformMessage.isDraft" value="0"/>
		<input type="hidden" name="platformMessage.messageMode" value="${mode }"/>
		<input type="hidden" name="platformMessage.replyMessageId"  value="${platformMessage.id }"/>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">收件人：</label><!-- class="defaultBtn" id="selectPersonBtn"-->
		</div>
		<div class="grid_16 heightAuto" id="Grid_List_10">
			<input type="text" name="userReceivers"	id="userReceivers" class="form-txt"/>
			<input type="hidden" name="orgReceivers" id="orgReceivers"/>
			<input type="hidden" name="roleReceivers" id="roleReceivers"/>
			<input type="hidden" name="receiverNames" id="receiverNames"/>
		</div>
		<div class="grid_4 heightAuto">
			<input type="button" class="defaultBtn" id="selectPersonBtn" value="添加" >
			<input type="button" class="defaultBtn" id="clearPersonBtn" value="清空" >
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">标题：</label>
		</div>
		<div class="grid_20" id="titleDiv">
			<input type="text" name="platformMessage.title" maxlength="50" value="${platformMessage.title }"  class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">内容：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea rows="5" class="form-txt" name="platformMessage.content"  style="width:97%;height:100px;margin:0px;float:left;">${platformMessage.content}</textarea>
			<textarea rows="5" class="form-txt" style="width:97%;height:150px;margin:0px;float:left;" disabled>
      发件人：${platformMessage.sender.name}
      发件人所属部门:${platformMessage.sender.organization.orgName}
      发送时间:<s:date name="platformMessage.sendDate" format="yyyy-MM-dd HH:mm:SS" ></s:date>
  ----------------------------------------
      消息内容：${platformMessage.content}
			</textarea>
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="display:none"></select>
	</form>
	<div class="filePanel">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div id="attachFilesDiv" class="grid_20 heightAuto">
			<div id="custom-queue"></div>
			<input id="custom_file_upload" name="uploadFile" type="file" />
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").attr("action","${path}/interactive/outboxPlatformMessageManage/forwardInboxPlatformMessage.action")
	//收件人选择窗口
	$("#selectPersonBtn").click(function(){
		$("#selectPersonDialog").createDialog({
			width: 650,
			height: 340,
			title:'选择收件人',
			url:'${path}/interactive/outboxPlatformMessageManage/dispatch.action?mode=selectReceiver',
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
	
	$("#clearPersonBtn").click(function(){
		var selectId = $("#userReceivers").val().split(",");
		var selectOrgId = $("#orgReceivers").val().split(",");
		var selectRoleId = $("#roleReceivers").val().split(",");
		if(selectId!=null && selectId.length!=0&& selectId!=undefined){
			for(var i=0;i<selectId.length;i++){
				$("#userReceivers").removePersonnelCompeleteValue(selectId[i]);
			}
		}
		if(selectOrgId!=null && selectOrgId.length!=0&& selectOrgId!=undefined){
			for(var i=0;i<selectOrgId.length;i++){
				$("#orgReceivers").removePersonnelCompeleteValue(selectOrgId[i]);
			}
		}
		if(selectRoleId!=null && selectRoleId.length!=0&& selectRoleId!=undefined){
			for(var i=0;i<selectRoleId.length;i++){
				$("#roleReceivers").removePersonnelCompeleteValue(selectRoleId[i]);
			}
		}
		
		$("#holder_userReceivers .bit-box").each(function(){
				$(this).remove();
		});
		
	});
	//收件人联想补全
	$("#userReceivers").personnelComplete({
		url: "${path}/interactive/outboxPlatformMessageManage/searchContacterForAutoComplete.action",
		multiple: true,
		postDataFunction: function(){return {orgId:''};},
		select: function(data){},
		itemClose: function(data){
			//从收件人栏移除已选择的层级或岗位时把相应的收件人id置空
			if(data=="-1"){
				$("#orgReceivers").val('');
			}else if(data=="-2"){
				$("#roleReceivers").val('');
			}
		}
	});

	//附件上传
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"
	});

$("#attachFileNames").empty();
	
		fillAttenchFiles(); 			
	
	
	function fillAttenchFiles(){
		<s:if test="platformMessage.pmAttachFiles!=null && platformMessage.pmAttachFiles.size > 0">
		    <s:iterator value="platformMessage.pmAttachFiles">
		    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='id'/>",
		     filename:"<s:property value='fileName'/>",
		     link:"${path }/interactive/outboxPlatformMessageManage/downloadPmAttachFileById.action?pmAttachFile.id=<s:property value='id'/>",   	     	 
			 onRemove:function(){removeAttach("<s:property value='fileName' escape='false'/>")}
		     });
		    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#attachFileNames"));
		    </s:iterator>
		</s:if>
	}
	
	function removeAttach(fileName){
		$("input[name='file']").removeAttr("disabled");
		$("#attachFileNames").find("option:contains("+fileName+")").remove();
	}
	//提交
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
		if($("#userReceivers").val()==''&&$("#orgReceivers").val()==''){
			 $.messageBox({message:"收件人不能为空！",level: "warn"});
			 return ;
		}
		var receiverNames="";
		$("#holder_userReceivers .bit-box").each(function(){
				receiverNames+=$(this).data("data")+"="+$(this).text()+",";
		});
		if(receiverNames!='' && receiverNames.length!=0){
			$("#receiverNames").attr("value",receiverNames);
		}
		$.dialogLoading("open");
	    $(form).ajaxSubmit({
	         success: function(data){
	                 if(data!=true){
	                    $.messageBox({message:data,level: "error"});
	                 	return;
	                 }
	                 if($("#isDraft").val()==1){
	                	 $.messageBox({message:"消息已成功保存至草稿箱！"});
	                 }else{
	                	 $.messageBox({message:"消息已成功发送！"});
	                 }
	                 $("#benchDialog").dialog("close");
	                 findUnreadInboxPlatformMessage();
	  	   },
	  	   error: function(XMLHttpRequest, textStatus, errorThrown){
	  	      alert("提交错误");
	  	   }
	  	  });
		},
		rules:{
			"platformMessage.title":{
					required:true,
					minlength:2,
					maxlength:50
				},
			"platformMessage.content":{
					required:true,
					minlength:2,
					maxlength:5000
			}
			},
		messages:{
			"platformMessage.title":{
				required:"请输入标题",
				minlength:$.format("标题至少需要输入{0}个字符"),
				maxlength:$.format("标题最多输入{0}个字符")
				},
			"platformMessage.content":{
					required:"请输入内容",
					minlength:$.format("标题至少需要输入{0}个字符"),
					maxlength:$.format("标题最多输入{0}个字符")
			}
		}
	});
	
});

</script>


