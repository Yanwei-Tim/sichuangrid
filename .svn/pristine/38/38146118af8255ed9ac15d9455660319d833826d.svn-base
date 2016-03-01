<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="困难群众台账-家庭成员维护" class="container container_24">
	<form id="maintainPoorPeopleMembersForm" method="post" action="<s:if test='"edit".equals(mode)'>/account/poorPeopleMemberManage/updatePoorPeopleMembers.action</s:if><s:elseif test='"add".equals(mode)'>/account/poorPeopleMemberManage/addPoorPeopleMembers.action</s:elseif>" >
	<pop:token/>
	<input type="hidden" name="poorPeopleMembers.id" value="${poorPeopleMembers.id}" />
	<input type="hidden" name="poorPeopleMembers.poorPeople.id" value="${poorPeopleMembers.poorPeople.id}" />
	<div  class="grid_4 lable-right" >
		<em class="form-req">*</em>
		<label class="form-lbl">姓名： </label>
	</div>
	<div class="grid_7" id="userDiv">
   		<input type="text" name="poorPeopleMembers.name" value="${poorPeopleMembers.name}" maxlength="10" 
   			class="form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,validatorNativePlaceAddress:true,messages:{required:'姓名必须输入',exculdeParticalChar:'不能输入非法字符',minlength:$.format('姓名至少需要输入{0}个字符'),maxlength:$.format('姓名最多需要输入{0}个字符'),validatorNativePlaceAddress:'姓名不能输入特殊字符'}}" />
	</div>
	<div class="grid_5 lable-right">
	    <label class="form-lbl">性别：</label>
	</div>
	<div class="grid_7">
   		<select name="poorPeopleMembers.gender.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${poorPeopleMembers.gender.id}"/></select>
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">出生年月：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="poorPeopleMembers.birthday" id="birthDay" value="<s:date name="poorPeopleMembers.birthday" format="yyyy-MM-dd"/>" readonly="readonly" class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
	    <label class="form-lbl">政治面貌：</label>
	</div>
	<div class="grid_7">
   		<select name="poorPeopleMembers.politiCalbackGround.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${poorPeopleMembers.politiCalbackGround.id}"/></select>
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">学历：</label>
	</div>
	<div class="grid_7">
   		<select name="poorPeopleMembers.schooling.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${poorPeopleMembers.schooling.id}"/></select>
	</div>
    <div class="grid_5 lable-right"><label class="form-lbl">民族：</label></div>
    <div class="grid_7">
   		<select name="poorPeopleMembers.nation.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${poorPeopleMembers.nation.id}"/></select>
    </div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
	    <label class="form-lbl">职业：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="poorPeopleMembers.career" value="${poorPeopleMembers.career }" maxlength="20" class="form-txt {maxlength:20,messages:{maxlength:$.format('职业最多需要输入{0}个字符')}}" />
	</div>
	<div class="grid_5 lable-right">
	    <label class="form-lbl">健康状况：</label>
	</div>
	<div class="grid_7">
   		<select name="poorPeopleMembers.healthState.id" class="form-txt">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HEALTH_STATE" defaultValue="${poorPeopleMembers.healthState.id}"/></select>
	</div>
	<div class='clearLine'></div>
    <div class="grid_4 lable-right">
   		<em class="form-req">*</em>
   		<label class="form-lbl">与户主关系：</label></div>
    <div class="grid_7">
   		<select name="poorPeopleMembers.relationShipWithHead.id" class="form-txt {required:true,messages:{required:'与户主关系必须选择'}}">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" defaultValue="${poorPeopleMembers.relationShipWithHead.id}"/></select>
    </div>
    <div class="grid_5 lable-right">
   		<em class="form-req">*</em>
   		<label class="form-lbl">纳入低保（五保）情况：</label></div>
    <div class="grid_7">
   		<select name="poorPeopleMembers.insuranceType.id" class="form-txt {required:true,messages:{required:'纳入低保（五保）情况必须选择'}}">
	   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INSURANCETYPE" defaultValue="${poorPeopleMembers.insuranceType.id}"/></select>
    </div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#birthDay').datePicker({
		yearRange: '1900:2030',
   		dateFormat: 'yy-mm-dd',
		maxDate:'+0d'
	});
	$("#maintainPoorPeopleMembersForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
	             success: function(data){
                     if(data==null || !data.id){
                    	 $.messageBox({ message:data, level: "error" });
                     	return;
                     }
        	   		 <s:if test='"add".equals(mode) || "copy".equals(mode) '>
				    	$.messageBox({message:"新增成功!"});
				     </s:if>
				     <s:if test='"edit".equals(mode)'>
				    	$.messageBox({message:"修改成功!"});
				     </s:if>
					$("#poorPeopleMembersList").trigger("reloadGrid");
				     $("#poorPeopleMembersDialog").dialog("close");
				     $("#poorPeopleMembersList").setSelection(data.id);
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
			});
		}
	});
});
</script>