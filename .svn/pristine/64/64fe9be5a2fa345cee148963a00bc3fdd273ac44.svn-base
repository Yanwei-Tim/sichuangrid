<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="com.tianque.domain.property.OrganizationType,com.tianque.domain.Organization,com.tianque.domain.property.OrganizationLevel,
         com.tianque.core.util.ThreadVariable,com.tianque.domain.User" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="/includes/baseInclude.jsp" />

<%
    Organization userOrg = ThreadVariable.getUser().getOrganization();
	boolean isTown = false,isVillage = false,isJg = false,isFun = false;
	if (userOrg.getOrgType().getDisplayName().equals(OrganizationType.getInternalProperties().get(OrganizationType.FUNCTIONAL_ORG).getDisplayName())) {
		if ((userOrg.getDepartmentNo().endsWith("1jg") || userOrg.getDepartmentNo().endsWith("1lx") || userOrg.getDepartmentNo().endsWith("1xw"))) {
			isJg = true;
		}else{
			isFun = true;
		}
		
	}
    if (userOrg.getOrgLevel().getInternalId()==OrganizationLevel.TOWN) {
    	isTown = true;
	}
    if(userOrg.getOrgLevel().getInternalId()==OrganizationLevel.VILLAGE){
		isVillage = true;
	}
    request.setAttribute("isTown", isTown);
    request.setAttribute("isVillage", isVillage);
    request.setAttribute("isJg", isJg);
    request.setAttribute("isFun", isFun);
%>

<div id="tabList">
	<ul>
	<c:if test="${isJg}">
		<li><a href="${path}/account/informationAnalysis/currentYearCollectDoneRateSum.jsp">汇总&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/informationAnalysis/currentYearCollectDoneRateDetail.jsp?orgType=1">乡镇详情&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/informationAnalysis/currentYearCollectDoneRateDetail.jsp?orgType=0">县级详情&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	</c:if>
	<c:if test="${isTown}">
		<li><a href="${path}/account/informationAnalysis/currentYearCollectDoneRateSum.jsp">汇总&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
		<li><a href="${path}/account/informationAnalysis/currentYearCollectDoneRateDetail.jsp?orgType=1">村级详情&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	</c:if>
	<c:if test="${isVillage || isFun}">
		<li><a href="${path}/account/informationAnalysis/currentYearCollectDoneRateSum.jsp">汇总&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
	</c:if>
	</ul>
 </div>
 
 <script type="text/javascript">

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