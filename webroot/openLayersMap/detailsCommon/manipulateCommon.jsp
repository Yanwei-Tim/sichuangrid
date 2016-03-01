<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">

//绑定
var _id,_tableName,_type,_hourseId,_event,_index,_orgId,_functionType,_isSerachMode,
	_mainTableName,_childTableName,_modeType,_modeTypeDetailView,_bound,_dataTitle;//用于绑定
function boundCommon(id,tableName,type,lon,lat,hourseId,event,index,orgId,functionType,mainTableName,childTableName,modeTypeDetailView,dataTitle){
	_bound=1;
	_id = id;
	_tableName = tableName;
	_hourseId = hourseId;
	_event = event;
	_index = index;
	_type = type;
	_lon = lon;
	_lat = lat;
	_orgId = orgId;
	_functionType = functionType;
	_mainTableName = mainTableName;
	_childTableName = childTableName;
	_dataTitle=dataTitle;
	_modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@COMMON_BING' />";//绑定--解绑的modeType;
	if(mainTableName=="dustbin"||mainTableName=="dustbinHasVideo"){
		_modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@CITY_COMPONENTS_BING' />";
	}
	_modeTypeDetailView=modeTypeDetailView;
	var img="/resource/openLayersMap/images/bubble.png";
	if(childTableName=="监控电子眼"){
		img="/resource/openLayersMap/images/video/video_off.png";
	}
	
// 	$("#map").TqMap("addMarker",{
// 		iconUrl:PATH +img,
// 		markerW:20,
// 		markerH:25,
// 		markerId:"_commmon"
// 	});
	var layerData = getGis2DLayerDataByOrgId(orgId);
	loadFeatureByOrgId(layerData);
//	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
	if(event=="bindInBuild"){
		if($("#hourseInfo").attr("checked")!="checked"){
			$("#hourseInfo").attr("checked","checked");
			var screen = $("#map").TqMap("getScreenExtent");
			var minX = screen.minLon;
			var minY = screen.minLat;
			var maxX = screen.maxLon;
			var maxY = screen.maxLat;
			var orgId=$('#currOrgId').val();
			if(bindInWfs==true){//如果有热区
				loadAndActivateWfs();
			}else{
				$("#addHourseInfo").show();
				$("#deleteHourseInfo").show();
			}
			viewAllHourseMapInfo(orgId,minX,minY,maxX,maxY);
		}
		$("#map").TqMap("addMarker",{
			iconUrl:PATH +img,
			markerW:20,
			markerH:25,
			markerId:"_commmon"
		});
		$("#map").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
		$("#map").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAddPlace});
	}else{
		$("#map").TqMap("addMarker",{
			iconUrl:PATH +img,
			markerW:20,
			markerH:25,
			markerId:"_commmon"
		});
		$("#map").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
		$("#map").TqMap("deactivateSelectFeature",{sfname: "selectF"});//不激活自定义的Feature图层
		$("#map").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAddIssue});
		$("#map").TqMap("registerEvent",{eventName:"click",func:boundCommonOnMap});
	}
}

