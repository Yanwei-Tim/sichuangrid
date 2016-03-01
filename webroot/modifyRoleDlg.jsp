<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
a.hover-blue:hover{color:blue;}
</style>

<div class="container container_24">
<form id="modifyRoleForm" method="post"	action="${path}/sessionManage/mockLogin.action" >
			<div class="grid_24 lable-center">
			   <label style="font-family: Tahoma, Geneva, sans-serif; font-weight: bold; font-size: 12px !important; color: #000;">请输入需要更换的用户名；退出后将自动恢复原角色
 </label>
	  		</div>
	  		<div class="clearLine"></div>
	  			<hr width="300">
	  		<div class="clearLine"></div>
  			<div  class="grid_7 lable-right" >
  			<em class="form-req">*</em>
				<label class="form-lbl">更改用户名： </label>
			</div>
			<div class="grid_7">
			    	<input type="text" name="switchUserName" id="switchUserName" maxlength="32"
			    	style="ime-mode:disabled" value=""
	  				class="form-txt" title="要更改的用户的帐号！"/>
	  		</div>
	  		<div class="grid_1">			
			</div>

	  		<div  class="grid_8 lable-left" >
			<a id="lookUser" href="javascript:void(0)" class='hover-blue'><span><strong>该用户详细信息</strong></span></a>
			</div>

	</form>
</div>
			<div id="userMaintanceDialog"></div>

<script type="text/javascript">
$(document).ready(function(){
	$("#lookUser").click(function(event){
		var userName =$("#switchUserName").val();
		if(userName==""){
			$.messageBox({
				message:"请输入用户名!",
				level:"info"
			});
	 		return;
		}
			$.ajax({
				url:'${path}/sysadmin/userManage/checkIsExistByUserName.action?user.userName='+userName,
				type: 'post',
				dataType:'json',
				success: function(data){
						if(null != data && data == true){
							viewUserInfo(userName);
						}else{
							$.messageBox({
								message:"没有该用户,请重新输入!",
								level:"error"
							});
						}
					}
			});
	});

	function viewUserInfo(userName){
		$("#userMaintanceDialog").createDialog({
			width: 580,
			height: 540,
			title:'查看用户信息',
			modal : true,
			url:'${path}/sysadmin/userManage/viewUserOperateByUserName.action?mode=view&user.userName='+userName,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

$("#modifyRoleForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form) {
     $(form).ajaxSubmit({
         success: function(data){
			if(data==true){
				$.messageBox({message:"更改角色成功!"});
				//如果是切换用户模式的话 那么不需要显示出 介绍
				var date=new Date();
                document.location.href="${path}/module.jsp?isSwitchover=true&date="+date+"#index";
				//document.location.reload();
			}else if(data==false){
				document.location.href="${path}/sessionManage/toFirstPasswordUpdate.action";
			}else{
				$.messageBox({
					message:"不能更改为该角色,没有这个角色或者该角色为admin或者该用户待激活或已停用！",
					level:"error"
				});
			}
     },
	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      alert("提交错误");
	   }
	  });
	},
	rules:{
		"switchUserName":{
		required:true
	}
	},
	messages:{
		"switchUserName":{
		required:"请输入用户名"
		}
	}
});

});
</script>
