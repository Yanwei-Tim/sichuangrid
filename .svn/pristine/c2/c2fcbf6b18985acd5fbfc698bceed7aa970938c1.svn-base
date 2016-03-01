<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>考核评分</title>
<jsp:include page="/includes/baseInclude.jsp" />
<jsp:include page="/includes/jsInclude.jsp" />

<script type="text/javascript">
$(function() {
    $.layout();
    $.selectMenu("examineAssessment-menu");
    $('<div id="baseLine"></div>').appendTo('body');
});
</script>
</head>
<body>
  	<div class="ui-layout-north">
		<jsp:include page="/includes/navigation.jsp" />
	</div>
  	<div class="ui-layout-west"><jsp:include page="/examine/examineSideBar.jsp" /></div>
	<div class="ui-layout-center" style="overflow:hidden">
		<div class="path">
			<h1>当前位置：<a href="javascript:void(0)" id="parentPosition">考核评分</a>&nbsp;&gt;&nbsp;<a href="javascript:void(0)" id="currentPosition" class="dq">年度考核细则</a></h1>
		</div>
		<div class="content" >
			<div id="loading" style="display: none;color:#999;text-align:center;height:32px;line-height:32px;margin-top:200px;"><img
	src="${resource_path}/resource/images/loading.gif" alt="加载中..." style="vertical-align:middle;margin-right:5px;" />加载中，请稍候...</div>
		  	<div id="contentDiv"></div>
		</div>
	</div>
	<div class="ui-layout-south">
		<jsp:include page="/includes/footer.jsp" />
	</div>
	<div id="jGrowl"></div>
</body>
</html>