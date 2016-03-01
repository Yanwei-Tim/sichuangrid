<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>

<%	
	Long orgId = ThreadVariable.getSession().getOrganization().getId();
%>
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<div id="container1" class="SigmaReport" style="height:230px;"></div>

<script type="text/javascript">
if(<%=AnalyseUtilNew.IS_NEWANALYSE_JOB%>){
	var data_table_url = "${path}/baseInfoStat/tendencyChartNew/showTendencyChartForPositiveinfo.action?organization.id=";
}else{
	var data_table_url = "${path}/baseInfoStat/tendencyChart/showTendencyChartForPositiveinfo.action?organization.id=";
}
$(document).ready(function() {
	onOrgChanged();
});

function onOrgChanged(){
	$("#container1").lineChart({
		url: data_table_url+<%=orgId%>+"&type="+'<s:property value="#parameters.tableNameKey"/>',
		moduleName:""
	});
}
</script>