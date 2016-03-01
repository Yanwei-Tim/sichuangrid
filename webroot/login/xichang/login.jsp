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
<title>西昌市网格化服务管理信息系统</title>
<jsp:include page="/includes/baseInclude.jsp" />
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path }/resource/js/poshytip/tip-error/tip-error.css" rel="stylesheet" />
<link type="text/css" href="${resource_path }/resource/js/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" />
<link rel="shortcut icon" href="${resource_path }/resource/images/favicon.ico" type="image/x-icon"/>
<style type="text/css">
body,h1,h2,h3,h4,h5,h6,p,blockquote,dl,dt,dd,ul,ol,li,pre,fieldset,lengend,button,input,textarea,th,td {margin: 0;padding: 0;}
html,body{width:100%;height:100%;overflow:hidden;}
.product-logCon{position:relative;}
.product-logCon .logo{width:767px;height:312px;margin:0 0 0 -483px;background:url(/resource/system/images/login/xichang/icon_login.png) no-repeat;position:absolute;top:30px;left:50%;z-index:1;}
.product-logCon .product-logform{width:335px;height:210px;margin:0 0 0 -483px;position:absolute;bottom:20px;left:50%;z-index:1;}
.product-logCon .product-logform .item{margin:0 0 10px 0;}
.product-logCon .product-logform .item-input{width:248px;height:50px;padding:0 0 0 60px;background:url(/resource/system/images/login/xichang/icon_inputTxt.png) no-repeat;}
.product-logCon .product-logform .item-input.item-inputTxt{background-position:0 0;}
.product-logCon .product-logform .item-input.item-inputPwd{background-position:0 -50px;}
.product-logCon .product-logform .item-input .inputTxt{width:200px;height:20px;margin:10px 0 0 0;border:1px solid #fff;font:bold 14px/20px "";color:#B5D6EE;}
.product-logCon .product-logform .item .product-login{float:left;width:152px;height:48px;display:inline-block;*display:inline;*zoom:1;background:url(/resource/system/images/login/xichang/icon_btn.png) no-repeat;}
.product-logCon .product-logform .item label{float:left;margin:10px 0 0 10px;color:#fff;}
.product-logCon .product-logform .item-validate{width:298px;height:35px;margin:0 0 10px 5px;border:1px dashed #673D49;text-shadow: 2px 0px 0px #673D49,-2px 0px 0px #673D49,0px 2px 0px #673D49,0px -2px 0px #673D49;font:bold 17px/35px "";color:#FFE6E6;text-align:center;position:relative;}
.product-logCon .product-logform .item-validateBg{width:298px;height:35px;background:#807783;opacity:0.6;filter:alpha(oapcity=60);position:absolute;}
.product-logCon .product-logform .item-validateTxt{position:relative;z-index:1;}
.product-logCon .product-logform .item-validateArea{height:35px;}
</style>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.md5.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.cookie.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/poshytip/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/formValidate.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget/widget.js"></script>
<script type="text/javascript">
$(function(){
	var container=function(){
		var timer;
		function imagesArea(){
			$("#imgArea").width($(window).width()).height($(window).height())	
		}
		imagesArea()
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setTimeout(function(){imagesArea()},100);	
		})	
	}()
	
    $("#username").focus();
	$(".item-validate").hide();
    $("#sub").click(function(){
        $('#loginForm').submit();
    });
    $("#changeValidateCode").click(function(){
        $("#validateCodeImg").attr("src","${path }/validateCodeServlet?date="+new Date());
    });

    $("#username,#password").bind("keydown",function(evt){
        var evt = window.event?window.event:evt;
        if(evt.keyCode==13){
            $('#loginForm').submit();
        }
    });

    if($.cookie('username')){            //判断cookie是否为空，如果不为空继续下一步。
         $('#username').val($.cookie('username'));     //从cookie里面取出里面的值。
         $('#remuser').attr("checked","checked");
    }
    $("#loginForm").formValidate({
        submitHandler: function(form) {
        $(".item-validate").show();
        $("#loginInfo").text("系统登录中...");
        $("#submithref").attr('disabled', true);
        $("#password").val($.md5($("#password").val()));
            $(form).ajaxSubmit({
                success:function(data){
                    $("#submithref").attr('disabled', false);
                    if(data==null||data==true){
                        if($.cookie("failureTimes")){
                            $.cookie("failureTimes",0,{path: '/'});
                        }
                        var userName=$('#username').val();
                        if($('#remuser').attr('checked')){
                            $.cookie('username',userName,{ path: '/',expires:7});
                        }else{
                            $.cookie('username',null,{ path: '/',expires:0});
                        }    
                        document.location.href="${path}/module.jsp";
                    }else if(data==false){
                        document.location.href="${path}/sessionManage/toFirstPasswordUpdate.action?path=urlAddress";
                    }else if(data=='passwordOutTime'){
                        document.location.href="${path}/sessionManage/passwordOutTimeUpdate.action";
                    }else{
                    	$("#loginInfo").text("");
                        if($.cookie("failureTimes")){
                            $.cookie("failureTimes",parseInt($.cookie("failureTimes"))+1);
                        }else{
                            $.cookie("failureTimes",1,{path: '/'});
                        }
                        var jsonMsg=eval("("+data+")");
                        if(jsonMsg.userName){
                            var inputObject=$("input[name='userName']");
                            $("#loginInfo").html(jsonMsg.userName);
                            if($.cookie("failureTimes")>=3||parseInt(jsonMsg.failureTimes)>=3){
                                $(".login-yztext").show();
                                $("#validateCodeImg").attr("src","${path }/validateCodeServlet?date="+new Date());
                            }
                        }
                        if(jsonMsg.validateCode){
                            var inputObject=$("input[name='validateCode']");
                            inputObject.dialogtip({
                                content:jsonMsg.validateCode,alignX: 'center',alignY: 'top'
                            });
                            inputObject.poshytip('show');
                        }
                    }
                }
            });
            return false;
        },
        rules: {
            userName:{
                required:true
                //isDigitStrAndUnderline:true
            },
            password:{
                required:true
            }
        },
        messages: {
            userName:{
                required:"用户名不能为空！"
                //isDigitStrAndUnderline:"用户名只能由数字、字母、下划线组成"
            },
            password:{
                required:"密码不能为空！"
            }
        }
    });
});
</script>
</head>
<body>
<div class="product-logCon">
	<img src="${resource_path}/resource/system/images/login/xichang/bg.jpg" id="imgArea"/>
    <div class="logo"></div>
    <div class="product-logform">
    	<div class="item item-validateArea">
	    	<div id="loginInfo" class="item-validate">
	            <div class="item-validateBg"></div>
	        	<span class="item-validateTxt">${errorMessage}<s:property value="#parameters.errorMessage"/></span>
	        </div>
        </div>
    	<form method="post" action="${path}/sessionManage/login.action" id="loginForm">
        	<div class="item item-input item-inputTxt"><input id="username" type="text" class="inputTxt" name="userName" /></div>
            <div class="item item-input item-inputPwd"><input id="password" type="password" class="inputTxt" name="password"/></div>
            <div class="item">
				<a href="javascript:;" class="product-login" id="submithref" name="submithref" onclick="$('#loginForm').submit();"></a>
                <label><input type="checkbox" />记住账号</label>
            </div>
        </form>
    </div>	
</div>
       <!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG.js" ></script>

<script type="text/javascript">
 DD_belatedPNG.fix('.product-logCon .logo,.product-logCon .product-logform .item-input,.product-logCon .product-logform .item .product-login');
</script>
<![endif]-->
</body>
</html>
