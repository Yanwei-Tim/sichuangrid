<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
String sysBottomPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_BOTTOM_PAGE);
String sysHeaderPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_HEADER_PAGE);
String filePath = globalSettingService.getGlobalValue(GlobalSetting.MOBILE_APK_PATH);
if (filePath != null) {
	filePath = filePath.replace("\\","/");
}
request.setAttribute("sysHeaderPage",sysHeaderPage);
%>
<span>技术支持：中国电信浙江分公司</span>
<!-- <a class="down" id="clientDownload" href="javascript:;" title="即时通讯客户端" target="_blank">下载IM客户端</a> -->
<!--  
<span class="service">服务热线：400-80-800</span>
<span><a target="_blank" class="feedback" href="http://115.236.101.203:9006/feedback/?username=<s:property value="@java.io.File@getUser().getName()"/>&version=产品3.0">反馈区</a></span>
<a class="down" id="clientDownloadandroid" href="javascript:;" title="手机客户端" target="_blank">下载android客户端</a>
<a href="javascript:;" id="showVersion"><span>新功能介绍</span></a>
-->
<script>
	$('#clientDownload').attr('href','${path}/clientDownload/clientDownloadManage/doDownload.action?fileName=TQIM.exe&filePath=uploadFile/upload/TQIM.exe');
	$('#clientDownloadandroid').attr('href','${path}/clientDownload/clientDownloadManage/doDownload.action?fileName=tq_product.apk&filePath=<%=filePath%>');
</script>