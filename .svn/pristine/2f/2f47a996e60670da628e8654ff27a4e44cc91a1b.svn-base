//加载地图
function loadMap(){
	var option = changeGisType(Properties.USER_ORG_DEPARTMENTNO);//为特定的组织机构      初始化    特定的值
	$("#map").empty();
	var layerData=getGis2DLayerDataByOrgId($("#currOrgId").val());
	var boundsOption = {};
	if(layerData!=null && layerData!=""){
		boundsOption={
//			boundsMinLon:layerData.minLon,
//			boundsMaxLon:layerData.maxLon,
//			boundsMinLat:layerData.minLat,
//			boundsMaxLat:layerData.maxLat,
			zoom:layerData.zoom,
			lon:layerData.lon,
			lat:layerData.lat
		}
	}
	
	var tqmapOption = {
			upgradeDiv:"TwoMapView",
			isShowOverviewMap:true,                     //是否显示鹰眼
			isShowM2D: true,						            //是否加载二维地图
			isShowM3D: true,						            //是否加载三维地图
			isShowMS: true,						            //是否加载卫星地图
			isShowWFS: true,						            //是否加载热区
			gisType:gisType
	}
	$.extend(tqmapOption,option,boundsOption);
	$("#map").TqMap(tqmapOption);
	var map = $("#map").TqMap("get","map");
	if(map){
		prevsZoom=map.getZoom();//获取地图当前层级
		prevsCenterLonLat =map.getCenter();//获取当前地图的中心点
	}
	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:getMapMousemoveLonLat});//鼠标移动事件，用于获取鼠标当前坐标
	$("#map").TqMap("registerEvent",{eventName:"moveend",func:mapMoveEndFun});//注册地图拖动结束事件
    if(isGridOrganization()){//如果是网格则加载热区信息
    }else{
    	loadSubordinateMapArea($("#currOrgId").val());//加载下辖机构地图区域
    }
}

function changeGisType(orgDepartNo){//为特定的组织机构      初始化    特定的值
	var that = this;
	if(orgDepartNo==null) return;
	return TQMap.OrgMap.extend({
		init:function(orgMapInfo){
			gisType = orgMapInfo.gisType;
			var gisTypeControl = $("#map").TqMap("get","GisTypeControl");
			if(gisTypeControl){
				gisTypeControl.changeTool(orgMapInfo.gisType);
			}
			if(orgMapInfo.gisType == TQMap.GisType.M3D){
				bindInWfs = true;
				$("#map").TqMap( "activateSelectFeature",{sfname:"wfsSelectF"});
			}
		},
		change:function(orgMapInfo){
			$("#"+orgMapInfo.gisType).click();
		}
	}).changeGisType("map",gisType,orgDepartNo);
}
	

/*---------------------------------建筑物热区事件-----------------------------------*/

//加载建筑物热区且激活热区
function loadAndActivateWfs(){
	$("#map").TqMap("deactivateSelectFeature",{sfname:"wfsSelectF"});
	deleteSubordinateMapAreaInfo();
	
	$("#map").TqMap( "createDragPanFeature",{
		layer:"wfslayer",             //SelectFeature操作的图层名称
		name:"wfsSelectF", 
		clickFeature:wfsFeatureClickFunc,		//鼠标点击事件
		onSelect:wfsFeatureSelectFunc,			//鼠标放上去事件
		onUnselect:wfsFeatureUnselectFunc,		//鼠标移开事件
		onMouseMove:wfsFeatureMouseTip      //鼠标未按下移动事件
	})
}

//销毁建筑物热区图层选择控件
function destroyAndDeactivateWfs(){
	if($("#map").TqMap("get","wfsSelectF")) $("#map").TqMap("get","wfsSelectF").unselectAll();
	$("#map").TqMap("deactivateSelectFeature",{sfname:"wfsSelectF"});
	$("#map").TqMap("destroySelectFeature",{sfname:"wfsSelectF"});
	//$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:featureMouseOverFunc});
	if($("#map").TqMap("get","wfslayer")) $("#map").TqMap("get","wfslayer").redraw();
	$("#map").TqMap("deleteMouseTip");
}

