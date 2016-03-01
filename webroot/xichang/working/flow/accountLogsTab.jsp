<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id=tabs>
	<ul>
		<li id="peopleaspiration"><a href="${path}/account/peopleAspirationManage/dispatchOperate.action?mode=${param.mode}&peopleAspirations.id=${param.id}">基本信息</a></li>
		<li id="poorpeople"><a href="${path}/account/poorPeopleManage/dispatch.action?mode=${param.mode}&poorPeople.id=${param.id}">基本信息</a></li>
		<li id="steadywork"><a href="${path}/account/steadyWorkManage/dispatch.action?mode=${param.mode}&steadyWork.id=${param.id}">基本信息</a></li>
		<li><a href="${path }/xichang/working/flow/accountLogsList.jsp?accountId=${param.id}&mode=${param.mode}&accountType=${param.accountType}">处理情况</a></li>
		<li id="evaluate"><a href="${path }/account/evaluateFeedbacksManage/dispatch.action?evaluateFeedbacks.accountId=${param.id}&evaluateFeedbacks.accountType=${param.accountType}&evaluateMode=${param.evaluateMode}">反馈评价</a></li>
	    <li id="poorPeoplemember"><a href="${path}/account/poorPeopleMemberManage/poorPeopleMemberList.action?poorPeopleMembers.poorPeople.id=${param.id}&mode=${param.mode}">成员信息</a></li>
	</ul>
</div>
<script>
var accountType="${param.accountType }";
var isEvaluate="${param.isEvaluate }";
var isCanEvaluate="${isCanEvaluate }";
var mode="${param.mode }";
var evaluateMode="${param.evaluateMode }";
$(function() {
	if(accountType=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@PEOPLEASPIRATION'/>"){
		$("#tabs #poorpeople,#steadywork,#poorPeoplemember").remove();
	}
	if(accountType=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE'/>"){
		$("#tabs #peopleaspiration,#steadywork").remove();
	}
	if(accountType=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@STEADYWORK'/>"){
		$("#tabs #peopleaspiration,#poorpeople,#poorPeoplemember").remove();
	}
	if(isEvaluate!="true"&&evaluateMode!="add"&&isCanEvaluate!="true"){
		$("#tabs #evaluate").remove();
	}
	if(isEvaluate=="true"&&evaluateMode=="add"){
		
		$("#tabs").tabs({cache:true,selected:2});
	}else{
		$("#tabs").tabs({cache:true,select:0});
	}
	
});
</script>