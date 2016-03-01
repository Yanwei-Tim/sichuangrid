<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>

<script type="text/javascript">
<@pop.formatterProperty name="attentionExtent" domainName="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" />
function attentionExtentColor(el,options,rowData){
	var displayName=attentionExtentFormatter(el,options,rowData);
	if(displayName=='undefined'||displayName=='')
		return '';
	else if(displayName=='严重')
		return '<span>严重：<span style="color:#ff0000;">█████</span></span>';
	else if(displayName=='中等')
		return '<span>中等：<span style="color:#ffcc00;">███</span></span>';
	else if(displayName=='一般')
		return '<span>一般：<span style="color:#99cc00;">█</span></span>';
	else
		return '';
}
	var title = document.title;
	function onOrgChanged(){
		$("#areaDatatable").setGridParam({
			url:"${(path)!}/baseinfo/leaderViewManage/statisticsBaseInfo.action",
			datatype:"json",
			page:1,
			loadComplete:function(){
				var wrapHeight=($(".ui-layout-center").height()-$(".btnbanner").height()-160)/2;
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
	    	"tableName":"${RequestParameters.moduleName}"
	   	});
	
		$("#areaDatatable").trigger("reloadGrid");
		getBaseInfoSummary();
	}
	
	function getBaseInfoSummary(){
		$("#dataMonthTable").setGridParam({
			url:"${(path)!}/baseinfo/leaderViewManage/statisticsSummary.action",
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
	    	"tableName":"${RequestParameters.moduleName}"
	   	});
		$("#dataMonthTable").trigger("reloadGrid");
	}
	
	$("#refreshOrgTree1").click(function(){
		$("#searchByCondition").attr("value","请输入姓名或身份证号码");
		$("#searchText").attr("value","请输入姓名或身份证号码");
	});
	$(function(){
		var tableHeight=($(".ui-layout-center").height()-$("#thisCrumbs").height()-190)/2;
		function numberFormber(el, options, rowData){
			return rowData.allCount-rowData.attentionCount;
		}
		
		$(".leaderTit").each(function(){
			$(this).text(document.title+$(this).text());
		});
		
		$("#areaDatatable").jqGridFunction({
			rowNum:100,
			datatype:"local",	
			width:380,
			height:'auto',
			colModel:[
		  		{name:'id',index:'id',hidden:true},
		  		{name:'statisticsType',index:'statisticsType',label:"部门",sortable:false,width:70},
		 		{name:'todayAddCount',index:'todayAddCount',label:"今天新增",sortable:false,width:60},
		 		{name:'thisMonthCount',index:'thisMonthCount',label:"本月新增",sortable:false,width:60},
		 		{name:'attentionCount',index:'attentionCount',label:"关注人数",sortable:false,width:80},
		 		{name:'allCount',index:'allCount',label:"人员总数",sortable:false,width:80}
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
		$("#searchByIdcardA").click(function(){
			$("#${RequestParameters.moduleName}statisticsListDialog").createDialog(getDialogParam("fast"));
		});
			
		$("#searchHighA").click(function(){
			$("#${RequestParameters.moduleName}Dialog").createDialog({
				width: 650,
				height: 330,
				title: title+'查询-请输入查询条件',
				modal:true,
				url: '${(path)!}/baseinfo/${RequestParameters.moduleName}Manage/dispatchOperate.action?mode=search&organizationId='+getCurrentOrgId(),
				buttons: {
					"查询" : function(event){
						$("#${RequestParameters.moduleName}statisticsListDialog").createDialog(getDialogParam("advanced"));
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
				url: '${(path)!}/baseinfo/${RequestParameters.moduleName}Manage/dispatchOperate.action?mode=statistic&searchType='+type,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			};
		}
		var layout=function(){
			var wrapWidth=$(".ui-layout-center").width()-400;
			var wrapHeight=($(".ui-layout-center").height()-$("#thisCrumbs").height()-160)/2;
			var tableHeight=wrapHeight-50;
			$(".highcharts-container,.warpTable").height(wrapHeight);
			$(".highcharts-container").width(wrapWidth);
		}
		layout();
		$(window).resize(function(){
			layout();
		})
	});
</script>
<@s.include value="/baseinfo/leaderView/searchButton.jsp"></@s.include>


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
<div id="${RequestParameters.moduleName}Dialog"></div>
<div id="${RequestParameters.moduleName}statisticsListDialog"></div>