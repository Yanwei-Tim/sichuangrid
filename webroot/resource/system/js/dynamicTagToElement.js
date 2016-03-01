;(function ($){
	/*$.fn.dynamicTagElement = function(options){
		var defaultOptions = {
				height      : "12px",
				width		: "25px"
		}
		var self=$(this);
		$.extend(defaultOptions,options);
		self.css("width",defaultOptions.width).css("height",defaultOptions.height).css("background-color","white");
	}*/
	$.fn.addDynamicTag = function(options){
		var defaultOptions = {
				id      :null,
				name		:null,
				outerDivId	:null,
				onCallBack	:null,
				isRemoved	:null
		}
		var self=$(this);
		$.extend(defaultOptions,options);
		var createConstruct="<div id='"+defaultOptions.outerDivId+"-"+defaultOptions.id+"' class='sendObjTextArea'>"
				+defaultOptions.name
				+"<a class='delete' id='"+defaultOptions.outerDivId+"closebtn-"+defaultOptions.id+"' href='javascript:void(0)'></a></div>";
		self.append(createConstruct);
		if(defaultOptions.onCallBack){
			defaultOptions.onCallBack(defaultOptions);
		}
		
		var aTagId = defaultOptions.outerDivId+"closebtn-"+defaultOptions.id;//要删除的a标签id
		$("#"+aTagId).click(function(){
			var self = $(this);
			var $removedElement = self.parent().remove();
			if($removedElement){
				var deletedId = self.attr("id");
				var index = deletedId.indexOf('-');
				var turnBackId = deletedId.substring(index+1,deletedId.length);
				//alert("turnBackId:"+turnBackId);
				defaultOptions.isRemoved(turnBackId);
			}
			return false;
		})
	}
	
})(jQuery);



















