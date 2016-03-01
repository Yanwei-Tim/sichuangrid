<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div>
<s:iterator value="myAreas" var="area">
${organization.orgName} 层级下的
<br>
<s:iterator value="#area.organizations" var="org">
${orgName}
</s:iterator>
<hr>
</s:iterator>

</div>