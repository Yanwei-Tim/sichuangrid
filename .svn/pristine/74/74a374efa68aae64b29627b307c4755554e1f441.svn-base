<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />
<jsp:include page="/openLayersMap/includes/cssInclude.jsp" />
<style type="text/css">
	.marginleft {margin-left: 0px !important;}
	.olMap,.olMap .olDragDown {cursor:pointer!important;}
</style>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-left">
	<div>
		<div class="ui-widget">
			<input id="org-tree-autocomplete"  type="text" value=""/>
			<button id="refreshOrgTree"  class="ui-icon ui-icon-refresh" ></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
	<input type="hidden" value="" id="currentOrgId"></input>
	<input type="hidden" value="" id="parentOrgId"></input>
</div>

<div class="center-right">
	<div class="content" >
		<div class="ui-corner-all" id="nav">
			<div>
				<pop:JugePermissionTag ename="addLayer">
					<a id="add" href="javascript:void(0)"  title="对父级区域进行面切分"><span>新增</span></a>
				</pop:JugePermissionTag> 
				<pop:JugePermissionTag ename="updateLayer">
					<a id="update" href="javascript:void(0)"  title="对父级区域进行面切分"><span>修改</span></a>
				</pop:JugePermissionTag> 
				<pop:JugePermissionTag ename="deleteLayer">
					<s:if test="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().getInternalId()==@com.tianque.domain.property.OrganizationLevel@COUNTRY">
						<a id="delete" href="javascript:void(0)"><span>下辖删除</span></a>
					</s:if>
					<a id="sameLevelDelete" href="javascript:void(0)"><span>本级删除</span></a>
				</pop:JugePermissionTag> 
				<a id="fullScreen" href="javaScript:void(0);"><span id="outFull">全屏</span></a>
				<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()">
					<a id="import" href="javaScript:void(0);"><span>导入</span></a>
					<a id="reimportLonlat" href="javaScript:void(0);"  style="display: none;"><span>重置坐标导入</span></a>
					<a id="reimportLayer" href="javaScript:void(0);"  style="display: none;"><span>重置区域导入</span></a>
				</s:if>
			</div>
		</div>
		<div id="systemMap" style="z-index: 1;position: relative;"></div>
	</div>
</div>
<div id="layerMaintanceDialog"></div>
<input type="hidden" id="layerManageId"/>
<input type="hidden" id="pointsLayerStr"/>
<input type="hidden" id="layerMapZoom"/>

