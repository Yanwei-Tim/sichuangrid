<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="searchResult" style="overflow: hidden;">
	<div class="resultNum"><span class="plaint" title="清空"><a id="emptyHouseMapAndList" href='javascript:void(0)' >【清空】</a></span>共有<span id="houseListTotal">0</span>条搜索记录</div>
</div>
<div id="houseListPager" class="pagination"></div>
<div id="personPieChartlDialog"></div>
<input type="hidden" id="moduleType" value="place" />

<script type="text/javascript">
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
	var orgId=$('#currOrgId').val();
	clearMapAllMarkerAndPopup();
	var str= $("#queryString").val();
	if(queryStringValueIsNull()){
		str='';
	}
	
	if("<s:property value='#parameters.flag'/>"){//判断是否右边列表点楼宇 true则为是的情况
		var buildDatasType="<s:property value='#parameters.type'/>";
		var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@BUILDDATA_STATISTIC'/>";
		boundBuildDatasList(orgId,buildDatasType,modeType);
		$("#jurisdictionDistribution").click();
		return;
	}
}

function boundBuildDatasList(orgId,buildDatasType,modeType){
	$.ajax({
		url:'${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndTypeName.action',
		async:false,
		data:{
			"organization.id":orgId,
			"typeName":buildDatasType,
			"modeType":modeType
		},
		success:function(data){
			clearFeaturesAndPopupText();
			for(var i=0;i<data.length;i++){
				var color = loadFeature(data[i],i,buildDatasType,modeType);
				if(data[i].lon!=null && data[i].lat!=null){
					var pop=$("#map").TqMap("addPopupText",{
						 lon:data[i].lon,
						 lat:data[i].lat,
						 popupW:50,
						 popupH:12,
						 popupText:"<div class='popFrame'><span>"+data[i].sumNum+"</span></div>",
						 backgroundColor:'transparent',
						 popupTextId:i+1,
						 data:{orgId:data[i].organization.id,typeName:buildDatasType,modeType:modeType},
						 autoSize:true,
						 isBgImg:true
					});
					$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:buildJurisdictionDetail});
					var popText=$("#map").TqMap("addPopupText",{
						 lon:data[i].lon*1-0.001,
						 lat:data[i].lat*1+0.0025,
						 popupW:100,
						 popupH:20,
						 popupText:""+data[i].organization.orgName+"",
						 data:{orgId:data[i].organization.id,typeName:buildDatasType,modeType:modeType},
						 backgroundColor:'transparent'
					});
					$(pop.contentDiv).attr("title",data[i].organization.orgName+" "+housePropertyTypeName+" 总数:"+data[i].sumNum);
					$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:buildJurisdictionDetail});
				}
			}
			showLegend(data);
		}
	});
}
function buildJurisdictionDetail(e){
	IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
	$("#map").TqMap("removeAllFeatures");//清除二维图上的面
	$("#map").TqMap("deleteAllPopupText");
	$(".gis_zoom_button").addClass("hidden");//清除图例按钮
	$(".gis_zoom_content").hide();//清除图例
	$("#detailClick").attr("value",true);
	clearMarkersByMarkerId_objectName("hourse");//清除楼宇
	clearMarkersByMarkerId_objectName("common");
	treeSynchro(e.object.data.orgId);
	var houseType="<s:property value='#parameters.type'/>";
	var mainTableName="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@BUILD_TABLENAME'/>";
	var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@JURISDICTIONDISTRIBUTION'/>";
	var url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
	var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@BUILDDATA_SEARCH'/>";
	var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@BUILDDATA_DETAILVIEW'/>";
	loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName='+mainTableName+'&modeType='+modeType+'&url='+url+'&type='+houseType+"&functionType="+functionType
			+"&modeTypeDetailView="+modeTypeDetailView);
	
}
</script>