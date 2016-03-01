<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<jsp:include page="/openLayersMap/includes/jsInclude.jsp"></jsp:include>
<jsp:include page="/openLayersMap/information/gisInformation.jsp"></jsp:include>
<jsp:include page="/openLayersMap/detailsCommon/manipulateCommon.jsp"></jsp:include>
<script type="text/javascript" src="${resource_path}/resource/openLayersMap/js/index.js" ></script>
<style type="text/css">
	.ui-layout-center{width:auto !important;padding:0;}
	.content{margin:0;}
	.ui-layout-south{display:none !important;}
	.ui-layout-south,.common-menu-button{display:none;}
</style>
<div id="TwoMapView" style="position:relative;z-index:0;">
	<div class="gisView">
		<div class="gisContainer" id="gisContainer">
			<jsp:include page="/openLayersMap/gisToolbar/gisToolBar.jsp"></jsp:include>
			<jsp:include page="/openLayersMap/gisToolbar/mapLayer.jsp"></jsp:include>
			<div class="gis_zoom">
				<a href="javascript:;" class="gis_zoom_button hidden">图例</a>
				<div class="gis_zoom_content hidden">
					<ul class="legendTitle">
						<li>辖区名称</li>
						<li>颜色</li>
						<li>数量</li>
					</ul>
					<div class="legendContent"></div>
				</div>
			</div>
			<div id="map"></div>
			<div id="mapChangeDiv"></div>
			<div id="pointNorthDiv"></div>
			<div class="gis_zoomOropen">
				<a href="javaScript:void(0);" title="显示/隐藏侧边栏" class="zoom openZoom"></a>
			</div>
		</div>
		<jsp:include page="/openLayersMap/gisInformation/gisInfo.jsp"></jsp:include>
	</div>
	
	<div id="housePropertyDialog"></div>
	<div id="bindingHouseDialog"></div>
	<div id="houseInfoDialog"></div>
	<div id="budlingDialog"></div>
	<div id="bindingPlaceDialog"></div>
	<div id="buildLayoutDialog"></div>
	<div id="builddatasMaintanceDialog"></div>
	<div id="gisTypeManageDialog"></div>
	<div id="editGisTypeManageDiv"></div>
	<div id="commonDialog"></div>
	<div id="chartDialog"></div>
	<div id="videoDialog"></div>
	<div id="videotapeDialog"></div>
	<div id="360vistaDialog"></div>
	<div>
		<input type="hidden" id="boundModuleType"/>
		<input type="hidden" id="selectOrgLevel"/>
		<input type="hidden" id="mapType" value="map2D"/>
		<div id="infoList"></div>
		<input type="hidden" id="isViewHourse"/>
	</div>
	<div id="jGrowl"></div>
	<div id="globalOrgBox" class="" style="min-height: 52px; height: auto; width: 526px; display: none; " scrolltop="0" scrollleft="0"></div>
	<input type="hidden" id="currOrgId"
		 <s:if test="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgType().getInternalId()==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG">
			 	 orglevelinternalid="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgLevel().getInternalId()" />" 
				 text="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgName()" />" 
				 leafNum="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getSubCount()" />" 
				 value='<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getId()"/>' />
				 orgInternalCode='<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgInternalCode()" />'
		 </s:if>
		 <s:else>
			 	orglevelinternalid="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().getInternalId()" />" 
			 	text="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()" />" 
				leafNum="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getSubCount()" />" 
			 	value='<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>' 
			 	orgInternalCode='<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgInternalCode()" />'
		</s:else>
	 />
</div>

<script type="text/javascript">
var userOrgInternalCode = $("#currOrgId").attr("orgInternalCode");
var mapMousemoveLon=null;//地图鼠标跟随的经度
var mapMousemoveLat=null;//地图鼠标跟随的纬度
var LOCATIONVO_id,LOCATIONVO_type,LOCATIONVO_index;//建筑物信息的id,type,index
var _mode;//模式
var searchArray=new Array();//周边元素maker数组
var searchIndex=0;//周边元素maker数组下标
var locationType="school";//重点场所类型
var personType="rectificativePerson";//特殊人群类型
var prevsCenterLonLat=null;//记录地图上次拖动的中心点
var prevsZoom=null;//记录地图上次拖动的地图级别
var lastZoom=null;
var IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开关设置，默认开启
var bindInWfs=false;//是否绑定在wfs
var prevsOrgId=null;
var isTransformat = false;//是否需要转换坐标（测量，搜素范围）
var deltLen = 2.518896585495241;//25D地图测距的比率，即在25D地图上测距100m等于实际测距的deltLen*100m
var gisType = "2D";
var IS_GISTYPE_CHANGE_END = true;//判断地图切换是否结束（为当前25D地图和2D地图的放大层级相差一级而设置）
var Booleans={
		init:function(){
			$.extend(true,Booleans,{
				USE_CURRENT_SCREEN :true,//是否使用当前的屏幕范围，主要与当前屏幕范围查询进行区分
				IS_ORG_CHANGE :false     //组织机构是否改变
			})
		}
	}
