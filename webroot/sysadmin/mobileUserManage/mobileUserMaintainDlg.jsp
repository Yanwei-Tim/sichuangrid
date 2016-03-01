<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>


<style>
ul#selectzones {margin: 0; padding: 0;}
ul#selectzone {margin: 0; padding: 0;}
ul#selectzones li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
ul#selectzones span.ui-icon {float: left; margin: 0 4px;}
</style>

<div id="dialog-form" title="手机账号维护" class="container container_24">
	<s:if test='!"view".equals(mode)'>
	 <form id="maintainForm" method="post"	action="${path}/sysadmin/userManage/maintainMobileUser.action" >
	 <%-- <pop:token/> --%>
	</s:if>
			<input id="mode"	type="hidden" name="mode" value="${mode}" />
			<input id="userId"	type="hidden" name="user.id" value="${user.id}" />
		
			<div  class="grid_4 lable-right" >
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">用户名： </label>
			</div>
			<div class="grid_4" id="userDiv">
			    	<input type="text" name="user.userName" id="userName" maxlength="32" style="ime-mode:disabled"
	  				<s:if test='!"add".equals(mode)'>readonly</s:if> value="${user.userName}"
	  				class="form-txt" title="用户名：用户登录系统的帐号！"/>
	  		</div>
	  		<s:if test='"add".equals(mode)'>
		  		<div class="grid_3">
		  		    <input type="text" name="user.vpdn" id="uservpdn" value="@" 
		  		     
		  		     style="ime-mode:disabled" class="form-txt"/>
		  		</div>
	  		</s:if>
	  		<div class="grid_1"></div>
	  		<div  class="grid_4 lable-right">
	  		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	  			<label class="form-lbl">姓名：</label>
	  		</div>
			<div class="grid_7">
			    <input type="text" name="user.name" id="name" maxlength="20" title="姓名：用户的真实姓名！"
	  				<s:if test='"view".equals(mode)'>readonly</s:if> value="${user.name}"
	  				class="form-txt" />
    		</div>
    		<div class='clearLine'>&nbsp;</div>
    	<s:if test='"add".equals(mode)'>
    	    <div class="grid_4 lable-right">
    	    <s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">密码：</label>
			</div>
			<div class="grid_7 heightAuto">
			    <input type="password" name="user.password" id="password" maxlength="16"
	  				 value="${mobileUser.password}" class="dialogtip form-txt"
	  				  title="请输入由6-16位字母、数字、特殊字符任意组合的密码" />
    		</div>

    		<div class="grid_1">
    		</div>
    		<div  class="grid_4 lable-right">
    		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
    			<label class="form-lbl">确认密码：</label>
    		</div>
    		<div class="grid_7 heightAuto">
			    <input type="password" name="confirmPwd" id="confirmPwd"  maxlength="16"
	  				value=""
	  				class="form-txt" />
    		</div>
    		<div class="grid_1">    		
    		</div>
    	</s:if>
			<div class='clearLine'>&nbsp;</div>
            <div  class="grid_4 lable-right">
            <s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
               <label class="form-lbl">手机：</label>
            </div>
            <div class="grid_7">
                <input type="text" name="user.mobile" id="mobile"  maxlength="11" title="请输入11位以1开头的联系手机 例如：13988888888"
                    <s:if test='"view".equals(mode)'>readonly</s:if> value="${user.mobile}"
                    class="form-txt" />
            </div>
            <div class="grid_1">            
            </div>
    		<div class="grid_4 lable-right">
    		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
    		  <label class="form-lbl">工作电话：</label>
    		</div>
    		<div class="grid_7">
			    <input type="text" name="user.workPhone" id="workPhone"  maxlength="13" title="请输入由数字和-组成的联系电话 例如：0577-88888888"
	  				<s:if test='"view".equals(mode)'>readonly</s:if> value="${user.workPhone}"
	  				class="form-txt" />
    		</div>

            <div class="grid_1">           
            </div>

    		<div class='clearLine'>&nbsp;</div>
    		<div  class="grid_4 lable-right">
   		       <label class="form-lbl">住宅电话：</label>
   		    </div>
    		<div class="grid_7">
			    <input type="text" name="user.homePhone" id="homePhone"  maxlength="13" title="请输入由数字和-组成的联系电话 例如：0577-88888888"
	  				<s:if test='"view".equals(mode)'>readonly</s:if> value="${user.homePhone}"
	  				class="form-txt" />
    		</div>
    		<div class="grid_1"></div>

            <div  class="grid_4 lable-right">
              <label class="form-lbl">工作单位：</label>
            </div>
            <div class="grid_7">
                <input type="text" name="user.workCompany" id="workCompany"  maxlength="21"
                    <s:if test='"view".equals(mode)'>readonly</s:if> value="${user.workCompany}"
                    class="form-txt" />
            </div>
    		<div class='clearLine'></div>
    		<div  class="grid_5 lable-right">
    			<label class="form-lbl">PC基本设置</label>
    		</div>
    		<div class="grid_19"><hr style="margin-top: 15px;" /></div>
    		<div class='clearLine'></div>
    		<div class="grid_2"></div>
			<div class="grid_10">
					<input id="pcusable" name="user.pcusable" type="checkbox" value="true" <s:if test="user.pcusable">checked="checked"</s:if>
					<s:if test='"view".equals(mode)'>disabled</s:if> />
					<label class="form-check-text" for="pcusable">是否pc可登录</label>
			</div>
			
			<div class="grid_10">
				<input id="connectVpdn" name="user.connectVpdn" type="checkbox" value="true" <s:if test="user.connectVpdn">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if>/>
				<label class="form-check-text" for="connectVpdn">登陆时是否连接vpdn</label>
			</div>
			
			<div class='clearLine'></div>
    		<div class="grid_2"></div>
	    	<div class="grid_10">
				<input id="changePassword" name="user.changePassword" type="checkbox" value="true" <s:if test="user.changePassword">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="changePassword">登录时必须修改密码</label>
			</div>
			<div class="grid_10">
				<input type="hidden" id="userIsAdmin" name="user.admin" value="${user.admin}"/>
				<label class="form-check-text">
					<input id="isAdmin" type="checkbox" value="true"
					<s:if test="user.admin" >checked="checked"</s:if>
					<s:if test='"view".equals(mode)'>disabled</s:if>/>
					是否为系统管理员
				</label>
			</div>
			<div class='clearLine'></div>
			<div class="grid_2"></div>
			<div class="grid_10">
				<input type="hidden" id="userIsFourthAccount" name="user.fourthAccount" value="${user.fourthAccount}"/>
				<label class="form-check-text">
					<input id="isFourthAccount" type="checkbox" value="true"
					 <s:if test="user.fourthAccount" >checked="checked"</s:if> 
					<s:if test='"view".equals(mode)||("edit".equals(mode)&&user.userName.equals("admin"))'>disabled</s:if>/>
					是否四级体系建设账号
				</label>
			</div>
    		<div class='clearLine'></div>
				<div  class="grid_5 lable-right">
    			<label class="form-lbl">手机基本设置</label>
    		</div>
    		<div class="grid_19"><hr style="margin-top: 15px;" /></div>
    		<div class='clearLine'></div>
			<div class="grid_2"></div>
			<div class="grid_10">
					<input id="mobileusable" name="user.mobileusable" type="checkbox" value="true" checked="checked" readonly />
					<label class="form-check-text" for="mobileusable">是否手机可登录</label>
			</div>
			<div class="grid_10">
				<input type="hidden" id="userIsValidatorImsi" name="user.validatorImsi" value="${user.validatorImsi}"/>
				<label class="form-check-text">
					<input id="isValidatorImsi" type="checkbox" value="true"
					<s:if test="user.validatorImsi" >checked="checked"</s:if>
					<s:if test='"view".equals(mode)'>disabled</s:if> />
					是否验证IMSI号
				</label>
			</div>
			<div class='clearLine'></div>
			<div class="grid_2"></div>
			<div class="grid_10">
				<input type="hidden" id="userIsGps" name="user.gps" value="${user.gps}"/>
				<label class="form-check-text">
					<input id="isGps" type="checkbox" value="true"
					 <s:if test="user.gps" >checked="checked"</s:if> 
					<s:if test='"view".equals(mode)||("edit".equals(mode)&&user.userName.equals("admin"))'>disabled</s:if>/>
					是否开启GPS定位
				</label>
			</div>
			<div class="grid_10">
				<input type="hidden" id="userIsFourTeams" name="user.fourTeams" value="${user.fourTeams}"/>
				<label class="form-check-text">
					<input id="isFourTeams" type="checkbox" value="true"
					 <s:if test="user.fourTeams" >checked="checked"</s:if> 
					<s:if test='"view".equals(mode)||("edit".equals(mode)&&user.userName.equals("admin"))'>disabled</s:if>/>
					是否开启四支队伍
				</label>
			</div>
			<div class='clearLine'></div>
    		<div id="userImsi" style="display: none;">
    			<div  class="grid_4 lable-right">
            		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
              	 	<label class="form-lbl">IMSI号：</label>
            	</div>
            	<div class="grid_18">
                	<input type="text" name="user.imsi" id="imsi"  maxlength="30" title="请输入手机IMSI码"
                   	<s:if test='"view".equals(mode)'>readonly</s:if> value="${user.imsi}"
                    	class="form-txt" />
            	</div>
            </div>
	<s:if test='!"view".equals(mode)'>
	   </form>
	</s:if>
