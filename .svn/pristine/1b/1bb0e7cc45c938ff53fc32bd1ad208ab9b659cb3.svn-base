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
<title>成都市成华区网格化服务管理信息系统</title>
<jsp:include page="/includes/baseInclude.jsp" />
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path }/resource/js/poshytip/tip-error/tip-error.css" rel="stylesheet" />
<link type="text/css" href="${resource_path }/resource/js/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" />
<link rel="shortcut icon" href="${resource_path }/resource/images/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" href="/resource/chenghua/css/reset.css"/>
<link rel="stylesheet" href="/resource/chenghua/css/style.css"/>
<style type="text/css">
.item-validateArea{height:35px;position:relative;}
.item-validate{width:298px;height:35px;font:12px "";color:#EC4445;position:relative;}
.item-validateTxt{position:absolute;left:10px;top:20px;}
.loginBtn{float:left;width:100px;height:39px;display:inline-block;*display:inline;*zoom:1;font:18px/39px "";text-align:center;color:#fff;text-shadow:1px 1px 1px #115B8B; text-decoration:none;background:url(/resource/system/images/login/wusheng/icon_inputTxt.png) no-repeat -4px -129px;}
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
		imagesArea();
		$(window).resize(function(){
			clearTimeout(timer);
			timer=setTimeout(function(){imagesArea()},100);	
		})	
	}() 
	
	$(".inputTxt").bind("focusin",function(){
		 if( this.value === this.defaultValue ){
            this.value = '';
        }	
	}).bind("focusout",function(){
		 if( this.value === '' ){
            this.value = this.defaultValue
        }	
	})
	
	
    $("#username").focus();
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
    
    $("#reset").click(function() {
		$("#username").val("");
		$("#password").val("");
		$("#loginInfo").text("");
	});

    if($.cookie('username')){            //判断cookie是否为空，如果不为空继续下一步。
         $('#username').val($.cookie('username'));     //从cookie里面取出里面的值。
         $('#remuser').attr("checked","checked");
    }
    $("#loginForm").formValidate({
        submitHandler: function(form) {
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
<div class="login-bg">
	<img src="${resource_path}/resource/system/images/login/chenghua/bg.jpg"/>
    <div class="login_area">
      <h1>成都市成华区网格化服务管理信息系统</h1>
      <div class="login-form">
        <form method="post" action="${path}/sessionManage/login.action" id="loginForm">
                        <div class="item item-validateArea">
                    <div class="item-validate">
                        <span id="loginInfo" class="item-validateTxt">
                        	${errorMessage}<s:property value="#parameters.errorMessage"/>
                        </span>
                    </div>
                </div>
          <ul>
            <li>
               <div class="username inputBg"><input id="username" type="text" class="inputTxt" name="userName"/></div>
            </li>
            <li>
              <div class="password inputBg"><input id="password" type="password" class="inputTxt" name="password"/></div>
            </li>
            <li class="rem-pas">
              <input type="checkbox" id="remmber-pas"/>
              <label for="remmber-pas">记住密码</label>
            </li>
            <li>
           	  <input type="button" class="login-btn" value="登  录" id="submithref" name="submithref" onclick="$('#loginForm').submit();"/>
              <input type="button" class="login-btn reset-btn" value="重  置" id="reset"/>
            </li>
          </ul>
        </form>
      </div>
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
