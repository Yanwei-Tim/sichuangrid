<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="importantPersonlColumn" class="SigmaReport"></div>
<script type="text/javascript">


$(document).ready(function() {
	
	onOrgChanged();
});

function onOrgChanged(){
	var chartCount=$("#importantPersonlColumn").columnChart({
		url: "${path}/baseInfoStat/statisticsPersonnel/getStatisticsImportantPersonlColumn.action?orgId="+getCurrentOrgId(),
		moduleName:"特殊人群区域分布图",
		quantity:'人数'	
	});
	enableOrDisableColumn(1);
}

</script>