<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<div id="gisTablist" class="searchBox leaveOpacity clearfix">
        <div class="formBox">
	        <div class="searchContain">
	        	<div class="searchForm">
                    <div class="searchTrees searchSelect searchContent">
                        <input type="text" class="searchText" id="queryString" />
                    </div>
                    <span><s:property value="gisModuleVo.iconUrl" /></span>
                     <select id="updownlist" class="hidden">
                     <s:iterator value="headerSearchList">
                     	<option name="<s:property value="searchFieldAName" />"
                     			title="<s:property value="searchFieldBName" />"
                     			searchFieldName="<s:property value="searchFieldCName" />"
                     			alt="<s:property value="searchFieldA" />"
                     			dir="<s:property value="searchFieldB" />"
                     			searchField="<s:property value="searchFieldC" />"
                     	  		value="<s:property value='tableName' />"
                     	  		modeType="<s:property value='modeType' />"
                     	  		>
                     		<s:property  value="moduleName" />
                     	</option>
                     </s:iterator>
                   	</select>
	            </div>
	             <a href="javascript:;" class="searchBtn" id="gisSearchBtn"><span>搜 索</span></a>
	             
	             <!-- <a href="javascript:;" class="searchBtn" id="video"><span>视频定位</span></a> -->
	        </div>
        </div>
	</div>
	 <!--<ul class="searchMark">
		<%--<li class="currentIndex"><a href="<s:property value='@com.tianque.util.GridProperties@PRODUCT_SERVER'/>/module.jsp#index" title="系统主页"></a></li> --%>
		<pop:JugePermissionTag ename="gisSystemManagement">
        	<li class="systemControl"><a id="gisSystemManageBtn" target="_blank" href="${path}/openLayersMap/system/module.jsp" title="系统管理"></a></li>
        </pop:JugePermissionTag>
        <li class="currentBack"><a href="javascript:window.close();" title="退出"></a></li>
        <div id="videoDialog"></div>
<div id="videotapeDialog"></div>
    </ul>-->



<script type="text/javascript">
	$(function(){
		$("#gisTablist").hover(function(){
			$(this).addClass("hoverOpacity").removeClass("leaveOpacity");
		},function(){
			$(this).addClass("leaveOpacity").removeClass("hoverOpacity");
		})
		
		$("#video").click(function(){
			//模拟四个坐标点
			var points= [{"lon":106.93497823424,"lat":34.105827932602,"id":1},
						 {"lon":106.92897008604,"lat":34.106943731552,"id":2},
						 {"lon":106.9493977899,"lat":34.102738027817,"id":3},
						 {"lon":106.93351911253,"lat":34.097330694443,"id":4},
						 {"lon":106.94484876341,"lat":34.092695837265,"id":5}];
			for(var i=0;i<points.length;i++){
				var marker=$("#map").TqMap("addMarker",{
					iconUrl:"${resource_path}/resource/openLayersMap/images/video.gif",
					markerW:20,
					markerH:50,
					lon:points[i].lon,
					lat:points[i].lat,
					markerId:points[i].id
				});
				$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:function(){
					$("#videoDialog").createDialog({
						width: 580,
						height: 450,
						modal:false,
						title:"实时视频播放",
						url:"${path}/openLayersMap/gisHeader/maintainVideoDlg.jsp",
						buttons: {
					   		"关闭" : function(){
					        	$(this).dialog("close");
					   		}
						}
					});
				}});
			}
		});
		
		$("#updownlist").gisSearchSelect({//下拉列表
		});
		
		$("#queryString").keydown(function(event){
			switch(event.keyCode) {
				case 13: $("#gisSearchBtn").click();
					break;
				default: ;
			}
		});
		$("#gisSearchBtn").click(function(){
			var flag=isNullQueryString();
			if(flag){
				$.messageBox({message:"请输入搜索条件",level: "error"});
				return;
			}
			$("#detailClick").val("");
			$("#map").TqMap("deletePopup");//删除popup
			var tableName=$(".updownList dt").attr("value");
			var modeType=$(".updownList dt").attr("modeType");
			var modeTypeDetailView=modeType+"DetailViewService";//详情查看modeType
			var filedA=$(".updownList dt").attr("name");
			var filedB=$(".updownList dt").attr("dir");
			var filedC=$(".updownList dt").attr("searchField");
			allTypeName=$(".updownList dt").html();
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
			}else if(modeType=="gpsMapSearchService"){
				tablistUrl = tablistUrl+"&sordField=locatedate";
			}
			loadTabListInfo(tablistUrl);
		});

		$("#gisTypeManageBtn").click(function(){
			$("#gisTypeManageDialog").createDialog({
			    width: 900,
			    height: 620,
			    title:'类型管理',
			    url:'${path}/gis/gisTypeManage/dispatch.action?mode=manage',
			    buttons: {
			        "关闭" : function(){
			         $(this).dialog("close");
			        }
			    }
			});
		})
		
	})
	function isNullQueryString(){
		var str= $("#queryString").val();
		if(str.indexOf('请输入')>=0 ||str==null || str.trim()==""){
			return true;
		}
		return false;
	}
</script>