</div>
<script type="text/javascript">

jQuery.validator.addMethod("checkPassWordStrong", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	var passwordVal=$(".password-strength-bar").text();
	if(passwordVal=="弱"){
		return false;
	}else{
		return true;
	}
});
jQuery.validator.addMethod("userNameMaxLength", function(value, element){
	<s:if test='"add".equals(mode)'>
		if(value==null||value==undefined||value==""){return true}
		var str = $("#userName").val();
		if(str.length>20){
			return false;
		}
		return true;
	</s:if>
	return true;
});
jQuery.validator.addMethod("checkUserName", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	var vpdn = $("#userName").val();
	var userName = vpdn.substring(0,vpdn.indexOf("@",1));
	if(userName.length==0){
		return false;
	}
	return true;
});
jQuery.validator.addMethod("isDigitOrStrOrUnderline", function(value, element){
	<s:if test='"add".equals(mode)'>
		if(value==null||value==undefined||value=="" ){return true};
		var patrn=/^[A-Za-z0-9_]+$/;
		var vpdn = $("#userName").val();
		if(!patrn.exec(vpdn.replace(/[ ]/g,""))){
			return false;
		}
		return true;
	</s:if>
	return true;
});

jQuery.validator.addMethod("userVpdnValidator", function(value, element){//mobileUser.vpdn
	<s:if test='"add".equals(mode)'>
		if(value==null||value==undefined||value=="" ){return true};
		//var patrn=/^[A-Za-z0-9_]+$/;
		var patrn=/^@[A-Za-z0-9_]+.sg$/;
		
		var vpdn = $("#uservpdn").val();
		if(!patrn.exec(vpdn.replace(/[ ]/g,""))){
			return false;
		}
		return true;
	</s:if>
	return true;
});
jQuery.validator.addMethod("ishasSameName", function(value, element){
	var flag=true;
	$.ajax({
		async:false,
		url:"${path}/sysadmin/userManage/validateMobileUserName.action",
		data:{
			"user.userName": function(){ return $('#userName').val()},
			"mode" : function(){ return $('#mode').val()},
			"user.id" : function(){ return $('#userId').val()},
			"user.vpdn":function(){ return $('#uservpdn').val()}
			//,"organizationId":function(){ return $('#organizationId').val()}
		},
		success:function(responseData){
			flag = responseData;
		}
	});
	return flag;
});

