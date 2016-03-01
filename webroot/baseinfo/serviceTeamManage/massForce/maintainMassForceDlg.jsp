<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<jsp:include page="/baseinfo/serviceTeamManage/commonServiceTeam/maintainCommonDlg.jsp">
	<jsp:param value="MassForce" name="moduleName" />
	<jsp:param value="群防群治队伍" name="moduleCName" />
	<jsp:param value="队伍名称" name="serviceTeamName" />
	<jsp:param value="队伍类别" name="serviceTeamType" />
</jsp:include>