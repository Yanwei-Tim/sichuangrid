/**
 * 地图的各种URL在gis.yaml文件中配置
 * 
 * 在TQMapUtil.js中设置了不同组织机构对应不同的图层及中心点
 */
if(!window.TQMap){
	window.TQMap = function(option){if(this.TQMap) this.TQMap(option);};
}
$.fn.extend({
	TqMap:function(option,withoutOption){
		var self=$(this);
		var mapId = self.attr("id");
		if(typeof option=='string'){
			var tqmap = TQMap["CACHE"+mapId];
			if(tqmap && option.substring(0,1)!='_'){
				if(option == "get" || option == "set"){ option += "Data"; }
				if(typeof tqmap[option] == "function"){
					return tqmap[option](withoutOption);
				}
			}
		}else{
			var tqmap = new TQMap($.extend(option,{ mapId:mapId }));
			TQMap["CACHE"+mapId] = tqmap;
			return tqmap;
		}
	}
})
if(eval(TQMap.UPGRADE)){
	$.fn.extend({
		TqMap:function(option,withoutOption){
				var container = (option && option.upgradeDiv) || $(this).attr("id");
				$("#"+container).empty().load(PATH+"/openLayersMap/upgrade.jsp");
		}
	})
}
/**TQMap的构造函数*/
TQMap.prototype.TQMap=function(option){
	this.extend(this,{
		ImgPath:'/resource/openLayersMap/js/OpenLayers-1/',
		M2DOption:TQMap.M2D_OPTION,                                    //二维地图配置
		M3DOption:TQMap.M3D_OPTION,                                    //三维地图配置
		MSOption:TQMap.MS_OPTION,                                        //卫星图(Manmade Satellite)配置
		WFSOption:TQMap.WFS_OPTION,                                    //WFS要素图层配置
		videoServicePath:TQMap.VIDEO_OPTION,                         //对于视频监控查询的接口
		layerType:TQMap.MAPTYPE,                 //图层类型：包括底图的加载方式，及坐标转换的转换公式......
		lon:TQMap.CENTERLON,						//当前地图加载时定位的坐标
		lat:TQMap.CENTERLAT,						    //当前地图加载时定位的坐标
		zoom:0,										        //当前地图加载的层级
		mapMaxResolution:11,						    //地图显示的最小层级
		mapMinResolution:19,						    //地图显示的最大层级
		wfsMaxResolution:18,					        //热区图层显示的最小层级
		wfsMinResolution:19,					        //热区图层显示的最大层级
		isShowM2D:true,						            //是否加载二维地图
		isShowM3D:false,						            //是否加载三维地图
		isShowMS:false,						            //是否加载卫星地图
		isShowWFS:false,						            //是否加载热区
		isViewPanZoomBar:true,						     //是否显示平移缩放工具栏
		isViewScaleLine:true,						             //是否显示比例尺
		isViewMousePosition:true,					     //是否显示鼠标移动位置坐标
		isValidMapDefaultDblClick:true,				 //地图双击放大缩小事件是否有效
		isValidZoomWheelEnabled:true,				 //鼠标滚动放大缩小事件是否有效
		isValidHandleRightClicks:true,				     //右键菜单是否有效
		isShowOverviewMap:false,					     //是否显示鹰眼
		isShowCompass:false,                               //是否显示指南针
		boundsMinLon: -180,							    //地图允许的最小经度
		boundsMaxLon: 180,							        //地图允许的最大经度
		boundsMinLat: -90,							        //地图允许的最小纬度
		boundsMaxLat: 90,							        //地图允许的最大纬度
		gisType:"2D",                                            //设置初始地图  2D、3D 或  MS(Manmade Satellite 卫星地图)
		mapId :null,                                             //地图容器id
		orgZoom:[
					{title:'网格',name:'GRID',zoom:8},
					{title:'村/社区',name:'VILLAGE',zoom:6},
					{title:'乡镇/街道',name:'TOWN',zoom:4},
					{title:'区/县',name:'DISTRICT',zoom:2},
					{title:'市',name:'CITY',zoom:0}
				]
	});
	this.extend(this,option);
	var dfop = this;
	$(window).resize(function() {
		clearTimeout(window.resizeHandle);
		window.resizeHandle = setTimeout(function(){
			if(dfop.map){
				dfop.map.updateSize();
			}
		}, 500);
	}).resize();
	
	OpenLayers._getScriptLocation=function(){
		return dfop.ImgPath;
	}
	//出现异常图片区域，现在使用样式.olImageLoadError
    OpenLayers.Util.onImageLoadError = function() { this.style.display = "none"; };
	
    //初始化属性值
    TQLayers.type=dfop.layerType;
	TQLayers.addOption(dfop);                                   //根据“TQLayers.type”添加配置信息
	TQTransformat.type=dfop.layerType;
	TQMap.gisType=dfop.gisType;
	if(dfop.M2DOption && typeof dfop.M2DOption === "string") dfop.M2DOption = JSON.parse(dfop.M2DOption);
	if(dfop.M3DOption && typeof dfop.M3DOption === "string") dfop.M3DOption = JSON.parse(dfop.M3DOption);
	if(dfop.MSOption && typeof dfop.MSOption === "string") dfop.MSOption = JSON.parse(dfop.MSOption);
	if(dfop.WFSOption && typeof dfop.WFSOption === "string") dfop.WFSOption = JSON.parse(dfop.WFSOption);
	
    this._initMap = function(){
    	var that = this;
		//构造地图对象实例,并添加到id为map的div容器中
		if(!$("#"+that.mapId)[0]){return false;}
		$("#"+that.mapId).empty();
		var map = new OpenLayers.Map(that.mapId,{controls: []});
		//初始化地图图层
		that.baseLayer = TQLayers.Base(that);
		that.layer3D = (!that.isShowM3D)?null:TQLayers.get3DLayer(that);
		that.layer2D = (!that.isShowM2D)?null:TQLayers.get2DLayer(that);
		that.layerMS = (!that.isShowMS)?null:TQLayers.getMSLayer(that);
		that.layerWFS = (!that.isShowWFS)?null:TQLayers.getWFSLayer(that);
	
		
		map.addLayers([that["layer"+that.gisType],that.baseLayer]);
		if(that.layerWFS!=null) map.addLayer(that.layerWFS);
    	//创建作图层
    	var vector = new OpenLayers.Layer.Vector("作图层");
	    map.addLayer(vector);
    	//定位到中心点，缩放级别
        map.setCenter(new OpenLayers.LonLat(that.lon,that.lat), that.zoom); 
        that.extend(this,{
        	"vector":vector,                       //作图层
        	"wfsFeatureVectorArray":new Array(),
        	"wfslayer":that.layerWFS,
        	"marker":null,
        	"markers":null,
        	"popupText":null,
        	"popup":null,
        	"selectF":null,
        	"map":map,
        	"history":{}
        })
    	//添加地图工具
		that._addControls();
	}
    
    this._addControls = function(){
    	var that = this;
    	var map = this.map;
		if(that.isViewScaleLine){
			OpenLayers.INCHES_PER_UNIT["千米"] = OpenLayers.INCHES_PER_UNIT["km"]; 
			OpenLayers.INCHES_PER_UNIT["米"] = OpenLayers.INCHES_PER_UNIT["m"]; 
			OpenLayers.INCHES_PER_UNIT["英里"] = OpenLayers.INCHES_PER_UNIT["mi"]; 
			OpenLayers.INCHES_PER_UNIT["英寸"] = OpenLayers.INCHES_PER_UNIT["ft"];
			map.addControl(new OpenLayers.Control.ScaleLine({
				topOutUnits:"千米",
				topInUnits:"米",
				bottomOutUnits:"英里",
				bottomInUnits:"英寸"
			}));
		}
		if(that.isViewMousePosition){
			map.addControl(new OpenLayers.Control.MousePosition());//鼠标移动位置坐标效果
		}
		map.addControl(new OpenLayers.Control.TQNavigation({//鼠标放大缩小效果(含拖动效果)
			path:PATH,
			zoomWheelEnabled:that.isValidZoomWheelEnabled,//鼠标滚轮时地图是否放大缩小
			handleRightClicks:that.isValidHandleRightClicks,//是否禁掉右键菜单
			isValidMapDefaultDblClick:that.isValidMapDefaultDblClick,//是否双击放大
			isViewIcon:true,    //是否显示滚动放大的图片特效
			isCanZoomDown:that.isCanZoomDown
		}));
		
		if(that.isViewPanZoomBar){
			//平移缩放工具栏重写
			map.addControl(new OpenLayers.Control.TQPanZoomBar({
				'zoomWorldIcon':true,
				lon:that.lon,
				lat:that.lat,
				zoom:that.zoom,
				isZoomBarDrag:true,
				position: new OpenLayers.Pixel(10, 10),
				//GRID:片组片格,VILLAGE：村，TOWN：镇、街道，DISTRICT：区，CITY：市，PROVINCE：省，COUNTRY：全国 
				orgZoom:that.orgZoom,
				isCanZoomDown:that.isCanZoomDown,
				orgStyle:{
					top:82,
					left:40
				}
			}));
		}
		//map.addControl(new OpenLayers.Control.LayerSwitcher({'ascending':false}));//图层工具栏
		that.addCompassControl({type:that.gisType,isShowCompass:that.isShowCompass});//指南针
		that.addOverviewMapControl({
			layers:["layer"+that.gisType,"baseLayer"],
			isShowOverviewMap:that["isShowOverviewMap"]
		});//鹰眼显示
		//加载进行中控件
		that.map.addControl(new OpenLayers.Control.TQLoadingPanel());
	}
	this._initMap();
	//删除对象属性
	this.remove(["_initMap","_addControls"]);
};
/**属性继承*/
TQMap.prototype.extend = function(obj1,obj2) {
	for ( var property in obj2) {
		obj1[property] = obj2[property];
	}
	return obj1;
}
/**移除属性*/
TQMap.prototype.remove = function(propertys) {
	var that = this;
	var typeOf = Object.prototype.toString;
	if(typeOf.call(propertys)=="[object Array]"){
		for (var i=0;i<propertys.length;i++) {
			delete that[propertys[i]];
		}
	}else{
		delete that[propertys];
	}
	return that;
}
/**新增属性*/
TQMap.prototype.setData = function(option) {
	var idfop = $.extend({
		key:"",
		value:""
	},option)
	if(idfop.key){ this[idfop.key] = idfop.value; }
}
/**获取属性*/
TQMap.prototype.getData = function(key) {
	return this[key];
}
/**获取当前屏幕范围*/
TQMap.prototype.getScreenExtent = function(){
	var that = this;
	var screen = that.map.getExtent();
	var offsetX = (screen.right-screen.left)/3;//经度偏差值为当前屏幕总距离的三分之一，为滑动搜素服务
	var offsetY = (screen.top-screen.bottom)/2;//纬度偏差值为当前屏幕总距离的二分之一，为滑动搜素服务
	return $.extend(screen,{
		"minLon":screen.left - offsetX,
		"maxLon":screen.right + offsetX,
		"minLat":screen.bottom - offsetY,
		"maxLat":screen.top + offsetY,
		"offsetX":Math.abs(offsetX),
		"offsetY":Math.abs(offsetY)
	})
}
/**是否能够缩小地图级别*/
TQMap.prototype.isCanZoomDown = function(option){
	var that = this;
	var idfop={
			zoomStart:that.zoom,
			nowZoom:that.zoom
	}
	$.extend(idfop,option);
	if(idfop.nowZoom<idfop.zoomStart-1) return false;
	return true;
}
/**2D、3D等图层的切换*/
TQMap.prototype.layerSwitch = function(option){
	var that = this;
	var idfop={
			type:"3D"
		}
	$.extend(idfop,option);
	var layerName = "layer" + idfop.type;
	var map = that.map;
	if(!map) return;
	var layer2D = map.getLayersByName("2DLayer");
	var layer3D = map.getLayersByName("3DLayer");
	var layerMS = map.getLayersByName("MSLayer");
	if(layer2D.length>0) map.removeLayer(layer2D[0]);
	if(layer3D.length>0) map.removeLayer(layer3D[0]);
	if(layerMS.length>0) map.removeLayer(layerMS[0]);
	var ms3D = TQLayers.SuperMapCloud(that,{
		layerPath: 'http://10.0.188.222:8096/FileService/image/meishanmap/',
		resolutions: that.M3DOption.resolutions,
		layerName:"MS3DLayer"
	});
	if(that.isShowM3D){
		map.addLayer(ms3D);
	}
	var layer = that[layerName];
	map.addLayer(layer);
	if(layer.isBaseLayer){
		map.setBaseLayer(layer);
	}
	that.addCompassControl({type:idfop.type,isShowCompass:that["isShowCompass"]});//指南针
	that.addOverviewMapControl({
		layers:[layerName,"baseLayer"],
		isShowOverviewMap:that["isShowOverviewMap"]
	});//鹰眼
}
/**图层切换控件*/
TQMap.prototype.addGisTypeControl = function(option){
	var that = this;
	var imap = that["map"];
	if(!imap) return;
	var idfop={
			gisType:that["gisType"],
			showType:["2D","3D","MS"],
			style:{},
			changeBeforFunc:function(prevGisType,targitGisType){},
			changingFunc:function(prevGisType,targitGisType){},
			changeAfterFunc:function(prevGisType,targitGisType){},
			layerSwitch:function(option){that.layerSwitch(option)}
	}
	$.extend(idfop,option,{
		onClick:function(gisType){
			TQMap.gisType = gisType;
		}
	});
	var control = new OpenLayers.Control.TQGisType(idfop);
	imap.addControl(control);
	that["GisTypeControl"] = control;
}
/**指南针控件*/
TQMap.prototype.addCompassControl = function(option){
	var that = this;
	var idfop={
			type:"2D",
			isShowCompass:true
	}
	$.extend(idfop,option)
	var imap = that["map"];
	if(!imap) return;
	var compass = imap.getControlsBy("displayClass","olControlTQCompass")[0];
	if(compass){
		imap.removeControl(compass);
	}
	if(idfop.isShowCompass==true){
		imap.addControl(new OpenLayers.Control.TQCompass({type:idfop.type}));
	}
}
/**鹰眼控件*/
TQMap.prototype.addOverviewMapControl = function(option){
	var that = this;
	var idfop={
			isShowOverviewMap:true,
			layers:[]
	}
	$.extend(idfop,option)
	var imap = that["map"];
	var overviewMap = imap.getControlsBy("displayClass","olControlTQOverviewMap")[0];
	var isOverviewMapDivShow = false;
	if(overviewMap!=null){
		isOverviewMapDivShow = overviewMap.isDivShow;
		imap.removeControl(overviewMap);
	}
	if(idfop.isShowOverviewMap==true){
		var layers = [];
		for(var i=0;i<idfop.layers.length;i++){
			var layer = that[idfop.layers[i]];
			layer && layers.push(layer.clone());
		}
		//imap.addControl(new OpenLayers.Control.OverviewMap({}));//鹰眼显示
		//maximized设置初始时鹰眼框是否是显示
		imap.addControl(new OpenLayers.Control.TQOverviewMap({
			layers:layers,
			maximized:isOverviewMapDivShow
		}));//鹰眼显示(扩展的鹰眼)
	}
}

