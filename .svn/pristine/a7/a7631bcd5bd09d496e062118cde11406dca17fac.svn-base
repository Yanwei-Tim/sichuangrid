<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="searchRegionalPartyMemberForm" method="post" action="">
		<input type="hidden" name="mode" id="modePart" value="${mode}" />
		<input type="hidden" name="regionalPartyMemberVo.id" id="regionalPartyMemberid" value="${regionalPartyMemberVo.id}" />
		<input type="hidden" name="regionalPartyMemberVo.organization.id" id="regionalPartyMemberOrgId" value="${regionalPartyMemberVo.organization.id}" />

	    <div class="grid_5 lable-right">
	    	<em class="form-req">*</em>
			<label >所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="orgInternalCode" name="orgInternalCode" class="form-txt" maxlength="40" value="${regionalPartyMemberVo.organization.orgName}" readonly />
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<label>姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="regionalPartyMemberName" name="regionalPartyMemberVo.name" class='form-txt' maxlength="60" value="" />
		</div>

		<div class="grid_5 lable-right">
			<label>性别：</label>
		</div>
		<div class="grid_7">
			<select name="regionalPartyMemberVo.gender.id" id="regionalPartyMemberGender" class='form-txt'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"  defaultValue="" />
			</select>
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label>区域党委职务：</label>
		</div>
		<div class="grid_7">
			<select name="regionalPartyMemberVo.partyOrgDuty.id" id="partyOrgDuty" class='form-txt'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGDUTY"  defaultValue="" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label>所属单位：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="unit" name="regionalPartyMemberVo.unit" class="form-txt" maxlength="60" value="" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<label>所属单位职务：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="unitDuty" name="regionalPartyMemberVo.unitDuty" class="form-txt" maxlength="60" value="" />
		</div>
		<div class="grid_5 lable-right">
			<label >联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="mobileNumber" name="regionalPartyMemberVo.mobileNumber" class='form-txt {mobile:true,messages:{mobile:"请正确输入联系手机"}}' maxlength="15" value="" />
		</div>
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<label >固定电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="telephone" name="regionalPartyMemberVo.telephone" class="form-txt {telephone:true,messages:{telephone:'请正确输入固定电话'}}" maxlength="15" value="${regionalPartyMember.telephone}" />
		</div>
	   	</form>
	<div style="clear:both"></div>
</div>

<script type="text/javascript">


</script>