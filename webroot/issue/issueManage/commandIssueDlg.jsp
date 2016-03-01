<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="事件处理领导批示" class="container container_24">

<form id="commandIssueForm" method="post" action="${path}/issue/issueBusinessManage/instruct.action">
		<input type="hidden" name="issueLog.dealOrg.id" id="dealOrgId" value="${issueLog.dealOrg.id }" />
		<input type="hidden" name="issueLog.issue.id" id="issueId" value="${issueLog.issue.id }" />
		<input type="hidden" name="stepId" id="stepId" value="${stepId }" />
		
  <div>
    <div class="grid_4 lable-right">
		<label class="form-lbl">批示人：</label>
	</div>
	<div class="grid_7 form-left">
    	<input type="text" id="issueLogNew-dealUserName" name="issueLog.dealUserName" maxlength="20" value="${issueLog.dealUserName }" class="form-txt" />
	</div>
	<div class="grid_1">
		<em class="form-req">*</em>
	</div> 
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_7 form-left">
    	<input type="text" id="issueLogNew-mobile" name="issueLog.mobile" maxlength="11" value="${issueLog.mobile }" class="form-txt" />
	</div>
    <div class="grid_1">
        <em class="form-req">*</em>
    </div> 
	 <div class='clearLine'>&nbsp;</div>
	 
	<div class="grid_4 lable-right">
			<label class="form-lbl">批示内容：</label>
		</div>
		<div class="grid_19">
			<textarea name="issueLog.content" class="xheditor-mfull" rows="12" cols="80" style="width: 99%" id="content" ></textarea>
		</div>
		<div class="grid_1">
			<em class="form-req">*</em>
		</div> 
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_22" style="position:absolute;top:250px;left:50px;">
			<hr/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_12 lable-right" style="position:absolute;top:270px;left:30px;">
		<em class="form-req">数据一旦保存，将无法修改，请您仔细检查输入。</em>
		</div>
</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#commandIssueForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.issueLogId){
	           	 		$.messageBox({
							message:data,
							level:"error"							
				 		});
	           	 		$("#issueDialog").dialog("close");
	            		return;
	            	}
					$.messageBox({message:"已成功批示该事件处理信息!"});
					$("#issueList").setRowData(data.issueLogId,data,"after",$("#issueLogNewId").val());
					$("#issueList").setSelection(data.issueLogId);
					$("#issueList").toggleSubGridRow(data.issueLogId);
					$("#issueDialog").dialog("close");
	     			
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		},
		rules:{
			"issueLog.dealUserName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"issueLog.mobile":{
				required:true,
                mobile:true
			},
			"issueLog.content":{
				required:true
			}
		},
		messages:{
			"issueLog.dealUserName":{
				required:"请输入批示人",
				exculdeParticalChar:"批示人只能输入字母，数字和中文字符",
				minlength:$.format("批示人至少需要输入{0}个字符"),
				maxlength:$.format("批示人最多只能输入{0}个字符")
			},
			"issueLog.mobile":{
				required:"请输入联系人手机",
                mobile:$.format("手机不合法，只能输入以1开头的11位数字")
			},
			"issueLog.content":{
				required:"请输入批示内容"
			}
		}
	});
});
</script>