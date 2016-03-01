/***
 * 加载中、加载进度条控件
 */
OpenLayers.Control.TQLoadingPanel = OpenLayers.Class(OpenLayers.Control, {
	imgUrl:"/resource/openLayersMap/images/loading_big.gif",
	imgW: 130,
	imgH: 130,
	backgroundColor:"#000",
	opacity: 0.3,
	autoActivate:false,    //是否自动激活
    draw: function() {
        OpenLayers.Control.prototype.draw.apply(this, arguments);
        if(this.imgUrl){
        	var img = document.createElement("img");
        	img.src = this.imgUrl;
        	if(this.imgW) img.style.width=this.imgW+"px";
        	if(this.imgH) img.style.height=this.imgH+"px";
        	img.className = this.displayClass + "Img";
        	this.div.appendChild(img);
        	if(this.backgroundColor)  this.div.style.backgroundColor=this.backgroundColor;
        	if(this.opacity){
        		this.div.style.opacity = this.opacity;
        		this.div.style.filter = "alpha(opacity="+(this.opacity*100)+")";
        	}
        }
        this.deactivate();
        return this.div;
    },
    activate: function () {
    	OpenLayers.Control.prototype.activate.apply(this, arguments);
    	this.div.style.display="block";
    },
    deactivate: function () {
    	OpenLayers.Control.prototype.deactivate.apply(this, arguments);
    	this.div.style.display="none";
    },
    CLASS_NAME: "OpenLayers.Control.TQLoadingPanel"
});