/**设置中心点*/
TQMap.prototype.setCenter = function(option){
	var that = this;
	var idfop = $.extend({
		lon:that["lon"],
		lat:that["lat"],
		zoom:that["map"].zoom
	},option);
	that["map"].setCenter(new OpenLayers.LonLat(idfop.lon,idfop.lat), idfop.zoom);
}

/**画图形量算（一般画图形直接用这个接口，方便获取距离或面积，和单步点击地图坐标获取）*/
TQMap.prototype.drawFeatureMeasure = function(option){
	var that = this;
	var idfop={
		type: "line",//默认为画线量算，polygon为画面量算，regularPolygon为画多边形量算
		featureClickMapEnd: function(){},//测量点击结束事件
		featureClickMap: function(){},//测量每一步点击事件
		pointStyle: {//所画点的默认样式
			pointRadius: 4,
			graphicName: "square",
			fillColor: "white",
			fillOpacity: 1,
			strokeWidth: 1,
			strokeOpacity: 1,
			strokeColor: "#FF3333"
		},
		lineStyle: {//所画线的默认样式
			strokeWidth: 3,
			strokeOpacity: 1,
			strokeColor: "#FF3333",
			strokeDashstyle: "dash"
		},
		polygonStyle: {//所画面的默认样式
			strokeWidth: 3,
			strokeOpacity: 1,
			strokeColor: "#FF3333",
			fillColor: "white",
			fillOpacity: 0.3
		},
		sides: 3,//与画多边形（regularPolygon）绑定，数字为几，画出来的多边形的边数就为几
		irregular: true//默认画不规则形状，与画多边形（regularPolygon）绑定
	};
	$.extend(idfop,option);
	that.deactivateMeasureControl();	
    var style = new OpenLayers.Style();
    style.addRules([
        new OpenLayers.Rule({symbolizer: {
			"Point": idfop.pointStyle,
			"Line": idfop.lineStyle,
			"Polygon": idfop.polygonStyle
		}})
    ]);
    var styleMap = new OpenLayers.StyleMap({"default": style});
	var handlerType=OpenLayers.Handler.Path;
	if(idfop.type=="polygon"){
		handlerType=OpenLayers.Handler.Polygon;
	}else if(idfop.type=="regularPolygon"){
		handlerType=OpenLayers.Handler.RegularPolygon;
	}
	var measureControl= new OpenLayers.Control.Measure(handlerType,{
		persist: true,
		handlerOptions:{layerOptions:{styleMap: styleMap}}
	})
	measureControl.events.on({
		"measure": idfop.featureClickMapEnd,
		"measurepartial": idfop.featureClickMap
	});
	that["map"].addControl(measureControl);
	if(idfop.type=="regularPolygon"){
		measureControl.handler.setOptions({sides: idfop.sides,irregular: idfop.irregular});
	}
	measureControl.activate();
	that["measureControl"] = measureControl;
	$(document).bind("keydown",function(evt){//画区域撤销上一步操作
		var evt = window.event?window.event:evt;
        if(evt.keyCode==OpenLayers.Event.KEY_ESC){//键盘键 ESC 事件
        	that.drawBackToUpper();
        }
	});
}
/**修改热区图形*/
TQMap.prototype.modifyFeature = function(option){
	var that = this;
	var idfop={
			layer:"vector",
			orgId:null,
			modifyFeatureEndFunc: function(){},//修改结束事件
			dragFeatureEndFunc: function(){},//拖拽结束事件
			beforeSelectFeature:function(feature){},
			modifyEndFunc:function(){},
			modifyFunc:function(){},
			modifyStartFunc:function(feature){}
		};
	$.extend(idfop,option);
	that["history"].features = new Array();
	that.deactivateMeasureControl();	
	var measureControl=new OpenLayers.Control.ModifyFeature(that[idfop.layer],{
		modifyPoint:null,
		onModificationStart : function(feature){
			var control = that["measureControl"];
			var orgId = idfop.orgId;
			control.isModifyEnd = true;
			if(orgId!=null && feature.data.orgId!=orgId){
				feature = that.getFeaturesBy({ key:"data.orgId", value:orgId})[0];
				if(feature) control.selectFeature(feature);
				//control.isModifyEnd = false;
				//control.unselectFeature(feature);
				//control.activate();
			}
			idfop.modifyStartFunc(feature);
		},
		onModification : function(feature){
			this.historyFeature(feature);
			that["measureControl"].modifiedFeature = feature;//用于获取修改后的热区的数据
			idfop.dragFeatureEndFunc(feature,this.modifyPoint);
		},
		onModificationEnd : idfop.modifyFeatureEndFunc,
		beforeSelectFeature:function(feature){
			this.historyFeature(feature);
			idfop.beforeSelectFeature(feature);
			that["measureControl"].isModifyEnd = false;//为解决错误的编辑了非当前组织机构的热区
			OpenLayers.Control.ModifyFeature.prototype.beforeSelectFeature.apply(this, arguments);
		},
		dragVertex: function(vertex, pixel) {
			this.modifyPoint = this.map.getLonLatFromPixel(pixel);
			OpenLayers.Control.ModifyFeature.prototype.dragVertex.apply(this, arguments);
		},
		historyFeature:function(feature){
			var fea = feature.clone();
			fea.featureId = feature.featureId;
			fea.data = feature.data;
			that["history"].features.push(fea);
			return fea;
		}
	})
	measureControl.createVertices = true;
    
	that["map"].addControl(measureControl);
	measureControl.activate();
	that["measureControl"] = measureControl;
	$(document).bind("keydown",function(evt){//修改地图区域撤销上一步操作
		var evt = window.event?window.event:evt;
        if(evt.keyCode==OpenLayers.Event.KEY_ESC){//键盘键 ESC 事件
        	that.modifyBackToUpper();
        }
	});
}
/**不激活测量工具measureControl*/
TQMap.prototype.deactivateMeasureControl = function(){
	var that = this;
	var measureControl = that["measureControl"];
	if(measureControl && measureControl.id!=null){
		measureControl.deactivate();
 	}
	$(document).unbind("keydown");//画区域撤销上一步操作
 }
