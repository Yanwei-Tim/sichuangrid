<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div title="台账退回" class="container container_24">
	<form id="backDialog-form" method="post" action="" >
		<input id="mode" type="hidden"  name="mode" value="${mode}" />
	   	<input id="dailyLogid" name="dailyLogId" type="hidden" value="${dailyLogId}" /> 
	    <input id="orgid" name="organization.id" type="hidden" value="${orgId}" /> 
		<div style="clear:both"></div>
		<div class="grid_1"><em class="form-req">*</em></div>
		<div class="grid_4 lable-right">
			<label>消息标题：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="platformMessage.title" class="form-txt" readonly id="msgTitle" value="${msgtitle}"/>
		</div>
		<div style="clear:both"></div>
		<div class="grid_1"><em class="form-req">*</em></div>
		<div class="grid_4 lable-right">
			<label >描述：</label>
		</div>
		<div class="grid_19" style="display:inline;float:left;height:100px;line-height:100px">
			<textarea name="platformMessage.content" id="msgContent" style="height:90px;"  class="form-txt"></textarea>
		</div>
	</form>
</div>
<script type="text/javascript">

$(document).ready(function(){
		var mode=document.getElementById('mode');
		$("#backDialog-form").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit(mode);
			},
			rules:{
				"platformMessage.content":{
					required:true,
					minlength:2,
					maxlength:50
				}
			},
			messages:{
				"platformMessage.content":{
					required:"请输入退回原因!",
					minlength:$.format("请输入退回原因最少需要输入{0}个字符"),
					maxlength:$.format("请输入退回原因最多只能输入{0}个字符")
				}
			}
			});
		if("society"==mode.value){
			$("#backDialog-form").attr("action","${path}/daily/socialConflictReordManage/reportBack.action");
		}
		if("issue"==mode.value){
			$("#backDialog-form").attr("action","${path}/daily/publicSecurityRenovateManage/reportBack.action");
		}
});

function doAjaxSubmit(mode){
	$("#backDialog-form").ajaxSubmit({
        success: function(data){
	        if(data!=null && data.id){
      			$.messageBox({message:"台账回退成功!"});
      			if("society"==mode.value){
      				$("#socialConflictList").trigger("reloadGrid");
      			}
      			if("issue"==mode.value){
      				$("#publicSecurityList").trigger("reloadGrid");
      			}
			    $("#dailyLogMaintanceDialog").dialog("close");
	        }else{
	        	$.messageBox({message:"操作失败，请联系管理员!",level:"error"});
		    }
		    return ;
 	   }
 	});
}
function doNothing(){}

</script>