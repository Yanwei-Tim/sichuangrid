<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<script type="text/javascript" src="/resource/js/jQueryRotate.js" />
<script type="text/javascript">
	var size = 0;
	//放大缩小图片
	function imgToSize(size) {
		var img = $("#Imgbox");
		var oWidth = img.width(); //取得图片的实际宽度
		var oHeight = img.height(); //取得图片的实际高度
		img.width(oWidth + size);
		img.height(oHeight + size / oWidth * oHeight);
	}
	// 翻转图片
	function imgReverse(arg) {
		var img = $("#Imgbox");
		if (arg == 'h') {
			img.css({
				'filter' : 'fliph',
				'-moz-transform' : 'matrix(-1, 0, 0, 1, 0, 0)',
				'-webkit-transform' : 'matrix(-1, 0, 0, 1, 0, 0)'
			});
		} else {
			img.css({
				'filter' : 'flipv',
				'-moz-transform' : 'matrix(1, 0, 0, -1, 0, 0)',
				'-webkit-transform' : 'matrix(1, 0, 0, -1, 0, 0)'
			});
		}
	}
	var max = 0;
	function rotate(flg) {
		if (flg == 'right') {
			max = max + 20;
			$('#Imgbox').rotateAnimation(-max);
		} else {
			$('#Imgbox').rotateAnimation(0);
		}
		$('#Imgbox').width(350);
		$('#Imgbox').height(350);
	}
</script>
<div id="img"><img id="Imgbox" src="${(inboxAttachment.fileActualUrl)?if_exists}" height="350" width="350"/></img></div>
<br/>
<div >
	<input type="button" value="旋转" onclick="rotate('right')"> <input
		type="button" value="原图" onclick="rotate('left')"> 
	<!-- <input	type="button" value="水平翻转" onclick="imgReverse('h');"> <input
		type="button" value="垂直翻转" onclick="imgReverse('v');"> -->
</div>


