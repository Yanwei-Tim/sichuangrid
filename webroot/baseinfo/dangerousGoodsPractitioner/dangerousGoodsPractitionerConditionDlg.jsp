<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="searchDangerousGoodsPractitionerForm">
<pop:token />
<div id="search-condition-dangerousGoodsPractitioner" title="危险品从业人员查询" class="container container_24">
	<div class="grid_4 lable-right">
				<label class="form-lbl">关注状态：</label>
	</div>
	<div class="grid_8">
              <select id="isLock" name="searchDangerousGoodsPractitionerVo.empsisState" class="form-txt">
                  <option value="-1" selected="selected">全部</option>
                  <option value="0">现在关注</option>
                  <option value="1">曾经关注</option>
              </select>
              	</div>
          	<div class="grid_4 lable-right">
				<label class="form-lbl">死亡状态：</label>
			</div>
		<div class="grid_8">
              <select id="isDeath" name="searchDangerousGoodsPractitionerVo.isDeath" class="form-txt">
                  <option value="-1" selected="selected">全部</option>
                  <option value="0">正常</option>
                  <option value="1">已死亡</option>
              </select>
	</div>
	<div class="grid_4 lable-right">   
		<label class="form-lbl">姓名：<c:if test='${mode!="view"}'></c:if></label>
   	</div>
	<div class="grid_8">
		<input type="text" name="searchDangerousGoodsPractitionerVo.name"  maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_8">
		 <input type="text" name="searchDangerousGoodsPractitionerVo.idCardNo" maxlength="18" class="form-txt" />
	</div>
			
	<div class="grid_4 lable-right">
		<label class="form-lbl">危险从业类别：<c:if test='${mode!="view" }'></c:if></label>
	</div>
	<div class="grid_8">
		<select name="searchDangerousGoodsPractitionerVo.dangerousWorkingType.id" class="form-txt">
   			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DANGEROUS_WORKING_TYPE" />
 		</select>
 	</div>	
 	
 	<div class="grid_4 lable-right">
		<label class="form-lbl">就职企业：</label>
	</div>
	<div class="grid_8"> 
		<input type="text" name="searchDangerousGoodsPractitionerVo.workUnit"  maxlength="50" class="form-txt"/>
	</div>	
 	
 	<div class="grid_4 lable-right">
		<label class="form-lbl">企业法人代表：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="searchDangerousGoodsPractitionerVo.legalPerson"  maxlength="20" class="form-txt" />
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">法人联系手机：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="searchDangerousGoodsPractitionerVo.legalPersonMobileNumber"  maxlength="11"	class="form-txt" />
	</div>
			
	<div class="grid_4 lable-right">
		<label class="form-lbl">法人固定电话：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="searchDangerousGoodsPractitionerVo.legalPersonTelephone" maxlength="15" class="form-txt" />
	</div>		
	
	<div class="grid_5 lable-right">
	    <label class="form-lbl">文化程度：</label>
	</div>
	<div class="grid_7">
	    <select name="searchDangerousGoodsPractitionerVo.schooling.id" class="form-txt">
	        <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
	    </select>
	</div>
	
	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="服务成员：" name="memberLabelName"/>
		<jsp:param value="searchDangerousGoodsPractitionerVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="服务记录：" name="recordLabelName"/>
		<jsp:param value="searchDangerousGoodsPractitionerVo.hasServiceTeamRecord" name="recordSelectName"/>
	</jsp:include>
	
	<div class='clearLine'></div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">
		    <div class="grid_4 lable-right">
				<label class="form-lbl">从业资格证书：</label>
			</div>
			<div class="grid_8">
			    <input type="text" name="searchDangerousGoodsPractitionerVo.workingCertificate" maxlength="20" class="form-txt" />
			</div>
		
			<div class="grid_5 lable-right">
				<label class="form-lbl">从业资格证书号：</label>
			</div>
			<div class="grid_7">
			    <input type="text" name="searchDangerousGoodsPractitionerVo.workingCertificateNo" maxlength="20" class="form-txt" />
			</div>
			
			<div class="grid_4 lable-right">
				<label class="form-lbl">有效期：</label>
			</div>
			<div class="grid_3 lable-right">
			    <input type="text" name="searchDangerousGoodsPractitionerVo.startAvailableDate" id="conditionStartAvailableDate" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">至&nbsp;&nbsp;&nbsp;</label>
			</div>
			<div class="grid_3 lable-right">
			    <input type="text" name="searchDangerousGoodsPractitionerVo.endAvailableDate" id="conditionEndAvailableDate" readonly="readonly" class="form-txt" />
			</div>
				
			<div class="grid_5 lable-right">
				<label class="form-lbl">性别：</label>
			</div>
			<div class="grid_7">
				<select name="searchDangerousGoodsPractitionerVo.gender.id"  class="form-txt">
	   				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"/>
	 			</select>
	 		</div>
	 		
	 		<div class="grid_4 lable-right">
				<label class="form-lbl">出生日期：</label>
			</div>
			<div class="grid_3">
			    <input type="text" name="searchDangerousGoodsPractitionerVo.startBirthday" id="conditionStartBirthday" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_2 lable-right">
				<label class="form-lbl">至&nbsp;&nbsp;&nbsp;</label>
			</div>
			<div class="grid_3">
			    <input type="text" name="searchDangerousGoodsPractitionerVo.endBirthday" id="conditionEndBirthday" readonly="readonly" class="form-txt" />
			</div>
	 		
	 		<div class="grid_5 lable-right">
	    		<label class="form-lbl">职业：</label>
			</div>
			<div class="grid_7">
				<select name="searchDangerousGoodsPractitionerVo.career.id" class="form-txt">
	        		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CAREER"/>
				</select>
			</div>
	 		
	 		<div class="grid_4 lable-right">
				<label class="form-lbl">户籍地：</label>
			</div>
			<div class="grid_5 lable-right">
	            <select id="province" name="searchDangerousGoodsPractitionerVo.province" class="form-txt">
	            </select>
			</div>
			<div class="grid_1 lable-left">
				<label class="form-lbl">&nbsp;省</label>
			</div>
			<div class="grid_5 lable-right">
	        	<select id="city" name="searchDangerousGoodsPractitionerVo.city" class="form-txt">
	            </select>
			</div>
			<div class="grid_1 lable-left">
				<label class="form-lbl">&nbsp;市</label>
			</div>
			<div class="grid_5 lable-left">
	            <select id="district" name="searchDangerousGoodsPractitionerVo.district" class="form-txt">
	         	</select>
			</div>
			<div class="grid_1 lable-left">
				<label class="form-lbl">&nbsp;县</label>
			</div>
			<div class="grid_2"></div>
	 		
			<div class="grid_4 lable-right">
				<label class="form-lbl">户籍详址：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchDangerousGoodsPractitionerVo.nativePlaceAddress" class="form-txt" maxlength="50" />
			</div>
	
	 		<div class="grid_4 lable-right">
	 			<label class="form-lbl">常住地址：</label>
	 		</div>
			<div class="grid_20">
				<input type="text" name="searchDangerousGoodsPractitionerVo.currentAddress" class="form-txt" maxlength="50" />
			</div>
		</div>
	</div>
