<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input type="hidden" readonly="readonly" name="population.birthday" value="<s:date name="population.birthday" format="yyyy-MM-dd"/>"  id="birthday" class="form-txt" />
	<span style="font-weight: bold; margin-left:20px; ">老年人</span>
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
	<label class="form-lb1">老年人证号：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.elderPeopleId" id="elderPeopleId" maxlength="18" value="${population.elderPeopleId}"  class="form-txt"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
	<label class="form-lb1">人员类型：</label>
</div>
<div class="grid_7">
	<select name="population.peopleType.id" id="peopleType" class="form-txt"  >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPECIAL_PERSON" defaultValue="${population.peopleType.id}" />
	</select>
</div>
 		<div  class="grid_5 lable-right">
 			<label class="form-lb1">当前状况：</label>
		</div>
<div class="grid_7">
    <select name="population.currentStatus.id" id="currentStatus" class="form-txt"  >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_STATUS" defaultValue="${population.currentStatus.id}" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">居住情况：</label>
		</div>
		<div class="grid_7">
		<select name="population.liveStatus.id" id="liveStatus"  class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LIVE_STATUS" defaultValue="${population.liveStatus.id}" />
	</select>
</div>
<div class="grid_5 lable-right">
	<label class="form-lb1">配偶情况：</label>
</div>
<div class="grid_7">
	<select name="population.spouseStatus.id" id="spouseStatus"  class="form-txt">
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPOUSE_STATUS" defaultValue="${population.spouseStatus.id}" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
	<label class="form-lb1">经济来源：</label>
</div>
<div class="grid_7">
	<select name="population.incomeSource.id" id="incomeSource"
		class="form-txt">
		<pop:OptionTag
			name="@com.tianque.domain.property.PropertyTypes@INCOME_SOURCE"
			defaultValue="${population.incomeSource.id}" />
	</select>
</div>

 		<div class="grid_5 lable-right">
 			<label class="form-lb1">参加工作日期：</label>
 		</div>
 		<div class="grid_7">
 			<input type="text" name="population.enterWorkDate" id="enterWorkDate" class="form-txt {compareBirthday:true,messages:{compareBirthday:'参加工作日期不能小于 出生日期'}}" readonly="readonly"   value='<s:date name="population.enterWorkDate" format="yyyy-MM-dd" />'
 			style="background-color: white"/>
 		</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_5 lable-right">
 			<label class="form-lb1">离退休单位：</label>
 		</div>
 		<div class="grid_7">
 			<input type="text" name="population.retireUnitAndDuty" id="retireUnitAndDuty"   value='${population.retireUnitAndDuty}' maxlength="30"
 			class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"单位类型不能输入非法字符"}}'
 			/>
</div>

<div class="grid_5 lable-right">
	<label class="form-lb1">离退休日期：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.retireDate" id="retireDate" class="form-txt {retireDate:true,messages:{retireDate:'离退休日期不能小于参加工作日期'}}" readonly value='<s:date name="population.retireDate" format="yyyy-MM-dd" />'
	style="background-color: white"/>
 		</div>
<div class="grid_5 lable-right">
 			<label class="form-lb1">原单位职务：</label>
 		</div>
 		<div class="grid_7">
 			<input type="text" name="population.zhiwu" id="zhiwu"  value='${population.zhiwu}' maxlength="30"
 			class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"职位不能输入非法字符"}}'
 			/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">退休金：</label>
		</div>
		<div class="grid_6">
			<input id="population.pension" type="text" name="population.pension" class="form-txt {number:true,min:0,max:999999999,messages:{number: '退休金需要输入正数',	min: '退休金需要输入正数',max: '退休金最大输入999999999'}}" style="text-align:right;" maxlength="9"
 title="退休金"
value='${population.pensionStringValue}' />
</div>
<div class="grid_1">(元)</div>


<div class='clearLine'>&nbsp;</div>
<script>
$(function(){
	jQuery.validator.addMethod("enterWorkDate", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var retireDate = $("#retireDate").val();
		if(retireDate==null||retireDate==undefined||retireDate==""){return true}
		if(value>retireDate){
			return false;
		}else{
			return true;
		}
	});

	jQuery.validator.addMethod("compareBirthday", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var birthday = $("#birthday").val();
		if(value<birthday){
			return false;
		}else{
			return true;
		}
	});

	jQuery.validator.addMethod("retireDate", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var registerDate = $("#enterWorkDate").val();
		if(registerDate==null||registerDate==undefined||registerDate==""){return true}
		if(value<registerDate){
			return false;
		}else{
			return true;
		}
	});
	$('#enterWorkDate').datePicker({
		yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
	       maxDate:'+0d'});
	$('#retireDate').datePicker({
		yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
	       maxDate:'+0d'});
})
</script>