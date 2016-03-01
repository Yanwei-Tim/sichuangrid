<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	$("#portlet-task-issueOfNeed").find("li").hover(function(){
		$(this).css("background","#f2f2f2");
		$(this).find(".lookNew").addClass("btnHover")	
	},function(){
		$(this).css("background","#fff"); 
		$(this).find(".lookNew").removeClass("btnHover")	
	})
</script>
<div class="task_title">
	待办事项（ <span class="num"> <c:choose>
			<c:when test="${gridPage.records >0}">
							${gridPage.records}
						</c:when>
			<c:otherwise>
					0
					</c:otherwise>
		</c:choose>
	</span>）
</div>
<c:choose>
	<c:when test="${not empty gridPage.rows}">
		<ul id="portlet-task-issueOfNeed">
			<c:forEach items="${gridPage.rows}" var="issueOfNeed">
				<li
					style="background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;" class="isNew issueOfNeed">
					<span class="balckdot"></span>
					<c:choose>
						<c:when test="${fn:length(issueOfNeed.subject) > 18}">
							<c:out value="${fn:substring(issueOfNeed.subject, 0, 18)}......" />
						</c:when>
						<c:otherwise>
							<c:out value="${issueOfNeed.subject}" />
						</c:otherwise>
					</c:choose>
					</li>
			</c:forEach>
		</ul>
	</c:when>
	<c:otherwise>
		<div class="posa">
			<div class="warnspace">暂无待办事项</div>
		</div>
	</c:otherwise>
</c:choose>
<div class="showMore cf">
	<pop:JugePermissionTag ename="serviceWork">
		<a href="javascript:;" id="serviceWorkMenu">更多&gt;&gt;</a>
	</pop:JugePermissionTag>