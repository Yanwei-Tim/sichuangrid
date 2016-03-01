<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<form id="searchNewSocietyOrganizationsForm" >
<pop:token />
<input type="hidden" id="objectType" name="searchNewSocietyOrganizationsVo.objectType" value="">
	<div id="search-condition-form" title="社会组织查询" class="container container_24">
		<input type="hidden" name="searchNewSocietyOrganizationsVo.orgInternalCode" id="query_orgInternalCode"
		value="${searchNewSocietyOrganizationsVo.orgInternalCode}" >
		<div class="grid_5 lable-right">
	  		<label class="form-lbl">所属区域：</label>
	  	</div>
   		<div class="grid_19 form-left">
<%-- 			<input type="hidden" name="searchPartyMemberInfoVo.organization.id" id="orgInternalCode" class="form-txt" value="${orgId}"/> --%>
<%-- 	    	<input type="text" name="orgInternalCode" id="orgInternalCode" class="form-txt" value="${population.organization.orgName}" readonly/> --%>
	    	<input type="text" id="orgName" name="orgName" value="${newSocietyOrganizations.organization.orgName}"  readonly class="form-txt" />

   		</div>
		<div class="clearLine">&nbsp;</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">组织名称：</label>
		</div>
		<div class="grid_19 form-left">
			<input type="text" id="query_unitName" name="searchNewSocietyOrganizationsVo.unitName" maxlength="30" class="form-txt" />
		</div>
		<div class='clearLine'></div>

		<div  class="grid_5  lable-right" >
			<label class="form-lbl">住所：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="query_address" name="searchNewSocietyOrganizationsVo.address" maxlength="30" class="form-txt" />
		</div>
		<div class='clearLine'></div>

        <div class="grid_5 lable-right">
			<label class="form-lbl">主要职责：</label>
		</div>
		<div class="grid_7 form-left">
			<input type="text" id="query_mainResponsibilities" name="searchNewSocietyOrganizationsVo.mainResponsibilities" maxlength="50"
			class="form-txt" />
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">组织类别：</label>
		</div>
		<div class="grid_7 form-left">
			<select name="searchNewSocietyOrganizationsVo.typeId" id="query_typeId" class="form-txt">
				<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@SOCIETY_GROUP" defaultValue="${searchNewSocietyOrganizationsVo.typeId}"  reletionId="sub_queryTypeId"
				reletionName="@com.tianque.domain.property.PropertyTypes@NEW_SOCIETY_TYPE" id="query_typeId"/>
			</select>
		</div>
   		<div class="clearLine">&nbsp;</div>

		<%-- <div class="grid_5 lable-right">
			<label class="form-lbl">组织子类别：</label>
		</div>
		<div class="grid_7 form-left">
 			<select name="searchNewSocietyOrganizationsVo.subType" id="sub_queryTypeId" class="form-txt">
 			</select>
		</div> --%>
		<div class='clearLine'></div>
		<jsp:include page="/baseinfo/common/commonHasOrHavntForSearch.jsp">
			<jsp:param value="治安负责人：" name="memberLabelName"/>
			<jsp:param value="searchNewSocietyOrganizationsVo.hasServiceTeamMember" name="memberSelectName"/>
			<jsp:param value="巡场记录：" name="recordLabelName"/>
			<jsp:param value="searchNewSocietyOrganizationsVo.hasServiceRecord" name="recordSelectName"/>
		</jsp:include>
		<div class='clearLine'></div>
       	<div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" class="form-txt" >
				<option value="" selected="selected">全部</option>
				<option value="false">现在关注</option>
				<option value="true">曾经关注</option>
			</select>
		</div>
		<div class="grid_5 lable-right">
  			<label class="form-lbl">固定电话：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="query_legalPersonTelephone" name="searchNewSocietyOrganizationsVo.legalPersonTelephone" maxlength="15"
			class="form-select form-txt dialogtip" title="请输入由数字和-组成的联系电话!例如：0577-88888888" />
		</div>
        <div class='clearLine'></div>
    	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
		<div id="showMoreCtt" style="display: none">

		<div class="grid_5 lable-right">
			<label class="form-lbl">法定代表人：</label>
		</div>
		<div class="grid_7 form-left">
			<input type="text" id="query_legalPerson" name="searchNewSocietyOrganizationsVo.legalPerson" maxlength="20" class="form-txt" />
	    </div>
		<div  class="grid_5  lable-right" >
			<label class="form-lbl">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="query_legalPersonMobileNumber" name="searchNewSocietyOrganizationsVo.legalPersonMobileNumber" maxlength="11" class="form-txt" />
		</div>
		<div class='clearLine'></div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">业务主管单位：</label>
		</div>
		<div class="grid_7 form-left">
			<input type="text" id="query_chargeUnit" name="searchNewSocietyOrganizationsVo.chargeUnit" maxlength="50" class="form-txt" />
	    </div>
		<div class="grid_5 lable-right">
  			<label class="form-lbl">登记证号码：</label>
  		</div>
		<div class="grid_7 form-left">
			<input type="text" id="query_registrationNumber" name="searchNewSocietyOrganizationsVo.registrationNumber" maxlength="50" class="form-txt" />
	    </div>
		<div class='clearLine'></div>

	 	<div class="grid_5 lable-right">
            <label class="form-lbl">有效期起始时间：从</label>
        </div>
        <div class="grid_3">
            <input type="text" id="startValidityStartDate" name="searchNewSocietyOrganizationsVo.startValidityStartDate" readonly="readonly" class="form-txt" />
        </div>
	     <div class="grid_1 lable-right">
				<label class="form-lbl">&nbsp;至：</label>
		</div>
        <div class="grid_3">
            <input type="text" id="endValidityStartDate" name="searchNewSocietyOrganizationsVo.endValidityStartDate" readonly="readonly" class="form-txt" />
        </div>
		<div class="grid_5 lable-right">
            <label class="form-lbl">有效期结束时间：从</label>
        </div>
        <div class="grid_3">
            <input type="text" id="startValidityEndDate" name="searchNewSocietyOrganizationsVo.startValidityEndDate" readonly="readonly" class="form-txt" />
        </div>
	     <div class="grid_1 lable-right">
				<label class="form-lbl">&nbsp;至：</label>
		</div>
        <div class="grid_3">
            <input type="text" id="endValidityEndDate" name="searchNewSocietyOrganizationsVo.endValidityEndDate" readonly="readonly" class="form-txt" />
        </div>
		<div class='clearLine'></div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">成员数：从</label>
		</div>
		<div class="grid_3 form-left">
			<input type="text" id="query_minPersonNum" name="searchNewSocietyOrganizationsVo.minPersonNum" maxlength="20" class="form-txt" />
	    </div>
	    <div class="grid_1 lable-right">
				<label class="form-lbl">&nbsp;至：</label>
		</div>
        <div class="grid_3">
            <input type="text" id="query_maxPersonNum" name="searchNewSocietyOrganizationsVo.maxPersonNum" maxlength="20" class="form-txt" />
        </div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">党员人数：从</label>
		</div>
		<div class="grid_3 form-left">
			<input type="text" id="query_minPartyNum" name="searchNewSocietyOrganizationsVo.minPartyNum" maxlength="20" class="form-txt" />
	    </div>
	    <div class="grid_1 lable-right">
				<label class="form-lbl">&nbsp;至：</label>
		</div>
        <div class="grid_3">
            <input type="text" id="query_maxPartyNum" name="searchNewSocietyOrganizationsVo.maxPartyNum" maxlength="20" class="form-txt" />
        </div>
		<div class='clearLine'></div>
	</div>
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	
	//将对象类型赋值给隐藏的input
	$('#objectType').val($("#_locationType_").val());
	
	var placeName = $("#searchByPlaceName").val();
	if(placeName=="请输入社会组织名称"){
		$("#query_unitName").val("");
	}else{
		$("#query_unitName").val(placeName);
	}

	$('#startValidityStartDate').datePicker(
			{
				yearRange : '1900:2030',
				dateFormat : 'yy-mm-dd',
				maxDate : '+0d'
			});

	$('#endValidityStartDate').datePicker(
			{
			});

	$('#startValidityEndDate').datePicker(
			{
				yearRange : '1900:2030',
				dateFormat : 'yy-mm-dd',
				maxDate : '+0d'

			});

	$('#endValidityEndDate').datePicker(
			{
			});
	

	$("#showMoreBtn").toggle(
		function(){
			$("#newSocietyOrganizationsDialog").dialog( "option" , {height:450});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#newSocietyOrganizationsDialog").dialog( "option" , {height:320} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);

});
</script>