<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script>
	 $(function(){
		 $("#thisCrumbs").hide();
		$(".tools").click(function(){
			if($(".tools_content").is(":visible")){
				$(".tools_content").hide();
			}else{
				$(".tools_content").show();
				
				$(".tools_content").hover(function(){
					$(this).show();
				},function(){
					$(this).hide();
				})
			}
		})
	 })
	var setPNG=function(img,w,h) {
        if(navigator.userAgent.indexOf('MSIE 6')>-1){
            var i = "display:inline-block;";
            var s = "<span style=\"width:" + w + "px; height:" + h + "px;" + i + ";" + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + img.src + "', sizingMethod='scale');\"></span>";
            img.outerHTML = s;
        }
    }; 
</script>
<jsp:include page="index.jsp"/>

