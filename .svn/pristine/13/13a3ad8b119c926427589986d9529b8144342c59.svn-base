<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>


	<span style="font-weight: bold; margin-left:20px; ">社区矫正人员</span>
	<div class='clearLine'>&nbsp;</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_6">
	<select name="population.attentionExtent.id" id="rectificativePersonAttentionExtent" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>

<div class="grid_6 lable-right">
<em class="form-req">*</em>
	<label class="form-lbl">罪名：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.accusation" maxlength="50"
	class="form-txt  dialogtip {required: true,minlength:2,maxlength:50,messages:{required: '请输入罪名',minlength:'最少需要｛2｝个字符',maxlength:'最多需要{0}个字符'}}"
		value="${population.accusation}" id="penaltyTerm"/>
</div>
<div class='clearLine'>&nbsp;</div>

<div class="grid_6 lable-right" >
<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
	<label class="form-lbl">刑法执行类别：</label>
</div>
<div class="grid_6">
	<select name="population.executeType.id" id="rectificativePersonExecuteType" class="form-select {required:true,messages:{required:'请输入刑法执行类别'}}"
	>
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EXECUTE_TYPE" defaultValue="${population.executeType.id}"/>
	</select>
</div>
<div class="grid_6 lable-right">
	<label class="form-lbl">原判刑期：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.penaltyTerm" maxlength="20"
	class="form-txt  dialogtip {minlength:2,maxlength:20,messages:{minlength:'最少需要｛2｝个字符',maxlength:'最多需要{0}个字符'}}"
		value="${population.penaltyTerm}" id="penaltyTerm"/>
</div>
<!-- title="该规正人员当初被判刑的时候的刑期，是没有被减刑或者加刑的刑期" -->
<div class='clearLine'>&nbsp;</div>

<div class="grid_6 lable-right">
<em class="form-req">*</em>
	<label class="form-lbl">社区矫正日期：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.rectifyStartDate"
		id="antidoteRectifyStartDate"  readonly class="form-txt	{required: true,antidoteRectifyStartDate :true,	messages:{required: '请输入矫正开始时间', antidoteRectifyStartDate :'矫正开始时间不能小于出生日期 或者大于矫正结束日期 !'}}"
		value='<s:date name="population.rectifyStartDate" format="yyyy-MM-dd"/>'
		 style="background-color: white"/>
</div>
<div class="grid_6 lable-right" >
   <em class="form-req">*</em>
	<label class="form-lbl">至：</label>
</div>
<div class="grid_6">
	<input type="text" name="population.rectifyEndDate" id="antidoteRectifyEndDate"
	 readonly value='<s:date name="population.rectifyEndDate" format="yyyy-MM-dd"/>'
	class="form-txt {required: true,antidoteRectifyEndDate :true,antidoteRectifyEndDateWithNow :true,messages:{required:'请输入矫正结束时间',antidoteRectifyEndDate :'矫正结束日期不能小于矫正开始日期!',antidoteRectifyEndDateWithNow:'矫正结束日期不能小于当前日期!'}}"
	style="background-color: white"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_6 lable-right">
	<label class="form-lbl">近况描述：</label>
</div>
<div class="grid_18">
	<input type="text" name="population.recentSituation" maxlength="100"
	class='form-txt'
	value="${population.recentSituation}" id="recentSituation" />
</div>
<div class='clearLine'>&nbsp;</div>
<script>
$(function(){

	$('#antidoteRectifyStartDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#antidoteRectifyEndDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
		 minDate:'+1d'});
	jQuery.validator.addMethod("antidoteRectifyStartDate", function(value, element){
		if($('#antidoteRectifyStartDate').val() < $('#birthday').val()){
           return false;
		}else return true;
	});

	jQuery.validator.addMethod("antidoteRectifyEndDate",function(value,element){
        if($('#antidoteRectifyStartDate').val() > $('#antidoteRectifyEndDate').val()){
            return false;
           }else return true;
	});
	
	jQuery.validator.addMethod("antidoteRectifyEndDateWithNow", function(value, element){
		var date=new Date();
		var month=date.getMonth()+1+"";
		if(month.length==1){
			month="0"+month;
		}
		var day=date.getDate()+"";
		if(day.length==1){
			day="0"+day;
		}
		var now=date.getFullYear() + "-"+month + "-"+day + " "
		var end=$('#antidoteRectifyEndDate').val();
        if(now >end){
            return false;
           }else return true;
	});
	
	jQuery.validator.addMethod("exculdeDouhao", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var patrn=/^(?:[A-Za-z0-9\u4E00-\u9FA5]*[\(|\（\,\，]*[\,，)|\）]*-*)+$/;
		if (!patrn.exec(value.replace(/[ ]/g,""))) return false
		return true
	});
})
</script>