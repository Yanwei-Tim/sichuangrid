<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="jobLogDIV" style="padding: 5px 0;">
	<ul class="subnavbar">
		<li><a href="${path}/sysadmin/jobMonitor/jobMonitorIndex.jsp">quatz日志</a></li>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
			<li><a href="${path}/tbSchedule/tbScheduleJobLog.ftl">TBSchedule日志</a></li>
			<li><a href="${path}/tbSchedule/illegalIdCardNo.ftl">非法身份证</a></li>
		</s:if>
	</ul>
</div>
<script type="text/javascript">
	$(function() {
		$("#jobLogDIV").tabs({
			selected : 0
		});
	});
</script>