<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="searchResult" style="overflow: hidden;">
	<div class="resultNum"><span class="plaint" title="清空"><a id="emptyHouseMapAndList" href='javascript:void(0)' >【清空】</a></span>共有<span id="houseListTotal">0</span>条搜索记录</div>
    <div class="resultCon">
    	<div id="houseList"></div>
    	<div id="loadingValue"><img src='../resource/images/loading.gif' alt='加载中...'  style='vertical-align:middle;margin-left:15px;height:25px;' />信息加载中，请等待...</div>
    </div>
</div>
<div id="houseListPager" class="pagination"></div>
<input type="hidden" id="moduleType" value="place" />

<script type="text/javascript">
var tempPageMarkerArray=new Array();
var tempPageIndex=0
var hourseListPrevsPageInfo =null;//住房列表上一页数据信息
$(function(){
	showHousePropertyLayerStaticInfo();
	$("#emptyHouseMapAndList").click(function(){
		IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
		if(!"<s:property value='#parameters.flag'/>"){//判断是否辖区分布统计 true则为是的情况
			$("#updownlist").val(1);//将搜索下拉选项选中为人员信息
			$("#queryString").val("请输入(姓名/身份证号码/地址)");
			$("#housePropertyLayer").parent().removeClass("currentPosClick");//去除住房图层选中样式
		}
		$("#map").TqMap("deletePopup");
		clearMarkersByMarkerId_objectName("houseProperty");
		$("#houseListTotal").html(0);
		$("#emptyHouseMapAndList").hide();
		$("#houseList").nextAll().remove();
		$("#houseListPager").empty();
	});
});

function showHousePropertyLayerStaticInfo(){
	var screen = $("#map").TqMap("getScreenExtent");
	var minX = screen.minLon;
	var minY = screen.minLat;
	var maxX = screen.maxLon;
	var maxY = screen.maxLat;
	var orgId=$('#currOrgId').val();
	var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_SEARCH'/>";
	clearMapAllMarkerAndPopup();
	var str= $("#queryString").val();
	if(queryStringValueIsNull()){
		str='';
	}
	
	if("<s:property value='#parameters.flag'/>"){//判断是否右边列表点住房 true则为是的情况
		var housePropertyType="<s:property value='#parameters.type'/>";
		boundHouseList(orgId,modeType,housePropertyType);
		return;
	}
	showDetailsList();
	if($("#showPersonLayer li.currentPosClick label").first().text()==$("#housePropertyLayer").text()){
		if(isTownDownOrganization()){
			$("#map").TqMap("clearMarkers");
			viewTownAboveHousePropertyInformation(minX,minY,maxX,maxY,orgId);//乡镇级别以上住房标记层的数据信息展现
		}else{
			viewTownUnderHousePropertyInInformation(minX,minY,maxX,maxY,orgId);//乡镇级别以下住房标记层的数据信息展现
		}
	}else{
		$("#loadingValue").html('');
	}
}

function boundHouseList(orgId,modeType,housePropertyType){
	$.ajax({
		url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action',
		async:false,
		data:{
			"organization.id":orgId,
			"modeType":modeType,
			"typeName":housePropertyType,
			"page":1,
			"rows":10000000,
			"sidx":"centerlon",
	        "sord":"asc"
		},
		success:function(data){
			drawMarker(data);
		}
	});
	$("#houseList").GisList({
		url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action',
		data:{
			"organization.id":orgId,
			"modeType":modeType,
			"typeName":housePropertyType,
			"page":1,
			"rows":10,
			"sidx":"centerlon",
	        "sord":"asc"
		},
		ajaxLoad:hourseListNextPage,//翻页方法
		rowFormatter:function(row,rowNumber){
			return showRigthData(row,rowNumber);
		}
	});
}

//未绑定住房
function unBoundHouseList(){
	window._housePropertyIndex=0;
	$("#houseList").GisList({
		url:'${path}/gis/commonOpenLayersManage/findUnBoundHousePropertyByOrgCode.action',
		data:{
			"orgId":$("#currOrgId").val(),
			"page":1,
			"rows":10,
			"sidx":"centerlon",
	        "sord":"asc"
		},
		rowFormatter:function(row,rowNumber){
			return showRigthData(row,rowNumber);
		}
	});
}


