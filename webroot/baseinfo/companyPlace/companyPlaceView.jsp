<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<!-- include pages -->
<%@ include file="formPage/publicForm.jsp"%>
<%@ include file="formPage/companyForm.jsp"%>
<%@ include file="formPage/placeForm.jsp"%>
<div class="container container_24 cf" style="position:relative;">
<div style="width:145px;height:176px; position:absolute;top:20px;left:10px; border:1px solid #ccc;overflow:hidden;">
<c:choose>
	<c:when test='${null!=companyPlace.imgUrl && not empty companyPlace.imgUrl }'>
		<img id="img" name="companyPlace.imgUrl" src="${path }${companyPlace.imgUrl}?111" width="148px"/>
	</c:when>
	<c:otherwise>
		<img id="img" name="companyPlace.imgUrl" src="${resource_path }/resource/images/locationHead.png?111" width="148px"/>
	</c:otherwise>
</c:choose>
</div>
	<form id="maintainForm" method="post cf"	action="" style="position:absolute;top:20px;right:5px;width:600px;">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modul" id="modul" value="${modul}"/>
		<input type="hidden" id="isBusinessInfo" name="isBusinessInfo" value="${isBusinessInfo}"/>
		<c:if test='${mode=="add"}'>
			<pop:token/>
		</c:if>
		<input id="organizationId"	type="hidden" name="companyPlace.org.id" value="${companyPlace.org.id }" />
		<input id="companyPlace.baseId"	type="hidden" name="companyPlace.baseId" value="${companyPlace.id }" />
		<input id="companyPlace.cid"	type="hidden" name="companyPlace.cid" value="${companyPlace.cid }" />
		<input id="classifiCationEnId" type="hidden" name="companyPlace.classifiCationEn" value="${companyPlace.classifiCationEn }">
		<input id="_imgUrl" name="companyPlace.imgUrl" type="hidden" value="${companyPlace.imgUrl}"/>
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text"  id="orgName"  name=""  style="width: 99%"  readonly value="${companyPlace.org.orgName}" class="form-txt" />
		</div>
	<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">名称：</label>
		</div>
		<div class="grid_12">
			<input type="text" name="companyPlace.name" title="${companyPlace.name }" id="companyPlaceName" maxlength="50" style="width: 99%" value='${companyPlace.name }' class="form-txt {required:true, messages:{required:'请输入名称'}}" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">关注程度：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlace.attentionextent.id" id="companyPlaceAttentionextent" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${companyPlace.attentionextent.id}"></pop:OptionTag>
			</select>
		</div>
	<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="companyPlace.address" title="${companyPlace.address }" id="companPlaceAddress" maxlength="50" style="width: 99%" value='${companyPlace.address }' class="form-txt {required:true,addressStr:true, messages:{required:'请输入地址',addressStr:'请正确填写地址'}}"  />
		</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">类型：</label>
		</div>
		<div class="grid_4">
			<input name="companyPlace.type.id" id="companyPalceType_realy" value="${companyPlace.type.id}" type="hidden">
			<select name="companyPalceTypeId"  id="companyPalceTypeId" class="form-txt {required:true, messages:{required:'请选择类型'}}" onchange="changeType()" >
				<pop:PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" id="companyPalceTypeId" reletionId="companyPalceClassifiCationId" 
		  		reletionName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" defaultValue="${companyPlace.type.id}"/>
			</select>
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req">*</em><label class="form-lb1">分类：</label>
		</div>
		<div class="grid_6">
			<input name="companyPlace.classifiCation.id" id="companyPalceClassifiCation_realy" value="${companyPlace.type.id}" type="hidden">
			<select name="companyPalceClassifiCationId" id="companyPalceClassifiCationId" class="form-txt {required:true, messages:{required:'请选择分类'}}" onchange="changeClassifiCation()" >
				<pop:PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" id="companyPalceClassifiCationId" reletionId="companyPalceCategoryId" 
		  		reletionName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" defaultValue="${companyPlace.classifiCation.id}"/>
			</select>
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req">*</em><label class="form-lb1">类别：</label>
		</div>
		<div class="grid_6">
			<select name="companyPlace.category.id" id="companyPalceCategoryId" class="form-txt {required:true, messages:{required:'请选择类别'}}" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" defaultValue="${companyPlace.category.id}"></pop:OptionTag>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div id="fromDiv" ></div>
		
		<div class='clearLine'>&nbsp;</div>
		<div id="EmphasisDiv" style="display: none;">		
		<div  style="height: 45px;">
			<div class="grid_4 lable-right">
				<input type="hidden" id="isEmphasisId" value='${companyPlace.isEmphasis }' />
				<label class="form-lb1">取消关注原因：</label>
			</div>
			<div class="grid_8">
				<input type="text" name="companyPlace.isEmphasisReason" id="isEmphasisReason" maxlength="50" style="width: 99%" value='${companyPlace.isEmphasisReason }' class="form-txt" />
			</div>
			<div class="grid_4 lable-right">
				<em class="form-req"></em>
				<label class="form-lb1">取消关注时间：</label>
			</div>
			<div class="grid_8">
				<input type="text" name="companyPlace.isEmphasisDate" id="isEmphasisDate" maxlength="50" style="width: 99%" value="<fmt:formatDate value="${companyPlace.isEmphasisDate}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" />
			</div>
		</div>
		</div>
	</form>
	<div id="remarksFromDiv" style="display: none;">		
		<div class="grid_4 lable-right" style="height: 120px;">
			<em class="form-req"></em>
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_18">
			<textarea rows="5"  name="companyPlace.remarks" class="form-txt" style="width: 111%">${companyPlace.remarks}</textarea>
		</div>
	</div>
	<input id="modulTypeValue" type="hidden" value="<s:property value="@com.tianque.baseInfo.companyPlace.constacts.ModulTypes@getTypeByName(modulType)"/>">
	<input id="modulClassValue" type="hidden" value="<s:property value="@com.tianque.baseInfo.companyPlace.constacts.ModulTypes@getTypeByName(modul)"/>">
	<input id="modulType_dlg" type="hidden" value="${modulType }"/>
	<input id="modulKey_dlg" type="hidden" value="${modulKey }">
