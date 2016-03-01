<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class='container_24'>
	<form id="report_form" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="edit" />
		<input type="hidden" name="reportForm.stepId"  value="${reportForm.stepId}" />
		<input type="hidden" name="reportForm.sourceOrg.id"  value="${reportForm.sourceOrg.id}" />
		<input type="hidden" id="_reportFormLedgerType" name="reportForm.ledgerType"  value="${reportForm.ledgerType}" />
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_6">
		<input type="text"  name="reportForm.serialNumber" id="serialNumber"  maxlength="30" value="${reportForm.serialNumber}" 
				readonly="readonly" class='form-txt' />
		</div>
		
		<div class="grid_5 lable-right">        
			<label class="form-lbl">呈报单位：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="reportForm.sourceOrg.orgName" id="reportForm.sourceOrg.orgName"  maxlength="30" value="${reportForm.sourceOrg.orgName}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">呈报单位联系人：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="reportForm.name" id="reportForm.name"  maxlength="30" value="${reportForm.name}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="reportForm.mobile" id="reportForm.mobile"  maxlength="30" value="${reportForm.mobile}" 
				 class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">        
			<label class="form-lbl">呈报时间：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" id="reportDate" name="reportForm.reportDate" class="form-txt"
			value="<s:date name="reportForm.reportDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">上级部门：</label>
	 	</div>
		<div class="grid_6">
		<input type="text"  name="reportForm.targetOrg.orgName" id="reportForm.targetOrg.orgName"  maxlength="30" value="${reportForm.targetOrg.orgName}" 
				readonly="readonly" class='form-txt' />
		</div>        
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">呈报主要事项及原因：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="reason" name="reportForm.reason"  maxlength="50" class='form-txt' >${reportForm.reason}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">呈报单位工作建议意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="otherContent" name="reportForm.handleContent"  maxlength="50" class='form-txt' >${reportForm.handleContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
	</form>
</div>

<div id="formPrint" style="display:none">
	
</div>


<script type="text/javascript">

$(document).ready(function(){
	$('#reportDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});

	$("#report_form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				type: 'post',
		        url: '/threeRecords/reportForm/dispatch.action?'+$("#issueDealForm").serialize(), 
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
					//$.messageBox({message:"已经成功保存呈报单!"});
					$("#formPrint").printArea();
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交数据时发生错误");
		  	}
		});
		},
		rules:{
			"reportForm.reason":{
			required:true,
			minlength:2,
			maxlength:50
			},
			"reportForm.handleContent":{
				required:true,
				minlength:2,
				maxlength:50
			}
		},
		messages:{
			"reportForm.reason":{
				required:"请输入主要事项及原因",
				minlength:$.format("主要事项及原因至少需要输入{0}个字符"),
				maxlength:$.format("主要事项及原因最多需要输入{0}个字符")
			},
			"reportForm.handleContent":{
				required:"请输入工作建议意见",
				minlength:$.format("工作建议意见至少需要输入{0}个字符"),
				maxlength:$.format("工作建议意见最多需要输入{0}个字符")
			}
	    }
	});
})









</script>