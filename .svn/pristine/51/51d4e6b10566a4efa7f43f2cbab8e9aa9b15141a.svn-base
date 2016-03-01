<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
String frontPageBottom = globalSettingService.getGlobalValue(GlobalSetting.FRONTPAGE_BOTTOM_PAGE);
String backImage = globalSettingService.getGlobalValue(GlobalSetting.BACK_IMAGE);
String prevImage = globalSettingService.getGlobalValue(GlobalSetting.PREV_IMAGE);
String buttonImage = globalSettingService.getGlobalValue(GlobalSetting.BUTTON_IMAGE);
String mouseMoveImage = globalSettingService.getGlobalValue(GlobalSetting.MOUSE_MOVE_IMAGE);
request.setAttribute("backImage",backImage);
request.setAttribute("prevImage",prevImage);
request.setAttribute("buttonImage",buttonImage);
request.setAttribute("mouseMoveImage",mouseMoveImage);
%>
<!DOCTYPE html>
<html>
<head>
<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>成都市锦江区社会服务管理信息系统</title>
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path }/resource/js/poshytip/tip-error/tip-error.css" rel="stylesheet" />
<link type="text/css" href="${resource_path }/resource/js/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" />
<link rel="shortcut icon" href="${resource_path }/resource/images/favicon.ico" type="image/x-icon"/>
<style type="text/css">
body,h1,h2,h3,h4,h5,h6,p,blockquote,dl,dt,dd,ul,ol,li,pre,fieldset,lengend,button,input,textarea,th,td {margin: 0;padding: 0;}
html,body{width:100%;height:100%;overflow:hidden;}
.cd_guide{position:relative;}
.cd_guide .logo{position:absolute;top:50px;left:50px;}
.cd_guide .cd_cont{width:710px;height:550px;position:absolute;left:50%;top:50%;background: url(/resource/system/images/login/jinjiang/title.png) 0 50% no-repeat;;margin:-275px 0 0 -355px;}
.cd_guide .cd_list{position:relative;}
.cd_guide .cd_list li{text-align:center;list-style:none;background:url(/resource/system/images/login/jinjiang/sprite.png) no-repeat;position:absolute;cursor:pointer;z-index:12;width:176px;height:176px;}
.cd_guide .cd_list li.cd_grid{background-position:0 0;right:170px;}
.cd_guide .cd_list li.cd_city{background-position:0 -193px;right:0px;top:104px;}
.cd_guide .cd_list li.cd_com{background-position:0 -386px;right:0px;top:296px;}
.cd_guide .cd_list li.cd_live{background-position:0 -578px;right:170px;top:400px;}
.cd_guide .cd_list li a{text-decoration:none;display:block;position:relative;padding-top:90px;padding-bottom:30px;font-size:20px;color:#fff;font-family:"microsoft yahei";_padding-top:95px;_padding-bottom:35px;}
</style>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.cookie.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/poshytip/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/formValidate.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget/widget.js"></script>
</head>
<body>
<div class="cd_guide">
	<img src="${resource_path}/resource/system/images/login/jinjiang/bg_guide.jpg"  name="bg" border="0" usemap="#bgMap" id="bg"/>
    <img src="${resource_path}/resource/system/images/login/jinjiang/ico_logo.png" class="logo" />
    <div class="cd_cont">
    	<ul class="cd_list">
        	<li class="cd_grid"><a href="/login/jinjiang/login/login.jsp">网格化服务管理<br>信息系统</a></li>
            <li class="cd_city"><a href="javascript:;">数字化城市管理<br>信息系统</a></li>
            <li class="cd_com"><a href="javascript:;">社区公共服务<br>信息系统</a></li>
            <li class="cd_live"><a href="javascript:;">民生服务<br>信息系统</a></li>
        </ul>
    </div>	
</div>
<script type="text/javascript">
$(function() {
		var container=function(){
			var timer;
			function imagesArea(){
				$("#bg").width($(window).width()).height($(window).height())	
			}
			imagesArea()
			$(window).resize(function(){
				clearTimeout(timer);
				timer=setTimeout(function(){imagesArea()},100);	
			})	
		}() 

});
</script>
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG.js"> --</script>
<script type="text/javascript">
	DD_belatedPNG.fix('img');
    DD_belatedPNG.fix('.cd_guide .cd_cont');
	DD_belatedPNG.fix('.cd_guide .cd_list .cd_grid');
    DD_belatedPNG.fix('.cd_guide .cd_list .cd_city');
    DD_belatedPNG.fix('.cd_guide .cd_list .cd_com');
    DD_belatedPNG.fix('.cd_guide .cd_list .cd_live');
</script>
<![endif]-->
</body>
</html>