function viewTownAboveHousePropertyInformation(minX,minY,maxX,maxY,orgId){
	$.ajax({
		url:'${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName.action',
		async:false,
		data:{
			"organization.id":orgId,
			"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_STATISTIC'/>",
			"screenCoordinateVo.minLon":minX,
			"screenCoordinateVo.maxLon":maxX,
			"screenCoordinateVo.minLat":minY,
			"screenCoordinateVo.maxLat":maxY
		},
		success:function(data){
			var str="<tr class='keyPopulation'><td class='msgL'><span class='text'>所属辖区</td><td class='msgL'><span class='text'>已绑数</td>";
			for(var i=0;i<data.length;i++){
				str+="<tr class='houseProperty' onclick='detailedListingClick("+data[i].organization.id+")'><td class='msgL'><span class='text'>"+data[i].organization.orgName+"</span></td><td class='dataPoint'><span class='num'>"+data[i].boundMapNum+"</span></td></tr>";
				if(data[i].lon!=null && data[i].lat!=null){
					 var pop=$("#map").TqMap("addPopupText",{
						 lon:data[i].lon,
						 lat:data[i].lat,
						 popupW:30,
						 popupH:12,
						 popupText:""+data[i].boundMapNum+"",
						 backgroundColor:"#FF0000",
						 popupTextId:i+1,
						 data:data[i].organization.id
					});
					 $(pop.contentDiv).attr("title",data[i].organization.orgName+" 住房数："+data[i].boundMapNum);
					 $("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:popClick});
				 }
			}
			$("#tabList").html("<div class='new_personal_tableCon'><table class='new_personal_table'>"+str+"</table></div>");
			$(".new_personal_table tr").hover(function(){
				$(this).addClass("tableCur").siblings().removeClass("tableCur");
			},function(){
				$(this).removeClass("tableCur");
			});
		}
	});
}


function viewTownUnderHousePropertyInInformation(minX,minY,maxX,maxY,orgId){
	$.ajax({
		url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action',
		async:false,
		data:{
			"screenCoordinateVo.minLon":minX,
			"screenCoordinateVo.maxLon":maxX,
			"screenCoordinateVo.minLat":minY,
			"screenCoordinateVo.maxLat":maxY,
			"organization.id":orgId,
			"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_SEARCH'/>",
			"page":1,
			"rows":1000000,
			"sidx":"centerlon",
	        "sord":"asc"
		},
		success:function(data){
			drawMarker(data);
		}
	});
	$("#houseList").GisList({
		url:'${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action',
		data:{
			"screenCoordinateVo.minLon":minX,
			"screenCoordinateVo.maxLon":maxX,
			"screenCoordinateVo.minLat":minY,
			"screenCoordinateVo.maxLat":maxY,
			"organization.id":orgId,
			"modeType":"<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@HOUSEPROPERTY_SEARCH'/>",
			"page":1,
			"rows":10,
			"sidx":"centerlon",
	        "sord":"asc"
		},
		ajaxLoad:hourseListNextPage,//翻页方法
		rowFormatter:function(row,rowNumber){
			return showRigthData(row,rowNumber);
		}
	});
}

function drawMarker(data){
	if(data.records>0){
		var rows=data.rows;
		for(var i =0 ;i<rows.length;i++){
			if(rows[i].lat!=null&&rows[i].lon!=null){
				var imgUrl="../resource/openLayersMap/images/bubble.png";
				var mouseoverImgUrl="../resource/openLayersMap/images/bubble_GLUE.fw.png";
				$("#map").TqMap("addMarker",{//画房屋marker
					iconUrl:PATH + imgUrl,
					markerW:15,
					markerH:20,
					lon:rows[i].lon,
					lat:rows[i].lat,
					markerId:rows[i].id+"_houseProperty",
					data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"房主："+rows[i].houseName}
				});
				$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClick});
				registerMarkerMouseoverAndMouseoutEvent();
		 	}
		}
	}
}