</form>
<script type="text/javascript">
$("#showMoreBtn").toggle(
	function(){
		$("#dangerousGoodsPractitionerDialog").dialog( "option" , {height:450});
		$("#showMoreCtt").show();
		$(this).addClass("cur").text("隐藏更多条件");
	},
	function(){
		$("#dangerousGoodsPractitionerDialog").dialog( "option" , {height:330} );
		$("#showMoreCtt").hide();
		$(this).removeClass("cur").text("显示更多条件");
	}
);
$(document).ready(function(){
	threeSelect({
		province:"province",
		city:"city",
		district:"district"
	});
	
	
	$("#conditionStartAvailableDate, #conditionEndAvailableDate").datePicker({
		yearRange: '1970:2030',
		defaultDate: "+1d",
		onSelect: function( selectedDate ) {
			var option = this.id == "conditionStartAvailableDate" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
			$("#conditionStartAvailableDate, #conditionEndAvailableDate").not( this ).datepicker( "option", option, date);
		}
	});
	
	$("#conditionStartBirthday, #conditionEndBirthday").datePicker({
		yearRange: '1900:2030',
		defaultDate:'+0d',
		onSelect: function( selectedDate ) {
			var option = this.id == "conditionStartBirthday" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
			$("#conditionStartBirthday, #conditionEndBirthday").not( this ).datepicker( "option", option, date);
		}
	});
	
	$("#state").change(function(){
		if($("#state").val()=='1'){
			$("#isLock").css("display","inline");
			$("#isDeath").css("display","none");
		}else{
			$("#isLock").css("display","none");
			$("#isDeath").css("display","inline");
		}
	});
});


</script>