/**画区域撤销上一步操作*/
TQMap.prototype.drawBackToUpper = function(){
	var that = this;
	var measureControl = that["measureControl"];
	if(measureControl && measureControl.handler && measureControl.handler.polygon ){
		var length = measureControl.handler.polygon.geometry.components[0].components.length;
		if(length>3){
    		measureControl.handler.polygon.geometry.components[0].components.removeAt(length-3);
        	measureControl.handler.layer.redraw();
    	}else{
    		measureControl.cancel();
    	}
	}
}
/**修改地图区域撤销上一步操作*/
TQMap.prototype.modifyBackToUpper = function(){
	var that = this;
	var mc = that["measureControl"];
	var hi = that["history"];
	var layer = that["vector"];
	if(mc && layer && hi && hi.features && hi.features.length>1){
		mc.isModifyEnd = false;
		hi.features.pop();
		var prevFeature = hi.features.pop();
		if(prevFeature==null) return;
		var featureId = prevFeature.featureId;
		var feature = layer.getFeatureBy("featureId",featureId);
		if(prevFeature!=null && feature!=null){
			if(mc.feature!=null){
				mc.unselectFeature(feature);
			}
			layer.removeFeatures([feature]);
			layer.addFeatures([prevFeature]);
			mc.selectFeature(layer.getFeatureBy("featureId",featureId));
		}
	}
}


