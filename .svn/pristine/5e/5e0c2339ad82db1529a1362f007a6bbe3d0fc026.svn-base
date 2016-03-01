<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_DEPENDENT">
	<div id="" >
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="currentAddress" name="population.currentAddress"  maxlength="50"  value="${population.currentAddress }" style="width: 99%" class="form-txt {required:true,messages:{required:'请输入常住地址'}}"/>
		</div>
	</div>
</pop:GlobalSettingTag>
<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_DEPENDENT" condition="notEq">
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1">有无固定地：</label>
	</div>
	<div class="grid_4">
		<select id="populationHasHouseInfo" name="population.isHaveHouse" class="form-txt"
			<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">disabled</pop:GlobalSettingTag>
		>
			<option value="true" <s:if test="population.isHaveHouse">selected</s:if>>有</option>
			<option value="false" <s:if test="!population.isHaveHouse">selected</s:if>>无</option>
		</select>
	</div>
	<div id="houseCodeDiv1">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
				<label class="form-lb1">房屋编号：</label>
			</div>
			<div class="grid_10">
				<input type="text" id="houseCode"  name="population.houseCode" value="${population.houseCode}"  maxlength="50" style="width: 99%"
					class="form-txt {required:true,isCodeValidate:true,existhouseCode:true,messages:{required:'请输入房屋编号',isCodeValidate:'不能输入非法字符',existhouseCode:'房屋编号不存在'}}"
				<s:if test='!@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION.equals(populationType)&&!@com.tianque.service.util.PopulationType@FLOATING_POPULATION.equals(populationType)&&!@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF.equals(populationType)'>
	  				<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
				</s:if>
			/>
		</div>
	</div>
	<div id="haveNotHouse" style="display: none;">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">无房原因：</label>
		</div>
		<div class="grid_10">
			<input type="text" name="population.noHouseReason" value="${population.noHouseReason}"  maxlength="50" style="width: 99%"
					class="form-txt {required:true,messages:{required:'请输入无房原因'}}" id="noHouseReason"
			<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
			/>
		</div>
	</div>
	<div id="houseCodeDiv2">
		<div class="grid_4 lable-right">
		<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_3">
			<select name="population.currentAddressType.id" id="currentAddressType" class="form-txt " disabled="disabled"
			>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@CURRENT_ADDRESS_TYPE" defaultValue="${population.currentAddressType.id}" showInternalId="true"  notNull="true" />
			</select>
		</div>
		<div id="building" >
			<input type="hidden" name="population.houseId" id="houseId" value="${population.houseId}"/>
			<div class="grid_3">
				<input type="text" readonly id="community"  name="population.community"  maxlength="20"  value="${population.community}" style="width: 93%" class="form-txt " />
			</div>
			<div class="grid_2">
		   		 <label class="form-lbl">小区</label>
			</div>
			<div class="grid_2">
				<input type="text" readonly id="block"  name="population.block"  maxlength="8"  value="${population.block}" style="width: 93%"	class="form-txt" />
			</div>
			<div class="grid_1">
			     <label class="form-lbl">幢</label>
			</div>
			<div class="grid_2">
				 <input type="text" readonly id="unit"  name="population.unit"  maxlength="6"  value="${population.unit}" style="width: 93%"	class="form-txt" />
			</div>
			<div class="grid_2">
			 	 <label class="form-lbl">单元</label>
			</div>
			<div class="grid_2">
			 	<input type="text" readonly id="room"  name="population.room"  maxlength="10"  value="${population.room}" style="width: 93%"	class="form-txt" />
			</div>
			<div class="grid_1">
			  	<label class="form-lbl">室</label>
			</div>
		</div>
	</div>
	<div id="otherAddress" class="grid_15" style="display:none">
		<s:if test="'add'.equals(mode) && population.houseId!=null">
			<input type="text" readonly id="currentAddress" name="population.currentAddress"  maxlength="50"  value="${population.currentAddress }" readonly style="width: 99%" class="form-txt {required:true,messages:{required:'请输入常住地址'}}"/>
		</s:if>
		<s:else>
			<input type="text" readonly id="currentAddress" name="population.currentAddress"  maxlength="50"  value="${population.currentAddress }" style="width: 99%"
					class="form-txt"
				<s:if test='!@com.tianque.service.util.PopulationType@UNSETTLED_POPULATION.equals(populationType)&&!@com.tianque.service.util.PopulationType@FLOATING_POPULATION.equals(populationType)&&!@com.tianque.service.util.PopulationType@HOUSEHOLD_STAFF.equals(populationType)'>
					<pop:GlobalSettingTag key="@com.tianque.core.globalSetting.util.GlobalSetting@BUSINESS_DEPENDENT_ACTUAL_POPULATION" value="@com.tianque.core.globalSetting.util.GlobalSetting@NOT_ADD_POPULATION">readonly</pop:GlobalSettingTag>
				</s:if>
			/>
		</s:else>
	</div>
