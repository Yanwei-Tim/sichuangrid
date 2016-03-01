<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="事件处理督办" class="container container_24">

<form id="superviseIssueForm" method="post" action="${path}/issue/issueBusinessManage/supervise.action">
        <input type="hidden" name="stepId" id="stepId" value="${stepId}" />
        <input type="hidden" name="dealType" id="stepId" value="${dealType}" />
		
  <div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">督办人：</label>
	</div>
	<div class="grid_7 form-left">
    	<input type="text" id="issueLogNew-dealUserName" name="issueLog.dealUserName" maxlength="20" value="${issueLog.dealUserName }" class="form-txt"  />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_6 form-left">
    	<input type="text" id="issueLogNew-mobile" name="issueLog.mobile" maxlength="11" value="${issueLog.mobile }" class="form-txt" />
	</div>
    <div class="grid_1">
        <em class="form-req">*</em>
    </div> 
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right"><label class="form-lbl">督办类型：</label></div>
	<div class="grid_7 form-left">
		<s:property value="operate"/> 
	</div>
	<div class="grid_11 lable-right">
		<s:if test="operate.cent>0">
			<font color="red">
          		${operate}将导致该处理部门被扣${operate.cent}分
	   		</font> 
		</s:if>
	</div>
	
    <div class='clearLine'>&nbsp;</div>
    
	<div class="grid_5 lable-right">
		<label class="form-lbl">督办意见：</label>
	</div>
	<div class="grid_18 lable-right">
  		<textarea style="height:130px;" id="issueLogNew-content" name="issueLog.content" class="form-select"></textarea>
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
	$("#superviseModeSelect").val('${superviseMode }');
	$("#superviseIssueForm").formValidate({
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
    				$("#issueList").setRowData(data.issueLogId,data);
    				$("#issueList").setSelection(data.issueLogId);
    				$("#issueList").toggleSubGridRow(data.issueLogId);
    				$("#issueDialog").dialog("close");
			        $("#issueList").trigger("reloadGrid");
			        $.messageBox({message:"对此部门<s:property value='operate'/>成功!"});
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
				required:"请输入督办意见"
			}
		}
	});
});
</script>