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
<title>四川省网格化服务管理信息系统</title>
<%-- <jsp:include page="/includes/baseInclude.jsp" /> --%>
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path }/resource/external/poshytip/tip-error/tip-error.css" rel="stylesheet" />
<link type="text/css" href="${resource_path }/resource/external/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" />
<link rel="shortcut icon" href="${resource_path }/resource/images/favicon.ico" type="image/x-icon"/>
<style type="text/css">
body,ul,li,p,a,form,h1{margin:0;padding:0;}
.clearfix:after{content:".";display:block;height:0;clear:both; visibility:hidden;}
.clearfix{display:inline-block;}
.clearfix{display:block;}
*:focus{outline:0 none;}
a{text-decoration:none;}
html{overflow-y:hidden;}
.scGrid{width:804px;margin:0 auto;position:relative;}
.logo{ position:absolute;left:53px; top:7%; background:url(/resource/images/login/sichuan/login-logo.png) no-repeat;width:702px;height:96px;}
.logo a{display:block;width:702px;height:96px;text-indent:-9999em;}
.gridLogin{position:absolute;left:50%;top:43%; width:307px;margin-left:28px; background:url(/resource/images/login/sichuan/login-login.png) no-repeat;}
.scLoginPart{width:307px;height:250px;*height:333px;}
.scLoginPart ul{margin-top:89px;width:258px;margin-left:14px;}
.scLoginPart ul li label{float:left;margin-top:8px;font-size:18px;text-indent:-9999em;}
.scLoginPart ul li input{margin-left:55px;height:30px;line-height:30px; background:none;width:135px;}
.userInput,.pwdInput{border:0 none;}
.userInput{margin-top:-4px;}
.userLi{margin-bottom:18px;}
.userLi,.pwdLi{height:27px;}
.pwdLi{height:35px;}
.remeberLi{clear:both;margin-top:22px;height:18px;*margin-top:0px;_margin-top:0px;}
.scLoginPart ul li .remeber{float:left;margin-top:-9px;margin-top :-12px\9;*margin-top:0px;_margin-top:0px;margin-left:84px;display:inline;width:auto;height:auto;}
.scLoginPart ul .remeberLi label{cursor:pointer;width:75px;height:16px;margin-top:-17px;*margin-top:-20px;_margin-top:10px;margin-left:100px;}
.login,.reset{float:left;height:34px;width:85px;margin-top:63px;*margin-top:63px;_margin-top:26px;}
.login{margin-left:47px;margin-right:13px;display:inline;}
.error{position:absolute;border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;border:1px solid #eea83f;background:#fdebd0;color:#ce0000;width:216px;height:30px;padding:5px;padding-top:10px;font-size:12px;left:46px;top:203px;list-style:none;display:none;}
.error span{display:block;}
.pwdInput{_margin-top:-5px;}
</style>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.md5.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.cookie.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/poshytip/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/formValidate.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget/widget.js"></script>
<script type="text/javascript">
function resizeWin(){
	var wH=$(window).innerHeight();
	$(".scGrid").height(wH);
}
function  getParamInfo(){
	var info = "${param.errorMessage}";
	if(info!=""){
		$(".error").show();
	    $("#loginInfo").text(${info});
	}
}
$(function(){
	$(".userInput,.pwdInput").focus(function(){
		$(".error").hide();
	})
	$(".reset").click(function(){
		$(".error").hide();
	})
	resizeWin();
	$(window).resize(resizeWin);

   // $("#username").focus();
    $(".userInput,.pwdInput").focus(function(){
		$(".error").hide();
	});
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
    
    $(".reset").click(function() {
		$("#username").val("");
		$("#password").val("");
		$(".error").hide();
	});

    if($.cookie('username')){            //判断cookie是否为空，如果不为空继续下一步。
         $('#username').val($.cookie('username'));     //从cookie里面取出里面的值。
         $('#remuser').attr("checked","checked");
    }
    $("#loginForm").formValidate({
        submitHandler: function(form) {
        $(".error").show();
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
                      //添加登录成功提示，视觉上提示登录速度！ 
                      // $("#loginInfo").text("登录成功，数据加载中...");
                      // $(document.body).html('<div style="text-align: center; padding-top: 60px; font-size: 12px;">登录成功，数据加载中...</div>'); 
	                  // $("#loginFormDiv ul").html("");
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
           /* userName:{
                required:true
                //isDigitStrAndUnderline:true
            },
            password:{
                required:true
            }*/
        },
        messages: {
           /* userName:{
                required:"用户名不能为空！"
                //isDigitStrAndUnderline:"用户名只能由数字、字母、下划线组成"
            },
            password:{
                required:"密码不能为空！"
            }*/
        }
    });
    getParamInfo();
});
</script>
</head>
<body>
	<img src="${resource_path}/resource/images/login/sichuan/login-bg.jpg" style="width:100%;height:100%;position:absolute;left:0;top:0;"/>
	<div class="scGrid">
    	<h1 class="logo"><a href="javascript:;">四川省网格化服务管理信息系统</a></h1>
        <div class="gridLogin">
       		<form method="post" action="${path}/sessionManage/login.action" id="loginForm">
        	<div class="scLoginPart" id="loginFormDiv">
            	<ul>
                	<li class="clearfix userLi"><label for='username'>用户名：</label><input id="username" type="text" class="userInput" name="userName" /></li>
					<li class="clearfix pwdLi"><label for='password'>密码：</label><input id="password" type="password" class="pwdInput" name="password" /></li>
					<li class="error"><span id="loginInfo">${errorMessage}<s:property value="#parameters.errorMessage"/></span></li>
					<li class="clearfix remeberLi"><input type="checkbox" class="remeber" id="remuser"><label for="remuser">记住用户名</label></li>
					<li class="clearfix"><a href="javascript:;" class="login" id="submithref" name="submithref" onclick="$('#loginForm').submit();">&#12288;</a>
					<a href="javascript:;" class="reset"></a></li>
                </ul>
            </div>
            </form>
        </div>
    </div>
	
<!--[if IE 6]>
<script type="text/javascript" src="${resource_path}/resource/js/DD_belatedPNG.js"></script>
<script type="text/javascript">
	//解决IE6下 PNG 图片兼容的JS插件
	DD_belatedPNG.fix('#productLogin .product-logCon');
	DD_belatedPNG.fix('#productLogin .product-logform .inputSearch a');
    DD_belatedPNG.fix('#productLogin .product-logform .inputSearch .searchT');
    DD_belatedPNG.fix('#productLogin .product-topBg');
	DD_belatedPNG.fix('#productLogin ..product-loginC');
    DD_belatedPNG.fix('#productLogin .product-logform .inputSearch,.product-logCon .logo img');
</script>
<![endif]-->
</body>
</html>