//在地图的任意地方进行绑定
function boundCommonOnMap(e){
	var id = _id;
	var tableName = _tableName;
	var type = _type;
	var lonlat = $("#map").TqMap("get","map").getLonLatFromPixel(e.xy);
	var lon=lonlat.lon;
	var lat=lonlat.lat;
	var hourseId = _hourseId;
	var event = _event;
	var index = _index;
	var orgId = _orgId;
	var functionType = _functionType;
	var mainTableName = _mainTableName;
	var childTableName = _childTableName;
	var dataTitle=_dataTitle;
	var modeType = _modeType;//绑定--解绑的modeType
	var modeTypeDetailView=_modeTypeDetailView;//详情查看modeType
	var layerData=getGis2DLayerDataByOrgId(orgId);
	if(layerData){
		if(checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
			clearBoundCommonInfo();
			_bound=0;
			$.ajax({
				async: false,
				url:"${path}/openLayersMap/twoDimensionMapManageManage/boundTwoDimensionMap.action",
				data:{
					"gisBoundVo.ids":id,
					"gisBoundVo.lon":lon,
					"gisBoundVo.lat":lat,
					"gisBoundVo.gisType":gisType,
					"gisBoundVo.isTransformat":TQMap.isTransformat,
					"tableName":tableName,
					"event":event,
					"type":type,
					"functionType":functionType,
					"modeType":modeType,
					"gisBoundVo.orgId":$("#currOrgId").val()
				},
				datatype: "json",
				success:function(data){
					if(data){
						$.messageBox({
							message:"绑定成功"
						});
						$("#map").TqMap("activateSelectFeature");//激活自定义Feature图层
						$("#map").TqMap("deleteFeatureByFeatureId",{featureId:layerData.id});//通过自定义的featureId删除feature
						var iconUrl = getIconUrl(data.iconUrl,index);
						var mouseoverIconUrl = getMouseoverIconUrl(data.iconUrl,index);
						var markerW=20;
						var markerH=25;
						var markerId = id+"_common";
						if(mainTableName=="publicSecurity" && _isSerachMode=="true"){
							markerId = id+type+"_common";
						}
						if(mainTableName=="dustbin"||mainTableName=="dustbinHasVideo"){
							iconUrl=getIconUrl("/resource/openLayersMap/images/redBubble",index);
							mouseoverIconUrl=getMouseoverIconUrl("/resource/openLayersMap/images/redBubble",index);
							if(childTableName=="监控电子眼"){
								iconUrl="/resource/openLayersMap/images/video/video_off.png";
								mouseoverIconUrl="/resource/openLayersMap/images/video/video_on.png";
								markerW=24;
								markerH=42;
							}
						}
						$("#map").TqMap("addMarker",{
							iconUrl:PATH+iconUrl,
							markerW:markerW,
							markerH:markerH,
							lon:lon,
							lat:lat,
							markerId:markerId,
							data:{id:id,iconUrl:iconUrl,mouseoverIconUrl:mouseoverIconUrl,title:dataTitle,tableName:tableName,detailsUrl:data.detailsUrl,type:type,lon:lon,lat:lat,functionType:functionType,
								mainTableName:mainTableName,childTableName:childTableName,modeType:modeTypeDetailView}
						});
						$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerOnClickCommon});
						_tableName=null;
						registerMarkerMouseoverAndMouseoutEvent();
						var btnHtml = $('<a href="javascript:;" class="cancelBind">[解除绑定]</a>').click(function(){
							unBoundCommon(id,tableName,type,lon,lat,hourseId,event,index,orgId,functionType,mainTableName,childTableName,modeTypeDetailView,dataTitle);
						});
						
						if(tableName=="keyPlaces" && _isSerachMode=="true"){
							$(".resultlist:visible ."+type+" .gisBtnList").html(btnHtml);	//按钮设置
							$(".resultlist:visible ."+type+" .title a").unbind("click").click(function(){
								showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
							});
							$(".resultlist:visible ."+type+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
						}else if(mainTableName=="publicSecurity" && _isSerachMode=="true"){
							var marker = id+type;
							$(".resultlist:visible ."+marker+" .gisBtnList").html(btnHtml);	//按钮设置
							$(".resultlist:visible ."+marker+" .title a").unbind("click").click(function(){
								showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
							});
							$(".resultlist:visible ."+marker+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
						}else{
							$(".resultlist:visible #"+id+" .gisBtnList").html(btnHtml);	//按钮设置
							$(".resultlist:visible #"+id+" .title a").unbind("click").click(function(){
								showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
							});
							$(".resultlist:visible #"+id+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
						}
						
					}else{
						$.messageBox({
							error:"系统错误，请与管理员联系!"
						});
					}
				}
			});
		}else{
			$.messageBox({level:'error',message:"该位置已超出了当前部门所划分地图区域的范围！"});
		}
	}else{
		var organization = getSimpleOrgById(orgId);
		if(organization) $.messageBox({level:'error',message:"数据所在部门【"+organization.orgName+"】尚未划分地图区域，请先划分地图区域！"});
	}
}

//在建筑物上进行绑定
function boundCommonOnHourse(hourseId,lon,lat){
	var id = _id;
	var tableName = _tableName;
	var type = _type;
	//var lon = mapMousemoveLon;这里不能取鼠标的坐标，应该是建筑物的坐标
	//var lat = mapMousemoveLat;
	var event = _event;
	var index = _index;
	var orgId = _orgId;
	var dataTitle=_dataTitle;
	var functionType = _functionType;
	var mainTableName = _mainTableName;
	var childTableName = _childTableName;
	var modeType = _modeType;//绑定--解绑的modeType
	var modeTypeDetailView=_modeTypeDetailView;//详情查看modeType
	if(event==null || event == "") return false;
	clearBoundCommonInfo();
	$.ajax({
		async: false,
		datatype: "json",
		method:"post",
		url:"${path}/openLayersMap/twoDimensionMapManageManage/boundTwoDimensionMap.action",
		data:{
			"gisBoundVo.ids":id+"",
	         "gisBoundVo.lon":lon,
	         "gisBoundVo.lat":lat,
	         "gisBoundVo.gisType":gisType,
	         "gisBoundVo.isTransformat":TQMap.isTransformat,
		     "gisBoundVo.buildDataId":hourseId,
		     "tableName":tableName,
	         "event":event,
	         "type":type,
	         "functionType":functionType,
	         "modeType":modeType,
	         "gisBoundVo.orgId":$("#currOrgId").val()
		},
		success:function(data){
			if(data){
				$.messageBox({
					message:"绑定成功"
				});
				var iconUrl = getIconUrl(data.iconUrl,index);
				var mouseoverIconUrl = getMouseoverIconUrl(data.iconUrl,index);
				var markerId = id+"_common";
				if(tableName=="keyPlaces" && _isSerachMode=="true"){
					markerId = type+"_common";
				}
				$("#map").TqMap("addMarker",{
					iconUrl:PATH+iconUrl,
					markerW:20,
					markerH:25,
					lon:lon,
					lat:lat,
					markerId:markerId,
					data:{id:id,iconUrl:iconUrl,mouseoverIconUrl:mouseoverIconUrl,title:data.titleName+"："+data.titleValue,tableName:tableName,detailsUrl:data.detailsUrl,type:type,lon:lon,lat:lat,functionType:functionType,
						mainTableName:mainTableName,childTableName:childTableName,modeType:modeTypeDetailView}
				});
				$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerOnClickCommon});
				registerMarkerMouseoverAndMouseoutEvent();
				var btnHtml = $('<a href="javascript:;" class="cancelBind">[解除绑定]</a>').click(function(){
					unBoundCommon(id,tableName,type,lon,lat,hourseId,event,index,orgId,functionType,mainTableName,childTableName,modeTypeDetailView,dataTitle);
				});
				
				if(tableName=="keyPlaces" && _isSerachMode=="true"){
					$(".resultlist:visible ."+type+" .gisBtnList").html(btnHtml);	//按钮设置
					$(".resultlist:visible ."+type+" .title a").unbind("click").click(function(){
						showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
					});
					$(".resultlist:visible ."+type+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
				}else if(mainTableName=="publicSecurity" && _isSerachMode=="true"){
					var marker = id+type;
					$(".resultlist:visible ."+marker+" .gisBtnList").html(btnHtml);	//按钮设置
					$(".resultlist:visible ."+marker+" .title a").unbind("click").click(function(){
						showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
					});
					$(".resultlist:visible ."+marker+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
				}else{
					$(".resultlist:visible #"+id+" .gisBtnList").html(btnHtml);	//按钮设置
					$(".resultlist:visible #"+id+" .title a").unbind("click").click(function(){
						showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
					});
					$(".resultlist:visible #"+id+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
				}
				
			}else{
				$.messageBox({
					error:"系统错误，请与管理员联系!"
				});
			}
		}
	});
}

//在wfs层要素上进行绑定
function boundCommonOnFeature(hourseId,lon,lat){
	var id = _id;
	var tableName = _tableName;
	var type = _type;
	var event = _event;
	var index = _index;
	var orgId = _orgId;
	var functionType = _functionType;
	var mainTableName = _mainTableName;
	var childTableName = _childTableName;
	var dataTitle=_dataTitle;
	var modeType = _modeType;//绑定--解绑的modeType
	var modeTypeDetailView=_modeTypeDetailView;//详情查看modeType
	if(event==null || event == "") return false;
	clearBoundCommonInfo();
	$.ajax({
		async: false,
		datatype: "json",
		method:"post",
		url:"${path}/openLayersMap/twoDimensionMapManageManage/boundTwoDimensionMap.action",
		data:{
			"gisBoundVo.ids":id+"",
	         "gisBoundVo.lon":lon,
	         "gisBoundVo.lat":lat,
	         "gisBoundVo.gisType":gisType,
	         "gisBoundVo.isTransformat":TQMap.isTransformat,
		     "gisBoundVo.buildDataId":hourseId,
		     "tableName":tableName,
	         "event":event,
	         "type":type,
	         "functionType":functionType,
	         "modeType":modeType,
	         "gisBoundVo.orgId":$("#currOrgId").val()
		},
		success:function(data){
			if(data){
				$.messageBox({
					message:"绑定成功"
				});
				var iconUrl = getIconUrl(data.iconUrl,index);
				var mouseoverIconUrl = getMouseoverIconUrl(data.iconUrl,index);
				var markerW=20;
				var markerH=25;
				var markerId = id+"_common";
				if(tableName=="keyPlaces" && _isSerachMode=="true"){
					markerId = type+"_common";
				}
				if(mainTableName=="dustbin"||mainTableName=="dustbinHasVideo"){
					iconUrl=getIconUrl("/resource/openLayersMap/images/redBubble",index);
					mouseoverIconUrl=getMouseoverIconUrl("/resource/openLayersMap/images/redBubble",index);
					if(childTableName=="监控电子眼"){
						iconUrl="/resource/openLayersMap/images/video/video_off.png";
						mouseoverIconUrl="/resource/openLayersMap/images/video/video_on.png";
						markerW=24;
						markerH=42;
					}
				}
				$("#map").TqMap("addMarker",{
					iconUrl:PATH+iconUrl,
					markerW:markerW,
					markerH:markerH,
					lon:lon,
					lat:lat,
					markerId:markerId,
					data:{id:id,iconUrl:iconUrl,mouseoverIconUrl:mouseoverIconUrl,title:dataTitle,tableName:tableName,detailsUrl:data.detailsUrl,type:type,lon:lon,lat:lat,functionType:functionType,
						mainTableName:mainTableName,childTableName:childTableName,modeType:modeTypeDetailView}
				});
				$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerOnClickCommon});
				registerMarkerMouseoverAndMouseoutEvent();
				var btnHtml = $('<a href="javascript:;" class="cancelBind">[解除绑定]</a>').click(function(){
					unBoundCommon(id,tableName,type,lon,lat,hourseId,event,index,orgId,functionType,mainTableName,childTableName,modeTypeDetailView,dataTitle);
				});
				
				if(tableName=="keyPlaces" && _isSerachMode=="true"){
					$(".resultlist:visible ."+type+" .gisBtnList").html(btnHtml);	//按钮设置
					$(".resultlist:visible ."+type+" .title a").unbind("click").click(function(){
						showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
					});
					$(".resultlist:visible ."+type+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
				}else if(mainTableName=="publicSecurity" && _isSerachMode=="true"){
					var marker = id+type;
					$(".resultlist:visible ."+marker+" .gisBtnList").html(btnHtml);	//按钮设置
					$(".resultlist:visible ."+marker+" .title a").unbind("click").click(function(){
						showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
					});
					$(".resultlist:visible ."+marker+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
				}else{
					$(".resultlist:visible #"+id+" .gisBtnList").html(btnHtml);	//按钮设置
					$(".resultlist:visible #"+id+" .title a").unbind("click").click(function(){
						showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
					});
					$(".resultlist:visible #"+id+" .resNum").empty().removeClass("resNum").addClass("resAbc").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
				}
				
			}else{
				$.messageBox({
					error:"系统错误，请与管理员联系!"
				});
			}
		}
	});
}


//解除绑定
function unBoundCommon(id,tableName,type,lon,lat,hourseId,event,index,orgId,functionType,mainTableName,childTableName,modeTypeDetailView,dataTitle){
	var modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@COMMON_BING' />";//绑定--解绑的modeType;
	if(mainTableName=="dustbin"||mainTableName=="dustbinHasVideo"){
		modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@CITY_COMPONENTS_BING' />";
	}
	_dataTitle=dataTitle;
	$.confirm({
		title:"确认解除绑定",
		message:"已经绑定，确认要解除绑定吗？",
		width: 400,
		okFunc: function() {
			$.post(
					"${path}/openLayersMap/twoDimensionMapManageManage/unBoundTwoDimensionMap.action",
				{
					"id":id,
					"tableName":tableName,
					"event":event,
					"modeType":modeType,
					"type":type,
					"functionType":functionType,
					"gisBoundVo.orgId":$("#currOrgId").val()
				},
				function(result) {
					if(result) {
						$.messageBox({message:"解除绑定成功！"});
						var btnHtml = $('<a href="javascript:;" class="addressBind">[绑定]</a>').click(function(){
							boundCommon(id,tableName,type,lon,lat,hourseId,event,index,orgId,functionType,mainTableName,childTableName,modeTypeDetailView,dataTitle);
						});
						var iconUrl=""+result.iconUrl+"/default.png";
						if(mainTableName=="dustbin"||mainTableName=="dustbinHasVideo"){
							iconUrl="/resource/openLayersMap/images/redBubble/default.png";
						}
						if(tableName=="keyPlaces" && _isSerachMode=="true"){
							$(".resultlist:visible ."+type+" .gisBtnList").html(btnHtml);	//按钮设置
							$(".resultlist:visible ."+type+" .title a").unbind("click").click(function(){
								showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
							});
								$(".resultlist:visible ."+type+" .resAbc").empty().removeClass("resAbc").addClass("resNum").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
								$("#map").TqMap("deleteMarkerByMarkerId",{markerId:type+"_common"});
						}else if(mainTableName=="publicSecurity" && _isSerachMode=="true"){
							var marker = id+type;
							$(".resultlist:visible ."+marker+" .gisBtnList").html(btnHtml);	//按钮设置
							$(".resultlist:visible ."+marker+" .title a").unbind("click").click(function(){
								showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
							});
								$(".resultlist:visible ."+marker+" .resAbc").empty().removeClass("resAbc").addClass("resNum").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
								$("#map").TqMap("deleteMarkerByMarkerId",{markerId:marker+"_common"});
						}else{
							$(".resultlist:visible #"+id+" .gisBtnList").html(btnHtml);	//按钮设置
							$(".resultlist:visible #"+id+" .title a").unbind("click").click(function(){
								showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeTypeDetailView);
							});
							$(".resultlist:visible #"+id+" .resAbc").empty().removeClass("resAbc").addClass("resNum").html("<input type='image' src='"+iconUrl+"' />");//marker样式改变
							$("#map").TqMap("deleteMarkerByMarkerId",{markerId:id+"_common"});
						}
						$("#map").TqMap("deletePopup");
						
					}else {
						$.messageBox({level:'error', message:"解除绑定失败！"});
					}
				},
				'json'
			);
		}
	});
}

//显示公用pop
function showCommonPopup(id,tableName,type,lon,lat,functionType,mainTableName,childTableName,modeType){
	$("#map").TqMap("deletePopup");//清除之前的popup
	$("#centerLon").val(lon);
	$("#centerLat").val(lat);
	var lonlat={lon:lon,lat:lat};
	var url="${path}/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?id="+id+"&tableName="+mainTableName
			+"&modeType="+modeType+"&functionType="+functionType+"&lon="+lon+"&lat="+lat+"&orgId="+$('#currOrgId').val();
	
	if(_isSerachMode=="true"){
		if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PERSON'/>"){
			url += "&childTableName="+childTableName+"&isSerachMode=true"+"&type="+tableName;
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PERSON'/>"
			|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@OTHERPERSON'/>"
				|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@CAREPERSON'/>" 
				|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PUBLICSECURITY'/>" ){
			url += "&childTableName="+tableName+"&type="+type;
		} else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@TWONEWGROUP'/>"
			|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@ENTERPRISE_PLACE'/>"
				|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@NEW_COMPANY_PLACE'/>"
				|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@NEW_UNIT_PLACE'/>"
				|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_COMPANY_PLACE'/>"
				|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@SCENICS_MANAGE'/>" ){
			url +=  "&childTableName="+"keyPlaces"+"&type="+type;		
		}else{
			url += "&childTableName="+childTableName+"&isSerachMode=true"+"&type="+type;
		}
	}else{
		if(modeType=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_DETAILVIEW'/>"
			&&$("#detailClick").val()=="true"){
			url += "&childTableName="+childTableName+"&type="+issueType;
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PERSON'/>"){
			url += "&childTableName="+childTableName+"&isSerachMode=true"+"&type="+tableName;
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@HOUSEINFO'/>"){
			url += "&childTableName="+childTableName+"&type="+tableName;	
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PLACE'/>"){
			url += "&childTableName="+mainTableName+"&type="+type;	
		}else{
			url += "&childTableName="+childTableName+"&type="+type;	
		}
	}
	if(tableName=="dustbinHasVideo"){
		url='/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?tableName=dustbin'+'&modeType=cityComponentsMapDetailViewService'+'&functionType=refineSearch'+'&isVideo=true'+'&id='+id+'&orgId='+$('#currOrgId').val()
		var dialogWidth=950;
		var dialogHeight=600;
		$("#commonDialog").createDialog({
			width:950,
			height:600,
			zIndex:9999,
			title:'视频信息',
			url:"${path}"+url,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
		return;
	}
	var popupWMap = 430;
	var popupHMap = 310;
	//网格员队伍管理改变popup窗口大小
	if(isGridTeam){
		popupWMap = 240;
		popupHMap = 100;
	}
	$("#map").TqMap("addPopup",{
		url:url,
		lon:lon,
		lat:lat,
		lonlat:lonlat,
		popupW:popupWMap,
		popupH:popupHMap,
		load:function(dom){
			$(dom.contentDiv).click(function(){
				$(".ui-multiselect-menu").hide();
			})
			$(".typelistManage .multiselect").each(function(){
				var obj=$(this);
				var selectText=$(this).attr("title");
				$(this).uiMultiselect({
					open:function(){
						if(obj.children().length<=1){
							getSelectList($(this));
						}
					},
					noneSelectedText:"请选择"+selectText
					})
			});
		}
	});
}

function getSelectList(obj){
	$.ajax({
		async: false,
		url:"${path}/gis/twoDimensionMapDetailViewCommonManage/getNearObjectList.action",
		datatype: "json",
		data:{
			"type":obj.attr("id"),
			"internalId":obj.attr("internalid")
		},
		success:function(data){
			if(obj.attr("internalid")!=null){
				for(var i=0;i<data[0].length;i++){
					obj.append('<option value="'+data[0][i].id+'">'+data[0][i].displayName+'</option>');
				}
			}else{
				for(var i=0;i<data[0].length;i++){
					obj.append('<option value="'+data[0][i].key+'">'+data[0][i].name+'</option>');
				}
			}
			
			obj.multiselect('refresh');
		}
	});
}

//公用的marker点击事件
function markerOnClickCommon(e){
	var lon=e.object.lonlat.lon;
	var lat=e.object.lonlat.lat;
	var lonlat={lon:lon,lat:lat};
	//var id = e.object.markerId.split("_")[0];
	var id = e.object.data.id;
	var tableName=e.object.data.tableName;
	var type=e.object.data.type;
	var detailsUrl=e.object.data.detailsUrl;
	var functionType=e.object.data.functionType;
	var mainTableName=e.object.data.mainTableName;
	var childTableName=e.object.data.childTableName;
	var modeType=e.object.data.modeType;
	$("#centerLon").val(lon);
	$("#centerLat").val(lat);
	var url = "${path}/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?id="+id+"&tableName="+mainTableName+"&modeType="+modeType+"&functionType="+functionType+"&lon="+lon+"&lat="+lat+"&orgId="+$('#currOrgId').val();
	if(_isSerachMode=="true"){
		if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PERSON'/>"){
			url += "&childTableName="+childTableName+"&isSerachMode=true"+"&type="+tableName;
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PERSON'/>"
			|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@OTHERPERSON'/>"
			|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@CAREPERSON'/>"
				|| mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PUBLICSECURITY'/>"){
			url += "&childTableName="+tableName+"&type="+type;
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PLACE'/>"){
			url += "&childTableName="+mainTableName+"&type="+type;	
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@TWONEWGROUP'/>"
				||mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@SCENICS_MANAGE'/>"
				||mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@NEW_COMPANY_PLACE'/>"
				||mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@NEW_UNIT_PLACE'/>"
				||mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_COMPANY_PLACE'/>"
				||mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@ENTERPRISE_PLACE'/>"){
			childTableName="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PLACE'/>";
			url += "&childTableName="+childTableName+"&type="+type;	
		}else{
			url += "&childTableName="+childTableName+"&isSerachMode=true"+"&type="+type;
		}
		
	}else{
		if(modeType=="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_DETAILVIEW'/>"
			&&$("#detailClick").val()=="true"){
		    if(""==type){
		    	type=issueType;
		    }
			url += "&childTableName="+childTableName+"&type="+type;
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@PERSON'/>"){
			url += "&childTableName="+childTableName+"&isSerachMode=true"+"&type="+tableName;
		}else if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_PLACE'/>"){
			url += "&childTableName="+mainTableName+"&type="+type;	
		}else{
			url += "&childTableName="+childTableName+"&type="+type;
		}
	}
	if(tableName=="dustbinHasVideo"){
		url='/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?tableName=dustbin'+'&modeType=cityComponentsMapDetailViewService'+'&functionType=refineSearch'+'&isVideo=true'+'&id='+id+'&orgId='+$('#currOrgId').val()
		var dialogWidth=950;
		var dialogHeight=600;
		$("#commonDialog").createDialog({
			width:800,
			height:560,
			zIndex:9999,
			title:'视频监控',
			url:"${path}"+url,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
		return;
	}
	$("#map").TqMap("unregisterEvent",{eventName:"click",func:markerOnClickCommon});
	var popupWMap = 430;
	var popupHMap = 310;
	//网格员队伍管理改变popup窗口大小
	if(isGridTeam){
		popupWMap = 240;
		popupHMap = 100;
	}
	$("#map").TqMap("addPopup",{
		url:url,
		lon:lon,
		lat:lat,
		lonlat:lonlat,
		popupW:popupWMap,
		popupH:popupHMap,
		load:function(dom){
			$(dom.contentDiv).click(function(){
				$(".ui-multiselect-menu").hide();
			})
			$(".typelistManage .multiselect").each(function(){
				var obj=$(this);
				var selectText=$(this).attr("title");
				$(this).uiMultiselect({
					open:function(){
						if(obj.children().length<=1){
							getSelectList($(this));
						}
					},
					noneSelectedText:"请选择"+selectText
					})
			});
		}
	});
}

//得到详情信息
function getDetailInformation(id,detailsUrl,issueLogId,tableName,name,dustbinType){
	var url = detailsUrl;
	var dialogWidth=950;
	var dialogHeight=600;
	var title="查看详情信息";
	if(dustbinType=="监控电子眼"){
		url='/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?tableName=dustbin'+'&modeType=cityComponentsMapDetailViewService'+'&functionType=refineSearch'+'&isVideo=true'+'&id='+'&orgId='+$('#currOrgId').val()
		dialogWidth=800;
		dialogHeight=560;
		title="视频监控";
	}
	var layoutUrl = "";
	if(tableName=="buildDatas"){
		layoutUrl="<s:property value='@com.tianque.util.ProductProperties@VIEW_BUILDDATA_LAYOUT_URL'/>&builddatas.id="+id+"&flag=false&sid=<s:property value='@com.tianque.util.ThreadVariable@getSession().sessionId'/>";
		layoutUrl += "&view=true";
		//layoutUrl=base64encode(utf16to8(layoutUrl));//加密
	}else if(tableName=="deviceInformation"){
		dialogWidth=580;
		dialogHeight=360;
	}else if(tableName=="SKYNET" || tableName=="BAYONET" || tableName=="SNAPSHOTSYSTEM"){
		dialogWidth=600;
		dialogHeight=400;
	}else if(tableName=="keyPlaces"){
		dialogWidth=850;
		dialogHeight=580;
	}
	//if(issueLogId!=''&&issueLogId!=null){
		//url+=issueLogId;
	//}else{
		url+=id;
	//}
	//url=base64encode(utf16to8(url));
	$("#commonDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		zIndex:999,
		title:title,
		//url:'${path}/openLayersMap/detailsCommon/detailsViewDlg.jsp?viewUrl='+url+"&layoutUrl="+layoutUrl,
		url:"${path}"+url+"&name="+name,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}


//根据id得到字典项	
function getPropertyDictById(eventId){
	var eventName = null;
	$.ajax({
		async: false,
		url:"${path}/sysadmin/propertyManage/getPropertyDictById.action",
		datatype: "json",
		data:{
			"propertyDomain.domainName":"gis绑定类型",
			"propertyDict.id":eventId
		},
		success:function(data){
			eventName=data.displayName;
		}
	});
	return eventName;
}
//画出网格边界
function loadFeatureByOrgId(layerData){
	if(layerData==null || layerData=="") return ;
	$("#map").TqMap("featureShow",{
		points:layerData.points,
		featureId:layerData.id,
		fillOpacity:0.1,
		strokeColor: "#FF3333",
		strokeWidth: 3
	});
}

//清除绑定时的提示信息及注册事件
function clearBoundCommonInfo(){
	$("#map").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAddIssue});
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAddPlace});
	$("#map").TqMap("deleteMarkerByMarkerId",{markerId:"_commmon"});
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
	$("#map").TqMap("unregisterEvent",{eventName:"click",func:boundCommonOnMap});
	_tableName=null;
	_event=null;
}




