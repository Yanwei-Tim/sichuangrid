OpenLayers.Layer.TQTileCache = OpenLayers.Class(OpenLayers.Layer.Grid, {
	layerTileSize:null,
	isBaseLayer: true,
    format: 'image/png',
    serverResolutions: null,
	initialize: function(name, url, layername, options) {
		this.isBaseLayer = false;
		this.buffer = 0;		
    	//OpenLayers.Layer.TileCache.prototype.initialize.apply(this,[name, url, layername, options]);
    	OpenLayers.Layer.Grid.prototype.initialize.apply(this,[name, url, {}, options]);
        this.layerTileSize = this.tileSize;
        if(this.isBaseLayer)
        	this.transitionEffect = 'resize';
    },    
    clone: function (obj) {
        if (obj == null) {
            obj = new OpenLayers.Layer.TQTileCache(this.name,
                                                 this.url,
                                                 this.layername,
                                                 this.getOptions());
        }
        obj = OpenLayers.Layer.Grid.prototype.clone.apply(this, [obj]);
        return obj;
    },    
	/*
	 * 根据地图范围获取图片地址
	 * 
	 */
	getURL:function(bounds){	    	    
		var res = this.map.getResolution();
		var size = this.tileSize;
        var tileX = Math.round((bounds.left - this.tileOrigin.lon) / (res * size.w ));
        var tileY = Math.round((this.tileOrigin.lat - bounds.top) / (res * size.h ));
        var tileZ = this.serverResolutions != null ?	        
            OpenLayers.Util.indexOf(this.serverResolutions, res) :
            this.map.getZoom();
        for(var i = 0; i < this.serverResolutions.length;i++){
        	var resolution1 = this.serverResolutions[i];
        	var ratio1 = resolution1 / res;
        	if(ratio1 <=1.5 && ratio1 >= 0.75){
        		tileZ = i;
        		break;
        	}
        }        
        return this.formatURL(tileX,tileY,tileZ);
	},
	/*
	 * 图片地址一般为 http://xxx/z/y/x.png
	 * z从0开始
	 * 如果是其他形式的地址，只需继承或改写本方法
	 * 
	 */
	formatURL:function(tileX,tileY,tileZ){			
	    var url= OpenLayers.String.format(this.url,{'x': tileX, 'y': tileY, 'z': tileZ});
		return url;	
	},
    /**
     * Method: addTile
     * Create a tile, initialize it, and add it to the layer div. 
     *
     * Parameters: 
     * bounds - {<OpenLayers.Bounds>} 
     * position - {<OpenLayers.Pixel>}
     *
     * Returns:
     * {<OpenLayers.Tile.Image>} The added <OpenLayers.Tile.Image>
     */
    addTile:function(bounds1, position,w,h) {
        var url = this.getURL(bounds1);
        return new OpenLayers.Tile.Image(this, position, bounds1, 
                                             url,new OpenLayers.Size(w,h));//new OpenLayers.Size(Math.floor(this.tileSize.w) ,Math.floor(this.tileSize.h )));
    },
    calculateGridLayout: function(bounds, extent, resolution) {
        var tilelon = resolution * this.tileSize.w ;
        var tilelat = resolution * this.tileSize.h ;
        
        var offsetlon = bounds.left - this.tileOrigin.lon;
        var tilecol = Math.floor(offsetlon/tilelon) - this.buffer;
        var tilecolremain = offsetlon/tilelon - tilecol;
        var tileoffsetx = -tilecolremain * this.tileSize.w ;
        var tileoffsetlon = this.tileOrigin.lon + tilecol * tilelon;
        
        var offsetlat = this.tileOrigin.lat - bounds.top;  
        var tilerow = Math.floor(offsetlat/tilelat) - this.buffer;
        var tilerowremain = offsetlat/tilelat - tilerow + 1;
        var tileoffsety = -tilerowremain * this.tileSize.h ;
        var tileoffsetlat = this.tileOrigin.lat - tilerow * tilelat;   
        return { 
          tilelon: tilelon, tilelat: tilelat,
          tileoffsetlon: tileoffsetlon, tileoffsetlat: tileoffsetlat,
          tileoffsetx: tileoffsetx, tileoffsety: tileoffsety
        };

    },    
    initGriddedTiles:function(bounds){
    	//获取地图解析度
    	var resolution = this.map.getResolution();
    	//获取最接近地图解析度的图层的解析度
    	var success = false;
    	for(var i = 0; i < this.serverResolutions.length;i++){
    		var resolution1 = this.serverResolutions[i];
    		var ratio1 = resolution1 / resolution;
    		if(ratio1 <=1.5 && ratio1 >= 0.75){
    			this.setTileSize(new OpenLayers.Size(this.layerTileSize.w *ratio1,this.layerTileSize.h *ratio1));
    			success = true;
    			break;
    		}
    	}
    	var viewSize = this.map.getSize();
        var minRows = Math.ceil(viewSize.h/this.tileSize.h ) + 
                      Math.max(1, 2 * this.buffer);
        var minCols = Math.ceil(viewSize.w/this.tileSize.w ) +
                      Math.max(1, 2 * this.buffer);
        
        var extent = this.map.getMaxExtent();
           
        var tileLayout = this.calculateGridLayout(bounds, extent, resolution);

        var tileoffsetx = Math.round(tileLayout.tileoffsetx); // heaven help us
        var tileoffsety = Math.round(tileLayout.tileoffsety);

        var tileoffsetlon = tileLayout.tileoffsetlon;
        var tileoffsetlat = tileLayout.tileoffsetlat;
        
        var tilelon = tileLayout.tilelon;
        var tilelat = tileLayout.tilelat;

        this.origin = new OpenLayers.Pixel(tileoffsetx, tileoffsety);

        var startX = tileoffsetx; 
        var startLon = tileoffsetlon;

        var rowidx = 0;
        
        var layerContainerDivLeft = parseInt(this.map.layerContainerDiv.style.left);
        var layerContainerDivTop = parseInt(this.map.layerContainerDiv.style.top);
        
    
        do {
            var row = this.grid[rowidx++];
            if (!row) {
                row = [];
                this.grid.push(row);
            }

            tileoffsetlon = startLon;
            tileoffsetx = startX;
            var colidx = 0;
 
            do {
                var tileBounds = 
                    new OpenLayers.Bounds(tileoffsetlon, 
                                          tileoffsetlat, 
                                          tileoffsetlon + tilelon,
                                          tileoffsetlat + tilelat);

                var x = tileoffsetx;
                x -= layerContainerDivLeft;

                var y = tileoffsety;
                y -= layerContainerDivTop;
                var x1 = Math.floor(x);
                var y1 = Math.floor(y);
                var x2 = Math.floor(x + this.tileSize.w);
                var y2 = Math.floor(y + this.tileSize.h);
                var px = new OpenLayers.Pixel(x1, y1);
                var tile = row[colidx++];
                if (!tile) {
                    tile = this.addTile(tileBounds, px,x2-x1,y2-y1);
                    this.addTileMonitoringHooks(tile);
                    row.push(tile);
                } else {
                    tile.moveTo(tileBounds, px, false);
                }
     
                tileoffsetlon += tilelon;       
                tileoffsetx += this.tileSize.w ;
            } while ((tileoffsetlon <= bounds.right + tilelon * this.buffer)
                     || colidx < minCols);
             
            tileoffsetlat -= tilelat;
            tileoffsety += this.tileSize.h ;
        } while((tileoffsetlat >= bounds.bottom - tilelat * this.buffer)
                || rowidx < minRows + 1);
        
        //shave off exceess rows and colums       
    	if(!success){
    		rowidx = colidx = 0;
    	} 		
        this.removeExcessTiles(rowidx, colidx);
        
        //now actually draw the tiles
        this.spiralTileLoad();
    },
    removeExcessTiles: function(rows, columns) {
        
        // remove extra rows
        while (this.grid.length > rows) {
            var row = this.grid.pop();
            for (var i=0, l=row.length; i<l; i++) {
                var tile = row[i];
                this.removeTileMonitoringHooks(tile);
                tile.destroy();
            }
        }
        
        // remove extra columns
        while (this.grid.length > 0 && this.grid[0].length > columns) {
            for (var i=0, l=this.grid.length; i<l; i++) {
                var row = this.grid[i];
                var tile = row.pop();
                this.removeTileMonitoringHooks(tile);
                tile.destroy();
            }
        }
    },
    spiralTileLoad:function () {
        var tileQueue = [];
        var directions = ["right", "down", "left", "up"];
        var iRow = 0;
        var iCell = -1;
        var direction = OpenLayers.Util.indexOf(directions, "right");
        var directionsTried = 0;
        while (directionsTried < directions.length) {
            var testRow = iRow;
            var testCell = iCell;
            switch (directions[direction]) {
            case "right":
                testCell++;
                break;
            case "down":
                testRow++;
                break;
            case "left":
                testCell--;
                break;
            case "up":
                testRow--;
                break;
            }
            var tile = null;
            if ((testRow < this.grid.length) && (testRow >= 0) && (testCell < this.grid[0].length) && (testCell >= 0)) {
                tile = this.grid[testRow][testCell];
            }
            if ((tile != null) && (!tile.queued)) {
                tileQueue.unshift(tile);
                tile.queued = true;
                directionsTried = 0;
                iRow = testRow;
                iCell = testCell;
            } else {
                direction = (direction + 1) % 4;
                directionsTried++;
            }
        }
        for (var i = 0, len = tileQueue.length; i < len; i++) {
            var tile = tileQueue[i];
            tile.draw();
            tile.queued = false;
        }
    },
    CLASS_NAME: "OpenLayers.Layer.TQTileCache"
});