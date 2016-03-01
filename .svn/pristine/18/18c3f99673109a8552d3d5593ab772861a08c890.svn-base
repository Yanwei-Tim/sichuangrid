<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/includes/baseInclude.jsp" />

<div id="positiveinfoPie" class="SigmaReport" ></div>

<script type="text/javascript">
$(document).ready(function() {
	onOrgChanged();
});

function onOrgChanged(){
	$("#positiveinfoPie").pieChart({
		url:"${path}/baseinfo/evaluateStatistic/getEvaluateColumnByOrgId.action?orgId="+getCurrentOrgId(),
		moduleName:"考核评估"
	});
}
</script>