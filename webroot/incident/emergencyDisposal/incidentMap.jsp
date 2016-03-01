<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/gis3D/includes/popCssInclude.jsp"%>
<div class="gisContainer" id="gisContainer">
		 <div class="ui-corner-all" id="nav">
			<a id="roundSubmit" href="javascript:void(0)"><span><strong
					class="ui-ico-xz"></strong> 确定范围  </span></a>
		</div>
		<div id="EdushiMap"></div>
</div>
<script type="text/javascript">

function initMap(){
	if(typeof vEdushiMap =='undefined' || typeof vEdushiMap.Body.NewMapLayer !='function'){
		setTimeout("initMap()",50);
        return;
    } 
    var  mapView = new TQMap.Map({tbeginLevel:3});
    TQMap.EMap = vEdushiMap;
	TQMap.EMap.Body.flgContextMenu = false;
	mapView.vEdushiMap = vEdushiMap;
	
	var firstX,firstY;
	var parentLayerManage = new TQMap.Layer({id:'line_current_layer',z:10000});
	var polygon;
    var mousePointX;
	var mousePointY;
	
	$("#roundSubmit").click(function(){
		$(this).html("<span><strong class='ui-ico-xz'></strong> 重新确定位置</span>");
		var firstStart=true;
		if(polygon ){
			    polygon.destory();
			    firstX=null;
			    firstY=null;
			    polygon=null;
		}
		mapView.setOnMapClick(function(){
		
			if(polygon && firstStart){
		  
			   firstStart=false;
			   var minX ,maxX, minY,maxY;
			   minX =firstX<mousePointX?firstX:mousePointX;
			   minY =firstY<mousePointY?firstY:mousePointY;
			   maxX =firstX>mousePointX?firstX:mousePointX;
			   maxY =firstY>mousePointY?firstY:mousePointY;
			   var param= {"minOption.centerX":minX,"minOption.centerY":minY,"maxOption.centerX":maxX,"maxOption.centerY":maxY};
			   var urlParam ="minOption.centerX="+minX+"&minOption.centerY="+minY+"&maxOption.centerX="+maxX+"&maxOption.centerY="+maxY;
				$.ajax({
					url :'${path}/gis3D/buildingInfoManage/searchISRoundExistBuildingInfo.action',
					cache: false,
					data:param,
					success : function(data) {
						 if(data!='success'){
			   		    	   $.messageBox({message : "此周边范围没有被标注的信息",level : "error"});
			   		     }else{
			   			     $.ajax({
							       url :'${path}/gis3D/buildingInfoManage/searchRoundBuildingInfo.action',
							       cache:false,
						      	   data:param,
							       success : function(result1) {
					   				var popupLayer = new TQMap.Layer({id:"TQMap_Layer_POPUP",z:100});
					   				var middleX =(maxX+minX)/2-30;
					   				var localtionY=minY-20;
					   				var popup = new TQMap.Popup({
					   					x:middleX,
					   					y:localtionY,
					   					width:350,
					   					height:200,
					   					title:'数据统计',
					   					content:result1
					   				});
					   				popupLayer.addPopup(popup);
							  }
						   });
				   		 }
					}
				});
			}else {
				  firstX = mapView.PointerX();
				  firstY =  mapView.PointerY();
			}
		});

		 mapView.setOnMapMouseMove(function(){
					if(!firstX || !firstY){return; }
					if(!firstStart){return ;}
					
					 mousePointX= mapView.PointerX();
					 mousePointY= mapView.PointerY();
					 
					var points = firstX+','+firstY+','+mousePointX+','+firstY+','+mousePointX+','+mousePointY+','+firstX+','+mousePointY+','+firstX+','+firstY;
				
					if( polygon){
					
						polygon.destory();
					}
				
			    	polygon = new TQMap.Polygon({points:points,size:2});
			    	
					parentLayerManage.addPolygon(polygon);
					
				});
	});

}


$(function(){

	function style(){
		var mainHeight=document.body.clientHeight-150;
		$("#gisContainer").height(mainHeight);
	}
	function init(){
		var mapInitWidth=700;
		var mapInitHight=471;
		var mapUrl="<s:property value='@com.tianque.gis.util.GisGlobalValue@GIS_API_PATH'/>&L=zh-chs&MapID=EdushiMap&x=8268&y=9492&w="+mapInitWidth+"&h="+mapInitHight+"&eye=1&flgContextMenu=false&ew=190&eh=145&e=utf-8&v=1&z=1";
		$.getScript(mapUrl,function(){
		    initMap();
		})
	}
	style();
	init();
	$(window).resize(function() {
		style();
		//mapView.setMapWH($("#gisContainer").width(),$("#gisContainer").height());
	})
	//initLayers();
})	
	</script>
	