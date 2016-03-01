<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div title="台账退回" class="container container_24">
	<form id="backDialog-form"  method="post" action="" >
	<input id="keyAreasOfInvestigationInfoids" type='hidden' name="keyAreasOfInvestigationInfoids" value="${keyAreasOfInvestigationInfoids}" />
	<input  id="subOrgIds" name="subOrgIds" type='hidden' value="${subOrgIds}" />
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label>消息标题：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="platformMessage.title" class="form-txt" readonly id="msgTitle" value="治安重点地区排查情况信息回退提醒!"/>
	</div>
<div class="grid_1"><em class="form-req">*</em></div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label >描述：</label>
	</div>
	<div class="grid_19">
		<textarea name="platformMessage.content" id="msgContent"  class="form-txt" rows="8"></textarea>
	</div>
	<div class="grid_1"><em class="form-req">*</em></div>
	   </form>
</div>
<script type="text/javascript">

$(document).ready(function(){
		$("#backDialog-form").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
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
		$("#backDialog-form").attr("action","${path}/daily/keyAreasOfInvestigationInfoManage/backKeyAreasOfInvestigationInfo.action");
});

function doAjaxSubmit(){
	$("#backDialog-form").ajaxSubmit({
        success: function(data){
	        if(data==true){
	      			$.messageBox({message:"台账退回成功!"});
				    $("#keyAreasOfInvestigationInfoList").trigger("reloadGrid");
	        }else{
	        	$.messageBox({message:data,level: "error"	});
	        }
			$("#backkeyDialog").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});
 	   }
 	});
}

</script>