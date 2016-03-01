/***
 * 指南针
 */
OpenLayers.Control.TQCompass = OpenLayers.Class(OpenLayers.Control, {
    ediv: null,
    degree:0,
    type:null,
    draw: function() {
        OpenLayers.Control.prototype.draw.apply(this, arguments);
        if(this.type=="3D") this.degree = 45;
    	//degree设置初始时指南针的指向，现有0,45
        if (!this.ediv) {
        	this.ediv = document.createElement("div");
	        this.ediv.className = this.displayClass + "D"+this.degree;
        }
        this.div.appendChild(this.ediv);
        return this.div;
    },
    CLASS_NAME: "OpenLayers.Control.TQCompass"
});