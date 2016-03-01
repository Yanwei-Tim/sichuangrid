<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form id="searchNewEconomicOrganizations">
<pop:token />
<div id="search-condition-form" title="新经济组织信息查询"
	class="container container_24">
	<div class="grid_5 lable-right">
		<label class="form-lbl">名称：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionName" 
			class="form-txt" name="searchNewEconomicOrganizationsVo.name"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">住所：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionResidence" 
			class="form-txt" name="searchNewEconomicOrganizationsVo.residence"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">营业执照号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchNewEconomicOrganizationsVo.licenseNumber"
				id="conditionLicenseNumber" class="form-txt" />
	</div>
     <div class="grid_5 lable-right">
		<label class="form-lb1">类别：</label>
	</div>
	<div class="grid_7">
		<select name="searchNewEconomicOrganizationsVo.style" id="style"
				class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE" />
		</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">有效期日期：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchNewEconomicOrganizationsVo.validityStartDate"
				id="validityStartDateS" readonly
				class="form-txt" />
	</div>
	<div class="grid_1 lable-right">至&nbsp;</div>
	<div class="grid_3">
		<input type="text" name="searchNewEconomicOrganizationsVo.validityEndDate"
				id="validityEndDateE" readonly
				class="form-txt" />
	</div>

		<div class="grid_5 lable-right">
			<label class="form-lb1">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="searchNewEconomicOrganizationsVo.chief"
				id="chief"  class="form-txt"  />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lb1">面积(平方米)：</label>
		</div>
		<div class="grid_3">
		<input type="text" name="searchNewEconomicOrganizationsVo.areaStart"
				id="areaS"
				class="form-txt" />
	</div>
	<div class="grid_1 lable-right">至&nbsp;</div>
	<div class="grid_3">
		<input type="text" name="searchNewEconomicOrganizationsVo.areaEnd"
				id="areaE" 
				class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">联系手机：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchNewEconomicOrganizationsVo.mobileNumber"
			id="mobileNumber" maxlength="20"
			 class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">从业人数：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchNewEconomicOrganizationsVo.employeeNumberStart"
			id="employeeNumberS" maxlength="20"
			 class="form-txt" />
	</div>
	<div class="grid_1 lable-right">至&nbsp;</div>
    <div class="grid_3">
	<input type="text" name="searchNewEconomicOrganizationsVo.employeeNumberEnd"
			id="employeeNumberE" 
			class="form-txt" />
    </div>
    <div class="grid_5 lable-right">
		<label class="form-lb1">固定电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchNewEconomicOrganizationsVo.telephone"
			id="telephone" maxlength="20"
			 class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">党员人数：</label>
	</div>
	<div class="grid_3">
		<input type="text" name="searchNewEconomicOrganizationsVo.partyMemberNumberStart"
			id="partyMemberNumberS" maxlength="20"
			class="form-txt" />
	</div>
	<div class="grid_1 lable-right">至&nbsp;</div>
    <div class="grid_3">
	<input type="text" name="searchNewEconomicOrganizationsVo.partyMemberNumberEnd"
			id="partyMemberNumberE" 
			class="form-txt" />
    </div>
	<div class="grid_5 lable-right">
		<label class="form-lb1">传真号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchNewEconomicOrganizationsVo.foxNumber"
			id="foxNumber" maxlength="20"
			class="form-txt" />
	</div>
	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="治安负责人：" name="memberLabelName"/>
		<jsp:param value="searchNewEconomicOrganizationsVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="巡场记录：" name="recordLabelName"/>
		<jsp:param value="searchNewEconomicOrganizationsVo.hasServiceRecord" name="recordSelectName"/>
	</jsp:include>
	<div class="grid_5 lable-right">
   			<label class="form-lb1">关注状态：</label>
   	</div>
   	<div class="grid_7">
		<select id="isEmphasis" class="form-txt" name="searchNewEconomicOrganizationsVo.isEmphasis">
			<option value="-1" selected="selected">全部</option>
			<option value="0">现在关注</option>
			<option value="1">曾经关注</option>
		</select>
	</div>
		
</div>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		
				$('#validityStartDateS').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							maxDate : '+0d'
						});

				$('#validityStartDateE').datePicker(
						{
						});

				$('#validityEndDateS').datePicker(
						{
							yearRange : '1900:2030',
							dateFormat : 'yy-mm-dd',
							maxDate : '+0d'
							
						});

				$('#validityEndDateE').datePicker(
						{
							
						});

				
			});
</script>
