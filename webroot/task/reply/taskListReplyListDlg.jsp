<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file="/includes/baseInclude.jsp"%>	

<table class="tablelist" id="infoList">
	<c:if test="${empty taskListReplyList}">无回复</c:if>
	<c:forEach items="${taskListReplyList}" var="taskListReply">
		<tr>
			<td class="title"><label>回复时间：</label></td>
			 <td class="content" colspan="3"><span><fmt:formatDate  value="${taskListReply.replyDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
		</tr>
		<tr>
			<td class="title"><label>回复人：</label></td>
			 <td class="content" colspan="3"><span>${taskListReply.replyUser}</span></td>
		</tr>
		<tr>
			<td class="title"><label>处理情况：</label></td>
			 <td class="content" colspan="3"><span>${taskListReply.replyContent}</span></td>
		</tr>
		
		<tr>
			<td class="title"><label>附件：</label></td>
			 <td class="content" colspan="3">
			 <c:forEach items="${taskListReply.annexFiles }" var="annexFile">
				<span><a href='${path}/plugin/taskListManage/common/downLoadAttachFile.action?attachFileId=${annexFile.id}'>${annexFile.fileName}</a></span><br/>
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="2" ></td>
		</tr>
	</c:forEach>
</table>
