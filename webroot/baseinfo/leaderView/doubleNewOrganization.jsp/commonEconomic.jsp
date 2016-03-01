<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("moduleName",request.getParameter("moduleName"));%>
<%request.setAttribute("lowerCaseModuleName",request.getParameter("moduleName").substring(0,1).toLowerCase()+request.getParameter("moduleName").substring(1));%>
<%request.setAttribute("upperCaseModuleName",request.getParameter("moduleName").toUpperCase());%>
<%request.setAttribute("moduleCName",request.getParameter("moduleCName"));%>
 <%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<script type="text/javascript">
if(<%=AnalyseUtilNew.IS_NEWANALYSE_JOB%>){
	var area_data_table_url = "${path}/baseinfo/leaderViewManageNew/statisticsBaseInfo.action";
	var data_month_table_url = "${path}/baseinfo/leaderViewManageNew/statisticsSummary.action";
}else{
	var area_data_table_url = "${path}/baseinfo/leaderViewManage/statisticsBaseInfo.action";
	var data_month_table_url = "${path}/baseinfo/leaderViewManage/statisticsSummary.action";
}
var title = document.title;
		jQuery(document).ready(function() {		
			
			function numberFormber(el, options, rowData){
				return rowData.allCount-rowData.attentionCount;
			}
			
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-100)/2;
			var tableHeight=wrapHeight-50;
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
			 		{name:'attentionCount',index:'attentionCount',label:"未注销组织",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"组织总数",sortable:false,width:80}
				],
				autowidth:false,
				shrinkToFit:true,
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
			 		{name:'attentionCount',index:'attentionCount',label:"未注销组织",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"组织总数",sortable:false,width:80}
				]
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				getBaseInfoSummary();
			}
			
		
		$("#searchByIdcardA").click(function(){

			if("请输入组织名称或营业执照号码"==$("#searchText").val()){
				$.notice({
					level: 'alert',
				    okbtnName: "确定",
				    title:'提示',
				    message: "请输入组织名称或营业执照号码"
				});
				return;
			}
			
			$("#${moduleName}statisticsListDialog").createDialog({
					width: 900,
					height: 600,
					title: title+'信息',
					modal:true,
					url: '/baseinfo/twoNewOrganization/newEconomicOrganizations/newEconomicList.jsp',
					buttons: {
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
			});
			
		});
			
		$("#searchHighA").click(function(){
			$("#${moduleName}statisticsDialog").createDialog({
				width: 650,
				height: 350,
				title: title+'查询-请输入查询条件',
				modal:true,
				url: '${path}/baseinfo/twoNewOrganization/newEconomicOrganizations/searchnewEconomicOrganizationsCondition.jsp',
				buttons: {
					"查询" : function(event){
				    var param = "key=staticDailog";
					$("#${moduleName}statisticsListDialog").createDialog({
							width: 900,
							height: 600,
							title: title+'信息',
							modal:true,
							url: '/baseinfo/twoNewOrganization/newEconomicOrganizations/newEconomicList.jsp?'+param,
							buttons: {
						   		"关闭" : function(){
						        	$(this).dialog("close");
						   		}
							}
					});
					
				   },
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
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
					areaContainerChart.series[2].hide();
					$("#areaDatatable").find("tbody tr.jqgfirstrow").clone().appendTo("#areaDatatableSun");
					$("#areaDatatable").find("tbody tr.ui-row-ltr:last").css("background","#fff").appendTo("#areaDatatableSun");
					$("#areaDatatable").closest('div.ui-jqgrid-bdiv').css("max-height",tableHeight);
				}
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"${lowerCaseModuleName}"
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
						table:document.getElementById('gbox_dataMonthTable')
					});
					personGeneralConditionLine.series[1].hide();
					personGeneralConditionLine.series[2].hide();
				}
			});
			$("#dataMonthTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"${lowerCaseModuleName}"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
		
		$("#refreshSearchKey").click(function(){$("#searchText").attr("value","请输入组织名称或营业执照号码");});

</script>
<div class="btnbanner btnbannerData" style="text-align: center;">
	<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<!-- <div class="ui-widget autosearch">
		<input type="text" value="请输入组织名称或营业执照号码" name="searchText" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入组织名称或营业执照号码':this.value;" onfocus="value=(this.value=='请输入组织名称或营业执照号码')?'':this.value;">
		<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="searchByIdcardA"><span>搜索</span></a>
		<a href="javascript:;" id="searchHighA"><span>高级搜索</span></a>
	</div> -->
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
<div id="${moduleName}statisticsDialog"></div>
<div id="${moduleName}statisticsListDialog"></div>