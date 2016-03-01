<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*,com.tianque.plugin.account.domain.LedgerFeedBack,com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class='container_24'>
	<form id="accept_form" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="edit" />
		<input type="hidden" name="acceptForm.stepId"  value="${acceptForm.stepId}" />
		<input type="hidden" name="keyId"  value="${acceptForm.stepId}" />
		<input type="hidden" name="acceptForm.acceptOrg.id"  value="${acceptForm.acceptOrg.id}" />
		<input type="hidden" id="_acceptFormLedgerType" name="acceptForm.ledgerType"  value="${acceptForm.ledgerType}" />
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">编号：</label>
	 	</div>
		<div class="grid_6">
		<input type="text"  name="acceptForm.serialNumber" id="serialNumber"  maxlength="30" value="${acceptForm.serialNumber}" 
				readonly="readonly" class='form-txt' />
		</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">受理单位：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="acceptForm.acceptOrg.orgName" id="acceptForm.acceptOrg.orgName"  maxlength="30" value="${acceptForm.acceptOrg.orgName}" 
				readonly="readonly" class='form-txt' />
		</div>
		
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">来件时间：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" id="formComeDate" name="acceptForm.formComeDate" class="form-txt"
			value="<s:date name="acceptForm.formComeDate" format="yyyy-MM-dd "/>" readonly />
		</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">接件时间：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" id="formAcceptDate" name="acceptForm.formAcceptDate" class="form-txt"
			value="<s:date name="acceptForm.formAcceptDate" format="yyyy-MM-dd "/>" readonly />
		</div>    
		
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">来件类型：</label>
	 	</div>
		<div class="grid_6">
			<!--<input type="text"  name="acceptForm.formType" id="formType"  maxlength="30" value="${acceptForm.formType}" 
				 class='form-txt' />-->
			<select name="acceptForm.formType.id" id="formType" class="form-select {required:true,messages:{required:'来件类型必须选择'}}" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FORM_TYPE" defaultValue="${acceptForm.formType.id }"  showInternalId="true"/>
			</select>
		</div>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">承办人：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="acceptForm.dealUserName" id="acceptForm.dealUserName"  maxlength="30" value="${acceptForm.dealUserName}" 
				 class='form-txt' />
		</div>
		
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">本级台账办拟办意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="currentOrgOpinion" name="acceptForm.currentOrgOpinion"  maxlength="50" class='form-txt' >${acceptForm.currentOrgOpinion}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">分管领导意见：</label>
		</div>
		<div class="grid_17 heightAuto">
			<textarea  id="inChargeOfLeaderOpinion" name="acceptForm.inChargeOfLeaderOpinion"  maxlength="50" class='form-txt' >${acceptForm.inChargeOfLeaderOpinion}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">主要领导意见：</label>
		</div>	
		<div class="grid_17 heightAuto">
			<textarea  id="majorLeaderOpinion" name="acceptForm.majorLeaderOpinion"  maxlength="50" class='form-txt' >${acceptForm.majorLeaderOpinion}</textarea>
		</div>
		
		
	</form>
</div>

<div id="formPrint" style="display:none">

</div>


<script type="text/javascript">
$(document).ready(function(){
	$('#formComeDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	
	$('#formAcceptDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	
	$("#accept_form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				type: 'post',
		        url: '/threeRecords/acceptForm/dispatch.action', 
				success:function(data){
					if(data!=null){
						$("#formPrint").html(data);
	            	}
	            	
	            	if($("#_acceptFormLedgerType").val()==2||$("#_acceptFormLedgerType").val()==3){
	            		onLoadDelay();
		            }else{
						reloadIssue();
		            }
				//	$("#issueDialog").dialog("close");
	
					$("#printFormDialog").next().find(".ui-dialog-buttonset  button").eq(0).hide();
					$("#formPrint").printArea();
					$("#issueDialog").dialog("close");
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("提交数据时发生错误");
		  	}
		});
		},
		
		rules:{
			"acceptForm.currentOrgOpinion":{
				required:true,
				minlength:2,
				maxlength:50
			},
			"acceptForm.formComeDate":{
				required:true
			}
		},
		
		messages:{
			"acceptForm.currentOrgOpinion":{
				required:"请输入内容",
				minlength:"意见至少要输入2个字符！",
				maxlength:"意见不能超过50个字符！"
			},
			"acceptForm.formComeDate":{
				required:"来件时间"
			}
		}
		
	});
})
</script>