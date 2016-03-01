OpenLayers.Layer.XYZ.Modified = OpenLayers.Class(OpenLayers.Layer.XYZ, {
    /**
     * 由于一些切片是根据tile origin而非max extent来计算的.
     * 因此只需用tileOrigin替换maxExtent即可.
     * With gridded tile systems, sometimes the tile origin is far from the actual maxExtent of the data.
     * For example, when a "google-like" tiling scheme is used with local data.
     * This type of setup is also common with ESRI tile caches.
     */
    getXYZ: function(bounds) {
		var res = this.getServerResolution();
        var x = Math.round((bounds.left - this.tileOrigin.lon)
            / (res * this.tileSize.w));
        var y = Math.round((this.tileOrigin.lat - bounds.top)
            / (res * this.tileSize.h));
		var z=this.getServerZoom();
        return {'x': x, 'y': y, 'z': z};
    },

    /**
     * 在最新版本(trunk/openlayers)中, 已经有人提取了getURL如何获取x, y, z的逻辑,
     * 子类只需重写getXYZ即可, 这里只是将最新版的getURL照搬过来, 没有进行扩展.
     * 
     * TODO 因此当新版本中采用getXYZ后, 不需要重写此方法, 只需要重写getXYZ
     */
    getURL: function (bounds) {
        var xyz = this.getXYZ(bounds);
        var url = this.url;
        if (url instanceof Array) {
            var s = '' + xyz.x + xyz.y + xyz.z;
            url = this.selectUrl(s, url);
        }

        return OpenLayers.String.format(url, xyz);
    },

    /**
     * 在最新版本(trunk/openlayers)中, 已经有人修改了initGriddedTiles如何获取origin的逻辑.
     * 以前(2.10)是直接获得maxExtent, 再计算calculateGridLayout(bounds, extent, resolution),
     * 如今origin通过一个getTileOrigin来获取, 判断有tileOrigin则返回tileOrigin,
     * 无则根据maxExtent来计算出origin, 再计算calculateGridLayout(bounds, origin, resolution).
     * 
     * TODO 因此当新版本中采用getTileOrigin后, 不需要重写此方法
     */
    calculateGridLayout: function(bounds, origin, resolution) {
		var tilelon = resolution * this.tileSize.w;
        var tilelat = resolution * this.tileSize.h;
        
        var offsetlon = bounds.left - origin.lon;
        var tilecol = Math.floor(offsetlon/tilelon) - this.buffer;
        
        var rowSign = this.rowSign;

        var offsetlat = rowSign * (origin.lat - bounds.top + tilelat);  
        var tilerow = Math[~rowSign ? 'floor' : 'ceil'](offsetlat/tilelat) - this.buffer * rowSign;
        
        return { 
          tilelon: tilelon, tilelat: tilelat,
          startcol: tilecol, startrow: tilerow
        };
    },

    CLASS_NAME: "OpenLayers.Layer.XYZ.Modified"
});
