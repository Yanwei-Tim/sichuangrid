<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("list",request.getParameter("list"));%>
<table class="tablelist">
	<tr>
	 	<td class="title"><label>上报单位</label></td>
	 	<td class="title"><label>是否阵地建设</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class="title"><label>党员人数</label></td>
	 	</s:if>
	 	<td class="title"><label>三务公开群众满意度(%)</label></td>
	 	<td class="title"><label>调查满意度(%)</label></td>
	</tr>
	<c:forEach items="${list }" var="vo">
		<tr>
		<td class=""><label>${vo.reportOrg.orgName }</label></td>
	 	<td class=""><label id="positionBuilding">${vo.organizationConstruction.isPositionBuilding }</label></td>
	 	<s:if test="0==isPlaning">
	 	<td class=""><label>${vo.organizationConstruction.partyMembers }</label></td>
	 	</s:if>
	 	<td class=""><label>${vo.organizationConstruction.threeSatisfaction }</label></td>
	 	<td class=""><label>${vo.organizationConstruction.surveySatisfaction }</label></td>
		</tr>
	</c:forEach>
</table>

