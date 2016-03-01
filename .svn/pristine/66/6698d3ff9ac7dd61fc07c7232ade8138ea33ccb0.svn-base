<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="keyType"
	value="<s:property value="#parameters.keyType"/>" />
<div id="search-condition-form" title="治安重点信息查询" class="container container_24">	
	<form id="searchEnterpriseForm">
	<input id="keyType" type="hidden" name="enterpriseSearchCondition.keyType" value="${keyType }" />
	<input name="enterpriseSearchCondition.renovateType" id="renovateTypeKey" type="hidden" value="${enterpriseSearchCondition.renovateType}">
	<div class="grid_5 lable-right">
		<label class="form-lbl">场所名称：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionName" name="enterpriseSearchCondition.name" style="width:99%;" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">场所地址：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionAddress" name="enterpriseSearchCondition.address" style="width:99%;" class="form-txt"/>
	</div>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">负责人：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionLegalPerson" name="enterpriseSearchCondition.legalPerson" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">场所类型：</label>
	</div>
	<div class="grid_7">
		<select id="conditionTypeId" name="enterpriseSearchCondition.typeId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SECURITY_TYPE" />
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">负责人电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionTelephone" name="enterpriseSearchCondition.telephone" maxlength="15" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">负责人手机号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionMobileNumber" name="enterpriseSearchCondition.mobileNumber" maxlength="11" class="form-txt"/>
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" name="enterpriseSearchCondition.isEmphasis" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">是否存在隐患：</label>
	</div>
	<div class="grid_7">
		<select id="conditionIsRiskEnterprise" name="enterpriseSearchCondition.isRiskEnterprise" class="form-txt">
			<option value=""></option>
			<option value="true">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
			<option value="false">否</option>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="治安负责人：" name="memberLabelName"/>
		<jsp:param value="enterpriseSearchCondition.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="巡场记录：" name="recordLabelName"/>
		<jsp:param value="enterpriseSearchCondition.hasServiceRecord" name="recordSelectName"/>
	</jsp:include>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">挂牌整治：</label>
	</div>
	<div class="grid_19">
			<s:iterator value="@com.tianque.domain.property.RenovateType@getInternalProperties()" var="renovateTypes">
				<input type="radio" name="renovateTypeName" value="${identify}" onclick="renovateTypeChange()" >${displayName}
			</s:iterator>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">隐患情况：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionHiddenCases" name="enterpriseSearchCondition.hiddenCases" style="width:99%" class="form-txt"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">隐患整改情况：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionHiddenRectification" name="enterpriseSearchCondition.hiddenRectification" style="width:99%;" class="form-txt"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var placeName = $("#searchByPlaceName").val();
	if(placeName=="请输入场所名称"){
		$("#conditionName").val("");
	}else{
		$("#conditionName").val(placeName);
	}
});
function renovateTypeChange(){
	$("#renovateTypeKey").val($("input[name='renovateTypeName']:checked").val());
}
</script>