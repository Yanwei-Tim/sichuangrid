<!DOCTYPE html>
<html>
<head>
<#assign language="java" pageEncoding="UTF-8">
<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/includes/jsInclude.jsp" />
<link href="/resource/tqSearch/css/search.css" rel="stylesheet" type="text/css">
<script src="${resource_path }/resource/tqSearch/js/search.js" type="text/javascript"></script>
<script src="${resource_path }/resource/tqSearch/js/swfobject.js" type="text/javascript"></script>
<title>统一搜索</title>
</head>
<body>
<div class="zjsSeekLay searchCont">
	<@s.if test='mode.equals("list")'>
		<@s.include value="${path}/tqSearch/tqSearchListRight.ftl" />
	</@s.if>
	<@s.else>
		<@s.include value="${path}/tqSearch/tqSearchIndexRight.ftl" />
	</@s.else>
</div>
<script type="text/javascript">
$(function(){
})
</script>
</body>
</html>