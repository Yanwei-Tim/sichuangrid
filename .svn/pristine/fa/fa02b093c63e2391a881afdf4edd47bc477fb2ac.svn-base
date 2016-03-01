<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<div class="btnbanner">
	<div class="ui-widget autosearch">
		<input type="text" value="请输入企业名称" class="searchtxt" id="searchByPlaceName" maxlength="18" onfocus="if(this.value == '请输入企业名称') this.value = '';" onblur="if(this.value == '') this.value = '请输入企业名称';">
		<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="searchByIdcardA"><span>查询</span></a>
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

    
<script type="text/javascript">
var title = "规上企业";	
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
				width:370,
				height:tableHeight,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"部门",sortable:false,width:70},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:60},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:60},
			 		{name:'attentionCount',index:'attentionCount',label:"关注场所",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"场所总数",sortable:false,width:80}
				],
				gridComplete:function(){
				$("#areaDatatable").find("tbody tr:last td").css('font-weight',700);
				}
			});
			$("#dataMonthTable").jqGridFunction({
				datatype:"local",	
				width:370,
				height:tableHeight,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false,width:70},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:70},
			 		{name:'attentionCount',index:'attentionCount',label:"关注场所",sortable:false,width:80},
			 		{name:'allCount',index:'allCount',label:"场所总数",sortable:false,width:80}
				]
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			$("#searchHighA").click(function(){
				$("#statisticsDialog").createDialog({
					width: 700,
					height: 450,
					title: title+'查询-请输入查询条件',
					modal:true,
					url: '${path}/baseinfo/siteinfo/enterprise/searchEnterpriseCondition.jsp?keyType='+"enterpriseKey",
					buttons: {
						"查询" : function(event){
							$("#statisticsListDialog").createDialog(getDialogParam());
					   },
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			});

			$("#searchByIdcardA").click(function(){
				if($("#searchByPlaceName").val()=="请输入企业名称"){
					$.notice({
						level: 'alert',
					    okbtnName: "确定",
					    title:'提示',
					    message: "请输入企业名称"
					});
					return;
				};
				$("#statisticsListDialog").createDialog(getDialogParam());
			});
			$("#refreshOrgTree1").click(function(){
				$("#searchByPlaceName").attr("value","");
				$("#searchByPlaceName").attr("value","请输入企业名称");
			});
		});
		function getDialogParam(){
			return {
				width: 900,
				height: 600,
				title: title+'信息',
				modal:true,
				url: '${path}/baseinfo/siteinfo/enterprise/enterpriseListForStatistics.jsp?isSearch=1&keyType='+"enterpriseKey",
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
					var areaContainerChart = $("#areaContainer").columnChart({
						ajax:false,
						table:document.getElementById('gbox_areaDatatable')
					});
					areaContainerChart.series[2].hide();
					areaContainerChart.series[3].hide();
				}
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableName":"enterpriseKey"
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
		    	"tableName":"enterpriseKey"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
</script>
<style>

</style>
		