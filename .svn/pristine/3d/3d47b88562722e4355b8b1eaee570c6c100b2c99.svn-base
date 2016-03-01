<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<script type="text/javascript">
var title = "网吧";	
		jQuery(document).ready(function() {		
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
			var tableHeight=wrapHeight-50;
			$("#areaDatatable").jqGridFunction({
				rowNum:100,
				datatype:"local",	
				width:380,
				height:'auto',
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"地区",sortable:false,width:70},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:60},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:60},
			 		{name:'attentionCount',index:'attentionCount',label:"未注销场所",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"场所总数",sortable:false,width:80}
				],
				gridComplete:function(){
				$("#areaDatatable").find("tbody tr:last td").css('font-weight',700);
				}
			});
			$("#dataMonthTable").jqGridFunction({
				datatype:"local",	
				width:380,
				height:tableHeight,
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false,width:70},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:70},
			 		{name:'attentionCount',index:'attentionCount',label:"未注销场所",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"场所总数",sortable:false,width:80}
				]
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			$("#searchHighA").click(function(){
				$("#internetBarDialog").createDialog({
					width: 650,
					height: 320,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${path}/baseinfo/internetBarManage/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
					buttons: {
						"查询" : function(event){
							$("#statisticsListDialog").createDialog(getDialogParam(1));
					   },
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			});

			$("#searchByIdcardA").click(function(){
				if("请输入网吧名称"==$("#searchByPlaceName").val()){
					$.notice({
						level: 'alert',
					    okbtnName: "确定",
					    title:'提示',
					    message: "请输入网吧名称"
					});
					return;
				}
				$("#statisticsListDialog").createDialog(getDialogParam(0));
			});
			$("#refreshOrgTree1").click(function(){
				$("#searchByPlaceName").attr("value","");
				$("#searchByPlaceName").attr("value","请输入网吧名称");
			});
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
		function getDialogParam(isSearch){
			return {
				width: 900,
				height: 600,
				title: title+'信息',
				modal:true,
				url: '${path}/baseinfo/siteinfo/internetBar/internetBarListForStat.jsp?isSearch='+isSearch,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		function onOrgChanged(){
			$("#areaDatatable").setGridParam({
				url:"${path}/baseinfo/leaderViewManage/statisticsBaseInfoAddCountByOrgIdForArea.action",
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
					areaContainerChart.series[2].hide();
					$("#areaDatatable").find("tbody tr.jqgfirstrow").clone().appendTo("#areaDatatableSun");
					$("#areaDatatable").find("tbody tr.ui-row-ltr:last").css("background","#fff").appendTo("#areaDatatableSun");
					$("#areaDatatable").closest('div.ui-jqgrid-bdiv').css("max-height",tableHeight);
				}
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"internetBar"
		   	});

			$("#areaDatatable").trigger("reloadGrid");
			getBaseInfoSummary();
		}
		
		function getBaseInfoSummary(){
			$("#dataMonthTable").setGridParam({
				url:"${path}/baseinfo/leaderViewManage/statisticsBaseInfoSummary.action",
				datatype:"json",
				page:1,
				loadComplete:function(){
					var areaContainerChart = $("#containerSummary").lineChart({
						ajax:false,
						table:document.getElementById('gbox_dataMonthTable')
					});
					areaContainerChart.series[1].hide();
					areaContainerChart.series[2].hide();
				}
			});
			$("#dataMonthTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"internetBar"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
</script>
<style>

</style>
<div class="btnbanner">
	<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<div class="ui-widget autosearch">
		<input type="text" value="请输入网吧名称" class="searchtxt" id="searchByPlaceName" style="width:215px;" maxlength="18" onfocus="if(this.value == '请输入网吧名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入网吧名称';">
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="searchByIdcardA"><span>搜索</span></a>
		<a href="javascript:;" id="searchHighA"><span>高级搜索</span></a>
	</div>
</div>

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

<div id="internetBarDialog"></div>
<div id="statisticsListDialog"></div>

		