<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />
<div id="map" style="height:165px;"></div>
<script type="text/javascript">
var currentOrgId = "<s:property value='#parameters.currentOrgId'/>";
var flag = "<s:property value='#parameters.flag'/>";
var lon = "<s:property value='#parameters.lon'/>";
var lat = "<s:property value='#parameters.lat'/>";

var gisType = "2D";
$(function() {
	initMap();
	function initMap() {
		var options = changeGisType(currentOrgId);
		$("#map").empty().TqMap($.extend({
			isShowM2D: true,						            //是否加载二维地图
			isShowM3D: true,						            //是否加载三维地图
			isShowWFS: true,						            //是否加载热区
			isViewMousePosition:false,
			isViewPanZoomBar:false,
			isViewScaleLine:false,
			gisType:gisType
		},options));
		$("#map").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAdd});
		$("#map").TqMap("registerEvent",{eventName:"click",func:mapClick});
		
		if(currentOrgId!=null&&currentOrgId!=''){
			var layerData = get2DLayerData(currentOrgId);
			if(layerData!=null&&layerData!=""){
				if(lon!=""&&lat!=""){
					$("#map").TqMap("setCenter",{ lon:lon, lat:lat, zoom:layerData.zoom });
					$("#map").TqMap("addMarker",{
						iconUrl:PATH +"/resource/openLayersMap/images/a.png",
						markerW:26,
						markerH:30,
						lon:lon,
						lat:lat
					});
				}else{
					$("#map").TqMap("setCenter",{ lon:layerData.lon, lat:layerData.lat, zoom:layerData.zoom });
				}
			}
		}
	}
})

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
	
function mouseTipFunAdd(){
	$("#map").TqMap("addMouseTip",{evt:event,msg:"点击地图进行绑定"});
}

//地图点击事件
function mapClick(e){
	$("#bindMap").trigger("click");

}
function get2DLayerData(orgId){
	var layerData=null;
	$.ajax({
		async:false,
		url:"${path}/system/gis2DLayerManage/getGis2DLayerByOrganizationId.action",
		data:{
			"organization.id":orgId
		},
		success:function(response){
			if(response!=null&&response!=""){
				layerData = TQConvert.toPoints(response);
			}
		}
	});
	return layerData;
}
	
</script>