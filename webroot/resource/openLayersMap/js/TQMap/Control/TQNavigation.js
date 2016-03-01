/**
 * 鼠标放大缩小效果(含拖动效果)
 */
OpenLayers.Control.TQNavigation = OpenLayers.Class(OpenLayers.Control.Navigation, {
	path:"",
	isValidMapDefaultDblClick:true,
	isViewIcon:true,                      //鼠标中间键，滚动时是否显示图片特效
	dragPanOptions :{enableKinetic: true},//拖动地图时是否平滑移动
	uricon:null,
	ulicon:null,
	bricon:null,
	blicon:null,
	isCanZoomDown:function(){return true},
	initialize:function(options){
		var that = this;
		var size = new OpenLayers.Size(10,6);
		var offset = new OpenLayers.Pixel(-(size.w/2), -(size.h/2));
		that.uricon = new OpenLayers.Icon(that.path + "/resource/openLayersMap/images/ur.png",size,offset);
		that.ulicon = new OpenLayers.Icon(that.path + "/resource/openLayersMap/images/ul.png",size,offset);
		that.bricon = new OpenLayers.Icon(that.path + "/resource/openLayersMap/images/bl.png",size,offset);
		that.blicon = new OpenLayers.Icon(that.path + "/resource/openLayersMap/images/br.png",size,offset);
		OpenLayers.Control.Navigation.prototype.initialize.apply(this, [options]);
	},
	wheelUp: function(evt, delta) {
		var that=this;
		clearTimeout(window._wheel);
		window._wheel=setTimeout(function() {
			if(that.isViewIcon){
				var uricon = that.uricon;
				var ulicon = that.ulicon;
				var bricon = that.bricon;
				var blicon = that.blicon;
				var newZoom = that.map.getZoom();
				if (newZoom<that.map.getNumZoomLevels()-1) {
					var markers=new OpenLayers.Layer.Markers("Zoomin",{displayInLayerSwitcher: false});
					var x=evt.xy.x;
					var y=evt.xy.y;
					var marker1=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x+5,y+5)),bricon.clone());
					var marker2=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x-5,y+5)),blicon.clone());
					var marker3=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x+5,y-5)),uricon.clone());
					var marker4=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x-5,y-5)),ulicon.clone());
					marker1.map=that.map;
					marker2.map=that.map;
					marker3.map=that.map;
					marker4.map=that.map;
					markers.addMarker(marker1);
					markers.addMarker(marker2);
					markers.addMarker(marker3);
					markers.addMarker(marker4);
					that.map.addLayer(markers);
					var j=0;
					var t;
					var movemarker=function(){
						marker1.moveTo(new OpenLayers.Pixel(x+15*(j+2),y+10*(j+2)));
						marker2.moveTo(new OpenLayers.Pixel(x-15*(j+2),y+10*(j+2)));
						marker3.moveTo(new OpenLayers.Pixel(x+15*(j+2),y-10*(j+2)));
						marker4.moveTo(new OpenLayers.Pixel(x-15*(j+2),y-10*(j+2)));
						j++;
						if(j==4){
							that.map.removeLayer(markers);
							markers.clearMarkers();
							markers.destroy();
							window.clearInterval(t);
						}
					}
					if(document.all){
						t=window.setInterval(function(){movemarker()}, 150);
					}else{
						t=window.setInterval(function(){movemarker()}, 150);
					}
				}
			}
			OpenLayers.Control.Navigation.prototype.wheelUp.apply(that, [arguments,delta] );
		}, 300);
    },
    wheelDown: function(evt, delta) {
    	var that=this;
    	clearTimeout(window._wheel);
    	window._wheel=setTimeout(function() {
    		if(that.isViewIcon){
    			var uricon = that.uricon;
				var ulicon = that.ulicon;
				var bricon = that.bricon;
				var blicon = that.blicon;
				var newZoom = that.map.getZoom();
				if(!that.isCanZoomDown({nowZoom:newZoom-2})) return;
				if (newZoom>0) {
					var markers=new OpenLayers.Layer.Markers("Zoomout",{displayInLayerSwitcher: false});
					var x=evt.xy.x;
					var y=evt.xy.y;
					var marker1=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x+12*6,y+10*6)),ulicon.clone());
					var marker2=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x-12*6,y+10*6)),uricon.clone());
					var marker3=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x+12*6,y-10*6)),blicon.clone());
					var marker4=new OpenLayers.Marker(that.map.getLonLatFromPixel(new OpenLayers.Pixel(x-12*6,y-10*6)),bricon.clone());
					marker1.map=that.map;
					marker2.map=that.map;
					marker3.map=that.map;
					marker4.map=that.map;
					markers.addMarker(marker1);
					markers.addMarker(marker2);
					markers.addMarker(marker3);
					markers.addMarker(marker4);
					that.map.addLayer(markers);
					var j=1;
					var t;
					var movemarker=function(){
						marker1.moveTo(new OpenLayers.Pixel(x+12*5-12*(j),y+10*5-10*(j)));
						marker2.moveTo(new OpenLayers.Pixel(x-12*5+12*(j),y+10*5-10*(j)));
						marker3.moveTo(new OpenLayers.Pixel(x+12*5-12*(j),y-10*5+10*(j)));
						marker4.moveTo(new OpenLayers.Pixel(x-12*5+12*(j),y-10*5+10*(j)));
						j++;
						if(j==6){
							that.map.removeLayer(markers);
							markers.clearMarkers();
							markers.destroy();
							window.clearInterval(t);
						}
					}
					if(document.all){
						t=window.setInterval(function(){ movemarker()}, 150);
					}else{
						t=window.setInterval(function(){movemarker()}, 150);
					}
				}
    		}
			OpenLayers.Control.Navigation.prototype.wheelDown.apply(that, [arguments,delta] );
		}, 300);
	},
	defaultDblClick:function(evt){//地图双击事件重写
		if(this.isValidMapDefaultDblClick){
			OpenLayers.Control.Navigation.prototype.defaultDblClick.apply(this, arguments );
		}
	},
	CLASS_NAME: "OpenLayers.Control.TQNavigation"
});