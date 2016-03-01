<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24" >
	<form id="maintainLedgerFeedBackForm" method="post" action="" >
	<s:if test="#parameters.evaluateMode[0]=='add'">
	<pop:token/>
	</s:if>
		<input id="stepId" type="hidden" name="ledgerFeedBack.stepId" value="${param.keyId }" />
		<input id="ledgerId" type="hidden" name="ledgerFeedBack.ledgerId" value="${param.ledgerId }" />
		<input id="ledgerType" type="hidden" name="ledgerFeedBack.ledgerType" value="${param.ledgerType }" />
		<input id="evaluateUser" type="hidden" name="ledgerFeedBack.evaluateUser" value='<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/>' />
		<input id="orgId" type="hidden" name="ledgerFeedBack.evaluateOrganization.id" value='<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>' />
		<input id="orgInternalId" type="hidden" name="ledgerFeedBack.evaluateOrganization.orgInternalCode" value='<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgInternalCode()"/>' />
		<input id="evaluateMode" type="hidden" name="evaluateMode" value="${param.evaluateMode }" />
		<div class="grid_4">
			<em class="form-req">*</em>
   			<label class="form-lb1">反馈方式:</label>
		</div>
		<div class="grid_5">
			<label class=""><input id="type_verbal" type="radio" checked="checked" value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@TYPE_VERBAL'/>" name="ledgerFeedBack.feedBackType" <s:if test="evaluateMode=='view'">disabled</s:if> />口头</label>
		</div>
		<div class="grid_5">
			<label class=""><input id="type_phone" type="radio" value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@TYPE_PHONE'/>" name="ledgerFeedBack.feedBackType" <s:if test="evaluateMode=='view'">disabled</s:if> />电话</label>
		</div>
		<div class="grid_5">
			<label class=""><input id="type_written" type="radio" value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@TYPE_WRITTEN'/>" name="ledgerFeedBack.feedBackType" <s:if test="evaluateMode=='view'">disabled</s:if> />书面</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4">
			<em class="form-req">*</em>
   			<label class="form-lb1">反馈意见:</label>
		</div>
		<div class="grid_5">
			<label class=""><input id="opinion_satisfaction" type="radio" checked="checked" value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_SATISFACTION'/>" name="ledgerFeedBack.feedBackOpinion" <s:if test="evaluateMode=='view'">disabled</s:if>/>满意</label>
		</div>
		<div class="grid_5">
			<label class=""><input id="opinion_rudimentary_satisfaction" type="radio" value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_RUDIMENTARY_SATISFACTION'/>" name="ledgerFeedBack.feedBackOpinion" <s:if test="evaluateMode=='view'">disabled</s:if> />基本满意</label>
		</div>
		<div class="grid_5">
			<label class=""><input id="opinion_not_satisfaction" type="radio" value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_NOT_SATISFACTION'/>" name="ledgerFeedBack.feedBackOpinion" <s:if test="evaluateMode=='view'">disabled</s:if> />不满意</label>
		</div>
		<div class="grid_5">
			<label class=""><input id="opinion_other" type="radio" value="<s:property value='@com.tianque.plugin.account.constants.CompleteType@OPINION_OTHER'/>" name="ledgerFeedBack.feedBackOpinion" <s:if test="evaluateMode=='view'">disabled</s:if> />其他</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4" >
			<em class=""></em>
	   			<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_18" style="height: 120px">
	   			<textarea rows="4" name="ledgerFeedBack.remark" id="remark" style="width: 99%"
	   				class='form-txt {maxlength:100,messages:{maxlength:"备注最多需要输入100个字符"}}' 
	   			 <s:if test="evaluateMode=='view'">readOnly</s:if>>${ledgerFeedBack.remark }</textarea>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	$("#maintainLedgerFeedBackForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
					   if(data){
						$.messageBox({message:"评价完成"});
						$("#feedBackDialog").dialog("close");
						$("#issueList").trigger("reloadGrid");
					} 
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{			
		},
		messages:{
		},
		ignore:':hidden'
	});
		$("#maintainLedgerFeedBackForm").attr("action","${path}/threeRecords/feedBack/addFeedBack.action");
	
});
</script>