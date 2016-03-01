(function(){
	var path = PATH + "/resource/openLayersMap";
	var jsFiles = [
       //图层类 
	   "/js/TQMap/Layer/TQPopups.js",
	   "/js/TQMap/Layer/TQTileCache.js",
	   "/js/TQMap/Layer/GeoTile.js",
	   "/js/TQMap/Layer/ModifiedXYZ.js",
	   "/js/TQMap/Layer/SuperMapCloud.js",                //超图图层
	   //控件类
	   "/js/TQMap/Control/TQCompass.js",                    //指南针
	   "/js/TQMap/Control/TQDragPanFeature.js",         //热区操作
	   "/js/TQMap/Control/TQGisType.js",                      //图层切换
	   "/js/TQMap/Control/TQNavigation.js",                 //导航
	   "/js/TQMap/Control/TQOverviewMap.js",            //鹰眼
	   "/js/TQMap/Control/TQPanZoomBar.js",              //平移缩放
	   "/js/TQMap/Control/TQLoadingPanel.js",             //加载进行中控件
	   //工具类
	   "/js/TQMap/Util/TQUtil.js",
	   "/js/TQMap/Util/TQConvert.js"                           //转换工具,包括将坐标转换为同一个展示字段
	]; // etc.
	var cssFiles=[
	   "/css/tqOpenLayers.css"
	];
	for (var i=0, len=jsFiles.length; i<len; i++) {
		var domscript = document.createElement('script');
		domscript.src = path + jsFiles[i];
		//document.getElementsByTagName('head')[0].appendChild(domscript);
		LoadResource.get(path + jsFiles[i]);
	}
	for (var i=0, len=cssFiles.length; i<len; i++) {
		var domcss = document.createElement('link');
		domcss.rel = "stylesheet";
		domcss.href = path + cssFiles[i];
		document.getElementsByTagName('head')[0].appendChild(domcss);
	}
})()