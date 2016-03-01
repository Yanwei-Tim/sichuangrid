<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String workbenchApkPath = globalSettingService.getGlobalValue(GlobalSetting.WORKBENCH_APK_PATH);
request.setAttribute("workbenchApkPath",workbenchApkPath);
%>
<div id="dialog-form" title="手机版本设置" class="container container_24" style="overflow:hidden;padding-top:25px;">
    <form id="maintainForm" method="post" action="" enctype="multipart/form-data">
    
    	<div class="grid_6 lable-right">
			<label >apk地址：</label>
		</div>
		<div class="grid_2">
			<input type="text" id="workbenchApkPath" name="workbenchApkPath" class="form-txt" style="width: 500px"
				value="${workbenchApkPath}"/>
		</div>
	  
	  	<div style="clear:both"></div>
		<div class="grid_6 lable-right">
			<label >APP二维码图片：</label>
		</div>
		<div class="grid_2">
			<input type="file" id="workbenchAppQRcodeImageToUpload" name="workbenchAppQRcodeImage"/>
		</div>
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<label></label>
		</div>
		<s:if test="userName=='admin'">
			<div class="grid_8">
				<button type="submit" style="height:30px;">保存</button>
				<button type="reset" id="chongzhi" style="height:30px;">重置</button>
			</div>
		</s:if>
   	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	jQuery.validator.addMethod("checkImgIsNull", function(value, element){
		if($("#workbenchAppQRcodeImageToUpload").val() == null || $("#workbenchAppQRcodeImageToUpload").val()==""){
			return false;
		 }
		return true;
	});
	jQuery.validator.addMethod("checkImgExtName", function(value, element){
		if($("#workbenchAppQRcodeImageToUpload").val().substring($("#workbenchAppQRcodeImageToUpload").val().lastIndexOf(".")+1,$("#workbenchAppQRcodeImageToUpload").val().length) != "jpg"
			&& $("#workbenchAppQRcodeImageToUpload").val().substring($("#workbenchAppQRcodeImageToUpload").val().lastIndexOf(".")+1,$("#workbenchAppQRcodeImageToUpload").val().length) != "JPG"
			&& $("#workbenchAppQRcodeImageToUpload").val().substring($("#workbenchAppQRcodeImageToUpload").val().lastIndexOf(".")+1,$("#workbenchAppQRcodeImageToUpload").val().length)!="gif"
			&& $("#workbenchAppQRcodeImageToUpload").val().substring($("#workbenchAppQRcodeImageToUpload").val().lastIndexOf(".")+1,$("#workbenchAppQRcodeImageToUpload").val().length) != "GIF"
			&& $("#workbenchAppQRcodeImageToUpload").val().substring($("#workbenchAppQRcodeImageToUpload").val().lastIndexOf(".")+1,$("#workbenchAppQRcodeImageToUpload").val().length)!="png"
			&& $("#workbenchAppQRcodeImageToUpload").val().substring($("#workbenchAppQRcodeImageToUpload").val().lastIndexOf(".")+1,$("#workbenchAppQRcodeImageToUpload").val().length) != "PNG"){
				return false;
		 }
		return true;
	});
	
	
	jQuery.validator.addMethod("checkApkPathIsNull", function(value, element){
		if($("#workbenchApkPath").val() == null || $("#workbenchApkPath").val()==""){
			return false;
		 }
		return true;
	});
	jQuery.validator.addMethod("checkApkPathExtName", function(value, element){
		if($("#workbenchApkPath").val().substring($("#workbenchApkPath").val().lastIndexOf(".")+1,$("#workbenchApkPath").val().length) != "apk"
			&& $("#workbenchApkPath").val().substring($("#workbenchApkPath").val().lastIndexOf(".")+1,$("#workbenchApkPath").val().length) != "APK"){
				return false;
		 }
		return true;
	});
	
	
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			doAjaxSubmit();
		},
       rules:{
			"workbenchAppQRcodeImage":{
			checkImgIsNull:true,
			checkImgExtName:true
		   },
			"workbenchApkPath":{
			checkApkPathIsNull:true,
			checkApkPathExtName:true
		   }
		},
		messages:{
			"workbenchAppQRcodeImage":{
				checkImgIsNull:"文件为空不能上传!",
				checkImgExtName:"二维码图片只能为 jpg、gif、png格式!"
		   },
		   "workbenchApkPath":{
			   checkApkPathIsNull:"APK地址不能为空!",
			   checkApkPathExtName:"APK地址有误!"
		   }
		}
	});
	$("#maintainForm").attr("action","${path}/sysadmin/globalSettingManage/updateWorkbenchAppQRcodeSetting.action");
	
	$("#chongzhi").click(function(){
		$.ajax({
			url:"${path}/sysadmin/globalSettingManage/resetWorkbenchAppQRcodeSetting.action",
			data:{
			},
			success:function(data){
				if(data.workbenchAppQRcode==""){
					$.messageBox({message:"重置成功！"});
				}else{
					 $.messageBox({ message:"重置失败!",level: "error"});
				}
			}
		});
	});
	$.loadingComp("close");
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
		dataType:"json", //数据类型
        success: function(data){
        	if(data.errorMessage != null){
        		$.messageBox({message:data.errorMessage,level:"error"});
        	}else{
        		$.messageBox({message:"上传APP二维码成功!"});
        	}
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     	$.messageBox({message:"提交错误",level: "error"	});				
 	   }
 	});
}
</script>