</div>
<script>
var companyPlaceClassId;
var companyPlaceTypeName;
var titleName;
var internalId;
var name= $("#modul").val();
var modulType_dlg = $("#modulType_dlg").val();
var modulKey_dlg = $("#modulKey_dlg").val();
<pop:formatterProperty name="typeClass" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" />
/**获取字典项ID**/
function getTypekeyByValue(value){
	 var resultId;
	 for(var key in  typeClassData ){
		 if(typeClassData[key]==value){
			 resultId=key;
			break;
		 }
	 }
	 return resultId;
}
function getTypeValueByKey(keyValue){
	 var resultValue;
	 for(var key in  typeClassData ){
		 if(key == keyValue){
			 resultValue=typeClassData[key];
			break;
		 }
	 }
	 return resultValue;
}
<pop:formatterProperty name="classificationClass" domainName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" />
function getClasskeyByValue(value){
	 var resultId;
	 for(var key in  classificationClassData ){
		 if(classificationClassData[key]==value){
			 resultId=key;
			break;
		 }
	 }
	 return resultId;
}
function getClassValueByKey(keyValue){
	 var resultValue;
	 for(var key in  classificationClassData ){
		 if(key == keyValue){
			 resultValue=classificationClassData[key];
			break;
		 }
	 }
	 return resultValue;
}


if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}
/////////////////////////
function disableDivById(divId){
	$("#"+divId+" :input").attr('disabled', "disabled");  
	$("#"+divId+"").hide();
}
function enableDivById(divId){
	$("#"+divId+" :input").removeAttr('disabled');  
	$("#"+divId+"").show();
}
//设置modulKey
function setModulKey(keyValue){
}

