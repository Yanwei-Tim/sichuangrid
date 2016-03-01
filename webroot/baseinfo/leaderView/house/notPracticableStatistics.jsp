<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<script type="text/javascript">
var title = "无户籍证明人员";
var flog=1;//flog=1 多条件查询  flog=2 一个条件查询  
		jQuery(document).ready(function() {		
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var wrapWidth=$(".ui-layout-center").width()-425;
			$(".content").height($(".ui-layout-center").height()-60).css("overflow","auto");
			var wrapHeight=($(".ui-layout-center .content").height()-$(".btnbanner").height()-130)/2;
			var tableHeight=wrapHeight-50;
			$(".highcharts-container,.warpTable").height(wrapHeight);
			$(".highcharts-container").width(wrapWidth);
			$("#areaDatatable").jqGridFunction({
				rowNum:100,
				datatype:"local",	
				width:380,
				height:tableHeight,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"地区",sortable:false,width:70},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:60},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:60},
			 		//{name:'attentionCount',index:'attentionCount',label:"关注无户籍证明人员数",sortable:false,width:120},
			 		{name:'allCount',index:'allCount',label:"无户籍证明人员总数",sortable:false,width:120}
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
			 		//{name:'attentionCount',index:'attentionCount',label:"关注无户籍证明人员数",sortable:false,width:120},
			 		{name:'allCount',index:'allCount',label:"无户籍证明人员总数",sortable:false,width:120}
				]
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				getBaseInfoSummary();
			}
			$("#searchHighA").click(function(){
				$("#statisticsDialog").createDialog({
					width: 750,
					height: 450,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${path}/baseinfo/demographicInformation/notPracticable/searchNotpracticableDlg.jsp?orgIdForStat='+getCurrentOrgId(),
					buttons: {
						"查询" : function(event){
							flog=1;
							$("#statisticsListDialog").createDialog(getDialogParam());
							
					   },
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			});

			$("#searchByNameA").click(function(){
				if($("#searchByName").val()=="请输入姓名"){
					$.notice({
						level: 'alert',
					    okbtnName: "确定",
					    title:'提示',
					    message: "请输入姓名"
					});
					return;
				};
				flog = 2;
				$("#statisticsListDialog").createDialog(getDialogParam());
			});
			$("#refreshOrgTree1").click(function(){
				$("#searchByName").attr("value","");
				$("#searchByName").attr("value","请输入姓名");
			});
		});
		function getDialogParam(){
			return {
				width: 900,
				height: 500,
				title: title+'信息',
				modal:true,
				url: '${path}/baseinfo/demographicInformation/notPracticable/notPracticableListForStatistics.jsp?isSearch='+flog,
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
					//areaContainerChart.series[2].hide();
					//areaContainerChart.series[3].hide();
				}
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"notPracticables"
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
					var personGeneralConditionLine = $("#containerSummary").lineChart({
						ajax:false,
						table:document.getElementById('gbox_dataMonthTable')
					});
					//personGeneralConditionLine.series[1].hide();
					//personGeneralConditionLine.series[2].hide();
				}
			});
			$("#dataMonthTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"notPracticables"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
</script>
<div class="btnbanner">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<div class="ui-widget autosearch">
		<input type="text" value="请输入姓名" id="searchByName" maxlength="20" style="width:215px;" class="searchtxt" onfocus="if(this.value == '请输入姓名') this.value = '';" onblur="if(this.value == '') this.value = '请输入姓名';">
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="searchByNameA"><span><strong class="search"></strong>搜索</span></a>
		<a href="javascript:;" id="searchHighA"><span><strong class="advancedsearch"></strong>高级搜索</span></a>
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
<div id="statisticsDialog"></div>
<div id="statisticsListDialog"></div>