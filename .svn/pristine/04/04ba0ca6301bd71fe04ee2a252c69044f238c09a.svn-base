<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="searchPrimaryOrgForm">
<pop:token />
	<input id="mode" type="hidden" name="mode" value="${mode}"/> 

	<div id="search-condition-form" title="组织机构查询" class="container container_24">
		 
		 <div class="grid_4 lable-right">
	  		<label class="form-lbl">所属区域：</label>
	  	</div>
   		<div class="grid_19 form-left">
	    	<input type="text" id="orgName" name="primaryOrg.org.orgName" value="${primaryOrg.org.orgName}"  readonly class="form-txt" />
   		</div>
		<div class='clearLine'></div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">组织名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionDetailName" name="primaryOrgVo.detailName" class="form-txt" maxlength="30"/>
		</div>
		 
	<c:if test="${teamTypeName!='' }">
		<div class="grid_4 lable-right">
			<label class="form-lbl">组织类别：</label>
		</div>
		<div class="grid_8">
			<select id="teamTypeId" name="primaryOrgVo.teamType.id" class="form-select" >
				<s:if test="isCommissionOrganization.equals('comprehensive') || isCommissionOrganization.equals('commission') ||isCommissionOrganization.equals('commissionMember')">
						<option value="">请选择</option>
						<option value="${typeId }">${teamTypeName }</option>
					</s:if>
					<s:else>
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@${teamTypeName}" defaultValue="${primaryOrgVo.teamType.id}" />
					</s:else>
			</select>
	  	</div>
	</c:if>
	  	
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">成员人数：从</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMin" name="primaryOrgVo.memberCountMin" class="form-txt" />
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="conditionTeamCountMax" name="primaryOrgVo.memberCountMax" class="form-txt" />
		</div>
		<div class='clearLine'></div>
		
		<c:if test="${'Masseduty'==name}"> 
		<div class="grid_4 lable-right">
			<label class="form-lbl">数据来源：</label>
		</div>
		<div class="grid_8">
			<select id="teamTypeId" name="primaryOrgVo.isSynchronize" class="form-select" >
				<option value="">全部</option>
				<option value="0">录入</option>
				<option value="1">同步</option>
			</select>
		</div>
		</c:if>
		<c:if test="${'Postulantduty'==name}"> 
		<div class="grid_8 lable-right">
			<label class="form-lbl">是否已同步到群防群治组织：</label>
		</div>
		<div class="grid_4">
			<select id="teamTypeId" name="primaryOrgVo.isSynchronize" class="form-select" >
				<option value="">请选择</option>
				<option value="1">是</option>
				<option value="0">否</option>
			</select>
	  	</div>
	  	</c:if>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){

	$("#searchPrimaryOrgForm").formValidate({
		promptPosition: "bottomLeft",
		rules:{
			"primaryOrgVo.memberCountMin":{
				nonNegativeInteger:true,
				min:0,
				max:999999
			},
			"primaryOrgVo.memberCountMax":{
				nonNegativeInteger:true,
				min:0,
				max:999999
			}
		},
		messages:{
			"primaryOrgVo.memberCountMin":{
				nonNegativeInteger:"成员人数只能输入正整数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			},
			"primaryOrgVo.memberCountMax":{
				nonNegativeInteger:"成员人数只能输入正整数",
				min:"成员人数最小输入0",
				max:"成员人数最大输入999999"
			}
		}
	});
	
});
</script>
