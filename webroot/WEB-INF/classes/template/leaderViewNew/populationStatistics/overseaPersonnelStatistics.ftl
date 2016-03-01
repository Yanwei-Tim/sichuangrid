<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
    
<script type="text/javascript">
var title = "境外人员";
		jQuery(document).ready(function() {		
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
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"部门",sortable:false,width:70},
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
				autowidth:false,
				shrinkToFit:true,
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
			$("#searchHighA").click(function(){
				$("#overseaPersonnelMaintanceDialog").createDialog({
					width: 650,
					height: 330,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${(path)!}/baseinfo/overseaPersonnel/maintainOverseaPersonnelConditionDlg.jsp',
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
				url: '${(path)!}/baseinfo/overseaPersonnel/overseaPersonnelListForStatistics.jsp?isSearch='+isSearch,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		function onOrgChanged(){
			$("#areaDatatable").setGridParam({
				url:"${(path)!}/baseinfo/leaderViewManage/statisticsBaseInfoAddCountByOrgIdForArea.action",
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
		    	"tableName":"overseaStaff"
		   	});

			$("#areaDatatable").trigger("reloadGrid");
			getBaseInfoSummary();
		}
		
		function getBaseInfoSummary(){
			$("#dataMonthTable").setGridParam({
				url:"${(path)!}/baseinfo/leaderViewManage/statisticsPopulationSummary.action",
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
		    	"tableName":"overseaStaff"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
</script>
<@s.include value="/leaderView/populationStatistics/commonStatistics.ftl">
	<@s.param  name="populationType"><@s.property value="@com.tianque.core.util.BaseInfoTables@OVERSEAPERSONNEL_KEY"/></@s.param>
</@s.include>
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
<div id="overseaPersonnelMaintanceDialog"></div>
<div id="statisticsListDialog"></div>