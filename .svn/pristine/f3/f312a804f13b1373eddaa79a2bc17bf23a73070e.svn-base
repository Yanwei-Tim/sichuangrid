<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="orgId" value='<s:property value="#parameters.parentOrgId"/>'/>
<input type="hidden" id="keyType" value='<s:property value="#parameters.keyType"/>'/>
<div id="populationSituationPrint"  style="overflow: auto;height:100%;width:100%">
   <style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 24px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:640px;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:595px;  
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
   <div id="personSituationGridbox" class="SigmaReportPrint" style="overflow: auto !important;height:100%;width:100%"></div>
</div>
<script>
var importPersonSituationGrid = null;
$(document).ready(function(){
	$.ajax({
		url:'${path}/sysadmin/orgManage/ajaxOrganization.action?organization.id=<s:property value="#parameters.parentOrgId"/>',
		success:function(data){
			if(data == null){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			$("#title_personSituationGridbox").children().remove();
			$("#title_personSituationGridbox").html(data.orgName+document.title+"报表");
		}
	 })
		var context = {};
		var columns = [
			{name:"org.orgName",caption:"区域",width:120,mode:"string"},	
			{name:"general",caption:"总况",children:[
				{name:"populationDetailDatas[index].name",caption:"类型",width:150,mode:"string",align:"left"},
				{name:"populationDetailDatas[index].amount",caption:"数量",width:80,format:"#",align:"left"},
				{name:"populationDetailDatas[index].amountPercentage",caption:"百分比",width:80,mode:"number",format:"#.00%",align:"left"}
			]}	

		];
		importPersonSituationGrid = new SigmaReport("personSituationGridbox",context,columns, "SigmaReportPrint","printTable_title");
		importPersonelSituationPrint();

	});
function importPersonelSituationPrint(){
	$.ajax({
		url:'/baseInfoStat/companyPlaceStanalsManageNew/getStatisticsCompanyPlaceList.action?orgId='+'<s:property value="#parameters.parentOrgId"/>'+'&year='+'<s:property value="#parameters.year"/>'+'&month='+'<s:property value="#parameters.month"/>'+'&moduleType='+'<s:property value="#parameters.moduleType"/>&&orgLevelDistance=<s:property value="#parameters.orgLevelDistance"/>',
		success:function(data){
		importPersonSituationGrid.bindData(data);
		}
	})
}
</script>