//建筑物热区鼠标离开事件
function wfsFeatureUnselectFunc(feature){
	$("#map").TqMap("deleteMouseTip");
}

//建筑物热区鼠标放上去事件
function wfsFeatureSelectFunc(feature){
	$("#map").data("wfsFeatureMouseTip",null);
	var featureInfo = $("#map").TqMap("getInfoFromFeature",feature);
	$("#map").data("wfsFeatureMouseTip",featureInfo.name);
}

function wfsFeatureMouseTip(){
	var feature = $("#map").TqMap("get","wfslayer").selectedFeatures;
	var mouseTip = $("#map").data("wfsFeatureMouseTip");
	if(feature && feature.length>0 && mouseTip ){
		$("#map").TqMap("addMouseTip",{evt:event,msg:mouseTip});
	}
}

//建筑物热区点击事件
function wfsFeatureClickFunc(feature){
	if($("#addHourseInfo").val()==true || $("#addHourseInfo").val()=="true" ){
		$.messageBox({
			message:"不能在wfs要素上进行操作！",
			level:'error'
		});
		return;
	}
	var sf = $("#map").TqMap("get","wfslayer").selectedFeatures;
	if(sf==null || sf.length<1){
		return;
	}
	if(!isGridOrganization()){
		$.messageBox({
			message:"请选择到网格级别进行操作！",
			level:'error'
		});
		return;
	}
	sf = sf[0];
	var featureInfo = $("#map").TqMap("getInfoFromFeature",sf);
	var orgId=$('#currOrgId').val();
	$.post(PATH+"/gis/hourseTwoDimensionMapManage/addHourseInfo.action",
		{
			"hourseInfo.lon":featureInfo.lon,
			"hourseInfo.lat":featureInfo.lat,
			"hourseInfo.featureId":featureInfo.id,
			"hourseInfo.organization.id":orgId,
			"hourseInfo.isTransformat":TQMap.isTransformat,
			"gisType":gisType
		},
		function(result){
			var centerLon=feature.geometry.getBounds().getCenterLonLat().lon;
			var centerLat=feature.geometry.getBounds().getCenterLonLat().lat;
			var isbound = boundCommonOnFeature(result.id,centerLon,centerLat);
			if(isbound==false){
				$("#map").TqMap("addPopup",{
					url:PATH+"/gis/hourseTwoDimensionMapManage/dispatch.action?buildDataInfoVo.lon="+centerLon+"&buildDataInfoVo.lat="+centerLat+"&buildDataInfoVo.gisType="+gisType+"&buildDataId="+result.id+"&organization.id="+$("#currOrgId").val(),
					lon:mapMousemoveLon,
					lat:mapMousemoveLat,
					lonlat:{lon:mapMousemoveLon,lat:mapMousemoveLat},
					popupW:440,
					popupH:250
				});
				$("#isViewHourse").attr("value",false);//用来判断建筑物绑定--解除绑定后，地图上是否还显示建筑物图标
			}
		},'json'
	);
	
}

//建筑物点击事件
function hourseClick(e){
	clearFeatureByLayerData();//删除自定义feature
	var id=e.object.markerId.split("_")[0];
	var lon=e.object.data.lon;
	var lat=e.object.data.lat;
	var imgUrl=e.object.icon.url;
	var tableName=_tableName;
	var event=_event;
	if($("#deleteHourse").val()=="delete"||$("#"+e.object.events.element.id+"_delete").hasClass("canDel")){
		deleteHourseInfo(id);
	}else if(tableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PLACE'/>" && event=="绑定房屋"){
		boundCommonOnHourse(id,lon,lat);
		_tableName=null;
		_event=null;
		_bound=0;
		return ;
	}else if(tableName == "<s:property value='@com.tianque.openLayersMap.util.BigModeType@HOUSEPROPERTY'/>"){
		boundCommonOnHourse(id,lon,lat);//点击住房绑定
		_tableName=null;
	}else if(tableName == "<s:property value='@com.tianque.openLayersMap.util.BigModeType@ISSUE'/>"){
		boundCommonOnMap(e);
		_tableName=null;
		return ;
	}else if(event=="bindInBuild"){
		boundCommonOnHourse(id,lon,lat);
		_event=null;
		return;
	}else{
		viewHoursePopup(id,lon,lat,imgUrl);
		$("#isViewHourse").attr("value",true);//用来判断建筑物绑定--解除绑定后，地图上是否还显示建筑物图标
	}
}


