<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="search-condition-form" title="其他场所信息查询" class="container container_24">
	<form id="searchOtherLocaleForm">	
	<div class="grid_5 lable-right">
		<label class="form-lbl">场所名称：</label>
	</div>
	<div class="grid_19 form-left">
		<input type="text" id="conditionName" name="searchOtherLocaleVo.name" class="form-txt" />
	</div>
  	<div class='clearLine'></div>
		
	<div class="grid_5 lable-right">
		<label class="form-lbl">场所地址：</label>
	</div>
	<div class="grid_19 form-left">
		<input type="text" id="conditionAddress" name="searchOtherLocaleVo.address" class="form-txt" />
	</div>
		
	<div class="grid_5 lable-right">
	  	<label class="form-lbl">场所类型：</label>
	  </div>
	<div class="grid_7 form-left">
		<select id="conditionType" name="searchOtherLocaleVo.typeId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@OTHER_LOCALE_TYPE" />
		</select>
    </div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">联系人：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionContactPerson" name="searchOtherLocaleVo.contactPerson" class="form-txt" />
	</div>
			
	<div class="grid_5 lable-right">
		<label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionTelephone" name="searchOtherLocaleVo.telephone" class="form-txt" maxlength="15" />
	</div>
			
	<div class="grid_5 lable-right">
			<label class="form-lbl">联系手机：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionMobileNumber" name="searchOtherLocaleVo.mobileNumber" class="form-txt" maxlength="11" />
	</div>
	<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
		<jsp:param value="治安负责人：" name="memberLabelName"/>
		<jsp:param value="searchOtherLocaleVo.hasServiceTeamMember" name="memberSelectName"/>
		<jsp:param value="巡场记录：" name="recordLabelName"/>
		<jsp:param value="searchOtherLocaleVo.hasServiceRecord" name="recordSelectName"/>
	</jsp:include>
	<div class="grid_5 lable-right">
  		<label class="form-lb1">关注状态：</label>
	</div>
	<div class="grid_7">
		<select id="isLock" name="searchOtherLocaleVo.isEmphasis" class="form-txt" >
		<option value="-1" selected="selected">全部</option>
		<option value="0">现在关注</option>
		<option value="1">曾经关注</option>
		</select>
	</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var placeName = $("#searchByPlaceName").val();
	if(placeName=="请输入其他场所名称"){
		$("#conditionName").val("");
	}else{
		$("#conditionName").val(placeName);
	}
	$.ajax({
		async: false,   
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":getCurrentOrgId()
		},
		success:function(responseData){
			$("#orgName").val(responseData);
		}
	});
	
});
</script>