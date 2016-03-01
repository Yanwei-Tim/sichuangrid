<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
	<form id="tencentUserForm" method="post" action="${path}/tencentUserManage/addTencentUser.action">
	<@pop.token />
	 <div class="grid_4 lable-right">
    	<em class="form-req">*</em>
		<label >所属层级：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="tencentUser_organization_orgName" name="tencentUser.organization.orgName" class="form-txt" value="${(tencentUser.organization.orgName)?if_exists}" readonly/>
	</div>
	<div style="clear:both"></div>
	
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>原始ID：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="tencentUser_weChatUserId" name="tencentUser.weChatUserId"  maxlength="100" value="${(tencentUser.weChatUserId)?if_exists}" 
		class='form-txt {required:true,maxlength:100,isHasSameWeChatUserId:true,messages:{required:"请输入原始ID",maxlength:$.format("原始ID最多可以输入{0}个字符"),isHasSameWeChatUserId:"该原始ID已存在"}}'/>
	</div>
	<div style="clear:both"></div>
	
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>appID：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="tencentUser_appId" name="tencentUser.appId" class='form-txt {required:true,maxlength:100,messages:{required:"请输入appID",maxlength:$.format("appID最多可以输入{0}个字符")}}' maxlength="100" value="${(tencentUser.appId)?if_exists}" />
	</div>
	<div style="clear:both"></div>
	
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>appsecret：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="tencentUser_appSecret" name="tencentUser.appSecret" class='form-txt {required:true,maxlength:100,messages:{required:"请输入appsecret",maxlength:$.format("appsecret最多可以输入{0}个字符")}}' maxlength="100" value="${(tencentUser.appSecret)?if_exists}" />
	</div>
	<div style="clear:both"></div>

	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label>微信昵称：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="tencentUser_name" name="tencentUser.name" class='form-txt {required:true,maxlength:64,messages:{required:"请输入微信昵称",maxlength:$.format("微信昵称最多可以输入{0}个字符")}}' maxlength="64" value="${(tencentUser.name)?if_exists}" />
	</div>
	
	<div style="clear:both"></div>
	<input type="hidden"  id="mode" name="mode" value="${mode?if_exists}" />
	<input type="hidden"  id="tencentUser_tencentUserId" name="tencentUser.tencentUserId" value="${(tencentUser.tencentUserId)?if_exists}" />
	<input type="hidden"  id="tencentUser_organization_id" name="tencentUser.organization.id" value="${(tencentUser.organization.id)?if_exists}" />
	
	
	<div class='clearLine'>&nbsp;</div>
	<div id="tencentUserDialog"></div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	jQuery.validator.addMethod("isHasSameWeChatUserId", function(value, element){
		var flag=false;
		$.ajax({
			async:false,
			url:"${path}/tencentUserManage/validateWeChatUserId.action?tencentUser.weChatUserId="+$("#tencentUser_weChatUserId").val(),
			success:function(responseData){
			    if(responseData!=null){
			      if("edit" == $("#mode").val()){
						if($("#tencentUser_weChatUserId").val()==responseData["weChatUserId"]&&$("#tencentUser_tencentUserId").val()==responseData["tencentUserId"]){
							flag=true;
						}
					}
				}
				if(responseData==null){
					flag=true;
				}
			}
		});
		return flag;
	});
	$("#tencentUserForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				async : false,
				success:function(data){
					if(data!=null){
           	 			$.messageBox({
							level: "error",
							message : data
			 			});
            			return;
					}
					if("add" == $("#mode").val()){
						$("#tencentUserDialog").dialog("close");
						$.messageBox({message:"成功添加微信服务号信息!"});
						$("#tencentUserList").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#tencentUserDialog").dialog("close");
						$.messageBox({message:"成功修改微信服务号信息!"});
						$("#tencentUserList").trigger("reloadGrid");
         			}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					$.messageBox({message:"提交数据时发生错误"});
	   		    }
			});
		},
		rules:{
		},
		messages:{
		}		
	});
		
});
		
</script>
