<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="userActivateReportPrint"  style="overflow: auto;height:100%;width:100%">
   <style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 24px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:900px; !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:900px;  
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
   <div id="userActivateReportGridbox" class="SigmaReportPrint" style="overflow: auto;height:100%;width:100%"></div>
</div>
<script>
var userActivateReportGrid = null;
$(document).ready(function(){
	
		var context = {};
		var columns = [
			{name:"organization.orgName",caption:"区域",width:40,mode:"string"},
			{name:"general",caption:"城乡总量",width:60,children:[
				{name:"general",caption:"街道（乡镇）",width:40,children:[
				{name:"townCount",caption:"数量",width:20,mode:"string",format:"#"}
				]},
				{name:"general",caption:"社区",children:[
				{name:"communityCount",caption:"数量",width:40,mode:"string",format:"#"}
				]},
				{name:"general",caption:"村",children:[
				{name:"villageCount",caption:"数量",width:40,mode:"string",format:"#"}
				]}
			]},
			{name:"general",caption:"已开展工作情况",children:[
				{name:"general",caption:"街道（乡镇）",children:[
				{name:"townActivateCount",caption:"数量",width:60,mode:"string",format:"#"},
				{name:"townAccountPercentage",caption:"百分比",width:60,mode:"string",format:"#"},
				{name:"townMonthCoverageRate",caption:"月活跃度",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"社区",children:[
				{name:"communityActivateCount",caption:"数量",width:60,mode:"string",format:"#"},
				{name:"communityAccountPercentage",caption:"百分比",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"村",children:[
				{name:"villageActivateCount",caption:"数量",width:60,mode:"string",format:"#"},
				{name:"villageAcountPercentage",caption:"百分比",width:60,mode:"string",format:"#"}
				]},
				{name:"general",caption:"社区（村）活跃度",children:[
				{name:"communityWeekCoverageRate",caption:"周活跃度",width:60,mode:"string",format:"#"},
				{name:"communityMonthCoverageRate",caption:"月活跃度",width:60,mode:"string",format:"#"}
				]}
			]}
			<#--,
			{name:"agencyOfOpinionCount",caption:"本月社情民意收集数量",width:40,mode:"string",format:"#"},
			{name:"issueDealCount",caption:"本月事件处理总量",width:40,mode:"string",format:"#"}-->
		];
		userActivateReportGrid = new SigmaReport("userActivateReportGridbox",context,columns, "SigmaReportPrint","printTable_title");
		$("#title_userActivateReportGridbox").children().remove();
		$("#title_userActivateReportGridbox").html("网格化服务管理工作情况通报表（一）");
		userActivateReportGridPrint();
		$("#userActivateReportGridbox").css({"overflow-y": "auto"});
	});
function userActivateReportGridPrint(){
	$.ajax({
		url:'/userActivateReportManage/userActivateReportSort.action?orgLevelDistance='+'<@s.property value="#parameters.orgLevelDistance"/>'+'&year='+'<@s.property value="#parameters.year"/>'+'&month='+'<@s.property value="#parameters.month"/>'+'&sortName='+'<@s.property value="#parameters.sortName"/>'+'&sort='+'<@s.property value="#parameters.sort"/>',
		success:function(data){
		userActivateReportGrid.bindData(data);
		}
	})
}
</script>