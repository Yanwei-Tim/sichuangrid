<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/edushiGis/information/gisInformation.jsp"/>
<jsp:include page="/edushiGis/common/pageInfo.jsp"/>
<style>
.map-ctt-top {
	font-size: 12px;
	color: #333;
	overflow:hidden;
}




</style>

<script type="text/javascript" src="${resource_path}/resource/edushiGis/js/tqMap/init.js"></script>
<script type="text/javascript" src="${resource_path}/resource/edushiGis/js/tqMap/constant.js"></script>
<script type="text/javascript" src="${resource_path}/resource/edushiGis/js/jscroll.js"></script>

<div class="map-ctt-top">
	<div id="edushimap">
		<div id="EdushiMap"></div>
	</div>
	
	<input id="mode" name="mode" type="hidden" value="<s:property value='#parameters.mode'/>"/>
</div>


<script>
var mapView;
var borderLine;
	$(function(){
		init();
		
	})

	function init(){
		var centerX=20000;
		var centerY=15000;
		var mapUrl='<s:property value="@com.tianque.gis.util.GisGlobalValue@GIS_API_PATH"/>'+"?City=ningbo&MapID=EdushiMap&w="+800+"&h="+480+"&z=0&e=utf-8&v=0";
		
		$.getScript(mapUrl,function(){
			 initMap();
			
		})
		
	}

	function initMap(){
		
		if(typeof vEdushiMap =='undefined' || typeof vEdushiMap.Body.NewMapLayer !='function'){
			setTimeout("initMap()",50);
	        return;
	    }
		
		mapView = new TQMap.Map();
		mapView.vEdushiMap = vEdushiMap;
		TQMap.EMap = mapView.vEdushiMap;
		mapView.addZoomBar("EdushiMap");
		
		if($("#mode").val()!='view'){
			mapView.vEdushiMap.onSpotClick = function(spot){onSpotClick(spot,mapView)};
		}
		
		if($("#centerx").val() != "" && $("#centery").val() !=""){
			addBuildMarker($("#centerx").val(),$("#centery").val());
		}
	}
	
	function addBuildMarker(centerx,centery){
		var imgurl ="${resource_path}/resource/edushiGis/js/tqMap/style/images/defaultMarker.gif";
		var icon = null;
		if(icon==null){
			icon = new TQMap.Icon(imgurl,{offset_x:10,offset_y:32});
		}
		var marker = new TQMap.Marker(new TQMap.Layer(999), icon, '');
		marker.addMarker(centerx,centery,false);
		
		mapView.moveTo(centerx,centery, true);
		
		
	}
	
	function onSpotClick(spot,map) {

		parent.saveBuild(spot);
		
		
	}

</script>