//清除下辖地图区域的相关信息
function deleteSubordinateMapAreaInfo(){
	$("#map").TqMap("deactivateSelectFeature");
	$("#map").TqMap("deleteAllPopupText");
	$("#map").TqMap("removeAllFeatures");
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:subordinateFeatureMouseTip});
	$("#map").TqMap("deleteMouseTip");
}

//加载下辖机构地图区域
function loadSubordinateMapArea(orgId){
	$("#map").TqMap("deactivateSelectFeature",{sfname:"wfsSelectF"});
	deleteSubordinateMapAreaInfo();
	if(!isGridOrganization()){//如果是网格级别则不加载
		var gis2DLayers = getUnderGis2DLayersByOrgId(orgId);
		if(gis2DLayers){
			viewSubordinateFeatureAndPopupText(gis2DLayers);//显示下辖边界和名称
		}
	}else{
	}
}
//显示下辖边界和名称
function viewSubordinateFeatureAndPopupText(response){
	for(var i=0;i<response.length;i++){
		var layerData = response[i];
		layerData = TQConvert.toPoints(layerData);
		var orgId ="";
		var text = "";
		if(layerData.organization){
			orgId = layerData.organization.id;
			text = layerData.organization.orgName;
		}
		if(layerData.popupText!=null){
			text = layerData.popupText;
		}
		var popText=$("#map").TqMap("addPopupText",{
			 lon:layerData.lon,
			 lat:layerData.lat,
			 popupText:text,
			 data:{orgId:orgId},
			 backgroundColor:'#66CC33',
			 autoSize:true
		});
		var color = getFillColorUrl(i);
		$("#map").TqMap("featureShow",{
			points:layerData.points,
			featureId:layerData.id,
			fillColor:color,
			strokeColor:color,
			data:{lon:layerData.lon,lat:layerData.lat,orgId:orgId}
		});
	}
	var timeout;
	$("#map").TqMap( "createDragPanFeature",{
		layer:"vector",             //SelectFeature操作的图层名称
		name:"selectF", 
		clickFeature:function(feature){
			$("#map").TqMap("deactivateSelectFeature",{sfname:"wfsSelectF"});
			deleteSubordinateMapAreaInfo();
			var layerData1=getGis2DLayerDataByOrgId(feature.data.orgId);
			if(layerData1!=null && layerData1!=""){
				$("#map").TqMap("setCenter",{lon:layerData1.lon,lat:layerData1.lat,zoom:layerData1.zoom});
			}else{
				$.messageBox({message:"当前部门还未划分区域!",level:"error"});
			}
		},
		onSelect:function(feature){
			
			//统计下辖信息【实有人口总数、实有房屋、常住人口、流动人口、境外人员、未落户人员、重点人员、关怀对象、其他关注对象、组织场所】
			if(feature.data.orgId!=null&&feature.data.orgId!=""&&feature.data.orgId!="undefined"){
				var prevOrgId = $("#map").data("subordinateFeatureOrgId");
				if(prevOrgId == feature.data.orgId) return;
				$("#map").data("subordinateFeatureMouseTip",null);
				clearTimeout(timeout);
				timeout = setTimeout(function() {
					TQMap.ajax({
					cache:true,
					async:true,
					url:PATH + "/gis/twoDimensionMapStatisticCommonManage/countJurisdictionStatisticByOrgId.action",
					data:{
						"organization.id":feature.data.orgId
					},
					success: function(data) {
						if(data!=null && data!=""){
							$("#map").data({
									subordinateFeatureMouseTip : data,
									subordinateFeatureOrgId : feature.data.orgId
							});
						}
					}
				})
			}, 5000);
			}
		},
		onUnselect:function(){
			$("#map").TqMap("deleteMouseTip");
			clearTimeout(timeout);
		},
		onMouseMove:function(e,isMouseDown){
			if(!isMouseDown){
				subordinateFeatureMouseTip();
			}else{
				$("#map").TqMap("deleteMouseTip");
			}
		}
	})
}

