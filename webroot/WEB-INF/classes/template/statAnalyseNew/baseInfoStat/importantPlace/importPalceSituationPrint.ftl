<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<input type="hidden" id="orgId" value='<@s.property value="#parameters.parentOrgId"/>'/>
<div id="placeStuationPrint" style="overflow: auto;height:100%;width:100%">
   <style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 25px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:640px !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:600px;  
			border-collapse:collapse;
			overflow-y: hidden;
		}
		.SigmaReportPrint td{
			font-weight:normal;
			color:#333;
		}
		.SigmaReportPrint .head{
		    width:600px;
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
			line-height:28px;
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
   <div id="placeStuationGridbox" class="SigmaReportPrint" style="overflow: auto;height:100%;width:100%"></div>
</div>
<script>
var enterPriseGrid = null;
var orgId = $("#orgId").val();
$(document).ready(function(){
	$.ajax({
		url:'${path}/sysadmin/orgManage/ajaxOrganization.action?organization.id='+orgId,
		success:function(data){
			if(data == null){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			$("#title_placeStuationGridbox").children().remove();
			$("#title_placeStuationGridbox").html(data.orgName+"重点场所总况报表");
		}
	});
		var context = {};
		var columns = [
			{name:"org.orgName",caption:"区域",width:120,mode:"string"},	
			{name:"general",caption:"总况",children:[
				{name:"personnelDetailDatas[index].name",caption:"类型",width:150,mode:"string",align:"left"},
				{name:"personnelDetailDatas[index].amount",caption:"数量",width:80,mode:"string",format:"#",align:"left"},
				{name:"personnelDetailDatas[index].amountPercentage",caption:"百分比",width:80,mode:"number",format:"#.00%",align:"left"}
			]}	
	    ];
		enterPriseGrid = new SigmaReport("placeStuationGridbox",context,columns, "SigmaReportPrint","printTable_title");
		onOrgChanged1();

	});
	function onOrgChanged1(){
		$.ajax({
			url:'/baseInfoStat/statisticsPlace/getStatisticsPlaceInfoList.action?orgId='+orgId+'&year='+'<@s.property value="#parameters.year"/>'+'&month='+'<@s.property value="#parameters.month"/>'+'&typeTableName='+'<@s.property value="#parameters.typeTableName"/>',
			success:function(data){
			enterPriseGrid.bindData(data);
			}
		})
	}
</script>
