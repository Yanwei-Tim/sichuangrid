<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="viewCompanyPlace">
<div style="display:none;" id="dynamicFormEls">
    <#include "publicForm.ftl">
    <#include "placeForm.ftl">
    <#include "companyForm.ftl">
</div>
<input id="organizationId"	type="hidden" name="companyPlace.org.id" value="${population.org.id! }" />
<input id="companyPlacebaseId"	type="hidden" name="companyPlace.baseId" value="${population.id! }" />
<input type="hidden" name="companyPlace.id" value="${population.id! }" />
<input id="companyPlace.cid"	type="hidden" name="companyPlace.cid" value="${population.cid! }" />
<div class="container container_24">
<div style="width:145px;height:176px; position:absolute;top:20px;left:25px; border:1px solid #ccc;">
<input id="_imgUrl" type="hidden" name="population.imgUrl" value="${(population.imgUrl)!}"/>
	<@s.if test='null!=population.imgUrl && !"".equals(population.imgUrl)'>
		<img id="popuUrl" name="population.imgUrl" src="${path! }${(population.imgUrl)!}" width="148px"  />
	</@s.if>
	<@s.else>
		<img id="img" name="population.imgUrl" src="${path! }/resource/images/locationHead.png" width="148px"  />
	</@s.else>
</div>
	<div id="maintainFormDiv" class="cf" style="position:absolute;top:20px;right:25px;width:600px;" >
		<input id="classifiCationEnId" type="hidden"  name="companyPlace.classifiCationEn" value="${population.classifiCationEn! }">
		<div class="grid_4 lable-right">
		   	<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text"  id="orgName"  style="width: 99%"  readonly value="${(population.organization.orgName)!}" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">名称：</label>
		</div>
		<div class="grid_12">
			<div class="ui-widget">	
				<input type="text" name="companyPlace.name" id="companyPlaceName" maxlength="50" style="width: 99%" title='${population.name! }'  value='${population.name! }' class="form-txt {required:true,isLawful:true,ishasSameName:true,exculdeParticalChar:true,minlength:2,maxlength:50, messages:{required:'请输入名称',isLawful:'您输入了非法脚本，请重新输入！',ishasSameName:'网格内已经存在该名称的单位场所',exculdeParticalChar:'不能输入非法字符',minlength:$.format('名称至少需要输入{0}个字符'),maxlength:$.format('名称最多需要输入{0}个字符')}}" />
			</div>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">关注程度：</label>
		</div>
		<div class="grid_4">
			<select name="companyPlace.attentionextent.id" id="companyPlaceAttentionextent" class="form-txt" >
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${(population.attentionextent.id)!}"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">地址：</label>
		</div>
		<div class="grid_20">
			<input type="text" name="companyPlace.address" id="companPlaceAddress" maxlength="50" style="width: 99%" title='${population.address! }'  value='${population.address! }' class="form-txt {required:true,isLawful:true,addressStr:true,minlength:2,maxlength:50, messages:{required:'请输入地址',isLawful:'您输入了非法脚本，请重新输入！',addressStr:'请正确填写地址',minlength:$.format('地址至少需要输入{0}个字符'),maxlength:$.format('地址最多需要输入{0}个字符')}}"  />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">类型：</label>
		</div>
		<div class="grid_4">
			<input name="companyPlace.type.id" id="companyPalceType_realy" value="${(population.type.id)!}" type="hidden">
			<select name="companyPalceTypeId"  id="companyPalceTypeId" class="form-txt {required:true, messages:{required:'请选择类型'}}">
				<@pop.PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_TYPE" id="companyPalceTypeId" reletionId="companyPalceClassifiCationId" 
		  		reletionName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" defaultValue="${(population.type.id)!}"/>
			</select>
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req">*</em><label class="form-lb1">分类：</label>
		</div>
		<div class="grid_6">
			<input name="companyPlace.classifiCation.id" id="companyPalceClassifiCation_realy" value="${(companyPlace.classifiCation.id)!}" type="hidden">
			<select name="companyPalceClassifiCationId" id="companyPalceClassifiCationId" class="form-txt {required:true, messages:{required:'请选择分类'}}" >
				<@pop.PropertyDictLevelSelectTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CLASSIFICATION" id="companyPalceClassifiCationId" reletionId="companyPalceCategoryId" 
		  		reletionName="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" defaultValue="${(population.classifiCation.id)!}"/>
			</select>
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req">*</em><label class="form-lb1">类别：</label>
		</div>
		<div class="grid_6">
		    <input name="companyPlace.category.id" id="companyPalceCategory_realy" value="${(population.category.id)!}" type="hidden">
			<select name="companyPalceCategoryId" onchange="changeCategory()" id="companyPalceCategoryId" class="form-txt {required:true, messages:{required:'请选择类别'}}" >
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@COMPANY_PLACE_CATEGORY" defaultValue="${(population.category.id)!}"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="fromDiv"></div>
		<div class='clearLine'>&nbsp;</div>
		<div id="remarksFromDiv">		
			<div class="grid_4 lable-right">
				<em class="form-req"></em>
				<label class="form-lb1">备注：</label>
			</div>
			<div class="grid_18">
				<textarea rows="5" maxlength="150" name="companyPlace.remarks" class="form-txt {isLawful:true,exculdeParticalChar:true,minlength:2,maxlength:150, messages:{isLawful:'您输入了非法脚本，请重新输入！',exculdeParticalChar:'不能输入非法字符',minlength:$.format('备注至少需要输入{0}个字符'),maxlength:$.format('备注最多需要输入{0}个字符')}}" style="width: 111%" style="width: 111%">${population.remarks!}</textarea>
			</div>
		</div>
		</div>
