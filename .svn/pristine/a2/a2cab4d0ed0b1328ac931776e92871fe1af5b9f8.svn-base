<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="/openLayersMap/includes/jsInclude.jsp"></jsp:include>

<div id="mapForBind" style="height:480px;"></div>
<script type="text/javascript">
var buildId="<s:property value='#parameters.buildId'/>";

var flag = "<s:property value='#parameters.flag'/>";
var module = "<s:property value='#parameters.module'/>";
var orgnizationId="<s:property value='#parameters.organizationId'/>";
var viewLon="<s:property value='#parameters.viewLon'/>";
var viewLat="<s:property value='#parameters.viewLat'/>";
var id="<s:property value='#parameters.id'/>";

var gisType="<s:property value='#parameters.gisType'/>";

$(function() {
	init2DMap();

	function init2DMap() {
		var options = changeGisType(orgnizationId);
		$("#mapForBind").TqMap($.extend({
			isShowM2D: true,						            //是否加载二维地图
			isShowM3D: true,						            //是否加载三维地图
			isShowWFS: true,						            //是否加载热区
			isViewMousePosition:true,
			isViewPanZoomBar:false,
			isViewScaleLine:false,
			gisType:gisType
		},options));
		if(viewLon==""&&viewLat==""){
			viewAllHourseMapInfo(orgnizationId);
		}
		loadMap(orgnizationId);
	}

	function loadMap(orgId){
		var layerData = get2DLayerData(orgId);
		if(layerData!=null&&layerData!=""){
			var lon = layerData.lon;
			var lat = layerData.lat;
			if(viewLon!=""&&viewLat!=""){
				lon=viewLon;
				lat=viewLat;
			}
			$("#mapForBind").TqMap("setCenter",{ lon:lon, lat:lat, zoom:layerData.zoom });
			load2DFeature(layerData);
			if(flag!="3"){
				//if(gisType=="2D"){
// 					viewAllHourseMapInfo(orgId);
// 				}else 
				if(gisType=="3D"){
					$("#mapForBind").TqMap( "createDragPanFeature",{clickFeature:wfsFeatureClickFunc});
				}
				
				$("#mapForBind").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAdd});
				
				markerMousemove();
				$("#mapForBind").TqMap("registerEvent",{eventName:"click",func:mapClickBuilddatas});
			}else{
				add2DMarker(viewLon,viewLat);
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
				var orgMapInfo = TQMap.OrgMap.changeGisType("mapForBind",gisType,data.departmentNo)
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
	function markerMousemove(){
		$("#mapForBind").TqMap("addMarker",{
			iconUrl:PATH +"/resource/openLayersMap/images/bubble.png",
			markerW:26,
			markerH:30
		});
		$("#mapForBind").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemoveFun});
	  
	}
	
	//marker鼠标跟随事件
	function markerMousemoveFun(e){
		$("#mapForBind").TqMap("moveTo",{objectName:"marker",e:e});
	}

	function viewAllHourseMapInfo(orgId){
		$.ajax({
			url:'${path}/gis/hourseTwoDimensionMapManage/findUnboundHouseInfoByOrganizationId.action',
			async:false,
			data:{
				"organization.id":orgId
			},
			success:function(data){
				if(data!=null&&data!=""){
					rows=data.rows;
					if(rows){
						for(var i=0;i<rows.length;i++){
							var row = TQConvert.toLonlat(rows[i])
							var imgUrl="/resource/openLayersMap/images/house-3.png";
							//判断一下，如果是单位场所进来。不过滤已经绑定房屋的建筑物
							if(module != null && module == 'companyplace'){
								viewHourseMarker(imgUrl,row.lon,row.lat,row.id+"_hourse",40,30);
								$("#mapForBind").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:hourseClick});
							}else{
								if(row.typeId==null || row.typeId=='' || row.typeId==undefined ){
									viewHourseMarker(imgUrl,row.lon,row.lat,row.id+"_hourse",40,30);
									$("#mapForBind").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:hourseClick});
								}
							}
						}
					}
				}
			}
		});
	}
	
	function viewHourseMarker(imgUrl,lon,lat,markerId,markerW,markerH){
		$("#mapForBind").TqMap("addMarker",{
			iconUrl:PATH + imgUrl,
			markerW:markerW,
			markerH:markerH,
			lon:lon,
			lat:lat,
			markerId:markerId,
			data:{lon:lon,lat:lat}
		});
	}

	function setBoundValue(lon,lat){
		$("#builddatas-centerLon").val(lon);
		$("#builddatas-centerLat").val(lat);
		$("#builddatas-isTransformat").val(TQMap.isTransformat);
		$("#builddatas-gisType").val(gisType);
		$("#gisBuilddatasDialog").dialog("close");
		$("#builddatasList").trigger("reloadGrid");
	}
	
	function hourseClick(e){
		var featureId=e.object.markerId.split("_")[0];
		var lon=e.object.data.lon;
		var lat=e.object.data.lat;
		var layerData=get2DLayerData(orgnizationId);

		if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
			$.messageBox({message:"请在您所画的辖区内绑定楼宇",level:"error"});
		}else{
			$("#buildingid").val(featureId);
			setBoundValue(lon,lat);
		}
		
	}

	function wfsFeatureClickFunc(e){
		var featureInfo = $("#mapForBind").TqMap("getInfoFromFeature",e);
		var fid = featureInfo.id;
		var lon=featureInfo.lon;
		var lat=featureInfo.lat;
			$.ajax({
				url:"${path}/twoDimensionMap/buildDataTwoDimensionMapManage/getBuildDataInfoVoByCenterLonLat.action",
				data:{
					"buildDataInfoVo.lon":lon,
					"buildDataInfoVo.lat":lat,
					"buildDataInfoVo.featureId":fid,
					"buildDataInfoVo.gisType":gisType,
					"orgId":orgnizationId
				},
				success:function(data){
					if(data != null){
						$.messageBox({evel: "error",message:"该热区已绑定楼宇！"});
			            return;
					}
					var layerData=get2DLayerData(orgnizationId);
					if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
						$.messageBox({message:"请在您所画的辖区内绑定楼宇",level:"error"});
					}else{
						if(flag=="1"){
							//$.messageBox({message:"绑定成功!"});
							$("#featureId").val(fid);
							setBoundValue(lon,lat);
						}
					}
				}
			});	
