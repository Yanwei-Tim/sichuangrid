<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<link rel="stylesheet" type="text/css" href="/resource/external/blockBox/css/blackbox.css" />
<script src="${resource_path}/resource/external/blockBox/js/jquery.blackbox.js"></script>
<script>
	function createRaphael(id){
		var holder=$.raphael({
			id:id,
			url:'${path}/issues/issueManage/viewIssueMap.action?issue.id=${issue.id}&statusType=${param.statusType }'
		});
		return holder;
	}
	$(function(){
		var holder=createRaphael('iconHolder');
		$(".fullScreen").click(function(){
			var box = new BlackBox({
			    'clickOverlayEffect': 'close',
			    'overlayOpacity': 0.6, 
			    'allowPromptBlank': false,
			    'displayClose': true, 
			    'enableKeyPress': true
			});
			box.popup('<div id="fullScreenBox" class="fullScreenBox"></div>',function(){
				var fullScreenHolder=createRaphael('fullScreenBox');
				$(fullScreenBox).width($(fullScreenHolder.canvas).width()).height($(fullScreenHolder.canvas).height());
				var $box=$(".BlackBoxContent");
				var resize = function () {
				   $box.css({
	                   left: ($("body").width() - $(fullScreenBox).width()) / 2,
	                   top: ($("body").height() - $(fullScreenBox).height()) / 2
	               })
	            };
		        resize();
		        $(window).resize(resize);
			});
		});
	});
</script>
<div id="holder">
	<a href="javascript:;" class="fullScreen" id="fullScreen" title="全屏查看">全屏</a>
	<div id="iconHolder" style="position:relative;"></div>
</div>