//下辖区域鼠标提示
function subordinateFeatureMouseTip(){
	var feature = $("#map").TqMap("get","vector").selectedFeatures;
	var mouseTip = $("#map").data("subordinateFeatureMouseTip");
	if(feature && feature.length>0 && mouseTip){
		$("#map").TqMap("addMouseTip",{evt:event,msg:mouseTip});
	}
}

//----------------------------------------------------建筑物查看页面方法------------------------------------
//绑定建筑物
function bindingBuilding(buildDataId,lon,lat){
	var layerData=getGis2DLayerDataByOrgId($('#currOrgId').val());
	if(layerData==null || layerData==""){
		$.messageBox({message:"当前部门还未划分区域!",level:"error"});
		return;
	}
	if(isGridOrganization()){
		if(checkPointInPolygonPointsForBind({x:lon,y:lat},layerData.points)){
			$("#budlingDialog").createDialog({
		        width: 600,
		        height: 360,
		        title:"建筑物绑定",
		        url:PATH+"/openLayersMap/building/maintainBuildingDlg.jsp?centerLon="+lon+"&centerLat="+lat+"&buildDataId="+buildDataId,
		        buttons: {
		            "绑定并关闭" : function(event){
		            	jQuery("#maintainBindingHouseForm").submit();
		            },
		            "关闭" : function(){
		            	jQuery(this).dialog("close");
		            }
		        }
		    });
		}else{
			$.messageBox({message:"请在您所画的辖区内绑定建筑物",level:"error"});
			showFeature(layerData);
		}
	}else {
		$.messageBox({
 			message:"请选择网格层级进行绑定！",
 			level:'error'
 		});
 	}
}



//点击建筑物绑定重点场所
function boundKeyPlaceing(lon,lat){
	var layerData=getGis2DLayerDataByOrgId($("#currOrgId").val());
	if(layerData==null || layerData==""){
		$.messageBox({message:"当前部门还未划分区域!",level:"error"});
		return;
	}
	if(isGridOrganization()){
		if(checkPointInPolygonPointsForBind({x:lon,y:lat},layerData.points)){
			document.getElementById('placeModules').style.display='block';
		}else{
			$.messageBox({message:"请在您所画的辖区内绑定场所",level:"error"});
			showFeature(layerData);
		}
	}else{
		$.messageBox({
 			message:"请选择网格层级进行绑定！",
 			level:'error'
 		});
	}
}

//绑定场所列表
function showUnbindingPlaceDialog(moduleType, buildDataId, lon, lat, innerKey){
	var mainTableName;
	if(innerKey!=null && innerKey!="" && typeof(innerKey)!="undefined" ){
		$(innerKey).find("option").each(function(){
			if($(this).attr("selected"))
				mainTableName=$(this).attr("innerKey");
		});
	}
	
	if(moduleType.length < 1) {
		document.getElementById('placeModules').style.display='none';
		return;
	}
  	if(isGridOrganization()){
		$("#bindingPlaceDialog").createDialog({
	        width: 600,
	        height: 360,
	        title:"场所绑定",
	        url:PATH+"/openLayersMap/place/maintainBindPlaceDlg.jsp?orgId="+$('#currOrgId').val()+"&orgLevle="+$('#selectOrgLevel').val()+"&buildDataId="+buildDataId+"&centerLon="+lon+"&centerLat="+lat+"&moduleType="+moduleType+"&mainTableName="+mainTableName,
	        buttons: {
	            "绑定并关闭" : function(event){
	            	initPlaceModules();
	            	jQuery("#maintainBindingPlaceForm").submit();
	            },
	            "关闭" : function(){
	            	initPlaceModules();
	            	jQuery(this).dialog("close");
	            }
	        }
	    });		
 	}else {
 		$.messageBox({
 			message:"请选择网格层级进行绑定！",
 			level:'error'
 		});
 	}
}

