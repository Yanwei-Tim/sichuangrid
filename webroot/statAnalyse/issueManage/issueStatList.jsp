<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div id="chartsTabs">
	<ul>
		<li><a href="${path}/statAnalyse/issueManage/issueAnalysisChartManage/issueAreaStatisticColumn.jsp">区域分布</a></li>
		<li><a href="${path}/statAnalyse/issueManage/issueAnalysisChartManage/issueStatisticPie.jsp">类型分布</a></li>
		<li><a href="${path}/statAnalyse/issueManage/listMange/index.jsp">列表信息</a></li>
		<li><a href="${path}/statAnalyse/issueManage/issueAnalysisChartManage/issueTendencyChart.jsp">趋势图</a></li>
	</ul>
</div>
<script>
$(function() {
	$( "#chartsTabs" ).tabs({ selected: 0 ,beforeLoad:function(){
		$( "#chartsTabs" ).find(".ui-tabs-panel").empty();
	}});
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );
	
	// 列表信息 和 柱图 饼图   外层容器计算高度
	$.sigmaReportLayout();
	
	$.loadingComp("close");
});

function enableOrDisableColumn(i){
	if(isGrid()){
		$("#chartsTabs").tabs("select", i);
		$("#chartsTabs").tabs("disable", 0);
		$("#chartsTabs").tabs("disable", 2);
	}else{
		$("#chartsTabs").tabs("enable", 0);
		$("#chartsTabs").tabs("enable", 2);
	}
	var ifOldIssueStatReport = '<pop:JugePermissionTag ename="oldIssueStatReport">true</pop:JugePermissionTag>';
	var iffNewIssueStatReport = '<pop:JugePermissionTag ename="newIssueStatReport">true</pop:JugePermissionTag>';
	if(ifOldIssueStatReport!='true' && iffNewIssueStatReport!='true'){
		$("#chartsTabs").tabs("disable", 2);
	}
}

function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#year").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#month").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
}
</script>