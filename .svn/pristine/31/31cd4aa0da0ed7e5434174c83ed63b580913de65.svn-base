<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />

<div id="mapIssueView" style="height:490px;"></div>
<div class="" style="position:absolute;top:30px;right:30px;z-index:9999;" title="选中事件的周边搜素">
	<a id="mapIssueViewSearch" href="javascript:void(0)">
         <img src="${path }/resource/openLayersMap/images/surroundingSearch.png"></img>
    </a>
</div>
<div class="" style="position:absolute;top:70px;right:30px;z-index:9999;">
	<a id="mapIssueViewClear" href="javascript:void(0)">
         <img src="${path }/resource/openLayersMap/images/clearTraces.png"></img>
    </a>
</div>
<div id="dealIssueLogsDialog"></div>

<script type="text/javascript">
var modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@COMMON_BING' />";//绑定--解绑的modeType;
var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
var userOrgId= '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>';

var bindInfo={isBind:false,data:null};
var deltLen = 2.518896585495241;
var elementsCheck="";
var rangeCheck="";
var peripheryType="place";

var centerLon;
var centerLat;

$(function() {
	initMap();
	loadIssueMarker();
	initBindOrCancleBind();
	function initMap() {
		$("#mapIssueView").css("height",$("#mapIssueView").parent().height());
		$("#mapIssueView").empty();
		$("#mapIssueView").TqMap({
			isShowM2D: true,						            //是否加载二维地图
			isShowWFS: true						            //是否加载热区
		});
		//$("#mapIssueView").TqMap("registerEvent",{eventName:"moveend",func:mapMoveEndFun});//注册地图拖动结束事件
		var layerData = getGis2DLayerDataByOrgId(userOrgId);
		if(layerData!=null){
			$("#mapIssueView").TqMap("setCenter",{lon:layerData.lon,lat:layerData.lat,zoom:layerData.zoom});
		}
		setTimeout(function(){
			$("#mapIssueView").css("height",$("#mapIssueView").parent().height());
			$("#mapIssueView").TqMap("get","map").updateSize();//修改地图大小
		},1000);
	}
	$("#mapIssueView").TqMap("setCenter",{lon:layerData.lon,lat:layerData.lat,zoom:$("#mapIssueView").TqMap("get","map").zoom});
	$("#mapIssueView").TqMap( "createDragPanFeature",{clickFeature:wfsFeatureClickFunc})

	$("#mapIssueViewClear").click(function(){
		$("#mapIssueView").TqMap("removeAllFeatures");
		$("#mapIssueView").TqMap("clearMarkersLikeMarkerId","search");
	});
	$("#mapIssueViewSearch").click(function(){
		var $a = getSelectedIssue();
		var issueId = $a.attr("issueId");
		var lon = $a.attr("centerLon");
		var lat = $a.attr("centerLat");
		if(lon==null || lon==""){
			$.messageBox({level:'error', message:"选中事件尚未绑定，请先绑定，再进行周边搜素！"});
			return;
		}
		var tableName="issues";
		var type="";
		var detailsUrl="/issues/issueManage/dispatch.action?mode=view&keyId=";
		var functionType="refineSearch";
		var mainTableName="issues";
		var childTableName="";
		var modeType="issueMapDetailViewService";
		var url = "${path}/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?id="+issueId+"&tableName="+mainTableName+"&modeType="+modeType+"&functionType="+functionType+"&lon="+lon+"&lat="+lat+"&childTableName="+childTableName+"&isSerachMode=true"+"&type="+type+"&isIsuue=true";
		
		$("#viewDetailsIssueInformation").createDialog({
			width: 430,
			height: 330,
			title:'周边搜索',
			url:url,
			buttons: {
			   "搜索" : function(){
				   searchPeriphery(lon,lat);
				},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		}); 
	});

	function searchPeriphery(lon,lat){
		var elements=document.all.elements;
		var range=document.all.range;
		var queryStr = $("#searchValue").val();
		$(".typelistManage:visible .multiselect").each(function(){
			if($(this).val()){
				elementsCheck=$(this).val().join(",")+",";
			}
		})
		for(i=0;i<range.length;i++){
	        if(range[i].checked==true){
	        	rangeCheck = range[i].value;
	        }
	    }
		if(elementsCheck=="" || rangeCheck==""){
			$.messageBox({level:'error', message:"请选择要素和范围！"});
			return;
		}
		rangeCheck = (Math.floor((eval(rangeCheck)/deltLen))+1)+"";//25D距离与实际距离的换算
		$("#mapIssueView").TqMap("removeAllFeatures");
		$("#mapIssueView").TqMap("drawCircle",{lon:lon,lat:lat,redius:rangeCheck});
		viewSurroundingSearchInformation(lon,lat);
	}

	function viewSurroundingSearchInformation(lon,lat){
		$("#mapIssueView").TqMap("clearMarkersLikeMarkerId","search");
		var modeType;
		var functionType="refineSearch";
		if(peripheryType=="place"){
			modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_CIRCUM_SEARCH'/>";
		}else if(peripheryType=="person"){
			modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_CIRCUM_SEARCH'/>";
		}else if(peripheryType=="dustbin"){
			modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@CITY_COMPONENTS_CIRCUM_SEARCH'/>";
		}else{
			if(elementsCheck=="deviceinformation" || elementsCheck=="deviceinformation"+","){
				modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@GPS_CIRCUM_SEARCH'/>";
			}
		}
		showResult(modeType,functionType,lon,lat);
	}

	function showResult(modeType,functionType,lon,lat){
		var queryStr = $("#searchValue").val();
		var dfoption = {
				url:'${path}/gis/twoDimensionMapCircumSearchCommonManage/findTwoDimensionMapCircumInfoByElementsAndRangeAndCenterLonLat.action',
				dataType:"json",
				async:false,
				data:{
					"circumInfoVo.elements":elementsCheck,
	 				"circumInfoVo.range":rangeCheck,
	 				"circumInfoVo.lon":lon,
	 				"circumInfoVo.lat":lat,
	 				"circumInfoVo.functionType":functionType,
	 				"circumInfoVo.queryStr":queryStr,
	 				"modeType":modeType,
	 				"mode":$("#mode").val(),
	 				"page":1,
					"rows":1000,
	 				"type":""
				}
			}
		$.ajax($.extend(true,dfoption,{
				data:{ "rows":1000000 },
				success:function(data){
					peripheryMarker(data);
				}
		}));
	}

	function peripheryMarker(data){
		$("#viewDetailsIssueInformation").dialog("close");
		if(data!=null && data.rows!=null){
			var data=data.rows;
			var name;
			for(var i=0;i<data.length;i++){
				var imgUrl="../resource/openLayersMap/images/defaultMarker.gif";
				var mouseoverImgUrl="../resource/openLayersMap/images/defaultMarker-2.gif";
				var markerW=28;
				var markerH=31;
				
				if(data[i].typeName=="监控电子眼"){
					imgUrl="../resource/openLayersMap/images/video/video_off.png";
					mouseoverImgUrl="../resource/openLayersMap/images/video/video_on.png";
					markerW=24;
					markerH=42;
				}
				var parameter = {
						iconUrl:PATH+imgUrl,
						markerW:markerW,
						markerH:markerH,
						lon:data[i].lon,
						lat:data[i].lat,
						markerId:data[i].id+'_search',
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:data[i].typeName+"："+data[i].name,viewUrl:data[i].viewUrl,type:data[i].type}
					}
				if(peripheryType=="person"){
					$.extend(parameter,{
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:data[i].typeName+"："+data[i].name,viewUrl:data[i].viewUrl,type:data[i].type,idCardNo:data[i].idCardNo}
					});
				}else if(peripheryType=="dustbin"){
					$.extend(parameter,{
						data:{typeName:data[i].typeName,imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:data[i].typeName+"："+data[i].dustbinCode,viewUrl:data[i].viewUrl,type:data[i].type}
					});
				}
				
				$("#mapIssueView").TqMap("addMarker",parameter);
				//注册marker点击事件
				$("#mapIssueView").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClickbyType});
				registerMarkerMouseoverAndOutEvent();
			}
		}
	}

	function markerClickbyType(e){
		var typeName=e.object.data.typeName;
		var id=e.object.markerId.split("_")[0];
		var viewUrl=e.object.data.viewUrl;
		var url=viewUrl+id;
		if(typeName!="监控电子眼"){
			$("#viewDialog").createDialog({
				width:920,
				height:550,
				title:"查看信息",
				url:url,
				zIndex:9999,
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close"); 
				   }
				}
			});
		}else{
			url='/gis/twoDimensionMapDetailViewCommonManage/getDetailViewPopupInfoByIdAndType.action?tableName=dustbin'+'&modeType=cityComponentsMapDetailViewService'+'&functionType=refineSearch'+'&isVideo=true'+'&id='+id
			$("#viewDialog").createDialog({
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
		}
	}
})

