<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	$("#portlet-task-message").find("li").hover(function(){
		$(this).css("background","#f2f2f2");
		$(this).find(".lookNew").addClass("btnHover")	
	},function(){
		$(this).css("background","#fff");
		$(this).find(".lookNew").removeClass("btnHover")	
	})
</script>

<div class="task_title">
	平台消息（<span class="num"> <c:choose>
			<c:when test="${gridPage.records >0}">
							${gridPage.records}
						</c:when>
			<c:otherwise>
						0
						</c:otherwise>
		</c:choose> </span>）
</div>
<c:choose>
	<c:when test="${gridPage.records >0}">
		<ul id="portlet-task-message">
			<c:forEach items="${gridPage.rows}" var="messageOfUnread">
				<li
					style="background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;">
					<span class="balckdot"></span> <a href="javascript:;"
					onclick="viewPlatformMessage(${messageOfUnread.id},${messageOfUnread.sendType})"
					class="isNew"> <c:choose>
							<c:when test="${fn:length(messageOfUnread.title) >35}">
								<c:out
									value="${fn:substring(messageOfUnread.title, 0, 35)}......" />
							</c:when>
							<c:otherwise>
								<c:out value="${messageOfUnread.title}" />
							</c:otherwise>
						</c:choose> <a href="javascript:;"
						onclick="viewPlatformMessage(${messageOfUnread.id},${messageOfUnread.sendType})"
						class="lookNewspe" target="_parent"><span>马上处理</span> </a> <span
						class="date"><fmt:formatDate
								value="${messageOfUnread.sendDate}" type="both" /> </span>
				</li>
			</c:forEach>
		</ul>
		<div class="showMore cf">
			<a href="javascript:;" id="interactionManagement">更多&gt;&gt;</a>
		</div>
	</c:when>
	<c:otherwise>
		<div class="posa">
			<div class="warnspace">暂无平台消息</div>
		</div>
	</c:otherwise>
</c:choose>
<script type='text/javascript'>
$(document).ready(function(){
	$("#interactionManagement").click(function(){
		$("#interactionManagement").attr("href","/module.jsp#interactionManagement");
		$(".ui-layout-center").css({
			width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
		})
		$(".ui-layout-west").show().load("/sysadmin/menuManage/getLeftMenuListToJson.action?ename=interactionManagement",function(){
			$("#thisCrumbs,.slideResizer").show();
		});
	});
	
});
</script>