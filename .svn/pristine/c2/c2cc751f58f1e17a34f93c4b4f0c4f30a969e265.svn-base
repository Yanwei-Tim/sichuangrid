<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style type="text/css">
	.del{
		display: none;
	}

</style>
<jsp:include page="/includes/baseInclude.jsp" />
<div id="dialog-form" class="container container_24">
<form id="maintainFormOfIndustry" method="post" action="${path }/baseinfo/industryDevelopmentManage/maintainIndustryDevelopment.action"  >
<pop:token />
<input type="hidden" name="industryDevelopment.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="industryDevelopment.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="industryDevelopment.id" id="commonServiceInfoId" value="${industryDevelopment.id }"/>

<div id="newVillageDialog" style="height:480px;">
<span style="font-weight: bold; ">种植业</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">粮食作物(亩)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.grainCrops" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据,且小数位最大为2位'}}" value="<fmt:formatNumber value='${industryDevelopment.grainCrops}' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">经济作物(亩)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.economicCrops" id="economicCrops" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据,且小数位最大为2位'}}" value="<fmt:formatNumber value='${industryDevelopment.economicCrops }' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">特色产业种植(亩)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.characteristicPlanting" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据,且小数位最大为2位'}}" value="<fmt:formatNumber value='${industryDevelopment.characteristicPlanting }' pattern='#0.00'/>" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<s:if test="0==isPlaning">
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >适度规模种植户(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.scalePlanting" value="${industryDevelopment.scalePlanting }" id="scalePlanting" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999正整数'}}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >种植户总数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.plantingHouseholdsCount" value="${industryDevelopment.plantingHouseholdsCount }" id="scalePlanting" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999正整数'}}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
</s:if>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold;">养殖业</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">生猪(头)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.pigNum" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.pigNum }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">牛羊(头)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.cattleSheepNum" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.cattleSheepNum }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">小家禽/畜(只)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.poultryNum" value="${industryDevelopment.poultryNum }" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">水产(亩)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.aquaticProductNum" value="<fmt:formatNumber value='${industryDevelopment.aquaticProductNum }' pattern='#0.00'/>" id="" class="form-txt {posNumWiPot:true,max:9999999999.99,messages:{posNumWiPot:'只能输入0-9999999999.99的数据,且小数位最大为2位'}}" maxlength="12" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<s:if test="0==isPlaning">
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >适度规模养殖户(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.scaleBreed" id="scaleBreed" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.scaleBreed }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >养殖户总数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.farmHouseholds" id="scaleBreed" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.farmHouseholds }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
</s:if>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold;">产业化经营</span>
<div class='clearLine'>&nbsp;</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">专业合作组织(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.specialistNum" value="${industryDevelopment.specialistNum }" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">家庭农场(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.familyFarmNum" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.familyFarmNum }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">种养大户(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.plantingNum" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.plantingNum }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">农产品加工企业(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.productProcessing" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.productProcessing }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">县级以上龙头企业(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.countyCorporateChampion" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.countyCorporateChampion }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">产业化经营带动农户数(户)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.industrialOrganization" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.industrialOrganization }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold;">乡村旅游业</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">乡村酒店(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.villaggioBoutiqueHotel" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.villaggioBoutiqueHotel }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">农家乐(个)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.agritainment" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.agritainment }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">带动农户就业人数(人)：</label>
</div>
<div class="grid_6">
	<input type="text" name="industryDevelopment.householdEmployment" id="" class="form-txt {digits:true,max:999999999,messages:{digits:'请输入0-999999999整数'}}" value="${industryDevelopment.householdEmployment }" maxlength="8" <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
 </div>
 <s:if test="!'view'.equals(mode)">
  <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 900px;"/>
 </form>
 </s:if>
 </div>
<script>
	$(document).ready(function(){
		$("#maintainFormOfIndustry").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id){
							 $("#commonServiceInfoId").val(data.id);
							 $.messageBox({message:"产业发展信息保存成功!"});
						}else{
							 $.messageBox({message:data,level:'error'});
						}
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			},
			messages:{
			}
		});
	});
	
</script>