function showRigthData(row,rowNumber){
	var moduleType = $("#moduleType").val();
	var thisRow = $("<li id='"+row.id+"'/>");
	var i=rowNumber;
	if(row!=null) {
		var num = "";
		var name = "";
		var imgUrl ="";
		if(null != row.lon&&null != row.lat) {
			num = $("<div />").html("<span>"+getBubble(rowNumber++)+"</span>").addClass("resAbc");
			name = $("<div class='name' />").html("<a href=\"javascript:void(0);\" onclick=\"javascript:showHousePropertyPopup("+row.lon+","+row.lat+","+null+",'person',"+row.id+",\'"+moduleType+"\')\">房主姓名："+row.houseName+"</a>");
		}else{
			num = $("<div />").addClass("resNum");
			name = $("<div class='name'/>").html("<a>"+"房主姓名："+row.houseName+"</a>");
		}
		var address = $("<div />").html("住&nbsp;&nbsp;&nbsp;址："+row.address);
		var btnList = $("<div />").addClass("btnList").append(address);
		thisRow.append(num).append(name).append(address).append(btnList);
	}
	return thisRow;
}
function hourseListNextPage(data){
	if(data.records>0){
		var rows=data.rows;
		if(hourseListPrevsPageInfo!=null && hourseListPrevsPageInfo!="undefined" && hourseListPrevsPageInfo!=""){
			for(var j = 0; j < hourseListPrevsPageInfo.length; j++){
				$("#map").TqMap("deleteMarkerByMarkerId",{markerId:hourseListPrevsPageInfo[j].id+"_houseProperty"});
				if(hourseListPrevsPageInfo[j].lon!=null&&hourseListPrevsPageInfo[j].lat!=null){
					var imgUrl="../resource/openLayersMap/images/bubble.png";
					var mouseoverImgUrl="../resource/openLayersMap/images/bubble_GLUE.fw.png";

					$("#map").TqMap("addMarker",{//画房屋marker
						iconUrl:PATH + imgUrl,
						markerW:15,
						markerH:20,
						lon:hourseListPrevsPageInfo[j].lon,
						lat:hourseListPrevsPageInfo[j].lat,
						markerId:hourseListPrevsPageInfo[j].id+"_houseProperty",
						data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"房主："+hourseListPrevsPageInfo[j].houseName}
					});
					$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClick});
					registerMarkerMouseoverAndMouseoutEvent();
				}
				
			}
	 	}
		hourseListPrevsPageInfo=rows;
		for(var i =0 ;i<rows.length;i++){
			$("#map").TqMap("deleteMarkerByMarkerId",{markerId:rows[i].id+"_houseProperty"});
			if(rows[i].lon!=null&&rows[i].lat!=null){
				var imgUrl = getBubbleUrl(i);
				var mouseoverImgUrl=getMouseoverBubbleUrl(i);
				$("#map").TqMap("addMarker",{//画房屋marker
					iconUrl:PATH + imgUrl,
					markerW:20,
					markerH:25,
					lon:rows[i].lon,
					lat:rows[i].lat,
					markerId:rows[i].id+"_houseProperty",
					data:{imgUrl:imgUrl,mouseoverImgUrl:mouseoverImgUrl,title:"房主："+rows[i].houseName}
				});
				$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClick});
				registerMarkerMouseoverAndMouseoutEvent();
			}
		}
	}
}

function addHousePropertyMarker(imgUrl,lon,lat,markerId){
	$("#map").TqMap("addMarker",{//画房屋marker
		iconUrl:PATH + imgUrl,
		markerW:26,
		markerH:30,
		lon:lon,
		lat:lat,
		markerId:markerId
	});
	$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClick});
}

function clearTempMarkerAndPopupText(){
	$("#map").TqMap("deleteAllPopupText");
	for(var i=0;i<tempPageMarkerArray.length;i++){
		$("#map").TqMap("deleteMarkerByMarkerId",{markerId:tempPageMarkerArray[i]});
	}
}

//绑定
function bindHouseInfo(id,index) {
	LOCATIONVO_type="houseInfo";
	LOCATIONVO_id=id;
	LOCATIONVO_index=index;
	$("#map").TqMap("addMarker",{
		iconUrl:PATH +"/resource/edushiGis/images/bubble.png",
		markerW:26,
		markerH:30,
		markerId:id
	});
	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:markerMousemove});
    $("#map").TqMap("addPopupText",{popupW:200,popupH:20,popupText:"请选择地图上的热区进行住房绑定"});
	$("#map").TqMap("registerEvent",{eventName:"mousemove",func:popupTextMousemove});

}
//解除住房绑定
function unbindHouseInfo(id,index) {
	var moduleType = '<s:property value="@com.tianque.util.ModuleMap@PLACE_LETTING_HOUSE.getModuleType()"/>';
	$.confirm({
		title:"确认解除绑定",
		message:"该住房已经绑定，确认要解除绑定吗？",
		width: 400,
		okFunc: function() {
			$.post(
				"${path}/gis/commonOpenLayersManage/updateHousePropertyFeatureId.action",
				{
					"houseProperty.id":id
				},
				function(result) {
					if(null != result) {
						$.messageBox({message:"住房解除绑定成功！"});
						var btnHtml = "<a href='javascript:;'class='addressBind' onclick='bindHouseInfo("+result.id+","+index+");'>[绑定]</a>";
						$(".resultlist:visible #"+id+" .btnList").html(btnHtml);
						$(".resultlist:visible #"+id+" .name a").removeAttr("onclick");
						$(".resultlist:visible #"+id+" .resAbc").empty().removeClass("resAbc").addClass("resNum");
						var featureId=result.id+"_houseProperty";
						$("#map").TqMap("deleteMarkerByMarkerId",{markerId:featureId});
					}else {
						$.messageBox({
							message:"住房解除绑定失败！",
							level:'error'
						});
					}
				},
				'json'
			);
		}
	});
}

