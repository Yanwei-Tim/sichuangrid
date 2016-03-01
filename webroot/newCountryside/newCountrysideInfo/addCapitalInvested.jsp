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
<form id="maintainFormOfCapitalInvested" method="post" action="${path }/baseinfo/capitalInvestedManage/maintainCapitalInvested.action"  >
<pop:token />
<input type="hidden" name="capitalInvested.organization.id" value="${organization.id }"/>
<input type="hidden" name="dataType" value="${dataType }"/>
<input type="hidden" name="capitalInvested.newVillageBasic.id" id="newVillageBasicId" value="${newVillageBasic.id }"/>
<input type="hidden" name="capitalInvested.id" id="capitalInvestedId" value="${capitalInvested.id }"/>

<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">概算总投资(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.allInvestmentCount" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.allInvestmentCount}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<div id="capitalInvestedDialog" style="height:350px;">
<span style="font-weight: bold; ">财政资金投入</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">财政资金投入总额(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.capitalInvestmentCount" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.capitalInvestmentCount}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">其中：</label>
	<label class="form-lbl">中央/省投入(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.centralProvinceInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.centralProvinceInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">&emsp;&emsp;市/州投入(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.municipalityInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.municipalityInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl" >县级投入(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.countyInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.countyInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold;">社会资金投入</span>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">&emsp;&emsp;社会资金投入总额：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.sociologyInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.sociologyInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>

<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
<label class="form-lbl">&emsp;&emsp;其中：</label>
	<label class="form-lbl">金融投入(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.financialInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.financialInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">工商资本投入(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.industryAndCommerceInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.industryAndCommerceInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">其他(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.otherInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.otherInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>
<div class='clearLine'>&nbsp;</div>
<hr/>
<div class='clearLine'>&nbsp;</div>
<span style="font-weight: bold;">农民投入：</span>
<div class='clearLine'>&nbsp;</div>


<div class="grid_4 lable-right" style="margin-left: 50px;">
	<label class="form-lbl">农民自筹资金(万元)：</label>
</div>
<div class="grid_6">
	<input type="text" name="capitalInvested.farmerInvested" id="" class="form-txt" value="<fmt:formatNumber value='${capitalInvested.farmerInvested}' pattern='#0.00'/>" maxlength="12"  <s:if test="'view'.equals(mode)">disabled</s:if>/>
</div>


 </div>
 <s:if test="!'view'.equals(mode)">
  <input type="submit" value="保存" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" style="margin-left: 900px;"/>
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
		$("#maintainFormOfCapitalInvested").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					async:false,
					success:function(data){
						if(data.id){
							 $("#capitalInvestedId").val(data.id);
							 $.messageBox({message:"资金投入信息保存成功!"});
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
				"capitalInvested.centralProvinceInvested":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.municipalityInvested":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.countyInvested":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.financialInvested":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.industryAndCommerceInvested":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.farmerInvested":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.allInvestmentCount":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.capitalInvestmentCount":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.sociologyInvested":{
					posNumWiPot:true,
					max:9999999999.99
				},
				"capitalInvested.otherInvested":{
					posNumWiPot:true,
					max:9999999999.99
				}
			},
			messages:{
				"capitalInvested.centralProvinceInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.municipalityInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.countyInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.financialInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.industryAndCommerceInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.farmerInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.allInvestmentCount":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.capitalInvestmentCount":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.sociologyInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				},
				"capitalInvested.otherInvested":{
					posNumWiPot : "请输入0-9999999999.99范围数字,最大小数位2位"
				}
			}
		});
	});
</script>