Booleans.init();
$(function(){
	var documentHeight=document.documentElement.clientHeight-$(".ui-layout-north").outerHeight();
	$(".ui-layout-center,.slideResizer").height(documentHeight-5);
	//页面高度控制区
	var documentHeight=function(){
		var timer;
		function currentHeight(){
			var bodyHeight=document.documentElement.clientHeight||document.body.clientHeight;
			var systemHeaderHeight=$(".systemHeader:visible").height();
			var gisToolsHeight=$(".gis_tools_info").height();
			var layoutCenterHeight=bodyHeight-systemHeaderHeight;
			var newPersonTitleHeight=$(".new_person_title").height();
			var newPersonListHeight=$(".new_personal_list").height();
			var tableConHeight=layoutCenterHeight-newPersonTitleHeight-newPersonListHeight-20;
			$(".ui-layout-center,#gisContainer").height(layoutCenterHeight);
			$("#map").height(layoutCenterHeight-30);
			$(".new_personal_tableCon").height(tableConHeight-10);
		}
		currentHeight();
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setTimeout(function(){
				currentHeight();
			},500);
		})
	}();
	loadMap();//加载地图
	initOrg();//初始化组织机构
	initGisTypeTool({gisType:gisType,showType:["2D","3D"]});//加载图层切换工具
	//缩放控制
	$(".gis_zoomOropen .zoom").on("click",function(){
		var self=$(this);
		if($(".gisLeft").is(":visible")){
			$(".gisLeft").hide();
			$(this).addClass("gis_left_zoomOut");
			self.addClass("closeZoom").removeClass("openZoom");
			$(".gisContainer").css("margin-left","0");
			$(".periphery_button").css("left",-2);
		}else{
			$(".gisLeft").show();
			self.addClass("openZoom").removeClass("closeZoom");
			$(this).removeClass("gis_left_zoomOut");
			$(".periphery_button").css("left","");
			$(".gisContainer").css("margin-left","268px");
		}
		$("#map").TqMap("get","map").updateSize();//修改地图大小
	});
})

//解除建筑物绑定
function cancelBindingBuilding(buildDataId,id,lon,lat){
	$("#houseMsg").empty();//popup中的建筑物名称显示为空
	$("#showTitle").empty();//popup中的建筑物显示为空
	$("#buildLayout").empty();//查看布局按钮不显示
	$.ajax({
	     async: false,
	     datatype: "json",
	     url:"${path}/openLayersMap/twoDimensionMapManageManage/unBoundTwoDimensionMap.action",
	     data:{
	    	 "modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@BUILDDATA_BING'/>",
	    	 "id":id,
	    	 "gisBoundVo.orgId":$("#currOrgId").val()
		 },
         success: function(data){
			if(data!=null){
				$("#map").TqMap("deleteMarkerByMarkerId",{markerId:buildDataId+"_hourse"});
				if(bindInWfs==false || $("#isViewHourse").val()=="true"){
					$("#map").TqMap("addMarker",{
						iconUrl:PATH+"/resource/openLayersMap/images/house-3.png",
						markerW:40,
						markerH:30,
						lon:lon,
						lat:lat,
						markerId:buildDataId+"_hourse",
						data:{lon:lon,lat:lat}
					}); 
					$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:hourseClick});
				}
				$.messageBox({message:"解除绑定成功!"});
				$("#map").TqMap("deletePopup");//删除popup
				$("#map").data("buildingNames",null);
			}
 	    },
 	    error: function(XMLHttpRequest, textStatus, errorThrown){
 	    	$.messageBox({message:"提交错误!"});
 	    }
 	});
}


//查询某建筑物下的房屋列表信息
function findHourseListByBuildDataId(buildDataId,houseNum){
	if(houseNum==0){
		$(".tabChange").hide();
		return;
	}
	$("#map").TqMap("deletePopup");
	clearMarkersByMarkerId_objectName("common");
	var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_SEARCH'/>";
	var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_DETAILVIEW'/>";
	var url='${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByBuildingId.action';
	loadTabListInfo("/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName=houseInfo&buildDataId="+buildDataId+"&functionType=refineSearch"+"&modeType="+modeType+"&modeTypeDetailView="+modeTypeDetailView+"&isSerachMode=true");
}


