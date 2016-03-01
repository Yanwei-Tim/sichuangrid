<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<jsp:include page="/common/orgSelectedComponent.jsp"/>     
<style>
.SigmaReport{
/*   overflow: auto !important; */
}
</style>
<div id="nav" class="ui-corner-all" style="margin-left: 9px;">
		 <label style="float:left;padding:0 10px;line-height:25px;">时间：</label>
		<select name="year" id="year" onchange="" style="float:left;">
			
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
       <%--  <select style="float:left;" name="quarter" id="quarter" onchange="">
		       
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">季度</label>
        <a id="searchList" href="javascript:void(0)"><span>查询</span></a> --%>
        
</div>
<div class="content" >
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="${type}PrintDlg"></div>

<script type="text/javascript">
var grid = null;
function onOrgChanged(){
	$.ajax({
		url:'${path}/baseinfo/villageReportSummaryManage/countVillageReportSummaryForlist.action',
		data:{"reportDataSummary.organization.id":getCurrentOrgId(),"reportDataSummary.year":$("#year").val()/* ,"reportDataSummary.quarter":$("#quarter").val() */},
		success:function(data){
			if(data == null){
				$.messageBox({
					message: "幸福美丽新村建设统计表查询统计失败",
					level: "error"
				});
				return;
			}
			grid.bindData(data);
		}
	})
}

function getQuarter(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForQuarter.action?currenYear="+$("#year").val(),
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#quarter").append("<option  value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			onOrgChanged();
		}
	});
}


function findReportSummaryByYearAndQuarter(){
	 var	initParam = {
				"reportDataSummary.organization.id":getCurrentOrgId(),
				//"reportDataSummary.quarter":$("#quarter").val(),
				"reportDataSummary.year":$("#year").val()
			}

			$("#basicInfoMationList").setGridParam({
				url:'${path}/baseinfo/villageReportSummaryManage/findVillageReportSummaryForlist.action',
				datatype:'json',
				page:1
			});
			$("#villageReportSummaryList").setPostData(initParam);
			$("#villageReportSummaryList").trigger("reloadGrid");
}


$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			//getQuarter();
			onOrgChanged();
		}
	});
	
	var columns =getColumn() ;
	
	
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,null,null,null);
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
	
	$("#title_gridbox").children().remove();
	$("#title_gridbox").html("幸福美丽新村建设情况统计表");//<a href='javascript:;' class='print' title='打印'></a>
	

// 	$(".print").click(function(){
// 		$("#zongkuangPrint").createDialog({
// 			width:1200,
// 			height:600,
// 			title:document.title,
// 			url:'${path}/newCountryside/statistics/newRuralConstructionStatisticsPrint.jsp',
// 			buttons: {
// 			   "打印" : function(){
// 				print();
// 		  	   },
// 			   "关闭" : function(){
// 			        $("#zongkuangPrint").dialog("close");
// 			   }
// 			}
// 		});
// 	});
	
	
	$("#year").change(function(){
		//$("#quarter").empty();
		//getQuarter();
		onOrgChanged();
	});
	
	
	
// 	function print(){
// 		$("#zongkuangPrint").printArea();
// 		$("#zongkuangPrint").dialog("close");
// 	}
	
// 	$('#title_gridbox,#head_gridbox,#body_gridbox').width( $('#tHead_gridbox').width() )

});

