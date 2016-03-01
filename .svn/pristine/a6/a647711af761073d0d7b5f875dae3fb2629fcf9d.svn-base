<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="事件处理加急" class="container container_24">

<form id="urgentIssueForm" method="post" action="${path}/issue/issueManage/urgentIssue.action">
		<input type="hidden" name="issueLogNew.dealOrg.id" id="dealOrgId" value="${issueLogNew.dealOrg.id }" />
		<input type="hidden" name="step.id" id="stepId" value="${step.id }">
		<input id="DialogName" name="" type="hidden" value="${DialogName}" />
  <div>
    <div class="grid_3 lable-right">
		<label class="form-lbl">加急单位：</label>
	</div>
	<div class="grid_7 form-left">
    	<input type="text" id="issueLogNew-dealOrg-orgName" name="issueLogNew.dealOrg.orgName" maxlength="20" value="${issueLogNew.dealOrg.orgName }" class="form-txt" readonly="readonly" />
	</div>

	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_3 lable-right">
		<label class="form-lbl">加急人：</label>
	</div>
	<div class="grid_7 form-left">
    	<input type="text" id="issueLogNew-dealUserName" name="issueLogNew.dealUserName" maxlength="20" value="${issueLogNew.dealUserName }" class="form-txt" readonly="readonly" />
	</div>
	
	<div class="grid_7 lable-right">
		<label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_6 form-left">
    	<input type="text" id="issueLogNew-mobile" name="issueLogNew.mobile" maxlength="11" value="${issueLogNew.mobile }" class="form-txt" />
	</div>
	<div class="grid_1">
        <em class="form-req">&nbsp;*</em>
    </div> 
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_3 lable-right">
		<label class="form-lbl">加急意见：</label>
	</div>
	<div class="grid_20 lable-right">
  		<textarea style="height:250px;" id="issueLogNew-content" name="issueLogNew.content" class="form-select"></textarea>
  	</div>
  	<div class="grid_1">
	   <em class="form-req">&nbsp;*</em>
	</div>  
  	<div class='clearLine'>&nbsp;</div>
  </div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#urgentIssueForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.issueLogId){
	           	 		$.messageBox({
							message:data,
							level:"error"							
				 		});
	           	 	if($("#DialogName").val()=='maxUrgent'){
						 $("#maxIssueDialogforbench").dialog("close");
					}else{
						 $("#issueDialogforbench").dialog("close");
					}
	            		return;
	            	}

					if($("#DialogName").val()=='maxUrgent'){
						 $("#maxIssueDialogforbench").dialog("close");
						 $("#maxJurisdictionsList").setRowData(data.issueLogId,data,"after",$("#issueLogNewId").val());
						 $("#maxJurisdictionsList").setSelection(data.issueLogId);
						 $("#maxJurisdictionsList").toggleSubGridRow(data.issueLogId);
					}else{
						 $("#issueDialogforbench").dialog("close");
						 $("#jurisdictionsList").setRowData(data.issueLogId,data,"after",$("#issueLogNewId").val());
						 $("#jurisdictionsList").setSelection(data.issueLogId);
						 $("#jurisdictionsList").toggleSubGridRow(data.issueLogId);
					}
					$.messageBox({message:"已经成功保存该事件处理加急信息!"});
	     			selectRow();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		},
		rules:{
			"issueLogNew.mobile":{
				required:true,
	            mobile:true
			},
			"issueLogNew.content":{
				required:true
			}
		},
		messages:{
			"issueLogNew.mobile":{
				required:"请输入联系人手机",
	            mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			},
			"issueLogNew.content":{
				required:"请输入加急意见"
			}
		}
	});
});
</script>