</pop:GlobalSettingTag>
<script type="text/javascript">
$(document).ready(function(){
	function procCurrentAddressType(data) {
		$("#houseId").val(data.id);
		$("#currentAddressType").val(data.addressType.id);
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			$("#building").show();
			$("#otherAddress").hide();
			$("#community").val(data.community);
			$("#block").val(data.block);
			$("#unit").val(data.unit);
			$("#room").val(data.room);
		} else {
			$("#building").hide();
			$("#otherAddress").show();
			$("#currentAddress").val(data.address);
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
		}
	}
	function renderHouseInfoForHouseCode(data){
		$("#houseId").val(data.houseId);
		$("#houseCode").val(data.houseCode);
		
		$("#currentAddressType").val(data.addressType.id);
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>) {
			$("#building").show();
			$("#otherAddress").val("");
			$("#otherAddress").hide();
			$("#community").val(data.community);
			$("#block").val(data.block);
			$("#unit").val(data.unit);
			$("#room").val(data.room);
		} else {
			$("#building").hide();
			$("#otherAddress").show();
			$("#currentAddress").val(data.address);
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
		}
	}

	function chooseCurrentAddressType(){
	
		if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
			$("#building").show();
			$("#otherAddress").hide();
		}else if($("#currentAddressType").find("option:selected").attr("internalId")==<s:property value="@com.tianque.domain.property.CurrentAddressType@OTHER"/>){
			$("#building").hide();
			$("#otherAddress").show();
		}
	
		
	}
	function populationHasHouseInfoChanged(){
		var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
		if($("#populationHasHouseInfo").val()=="false"){
			$("#haveNotHouse").show();
			$("#houseCode").val("");
			$("#community").val("");
			$("#block").val("");
			$("#unit").val("");
			$("#room").val("");
			$("#currentAddress").val("");
			$("#houseCodeDiv1,#houseCodeDiv2,#otherAddress").hide();
		}else{
			$("#building").show();
			$("#haveNotHouse,#otherAddress").hide();
			$("#noHouseReason").val("");
			$("#houseCodeDiv1,#houseCodeDiv2").show();
		}
	}
	$("#populationHasHouseInfo").change(populationHasHouseInfoChanged);
	<s:if test="!population.isHaveHouse">
	populationHasHouseInfoChanged();
	</s:if>
	$("#currentAddressType").change(chooseCurrentAddressType);
	
	function getHouseOrgId(){
		if($("#populationOrgId").val()!= undefined && $("#populationOrgId").val()){
			return $("#populationOrgId").val();
		}else{
			return getCurrentOrgId();
		}
	}
	jQuery.validator.addMethod("existhouseCode", function(value, element){
		var flag=true;
		if(value==null||value==undefined||value=="" ){return true};
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/actualHouseManage/getHouseInfoByHouseCode.action",
		   	data:{
				"searchHouseInfoVo.houseCode":$('#houseCode').val(),
				"searchHouseInfoVo.organization.id":getHouseOrgId()
	        },
			success:function(data){
				if(null != data) {
					flag= true;
				} else {
					flag= false;
				}
			}
		});
		return flag;
		
	});
	
	$("#houseCode").houseAutoComplete({
		searchLevel:"houseCode",
		select:function(event,ui){
			renderHouseInfoForHouseCode(ui.item);
		}
	});
	chooseCurrentAddressType();
});
</script>