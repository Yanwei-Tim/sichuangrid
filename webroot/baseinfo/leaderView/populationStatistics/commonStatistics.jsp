<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
 <%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<%
request.setAttribute("younthType",request.getParameter("younthType"));
request.setAttribute("populationType",request.getParameter("populationType"));
request.setAttribute("areaDataTableUrl",request.getParameter("areaDataTableUrl"));
request.setAttribute("dataMonthTableUrl",request.getParameter("dataMonthTableUrl"));
%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<script type="text/javascript">
var title = document.title;
if(<%=AnalyseUtilNew.IS_NEWANALYSE_JOB%>){
	var area_data_table_url = "${path}/baseinfo/leaderViewManageNew/statisticsBaseInfoAddCountByOrgIdForArea.action";
	var data_month_table_url = "${path}/baseinfo/leaderViewManageNew/statisticsPopulationSummary.action";
}else{
	var area_data_table_url = "${path}/baseinfo/leaderViewManage/statisticsBaseInfoAddCountByOrgIdForArea.action";
	var data_month_table_url = "${path}/baseinfo/leaderViewManage/statisticsPopulationSummary.action";
}
if('${areaDataTableUrl}'){
	area_data_table_url = '${areaDataTableUrl}';
}
if('${dataMonthTableUrl}'){
	data_month_table_url = '${dataMonthTableUrl}';
}
		jQuery(document).ready(function() {		
			
			function numberFormber(el, options, rowData){
				return rowData.allCount-rowData.attentionCount;
			}
			
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
			var tableHeight=wrapHeight-50;
			$("#areaDatatable").jqGridFunction({
				rowNum:100,
				datatype:"local",	
				width:375,
				height:'auto',
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"地区",sortable:false},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false},
			 		/* <s:if test="(#request.populationType=='floatingPopulation' || #request.populationType=='householdStaff')&&(#request.younthType!='' && #request.younthType!='younth')">
			 		{name:'thisMonthReduceCount',index:'thisMonthReduceCount',label:"本月减少",sortable:false},
			 		</s:if> */
			 		{name:'attentionCount',index:'attentionCount',label:"人员总数",sortable:false}
			 		<s:if test="#request.populationType=='floatingPopulation'">
			 		<pop:JugePermissionTag ename='tibetSinkiangIdentification'>
			 		,{name:'involveSinkiangCount',index:'involveSinkiangCount',label:"涉疆人员",sortable:false}
			 		</pop:JugePermissionTag>
			 		</s:if>
// 			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false}
				],
				autowidth:false,
				shrinkToFit:true,
				gridComplete:function(){
					$("#areaDatatable").find("tbody tr:last td").css('font-weight',700);
				}
			});
			$("#dataMonthTable").jqGridFunction({
				datatype:"local",	
				width:375,
				height:tableHeight,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false},
			 		/* <s:if test="(#request.populationType=='floatingPopulation' || #request.populationType=='householdStaff')&&(#request.younthType!='' && #request.younthType!='younth')">
			 		{name:'thisMonthReduceCount',index:'thisMonthReduceCount',label:"本月减少",sortable:false},
			 		</s:if> */
			 		{name:'attentionCount',index:'attentionCount',label:"人员总数",sortable:false}
			 		<s:if test="#request.populationType=='floatingPopulation'">
			 		<pop:JugePermissionTag ename='tibetSinkiangIdentification'>
			 		,{name:'involveSinkiangCount',index:'involveSinkiangCount',label:"涉疆人员",sortable:false}
			 		</pop:JugePermissionTag>
			 		</s:if>
// 			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false}
				],
				autowidth:false,
				shrinkToFit:true
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				getBaseInfoSummary();
			}
			var layout=function(){
				var wrapWidth=$(".ui-layout-center").width()-400;
				var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
				var tableHeight=wrapHeight-50;
				$(".highcharts-container,.warpTable").height(wrapHeight);
				$(".highcharts-container").width(wrapWidth);
			}
			layout();
			$(window).resize(function(){
				layout();
			})
		});
		function onOrgChanged(){
			$("#areaDatatable").setGridParam({
				url:area_data_table_url,
				datatype:"json",
				page:1,
				loadComplete:function(){
					var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
					var tableHeight=wrapHeight-50;
					var areaContainerChart = $("#areaContainer").columnChart({
						ajax:false,
						table:document.getElementById('gbox_areaDatatable')
					});
					areaContainerChart.series[0].hide();
					areaContainerChart.series[1].hide();
					/* <s:if test="(#request.populationType=='floatingPopulation' || #request.populationType=='householdStaff')&&(#request.younthType!='' && #request.younthType!='younth')">
					areaContainerChart.series[2].remove();
					</s:if>*/
					if(areaContainerChart.series[3]!=undefined){
	 					areaContainerChart.series[3].remove();
					}
					$("#areaDatatableSun").empty();
					$("#areaDatatable").find("tbody tr.jqgfirstrow").clone().appendTo("#areaDatatableSun");
					$("#areaDatatable").find("tbody tr.ui-row-ltr:last").css("background","#fff").appendTo("#areaDatatableSun");
					$("#areaDatatable").closest('div.ui-jqgrid-bdiv').css("max-height",tableHeight);
				}
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"${populationType}"
		   	});

			$("#areaDatatable").trigger("reloadGrid");
			getBaseInfoSummary();
		}
		
		function getBaseInfoSummary(){
			$("#dataMonthTable").setGridParam({
				url:data_month_table_url,
				datatype:"json",
				page:1,
				loadComplete:function(){
					var personGeneralConditionLine = $("#containerSummary").lineChart({
						ajax:false,
						table:document.getElementById('gbox_dataMonthTable'),
						isChangeColor:true
					});
					/* <s:if test="(#request.populationType=='floatingPopulation' || #request.populationType=='householdStaff')&&(#request.younthType!='' && #request.younthType!='younth')">
						personGeneralConditionLine.series[1].remove();
					</s:if>*/
					personGeneralConditionLine.series[1].hide();
					if(personGeneralConditionLine.series[2]!=undefined){
						personGeneralConditionLine.series[2].remove();
					}
// 					personGeneralConditionLine.series[2].hide();
				}
			});
			$("#dataMonthTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"${populationType}"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
		$("#refreshOrgTree1").click(function(){
			var searchConditionStr="请输入姓名或身份证号码";
			if("${populationType}"=="overseaStaff"){
				searchConditionStr="请输入英文名或证件号码";
			}
			$("#searchByCondition").attr("value",searchConditionStr);
			$("#searchText").attr("value",searchConditionStr);
		});

</script>
<s:include value="/baseinfo/leaderView/searchButton.jsp">
	<s:param  name="populationType">${populationType}</s:param>
</s:include>
<div class="leaderTit">概况</div>
<div class="leaderCon">
	<div id="areaContainer" class="highcharts-container">
	</div>
	<div class="warpTable">
		<table id="areaDatatable">
		</table>
		<table id="areaDatatableSun" class="ui-jqgrid leaderAreaDatatable"></table>
	</div>
	<div class="clear"></div>
</div>
<div class="leaderTit">各月份图表</div>
<div class="leaderCon">
	<div id="containerSummary" class="highcharts-container">
	</div>
	<div class="warpTable">
		<table id="dataMonthTable">
		</table>
	</div>
	<div class="clear"></div>
</div>
<div id="${populationType}statisticsDialog"></div>
<div id="statisticsListDialog"></div>