/**画图形（点、线、面、多边形）*/
TQMap.prototype.drawFeature = function(option){
	var that = this;
	var idfop={
		featureClickMapEnd: function(){},//点击结束事件
		pointStyle: {//所画点的默认样式
			pointRadius: 4,
			graphicName: "square",
			fillColor: "white",
			fillOpacity: 1,
			strokeWidth: 1,
			strokeOpacity: 1,
			strokeColor: "#333333"
		},
		lineStyle: {//所画线的默认样式
			strokeWidth: 4,
			strokeOpacity: 1,
			strokeColor: "#CC3399",
			strokeDashstyle: "dash"
		},
		polygonStyle: {//所画面的默认样式
			strokeWidth: 2,
			strokeOpacity: 1,
			strokeColor: "#CC3399",
			fillColor: "white",
			fillOpacity: 0.3
		},
		type: "line",//默认为画线，point为画点，polygon为画面，regularPolygon为画多边形
		sides: 3,//与画多边形（regularPolygon）绑定，数字为几，画出来的多边形的边数就为几
		irregular: true//默认画不规则形状，与画多边形（regularPolygon）绑定
	};
	$.extend(idfop,option);
		
    var style = new OpenLayers.Style();
    style.addRules([
        new OpenLayers.Rule({symbolizer: {
			"Point": idfop.pointStyle,
			"Line": idfop.lineStyle,
			"Polygon": idfop.polygonStyle
		}})
    ]);
    var styleMap = new OpenLayers.StyleMap({"default": style});
	var handlerType=OpenLayers.Handler.Path;//默认为画线
	if(idfop.type=="point"){
		handlerType=OpenLayers.Handler.Point;
	}else if(idfop.type=="polygon"){
		handlerType=OpenLayers.Handler.Polygon;
	}else if(idfop.type=="regularPolygon"){
		handlerType=OpenLayers.Handler.RegularPolygon;
	}
	var featureControl= new OpenLayers.Control.DrawFeature(that["vector"],handlerType,{
		handlerOptions: {holeModifier: "altKey"},
		featureAdded: idfop.featureClickMapEnd
	})
	
	that["map"].addControl(featureControl);
	if(idfop.type=="regularPolygon"){
		featureControl.handler.setOptions({sides: idfop.sides,irregular: idfop.irregular});
	}
	featureControl.activate();
	that["featureControl"] = featureControl;
}
/***/
TQMap.prototype.setLayerZIndex = function(option){
	if(!option.layerName||!option.zIndex)return false;
	var that = this;
	var layer = that[option.layerName];
	layer.setZIndex(option.zIndex);
}
/**添加marker*/
TQMap.prototype.addMarker = function(option){
	var that = this;
	var idfop={
		iconUrl: "/resource/images/markers/bangonglou.png",
		markerW:15,
		markerH:10,
		lon:0,
		lat:0,
		offset:null,   //marker偏移
		markerId:0,
		showMaxZoom:10,//设置图层激活后显示的最小级别
		showMinZoom:20,//设置图层激活后显示的最大级别
		zIndex:750,//设置markers图层的层级大小
		data:{},
		isOnMouseOverAndOut:false,
		isOnMouseOverMarkEnlarge:true,//mark图标放大
		layerName:"markers",
		onMouseover:function(marker){},
		onMouseout:function(marker){},
		onClick:null
	};
	$.extend(idfop,option);
	var layerName = idfop.layerName;
	var size = new OpenLayers.Size(idfop.markerW,idfop.markerH);
	var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	if(idfop.offset!=null){
		offset = idfop.offset;
	}
    var icon = new OpenLayers.Icon(idfop.iconUrl, size, offset);
    var marker=new OpenLayers.Marker(new OpenLayers.LonLat(idfop.lon,idfop.lat),icon);
    $(icon.imageDiv).css("cursor","pointer");
    marker.markerId=idfop.markerId;
    marker.data=idfop.data;
    var markerLayer = that[layerName];
    if(!markerLayer){
    	markerLayer = new OpenLayers.Layer.Markers( layerName,{
			maxResolution:(360/256)/Math.pow(2, idfop.showMaxZoom),
			minResolution:(360/256)/Math.pow(2, idfop.showMinZoom)});
		that["map"].addLayer(markerLayer);
		that[layerName] = markerLayer
	}
    if(markerLayer==null) return;
    markerLayer.setZIndex(idfop.zIndex);
    markerLayer.addMarker(marker);
    marker.icon.imageDiv.style.zIndex = idfop.zIndex;
    $(marker.icon.imageDiv).hover(function(){
    	$(this).css({zIndex:9999})
    },function(){
    	$(this).css({zIndex:idfop.zIndex})
    })
    if(idfop.isOnMouseOverAndOut){
	    marker.events.register("mouseover",marker,function(e){
			$(e.object.events.element).attr("title",e.object.data.title);
			e.object.setUrl(e.object.data.mouseoverImgUrl);
			if(idfop.isOnMouseOverMarkEnlarge)e.object.inflate(1.41);
			idfop.onMouseover(e.object);
		});
	    marker.events.register("mouseout",marker,function(e){
	    	e.object.setUrl(e.object.data.imgUrl);
	    	if(idfop.isOnMouseOverMarkEnlarge)e.object.inflate(1/1.41);
			idfop.onMouseout(e.object);
		});
    }
    if(idfop.onClick!=null && typeof idfop.onClick == "function"){
    	marker.events.register("click",marker,function(e){
    		idfop.onClick(e.object);
		});
    }
    that["marker"] = marker;
    return marker;
}
/**清除单个marker*/
TQMap.prototype.clearMarker = function() {
	var that = this;
	var marker = that["marker"];
	if(marker){
		marker.destroy();
		that["marker"] = null;
	}
}
/**清除markers及清除所有marker*/
TQMap.prototype.clearMarkers = function() {
	var that = this;
	var markers = that["markers"];
	if(markers){
		markers.destroy();
		that["markers"] = null;
	}
}
/**通过键值对获取marker*/
TQMap.prototype.getMarkerBy = function(option){
	var that = this;
	var idfop = $.extend({
		key:"markerId",
		value:"",
		layerName:"markers"
	},option)
	var layer = that[idfop.layerName];
	if(layer && idfop.key && idfop.value){
		var markers = layer.markers;
		for(var i = 0; i < markers.length; i++){
			if(markers[i].data[idfop.key]==idfop.value
					|| markers[i][idfop.key]==idfop.value){
				return markers[i];
			}
		}
 	}
}
/**通过自定义的markerId删除marker*/
TQMap.prototype.deleteMarkerByMarkerId = function(option){
	var that = this;
	var idfop={
		markerId:0
	};
	$.extend(idfop,option);
	var markers = that["markers"];
	if(markers && idfop.markerId){
		var marker_s = markers.markers.copy();
		for(var i = 0; i < marker_s.length; i++){
			if(marker_s[i].markerId==idfop.markerId){
				markers.removeMarker(marker_s[i]);
			}
		}
 	}
 }
