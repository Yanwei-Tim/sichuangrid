<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<% String dailogName = request.getParameter("dailogName");
	request.setAttribute("dailogName", dailogName);
 	request.setAttribute("type", request.getParameter("publicSecurityType"));
%>

<jsp:include page="/openLayersMap/includes/jsInclude.jsp"></jsp:include>

<div id="map" style="height:480px;"></div>
<script type="text/javascript">
var flag = "<s:property value='#parameters.flag'/>";
var orgnizationId="<s:property value='#parameters.organizationId'/>";
var viewLon="<s:property value='#parameters.viewLon'/>";
var viewLat="<s:property value='#parameters.viewLat'/>";
var id="<s:property value='#parameters.id'/>";
var gisType="<s:property value='#parameters.gisType'/>";

$(function() {
	init2DMap();

	function init2DMap() {
		var options = changeGisType(orgnizationId);
		$("#map").TqMap($.extend({
			isShowM2D: true,						            //是否加载二维地图
			isShowM3D: true,						            //是否加载三维地图
			isShowWFS: true,						            //是否加载热区
			isViewMousePosition:true,
			isViewPanZoomBar:false,
			isViewScaleLine:false,
			gisType:gisType
		},options));
		loadMap(orgnizationId);
	}

	function loadMap(orgId){
		var layerData = get2DLayerData(orgId);
		if(layerData!=null&&layerData!=""){
			var lon=layerData.lon;;
			var lat=layerData.lat;
			if(viewLon!=""&&viewLat!=""){
				 lon=viewLon;
				 lat=viewLat;
			}
			$("#map").TqMap("setCenter",{lon:lon,lat:lat,zoom:layerData.zoom});
			load2DFeature(layerData);
			if(flag!="3"){
				$("#map").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAdd});
				markerMousemove();
				//if(gisType=="3D"){
					//	$("#map").TqMap("createDragPanFeature",{clickFeature:wfsFeatureClickFunc});
					//}
				//else{
					$("#map").TqMap("registerEvent",{eventName:"click",func:mapClick});
				//}
			}else{
				viewMap(viewLon,viewLat);
			}
		}else{
			$.messageBox({message:"该辖区还未划分地图区域",level:"error"});
			
		}
		
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
				var orgMapInfo = TQMap.OrgMap.changeGisType("map",gisType,data.departmentNo)
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

//地图点击事件
function mapClick(e){
	var imap = $("#map").TqMap("get","map");
	var lon = imap.getLonLatFromPixel(e.xy).lon;
	var lat = imap.getLonLatFromPixel(e.xy).lat;
	var zoom= imap.zoom;
	var layerData=get2DLayerData(orgnizationId);
	if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
		$.messageBox({message:"请在您所画的辖区内绑定公安部件",level:"error"});
	}else{
		setBoundValue(lon,lat);
	}
}

function wfsFeatureClickFunc(e){
	var lonlat = e.geometry.bounds.getCenterLonLat();
	var lon=lonlat.lon;
	var lat=lonlat.lat;
	var layerData=get2DLayerData(orgnizationId);
	if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
		$.messageBox({message:"请在您所画的辖区内绑定公安部件",level:"error"});
	}else{
		setBoundValue(lon,lat);
	}
}

function setBoundValue(lon,lat){
	$("#map").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAdd});
	$("#map").TqMap("unregisterEvent",{eventName:"click",func:mapClick});
	$("#${dailogName}-centerLon").val(lon);
	$("#${dailogName}-centerLat").val(lat);
	$("#${dailogName}-gisType").val(gisType);
	$("#${dailogName}-isTransformat").val(TQMap.isTransformat);
	$("#gis${dailogName}Dialog").dialog("close");
	$("#${dailogName}List").trigger("reloadGrid");
}

//查看地图
function viewMap(lon,lat){
	$("#map").TqMap("setCenter",{lon:lon,lat:lat,zoom:6});
	$("#map").TqMap("addMarker",{
		iconUrl:"${resource_path}/resource/openLayersMap/images/a.png",
		markerW:24,
		markerH:27,
		lon:lon,
		lat:lat
	});
	
}

function markerMousemove(){
	$("#map").TqMap("addMarker",{
		iconUrl:PATH +"/resource/openLayersMap/images/bubble.png",
		markerW:26,
		markerH:30
	});
	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemoveFun});
  
}

//marker鼠标跟随事件
function markerMousemoveFun(e){
	$("#map").TqMap("moveTo",{objectName:"marker",e:e});
}

function mouseTipFunAdd(e){
	$("#map").TqMap("addMouseTip",{evt:event,msg:"请点击地图，或是地图建筑物进行绑定"});
}

function get2DLayerData(orgId){
	var layerData=null;
	$.ajax({
		async:false,
		url:"${path}/system/gis2DLayerManage/getGis2DLayerByOrganizationId.action",
		data:{
			"organization.id":orgId,
			"gisType":gisType
		},
		success:function(response){
			if(response!=null&&response!=""){
				layerData = TQConvert.toPoints(response);
			}
		}
	});
	return layerData;
}

function load2DFeature(layerData){
	var points,lon,lat;
		points = layerData.points;
		lon = layerData.lon;
		lat = layerData.lat;
	$("#map").TqMap("featureShow",{
		points:points,
		featureId:layerData.id,
		fillOpacity:0.1,
		strokeColor: "#FF3333",
		strokeWidth: 3
	});
	$("#map").TqMap("addMarker",{
		iconUrl:"${resource_path}/resource/images/u331.png",
		markerW:24,
		markerH:27,
		lon:lon,
		lat:lat
	});
}
</script>
