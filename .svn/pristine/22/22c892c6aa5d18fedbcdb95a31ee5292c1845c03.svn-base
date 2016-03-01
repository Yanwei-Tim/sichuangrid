<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="importantPersonlColumn" class="SigmaReport"></div>

<script type="text/javascript">


$(document).ready(function() {
	onOrgChanged();
});


function onOrgChanged(){
	var orgId="<s:property value='#parameters.orgId'/>";
	var typeName="<s:property value='#parameters.typeName'/>";
	var modeType="<s:property value='#parameters.modeType'/>";
	
	var chartCount=$("#importantPersonlColumn").columnChart({
		url: '${path}/gis/twoDimensionMapStatisticCommonManage/getStatisticTwoDimensionMapColumnChartInfo.action?organization.id='+orgId+'&typeName='+typeName+'&modeType='+modeType,
		moduleName:"类别分布图",
		quantity:'人数'	
	});
}

</script>