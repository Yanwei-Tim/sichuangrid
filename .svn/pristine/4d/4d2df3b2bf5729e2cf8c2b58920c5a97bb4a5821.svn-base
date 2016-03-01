<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
    
<script type="text/javascript">
var title = "境外人员";
		jQuery(document).ready(function() {		
			/* $(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			}); */
			var wrapWidth=$(".ui-layout-center").width()-425;
			var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2-10;
			var tabWidth = $(".ui-layout-center").width() - 400;
			var tableHeight=wrapHeight-50;
			$(".highcharts-container,.warpTable").height(wrapHeight);
			$(".highcharts-container").width(wrapWidth);
			$("#areaDatatable").jqGridFunction({
				rowNum:100,
				datatype:"local",	
				width:360,
				height:tableHeight,
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"地区",sortable:false,width:70},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:60},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:60},
			 		{name:'attentionCount',index:'attentionCount',label:"人员总数",sortable:false,width:80}
// 			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false,width:80}
				]
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
			 		{name:'attentionCount',index:'attentionCount',label:"人员总数",sortable:false,width:80}
// 			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false,width:80}
				]
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				getBaseInfoSummary();
			}
			$("#searchHighA").click(function(){
				$("#overseaPersonnelMaintanceDialog").createDialog({
					width: 650,
					height: 330,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${path}/baseinfo/overseaPersonnel/maintainOverseaPersonnelConditionDlg.jsp',
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
				
				if($("#searchByC").val()=="请输入护照号码"){
					$.notice({
						level: 'alert',
					    okbtnName: "确定",
					    title:'提示',
					    message: "请输入护照号码"
					});
					return;
				};
				$("#statisticsListDialog").createDialog(getDialogParam(0));
			});
			$("#refreshOrgTree1").click(function(){
				$("#searchByC").attr("value","");
				$("#searchByC").attr("value","请输入护照号码");
			});
		});
		function getDialogParam(isSearch){
			return {
				width: 900,
				height: 600,
				title: title+'信息',
				modal:true,
				url: '${path}/baseinfo/overseaPersonnel/overseaPersonnelListForStatistics.jsp?isSearch='+isSearch,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		function onOrgChanged(){
			$("#areaDatatable").setGridParam({
				url:"${path}/baseinfo/leaderViewManage/statisticsBaseInfoAddCountByOrgIdForArea.action?isGrid=true",
				datatype:"json",
				page:1
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"overseaStaff"
		   	});

			$("#areaDatatable").trigger("reloadGrid");
			getBaseInfoSummary();
		}
		
		function getBaseInfoSummary(){
			$("#dataMonthTable").setGridParam({
				url:"${path}/baseinfo/leaderViewManage/statisticsPopulationSummary.action",
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
		    	"tableName":"overseaStaff"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
</script>
<s:include value="/baseinfo/leaderView/populationStatistics/gridCommonStatistics.jsp">
	<s:param  name="populationType"><s:property value="@com.tianque.core.util.BaseInfoTables@OVERSEAPERSONNEL_KEY"/></s:param>
</s:include>
<div class="leaderTit">各月份图表</div>
<div class="leaderCon">
	<div id="containerSummary" class="highcharts-container_1">
	</div>
	<div class="clear"></div>
</div>
<div class="leaderTit_1"></div>

<div class="leaderCon leaderCon_1">
	<div class="warpTable_1 ">
		<table id="dataMonthTable"></table>
	</div>
	<div class="warpTable1_1">
		<table id="areaDatatable">
		</table>
	 </div>
</div>
<div id="overseaPersonnelMaintanceDialog"></div>
<div id="statisticsListDialog"></div>