/**通过自定义的markerId下划线后面的对象名称删除marker*/
TQMap.prototype.clearMarkersLikeMarkerId = function(markerId){
	var that = this;
	var markerLayer = that["markers"];
	if(markerLayer && markerId && markerLayer.markers.length>0){
		var marker_s = markerLayer.markers.copy();
		for(var i = 0; i < marker_s.length;i++){
			if(marker_s[i]!=null && marker_s[i].markerId!=null && (marker_s[i].markerId+"").indexOf(markerId)>=0){
				markerLayer.removeMarker(marker_s[i]);
			}
		}
 	}
 }

 /**获取无箭头的弹出框（可放文本充当Lable）*/
TQMap.prototype.addPopupText = function(option) {
	var that = this;
	var idfop={
			id:"chicken_map",
			lon:0,
			lat:0,
			popupText:"",
			popupW:300,
			popupH:200,
			backgroundColor:null,
			popupTextId:null,
			opacity:"1",
			data:null,
			isBgImg:false,
			autoSize:false,
			textColor:null,
			borderColor:""
	};
	$.extend(idfop,option);
	var popupText=new OpenLayers.Popup(idfop.id,new OpenLayers.LonLat(idfop.lon,idfop.lat),new OpenLayers.Size(idfop.popupW,idfop.popupH),idfop.popupText,null,true);
	popupText.isBgImg=idfop.isBgImg;
	popupText.backgroundColor = idfop.backgroundColor;
	popupText.opacity = idfop.opacity;
	popupText.popupTextId=idfop.popupTextId;
	popupText.autoSize=idfop.autoSize;
	popupText.data=idfop.data;//外部插入的外部对象,用时可以直接获取
	popupText.contentDiv.style.color=idfop.textColor;
	popupText.contentDiv.style.borderColor=idfop.borderColor;
	that["map"].addPopup(popupText);
	$(".popFrame").children("span").find("span").remove();
	that["popupText"] = popupText;
	return popupText;
}
/**删除充当leble的popup*/
TQMap.prototype.deletePopupText = function() {
	var that = this;
	var popupText = that["popupText"];
	if( popupText && popupText.id ){
		popupText.destroy();
		that["popupText"] = null;
	}
}
/**获取充当leble的popup*/
TQMap.prototype.getPopupTextById = function(id) {
	var that = this;
	var imap = that["map"];
	if(imap && imap.popups && imap.popups.length > 0){
		var length = imap.popups.length;
		for(var i=0;i<length ;i++){
			if(imap.popups[i].popupTextId==id){
				return imap.popups[i];
			}
		}
	}
	return null;
}
/**删除所有充当leble的popup*/
TQMap.prototype.deleteAllPopupText = function() {
	var that = this;
	var imap = that["map"];
	if(imap && imap.popups && imap.popups.length > 0){
		var length = imap.popups.length-1;
		for(var i=0;i<= length ;i++){
			var popupText = imap.popups[0];
			imap.removePopup(imap.popups[0]);
			popupText.destroy();
		}
	}
}

/**创建带箭头的Popup*/
TQMap.prototype.addPopup = function(option) {
	var that = this;
	var idfop={
			lon:null,
			lat:null,
			url:null,
			isDelete:true,
			lonlat:null,
			popupW:400,
			popupH:200,
			autoSize:false,//popup的默认自适应关闭
			closeBoxCallback:function(){
				this.destroy();
				$(".ui-multiselect-menu").hide();
			},
			load:function(){
				
			}
			
	};
	$.extend(idfop,option);
	if(idfop.isDelete){
		$.ajax({
			url:idfop.url,
			success:function(responseData){
				//删除后重建
				that.deletePopup();
				if(responseData){
					//获取无箭头的弹出框（可放文本充当Lable）
					//popup = new OpenLayers.Popup("chicken",new OpenLayers.LonLat(lon,lat),new OpenLayers.Size(300,200),""+responseData,false);
					var popup = new OpenLayers.Popup.FramedCloud("chicken",idfop.lonlat,new OpenLayers.Size(idfop.popupW,idfop.popupH),responseData,null,true,idfop.closeBoxCallback);
					popup.autoSize = idfop.autoSize;
					that["map"].addPopup(popup);
					that["popup"] = popup;
					idfop.load(popup);
				}
			},
			error:function(e){
				alert("系统错误！");
			}
		});
	}else{
		//重写加载弹出框内容
		$.ajax({
			url:url,
			success:function(responseData){
				var popup = that["popup"];
				if(popup){
					popup.setContentHTML(responseData);
					idfop.load();
				}
			}
		});
	}
}
/**删除带箭头的Popup*/
TQMap.prototype.deletePopup = function() {
	var that = this;
	var popup = that["popup"];
	if(popup && popup.id != null){
		popup.destroy();
		that["popup"] = null;
	}
}

