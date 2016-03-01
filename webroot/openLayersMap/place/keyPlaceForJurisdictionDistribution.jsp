<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div id="personPieChartlDialog"></div>
<script type="text/javascript">

$(function(){
	findKeyPlaceJurisdictionDistribution();
	function findKeyPlaceJurisdictionDistribution(){
		var keyPlaceType = "<s:property value='#parameters.placeType'/>";
		var modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_STATISTIC'/>";
		clearMapAllMarkerAndPopup();
		$.ajax({
			url:'${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndTypeName.action',
			async:false,
			data:{
				"organization.id":$('#currOrgId').val(),
				"typeName":keyPlaceType,
				"modeType":modeType
				
			},
			success:function(data){
				clearFeaturesAndPopupText();
				for(var i=0;i<data.length;i++){
					var color = loadFeature(data[i],i,keyPlaceType,modeType);
					if(data[i].lon!=null && data[i].lat!=null){
						var pop=$("#map").TqMap("addPopupText",{
							 lon:data[i].lon,
							 lat:data[i].lat,
							 popupW:50,
							 popupH:12,
							 popupText:"<div class='popFrame'><span>"+data[i].sumNum+"</span></div>",
							 backgroundColor:'transparent',
							 popupTextId:i+1,
							 data:{orgId:data[i].organization.id,typeName:keyPlaceType,modeType:modeType},
							 autoSize:true,
							 isBgImg:true
						});
						$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:keyPlaceJurisdictionDetail});//气泡点击事件
						var popText=$("#map").TqMap("addPopupText",{
							 lon:data[i].lon*1-0.001,
							 lat:data[i].lat*1+0.0025,
							 popupW:100,
							 popupH:20,
							 popupText:""+data[i].organization.orgName+"",
							 data:{orgId:data[i].organization.id,typeName:keyPlaceType,modeType:modeType},
							 backgroundColor:'transparent'
						});
						$(pop.contentDiv).attr("title",data[i].organization.orgName+" "+personOrPlaceName+" 总数:"+data[i].sumNum);
						$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:keyPlaceJurisdictionDetail});//气泡点击事件
					}
				}
				showLegend(data);
			}
		});
		$("#jurisdictionDistribution").click();
	}
	
})

function keyPlaceJurisdictionDetail(e){
	IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
	$("#map").TqMap("removeAllFeatures");//清除二维图上的面
	$("#map").TqMap("deleteAllPopupText");
	$(".gis_zoom_button").addClass("hidden");//清除图例按钮
	$(".gis_zoom_content").hide();//清除图例
	$("#detailClick").attr("value",true);
	clearMarkersByMarkerId_objectName("hourse");//清除楼宇
	clearMarkersByMarkerId_objectName("common");
	treeSynchro(e.object.data.orgId);
	var locationType="<s:property value='#parameters.placeType'/>";
	var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@JURISDICTIONDISTRIBUTION'/>";
	var url="${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action";
	var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_SEARCH'/>";
	var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_DETAILVIEW'/>";
	loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName=keyPlaces&childTableName=keyPlaces&url='+url+'&modeType='+modeType+"&keyType="+locationType+'&type='+locationType+"&functionType="+functionType
			+"&modeTypeDetailView="+modeTypeDetailView);//乡镇级别的公用页面
}

</script>