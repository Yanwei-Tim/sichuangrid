<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="dailyWorkReportPrint"  style="overflow: auto;height:100%;width:100%">
   <style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 24px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:1200px; !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:1200px;  
			border-collapse:collapse;
			overflow-y: hidden;
		}
		.SigmaReportPrint td{
			font-weight:normal;
			color:#333;
		}
		.SigmaReportPrint .head{
		    width:595px;
			margin:0 5px;
			border-left:1px solid #ccc;
		}
		
		.SigmaReportPrint .body{
			margin:0 5px;
			border-left:1px solid #ccc;
		}
		
		.SigmaReportPrint .body tr.selected{
			background-color:#CCE4F9;
		}
		.SigmaReportPrint .body tr.disabled{
			background:#F0EDED;
			color:#CECECE;
		}
		
		.SigmaReportPrint .body tr.hover{
			background-color:rgb(255,255,151);
		}
		
		.SigmaReportPrint .scroll{
		}
		
		.SigmaReportPrint .body td{
			border-right:1px solid #ccc;
			border-bottom:1px solid #ccc;
			font-size:12px;
			height:20px;
			padding:0px;
			text-align:center;
			color:#333;
		}
		
		.SigmaReportPrint .body input{
			font-size:12px;
			border:0px solid red;
		}
		.SigmaReportPrint  input{
			font-size:12px;
			border:0px solid red;
		}
		
		.SigmaReportPrint .body div.focused{
			background-color:rgb(255,250,255);
		}
		
		.SigmaReportPrint .body div{
			white-space:nowrap;
			padding:3px;
			display:block;
			text-align:center;
		}
		.SigmaReportPrint .body div.checked{
			width:16px;
			height:16px;
			border:1px solid red;
			background-image:url(right.gif);
			background-repeat:no-repeat;
		}
		
		.SigmaReportPrint .head td{
			background:#e7edf5;
			border-right:1px solid #ccc;
			border-bottom:1px solid #ccc;
			font-size:12px;
			height:28px;
			line-height:28px;(
			overflow:hidden;
			padding-top:2px;
		}
		
		
		.SigmaReportPrint .head div.title{
			padding-top:2px;
			float:left;
			height:18px;
			overflow:hidden;
			white-space:nowrap;
		}
	</style>
   <div id="dailyWorkGridbox" class="SigmaReportPrint" style="overflow: auto;height:100%;width:100%"></div>
</div>
<script>
var dailyWorkGrid = null;
$(document).ready(function(){
	
		var context = {};
		var columns = [
				{name:"organization.orgName",caption:"市州",width:70,mode:"string"}, 
				{name:"general",caption:"职能部门参与(0.1分)",width:40,mode:"string"}, 
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
		dailyWorkGrid = new SigmaReport("dailyWorkGridbox",context,columns, "SigmaReportPrint","printTable_title");
		
		$("#title_dailyWorkGridbox").children().remove();
		$("#title_dailyWorkGridbox").html("网格化服务管理工作日常考核表");
		
		dailyWorkGridboxPrint();
		$("#dailyWorkGridbox").css({"overflow-y": "auto"});
	});
function dailyWorkGridboxPrint(){
	$.ajax({
		url:'${path}/baseInfoStat/DailyWorkReportManage/getDailyWorkReportForList.action',
		success:function(data){
			dailyWorkGrid.bindData(data);
		}
	})
}
</script>