// 			$.post("${path}/gis/hourseTwoDimensionMapManage/addHourseInfo.action",
// 					{
// 						"hourseInfo.lon":lon,
// 						"hourseInfo.lat":lat,
// 						"hourseInfo.featureId":fid,
// 						"hourseInfo.organization.id":orgId,
//                     "hourseInfo.isTransformat":TQMap.isTransformat,
// 						"gisType":gisType
// 					},
// 					function(result){
						
// 					},'json'
// 			);
	}
	
	function add2DMarker(lon,lat){
		$("#mapForBind").TqMap("setCenter",{lon:lon,lat:lat,zoom:6});
		$("#mapForBind").TqMap("addMarker",{
			iconUrl:"${resource_path}/resource/openLayersMap/images/a.png",
			markerW:24,
			markerH:27,
			lon:lon,
			lat:lat
		});
		
	}

	function mouseTipFunAdd(e){
		$("#mapForBind").TqMap("addMouseTip",{evt:e,msg:"请选中地图建筑物，点击绑定"});
	}

	//地图点击事件
	function mapClickBuilddatas(e){
		$.messageBox({message:"请选择地图中的建筑物进行绑定",level:"error"});
	}
	
	function load2DFeature(layerData){
		var points,lon,lat;
			points = layerData.points;
			lon = layerData.lon;
			lat = layerData.lat;
		$("#mapForBind").TqMap("featureShow",{
			points:points,
			featureId:layerData.id,
			fillOpacity:0.1,
			strokeColor: "#FF3333",
			strokeWidth: 3
		});
		$("#mapForBind").TqMap("addMarker",{
			iconUrl:"${resource_path}/resource/images/u331.png",
			markerW:24,
			markerH:27,
			lon:lon,
			lat:lat
		});
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
	
</script>


