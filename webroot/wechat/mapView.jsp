<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />
<input type="hidden" id="lat" value="<%=request.getParameter("lat") %>"/>
<input type="hidden" id="lng" value="<%=request.getParameter("lng") %>"/>
<input type="hidden" id="title" value="<%=request.getParameter("title") %>"/>
<div id="map" style="float: right; width: 100%; height: 90%;"></div>
<p>&nbsp</p>
<span style="color:red">注：此地图所显示的位置可能与实际位置有所偏差！</span>
<script type="text/javascript">
var GPS = {
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
//document.write("GPS: 39.933676862706776,116.35608315379092<br />");
//var arr2 = GPS.gcj_encrypt(39.933676862706776, 116.35608315379092);
//document.write("中国:" + arr2['lat']+","+arr2['lon']+'<br />');
//var arr3 = GPS.gcj_decrypt_exact(arr2['lat'], arr2['lon']);
//document.write('逆算:' + arr3['lat']+","+arr3['lon']+' 需要和第一行相似（目前是小数点后9位相等）');

var map = null;
var arr3=null;
$(function() {
	arr3= GPS.gcj_decrypt_exact(parseFloat($("#lat").val()),parseFloat($("#lng").val()));
	initMap();
	function initMap() {
		//$("#map").height($("#contentDiv").height() - $("#nav").height() - 51);
		map = new TQMap({
			mapId:"map",
//			layerPath3D: GisProperties.LAYERPATH_3D,                   //三维地图配置
//			layerLayers3D: GisProperties.LAYERLAYERS_3D,
//			layerPath2D: GisProperties.LAYERPATH_2D,                   //2维地图配置
//			resolutions2D: GisProperties.RESOLUTIONS_2D,
//			layerPathMS: GisProperties.LAYERPATH_MS,                    //卫星图(Manmade Satellite)配置
//			layerLayersMS: GisProperties.LAYERLAYERS_MS,
//			resolutionsMS: GisProperties.RESOLUTIONS_MS, 
			isViewPanZoomBar :false,	
			gisType:"2D"
		});//加载地图
	}                   
	map.setCenter({ lon:arr3['lon'],lat:arr3['lat'],zoom:8});
})
	map.addMarker({
		iconUrl:getIconUrl("${path}/resource/openLayersMap/images/greenBubble"),
		markerW:26,
		markerH:30,
		lon:arr3['lon'],
		lat:arr3['lat'],
		isOnMouseOverAndOut:true,
		data:{
			title:$("#title").val()
		}
	});
</script>