//类型选择改变
function changeType(){
	$("#companyPalceType_realy").val($("#companyPalceTypeId").val());
}

//分类选择改变
function changeClassifiCation(){
	var keyValue = $("#companyPalceClassifiCationId").val();
	$("#companyPalceClassifiCation_realy").val(keyValue);
	if(keyValue == '' ){
		$("#fromDiv").empty();
		return ;
	}else{
	}
	var typeValue = getClassValueByKey(keyValue);
	var modulKey = "";
	if(typeValue == '公共活动场所' || typeValue == '交通场所' ){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#executiveAreaFromDiv").html());
	}else if(typeValue == '娱乐场所'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#executiveAreaFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '商贸场所'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#privateTradeFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '网吧'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#InternetInfoFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		$("#fromDiv").append($("#InternetSeniorityFormDiv").html());
		setHasLicense();
	}else if(typeValue == '住宿服务场所'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '餐饮服务场所'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '旅游场所'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
	}else if(typeValue == '施工场所'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#constructionFormDiv").html());
		setDatePicker();
	}else if(typeValue == '场所-其他'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '党政机关'){
		$("#fromDiv").empty().append($("#base_form_pf_id_div").html());
	}else if(typeValue == '学校'){
		$("#fromDiv").empty().append($("#eduFormDiv").html());
		$("#fromDiv").append($("#base_form_pf_id_div").html());
		$("#fromDiv").append($("#eduFormInfoDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '医院'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#hospitalFormDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '危化品存放单位'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#dangerousFormDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setHasLicense();
	}else if(typeValue == '单位-其他'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#otherCompanyFormDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		setDatePicker();
		setHasLicense();
	}else if(typeValue == '寄递物流点'){
		//添加寄递物流点的页面内容
		$("#fromDiv").empty().append($("#jiDiLogisticsFormDiv").html());
	}else{
	
	}
	$("#classifiCationEnId").val(modulKey);
	$("#fromDiv").append($("#remarksFromDiv").html());
}

function setHasLicense(){
	//$("#maintainForm input[name='companyPlace.hasLicense']").attr("id","hasLicenseValue_id");
	$("#maintainForm input[name='companyPlace.hasLicense']").attr("id","hasLicense_id");
	//$("#hasLicenseValue_id").val("0");
	var licenseValue = $("#hasLicense_id").val();
	if(licenseValue == 1){
		 $("#hasLicense_id").attr("checked","checked");
	}
	$("#hasLicense_id").click(function(){
		var licenseValue = $("#hasLicense_id").val();
			if(licenseValue == '' || licenseValue == 0){
				 $("#hasLicense_id").val(1);
			}else{
				 $("#hasLicense_id").val(0);
			}
		});
}

function setDatePicker(){
	$("#maintainForm input[name='companyPlace.begintime']").attr("id","beginTime");
	$("#maintainForm input[name='companyPlace.endtime']").attr("id","endTime");
	
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
function getBusinessInfos(){
	var isBusinessInfo= $("#isBusinessInfo").val();
	if('0'==isBusinessInfo){
		$("#businessInfos").remove()
	}
	
}
$(document).ready(function(){
		getBusinessInfos();
		if($("#isEmphasisId").val() == '1' || $("#isEmphasisId").val() == 1 ){
			$("#EmphasisDiv").show();
		}
	
		var type = $("#companyPalceTypeId").val() ;
		var classifiCation = $("#companyPalceClassifiCationId").val();
		var category = $("#companyPalceCategoryId").val() ;
		$("#companyPalceTypeId").val(type);
		$("#companyPalceTypeId").change();
		$("#companyPalceClassifiCationId").val(classifiCation);
		$("#companyPalceClassifiCationId").change();
		$("#companyPalceCategoryId").val(category);

		$("#companyPalceType_realy").val(type);
		$("#companyPalceClassifiCation_realy").val(classifiCation);
		changeClassifiCation(classifiCation);
		$("#maintainForm").find("input,select,textarea").attr("disabled","disabled");
	
});
</script>