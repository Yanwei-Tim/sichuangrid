<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getGisModuleByIssearch" var="searchType" executeResult="false" namespace="/gis/twoDimensionMapModuleManage" >
</s:action>
<div class="gis_tools_info" style="height: 30px;">
	<div class="gis_tools">
		<jsp:include page="/openLayersMap/gisToolbar/orgSelectedComponent.jsp"></jsp:include>
		<jsp:include page="/openLayersMap/gisToolbar/drawConstruction.jsp"></jsp:include> 
		<div id="popedomIntroduce" style="float: left;margin-right: 3px;cursor:pointer;">辖区概况</div>
		<div id="tool_container" class="tools_r">
		    <div class="tools_r_con" id="tool_container_con" style="width: 300px;">
		    	<div class="toolBtn toolBtn--img toolBtn--border toolBtn--img--sehinv" id="tool_sehInView">
		    		<span class="toolBtn-cont"><span class="toolBtn-img"></span>视野内搜索</span>
		    		<div class="map_popup">
					    <div class="popup_main">
					        <div class="title">在屏幕范围内搜索</div>
					        <div class="content" style="padding-left: 10px; overflow-y: auto;">
					            <div id="siv_main" class="blueC">
					                <ul class="siv_list" id="sivList">
					                	<s:iterator value="#searchType.headerSearchList">
					                     	<li name="<s:property value="searchFieldAName" />"
					                     			title="<s:property value="searchFieldBName" />"
					                     			searchFieldName="<s:property value="searchFieldCName" />"
					                     			alt="<s:property value="searchFieldA" />"
					                     			dir="<s:property value="searchFieldB" />"
					                     			searchField="<s:property value="searchFieldC" />"
					                     	  		data-value="<s:property value='tableName' />"
					                     	  		modeType="<s:property value='modeType' />"
					                     	  		>
					                     		<a href="javascript:;"><s:property  value="moduleName" /></a>
					                     	</li>
					                     </s:iterator>
					                </ul>
					                <div class="siv_sec">
				                        <input id="queryString" value="请输入搜索条件" class="siv_txt show_search_btn" type="text" autocomplete="off" />
				                        <input value="搜索" type="submit" class="siv_btn" id="sivSubmitBtn" />
					                </div>
					            </div>
					            <div class="siv_result" id="sivResult">
						        	<p class="siv_resultext">当前搜索：<a href="javascript:;" class="resultitem"> <span class="important">公交车站</span><span class="remove"></span></a><a class="siv_result_change_link" href="javascript:;">更改条件</a>
						        	</p>
						        </div>
					        </div>
					        <button id="sivCtrlCloseBtn" title="收缩"></button>
					    </div>
					</div>
		    	</div>
		        <div class="toolBtn toolBtn--noHover toolBtn--img toolBtn--img--both toolBtn--img--tool" id="tool_tollCon"><span class="toolBtn-cont"><span class="toolBtn-img"></span>工具<span class="toolBtn-img toolBtn-img--right"></span></span>
		            <div class="toolBox" id="tools_box">
						<a title="测距" onclick="measureRange(this);" class="toolBox-item"><strong class="ranging"></strong>测距</a>
						<a title="测面积" onclick="measureArea(this);" class="toolBox-item"><strong class="measure"></strong>测面</a>
						<a title="显示本级区域边界" onclick="showCurrentArea(this);" class="toolBox-item"><strong class="showCurrentArea"></strong>本级</a>
						<a title="显示下辖区域边界" onclick="showJurisdictionArea(this);" class="toolBox-item"><strong class="showJurisdictionArea"></strong>下辖</a>
						<a title="画多边形统计" id="drawPolygon" class="toolBox-item"><strong class="drawface"></strong>多边形</a>
						<a title="画圆统计" onclick="drawRegularPolygon(this,40)" id="drawAreaCount" class="toolBox-item"><strong class="drawCircle"></strong>圆</a>
						<a title="画矩形统计" onclick="drawRegularPolygon(this,4)" class="toolBox-item"><strong class="rectangular"></strong>矩形</a>
			        </div>
		    	</div>
		    	<a title="清除痕迹" class="toolBtn toolBtn--img toolBtn--border toolBtn--img--traf" onclick="cleanMapTrace(this);"><span class="toolBtn-cont"><span class="toolBtn-img"></span>清除</span></a>
		        <a class="toolBtn toolBtn--img toolBtn--border toolBtn--img--full" id="fullScreen"><span class="toolBtn-cont"><span class="toolBtn-img"></span><span id="fullScreenText">全屏</span></span></a></div>
		        
		</div>
	</div>
