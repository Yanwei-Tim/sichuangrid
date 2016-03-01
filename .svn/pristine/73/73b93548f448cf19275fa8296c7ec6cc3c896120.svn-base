<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
    
<script type="text/javascript">
if(<%=AnalyseUtilNew.IS_NEWANALYSE_JOB%>){
	var area_data_table_url = "${path}/baseinfo/leaderViewManageNew/statisticsBaseInfoAddCountByOrgIdForArea.action";
	var data_month_table_url = "${path}/baseinfo/leaderViewManageNew/statisticsSummary.action";
}else{
	var area_data_table_url = "${path}/baseinfo/leaderViewManage/statisticsBaseInfoAddCountByOrgIdForArea.action";
	var data_month_table_url = "${path}/baseinfo/leaderViewManage/statisticsSummary.action";
}
var title = "楼宇";
		$(function() {		
			var tableHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
			
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
			 		{name:'allCount',index:'allCount',label:"楼宇总数",sortable:false,width:80}
			 		//{name:'attentionCount',index:'attentionCount',label:"关注无户籍证明人员数",sortable:false,width:120},
			 		//{name:'allCount',index:'allCount',label:"无户籍证明人员总数",sortable:false,width:120}
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
			 		{name:'allCount',index:'allCount',label:"楼宇总数",sortable:false,width:80}
			 		//{name:'attentionCount',index:'attentionCount',label:"关注无户籍证明人员数",sortable:false,width:120},
			 		//{name:'allCount',index:'allCount',label:"无户籍证明人员总数",sortable:false,width:120}
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
					height: 200,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${path}/baseinfo/buildDatas/builddatasSearchDlg.jsp?orgId='+getCurrentOrgId(),
					buttons: {
						"查询" : function(event){
							$("#statisticsListDialog").createDialog(getDialogParam());
							setTimeout("searchBuilddatas()",500);
							
					   },
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			});

			$("#searchByNameA").click(function(){
				$("#statisticsListDialog").createDialog(getDialogParam());
				setTimeout("searchBuilddatasByName()",500);
			});
			$("#refreshOrgTree1").click(function(){
				$("#searchByName").attr("value","");
				$("#searchByName").attr("value","请输入楼宇名称");
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
			});
		});
		
		function searchBuilddatasByName(){
			$("#builddatasList").setGridParam({
				url : '${path}/builddatasManage/searchBuilddatas.action',
				datatype : "json",
				page : 1
			});
			var condition="";
			if($("#searchByName").val()!="请输入楼宇名称"){
				condition=$("#searchByName").val();
			}
			var param={"builddatasSearchVo.buildingname":condition,"builddatasSearchVo.organization.id":getCurrentOrgId(),"organization.id":getCurrentOrgId()};
			$("#builddatasList").setPostData(param);
			$("#builddatasList").trigger("reloadGrid");
		}
		
		function searchBuilddatas() {
			var formdata = $("#maintainForm").serialize();
			if (formdata != '') {
				formdata = formdata.replace(/=/g, '":"');
				formdata = formdata.replace(/&/g, '","');
				formdata += '"';
			}
			formdata = decodeURIComponent('{"' + formdata + '}');

			$("#builddatasList").setGridParam({
				url : '${path}/builddatasManage/searchBuilddatas.action',
				datatype : "json",
				page : 1
			});
			$("#builddatasList").setPostData(parseObj(formdata));
			$("#builddatasList").trigger("reloadGrid");
		}
		
		function parseObj(strData) {
			return (new Function("return " + strData))();
		}
		
		function getDialogParam(){
			return {
				width: 900,
				height: 500,
				title: title+'信息',
				modal:true,
				url: '${path}/baseinfo/leaderView/house/searchBuilddataResult.jsp',
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
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
					$("#areaDatatable").find("tbody tr.jqgfirstrow").clone().appendTo("#areaDatatableSun");
					$("#areaDatatable").find("tbody tr.ui-row-ltr:last").css("background","#fff").appendTo("#areaDatatableSun");
					$("#areaDatatable").closest('div.ui-jqgrid-bdiv').css("max-height",tableHeight);
				}
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"builddatas"
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
					var buildDataSummary = $("#containerSummary").lineChart({
						ajax:false,
						table:document.getElementById('gbox_dataMonthTable')
					});
					buildDataSummary.series[1].hide();
				}
			});
			$("#dataMonthTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"builddata"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
</script>
<div class="btnbanner">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<!-- <div class="ui-widget autosearch">
		<input type="text" value="请输入楼宇名称" id="searchByName" maxlength="20" style="width:215px;" class="searchtxt" onfocus="if(this.value == '请输入楼宇名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入楼宇名称';">
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="searchByNameA"><span><strong class="search"></strong>搜索</span></a>
		<a href="javascript:;" id="searchHighA"><span><strong class="advancedsearch"></strong>高级搜索</span></a>
	</div> -->
</div>
<div class="leaderTit">楼宇信息概况</div>
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
<div class="leaderTit">楼宇信息各月份图表</div>
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