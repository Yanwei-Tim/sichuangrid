<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%request.setAttribute("name",request.getParameter("name"));%>
	<s:if test="'overseaPersonnel'.equals(#request.name)||'nurturesWomenDialog'.equals(#request.dailogName)">
<div class="grid_5 lable-right">
	</s:if><s:else><div class="grid_4 lable-right"></s:else>
	<em class="form-req">*</em>
	<label class="form-lb1">有无住所：</label>
</div>
	<s:if test="'overseaPersonnel'.equals(#request.name)||'nurturesWomenDialog'.equals(#request.dailogName)">
<div class="grid_3">
	</s:if><s:else><div class="grid_4"></s:else>
	<s:if test="'unsettledPopulation'.equals(#request.name)">
		<select id="populationHasHouseInfo" name="unsettledPopulation.isHaveHouse" class="form-txt">
			<option value="true" <s:if test="unsettledPopulation.isHaveHouse">selected</s:if>>有</option>
			<option value="false" <s:if test="unsettledPopulation.isHaveHouse==false">selected</s:if>>无</option>
			<option value="null" <s:if test="unsettledPopulation.isHaveHouse==null">selected</s:if>>未知</option>
		</select>
	</s:if>
	<s:elseif test="'overseaPersonnel'.equals(#request.name)">
		<select id="populationHasHouseInfo" name="overseaPersonnel.isHaveHouse" class="form-txt">
			<option value="true" <s:if test="overseaPersonnel.isHaveHouse">selected</s:if>>有</option>
			<option value="false" <s:if test="overseaPersonnel.isHaveHouse==false">selected</s:if>>无</option>
			<option value="null" <s:if test="overseaPersonnel.isHaveHouse==null">selected</s:if>>未知</option>
		</select>
	</s:elseif>
	<s:elseif test="'householdStaff'.equals(#request.name)">
		<select id="populationHasHouseInfo" name="population.isHaveHouse" class="form-txt">
			<option value="true" <s:if test="population.isHaveHouse">selected</s:if>>有</option>
			<option value="false" <s:if test="population.isHaveHouse==false">selected</s:if>>无</option>
			<option value="null" <s:if test="population.isHaveHouse==null">selected</s:if>>未知</option>
		</select>
	</s:elseif>
	<s:elseif test="'floatingPopulation'.equals(#request.name)">
		<select id="populationHasHouseInfo" name="population.isHaveHouse" class="form-txt">
			<option value="true" <s:if test="population.isHaveHouse">selected</s:if>>有</option>
			<option value="false" <s:if test="population.isHaveHouse==false">selected</s:if>>无</option>
			<option value="null" <s:if test="population.isHaveHouse==null">selected</s:if>>未知</option>
		</select>
	</s:elseif>
	<s:else>
		<select id="populationHasHouseInfo" name="population.isHaveHouse" class="form-txt">
			<option value="true" <s:if test="population.isHaveHouse">selected</s:if>>有</option>
			<option value="false" <s:if test="population.isHaveHouse==false">selected</s:if> >无</option>
			<option value="null" <s:if test="population.isHaveHouse==null">selected</s:if>>未知</option>
		</select>
	</s:else>
</div>
<s:if test="'unsettledPopulation'.equals(#request.name)">
	<div id="haveNotHouse" <s:if test="unsettledPopulation.id==null||unsettledPopulation.isHaveHouse">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">无住所原因：</label>
		</div>
		<div class="grid_11">
			<input type="text" name="unsettledPopulation.noHouseReason" value="${unsettledPopulation.noHouseReason}"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入无住所原因"}}'  id="noHouseReason"
			/>
		</div>
	</div>
	<div id="haveHouse" <s:if test="unsettledPopulation.id==null||unsettledPopulation.isHaveHouse==false">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_11">
			<input type="hidden" id="houseId" value="${unsettledPopulation.houseId }" name="unsettledPopulation.houseId" maxlength="50" style="width: 99%"
				class='form-txt'/>
			<input type="text" id="currentAddress" name='unsettledPopulation.currentAddress' value="${unsettledPopulation.currentAddress }"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入常住地址"}}'/>
		</div>
	</div>
