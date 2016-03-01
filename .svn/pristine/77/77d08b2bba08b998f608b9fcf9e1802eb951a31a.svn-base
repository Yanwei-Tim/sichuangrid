<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/baseInclude.jsp" %>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>虎台街道区域化党建</title>
    <jsp:include page="/partyBuilding/common/jsInclude.jsp"></jsp:include>
</head>
<body>
	<!-- start header -->
	<jsp:include page="/partyBuilding/common/header.jsp"></jsp:include>
	<!-- End header -->

	<!-- start home -->
	<div class="content layout cf">
        <jsp:include page="/partyBuilding/common/navigationBar.jsp">
        	<jsp:param value="article" name="navCurrent"/>
        </jsp:include>
        <!--nav-->
        
        <div class="main list">
            <div class="tit">
                <span class="BreadCrumbs">
                    当前位置：
                    <a href="">党建信息</a>
                </span>
                <strong class="d"><span>党建新闻</span></strong>
            </div>
            <div class="article">
                <h1>{obj.title}</h1>
                <div class="info">发布者：{obj.createUserRealName} 发布时间：{obj.createDate} </div>
                <div class="txt">
                   <p>{obj.content}</p>
                    <div class="function">
                  
                    </div>
                </div>
            </div>
        </div>
	</div>
	<!-- End home --> 
	
	<!-- start footer -->
	<jsp:include page="/partyBuilding/common/fooder.jsp"></jsp:include>
	<!-- End footer -->
</body>
<!--[if IE 6]>
<script type="text/javascript" src="${resource_path}/resource/external/DD_belatedPNG.js"></script>
<script type="text/javascript">
DD_belatedPNG.fix('.header strong,.header a.goHome');
</script>
<![endif]-->
</html>