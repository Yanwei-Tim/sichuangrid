<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="companyPlace" class="container container_24">
		<div id=tabs>
			<ul>
			<li><a href="${path}/baseinfo/companyPlaceManage/dispatchOperate.action?cids=${param.cid}&mode=view">基本信息</a></li>
			<li id="businessInfos"><a href="${path}/baseinfo/companyPlaceBusinessManage/dispatch.action?id=${param.cid}&mode=view&modeName=${param.modulKey}">业务信息</a></li>
			<li><a id="personnelTrackInfos">轨迹信息</a></li>
			<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocationByEncrypt.action?mode=view1&id=${param.cid}&module=${param.modulKey}&name=${param.name}"> <c:if test="${param.modulKey=='POLLUTIONSOURCE'}">环保负责人</c:if> <c:if test="${param.modulKey!='POLLUTIONSOURCE'}">治安负责人</c:if> </a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="serviceRecordManagement">
				<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulationByEncrypt.action?mode=page&fromSource=population&id=${param.cid}&module=${param.modulKey}"><c:if test="${param.modulKey=='POLLUTIONSOURCE'}">巡查情况</c:if> <c:if test="${param.modulKey!='POLLUTIONSOURCE'}">巡场情况</c:if></a></li>
			</pop:JugePermissionTag>
			</ul>
   		</div>
  	</div>
	<script>
	$("#personnelTrackInfos").attr("href","${path }/baseinfo/companyPlace/companyPlaceTrackInfos.jsp?id=${param.cid}&width=750&height=370&populationType=${populationType}");
	$(function() {
		$( "#tabs" ).tabs({ selected: 0 });
	});
	</script>