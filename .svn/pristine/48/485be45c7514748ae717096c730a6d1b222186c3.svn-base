<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/common/orgSelectedComponent.jsp"/>
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
	<div id="personGeneralConditionLine" class="highcharts-container">
	</div>
	<div class="warpTable">
		<table id="personMonthGeneralConditionTable">
		</table>
	</div>
	<div class="clear"></div>
</div>
<script type="text/javascript">
	
		jQuery(document).ready(function() {		
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var tableHeight=($(".ui-layout-center").height()-$("#thisCrumbs").height()-190)/2;
			$("#areaDatatable").jqGridFunction({
				datatype:"local",
				rowNum:100,
				width:380,
				height:'auto',
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"部门",sortable:false,width:70},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:60},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:60},
			 		{name:'attentionCount',index:'attentionCount',label:"场所总数",sortable:false,width:80}
			 		<#--{name:'allCount',index:'allCount',label:"场所总数",sortable:false,width:80}-->
				],
				gridComplete:function(){
				$("#areaDatatable").find("tbody tr:last td").css('font-weight',700);
			  }
			});

			$("#personMonthGeneralConditionTable").jqGridFunction({
				datatype:"local",
				width:380,
				height:tableHeight,
				autowidth:false,
				shrinkToFit:true,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false,width:70},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:70},
			 		{name:'attentionCount',index:'attentionCount',label:"场所总数",sortable:false,width:80}
			 		<#--{name:'allCount',index:'allCount',label:"场所总数",sortable:false,width:80}-->
				]
			});
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
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
				url:"${path}/baseinfo/leaderViewManageNew/personGeneralCondition.action",
				datatype:"json",
				page:1,
				loadComplete:function(){
					var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-190)/2;
					var tableHeight=wrapHeight-50;
					var areaContainerChart = $("#areaContainer").columnChart({
						ajax:false,
						table:document.getElementById('gbox_areaDatatable')
					});
					areaContainerChart.series[2].hide();
					<#--areaContainerChart.series[3].hide();-->
					$("#areaDatatable").find("tbody tr.jqgfirstrow").clone().appendTo("#areaDatatableSun");
					$("#areaDatatable").find("tbody tr.ui-row-ltr:last").css("background","#fff").appendTo("#areaDatatableSun");
					$("#areaDatatable").closest('div.ui-jqgrid-bdiv').css("max-height",tableHeight);
				}
			});
			$("#areaDatatable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableType":"IMPORTANTPLACE"
		   	});

			$("#areaDatatable").trigger("reloadGrid");
			getPersonMonthGeneralConditionData();
		}
		function getPersonMonthGeneralConditionData(){
			$("#personMonthGeneralConditionTable").setGridParam({
				url:"${path}/baseinfo/leaderViewManageNew/personMonthGeneralCondition.action",
				datatype:"json",
				page:1,
				loadComplete:function(){
					var personGeneralConditionLine = $("#personGeneralConditionLine").lineChart({
						ajax:false,
						table:document.getElementById('gbox_personMonthGeneralConditionTable'),
						isChangeColor:true
					});
					personGeneralConditionLine.series[1].hide();
					<#--personGeneralConditionLine.series[2].hide();-->
				}
			});
			$("#personMonthGeneralConditionTable").setPostData({
		    	"orgId":getCurrentOrgId(),
		    	"tableType":"IMPORTANTPLACE"
		   	});

			$("#personMonthGeneralConditionTable").trigger("reloadGrid");
		}
</script>



		