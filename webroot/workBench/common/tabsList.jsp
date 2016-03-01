<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="${keyName}Tabs">
	<ul class="tableList">
	<c:forEach items="${tabPatels }" var="tabPatel">
		<pop:JugePermissionTag ename="needIssueListManagement,checkPendingIssueListManagement,verificationIssueListManagement,checkGradeIssueListManagement,pmInboxManagement">
		<li><a href="${tabPatel.url}" id="${tabPatel.keyName}" maxUrl="${tabPatel.maxUrl}">${tabPatel.cname}</a></li>
		</pop:JugePermissionTag>
	</c:forEach>
	</ul>
</div>
<div id="module">
     <div class="posa noIssueOfSuper" id="noIssueOfSuper" style="border:1px solid #d4d4d4;border-top:0;border-radius:0 0  5px 5px;width:99%;">
		<div class="warnspace">暂无权限</div>
	 </div>
</div>
<script>
$(function(){
	function shortcutList(){		
		
		if($('.tableList').find('li').length > 0){
			$("#module .noIssueOfSuper").hide();
		}
		
	}
	shortcutList();
	$("#${keyName}Tabs").workBenchTabs();
});

</script>