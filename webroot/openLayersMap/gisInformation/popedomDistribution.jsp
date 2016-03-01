<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>

<div id="jurisdictionDistributionBox" class="tabChange">
	<div>
		<div class="new_personList">
			<ul class="new_personal_list" id="distributionList">
				<li class="current"  >
					<a href="javascript:;" id="keyPerson"  class="keyPersonTwoDimensionMap" >人口信息</a>
					<input id="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_STATISTIC'/>" type="hidden" >
				</li>
				<li >
					<a href="javascript:;" id="keyPlaces" class="keyPlaceTwoDimensionMap">组织场所</a>
					<input  id="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_STATISTIC'/>" type="hidden" >
				</li>
				<li >
					<a href="javascript:;"  class="issueTwoDimensionMap">事件处理</a>
					<input id="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_STATISTIC'/>" type="hidden" >
				</li>
				<li >
					<a href="javascript:;" class="housePropertyTwoDimensionMap">房屋信息</a>
					<input id="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_STATISTIC'/>" type="hidden" >
				</li>
			</ul>
		</div>
		<div id="personTool" class="personTool">
			<a title="柱状图" href="javascript:;" class="columnChart"></a>
			<a title="饼状图" href="javascript:;" class="pieChart"></a>
		</div>
		<div class="new_personal_tableCon">
			<table class="new_personal_table" id="AreaDistributedPerson"></table>
			<table class="new_personal_table hidden" id="AreaDistributedPlace"></table>
			<table class="new_personal_table hidden" id="AreaDistributedIssue"></table>
			<table class="new_personal_table hidden" id="AreaDistributedHourse"></table>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$("#personTool").hide();
	loadAreaDistributedList("person")
});

	//柱状图
	$(".columnChart").click(function(){
		$("#chartDialog").createDialog({
			width:920,
			height:500,
			title:"柱状图显示",
			zIndex:1007,
			url:"${path}/openLayersMap/columnChart/columnChart.jsp?modeType="+$('#modeType').val(),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	//饼状图
	$(".pieChart").click(function(){
		$("#chartDialog").createDialog({
			width:650,
			height:500,
			title:"饼状图显示",
			zIndex:1007,
			url:"${path}/openLayersMap/columnChart/pieChart.jsp?orgId="+$('#currOrgId').val()+"&modeType="+$('#modeType').val(),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	
	//加载辖区分布列表
	function loadAreaDistributedList(innerKey){
		$.ajax({
			async: false,
			url: "${path }/gis/gisTypeManage/getNeedGisTypeManagesByInnerTypeAndOrgId.action",
			data:{
				"gisTypeManage.innerKey":innerKey,
				"organization.id":$('#currOrgId').val()
			},
			success:function(responseData){
				var className;
				if(innerKey=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PLACE_MODE'/>"){
					className="keyPlaceDetail";
				}else if(innerKey=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERSONAL_MODE'/>"){
					className="keyPersonDetail";
				}else if(innerKey=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@ISSUE_MODE'/>"){
					className="issueDetail";
				}else{
					className="houseDetail";
				}
				
				var str="";
				if(innerKey=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PLACE_MODE'/>"){
					for(var i=0;i<responseData.length;i++){//重点场所
						str+=assemblyData(responseData[i].gisTypeManage.key,responseData[i].gisTypeManage.name,responseData[i].sumNum,className);
					}
					$("#AreaDistributedPlace").html(str);
				}else if(innerKey=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERSONAL_MODE'/>"){
					for(var i=0;i<responseData.length;i++){//重点人员
						str+=assemblyData(responseData[i].gisTypeManage.tableName,responseData[i].gisTypeManage.name,responseData[i].sumNum,className);
					}
					$("#AreaDistributedPerson").html(str);
				}else if(innerKey=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@ISSUE_MODE'/>"){
					for(var i=0;i<responseData.length;i++){//事件
						str+=assemblyData(responseData[i].typeName,responseData[i].moduleName,responseData[i].sumNum,className);
					}
					$("#AreaDistributedIssue").html(str);
					if(!isGridDownOrganization()){//网格层级时隐藏下辖信息
						$("#"+"<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@FORTHING'/>").hide();
						$("#"+"<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@ALREADYTHING'/>").hide();
					}
				}else{
					for(var i=0;i<responseData.length;i++){//房屋信息
						str+=assemblyData(responseData[i].typeName,responseData[i].moduleName,responseData[i].sumNum,className);
					}
					$("#AreaDistributedHourse").html(str);
				}
				//$(".new_personal_table tr:even").addClass("even").removeClass("odd");
				//$(".new_personal_table tr:odd").addClass("odd").removeClass("even");;
				$(".new_personal_table tr").each(function(index){
					var personId=$(this).attr("id");
					if(personId=="person"||personId=="keyPerson"||personId=="careobject"||personId=="NURTURESWOMEN"
							||personId=='totalHouse'||personId=='totalRentHouse'||personId=='totalBuilding'
							||personId=='actualcompany'||personId=='keyPlace'||personId=='carePerson'||personId=='otherPerson'){
						
						$(this).find("td").eq(2).removeClass("detailInfo details").find("span").remove();
						$(this).find("td").eq(0).find("span").parent().css("cursor","auto");
						$(this).addClass("even");
						$(this).find("td").eq(2).empty();
						$(this).find("td").eq(2).html("<span id='personTool' class='personTool'>"
								+"<a title='柱状图' href='javascript:;' onclick='showColumnChart("+personId+")' class='columnChart'></a>"
								+"<a title='饼状图' href='javascript:;' onclick='showPieChart("+personId+")' class='pieChart'></a>"
								+"</span>");
					}
				})
			}
		});
	}
	
	//拼装数据
	function assemblyData(id,name,sumNum,className){
		var  str="<tr  id='"+id+"' >"
				+"<td class='msgL'><span class='text'>"+name+"</span></td> "
				+"<td class='dataPoint'><span class='num'>"+sumNum+"</span><span id='units'></span></td>"
				+"<td class='detailInfo details' ><span class='"+className+"'><a href='javascript:void(0)'>详情</a></span></td></tr>";
		return str;
	}
	
	function showColumnChart(personId){
		var personType=personId.id
		if(personType=="keyPerson"){
			$('#modeType').val("keyPersonMapStatisticService");
		}else{
			$('#modeType').val("personMapStatisticService");
		}
		$("#chartDialog").createDialog({
			width:800,
			height:380,
			title:"柱状图显示",
			zIndex:1007,
			url:"${path}/openLayersMap/columnChart/columnChart.jsp?modeType="+$('#modeType').val(),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	
	function showPieChart(personId){
		var personType=personId.id
		if(personType=="keyPerson"){
			$('#modeType').val("keyPersonMapStatisticService");
		}else{
			$('#modeType').val("personMapStatisticService");
		}
		$("#chartDialog").createDialog({
			width:650,
			height:530,
			title:"饼状图显示",
			zIndex:1007,
			url:"${path}/openLayersMap/columnChart/pieChart.jsp?orgId="+$('#currOrgId').val()+"&modeType="+$('#modeType').val(),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	
	$("#jurisdictionDistribution").click(function(){//点击辖区分布
		$("#modeType").val($(".new_personal_list>li.current").children("input").attr("id"));
		if($(".new_personal_list>li.current").children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_STATISTIC'/>"){
			var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERSONAL_MODE'/>"; 
		}else if($(".new_personal_list>li.current").children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_STATISTIC'/>"){
			var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PLACE_MODE'/>";
		}else if($(".new_personal_list>li.current").children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_STATISTIC'/>"){
			var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@ISSUE_MODE'/>";
		}else if($(".new_personal_list>li.current").children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_STATISTIC'/>"){
			var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@HOURSE_MODE'/>";
		}
		if(innerKey=="person"){
			$("#personTool").hide();
		}
		loadAreaDistributedList(innerKey);
	})
	
	
	$(".new_personal_list>li").each(function(index){
			$(this).click(function(){
				if($(this).children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_STATISTIC'/>"){
					var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PLACE_MODE'/>";
				}else if($(this).children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_STATISTIC'/>"){
					var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERSONAL_MODE'/>";
				}else if($(this).children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_STATISTIC'/>"){
					var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@ISSUE_MODE'/>";
				}else if($(this).children("input").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_STATISTIC'/>"){
					var innerKey="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@HOURSE_MODE'/>";
				}
				if(innerKey=="person"){
					$("#personTool").hide();
				}else{
					$("#personTool").show();
				}
				loadAreaDistributedList(innerKey);
 				$("#modeType").val($(this).children("input").attr("id"));
				$(this).addClass("current").siblings().removeClass("current");
				$(".new_personal_tableCon").children("table").hide().eq(index).show().siblings().hide();
			})
		});
	
	$(".new_personal_table tr").hover(function(){
		$(this).addClass("tableCur").siblings().removeClass("tableCur");
	},function(){
		$(this).removeClass("tableCur");
	});

	$(".new_personal_table").delegate("tr", "hover", function(){
		$(this).toggleClass("tableCur");
	});


	$("#AreaDistributedPerson").delegate("span.keyPersonDetail","click",function(){//重点人员辖区分布详情点击
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		$("#map").TqMap("removeAllFeatures");//清除二维图上的面
		$("#map").TqMap("deleteAllPopupText");
		$(".gis_zoom_button").addClass("hidden");//清除图例按钮
		$(".gis_zoom_content").hide();//清除图例
		clearMarkersByMarkerId_objectName("hourse");//清除楼宇
		clearMarkersByMarkerId_objectName("common");
		var keyPersonType=$(this).parent().parent().attr("id");
		personType=keyPersonType;
		$("#detailClick").attr("value",true);
		var keyType="";
		var modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_SEARCH'/>";
		var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_DETAILVIEW'/>";
		var mainTableName="keyPerson";
		if(personType=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@keyPersonalType.get("overseaStaff")'/>"
				||personType=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@keyPersonalType.get("unsettledPopulation")'/>"
					||personType=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@keyPersonalType.get("floatingPopulation")'/>"
						||personType=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@keyPersonalType.get("householdStaff")'/>"){
				modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_SEARCH'/>";
				modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_DETAILVIEW'/>";
				mainTableName="person";
			}
		var url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
		var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
		loadTabListInfo("${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?type="+personType+"&mainTableName="+mainTableName+"&childTableName="+personType+"&keyType=null&modeType="+modeType+"&url="+url+"&functionType="+functionType
				+"&modeTypeDetailView="+modeTypeDetailView);
	});
	$("#AreaDistributedPlace").delegate("span.keyPlaceDetail","click",function(){//重点场所辖区分布详情点击
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		$("#map").TqMap("removeAllFeatures");//清除二维图上的面
		$("#map").TqMap("deleteAllPopupText");
		$(".gis_zoom_button").addClass("hidden");//清除图例按钮
		$(".gis_zoom_content").hide();//清除图例
		$("#detailClick").attr("value",true);
		locationType=$(this).parent().parent().attr("id");
		clearMarkersByMarkerId_objectName("common");
		var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
		var url="${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action";
		var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_SEARCH'/>";
		var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_DETAILVIEW'/>";
		loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName=keyPlaces&childTableName=keyPlaces&url='+url+'&modeType='+modeType+"&keyType="+locationType+'&type='+locationType+"&functionType="+functionType+
					"&modeTypeDetailView="+modeTypeDetailView);//乡镇级别的公用页面
	});
	$("#AreaDistributedIssue").delegate("span.issueDetail","click",function(){//事件辖区分布详情点击
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		$("#map").TqMap("removeAllFeatures");//清除二维图上的面
		$("#map").TqMap("deleteAllPopupText");
		$(".gis_zoom_button").addClass("hidden");//清除图例按钮
		$(".gis_zoom_content").hide();//清除图例
		clearMarkersByMarkerId_objectName("hourse");//清除楼宇
		clearMarkersByMarkerId_objectName("common");
		$("#detailClick").attr("value",true);
		issueType=$(this).parent().parent().attr("id");
		var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
		var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_SEARCH'/>";
		var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_DETAILVIEW'/>";
		url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
		loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?isSerachMode=true&mainTableName=issues&sordField=iu.centerlon&modeType='+modeType+'&url='+url+'&type='+issueType+"&functionType="+functionType
				+"&modeTypeDetailView="+modeTypeDetailView+"&flag=true");
	});


	$("#AreaDistributedHourse").delegate("span.houseDetail","click",function(){//房屋信息分布详情点击
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		$("#map").TqMap("removeAllFeatures");//清除二维图上的面
		$("#map").TqMap("deleteAllPopupText");
		$(".gis_zoom_button").addClass("hidden");//清除图例按钮
		$(".gis_zoom_content").hide();//清除图例
		clearMarkersByMarkerId_objectName("hourse");//清除楼宇
		clearMarkersByMarkerId_objectName("common");
		$("#detailClick").attr("value",true);
		var houseType=$(this).parent().parent().attr("id");
		var modeType="";
		var mainTableName="";
		var childTableName="";
		var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
		var url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
		if(houseType=="HOUSEINFO"){
			childTableName="houseInfo";
		}else if(houseType=="RENTALHOUSE"){
			childTableName="rentalHouse";
		}
		modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_SEARCH'/>";
		mainTableName="houseInfo";
		var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_DETAILVIEW'/>";
		loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName='+mainTableName+'&modeType='+modeType+'&url='+url+'&type='+houseType+"&functionType="+functionType
					+"&modeTypeDetailView="+modeTypeDetailView+"&keyType="+houseType+"&childTableName="+childTableName);
	});

	//辖区分布人员检索
	$("#AreaDistributedPerson").delegate("td.msgL","click",function(){
		var text=$(this).parent().find(".text").text();
		var type=$(this).parent().attr("id");
		if(type=="person"||type=="keyPerson"){
			return;
		}
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		$("#person-type").val(type);
		$("#clickType").attr("value",type);
		personType=type;
		personOrPlaceName=text;
		allTypeName=personOrPlaceName;
		$("#detailClick").attr("value",true);
		$(this).parent().addClass("popClick").siblings().removeClass("popClick");
		$("#map").TqMap("deletePopup");
		$("#map").TqMap("removeAllFeatures");//清除二维图上的面
		$("#map").TqMap("deleteAllPopupText");
		clearMarkersByMarkerId_objectName("hourse");//清除楼宇
		clearMarkersByMarkerId_objectName("common");
		if(isVillageDownOrganization()){
			//$("#areaInfo").load("/openLayersMap/personnel/keyPersonForJurisdictionDistribution.jsp?flag=true&orgId="+$('#currOrgId').val()+"&type="+type);
		}else{
			var modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_SEARCH'/>";
			var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_DETAILVIEW'/>";
			var url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
			var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
			loadTabListInfo("${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?type="+personType+"&mainTableName=keyPerson&childTableName="+personType+"&keyType=null&modeType="+modeType+"&url="+url+"&functionType="+functionType
					+"&modeTypeDetailView="+modeTypeDetailView);
		}
	})

	//辖区分布场所检索
	$("#AreaDistributedPlace").delegate("td.msgL","click",function(){
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		var text=$(this).parent().find(".text").text();
		var type=$(this).parent().attr("id");
		$("#clickType").attr("value",type);
		locationType=type;
		personOrPlaceName=text;
		allTypeName=personOrPlaceName;
		$("#detailClick").attr("value",true);
		$(this).parent().addClass("popClick").siblings().removeClass("popClick");
		$("#map").TqMap("deletePopup");
		$("#map").TqMap("removeAllFeatures");//清除二维图上的面
		$("#map").TqMap("deleteAllPopupText");
		clearMarkersByMarkerId_objectName("common");
		var url="";
		var modeType="";
		if(isVillageDownOrganization()){
			loadTabListInfo("${path}/openLayersMap/place/keyPlaceForJurisdictionDistribution.jsp?placeType="+locationType);
		}else{
			var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
			url="${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action";
			modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_SEARCH'/>";
			var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_DETAILVIEW'/>";
			loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName=keyPlaces&childTableName=keyPlaces&url='+url+'&modeType='+modeType+"&keyType="+locationType+'&type='+locationType+"&functionType="+functionType+"&modeTypeDetailView="+modeTypeDetailView);//乡镇级别的公用页面
		}
	})

	//辖区分布事件搜索
	$("#AreaDistributedIssue").delegate("td.msgL","click",function(){
		var text=$(this).parent().find(".text").text();
		issueTypeName = text;
		allTypeName = issueTypeName;
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		var type=$(this).parent().attr("id");
		$("#issueType").attr("value",type);
		$("#clickType").attr("value",type);
		$("#detailClick").attr("value",true);
		$(this).parent().addClass("popClick").siblings().removeClass("popClick");
		$("#map").TqMap("deletePopup");
		$("#map").TqMap("removeAllFeatures");//清除二维图上的面
		clearMarkersByMarkerId_objectName("hourse");//清除楼宇
		if(isVillageDownOrganization()){
			loadTabListInfo('/openLayersMap/issue/issueForJurisdictionDistribution.jsp?flag=true&type='+type);
		}else{
			var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
			var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_SEARCH'/>";
			var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_DETAILVIEW'/>";
			var url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
			loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName=issuesNew&sordField=iu.centerlon&modeType='+modeType+'&url='+url+'&type='+type+"&functionType="+functionType
					+"&modeTypeDetailView="+modeTypeDetailView);
		}
	})


	//辖区分布房屋信息搜索
	$("#AreaDistributedHourse").delegate("td.msgL","click",function(){
		IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
		var text=$(this).parent().find(".text").text();
		housePropertyTypeName = text;
		allTypeName=housePropertyTypeName;
		var type=$(this).parent().attr("id");
		$("#detailClick").attr("value",true);
		$("#clickType").attr("value",type);
		$(this).parent().addClass("popClick").siblings().removeClass("popClick");
		$("#houseType").val(type);
		var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
		if(isVillageDownOrganization()){
			if(type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@BOUNDBUILDING'/>"
					|| type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@UNBOUNDBUILDING'/>"){
				$("#map").TqMap("removeAllFeatures");
				$("#map").TqMap("deleteAllPopupText");
				clearMarkersByMarkerId_objectName("hourse");
				loadTabListInfo('/openLayersMap/houseProperty/boundBuildDatasForJurisdictionDistribution.jsp?flag=true&type='+type);
			}else if(type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@BOUNDHOUSE'/>" 
					|| type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@UNBOUNDHOUSE'/>"){
				$("#map").TqMap("removeAllFeatures");
				$("#map").TqMap("deleteAllPopupText");
				clearMarkersByMarkerId_objectName("hourse");//清除楼宇
				loadTabListInfo('/openLayersMap/houseProperty/housePropertyForJurisdictionDistribution.jsp?flag=true&type='+type);
			}
		}else{
			var url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
			var modeType=""
			$("#map").TqMap("removeAllFeatures");//清除二维图上的面
			$("#map").TqMap("deleteAllPopupText");
			clearMarkersByMarkerId_objectName("hourse");//清除楼宇
			clearMarkersByMarkerId_objectName("common");
			var mainTableName="";
			if(type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@BOUNDBUILDING'/>"
					|| type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@UNBOUNDBUILDING'/>"){
				modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@BUILDDATA_SEARCH'/>";
				var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@BUILDDATA_DETAILVIEW'/>";
				mainTableName="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@BUILD_TABLENAME'/>";
				loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName='+mainTableName+'&modeType='+modeType+'&url='+url+'&type='+type+"&functionType="+functionType
						+"&modeTypeDetailView="+modeTypeDetailView);
				
			}else if(type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@BOUNDHOUSE'/>" 
						|| type=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@UNBOUNDHOUSE'/>"){
				modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_SEARCH'/>";
				mainTableName="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@HOUSEINFO_TABLENAME'/>";
					var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_DETAILVIEW'/>";
				loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName='+mainTableName+'&modeType='+modeType+'&url='+url+'&type='+type+"&functionType="+functionType
						+"&modeTypeDetailView="+modeTypeDetailView);
			}
		}
	})
</script>