<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="globalSettingTab">
	<ul class="subnavbar">
		<li><a href="${path}/hotModuel/sysadmin/publicNoticeManage/publicNoticeReceiveList.jsp">收通知</a></li>
		<li><a href="${path}/hotModuel/sysadmin/publicNoticeManage/publicNoticeMainList.jsp">发通知</a></li>
	</ul>
</div>
<script type="text/javascript">
$(function() {
	$("#globalSettingTab").tabs({cache:false,beforeLoad:function(ui){
		$("#maintainForm", $("#contentDiv")).remove();
		$(ui.panel).height($(".ui-layout-center").height()-60);
	}});
});
</script>