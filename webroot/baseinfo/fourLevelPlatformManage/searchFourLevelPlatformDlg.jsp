<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="dialog-form" class="container container_24">
	<form id="searchPlatformForm">
		<input type="hidden" id="organization" name="searchFourLevelPlatformVo.orgId" value="${param.orgId}"/>
		<input type="hidden" id="organization.id" name="organization.id" value="${param.orgId}"/>
		<input type="hidden" id="displayLevel" name="searchFourLevelPlatformVo.displayLevel" value="${param.displayLevel}"/>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">组织名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="name" name="searchFourLevelPlatformVo.name" class="form-txt" maxlength="30"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">组织类型：</label>
		</div>
		<div class="grid_8">
			<select id="teamTypeId" name="searchFourLevelPlatformVo.teamTypeId" class="form-select" >
				<option value="">全部</option>
				<option value="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>">县监管中心</option>
				<option value="<s:property value='@com.tianque.domain.property.OrganizationLevel@TOWN'/>">镇监管分中心</option>
				<option value="<s:property value='@com.tianque.domain.property.OrganizationLevel@VILLAGE'/>">社区工作站</option>
				<option value="<s:property value='@com.tianque.domain.property.OrganizationLevel@GRID'/>">网格</option>
			</select>
	  	</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">成员人数：从</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMin" name="searchFourLevelPlatformVo.memberCountMin" class="form-txt" />
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMax" name="searchFourLevelPlatformVo.memberCountMax" class="form-txt" />
		</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){

	$("#searchPrimaryOrgForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
			"primaryOrgVo.memberCountMin":{
				number:true,
				min:0,
				max:999999
			},
			"primaryOrgVo.memberCountMax":{
				number:true,
				min:0,
				max:999999
			}
		},
		messages:{
			"primaryOrgVo.memberCountMin":{
				number:"成员人数只能输入正数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			},
			"primaryOrgVo.memberCountMax":{
				number:"成员人数只能输入正数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			}
		}
	});
	
});
</script>
