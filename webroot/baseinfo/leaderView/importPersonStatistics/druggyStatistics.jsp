<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
   .font1
	{color:#333333;
	font-size:12.0pt;
	font-weight:700;
	font-style:normal;
	text-decoration:none;
	font-family:"宋体";
	mso-font-charset:134;}
</style>
<%
request.setAttribute("isGrid",request.getParameter("isGrid"));
%>
<s:if test='#request.isGrid !="" && #request.isGrid  =="true"'>
<jsp:include page="/baseinfo/leaderView/gridCommonStatistics.jsp">
	<jsp:param value="Druggy" name="moduleName"/>
</jsp:include>	
</s:if>
<s:else>
<jsp:include page="/baseinfo/leaderView/commonStatistics.jsp">
	<jsp:param value="Druggy" name="moduleName"/>
</jsp:include>	
</s:else>

    

		