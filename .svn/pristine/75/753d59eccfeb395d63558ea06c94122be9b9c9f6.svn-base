/**
 * 平移缩放工具栏重写
 */
/**
 * 平移缩放工具栏重写
 */
OpenLayers.Control.TQPanZoomBar = OpenLayers.Class(OpenLayers.Control.PanZoomBar, {
	'zoomWorldIcon':true,
	lon:null,
	lat:null,
	zoom:null,
	isZoomBarDrag:true,
	position: new OpenLayers.Pixel(20, 20),
	zoomStopWidth:31,
	zoomStopHeight:16,
	//GRID:片组片格,VILLAGE：村，TOWN：镇、街道，DISTRICT：区，CITY：市，PROVINCE：省，COUNTRY：全国 
	orgZoom:[],//Array element like {title:'网格',name:'GRID',zoom:8}
	isCanZoomDown:function(){return true},
	orgStyle:{},
	draw:function(px){
		var that=this;
		OpenLayers.Control.prototype.draw.apply(this, arguments);
		px = this.position.clone();
		this.buttons = [];
		var sz = {w: 8, h: 13};
		var zoomworld;
		if (this.panIcons) {
			var centered = new OpenLayers.Pixel(px.x+sz.w/2, px.y);
			var wposition = sz.w;
			//if (this.zoomWorldIcon) {
				centered = new OpenLayers.Pixel(px.x+sz.w, px.y);
			//}
			this._addButton("circle", "circle.png", {x:centered.x-18,y:centered.y-10}, {w:sz.w+51,h:sz.h+47});
			this._addButton("panup", "north-mini.png", {x:centered.x+4,y:centered.y-5}, {w:sz.w+7,h:sz.h+4});
			this._addButton("pandown", "south-mini.png", {x:centered.x+4,y:centered.y+26}, {w:sz.w+7,h:sz.h+4});
			this._addButton("panleft", "west-mini.png", {x:centered.x-15,y:centered.y+14}, {w:sz.w+10,h:sz.h-1});
			this._addButton("panright", "east-mini.png", {x:centered.x+17,y:centered.y+14}, {w:sz.w+10,h:sz.h-1});
			px.y = centered.y+sz.h;
			this._addButton("zoomin", "zoom-plus-mini.png",{x:centered.x-4,y:centered.y+53}, {w:sz.w+23,h:sz.h+4});
			if (this.zoomWorldIcon) {
				zoomworld=this._addButton("zoomworld", "zoom-world-new.png",{x:centered.x+2,y:centered.y+12}, {w:sz.w+9,h:sz.h+3});
			}
			centered = this._addZoomBar(centered.add(-4, sz.h*5 + 5));
			this._addButton("zoomout", "zoom-minus-mini.png",centered,{w:sz.w+23,h:sz.h+4});
		}else {
			this._addButton("zoomin", "zoom-plus-mini.png", px, sz);
			centered = this._addZoomBar(px.add(0, sz.h));
			this._addButton("zoomout", "zoom-minus-mini.png", centered, sz);
			if (this.zoomWorldIcon) {
				centered = centered.add(0, sz.h+3);
				zoomworld=this._addButton("zoomworld", "zoom-world-mini.png", centered, sz);
			}
		}
		
		var id = this.id + "_" + this.map.id;
		var imgLocation = OpenLayers.Util.getImagesLocation();
		var orgZoom=this.orgZoom;
		var $thatToolBar=$(this.div);
		var topHeight=that.position.clone().y+orgZoom[0].zoom*that.zoomStopHeight+100;
		var $orgBox=$('<ul class="orgDiv"></ul>').css($.extend({
			top:100,
			left:50
		},that.orgStyle));
		function orgZoomFunction(){
			var zoom=null;
			for(var i=0;i<orgZoom.length;i++){
				if(orgZoom[i]!=null&&orgZoom[i]!=""){
					var thisOrg=$("<li><a href='javascript:;'><span>"+orgZoom[i].title+"</span></a></li>");
					thisOrg.attr("id","org_"+orgZoom[i].name).attr("zoom",orgZoom[i].zoom).addClass("orgDom").css("bottom",orgZoom[i].zoom*that.zoomStopHeight+that.position.clone().y).click(function(){
						zoom = $(this).attr("zoom");
						if(!that.isCanZoomDown({nowZoom:zoom})) return;
						that.map.zoomTo($(this).attr("zoom"));
					});
					$orgBox.append(thisOrg);
				}
			}
			$thatToolBar.hover(function(){
				setTimeout(function() {
					if($thatToolBar.children("ul.orgDiv").length==0){
						$thatToolBar.append($orgBox);
					}
					$orgBox.show();
				}, 300);
			},function(){
				$orgBox.hide();
			})
		}
		orgZoomFunction();
		$(zoomworld).attr("title","返回默认页面");
		return this.div;
	},
	onButtonClick: function (evt) {
		var btn = evt.buttonElement;
        switch (btn.action) {
            case "panup": 
                this.map.pan(0, -this.getSlideFactor("h"));
                break;
            case "pandown": 
                this.map.pan(0, this.getSlideFactor("h"));
                break;
            case "panleft": 
                this.map.pan(-this.getSlideFactor("w"), 0);
                break;
            case "panright": 
                this.map.pan(this.getSlideFactor("w"), 0);
                break;
            case "zoomin": 
                this.map.zoomIn(); 
                break;
            case "zoomout": 
            	var newZoom = this.map.getZoom();
				if(!this.isCanZoomDown({nowZoom:newZoom-2})) return;
				this.map.zoomOut(); 
				break;
            case "zoomworld": 
            	this.map.setCenter(new OpenLayers.LonLat(this.lon,this.lat), this.zoom);
				//this.map.zoomToMaxExtent();//放大到最大级别
				break;
        }
	},
	divClick: function(a) {
		if (OpenLayers.Event.isLeftClick(a)) {
            var b = a.xy.y / this.zoomStopHeight;
            if (this.forceFixedZoomLevel || !this.map.fractionalZoom) b = Math.floor(b);
            b = this.map.getNumZoomLevels() - 1 - b;
            b = Math.min(Math.max(b, 0), this.map.getNumZoomLevels() - 1);
            if(!this.isCanZoomDown({nowZoom:b})) return;
            this.map.zoomTo(b);
            OpenLayers.Event.stop(a)
        }
    },
//    zoomBarDrag: function(a) {
//    	var nowZoom = a.xy.y / this.zoomStopHeight;
//    	nowZoom = parseInt(this.map.getNumZoomLevels() - 2 - nowZoom);
//        if (!this.isCanZoomDown({nowZoom:nowZoom-2})) return;
//        OpenLayers.Control.PanZoomBar.prototype.zoomBarDrag.apply(this, arguments);
//    }
    zoomBarUp:function(evt) {
        if (!OpenLayers.Event.isLeftClick(evt) && evt.type !== "touchend") {
            return;
        }
        if (this.mouseDragStart) {
            this.div.style.cursor="";
            this.map.events.un({
                "touchmove": this.passEventToSlider,
                "mouseup": this.passEventToSlider,
                "mousemove": this.passEventToSlider,
                scope: this
            });
            var zoomLevel = this.map.zoom;
            if (!this.forceFixedZoomLevel && this.map.fractionalZoom) {
                zoomLevel += this.deltaY/this.zoomStopHeight;
                zoomLevel = Math.min(Math.max(zoomLevel, 0), 
                                     this.map.getNumZoomLevels() - 1);
            } else {
                zoomLevel += this.deltaY/this.zoomStopHeight;
                zoomLevel = Math.max(Math.round(zoomLevel), 0);      
            }
            if(this.isCanZoomDown({nowZoom:zoomLevel-1})){
            	this.map.zoomTo(zoomLevel);
            }else{
            	var newTop = 
                    ((this.map.getNumZoomLevels()-1) - this.map.getZoom()) * 
                    this.zoomStopHeight + this.startTop + 1;
                this.slider.style.top = newTop + "px";
            	this.map.zoomTo(this.zoom);
            }
            this.mouseDragStart = null;
            this.zoomStart = null;
            this.deltaY = 0;
            OpenLayers.Event.stop(evt);
        }
    },
    CLASS_NAME:"OpenLayers.Control.TQPanZoomBar"
})