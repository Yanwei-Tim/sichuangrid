<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
.imageC pre img{width:200px;height:200px;}

</style>
<table class="tablelist">
	<tr>
		<td class="title" style="width: 100px;"><label>区 域</label></td>
		<td class="content" style="width: 670px;">${publicNotice.organization.orgName}</td>
	</tr>
	<tr>
		<td class="title" style="width: 100px;"><label>标 题</label></td>
		<td class="content" style="width: 670px;">
			<span id="">${publicNotice.publicNoticeTitle}</span>
		</td>
	</tr>
	<tr>
		<td class="title" style="width: 100px;"><label>接收对象</label></td>
		<td  class="content" style="padding-top: 8px; padding-bottom: 5px; width: 670px;">
			<span id="" class="imageC">${publicNotice.publicNoticeObject.publicNoticeObject}</span>
		</td>
	</tr>
	<tr>
		<td class="title" style="width: 100px;"><label>落款</label></td>
		<td class="content" style="width: 670px;"><span>${publicNotice.publicNoticeInscribe}</span></td>
	</tr>
	<tr>
		<td class="title" style="width: 100px;">内 容</td>
		<td  class="content" style="padding-top: 8px; padding-bottom: 5px; width: 670px;">
			<span id="" class="imageC">${publicNotice.publicNoticeMatter}</span>
		</td>
	</tr>
	<c:if test='${publicNotice.isAttachment }'>
	<tr>
	 <td class="title" style="width: 100px;">附件</td>
	 <td class="content" style="width: 670px;">
		 <c:forEach items="${publicNotice.noticeFiles }" var="obj">
			<a href="/workBench/noticeManage/downLoadActualNoticeFile.action?fileId=${obj.id}" >
				<img  src="${resource_path}/resource/images/fujian.jpg"/>${obj.fileName}
			</a>
		</c:forEach>
	 </td>
	</tr>
	</c:if>
	<tr>
		<td class="title" style="width: 100px;">截止日期</td>
		<c:choose>
		<c:when test='${publicNotice.overdueDate == null }'>
			<td class="content" style="width: 670px;">不限</td>
		</c:when>
		<c:otherwise>
			<td class="content" style="width: 670px;">
				<fmt:formatDate value="${publicNotice.overdueDate}" type="date" pattern="yyyy-MM-dd" />
			</td>
		</c:otherwise>
		</c:choose>
	</tr>
</table>

