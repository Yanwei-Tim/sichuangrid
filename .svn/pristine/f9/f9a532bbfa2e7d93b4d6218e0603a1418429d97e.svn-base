<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%request.setAttribute("populationType",request.getParameter("populationType"));%>
    
<script type="text/javascript">
var title =document.title;
		jQuery(document).ready(function() {		
			
			function numberFormber(el, options, rowData){
				return rowData.allCount-rowData.attentionCount;
			}
			
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var wrapWidth=$(".ui-layout-center").width()-425;
			var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
			var tableHeight=wrapHeight-50;
			$(".highcharts-container,.warpTable").height(wrapHeight);
			$(".highcharts-container").width(wrapWidth);
			$("#areaDatatable").jqGridFunction({
				rowNum:100,
				datatype:"local",	
				width:380,
				height:'auto',
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"地区",sortable:false,width:70},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:60},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:60},
			 		{name:'attentionCount',index:'attentionCount',label:"关注人数",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false,width:80}
				],
				gridComplete:function(){
				$("#areaDatatable").find("tbody tr:last td").css('font-weight',700);
				}
			});
			$("#dataMonthTable").jqGridFunction({
				datatype:"local",	
				width:380,
				height:tableHeight,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false,width:70},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:70},
			 		{name:'attentionCount',index:'attentionCount',label:"关注人数",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false,width:80}
				]
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				getBaseInfoSummary();
			}
		});
		function onOrgChanged(){
			$("#areaDatatable").setGridParam({
				url:"${path}/baseinfo/leaderViewManage/statisticsBaseInfo.action",
				datatype:"json",
				page:1,
				loadComplete:function(){
					var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
					var tableHeight=wrapHeight-50;
					var areaContainerChart = $("#areaContainer").columnChart({
						ajax:false,
						table:document.getElementById('gbox_areaDatatable')
					});
					areaContainerChart.series[2].hide();
					areaContainerChart.series[3].hide();
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
				url:"${path}/baseinfo/leaderViewManage/statisticsSummary.action",
				datatype:"json",
				page:1,
				loadComplete:function(){
					var personGeneralConditionLine = $("#containerSummary").lineChart({
						ajax:false,
						table:document.getElementById('gbox_dataMonthTable')
					});
					personGeneralConditionLine.series[1].hide();
					personGeneralConditionLine.series[2].hide();
				}
			});
			$("#dataMonthTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"${populationType}"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
		
		$("#refreshOrgTree1").click(function(){
			$("#searchByCondition").attr("value","");
			$("#searchText").attr("value","");
		});

</script>

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