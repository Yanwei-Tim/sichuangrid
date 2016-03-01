<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div class="container container_24">
	<form id="groupFamilyForm" method="post" action="/baseinfo/familyInfo/proccessGroupFamily.action">
	<pop:token />
		<input type="hidden" name="groupFamily.id" value="${population.groupFamily.id}"/>
		<input type="hidden" name="population.actualPopulationType" value="${population.actualPopulationType}"/>
		<input type="hidden" name="population.orgInternalCode" value="${population.orgInternalCode}"/>
		<input type="hidden" name="population.id" value="${population.id}"/>
		<input type="hidden" name="mode" value="${mode}"/>
		
		
		<jsp:include page="/baseinfo/commonPopulation/populationBaseInfo.jsp"/>
		
		<hr width="90%"></hr>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">家庭编号：</label>
		</div>
		<div class="grid_5">
			<input type="text" name="groupFamily.familyAccount" id="_familyAccount"  maxlength="50" 
			value="${population.groupFamily.familyAccount}"  
			onkeyup="javascript:fetchGroupFamily('true');" 
			<s:if test="('edit'==mode)&&(null != population.groupFamily.familyAccount)">
			readonly="readonly" disabled="disabled"
			</s:if>
			class="form-txt dialogtip {maxlength:50,messages:{maxlength:$.format('家庭编号最多需要输入{0}个字符')}}" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">与家长关系：</label>
		</div>
		<div class="grid_5">
			<select name="groupFamily.familyRelation.id" onchange="javascript:changeRelation();"  
				<s:if test="null == population.groupFamily.familyAccount">
				disabled="disabled"
				</s:if>
				id="_familyRelation_id" class="form-select {inputedFamilyAccount:true,reduplicateFamilyMaster:true, messages:{inputedFamilyAccount:'请选择与家长关系',reduplicateFamilyMaster:'该家庭编号已存在家长，请选择其他关系'}}" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_MASTER" defaultValue="${population.groupFamily.familyRelation.id}" />
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">家长证件号：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="_masterCardNo" disabled="disabled"  name="groupFamily.masterCardNo" 
			value="${population.groupFamily.masterCardNo}"
			class="form-txt dialogtip" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">家长姓名：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="_familyMaster" disabled="disabled"  name="groupFamily.familyMaster"
			value="${population.groupFamily.familyMaster}"
			class="form-txt dialogtip" />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">家长手机：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="_masterMobileNumber" maxlength="11" disabled="disabled" name="groupFamily.masterMobileNumber"
			value="${population.groupFamily.masterMobileNumber}"
			class="form-txt {mobile:true,messages:{mobile:'手机号码输入只能是以1开头的11位数字'}}" />
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">家长电话：</label>
		</div>
		<div class="grid_5">
			<input type="text" id="_masterTelephone" maxlength="20" disabled="disabled" name="groupFamily.masterTelephone"
			value="${population.groupFamily.masterTelephone}"
			class="form-txt dialogtip {telephone:true,messages:{telephone:'固定电话不合法，只能输数字和横杠(-)'}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
	</form>
