<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String type = request.getParameter("type");
	request.setAttribute("type",type);
%>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/statAnalyse/baseInfoStat/common/commonStatisticPie.jsp">
	<jsp:param value="${type}" name="type"/>
</jsp:include>