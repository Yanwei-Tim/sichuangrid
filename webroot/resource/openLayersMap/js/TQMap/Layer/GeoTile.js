OpenLayers.Pyramid = OpenLayers.Class( {
	name : null,
	pyramidID : null,
	description : null,
	topLevelIndex : null,
	bottomLevelIndex : null,
	scaleX : null,
	scaleY : null,
	tileSize : null,
	originRowIndex : null,
	originColIndex : null,
	topTileFromX : null,
	topTileFromY : null,
	topTileToX : null,
	topTileToY : null,
	initialize : function(A) {
		if (!A || A === {}) {
			A = OpenLayers.Pyramid.DEFAULT_PYRAMID
		}
		OpenLayers.Util.extend(this, A)
	},
	getMaxExtent : function() {
		var B, D, A, C;
		if (this.topTileFromX < this.topTileToX) {
			B = this.topTileFromX;
			A = this.topTileToX
		} else {
			A = this.topTileFromX;
			B = this.topTileToX
		}
		if (this.topTileFromY < this.topTileToY) {
			D = this.topTileFromY;
			C = this.topTileToY
		} else {
			C = this.topTileFromY;
			D = this.topTileToY
		}
		return new OpenLayers.Bounds(B, D, A, C)
	},
	getLevelForResolution : function(D) {
		if (!D) {
			return 0
		}
		var B = D;
		var A = this.getMaxResolution();
		var C = Math.round(A / B);
		if (C < 1) {
			return 0
		}
		var E = 0;
		while ((C / 2) >= 1) {
			E++;
			C = C / 2
		}
		return E
	},
	getLevelForScale : function(B) {
		var A = OpenLayers.Util.getResolutionFromScale(B);
		var C = this.getLevelForResolution(A);
		return C
	},
	getResolutionForLevel : function(A) {
		var B = (this.getTopTileSize().w / this.tileSize.w);
		return B / Math.pow(2, A)
	},
	getMaxResolution : function() {
		return this.getResolutionForLevel(this.topLevelIndex)
	},
	getMinResolution : function() {
		return this.getResolutionForLevel(this.bottomLevelIndex)
	},
	getNumZoomLevels : function() {
		return this.bottomLevelIndex - this.topLevelIndex + 1
	},
	getTopTileSize : function() {
		var B = Math.abs(this.topTileToX - this.topTileFromX);
		var A = Math.abs(this.topTileToY - this.topTileFromY);
		return new OpenLayers.Size(B, A)
	},
	getTileIndex : function(K, L) {
		var E = K.lon;
		var A = K.lat;
		var G = this.getTopTileSize();
		var D = G.w / Math.pow(2, L);
		var J = this.maxExtent.getWidth();
		var C = this.maxExtent.getHeight();
		var H = Math.round(J / D);
		var I = Math.round(C / D);
		var M = (this.topTileFromX < this.topTileToX) ? true : false;
		var F = Math.ceil(Math.abs(E - this.topTileFromX) / D) - 1;
		if (!M) {
			F = H - F
		}
		var N = (this.topTileFromY > this.topTileToY) ? true : false;
		var B = Math.ceil(Math.abs(A - this.topTileFromY) / D) - 1;
		if (!N) {
			B = I - B
		}
		return {
			col : F,
			row : B
		}
	},
	clone : function() {
		return OpenLayers.Util.extend( {}, this)
	},
	CLASS_NAME : "OpenLayers.Pyramid"
});
OpenLayers.Pyramid.Degree360 = {
	name : "360DegreePyramid",
	topLevelIndex : 0,
	bottomLevelIndex : 20,
	scaleX : 2,
	scaleY : 2,
	topTileFromX : -180,
	topTileFromY : 90,
	topTileToX : 180,
	topTileToY : -270,
	tileSize : new OpenLayers.Size(256, 256),
	originRowIndex : 0,
	originColIndex : 0,
	maxExtent : new OpenLayers.Bounds(-180, -90, 180, 90)
};
OpenLayers.Pyramid.Degree18 = {
	name : "18DegreePyramid",
	topLevelIndex : 0,
	bottomLevelIndex : 20,
	scaleX : 2,
	scaleY : 2,
	topTileFromX : -180,
	topTileFromY : -90,
	topTileToX : -162,
	topTileToY : -72,
	tileSize : new OpenLayers.Size(256, 256),
	originRowIndex : 0,
	originColIndex : 0,
	maxExtent : new OpenLayers.Bounds(-180, -90, 180, 90)
};
OpenLayers.Pyramid.DEFAULT_PYRAMID = OpenLayers.Pyramid.Degree360;
OpenLayers.Layer.GlobeTile = OpenLayers.Class(OpenLayers.Layer.Grid, {
	alwaysInRange : false,
	isBaseLayer : false,
	topLevel : null,
	bottomLevel : null,
	pyramid : null,
	accessUrl : null,
	mirrorUrl : null,
	serviceName : null,
	verstionTime : "9999-01-01 00:00:00",
	cacheExpireTime : "now",
	buffer : 0,
	transitionEffect : null,
	initialize : function(D, B, C) {
		this.name = D;
		this.url = B;
		this.convertUrl(this.url);
		if (!C) {
			C = {}
		}
		C.pyramid = C.pyramid ? C.pyramid : new OpenLayers.Pyramid();
		C.topLevel = C.topLevel ? C.topLevel : C.pyramid.topLevelIndex;
		C.bottomLevel = C.bottomLevel ? C.bottomLevel
				: C.pyramid.bottomLevelIndex;
		var F = C.pyramid.getMaxResolution();
		C.maxResolution = C.pyramid.getResolutionForLevel(C.topLevel);
		C.minResolution = C.pyramid.getResolutionForLevel(C.bottomLevel);
		C.tileSize = C.pyramid.tileSize;
		var A = null;
		var E = [ D, B, A, C ];
		OpenLayers.Layer.Grid.prototype.initialize.apply(this, E)
	},
	convertUrl : function(A) {
		var B = A.split("/services/");
		if (B[0] && B[1]) {
			this.accessUrl = B[0] + "/DataServer";
			this.serviceName = B[1]
		} else {
			OpenLayers.Console.error("解析GeoGlobe 2.0.0服务地址错误:" + A);
			return
		}
	},
	addTile : function(A, B) {
		return new OpenLayers.Tile.Image(this, B, A, null, this.tileSize)
	},
	setVerstionTime : function(A) {
		if (A) {
			this.verstionTime = A;
			this.redraw()
		}
	},
	getURL : function(A) {
		var G = OpenLayers.Util.getImagesLocation() + "blank.gif";
		A = this.adjustBounds(A);
		var D = this.pyramid.getMaxResolution();
		var J, F, I, H;
		F = this.pyramid.getLevelForResolution(this.map.getResolution());
		J = this.pyramid.getTopTileSize().w / Math.pow(2, F);
		if (this.pyramid.topTileFromX < this.pyramid.topTileToX) {
			I = Math.round((A.left - this.pyramid.topTileFromX) / J)
		} else {
			I = Math.round((this.pyramid.topTileFromX - A.right) / J)
		}
		if (this.pyramid.topTileFromY < this.pyramid.topTileToY) {
			H = Math.round((A.bottom - this.pyramid.topTileFromY) / J)
		} else {
			H = Math.round((this.pyramid.topTileFromY - A.top) / J)
		}
		if (I < 0 || H < 0) {
			return OpenLayers.Util.getImagesLocation() + "blank.gif"
		}
		var B = "";
		var C = {};
		if (this.mirrorUrl == null) {
			B = this.accessUrl;
			C = {
				"T" : this.serviceName,
				"X" : I,
				"Y" : H,
				"L" : F,
				"INDATE" : this.verstionTime
			}
		} else {
			B = this.selectUrl(I, this.mirrorUrl);
			var E = B.split("/services/");
			if (E[0] && E[1]) {
				B = E[0] + "/DataServer";
				C = {
					"T" : E[1],
					"X" : I,
					"Y" : H,
					"L" : F,
					"INDATE" : this.verstionTime
				}
			}
		}
		B = this.getFullRequestString(C, B);
		return B
	},
	selectUrl : function(A, D) {
		var C = D.length;
		var B = A % C;
		return D[B]
	},
	getDataExtent : function() {
		if (this.maxExtent) {
			return this.maxExtent.clone()
		}
	},
	clone : function(A) {
		if (A == null) {
			A = new OpenLayers.Layer.GlobeTile(this.name, this.url,
					this.options)
		}
		A = OpenLayers.Layer.Grid.prototype.clone.apply(this, [ A ]);
		return A
	},
	CLASS_NAME : "OpenLayers.Layer.GlobeTile"
});