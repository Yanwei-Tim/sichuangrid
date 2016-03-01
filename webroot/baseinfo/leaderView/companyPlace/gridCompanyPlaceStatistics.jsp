<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%request.setAttribute("modul",request.getParameter("modul"));%>
<jsp:include page="/baseinfo/leaderView/companyPlace/gridCompanyPlaceCommonSraristics.jsp">
	<jsp:param value="${modul}" name="modul"/>
</jsp:include>