<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="maintainForm" method="post"	action="" >
	<input name="companyPlaceVo.org.id" value="${companyPlaceVo.org.id }" type="hidden">
	<input name="modulKey" value="${modulKey }" type="hidden">
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lb1">名称：</label>
		</div>
		<div class="grid_12">
			<input type="text" name="companyPlaceVo.name" id="companyPlaceVoName" maxlength="50" style="width: 99%" value='' class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">关注程度：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceVo.attentionextent.id" id="companyPlaceVoAttentionextent" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue=""></pop:OptionTag>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<em class="form-req"></em><label class="form-lb1">地址：</label>
			</div>
			<div class="grid_12">
				<input type="text" name="companyPlaceVo.address" id="companPlaceAddress" maxlength="50" style="width: 99%" value='' class="form-txt"  />
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lb1">是否关注：</label>
			</div>
			<div class="grid_4">
				<select name="companyPlaceVo.isEmphasis" id="companyPlaceVoisEmphasis" class="form-txt" >
					<option value="0">已关注</option>
					<option value="1">未关注</option>
				</select>
			</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
				<em class="form-req"></em><label class="form-lb1">类型：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlaceVo.type.id"  id="companyPalceTypeId" class="form-txt">
				<pop:PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" id="companyPalceTypeId" reletionId="companyPalceClassifiCationId" 
		  		reletionName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" defaultValue=" "/>
			</select>
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req"></em><label class="form-lb1">分类：</label>
		</div>
		<div class="grid_6">
			<select name="companyPlaceVo.classifiCation.id" id="companyPalceClassifiCationId" class="form-txt">
				<pop:PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" id="companyPalceClassifiCationId" reletionId="companyPalceCategoryId" 
		  		reletionName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" defaultValue=" "/>
			</select>
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req"></em><label class="form-lb1">类别：</label>
		</div>
		<div class="grid_6">
			<select name="companyPlaceVo.category.id" id="companyPalceCategoryId" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" defaultValue=" "></pop:OptionTag>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lb1">负责人：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="companyPlaceVo.userName" id="" maxlength="15" style="width: 99%" value='${companyPlace.userName }' class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="companyPlaceVo.mobileNumber" id="" maxlength="50" style="width: 99%" value='${companyPlace.mobileNumber }' class="form-txt {mobile:true,messages:{mobile:'请输入正确的手机号码！'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lb1">联系固话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="companyPlaceVo.telePhone" id="" maxlength="50" style="width: 99%" value='${companyPlace.telePhone }' class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lb1">传真号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="companyPlaceVo.faxNumber" id="" maxlength="50" style="width: 99%" value='${companyPlace.faxNumber }' class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lb1">组织机构代码：</label>
		</div>
		<div class="grid_8">
		<input type="text" name="companyPlaceVo.orgCode" id="" maxlength="50" style="width: 99%" value='${companyPlace.orgCode }' class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lb1">营业执照号：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="companyPlaceVo.businessLicenseNo" id="" maxlength="50" style="width: 99%" value='' class="form-txt"  />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lb1">企业规模：</label>
		</div>
		<div class="grid_8">
			<select class="form-txt" name="companyPlaceVo.scaleType.id" onchange="changeScaleType(this.value)">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ENTERPRISE_TYPE" defaultValue=""></pop:OptionTag>
		</select>
		</div>
	
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lb1">经济性质：</label>
		</div>
		<div class="grid_8">
			<select class="form-txt" name="companyPlaceVo.economicNature.id" >
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_ECONOMICNATURE" defaultValue=""></pop:OptionTag>
		</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lb1">管理等级：</label>
		</div>
		<div class="grid_8">
			<select name="companyPlaceVo.managementLevel.id"  class="form-txt" >
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" defaultValue=""></pop:OptionTag>
		</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em><label class="form-lb1">消防等级：</label>
		</div>
		<div class="grid_8">
		<select name="companyPlaceVo.fireLevel.id"  class="form-txt" >
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ACTUALCOMPANY_FIREFIGHTINGLEVEL" defaultValue=""></pop:OptionTag>
		</select>
		</div>
	
	
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script>

function setDatePicker(){
	$('#beginTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#endTime").datepicker("option", "minDate",date);
			}
		}
	}); 
	$('#endTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#beginTime").datepicker("option", "maxDate",date);
			}
		}
	});
}
$(document).ready(function(){

	
});
</script>