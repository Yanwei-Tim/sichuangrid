<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24" >
	<form id="maintainEvaluateFeedbacksForm" method="post" action="" >
	<s:if test="#parameters.evaluateMode[0]=='add'">
	<pop:token/>
	</s:if>
		<input id="accountId" type="hidden" name="evaluateFeedbacks.accountId" value="${evaluateFeedbacks.accountId }" />
		<input id="id" type="hidden" name="evaluateFeedbacks.id" value="${evaluateFeedbacks.id }" />
		<input id="accountType" type="hidden" name="evaluateFeedbacks.accountType" value="${evaluateFeedbacks.accountType }" />
		<input id="evaluateUser" type="hidden" name="evaluateFeedbacks.evaluateUser" value='<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>' />
		<input id="orgId" type="hidden" name="organization.id" value='<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>' />
		<input id="evaluateMode" type="hidden" name="evaluateMode" value="${param.evaluateMode }" />
		<div class="grid_4">
			<em class="form-req">*</em>
   			<label class="form-lb1">反馈方式:</label>
		</div>
		<div class="grid_5">
			<label class="form-check-text"><input id="type_verbal" type="radio" checked="checked" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@TYPE_VERBAL'/>" name="evaluateFeedbacks.feedbacksType" <s:if test="evaluateMode=='view'">disabled</s:if> />口头</label>
		</div>
		<div class="grid_5">
			<label class="form-check-text"><input id="type_phone" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@TYPE_PHONE'/>" name="evaluateFeedbacks.feedbacksType" <s:if test="evaluateMode=='view'">disabled</s:if> />电话</label>
		</div>
		<div class="grid_5">
			<label class="form-check-text"><input id="type_written" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@TYPE_WRITTEN'/>" name="evaluateFeedbacks.feedbacksType" <s:if test="evaluateMode=='view'">disabled</s:if> />书面</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4">
			<em class="form-req">*</em>
   			<label class="form-lb1">反馈意见:</label>
		</div>
		<div class="grid_5">
			<label class="form-check-text"><input id="opinion_satisfaction" type="radio" checked="checked" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@OPINION_SATISFACTION'/>" name="evaluateFeedbacks.feedbacksOpinion" <s:if test="evaluateMode=='view'">disabled</s:if>/>满意</label>
		</div>
		<div class="grid_5">
			<label class="form-check-text"><input id="opinion_rudimentary_satisfaction" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@OPINION_RUDIMENTARY_SATISFACTION'/>" name="evaluateFeedbacks.feedbacksOpinion" <s:if test="evaluateMode=='view'">disabled</s:if> />基本满意</label>
		</div>
		<div class="grid_5">
			<label class="form-check-text"><input id="opinion_not_satisfaction" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@OPINION_NOT_SATISFACTION'/>" name="evaluateFeedbacks.feedbacksOpinion" <s:if test="evaluateMode=='view'">disabled</s:if> />不满意</label>
		</div>
		<div class="grid_5">
			<label class="form-check-text"><input id="opinion_other" type="radio" value="<s:property value='@com.tianque.xichang.working.flow.constant.FinishType@OPINION_OTHER'/>" name="evaluateFeedbacks.feedbacksOpinion" <s:if test="evaluateMode=='view'">disabled</s:if> />其他</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4" >
			<em class=""></em>
	   			<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_18" style="height: 120px">
	   			<textarea rows="4" name="evaluateFeedbacks.remark" id="remark" style="width: 99%"
	   				class='form-txt {maxlength:200,messages:{maxlength:"备注最多需要输入200个字符"}}' 
	   			 <s:if test="evaluateMode=='view'">readOnly</s:if>>${evaluateFeedbacks.remark }</textarea>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	<s:if test="#parameters.evaluateMode[0]=='view'">
		 $(":radio[name='evaluateFeedbacks.feedbacksType'][value='${evaluateFeedbacks.feedbacksType }']").attr("checked", "checked");
		 $(":radio[name='evaluateFeedbacks.feedbacksOpinion'][value='${evaluateFeedbacks.feedbacksOpinion }']").attr("checked", "checked");
		
	</s:if>
	$("#maintainEvaluateFeedbacksForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
					if('add'=='<s:property value="#parameters.evaluateMode[0]"/>'){
						if(data!=null&&data!='true'&&data!=true){
							$.messageBox({
								message:data,
								level: "error"
							});
							return;
						}
						$.messageBox({message:"评价完成"});
						$("#peopleaspirationsDialog").dialog("close");
						$("#poorPeopleDialog").dialog("close");
						$("#steadyWorkDialog").dialog("close");
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
	var mode='${parameters.evaluateMode[0] }';
	if('add'==mode){
		$("#maintainEvaluateFeedbacksForm").attr("action","${path}/account/evaluateFeedbacksManage/addEvaluateFeedbacks.action");
	} 
	
});
</script>