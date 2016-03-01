<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="事件处理申请延期" class="container container_24">
<form id="auditDelayForm" method="post" action="${path}/issues/issueApplyDelay/auditDelay.action">
	<input type="hidden" name="issueApplyDelay.issueStepId" value="${parameters.issueStepId[0]}" />
	<input type="hidden" name="issueApplyDelay.id" value="${issueApplyDelay.id}" />
	<div class="grid_12 heightAuto">
	    <div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">申请延期：</label>
		</div>
		<div class="grid_3 form-left">
	    	<input type="text" readonly id="issueApplyDelay_delayWorkday" class="form-txt" value="${issueApplyDelay.delayWorkday }"/>
		</div>
		<div class="grid_3 lable-right">工作日</div>
		 <div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">延期原因：</label>
		</div>
		<div class="grid_16 heightAuto">
			<textarea readonly class="form-txt" style="width: 99%; height: 272px;">${issueApplyDelay.delayInfo }
			</textarea>
		</div>
	</div>
	<div class="grid_12 heightAuto">
		<div class="grid_6 lable-right">
			<input type="radio" id="auditProcess" checked="checked" value='2' name="issueApplyDelay.auditState"/><label>同意</label>
		</div>
		<div class="grid_4 lable-right">
			<input type="radio" id="auditNotProcess" value='3' name="issueApplyDelay.auditState"/><label>不同意</label>
		</div>
		<div title="选择同意时该值生效，选择不同意时该值无效">
			<div class="grid_6 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">允许延期：</label>
			</div>
			<div class="grid_3 form-left">
		    	<input type="text" name="issueApplyDelay.permitDelayWorkday" id="issueApplyDelay_permitDelayWorkday" value="${issueApplyDelay.delayWorkday }" 
		    		class="form-txt {required:true,positiveInteger:true,messages:{required:'工作日必须输入',positiveInteger:'必须输入正整数'}}" maxlength="2" />
			</div>
			<div class="grid_3 lable-right">工作日</div>
		</div>
		 <div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_16 heightAuto">
			<textarea name="issueApplyDelay.auditInfo" maxlength="200" class="form-txt {minlength:2,maxlength:200,messages:{minlength:'备注至少需要输入2个字符',maxlength:'备注最多需要输入200个字符'}}" style="width: 99%; height: 240px;"></textarea>
		</div>
	</div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#auditProcess').click(function() {
		$('#issueApplyDelay_permitDelayWorkday').attr('readonly',false);
	});
	$('#auditNotProcess').click(function() {
		$('#issueApplyDelay_permitDelayWorkday').val($('#issueApplyDelay_delayWorkday').val());
		$('#issueApplyDelay_permitDelayWorkday').attr('readonly',true);
	});
	$("#auditDelayForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
	           	 		$.messageBox({ message: data, level:"error" });
	           	 		$("#issueForBenchDialog").dialog("close");
	            		return;
	            	}
					$.messageBox({message:"审批成功!"});
					$("#issueForBenchDialog").dialog("close");
					findIssueNeedDo();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		}
	});
});
</script>