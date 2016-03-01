<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
String sysBottomPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_BOTTOM_PAGE);
String sysHeaderPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_HEADER_PAGE);
String simpleRelease = globalSettingService.getGlobalValue(GlobalSetting.SIMPLE_RELEASE);
request.setAttribute("sysHeaderPage",sysHeaderPage);
request.setAttribute("simpleRelease",simpleRelease);
%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<title>公共安全应急指挥系统</title>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/includes/jsInclude.jsp" />
<script type="text/javascript" src="${resource_path}/resource/system/js/incident/incidentTypeTreeManager.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/incident/init.js"></script>
<script type="text/javascript">
$(function() {
	$.layout({
		west__size:230
	});
	$('<div id="baseLine"></div>').appendTo('body');
});
</script>
</head>
<body>
	<div class="ui-layout-north">
		<s:if test='null!=@com.tianque.core.util.GridProperties@CURRENT_PROJECT&&!"".equals(@com.tianque.core.util.GridProperties@CURRENT_PROJECT)'>
			<jsp:include page="/incident/navigation.jsp" >
				<jsp:param value="${sysHeaderPage}" name="sysHeaderPage"/>
				<jsp:param value="${simpleRelease}" name="simpleRelease"/>
			</jsp:include>
		</s:if>
		<s:else>
			<jsp:include page="/login/default/navigation.jsp" >
				<jsp:param value="${sysHeaderPage}" name="sysHeaderPage"/>
				<jsp:param value="${simpleRelease}" name="simpleRelease"/>
			</jsp:include>
		</s:else>
	</div>
	<div class="ui-layout-west">
		
	</div>
	<div class="ui-layout-center">
		<div class="submenu" style="display: none">
			<div class="subnav">
			</div>
		</div>
		<div class="path currentPath" style="display: none">
			
		</div>
		<div class="ceng"></div>
		<div class="popMenu" style="display: none"></div>
		<div class="clear"></div>
		<div class="content">
			<div id="loading" style="display: none;color:#999;text-align:center;height:32px;line-height:32px;margin-top:200px;"><img
				src="${resource_path}/resource/images/loading.gif" alt="加载中..." style="vertical-align:middle;margin-right:5px;" />加载中，请稍候...</div>
			<div id="contentDiv"></div>
		</div>
	</div>
	<div class="ui-layout-south">
		<div id="bottom">
			<p><%=sysBottomPage %></p>
		</div>
	</div>
	<div id="jGrowl"></div>
</body>
</html>