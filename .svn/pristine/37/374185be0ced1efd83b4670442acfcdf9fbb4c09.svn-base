<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/includes/baseInclude.jsp" />
<style type="text/css">
	.select_width{
		width: 156px !important;
	}
	.div_width{
		width: 35px;
	}
	.span_none{
		display: none;
	}
</style>
<div id="dialog-form" class="container container_24">
<form id="maintainFormOfFarmerPerIncomeInfo" method="post" action="${path }/baseinfo/farmerPerIncomeInfoManage/maintainFarmerPerIncomeInfo.action"  >
<pop:token />
<input type="hidden" name="farmerPerIncomeInfo.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="farmerPerIncomeInfo.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="farmerPerIncomeInfo.id" id="farmerPerIncomeInfoId" value="${farmerPerIncomeInfo.id }"/>

<div id="farmerPerIncomeInfoDialog" style="height:300px;">
<span style="font-weight: bold; ">农村经济收入</span>
<div class='clearLine'>&nbsp;</div>


<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">农业主导产业收入(元):</label>
</div>
<div class="grid_6">
	<input type="text" name="farmerPerIncomeInfo.agriculturalIncome" id="" class="form-txt" value="<fmt:formatNumber value='${farmerPerIncomeInfo.agriculturalIncome}' pattern='#0.00'/>"  <s:if test="'view'.equals(mode)">disabled</s:if> maxlength="12"/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">农村家庭经营性收入(元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="farmerPerIncomeInfo.householdIncome" id="" class="form-txt" value="<fmt:formatNumber value='${farmerPerIncomeInfo.householdIncome}' pattern='#0.00'/>"  <s:if test="'view'.equals(mode)">disabled</s:if> maxlength="14"/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">农村居民人均可支配收入(元):</label>
</div>
<div class="grid_6">
	<input type="text" name="farmerPerIncomeInfo.farmerPerIncome" id="" class="form-txt" value="<fmt:formatNumber value='${farmerPerIncomeInfo.farmerPerIncome}' pattern='#0.00'/>"  <s:if test="'view'.equals(mode)">disabled</s:if> maxlength="12"/>
</div>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">村集体经济收入(元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="farmerPerIncomeInfo.villageCollectiveIncome" id="" class="form-txt" value="<fmt:formatNumber value='${farmerPerIncomeInfo.villageCollectiveIncome}' pattern='#0.00'/>"  <s:if test="'view'.equals(mode)">disabled</s:if> maxlength="14"/>
</div>
 </div>
 <s:if test="!'view'.equals(mode)">
  <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 910px;"/>
 </form>
 </s:if>
 </div>
<script>
	
	function mySelected(values){
		if($("#"+values).val() == 1){
			$("#"+values+"S").removeClass("span_none");
		}else{
			$("#"+values+"I").val("");
			$("#"+values+"S").addClass("span_none");
		}
	}
	
	$(document).ready(function(){
		$("#maintainFormOfFarmerPerIncomeInfo").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id){
							 $("#farmerPerIncomeInfoId").val(data.id);
							 $.messageBox({message:"农民人均可支配收入保存成功!"});
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
				"farmerPerIncomeInfo.agriculturalIncome":{
					posNumWiPot:true,
					min:0,
					max:9999999999.99
				},
				"farmerPerIncomeInfo.householdIncome":{
					numWiPot:true,
					min:-9999999999.99,
					max:9999999999.99
				},
				"farmerPerIncomeInfo.villageCollectiveIncome":{
					posNumWiPot:true,
					min:0,
					max:9999999999.99
				},
				"farmerPerIncomeInfo.farmerPerIncome":{
					numWiPot:true,
					min:-9999999999.99,
					max:9999999999.99
				}
			},
			messages:{
				"farmerPerIncomeInfo.agriculturalIncome":{
					posNumWiPot:"请输入最多两位小数的正数,且不大于9999999999.99"
				},
				"farmerPerIncomeInfo.householdIncome":{
					numWiPot:"输入范围-9999999999.99至9999999999.99,最大小数位为2位"
				},
				"farmerPerIncomeInfo.villageCollectiveIncome":{
					posNumWiPot:"请输入最多两位小数的正数,且不大于9999999999.99"
				},
				"farmerPerIncomeInfo.farmerPerIncome":{
					numWiPot:"输入范围-9999999999.99至9999999999.99,最大小数位为2位"
				}
			}
		});
	});
	//一位到两位小数的正实数
	jQuery.validator.addMethod("numWiPot", function(value, element) {
		var digits = /^([-]{0,1})?[0-9]+(.[0-9]{0,2})?$/;
		return this.optional(element) || (digits.test(value));
	});
</script>