//绑定场所后将下拉选框隐藏选中值置为请选择
function  initPlaceModules(){
	$("#placeModules").find("select").find("option").removeAttr("selected");
	$("#placeModules").find("select").find("option").eq(0).attr("selected",true);
	//document.getElementById('placeModules').style.display='block';
}
//查看楼宇布局
function viewBuildLayout(buildingId,buildingName){
	$("#buildLayoutDialog").createDialog({
	    width: 950,
	    height: 600,
	    zIndex:99999,
	    title:'【'+buildingName+'】详情',
	    url:"/openLayersMap/building/builddatasLayout.jsp?id="+buildingId,
	    buttons: {
	        "关闭" : function(){
	            $(this).dialog("close");
	        }
	    },
	    shouldEmptyHtml:false
	});
}

function changeTypeByOrgChange(){
	var $searchInView_jurisdictionsIssue = $("#sivList").find("li[data-value='jurisdictionsIssue']").show();
	var $layer_jurisdictionsIssue = $("#showPersonLayer #jurisdictionsIssue").parents("li:eq(0)").show();
	$layer_jurisdictionsIssue.find("li>span").show();
	var $tool_showJurisdictionArea = $("#tool_tollCon .showJurisdictionArea").parents("a:eq(0)").show();
	if(isGridOrganization()){
		$searchInView_jurisdictionsIssue.hide();
		$layer_jurisdictionsIssue.hide();
		$tool_showJurisdictionArea.hide();
		if($layer_jurisdictionsIssue.hasClass("currentPosClick")){
			$layer_jurisdictionsIssue.removeClass("currentPosClick").find(".currentPosClick").removeClass("currentPosClick");
			$(".plaint").click();
		}
	}
}

/*//计算地图滑动是否在有效距离内
function moveDistanceManage(){
	//获取当前屏幕的范围坐标
	var imap = $("#map").TqMap("get","map");
	var screen = imap.getExtent().toArray();
	var minX = screen[0];
	var minY = screen[1];
	var maxX = screen[2];
	var maxY = screen[3];
	var screenLonDValue=(maxX-minX)/2;//经度偏差值为当前屏幕总距离的三分之一
	var screenLatDValue=(maxY-minY)/1;//纬度偏差值为当前屏幕总距离的二分之一
	var zoom=imap.getZoom();
	var centerLonLat=imap.getCenter();
	//var zoomLevels=imap.getNumZoomLevels();//获取地图缩放级别的总数
	if(centerLonLat==null||centerLonLat==""||prevsCenterLonLat==null||prevsCenterLonLat==""){
		return false;
	}
	if(prevsZoom!=zoom){//地图级别更改时，算有效距离
		return true;
	}
	var lonDValue=centerLonLat.lon-prevsCenterLonLat.lon;
	var latDValue=centerLonLat.lat-prevsCenterLonLat.lat;
	if(lonDValue<0){
		lonDValue=-lonDValue;
	}
	if(latDValue<0){
		latDValue=-latDValue;
	}
	if(latDValue>screenLatDValue||lonDValue>screenLonDValue){
		return true;
	}else{
		return false ;
	}
	return true;
}*/

//计算地图滑动是否在有效距离内
function moveDistanceManage(){
	//获取当前屏幕的范围坐标
	var screen = $("#map").TqMap("getScreenExtent");
	var screenLonDValue=screen.offsetX;//滑动搜素时，经度的有效距离值
	var screenLatDValue=screen.offsetY;//滑动搜素时，纬度的有效距离值
	var zoom= $("#map").TqMap("get","map").getZoom();
	var centerLonLat= $("#map").TqMap("get","map").getCenter();
	if(prevsCenterLonLat==null){
		return false;
	}
	var lonDValue = Math.abs(centerLonLat.lon-prevsCenterLonLat.lon);
	var latDValue = Math.abs(centerLonLat.lat-prevsCenterLonLat.lat);
	//算滑动搜素有效距离
	if(prevsZoom!=zoom //当地图级别更改时
			|| Boolean.IS_ORG_CHANGE //当组织机构变化时
			|| latDValue>screenLatDValue || lonDValue>screenLonDValue//当经度或纬度变化范围超过规定值时
			||false){
		Boolean.IS_ORG_CHANGE = false;
		return true;
	}else{
		return false ;
	}
}

