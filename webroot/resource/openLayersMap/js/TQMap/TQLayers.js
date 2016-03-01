/**
 * 对于不同地图图层（Layer）的的加载
 * 
 * 包含：图层加载方法，获取2D、25D、MS、WFS等图层，配置二三维图层（与org相关）
 *******************************************************************************************/
/********获取二三维图层**********/
window.TQLayers = {
	type:null,                                                                                   //(区域)地图类型
	addOption:function(option){                                                     //为不同区域配置特定的参数
		var appendOption = this.execute(option, "addOption");
		return $.extend(option,appendOption);
	},
	get3DLayer:function(option){
		if(option.M3DOption){
			return this.execute(option,option.M3DOption,"get3DLayer");
		}
	},
	get2DLayer:function(option){
		if(option.M2DOption){
			return this.execute(option,option.M2DOption,"get2DLayer");
		}
	},
	getMSLayer:function(option){
		if(option.MSOption){
			return this.execute(option,option.MSOption,"getMSLayer");
		}
	},
	getWFSLayer:function(option){
		if(option.WFSOption){
			return this.execute(option,option.WFSOption,"getWFSLayer");
		}
	},
	execute:function(option,layerOption,funcName){
		var funcs = this.Funcs[this.type];
		if(!funcs){
			funcs = this.Funcs["default"];
		}
		var func = (funcs)?funcs[funcName]:null;
		if(func){
			return func(option,layerOption);
		}
		return false;
	},
	extend:function(obj) {/**属性继承*/
		var that = this;
		for ( var property in obj) {
			that[property] = obj[property];
		}
		return that;
	}
}
/********图层加载方法**********/
TQLayers.extend({
		Base:function(option){/**加载基础图层 */
			var idfop = $.extend({
				layerName:"baseLayer",
				mapMaxResolution:0,
				mapMinResolution:15
			},option);
			return new OpenLayers.Layer("baseLayer",{
				maxResolution : (360/256)/Math.pow(2, idfop.mapMaxResolution), 
				minResolution : (360/256)/Math.pow(2, idfop.mapMinResolution),
				displayInLayerSwitcher: false,
				isBaseLayer: true,
				maxExtent: new OpenLayers.Bounds(idfop.boundsMinLon, idfop.boundsMinLat, idfop.boundsMaxLon, idfop.boundsMaxLat)
			})
		},
		GlobeTile:function(baseOption,option){/**加载GlobeTile类型的矢量图层 */
			var idfop={
					layerPath:null,
					layerName:"全球1:100万矢量底图",
					mapMaxResolution:0,
					mapMinResolution:15,
					projection:"EPSG:4326",
					displayProjection:"EPSG:4326",
					isBaseLayer:true
				}
			$.extend(idfop,baseOption,option);
			if(idfop.layerPath==null || $.trim(idfop.layerPath)=="") return null;
			return new OpenLayers.Layer.GlobeTile(idfop.layerName, idfop.layerPath, {
	            transitionEffect: "resize",
	            topLevel: idfop.mapMaxResolution,
	            bottomLevel: idfop.mapMinResolution,
	            isBaseLayer: idfop.isBaseLayer,
	            projection: idfop.projection,
                displayProjection: new OpenLayers.Projection(idfop.displayProjection),
                maxExtent: new OpenLayers.Bounds(idfop.boundsMinLon, idfop.boundsMinLat, idfop.boundsMaxLon, idfop.boundsMaxLat)
	        })
		},
		WMS:function(baseOption,option){
			var idfop={
					layerPath:null,
					layerLayers:null,
					layerName:'OpenLayers WMS',
					isBaseLayer:true,
					mapMaxResolution:0,
					mapMinResolution:15
				}
			$.extend(idfop,baseOption,option);
			if(idfop.layerPath==null || $.trim(idfop.layerPath)=="") return null;
			return new OpenLayers.Layer.WMS(idfop.layerName,idfop.layerPath,
					 {
					 	layers: idfop.layerLayers,
					 	srs:'EPSG:4326'
					 },
					 {
						 isBaseLayer: idfop.isBaseLayer, 
						 maxResolution : (360/256)/Math.pow(2, idfop.mapMaxResolution), 
						 minResolution : (360/256)/Math.pow(2, idfop.mapMinResolution),
						 maxExtent: new OpenLayers.Bounds(idfop.boundsMinLon, idfop.boundsMinLat, idfop.boundsMaxLon, idfop.boundsMaxLat),
						 transitionEffect: 'resize',
	                     buffer: 0
					 }
			)
		},
		TQTileCache:function(baseOption,option){
			var idfop = {
//					lon:120.41005,
//					lat:29.78689,
//					zoom:2,
//					boundsMinLon:116.28774491829,
//					boundsMinLat:26.763620659367,
//					boundsMaxLon:124.74571715665,
//					boundsMaxLat:31.592033939785
					isBaseLayer:true,
					layerPath:null,
					labelLayer:null,        //地名图层
					resolutions:null,
					layerName:"浙江地图",
					mapMaxResolution:1,
					mapMinResolution:15
			}
			$.extend(idfop,baseOption,option);
			if(idfop.layerPath==null || idfop.layerPath.trim()=="") return null;
			//var resolutions = new Array(1.40625, 0.703125, 0.3515625, 0.17578125, 0.087890625, 0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, 0.00274658203125, 0.001373291015625, 0.0006866455078125, 0.00034332275390625, 0.000171661376953125, 0.0000858306884765625, 0.00004291534423828125, 0.000021457672119140625, 0.000010728836059570312, 0.000005364418029785156, 0.000002682209014892578, 0.000001341104507446289);
			var resolutions = eval(idfop.resolutions);
			var layer = new OpenLayers.Layer.TQTileCache(idfop.layerName,idfop.layerPath,idfop.layerName, {
					isBaseLayer : idfop.isBaseLayer,
					tileSize : new OpenLayers.Size(256,256),
					tileOrigin : new OpenLayers.LonLat(-180, 90),
					maxResolution : (360/256)/Math.pow(2, idfop.mapMaxResolution), 
					minResolution : (360/256)/Math.pow(2, idfop.mapMinResolution),
					serverResolutions : resolutions,
					maxExtent: new OpenLayers.Bounds(idfop.boundsMinLon, idfop.boundsMinLat, idfop.boundsMaxLon, idfop.boundsMaxLat)
			});
			layer.levels = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
			layer.formatURL = function(x, y, z) {
				z = this.levels[z];
				var url = OpenLayers.String.format(this.url, {
						'x' : x,
						'y' : y,
						'z' : z
					});
				return url;
			};
			layer.labelLayer = idfop.labelLayer;
			return layer;
		},
		XYZModified:function(baseOption,option){
			var idfop = {
					layerPath:null,
					resolutions:[5.948652514575699E-4,2.9743262572878496E-4,1.522855043731379E-4,7.614275218656895E-5,3.8071376093284474E-5,1.9035688046642237E-5,9.517844023321119E-6,4.758922011660559E-6,2.3794610058302796E-6,1.1897305029151398E-6],
					layerName:"XYZTileLayer",
					mapMaxResolution:1,
					mapMinResolution:15,
					isBaseLayer:true
				}
				$.extend(idfop,baseOption,option);
				if(idfop.layerPath==null || $.trim(idfop.layerPath)=="") return null;
				var tileOrigin = new OpenLayers.LonLat(-400.0,400.0);
				return new OpenLayers.Layer.XYZ.Modified(idfop.layerName,idfop.layerPath + "/tile/\${z}/\${y}/\${x}", {
		                maxExtent: new OpenLayers.Bounds(idfop.boundsMinLon, idfop.boundsMinLat, idfop.boundsMaxLon, idfop.boundsMaxLat),
		                tileOrigin: tileOrigin,
		                serverResolutions:eval(idfop.resolutions),
		                tileSize: new OpenLayers.Size(256,256),
		                maxResolution : (360/256)/Math.pow(2, idfop.mapMaxResolution), 
						minResolution : (360/256)/Math.pow(2, idfop.mapMinResolution),
						isBaseLayer: idfop.isBaseLayer
		            });
		},
		WMTS:function(baseOption,option){
			var idfop={
					layerPath:null,
					layerLayers:null,
					layerName:'OpenLayers WMTS',
					isBaseLayer:true,
					mapMaxResolution:1,
					mapMinResolution:15,
					matrixSet: "Matrix_0",
			        matrixIds: ["11","12","13","14","15","16","17","18","19"],
					zoomOffset:0,
			        format: "image/png",
			        style: "default"
				}
			$.extend(idfop,baseOption,option);
			if(!idfop.layerPath || !idfop.layerLayers) return null;
			return new OpenLayers.Layer.WMTS({
				name: idfop.layerName,
				maxResolution : (360/256)/Math.pow(2, idfop.mapMaxResolution),
				minResolution : (360/256)/Math.pow(2, idfop.mapMinResolution),
				url: idfop.layerPath,
				layer: idfop.layerLayers,
				matrixSet: idfop.matrixSet,
				matrixIds: idfop.matrixIds,
				zoomOffset: idfop.zoomOffset,
				format: idfop.format,
				style: idfop.style,//"ls25d_0920",
				isBaseLayer: idfop.isBaseLayer} )
		},
		SuperMapCloud:function(baseOption,option){
			var idfop = {
					layerPath:null,
					resolutions:[],
					layerName:"SuperMapCloudLayer",
					mapMaxResolution:1,
					mapMinResolution:15,
					isBaseLayer:false
				}
				$.extend(idfop,baseOption,option);
			if(!idfop.layerPath) return null;
			return new OpenLayers.Layer.SuperMapCloud(idfop.layerName, idfop.layerPath, {
                tileOrigin: new OpenLayers.LonLat(-180, 90),
                tileSize: new OpenLayers.Size(256,256),
                resolutions: eval(idfop.resolutions),
                sphericalMercator: true,
                maxExtent: new OpenLayers.Bounds(-180, -90, 180, 90),
                useArcGISServer: false,
                isBaseLayer:idfop.isBaseLayer,
                type: 'png',
                projection: 'EPSG:4326'
            });
		},
		WFS:function (baseOption,option){
			var idfop={
					proxyPath:PATH +"/proxyHandler",
					layerName:'WFS',
					layerPath:null,
					featureType:null,
					featurePrefix:null,
					geometryName:null,
					version:"1.0.0",
					wfsMaxResolution:11,
					wfsMinResolution:19,
					onFeatureInsert:function(feature){}
				}
			$.extend(idfop,baseOption,option);
			if(!idfop.layerPath) return;
		   //定义热区图层样式
	       var myStyles = new OpenLayers.StyleMap({
	            "default": new OpenLayers.Style({
	                pointRadius: "${type}", // sized according to type attribute
	                fillColor: "#ffcc66",
	                strokeColor: "#ff9933",
	                strokeWidth: 0,
	                graphicZIndex: 1,
	                fillOpacity: 0,
	                strokeOpacity: 0.3
	            }),
	            "select": new OpenLayers.Style({
	                fillColor: "#66ccff",
	                strokeColor: "#3399ff",
	                graphicZIndex: 2
	            })
	        });
	       //创建热区图层
		   return new OpenLayers.Layer.Vector(idfop.layerName, {
			   	styleMap: myStyles,
		    	maxResolution:(360/256)/Math.pow(2, idfop.wfsMaxResolution),//设置图层激活后显示的最小级别
				minResolution:(360/256)/Math.pow(2, idfop.wfsMinResolution),//设置图层激活后显示的最大级别
		    	strategies: [new OpenLayers.Strategy.BBOX()],
				protocol: new OpenLayers.Protocol.WFS({
					//url: "http://localhost:8089/proxyHandler?url=http://tianque.oicp.net:9090/iserver/services/data-guangzhoubuild2/wfs100/utf-8",
					url: idfop.proxyPath + "?url=" + idfop.layerPath,
					VERSION:idfop.version,
					featureType: idfop.featureType,
		            featureNS: false,
		            featurePrefix: idfop.featurePrefix,
		            geometryName: idfop.geometryName
				}),
				onFeatureInsert:idfop.onFeatureInsert
			});
		}
})
/********配置二三维图层（与org相关）**********/
TQLayers.Funcs={
	"sichuan":{
		"addOption":function(option){
			return {
//				mapMaxResolution:10,						//地图显示的最小层级
//				mapMinResolution:18,						//地图显示的最大层级
//				wfsMaxResolution:18,					//热区图层显示的最小层级
//				wfsMinResolution:18,					//热区图层显示的最大层级
//				lon:106.29214716586775,
//				lat:30.330239830411703
				
			}
		},
		"get3DLayer":function(option,M3DOption){
			return TQLayers.SuperMapCloud(option,{
				layerPath: M3DOption.layerPath,
				resolutions: M3DOption.resolutions,
				layerName:"3DLayer"
			})
		},
		"get2DLayer":function(option,M2DOption){
			return TQLayers.SuperMapCloud(option,{
				layerPath:M2DOption.layerPath,
				resolutions:M2DOption.resolutions,
				layerName:"2DLayer"
			})
		},
		"getWFSLayer":function(option,WFSOption){
			return TQLayers.WFS(option,{
				proxyPath:PATH + "/openLayersMap/Proxy.jsp",
				layerName:'WFS',
				layerPath:WFSOption.layerPath,
				featureType:WFSOption.featureType,
				featurePrefix:WFSOption.featurePrefix,
				geometryName:WFSOption.featureType+":the_geom"
			})
		}
	},
	"oldSiChuan":{
		"get3DLayer":function(option,M3DOption){
			return TQLayers.WMTS(option,{
				layerPath:M3DOption.layerPath,
				layerLayers:M3DOption.layerLayers,
				layerName:"3DLayer"
			})
		},
		"get2DLayer":function(option,M2DOption){
			return TQLayers.GlobeTile(option,{
				layerPath:M2DOption.layerPath,
				layerName:"2DLayer"
			})
		},
		"getWFSLayer":function(option,WFSOption){
			return TQLayers.WFS(option,{
				proxyPath:PATH + "/proxyHandler",
				layerName:'WFS',
				layerPath:WFSOption.layerPath,
				featureType:WFSOption.featureType,
				featurePrefix:WFSOption.featurePrefix,
				geometryName:"GEOMETRY"
			})
		}
	}
}