</s:if>
<s:elseif test="'overseaPersonnel'.equals(#request.name)">
	<div id="haveNotHouse" <s:if test="overseaPersonnel.id==null||overseaPersonnel.isHaveHouse">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">无住所原因：</label>
		</div>
		<div class="grid_11">
			<input type="text" name="overseaPersonnel.noHouseReason" value="${overseaPersonnel.noHouseReason}"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入无住所原因"}}'  id="noHouseReason"
			/>
		</div>
	</div>
	<div id="haveHouse" <s:if test="overseaPersonnel.id==null||overseaPersonnel.isHaveHouse==false">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_11">
			<input type="hidden" id="houseId" value="${overseaPersonnel.houseId }" name="overseaPersonnel.houseId" maxlength="50" style="width: 99%"
				class='form-txt'/>
			<input type="text" id="currentAddress" name='overseaPersonnel.currentAddress' value="${overseaPersonnel.currentAddress }"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入常住地址"}}'/>
		</div>
	</div>
</s:elseif>
<s:elseif test="'householdStaff'.equals(#request.name)">
	<div id="haveNotHouse" <s:if test="population.id==null||population.isHaveHouse">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">无住所原因：</label>
		</div>
		<div class="grid_11">
			<input type="text" name="population.noHouseReason" value="${population.noHouseReason}"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入无住所原因"}}'  id="noHouseReason"
			/>
		</div>
	</div>
	<div id="haveHouse" <s:if test="population.id==null||population.isHaveHouse==false">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_11">
			<input type="hidden" id="houseId" value="${population.houseId }" name="population.houseId" maxlength="50" style="width: 99%"
				class='form-txt'/>
			<input type="text" id="currentAddress" name='population.currentAddress' value="${population.currentAddress }"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入常住地址"}}'/>
		</div>
	</div>
</s:elseif>
<s:elseif test="'floatingPopulation'.equals(#request.name)">
	<div id="haveNotHouse" <s:if test="population.id==null||population.isHaveHouse">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">无住所原因：</label>
		</div>
		<div class="grid_11">
			<input type="text" name="population.noHouseReason" value="${population.noHouseReason}"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入无住所原因"}}'  id="noHouseReason"
			/>
		</div>
	</div>
	<div id="haveHouse" <s:if test="population.id==null||population.isHaveHouse==false">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_11">
			<input type="hidden" id="houseId" value="${population.houseId }" name="population.houseId" maxlength="50" style="width: 99%"
				class='form-txt'/>
			<input type="text" id="currentAddress" name='population.currentAddress' value="${population.currentAddress }"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入常住地址"}}'/>
		</div>
	</div>
</s:elseif>

<s:else>
	<div id="haveNotHouse" <s:if test="population.id==null||population.isHaveHouse">style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">无住所原因：</label>
		</div>
		<div class="grid_11">
			<input type="text" name="population.noHouseReason" value="${population.noHouseReason}"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入无住所原因"}}'  id="noHouseReason"
			/>
		</div>
	</div>
	<div id="haveHouse" <s:if test='population.id==null||population.isHaveHouse==false'>style="display: none;"</s:if>>
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_11">
			<input type="hidden" id="houseId" value="${population.houseId }" name="population.houseId" maxlength="50" style="width: 99%"
				class='form-txt'/>
			<input type="text" id="currentAddress" name='population.currentAddress' value="${population.currentAddress }"  maxlength="50" style="width: 99%"
				class='form-txt {required:true,messages:{required:"请输入常住地址"}}'/>
		</div>
	</div>
</s:else>
<script>
jQuery.validator.addMethod("houseIdIsNotNull", function(value, element){
	return ($("#houseId").val()=="" || $("#houseId").val()==null) ? false : true;
});

