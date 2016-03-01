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
var newvillageAcceptance = null;
var orgName;
function getNewVillageStatistics(){
	$.ajax({
		url:'${path}/newVillageAcceptanceManage/findNewVillageAssessmentVoForList.action',
		data:{"year":$("#year").val()},
		success:function(data){
			if(data == null || (data[0].organization == null)){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			newvillageAcceptance.bindData(data);
		}
	})
}


$(document).ready(function(){
	$.ajax({
		url:'${path}/newVillageAcceptanceManage/findNewVillageAssessmentVoForList.action',
		data:{"year":$("#year").val()},
		success:function(data){
			if(data == null){
				$.messageBox({
					message: data,
					level: "error"
				});
				return;
			}
			$("#title_gridbox12").children().remove();
			$("#title_gridbox12").html("幸福美丽新村建设验收表");
		}
	});
	var context = {};
	var columns = [		
		   			{name:"organization.orgName",caption:"区县",width:80,mode:"string"}, 
					{name:"general",caption:"培育有优势特色产业",width:60,mode:"string",children:[
						{name:"characteristicPlantingStr",caption:"完成数",width:30,mode:"string"},
						{name:"characteristicPlantingFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"农业主导产业收入占农村家庭经营收入的比重",width:60,mode:"string",children:[
					    {name:"proportionOfIncomeStr",caption:"完成数",width:30,mode:"string"},
					    {name:"proportionOfIncomeFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"人均可支配收入",width:60,mode:"string",children:[
					    {name:"farmerPerIncome",caption:"完成数",width:30,mode:"string"},
					    {name:"farmerPerIncomeFraction",caption:"得分",width:30,mode:"string"}
					]},
					{name:"general",caption:"人均安全住房面积",width:60,mode:"string",children:[
					    {name:"capitaHousingArea",caption:"完成数",width:30,mode:"string"},
					    {name:"capitaHousingAreaFraction",caption:"得分",width:30,mode:"string"}
					]},
					{name:"general",caption:"无房户、危房户、住房困难户住房问题全部解决",width:60,mode:"string",children:[
					    {name:"houseProblemCountStr",caption:"完成数",width:30,mode:"string"},
					    {name:"houseProblemFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"九年义务教育目标人群全覆盖",width:60,mode:"string",children:[
						{name:"compulsoryEducationStr",caption:"完成数",width:30,mode:"string"},
						{name:"compulsoryEducationFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"新农合参保率",width:60,mode:"string",children:[
						{name:"insuredProportionStr",caption:"完成数",width:30,mode:"string"},
						{name:"insuredProportionFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"新农保应保尽保",width:60,mode:"string",children:[
						{name:"hasBuyInsuranceStr",caption:"完成数",width:30,mode:"string"},
						{name:"hasBuyInsuranceFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"每年组织群众性文体活动",width:60,mode:"string",children:[
						{name:"recreationalActivities",caption:"完成数",width:30,mode:"string"},
						{name:"recreationalActivitiesFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"公共活动服务中心设施面积",width:60,mode:"string",children:[
						{name:"socialWorkCenterArea",caption:"完成数",width:30,mode:"string"},
						{name:"socialWorkCenterAreaFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"基层党组织/党员调查满意度",width:60,mode:"string",children:[
						{name:"surveySatisfactionStr",caption:"完成数",width:30,mode:"string"},
						{name:"surveySatisfactionFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"三务公开满意度",width:60,mode:"string",children:[
						{name:"threeSatisfactionStr",caption:"完成数",width:30,mode:"string"},
						{name:"threeSatisfactionFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"新村建设全覆盖",width:60,mode:"string",children:[
						{name:"isAllStandingStr",caption:"完成数",width:30,mode:"string"},
						{name:"isAllStandingFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"通村通组道路硬化率",width:60,mode:"string",children:[
						{name:"villageHardenedRoadStr",caption:"完成数",width:30,mode:"string"},
						{name:"villageHardenedFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"安全饮水全覆盖",width:60,mode:"string",children:[
						{name:"drinkingWaterStr",caption:"完成数",width:30,mode:"string"},
						{name:"drinkingWaterNumFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"电网改造全覆盖",width:60,mode:"string",children:[
						{name:"isPowerGrid",caption:"完成数",width:30,mode:"string"},
						{name:"isPowerGridFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"面源污染有效治理",width:60,mode:"string",children:[
						{name:"treatmentPollutionStr",caption:"完成数",width:30,mode:"string"},
						{name:"treatmentPollutionFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"生活垃圾处理全覆盖",width:60,mode:"string",children:[
						{name:"garbageDisposalStr",caption:"完成数",width:30,mode:"string"},
						{name:"garbageDisposalFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"生活污水处理覆盖率",width:60,mode:"string",children:[
						{name:"sanitarySewageStr",caption:"完成数",width:30,mode:"string"},
						{name:"sanitarySewageFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"general",caption:"农村院落整治全覆盖",width:60,mode:"string",children:[
						{name:"involveHouseStr",caption:"完成数",width:30,mode:"string"},
						{name:"involveHouseCountFraction",caption:"得分",width:30,mode:"string"}
					]}, 
					{name:"newVillageFraction",caption:"综合得分",width:60,mode:"string"},
					{name:"isAdoptStr",caption:"验收结果",width:60,mode:"string"}
		   		];
	
	newvillageAcceptance = new SigmaReport("gridbox12",context,columns, "SigmaReportPrint","printTable_title");
	getNewVillageStatistics();
	$("#gridbox12").append($("#cssDiv").html());
})
</script>


