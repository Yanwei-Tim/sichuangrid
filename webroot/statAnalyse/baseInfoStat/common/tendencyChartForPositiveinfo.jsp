<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="container1" class="SigmaReport"></div>

<script type="text/javascript">
$(document).ready(function() {
	onOrgChanged();
});

function onOrgChanged(){
	$("#container1").lineChart({
		url: "${path}/baseInfoStat/tendencyChart/showTendencyChartForPositiveinfo.action?organization.id="+getCurrentOrgId()+"&type="+'<s:property value="#parameters.tableNameKey"/>',
		moduleName:""
	});
	enableOrDisableColumn(3);
}
</script>