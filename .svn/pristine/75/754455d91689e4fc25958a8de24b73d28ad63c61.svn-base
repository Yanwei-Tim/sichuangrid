<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
	<span style="font-weight: bold; margin-left:20px; ">需救助人员</span>
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
<em class="form-req">*</em>
	<label class="form-lbl">救助原因：</label>
</div>
<div class="grid_7">
	<select name="population.aidReason.id" id="aidReason" class="form-txt {required:true,messages:{required:'请输入救助原因'}}" >
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@AIDRREASON" defaultValue="${population.aidReason.id}" />
	</select>
</div>
<div class="grid_8 lable-right">
	<input id="isLowHouseholds" type="checkbox"  name="population.isLowHouseholds" value="true" class="dialogtip"
	<s:if test='true==population.isLowHouseholds'>checked="checked"</s:if>
	/>
	<label class="form-check-text">是否低保户 </label>
</div>
<div id="effectiveDateShowOrHidden" style="display:none;">
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">困难证号：</label>
	</div>
	    <div class="grid_7">
		<input type="text" name="population.difficultCardNo" id="difficultCardNo" value="${population.difficultCardNo}" class="form-txt {required:'#isLowHouseholds:checked',maxlength:30,messages:{required:'困难证号不能为空',maxlength:'困难证号最多只能输入{0}个字符'}}" maxlength="30"/>
	</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
		<label class="form-lbl">困难类型：</label>
	</div>
	<div class="grid_7">
	        <select name="population.difficultType.id" id="difficultType" class="form-txt {required:'#isLowHouseholds:checked',messages:{required:'困难类型不能为空 '}}" >
	 		      <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DIFFICULT_TYPE" defaultValue="${population.difficultType.id}" />
	</select>
	      </div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">领取金额：</label>
	</div>
	    <div class="grid_6">
		<input type="text" name="population.subsidiesAmount" id="subsidiesAmount" value="${population.subsidiesAmount}" class="form-txt {decimal:true,range:[0,999999.99],messages:{decimal:'请输入非负数 ，保留两位位小数点',range: '请输入0至999999.99之间的非负数 '}}" maxlength="9"/>
	</div>
	<div class="grid_1">(元)</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">领取时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.subsidiesGetTime" id="subsidiesGetTime" class="form-txt"
		value="<s:date name="population.subsidiesGetTime" format="yyyy-MM-dd"/>" readonly style="background-color: white"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">享受起始时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.subsidiesStartTime" id="subsidiesStartTime" class="form-txt"
		value="<s:date name="population.subsidiesStartTime" format="yyyy-MM-dd"/>" readonly style="background-color: white"/>
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">享受人数：</label>
	</div>
	    <div class="grid_7">
		<input type="text" name="population.subsidiesPopulation" id="subsidiesPopulation" value="${population.subsidiesPopulation}" class="form-txt {digits:true,range:[0,999],messages:{digits:'只能输入非负整数 ',range:'请输入0至999之间的非负整数'}}" maxlength="3"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">享受地区：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="population.subsidiesArea"  id="subsidiesArea" maxlength="50" value="${population.subsidiesArea}" class="form-txt {required:'#isLowHouseholds:checked',maxlength:50,exculdeParticalChar:true,messages:{required:'享受地区不能为空',maxlength:'享受地区最多只能输入{0}个字符',exculdeParticalChar:'不能输入非法字符'}}" maxlength="50"/>
	</div>


</div>
<div class='clearLine'>&nbsp;</div>
<script type="text/javascript">
$(function(){
	$("#isLowHouseholds").change(function(){
		if ($(this).attr("checked") == null){
			$("#effectiveDateShowOrHidden").hide();
			$("#difficultCardNo").val("");
			$("#difficultType").val("");
			$("#subsidiesAmount").val("");
			$("#subsidiesGetTime").val("");
			$("#subsidiesStartTime").val("");
			$("#subsidiesPopulation").val("");
			$("#subsidiesArea").val("");
		}else{
			$("#effectiveDateShowOrHidden").show();
		}
	});

	$('#subsidiesGetTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d'
	});
	$('#subsidiesStartTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d'
	});

	jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		if(isGridForTreeSelect()){
			return true;
		}else{
			return false;
		}
	});

	 jQuery.validator.addMethod("decimal", function(value, element) {
	     var decimal = /^-?\d+(\.\d{1,2})?$/;
	     if (value==""){
		     return true;
		 }
	     if(value>=0){
	     	return this.optional(element) || (decimal.test(value));
	     }
	     return false;
    });
})
$(document).ready(function(){
	if($("#isLowHouseholds").attr("checked")=="checked"){
		$("#effectiveDateShowOrHidden").show();
	}

});

</script>

