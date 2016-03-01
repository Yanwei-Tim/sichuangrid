<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
request.setAttribute("isGrid",request.getParameter("isGrid"));
%>
<s:if test='#request.isGrid !="" && #request.isGrid  =="true"'>
<jsp:include page="/baseinfo/leaderView/gridCommonStatistics.jsp">
	<jsp:param value="PositiveInfo" name="moduleName"/>
	<jsp:param value="positiveInfo" name="populationType"/>
	<jsp:param value="刑释人员" name="moduleCName"/>
</jsp:include>
</s:if>
<s:else>
<jsp:include page="/baseinfo/leaderView/commonStatistics.jsp">
	<jsp:param value="PositiveInfo" name="moduleName"/>
	<jsp:param value="positiveInfo" name="populationType"/>
	<jsp:param value="刑释人员" name="moduleCName"/>
</jsp:include>
</s:else>
		