<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />


<input name="keyType" id = "keyType" value="<s:property value="#parameters.keyTpe"/>" type="hidden"></input>
<div id="place" class="SigmaReport"></div>

<script type="text/javascript">

$(document).ready(function() {
	onOrgChanged();
});

function onOrgChanged(){
	var keyType=$("#keyType").val();
	var chartCount = $("#place").columnChart({
		url: "${path}/baseinfo/statAnalysePlace/findStatAnalysePlaceForHighchartColumnVo.action?orgId="+getCurrentOrgId()+"&keyType="+keyType,
		quantity:'个数'	
	});
	//chartCount.series[1].hide();

	enableOrDisableColumn(1);
}
</script>