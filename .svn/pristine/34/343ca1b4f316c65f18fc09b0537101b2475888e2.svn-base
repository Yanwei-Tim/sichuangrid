<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	#centerImgBox{
		 clear: both;
		 display: block;
		 margin:auto;
	}
</style>
<div id="centerImgBoxCtt" style="overflow: auto;">
	<img  src="${resource_path}/resource/images/chartNew.png" id="centerImgBox">
</div>
<script type="text/javascript">
	$(function(){
		function autoHeight(){
			$("#centerImgBoxCtt").height($(".ui-layout-center").height()-40);
		}
		$(window).resize(function(){
			clearTimeout(window.imgboxTimer);
			window.imgboxTimer=setTimeout(autoHeight,500);
		});
		autoHeight();
	})
</script>
