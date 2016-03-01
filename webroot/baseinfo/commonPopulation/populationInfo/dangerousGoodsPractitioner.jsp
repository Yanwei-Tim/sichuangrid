<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
	<span style="font-weight: bold; margin-left:20px; ">危险品从业人员</span>
	<div class='clearLine'>&nbsp;</div>
<div class="grid_7 lable-right">
	<label class="form-lbl">关注程度：</label>
</div>
<div class="grid_5">
	<select name="population.attentionExtent.id" id="attentionExtent" class="form-txt"
		<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${population.attentionExtent.id}" />
	</select>
</div>
<div class="grid_6 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">危险从业类别：</label></div>
	<div class="grid_6">
	  <select name="population.dangerousWorkingType.id" class="form-txt {required:true,messages:{required:'请输入危险品从业类别'}}" title="民爆：民用爆炸品（比如：炸药 ,震源药柱 ,起爆药柱,工程雷管 ,塑料导爆管,  导火索,导爆索,聚能射孔弹）。 化工行业的危险物品（比如：硫酸，硫磺，）">
   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DANGEROUS_WORKING_TYPE" defaultValue="${population.dangerousWorkingType.id}"/>
 	  </select>
 	</div>

	<div class="grid_7 lable-right"><label class="form-lbl">从业资格证书：</label></div>
	<div class="grid_5">
		 <input type="text" name="population.workingCertificate" maxlength="20"
		   		class="form-txt dialogtip {exculdeParticalChar:true,messages:{exculdeParticalChar:'不能输入非法字符'}}" title="从事某特殊行业的资格证书（例如：危险品行业资格证书）"
	  			value="${population.workingCertificate}" />
	</div>

	<div class="grid_6 lable-right"><label class="form-lbl">从业资格证书号：</label></div>
	<div class="grid_6">
		<input type="text" name="population.workingCertificateNo" maxlength="20"
		       class='form-txt dialogtip' title="从业资格证书编号"
	  		   value="${population.workingCertificateNo}" />
	</div>
	<div class="grid_7 lable-right"><label class="form-lbl">有效期：</label></div>
	<div class="grid_5"><input type="text" maxlength="13" id="startDay" class="form-txt " name="population.periodOfValidityStart"  value="<s:date name="population.periodOfValidityStart" format="yyyy-MM-dd"/>" readonly="readonly" style="background-color: white"/></div>
	<div class="grid_6 lable-right"><label class="form-lbl">至：</label></div>
	<div class="grid_6"><input type="text" size="13" id="endDay" class="form-txt"  name="population.periodOfValidityEnd"  value="<s:date name="population.periodOfValidityEnd" format="yyyy-MM-dd"/>" readonly="readonly" style="background-color: white"/></div>
	
	<div class="grid_7 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">企业法人代表：</label></div>
	<div class="grid_5">
		<input type="text" name="population.legalPerson" maxlength="20" value="${population.legalPerson}" class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入法人姓名",exculdeParticalChar:"法人姓名只能输入字母，数字和中文字符",minlength:"法人姓名至少需要输入{0}个字符",maxlength:"法人姓名最多需要输入{0}个字符"}}' />
	</div>
	<div class="grid_6 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">企业法人手机号：</label></div>
	<div class="grid_6">
		 <input type="text" name="population.legalPersonMobileNumber" maxlength="11"
		    	class="form-txt {required:true,mobile:true,messages:{required:'请输入手机号码',mobile:'手机号码输入只能是以1开头的11位数字'}}" title="请输入11位以1开头的联系手机  例如：13988888888"
  				value="${population.legalPersonMobileNumber}" />
	</div>
	<div class="grid_7 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">企业法人联系电话：</label></div>
	<div class="grid_5">
		 <input type="text" name="population.legalPersonTelephone" maxlength="20"
		    	class="form-txt {required:true,telephone:true,messages:{required:'请输入联系电话',telephone:'联系电话只能输数字和横杠(-)'}}" title="请输入由数字和-组成的联系电话 例如：0577-88888888"
  			    value="${population.legalPersonTelephone}"/>
	</div>
	<%-- 
	<div class="grid_6 lable-right"><em class="form-req">*&nbsp;</em><label class="form-lbl">工作单位或就读学校：</label></div>
	<div class="grid_6">
		<input type="text" name="population.workUnit" maxlength="20" value="${population.workUnit}" class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入工作单位或就读学校",exculdeParticalChar:"工作单位或就读学校只能输入字母，数字和中文字符",minlength:"工作单位或就读学校至少需要输入{0}个字符",maxlength:"工作单位或就读学校最多需要输入{0}个字符"}}' />
	</div>
	 --%>
	
	
	<div class='clearLine'>&nbsp;</div>

<script>
$(function(){
	$("#startDay").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
		maxDate: '-1d',
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,parseInt(dateUnit[2])+1);
				$("#endDay").datepicker("option","minDate",date);
			}
		}
    }); 
	$("#endDay").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]-1);
				var today = new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate());
				if(date>=today){
					date = new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()-1);
					$("#startDay").datepicker("option", "maxDate",date);
				}
				$("#startDay").datepicker("option", "maxDate",date);
			}
		}
	});
	
	/*jQuery.validator.addMethod("periodOfValidityStartWithNow", function(value, element){
		if(value=="") return true;
		else return !compareDateWithNow(value);
	});
	$('#startDay').datePicker({
		yearRange: '1900:2060',
		dateFormat:'yy-mm-dd',
		onSelect : function(dateText, inst) {
			$("#endDay").datePicker({
				    yearRange: '1900:2060',
				    dateFormat:'yy-mm-dd',
					minDate: dateText
			});
		}
	});
	jQuery.validator.addMethod("endDayMustBigStartDay",function(value,element){
        var endDay=$("#endDay").val();
        if(endDay==null && value==null){
            return false;
        }else{
        	alert(endDay!=null && value!=null);
 	       if(endDay>value){
 	           return true;
 	       }
        }
	});*/

})
</script>