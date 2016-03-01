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

<div id="dialog-form" title="用户维护" class="container container_24">
	<s:if test='!"view".equals(mode)'>
	 <form id="maintainForm" method="post"	action="" >
	</s:if>
	        <input id="vpdn" type="hidden" name="" value="${vpdnRule}">
			<input id="mode"	type="hidden" name="mode" value="${mode}" />
			<input id="organizationId"	type="hidden" name="organizationId" value="${organizationId}" />
			<input id="userId"	type="hidden" name="user.id" value="${user.id}" />
			<s:if  test='!"view".equals(mode)'>
				<pop:token/>
			</s:if>
			<div  class="grid_4 lable-right" >
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">用户名： </label>
			</div>
			<div class="grid_3" id="userDiv">
			    	<input type="text" name="user.userName" id="userName" maxlength="32" style="ime-mode:disabled"
	  				<s:if test='!"add".equals(mode)'>readonly</s:if> value="${user.userName}"
	  				class="form-txt" title="用户名：用户登录系统的帐号！"/>
	  		</div>
	  		<s:if test='"add".equals(mode)'>
		  		<div class="grid_4">
		  		     <input type="text" name="user.vpdn" id="uservpdn" value="@${vpdnRule}" 
		  		     readonly
		  		     style="ime-mode:disabled" class="form-txt"/>
		  		</div>
	  		</s:if>
	  		<div class="grid_1">
			
			</div>
	  		<div  class="grid_4 lable-right">
	  		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	  			<label class="form-lbl">姓名：</label>
	  		</div>
			<div class="grid_7">
			    <input type="text" name="user.name" id="name" maxlength="20" title="姓名：用户的真实姓名！"
	  				<s:if test='"view".equals(mode)'>readonly</s:if> value="${user.name}"
	  				class="form-txt" />
    		</div>
    		<div class="grid_1">
			
			</div>

    		<div class='clearLine'>&nbsp;</div>
    	<s:if test='"add".equals(mode)'>
    	    <div class="grid_4 lable-right">
    	    <s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
				<label class="form-lbl">密码：</label>
			</div>
			<div class="grid_7 heightAuto">
			    <input type="password" name="user.password" id="password" maxlength="16"
	  				 value="${user.password}" class="dialogtip form-txt"
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
            <s:if test="operateUser.admin">
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
    		<div class="grid_2"></div>
	    	<div class="grid_10">
				<input id="changePassword" name="user.changePassword" type="checkbox" value="true" <s:if test="user.changePassword">checked="checked"</s:if>
				<s:if test='"view".equals(mode)'>disabled</s:if> />
				<label class="form-check-text" for="changePassword">登录时必须修改密码</label>
			</div>
			<div class='clearLine'></div>
			<div class="grid_2"></div>
			<div class="grid_10">
					<input type="hidden" id="userIsAdmin" name="user.admin" value="${user.admin}"/>
					<label class="form-check-text">
						<input id="isAdmin" type="checkbox" value="true"
						<s:if test="user.admin" >checked="checked"</s:if>
						<s:if test='"view".equals(mode)||("edit".equals(mode)&&user.userName.equals("admin"))'>disabled</s:if>/>
						是否为系统管理员
					</label>
				</div>
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
			</s:if>
						
			<div class='clearLine'></div>
    		<div class="grid_4 lable-right">
				<em class="form-req"></em>
				<label class="form-lb1">岗位：&nbsp;&nbsp;&nbsp;&nbsp;</label>
			</div>
			<div class="grid_15" style="height: 50px;">
				<textarea rows="3"  cols="2" style="width: 100% ; background-color: white;" readonly="readonly"  id="roles_panel" ></textarea>
				<select id="roleIds" name="roleIds" multiple="multiple"  style="display:none"></select>
			</div>
			<div class="grid_2" style="padding-left: 24px;">
				<s:if test='@com.tianque.core.util.ThreadVariable@getUser().isAdmin()||"add".equals(mode)'>
					<input id="selectRoles" type="button" value="选择岗位" class="form-button">
				</s:if>
			</div> 
			<div class='clearLine' style="padding-top: 8px;"></div>
    		<div class="grid_4 lable-right">
				<em class="form-req"></em>
				<label class="form-lb1">负责部门：</label>
			</div>
			<div class="grid_15" style="height: 50px;">
				<textarea rows="3"  cols="2" style="width: 100% ; background-color: white;" readonly="readonly" id="zoneInfos_panel" ></textarea>
				<!-- <div class="grid_23 ui-widget-border" id="zoneInfos_panel" style="margin-top:30px;height:100px;"></div> -->
				<select id="zoneIds" name="zoneIds" multiple="multiple" style="display:none"></select>
			</div>
			<div class="grid_2" style="padding-left: 24px;">
				<s:if test='@com.tianque.core.util.ThreadVariable@getUser().isAdmin()||"add".equals(mode)'>
					<input id="selectDept" type="button" value="选择部门" class="form-button">
				</s:if>
			</div> 
		
			<s:if test="operateUser.admin">
			<div class='clearLine'></div>
				<div  class="grid_5 lable-right">
    			<label class="form-lbl">手机基本设置</label>
    		</div>
    		<div class="grid_19"><hr style="margin-top: 15px;" /></div>
    		<div class='clearLine'></div>
    		<div class="grid_2"></div>
			<div class="grid_10">
					<input id="mobileusable" name="user.mobileusable" type="checkbox" value="true" <s:if test="user.mobileusable">checked="checked"</s:if>
					<s:if test='"view".equals(mode)'>disabled</s:if> />
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
			</s:if>
			
			<br>
			<div class='clearLine'></div>
			
	<s:if test='!"view".equals(mode)'>
	   </form>
	</s:if>
	<div id="rolesDialog"></div>
