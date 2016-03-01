<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="zongkuangPrint" style="overflow: auto;height:100%;width:100%">

<div id="gridbox12" class="SigmaReportPrint">
</div>
<div id="cssDiv">
	<style type="text/css">
	       .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
	             border-top: 1px solid #ccc;border-left:1px solid #ccc;
	             border-right: 1px solid #ccc;font-size: 12px;height: 23px;line-height: 28px;
	             padding-top: 2px;font-weight:bold;color: #333;text-align:center;
	             font-size:16px;width:1100px !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:1100px;  
			border-collapse:collapse;
			overflow: hidden;
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
</div>
</div>
<script type="text/javascript">
var primaryOrgStatGrid = null;
var orgName;
function getPrimaryOrgStat(){
	$.ajax({
		url:'${path}/baseInfo/primaryOrganizationStat/primaryOrganizationStatisticList.action?orgId='+getCurrentOrgId()+"&year="+$("#year").val()+"&month="+$("#month").val(),
		success:function(data){
			if(data == null || (data[0]!= null &&data[0].org == null)){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			primaryOrgStatGrid.bindData(data);
		}
	})
}


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
			$("#title_gridbox12").children().remove();
			$("#title_gridbox12").html(data.orgName+"组织机构总况报表");
		}
	});
	var context = {};
	var columns = [		
		   			{name:"org.orgName",caption:"区域",width:120,mode:"string"},	
		   			{name:"organizationName",caption:"组织名称",width:80,align:'center',children:[
		   				{name:"general",caption:"党委部门",width:110,mode:"string",children:[
		   					{name:"partyCommitteeCount",caption:"队伍数量",width:55,mode:"string"},
		   					{name:"partyCommitteeMemberCount",caption:"人数",width:55,mode:"string"}
		   				 ]},	
		   				{name:"general",caption:"基层党委",width:110,mode:"string",children:[
		   				    {name:"partyTeamCount",caption:"队伍数量",width:55,mode:"string"},
		   				    {name:"partyTeamMemberCount",caption:"人数",width:55,mode:"string"}
		   				 ]},	
		   				{name:"general",caption:"社会组织",width:110,mode:"string",children:[
		   				    {name:"socialOrganizationCount",caption:"队伍数量",width:110,mode:"string"}
		   				 ]},	
		   				{name:"general",caption:"非公有制经济组织",width:110,mode:"string",children:[
		   				    {name:"newEconomicOrganizationsCount",caption:"队伍数量",width:110,mode:"string"}
		   				 ]},	
		   				{name:"general",caption:"基层自治组织",width:110,mode:"string",children:[
		   				    {name:"autonomyTeamCount",caption:"队伍数量",width:55,mode:"string"},
		   				    {name:"autonmoyTeamMemberCount",caption:"人数",width:55,mode:"string"}
		   				 ]},	
		   				{name:"general",caption:"群防群治组织",width:110,mode:"string",children:[
		   				    {name:"massesTeamCount",caption:"队伍数量",width:55,mode:"string"},
		   				    {name:"massesTeamMemberCount",caption:"人数",width:55,mode:"string"}
		   				 ]},	
		   				{name:"general",caption:"社会志愿者队伍",width:110,mode:"string",children:[
		   				    {name:"postulantTeamCount",caption:"队伍数量",width:55,mode:"string"},
		   				    {name:"postulantTeamMemberCount",caption:"人数",width:55,mode:"string"}
		   				 ]},	
		   				{name:"general",caption:"政府部门",width:110,mode:"string",children:[
		   				    {name:"governmentDepartmentsCount",caption:"队伍数量",width:55,mode:"string"},
		   				    {name:"governmentDepartmentsMemberCount",caption:"人数",width:55,mode:"string"}
		   				]},
		   				{name:"general",caption:"群团组织",width:110,mode:"string",children:[
		   				    {name:"massOrganizationCount",caption:"队伍数量",width:55,mode:"string"},
		   				    {name:"massOrganizationMemberCount",caption:"人数",width:55,mode:"string"}
		   				]}
		   			]}
		   		];
	
	primaryOrgStatGrid = new SigmaReport("gridbox12",context,columns, "SigmaReportPrint","printTable_title");
	getPrimaryOrgStat();
	$("#gridbox12").append($("#cssDiv").html());
})
</script>