/**创建自定义的SelectFeature(含Feature的事件和鼠标放上去样式)*/
TQMap.prototype.createSelectFeature = function(option){
	var that = this;
	var idfop={
			clickFeature:function(){},		//鼠标点击事件
			onSelect:function(){},			//鼠标放上去事件
			onUnselect:function(){},		//鼠标移开事件
			select_stroke:true,				//鼠标放上去是否显示边框
			select_strokeColor: "#3399ff",	//鼠标放上去边框颜色
			select_strokeWidth: 1,			//鼠标放上去边框像素
			select_strokeDashstyle: "solid",//鼠标放上去边框样式默认solid(实体)，[dot | dash | dashdot | longdash | longdashdot | solid]
			select_fill:true,				//鼠标放上去是否设置填充颜色
			select_fillOpacity : 0.4,		//鼠标放上去透明度0-1
			select_fillColor: "#66ccff",	//鼠标放上去默认颜色
			select_pointRadius: 6,			//鼠标放上去像素点的半径。默认值是6
			select_layer:that["vector"],//SelectFeature操作的图层
			select_name:"selectF",           //SelectFeature的名称
			select_map :that["map"]     //SelectFeature所属的map
	};
	$.extend(idfop,option);
	var selectFeature = that[idfop.select_name];
	if(!selectFeature){
		selectFeature = new OpenLayers.Control.SelectFeature(idfop.select_layer,{
			clickFeature:idfop.clickFeature,
			onSelect: function(feature){
				feature.layer.redraw();
				feature.layer.drawFeature(feature, this.selectStyle);
				idfop.onSelect(feature);
			},
			onUnselect: idfop.onUnselect,
	    	multiple: false, 
	    	hover: true,
	    	selectStyle: {
				stroke: idfop.select_stroke,
				strokeColor: idfop.select_strokeColor,
				strokeWidth: idfop.select_strokeWidth,
				strokeDashstyle: idfop.select_strokeDashstyle,
				fill: idfop.select_fill,
				fillOpacity : idfop.select_fillOpacity,
				fillColor: idfop.select_fillColor,
				pointRadius: idfop.select_pointRadius
			}
	    });
		idfop.select_map.addControl(selectFeature);
		that[idfop.select_name] = selectFeature;
	}
	return selectFeature;
}
/**拖动或编辑热区控件*/
TQMap.prototype.createDragPanFeature = function(option){
	var that = this;
	var idfop={
			clickFeature:function(data){},		//鼠标点击事件
			onSelect:function(data){},			//鼠标放上去事件
			onUnselect:function(data){},		//鼠标移开事件
			onMouseDown:function(e){},      //鼠标按下事件
			onMouseMove:function(e){},      //鼠标未按下移动事件
			onMouseUp:function(e){},        //鼠标释放按下事件
			stroke:true,				//鼠标放上去是否显示边框
			strokeColor: "#3399ff",	//鼠标放上去边框颜色
			strokeWidth: 1,			//鼠标放上去边框像素
			strokeDashstyle: "solid",//鼠标放上去边框样式默认solid(实体)，[dot | dash | dashdot | longdash | longdashdot | solid]
			fill:true,				//鼠标放上去是否设置填充颜色
			fillOpacity : 0.4,		//鼠标放上去透明度0-1
			fillColor: "#66ccff",	//鼠标放上去默认颜色
			pointRadius: 6,			//鼠标放上去像素点的半径。默认值是6
			layer:"wfslayer",             //SelectFeature操作的图层名称
			name:"wfsSelectF",            //SelectFeature的名称
			map :that["map"],              //SelectFeature所属的map
			backgroundGraphic:null,
			backgroundHeight:10,
			backgroundWidth:10,
			multiple:false,                                //允许选择多个地物
			toggle:false,                                  //单击当前选中的要素时，是否取消其选中状态
			clickout:true,                                //是否在地物之外点击时，取消选择地物
			hover:true                                    //在鼠标悬浮在地物上时，选中地物；移出地物时，取消选中。
	};
	$.extend(idfop,option);
	var selectFeature = that[idfop.name];
	var layer = that[idfop.layer];
	if(selectFeature){
		selectFeature.deactivate();
		selectFeature.destroy();
		that[idfop.name] = null;
	}
	selectFeature = new OpenLayers.Control.TQDragPanFeature(layer,{
		clickFeature:function(data){
			OpenLayers.Control.SelectFeature.prototype.clickFeature.apply(this, arguments);
			idfop.clickFeature(data);
		},
		onSelect:function(data){
			OpenLayers.Control.SelectFeature.prototype.onSelect.apply(this, arguments);
			idfop.onSelect(data);
			clearMousemoveFunc();
			idfop.map.events.register("mousemove",idfop.map,onMouseMoveFunc);
			that["mousemove"] = onMouseMoveFunc;
			if (this.dragPan){
				this.dragPan.deactivate();
				this.dragPan.activate();
			}
		},
		onUnselect:function(data){
			OpenLayers.Control.SelectFeature.prototype.onUnselect.apply(this, arguments);
			idfop.onUnselect(data);
			clearMousemoveFunc();
			if (this.dragPan) this.dragPan.deactivate();
		},
		onMouseDown:idfop.onMouseDown,
		onMouseUp:idfop.onMouseUp,
		clickout: idfop.multiple, 
		toggle: idfop.toggle,
        multiple: idfop.multiple, 
        hover: idfop.hover,
        toggleKey: "ctrlKey", // ctrl key removes from selection
        multipleKey: "shiftKey", // shift key adds to selection
        box: false,
    	selectStyle: {
			stroke: idfop.stroke,
			strokeColor: idfop.strokeColor,
			strokeWidth: idfop.strokeWidth,
			strokeDashstyle: idfop.strokeDashstyle,
			fill: idfop.fill,
			fillOpacity : idfop.fillOpacity,
			fillColor: idfop.fillColor,
			pointRadius: idfop.pointRadius,
			backgroundGraphic:idfop.backgroundGraphic,
			backgroundHeight:idfop.backgroundHeight,
			backgroundWidth:idfop.backgroundWidth
		}
    });
	idfop.map.addControl(selectFeature);
	that[idfop.name] = selectFeature;
	selectFeature.activate();
	function onMouseMoveFunc(e){
		idfop.onMouseMove(e,OpenLayers.Event.isLeftClick(e))
	}
	function clearMousemoveFunc(){
		for(var i=0;i<idfop.map.events.listeners.mousemove.length;i++){
			if(idfop.map.events.listeners.mousemove[i].func.hasOwnProperty("getName") 
					&& idfop.map.events.listeners.mousemove[i].func.getName().indexOf("onMouseMoveFunc")>=0){
				idfop.map.events.listeners.mousemove.removeAt(i);
				idfop.map.events.unregister("mousemove",idfop.map,onMouseMoveFunc);
			}
		}
		idfop.map.events.unregister("mousemove",idfop.map,onMouseMoveFunc);
		that["mousemove"] = null;
	}
}
/**销毁自定义的SelectFeature*/
TQMap.prototype.destroySelectFeature = function(option){
	var that = this;
	var idfop={ sfname: "selectF" }
	$.extend(idfop,option);
	var sf = that[idfop.sfname];
	if(sf  && sf!="undefined" && sf.id!=null){
		sf.destroy();
 		that[idfop.sfname] = null;
 	}
}
/**激活自定义的SelectFeature*/
TQMap.prototype.activateSelectFeature = function(option){
	var that = this;
	var idfop={ sfname: "selectF" }
	$.extend(idfop,option);
	var sf = that[idfop.sfname];
 	if(sf!=null && sf!="undefined" && sf.id!=null){
 		sf.activate();
 	}
}
/**不激活自定义的SelectFeature*/
TQMap.prototype.deactivateSelectFeature = function(option){
	var that = this;
	var idfop={ sfname: "selectF" }
	$.extend(idfop,option);
	var sf = that[idfop.sfname];
 	if(sf!=null && sf!="undefined" && sf.id!=null){
 		sf.deactivate();
 	}
 }
