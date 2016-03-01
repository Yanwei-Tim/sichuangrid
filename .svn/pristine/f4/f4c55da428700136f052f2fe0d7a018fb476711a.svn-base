<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>二维地图</title>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/includes/jsInclude.jsp" />
<jsp:include page="/openLayersMap/includes/cssInclude.jsp" />
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />
<style>
	body,html{width:100%;height:100%;}
	.shadowLink{
		width: 139px;
		height: 36px;
		display: inline-block;
		position: absolute;
		right: 10px;
		top: 5px;
		z-index: 9999;
		color: #333;
		text-indent: -9999em;
		background: url(/resource/openLayersMap/images/icon_gislogin.png) no-repeat;
	}
</style>
</head>
<body>
<a href='/module.jsp#map' target="_blank" title="点击进入GIS地理信息系统" class="shadowLink"></a>
<div id="gismap" ></div>
<div id="jGrowl"></div>
<div id="statisticInfo" style="position: absolute;bottom: 0;right: 0;width: 200px;height: 158px;border:1px;z-index: 999;"></div>
</body>
<script type="text/javascript">
var orgnizationId="<s:property value='#parameters.organizationId'/>";
var gisType="<s:property value='#parameters.gisType'/>";
var lon,lat;
$(function(){
	loadMap();
	//showStatisticInfo();

	function loadMap(){
		var options = changeGisType(orgnizationId);
		$("#gismap").TqMap($.extend({
			isShowM2D: true,						            //是否加载二维地图
			isShowM3D: true,						            //是否加载三维地图
			//isShowWFS: true,						            //是否加载热区
			isViewMousePosition:false,
			isValidZoomWheelEnabled:false,
			//isViewPanZoomBar:false,
			isViewScaleLine:false,
			isValidMapDefaultDblClick:false,
			isValidHandleRightClicks:false,
			gisType:gisType
		},options));
		var map = $("#gismap").TqMap("get","map");
		setTimeout(function(){
			$("#gismap").width($("#gismap").parent().width()).height($("#gismap").parent().height());
			if(map){
				map.updateSize();//修改地图大小
			}
		},1000);
		getGis2DLayerDataByOrgId(orgnizationId,function(layerData){
			if(layerData!=null&&layerData!=""){
				$("#gismap").TqMap("setCenter",{
					lon:layerData.lon,
					lat:layerData.lat,
					zoom:layerData.zoom
				});
				loadSubFeature(orgnizationId);
			}else{
				$.messageBox({message:"该辖区还未划分地图区域",level:"error"});
			}
		});
	}
});

function changeGisType(orgId){
	if(orgId==null) return;
	var result = {};
	$.ajax({
		async:false,
		url:"${path}/sysadmin/orgManage/getFullOrgById.action",
		data:{
			"organization.id":orgId
		},
		success:function(data){
			if(data!=null){
				var orgMapInfo = TQMap.OrgMap.changeGisType("gismap",gisType,data.departmentNo)
				if(orgMapInfo!=null){
					result = orgMapInfo;
					gisType = orgMapInfo.gisType
					return result;
				}
			}
		}
	})
	return result;
}	

var index=0;
function myLoadFeature(layerData,orgId,text){
	var points = "";
	if(gisType=="3D"){
		lon = layerData.centerX;
		lat = layerData.centerY;
		points = layerData.points;
	}else {
		lon = layerData.centerX2;
		lat = layerData.centerY2;
		points = layerData.points2;
	}
	var popText=$("#gismap").TqMap("addPopupText",{
		 lon:lon,
		 lat:lat,
		 popupText:text,
		 data:{orgId:orgId},
		 backgroundColor:"#66CC33",
		 autoSize:true
	});
	$("#gismap").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:popupTextClick});
	
	var color = getFillColorUrl(index);
	index++;
	$("#gismap").TqMap("createSelectFeature",{clickFeature:myClickFeature});
	$("#gismap").TqMap("featureShow",{
		points:points,
		featureId:layerData.id,
		fillColor:color,
		data:{lon:lon,lat:lat,orgId:orgId}
	});
	$("#map").TqMap("activateSelectFeature");
}

function popupTextClick(e){
	var lon=e.object.lonlat.lon;
	var lat=e.object.lonlat.lat;
	var id = e.object.data.orgId;
	
	gridPolygonStatisticsInfo(lon,lat,id);
	
}

function loadSubFeature(orgnizationId){
	getUnderGis2DLayersByOrgId(orgnizationId,function(response){
		if(response!=null&&response!=""){
			for(var i=0;i<response.length;i++){
				myLoadFeature(response[i],response[i].organization.id,response[i].organization.orgName);
			}
		}
	});
}

function myClickFeature(feature){
	var centerLon=feature.data.lon;
	var centerLat=feature.data.lat;
	var orgId=feature.data.orgId;
	gridPolygonStatisticsInfo(centerLon,centerLat,orgId);
}

function gridPolygonStatisticsInfo(centerLon,centerLat,orgId){
	var lonlat={lon:centerLon,lat:centerLat};
	$("#gismap").TqMap("addPopup",{
		url:PATH+"/gis/twoDimensionMapStatisticCommonManage/countJurisdictionStatisticByOrgId.action?organization.id="+orgId,
		lon:centerLon,
		lat:centerLat,
		lonlat:lonlat,
		popupW:200,
		popupH:240
	});
}

function showStatisticInfo(){
	$.ajax({
		url:"/gis/twoDimensionMapStatisticCommonManage/countGridLayerInfoByOrgIdForProduct.action?organization.id="+orgnizationId,
		data:{
			parentId:orgnizationId
		},
		success:function(response){
			$("#statisticInfo").html(response);
		}
	});
}
</script>
</html>