</div>
<script>
$(function(){
	$("#groupFamilyForm").formValidate({
		promptPosition:"bottomLeft",
		submitHandler:function(form) {
			$("input[disabled='disabled']").each(function(ind){
 				$(this).attr("disabled",false);
			});
			$(form).ajaxSubmit({
				success:function(data){
					if(data.groupFamily.id){
			            $.messageBox({message:"家庭信息成功保存！"});
					}
					doAction('<s:property value="#parameters.dailogName[0]"/>',data.id,data);
				}
			});
		},
		rules:{},
		messages:{}
	});
})
var familyFlag=false;
function changeRelation() {
	if(isNotEmptyStr("_familyAccount")) {
		$.ajax({
			async : false,
			url : "${path}/baseinfo/familyInfo/whetherFamilyMaster.action",
			data : {
				"groupFamily.familyRelation.id":$("#_familyRelation_id").val()
			},
			success : function(data) {
				if(data == true || data == "true"){
					if($("#dailogName").val()=='overseaPersonnelMaintanceDialog'){
						if($("#_masterCardNo").val() != $("#_idCardNo_").val() || $("#_familyMaster").val() != $("#englishName").val()){
							$.messageBox({
								message : "该境外人员的与家长关系不能选择为家长 ！",
								level : "error"
							});
						}
					}else{
						familyFlag = true;
						if($("#_masterCardNo").val()==""){
							$("#_masterCardNo").val($("#_idCardNo_").val());
							$("#_familyMaster").val($("#_name_").val());
							$("#_masterMobileNumber").val($("#_mobileNumber_").val());
							$("#_masterTelephone").val($("#_telephone_").val());
							return;
						}
						if($("#_masterCardNo").val() != $("#_idCardNo_").val() || $("#_familyMaster").val() != $("#_name_").val()){
							$.messageBox({
								message : "该家庭编号已经存在家长,与家长关系不能选择家长",
								level : "error"
							});
						}
					}
					
				}else {
					familyFlag = false;
					$("#_masterMobileNumber").attr("disabled",true);
					$("#_masterTelephone").attr("disabled",true);
					fetchGroupFamily(true);
				}
			}
		});
	}
}

function fetchGroupFamily(boo){
	$("#_familyRelation_id").attr("disabled",!isNotEmptyStr("_familyAccount"));
	$.ajax({
		async : false,
		url : '${path}/baseinfo/familyInfo/findGroupFamilyByOrgIdAndFamilyAccount.action',
		data : {
			"orgId":$("#currentOrgId").val(),
			"groupFamily.familyAccount":$("#_familyAccount").val()
		},
		success : function(data) {
			if(data != null && data.familyMaster != null && data.familyMaster != ""){
				$("#_masterCardNo").val(data.masterCardNo);
				$("#_familyMaster").val(data.familyMaster);
				$("#_masterMobileNumber").val(data.masterMobileNumber);
				$("#_masterTelephone").val(data.masterTelephone);
			}else{
				$("#_masterCardNo").val("");
				$("#_familyMaster").val("");
				$("#_masterMobileNumber").val("");
				$("#_masterTelephone").val("");
				if(familyFlag==true){
					$("#_masterCardNo").val($("#_idCardNo_").val());
					$("#_familyMaster").val($("#_name_").val());
				}
			}
		}
	});
}
function isNotEmptyStr(id) {
	return null != $("#"+id).val() && '' != $("#"+id).val();
}

jQuery.validator.addMethod("inputedFamilyAccount", function(value, element){
	if(isNotEmptyStr("_familyAccount")) {
		if(null == value || value.length < 1) {
			return false;
		}else return true;
	} else {
		return true;
	}
});

jQuery.validator.addMethod("reduplicateFamilyMaster", function(value, element){
	var flag = true; <s:if test='!"edit".equals(mode)'>if(value != '${population.groupFamily.familyRelation.id}'){</s:if>
	$.ajax({
		async : false,
		url : "${path}/baseinfo/familyInfo/whetherFamilyMaster.action",
		data : {
			"groupFamily.familyRelation.id": value
		},
		success : function(data) {
			if(data == true || data == "true"){
				if($("#dailogName").val()=='overseaPersonnelMaintanceDialog'){
					if($("#_masterCardNo").val() != $("#_idCardNo_").val() || $("#_familyMaster").val() != $("#englishName").val()){
						flag = false;	
					}
				}else{
					familyFlag = true;
					if($("#_masterCardNo").val()==""){
						$("#_masterCardNo").val($("#_idCardNo_").val());
						$("#_familyMaster").val($("#_name_").val());
						return;
					}
					if($("#_masterCardNo").val() != $("#_idCardNo_").val() || $("#_familyMaster").val() != $("#_name_").val()){
						flag = false;	
						
					}
				}
			}else{
				familyFlag = false;
			}
		}
	});<s:if test='!"edit".equals(mode)'>}</s:if>
	return flag;
});


</script>