function getColumn(){
	var columns = [		
					{name:"organization.orgName",caption:"区县",width:80,mode:"string"}, 
					{name:"list[index].statisticsDimensions",caption:"维度",width:120,mode:"string"},
// 					{name:"general",caption:"基本情况",width:60,mode:"string",children:[
// 						{name:"general",caption:"土地",width:60,mode:"string",children:[
// 							{name:"list[index].basicYearInfo.totalArea",caption:"幅员面积(亩)",width:85,mode:"string"},
// 							{name:"list[index].basicYearInfo.cultivatedLandArea",caption:"耕地面积(亩)",width:85,mode:"string"},
// 							{name:"list[index].basicYearInfo.woodlandArea",caption:"林地面积(亩)",width:85,mode:"string"},
// 							{name:"list[index].basicYearInfo.wastelandArea",caption:"荒地面积(亩)",width:85,mode:"string"},
// 							{name:"list[index].basicYearInfo.landTransfer",caption:"土地流转面积(亩)",width:100,mode:"string"}
//                         ]},
//                         {name:"general",caption:"人口",width:60,mode:"string",children:[
// 							{name:"list[index].basicYearInfo.totalHouseholdsNum",caption:"总户数(户)",width:65,mode:"string"},
// 							{name:"list[index].basicYearInfo.totalPeopleNum",caption:"总人数(口)",width:65,mode:"string"},
// 							{name:"list[index].basicYearInfo.outWorkNum",caption:"外出务工人员(口)",width:110,mode:"string"}                                                    	
//                         ]},
//                         {name:"general",caption:"村集体资产",width:60,mode:"string",children:[
// 							{name:"list[index].basicYearInfo.collectiveFunds",caption:"集体资金(万元)",width:120,mode:"string"},
// 							{name:"list[index].basicYearInfo.collectiveAssets",caption:"集体资产折资(万元)",width:130,mode:"string"}
//                         ]}
// 					]}, 
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
							{name:"list[index].newVillage.noHouseCount",caption:"无房户(户)",width:80,mode:"string"},    
							{name:"list[index].newVillage.dangerousHouseCount",caption:"其中危房户(户)",width:100,mode:"string"},    
							{name:"list[index].newVillage.housingDifficultCount",caption:"其中住房困难户(户)",width:120,mode:"string"},    
							{name:"list[index].newVillage.lowRentHousing",caption:"其中廉租房(户)",width:100,mode:"string"},
							{name:"list[index].newVillage.inLowRentHousing",caption:"其中入住廉租房(户)",width:100,mode:"string"},
							{name:"list[index].newVillage.numberInvolved",caption:"涉及人数",width:100,mode:"string"},
							{name:"list[index].newVillage.capitaHousingArea",caption:"农村人均安全住房面积(平方米)",width:100,mode:"string"}
						]}
					]},
					{name:"general",caption:"产业发展",width:60,mode:"string",children:[
						{name:"general",caption:"种植业",width:60,mode:"string",children:[
							{name:"list[index].industryDevelopment.grainCrops",caption:"粮食作物(亩)",width:80,mode:"string"},         
							{name:"list[index].industryDevelopment.economicCrops",caption:"经济作物(亩)",width:80,mode:"string"},    
							{name:"list[index].industryDevelopment.scalePlanting",caption:"适度规模种植户(户)",width:120,mode:"string"},  
							{name:"list[index].industryDevelopment.plantingHouseholdsCount",caption:"种植户总数",width:120,mode:"string"}
						]},
						{name:"general",caption:"养殖业",width:60,mode:"string",children:[
							{name:"list[index].industryDevelopment.pigNum",caption:"生猪(头)",width:55,mode:"string"},    
							{name:"list[index].industryDevelopment.cattleSheepNum",caption:"牛羊(头)",width:55,mode:"string"},
							{name:"list[index].industryDevelopment.poultryNum",caption:"小家禽/畜(只)",width:80,mode:"string"},
							{name:"list[index].industryDevelopment.aquaticProductNum",caption:"水产(亩)",width:55,mode:"string"},
							{name:"list[index].industryDevelopment.scaleBreed",caption:"适度规模养殖户(户)",width:120,mode:"string"},
							{name:"list[index].industryDevelopment.farmHouseholds",caption:"养殖户总数(户)",width:120,mode:"string"}
						]},
						{name:"general",caption:"产业化经营",width:60,mode:"string",children:[
							{name:"list[index].industryDevelopment.specialistNum",caption:"专业合作组织(个)",width:140,mode:"string"},    
							{name:"list[index].industryDevelopment.familyFarmNum",caption:"家庭农场(个)",width:100,mode:"string"},
							{name:"list[index].industryDevelopment.plantingNum",caption:"种养大户(户)",width:100,mode:"string"},
							{name:"list[index].industryDevelopment.productProcessing",caption:"农产品加工企业(个)",width:100,mode:"string"},
							{name:"list[index].industryDevelopment.countyCorporateChampion",caption:"其中龙头企业(个)",width:100,mode:"string"},
							{name:"list[index].industryDevelopment.industrialOrganization",caption:"带动农户数(户)",width:100,mode:"string"}
						]},
						{name:"general",caption:"乡村旅游",width:60,mode:"string",children:[
						    {name:"list[index].industryDevelopment.villaggioBoutiqueHotel",caption:"乡村酒店(个)",width:140,mode:"string"},    
						    {name:"list[index].industryDevelopment.agritainment",caption:"农家乐(个)",width:100,mode:"string"},
						    {name:"list[index].industryDevelopment.householdEmployment",caption:"带动就业人数(户)",width:100,mode:"string"},
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
							{name:"list[index].infrastructure.drinkingWaterNum",caption:"安全饮水户数(户)",width:120,mode:"string"}
						]},
// 						{name:"general",caption:"电力",width:60,mode:"string",children:[
// 							{name:"list[index].infrastructure.powerGridNum",caption:"农村电网改造数",width:120,mode:"string"}                                                           
// 						]},
						{name:"general",caption:"清洁能源",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.biogasNum",caption:"户用沼气池(口)",width:100,mode:"string"},
							{name:"list[index].infrastructure.solarEnergy",caption:"太阳能(个)",width:100,mode:"string"},
							{name:"list[index].infrastructure.naturalGas",caption:"天燃气(户)",width:100,mode:"string"}    
						]},
						{name:"general",caption:"三建四改",width:60,mode:"string",children:[
							{name:"list[index].infrastructure.involveHouseCount",caption:"涉及户数(户)",width:90,mode:"string"}                                                           
						]},
						{name:"general",caption:"农村信息化",width:60,mode:"string",children:[
// 							{name:"list[index].infrastructure.isBroadbandVillage",caption:"宽带乡村个数(户)",width:110,mode:"string"},
							{name:"list[index].infrastructure.cableTvCount",caption:"有线电视(户)",width:90,mode:"string"}, 
							{name:"list[index].infrastructure.broadbandCount",caption:"宽带(户)",width:80,mode:"string"}
						]} 

					]},
					{name:"general",caption:"公共服务",width:60,mode:"string",children:[
						{name:"general",caption:"教育",width:60,mode:"string",children:[
							{name:"list[index].commonServiceInfo.villageSchool",caption:"村小(所)",width:80,mode:"string"},       
							{name:"list[index].commonServiceInfo.kindergarten",caption:"幼儿园(所)",width:80,mode:"string"},
							{name:"list[index].commonServiceInfo.highSchool",caption:"中学(所)",width:80,mode:"string"},
							{name:"list[index].commonServiceInfo.compulsoryEducationCount",caption:"九年义务教育适龄人数(人)",width:80,mode:"string"},
							{name:"list[index].commonServiceInfo.inCompulsoryEducationCount",caption:"其中已入学人数(人)",width:80,mode:"string"}
						]},
						{name:"general",caption:"卫生",width:60,mode:"string",children:[
							{name:"list[index].commonServiceInfo.insuredNumber",caption:"新农合参保人数(人)",width:80,mode:"string"},
							{name:"list[index].commonServiceInfo.clinic",caption:"卫生室(个)",width:80,mode:"string"}                                                 
						]},
						{name:"general",caption:"文体",width:60,mode:"string",children:[
							{name:"list[index].commonServiceInfo.socialWorkCenter",caption:"村级公共服务活动中心(个)",width:150,mode:"string"},
							{name:"list[index].commonServiceInfo.socialWorkCenterArea",caption:"活动中心面积(平方米)",width:150,mode:"string"},
							{name:"list[index].commonServiceInfo.library",caption:"图书室(个)",width:100,mode:"string"},
							{name:"list[index].commonServiceInfo.fitnessSquare",caption:"健身广场(平方米)",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.fitnessEquipment",caption:"健身器材(套)",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.entertainmentRoom",caption:"文化活动室(个)",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.farmerSupermarket",caption:"农家超市(个)",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.recreationalActivities",caption:"组织开展文体活动",width:120,mode:"string"},
							{name:"list[index].commonServiceInfo.recreationalActivitiesPeople",caption:"参加活动人数",width:120,mode:"string"}
						]}
					]},
					{name:"general",caption:"环境整治",width:60,mode:"string",children:[
						{name:"general",caption:"",width:60,mode:"string",children:[
							{name:"list[index].environmentalReform.garbageTank",caption:"垃圾池(个)",width:100,mode:"string"},
							{name:"list[index].environmentalReform.toilets",caption:"公厕数(个)",width:100,mode:"string"},
							{name:"list[index].environmentalReform.treatmentFacilities",caption:"污水处理设施(个)",width:130,mode:"string"},
							{name:"list[index].environmentalReform.sanitarySewage",caption:"生活污水处理户数(户)",width:130,mode:"string"}
						]}
					]},
					{name:"general",caption:"基层组织建设",width:60,mode:"string",children:[
						{name:"general",caption:"",width:60,mode:"string",children:[
                            {name:"list[index].organizationConstruction.partyMembers",caption:"党员人数(人)",width:100,mode:"string"}
                        ]}                                                            
					]},
					{name:"general",caption:"资金投入情况",width:60,mode:"string",children:[
						{name:"general",caption:"",width:60,mode:"string",children:[
                            {name:"list[index].capitalInvested.allInvestmentCount",caption:"概算总投资(万元)",width:130,mode:"string"},
                        ]},
						{name:"general",caption:"财政资金投入(万元)",width:60,mode:"string",children:[
							{name:"list[index].capitalInvested.centralProvinceInvested",caption:"中央/省级投入(万元)",width:130,mode:"string"},
							{name:"list[index].capitalInvested.municipalityInvested",caption:"市/州投入(万元)",width:120,mode:"string"},
							{name:"list[index].capitalInvested.countyInvested",caption:"县级投入(万元)",width:120,mode:"string"}
						]},
						{name:"general",caption:"社会资金投入",width:60,mode:"string",children:[
							{name:"list[index].capitalInvested.financialInvested",caption:"金融投入(万元)",width:120,mode:"string"},
							{name:"list[index].capitalInvested.industryAndCommerceInvested",caption:"工商资本投入(万元)",width:140,mode:"string"},
							{name:"list[index].capitalInvested.otherInvested",caption:"其他(万元)",width:140,mode:"string"}                                                           
						]},
						{name:"general",caption:"农民投入",width:60,mode:"string",children:[
							{name:"list[index].capitalInvested.farmerInvested",caption:"自筹资金(万元)",width:120,mode:"string"}
						]}
					]},
					{name:"general",caption:"农村经济收入",width:60,mode:"string",children:[
						{name:"general",caption:"",width:60,mode:"string",children:[
							{name:"list[index].farmerPerIncomeInfo.agriculturalIncome",caption:"农业主导产业收入(万元)",width:100,mode:"string"},
							{name:"list[index].farmerPerIncomeInfo.householdIncome",caption:"农村家庭经营性收入(万元)",width:100,mode:"string"},
							{name:"list[index].farmerPerIncomeInfo.farmerPerIncome",caption:"农村居民人均可支配收入(万元)",width:100,mode:"string"},
							{name:"list[index].farmerPerIncomeInfo.villageCollectiveIncome",caption:"村集体经济收入(万元)",width:100,mode:"string"}
						]}  		                                                                  
					]}
					 
		   		];
	return columns;
}





</script>