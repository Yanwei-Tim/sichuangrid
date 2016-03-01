<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="searchResult" style="overflow: hidden;">
	<div class="resultNum"><span class="plaint" title="清空"><a id="emptyIssueMapAndList" href='javascript:void(0)' >【清空】</a></span>共有<span id="issueListTotal">0</span>条搜索记录</div>
<!-- 	<div class="resultCon"> -->
<%-- 		<div id="issueList"></div> --%>
<!-- 		<div id="loadingValue"><img src='../resource/images/loading.gif' alt='加载中...'  style='vertical-align:middle;margin-left:15px;height:25px;' />信息加载中，请等待...</div> -->
<!-- 	</div> -->
</div>
<div id="personPieChartlDialog"></div>
<div id="issueListPager" class="pagination"></div>

<script type="text/javascript">
var issueListPrevsPageInfo=null;

$(function(){
	viewIssueLayerInformation();
	$("#emptyIssueMapAndList").click(function(){
		IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
		if(!"<s:property value='#parameters.flag'/>"){//判断是否辖区分布统计 true则为是的情况
			$("#updownlist").val(1);//将搜索下拉选项选中为人员信息
			$("#queryString").val("请输入(姓名/身份证号码/地址)");
			$("#issueMarkerId").parent().removeClass("currentPosClick");//去除服务办事图层选中样式
		}
		$("#map").TqMap("deletePopup");
		clearMarkersByMarkerId_objectName("issue");
		$("#issueListTotal").html(0);
		$("#emptyIssueMapAndList").hide();
		$("#issueList").nextAll().remove();
		$("#issueListPager").empty();
	});
	
	function viewIssueLayerInformation(){
		var orgId = $('#currOrgId').val();
		clearMapAllMarkerAndPopup();
		if("<s:property value='#parameters.flag'/>"){//判断是否右边列表点击事件 true则为是的情况
			var url="";
			var issueType="<s:property value='#parameters.type'/>";
			var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_STATISTIC'/>";
			issueAreaDistribution(orgId,issueType,modeType);
			$("#jurisdictionDistribution").click();
			return;
		}
		
	}
	
	//事件辖区分布
	function issueAreaDistribution(orgId,issueType,modeType){
		$.ajax({
			url:'${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndTypeName.action',
			async:false,
			data:{
				"organization.id":orgId,
				"typeName":issueType,
				"modeType":modeType
			},
			success:function(data){
				clearFeaturesAndPopupText();
				for(var i=0;i<data.length;i++){
					var color = loadFeature(data[i],i,issueType,modeType);
					if(data[i].lon!=null && data[i].lat!=null){
						var pop=$("#map").TqMap("addPopupText",{
							 lon:data[i].lon,
							 lat:data[i].lat,
							 popupW:50,
							 popupH:12,
							 popupText:""+data[i].sumNum+"",
							 popupText:"<div class='popFrame'><span>"+data[i].sumNum+"</span></div>",
							 backgroundColor:'transparent',
							 popupTextId:i+1,
							 data:{orgId:data[i].organization.id,typeName:issueType,modeType:modeType},
							 autoSize:true,
							 isBgImg:true
						});
						$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:issueJurisdictionDetail});
						var popText=$("#map").TqMap("addPopupText",{
							 lon:data[i].lon*1-0.001,
							 lat:data[i].lat*1+0.0025,
							 popupW:100,
							 popupH:20,
							 popupText:""+data[i].organization.orgName+"",
							 data:{orgId:data[i].organization.id,typeName:issueType,modeType:modeType},
							 backgroundColor:'transparent'
						});
						$(pop.contentDiv).attr("title",data[i].organization.orgName+" "+issueTypeName+" 总数:"+data[i].sumNum);
						$("#map").TqMap("registerEvent",{objectName:"popupText",eventName:"click",func:issueJurisdictionDetail});
					}
				}
				showLegend(data);
			}
		});
	}
});

function issueJurisdictionDetail(e){
	IS_MAP_MOVE_SEARCH=false;//地图滑动搜索关闭
	$("#map").TqMap("removeAllFeatures");//清除二维图上的面
	$("#map").TqMap("deleteAllPopupText");
	$(".gis_zoom_button").addClass("hidden");//清除图例按钮
	$(".gis_zoom_content").hide();//清除图例
	clearMarkersByMarkerId_objectName("hourse");//清除楼宇
	clearMarkersByMarkerId_objectName("common");
	$("#detailClick").attr("value",true);
	treeSynchro(e.object.data.orgId);
	var issueType="<s:property value='#parameters.type'/>";
	var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@JURISDICTIONDISTRIBUTION'/>";
	var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_SEARCH'/>";
	var modeTypeDetailView="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@ISSUE_DETAILVIEW'/>";
	url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndTypeName.action';
	loadTabListInfo('${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName=issues&sordField=iu.centerlon&modeType='+modeType+'&url='+url+'&type='+issueType+"&functionType="+functionType
			+"&modeTypeDetailView="+modeTypeDetailView);
}

</script>