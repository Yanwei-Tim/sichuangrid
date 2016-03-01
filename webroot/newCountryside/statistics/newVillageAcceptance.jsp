<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<%@page import="com.tianque.plugin.analysisNew.util.AnalyseUtilNew"%>
<style>
</style>
<div id="nav" class="ui-corner-all" style="margin-left: 9px;">
		 <label style="float:left;padding:0 10px;line-height:25px;">时间：</label>
		<select name="year" id="year" onchange="" style="float:left;">
			
        </select>
        <label style="float:left;padding:0 10px;line-height:25px;">年</label>
        <a id="searchList" href="javascript:void(0)"><span>查询</span></a>
        
</div>
<div class="content" >
	<div id="gridbox" class="SigmaReport"></div>
</div>
<div id="zongkuangPrint"></div>

<script type="text/javascript">
var grid = null;
function onOrgChanged(){
	$.ajax({
		url:'${path}/newVillageAcceptanceManage/findNewVillageAssessmentVoForList.action',
		data:{"year":$("#year").val()},
		success:function(data){
			if(data == null   || (data[0]!= null &&data[0].organization == null)){
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

$(document).ready(function(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYear.action",
		success:function(responseData){
			var dataLength = responseData.length;
			for(var i = 0;i<dataLength;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			onOrgChanged();
		}
	});
	
	var columns =getColumn() ;
	$.gridboxHeight();
	var context = {};
	grid = new SigmaReport("gridbox",context,columns,null,null,null,null,null);
	$("#gridbox").css({"overflow": "auto", "height": document.documentElement.offsetHeight - ($.browser.msie ? 240 : 216)});
	
	$("#title_gridbox").children().remove();
	$("#title_gridbox").html("幸福美丽新村建设验收表");//<a href='javascript:;' class='print' title='打印'></a>
	
	$("#searchList").click(function(){
		onOrgChanged();
	});

// 	$(".print").click(function(){
// 		$("#zongkuangPrint").createDialog({
// 			width:1200,
// 			height:600,
// 			title:document.title,
// 			url:'${path}/newCountryside/statistics/newVillageAcceptancePrint.jsp',
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
		onOrgChanged();
	});
	
	
	
	function print(){
		$("#zongkuangPrint").printArea();
		$("#zongkuangPrint").dialog("close");
	}
	
// 	$('#title_gridbox,#head_gridbox,#body_gridbox').width( $('#tHead_gridbox').width() )

});

function getColumn(){
	var columns = [		
					{name:"organization.orgName",caption:"区县",width:80,mode:"string"}, 
					{name:"general",caption:"培育有优势特色产业",width:60,mode:"string",children:[
						{name:"characteristicPlantingStr",caption:"完成数",width:60,mode:"string"},
						{name:"characteristicPlantingFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"农业主导产业收入占农村家庭经营收入的比重",width:60,mode:"string",children:[
					    {name:"proportionOfIncomeStr",caption:"完成数",width:60,mode:"string"},
					    {name:"proportionOfIncomeFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"人均可支配收入",width:60,mode:"string",children:[
					    {name:"farmerPerIncome",caption:"完成数",width:60,mode:"string"},
					    {name:"farmerPerIncomeFraction",caption:"得分",width:60,mode:"string"}
					]},
					{name:"general",caption:"人均安全住房面积",width:60,mode:"string",children:[
					    {name:"capitaHousingArea",caption:"完成数",width:60,mode:"string"},
					    {name:"capitaHousingAreaFraction",caption:"得分",width:60,mode:"string"}
					]},
					{name:"general",caption:"无房户、危房户、住房困难户住房问题全部解决",width:60,mode:"string",children:[
					    {name:"houseProblemCountStr",caption:"完成数",width:60,mode:"string"},
					    {name:"houseProblemFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"九年义务教育目标人群全覆盖",width:60,mode:"string",children:[
						{name:"compulsoryEducationStr",caption:"完成数",width:60,mode:"string"},
						{name:"compulsoryEducationFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"新农合参保率",width:60,mode:"string",children:[
						{name:"insuredProportionStr",caption:"完成数",width:60,mode:"string"},
						{name:"insuredProportionFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"新农保应保尽保",width:60,mode:"string",children:[
						{name:"hasBuyInsuranceStr",caption:"完成数",width:60,mode:"string"},
						{name:"hasBuyInsuranceFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"每年组织群众性文体活动",width:60,mode:"string",children:[
						{name:"recreationalActivities",caption:"完成数",width:60,mode:"string"},
						{name:"recreationalActivitiesFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"公共活动服务中心设施面积",width:60,mode:"string",children:[
						{name:"socialWorkCenterArea",caption:"完成数",width:60,mode:"string"},
						{name:"socialWorkCenterAreaFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"基层党组织/党员调查满意度",width:60,mode:"string",children:[
						{name:"surveySatisfactionStr",caption:"完成数",width:60,mode:"string"},
						{name:"surveySatisfactionFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"三务公开满意度",width:60,mode:"string",children:[
						{name:"threeSatisfactionStr",caption:"完成数",width:60,mode:"string"},
						{name:"threeSatisfactionFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"新村建设全覆盖",width:60,mode:"string",children:[
						{name:"isAllStandingStr",caption:"完成数",width:60,mode:"string"},
						{name:"isAllStandingFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"通村通组道路硬化率",width:60,mode:"string",children:[
						{name:"villageHardenedRoadStr",caption:"完成数",width:60,mode:"string"},
						{name:"villageHardenedFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"安全饮水全覆盖",width:60,mode:"string",children:[
						{name:"drinkingWaterStr",caption:"完成数",width:60,mode:"string"},
						{name:"drinkingWaterNumFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"电网改造全覆盖",width:60,mode:"string",children:[
						{name:"isPowerGridStr",caption:"是否完成",width:60,mode:"string"},
						{name:"isPowerGridFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"面源污染有效治理",width:60,mode:"string",children:[
						{name:"treatmentPollutionStr",caption:"完成数",width:60,mode:"string"},
						{name:"treatmentPollutionFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"生活垃圾处理全覆盖",width:60,mode:"string",children:[
						{name:"garbageDisposalStr",caption:"完成数",width:60,mode:"string"},
						{name:"garbageDisposalFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"生活污水处理覆盖率",width:60,mode:"string",children:[
						{name:"sanitarySewageStr",caption:"完成数",width:60,mode:"string"},
						{name:"sanitarySewageFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"general",caption:"农村院落整治全覆盖",width:60,mode:"string",children:[
						{name:"involveHouseStr",caption:"完成数",width:60,mode:"string"},
						{name:"involveHouseCountFraction",caption:"得分",width:60,mode:"string"}
					]}, 
					{name:"newVillageFraction",caption:"综合得分",width:60,mode:"string"},
					{name:"isAdoptStr",caption:"验收结果",width:60,mode:"string"}
					 
		   		];
	return columns;
}





</script>