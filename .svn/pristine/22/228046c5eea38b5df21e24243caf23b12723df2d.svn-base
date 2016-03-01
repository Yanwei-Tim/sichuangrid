<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.tianque.domain.property.OrganizationType,com.tianque.domain.Organization,com.tianque.domain.property.OrganizationLevel"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<%
    Organization userOrg = ThreadVariable.getUser().getOrganization();
    boolean isShow=false ;
    boolean isShowNotice=false ;
    if(userOrg!=null&&userOrg.getOrgType()!=null&&userOrg.getOrgLevel()!=null){
	    if (userOrg.getOrgType().getDisplayName().equals(
	            OrganizationType.getInternalProperties().get(
	                    OrganizationType.FUNCTIONAL_ORG).getDisplayName())&&userOrg.getOrgLevel().getInternalId()==OrganizationLevel.DISTRICT) {
	    	if(userOrg.getDepartmentNo()!=null&&!(userOrg.getDepartmentNo().endsWith("1xw") || userOrg.getDepartmentNo().endsWith("1lx")||userOrg.getDepartmentNo().endsWith("1jg")) ){
	     		isShow = true;
	    	}
	     	isShowNotice = true;
   		 }
	    if (userOrg.getOrgLevel().getInternalId()==OrganizationLevel.TOWN) {
	     	isShow = true;
	     	isShowNotice = true;
   		}
    }
    request.setAttribute("isShowNotice", isShowNotice);
    request.setAttribute("isShow", isShow);
%>
<style>
.issueCount {border-radius: 5px;cursor: pointer;position: absolute;color: #fff;font: bold 11px/1.5em '';top: 6px;right: 9px;background: #F68300;padding: 0 5px;z-index: 1;
}
</style>
<div id="tabList">
	<ul>
		<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=need&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">待办&nbsp;&nbsp;&nbsp;&nbsp;<label id="doingCount" class="issueCount"></label></a> </li>
			<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=done&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">程序性办结&nbsp;&nbsp;&nbsp;&nbsp;<label id="doneCount" class="issueCount"></label></a></li>
			<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=period&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">阶段性办结&nbsp;&nbsp;&nbsp;&nbsp;<label id="periodCount" class="issueCount"></label></a></li>
			<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=completed&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">实质性办结&nbsp;&nbsp;&nbsp;&nbsp;<label id="completedCount" class="issueCount"></label></a></li>
		<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=createAndDone&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">已办&nbsp;&nbsp;&nbsp;&nbsp;<label id="createAndDoneCount" class="issueCount"></label></a> </li>
		<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=feedback&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">待反馈&nbsp;&nbsp;&nbsp;&nbsp;<label id="feedbackCount" class="issueCount"></label></a> </li>
	<!-- 	<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=follow&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">待跟进</a> </li> -->
		<c:if test="${!(fn:endsWith(loginAction.user.organization.departmentNo, '1jg')||fn:endsWith(loginAction.user.organization.departmentNo, 'lxw')||fn:endsWith(loginAction.user.organization.departmentNo, '1lx'))}">
			<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=assign&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">上级交办&nbsp;&nbsp;&nbsp;&nbsp;<label id="assignCount" class="issueCount"></label></a> </li>
			<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=submit&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">上报&nbsp;&nbsp;&nbsp;&nbsp;<label id="submitCount" class="issueCount"></label></a> </li>
		</c:if>
		<c:if test="${isShow}">
			<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=support&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">协办&nbsp;&nbsp;&nbsp;&nbsp;<label id="supportCount" class="issueCount"></label></a></li>
		</c:if>
		<c:if test="${isShowNotice}">
			<li><a href="${path}/account/peopleAspiration/otherForViewList.jsp?viewType=notice&orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>">抄告&nbsp;&nbsp;&nbsp;&nbsp;<label id="noticeCount" class="issueCount"></label></a></li>
		</c:if>
	</ul>
 	</div>
<script>
$(function() {
	var isAuditDelay= '<s:property value="#parameters.isAuditDelay"/>';
	
	if(isAuditDelay!='' && isAuditDelay!=null){
		$( "#tabList" ).tabs({ selected: isAuditDelay ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		}});
	}else{
		$( "#tabList" ).tabs({ selected: 0 ,beforeLoad:function(){
			$( "#tabList" ).find(".ui-tabs-panel").empty();
		}});
	}
});
</script>