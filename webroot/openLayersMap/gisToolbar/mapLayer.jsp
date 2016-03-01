<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="partTypeCache" />
<input type="hidden" id="partNameCache" />
<jsp:include page="gisLayersToolsList.jsp"></jsp:include>
<div id="mapChangeToolsArea" class="mapChangeToolsArea clearfix">
	<div class="mapLayoutTools clearfix">
		<ul class="LayerDisplay clearfix">
			<li>
				<a href="javaScript:void(0);" class="personalLayer personalLayerTools curHoverEffect clearfix">
					<span class="tit"><strong class="drawlayer"></strong>图层</span>
					<div id="showPersonLayer" class="showPersonLayer hidden clearfix">
						<ul class="dataMsg"></ul>
					</div>
				</a>
			</li>
		</ul>
	</div>
</div>
<div id="dustbinSelectDialog"></div>
<script type="text/javascript">
var GPSUtil = {
	    PI : 3.14159265358979324,
	    x_pi : 3.14159265358979324 * 3000.0 / 180.0,
	    delta : function (lat, lon) {
	        // Krasovsky 1940
	        //
	        // a = 6378245.0, 1/f = 298.3
	        // b = a * (1 - f)
	        // ee = (a^2 - b^2) / a^2;
	        var a = 6378245.0; //  a: 卫星椭球坐标投影到平面地图坐标系的投影因子。
	        var ee = 0.00669342162296594323; //  ee: 椭球的偏心率。
	        var dLat = this.transformLat(lon - 105.0, lat - 35.0);
	        var dLon = this.transformLon(lon - 105.0, lat - 35.0);
	        var radLat = lat / 180.0 * this.PI;
	        var magic = Math.sin(radLat);
	        magic = 1 - ee * magic * magic;
	        var sqrtMagic = Math.sqrt(magic);
	        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * this.PI);
	        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * this.PI);
	        return {'lat': dLat, 'lon': dLon};
	    },
	     
	    //WGS-84 to GCJ-02
	    gcj_encrypt : function (wgsLat, wgsLon) {
	        if (this.outOfChina(wgsLat, wgsLon))
	            return {'lat': wgsLat, 'lon': wgsLon};
	 
	        var d = this.delta(wgsLat, wgsLon);
	        return {'lat' : wgsLat + d.lat,'lon' : wgsLon + d.lon};
	    },
	    //GCJ-02 to WGS-84
	    gcj_decrypt : function (gcjLat, gcjLon) {
	        if (this.outOfChina(gcjLat, gcjLon))
	            return {'lat': gcjLat, 'lon': gcjLon};
	         
	        var d = this.delta(gcjLat, gcjLon);
	        return {'lat': gcjLat - d.lat, 'lon': gcjLon - d.lon};
	    },
	    //GCJ-02 to WGS-84 exactly
	    gcj_decrypt_exact : function (gcjLat, gcjLon) {
	        var initDelta = 0.01;
	        var threshold = 0.000000001;
	        var dLat = initDelta, dLon = initDelta;
	        var mLat = gcjLat - dLat, mLon = gcjLon - dLon;
	        var pLat = gcjLat + dLat, pLon = gcjLon + dLon;
	        var wgsLat, wgsLon, i = 0;
	        while (1) {
	            wgsLat = (mLat + pLat) / 2;
	            wgsLon = (mLon + pLon) / 2;
	            var tmp = this.gcj_encrypt(wgsLat, wgsLon)
	            dLat = tmp.lat - gcjLat;
	            dLon = tmp.lon - gcjLon;
	            if ((Math.abs(dLat) < threshold) && (Math.abs(dLon) < threshold))
	                break;
	 
	            if (dLat > 0) pLat = wgsLat; else mLat = wgsLat;
	            if (dLon > 0) pLon = wgsLon; else mLon = wgsLon;
	 
	            if (++i > 10000) break;
	        }
	        //console.log(i);
	        return {'lat': wgsLat, 'lon': wgsLon};
	    },
	    //GCJ-02 to BD-09
	    bd_encrypt : function (gcjLat, gcjLon) {
	        var x = gcjLon, y = gcjLat;  
	        var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * this.x_pi);  
	        var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * this.x_pi);  
	        bdLon = z * Math.cos(theta) + 0.0065;  
	        bdLat = z * Math.sin(theta) + 0.006; 
	        return {'lat' : bdLat,'lon' : bdLon};
	    },
	    //BD-09 to GCJ-02
	    bd_decrypt : function (bdLat, bdLon) {
	        var x = bdLon - 0.0065, y = bdLat - 0.006;  
	        var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * this.x_pi);  
	        var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * this.x_pi);  
	        var gcjLon = z * Math.cos(theta);  
	        var gcjLat = z * Math.sin(theta);
	        return {'lat' : gcjLat, 'lon' : gcjLon};
	    },
	    //WGS-84 to Web mercator
	    //mercatorLat -> y mercatorLon -> x
	    mercator_encrypt : function(wgsLat, wgsLon) {
	        var x = wgsLon * 20037508.34 / 180.;
	        var y = Math.log(Math.tan((90. + wgsLat) * this.PI / 360.)) / (this.PI / 180.);
	        y = y * 20037508.34 / 180.;
	        return {'lat' : y, 'lon' : x};
	        /*
	        if ((Math.abs(wgsLon) > 180 || Math.abs(wgsLat) > 90))
	            return null;
	        var x = 6378137.0 * wgsLon * 0.017453292519943295;
	        var a = wgsLat * 0.017453292519943295;
	        var y = 3189068.5 * Math.log((1.0 + Math.sin(a)) / (1.0 - Math.sin(a)));
	        return {'lat' : y, 'lon' : x};
	        //*/
	    },
	    // Web mercator to WGS-84
	    // mercatorLat -> y mercatorLon -> x
	    mercator_decrypt : function(mercatorLat, mercatorLon) {
	        var x = mercatorLon / 20037508.34 * 180.;
	        var y = mercatorLat / 20037508.34 * 180.;
	        y = 180 / this.PI * (2 * Math.atan(Math.exp(y * this.PI / 180.)) - this.PI / 2);
	        return {'lat' : y, 'lon' : x};
	        /*
	        if (Math.abs(mercatorLon) < 180 && Math.abs(mercatorLat) < 90)
	            return null;
	        if ((Math.abs(mercatorLon) > 20037508.3427892) || (Math.abs(mercatorLat) > 20037508.3427892))
	            return null;
	        var a = mercatorLon / 6378137.0 * 57.295779513082323;
	        var x = a - (Math.floor(((a + 180.0) / 360.0)) * 360.0);
	        var y = (1.5707963267948966 - (2.0 * Math.atan(Math.exp((-1.0 * mercatorLat) / 6378137.0)))) * 57.295779513082323;
	        return {'lat' : y, 'lon' : x};
	        //*/
	    },
	    // two point's distance
	    distance : function (latA, lonA, latB, lonB) {
	        var earthR = 6371000.;
	        var x = Math.cos(latA * this.PI / 180.) * Math.cos(latB * this.PI / 180.) * Math.cos((lonA - lonB) * this.PI / 180);
	        var y = Math.sin(latA * this.PI / 180.) * Math.sin(latB * this.PI / 180.);
	        var s = x + y;
	        if (s > 1) s = 1;
	        if (s < -1) s = -1;
	        var alpha = Math.acos(s);
	        var distance = alpha * earthR;
	        return distance;
	    },
	    outOfChina : function (lat, lon) {
	        if (lon < 72.004 || lon > 137.8347)
	            return true;
	        if (lat < 0.8293 || lat > 55.8271)
	            return true;
	        return false;
	    },
	    transformLat : function (x, y) {
	        var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
	        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
	        ret += (20.0 * Math.sin(y * this.PI) + 40.0 * Math.sin(y / 3.0 * this.PI)) * 2.0 / 3.0;
	        ret += (160.0 * Math.sin(y / 12.0 * this.PI) + 320 * Math.sin(y * this.PI / 30.0)) * 2.0 / 3.0;
	        return ret;
	    },
	    transformLon : function (x, y) {
	        var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
	        ret += (20.0 * Math.sin(6.0 * x * this.PI) + 20.0 * Math.sin(2.0 * x * this.PI)) * 2.0 / 3.0;
	        ret += (20.0 * Math.sin(x * this.PI) + 40.0 * Math.sin(x / 3.0 * this.PI)) * 2.0 / 3.0;
	        ret += (150.0 * Math.sin(x / 12.0 * this.PI) + 300.0 * Math.sin(x / 30.0 * this.PI)) * 2.0 / 3.0;
	        return ret;
	    }
	};