//共有人员列表
function findPersonList(buildDataId,centerLon,centerLat,houseNum,personType){
	if(personSum==0){
		$(".tabChange").hide();
		return;
	}
	$("#map").TqMap("deletePopup");
	clearMarkersByMarkerId_objectName("common");
	var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@PERSON_DETAILVIEW'/>";
	var mainTableName="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PERSONAL_MODE'/>";
	var url='${path}/gis/twoDimensionMapSearchCommonManage/findPersonByOrgIdAndTypeName.action';
	loadTabListInfo("/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+mainTableName+"&buildDataId="+buildDataId+"&functionType=refineSearch"+"&type="+personType+"&modeTypeDetailView="+modeTypeDetailView);
}

//加载辖区分布信息 
function loadPopedomDistributionInfo(){
	if($("#jurisdictionDistributionBox:visible")[0]){//当跳入下一层级时如果辖区分布打开则重新加载列表
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
		/**默认进来后不去加载辖区信息*/
		//loadAreaDistributedList(innerKey);
		//清除掉辖区分布的数据
		$("#AreaDistributedPerson").html("");
		$("#AreaDistributedPlace").html("");
		$("#AreaDistributedIssue").html("");
		$("#AreaDistributedHourse").html("");
		$("#AreaDistributedGirdTeam").html("");
		$("#jurisdictionDistribution").click();
	}
}

//场所列表
function getKeyPlaceList(buildDataId,centerLon,centerLat,type,newPlaceMainTableName) {
	$("#map").TqMap("deletePopup");
	clearMarkersByMarkerId_objectName("common");
	var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_SEARCH'/>";
	var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_DETAILVIEW'/>";
	var url='${path}/gis/twoDimensionMapSearchCommonManage/findBoundedKeyPalcesByOrgIdAndTypeName.action';
	loadTabListInfo("/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+newPlaceMainTableName+"&childTableName=keyPlaces&type="+type+"&centerLon="+centerLon+"&centerLat="+centerLat+"&keyType="+type+"&functionType=refineSearch"+"&modeType="+modeType+"&modeTypeDetailView="+modeTypeDetailView+"&buildDataId="+buildDataId);
}

function isTownDownOrganization(){
	return $("#currOrgId").attr("orgLevelInternalId") > <s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>;
}

function isVillageDownOrganization(){
	return $("#currOrgId").attr("orgLevelInternalId") > <s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
}

function isGridDownOrganization(){
	return $("#currOrgId").attr("orgLevelInternalId") > <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
}

function isGridOrganization(){
	return $("#currOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
}



//显示所有的建筑物信息
function viewAllHourseMapInfo(orgId,minX,minY,maxX,maxY){
	$.ajax({
		async: false,
		url:"${path}/gis/hourseTwoDimensionMapManage/findTownUnderHoursePageInfoBySearchValueAndOrgId.action?rows=1000000&page=1&screenCoordinateVo.minLon="+minX
				+"&screenCoordinateVo.minLat="+minY+"&screenCoordinateVo.maxLon="+maxX+"&screenCoordinateVo.maxLat="+maxY+"&organization.id="+orgId+"&screenCoordinateVo.searchInfoVo.gisType="+gisType,
		datatype: "json",
		success:function(data){
			if(data!=null&&data!=""){
				data=data.rows;
				for(var i=0;i<data.length;i++){
					var imgUrl="/resource/openLayersMap/images/house-3.png";
					var markerW=40;
					var markerH=30;
					var centerLon = "";
					var centerLat = "";
					if(data[i]!=null&&data[i].buildDataInfoVo!=null&&data[i].buildDataInfoVo.type!=null){
						var hourseData=getHourseTypeImgPathAndMarkerWH(data[i].buildDataInfoVo.type.id);
						imgUrl=hourseData.path;
						markerW=hourseData.markerW;
						markerH=hourseData.markerH;
					}
					if(gisType=="3D"){
						centerLon = data[i].lon;
						centerLat = data[i].lat;
					}else{
						centerLon = data[i].lon2;
						centerLat = data[i].lat2;
					}
					
					if(bindInWfs==false || data[i].featureId==undefined){
						viewHourseMarker(imgUrl,centerLon,centerLat,data[i].id+"_hourse",markerW,markerH);
						$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:hourseClick});
						
					}
				}
			}
		}
	});
}

function viewHourseMarker(imgUrl,lon,lat,markerId,markerW,markerH){
	$("#map").TqMap("addMarker",{
		iconUrl:PATH + imgUrl,
		markerW:markerW,
		markerH:markerH,
		lon:lon,
		lat:lat,
		markerId:markerId,
		data:{lon:lon,lat:lat}
	});
	
}

//通过楼宇类型ID获取楼宇图片和图片大小
function getHourseTypeImgPathAndMarkerWH(responseData){
	var path="/resource/openLayersMap/images/house-3.png";
	var markerW=40;
	var markerH=30;
	if(!(responseData==null||responseData==undefined)){
		if(responseData=="<s:property value='@com.tianque.openLayersMap.util.BuildDatasType@COMMON_BUILD'/>"){
			path="/resource/openLayersMap/images/house-1.png";
		}else if(responseData=="<s:property value='@com.tianque.openLayersMap.util.BuildDatasType@STORE'/>"){
			path="/resource/openLayersMap/images/house-5.png";
		}else if(responseData=="<s:property value='@com.tianque.openLayersMap.util.BuildDatasType@OFFICE_BUILDING'/>"){
			path="/resource/openLayersMap/images/house-2.png";
			markerW=50;
			markerH=50;
		}else if(responseData=="<s:property value='@com.tianque.openLayersMap.util.BuildDatasType@HIGH_BUILDING'/>"){
			path="/resource/openLayersMap/images/house-6.png";
			markerW=50;
			markerH=60;
		}else if(responseData=="<s:property value='@com.tianque.openLayersMap.util.BuildDatasType@OTHER_BUILDING'/>"){
			path="/resource/openLayersMap/images/house-4.png";
		}
	}
	var objectData={path:path,markerW:markerW,markerH:markerH};
	return objectData;
}
</script>
