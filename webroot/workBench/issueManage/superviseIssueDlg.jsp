<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="事件处理督办" class="container container_24">
	<form id="superviseIssueForm" method="post" action="${path}/issues/issueManage/dealIssue.action">
			<input type="hidden" name="operation.dealOrg.id" id="dealOrgId" value="${operation.dealOrg.id }" />
			<input type="hidden" name="operation.issue.id" id="issueId" value="${operation.issue.id }" />
			<input type="hidden" name="keyId" id="keyId" value="${keyId }" />
			<input type="hidden" name="dealCode" id="dealCode" value="${dealCode }" />
			<input id="DialogName" name="" type="hidden" value="${DialogName}" />
			<pop:token />
		  	<div>
				<div class="grid_5 lable-right">
					<em class="form-req">*</em>
					<label class="form-lbl">督办人：</label>
				</div>
				<div class="grid_7 form-left">
					<input type="text" id="dealUserName" name="operation.dealUserName" maxlength="20" value="${operation.dealUserName}" class="form-txt" />
				</div>
				<div class="grid_5 lable-right">
					<em class="form-req">*</em>
					<label class="form-lbl">联系手机：</label>
				</div>
				<div class="grid_7 form-left">
					<input type="text" id="mobile" name="operation.mobile" value="${operation.mobile}" maxlength="11" class="form-txt" />
				</div>
				<div class='clearLine'>&nbsp;</div>
				<div class="grid_5 lable-right"><label class="form-lbl">督办类型：</label></div>
				<div class="grid_7 form-left">
					<s:property value="@com.tianque.issue.state.IssueOperate@parse(dealCode)"/> 
				</div>
				<div class="grid_11 lable-right">
					<font color="red">${tag}</font> 
				</div>
				
			    <div class='clearLine'>&nbsp;</div>
			    
				<div class="grid_5 lable-right">
					<em class="form-req">*</em>
					<label class="form-lbl">督办意见：</label>
				</div>
				<div class="grid_18 lable-right">
			  		<textarea style="height:130px;" id="content" name="operation.content" class="form-select"></textarea>
			  	</div>
		  </div>
	</form>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$("#superviseModeSelect").val('${superviseMode }');
	$("#superviseIssueForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.issueStepId){
	           	 		$.messageBox({
							message:data,
							level:"error"							
				 		});
	           	 	if($("#DialogName").val()=='maxSupervise'){
						 $("#maxIssueDialogforbench").dialog("close");
					}else{
						 $("#issueDialogforbench").dialog("close");
					}
	            		return;
	            	}  
					if($("#DialogName").val()=='maxSupervise'){
						 $("#maxIssueDialogforbench").dialog("close");
						 $("#maxJurisdictionsList").setRowData(data.issueStepId,data);
		    			 $("#maxJurisdictionsList").setSelection(data.issueStepId);
					}else{
						$("#issueDialogforbench").dialog("close");
	    				$("#jurisdictionsList").setRowData(data.issueStepId,data);
	    				$("#jurisdictionsList").setSelection(data.issueStepId);
					}
			        $.messageBox({message:"督办成功!"});
			      
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		},
		rules:{
			"operation.mobile":{
				required:true,
	            mobile:true
			},
			"operation.content":{
				required:true
			}
		},
		messages:{
			"operation.mobile":{
				required:"请输入联系人手机",
	            mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			},
			"operation.content":{
				required:"请输入督办意见"
			}
		}
	});
});
</script>