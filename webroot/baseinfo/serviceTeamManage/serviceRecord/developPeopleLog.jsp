<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<% Long userId = ThreadVariable.getSession().getUserId(); %>
<style type="text/css">
.xheLayout{width:556px !important;background:#ccc;}
</style>
<div class="container container_24">
	<form id="maintainPeopleLogForm" method="post"	action="${path}/peopleLog/peopleLogManage/maintainPeopleLogFromServiceRecord.action" >
	<pop:token />
		<input id="userId"	type="hidden" name="peopleLog.userId" value="<%=userId%>" />
		<input type="hidden" name="peopleLog.serviceRecordId"  value="${serviceRecord.id}"/>
		<input type="hidden" name="peopleLog.organization.id" value="${serviceRecord.organization.id}"/>
		<input type="hidden" name="peopleLog.isAttachment" id="isAttachment">	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1"> 所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="peopleLog.organization.orgName"  readonly="readonly" value="${serviceRecord.organization.orgName}" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 日志所属人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="peopleLog.belonger" readonly="readonly" value="${serviceRecord.serviceMembers}" class="form-txt {required:true,maxlength:30,messages:{required:'请输入日志所属人',maxlength:'最多只能输入30字符'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 时间：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="peopleLog.publishDate" value='<s:date name="serviceRecord.occurDate" format="yyyy-MM-dd"/>' maxlength="18" readonly class="form-txt {required:true,messages:{required:'请输入日志时间'}}" style="width:93%"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<label class="form-lb1">地点：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="peopleLog.address" value="${serviceRecord.occurPlace}" readonly="readonly" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 标题：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="peopleLog.title" id="peopleLogTitle" value="${peopleLog.title}" class="form-txt {required:true,maxlength:20,exculdeParticalChar:true,messages:{required:'请输入日志标题',maxlength:'最多只能输入20字符',exculdeParticalChar:'不能输入非法字符'}}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1"> 内容：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea name="peopleLog.contents" id="contents" class="form-txt {required:true,isLawful:true,messages:{required:'请输入日志内容',isLawful:'您输入了非法脚本，请重新输入'}}" style="height:150px;">${serviceRecord.serviceContent}</textarea>
		</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_4 lable-right">
		<label class="form-lb1">总结：</label>
	</div>
	
	<div class="grid_20 heightAuto">
	<textarea id=summary name="peopleLog.summary"class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}" style="height:110px;">${peopleLog.summary}</textarea>
    </div>
    
    <select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	</form>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="filePanel">
		<div class="grid_4 lable-right">
			<label class="form-lbl">附件上传：</label>
		</div>
		<div class="grid_20">
			<div id="custom-queue" style="width:556px;height:65px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" />
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	function isAttachFileValue(){
		if($("#attachFileNames").val()){
			$("#isAttachment").val(true);
		}else{
			$("#isAttachment").val(false);
		}
	}

	$('#contents').richImg();


	$("#maintainPeopleLogForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			isAttachFileValue();
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							level: "error"
			 			});
            			return;
					}
					$.messageBox({message:"已经成功生成民情日志!"});
					$("#${dialogName}").dialog("close");				
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		},
		messages:{
		}
	});
	
	//附件
	$('#custom_file_upload').uploadFile({
		queueID:"custom-queue",
		selectInputId:"attachFileNames"
	});
	fillFile();
});

function fillFile(){
	<s:if test="serviceRecord.attachments.size > 0">
		<s:iterator id="attachment" value="serviceRecord.attachments">
	        $("#custom-queue").addUploadFileValue({
	        	id:"${attachment.id}",
			    filename:"${attachment.fileName}",
			    link:"${path}/baseinfo/searchServiceRecordManage/downloadServiceRecordAttachment.action?attachmentId=${attachment.id}",   	     	 
		        onRemove:function(id){removeAttachById(id)}
			});
	        $("<option>").attr("id",${attachment.id}).val("${attachment.id},${attachment.fileName}").html('${attachment.fileName}').attr("selected",true).appendTo($("#attachFileNames"));		
	     </s:iterator>
	</s:if>
}

function removeAttachById(id){
	$("#attachFileNames").find("option[id="+id+"]").remove();
}
</script>
