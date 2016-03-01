<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="globalSettingTab">
	<ul class="subnavbar">
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=personInfoChangeLevel">人员信息修改级别设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=baseInfoRestrict">基础信息约束配置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=loginPageImage">登入首页设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=workbenchAppQRcode">工作台APP二维码设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=simpleRelease">是否简化版设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=organizationDeparmentNoSettings">组织机构上传</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=fileDirectory">上传目录设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=sysHeaderAndBottomPage">页眉页脚设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=pluginSettings">插件设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=gridParams">系统参数配置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=mobileVersion">手机版本设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=platformMessage">平台消息模版设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=mobileGPSPositionTimeInterval">GPS定位设置</a></li>
			<li><a href="${path}/sysadmin/jmsMessageManager/queryJmsMessageList.action">jms设置</a></li>
			<li><a href="${path}/sysadmin/globalSettingManage/dispatch.action?mode=tqSearchManage">统一搜索设置</a></li>
	</ul>
</div>
<div>
	<div class="content_contact" >
	  	<div id="contentDiv_contact"></div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	$("#globalSettingTab").tabs({cache:false,beforeLoad:function(ui){
		$("#maintainForm", $("#contentDiv")).remove();
		$(ui.panel).height($(".ui-layout-center").height()-60)
	}});
});
</script>