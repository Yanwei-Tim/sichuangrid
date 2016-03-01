<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	String type = request.getParameter("type");
	request.setAttribute("type",type);
%>

<script type="text/javascript">
	
	var 
	 columns = [
			{name:"orgName",caption:"区域",width:120,align:'center',mode:"string"},
			{name:"amount",caption:"人数",width:80,mode:"number",align:'center',format:"#"},
			{name:"general",caption:"总况",children:[
				{name:"baseinfoStatisticDetailVo[index].typeName",caption:"类型",width:100,align:'center',mode:"string"},
				{name:"baseinfoStatisticDetailVo[index].sum",caption:"数量",width:80,mode:"number",align:'center',format:"#"}
				
			]}

		];
	
</script>
<jsp:include page="/statAnalyse/baseInfoStat/common/commonStatisticList.jsp">
	<jsp:param value="${type}" name="type"/>
</jsp:include>