</div>
</div>
<script type="text/javascript">

if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){//添加随机数保证IE下的图片查看没问题
		$("#popuUrl").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	}

initForm();
changeselect();

function changeCategory(){
    var keyValue = $("#companyPalceCategoryId").val();
	$("#companyPalceCategory_realy").val(keyValue);
}

function initForm(){
	var moduleType = $("#classifiCationEnId").val();
	if(moduleType == 'NEWPUBLICPLACE' || moduleType == 'TRAFFICPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#executiveAreaFromDiv").html());
		removeItem();
	}else if(moduleType == 'ENTERTAINMENTPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#executiveAreaFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'TRADEPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#privateTradeFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'NEWINTERNETBAR'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#InternetInfoFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		$("#fromDiv").append($("#InternetSeniorityFormDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'ACCOMMODATIONSERVICESPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'NEWFOODSERVICESPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'TRAVELINGPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		removeItem();
	}else if(moduleType == 'CONSTRUCTIONPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#constructionFormDiv").html());
		removeItem();
		setDatePicker();
	}else if(moduleType == 'OTHERPLACE'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'PARTYGOVERNMENTORGANCOMPANY'){
		$("#fromDiv").empty().append($("#base_form_pf_id_div").html());
		removeItem();
	}else if(moduleType == 'NEWSCHOOLS'){
		$("#fromDiv").empty().append($("#eduFormDiv").html());
		$("#fromDiv").append($("#base_form_pf_id_div").html());
		$("#fromDiv").append($("#eduFormInfoDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'NEWHOSPITAL'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#hospitalFormDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'NEWDANGEROUSCHEMICALSUNIT'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#dangerousFormDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setHasLicense();
	}else if(moduleType == 'OTHERCOMPANY'){
		$("#fromDiv").empty().append($("#baseFromDiv").html());
		$("#fromDiv").append($("#otherCompanyFormDiv").html());
		$("#fromDiv").append($("#scaleFromDiv").html());
		$("#fromDiv").append($("#licenseFromDiv").html());
		removeItem();
		setDatePicker();
		setHasLicense();
	}
}


function changeselect(){
	var vcompanyPalceType=$("#companyPalceType_realy").val();
	var companyPalceClassifiCation=$("#companyPalceClassifiCation_realy").val();
	var companyPalceCategory=$("#companyPalceCategory_realy").val()
	$("#companyPalceTypeId").val(vcompanyPalceType);
	$("#companyPalceTypeId").change();
	$("#companyPalceClassifiCationId").val(companyPalceClassifiCation);
	$("#companyPalceClassifiCationId").change();
	$("#companyPalceCategoryId").val(companyPalceCategory);
}

function removeItem(){
    $("#dynamicFormEls").remove();
}

function setHasLicense(){
	var licenseValue = $("#hasLicense_Realy").val();
	if(licenseValue == '1'||licenseValue==1){
		 $("#hasLicense_checkBox").attr("checked",true);
	}
}
function setDatePicker(){
	$("#maintainForm input[name='companyPlace.begintime']").attr("id","beginTime");
	$("#maintainForm input[name='companyPlace.endtime']").attr("id","endTime");

	$('#beginTime').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	$('#endTime').datePicker({
		yearRange : '1900:2060',
		dateFormat : 'yy-mm-dd',
		minDate : '+1d'
	});
}

$(document).ready(function(){
 $("#viewCompanyPlace").find("input,select,textarea").attr("disabled","disabled");
});
</script>