</div>
<div id="popedomIntroduceList"></div>
<script>
//全屏控制
$("#fullScreen").on("click",function(){
	if($(".gisLeft").is(":visible")){
		$(".ui-layout-center").trigger("resize");
		$("#map").trigger("resize");
		$("#gisContainer").trigger("resize");
		$(".ui-layout-north:visible").hide();
		$(".ui-layout-south:visible").hide();
		$(".gisLeft,.gis_left_turn").hide();
		$(this).addClass("gis_left_zoomOut");
		$(this).addClass("close").removeClass("open");
		$(".gisContainer").css("margin-left","0");
		$(".periphery_button").css("right",-2);
		$("#map").height($("body").height()).TqMap("get","map").updateSize();//修改地图大小
		$("#fullScreenText").text("退出全屏");
		$("#fullScreen").attr("title","退出全屏");
		$("#statisticInfo").css("right","5px");
		$(".message-tip").hide();
	}else{
		$(".ui-layout-center").trigger("resize");
		$("#map").trigger("resize");
		$("#gisContainer").trigger("resize");
		$(".ui-layout-north:hidden").show();
		$(".ui-layout-south:hidden").show();
		$(".gisLeft,.gis_left_turn").show();
		$(this).removeClass("gis_left_zoomOut");
		$(this).addClass("open").removeClass("close");
		$(".gisContainer").css("margin-left","268px");
		$(".periphery_button").css("right","");
		$("#map").height($("body").height()-$(".gis_header").height()-38).TqMap("get","map").updateSize();//修改地图大小
		$("#fullScreenText").text("全屏");
		$("#fullScreen").attr("title","全屏");
		$("#statisticInfo").css("left","280px");
		$(".message-tip").show();
	}
});

	$.fn.showTxtValue=function(){
		var self = $(this);
		var value = $(this).attr("value");
		self.unbind("focusin").unbind("focusout").focusin(function(){
			if(self.attr("value")==value){
				self.attr({"value":""})
			}
		}).focusout(function(){
			if(self.attr("value")==""){
				self.attr("value",value)
			}
		})
	}
	$(function(){
		$("#queryString").showTxtValue();
		$(".map_popup").click(function(event){
			event.stopPropagation();
		})
		$("#sivCtrlCloseBtn").click(function(){
			$("#tool_sehInView").find(".map_popup").hide();
		})
		$("#tool_sehInView").click(function(){
			var that=this;
			var thatPop=$(that).find(".map_popup");
			if(thatPop.is(":visible")){
				thatPop.hide();
			}else{
				thatPop.show();
			}
		}).click(function(){
			var that=this;
			var thatPop=$(that).find(".map_popup");
			if($(".map_popup").is(":hidden")){
				thatPop.hide();
			}else{
				thatPop.show();
			}
			/*$(document).one("click",function(){
				thatPop.hide();
			});*/
		})
		$("#tool_tollCon").hover(function(){
			$(this).find(".toolBox").show();
		},function(){
			$(this).find(".toolBox").hide();
		})
		$("#sivList li").click(function(){
			var thisText = $.trim($(this).attr("name"))+"/"+$.trim($(this).attr("title"))
			$(this).addClass("cur").siblings().removeClass("cur");
			if($("#queryString").val().indexOf("请输入")==-1){
				return false;
			}
			$("#queryString").val("请输入（"+thisText+"）");
			$("#queryString").showTxtValue();
		})
		$("#tool_sehInView").find("#sivList li").eq(0).click();
		$("#sivSubmitBtn").click(function(){
			var flag=isNullQueryString();
			if(flag){
				$.messageBox({message:"请输入搜索条件",level: "error"});
				return;
			}
			$("#detailClick").val("");
			$("#map").TqMap("deletePopup");//删除popup
			var tableName=$("#sivList li.cur").attr("data-value");
			/*if($("#sivList li.cur").attr("data-value")=="<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@TWO_NEWGROUP' />"){//如果是twoNewGroup，tableName为keyPalces
				tableName = "<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PLACE_MODE' />";
			}*/
			
			var modeType=$("#sivList li.cur").attr("modeType");
			var modeTypeDetailView=modeType+"DetailViewService";//详情查看modeType
			var filedA=$("#sivList li.cur").attr("name");
			var filedB=$("#sivList li.cur").attr("dir");
			var filedC=$("#sivList li.cur").attr("searchField");
			allTypeName=$("#sivList li.cur").html();
			if(tableName!="deviceInformation" || isTownDownOrganization()) clearGpsMapAllMarker();
			clearFeaturesByMarkerId_objectName("defaultGridLayer");//清除默认的下辖区域
			clearPopupTextById("defaultPopupText");//清除默认的下辖区域的POPup
			clearMarkersByMarkerId_objectName("common");
			$(".gis_zoom_button").addClass("hidden");//清除图例按钮
			$(".gis_zoom_content").hide();//清除图例内容
			IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
			var functionType="<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH'/>";
			if(isTownDownOrganization()&&tableName!="issues"){
				modeType=modeType+"StatisticService";
				url = '${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndSearchValue.action';
			}else{
				$("#map").TqMap("deletePopup");
				$("#map").TqMap("deleteAllPopupText");
				$("#map").TqMap("removeAllFeatures");
				modeType=modeType+"SearchService";
				url = '${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndSearchValue.action';
			}
			var tablistUrl = "${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?mainTableName="+tableName+"&modeType="+modeType+"&url="+url+"&modeTypeDetailView="+modeTypeDetailView+"&functionType="+functionType+"&isSerachMode=true";
			var fields = "&filedA="+filedA+"&filedB="+filedB+"&filedC="+filedC;
			if(modeType=="issueMapSearchService"){
				tablistUrl = tablistUrl+fields+"&sordField=iu.centerlon";
			}else if(modeType=="keyPlaceMapSearchService"){
				tablistUrl = tablistUrl+fields;
			}else if(modeType=="keyPersonMapSearchService"){
				tablistUrl = tablistUrl+fields+"&keyType=null";
			}else if(modeType=="publicSecurityMapSearchService"){
				tablistUrl = tablistUrl+fields+"&keyType=null";
			}else if(modeType=="gpsMapSearchService"){
				tablistUrl = tablistUrl+"&sordField=locatedate";
			}
			loadTabListInfo(tablistUrl);
			$("#siv_main").hide()
			$("#sivResult").show().find(".important").html($("#queryString").val());
			$(".map_popup").animate({height:70},300);
		})
		$(".siv_result_change_link").click(function(){
			$("#sivResult").hide();
			$("#siv_main").show();
			$(".map_popup").height(210);
		})
		$("#sivResult").delegate(".remove","click",function(){
			$(".resultitem").find(".important").empty();
			$(".siv_result_change_link").click();
		})
		/*$("#hourseInfo").click(function(){
			if($("#hourseInfo").attr("checked")=="checked"){
				$('#construction').show();
				$("#addHourseInfo").show();
				$("#deleteHourseInfo").show();
			}else{
				$('#construction').hide();
				$("#addHourseInfo").hide();
				$("#deleteHourseInfo").hide();
			}
		});	*/
		
		$("#popedomIntroduce").click(function(){
			$("#popedomIntroduceList").createDialog({
				width: 900,
				height: 600,
				zIndex:1020,
				title: '信息',
				modal:true,
				url: "${path}/openLayersMap/gisToolbar/introduce.jsp?orgId="+$("#currOrgId").val(),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   			}
					}
				});
			});
	})
	
	function isNullQueryString(){
		var str= $("#queryString").val();;
		if(str.indexOf('请输入')>=0 ||str==null || str.trim()==""){
			return true;
		}
		return false;
	}
	
</script>