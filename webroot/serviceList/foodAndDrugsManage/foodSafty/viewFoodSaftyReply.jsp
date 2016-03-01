<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file="/includes/baseInclude.jsp"%>	

<table class="tablelist" id="infoList">
	<c:if test="${empty serviceListReplyList}">无回复</c:if>
	<c:forEach items="${serviceListReplyList}" var="serviceList">
		<tr>
			<td class="title"><label>回复时间：</label></td>
			 <td class="content" colspan="3"><span><fmt:formatDate  value="${serviceList.replyDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
		</tr>
		<tr>
			<td class="title"><label>回复人：</label></td>
			 <td class="content" colspan="3"><span>${serviceList.replyPeople}</span></td>
		</tr>
		<tr>
			<td class="title"><label>处理情况：</label></td>
			 <td class="content" colspan="3"><span>${serviceList.replyContent}</span></td>
		</tr>
		
		<tr>
			<td class="title"><label>附件：</label></td>
			 <td class="content" colspan="3">
			 <c:forEach items="${serviceList.replyAttchs }" var="annexFile">
				<span><a href='${path}/serviceList/foodSaftyManage/downLoadReplyActualFile.action?attachFileId=${annexFile.id}'>${annexFile.name}</a></span><br/>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="2" ></td>
		</tr>
	</c:forEach>
</table>