function issueLiClickEffectInMap(that){//事件列表单击事件
	var map = $("#mapIssueView").TqMap("get","map");
	if(map==null) return;
	var lon = $(that).attr("centerLon");
	var lat = $(that).attr("centerLat");
	$("#mapIssueView").TqMap("clearMarkersLikeMarkerId","issueSelect");
	map.setCenter(new OpenLayers.LonLat(lon,lat), map.zoom);
	if(bindInfo.isBind==true && bindInfo.data!=null){
		var bindIssueId = $(bindInfo.data).attr("issueId");
		var issueId = $(that).attr("issueId")
		if(bindIssueId != issueId){
			clearBoundCommonInfo();
		}
	}else{
		addAndBinMarker(that,lon,lat,"now","issueSelect");
	}
}

function loadIssueMarker(){
	var map = $("#mapIssueView").TqMap("get","map");
	if(map==null) return;
	$("#mapIssueView").TqMap("clearMarkersLikeMarkerId","_common");
	$("a.cancelBind").each(function(index){
		var lon = $(this).attr("centerLon");
		var lat = $(this).attr("centerLat");
		addAndBinMarker(this,lon,lat);
		if(lon!=null && lon!=""){
			centerLon = lon;
			centerLat = lat;
		}
	})
}

function markerOnClickFunc(e){
	var issueId = e.object.data.issueId;
	var issueStepId = e.object.data.issueStepId;
	if(id==null) return;
	var dialogW=700;
	var dialogH=580;
	$.ajax({
		async:false,
		url:"${path}/issues/issueManage/viewSubDetail.action?mode=canDo&keyId="+issueStepId,
		success:function(data){
			var REPORTTO = "<s:property value='@com.tianque.issue.state.IssueOperate@REPORT_TO' escape='false'/>";
			var CONCEPT = "<s:property value='@com.tianque.issue.state.IssueOperate@CONCEPT' escape='false'/>";
			var READ = "<s:property value='@com.tianque.issue.state.IssueOperate@READ' escape='false'/>";
			if(inIssueCanDoArray(REPORTTO,data) || inIssueCanDoArray(CONCEPT,data) || inIssueCanDoArray(READ,data)){
				dialogW = 400;
				dialogH = 300;
			}
			$("#issueDialog").createDialog({
				width:dialogW,
				height:dialogH,
				title:'事件',
				url:'${path}/issue/issueManage/deals/dealIssueLogsDlg.jsp?issueStepId='+issueStepId+"&issueId="+issueId+"&width="+(dialogW-20),
				buttons: {
					"确定" : function(event){
						$("#issueDealForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
	});
	function inIssueCanDoArray(value,array){
		if(value==null || array==null) return;
		for(var i=0;i<array.length;i++){
			if(value==array[i].desc){
				return true;
			}
		}
	}
}
function initBindOrCancleBind(){
	$("a.cancelBind").click(function(){//解绑
		unBoundCommon(this);
	})
	$("a.addressBind").click(function(){//绑定
		bindInfo={isBind:true,data:this};
		boundCommon(this);
	})
}
//解除绑定
function unBoundCommon(that){
	var id = $(that).attr("issueId");
	$.confirm({
		title:"确认解除绑定",
		message:"已经绑定，确认要解除绑定吗？",
		width: 400,
		okFunc: function() {
			$.post(
				"${path}/openLayersMap/twoDimensionMapManageManage/unBoundTwoDimensionMap.action",
				{
					"id":id,
					"tableName":"issues",
					"functionType":functionType,
					"modeType":modeType,
					"gisBoundVo.orgId":$("#currOrgId").val()
				},
				function(result) {
					if(result) {
						$.messageBox({message:"解除绑定成功！"});
						$(that).attr("centerLon","").attr("centerLat","");
						$(that).removeClass("cancelBind").addClass("addressBind").unbind("click").click(function(){
							bindInfo={isBind:true,data:that};
							boundCommon(that);
						});
						$("#mapIssueView").TqMap("deletePopup");
						$("#mapIssueView").TqMap("deleteMarkerByMarkerId",{markerId:id+"_common"});
						$("#mapIssueView").TqMap("clearMarkersLikeMarkerId","issueSelect");
					}else {
						$.messageBox({level:'error', message:"解除绑定失败！"});
					}
				},
				'json'
			);
		}
	});
}
function boundCommon(that){
	var map = $("#mapIssueView").TqMap("get","map");
	if(map==null) return;
	var occurOrgId = $(that).attr("occurOrgId");
	$("#mapIssueView").TqMap("addMarker",{
		iconUrl:PATH +"/resource/openLayersMap/images/bubble.png",
		markerW:20,
		markerH:25,
		markerId:"tip_common"
	});
	var layerData = getGis2DLayerDataByOrgId(occurOrgId);
	map.setCenter(new OpenLayers.LonLat(layerData.lon,layerData.lat), layerData.zoom);
	loadFeatureByOrgId(layerData);
	$("#mapIssueView").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
 	$("#mapIssueView").TqMap("registerEvent",{eventName:"mousemove",func:mouseTipFunAdd});
 	$("#mapIssueView").TqMap("registerEvent",{eventName:"click",func:boundCommonOnMap});
}
//在地图的任意地方进行绑定
function boundCommonOnMap(e){
	var imap = $("#mapIssueView").TqMap("get","map");
	var that = getSelectedIssue();
	var occurOrgId = $(that).attr("occurOrgId");
	var lon=imap.getLonLatFromPixel(e.xy).lon;
	var lat=imap.getLonLatFromPixel(e.xy).lat;
	var layerData=getGis2DLayerDataByOrgId(occurOrgId);
	if(layerData!=null && layerData!=""){
		if(checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
			submitBoundTwoDimensionMap(lon,lat,layerData.id);
		}else{
			$.messageBox({
				level:'error',message:"该位置已超出了当前部门所划分地图区域的范围！",
			});
		}
	}
}
//建筑物热区的单击事件及绑定
function wfsFeatureClickFunc(e){
	if(e==null || e=="" || bindInfo.isBind!=true) return;
	var lonlat = e.geometry.bounds.getCenterLonLat();
	var lon=lonlat.lon;
	var lat=lonlat.lat;
	var zoom=$("#mapIssueView").TqMap("get","map").zoom;
	var orgId = $(bindInfo.data).attr("occurOrgId");
	var layerData=getGis2DLayerDataByOrgId(orgId);
	if(!checkPointInPolygonPoints({x:lon,y:lat},layerData.points)){
		$.messageBox({message:"请在您所画的辖区内绑定事件",level:"error"});
	}else{
		submitBoundTwoDimensionMap(lon,lat,layerData.id);
	}
}

function submitBoundTwoDimensionMap(lon,lat,layerDataId){
	clearBoundCommonInfo();
	var that = getSelectedIssue();
	var issueId = $(that).attr("issueId");
	$.ajax({
		async: false,
		url:"${path}/openLayersMap/twoDimensionMapManageManage/boundTwoDimensionMap.action",
		data:{
			"gisBoundVo.ids":issueId,
			"gisBoundVo.lon":lon,
			"gisBoundVo.lat":lat,
			"gisBoundVo.gisType":gisType,
	        "gisBoundVo.isTransformat":TQMap.isTransformat,
			"tableName":"issues",
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
				$("#mapIssueView").TqMap("deleteFeatureByFeatureId",{featureId:layerDataId});//通过自定义的featureId删除feature
				addAndBinMarker(that,lon,lat);
				$(that).attr("centerLon",lon).attr("centerLat",lat);
				$(that).removeClass("addressBind").addClass("cancelBind").unbind("click").click(function(){
					unBoundCommon(that);
				});
				bindInfo={isBind:false,data:null};
			}else{
				$.messageBox({
					error:"系统错误，请与管理员联系!"
				});
			}
		}
	});
}

function getSelectedIssue(){
	var selectIssue = null;
	$("li.issue").each(function(){
		var isChecked = $(this).find("input:checkbox").attr("checked");
		if(isChecked=="checked" || isChecked=="true" || isChecked==true){
			selectIssue =  $(this).find(".showTime>a");
		}
	})
	return selectIssue;
}

function getIssueIconUrl(urgent,supervisionState,cur){
	if(cur == null) cur="";
	if(cur != null && cur!="") cur = "_"+cur;
	var iconBasePath = "/resource/openLayersMap/images/issue";
	var icon = "issue";
	if(supervisionState==1){
		icon = "normalIssue";
	}else if(supervisionState==100){
		icon = "yellowCardIssue";
	}else if(supervisionState==200){
		icon = "redCardIssue";
	}else if(urgent==1){
		icon = "emergency";
	}
	return 	iconBasePath+"/"+icon+cur+".png";
}
function mapMoveEndFun(e){//注册地图拖动结束事件，主要解决marker 图标被wfs图层覆盖的问题
	var prevsZoom = $("#mapIssueView").data("prevsZoom");
	var zoom = $("#mapIssueView").TqMap("get","map").zoom;
	if(prevsZoom==null || prevsZoom!=zoom){
		clearTimeout(timehandel);
		var timehandel = setTimeout(function(){
			loadIssueMarker();
			if(bindInfo.isBind==true){
				$("#mapIssueView").TqMap("addMarker",{
					iconUrl:PATH +"/resource/openLayersMap/images/bubble.png",
					markerW:20,
					markerH:25,
					markerId:"tip_common"
				});
			}
		},1500)
		$("#mapIssueView").data("prevsZoom",zoom);
	}
}
//注册marker事件
function registerMarkerMouseoverAndOutEvent(){
	$("#mapIssueView").TqMap("registerEvent",{objectName:"marker",eventName:"mouseover",func:function(e){
		e.object.setUrl(e.object.data.mouseoverIconUrl);
		$(e.object.events.element).attr("title",e.object.data.title);
		//e.object.inflate(1.1);
	}});

	$("#mapIssueView").TqMap("registerEvent",{objectName:"marker",eventName:"mouseout",func:function(e){
		e.object.setUrl(e.object.data.iconUrl);
		//e.object.inflate(1/1.1);
	}});
}
//在地图上新增和绑定Marker的click事件
function addAndBinMarker(that,lon,lat,icon,markerId){
	if(lon==null || lon=="") return;
	if(markerId==null || markerId=="") markerId="common";
	var title = $(that).attr("subject");
	var urgent = $(that).attr("urgent");
	var supervisionState = $(that).attr("supervisionState");
	var issueId = $(that).attr("issueId");
	var issueStepId = $(that).attr("issueStepId");
	var iconUrl = getIssueIconUrl(urgent,supervisionState,icon);
	var mouseoverIconUrl = getIssueIconUrl(urgent,supervisionState,"cur");
	$("#mapIssueView").TqMap("addMarker",{
		iconUrl:"${path}"+iconUrl,
		markerW:50,
		markerH:50,
		lon:lon,
		lat:lat,
		markerId:id+"_"+markerId,
		data:{issueId:issueId,issueStepId:issueStepId,iconUrl:iconUrl,mouseoverIconUrl:mouseoverIconUrl,title:title,detailsUrl:"",lon:lon,lat:lat}
	});
	$("#mapIssueView").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerOnClickFunc});
	registerMarkerMouseoverAndOutEvent();
}

//清除提示信息及注册事件
function clearBoundCommonInfo(){
	$("#mapIssueView").TqMap("deleteMouseTip");//删除鼠标跟随的提示信息
	$("#mapIssueView").TqMap("deleteMarkerByMarkerId",{markerId:"tip_common"});
	$("#mapIssueView").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
	$("#mapIssueView").TqMap("unregisterEvent",{eventName:"mousemove",func:mouseTipFunAdd});
	$("#mapIssueView").TqMap("unregisterEvent",{eventName:"click",func:boundCommonOnMap});
}
//画出网格边界
function loadFeatureByOrgId(layerData){
	if(layerData==null || layerData=="") return ;
	$("#mapIssueView").TqMap("featureShow",{
		points:layerData.points,
		featureId:layerData.id,
		fillOpacity:0.1,
		strokeColor: "#FF3333",
		strokeWidth: 3
	});
}
//marker鼠标跟随事件
function markerMousemove(e){
	$("#mapIssueView").TqMap("moveTo",{objectName:"marker",e:e});
}
function mouseTipFunAdd(){
	$("#mapIssueView").TqMap("addMouseTip",{evt:event,msg:"请点击地图，或是地图建筑物进行绑定"});
}
</script>