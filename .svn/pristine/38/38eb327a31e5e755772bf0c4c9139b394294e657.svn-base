<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="regionalPartyMemberForm" method="post" action="${path}/partyBuilding/regionalPartyMemberManage/maintainRegionalPartyMember.action">
		<input type="hidden" name="mode" id="modePart" value="${mode}" />
		<input type="hidden" name="orgId" id="orgId" value="${regionalPartyMember.organization.id}" />
		<input type="hidden" name="regionalPartyMember.id" id="regionalPartyMemberid" value="${regionalPartyMember.id}" />
		<input type="hidden" name="regionalPartyMember.organization.id" id="regionalPartyMemberOrgId" value="${regionalPartyMember.organization.id}" />

	    <div class="grid_5 lable-right">
	    	<em class="form-req">*</em>
			<label >所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="orgInternalCode" name="orgInternalCode" class="form-txt" maxlength="40" value="${regionalPartyMember.organization.orgName}" readonly />
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label>姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="regionalPartyMemberName" name="regionalPartyMember.name" disabled="disabled"  class='form-txt {required:true,messages:{required:"输入姓名"}}' maxlength="60" value="${regionalPartyMember.name}" />
		</div>

		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label>性别：</label>
		</div>
		<div class="grid_7">
			<select name="regionalPartyMember.gender.id" id="regionalPartyMemberGender"  disabled="disabled" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"  defaultValue="${regionalPartyMember.gender.id}" />
			</select>
		</div>
		
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label>区域党委职务：</label>
		</div>
		<div class="grid_7">
			<select name="regionalPartyMember.partyOrgDuty.id" id="partyOrgDuty"  disabled="disabled" class='form-txt {required:true,messages:{required:"请选择区域党委职务"}}'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGDUTY"  defaultValue="${regionalPartyMember.partyOrgDuty.id}" />
			</select>
		</div>
		<div class="grid_5 lable-right">
			<label>所属单位：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="unit" name="regionalPartyMember.unit"  disabled="disabled" class="form-txt" maxlength="60" value="${regionalPartyMember.unit}" />
		</div>

		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<label>所属单位职务：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="unitDuty" name="regionalPartyMember.unitDuty"  disabled="disabled" class="form-txt" maxlength="60" value="${regionalPartyMember.unitDuty}" />
		</div>
		<div class="grid_5 lable-right">
			<label >联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="mobileNumber" name="regionalPartyMember.mobileNumber"  disabled="disabled" class='form-txt {mobile:true,messages:{mobile:"请正确输入联系手机"}}' maxlength="15" value="${regionalPartyMember.mobileNumber}" />
		</div>
		<div style="clear:both"></div>
		<div class="grid_5 lable-right">
			<label >固定电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="telephone" name="regionalPartyMember.telephone"  disabled="disabled" class="form-txt {telephone:true,messages:{telephone:'请正确输入固定电话'}}" maxlength="15" value="${regionalPartyMember.telephone}" />
		</div>
	   	</form>
	<div style="clear:both"></div>
</div>

<script type="text/javascript">

</script>