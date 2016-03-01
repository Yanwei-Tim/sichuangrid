<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<form name="searchForm" action="#">
<pop:token />
<div id="search-condition-form" title="优抚对象查询" class="container container_24">

	<div  class="grid_3 lable-right">
		<label class="form-lbl">姓名： </label>
	</div>
	<div class="grid_5">
	   <input type="text" id="conditionName" maxlength="20" class="form-txt" />
    </div>
    <div  class="grid_1"></div>
    
    <div  class="grid_3 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_5">
		<input type="text" id="conditionIdCardNo" maxlength="18" class="form-txt" />
    </div>
    <div  class="grid_3 lable-right">
	  	<label class="form-lbl">性别：</label>
	</div>
	<div class="grid_4">
		<select id="conditionGenderId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
		</select>
    </div>    
    
 	<div class='clearLine'>&nbsp;</div>    
	<hr width="90%" />	 	
 	<div class='clearLine'>&nbsp;</div>
 	

 	<div class='clearLine'>&nbsp;</div>
 	
 	<div class="grid_3 lable-right">
    	<label class="form-lb1">出生日期：</label>
    </div>
    <div class="grid_1 lable-right">
    	从：
    </div>
    <div class="grid_4">
    	<input type="text" id="conditionBirthdayStart" maxlength="32" class="form-txt" readonly />
    </div>
    <div  class="grid_1"></div> 
    <div class="grid_3 lable-right">
    	到：
    </div>
    <div class="grid_5">
    	<input type="text" id="conditionBirthdayEnd" maxlength="32" class="form-txt" readonly />
    </div>

    <div class="clearLine">&nbsp;</div>
 	
    <div class="grid_3 lable-right">
    	<label class="form-lb1">月生活费：</label>
    </div>
    <div class="grid_2">
    	<input type="text" id="conditionMinMonthsExpenses" maxlength="9" class="form-txt" style="width: 99%"/>
    </div>
    <div class="grid_1 lable-right">
    	到：
    </div>
    <div class="grid_2">
    	<input type="text" id="conditionMaxMonthsExpenses" maxlength="9" class="form-txt" style="width: 99%"/>
    </div>
    <div  class="grid_1"></div> 
        
    <div class="grid_3 lable-right">
    	<label class="form-lb1">联系电话：</label>
    </div>
    <div class="grid_5">
    	<input type="text" id="conditionTelephone" maxlength="15" class="form-txt" />
    </div>
    
    <div class="grid_3  lable-right">
    	<label class="form-lb1">联系手机：</label>
    </div>
    <div class="grid_4">
    	<input type="text" id="conditionMobileNumber" maxlength="11" class="form-txt" />
    </div>
    <div class="clearLine">&nbsp;</div>
   
	<div  class="grid_3 lable-right">
		<label class="form-lbl">优抚证号： </label>
	</div>
	<div class="grid_5">
	   <input type="text" id="conditionSpecialCareNo" maxlength="20" class="form-txt" />
    </div>
    <div  class="grid_1"></div>
    <div class="grid_3 lable-right">
    	<label class="form-lb1">优抚类型：</label>
    </div>
    <div class="grid_5">
    	<select id="conditionSpecialCareTypeId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SPECIAL_CARE_TYPE" />
		</select>
    </div>    
    
    <div class="grid_3 lable-right">
    	<label class="form-lb1">劳动能力：</label>
    </div>
    <div class="grid_4">
    	<select id="conditionLabourCapacityId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LABOUR_CAPACITY" />
		</select>
    </div>
    <div class="clearLine">&nbsp;</div>
    
    <div class="grid_3 lable-right">
    	<label class="form-lb1">生活能力：</label>
    </div>
    <div class="grid_5">
    	<select id="conditionViabilityId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@VIABILITY" />
		</select>
    </div>
    <div  class="grid_1"></div>
        
    <div class="grid_3 lable-right">
    	<label class="form-lb1">就业情况：</label>
    </div>
    <div class="grid_5">
    	<select id="conditionEmploymentStatusId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EMPLOYMENT_STATUS" />
		</select>
    </div>
    <div class="grid_3 lable-right">
    	<label class="form-lb1">供养情况：</label>
    </div>
    <div class="grid_4">
    	<select id="conditionSupportStatusId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SUPPORT_STATUS" />
		</select>
    </div>
    <div class="clearLine">&nbsp;</div>
 
	<div  class="grid_3 lable-right">
		<label class="form-lbl">常住地址： </label>
	</div>
	<div class="grid_14">
	   <input type="text" id="conditionCurrentAddress" maxlength="20" class="form-txt" />
    </div>

</div>
</form>

<script type="text/javascript">
$(document).ready(function(){
	$('#conditionBirthdayStart').datePicker({
		yearRange: '1930:2030',
        maxDate:'+0d'});
	$('#conditionBirthdayEnd').datePicker({
		yearRange: '1930:2030',
        maxDate:'+0d'});
});
</script>