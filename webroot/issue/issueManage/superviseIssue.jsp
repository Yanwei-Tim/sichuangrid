<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="事件处理督办" class="container container_24">
	
<form id="maintainForm" method="post" action="${path}/issue/issueManage/superviseIssueLog.action">

	<input id="issueLog.issueId" name="issueLog.issueId" type="hidden" value="${issueLog.issueId}" />
	<input id="issueLog.dealUser.id" name="issueLog.dealUser.id" type="hidden" value="${issueLog.dealUser.id}" />
	<input id="issueLog.dealOrg.id" name="issueLog.dealOrg.id" type="hidden" value="${issueLog.dealOrg.id}" />
	<input id="issueLog.dealType" name="issueLog.dealType" type="hidden" value="${issueLog.dealType}" />
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">督办单位：</label>
	</div>
	<div class="grid_8 form-left">
		<input type="text" id="" name="" value="${issueLog.dealOrg.orgName}" readonly="readonly" class="form-txt" />
   	</div>
	<div class='clearLine'></div>
			
	<div class="grid_4 lable-right">
		<label class="form-lbl">督办人：</label>
	</div>
	<div class="grid_8 form-left">
		<input type="text" id="issueLog.undertaker" name="issueLog.undertaker" value="${issueLog.undertaker}" readonly="readonly" class="form-txt" />
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_8 form-left">
		<input type="text" id="issueLog.undertakerTelephone" name="issueLog.undertakerTelephone" value="${issueLog.undertakerTelephone}" class="form-txt" maxlength="15" />
	</div>
	<div class='clearLine'></div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">督办意见：</label>
	</div>
	<div class="grid_20">
		<textarea id="content" name="issueLog.content" style="width: 99%;height: 300px" class="xheditor-mfull"></textarea>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
</form>

</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#content').richText();

	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
               	 		$.messageBox({
							message:data,
							level:"error"							
				 		});
                		return;
                	}
					data["sourceOrganization.orgName"]=data.sourceOrganization.orgName;
					$("#issueList").setRowData(data.id,data);
					$("#issueDialog").dialog("close");
					$.messageBox({message:"已经成功保存该督办信息!"});
	     			$("#issueList").setSelection(data.id);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		},
		rules:{
			"issueLog.undertakerTelephone":{
				telephone:true
			}
		},
		messages:{
			"issueLog.undertakerTelephone":{
				telephone:$.format("联系电话只能输数字和横杠(-)")
			}
		}
	});
	
});
</script>

