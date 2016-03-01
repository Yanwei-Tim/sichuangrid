<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

	<span style="font-weight: bold; margin-left:20px; ">吸毒人员</span>
	<div class='clearLine'>&nbsp;</div>
<div class="grid_6 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_6">
	<select name="population.attentionExtent.id" id="druggy-attentionExtent" class="form-txt" >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_6 lable-right">
	<label class="form-lbl">吸毒状态：</label>
</div>
<div class="grid_6">
    <select id="population.detoxicateCondition.id" name="population.detoxicateCondition.id"  class="form-txt">
    	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CONDITION"  defaultValue="${population.detoxicateCondition.id }"  />
    </select>
</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">首吸时间：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.drugFristDate" id="druggy-drugFristDate" class="form-txt {isDrugFristDateBirthday:true,messages:{isDrugFristDateBirthday:'首吸时间不能小于出生日期'}}"
	value="<s:date name="population.drugFristDate" format="yyyy-MM-dd"/>" readonly
	style="background-color: white"/>
</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">查获日期：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.ferretOutDate" id="druggy-ferretOutDate" class="form-txt {isDrugFristDateFerretOutDate:true,messages:{isDrugFristDateFerretOutDate:'查获日期不能小于首吸时间'}}"
	value="<s:date name="population.ferretOutDate" format="yyyy-MM-dd"/>" readonly
	style="background-color: white"/>
</div>
<div class='clearLine'>&nbsp;</div>
<input type="hidden" readonly="readonly" name="population.birthday" value="<s:date name="population.birthday" format="yyyy-MM-dd"/>"  id="druggy-birthday" class="form-txt" />
<div class="grid_6 lable-right">
	<label class="form-lbl">管控现状：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.controlActuality" id="controlActuality" class="form-txt dialogtip {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" value="${population.controlActuality}" maxlength="50"
	/>
</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">吸毒原因： </label>
</div>
<div class="grid_6">
	<select name="population.drugReason.id" class="form-txt"
    id="druggy-drugReason">
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DRUG_REASON" defaultValue="${population.drugReason.id}" />
	</select>
</div>
<div class='clearLine'>&nbsp;</div>

<%-- 
<div class="grid_6 lable-right">
	<label class="form-lbl">毒品来源：</label>
</div>
<div class="grid_6">
	<select name="population.drugSource.id" class="form-txt"
      id="druggy-drugSource">
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DRUG_SOURCE" defaultValue="${population.drugSource.id}" />
	</select>
</div>
 --%>

<div class="grid_6 lable-right">
    <em class="form-req">*</em>
	<label class="form-lbl">戒毒情况：</label>
</div>
<div class="grid_6">
	<select name="population.detoxicateCase.id" class="form-txt {required:true,messages:{required:'请输入戒毒情况'}}" id="druggy-detoxicateCase"
      >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DETOXICATE_CASE" defaultValue="${population.detoxicateCase.id}" />
	</select>
</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">滥用毒品种类：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.drugType" id="druggy-drugType" class="form-txt  dialogtip {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" value="${population.drugType}"
	title="指吸毒人员所接触的毒品名称,毒品是指鸦片、海洛因、甲基苯丙胺（冰毒）、吗啡、大麻、可卡因以及国家规定管制的其他能够使人形成瘾癖的麻醉药品和精神药品。" maxlength="50"
	/>
</div>
<div class="grid_6 lable-right">
</div>
<div class="grid_6">
	<input id="druggy-isAdanon" type="checkbox" id="druggy-adanon" name="population.adanon" value="true" class="dialogtip" title="<b>美沙酮：</b>又称美散痛，<br>属人工合成的麻醉药品。<br>其盐酸盐为无色或白色<br>结晶状，无嗅、味苦，<br>常见剂型为胶囊，在临<br>床上用作镇痛麻醉剂，<br>止痛效果略强于吗啡，毒<br>性、副作用较小，成瘾<br>性也比吗啡小。"
	<s:if test="population.adanon" >checked="checked"</s:if>
	/>
	<label class="form-check-text">是否服美沙酮戒毒 </label>
</div>
<div class="grid_6 lable-right">
</div>
<div class="grid_6">
<input id="isUndergoTreatment2" type="checkbox"  name="population.undergoTreatment" value="true" class="dialogtip"
	<s:if test='true==population.undergoTreatment'>checked="checked"</s:if>
	/>
	<label class="form-check-text">&nbsp;目前是否在接受康复治疗</label>
</div>
<div class='clearLine'>&nbsp;</div>

<script>
function resetBirthdayField(text){
	if (text!="" && $('#druggy-idCardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' name='druggy.birthday' id='druggy-birthday' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#druggy-birthday').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
        	maxDate:'+0d'
        });
	}
}
$(function(){
	jQuery.validator.addMethod("isDrugFristDateBirthday", function(value, element){
		var value = $('#druggy-drugFristDate').val();
		if(value==null||value==undefined||value==""){return true;}
		if(value < $('#druggy-birthday').val()){
           return false;
		}else {
			return true;
		}

	});

	jQuery.validator.addMethod("isDrugFristDateFerretOutDate", function(value, element){
		var value = $('#druggy-drugFristDate').val();
		var ferretOutDate = $('#druggy-ferretOutDate').val();
		if(value==null||value==undefined||value=="" || ferretOutDate==null||ferretOutDate==undefined||ferretOutDate==""){return true;}
		if(value >ferretOutDate){
           return false;
		}else {
			return true;
		}
	});

	$(".dialogtip").inputtip();
	$('#druggy-idCardNo').blur(idCardChanged);

	$('#druggy-ferretOutDate').datePicker({
		yearRange: '1900:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'+0y'
	});
	$('#druggy-drugFristDate').datePicker({
		yearRange: '1900:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'+0y'
	});
	resetBirthdayField($("#druggy-birthday").val());
})
</script>