var TQ = TQ||{};
function initGisTypeTool(option){
	$("#map").TqMap("addGisTypeControl",$.extend({
		gisType:"2D",
		showType:["2D","3D","MS"],
		changeBeforFunc:function(){
			var that = this;
			IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
			IS_GISTYPE_CHANGE_END = false;
		},
		changingFunc:function(){
			var that = this;
			var currOrgId = $("#currOrgId").val();
			var imap = $("#map").TqMap("get","map");
			var targitGisType = that.gisType;
			bindInWfs = false;
			isTransformat = false;
			gisType = that.gisType;
			if(!isGridOrganization()){//加载下辖机构地图区域
				loadSubordinateMapArea(currOrgId);
		    }
			if(typeof $("#tabList").data("reloadTabList") == "function"){
				$("#tabList").data("reloadTabList")();
			}
			$("#map").TqMap("deactivateSelectFeature",{sfname:"wfsSelectF"});
			if(gisType=="3D"){
				bindInWfs = true;
				isTransformat = false;
				$("#map").TqMap( "activateSelectFeature",{sfname:"wfsSelectF"});
			}
			if(TQMap.isTransformat==true){
				var lonlat =  imap.getCenter();
				if(gisType=="3D" && targitGisType!="3D"){
					lonlat = TQTransformat.to2DPoint(lonlat);
				}else if(gisType!="3D" && targitGisType=="3D"){
					lonlat = TQTransformat.to25DPoint(lonlat);
				}
				$("#map").TqMap("setCenter",{lon:lonlat.lon,lat:lonlat.lat,zoom:imap.zoom});
			}else{
				/* var layerData=getGis2DLayerDataByOrgId($("#currOrgId").val());
				if(layerData!=null && layerData!=""){
					$("#map").TqMap("setCenter",{lon:layerData.lon,lat:layerData.lat,zoom:layerData.zoom});
				}else{
					$.messageBox({message:"当前部门还未划分区域!",level:"error"});
				} */
			}
		},
		changeAfterFunc:function(){
			IS_GISTYPE_CHANGE_END = true;
		}
	},option))
}
$(function(){
	loadLayerInfo();
	//加载图层信息
	function loadLayerInfo(){
		$.ajax({
			async:false,
			url:"${path}/gis/twoDimensionMapModuleManage/findModule.action",
			success:function(responseData){
				$(".dataMsg").empty()
				var strA="";
				var strC="";
				var bussinss_layer="<s:property value='@com.tianque.openLayersMap.util.LayerChoose@BUSINESS_LAYER'/>";
				var base_layer="<s:property value='@com.tianque.openLayersMap.util.LayerChoose@BASE_LAYER'/>";
				for(var i=0;i<responseData.length;i++){//拼装数据
					if(responseData[i].isBusinessLayer==bussinss_layer){
						var strB="";
						if(responseData[i].isHasSonClass){
							if(responseData[i].modeType!="keyPlaceMap"){
								for(var j=0;j<responseData[i].gisTypeManageList.length;j++){
									strB+="<li><span class='text' id='"+responseData[i].gisTypeManageList[j].tableName+"' name='"+responseData[i].modeType+"'>"
									+"<label class='place' id='"+responseData[i].gisTypeManageList[j].key+"MarkerLayer'>"+responseData[i].gisTypeManageList[j].name+"</label></span></li>";
								}
							}else{
								for(var j=0;j<responseData[i].gisTypeManageList.length;j++){
									strB+="<li><span class='text' id='"+responseData[i].gisTypeManageList[j].key+"' name='"+responseData[i].modeType+"'>"
									+"<label class='place' id='"+responseData[i].gisTypeManageList[j].key+"MarkerLayer'>"+responseData[i].gisTypeManageList[j].name+"</label></span></li>";
								}
							}
						}
						if(strB!=""){
							strB = "<ul class='dataMsg dataMsgT  hidden clearfix'>"+strB+"</ul>";
						}
						strA+="<li><span><label name='"+responseData[i].tableName+"'class='"+responseData[i].modeType+"'  id='"+responseData[i].tableName+"'>"+responseData[i].moduleName+"<span class='gradeMark'></span></label></span>"+strB+"</li>"
					}else if(responseData[i].isBusinessLayer==base_layer&&responseData[i].isPopulation==0){//0表示不是常住人口
						strC+="<a href='javaScript:void(0);' class=''><label title='"+responseData[i].moduleName+"'><strong class=''></strong><input type='checkbox' name='1' class='"+responseData[i].modeType+"'  id='"+responseData[i].tableName+"' />"+' '+responseData[i].moduleName+"</lable></a>";
					}
				}
				$(".dataMsg").append(strA);
				$(".boxShadeContent").html(strC);
			}
		});
	}

	//业务图层子项点击事件
	$("#TwoMapView").delegate(".dataMsgT li","click",function(){
		clearBoundCommonInfo();
		clearGpsMapAllMarker();
		if(!IS_MAP_MOVE_SEARCH){
			$("#map").TqMap("deactivateSelectFeature",{sfname: "selectF"});//不激活自定义的Feature图层
			$("#map").TqMap("destroySelectFeature");//销毁自定义的SelectFeature
		}
		IS_MAP_MOVE_SEARCH=true;
		var _self=$(this);
		var _parent=$(this).parents("li");
		var mainTableName=_parent.children("span").find("label").attr("name");
		/* if(_parent.children("span").find("label").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@TWONEWGROUP'/>" 
			|| _parent.children("span").find("label").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@NEW_COMPANY_PLACE'/>"
				|| _parent.children("span").find("label").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@NEW_UNIT_PLACE'/>"
				|| _parent.children("span").find("label").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@KEY_COMPANY_PLACE'/>"
				|| _parent.children("span").find("label").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@ENTERPRISE_PLACE'/>"
					||_parent.children("span").find("label").attr("id")=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@SCENICS_MANAGE'/>"){//如果是twoNewGroup或enterprise或scenicsManage，tableName为keyPalces
			mainTableName = "<s:property value='@com.tianque.openLayersMap.util.GisGlobalValueMap@PLACE_MODE' />";
		} */
		if(mainTableName.toLowerCase()!="<s:property value='@com.tianque.openLayersMap.util.BigModeType@REDCUFFTEAMMEMBER'/>".toLowerCase()){
			$("#redCuffTeamMember").data("haveChoose",false);
		}
		var modeType=_parent.children("span").find("label").attr("class");
		var type=_self.find("span").attr("id");
		allTypeName=_self.find("label").text();   
		var functionType = "<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH' />"; 
		$(".gis_zoom_button").addClass("hidden");//清除图例按钮
		$(".gis_zoom_content").hide();//清除图例内容
		clearMarkersByMarkerId_objectName("common");
		var tempModeType=modeType+"SearchService";//查询modeType
		var modeTypeDetailView=modeType+"DetailViewService";//详情查看modeType
		if(_self.hasClass("currentPosClick")){
			if(!_self.hasClass("canRemoveCurrentPosClick")){
				return false;
			}
			$("#updownlistContent li").eq(0).click();
			clearDistributionFeatureAndPopupText();//清除辖区分布统计的面
			clearDetailsListInfo();//清空详情列表的内容
			if($("#listModeType").val()==tempModeType || tempModeType==null){
				$(".plaint").hide();//隐藏清除图标
				$(".detailsTotal").html(0);
				$(".detailsList").nextAll().remove();
				$(".pagination").empty();
			}	
			_self.removeClass("currentPosClick");
			_self.removeClass("canRemoveCurrentPosClick");
			_parent.removeClass("currentPosClick");
			_parent.removeClass("canRemoveCurrentPosClick");
			if($(":checkbox[name=1]#gis2dlayers").attr("checked")!="checked"||isTownDownOrganization()){
				clearFeaturesByMarkerId_objectName("gridLayer");//如果网格层不勾选或乡镇级别以上则删除相应热区
			}
		}else{
			var tablistUrl = "${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?type="+type+"&functionType="+functionType;
			if(isTownDownOrganization()&& mainTableName!="issues"){
				modeType+="StatisticService";
				url='${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
				tablistUrl += "&url="+url+"&modeType="+modeType;
			}else{
				modeType+="SearchService";
				url='${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
				tablistUrl += "&url="+url+"&modeType="+modeType+"&mainTableName="+mainTableName+"&modeTypeDetailView="+modeTypeDetailView
				if(modeType=="keyPlaceMapSearchService"){
					tablistUrl += "&childTableName="+"keyPlaces"+"&keyType="+type;	
				}else if(modeType=="issueMapSearchService"){
					tablistUrl += "&isSerachMode=true"+"&childTableName="+type+"&keyType=null";
				}else{
					tablistUrl += "&childTableName="+type+"&keyType=null";
				}
			}
			loadTabListInfo(tablistUrl);
			$("#updownlistContent li[value='"+mainTableName+"']").click();
			$("#detailClick").attr("value",false);
			_self.addClass("currentPosClick").siblings().removeClass("currentPosClick");
			_parent.addClass("currentPosClick");
			_parent.siblings().removeClass("currentPosClick");
			_parent.siblings().find("ul>li").removeClass("currentPosClick");
			$(".mapChangeToolsArea .LayerDisplay>li").removeClass("currentPosClick");
			
		}	
		return false;  //解决冒泡
	})
	
	
	//业务图层父项点击事件
	$("#TwoMapView").delegate("#showPersonLayer>ul>li","click",function(){	
		clearBoundCommonInfo();
		if(!IS_MAP_MOVE_SEARCH){
			$("#map").TqMap("deactivateSelectFeature",{sfname: "selectF"});//不激活自定义的Feature图层
			$("#map").TqMap("destroySelectFeature");//销毁自定义的SelectFeature
		}
		
		IS_MAP_MOVE_SEARCH=true;
		var _self=$(this);
		var tableName=_self.find("label").attr("name");
		var type=_self.find("span").attr("id");
		if(tableName.toLowerCase()!="<s:property value='@com.tianque.openLayersMap.util.BigModeType@REDCUFFTEAMMEMBER'/>".toLowerCase()){
			$("#redCuffTeamMember").data("haveChoose",false);
		}
		if(tableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@ACTUALCOMPANY'/>"){
			type = "<s:property value='@com.tianque.gis.util.GisGlobalValue@ACTUALUNIT'/>";
		}
		var modeType=_self.find("label").attr("class");
		var tableId=_self.find("label").attr("id");
		allTypeName=_self.find("label").text();   //问题 
		var functionType = "<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH' />"; 
		var tempModeType=null;
		var modeTypeDetailView=modeType+"DetailViewService";//详情查看modeType
		if(tableName!="deviceInformation" || isTownDownOrganization()) clearGpsMapAllMarker();
		$(".gis_zoom_button").addClass("hidden");//清除图例按钮
		$(".gis_zoom_content").hide();//清除图例内容
		if(_self.children("ul")[0]){
			
		}else{
			if(modeType!="null"){
				tempModeType=modeType+"SearchService";
			}
			clearMarkersByMarkerId_objectName("common");
			if(_self.hasClass("currentPosClick")){
				if(!_self.hasClass("canRemoveCurrentPosClick")){
					return false;
				}
				$("#updownlistContent li").eq(0).click();
				_self.find('label').removeData();//id在label上，所以根据id放的数据是在label上，这里的元素是li
				_self.removeClass("currentPosClick");
				_self.removeClass("canRemoveCurrentPosClick");
				clearDistributionFeatureAndPopupText();//清除辖区分布统计的面
				clearDetailsListInfo();//清空详情列表的内容
				if($("#listModeType").val()==tempModeType || tempModeType==null){
					$(".plaint").hide();//隐藏清除图标
					$(".detailsTotal").html(0);
					$(".detailsList").nextAll().remove();
					$(".pagination").empty();
				}
				if($(":checkbox[name=1]#gis2dlayers").attr("checked")!="checked"||isTownDownOrganization()){
					clearFeaturesByMarkerId_objectName("gridLayer");//如果网格层不勾选或乡镇级别以上则删除相应热区
				}
			}else{
				var sordField=(modeType=="issueMap")?"iu.centerlon":"id";
				_self.addClass("currentPosClick").siblings().removeClass("currentPosClick");
				$(".dataMsgT li").removeClass("currentPosClick");
				$("#updownlistContent li[value='"+tableId+"']").click();
				$("#detailClick").attr("value",false);
				if(isTownDownOrganization()&&tableName!="redCuffTeamMember"){
					modeType+="StatisticService";
					if(modeType=="commonMapStatisticService"){	//通用大类别url
						url='${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgId.action';
					}else{	
						url='${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
					}
				}else{
					modeType+="SearchService";
					url='${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
				}
				if(tableName=="dustbin"){
					$("#dustbinSelectDialog").createDialog({
						width:300,
						height:170,
						title:"部件类型",
						zIndex:1007,
						url:"${path}/openLayersMap/gisToolbar/dustbinSelect.jsp",
						buttons: {
							"确定" : function(event){
								type=$("#partType").val();
								var childTableName=$("#partNameId").val();
								$("#partTypeCache").val(type);
								$("#partNameCache").val(childTableName);
								$(this).dialog("close");
								loadTabListInfo("${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+tableName+"&type="+type+"&childTableName="+childTableName+"&modeType="+modeType+"&functionType="+functionType+"&modeTypeDetailView="+modeTypeDetailView);
						   },
						   "取消" : function(){
							   $("#dustbin").closest("li").removeAttr("class").addClass("currentDefault");
							   $("#updownlistContent li").eq(0).click();
						        $(this).dialog("close");
						   }
						}
					});
					return;
				}else if(tableName.toLowerCase()=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@REDCUFFTEAMMEMBER'/>".toLowerCase()){
					if($("#redCuffTeamMember").data("haveChoose")){
						// 滑动刷新是触发点击事件
						TQ.loadRedCuffTeam();
						return;
					}
					$("#redCuffTeamMemberSelectDialog").createDialog({
						width:300,
						height:170,
						title:"红袖套成员",
						zIndex:1007,
						url:"${path}/openLayersMap/gisToolbar/redCuffTeamMemberSelect.jsp",
						buttons: {
							"确定" : function(event){
								var redCuffTeamMemberTeamType=$("#redCuffTeamMemberTeamType").val();
								var redCuffTeamMemberSubTeamType=$("#redCuffTeamMemberSubTeamType").val();
								if($("#redCuffTeamMemberTeamType option:selected").text()=='请选择'){
									$.messageBox({
										message : "请选择队伍类别",
										level : "error"
									});
									return;
								}
								$(this).dialog("close");
								$("#redCuffTeamMember").data("haveChoose",true).data("redCuffTeamMemberTeamType",redCuffTeamMemberTeamType).data("redCuffTeamMemberSubTeamType",redCuffTeamMemberSubTeamType);
								$("#redCuffTeamMember").closest("li").addClass('canRemoveCurrentPosClick');
								var loadUrl = "${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+tableName+"&modeType="+modeType+"&functionType="+functionType+"&modeTypeDetailView="+modeTypeDetailView+"&type="+type+"&redCuffTeamMemberTeamType="+redCuffTeamMemberTeamType+"&redCuffTeamMemberSubTeamType="+redCuffTeamMemberSubTeamType;
								loadTabListInfo(loadUrl);
								TQ.loadRedCuffTeam = function(){loadTabListInfo(loadUrl)};
						   },
						   "取消" : function(){
							   $("#redCuffTeamMember").closest("li").removeClass().addClass("currentDefault");
							   $("#updownlistContent li").eq(0).click();
						        $(this).dialog("close");
						   }
						}
					});
					return;
				}
				loadTabListInfo("${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+tableName+"&modeType="+modeType+"&functionType="+functionType+"&modeTypeDetailView="+modeTypeDetailView+"&type="+type);
			}
		}
	});
	
	layersToolsSet();
	function layersToolsSet(){
		function layerTools(){
			var LayerTimer1;
			var LayerTimer2;
			$(".personalLayerTools").hover(function(event){
				$(".showPersonalTools").hide();
				clearTimeout(LayerTimer1);
				$(this).find(".showPersonLayer").stop(true).show();
				$(this).addClass("curHoverEffect").parent()
					.siblings().children().removeClass("curHoverEffect");
			},function(event){
				var that=this;
				
				LayerTimer1=setTimeout(function(){
					$(that).find(".showPersonLayer").hide();
				},600);
			});

			$(".personalTools").hover(function(event){
				$(".showPersonLayer").hide();
				clearTimeout(LayerTimer2);
				$(this).find(".showPersonalTools").stop(true).show();
				$(this).addClass("curHoverEffect").parent()
					.siblings().children().removeClass("curHoverEffect");
			},function(event){
				var that=this;
				LayerTimer2=setTimeout(function(){
					$(that).find(".showPersonalTools").hide();
				},100);
			});
		}
		layerTools();
		
		$(".PersonalToolsArea li").hover(function(){
			$(this).addClass("currentPosHover");
		},function(){
			$(this).removeClass("currentPosHover");
		})
		
		$(".personalLayer").MultiLevelMenu({});
	}
	//基础图层点击事件
	$(".boxShadeC").delegate(":checkbox[name=1]","click",function(){
		if(this.checked && this.id!="hourseInfo" && $(":checkbox[name=1]#hourseInfo").attr("checked")){
			$(":checkbox[name=1]#hourseInfo").attr("checked",false);
			baseLayerCheckBoxClickFunc($(":checkbox[name=1]#hourseInfo")[0]);
		}else if(this.checked && this.id!="gis2dlayers" && $(":checkbox[name=1]#gis2dlayers").attr("checked")){
			$(":checkbox[name=1]#gis2dlayers").attr("checked",false);
			baseLayerCheckBoxClickFunc($(":checkbox[name=1]#gis2dlayers")[0]);
		}
		baseLayerCheckBoxClickFunc(this);
	})
	function baseLayerCheckBoxClickFunc(node){
		var url="";
		var mainTableName=$(node).attr("id");
		var modeType=$(node).attr("class");
		allTypeName=$(node).parent("label").attr("title");	//问题
		var functionType = "<s:property value='@com.tianque.openLayersMap.util.FunctionType@REFINESEARCH' />"; 

		$(".gis_zoom_button").addClass("hidden");//清除图例按钮
		$(".gis_zoom_content").hide();//清除图例内容
		$("#detailClick").attr("value",false);
		if(!IS_MAP_MOVE_SEARCH){
			$("#map").TqMap("deactivateSelectFeature",{sfname: "selectF"});//不激活自定义的Feature图层
			$("#map").TqMap("destroySelectFeature");//销毁自定义的SelectFeature
		}
		var tempModeType=modeType+"SearchService";
		if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@HOURSEINFO'/>"){
			clearMarkersByMarkerId_objectName("hourse");
			if(!$(node).attr("checked")){
				if(isTownDownOrganization()){
					clearFeaturesAndPopupText();
					$(".tabChange").hide();//清空详情列表的内容
				}
				$("#addHourseInfo").hide();
				$("#deleteHourseInfo").hide();
				clearMarkersByMarkerId_objectName("hourse");
				$("#map").TqMap("deleteAllPopupText");//清空popupText
				if($("#listModeType").val()==tempModeType || tempModeType==null){
					$(".plaint").hide();//隐藏清除图标
					$(".detailsTotal").html(0);
					$(".detailsList").nextAll().remove();
					$(".pagination").empty();
				}
				$(".distinguishLine .buildingMarks").addClass("minusBorder").removeClass("plusBorder");
				destroyAndDeactivateWfs();
			}else{
				$(".distinguishLine .buildingMarks").addClass("plusBorder").removeClass("minusBorder");
				$('#construction').show();
				$("#addHourseInfo").show();
				$("#deleteHourseInfo").show();
				if(bindInWfs){
					loadAndActivateWfs();
				}
			}
		}
		if(mainTableName=="<s:property value='@com.tianque.openLayersMap.util.BigModeType@GRIDLAYER'/>"){
			clearFeaturesByMarkerId_objectName("gridLayer");
			if(!$(node).attr("checked")){
				clearFeaturesAndPopupText();
				$(".tabChange").hide();//清空详情列表的内容
				if($("#listModeType").val()==tempModeType || tempModeType==null){
					$(".plaint").hide();//隐藏清除图标
					$(".detailsTotal").html(0);
					$(".detailsList").nextAll().remove();
					$(".pagination").empty();
				}
			}
		}
		if($(node).attr("checked")){
			if(isTownDownOrganization()){
				modeType+="StatisticService";
				url='${path}/gis/twoDimensionMapStatisticCommonManage/statisticTwoDimensionMapInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
			}else{
				modeType+="SearchService";
				url='${path}/gis/twoDimensionMapSearchCommonManage/findTwoDimensionMapPageInfoByOrgIdAndScreenCoordinateVoAndTypeName.action';
			}
			IS_MAP_MOVE_SEARCH=true;//地图滑动搜索开启
			$("#tabList").load("${path}/openLayersMap/detailsCommon/detailsListCommonTwoDimensionMap.jsp?url="+url+"&mainTableName="+mainTableName+"&modeType="+modeType+"&functionType="+functionType);
		}
	}
	
	function showPersonLayer(){
		var LayerTimer;
		$(".personalLayer").hover(function(){
				clearTimeout(LayerTimer);
				$(".showPersonLayer").show();
		},function(){
			LayerTimer=setTimeout(function(){
				$(".showPersonLayer").hide();
			},500);
		});
	}
	showPersonLayer();

	$(".personalLayer").MultiLevelMenu({//图层选择效果
	});
})
</script>