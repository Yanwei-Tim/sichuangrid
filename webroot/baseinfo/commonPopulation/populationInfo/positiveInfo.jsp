<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
	<div style="font-weight: bold; margin-left:20px;line-height:30px;">刑释人员</div>
<div class="grid_6 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_6">
	<select name="population.attentionExtent.id" id="positiveInfo_attentionExtent_id" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>

<div class="grid_7 lable-right">
	<s:if test='!"view".equals(mode)'>
	<em class="form-req">*</em>
	</s:if>
	<label class="form-lbl">原罪名（错）：</label>
</div>
<div class="grid_5"><input id="positiveInfo_caseReason" style="width:99%"
	type="text" name="population.caseReason" class="form-txt  dialogtip {required:true,minlength:2,maxlength:20,exculdeParticalChar:true,messages:{required:'请输入原罪名/原罪错',minlength:'原罪名/原罪错至少需要输入{0}个字符',maxlength:'原罪名/原罪错最多输入{0}个字符',exculdeParticalChar:'不能输入非法字符'}}" maxlength="50"
	<s:if test='"view".equals(mode)'>disabled='true'</s:if> value='${population.caseReason}' />
</div>

<div class="grid_6 lable-right">
	<s:if test='!"view".equals(mode)'>
	<em class="form-req">*</em>
	</s:if>
	<label class="form-lbl">人员类型：</label>
</div>
<div class="grid_6">
	<select name="population.positiveInfoType.id" id="positiveInfo_positiveInfoType_id" class="form-txt {required:true,messages:{required:'人员类型不能为空'}}"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POSITIVEINFO" defaultValue="${population.positiveInfoType.id}" />
	</select>
</div>

<div class="grid_7 lable-right">
	<s:if test='!"view".equals(mode)'>
	<em class="form-req">*</em>
	</s:if><label class="form-lbl">劳教(服刑)场所：</label>
</div>
<div class="grid_5">
	<input id="positiveInfo_laborEduAddress"
	style="width: 99%" type="text" name="population.laborEduAddress"
	class="form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:'请输入劳教(服刑)场所',exculdeParticalChar:'不能输入非法字符',minlength:'劳教(服刑)场所至少需要输入{0}个字符',maxlength:'劳教(服刑)场所最多输入{0}个字符'}}"
	<s:if test='"view".equals(mode)'>disabled='true'</s:if> width="1000"
	value='${population.laborEduAddress}' maxlength="50" />
</div>

<div class="grid_6 lable-right">
	<s:if test='!"view".equals(mode)'>
	<em class="form-req">*</em>
	</s:if>
	<label class="form-lbl">原判刑期：</label>
</div>
<div class="grid_6">
	<input id="positiveInfo_imprisonmentDate"
	type="text" name="population.imprisonmentDate" class="form-txt {required:true,exculdeDouhao:true,minlength:2,maxlength:20,messages:{required:'请输入原判刑期',exculdeDouhao:'不能输入非法字符',minlength:'刑期至少需要输入{0}个字符',maxlength:'刑期最多输入{0}个字符'}}"
	maxlength="16"
	value='${population.imprisonmentDate}' />
</div>

<div class="grid_7 lable-right">
	<em class="form-req">*</em>
	<label class="form-lbl">解教（刑释）日期：</label>
</div>
<div class="grid_5" id="birthday">
	<input type="text" style="width :99%"
	name="population.releaseOrBackDate"
	id="positiveInfo_releaseOrBackDate"
	value='<s:date name="population.releaseOrBackDate" format="yyyy-MM-dd"/>'
	class="form-txt {required:true,messages:{required:'解教（刑释）日期不为空'}}" readonly="readonly"
	style="background-color: white" />
</div>
<div class="grid_6"></div>
	<div class="grid_9">
		<div style="padding-left:20px;">
			<input id="positiveInfo_isRepeat"
			type="checkBox" name="population.isRepeat" value='true'
			<s:if test='population.isRepeat == 1' >checked="checked"</s:if>
			<s:if test='"view".equals(mode)'>disabled='true'</s:if>
			class="dialogtip"
			title="累犯：是指受过一定的刑罚处罚,刑罚执行完毕或者赦免以后,在法定期限内又犯被判处一定的刑罚之罪的罪犯。" />
			<label class="form-check-text">是否累犯 </label>
		</div>
	</div>
	<div class="grid_9">
		<input id="positiveInfosIsCrime"
		type="checkBox" name="population.isCrime" value='true'
		<s:if test='population.isCrime  == 1' >checked="checked"</s:if>
		<s:if test='"view".equals(mode)'>disabled='true'</s:if>
		class="dialogtip" />
		<label class="form-check-text">本年度是否重犯</label>
	</div>

<div class="grid_6 lable-right">
	<label class="form-lbl ">犯罪行为：</label>
</div>
<div class="grid_18" style="height: 50px"><textarea class="form-txt {exculdeParticalChar:true,maxlength:300,messages:{exculdeParticalChar:'不能输入非法字符',maxlength:'最多允许输入300个字符'}}"
	name="population.criminalBehavior" id="positiveInfo_criminalBehavior" style="width: 99%; height: 43px;"
	<s:if test='"view".equals(mode)'>disabled='true'</s:if>>${population.criminalBehavior}</textarea>
</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">原职业：</label>
</div>
<div class="grid_6">
	<select name="population.agoProfession.id" id="agoProfession" class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>>
  		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER" defaultValue="${population.agoProfession.id}" />
	</select>
