<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="rectifiy" class="container container_24">
	<div id="tabs">
		<ul>
			<li><a href="${path}/baseinfo/scenicEquipmentManage/viewScenicEquipment.action?mode=viewBase&location.id=${location.encryptId}">基本信息 </a> </li>
		</ul>
	</div>
</div>
<script>
$(function() {
	$("#tabs").tabs();
});
</script>