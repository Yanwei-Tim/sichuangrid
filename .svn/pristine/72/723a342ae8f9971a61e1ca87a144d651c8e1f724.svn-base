OpenLayers.Layer.SuperMapCloud = OpenLayers.Class(OpenLayers.Layer.ArcGISCache,{
    initialize: function (name, url, options) {
        OpenLayers.Layer.ArcGISCache.prototype.initialize.apply(this, [name, url, options]);
    },
    getURL: function (bounds) {
    	OpenLayers.Layer.ArcGISCache.prototype.getURL.apply(this,  arguments);
    	var zoom = this.getServerZoom();
        // 获得当前分辨率
    	//var res = this.getResolution();
    	var res = this.getResolutionForZoom(zoom);
        var originTileX = (this.tileOrigin.lon + (res * this.tileSize.w / 2));
        var originTileY = (this.tileOrigin.lat - (res * this.tileSize.h / 2));
        // 获取地图中心点坐标
        var center = bounds.getCenterLonLat();
        var point = {
            x: center.lon,
            y: center.lat
        };
        var x = (Math.round(Math.abs((center.lon - originTileX)
				/ (res * this.tileSize.w))));
        var y = (Math.round(Math.abs((originTileY - center.lat)
				/ (res * this.tileSize.h))));
        //var z = this.map.getZoom();
        var z = zoom;
        
        if (this.lods) {
            var lod = this.lods[this.getServerZoom()];
            if ((x < lod.startTileCol || x > lod.endTileCol)
					|| (y < lod.startTileRow || y > lod.endTileRow)) {
                return null;
            }
        } else {
            var start = this.getUpperLeftTileCoord(res);
            var end = this.getLowerRightTileCoord(res);
            if ((x < start.x || x >= end.x)
					|| (y < start.y || y >= end.y)) {
                return null;
            }
        }
        
      //眉山的3D地图，使用了新的URL规则
        if(this.name=='MS3DLayer'  ){
        	//console.log(this.url.substr(0,this.url.length)+ "x=" + x + "/y=" + y + "/z=" + z);
        	 if(this.url.indexOf("?") == this.url.length -1){
        		 return this.url.substr(0,this.url.length)+ "x=" + x + "/y=" + y + "/z=" + z;
             }else{
            	 return this.url+ "x=" + x + "/y=" + y + "/z=" + z;
             }
            //http://192.168.1.127:8698/FileService/image/sucimap/x=13144/y=3097/z=14;
        }
        //var path = "http://42.120.49.194:8698/FileService/image?&x=" + x + "&y=" + y + "&z=" + z;
        if(this.url.indexOf("?")<0){
        	this.url = this.url+"?";
        }
        return this.url+ "&x=" + x + "&y=" + y + "&z=" + z;
    },
    clone:function(obj){
    	 if (obj == null) { 
             obj = new OpenLayers.Layer.SuperMapCloud(this.name, this.url, this.options);
         }
         return OpenLayers.Layer.ArcGISCache.prototype.clone.apply(this, [obj]);
    }
});


var SuperMapCloudconfig = {
	    proj: 'EPSG:4326',
	    mapResolutions: [1.40625, 
	    0.703125, 
	    0.3515625, 
	    0.17578125, 
	    0.087890625, 
	    0.0439453125,
	    0.02197265625, 
	    0.010986328125, 
	    0.0054931640625, 
	    0.00274658203125,
	    0.001373291015625, 
	    0.0006866455078125, 
	    0.00034332275390625,
	    0.000171661376953125, 
	    0.0000858306884765625, 
	    0.00004291534423828125,
	    0.00002145767211914062, 
	    0.00001072883605957031,
	    0.00000536441802978515],
	    tileSize: [256, 256],
	    TileOrigin: [-180, 90],
	    mapExtent: [-180, -90, 180, 90],
	    url1: 'http://42.120.49.194:8698/FileService/image?',
	    url2: 'http://42.120.49.194:8070/FileService/image?'

	};
