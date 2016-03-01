<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<style>
	.propertyMulSelect {overflow:hidden;}
	.propertyMulSelect li{float:left;width:49%;}
	.tip-yellowsimple{display:none;}
</style>
	<span style="font-weight: bold; margin-left:20px; ">重点青少年</span>
	<div class='clearLine'>&nbsp;</div>
	
<div class="grid_4 lable-right">
	<label class="form-lbl">监护人：</label>
</div>
<div class="grid_8 lable-right">
	<input type="text" name="population.guardianName" id="guardianName"  maxlength="20"
		value="${population.guardianName}" class="form-txt {exculdeParticalChar:true,minlength:2,maxlength:20,messages:{exculdeParticalChar:'不能输入非法字符',minlength:$.format('监护人至少需要输入{0}个字符'),maxlength:$.format('监护人最多需要输入{0}个字符')}}"/>
</div>

<div class="grid_4 lable-right">
	<label class="form-lbl">联系方式：</label>
</div>
<div class="grid_8 lable-right">
	<input type="text" name="population.guardianTelephone" id="guardianTelephone"  maxlength="11"
		value="${population.guardianTelephone}" class="form-txt {mobile:true,messages:{mobile:'联系方式输入只能是以1开头的11位数字'}}" />
</div>
<div class='clearLine'>&nbsp;</div>	
	
<div class="grid_4 lable-right">
	<label class="form-lbl">工作情况：</label>
</div>
<div class="grid_8 lable-right">
	<input type="text" name="population.workCondition" id="workCondition"  maxlength="50"
		value="${population.workCondition}" class='form-txt' />
</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">帮扶情况：</label>
</div>
<div class="grid_8">
	<select name="population.helpingSituation.id" id="helpingSituation" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@HELPING_SITUATION_TYPE" defaultValue="${population.helpingSituation.id}" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>		
	
<div class="grid_4 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_8">
	<select name="population.attentionExtent.id" id="idleYouthAttentionExtent" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_2 lable-right">
	<input type="checkbox" id="isStayInSchool" name="population.stayInSchool" value="true" class="dialogtip"
		<s:if test='true==population.stayInSchool'>checked="checked"</s:if> />
</div>
<div class="grid_8 lable-left">
	<label class="form-check-text">&nbsp;是否在校住宿</label>
</div>
<div class='clearLine'>&nbsp;</div>

<div id="schoolShowOrHidden" style="display: none;">
	<div  class="grid_4 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">学校名称：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="population.schoolName" id="schoolName"  maxlength="30" class="form-txt " value="${population.schoolName }" />
	</div>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<em class="form-req">*</em>
	<label class="form-lb1">人员类型：</label>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_2 lable-right"></div>
<div class="grid_22 heightAuto">
	<pop:PropertyDictMultiCheckbox name="staffTypeIds" column="2" className="{staffTypeNotNull:true,messages:{staffTypeNotNull:'人员类型必须至少勾选一种类型'}}" domainName="@com.tianque.domain.property.PropertyTypes@IDLEYOUTH_STAFF_TYPE" listVal="${population.staffType}" />
</div>
<div class='clearLine'></div>
<script>

$("#schoolName").schoolAutoComplete({
	postData:{
		schoolName:function(){
			var schoolname = $("#schoolName").val();
			return schoolname;
		}
	},
	select:function(){
		$("#schoolName").val(schoolName);
		$("#schoolName").blur();
		$("#schoolName").focus();
	}
});

$(function(){
jQuery.validator.addMethod("staffTypeNotNull", function(value, element){
	var conditionStaffTypeId = "";
	$("input[name='staffTypeIds']").each(function(i){
		 if ($(this).attr("checked")) {
			 conditionStaffTypeId+=','+$(this).val();
		 }
	});
	if(conditionStaffTypeId==""||conditionStaffTypeId==null||conditionStaffTypeId==undefined){
		$.messageBox({
			message : "人员类型至少勾选一种类型",
			level : "warn"
		});
		return false;

	}else {
		return true;
	}
});

if($("#isStayInSchool").attr("checked") == "checked" || $("#isStayInSchool").attr("checked") == true){
	$("#schoolShowOrHidden").show();
}

$("#isStayInSchool").change(function(){
	if ($(this).attr("checked") == null || typeof($(this).attr("checked")) == 'undefined'){
		$("input[name='population.schoolName']").rules("remove","required");
		$("#schoolShowOrHidden").hide();
	}else{
		$("input[name='population.schoolName']").rules("add",{required:true,maxlength:30,exculdeParticalChar:true,messages:{required:'请输入学校名称',maxlength:$.format('学校名称最多需要输入{0}个字符'),exculdeParticalChar:'不能输入非法字符'}});
		$("#schoolShowOrHidden").show();
	}
});
})
</script>