var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(
　　-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
　　-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
　　-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
　　52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
　　-1,　0,　1,　2,　3,  4,　5,　6,　7,　8,　9, 10, 11, 12, 13, 14,
　　15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
　　-1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
　　41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
function base64encode(str) {
　　var out, i, len;
　　var c1, c2, c3;
　　len = str.length;
　　i = 0;
　　out = "";
　　while(i < len) {
 c1 = str.charCodeAt(i++) & 0xff;
 if(i == len)
 {
　　 out += base64EncodeChars.charAt(c1 >> 2);
　　 out += base64EncodeChars.charAt((c1 & 0x3) << 4);
　　 out += "==";
　　 break;
 }
 c2 = str.charCodeAt(i++);
 if(i == len)
 {
　　 out += base64EncodeChars.charAt(c1 >> 2);
　　 out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
　　 out += base64EncodeChars.charAt((c2 & 0xF) << 2);
　　 out += "=";
　　 break;
 }
 c3 = str.charCodeAt(i++);
 out += base64EncodeChars.charAt(c1 >> 2);
 out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
 out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
 out += base64EncodeChars.charAt(c3 & 0x3F);
　　}
　　return out;
}
function base64decode(str) {
　　var c1, c2, c3, c4;
　　var i, len, out;
　　len = str.length;
　　i = 0;
　　out = "";
　　while(i < len) {
 /* c1 */
 do {
　　 c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
 } while(i < len && c1 == -1);
 if(c1 == -1)
　　 break;
 /* c2 */
 do {
　　 c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
 } while(i < len && c2 == -1);
 if(c2 == -1)
　　 break;
 out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));
 /* c3 */
 do {
　　 c3 = str.charCodeAt(i++) & 0xff;
　　 if(c3 == 61)
　return out;
　　 c3 = base64DecodeChars[c3];
 } while(i < len && c3 == -1);
 if(c3 == -1)
　　 break;
 out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
 /* c4 */
 do {
　　 c4 = str.charCodeAt(i++) & 0xff;
　　 if(c4 == 61)
　return out;
　　 c4 = base64DecodeChars[c4];
 } while(i < len && c4 == -1);
 if(c4 == -1)
　　 break;
 out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
　　}
　　return out;
}
function utf16to8(str) {
　　var out, i, len, c;
　　out = "";
　　len = str.length;
　　for(i = 0; i < len; i++) {
 c = str.charCodeAt(i);
 if ((c >= 0x0001) && (c <= 0x007F)) {
　　 out += str.charAt(i);
 } else if (c > 0x07FF) {
　　 out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
　　 out += String.fromCharCode(0x80 | ((c >>　6) & 0x3F));
　　 out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
 } else {
　　 out += String.fromCharCode(0xC0 | ((c >>　6) & 0x1F));
　　 out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
 }
　　}
　　return out;
}
function utf8to16(str) {
　　var out, i, len, c;
　　var char2, char3;
　　out = "";
　　len = str.length;
　　i = 0;
　　while(i < len) {
 c = str.charCodeAt(i++);
 switch(c >> 4)
 {
　 case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
　　 // 0xxxxxxx
　　 out += str.charAt(i-1);
　　 break;
　 case 12: case 13:
　　 // 110x xxxx　 10xx xxxx
　　 char2 = str.charCodeAt(i++);
　　 out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
　　 break;
　 case 14:
　　 // 1110 xxxx　10xx xxxx　10xx xxxx
　　 char2 = str.charCodeAt(i++);
　　 char3 = str.charCodeAt(i++);
　　 out += String.fromCharCode(((c & 0x0F) << 12) |
　　　　((char2 & 0x3F) << 6) |
　　　　((char3 & 0x3F) << 0));
　　 break;
 }
　　}
　　return out;
}
</script>