</div>
<script type="text/javascript">
var scoreResult=0;
function initUserParameter(){
	var mode="${mode }";
	var isValidatorImsi=${user.validatorImsi };
	if(mode!='add'){
		if(isValidatorImsi){
			$("#userImsi").show();
		}
	}
}
$(document).ready(function(){
	initUserParameter();
	
	if($("#connectVpdn").attr("checked")){
		$("#user\\.connectVpdn").val("true");
	}else{
		$("#user\\.connectVpdn").val("false");
	}
	var resultVpdn = $("#uservpdn").val();
	if("@"==resultVpdn){
		$("#uservpdn").attr("readonly","");
	}
	<s:if test='!"add".equals(mode)'>
	    $("#userDiv").attr("class","grid_7");
	    
    </s:if>
	$(".dialogtip").inputtip();
	$(function() {
		$( "#tabs" ).tabs();
	});
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
	jQuery.validator.addMethod("ishasSameName", function(value, element){
		var flag=true;
		$.ajax({
			async:false,
			url:"${path}/sysadmin/userManage/validateUserName.action",
			data:{
				"user.userName": function(){ return $('#userName').val()},
				"mode" : function(){ return $('#mode').val()},
				"user.id" : function(){ return $('#userId').val()},
				"user.vpdn":function(){ return $('#uservpdn').val()},
				"organizationId":function(){ return $('#organizationId').val()}
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
	<s:if test='!"view".equals(mode)'>
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				clearZonesIfNotGant();
	         $(form).ajaxSubmit({
	             success: function(data){
	                     if(data==null || !data.id){
	                    	 $.messageBox({
								message:data,
								level: "error"
							 });
	                     	return;
	                     }
	        	   		 <s:if test='"add".equals(mode) || "copy".equals(mode) '>
					    	$("#userList").addRowData(data.id,data,"first");
					    	$.messageBox({message:"成功保存新用户信息!"});
					    	$("#userList").trigger("reloadGrid");
					     </s:if>
					     <s:if test='"edit".equals(mode)'>
					        $("#userList").setRowData(data.id,data);
						    $.messageBox({message:"成功保存用户修改信息!"});
						    $("#userList").trigger("reloadGrid");
					     </s:if>
					     $("#userMaintanceDialog").dialog("close");
					     $("#userList").setSelection(data.id);
					     $("#userList").trigger("reloadGrid");
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
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
		$("#connectVpdn").change(function(event){
			$("#user\\.connectVpdn").attr("value",$("#connectVpdn").attr("checked"));
		});
		$('#selectzones li').hover(
			function() { $(this).addClass('ui-state-hover'); },
			function() { $(this).removeClass('ui-state-hover');}
		);
		/***改造添加部门**/
		$("#selectDept").click(function(event){
			$("#noticeDialog").createDialog({
				width: 500,
				height: 450,
				title:'请选择一个部门',
				url:'${path}/sysadmin/userManage/prepareZoneSelection.action?organizationId='+$("#organizationId").val(),
				buttons: {
				   	"确定" : function(){
						commitZone();
						$("#noticeDialog").dialog("close");
				   	},
				   	"取消":function(){
						$("#noticeDialog").dialog("close");
				   	}
				}
			});
		});
		
		$("#selectRoles").click(function(event){
			$("#rolesDialog").createDialog({
				width: 500,
				height: 500,
				title:'请选择岗位',
				//url:'${path}/sysadmin/userManage/prepareZoneSelection.action?organizationId='+$("#organizationId").val(),
				url:'${path}/sysadmin/userManage/searchSelectRoleDlg.jsp',
				buttons: {
				   	"确定" : function(){
				   		fillSelectRolesInputer("roles_panel","roleIds");
						$("#rolesDialog").dialog("close");
				   	},
				   	"取消":function(){
						$("#rolesDialog").dialog("close");
				   	}
				}
			});
		});
		
	</s:if>
	<s:if test="user.multiZone.size>0">
		$("#hasMultiZone").attr("checked",true);
		setMultiZoneVisabled(true);
		<s:iterator id="multiZone" value="user.multiZone" status="sta">
			addZone(${multiZone.id},'${multiZone.orgName}');
		</s:iterator>
		reviewZoneNameList();
	</s:if>
	
	
	<s:if test="user.roles.size>0">
		var roleNames= "";
		
		<s:iterator var="userRole" value="user.roles" status="sta">
			var rootRoleSelect=$("#roleIds");
			roleNames += "[" +'<s:property value='#userRole.roleName'/>' +"],";
			$("<option>").attr("id","role_"+<s:property value='#userRole.id'/>).val(<s:property value='#userRole.id'/>).attr("selected",true).appendTo(rootRoleSelect);
			</s:iterator>
			roleNames = roleNames.substring(0,roleNames.length-1);
			$("#roles_panel").empty().append(roleNames);
	</s:if>

		
	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/sysadmin/userManage/addUser.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#maintainForm").attr("action","${path}/sysadmin/userManage/updateUser.action");
	</s:if>
	$.ajax({
		url:"${path}/sysadmin/userManage/isSuperAdmin.action",
		success:function(responseData){
			if (responseData==false){
				$("#is_admin_panel").css("display","none");
				}else{
				$("#is_admin_panel").css("display","block");
				}
			}
	});
});

function removeZone(zone){
	$("#zone_"+zone.id).remove();
	$("#zoneId_"+zone.id).remove();
}

function hasSelectZone(zoneId){
	var zones=$("#zoneIds").find("option");
	for (var index=0;index<zones.length;index++){
		if (zoneId==zones[index].value){
			return true;
		}
	}
	return false;
}

function addZone(zoneId,zoneName){
	if (hasSelectZone(zoneId)) return;
	$("<option>").attr("id","zoneId_"+zoneId).val(zoneId).attr("selected",true).text(zoneName).appendTo($("#zoneIds"));
}

function containedRole(selectedRoles,role){
	for (var index=0;index<selectedRoles.length;index++){
  	   	 var rowid=selectedRoles[index];
  	   	 if (rowid==role) return true;
	}
	return false;
}


function doNothing(){}

function setMultiZoneVisabled(visiabled){
	if (visiabled){
		<s:if test='!"view".equals(mode)'>
		$("#addzone_panel").css("display","block");
		</s:if>
		$("#zones").css("display","block");
	}else{
		$("#addzone_panel").css("display","none");
		$("#zones").css("display","none");
	}
}

function reviewZoneNameList(){
	var zones=$("#zoneIds").find("option");
	var zoneInfos_panel_txt="";
	for (var index=0;index<zones.length;index++){
		if(zoneInfos_panel_txt==null||zoneInfos_panel_txt=="")
			zoneInfos_panel_txt="["+zones[index].text+"]";
		else
			zoneInfos_panel_txt=zoneInfos_panel_txt+",["+zones[index].text+"]";
	}
	$("#zoneInfos_panel").text(zoneInfos_panel_txt);
}

function clearZonesIfNotGant(){
	if($("#hasMultiZone").attr("checked")==false){
		$("#zoneIds").empty();
	}
}
</script>