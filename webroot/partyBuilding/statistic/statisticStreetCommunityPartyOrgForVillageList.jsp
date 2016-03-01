<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<link href="/resource/css/pacificUnionFounding.css" type="text/css" rel="stylesheet">

<div class="content">
	<div style="width: 100%;overflow:auto;" id="pacificUnionDiv">
		<table width="100%" cellspacing="0" class="countlist">
			<tr>
				<th>单位</th>
				<th>村/社区<br/>党支部数</th>
				<th>村/社区<br/>党员数</th>
				<th>村/社区<br/>党组织活动记录数</th>
			</tr>
			<c:forEach items="${statistic.partyOrgNum}" varStatus="sta">
				<tr>
			  		<td>${statistic.partyOrgNum[sta.index].organization.orgName }</td>
			  		<td>${statistic.partyOrgNum[sta.index].villageSum }</td>
			  		<td></td>
			  		<td></td>
		  		</tr>
		  	</c:forEach>
		</table>
	</div>
</div>

<script type="text/javascript" >
$(function(){
	$("#pacificUnionDiv").height($(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-32);
});
</script>
