<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.domain.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	User user = ThreadVariable.getUser();
	if(user!=null){
		request.setAttribute("user", user);
	}
%>
<div class='container_24'>
	<form id="maintainReplyForm" method="post" action="/threeRecords/replyForm">
		<input id="stepId" type="hidden" name="replyForm.stepId" value="${param.keyId }" />
		<input id="ledgerId" type="hidden" name="replyForm.ledgerId" value="${param.ledgerId }" />
		<input id="ledgerType" type="hidden" name="replyForm.ledgerType" value="${param.ledgerType }" />
		<input id="ledgerSerialNumber" type="hidden" name="replyForm.ledgerSerialNumber" value="${param.serialNumber }" />
		<input type="hidden"  name="replyForm.sourceOrg.id" id="replyForm.sourceOrg.id"  maxlength="30" value="${user.organization.id}" 
				readonly="readonly" class='form-txt' />
		
		<pop:token />
		
		<div class="grid_5 lable-right">        
			<label class="form-lbl">回复单位：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="replyForm.sourceOrg.orgName" id="replyForm.sourceOrg.orgName"  maxlength="30" value="${user.organization.orgName}" 
				readonly="readonly" class='form-txt' />
			
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">回复时间：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" id="replyDate" name="replyForm.replyDate" class="form-txt"
			value="<s:date name="replyForm.replyDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lbl">主要事项：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="reson" name="replyForm.reason"  maxlength="50" class='form-txt' >${replyForm.reason}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em><label class="form-lbl">回复内容摘要：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="handleContent" name="replyForm.handleContent"  maxlength="50" class='form-txt' >${replyForm.handleContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<em class="form-req">*</em><label class="form-lbl">回复单位联系人：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="replyForm.name" id="replyForm.name"  maxlength="30" value="${user.name}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">        
			<em class="form-req">*</em><label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="replyForm.mobile" id="replyForm.mobile"  maxlength="30" value="${user.mobile}" 
				 class='form-txt {phoneAndMobile:true,messages:{phoneAndMobile:$.format("请输入以固定电话：028-87653333或者手机：15102888888为格式的号码")}}' />
		</div>
		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	</form>
</div>
<div style="margin-left:100px;">
	<div  id="custom-queue" style="width: 500px; height:70px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9"></div>
	<input id="custom_file_upload" name="uploadFile" type="file" />
</div>



<script type="text/javascript">

//电话号码
jQuery.validator.addMethod("phoneAndMobile", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var mobile = /^(1[3|4|5|7|8][0-9])+\d{8}$/;
	var length = value.length;
	if(length == 11 && mobile.test(value)){return true;}
	var phone = /^([0-9]{3,4}-)+[0-9]{7,8}$/;	
	if (value.match(phone)==null) {return false;}
	return true
});

$(document).ready(function(){

	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});
	
	$('#replyDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	var thisTime=new Date();
	var addMonth=thisTime.getMonth()+1;
	var formatdate= thisTime.getFullYear()+"-"+addMonth+"-"+thisTime.getDate();
	$("#replyDate").attr("value",formatdate);
	$("#maintainReplyForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
				   if(data==true){
						$.messageBox({message:"回复完成"});
						$("#feedBackDialog").dialog("close");
						//$("#issueList").trigger("reloadGrid");
					} else{
						$.messageBox({message:"操作失败！请联系管理员"});
					}
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{	
			"replyForm.reason":{
				required:true,
				minlength:2,
				maxlength:250
			},
			"replyForm.handleContent":{
				required:true,
				minlength:2,
				maxlength:250
			},
			"replyForm.name":{
				required:true
			},
			"turnForm.mobile":{
				required:true,
				phoneAndMobile:true
			}
		},
		messages:{
			"turnForm.reason":{
				required:"请输入主要主要事项",
				minlength:$.format("主要事项至少需要输入{0}个字符"),
				maxlength:$.format("主要事项最多需要输入{0}个字符")
			},
			"turnForm.handleContent":{
				required:"请输入回复内容摘要",
				minlength:$.format("回复内容摘要至少需要输入{0}个字符"),
				maxlength:$.format("回复内容摘要最多需要输入{0}个字符")
			},
			"turnForm.name":{
				required:"请输入回复单位联系人"
			},
			"turnForm.mobile":{
				required:"请输入联系电话",
				phoneAndMobile:"请输入以固定电话：028-87653333或者手机：15102888888为格式的号码"	
			}
		},
		ignore:':hidden'
	});
		$("#maintainReplyForm").attr("action","${path}/threeRecords/replyForm/addReplyForm.action");
})
</script>