//验证imsi码的唯一性
jQuery.validator.addMethod("ishasSameImsi", function(value, element){
	var isValidatorImsiChecked=$("#isValidatorImsi").attr("checked");
	var imsiTemp=$("#imsi").val();
	var flag=true;
	if(isValidatorImsiChecked&&(imsiTemp!="" || imsiTemp!=null || typeof(imsiTemp)!="undefined")){
		$.ajax({
			async:false,
			url:"${path}/sysadmin/userManage/validateHasSameUserImsi.action",
			data:{
				"user.imsi": function(){ return $('#imsi').val()},
				"mode" : function(){ return $('#mode').val()},
				"user.id" : function(){ return $('#userId').val()}
			},
			success:function(responseData){
				flag = responseData;
			}
		});
	}
	
	return flag;
});

jQuery.validator.addMethod("iSImsiRequired", function(value, element){
	var isValidatorImsi=$("#isValidatorImsi").attr("checked");
	var imsiTemp=$("#imsi").val();
	if(isValidatorImsi&&(imsiTemp=="" || imsiTemp==null || typeof(imsiTemp)=="undefined")){
			return false;
		}
		return true;
});

function initMobileUserParameter(){
	var mode="${mode }";
	var isValidatorImsi=${user.validatorImsi };
	if(mode!='add'){
		$("#userDiv").attr("class","grid_7");
		if(isValidatorImsi){
			$("#userImsi").show();
		}
	}
}