</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">安置日期：</label>
</div>
<div class="grid_6" id="resettlementDate">
	<input type="text"
	name="population.resettlementDate" id="positiveInfo_resettlementDate"
	class="form-txt  dialogtip {isResettlement:true,messages:{isResettlement:'安置日期不能小于解教（刑释）日期'}}"
	value='<s:date name="population.resettlementDate" format="yyyy-MM-dd"/>'
	readonly="readonly"
	style="background-color: white" />
</div>

<div id="positiveInfosCrimeDate-div">
	<div class="grid_6 lable-right">
		<s:if test='!"view".equals(mode)'>
		<em class="form-req">*</em>
		</s:if>
		<label class="form-lbl">重犯日期：</label>
	</div>
	<div class="grid_6">
		<input type="text"
		name="population.crimeDate" id="positiveInfosCrimeDate"
		value='<s:date name="population.crimeDate" format="yyyy-MM-dd"/>'
		class="form-txt {isCrime:true,isBack:true,messages:{isCrime:'本年度重新犯罪日期必须输入',isBack:'本年度重新犯罪日期不能小于解教（刑释）日期'}}"
		style="background-color: white"
		readonly="readonly" />
	</div>
	<div class='clearLine'>&nbsp;</div>
</div>

<div class="grid_6 lable-right">
	<label class="form-lbl">未安置原因：</label>
</div>
<div class="grid_18" heightAuto>
	<textarea id="positiveInfo_noResettlementReason" style="width: 99%"
		name="population.noResettlementReason" rows="2" class="form-txt {exculdeParticalChar:true,maxlength:50,messages:{exculdeParticalChar:'不能输入非法字符',maxlength:'最多允许输入50个字符'}}" <s:if test='"view".equals(mode)'
	>disabled='true'</s:if>>${population.noResettlementReason}</textarea>
</div>
<div  class="grid_24">&nbsp;</div>
<script type="text/javascript">
jQuery.validator.addMethod("isCrime", function(value, element){
	if($("#positiveInfosIsCrime").attr("checked")!=null){
		if($("#positiveInfosCrimeDate").val()=="" || $("#positiveInfosCrimeDate").val()==null){
			return false;
		}
	}
	return true;
});
jQuery.validator.addMethod("isBack", function(value, element){
	if($('#positiveInfosCrimeDate').val()!=null&&$('#positiveInfosCrimeDate').val()!=""){
		if($('#positiveInfo_releaseOrBackDate').val() > $('#positiveInfosCrimeDate').val()){
           return false;
		}else {
			return true;
		}
	}
	return true;
});
jQuery.validator.addMethod("isResettlement", function(value, element){
	if($('#positiveInfo_resettlementDate').val()!=null&&$('#positiveInfo_resettlementDate').val()!=""){
		if($('#positiveInfo_resettlementDate').val() < $('#positiveInfo_releaseOrBackDate').val()){
           return false;
		}else {
			return true;
		}
	}
	return true;
});
function isShow(){
	if($("#positiveInfosIsCrime").attr("checked")==null){
		$("#positiveInfosCrimeDate-div").hide();
		$("#positiveInfosCrimeDate").val("");
	}else{
		$("#positiveInfosCrimeDate-div").show();
	}
}
$(function(){
	$('#positiveInfo_resettlementDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d',
	    onClose:function(dataText,inst){
	    	if(dataText!=""){
				$('#positiveInfo_noResettlementReason').val("");
				$('#positiveInfo_noResettlementReason').attr('disabled',true);
			}else{
				$('#positiveInfo_noResettlementReason').attr('disabled',false);
				}
	    }
	});
	$('#positiveInfo_resettlementDate').focus(function (){
		$('#positiveInfo_noResettlementReason').val("");
		$('#positiveInfo_noResettlementReason').attr('disabled',true);
	});
	$('#positiveInfo_noResettlementReason').focus(function (){
		$('#positiveInfo_resettlementDate').val("");
	    $('#positiveInfo_resettlementDate').attr('disabled',true);
	    $('#positiveInfo_resettlementDate').attr('readonly',false);
	    $('#positiveInfo_resettlementDate').css('background-color','#EEE');
	});

	$('#positiveInfo_noResettlementReason').blur(function (){
		if($(this).val().trim()!=""){
			$('#positiveInfo_resettlementDate').val("");
		    $('#positiveInfo_resettlementDate').attr('disabled',true);
		    $('#positiveInfo_resettlementDate').attr('readonly',false);
		    $('#positiveInfo_resettlementDate').css('background-color','#EEE');
		}else{
			$('#positiveInfo_resettlementDate').attr('disabled',false);
			 $('#positiveInfo_resettlementDate').attr('readonly',true);
			 $('#positiveInfo_resettlementDate').css('background-color','white');
			}
		});

	$('#positiveInfo_releaseOrBackDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#positiveInfosCrimeDate').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$("#positiveInfosCrimeDate-div").hide();
		isShow();
	$("#positiveInfosIsCrime").click(function(){
		isShow();
	});
	jQuery.validator.addMethod("exculdeDouhao", function(value, element){
		if(value==null||value==undefined||value=="" ){return true};
		var patrn=/^(?:[A-Za-z0-9\u4E00-\u9FA5]*[\(|\（\,\，]*[\,，)|\）]*-*)+$/;
		if (!patrn.exec(value.replace(/[ ]/g,""))) return false
		return true
	});

})


</script>