/**新增热区*/
TQMap.prototype.featureShow = function(option){
	var that = this;
	var idfop={
			async:true,
			geometry:null,                  
			points:null,					//坐标集
			featureId:null,					//自定义featureId
			stroke:true,					//是否显示边框
			strokeColor: "#ee9900",			//边框颜色
			strokeWidth: 1,					//边框像素
			strokeDashstyle: "solid",		//边框样式默认solid(实体)，[dot | dash | dashdot | longdash | longdashdot | solid]
			fill:true,						//是否设置填充颜色
			fillOpacity :0.4,				//透明度0-1
			fillColor: "#ee9900",			//默认颜色
			pointRadius: 6,					//像素点的半径。默认值是6
			data:{},
			layer:"vector",                                //热区图层名称
			backgroundGraphic:null,
			backgroundHeight:10,
			backgroundWidth:10,
			cursor:"",
			fontColor:"red"
	};
	$.extend(idfop,option);
	function addFunc(idfop){
		var original = idfop.geometry;
		if(original==null){
			original = OpenLayers.Geometry.fromWKT(idfop.points);
		}
		var fVector = new OpenLayers.Feature.Vector(original,$.extend({},idfop.data),{
			stroke: idfop.stroke,
			strokeColor: idfop.strokeColor,
			strokeWidth: idfop.strokeWidth,
			strokeDashstyle: idfop.strokeDashstyle,
			fill: idfop.fill,
			fillOpacity : idfop.fillOpacity,
			fillColor: idfop.fillColor,
			pointRadius: idfop.pointRadius,
			label:idfop.data.name,
			fontColor:idfop.fontColor,
			fontSize:15,
			fontFamily:"Microsoft Yahei",
			labelOutlineColor:'red',
			labelOutlineWidth:1,
			labelOutlineOpacity:0.3,
			backgroundGraphic:idfop.backgroundGraphic,
			backgroundHeight:idfop.backgroundHeight,
			backgroundWidth:idfop.backgroundWidth,
			cursor:idfop.cursor
		});
		fVector.featureId=idfop.featureId;
		fVector.data=idfop.data;
		that[idfop.layer].addFeatures([fVector]);
		return fVector;
	}
	return addFunc(idfop);
}

////显示自定义的Feature
//function featureShow(option){
//	var idfop={
//			points:null,					//坐标集
//			featureId:null,					//自定义featureId
//			stroke:true,					//是否显示边框
//			strokeColor: "#ee9900",			//边框颜色
//			strokeWidth: 1,					//边框像素
//			strokeDashstyle: "solid",		//边框样式默认solid(实体)，[dot | dash | dashdot | longdash | longdashdot | solid]
//			fill:true,						//是否设置填充颜色
//			fillOpacity : 0.4,				//透明度0-1
//			fillColor: "#ee9900",			//默认颜色
//			pointRadius: 6,					//像素点的半径。默认值是6
//			data:null
//	};
//	$.extend(idfop,option);
//	
//	var original = OpenLayers.Geometry.fromWKT(idfop.points);
//	var fVector = new OpenLayers.Feature.Vector(original,null,{
//		stroke: idfop.stroke,
//		strokeColor: idfop.strokeColor,
//		strokeWidth: idfop.strokeWidth,
//		strokeDashstyle: idfop.strokeDashstyle,
//		fill: idfop.fill,
//		fillOpacity : idfop.fillOpacity,
//		fillColor: idfop.fillColor,
//		pointRadius: idfop.pointRadius
//	});
//	fVector.featureId=idfop.featureId;
//	fVector.data=idfop.data;
//	that["vector"].addFeatures([fVector]);
//	return fVector;
//}
/**规范热区信息，规范取值*/
TQMap.prototype.getInfoFromFeature = function(feature){
	var that = this;
	if(!feature) return null;
	var XY = feature.geometry.getCentroid();//获取质心（矩心/面心）
	//var lonlat = feature.geometry.bounds.getCenterLonLat();//获取几何中心点
	var lonlat = new OpenLayers.LonLat(XY.x,XY.y);
	var attributes = {};
	if(feature.attributes){
		attributes = {
			id:feature.attributes[that["WFSOption"].featureId],
			name:feature.attributes[that["WFSOption"].featureName]  //获取建筑物名称
		}
	}
	return $.extend(feature, lonlat, attributes);
}
/**根据键值获取Feature数据*/
TQMap.prototype.getFeaturesBy = function(option){
	var that = this;
	var idfop = $.extend({ key:"", value:"", layer:"vector" },option)
	var layer = that[idfop.layer];
	var result = [];
	if(layer && idfop.key && idfop.value){
		var keys = idfop.key.split("."),
			features = layer.features,
			compareValue=null;
		for(var i = 0; i < features.length; i++){
			compareValue  = features[i];
			for(var j=0;j<keys.length;j++){
				if(compareValue==null) break;
				compareValue = compareValue[keys[j]];
			}
			if(compareValue==idfop.value){
				result.push(features[i]);
			}
		}
 	}
	return result;
}
/**删除所有自定义的Feature*/
TQMap.prototype.removeAllFeatures = function(option) {
	var that = this;
	var idfop={ sfname: "vector" }
	$.extend(idfop,option);
	var sf = that[idfop.sfname];
	if(sf!=null && sf!="undefined" && sf.id!=null){
		sf.removeAllFeatures();
	}
}
/**通过自定义的featureId删除feature*/
TQMap.prototype.deleteFeatureByFeatureId = function(option){
	var that = this;
	var idfop={
		featureId:null,
		layer:"vector"
	};
	$.extend(idfop,option);
	var layer = that[idfop.layer];
	if(layer && idfop.featureId && layer.features.length>0){
		var features = layer.features.copy();
		for(var i = 0; i < features.length; i++){
			if(features[i].featureId==idfop.featureId){
				layer.destroyFeatures(features[i]);
			}
		}
 	}
 }
