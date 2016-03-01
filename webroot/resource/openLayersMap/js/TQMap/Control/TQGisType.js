/***
 * 图层切换控件工具
 */
OpenLayers.Control.TQGisType = OpenLayers.Class(OpenLayers.Control, {
	autoActivate:true,    //是否自动激活
	top:-30,
	right:0,
	mapWidth:56,
	gisType:"2D",
	showType:["2D","3D","MS"],
	style:{},
	embedH:500,
	embedW:500,
	changeBeforFunc:function(prevGisType,targitGisType){},
	changingFunc:function(prevGisType,targitGisType){},
	changeAfterFunc:function(prevGisType,targitGisType){},
	onClick:function(){},
	layerSwitch:function(option){
		var that = this;
		var idfop={ type:"3D" };
		$.extend(idfop,option);
		var layerName = idfop.type + "Layer";
		var layers = that.map.getLayersByName(layerName);
		for(var i=0;layers && i<layers.length;i++){
			var layer = layers[i];
			if(layer.isBaseLayer){
				that.map.setBaseLayer(layer);
				break;
			}
		}
	},
	initialize: function (options) {
		this.extendOptions = options;
		OpenLayers.Control.prototype.initialize.apply(this, arguments);
    },
	draw:function(){
    	var that = this;
		this.div=$("<div/>").empty()
			.append('<div id="mapChangeDiv"/>')
			.append(
				$("<div class='olControlGisType clearfix'/>").append(
						$("<ul class='mapChangeTools clearfix'>").append(
								$("<li id='2D'><div class='img'><div class='map2D'></div><div class='text'>2D</div></div></li>")
							).append(
								$("<li id='MS'><div class='img'><div class='mapMS'></div><div class='text'>影象</div></div></li>")
							).append(
								$("<li id='3D'><div class='img'><div class='map3D'></div><div class='text'>2.5D</div></div></li>")
							)
				)
		).get(0);
		 this.div.className = this.displayClass;
		return this.div;
	},
	activate:function(){
		var that = this;
		var $tool = $(".mapChangeTools");
		$tool.find("li").hide();
		for(var i=0;i<that.showType.length;i++){
			$tool.find("li#"+that.showType[i]).show();
		}
		$(".mapChangeTools li:hidden").remove();
		var timer;
		var mapChangeToolsLength=$(".mapChangeTools li").length;
		var layoutWidth=130;
		var mapChangeToolsWidth=that.mapWidth*mapChangeToolsLength+2;
		$tool.css({"top":that.top,"right":that.right});
		that.changeLi(that.gisType);
		$(".mapChangeTools li").click(function(){
			that.changeLi(this.id);
			that.gisTypeChange(this.id);
		})
		$tool.hover(function(event){
			clearTimeout(timer);
			$(this).width(114);
			$tool.stop(true,true).animate({width:mapChangeToolsWidth+'px'},400);
		},function(event){
			timer=setTimeout(function(){
				$tool.stop(true,true).animate({width:56+'px'},400);
			},400)
		})
		OpenLayers.Control.prototype.activate.apply(this, arguments);
	},
	redraw:function(option){
    	$(".olControlTQGisType").remove();
    	var div = this.draw();
        if (div) {
            if(!this.outsideViewport) {
                div.style.zIndex = this.map.Z_INDEX_BASE['Control'] +
                                    this.map.controls.length;
                this.map.viewPortDiv.appendChild( div );
            }
        }
        if(this.autoActivate) {
            this.activate();
        }
	},
	changeLi:function(liId){
		var that = this;
		this.onClick(liId);
		this.map.gisType = liId;
		$(".mapChangeTools #"+liId).prependTo(".mapChangeTools");
		$(".mapChangeTools li").each(function(i){
			$(this).css({ right:that.mapWidth*i+1 })
		})
	},
	changeTool:function(liId){
		var that = this;
		that.changeLi(liId);
		that.gisType = liId;
	},
	changeGis:function(liId){
		$(".mapChangeTools #"+liId).click();
	},
	gisTypeChange:function(targitGisType){//2D,3D等地图之间的转换
		var that = this;			
		var $style = $("#mapChangeDiv");
		var prevGisType = that.gisType;
		that.gisType = targitGisType;
		if(prevGisType == targitGisType) return;
		that.changeBeforFunc(prevGisType,targitGisType);
		$style.html($('<div class=""><EMBED align=center src="'+PATH+'/resource/openLayersMap/images/flash/'+((targitGisType=="3D")?"2dto3d":"3dto2d")+'.swf" style="width:'+that.embedW+'px;height:'+that.embedH+'px;" type=application/x-shockwave-flash wmode="transparent" quality="high"></EMBED></div>')
				.css($.extend({
					"text-align": "center","z-index": 9999,width:"100%",height:"100%",background: "#000",
					opacity: 0.5,filter: "alpha(opacity=50)",position: "absolute",top: "0px"
				},that.style)));
		that.layerSwitch({type:targitGisType});//2D,3D等地图之间的转换		
		that.changingFunc(prevGisType,targitGisType);
		
		setTimeout(function() {
			$style.html("");
		}, 1500);
		that.changeAfterFunc(prevGisType,targitGisType);	
	},
    CLASS_NAME: "OpenLayers.Control.TQGisType"
});