function populationHasHouseInfoChanged(){
	var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
	if($("#currentAddressType").find("option:selected").attr("internalId")!=<s:property value="@com.tianque.domain.property.CurrentAddressType@BUSINESS_HOUSE"/>){
		$("#otherAddress").hide();
	}
	<s:if test='population.id==null'>
		<s:if test='!("toPositiveInfoDialog".equals(#attr.dailogName))'>
			$("#noHouseReason").val("");
		</s:if>
		$("#houseId").val("");
	</s:if>
	if($("#populationHasHouseInfo").val()=="false"){
		<s:if test="'unsettledPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","unsettledPopulation.isHaveHouse");
		</s:if>
		<s:elseif test="'overseaPersonnel'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","overseaPersonnel.isHaveHouse");
		</s:elseif>
		<s:elseif test="'householdStaff'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:elseif>
				<s:elseif test="'floatingPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:elseif>
		
		<s:else>
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:else>
		$("#haveNotHouse").show();
		$("#haveHouse").hide();
	}else if($("#populationHasHouseInfo").val()=="true"){
		<s:if test="'unsettledPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","unsettledPopulation.isHaveHouse");
		</s:if>
		<s:elseif test="'overseaPersonnel'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","overseaPersonnel.isHaveHouse");
		</s:elseif>
		<s:elseif test="'householdStaff'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:elseif>
				<s:elseif test="'floatingPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:elseif>
		
		<s:else>
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:else>
		$("#haveNotHouse").hide();
		$("#haveHouse").show();
	}else{
		<s:if test="'unsettledPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","unsettledPopulation.isHaveHouse1");
		</s:if>
		<s:elseif test="'overseaPersonnel'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","overseaPersonnel.isHaveHouse1");
		</s:elseif>
		<s:elseif test="'householdStaff'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse1");
		</s:elseif>
				<s:elseif test="'floatingPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse1");
		</s:elseif>
		<s:else>
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse1");
		</s:else>
		$("#haveHouse").hide();
		$("#haveNotHouse").hide();
		$("#noHouseReason").val("");
	}
	procHouseTab();
}

function renderHouseInfoFromADD(house){
	$("#currentAddress").val(house.address);
	$("#houseId").val(house.houseId);
	$("#currentAddress").blur();
	$("#currentAddress").focus();
}

$("#currentAddress").houseAutoComplete({
	search:function(){$("#houseId").val("")},
	searchByAddress:true,
	select:function(event,ui){
		renderHouseInfoFromADD(ui.item);
	}
});

$(document).ready(function(){
	
	//$("#populationHasHouseInfo").change(function(){
	//	procHouseTab();
	//});
	<s:if test='population.id==null || unsettledPopulation.id==null || overseaPersonnel.id==null'>
		<s:if test="'unsettledPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","unsettledPopulation.isHaveHouse");
		</s:if>
		<s:elseif test="'overseaPersonnel'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","overseaPersonnel.isHaveHouse");
		</s:elseif>
		<s:elseif test="'householdStaff'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:elseif>
				<s:elseif test="'floatingPopulation'.equals(#request.name)">
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:elseif>
		
		<s:else>
		   $("#populationHasHouseInfo").attr("name","population.isHaveHouse");
		</s:else>
	$("#haveNotHouse").hide();
	$("#haveHouse").show();
	$("#populationHasHouseInfo").val("true");
	
	// fateson add  社区矫正人员转刑释人员界面不能获取常住地址
		<s:if  test="'toPositiveInfoDialog'.equals(#parameters.dailogName[0])">
			<s:if test='population.isHaveHouse==null'>
				$("#populationHasHouseInfo").val("null");
			</s:if>
			<s:else>
				$("#populationHasHouseInfo").val("${population.isHaveHouse}");
			</s:else>
		  <s:if test='population.isHaveHouse==false || unsettledPopulation.isHaveHouse==false || overseaPersonnel.isHaveHouse==false'>
		    $("#haveNotHouse").show();
			$("#haveHouse").hide();
		  </s:if>
		</s:if>
	
	</s:if>
	<s:if test='population.id!=null && population.isHaveHouse==null || unsettledPopulation.isHaveHouse==null || overseaPersonnel.isHaveHouse==null'>
	$("#haveNotHouse").hide();
	$("#haveHouse").hide();
	</s:if>
	populationHasHouseInfoChanged();
	$("#populationHasHouseInfo").change(function(){
		populationHasHouseInfoChanged();
	});
});
	
	//是否添加标签页
	function procHouseTab(){
		if($("#populationHasHouseInfo").val()=="true"){
			var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
// 			$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("住房信息"));
// 			$("#"+dialogName).addTabToDialog(
// 					{
// 						title:"住房信息",
// 						url:'/baseinfo/houseInfoForPopulation/dispathHouseInfoForPopulation.action?dailogName='+dialogName,		
// 						index:1
// 					}
// 				)
// 				if("${id}" == null){
// 					$("#"+dialogName).tabDialog('disable');
// 				}
		}else{
			var dialogName = "<s:property value='#parameters.dailogName[0]'/>";
// 			$("#"+dialogName).removeTabFromDialog($("#"+dialogName).getTabIndexByTitle("住房信息"));
			
		}
	}
	procHouseTab();
</script>