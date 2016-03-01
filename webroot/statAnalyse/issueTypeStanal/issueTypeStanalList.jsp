<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>事件处理类别统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
    <div id="flashcontent" style="width: 100%; height: 350px">

	</div>

	<script type="text/javascript">
	var scopeId ='<%=request.getParameter("scopeId")%>';
	var orgId ='<%=request.getParameter("orgId")%>';

	var minYear ='<%=request.getParameter("minYear")%>';
	var minMonth ='<%=request.getParameter("minMonth")%>';
	var maxYear ='<%=request.getParameter("maxYear")%>';
	var maxMonth ='<%=request.getParameter("maxMonth")%>';
	var issueTypeStanalIssueTypeDomainId = '<%=request.getParameter("issueTypeStanal.issueTypeDomain.id")%>';

	$("#flashcontent").columnChart({
		url: "${path}/issueTypeStanal/issueTypeStanalManage/columnTypeSon.action?issueTypeStanal.issueTypeDomain.id="+issueTypeStanalIssueTypeDomainId+"&scopeId="+scopeId+"&orgId="+orgId+"&minYear="+minYear+"&minMonth="+minMonth+"&maxYear="+maxYear+"&maxMonth="+maxMonth,
		moduleName:"事件处理类别统计"
		
	})
	</script>
	
  </body>
</html>