//地图滑动结束事件
function mapMoveEndFun(e){
	$(".ui-multiselect-menu:visible .ui-multiselect-close").click();//清除周边搜索下拉框
	if(IS_GISTYPE_CHANGE_END==false) return;
	try{
		$("#map").TqMap("setLayerZIndex",{layerName:'markers',zIndex:750});// 关闭滑动搜索时markers图层的zIndex不对，在此修正
	}catch(e){
		TQMap.log("set layerZIndex(markers) err:"+e);
	}
	TQMap.setTimeout(function(){
		var imap = $("#map").TqMap("get","map");
		$.ajax({
			url:PATH + "/system/gis2DLayerManage/getGis2DLayerByCenterPointAndZoom.action",
			data:{
				"gis2DLayer.organization.id":$("#currOrgId").val(),
				"gis2DLayer.zoom": imap.getZoom(),
				"gis2DLayer.centerX": imap.getCenter().lon,
				"gis2DLayer.centerY": imap.getCenter().lat,
				"gis2DLayer.gisType":gisType
			},
			success: function(data) {
				if(data && data.organization && data.orgInternalCode.indexOf(userOrgInternalCode)>=0){
					var org = data.organization;
					if($("#currOrgId").val()!=org.id){//判断组织机构是否变更
						$("#currOrgId").attr({
							"orgLevelInternalId":org.orgLevel.internalId,
							text:org.orgName,
							value:org.id
						});
						initOrg();
						changeGisType(org.departmentNo);//为特定的组织机构      初始化    特定的值
						var $currentPos = $("#showPersonLayer li.currentPosClick");
						if(isNullQueryString()&&$currentPos.length==0){//当无搜索条件并且未选中图层时加载下辖地图区域
							loadSubordinateMapArea(org.id);//加载下辖地图区域
						}
						loadPopedomDistributionInfo();//加载辖区分布信息 
						Boolean.IS_ORG_CHANGE = true;
					}
				}
				_mapMoveEndFunc(e);
	        }
		});
	},1000)
}
function _mapMoveEndFunc(e){
	var imap = $("#map").TqMap("get","map");
	var zoom=imap.getZoom();
	if(IS_MAP_MOVE_SEARCH && moveDistanceManage()){
		prevsCenterLonLat=imap.getCenter();
		prevsZoom=imap.getZoom();
		$("#map").TqMap("deleteMouseTip");//删除提示
		//$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:wfsFeatureMouseTip});//注销建筑物名称鼠标跟随提示事件
		clearMarkersByMarkerId_objectName("common");//删除公共的marker图标
		clearGpsMapAllMarker();
		$("#map").TqMap("deletePopup");//删除popup
		if(typeof clearDetailsListInfo!='undefined' ){//判断方法是否存在
			clearDetailsListInfo();//清空详情列表的内容
		}
		var $currentPos = $("#showPersonLayer li.currentPosClick");
		//建筑物滑动搜索
//		var node = $(":checkbox[name=1]#hourseInfo")[0];
//		if($(node).attr("checked")){
//			var url="";
//			var mainTableName=$(node).attr("id");
//			var modeType=$(node).attr("class");
//			var functionType = "refineSearch"; 
//
//			if(isTownDownOrganization()){
//				modeType+="StatisticService";
//				url=PATH+'/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
//			}else{
//				modeType+="SearchService";
//				url=PATH+'/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
//			}
//			loadTabListInfo(PATH+"/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+mainTableName+"&modeType="+modeType+"&functionType="+functionType);
//		}
		if(!isNullQueryString()){//精确搜索
			deleteSubordinateMapAreaInfo();
			$("#sivSubmitBtn").click();
		}else if($currentPos.length==1){//没有子类的图层
			if($("#dustbin").closest("li").hasClass("currentPosClick")){
				var modeType="cityComponentsMap";
				var tableName="dustbin";
				var url="";
				var functionType ="refineSearch"; 
				var modeTypeDetailView="cityComponentsMapDetailViewService";//详情查看modeType
				if(isTownDownOrganization()){
					modeType+="StatisticService";
					if(modeType=="commonMapStatisticService"){	//通用大类别url
						url=PATH+'/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgId.action';
					}else{	
						url=PATH+'/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
					}
				}else{
					modeType+="SearchService";
					url=PATH+'/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
				}
				
				var type=$("#partTypeCache").val();
				var childTableName=$("#partNameCache").val();
				loadTabListInfo(PATH+"/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+tableName+"&type="+type+"&childTableName="+childTableName+"&modeType="+modeType+"&functionType="+functionType+"&modeTypeDetailView="+modeTypeDetailView);
				return false;
			}
			deleteSubordinateMapAreaInfo();
			$currentPos.removeClass("currentPosClick");
			$currentPos.click();
		}else if($currentPos.length==2){//拥有子类的图层
			deleteSubordinateMapAreaInfo();
			var $childCurrentPos = $currentPos.eq(1);
			$childCurrentPos.removeClass("currentPosClick");
			$childCurrentPos.click();
		}else if($currentPos.length==0){//基础图层(建筑物)
			var $boxShade = $(".boxShadeC :checkbox[name=1]");
			for(var i=0;i<$boxShade.length;i++){
				if($boxShade[i].checked){
					$boxShade.eq(i).click();
					$boxShade.eq(i).attr("checked","checked")
					break;
				}
			}
		}
	}
}

function queryStringValueIsNull(){
	var str=$("#queryString").val();
	if(str==null||str=="请输入(姓名/身份证号码/地址)" || str=="请输入(名称/地址)" || str=="请输入(房主姓名/常住地址)" || str=="请输入(服务单号/事件名称)" || str=="请输入(姓名/身份证号码)"){
		return true;
	}else{
		return false;
	}
}

function viewHoursePopup(id,lon,lat,imgUrl){
	clearFeatureByLayerData();//删除自定义feature
	$("#map").TqMap("addPopup",{
		url:PATH+"/gis/hourseTwoDimensionMapManage/dispatch.action?buildDataInfoVo.lon="+lon+"&buildDataInfoVo.lat="+lat+"&buildDataInfoVo.gisType="+gisType+"&buildDataId="+id+"&organization.id="+$("#currOrgId").val(),
		lon:lon,
		lat:lat,
		lonlat:{lon:lon,lat:lat},
		popupW:440,
		popupH:250
	});
}

//获取场所的名称
function getLocationName(type) {
	var title;
	$.ajax({
		async:false,
		url:PATH+'/twoDimensionMap/keyPlaceMapManage/getLocationNameByType.action',
		datatype:"json",
		data:{
			"type":type
		},
		success:function(data){
			title = data;
		}
	});
	return title;
}

//显示所画的辖区
function showFeature(layerData){
	if(layerData!=null&&layerData!=""){
		$("#map").TqMap("featureShow",{
			points:layerData.points,
			featureId:layerData.id,
			fill:false,
			strokeWidth:1.5,
			fillOpacity:0
		});
	}
	$("#map").TqMap("deletePopup");//删除popup
}

//通过自定义的markerId下划线后面的对象名称删除marker【由于有id加密的东西所以下划线最后的一个和markerId_objectName相同的删除】
function clearMarkersByMarkerId_objectName(markerId_objectName){
	var markers = $("#map").TqMap("get","markers");
	if(markers && markerId_objectName  && markers.markers.length>0){
		for(var i = 0; i < markers.markers.length;i++){
			if(markers.markers[i].markerId.split("_")[markers.markers[i].markerId.split("_").length-1]==markerId_objectName){
				markers.removeMarker(markers.markers[i]);
				i--;
			}
		}
 	}
 }

//通过自定义的featureId下划线后面的对象名称删除feature
function clearFeaturesByMarkerId_objectName(featureId_objectName){
	var vector = $("#map").TqMap("get","vector")
	 if(vector && featureId_objectName && vector.features.length>0){
		for(var i = 0; i < vector.features.length; i++){
			if(vector.features[i].featureId!=null&&(vector.features[i].featureId+"").split("_")[1]==featureId_objectName){
				vector.destroyFeatures(vector.features[i]);
				i--;
			}
		}
	}
}

//popupText鼠标跟随事件
function popupTextMousemove(e){
	//坐标修改，防止层被遮盖
	e.xy.x=e.xy.x+10;
	e.xy.y=e.xy.y-20;
	$("#map").TqMap("moveTo",{objectName:"popupText",e:e});
}
//marker鼠标跟随事件
function markerMousemove(e){
	$("#map").TqMap("moveTo",{objectName:"marker",e:e});
}


//获取地图鼠标跟随的坐标点
function getMapMousemoveLonLat(e){
	if(e!=null && e!="undefined"){
		var imap = $("#map").TqMap("get","map");
		mapMousemoveLon=imap.getLonLatFromPixel(e.xy).lon;
		mapMousemoveLat=imap.getLonLatFromPixel(e.xy).lat;
	}
}


//注册marker的鼠标得到焦点和失去焦点事件（含鼠标放上去显示标题信息）,创建marker时，需添加默认图片路径、鼠标放上去路径、标题名称
function registerMarkerMouseoverAndMouseoutEvent(){
	$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"mouseover",func:function(e){
		e.object.setUrl(e.object.data.mouseoverImgUrl);
		$(e.object.events.element).attr("title",e.object.data.title);
		e.object.inflate(1.41);
	}});

	$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"mouseout",func:function(e){
		e.object.setUrl(e.object.data.imgUrl);
		e.object.inflate(0.71);
	}});
}

