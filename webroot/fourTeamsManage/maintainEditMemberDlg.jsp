<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>

<div id="dialog-form" title="用户维护" class="container container_24">
	 <form id="maintainForm" method="post"	action="" >
	<pop:token />
        <input id="id"	type="hidden" name="fourTeamMembers.id" value="${fourTeamMembers.id}" />
  		<div class="grid_4 lable-right">
   			<em class="form-req" id="maritalStateStat" >*</em>
			<label class="form-lbl">服务网格： </label>
		</div>
		<div class="grid_7" id="userDiv">
			<input name="fourTeamMembers.serviceName" id="serviceName" maxlength="30" class="form-txt" value="${fourTeamMembers.serviceName }"/>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">姓名： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.names" id="names" maxlength="10" class="form-txt" value="${fourTeamMembers.names}"/>
  		</div>
  		
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">所在单位： </label>
		</div>
		<div class="grid_7" id=""> 
		    	<input type="text" name="fourTeamMembers.memberDepartement" maxlength="100" id="memberDepartement" value="${fourTeamMembers.memberDepartement}" class="form-txt"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
  			<em class="form-req" id="maritalStateStat"  >*</em>
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
		    	<input type="text" name="fourTeamMembers.duty" id="duty" maxlength="32" class="form-txt" value="${fourTeamMembers.duty}"/>
  		</div>
  		
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">特长： </label>
		</div>
		
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.specialty" id="specialty" maxlength="20" class="form-txt" value="${fourTeamMembers.specialty}"/>
  		</div>
  		
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">联系手机： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.mobile" id="mobile" maxlength="11" class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" value="${fourTeamMembers.mobile}"/>
  		</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">出生日期： </label>
		</div>
		<div class="grid_7">
			<input type="text" name="fourTeamMembers.birthday" id="birthday" value="<s:date name='fourTeamMembers.birthday' format='yyyy-MM-dd'/>" 	 class="form-txt" readonly="readonly" />
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">联系电话： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.telephone" id="telephone" maxlength="20" class="form-txt {telephone:true,messages:{telephone:$.format('固定电话不合法，只能输入数字和横杠（-）')}}" value="${fourTeamMembers.telephone}"/>
  		</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">网格管理员姓名： </label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.orgAdminName" id="orgAdminName" maxlength="10" class="form-txt" value="${fourTeamMembers.orgAdminName}"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div  class="grid_4 lable-right" >
			<label class="form-lbl">网格管理员电话：</label>
		</div>
		<div class="grid_7" id="userDiv">
		    	<input type="text" name="fourTeamMembers.orgAdminTelephone" id="orgAdminTelephone" maxlength="20" class="form-txt {telephone:true,messages:{telephone:$.format('固定电话不合法，只能输入数字和横杠（-）')}}" value="${fourTeamMembers.orgAdminTelephone}"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_19">
   			<textarea rows="5" cols="77" name="fourTeamMembers.comments" maxlength="100" id="comments" class="form-txt">${fourTeamMembers.comments }</textarea>
   		</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#birthday').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d'
	});

	jQuery.validator.addMethod("mobileLength", function(value, element){
		
		if(value==null||value==undefined||value==""){return true}
		var str = $("#mobile").val();
		if(str.length!=11){
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
	return true;
});

	jQuery.validator.addMethod("commentsLength", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			var str = $("#comments").val();
			if(str.length>300){
				return false;
			}
			return true;
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
			        	 $("#maintenanceTeamList").setRowData(data.id,data);
						    $.messageBox({
						    	message:"成功保存修改信息!"
						    	});
					     
					     $("#maintenanceTeamDialog").dialog("close");
					     $("#maintenanceTeamList").setSelection(data.id);
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
				memberDepartementLength:true
			}

		},
		messages:{
			"fourTeamMembers.serviceName":{
				required:"请输入服务网格!",
				serviceName:"至少需要两个字符"
			},
			"fourTeamMembers.names":{
				required:"请输入姓名!",
				memberNames:"至少需要两个问题"
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
				memberDepartementLength:"所属单位最多输入300个字符"
			}
		}
		});


	
	
		$("#maintainForm").attr("action","${path}/fourTeamsManage/editTeamMember.action?id="+${param.id});
	

});

</script>