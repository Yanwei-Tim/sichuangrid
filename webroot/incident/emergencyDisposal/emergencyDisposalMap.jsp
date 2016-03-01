<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<html>
<head>
	<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
	<script type="text/javascript">
	
	$(function(){
		var mapUrl="<s:property value='@com.tianque.gis.util.GisGlobalValue@GIS_API_PATH'/>?City=xining&L=zh-chs&MapID=EdushiMap&x=8390&y=9384&w=310&h=180&eye=1&ew=190&eh=145&e=gb2312&z=2&v=2";
		$.getScript(mapUrl,function(){
			setTimeout("",500);
		})
	})
	</script>
</head>
<body>
<div id="EdushiMap" style="z-index: 1; top: 27px; left: 0px;"></div>
</body>
</html>