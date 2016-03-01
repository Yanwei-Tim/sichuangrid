<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style>
	.propertyMulSelect {overflow:hidden;}
	.propertyMulSelect li{float:left;width:49%;}
</style>
	<span style="font-weight: bold; margin-left: 20px;">失业人员</span>
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
	<label class="form-lbl">人员类型： </label>
</div>
<div class="grid_7">
	<select name="population.unemployedPeopleType.id" class="form-txt"
		id="unemployedPeopleType">
		<pop:OptionTag
			name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYEDPEOPLETYPE"
			defaultValue="${population.unemployedPeopleType.id}" />
	</select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">登记证号：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.registerNumber" id="registerNumber"
		maxlength="30" value="${population.registerNumber}" class="form-txt" />
</div>

<div class="grid_5 lable-right">
	<em class="form-req">*&nbsp;</em><label class="form-lbl">失业时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.unemploymentDate"
		id="unemploymentDate"
		value="<s:date name="population.unemploymentDate" format="yyyy-MM-dd"/>"
		readonly  class="form-txt {required:true,checkUnemploymentDate:true,messages:{required:'请输入失业时间',checkUnemploymentDate:'失业时间不能小于出生日期'}}" style="background-color: white" />
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">失业原因： </label>
</div>
<div class="grid_19">
	<select name="population.unemploymentReason.id" class="form-select"
		id="unemploymentReason">
		<pop:OptionTag
			name="@com.tianque.domain.property.PropertyTypes@UNEMPLOYMENTREASON"
			defaultValue="${population.unemploymentReason.id}" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">参加工作时间：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.upEnterWorkDate" id="upEnterWorkDate"
		class="form-txt "
		value="<s:date name="population.upEnterWorkDate" format="yyyy-MM-dd"/>" style="background-color: white"
		readonly />
</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">原单位名称： </label>
</div>
<div class="grid_7">
	<input type="text" name="population.originalWorkUnit"	id="originalWorkUnit"
		value="${population.originalWorkUnit }" maxlength="30"
	class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"单位名称不能输入非法字符"}}'
	>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">原单位类型：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.originalWorkUnitType"
		id="originalWorkUnitType"
		value="${population.originalWorkUnitType }" maxlength="20"
		class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"单位类型不能输入非法字符"}}'
		>
</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">职称：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.title" id="title"
		value="${population.title}" maxlength="10"
class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"职称不能输入非法字符"}}'
		/>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">技术等级： </label>
</div>
<div class="grid_7">
	<select name="population.technologyLevel.id" class="form-select"
		id="technologyLevel">
		<pop:OptionTag
			name="@com.tianque.domain.property.PropertyTypes@TECHNOLOGYLEVEL"
			defaultValue="${population.technologyLevel.id}" />
	</select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lbl">技能特长：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.specialtySkills"
		id="specialtySkills" value="${population.specialtySkills}" maxlength="50"
	class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"不能输入非法字符"}}' />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">拟就业意向： </label>
</div>
<div class="heightAuto">
	<pop:PropertyDictMultiCheckbox name="employmentIntentionIds" column="3"
		domainName="@com.tianque.domain.property.PropertyTypes@EMPLOYMENTINTENTION"
		listVal="${population.employmentIntention}" />
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_5 lable-right">
	<label class="form-lbl">拟参加培训意向： </label>
</div>
<div class="grid_19" style="height: 60px">
	<pop:PropertyDictMultiCheckbox name="trainingIntentionIds" column="3"
		domainName="@com.tianque.domain.property.PropertyTypes@TRAININGINTENTION"
		listVal="${population.trainingIntention}" />
</div>
<div class="grid_5 lable-right" >
	参加过的培训项目及时间：
</div>
<div class="grid_19 heightAuto">
	<textarea rows="4" name="population.participatedPrograms"
		id="participatedPrograms" class="form-txt{maxlength:200,exculdeParticalChar:true,messages:{maxlength:'最多只能输入200字符',exculdeParticalChar:'不能输入非法字符'}}" style="width: 98%">${population.participatedPrograms }</textarea>
</div>
<div class='clearLine'>&nbsp;</div>
<script>
	$('#unemploymentDate').datePicker({
		yearRange : '1900:2060',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0y',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0],
						dateUnit[1] - 1, dateUnit[2]);
				$("#upEnterWorkDate")
						.datepicker("option", "maxDate",
								date);
			}
		}
	});
	$('#upEnterWorkDate').datePicker({
		yearRange : '1900:2060',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0y',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0],
						dateUnit[1] - 1, dateUnit[2]);
				$("#unemploymentDate")
						.datepicker("option", "minDate",
								date);
			}
		}
	});
	
	//失业时间不能大于出身时间
	jQuery.validator.addMethod("checkUnemploymentDate", function(value, element){
		if(value==null||value==undefined||value=="" ){return true;}
		var birthday='<fmt:formatDate value="${population.birthday}" type="date" pattern="yyyy-MM-dd" />';
		if (birthday>value) {return false;}
		return true;
	});
</script>