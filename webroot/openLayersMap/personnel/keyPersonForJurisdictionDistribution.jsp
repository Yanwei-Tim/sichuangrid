<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="searchResult">
	<div class="resultNum"><span class="plaint" title="清空"><a id="emptyKeyPersonAreaDistributionMapAndList" href='javascript:void(0)' >【清空】</a></span>共有<span id="personListTotal">0</span>条搜索记录</div>
<!-- 	<div class="resultCon"> -->
<%-- 		<div id="personList"></div> --%>
<!-- 		<div id="loadingValue"><img src='../resource/images/loading.gif' alt='加载中...'  style='vertical-align:middle;margin-left:15px;height:25px;' />信息加载中，请等待...</div> -->
<!-- 	</div> -->
	<input type="hidden" id="buildingId" value="" /></div>
<div id="personListPager" class="pagination"></div>
<div id="houseDialog"></div>
<div id="personPieChartlDialog"></div>

<script type="text/javascript">
$(function(){
	viewPersonLayerInformation();
	$("#emptyKeyPersonAreaDistributionMapAndList").click(function(){
		IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
		$("#emptyKeyPersonAreaDistributionMapAndList").hide();
		$("#map").TqMap("deletePopup");
		clearMarkersByMarkerId_objectName("person");
		$("#personListTotal").html(0);
		$("#personList").nextAll().remove();
		$("#personListPager").empty();
	});
	function viewPersonLayerInformation(){
		var orgId=$('#currOrgId').val();
		var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_STATISTIC'/>";
		clearMapAllMarkerAndPopup();
		var str= $("#queryString").val();
		if(queryStringValueIsNull()){
			str='';
		}
		if("<s:property value='#parameters.flag'/>"){//判断是否右边列表点击重点人员 true则为是的情况
			findKeyPersonJurisdictionDistribution(personType,orgId,modeType);	//乡镇级别一下
			$("#jurisdictionDistribution").click();
			return;
		}

	}

	function findKeyPersonJurisdictionDistribution(personType,orgId,modeType){
		$.ajax({
			url:'${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndTypeName.action',
			async:false,
			data:{
				"organization.id":orgId,
				"typeName":personType,
				"modeType":modeType
			},
			success:function(data){
				clearFeaturesAndPopupText();
				for(var i=0;i<data.length;i++){
					var color = loadFeature(data[i],i,personType,modeType);
					if(data[i].lon!=null && data[i].lat!=null){
						var pop=$("#map").TqMap("addPopupText",{
							 lon:data[i].lon,
							 lat:data[i].lat,
							 popupW:50,
							 popupH:12,
							 popupText:"<div class='popFrame'><span>"+data[i].sumNum+"</span></div>",
							 backgroundColor:'transparent',
							 popupTextId:i+1,
							 data:{orgId:data[i].organization.id,typeName:personType,modeType:modeType},
							 autoSize:true,
							 isBgImg:true
						});
						$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:keyPerpsonJurisdictionDetail});//气泡点击事件
						var popText=$("#map").TqMap("addPopupText",{
							 lon:data[i].lon*1-0.001,
							 lat:data[i].lat*1+0.0025,
							 popupW:100,
							 popupH:20,
							 popupText:""+data[i].organization.orgName+"",
							 data:{orgId:data[i].organization.id,typeName:personType,modeType:modeType},
							 backgroundColor:'transparent'
						});
						$(pop.contentDiv).attr("title",data[i].organization.orgName+" "+personOrPlaceName+" 总数:"+data[i].sumNum);
 						$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:keyPerpsonJurisdictionDetail});//气泡点击事件
					}
				}
				showLegend(data);
			}
		});
	}
	
})

function keyPerpsonJurisdictionDetail(e){
	IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
	$("#map").TqMap("removeAllFeatures");//清除二维图上的面
	$("#map").TqMap("deleteAllPopupText");
	$(".gis_zoom_button").addClass("hidden");//清除图例按钮
	$(".gis_zoom_content").hide();//清除图例
	clearMarkersByMarkerId_objectName("hourse");//清除楼宇
	clearMarkersByMarkerId_objectName("common");
	var personType="<s:property value='#parameters.type'/>";
	$("#detailClick").attr("value",true);
	treeSynchro(e.object.data.orgId);
	var keyType="";
	var modeType = "<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_SEARCH'/>";
	var url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
	var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@JURISDICTIONDISTRIBUTION'/>";
	var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PERSON_DETAILVIEW'/>";
	loadTabListInfo("${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?type="+personType+"&mainTableName=keyPerson&childTableName="+personType+"&keyType=null&modeType="+modeType+"&url="+url+"&functionType="+functionType
			+"&modeTypeDetailView="+modeTypeDetailView);
}

</script>