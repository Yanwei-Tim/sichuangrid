<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="zongkuangPrint" style="overflow: auto;height:100%;width:100%">
<style type="text/css">
        .printTable_title{position:relative;margin:0 5px;background: #E7EDF5;
              border-top: 1px solid #ccc;border-left:1px solid #ccc;
              border-right: 1px solid #ccc;font-size: 12px;height: 23px;line-height: 28px;
              padding-top: 2px;font-weight:bold;color: #333;text-align:center;
              font-size:16px;width:1150px !important;border-bottom:1px solid #ccc;}
		.SigmaReportPrint table{
			width:1150px;  
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
<div id="gridbox12" class="SigmaReportPrint">
</div>
</div>
<script type="text/javascript">
var grid = null;
function onOrgChanged(){
	$.ajax({
		url:'${path}/baseinfo/villageReportSummaryManage/countVillageReportSummaryForlist.action',
		data:{"reportDataSummary.organization.id":getCurrentOrgId(),"reportDataSummary.year":$("#year").val(),"reportDataSummary.quarter":$("#quarter").val()},
		success:function(data){
			if(data == null){
				$.messageBox({
					message: "新农村建设统计表查询统计失败",
					level: "error"
				});
				return;
			}
			grid.bindData(data);
		}
	})
}


$(document).ready(function(){
	var columns =getColumn() ;
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox12",context,columns, "SigmaReportPrint","printTable_title");
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
	//$("#title_gridbox").children().remove();
	//$("#title_gridbox").html("幸福美丽新村建设统计表");
	$('#title_gridbox,#head_gridbox,#body_gridbox').width( $('#tHead_gridbox').width() );

	onOrgChanged();
});

function getColumn(){
	/* var columns = [		
					{name:"reportOrg.orgName",caption:"区县",width:80,mode:"string"}, 
					{name:"general",caption:"新农村建设情况",width:60,mode:"string",children:[
					     {name:"general",caption:"基本情况",width:70,mode:"string",children:[
					          {name:"newVillage.totalHouseholdsNum",caption:"总户数",width:50,mode:"string"},
					          {name:"newVillage.totalPeopleNum",caption:"总人数",width:50,mode:"string"},
					          {name:"newVillage.totalLabourNum",caption:"总劳动力",width:50,mode:"string"},
					          {name:"newVillage.perCapitaIncome",caption:"人均收入",width:50,mode:"string"}
					     ]},
				    	 {name:"general",caption:"聚居点情况",width:50,mode:"string",children:[
				    	      {name:"newVillage.newlyBuildNum",caption:"新建",width:50,mode:"string"},
				    	      {name:"newVillage.reformNum",caption:"改造",width:50,mode:"string"},
				    	      {name:"newVillage.protectionNum",caption:"保护",width:50,mode:"string"}
				    	 ]},
				    	 {name:"general",caption:"分散居住情况",width:50,mode:"string",children:[
				    	      {name:"newVillage.noRoomNum",caption:"无房户",width:70,mode:"string"},
				    	      {name:"newVillage.difficultNum",caption:"苦难户",width:50,mode:"string"},
				    	      {name:"newVillage.adobeNum",caption:"土坯房廉租房",width:80,mode:"string"},
				    	      {name:"newVillage.dangerousNum",caption:"危房户",width:50,mode:"string"}
				    	 ]}
					]}, 
					{name:"general",caption:"产业发展",width:60,mode:"string",children:[
                       {name:"general",caption:"种植业",width:70,mode:"string",children:[
                            {name:"industryDevelopment.grainAcreage",caption:"粮食作物（亩）",width:100,mode:"string"},
                            {name:"industryDevelopment.economicCropsNum",caption:"经济作物（亩）",width:100,mode:"string"}
                       ]},
                       {name:"general",caption:"养殖业",width:50,mode:"string",children:[
                            {name:"industryDevelopment.pigNum",caption:"生猪（头）",width:70,mode:"string"},
                            {name:"industryDevelopment.cattleSheepNum",caption:"牛羊（头）",width:70,mode:"string"},
                            {name:"industryDevelopment.poultryNum",caption:"小家禽（只）",width:90,mode:"string"},
                            {name:"industryDevelopment.aquaticProductNum",caption:"水产（亩）",width:70,mode:"string"}
                       ]},
                       {name:"general",caption:"产业化经营",width:50,mode:"string",children:[
                            {name:"industryDevelopment.homeOrgNum",caption:"家和组织专业户（个）",width:130,mode:"string"},
                            {name:"industryDevelopment.familyFarmNum",caption:"家庭农场（个）",width:100,mode:"string"},
                            {name:"industryDevelopment.plantingNum",caption:"种养大户（户）",width:100,mode:"string"}
                       ]}
                  ]}, 
					{name:"general",caption:"基础设施建设",width:60,mode:"string",children:[
					     {name:"general",caption:"生产型基础设施",width:50,mode:"string",children:[
					          {name:"infrastructure.villageHardenedRoad",caption:"村路（公里）",width:80,mode:"string"},
					          {name:"infrastructure.homeHardenedRoad",caption:"入户路（公里）",width:100,mode:"string"},
					          {name:"infrastructure.tillHardenedRoad",caption:"耕种路（户）",width:90,mode:"string"},
					          {name:"infrastructure.weirPoolNum",caption:"塘湖堰池（个）",width:100,mode:"string"},
					          {name:"infrastructure.canalLength",caption:"水渠（公里）",width:80,mode:"string"},
					          {name:"infrastructure.reformArea",caption:"电力覆盖比例（%）",width:110,mode:"string"}
					     ]},
					     {name:"general",caption:"生活型基础设施",width:50,mode:"string",children:[
					          {name:"infrastructure.drinkingWaterNum",caption:"生活饮用水户数",width:100,mode:"string"},
					          {name:"infrastructure.biogasNum",caption:"沼气（口）",width:70,mode:"string"},
					          {name:"infrastructure.naturalGasNum",caption:"天然气（户）",width:80,mode:"string"},
					          {name:"infrastructure.WCNum",caption:"公厕数（个）",width:80,mode:"string"},
					          {name:"infrastructure.toCircleNum",caption:"改圈数（个）",width:80,mode:"string"},
					          {name:"infrastructure.changeWaterNum",caption:"改水数（户）",width:80,mode:"string"},
					          {name:"infrastructure.broadbandNum",caption:"宽带（户）",width:70,mode:"string"},
					          {name:"infrastructure.telephoneNum",caption:"电话（户）",width:70,mode:"string"}
					    ]}
					]}, 
					{name:"general",caption:"公共服务",width:60,mode:"string",children:[
					     {name:"general",caption:"教育",width:70,mode:"string",children:[
					          {name:"commonServiceInfo.villageSmallNum",caption:"村小（所）",width:70,mode:"string"},
					          {name:"commonServiceInfo.kindergartenNum",caption:"幼儿园（所）",width:80,mode:"string"},
					          {name:"commonServiceInfo.middleSchoolNum",caption:"中学（所）",width:70,mode:"string"}
					     ]},
					     {name:"general",caption:"卫生",width:50,mode:"string",children:[
					          {name:"commonServiceInfo.healthRoomNum",caption:"卫生室（个）",width:80,mode:"string"}
					     ]},
					     {name:"general",caption:"文体",width:50,mode:"string",children:[
					          {name:"commonServiceInfo.libraryNum",caption:"图书室（个）",width:80,mode:"string"},
					          {name:"commonServiceInfo.fitnessSquare",caption:"健身广场（平方米）",width:120,mode:"string"},
					          {name:"commonServiceInfo.fitnessEquipmentNum",caption:"健身器材（个）",width:100,mode:"string"},
					          {name:"commonServiceInfo.culturalActivities",caption:"文化活动（场）",width:100,mode:"string"},
					          {name:"commonServiceInfo.convenienceCenterNum",caption:"便民服务中心（个）",width:110,mode:"string"},
					          {name:"commonServiceInfo.farmerSupermarketNum",caption:"农家超市（个）",width:100,mode:"string"}
					    ]}
					]},
					{name:"general",caption:"",width:60,mode:"string",children:[
					{name:"general",caption:"环境改造",width:60,mode:"string",children:[
					     {name:"environmentalReform.villageNum",caption:"生态村（个）",width:80,mode:"string"},
					     {name:"environmentalReform.woodlandArea",caption:"林地占比（%）",width:90,mode:"string"},
					     {name:"environmentalReform.garbageTank",caption:"垃圾池（个）",width:80,mode:"string"},
					     {name:"environmentalReform.WCNum",caption:"公厕数（个）",width:80,mode:"string"},
					     {name:"environmentalReform.sewageTreatmentNum",caption:"污水处理设施（个）",width:110,mode:"string"}
					]}
					]}
		   		];
	return columns; */
	var columns = [		
					{name:"organization.orgName",caption:"区县",width:80,mode:"string"}, 
					{name:"list[index].statisticsDimensions",caption:"维度",width:120,mode:"string"},
//					{name:"general",caption:"基本情况",width:60,mode:"string",children:[
//						{name:"general",caption:"土地",width:60,mode:"string",children:[
//							{name:"basicYearInfo.totalArea",caption:"幅员面积(亩)",width:85,mode:"string"},
//							{name:"basicYearInfo.cultivatedLandArea",caption:"耕地面积(亩)",width:85,mode:"string"},
//							{name:"basicYearInfo.woodlandArea",caption:"林地面积(亩)",width:85,mode:"string"},
//							{name:"basicYearInfo.wastelandArea",caption:"荒地面积(亩)",width:85,mode:"string"},
//							{name:"basicYearInfo.landTransfer",caption:"流转土地面积(亩)",width:100,mode:"string"}
//                        ]},
//                        {name:"general",caption:"人口",width:60,mode:"string",children:[
//							{name:"basicYearInfo.totalHouseholdsNum",caption:"总户数(户)",width:65,mode:"string"},
//							{name:"basicYearInfo.totalPeopleNum",caption:"总人数(口)",width:65,mode:"string"},
//							{name:"basicYearInfo.outWorkNum",caption:"外出务工人员(口)",width:110,mode:"string"}                                                    	
//                        ]},
//                        {name:"general",caption:"经济",width:60,mode:"string",children:[
//							{name:"basicYearInfo.totalEconomicIncome",caption:"农村经济总收入(万元)",width:120,mode:"string"},
//							{name:"basicYearInfo.totalCollectiveEconomicIncome",caption:"村集体经济总收入(万元)",width:130,mode:"string"},
//							{name:"general",caption:"农民人均可支配收入(万元)",width:150,mode:"string"}                                                                       	
//                        ]},
//					]}, 
					{name:"general",caption:"新村建设情况",width:60,mode:"string",children:[
						{name:"general",caption:"聚居点情况",width:60,mode:"string",children:[
							{name:"list[index].newVillage.settlementsNumber",caption:"聚居点数量(个)",width:90,mode:"string"},         
							{name:"list[index].newVillage.settlementsBuild",caption:"其中新建数量(个)",width:110,mode:"string"},    
							{name:"list[index].newVillage.settlementsReform",caption:"其中改造数量(个)",width:110,mode:"string"},    
							{name:"list[index].newVillage.settlementsProtect",caption:"其中保护数量(个)",width:110,mode:"string"},    
							{name:"list[index].newVillage.involvingFarmers",caption:"涉及农户数(户)",width:90,mode:"string"}                                          
						]},
						{name:"general",caption:"住房情况",width:60,mode:"string",children:[
							{name:"list[index].newVillage.houseCount",caption:"房屋总数(户)",width:80,mode:"string"},         
							{name:"list[index].newVillage.masonryStructure",caption:"其中砖混结构房(户)",width:120,mode:"string"},    
							{name:"list[index].newVillage.noHouseCount",caption:"无房户(户)",width:80,mode:"string"},    
							{name:"list[index].newVillage.dangerousHouseCount",caption:"其中危房户(户)",width:100,mode:"string"},    
							{name:"list[index].newVillage.housingDifficultCount",caption:"其中住房困难户(户)",width:120,mode:"string"},    
							{name:"list[index].newVillage.lowRentHousing",caption:"其中廉租房(户)",width:100,mode:"string"}
						]}
					]},
					{name:"general",caption:"产业发展",width:60,mode:"string",children:[
						{name:"general",caption:"种植业",width:60,mode:"string",children:[
							{name:"list[index].industryDevelopment.grainCrops",caption:"粮食作物(亩)",width:80,mode:"string"},         
							{name:"list[index].industryDevelopment.economicCrops",caption:"经济作物(亩)",width:80,mode:"string"},    
							{name:"list[index].industryDevelopment.scalePlanting",caption:"适度规模种植户(户)",width:120,mode:"string"}                                                   
						]},
						{name:"general",caption:"养殖业",width:60,mode:"string",children:[
							{name:"list[index].industryDevelopment.pigNum",caption:"生猪(头)",width:55,mode:"string"},    
							{name:"list[index].industryDevelopment.cattleSheepNum",caption:"牛羊(头)",width:55,mode:"string"},
							{name:"list[index].industryDevelopment.poultryNum",caption:"小家禽(只)",width:80,mode:"string"},
							{name:"list[index].industryDevelopment.aquaticProductNum",caption:"水产(亩)",width:55,mode:"string"},
							{name:"list[index].industryDevelopment.scaleBreed",caption:"适度规模养殖户(户)",width:120,mode:"string"}
						]},
						{name:"general",caption:"产业化经营",width:60,mode:"string",children:[
							{name:"list[index].industryDevelopment.specialistNum",caption:"专业合作经济组织(个)",width:140,mode:"string"},    
							{name:"list[index].industryDevelopment.familyFarmNum",caption:"家庭农场(个)",width:100,mode:"string"},
							{name:"list[index].industryDevelopment.plantingNum",caption:"种养大户(户)",width:100,mode:"string"}                                                           
						]}
					]},
					{name:"general",caption:"基础设施建设",width:60,mode:"string",children:[
						{name:"general",caption:"道路",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.villageRoad",caption:"村社道路(公里)",width:110,mode:"string"},        
							{name:"list[index].infrastructure.villageHardenedRoad",caption:"硬化路(公里)",width:100,mode:"string"}, 
							{name:"list[index].infrastructure.villageMudKnotRoad",caption:"泥结石路(公里)",width:110,mode:"string"}
						]}, 
						{name:"general",caption:"水利",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.weirPoolNum",caption:"塘湖堰库(座)",width:90,mode:"string"}, 
							{name:"list[index].infrastructure.canalLength",caption:"水渠(公里)",width:60,mode:"string"}, 
							{name:"list[index].infrastructure.drinkingWaterNum",caption:"生活饮用水户数(户)",width:120,mode:"string"}
						]},
						{name:"general",caption:"电力",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.powerGridNum",caption:"农村电网改造数",width:120,mode:"string"}                                                           
						]},
						{name:"general",caption:"沼气",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.isBiogas",caption:"户用沼气池(口)",width:100,mode:"string"}                                                           
						]},
						{name:"general",caption:"三建四改",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.involveHouseCount",caption:"涉及户数(户)",width:90,mode:"string"}                                                           
						]},
						{name:"general",caption:"信息化",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.isBroadbandVillage",caption:"宽带乡村个数(户)",width:110,mode:"string"},
							{name:"list[index].infrastructure.cableTvCount",caption:"有线电视(户)",width:90,mode:"string"}, 
							{name:"list[index].infrastructure.broadbandCount",caption:"宽带(户)",width:80,mode:"string"}
						]} 

					]},
					{name:"general",caption:"公共服务",width:60,mode:"string",children:[
						{name:"general",caption:"教育",width:60,mode:"string",children:[
							{name:"list[index].commonServiceInfo.villageSchool",caption:"村小(所)",width:80,mode:"string"},       
							{name:"list[index].commonServiceInfo.kindergarten",caption:"幼儿园(所)",width:80,mode:"string"},
							{name:"list[index].commonServiceInfo.highSchool",caption:"中学(所)",width:80,mode:"string"}
						]},
						{name:"general",caption:"卫生",width:60,mode:"string",children:[
							{name:"list[index].commonServiceInfo.clinic",caption:"卫生室(个)",width:80,mode:"string"}                                                 
						]},
						{name:"general",caption:"文体",width:60,mode:"string",children:[
							{name:"list[index].commonServiceInfo.socialWorkCenter",caption:"村级公共服务活动中心(个)",width:150,mode:"string"},
							{name:"list[index].commonServiceInfo.library",caption:"图书室(个)",width:100,mode:"string"},
							{name:"list[index].commonServiceInfo.fitnessSquare",caption:"健身广场(平方米)",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.fitnessEquipment",caption:"健身器材(套)",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.entertainmentRoom",caption:"文化活动室(个)",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.farmerSupermarket",caption:"农家超市(个)",width:120,mode:"string"}
						]}
					]},
					{name:"general",caption:"环境整治",width:60,mode:"string",children:[
						{name:"general",caption:"",width:60,mode:"string",children:[
							{name:"list[index].environmentalReform.garbageTank",caption:"垃圾池(个)",width:100,mode:"string"},
							{name:"list[index].environmentalReform.toilets",caption:"公厕数(个)",width:100,mode:"string"},
							{name:"list[index].environmentalReform.treatmentFacilities",caption:"污水处理设施(个)",width:130,mode:"string"}
						]}
					]},
					{name:"general",caption:"基层组织建设",width:60,mode:"string",children:[
						{name:"general",caption:"",width:60,mode:"string",children:[
                           {name:"list[index].organizationConstruction.isPositionBuilding",caption:"阵地建设数(个)",width:100,mode:"string"},
                           {name:"list[index].organizationConstruction.threeSatisfaction",caption:"三务公开群众满意度(%)",width:130,mode:"string"},
                           {name:"list[index].organizationConstruction.surveySatisfaction",caption:"基层和党员调查满意度(%)",width:150,mode:"string"}
                       ]}                                                            
					]},
					{name:"general",caption:"资金投入情况",width:60,mode:"string",children:[
						{name:"general",caption:"财政资金投入(万元)",width:60,mode:"string",children:[
							{name:"list[index].capitalInvested.centralProvinceInvested",caption:"中央/省级投入(万元)",width:130,mode:"string"},
							{name:"list[index].capitalInvested.municipalityInvested",caption:"市/州投入(万元)",width:120,mode:"string"},
							{name:"list[index].capitalInvested.countyInvested",caption:"县级投入(万元)",width:120,mode:"string"}
						]},
						{name:"general",caption:"社会资金投入",width:60,mode:"string",children:[
							{name:"list[index].capitalInvested.financialInvested",caption:"金融投入(万元)",width:120,mode:"string"},
							{name:"list[index].capitalInvested.industryAndCommerceInvested",caption:"工商资本投入(万元)",width:140,mode:"string"}                                                           
						]},
						{name:"general",caption:"农民投入",width:60,mode:"string",children:[
							{name:"list[index].capitalInvested.farmerInvested",caption:"自筹资金(万元)",width:120,mode:"string"}
						]}
					]},
					{name:"general",caption:"农民人均可支配收入",width:60,mode:"string",children:[
						{name:"general",caption:"",width:60,mode:"string",children:[
							{name:"list[index].farmerPerIncomeInfo.farmerPerIncome",caption:"绝对值(万元)",width:100,mode:"string"},
							{name:"list[index].farmerPerIncomeInfo.increaseRate",caption:"增长幅度(%)",width:100,mode:"string"}
						]}  		                                                                  
					]}
					 
		   		];
	return columns;
}
</script>