//绑定住房
function boundHouseInfo(id) {
	var centerLon=mapMousemoveLon;
	var centerLat=mapMousemoveLat; 
	var featureId;
	if(id!=null){
		featureId="0"+id;
	}else{
		featureId=wfsFeature.data.OID;
	}
	$("#map").TqMap("deletePopupText");
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:popupTextMousemove});
	$("#map").TqMap("deleteMarkerByMarkerId",{markerId:LOCATIONVO_id});
	$("#map").TqMap("unregisterEvent",{eventName:"mousemove",func:markerMousemove});
	$.ajax({
		async: false,
		url: PATH+"/gis/commonOpenLayersManage/updateHousePropertyFeatureId.action",
		data:{
			 "houseProperty.id":LOCATIONVO_id,
	         "houseProperty.openLayersMapInfo.centerLon":centerLon,
	         "houseProperty.openLayersMapInfo.centerLat":centerLat,
	         "houseProperty.openLayersMapInfo.featureId":featureId
		},
		success:function(result){
			if(null != result){
				$.messageBox({message:"该住房信息绑定成功!"});
				
				var imgurl = getBubbleUrl(LOCATIONVO_index);
				$("#map").TqMap("addMarker",{
					iconUrl:PATH+imgurl,
					markerW:26,
					markerH:30,
					lon:centerLon,
					lat:centerLat,
					markerId:LOCATIONVO_id+"_houseProperty"
				});
				showHousePropertyPopup(centerLon,centerLat,null,"person",LOCATIONVO_id,"place");
				//注册marker点击事件
				$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:markerClick});
				var btnHtml = '<a href="javascript:;" class="cancelBind" onclick="javascript:unbindHouseInfo('+LOCATIONVO_id+','+LOCATIONVO_index+');">[解除绑定]</a>';
				$(".resultlist:visible #"+LOCATIONVO_id+" .btnList").html(btnHtml);
				$(".resultlist:visible #"+LOCATIONVO_id+" .resNum").removeClass("resNum").addClass("resAbc").html("<span><input type='image' src='"+imgurl+"' /></span>");
				$(".resultlist:visible #"+LOCATIONVO_id+" .name a").attr("onclick",
						"showHousePropertyPopup("+centerLon+","+centerLat+","+null+",'person',"+LOCATIONVO_id+",'place');");
			}else {
				$.messageBox({message:"该住房信息绑定失败",level:'error'});
			}
		}
	});  
}



//显示popup
function showHousePropertyPopup(centerLon,centerLat,idCardNo,mode,id,type){
	var lonlat = {lon:centerLon,lat:centerLat};
	$("#centerLon").val(centerLon);
	$("#centerLat").val(centerLat);
	$("#mode").val("<s:property value='@ com.tianque.openLayersMap.util.GisGlobalValueMap@PERSON_MODE'/>");
	$('#type').val(type);
	$("#id").val(id);
	tempPageMarkerArray[tempPageIndex++]=id;
	$("#map").TqMap("addPopup",{
		url:PATH+"/gis/commonOpenLayersManage/getHouseById.action?mode="+mode+"&id="+id+"&type="+type+"&showPeriphery="+true
				+"&idCardNo="+null,
		lon:centerLon,
		lat:centerLat,
		lonlat:lonlat,
		popupW:360,
		popupH:280
	});
}
function houseInfoListByOrgIdAndQueryStr(pageSize, page){
	var result = null;
	$.ajax({
		async:false,
		url:'${path}/baseinfo/housePropertyManage/getHouseInfoListByOrgIdAndHouseNameOrAddress.action',
		datatype:"json",
		data:{
			"orgId":$("#currOrgId").val(),
			"queryStr":$("#queryString").val(),
			"page":page<=0?1:page,
			"rows":pageSize,
			"sidx":"id",
			"sord":"desc"
		},
		success:function(data){
			result = data;
		}
	});
	return result;
}

</script>