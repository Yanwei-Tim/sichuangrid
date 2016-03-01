<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="事件处理申请延期" class="container container_24">
<form id="issueApplyDelayForm" method="post" action="${path}/issues/issueApplyDelay/applyDelay.action">
<pop:token />
<input type="hidden" name="issueApplyDelay.issueStepId" value="${param.issueStepId}" />
    <div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">申请延期：</label>
	</div>
	<%-- <div class="grid_7 form-left">
    	<input type="text" name="issueApplyDelay.delayDate" id="issueApplyDelay_delayDate" readonly="readonly" class="form-txt {required:true,messages:{required:'延期至必须输入'}}" />
	</div>
	<div class="grid_2 lable-right">
		<label class="form-lbl">即：</label>
	</div> --%>
	<div class="grid_3 form-left">
    	<input type="text" name="issueApplyDelay.delayWorkday" id="issueApplyDelay_delayWorkday" class="form-txt {required:true,positiveInteger:true,messages:{required:'工作日必须输入',positiveInteger:'必须输入正整数'}}" maxlength="2" />
	</div>
	<div class="grid_2 lable-right">工作日</div>
	 <div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">延期原因：</label>
	</div>
	<div class="grid_20 heightAuto">
		<textarea name="issueApplyDelay.delayInfo" maxlength="200" class="form-txt {required:true,minlength:2,maxlength:200,messages:{required:'延期原因必须输入',minlength:'延期原因至少需要输入2个字符',maxlength:'延期原因最多需要输入200个字符'}}" style="width: 99%; height: 72px;"></textarea>
	</div>
</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	/* $('#issueApplyDelay_delayDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
		minDate:'+0d',
		onSelect: function (val){$.post('${path}/issues/issueApplyDelay/getWorkDayByDate.action?issueApplyDelay.delayDate=' + val, function (data){
			if(isNaN(data)){
        	 	$.messageBox({ message: '获取工作日接口发生异常', level:"error" });
			}else{
				$("#issueApplyDelay_delayWorkday").val(data);
			}
		});}
	}); */
	$("#issueApplyDelayForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
	           	 		$.messageBox({ message: data, level:"error" });
	            		return;
	            	}
					$.messageBox({message:"申请延期成功!"});
					$("#issueDialog").dialog("close");
					onOrgChanged();
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		  	}
			});
		}
	});
});
</script>