<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<input type="hidden" id="orgId" value='<@s.property value="#parameters.parentOrgId"/>'/>
<input type="hidden" id="keyType" value='<@s.property value="#parameters.keyType"/>'/>
<div id="enterPrisePrint"  style="overflow: auto;height:100%;width:100%">
   <style type="text/css">
        .printTable_title{position:relative;margin:0 3px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 30px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:640px;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:640px;  
			border-collapse:collapse;
			overflow-y: hidden;
		}
		.SigmaReportPrint td{
			font-weight:normal;
			color:#333;
		}
		.SigmaReportPrint .head{
		    width:640px;
			margin:0 3px;
			border-left:1px solid #ccc;
		}
		
		.SigmaReportPrint .body{
			margin:0 3px;
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
   <div id="enterPriseGridbox" class="SigmaReportPrint"></div>
</div>
<script>
var enterPriseGrid = null;
var keyType=$("#keyType").val();
var orgId = $("#orgId").val();
$(document).ready(function(){
	var str = "公共复杂场所";
	$.ajax({
		url:'${path}/sysadmin/orgManage/ajaxOrganization.action?organization.id=<@s.property value="#parameters.parentOrgId"/>',
		success:function(data){
			if(data == null){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			$("#title_enterPriseGridbox").children().remove();
			$("#title_enterPriseGridbox").html(data.orgName+str+"报表");
		}
	})
			var context = {};
			var columns = [
			{name:"organization.orgName",caption:"区域",width:120,mode:"string"},
			{name:"total",caption:"总数",width:100,mode:"number",format:"#",align:"left"},
			{name:"general",caption:"已落实负责人",children:[
				{name:"helped",caption:"场所数量",width:100,mode:"number",format:"#",align:"left"},
				{name:"helped/total",caption:"比率",width:80,mode:"number",format:"#.00%",align:"left"}
			]},
			{name:"helpInfo",caption:"未落实负责人",children:[
				{name:"noHelp",caption:"场所数量",width:100,mode:"number",format:"#",align:"left"},
				{name:"noHelp/total",caption:"比率",width:80,mode:"number",format:"#.00%",align:"left"}
			]}

		];
		enterPriseGrid = new SigmaReport("enterPriseGridbox",context,columns, "SigmaReportPrint","printTable_title");
		loadAjax1();
		$("#title_enterPriseGridbox").width($("#tHead_enterPriseGridbox").width() - 1);

	});
function loadAjax1(){
	$.ajax({
		url: "${path}/baseinfo/statAnalysePlace/findStatAnalysePlace.action?orgId="+orgId+"&keyType="+keyType+'&year='+'<@s.property value="#parameters.year"/>'+'&month='+'<@s.property value="#parameters.month"/>',
		success:function(responseData){
			enterPriseGrid.bindData(responseData);
		}
	});
}

</script>
