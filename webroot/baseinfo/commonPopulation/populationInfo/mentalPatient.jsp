<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

	<span style="font-weight: bold; margin-left:20px; ">严重精神障碍患者</span>
	<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right">
	<em class="form-req">*</em>
	<label class="form-lbl">危险程度：</label>
</div>
<div class="grid_8">
	<select name="population.dangerLevel.id" id="dangerLevel" class="form-txt {required:true,messages:{required:'危险程度必须输入'}}">
	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MENTALPATIENT_DANGEROUS_LEVEL" defaultValue="${population.dangerLevel.id}"/>
	</select>
</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">患病名称：</label>
</div>
<div class="grid_8">
    <input type="text" name="population.psychosisName" id="psychosisName"  maxlength="50"
		value="${population.psychosisName}"
		class='form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:"不能输入非法字符"}}' />
</div>
<div class='clearLine'></div>
<div class="grid_4 lable-right">
	<label class="form-lbl">精神病类型：</label>
</div>
<div class="grid_8">
	<select name="population.psychosisType.id" id="psychosisType" class="form-txt">
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PSYCHOSIS_TYPES" defaultValue="${population.psychosisType.id}"/>
	</select>
</div>
<div class='clearLine'></div>
<div class="grid_2 lable-right">
<input id="isTreat" type="checkbox"  name="population.treat" value="true" class="dialogtip"
	<s:if test='true==population.treat'>checked="checked"</s:if>
	/>
</div>
<div class="grid_10 lable-left">
	<label class="form-check-text">&nbsp;是否接受过治疗</label>
</div>
<div class='clearLine'></div>
<div id="effectiveShowOrHidden">
<div  class="grid_4 lable-right">
	<label class="form-lbl">康复机构：</label>
</div>
<div class="grid_8">
	<input type="text" name="population.cureDepartment" id="cureDepartment"  maxlength="50"
	value="${population.cureDepartment }"
	class="form-txt {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" />
</div>
<div class='clearLine'></div>
</div>
<div class="grid_2 lable-right">
<input id="isUndergoTreatment" type="checkbox"  name="population.undergoTreatment" value="true" class="dialogtip"
	<s:if test='true==population.undergoTreatment'>checked="checked"</s:if>
	/>
</div>
<div class="grid_10 lable-left">
	<label class="form-check-text">&nbsp;目前是否在接受治疗</label>
</div>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold; margin-left:20px; ">历史发病情况</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">发病时间：</label>
</div>
<div class="grid_8">
	<input type="text" readonly="readonly" name="population.diseaseTime" id="diseaseTime" value='<s:date name="population.diseaseTime" format="yyyy-MM-dd" />'
	class="form-txt">
</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">治疗状态：</label>
</div>
<div class="grid_8">
	<select name="population.treatmentState.id" id="treatmentState" class="form-txt">
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TREATMENTSTATE" defaultValue="${population.treatmentState.id}"/>
	</select>
</div>
<div id="hideRecoveryTime">
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">康复时间：</label>
	</div>
	<div class="grid_8">
		<input type="text" readonly="readonly" name="population.recoveryTime" id="recoveryTime" value='<s:date name="population.recoveryTime" format="yyyy-MM-dd" />'
		class="form-txt">
	</div>
</div>

<script>
$(function(){
	var selectText=$("#treatmentState").find("option:selected").text();
	if(selectText!='已康复'){
		$('#hideRecoveryTime').hide();
	}
	if(null==$("#isTreat").attr("checked")){
		$("#effectiveShowOrHidden").hide();
	};
	$("#isTreat").change(function(){
		if ($(this).attr("checked") == null){
			$("#effectiveShowOrHidden").hide();
			$("#cureDepartment").val("");
		}else{
			$("#effectiveShowOrHidden").show();
		}
	});
	
	$("#treatmentState").change(function(){
		if($("#treatmentState").find("option:selected").text()=='已康复'){
			$('#hideRecoveryTime').show();
		}else{
			$('#hideRecoveryTime').hide();
			$("#recoveryTime").val("");
		}
	});   
	
	var startDay=$("#diseaseTime").val();
	var endDay=$("#recoveryTime").val();
	$("#diseaseTime").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
		maxDate: '+0d',
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#recoveryTime").datepicker("option","minDate",dateText);
				$("#recoveryTime").datepicker("option","maxDate",'Now');
			}
		}
    }); 
	$("#recoveryTime").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
		<s:if test="startDay!=null&&startDay!=''">
		minDate:startDay
		</s:if>
		<s:else>
		minDate:'+0d'
		</s:else>
	});
	
});


</script>