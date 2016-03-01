<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#load {
	width: 800px;
	height: 600px;
	text-align: center;
}
</style>

<form id="maintainMatchupForm" method="post" action="${path}/sysadmin/userManage/matchupOrganizationMobileUser.action" >
<div  title="请选择部门">
	<div class="nav">
		<input type="text" name="isRight" id="orgSelector"  class="form-txt" />
	 </div>
</div>
	<!-- <input id="targetOrgId" name="targetOrgId" type="hidden" value="" /> -->
	<input id="orgId" name="orgId" type="hidden" />
	<input name="userIds" id="userIds" value="<s:property value="#parameters.selectedIds"/>" type="hidden"/>
</form >
<script type="text/javascript" >
jQuery.validator.addMethod("isVpdnRight", function(value, element){
	var flag=false;
	$.ajax({
		async:false,
		url:"${path}/sysadmin/userManage/validateMobileVpdn.action",
		data:{
			"userIds": function(){ return $("#userIds").val()},
			"orgId":function(){ return $("#orgId").val() }
		},
		success:function(responseData){
			//alert(responseData)
			flag=responseData;
		}
	});
	
	return flag;
});

$(function(){
	var tree=$("#orgSelector").treeSelect({
		inputName:"orgId",
		 changeFun:function(node,e){
			 //isVpdnRight();
		} 
	});
	

	$("#maintainMatchupForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data){
	       	 			$.messageBox({
							evel: "error",
							message:data
			 			});
	        			return;
					}
					$.messageBox({message:"手机账号匹配网格成功！"});
	                $("#mobileUserList").trigger("reloadGrid");
	                $("#mobileUserMaintanceDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
		      			alert("提交数据时发生错误");
	   		    }
			});
		},
		
		rules:{
			
			"userIds":{
				isVpdnRight:true
			}
		},
		messages:{
			"userIds":{
				isVpdnRight:"用户名@后辍与所属网格自动生成的后辍不一致,不能匹配"
			}
		}
	});
	
	
});
</script>