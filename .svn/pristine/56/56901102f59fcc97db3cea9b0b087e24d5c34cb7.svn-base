<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/includes/baseInclude.jsp" />

<style>
input{
	margin: 5px;
}
</style>

<div id="orgList">
	<form id="moreOrgSelect" method="post" action="">
		<c:forEach items="${childOrg}" var="org">
			<input type="checkbox" id="${org.id} " />${org.orgName}
		</c:forEach>
	</form>
</div>

<script type="text/javascript">
function fillOrgInputer(){
	var orgIds = new Array();
	var chks=$("input:checked");
	for(var i = 0; i < chks.length; i++){
		orgIds[i]=chks[i].id;
	}
	return orgIds;
}
</script>