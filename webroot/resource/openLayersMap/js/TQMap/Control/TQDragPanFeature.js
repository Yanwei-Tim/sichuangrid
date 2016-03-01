/*
 * 使热区选中之余，可以在热区上拖动地图
 * you can extend this Control(DragPanFeature) use for reference DragPan.js or Navigation.js
 */
OpenLayers.Control.TQDragPanFeature = OpenLayers.Class(OpenLayers.Control.SelectFeature, {
    documentDrag: false,
    dragPanOptions: null,
    dragPan: null,
    layer: null,
    onMouseDown:function(e){},
	onMouseMove:function(e){},
    onMouseUp: function(e){},
    draw: function() {
    	var that = this;
		OpenLayers.Control.SelectFeature.prototype.draw.apply(this, arguments);
		this.dragPan = new OpenLayers.Control.DragPan(
            OpenLayers.Util.extend({
                map: this.map,
                documentDrag: this.documentDrag,
                canPan:false,
                panMapStart: function(evt) {
            		that.onMouseDown(evt);
                	OpenLayers.Control.DragPan.prototype.panMapStart.apply(this, arguments);
                },
                panMap: function(evt) {
                	that.onMouseMove(evt);
                	OpenLayers.Control.DragPan.prototype.panMap.apply(this, arguments);
                },
                panMapDone: function(evt) {
                	that.onMouseUp(evt);
                	OpenLayers.Control.DragPan.prototype.panMapDone.apply(this, arguments);
                }
            }, this.dragPanOptions)
        );
		this.dragPan.draw();
    },
    deactivate: function () {
        OpenLayers.Control.SelectFeature.prototype.deactivate.apply(this, arguments);
        if (this.dragPan) {
        	this.dragPan.deactivate();
        }
    },
    destroy: function() {
        OpenLayers.Control.SelectFeature.prototype.destroy.apply(this, arguments);
        if (this.dragPan) {
            this.dragPan.destroy();
        }
        this.dragPan = null;
    },
    activate: function () {
        OpenLayers.Control.SelectFeature.prototype.activate.apply(this, arguments );
        if (this.dragPan) {
        	this.dragPan.activate();
        }
    },
    CLASS_NAME: "OpenLayers.Control.TQDragPanFeature"
});