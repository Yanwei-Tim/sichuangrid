<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:forEach items="${moduelClicks }" var="tab">
		<div class="portlet_box portlet_boxPop" id="portlet_box">
			<div class="portlet_box_person">
				<a href="${tab.url}" rel="${tab.rel}"><img src="${tab.imgUrl}"/></a>
			</div>
			<p class="portlet_box_title"><a href="${tab.url}" rel="${tab.rel}">${tab.permission.cname}</a></p>
		</div>
	</c:forEach>
<script type="text/javascript">
$(function() {
	$("#portlet_box a").click(function(){
		var rel=$(this).attr("rel");
		showPageByTopMenu(rel);
	})
});
</script>