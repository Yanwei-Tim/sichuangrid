<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>

<div id="dialog-form" title="成员维护" class="container container_24">
	 <form id="maintainForm" method="post"	action="" >
	<pop:token />
        <input id="id"	type="hidden" name="fourTeams.id" value="${fourTeams.id}" />
        <input id="orgId"	type="hidden" name="organizationId" />
		<div class="grid_4 lable-right">
   			<em class="form-req" id="maritalStateStat" >*</em>
			<label class="form-lbl">服务网格： </label>
		</div>
		<div class="grid_7" id="userDiv">
			<input name="fourTeamMembers.serviceName" id="serviceName" maxlength="30" class="form-txt"/>
  		</div>
	
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">姓名： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.names" id="names" maxlength="10" class="form-txt {validatorName:true,messages:{validatorName:'姓名不能输入特殊字符'}}"/>
  		</div>
  		
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">所在单位： </label>
		</div>
		<div class="grid_7" id=""> 
		    	<input type="text" name="fourTeamMembers.memberDepartement" id="memberDepartement" value="${fourTeamMembers.memberDepartement}" class="form-txt"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
  			<em class="form-req" id="maritalStateStat" >*</em>
			<label class="form-lbl">性别： </label>
		</div>
		<div class="grid_7" id="userDiv">
			<select name="fourTeamMembers.gender.id" id="gender" class="form-txt">
		    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"  defaultValue="${fourTeamMembers.gender.id }"/>
		    </select>
  		</div>
  		
  		<div  class="grid_4 lable-right" >
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">政治面貌： </label>
		</div>
		<div class="grid_7" id="userDiv">
				<select name="fourTeamMembers.politics.id" id="politics" class="form-txt">
		    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND"  defaultValue="${fourTeamMembers.politics.id }"/>
		    </select>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div  class="grid_4 lable-right" >
			<label class="form-lbl">职务： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.duty" id="duty" maxlength="32" class="form-txt"/>
  		</div>
  		
  		
  		 
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">特长： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.specialty" id="specialty" maxlength="20" class="form-txt"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">联系手机： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.mobile" id="mobile" maxlength="11" class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}"/>
  		</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">出生日期： </label>
		</div>
		<div class="grid_7">
			<input type="text" name="fourTeamMembers.birthday" id="birthday" readonly class="form-txt"
			value="<fmt:formatDate value="${fourTeamMembers.birthday}" type="date" pattern="yyyy-MM-dd" />"/>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">联系电话： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.telephone" id="telephone" maxlength="20" class="form-txt {telephone:true,messages:{telephone:$.format('固定电话不合法，只能输入数字和横杠（-）')}}"/>
  		</div>
  		
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">网格管理员姓名： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.orgAdminName" id="orgAdminName" maxlength="6" class="form-txt"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">网格管理员电话： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.orgAdminTelephone" id="orgAdminTelephone" maxlength="20" class="form-txt {telephone:true,messages:{telephone:$.format('固定电话不合法，只能输入数字和横杠（-）')}}"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_19">
   			<textarea rows="5" cols="77"  maxlength="100" name="fourTeamMembers.comments" id="comments" class="form-txt"></textarea>
   		</div>
	</form>
</div>	
<script type="text/javascript">
var bol = false;
$(document).ready(function(){
	$('#birthday').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d'
	});
	jQuery.validator.addMethod("validatorName", validatorSpecialWord);

	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！-]");
		return this.optional(element)||!pattern.test(value) ; 
	}

	jQuery.validator.addMethod("mobileLength", function(value, element){
		
		if(value==null||value==undefined||value==""){return true}
		var str = $("#mobile").val();
		if(str.length!=11){
			return false;
		}
		return true;
	});


	jQuery.validator.addMethod("commentsLength", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			var str = $("#comments").val();
			if(str.length>300){
				return false;
			}
		return true;
	});
	
	jQuery.validator.addMethod("memberDepartementLength", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var str = $("#memberDepartement").val();
		if(str.length>300){
			return false;
		}
	return true;
});
	
	jQuery.validator.addMethod("serviceName", function(value, element){
		var str = $("#serviceName").val();
		if(str.trim().length>30 || str.trim().length<2){
			return false;
		}
	return true;
	});
	
	jQuery.validator.addMethod("memberNames", function(value, element){
		var str = $("#names").val();
		if(str.trim().length>30 || str.trim().length<2){
			return false;
		}
		return true;
	});
	
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
			        	 if(data==null || !data.id){
		                	 $.messageBox({
								message:data,
								level: "error"
							 });
		                 	return;
		                 }
			        	 $("#searchText").attr("value","请输入姓名或拼音");
					     refreshMemberList();
						 $.messageBox({message:"成功保存信息!"});
						 $("#teamManagementLists").trigger("reloadGrid");
						 $("#teamManagementList").trigger("reloadGrid");
					     $("#addMemberDialogs").dialog("close");
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
			rules:{
				"fourTeamMembers.serviceName":{
					required:true,
					serviceName:true
				},
				"fourTeamMembers.names":{
					required:true,
					exculdeParticalChar:true,
					memberNames:true
				},
				"fourTeamMembers.gender.id":{
					required:true
				},
				"fourTeamMembers.politics.id":{
					required:true
				},
				"fourTeamMembers.mobile":{
					mobileLength:true
					
				},
				"fourTeamMembers.comments":{
					commentsLength:true
				},
				"fourTeamMembers.memberDepartement":{
					memberDepartementLength:true,
					exculdeParticalChar:true
				}
	
			},
			messages:{
				"fourTeamMembers.serviceName":{
					required:"请输入服务网格名称",
					serviceName:"至少需要两个字符"
				},
				"fourTeamMembers.names":{
					required:"请输入姓名!",
					exculdeParticalChar:"不能输入特殊字符",
					memberNames:"至少需要两个字符"
				},
				"fourTeamMembers.gender.id":{
					required:"请选择性别!"
				},
				"fourTeamMembers.politics.id":{
					required:"请选择政治面貌!"
				},
				"fourTeamMembers.mobile":{
					mobileLength:"联系手机必须是11位!"
				},
				"fourTeamMembers.comments":{
					commentsLength:"备注最多需要输入300个字符!"
				},
				"fourTeamMembers.memberDepartement":{
					memberDepartementLength:"所属单位最多输入300个字符",
					exculdeParticalChar:"不能输入特殊字符"
				}
			}
		});


	
	
		$("#maintainForm").attr("action","${path}/fourTeamsManage/addTeamMember.action?id="+${param.id});
	

});

</script>