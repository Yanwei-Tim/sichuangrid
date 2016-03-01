<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<form id="issueEvaluateForm" method="post" action="${path}/fourTeamsIssueManage/issueEvaluate.action">
		<input id="id" name="issueEvaluate.id" type="hidden"" value="${issueEvaluate.id}" />
		<input id="issueId" name="issueEvaluate.issue.id" type="hidden" value="${issueEvaluate.issue.id}" />
		<div class="grid_4 lable-right" >
			<em class="form-req">*</em><label class="form-lbl">验证时间：</label>
		</div>
		<div class="grid_19 ">
			<input type="text" name="issueEvaluate.evaluateTime" id="evaluateTime" readonly value='<s:date name="issueEvaluate.evaluateTime" format="yyyy-MM-dd" />' class="form-txt {required:true,messages:{required:'请选择验证时间'}}"/>
		</div>
		<div class="clear"></div>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em><label class="form-lb1">验证等级：</label>
		</div>
		<div class="grid_19">
			<!-- <div id="star"></div> -->
			<input type="radio" id="evaluateType3" name="issueEvaluate.evaluateType" <s:if test="issueEvaluate.evaluateType==3">checked="checked"</s:if> value="3" class=" {required:true,messages:{required:'请选择验证等级'}}"/><label for="evaluateType3">满意</label>
			<input type="radio" id="evaluateType2" name="issueEvaluate.evaluateType" <s:if test="issueEvaluate.evaluateType==2">checked="checked"</s:if> value="2" class=" {required:true,messages:{required:'请选择验证等级'}}"/><label for="evaluateType2">基本满意</label>
			<input type="radio" id="evaluateType1" name="issueEvaluate.evaluateType" <s:if test="issueEvaluate.evaluateType==1">checked="checked"</s:if> value="1" class=" {required:true,messages:{required:'请选择验证等级'}}"/><label for="evaluateType1">不满意</label>
		</div>
		<div class="clear"></div>
		<div class="grid_4 lable-right" >
			<em class="form-req">*</em><label class="form-lb1">验证内容：</label>
		</div>
		<div class="grid_19 heightAuto">
			<textarea id="evaluateContent" rows="8" name="issueEvaluate.evaluateContent" class="form-txt {required:true,maxlength:200,messages:{required:'请输入验证内容',maxlength:$.format('验证内容最多需要输入{0}个字符')}}">${issueEvaluate.evaluateContent}</textarea>
		</div>
		 <select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	</form>
	<div class="filePanel">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div class="grid_19" style='*margin-top:10px;'>
			<div id="custom-queue" style="width:505px;*width:519px;height:100px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" />
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	 /*$('div#star').raty({
		 start:"${issueEvaluate.evaluateType }",
		 onClick: function(score) {
				$("#evaluateType").val(score);
			}
		 });*/
		 
	 $('#custom_file_upload').uploadFile({
		queueID:"custom-queue",
		selectInputId:"attachFileNames"
	});
		 
	fillIssueEvaluateAttenchFiles(); 			
		
	$("#issueEvaluateForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(!data.id){
           	 			$.messageBox({message:data,evel: "error"});
            			return;
					}
         			$("#issueDialog").dialog("close");
					$.messageBox({message:"成功验证该事件!"});
					reloadIssue();
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
      	  	});
		}
	});

	$('#evaluateTime').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d'
	});
	if('' == $("#evaluateTime").attr("value") || null == $("#evaluateTime").attr("value")){
		var nowDate = new Date().toString();
		var format = nowDate.substr(0, 10);
		$("#evaluateTime").attr("value",format);
	}
	
	function fillIssueEvaluateAttenchFiles(){
		<s:if test="issueEvaluate.issueAttachFiles!=null && issueEvaluate.issueAttachFiles.size > 0">
		    <s:iterator value="issueEvaluate.issueAttachFiles">
		    $("#custom-queue").addUploadFileValue({
		     id:"<s:property value='id'/>",
		     filename:"<s:property value='fileName'/>",
		     link:"${path }/fourTeamsIssueManage/downLoadAttachFile.action?keyId=<s:property value='id'/>&issueMode=<s:property value='issueMode'/>",   	     	 
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
});
</script>