<script type="text/javascript">
var layerPoints=null;
var tree;
var currentOrgNode;
var gisType = "2D";
$(function(){
	$.dialogLoading("close");
	fullScreen();
	initSystemMap();
	loadOrgTree();
	
	$("#add").unbind().click(function(){
		if ($(this).hasClass("disable")) return;
		var parentOrgId = $("#parentOrgId").val(),
				orgId = $("#currentOrgId").val();
		if(checkLayerDataIsDrawn(parentOrgId)){
			redrawOrgFeatures(false,true);
			$("#systemMap").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAdd});
			$("#systemMap").TqMap("drawFeatureMeasure",{type:"polygon",
				featureClickMapEnd:drawPolygonEndFunc,
				featureClickMap:drawPolygonClickMapFunc
			});
		}
	});
	
	$("#update").unbind("click").click(function(){
		if ($(this).hasClass("disable")) return;
		clearMapElement();
		var parentOrgId = $("#parentOrgId").val(),
				orgId = $("#currentOrgId").val();
		$("#systemMap").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunUpdate});
		redrawOrgFeatures(true,true);
		$("#systemMap").TqMap("modifyFeature",{
			modifyFeatureEndFunc:modifyFeatureEndFunc,
			dragFeatureEndFunc:dragFeatureEndFunc,
			orgId:orgId
		});
	});
	
	$("#delete").unbind("click").click(function(){
		if ($(this).hasClass("disable")){ 
			return; 
		}
		$.confirm({
			title:"确认删除",
			message:"该数据删除后，所有下级数据都将删除，数据一经删除，无法恢复，您确定删除吗?",
			okFunc: function() {
				deleteFeature($("#currentOrgId").val());
			}
		});
	});
	
	$("#sameLevelDelete").unbind("click").click(function(){
		if ($(this).hasClass("disable")){ 
			return; 
		}
		$.confirm({
			title:"确认删除",
			message:"该数据删除后，本级数据将删除，数据一经删除，无法恢复，您确定删除吗?",
			okFunc: function() {
				var layerData = getUnderGis2DLayersByOrgId($("#currentOrgId").val());
				if(layerData!=null&&layerData!="" ){
					$.messageBox({
		                message:"该区域有下辖区域数据，请先删除下辖区域的数据",
		                level:"error"
		            });
				}else{
					deleteSameLevelFeature($("#currentOrgId").val());
				}
				
			}
		});
	});
	
	$("#import").click(function(event){
		$("#layerMaintanceDialog").createDialog({
			width: 400,
			height: 250,
			zIndex: 99999,
			shouldEmptyHtml:false,
			title:'数据导入',
			url:'${path}/openLayersMap/system/layerManage/importLayerDlg.jsp',
			buttons:{
				"提交" : function(event){
			      $("#mForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});
	
	$("#reimportLonlat").click(function(){
		$("#layerMaintanceDialog").createDialog({
			width: 400,
			height: 250,
			zIndex: 99999,
			shouldEmptyHtml:false,
			title:'数据回导',
			url:'${path}/common/import.jsp',
			postData:{
				isNew:1,
				templates:"UPDATELONLAT",
				dataType:"UpdateLonlat",
				dialog:"layerMaintanceDialog",
				startRow:6
			},
			buttons:{
				"导入" : function(event){
			      $("#mForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	})
	$("#reimportLayer").click(function(){
		$("#layerMaintanceDialog").createDialog({
			width: 400,
			height: 250,
			zIndex: 99999,
			shouldEmptyHtml:false,
			title:'数据回导',
			url:'${path}/common/import.jsp',
			postData:{
				isNew:1,
				templates:"GIS2DLAYER",
				dataType:"Gis2DLayer",
				dialog:"layerMaintanceDialog",
				startRow:6
			},
			buttons:{
				"导入" : function(event){
			      $("#mForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	})

	function fullScreen(){//全屏
		var option={
				mapId:"systemMap",
				toolId:"fullScreen"
		}
		var clientH        = document.documentElement.clientHeight,
	        clientW        = document.documentElement.clientWidth,
		 	$north = $('.ui-layout-north'),
		 	$west = $('.ui-layout-west'),
		 	$center = $(".ui-layout-center"),
		 	$south = $(".ui-layout-south"),
		 	$slideResizer = $(".slideResizer"),
		 	$leftRight= $(".center-left,.center-right"),
		 	$mapCont = $(".center-left .orgTree,#"+option.mapId );
		var centerH = $center.outerHeight(true);
		var centerW = $center.outerWidth(true);
		var thisCrumbsH = $center.find("#thisCrumbs").outerHeight(true);
		var toolBarH = $center.find("div.ui-corner-all").outerHeight(true);
		$leftRight.height(centerH - thisCrumbsH);
		$mapCont.height(centerH - thisCrumbsH - toolBarH );
	/* 	$(window).resize(function() {
			clearTimeout(window.resizeHandleT);
			window.resizeHandleT = setTimeout(function(){
				if($("#"+option.mapId).TqMap("get","map")!=null){
					$("#"+option.mapId).TqMap("get","map").updateSize();
				}
				if($("#"+option.toolId ).hasClass("close")){
					$center.height(clientH).width(clientW);
				}
			}, 500);
		}) */
		 $("#"+option.toolId ).toggle(function(){
			 $north.hide();
			 $west.hide();
			 $south.hide();
			 $slideResizer.hide()
			 $center.height(clientH).width(clientW);
			$(this).addClass("close").removeClass("open").html("<span>退出全屏</span>");
			$leftRight.height(clientH - thisCrumbsH);
			$mapCont.height(clientH - thisCrumbsH - toolBarH );
			$("#"+option.mapId).TqMap("get","map").updateSize();
			$(".message-tip").remove();//消息提醒框
		},function(){
			$north.show();
			 $west.show();
			 $south.show();
			 $slideResizer.show();
			 $center.height(centerH).width(centerW);
			$(this).addClass("open").removeClass("close").html("<span>全屏</span>");
			$leftRight.height(clientH - thisCrumbsH);
			$mapCont.height(centerH - thisCrumbsH - toolBarH );
			$("#"+option.mapId).TqMap("get","map").updateSize();
		});
	}
});

function initSystemMap(){
	//加载地图
	$("#systemMap").TqMap({
		gisType:gisType,
		isShowM2D: true,						            //是否加载二维地图
		isShowM3D: true						            //是否加载三维地图
	});
}
var SpecialMapByOrg={//为特定的组织机构      初始化    特定的值
		selectMapInfo:null,
		changeGisType:function(orgDepartNo){
			var that = this;
			that.selectMapInfo = null;
			if(orgDepartNo==null) return;
			return TQMap.OrgMap.extend({
				change:function(orgMapInfo){
					if(orgMapInfo!=null){
						$("#systemMap").TqMap("setCenter",{lon:orgMapInfo.lon,lat:orgMapInfo.lat,zoom:orgMapInfo.zoom});
						$("#systemMap").TqMap("layerSwitch",{type:orgMapInfo.gisType});//2D,3D等地图之间的转换
						gisType = orgMapInfo.gisType;
						that.selectMapInfo = orgMapInfo;
					}
				}
			}).changeGisType("systemMap",gisType,orgDepartNo);
		},
		isDrawLayerStart:function(orgDepartNo){
			if(orgDepartNo==null) return;
			var orgMapInfo = TQMap.OrgMap.getByOrgDepartNo(orgDepartNo);
			if(orgMapInfo!=null && orgMapInfo.onlyType==true && orgDepartNo==orgMapInfo.departNo){
				return true;
			}
			return false;
		}
	
}

function loadOrgTree(){
	var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree();
	}
	$.addClick(tree, afterChangNode);

	$("#org-tree-autocomplete").autocomplete({
		source:function(request, response){
			$.ajax({
				url:"${path}/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":request.term},
				success:function(data){
					response($.map(data, function(item) {
						return {
							label: item.orgName +','+ stringFormatter(item.contactWay),
							value: item.orgName,
							id: item.id
						}
					}))
				},
				error:function(){
					$.messageBox({level:"error",message:"搜索失败，请重新登入"});
				}
			})
		},
		select:function(event, ui){
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(tree,data);
				}
			});
		}
	});
	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	});
	
	$('#refreshOrgTree li').hover(
		function() { $(this).addClass('ui-state-hover'); },
		function() { $(this).removeClass('ui-state-hover'); }
	);
}

function afterChangNode(node){
	var orgId=node.attributes.id;
	var parentOrgId=node.parentNode.id;
	currentOrgNode = node;
	$("#parentOrgId").attr("value",parentOrgId);
	$("#currentOrgId").attr("value",orgId);
	$("#org-tree-autocomplete").val(node.attributes.text);
	SpecialMapByOrg.changeGisType(node.attributes.departmentNo);
	
	onLayerOrgChanged();
}

function onLayerOrgChanged(){
	$("#systemMap").TqMap("removeLoadingPanel");
	var orgId = $("#currentOrgId").val(),
			parentOrgId = $("#parentOrgId").val();
	clearMapElement();
	var layerData = getGis2DLayerDataByOrgId(orgId);
	var zoom = 0;
	$("#layerManageId").val("");
	displayButtons(layerData);
	if(layerData){//已经画区域
		zoom = layerData.zoom;
		$("#systemMap").TqMap("setCenter",{ lon:layerData.lon, lat:layerData.lat, zoom:zoom });
		loadFeature(layerData);//加载区域图形
		$("#systemMap").TqMap("addMarker",{//显示区域中心点
			iconUrl:"${resource_path}/resource/images/u331.png",
			markerW:24,
			markerH:27,
			lon:layerData.lon,
			lat:layerData.lat
		});
		$("#layerManageId").val(layerData.id);
		$("#layerMapZoom").val(layerData.zoom);
	}else{
		//orgTree-root为根节点
		 if(parentOrgId && parentOrgId!="orgTree-root"){
			layerData = getGis2DLayerDataByOrgId(parentOrgId);//获取上级区域对象
		} 
		if(layerData && layerData.zoom!=-1){
			var zoom = $("#systemMap").TqMap("getOrgZoom",{zoom:layerData.zoom});
			$("#systemMap").TqMap("setCenter",{ lon:layerData.lon, lat:layerData.lat, zoom:zoom });
		}else if(SpecialMapByOrg.selectMapInfo != null){
			zoom=SpecialMapByOrg.selectMapInfo.zoom;
		}else{
			var zoom = $("#systemMap").TqMap("getOrgZoom",{type:"minZoom"});
			$("#systemMap").TqMap("setCenter");
			
	 	}
		$.messageBox({message:"该辖区还未划分地图区域",level:"error"});
	}
	$("#layerMapZoom").val(zoom);
}

function clearMapElement(){
	$("#systemMap").TqMap("clearMarkers");
	$("#systemMap").TqMap("deleteAllPopupText");
	$("#systemMap").TqMap("removeAllFeatures");
	//$("#systemMap").TqMap("drawFeature",{featureType:""});
	$("#systemMap").TqMap("deactivateMeasureControl");
	$("#systemMap").TqMap("clearMarkers");
	$("#systemMap").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
	$("#systemMap").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFun});
	$("#systemMap").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAdd});
	$("#systemMap").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunUpdate});
	$("#systemMap").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
	$("#systemMap").TqMap("unregisterEvent",{eventName:"click",func:mapClick});
}

function mapClick(e){
	var lon=$("#systemMap").TqMap("get","map").getLonLatFromPixel(e.xy).lon;
	var lat=$("#systemMap").TqMap("get","map").getLonLatFromPixel(e.xy).lat;
	var currentOrgId=$("#currentOrgId").val();
	var currentFeature = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.orgId", value:currentOrgId})[0];
	if(checkPointInPolygonPoints({x:lon,y:lat},layerPoints)){
		var mode = "edit";
		$("#pointsLayerStr").val(layerPoints);
		if($("#layerManageId").val()=='' || $("#layerManageId").val() == 'undefined' || $("#layerManageId").val() == null){
			mode = "add";
		}
		$("#layerMaintanceDialog").createDialog({
			width: 550,
			height: 350,
			zIndex:9999,
			title:((mode=="add")?"新增":"修改")+"组织机构辖区范围",
			url:"${path}/system/gis2DLayerManage/dispatch.action",
			ajaxType:"post",
			postData:{
				"mode":mode,
				"organization.id":currentOrgId,
				"gis2DLayer.centerX":lon,
				"gis2DLayer.centerY":lat,
				"gis2DLayer.remark":currentOrgNode.text,
				"gis2DLayer.gisType":gisType,
				"gis2DLayer.isTransformat":TQMap.isTransformat,
				"gis2DLayer.id":$("#layerManageId").val(),
				"gis2DLayer.points":$("#pointsLayerStr").val(),
				"gis2DLayer.zoom":$("#layerMapZoom").val()
			},
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}else{
		$.messageBox({message:"请在您所画的辖区内确定中心点",level:"error"});
	}
}
function deleteFeature(orgId){
	$.ajax({
		url:PATH + "/system/gis2DLayerManage/deleteGis2DLayerByOrganizationId.action?organization.id=" + orgId,
		success: function(data) {
			if(data==true){
				$.messageBox({
	                message:"辖区删除成功"
	            });
				$("#layerManageId").val("");
				clearMapElement();
				displayButtons();
			}else{
				$.messageBox({level:"error",message:data });
			}
        },
        error : function(){
            $.messageBox({level:"error",message:"系统错误，请与管理员联系"});
        }
	});
}
//删除本级的区域信息
function deleteSameLevelFeature(orgId){
	$.ajax({
		url:PATH + "/system/gis2DLayerManage/deleteSameLevelLayerByOrgId.action?organization.id=" + orgId,
		success: function(data) {
			if(data==true){
				$.messageBox({ message:"辖区删除成功"  });
				$("#layerManageId").val("");
				clearMapElement();
				displayButtons();
			}else{
				$.messageBox({level:"error", message:data });
			}
        },
        error : function(){
            $.messageBox({level:"error",message:"系统错误，请与管理员联系"});
        }
	});
}

function displayButtons(layerData){
	$("#add").buttonEnable();
	$("#update").buttonDisable();
	$("#delete").buttonDisable();
	$("#sameLevelDelete").buttonDisable();
	if(layerData != null && layerData.id!=null){
		$("#add").buttonDisable();
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
		$("#sameLevelDelete").buttonEnable();
	}else{
		$("#add").buttonEnable();
		$("#update").buttonDisable();
		$("#delete").buttonDisable();
		$("#sameLevelDelete").buttonDisable();
	}
}

function redrawOrgFeatures(isShowChild,isShowMy){
	var parentOrgId = $("#parentOrgId").val(),
			orgId = $("#currentOrgId").val();
	$("#systemMap").TqMap("removeAllFeatures");
	clearMapElement();
	//显示当前组织机构下辖区域
	if(isShowChild && orgId){
		showUnderGis2DLayers(orgId,null,null,{fillColor:"#2E2D2D",strokeWidth:0});
	}
	if(parentOrgId && parentOrgId!="orgTree-root"){
		var parentLayerData = getGis2DLayerDataByOrgId(parentOrgId);
		if(parentLayerData){
			loadFeature(parentLayerData);//显示上级地图区域 
			//显示同级别已画的区域信息
			showUnderGis2DLayers(parentOrgId,orgId,isShowMy,{strokeWidth:1,strokeColor:"#066CCA"});
		}else if(isShowMy){
			loadFeature(getGis2DLayerDataByOrgId(orgId));//显示本级地图区域
		}
	}
}

//显示同级别已画的区域信息
function showUnderGis2DLayers(parentOrgId,orgId,isShowCurrentOrg,featureStyle){
	var layerDataList = getUnderGis2DLayersByOrgId(parentOrgId);
	for(var i=0;layerDataList && i<layerDataList.length;i++){
		var color = getFillColorUrl(i);
		var layerData = TQConvert.toPoints(layerDataList[i])
		if( isShowCurrentOrg==false && layerData.organization.id==orgId){
			continue;
		}
		var data = {orgId:layerData.organization.id,parentOrgId:parentOrgId,lon:layerData.lon,lat:layerData.lat};
		var style = {fillColor:color,fillOpacity:0,strokeWidth:1.5,data:data};
		if(layerData.organization.id!=orgId){
			$("#systemMap").TqMap("addLabel",{lon:layerData.lon,lat:layerData.lat,label:layerData.organization.orgName});
			//$.extend(data,{name:layerData.organization.orgName});
			$.extend(style, {fillOpacity:0.4},featureStyle);
		}
		loadFeature(layerData,style);
	}
}
//画面结束方法
function drawPolygonEndFunc(event){
	var parentOrgId = $("#parentOrgId").val();
	var parentFeature = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.orgId", value:parentOrgId})[0];
	if(parentFeature && !checkPolygonsInPolygon([event],parentFeature)){
		$.messageBox({message:"已超出您所在上级的管辖区域，您无权划分，请重新划分区域!",level:"error"});
		return false;
	}
	$("#systemMap").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFun});
	$("#systemMap").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
	layerPoints = event.geometry+"";
	$("#systemMap").TqMap("deactivateMeasureControl");
	$("#systemMap").TqMap("featureShow",{
		points:layerPoints,
		featureId:"_featureShow",
		fill:false
	});
	$("#systemMap").TqMap("clearMarkers");
	$("#systemMap").TqMap("addMarker",{
		iconUrl:"${resource_path}/resource/images/u331.png",
		markerW:24,
		markerH:27
	});
	$("#systemMap").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
	$("#systemMap").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFun});
	$("#systemMap").TqMap("registerEvent",{eventName:"click",func:mapClick});
}

//将要保存的选区集,描点画面保存坐标集
function modifyFeatureEndFunc(feature){
	var control = $("#systemMap").TqMap("get","measureControl");
	if(control.isModifyEnd == false) return;
	layerPoints=feature.geometry+ "";
	var currentOrgId=$("#currentOrgId").val(),
		parentOrgId = $("#parentOrgId").val();
	var childFeatures = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.parentOrgId", value:currentOrgId});
	var currentFeature = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.orgId", value:currentOrgId})[0];
	var parentFeature = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.orgId", value:parentOrgId})[0];
	if(parentFeature && !checkPolygonsInPolygon([currentFeature],parentFeature)){
		$.messageBox({message:"已超出您所在上级的管辖区域，您无权划分，请重新划分区域!",level:"error"});
		return;
	}
	//for(var i=0;childFeatures && i<childFeatures.length;i++){
		if(!checkPolygonsInPolygon(childFeatures,currentFeature)){
			$.messageBox({message:"区域必须包含下级的管辖区域，请重新划分区域!",level:"error"});
			return;
		}
	//}
	$("#systemMap").TqMap("deactivateMeasureControl");
	$("#systemMap").TqMap("clearMarkers");
	$("#systemMap").TqMap("addMarker",{
		iconUrl:"${resource_path}/resource/images/u331.png",
		markerW:24,
		markerH:27
	});
	$("#systemMap").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFun});
	$("#systemMap").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
	$("#systemMap").TqMap("registerEvent",{eventName:"click",func:mapClick});
}

//拖动Feature结束事件
function dragFeatureEndFunc(feature,dragLonLat){
	if(SpecialMapByOrg.isDrawLayerStart($.getSelectedNode(tree).attributes.departmentNo)){
		return true;
	}
	layerPoints=feature.geometry+ "";
	var lon=dragLonLat.lon;
    var lat=dragLonLat.lat;
    var currentOrgId=$("#currentOrgId").val(),
		parentOrgId = $("#parentOrgId").val();
    var childFeatures = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.parentOrgId", value:currentOrgId});
    var parentFeature = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.orgId", value:parentOrgId})[0];
    for(var i=0;childFeatures && i<childFeatures.length;i++){
    	if(checkPointInPolygonPoints({x:lon,y:lat},childFeatures[i].geometry+"")){
			$.messageBox({message:"区域必须包含下级的管辖区域，请重新修改区域!",level:"error"});
			$("#systemMap").TqMap("modifyBackToUpper");
			return false;
		}
    }
    if(parentFeature!=null&&parentFeature!=""){
    	if(!checkPointInPolygonPoints({x:lon,y:lat},parentFeature.geometry)){
    		$.messageBox({message:"已超出您所在上级的管辖区域，您无权划分，请重新修改区域!",level:"error"});
    		$("#systemMap").TqMap("modifyBackToUpper");
    	}
    }
}

//画面点击地图事件
function drawPolygonClickMapFunc(event){
	if(SpecialMapByOrg.isDrawLayerStart($.getSelectedNode(tree).attributes.departmentNo)){
		return true;
	}
	var components = event.geometry.components[0].components;//坐标
	var lon=components[components.length-2].x;
    var lat=components[components.length-2].y;
    var parentOrgId = $("#parentOrgId").val();
	var parentFeature = $("#systemMap").TqMap("getFeaturesBy",{ key:"data.orgId", value:parentOrgId})[0];
    //验证是否超出上级划分的管辖区域
	if(parentFeature && !checkPointInPolygonPoints({x:lon,y:lat},parentFeature.geometry)){
		$.messageBox({message:"已超出您所在上级的管辖区域，您无权划分，请重新划分区域!",level:"error"});
		$("#systemMap").TqMap("drawBackToUpper");
		return false;
	}
    return true;
}

//检查父级是否划分区域
function checkLayerDataIsDrawn(orgId){
	var isDrawn = false,
		orgLevel = $.getSelectedNode(tree).attributes.orgLevelInternalId,
		orgDepartNo = $.getSelectedNode(tree).attributes.departmentNo;
	var layerData = getGis2DLayerDataByOrgId(orgId);
	if(layerData && layerData.id){
		isDrawn= true;
	}else{
		var cityLevel = Properties.ORG_LEVEL_CITY;
		if(orgLevel > cityLevel){
			$.messageBox({message:"该地图为市级地图！请在市级别划分地图管辖区域!", level:"error"});
			isDrawn= false;
		}else if(orgLevel*10 == cityLevel  //2.5判断
				|| orgLevel == cityLevel   //产品3.0判断
				|| SpecialMapByOrg.isDrawLayerStart(orgDepartNo)){
			isDrawn= true;
		}else if(orgLevel == cityLevel){
			isDrawn= true;
		}else{
			$.messageBox({message:"当前父级组织结构未划分管辖区域!", level:"error"});
			isDrawn= false;
		}
	}
	return isDrawn;
}
function loadFeature(layerData,option){
	try{
		if(layerData){
			$("#systemMap").TqMap("featureShow",$.extend({
				points:layerData.points,
				featureId:layerData.organization.id,
				fillOpacity:0,
				strokeDashstyle: "solid",		//边框样式默认solid(实体)，[dot | dash | dashdot | longdash | longdashdot | solid]
				strokeWidth:3,
				strokeColor:"#FF3333",
				data:{
					orgId:layerData.organization.id
				}
			},option));
		}
	}catch(e){
		console.log("function loadFeature() error: "+e.message);
	}
}
/**获取下辖组织机构区域*/
function getUnderGis2DLayersByOrgId(orgId){
	var layerData=null;
	$.ajax({
		async:false,
		url:"${path}/system/gis2DLayerManage/getUnderGis2DLayersByOrgId.action",
		data:{
			"organization.id":orgId
		},
		success:function(response){
			if(response!=null&&response!=""){
				layerData=response;
			}
		}
	});
	return layerData;
}

function stringFormatter(str){
	if(str==undefined){
		return "";
	}
	return str;
}
function markerMousemove(e){
	$("#systemMap").TqMap("moveTo",{objectName:"marker",e:e});
}
function mouseTipFun(){
	$("#systemMap").TqMap("addMouseTip",{evt:event,msg:"请在所画区域内单击确定中心点"});
}
function mouseTipFunAdd(){
	$("#systemMap").TqMap("addMouseTip",{evt:event,msg:"请单击地图画区域,双击结束画图,按ESC键撤销上一步操作"});
}
function mouseTipFunUpdate(){
	$("#systemMap").TqMap("addMouseTip",{evt:event,msg:"请单击所画区域拖动边点,点击地图结束画图,按ESC键撤销上一步操作"});
}
</script>
