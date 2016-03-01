<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="zt_tabs_style">
<div id="chartsTabs">
	<ul>
		<li><a href="${path }/statAnalyse/baseInfoStat/evaluateStatisticPie.jsp">区域分布图</a></li>
		<li><a href="#gridbox">列表信息</a></li>
	</ul>
	<div id="gridbox" class="SigmaReport">
	</div>
</div>
</div>
<script type="text/javascript">
var grid = null;

function onOrgChanged(){
	$.ajax({
		url:'/baseinfo/evaluateStatistic/findEvaluateStatistic.action?orgId='+getCurrentOrgId(),
		success:function(data){
			grid.bindData(data);
		}
	})
}

$(document).ready(function(){
	$("#chartsTabs").tabs().addClass( "ui-tabs-vertical ui-helper-clearfix" );
	$("#chartsTabs li" ).removeClass( "ui-corner-top" ).addClass( "ui-corner-left" );


	var context = {};
	var columns = [
			{name:"score",caption:"成绩",width:120,mode:"string"},
			{name:"amount",caption:"数量",width:80,mode:"number",format:"#"},
			{name:"sum",caption:"",width:80,mode:"number",format:"#",},
			{name:"amount/sum",caption:"比率",width:80,mode:"number",format:"#.00%"},
			{name:"resiteInfo",caption:"明细",children:[
			                     {name:"particulars[index].organization.orgName",caption:"下属单位",width:80,mode:"string",format:""},
			                     {name:"particulars[index].fraction",caption:"分数",width:80,mode:"number",format:"#"}
			                            			]}
		];
	grid = new SigmaReport("gridbox",context,columns);

	onOrgChanged();

	// 列表信息 和 柱图 饼图   外层容器计算高度
	$.sigmaReportLayout();

})

</script>