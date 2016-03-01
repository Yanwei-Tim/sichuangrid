/*
	修改在图片请求失败时，出现红叉图片的问题
*/

OpenLayers.Util.onImageLoadErrorHide = true;
OpenLayers.Util.onImageLoadErrorColor = "transparent";
OpenLayers.Util.onImageLoadError = function() {
    this._attempts = (this._attempts) ? (this._attempts + 1) : 1;
    if (this._attempts <= OpenLayers.IMAGE_RELOAD_ATTEMPTS) {
        var urls = this.urls;
        if (urls && OpenLayers.Util.isArray(urls) && urls.length > 1){
            var src = this.src.toString();
            var current_url, k;
            for (k = 0; current_url = urls[k]; k++){
                if(src.indexOf(current_url) != -1){
                    break;
                }
            }
            var guess = Math.floor(urls.length * Math.random());
            var new_url = urls[guess];
            k = 0;
            while(new_url == current_url && k++ < 4){
                guess = Math.floor(urls.length * Math.random());
                new_url = urls[guess];
            }
            this.src = src.replace(current_url, new_url);
        } else {
            this.src = this.src;
        }
    } else {
//        OpenLayers.Element.addClass(this, "olImageLoadError");
		this.style.backgroundColor = OpenLayers.Util.onImageLoadErrorColor;
        if(OpenLayers.Util.onImageLoadErrorHide){
        	this.src = OpenLayers.Util.getImagesLocation() + "blank.gif";
        }
    }
    this.style.display = "";
};