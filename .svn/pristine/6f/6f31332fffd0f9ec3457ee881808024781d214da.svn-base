<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="approvalItem" class="container container_24">
	<form id="maintainForm" method="post" action="${path}/approval/approvalItemManage/approval.action">
	
		<input type="hidden" id="appId" name="id" value="${approvalItem.id }" />
	
	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label class="form-lbl">审核人：</label>
		</div>
		<div class="grid_5 form-left">
	    	<input type="text" id="issueLog_name" name="issueLog.name" maxlength="20" value="${issueLog.dealUserName }" class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入审核人",exculdeParticalChar:"不能输入非法字符",minlength:$.format("审核人至少需要输入{0}个字符"),maxlength:$.format("审核人最多需要输入{0}个字符")}}' />
		</div>
		<div class="grid_1">
			
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_5 form-left">
	    	<input type="text" id="mobile" name="issueLog.mobile" maxlength="11" value="${issueLog.mobile }" class="form-txt" />
		</div>
	    <div class="grid_1">
	        
	    </div> 
		 <div class='clearLine'>&nbsp;</div>
		 <div class="grid_4 lable-right">
		 	<em class="form-req">*</em>
			 <label class="form-lbl">是否通过： </label>
		 </div>
		 <div class="grid_7 form-left">
		 	<input type="radio" name="approval" value="1" /> 是  <input type="radio" name="approval" value="2" /> 否
		 	
		</div>
		 <div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">审核内容：</label>
		</div>
		<div class="grid_16">
			<textarea name="issueLog.content" class="xheditor-mfull" rows="4" cols="80" style="width: 90%" id="content"  ></textarea>
			
		</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var id = $("#appId").val();
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
         	$(form).ajaxSubmit({
         		success: function(data){
         		   if(data == true || data == "true"){
         			 selectAuditItem();
   					 $.messageBox({message:"申请事项审核成功!"});
   					 if($("input[name='approval']:checked").val()=='1'){
   						 $("#auditToIssueDialog").createDialog({
   							width:880,
   		        			height:520,
   		        			title:'转入事件',
   		        			url:"${path}/issues/issueManage/auditItemToIssueDlg.action?id="+id,
   		        			buttons: {
   		        				"确定" : function(event){
   		        					$("#issueMaintainForm").attr("action","${path}/approval/approvalItemManage/auditItemPassToIssue.action");
   		        					$("#issueMaintainForm").submit();
   		        				},
   		        				"关闭" : function(event){
   		        					$(this).dialog("close");
   		        				}
   		        			}
   						 });
   					 }
         		   }
         		   $("#auditItemDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      	alert("提交错误");
	      	   	}
      	  	});
		},
		rules:{
			"issueLog.mobile":{
				required:true
			},
			"approval":{
				required:true
			},
			"issueLog.content":{
				required:true,
				minlength:2
			}
		},
		messages:{
			"issueLog.mobile":{
				required:"请输入联系手机号"
			},
			"approval":{
				required:"请选择审核是否通过"
			},
			"issueLog.content":{
				required:"请输入审核内容",
				minlength:$.format("审核内容最少需要输入{2}个字符")
		    }
		}
	});
	
});
</script>