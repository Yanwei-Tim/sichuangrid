<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setAttribute("moduleName",request.getParameter("moduleName"));%>
<%request.setAttribute("lowerCaseModuleName",request.getParameter("moduleName").substring(0,1).toLowerCase()+request.getParameter("moduleName").substring(1));%>
<%request.setAttribute("upperCaseModuleName",request.getParameter("moduleName").toUpperCase());%>
<%request.setAttribute("moduleCName",request.getParameter("moduleCName"));%>
    
<script type="text/javascript">
var title = document.title;
		jQuery(document).ready(function() {		
// 			function numberFormber(el, options, rowData){
// 				return rowData.allCount-rowData.attentionCount;
// 			}
			
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var wrapWidth=$(".ui-layout-center").width()-425;
			var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2-10;
			var tabWidth = $(".ui-layout-center").width() - 400;
			var tableHeight=wrapHeight-50;
			$(".highcharts-container,.warpTable").height(wrapHeight);
			$(".highcharts-container").width(wrapWidth+425);
			$("#areaDatatable1").jqGridFunction({
				rowNum:100,
				datatype:"local",	
				width:360,
				height:tableHeight,	
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"地区",sortable:false,width:70},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:80},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:80},
			 		{name:'attentionCount',index:'attentionCount',label:"房屋总数",sortable:false,width:80}
// 			 		{name:'allCount',index:'allCount',label:"房屋总数",sortable:false,width:80}
				],
// 				gridComplete:function(){
// 				$("#areaDatatable1").find("tbody tr:last td").hide();
// 				}
			});
			$("#dataMonthTable").jqGridFunction({
				datatype:"local",	
				width:tabWidth,
				height:tableHeight,
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false,width:70},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:70},
			 		{name:'attentionCount',index:'attentionCount',label:"房屋总数",sortable:false,width:80}
// 			 		{name:'allCount',index:'allCount',label:"房屋总数",sortable:false,width:80}
				]
			});	
			

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				getBaseInfoSummary();
			}
			
		
		$("#searchByIdcardA").click(function(){
			$("#${moduleName}statisticsListDialog").createDialog(getDialogParam("fast"));
		});
			
		$("#searchHighA").click(function(){
			$("#${lowerCaseModuleName}Dialog").createDialog({
				width: 650,
				height: 420,
				title: title+'查询-请输入查询条件',
				modal:true,
				url: '${path}/baseinfo/${lowerCaseModuleName}Manage/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
				buttons: {
					"查询" : function(event){
						$("#${moduleName}statisticsListDialog").createDialog(getDialogParam("advanced"));
				   },
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		function getDialogParam(type){
			return {
				width: 900,
				height: 600,
				title: title+'信息',
				modal:true,
				url: '${path}/baseinfo/${lowerCaseModuleName}Manage/dispatchOperate.action?mode=statistic&searchType='+type,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		});
		function onOrgChanged(){
			$("#areaDatatable1").setGridParam({
				url:"${path}/baseinfo/leaderViewManage/statisticsBaseInfo.action?isGrid=true",
				datatype:"json",
				page:1
			});
			$("#areaDatatable1").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"${lowerCaseModuleName}"
		   	});

			$("#areaDatatable1").trigger("reloadGrid");
			getBaseInfoSummary();
		}
		
		function getBaseInfoSummary(){//线形图
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
// 					personGeneralConditionLine.series[2].hide();
				}
			});
			$("#dataMonthTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"${lowerCaseModuleName}"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
		
		$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","请输入房屋编号或房屋地址");});

</script>
<div class="btnbanner btnbannerData" style="text-align: center;">
	<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<!-- <div class="ui-widget autosearch">
		<input type="text" value="请输入房屋编号或房屋地址" name="searchText" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入房屋编号或房屋地址':this.value;" onfocus="value=(this.value=='请输入房屋编号或房屋地址')?'':this.value;">
		<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="searchByIdcardA"><span>搜索</span></a>
		<a href="javascript:;" id="searchHighA"><span>高级搜索</span></a>
	</div> -->
</div>

<div class="leaderTit">各月份图表</div>
<div class="leaderCon">
	<div id="containerSummary" class="highcharts-container highcharts-container_1">
	</div>
	<div class="clear"></div>
</div>

<div class="leaderTit_1"></div>
<div class="leaderCon leaderCon_1">
	<div class="warpTable_1 ">
		<table id="dataMonthTable"></table>
	</div>
	<div class="warpTable1_1">
		<table id="areaDatatable1">
		</table>
	 </div>
</div>
<div id="${lowerCaseModuleName}Dialog"></div>
<div id="${moduleName}statisticsListDialog"></div>