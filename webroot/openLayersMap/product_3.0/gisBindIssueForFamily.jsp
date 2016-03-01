<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />
<div id="issueMap" style="height:490px;"></div>
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
		$("#issueMap").empty().TqMap($.extend({
			isShowM2D: true,						            //是否加载二维地图
			isShowM3D: false,						            //是否加载三维地图
			isShowWFS: false,						            //是否加载热区
			isViewMousePosition:true,
			isViewPanZoomBar:false,
			isViewScaleLine:false,
			gisType:gisType
		},options));
		
		loadMap(currentOrgId);
		
		if(flag=="1"){
			$("#issueMap").css("height","490px");
			markerAndPopupTextMousemove();//marker绑定事件
		}
	}

	function loadMap(orgId){
		var layerData = get2DLayerData(orgId);
		if(layerData!=null&&layerData!=""){
			$("#issueMap").TqMap("setCenter",{
				lon:layerData.lon,
				lat:layerData.lat,
				zoom:layerData.zoom
			});
			load2DFeature(layerData);
		}else{
			$("#issueMap").TqMap("setCenter");
			$.messageBox({message:"该辖区还未划分地图区域",level:"error"});
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
				var orgMapInfo = TQMap.OrgMap.changeGisType("issueMap",gisType,data.departmentNo)
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

	function markerAndPopupTextMousemove(){
		$("#issueMap").TqMap("addMarker",{
			iconUrl:PATH +"/resource/openLayersMap/images/bubble.png",
			markerW:26,
			markerH:30
		});
		$("#issueMap").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
	 	$("#issueMap").TqMap("registerEvent",{eventName:"click",func:mapClickIsuue});
	 	$("#issueMap").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAdd});
	  
	}
	
	//地图点击事件
	function mapClickIsuue(e){
		var imap = $("#issueMap").TqMap("get","map");
		var lon = imap.getLonLatFromPixel(e.xy).lon;
		var lat = imap.getLonLatFromPixel(e.xy).lat;
		var zoom = imap.zoom;
		var layerData=get2DLayerData(currentOrgId);
		if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
			$.messageBox({message:"请在您所画的辖区内绑定事件",level:"error"});
		}else{
			setIssueValue(lon,lat,zoom)
		}
	}
	
	function wfsFeatureClickFunc(e){
		var lonlat = e.geometry.bounds.getCenterLonLat();
		var lon=lonlat.lon;
		var lat=lonlat.lat;
		var layerData=get2DLayerData(currentOrgId);
		if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
			$.messageBox({message:"请在您所画的辖区内绑定事件",level:"error"});
		}else{
			setIssueValue(lon,lat,$("#issueMap").TqMap("get","map").zoom)
		}
	}

	function setIssueValue(lon,lat,zoom){
		$("#issueMap").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
		$("#issueMap").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAdd});
		$("#issueMap").TqMap("unregisterEvent",{eventName:"click",func:mapClickIsuue});
		$("#centerLon").val(lon);
		$("#centerLat").val(lat);
		$("#zoom").val(zoom);
		$("#gisType").val(gisType)
		$("#isTransformat").val(TQMap.isTransformat);
		$("#issueOpenLayersMapDialog").dialog("close");
		$("#issueViewMap").load("${path}/openLayersMap/product_3.0/gisIssue.jsp?currentOrgId="+$("#occurOrgId").val()+"&lon="+$("#centerLon").val()+"&lat="+$("#centerLat").val());
		$("#familyMap").load("${path}/openLayersMap/product_3.0/gisIssue.jsp?currentOrgId="+$("#organizationId").val()+"&lon="+$("#centerLon").val()+"&lat="+$("#centerLat").val());
		$("#familyMap").load("${path}/openLayersMap/product_3.0/gisIssue.jsp?currentOrgId="+$("#organizationId").val()+"&lon="+$("#centerLon").val()+"&lat="+$("#centerLat").val());
	}
	
	function load2DFeature(layerData){
		$("#issueMap").TqMap("featureShow",{
			points:layerData.points,
			featureId:layerData.id,
			fillOpacity:0.1,
			strokeColor: "#FF3333",
			strokeWidth: 3
		});
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
	//marker鼠标跟随事件
	function markerMousemove(e){
		$("#issueMap").TqMap("moveTo",{objectName:"marker",e:e});
	}
	
	function mouseTipFunAdd(){
		$("#issueMap").TqMap("addMouseTip",{evt:event,msg:"请点击地图，或是地图建筑物进行绑定"});
	}
</script>