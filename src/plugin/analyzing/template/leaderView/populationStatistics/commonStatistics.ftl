<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
 
<script type="text/javascript">
	var title = document.title;
		jQuery(document).ready(function() {		
			
			function numberFormber(el, options, rowData){
				return rowData.allCount-rowData.attentionCount;
			}
			
			$(".leaderTit").each(function(){
				$(this).text(document.title+$(this).text());
			});
			var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-130)/2;
			var tableHeight=wrapHeight-50;
			$("#areaDatatable").jqGridFunction({
				rowNum:100,
				datatype:"local",	
				width:375,
				height:'auto',
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"部门",sortable:false},
			 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false},
			 		{name:'attentionCount',index:'attentionCount',label:"未注销人数",sortable:false},
			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false}
				],
				autowidth:false,
				shrinkToFit:true,
				gridComplete:function(){
					$("#areaDatatable").find("tbody tr:last td").css('font-weight',700);
				}
			});
			$("#dataMonthTable").jqGridFunction({
				datatype:"local",	
				width:375,
				height:tableHeight,
				colModel:[
			  		{name:'id',index:'id',hidden:true},
			  		{name:'statisticsType',index:'statisticsType',label:"月份",sortable:false},
			 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false},
			 		{name:'attentionCount',index:'attentionCount',label:"未注销人数",sortable:false},
			 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false}
				],
				autowidth:false,
				shrinkToFit:true
			});	

			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				onOrgChanged();
			}
			
			if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
				getBaseInfoSummary();
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
		    	"tableName":"${RequestParameters.populationType}"
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
		    	"tableName":"${RequestParameters.populationType}"
		   	});
			$("#dataMonthTable").trigger("reloadGrid");
		}
		$("#refreshOrgTree1").click(function(){
			$("#searchByCondition").attr("value","请输入姓名或身份证号码");
			$("#searchText").attr("value","请输入姓名或身份证号码");
		});

</script>
<@s.include value="${(path)!}/leaderView/searchButton.ftl"></@s.include>
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
<div id="${RequestParameters.populationType}statisticsDialog"></div>
<div id="statisticsListDialog"></div>