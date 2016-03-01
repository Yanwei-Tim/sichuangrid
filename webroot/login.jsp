<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String currentProject = com.tianque.core.util.GridProperties.CURRENT_PROJECT;
	request.getRequestDispatcher("/login/"+currentProject+"/login.jsp").forward(request, response);
%>