<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	String type = request.getParameter("type");
	request.setAttribute("type",type);
%>
<jsp:include page="/statAnalyse/baseInfoStat/common/commonStatisticColumn.jsp">
	<jsp:param value="${type}" name="type"/>
</jsp:include>