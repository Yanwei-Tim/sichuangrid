<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<style type="text/css">
#load {position:relative;}
#load .ui-button{width:80px;height:25px;}
#load .ui-dialog-buttonset{position:absolute;bottom:20px;right:15px;}
</style>
<div class="container_24">
	<div style="clear: both;"></div>
	<div id="gridbox" class="SigmaReport" style="overflow-x:hidden;overflow-y:auto !important;position:relative;height: 440px;width:99%"></div>
</div>
<div id="MainDialog"></div>
<div id="dailyWorkPrintDialog"></div>
<script type="text/javascript">
$(document).ready(function(){
		reloadReportDate();
});

$(".print").click(function(){
	printDailyWork();
});

function reloadReportDate(){
	$.ajax({
		async: false,
		url:'${path}/baseInfoStat/DailyWorkReportManage/getDailyWorkReportForList.action',
		data:{
		},
		success:function(responseData){
			rebuildeGrid(responseData);
		}
	});
}

function rebuildeGrid(reportData){
   	var context = {};
   	var columns = [		
				{name:"organization.orgName",caption:"市州",width:70,mode:"string"}, 
				{name:"general",caption:"职能部门参与(0.1分)",width:60,mode:"string"}, 
				{name:"general",caption:"乡镇(街道)覆盖率(0.1分)",width:80,mode:"string"}, 
				{name:"general",caption:"社区覆盖率(0.1分)",width:50,mode:"string"}, 
				{name:"general",caption:"村覆盖率(0.1分)",width:50,mode:"string"}, 
				{name:"general",caption:"街道月活跃度(0.1分)",width:70,mode:"string"}, 
				{name:"general",caption:"社区(村)月活跃度(0.2分)",width:80,mode:"string"}, 
				{name:"general",caption:"流动/特殊人群信息录入(0.25分)",width:100,mode:"string"}, 
				{name:"general",caption:"特殊人群走访量(0.3分)",width:75,mode:"string"}, 
				{name:"general",caption:"监管中心事件办理(0.1分)",width:80,mode:"string"}, 
				{name:"general",caption:"社情民意收集(0.1分)",width:70,mode:"string"}, 
				{name:"general",caption:"事件办理总量(0.2分)",width:70,mode:"string"}
   		];
	grid = new SigmaReport("gridbox",context,columns);
	$("#title_gridbox").children().remove();
	$("#title_gridbox").html("网格化服务管理工作日常考核表<a href='javascript:;' class='print' title='打印'></a>");
	grid.bindData(reportData);
}

function printDailyWork(){
	$("#dailyWorkPrintDialog").createDialog({
			width:1250,
			height:480,
			title:document.title,
			url:'${path}/statAnalyse/evaluation/dailyWorkReportPrint.ftl',
			buttons:{
			   "打印":function(){
				print();
		  	   },
			   "关闭":function(){
			        $("#dailyWorkPrintDialog").dialog("close");
			   }
			}
	});
}

function print(){
	$("#dailyWorkReportPrint").printArea();
	$("#dailyWorkPrintDialog").dialog("close");
}

</script>