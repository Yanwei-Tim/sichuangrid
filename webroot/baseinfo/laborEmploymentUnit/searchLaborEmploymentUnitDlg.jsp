<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchLaborEmploymentUnitForm">
<pop:token />
	<div class="container container_24">
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位名称：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="searchLaborEmploymentUnitVo.companyName" id="conditionCompanyName" maxlength="50"  class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="searchLaborEmploymentUnitVo.companyAddress" id="conditionCompanyAddress" maxlength="50" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位类别：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.companyType" id="conditionCompanyType" maxlength="30"  class="form-txt" />
		</div>		
		<div class="grid_4">
		</div>	
		<div class="grid_8 lable-right">
			<input type="checkbox" name="searchLaborEmploymentUnitVo.isKey" id="conditionIsKey" value="true"/>
			<label class="form-lb1">是否重点单位</label>
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">法人代表：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.corporateRepresentative" id="conditionCorporateRepresentative" maxlength="50" class="form-txt"  />
		</div>
		
		<div  class="grid_4 lable-right">
			<label class="form-lb1">身份证号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.idCardNo" id="conditionIdCardNo"  maxlength="20"  class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">单位电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.telephone" id="conditionTelephone" maxlength="20" class="form-txt" />
		</div>	
		<div class="grid_4 lable-right">
			<label class="form-lb1">组织机构代码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.orgCode" id="conditionOrgCode"  maxlength="50"  class="form-txt"/>
		</div>	   
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">营业执照号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.businessLicenseNo" id="conditionBusinessLicenseNo" maxlength="32" class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">经济性质：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.economicNature" id="conditionEconomicNature" maxlength="50" class="form-txt" />
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">注册资本:从:</label>
		</div>
		<div class="grid_3" id="registeredCapitalDiv">
	    	<input type="text" name="searchLaborEmploymentUnitVo.registeredCapitalStart" id="conditionRegisteredCapitalStart" maxlength="10" class="form-txt "/>
		</div>
		<div class="grid_2 lable-right">
			&nbsp;至：
		</div>
		<div class="grid_3" id="registeredCapitalDiv">
	    	<input type="text" name="searchLaborEmploymentUnitVo.registeredCapitalEnd" id="conditionRegisteredCapitalEnd" maxlength="10" class="form-txt "/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">注册日期:从:</label>
		</div>
		<div class="grid_3" id="registrationDateDiv">
	    	<input type="text" name="searchLaborEmploymentUnitVo.registrationDateStart" id="conditionRegistrationDateStart" maxlength="32" readonly class="form-txt " />
		</div>
		<div class="grid_2 lable-right">
			&nbsp;至：
		</div>
		<div class="grid_3" id="registrationDateDiv">
	    	<input type="text" name="searchLaborEmploymentUnitVo.registrationDateEnd" id="conditionRegistrationDateEnd" maxlength="32" readonly class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">有效日期:从:</label>
		</div>
		<div class="grid_3" id="expiryDateDiv">
			<input type="text" name="searchLaborEmploymentUnitVo.expiryDateStart" id="conditionExpiryDateStart" maxlength="32" readonly class="form-txt " />		
		</div>
		<div class="grid_2 lable-right">
			&nbsp;至：
		</div>
		<div class="grid_3" id="expiryDateDiv">
	    	<input type="text" name="searchLaborEmploymentUnitVo.expiryDateEnd" id="conditionExpiryDateEnd" maxlength="32" readonly class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">主管部门：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.competentDepartment" id="conditionCompetentDepartment" maxlength="20"  class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">经营范围：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="searchLaborEmploymentUnitVo.businessScope" id="conditionBusinessScope" maxlength="18"  class="form-txt "/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">注册地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="searchLaborEmploymentUnitVo.registrationAddress" id="conditionRegistrationAddress" maxlength="20" class="form-txt " />
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">从业人数:从:</label>
		</div>
		<div class="grid_3" id="employeesNumDiv">
	    	<input type="text" name="searchLaborEmploymentUnitVo.employeesNumStar" id="conditionEmployeesNumStart" maxlength="11" class="form-txt " />
		</div>
		<div class="grid_2 lable-right">
			&nbsp;至：
		</div>
		<div class="grid_3" id="employeesNumDiv">
	    	<input type="text" name="searchLaborEmploymentUnitVo.employeesNumEnd" id="conditionEmployeesNumEnd" maxlength="11" class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">管理部门：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.supervisoryDepartment" id="conditionSupervisoryDepartment" maxlength="20" class="form-txt " />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">管理级别：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.supervisoryLevel" id="conditionSupervisoryLevel" maxlength="20" class="form-txt " />
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">消防等级：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchLaborEmploymentUnitVo.fireFightingLevel" id="conditionFireFightingLevel" maxlength="20" class="form-txt " />
		</div>
	</div>
</form>
<script type="text/javascript">
$(function(){
	$('#conditionRegistrationDateStart').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRegistrationDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionRegistrationDateEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionRegistrationDateStart").datepicker("option", "maxDate",date);
			}
		}
	});
	$('#conditionExpiryDateStart').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
   		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpiryDateEnd").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionExpiryDateEnd').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionExpiryDateStart").datepicker("option", "maxDate",date);
			}
		}
	});
});

</script>