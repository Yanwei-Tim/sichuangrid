<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<style type="text/css">
	.disabilityTypesDiv{word-break: break-all;}
</style>
<input type="hidden" readonly="readonly" name="population.birthday" value="<s:date name="population.birthday" format="yyyy-MM-dd"/>"  id="birthday" class="form-txt" />
	<span style="font-weight: bold; margin-left:20px; ">残疾人</span>
	<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_7">
	<select name="population.attentionExtent.id" id="attentionExtent" class="form-txt" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">残疾原因：</label>
</div>
<div class="grid_7">
		<input type="text" name="population.disabilityReason" maxlength="50" value="${population.disabilityReason}" class="form-txt {exculdeParticalChar:true,maxlength:50,messages:{exculdeParticalChar:'不能输入非法字符',maxlength:'残疾原因最多需要输入50个字符'}}" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right"><label class="form-lbl">残疾时间：</label></div>
<div class="grid_7">
	<input type="text" readonly value="<s:date name='population.disabilityDate' format='yyyy-MM-dd' />" name="population.disabilityDate" id="handicapped_disabilityDate" class="form-txt" style="background-color: white"/>

</div>

<div class="grid_5 lable-right">
    <em class="form-req">*</em>
	<label class="form-lbl">残疾证号：</label>
</div>
<div class="grid_7">
		<input  id="disabilityCardNo" type="text" name="population.disabilityCardNo" value="${population.disabilityCardNo}" maxlength="30" class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'请输入残疾证号',exculdeParticalChar:'不能输入非法字符'}}" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">残疾类别：</label>
</div>
<s:iterator value="handicapped.disabilityTypes" var="disablilityTypes">
	<div class="grid_3 disabilityTypesDiv multipeSelect">
	<input id="disablilityTypes${disablilityTypes.id}" name="population.disabilityTypeIds"  onclick="changeValue('${disablilityTypes.id}')" value="${disablilityTypes.id}" type="checkbox" class="category">&nbsp;<label class="form-check-text" for="disablilityTypes${disablilityTypes.id}">${disablilityTypes.displayName}</label>
	<input type="hidden" name="population.disabilityLevelIds" id="disablilityTypes${disablilityTypes.id}_level" value="<s:property value="disablilityLevelMap[#disablilityTypes.id]"/>"/>
	<ul class="zc-sf" >
		<li class="top"><p>残疾等级</p><span class="close">close</span></li>
		<s:iterator value="handicapped.disabilitys" var="disabilitys"><s:if test="#disabilitys.displayName.indexOf(#disablilityTypes.displayName) != -1">
		<li><label><input name="disablilityTypes${disablilityTypes.id}" onclick="putLevelValue('${disablilityTypes.id}')" type="radio" <s:if test="disablilityLevelMap[#disablilityTypes.id] == #disabilitys.id">checked="checked"</s:if> value="${disabilitys.id}" />${disabilitys.displayName}</label></li>
		</s:if></s:iterator>
	</ul>
	</div>
</s:iterator>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right" style="display: none;">
	<label class="form-lbl">是否有残疾证 ：</label>
</div>
<div class="grid_7" style="display: none;">
<%--  省平台那边 没有 选择的   并且那个 残疾证号为必填写   --%>
<%-- <input id='hadDisabilityCard' type="radio" name="population.hadDisabilityCard" <s:if test="population.hadDisabilityCard" >checked="checked"</s:if>
	 value="true"/>是
	<input  type="radio" name="population.hadDisabilityCard"  <s:if test="!population.hadDisabilityCard" >checked="checked"</s:if>
	 value="false"/>否
--%> <input id='hadDisabilityCard' type="radio" name="population.hadDisabilityCard" checked="checked" value="true"/>是
	<input  type="radio" name="population.hadDisabilityCard" value="false"/>否
</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">技能特长：</label>
</div>
<div class="grid_7">
	<select name="population.skillProfile.id" id="skillProfile" class="form-txt"
			    	<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SKILLS_AND_SPECIALITIES" defaultValue="${population.skillProfile.id}" />
				</select>
</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">劳动能力：</label>
</div>
<div class="grid_7">
	<select name="population.workProfile.id" id="workProfile" class="form-txt"
			    	<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" defaultValue="${population.workProfile.id}" />
				</select>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">监护人： </label>
</div>
<div class="grid_7">
	<input type="text" name="population.guardianName" value="${population.guardianName}" maxlength="20" class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"不能输入特殊字符"}}'/>
</div>
<div class='clearLine'>&nbsp;</div>
<script>
function putLevelValue(p1){
	$("#disablilityTypes"+p1+"_level").val($("input[type='radio'][name='disablilityTypes"+p1+"']:checked").val());
}
function changeValue(value){
	if($("input[type='radio'][name='disablilityTypes"+value+"']:checked").length==0){
		$("#disablilityTypes"+value).removeAttr("checked");
		return;
	}
	if($("#disablilityTypes"+value).attr("checked") == "undefined"||$("#disablilityTypes"+value).attr("checked")==null){
		$("#disablilityTypes"+value).removeAttr("checked");
		$("input[type='radio'][name='disablilityTypes"+value+"']:checked").attr("checked",false);
		$("#disablilityTypes"+value+"_level").val("");
	}
}
$(function(){
	$("input[name='population.disabilityTypeIds']:eq(6)").parents('div.disabilityTypesDiv').remove();
	<s:iterator value="handicapped.disabilityTypes" var="disablilityTypes">
	$("#disablilityTypes${disablilityTypes.id}").typeSelect({width:110,columns:1});
	</s:iterator>
	$("input[type='radio'][name^='disablilityTypes']").each(function (){
		if($(this).attr('checked')){
			$("#"+$(this).attr('name')).attr("checked", true);
		}
	});
	//给残疾时间是输入框加上时间控件
	$('#handicapped_disabilityDate').datePicker({
		yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
	       maxDate:'+0d'});

    //添加一个新的验证方法，验证残疾时间和出生时间比较
    jQuery.validator.addMethod("compareBirthday", function(value, element){
	if(value==null||value==undefined||value==""){return true}
	var birthday = $("#birthday").val();
	if(value<birthday){
		return false;
	}else{
		return true;
	}
});

    if($("input[name='population.hadDisabilityCard']:checked").val()=='true'){
    	$("#disabilityCardNo").attr("disabled",false);
        }else{
        	$("#disabilityCardNo").attr("disabled",true);
            }

})
</script>