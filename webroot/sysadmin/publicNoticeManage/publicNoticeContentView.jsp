<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<style type="text/css">
.imageC pre img{width:200px;height:200px;}
.noticeCtt{color:#666; padding:0 20px;}
.noticeCtt .title{font:bold 18px/40px ""; text-align: center;}
.noticeCtt .noticeContent{line-height: 22px;  font-size: 14px;text-indent: 28px; margin-bottom: 20px;word-break: break-all;}
.noticeCtt .noticeBottom{padding-left: 28px;}
.noticeCtt .noticeBottom span{}
.noticeCtt .noticeBottom a{}
.noticeCtt .noticeBottom p{font-size: 14px}
</style>


<div class="noticeCtt">
	<h2 class="title">${publicNotice.publicNoticeTitle}</h2>
	<div class="noticeContent">${publicNotice.publicNoticeMatter}</div>
	<div class="noticeBottom">
		<div style="float:right; padding-right: 20px; line-height: 24px;margin-top: 50px; text-align: center;">
		<p>${publicNotice.publicNoticeInscribe }</p>	
		<%-- 
		<p>区 域：${publicNotice.organization.orgName}</p>
		--%>
		<p>&nbsp;&nbsp; <fmt:formatDate value="${publicNotice.createDate}" type="date" pattern="yyyy-MM-dd" /></p>
		
		</div>
		<c:if test='${publicNotice.isAttachment }'>
		<span>附件：</span>
		<span>
		<c:forEach items="${publicNotice.noticeFiles }" var="obj">
			<a href="/workBench/noticeManage/downLoadActualNoticeFile.action?fileId=${obj.id}"  style="color:blue;">
				<img  src="${resource_path}/resource/images/fujian.jpg"/>${obj.fileName}
			</a>
		</c:forEach>
		</span>
		</c:if>
		 	
	</div>
</div>

