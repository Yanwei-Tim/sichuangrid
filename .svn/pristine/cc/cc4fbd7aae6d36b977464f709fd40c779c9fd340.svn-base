<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="keyType"
	value="<s:property value="#parameters.keyType"/>" />
<div id="search-condition-form" class="container container_24">
	<form id="searchEnterpriseForm">
	<input id="keyType" type="hidden" name="enterpriseSearchCondition.keyType" value="<s:property value='#parameters.keyType'/>" />
	<div class="grid_5 lable-right">
		<label class="form-lbl">企业名称：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionName" name="enterpriseSearchCondition.name" style="width:99%" class="form-txt"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">企业地址：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="conditionAddress" name="enterpriseSearchCondition.address" style="width:99%" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">法人代表：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionLegalPerson" name="enterpriseSearchCondition.legalPerson" class="form-txt"/>
	</div>
	<s:if test="'safetyProductionKey'.equals(keyType)">
		<div class="grid_5 lable-right">
			<label class="form-lbl">企业类型：</label>
		</div>
		<div class="grid_7">
			<select id="conditionTypeId" name="enterpriseSearchCondition.typeId" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE" />
			</select>
		</div>
	</s:if>
	<div class='clearLine'>&nbsp;</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">工商执照号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionBusinessLicense" name="enterpriseSearchCondition.businessLicense" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">所属行业：</label>
	</div>
	<div class="grid_7">
		<select id="conditionIndustryId" name="enterpriseSearchCondition.industryId" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BUSINESSTYPE" defaultValue=""/>
		</select>
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
		<label class="form-lbl">是否危化企业：</label>
	</div>
	<div class="grid_7">
		<select id="conditionIsRiskEnterprise" name="enterpriseSearchCondition.isRiskEnterprise" class="form-txt">
			<option value="" >请选择</option>
			<option value="true">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
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
	
	<div class='clearLine'>&nbsp;</div>
	<div class="showMoreBtnBox"><h1 class="searchArraw" id="showMoreBtn">显示更多条件</h1></div>
	<div id="showMoreCtt" style="display: none">
	<div class="grid_5 lable-right">
		<label class="form-lbl">面积(m<sup>2</sup>)：</label>
	</div>
	<div class="grid_3">
		<input type="text" id="conditionMinArea" name="enterpriseSearchCondition.minArea"maxlength="11" class="form-txt"/>
	</div>
	<div class="grid_1">&nbsp;&nbsp;到</div>
	<div class="grid_3">
		<input type="text" id="conditionMaxArea" name="enterpriseSearchCondition.maxArea" maxlength="11" class="form-txt"/>
	</div>
   	
	<div class="grid_5 lable-right">
		<label class="form-lbl">企业联系电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionEnterpriseTelephone" name="enterpriseSearchCondition.enterpriseTelephon" maxlength="15" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">党员数：</label>
	</div>
	<div class="grid_3">
		<input type="text" id="conditionMinPartyMemberAmount" name="enterpriseSearchCondition.minPartyMemberAmount" maxlength="11" class="form-txt"/>
	</div>
	<div class="grid_1">&nbsp;&nbsp;到</div>
	<div class="grid_3">
		<input type="text" id="conditionMaxPartyMemberAmount" name="enterpriseSearchCondition.maxPartyMemberAmount" maxlength="11" class="form-txt"/>
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">企业传真号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionFax" name="enterpriseSearchCondition.fax" maxlength="11" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">员工数：</label>
	</div>
	<div class="grid_3">
		<input type="text" id="conditionMinEmployeeAmount" name="enterpriseSearchCondition.minEmployeeAmount" maxlength="11" class="form-txt"/>
	</div>
	<div class="grid_1">&nbsp;&nbsp;到</div>
	<div class="grid_3">
		<input type="text" id="conditionMaxEmployeeAmount" name="enterpriseSearchCondition.maxEmployeeAmount" maxlength="11" class="form-txt"/>
	</div>

	<div class="grid_5 lable-right">
		<label class="form-lbl">法人手机号码：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionMobileNumber" name="enterpriseSearchCondition.mobileNumber" maxlength="11" class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">注册资金(万元)：</label>
	</div>
	<div class="grid_3">
		<input type="text" id="conditionMinRegisteredCapital" name="enterpriseSearchCondition.minRegisteredCapital" maxlength="11" class="form-txt"/>
	</div>
	<div class="grid_1">&nbsp;&nbsp;到</div>
	<div class="grid_3">
		<input type="text" id="conditionMaxRegisteredCapital" name="enterpriseSearchCondition.maxRegisteredCapital" maxlength="11" class="form-txt"/>
	</div>
	
	<div class="grid_5 lable-right">
		<label class="form-lbl">法人联系电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionTelephone" name="enterpriseSearchCondition.telephone" maxlength="15" class="form-txt"/>
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
		<input type="text" id="conditionHiddenRectification" name="enterpriseSearchCondition.hiddenRectification" style="width:99%" class="form-txt"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var placeName = $("#searchByPlaceName").val();
	if(placeName=="请输入企业名称" || placeName=="请输入场所名称"){
		$("#conditionName").val("");
	}else{
		$("#conditionName").val(placeName);
	}
	if($("#keyType").val()=="safetyProductionKey"){
		$("#search-condition-form").attr("title","安全生产重点信息查询");
	}else{
		$("#search-condition-form").attr("title","规上企业信息查询");
	}

	$("#showMoreBtn").toggle(
		function(){
			$("#safetyProductionKeyDialog").dialog( "option" , {height:460});
			$("#showMoreCtt").show();
			$(this).addClass("cur").text("隐藏更多条件");
		},
		function(){
			$("#safetyProductionKeyDialog").dialog( "option" , {height:320} );
			$("#showMoreCtt").hide();
			$(this).removeClass("cur").text("显示更多条件");
		}
	);
	
	 
	//页面加载的时候 改变名字  后台 该类型是 boolean
	$("#conditionIsRiskEnterprise").attr("name",'enterpriseSearchCondition.isRiskEnterprise1');
	$("#conditionIsRiskEnterprise").change(function (event){
		var riskEnterprisevalue= $("#conditionIsRiskEnterprise").val();
		if(riskEnterprisevalue==""){
			$("#conditionIsRiskEnterprise").attr("name",'enterpriseSearchCondition.isRiskEnterprise1');
		}else{
			$("#conditionIsRiskEnterprise").attr("name",'enterpriseSearchCondition.isRiskEnterprise');
		}
	});
	
	
	
	
	
	
});
</script>