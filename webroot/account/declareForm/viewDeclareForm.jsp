<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class='container_24'>
	<form id="report_form" method="post" action="/threeRecords/assignForm/dispatch.action">
		<input type="hidden" name="mode" id="mode" value="edit" />
		<input type="hidden" name="declareForm.stepId"  value="${declareForm.stepId}" />
		<input type="hidden" id="_reportFormLedgerType" name="declareForm.ledgerType"  value="${declareForm.ledgerType}" />
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_6">
		<input type="text"  name="declareForm.serialNumber" id="serialNumber"  maxlength="30" value="${declareForm.serialNumber}" 
				readonly="readonly" class='form-txt' />
		</div>
		
		<div class="grid_5 lable-right">        
			<label class="form-lbl">申报单位：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="declareForm.sourceOrg.orgName" id="declareForm.sourceOrg.orgName"  maxlength="30" value="${declareForm.sourceOrg.orgName}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">申报单位联系人：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="declareForm.name" id="declareForm.name"  maxlength="30" value="${declareForm.name}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="declareForm.mobile" id="declareForm.mobile"  maxlength="30" value="${declareForm.mobile}" 
				 class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">        
			<label class="form-lbl">申报时间：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" id="declareDate" name="declareForm.declareDate" class="form-txt"
			value="<s:date name="declareForm.declareDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">申报主要事项及原因：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="reason" name="declareForm.reason"  maxlength="50" class='form-txt' >${declareForm.reason}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">申报单位主要领导意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="handleContent" name="declareForm.handleContent"  maxlength="50" class='form-txt' >${declareForm.handleContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl"> 县台账办拟办意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="ledgerHandleContent" name="declareForm.ledgerHandleContent"  maxlength="50" class='form-txt' >${declareForm.ledgerHandleContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">联席会议召集人审核意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="jointContent" name="declareForm.jointContent"  maxlength="50" class='form-txt' >${declareForm.jointContent}</textarea>
		</div>
		
		<div id="formPrint" style="display:none"></div>
	</form>
</div>



<script type="text/javascript">

$(document).ready(function(){
	$('#declareDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});


	$("#report_form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				 type: 'post',
		         url: '/threeRecords/declareForm/dispatch.action?'+$("#issueDealForm").serialize(), // 需要提交的 url
			success:function(data){
				if(data!=null){
					$("#formPrint").html(data);
            	}
				if($("#_reportFormLedgerType").val()==2||$("#_reportFormLedgerType").val()==3){
            		onLoadDelay();
	            }else{
					reloadIssue();
	            }
				$("#issueDialog").dialog("close");

				$("#printFormDialog").next().find(".ui-dialog-buttonset  button").eq(0).hide();
				//$.messageBox({message:"已经成功保存申办单!"});
				$("#formPrint").printArea();
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交数据时发生错误");
		  	}
		});
		},
		rules:{
			"declareForm.reason":{
				required:true,
				minlength:2,
				maxlength:50
			},
			"declareForm.handleContent":{
				required:true,
				minlength:2,
				maxlength:50
			}
		},
		messages:{
			"declareForm.reason":{
				required:"请输入申报主要事项及原因",
				minlength:$.format("申报主要事项及原因至少需要输入{0}个字符"),
				maxlength:$.format("申报主要事项及原因最多需要输入{0}个字符")
			},
			"declareForm.handleContent":{
				required:"请输入申报单位主要领导意见",
				minlength:$.format("申报单位主要领导意见至少需要输入{0}个字符"),
				maxlength:$.format("申报单位主要领导意见最多需要输入{0}个字符")
			}
	    }
	});
})


</script>