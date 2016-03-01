<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %> 
<jsp:include page="/includes/baseInclude.jsp" />
<script type="text/javascript" src="${resource_path}/resource/openLayersMap/360vista/swfobject.js"></script>
<div id="flashContent"></div>

<script type="text/javascript">
$(function(){
		var panoApp = null ;
		initvista();//初始化
		function initvista(){
			var swfVersionStr = "10.0.0";
	        var xiSwfUrlStr = "/resource/openLayersMap/360vista/playerProductInstall.swf";
	        var flashvars = {};
	        var lon = '<s:property value="#parameters.lon"/>'*1000000;
	        var lat = '<s:property value="#parameters.lat"/>'*1000000;
	        
			flashvars.initLon = lon;
			flashvars.initLat = lat;
			
	        flashvars.panoPosChangeFunc = "panoPosChange";
	        flashvars.panoDirectChangeFunc = "panoDirectChange";
			flashvars.panoServerURL = "http://10.0.66.70:6005/PanoService/";
		
	           var params = {};
	           params.quality = "high";
	           params.bgcolor = "#ffffff";
	           params.allowscriptaccess = "sameDomain";
	           params.allowfullscreen = "true";
	           var attributes = {};
	           attributes.id = "ShowPanoApp";
	           attributes.name = "ShowPanoApp";
	           attributes.align = "middle";
	           swfobject.embedSWF(
	               "/resource/openLayersMap/360vista/ShowPanoApp.swf", "flashContent", 
	               "820", "450", 
	               swfVersionStr, xiSwfUrlStr, 
	               flashvars, params, attributes);
	           swfobject.createCSS("#flashContent", "display:block;text-align:left;");
			panoApp = swfobject.getObjectById("ShowPanoApp"); 
		}
})
		//街景之间跳转后回调接口，二维地图中心点移动
		function panoPosChange( lon , lat ){
	      	//alert("panoPosChange : " + lon  + "  " + lat );
	    }
	      	
		//街景内视点方向改变后回调接口
	   	function panoDirectChange( dir ){
	   		//alert( "panoDirectChange : " + dir ) ;
	   	}
		
		function movetoPano(){ 
			if( panoApp == null ){
				panoApp = swfobject.getObjectById("ShowPanoApp"); 
			}
			if( panoApp != null ){
				//panoApp.SPanoMoveTo(116394413,39993413);//116.394413,39.993413
				panoApp.SPanoMoveTo(parseFloat(x*1000000).toFixed(0),parseFloat(y*1000000).toFixed(0));
			}
		}
</script>
