<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div class="distinguishLine boxShadeC">
	<div class="boxShadeContent"></div>
	<div class="gisToolsGraph buildingMarks minusBorder hidden" style="width:73px;" id="construction">
			<a href="javaScript:void(0);" title="新增建筑物" id="addHourseInfo" style="display:none;"><strong class="drawdol"></strong>新增</a>
			<a href="javaScript:void(0);" title="清除建筑物" id="deleteHourseInfo" style="display:none;"><strong class="drawline"></strong>清除</a>
		<input type="hidden" id="deleteHourse" />
	</div>
</div>

<script type="text/javascript">
//添加建筑物
$("#addHourseInfo").click(function(){
	$("#addHourseInfo").attr("value",true);//用来判断建筑物是否能绑定在wfs要素上
	if(isGridOrganization()){
		var layerData = getGis2DLayerDataByOrgId($('#currOrgId').val());
		if(layerData!=null&&layerData!=""){
			$("#map").TqMap("featureShow",{
				points:layerData.points,
				featureId:layerData.id,
				fill:false,
				strokeWidth:1.5,
				fillOpacity:0
			});
		}
		$("#map").TqMap("addMarker",{
			iconUrl:PATH +"/resource/openLayersMap/images/house-3.png",
			markerW:40,
			markerH:30,
			markerId:"0_hourse"
		});
		$("#map").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
		$("#map").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAddHourse});
		$("#map").TqMap("registerEvent",{eventName:"click",func:clickMapAddHourseInfo});//注册地图点击事件
		$("#map").TqMap("deactivateSelectFeature",{sfname: "selectF"});
	}else{
		$.messageBox({
			message:"请选择到网格级别进行操作！",
			level:'error'
		});
	}
});

//删除建筑物
$("#deleteHourseInfo").click(function(){
	cleanMapTrace();
	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunDelHourse});
	$("#deleteHourse").val("delete");
});

function mouseTipFunAddHourse(){
	$("#map").TqMap("addMouseTip",{evt:event,msg:"请选中地图区域，单击保存建筑物"});
}

function mouseTipFunDelHourse(){
	$("#map").TqMap("addMouseTip",{evt:event,msg:"请选择要删除的建筑物,点击删除！"});
}

//点击地图保存建筑物
function clickMapAddHourseInfo(e){
	//获得点击的坐标点
	$("#addHourseInfo").attr("value",false);//用来判断建筑物是否能绑定在wfs要素上
	var lon=$("#map").TqMap("get","map").getLonLatFromPixel(e.xy).lon;
	var lat=$("#map").TqMap("get","map").getLonLatFromPixel(e.xy).lat;
	var orgId=$('#currOrgId').val();
	var layerData=getGis2DLayerDataByOrgId(orgId);
	if(layerData!=null && layerData!=""){
		if(checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
			$("#map").TqMap("deleteMarkerByMarkerId",{markerId:"0_hourse"});
			$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
			$("#map").TqMap("unregisterEvent",{eventName:"click",func:clickMapAddHourseInfo});
			 $.post("${path}/gis/hourseTwoDimensionMapManage/addHourseInfo.action",
				{
					"hourseInfo.lon":lon,
					"hourseInfo.lat":lat,
					"hourseInfo.organization.id":orgId,
					"hourseInfo.isTransformat":TQMap.isTransformat,
					"gisType":gisType
				},
				function(result){
					if(result!=null&&result!="") {
						$.messageBox({message:"新增建筑物成功！"});
						$("#map").TqMap("addMarker",{
							iconUrl:PATH+"/resource/openLayersMap/images/house-3.png",
							markerW:40,
							markerH:30,
							lon:lon,
							lat:lat,
							markerId:result.id+"_hourse",
							data:{lon:lon,lat:lat}
						});
						$("#map").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
						$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAddHourse});
						$("#map").TqMap("deleteFeatureByFeatureId",{featureId:layerData.id});//通过自定义的featureId删除feature
						//注册建筑物marker点击事件
						$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:hourseClick});
						$("#map").TqMap("activateSelectFeature");
					}else {
						$.messageBox({
							message:"新增建筑物失败！",
							level:'error'
						});
					}
				},'json'
			); 
		}else{
			$.messageBox({
				message:"该位置已超出了当前部门所划分地图区域的范围！",
				level:'error'
			});
		}
	}else{
		$.messageBox({
			message:"当前部门还未划分地图区域！",
			level:'error'
		});
	}
}


//清除建筑物
function deleteHourseInfo(id){
	$.confirm({
		title:"删除建筑物",
		message:"数据删除后，无法恢复，您确定要删除该信息吗？",
		width: 400,
		okFunc: function() {
			$.post(
				"${path}/gis/hourseTwoDimensionMapManage/deleteHourseInfoById.action",{
					"hourseInfo.id":id
				},
				function(result){
					if(result){
						$("#map").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
						$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunDelHourse});
						$("#deleteHourse").val("");
						$("#map").TqMap("deleteMarkerByMarkerId",{markerId:id+"_hourse"});
						$.messageBox({message:"建筑物删除成功！"});
					}else{
						$.messageBox({message:"建筑物删除失败！"});
					}
				},
				'json'
			);
		},
		cancelFunc:function(){
			$(".deleteImg").removeClass("canDel");
			$("#map").TqMap("deletePopupText");
			$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:popupTextMousemove});
			$("#deleteHourse").val("");
		}
	});
}

</script>