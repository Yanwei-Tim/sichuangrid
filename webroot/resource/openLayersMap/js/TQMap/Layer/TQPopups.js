/**
 * 为地图提供存放popup的图层，方便管理处理
 */
OpenLayers.Layer.TQPopups = OpenLayers.Class(OpenLayers.Layer, {
    isBaseLayer: false,
    popups: null,
    zIndex:725,

    /** 
     * Property: drawn 
     * {Boolean} internal state of drawing. This is a workaround for the fact
     * that the map does not call moveTo with a zoomChanged when the map is
     * first starting up. This lets us catch the case where we have *never*
     * drawn the layer, and draw it even if the zoom hasn't changed.
     */
    drawn: false,
    
    initialize: function(name, options) {
        OpenLayers.Layer.prototype.initialize.apply(this, arguments);
        this.popups = [];
    },
    destroy: function() {
        this.clearPopups();
        this.popups = null;
        OpenLayers.Layer.prototype.destroy.apply(this, arguments);
    },

    /**
     * APIMethod: setOpacity
     * Sets the opacity for all the popups.
     * 
     * Parameters:
     * opacity - {Float}
     */
    setOpacity: function(opacity) {
        if (opacity != this.opacity) {
            this.opacity = opacity;
            for (var i=0, len=this.popups.length; i<len; i++) {
                this.popups[i].setOpacity(this.opacity);
            }
        }
    },

    /** 
     * Method: moveTo
     *
     * Parameters:
     * bounds - {<OpenLayers.Bounds>} 
     * zoomChanged - {Boolean} 
     * dragging - {Boolean} 
     */
    moveTo:function(bounds, zoomChanged, dragging) {
        OpenLayers.Layer.prototype.moveTo.apply(this, arguments);

        if (zoomChanged || !this.drawn) {
            for(var i=0, len=this.popups.length; i<len; i++) {
                this.drawPopup(this.popups[i]);
            }
            this.drawn = true;
        }
    },

    /**
     * APIMethod: addPopup
     *
     * Parameters:
     * popup - {<OpenLayers.Marker>} 
     */
    addPopup: function(popup) {
        this.popups.push(popup);
        if (this.opacity < 1) {
            popup.setOpacity(this.opacity);
        }

        if (this.map && this.map.getExtent()) {
            popup.map = this.map;
            this.drawPopup(popup);
        }
    },

    /**
     * APIMethod: removePopup
     *
     * Parameters:
     * popup - {<OpenLayers.Marker>} 
     */
    removePopup: function(popup) {
        if (this.popups && this.popups.length) {
            OpenLayers.Util.removeItem(this.popups, popup);
            //popup.erase();
        }
    },

    /**
     * Method: clearPopups
     * This method removes all popups from a layer. The popups are not
     * destroyed by this function, but are removed from the list of popups.
     */
    clearPopups: function() {
        if (this.popups != null) {
            while(this.popups.length > 0) {
                this.removePopup(this.popups[0]);
            }
        }
    },

    /** 
     * Method: drawPopup
     * Calculate the pixel location for the popup, create it, and 
     *    add it to the layer's div
     *
     * Parameters:
     * popup - {<OpenLayers.Marker>} 
     */
    drawPopup: function(popup) {
        var px = this.map.getLayerPxFromLonLat(popup.lonlat);
        if (px == null) {
            popup.display(false);
        } else {
//            if (!popup.isDrawn()) {
//                var markerImg = popup.draw(px);
//                this.div.appendChild(markerImg);
//            } else if(popup.icon) {
//                popup.icon.moveTo(px);
//            }
        	var popupDiv = popup.draw();
            if (popupDiv) {
            	popupDiv.style.zIndex = this.zIndex;
            	this.setZIndex(this.zIndex);
                this.div.appendChild(popupDiv);
            }
        }
    },
    
    /** 
     * APIMethod: getDataExtent
     * Calculates the max extent which includes all of the popups.
     * 
     * Returns:
     * {<OpenLayers.Bounds>}
     */
    getDataExtent: function () {
        var maxExtent = null;
        
        if ( this.popups && (this.popups.length > 0)) {
            var maxExtent = new OpenLayers.Bounds();
            for(var i=0, len=this.popups.length; i<len; i++) {
                var popup = this.popups[i];
                maxExtent.extend(popup.lonlat);
            }
        }

        return maxExtent;
    },
    CLASS_NAME: "OpenLayers.Layer.Popups"
});