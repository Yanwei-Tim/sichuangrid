<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<form id="issueLogForm" method="post" action="${path}/fourTeamsIssueManage/updateIssueLog.action">
		<input type="hidden" id="issueLogid" name="issueLogForEdit.id" value="${ issueLogForEdit.id}">
		<input type="hidden" id="issueid" name="issueLogForEdit.issue.id" value="${issueLogForEdit.issue.id }">
		<div class="grid_3 lable-right" >
			<em class="form-req">*</em><label class="form-lb1">意见：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea id="evaluateContent" rows="8" name="issueLogForEdit.content" class="form-txt {required:true,maxlength:1000,messages:{required:'请输入意见',maxlength:$.format('意见最多需要输入{0}个字符')}}">${issueLogForEdit.content}</textarea>
		</div>
		<div class='clearLine'></div>
		<select id="issueLogAttachFile" name="attachFiles" multiple="multiple" style="display:none"></select>
	</form>
	<div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">上传附件：</label>
		</div>
		<div class="grid_20">
			<input id="custom_file_upload-issueLog" name="uploadFile" type="file" />
			<div class='clearLine'>&nbsp;</div>
			<div id="custom-queue-issueLog" style="overflow-y: auto;overflow-x: hidden;width:98%;"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
var isUpLoadComplete=false;
function attachFileUploader(){
	$('#custom_file_upload-issueLog').uploadFile({
		queueID : 'custom-queue-issueLog',
		selectInputId : "issueLogAttachFile",
		onAllComplete:function(){isUpLoadComplete = true ;}
	});
	$("#issueLogAttachFile").empty();
}

function issueAttenchFiles(){
	<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
	    <s:iterator value="issueAttachFiles">
	    $("#custom-queue-issueLog").addUploadFileValue({
	     id:"<s:property value='id'/>",
	     filename:"<s:property value='fileName'/>",
	     link:"${path }/fourTeamsIssueManage/downLoadAttachFile.action?keyId=<s:property value='id'/>&issueMode=<s:property value='issueMode'/>",   	     	 
		 onRemove:function(){removeAttach("<s:property value='fileName' escape='false'/>")}
	     });
	    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#issueLogAttachFile"));
	    </s:iterator>
	</s:if>
}

function removeAttach(fileName){
	$("input[name='file']").removeAttr("disabled");
	$("#issueLogAttachFile").find("option:contains("+fileName+")").remove();
}

$(document).ready(function(){
	attachFileUploader();
	issueAttenchFiles();
	$("#issueLogForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(!data.id){
           	 			$.messageBox({message:data,evel: "error"});
            			return;
					}
         			$("#issueEdit #tabs	div:first").load($("#issueEdit #tabs ul li:first a").attr("href"));
         			$("#editIssueLog").dialog("close");
					$.messageBox({message:"成功修改该处理过程!"});
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
      	  	});
		}
	});
});
</script>