$(document).ready(function(){
	
	initMobileUserParameter();
	
	//登录时必须修改密码
	$("#changePassword").change(function(event){
		$("#user\\.changePassword").attr("value",$("#changePassword").attr("checked"));
	});
	//登陆时是否连接vpdn
	$("#connectVpdn").change(function(event){
		$("#user\\.connectVpdn").attr("value",$("#connectVpdn").attr("checked"));
	});
	//是否pc可登录
	$("#pcusable").change(function(event){
		$("#user\\.pcusable").attr("value",$("#pcusable").attr("checked"));
	});
	//是否四级平台账号
	$("#isFourthAccount").change(function (event){
		if($("#isFourthAccount").attr("checked")=="checked"){
			$("#userIsFourthAccount").val(true);
		}else{
			$("#userIsFourthAccount").val(false);
		}
	});
	//是否GPS定位
	$("#isGps").change(function (event){
		if($("#isGps").attr("checked")=="checked"){
			$("#userIsGps").val(true);
		}else{
			$("#userIsGps").val(false);
		}
	});	
	//是否四支队伍
	$("#isFourTeams").change(function (event){
		if($("#isFourTeams").attr("checked")=="checked"){
			$("#userIsFourTeams").val(true);
		}else{
			$("#userIsFourTeams").val(false);
		}
	});	
	//是否验证IMSI号
	$("#isValidatorImsi").change(function (event){
		if($("#isValidatorImsi").attr("checked")){
			$("#userImsi").show();
		}else{
			$("#userImsi").hide();
			//$("#imsi").attr("value","")
		}
		if($("#isValidatorImsi").attr("checked")=="checked"){
			$("#userIsValidatorImsi").val(true);
		}else{
			$("#userIsValidatorImsi").val(false);
		}
	});
	
	$("#password").passwordCheck({
		keyUp:function(score){
			scoreResult = score;
		}
	});
	//是否为系统管理员
	$("#isAdmin").change(function(event){
		if ($("#isAdmin").attr("checked")){
			$.confirm({
				title:"警告",
				message:"超级管理员将自动拥有系统的所有管理权限，通常不建议设置超级管理员，您是否继续将该用户设置为超级管理员？",
				okFunc:function(){$("#isAdmin").attr("checked",true)},
				cancelFunc:function(){$("#isAdmin").removeAttr("checked");$("#userIsAdmin").val(false);}
				});
			}
		if($("#isAdmin").attr("checked")=="checked"){
			$("#userIsAdmin").val(true);
		}else{
			$("#userIsAdmin").val(false);
		}
	});
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
						$.messageBox({
                            message:data,
                            level:"error"
                        });
            			return;
					}
					
	                if("add"=="${mode}"){
	                	 $.messageBox({message:"手机账号新增成功！"});
	                	 $("#mobileUserList").trigger("reloadGrid");
	                }
	                if("edit"=="${mode}"){
	                	 $("#mobileUserList").trigger("reloadGrid");
	                	 $.messageBox({message:"手机账号修改成功！"});
	                }
	                $("#mobileUserMaintanceDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"user.userName":{
					required:true,
					//isDigitStrAndUnderline:true,
					minlength:1,
					userNameMaxLength:20,
					isDigitOrStrOrUnderline:true,
					ishasSameName:true
				},
				"user.vpdn":{
					required:true,
					userVpdnValidator:true
					
				},
			"user.password":{
					required:true,
					minlength:6,
					maxlength:24,
					checkPassWordStrong:true
				},
			"confirmPwd":{
					required:true,
					equalTo:"#password"
				},
			"user.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:10
				},
			"user.mobile":{
					required:true,
					mobile:true
			},
			"user.workPhone":{
				required:true,
				telephone:true
			},

			"user.homePhone":{
				telephone:true
			},
			"user.imsi":{
				digits:true,
				iSImsiRequired:true,
				ishasSameImsi:true
			}
		},
		messages:{
			"user.userName":{
				required:"请输入用户名",
				//isDigitStrAndUnderline:'"@"号前面的字符串只能由数字、字母、下划线组成',
				minlength:$.format('"@"符号之前最少输入1位'),
				userNameMaxLength:$.format('"@"符号之前最多输入20位'),
				isDigitOrStrOrUnderline:'"@"号前面的字符串只能由数字、字母、下划线组成',
				ishasSameName:"该用户名已经存在"
				},
				"user.vpdn":{
					required:"请输入",
					userVpdnValidator:"请正确输入如：@gasg"
				},
			"user.password":{
				required:"请输入用户密码",
				minlength:$.format("密码至少需要输入{0}个字符"),
				maxlength:$.format("密码至少需要输入{0}个字符"),
				checkPassWordStrong:"密码过弱，建议输入由6-16位字母、数字、特殊字符任意两种或三种组合的密码"
			},
			"confirmPwd":{
				required:"请再次输入密码",
				equalTo:"两次输入密码不一致"
			},
			"user.name":{
				required:"请输入姓名",
				exculdeParticalChar:"姓名只能输入字母，数字和中文字符",
				maxlength:$.format("姓名最多需要输入{0}个字符"),
				minlength:$.format("姓名至少需要输入{0}个字符")
			},
			
			"user.mobile":{
				required:"请输入手机",
				mobile:"手机输入只能是以1开头的11位数字"
			},
			"user.workPhone":{
				required:"请输入工作电话",
				telephone:"工作电话不合法，只能输数字和横杠(-)"
			},
			"user.homePhone":{
				telephone:"住宅电话不合法，只能输数字和横杠(-)"
			},
			"user.imsi":{
				digits:"必须输入数字",
				iSImsiRequired:"必须输入imsi号",
				ishasSameImsi:"该imsi码已经存在"
			}
		}
	});
});

</script>