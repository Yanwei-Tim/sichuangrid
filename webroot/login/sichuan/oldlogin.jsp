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
<jsp:include page="/includes/baseInclude.jsp" />
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path }/resource/js/poshytip/tip-error/tip-error.css" rel="stylesheet" />
<link type="text/css" href="${resource_path }/resource/js/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" />
<link rel="shortcut icon" href="${resource_path }/resource/images/favicon.ico" type="image/x-icon"/>
<style type="text/css">
body,ul,li,p,a,form,h1{margin:0;padding:0;}
.clearfix:after{content:".";display:block;height:0;clear:both; visibility:hidden;}
.clearfix{display:inline-block;}
.clearfix{display:block;}
*:focus{outline:0 none;}
a{text-decoration:none;}

body,html{background:#7abcef url(/resource/system/images/login/sichuan/Big.jpg) center -80px no-repeat;width:100%;height:100%;overflow:hidden;}
.scGrid{width:100%;height:100%;/* background:url(/resource/system/images/login/sichuan/Big.jpg) center -40px no-repeat; position:relative;margin:0 auto; */}
.scGrid h1{ text-indent:-9999em;}
.gridLogin{position:absolute;left:50%;top:338px; width:300px;margin-left:172px;}
.scLoginPart{width:257px;height:200px;}
.scLoginPart ul li label{float:left;margin-top:8px;font-size:18px;text-indent:-9999em;}
.scLoginPart ul li input{float:right;margin-left:15px;height:30px;line-height:30px; background:none;width:160px;}
.userInput,.pwdInput{border:0 none;}
.userInput{margin-top:-4px;}
.userLi{margin-bottom:18px;}
.userLi,.pwdLi{height:27px;}
.pwdLi{height:35px;}
.remeberLi{clear:both;margin-top:22px;height:18px;*margin-top:0px;_margin-top:0px;}
.scLoginPart ul li .remeber{float:left;margin-top:-9px;margin-top :-9px\9;*margin-top:0px;_margin-top:-5px;margin-left:84px;display:inline;width:auto;height:auto;}
.scLoginPart ul .remeberLi label{cursor:pointer;width:75px;height:16px;margin-top:-17px;*margin-top:-20px;_margin-top:10px;margin-left:100px;}
.login,.reset{float:left;height:34px;width:85px;margin-top:48px;*margin-top:63px;_margin-top:56px;}
.login{margin-left:47px;margin-right:40px;display:inline;}
.error{position:absolute;border-radius:5px;-webkit-border-radius:5px;-moz-border-radius:5px;border:1px solid #eea83f;background:#fdebd0;color:#ce0000;width:230px;height:30px;padding:5px;padding-top:10px;font-size:12px;left:19px;top:113px;list-style:none;display:none;}
.error span{display:block;}
.pwdInput{_margin-top:-5px;}
</style>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.cookie.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/poshytip/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/formValidate.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget/widget.js"></script>
<script type="text/javascript">
$(function(){
    $("#username").focus();
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
});
</script>
</head>
<body>
<div class="scGrid">
	<h1>四川省网格化服务管理信息系统</h1>
	<div class="gridLogin">
		<form method="post" action="${path}/sessionManage/login.action" id="loginForm">
			<div class="scLoginPart">
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