/**清除热区图层的包含id的热区*/
TQMap.prototype.clearFeatureLikeFeatureId = function(option){
	var that = this;
	var idfop = $.extend({ 
		featureId:null,
		layer:"vector" 
	},option);
	var layer = that[idfop.layer];
	if(layer && idfop.featureId && layer.features.length>0){
		for(var i = 0; i < layer.features.length; i++){
			var featureId = layer.features[i].featureId;
			if( featureId &&(featureId+"").indexOf(idfop.featureId+"")>=0){
				layer.destroyFeatures(layer.features[i]);
				i--;
			}
		}
	}
}
/**新增label文本*/
TQMap.prototype.addLabel = function(option){
	var that = this;
	var idfop={
			lon:null,
			lat:null,
			labelId:null,
			label:"",
			data:{}
	};
	$.extend(idfop,option);
	if(!idfop.lon || !idfop.lat) return;
	if(!idfop.labelId) idfop.labelId = "labelF_"+idfop.lon;
	idfop.data.name = idfop.label;
	$.extend(idfop,{
		geometry:new OpenLayers.Geometry.Point(idfop.lon,idfop.lat),
		featureId:idfop.labelId,
		stroke:false,
		fill:false
	});
	that.featureShow(idfop);
}
/**移除label文本*/
TQMap.prototype.clearLabelLikeLabelId = function(option){
	var that = this;
	var idfop = $.extend({ 
		labelId:"",
		layer:"vector" 
	},option);
	$.extend(idfop,{  featureId:idfop.labelId });
	that.clearFeatureLikeFeatureId(idfop);
}
/**添加加载中控件*/
TQMap.prototype.addLoadingPanel = function(option){
	var that = this;
	var loadingPanel = that["map"].getControlsBy("displayClass","olControlTQLoadingPanel")[0];
	if(loadingPanel) loadingPanel.activate();
	return false;
}
/**移除加载中控件*/
TQMap.prototype.removeLoadingPanel = function(option){
	var that = this;
	var loadingPanel = that["map"].getControlsBy("displayClass","olControlTQLoadingPanel")[0];
	if(loadingPanel) loadingPanel.deactivate();
}
/**注册事件*/
TQMap.prototype.registerEvent = function(option){
	var that = this;
	var idfop={
		objectName:"map",
		eventName:"click",
		func:function(){}
	};
	$.extend(idfop,option);
	var object = that[idfop.objectName];
	if(object==null) return;
	object.events.register(idfop.eventName,object,idfop.func);
}
/**销毁事件*/
TQMap.prototype.unregisterEvent = function(option){
	var that = this;
	var idfop={
		objectName:"map",
		eventName:"click",
		func:function(){}
	};
	$.extend(idfop,option);
	that[idfop.objectName].events.unregister(idfop.eventName,that[idfop.objectName],idfop.func);
}
/**移除事件*/
TQMap.prototype.removeEvent = function(option){
	var that = this;
	var idfop={
		objectName:"map",
		eventName:"click"
	};
	$.extend(idfop,option);
	that[idfop.objectName].events.remove(idfop.eventName);
}
/**移动跟随*/
TQMap.prototype.moveTo = function(option){
	var that = this;
	var idfop={
		e:null,
		objectName:"map"
	};
	$.extend(idfop,option);
	that[idfop.objectName].moveTo(that["map"].getLayerPxFromLonLat(that["map"].getLonLatFromPixel(idfop.e.xy)));
}
/**添加鼠标提示信息*/
TQMap.prototype.addMouseTip = function(option){
	var that = this;
	var idfop={
		evt:null,
		msg:"",
		style:{
			position:"absolute",
        	'z-index':'999',
        	border:'1px solid #FF0103',
        	padding:'3px 5px',
        	background:'white'
		}
	};
	$.extend(idfop,option);
	var moveTitleDiv;
	if($("#MouseTitle")[0]){
		moveTitleDiv=$("#MouseTitle");
	}else{
		moveTitleDiv=$("<div />").attr("id",'MouseTitle').addClass("MouseTitle");
	}
    $(that["map"].viewPortDiv).append(moveTitleDiv);
    //msg = "单击确定起点，双击结束量算";
    var offset = $("#"+that.mapId).offset();
    var ObjX,ObjY,mouseX = offset.left-15,mouseY = offset.top+15;
    if (idfop.evt.pageX || idfop.evt.pageY) {//firefox鼠标的位置
		ObjX = idfop.evt.pageX;
		ObjY = idfop.evt.pageY;
	} else {//IE鼠标的位置
		ObjX = idfop.evt.clientX + document.documentElement.scrollLeft - document.body.clientLeft;
		ObjY = idfop.evt.clientY + document.documentElement.scrollTop - document.body.clientTop;
	}
	moveTitleDiv.html(idfop.msg).css(idfop.style).css({
		left:ObjX - mouseX,
    	top:ObjY - mouseY
    })
}
/**删除鼠标提示信息*/
TQMap.prototype.deleteMouseTip = function(){
	$("#MouseTitle").remove();
}
/**根据半径和中心点画圆*/
TQMap.prototype.drawCircle = function(option){
	var that = this;
	var idfop={
		layer:"vector",
		lon:0.0,                     	//圆心经度
		lat:0.0,                        //圆心纬度
		redius:200,                   	//类型：float，半径
		sides:40,                     	//-类型：Integer，边数  
		rotation:0, 					//在原来的旋转角度
		stroke:true, 					//是否显示边框
		strokeColor: "#ee9900", 		//边框颜色
		strokeWidth: 1, 				//边框像素
		strokeDashstyle: "solid", 		//边框样式默认solid(实体)，[dot | dash | dashdot | longdash | longdashdot | solid]
		fill:true, 						//是否设置填充颜色
		fillOpacity : 0.4, 				//透明度0-1
		fillColor: "#ee9900", 			//默认颜色
		pointRadius: 6     				//像素点的半径。默认值是6
	};
	$.extend(idfop,option);
	var point=new OpenLayers.Geometry.Point(idfop.lon,idfop.lat);
	var circle = new OpenLayers.Geometry.Polygon.createRegularPolygon(point,idfop.redius/100000,idfop.sides,idfop.rotation);
	var fVector = new OpenLayers.Feature.Vector(circle,null,{
		stroke: idfop.stroke,
		strokeColor: idfop.strokeColor,
		strokeWidth: idfop.strokeWidth,
		strokeDashstyle: idfop.strokeDashstyle,
		fill: idfop.fill,
		fillOpacity : idfop.fillOpacity,
		fillColor: idfop.fillColor,
		pointRadius: idfop.pointRadius
	});
	that[idfop.layer].addFeatures([fVector]);
}
/*-------------------------------------------------------------------------------------------------------------------------------------*/
/**定位到中心点*/
TQMap.prototype.zoomTo = function(option){
	var that = this;
	var idfop={
			lon: that.lon,
			lat: that.lat,
			zoom: that.zoom,
			left: that.boundsMinLon,
			right: that.boundsMaxLon,
			bottom: that.boundsMinLat,
			top: that.boundsMaxLat
	}
	$.extend(idfop,option);
	var imap = that["map"];
	if(option.lon!=null && option.lat!=null){
		imap.setCenter(new OpenLayers.LonLat(idfop.lon,idfop.lat), idfop.zoom);
	}else{
		imap.zoomToExtent(new OpenLayers.Bounds(idfop.left,idfop.bottom,idfop.right,idfop.top));
		imap.zoomTo(idfop.zoom)
	}
};
/**查找PanZoomBar中orgZoom的zoom*/
TQMap.prototype.getOrgZoom = function(option){
	var that = this;
	var idfop={                   
		zoom:0,                   //当前组织结构的zoom
		ascNum:2,                 //
		type:""                   //PanZoomBar的底端(minZoom),PanZoomBar的顶端（maxZoom）
	}
	$.extend(idfop,option);
	var iorgZoom = that["orgZoom"];
	if(iorgZoom!=null && iorgZoom.length>0){
		if(idfop.type=="minZoom") return iorgZoom[iorgZoom.length-1].zoom;
		if(idfop.type=="maxZoom") return iorgZoom[0].zoom;
		for(var i=iorgZoom.length-1;i>=0;i--){
			if(iorgZoom[i].zoom == idfop.zoom){
				if((i-1)>=0) return iorgZoom[i-1].zoom;
			}
		}
	}
	return idfop.zoom + idfop.ascNum;
}
/**视屏查询*/
TQMap.prototype.queryServiceVideos = function(option){
	var that = this;
	var idfop={
			name:"",
			geometry:"",
			geometryType:"esriGeometryEnvelope", //参数esriGeometryPoint，esriGeometryPolyline，esriGeometryPolygon，esriGeometryMultipoint
			where:"",
			returnIdsOnly:false,
			returnGeometry:true,
			outFields:'*',
			success:function(data){}
		}
	$.extend(idfop,option);
	$.ajax({
		async:false,
		type:"post",
		dataType:"jsonp",
		url:that["videoServicePath"],
		data:{
			f:'pjson',
			text:idfop.name,
			geometry:idfop.geometry,
			geometryType:idfop.geometryType,
			where:idfop.where,
			returnIdsOnly:idfop.returnIdsOnly,
			returnGeometry:idfop.returnGeometry,
			outFields:idfop.outFields
		},
		success: idfop.success
	})
}