function clearMapAllMarkerAndPopup(){
	$("#map").TqMap("deletePopup");
	$("#map").TqMap("deleteAllPopupText");
	clearMarkersByMarkerId_objectName("place");
	clearMarkersByMarkerId_objectName("houseProperty");
	clearMarkersByMarkerId_objectName("person");
	clearMarkersByMarkerId_objectName("issue");
	clearMarkersByMarkerId_objectName("hourseInPlace");
	clearMarkersByMarkerId_objectName("search");
	clearMarkersByMarkerId_objectName("searchPerson");
	clearMarkersByMarkerId_objectName("common");
}
//删除自定义feature
function clearFeatureByLayerData(){
	var layerData=getGis2DLayerDataByOrgId($('#currOrgId').val());
	$("#map").TqMap("deleteFeatureByFeatureId",{featureId:layerData.id});//通过自定义的featureId删除feature
}

function clearPopupTextById(id){
	var popupTexts=$("#map").TqMap("get","map").popups;
	for(var i=0;i<popupTexts.length;i++){
		if(popupTexts[i].popupTextId==id){
			popupTexts[i].destroy();
		}
	}
}

function initOrg(){
	var selectFrame=$("<strong/>").addClass("btnMod");
	$("#globalOrgBtn").html($("#currOrgId").attr("text")).append(selectFrame);
	changeTypeByOrgChange();
}

function loadTabListInfo(loadUrl){
	if(loadUrl==null || loadUrl=="") return;
	showDetailsList();
	$("#tabList").load(loadUrl);
	$("#tabList").data("reloadTabList",function(){
		var lis = $("#tabList").find(".resultlist>li");
		if(lis!=null && lis.length>0){
			$("#tabList").load(loadUrl);
		}
	})
}

//清除辖区分布统计的面
function clearDistributionFeatureAndPopupText(){
	$("#map").TqMap("deleteAllPopupText");
	clearFeaturesByMarkerId_objectName("feature");//清除辖区分布统计的面
}
//清除绑定事件
function clearBoundEvent(){
	if(_bound==1){
		_event="";
		$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAddPlace});
		$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAddIssue});
		$("#map").TqMap("deleteMouseTip");
		$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
		clearMarkersByMarkerId_objectName("commmon");
		$("#map").TqMap("unregisterEvent",{eventName:"click",func:boundCommonOnMap});
		_bound==0;
	}
}
