(function($) {
	$.announcement = function(option) {
			var defaultOption={
				updateUrl:"#",
				content:"",
				width:$("body").width()-16,
				height:30,
				top:"0px",
				left:"0px",
				bottom:"0px",
				right:"0px",
				dataId:false
			};
			$.extend(defaultOption,option);
			var announcementName="announcement";
			var announcementValue=$.cookie(announcementName);
			if(announcementValue==null){
				$.cookie(announcementName,'', { path: '/', expires: 10 });
			};
			if(announcementValue==defaultOption.dataId){return false;}
			$("#announcementCtt").remove();
			$("body").prepend('<div id="announcementCtt"></div>');
			$("#announcementCtt").prepend(defaultOption.content);
			$("#announcementCtt").dialog({
				title:'公告',
				width:300,
				height:200,
				position:'right bottom',
				stack : true,
				resizable : false,
				close:function(){
					$.cookie(announcementName,defaultOption.dataId, { path: '/', expires: 10 });
				}
			}).closest(".ui-dialog").prependTo("body");
	};

})(jQuery);