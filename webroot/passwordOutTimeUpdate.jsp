<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<html>
<head>
<title>密码修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path}/resource/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${resource_path}/resource/css/guide.css" rel="stylesheet" />
<link href="${resource_path}/resource/external/poshytip/tip-error/tip-error.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/external/poshytip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.cookie.js"></script>
<link href="${resource_path}/resource/external/jsValidate/css/validationEngine.jquery.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/formValidate.js"></script>

<script type="text/javascript" src="${resource_path}/resource/external/poshytip/jquery.poshytip.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget/widget.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/validatePassword/digitalspaghetti.password.js"></script>
</head>
<body>
	<div id="getpassword" style="_padding-left:50px;">
		<div id="top" style="padding-right:10px;color:#F00">您已有一个月未更改密码，建议现在进行密码修改</div>
		<div class="form-list">
			<form name="loginform"  method="post" action="${path }/sysadmin/userManage/updatePasswordEmail.action" id="firstUpdatePassForm">
			<div style="color: red">${errorMessage }</div>
			<input type="hidden" value="<s:property value="session.userId"/>" name="user.id"/>
			<div class="row">
				<label class="form-label"><em>*</em>旧密码：</label>
				<div class="collection">
					<input type="password" class="form-txt" name="oldPassword" id="oldPassword" maxlength="16"/>
				</div>
			</div>
			<div class="row">
				<label class="form-label"><em>*</em>新密码：</label>
				<div class="collection" style="width:211px;">
					<input id="password" type="password" name="currentPassword" class="dialogtip form-txt" 
	  				  title="请输入由6-16位字母、数字、特殊字符任意组合的密码" maxlength="16"/>
				</div>
			</div>
			<div class="row">
				<label class="form-label"><em>*</em>再输一遍：</label>
				<div class="collection">
					<input type="password" class="form-txt" name="validatePassword" maxlength="16"/>
				</div>
			</div>
			<div class="row" style="text-align:center;margin-top:20px;" >
				<div class="collection">
					<input id="goSubmit" type="submit" class="form-btn" value="确认修改" style="margin-right:10px;" maxlength="16"/>
				</div>
				<div class="collection">
					<input id="goBack" type="button" class="form-btn" value="下次修改"/>
				</div>
			</div>
			</form>
		</div>
	</div>
<script type="text/javascript"> 
	$(document).ready(function(){
		$(".dialogtip").inputtip();
		jQuery.validator.addMethod("checkPassWordStrong", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			var passwordVal=$(".password-strength-bar").text();
			if(passwordVal=="弱"){
				return false;
			}else{
				return true;
			}
		});
		
		//$("#goSubmit").click(function(){$("#firstUpdatePassForm").submit();});
		$("#firstUpdatePassForm").formValidate({
			promptPosition :"bottomLeft",
			submitHandler: function(form) {
				$(form).ajaxSubmit({
					success:function(data){
						if(data==true){
							document.location.href="${path}/module.jsp#index";
						}else{
							var inputObject=$("input[name='oldPassword']");
							inputObject.dialogtip({
								content: data,alignX: 'right',alignY: 'center'
							});
							inputObject.poshytip('show');
						}
					}
				});
				return false;
			},
			rules: {
				oldPassword: {
					required: true
				},
				currentPassword :{
					notEqualTo:"#oldPassword",
					required: true,
					minlength: 6,
					maxlength: 24,
					checkPassWordStrong:true
				},
				validatePassword :{
					equalTo:"#password",
					required: true,
					minlength: 6,
					maxlength: 24
				}
			},
			messages: {
				oldPassword: {
					required: "请输入旧密码！",
					minlength  : $.format("至少需要{0}个字符"),
					maxlength  : $.format("不能大于{0}个字符")
				},
				currentPassword :{
					notEqualTo:"新密码不能和旧密码相同",
					required: "请输入新密码！",
					minlength: $.format("至少需要{0}个字符"),
					maxlength: $.format("不能大于{0}个字符"),
					checkPassWordStrong:"密码过弱，建议输入由6-16位字母、数字、特殊字符任意两种或三种组合的密码"
				},
				validatePassword :{
					equalTo:"两次输入密码不一致",
					required: "请重新输入密码！",
					minlength: $.format("至少需要{0}个字符"),
					maxlength: $.format("不能大于{0}个字符")
				}
			}
		});
		$("#goBack").click(function(){
			document.location.href="${path}/module.jsp#index";
		});
	});
	$("#password").passwordCheck({
		keyUp:function(score){
		scoreResult = score;
		}
	});
</script> 
</body>
</html>