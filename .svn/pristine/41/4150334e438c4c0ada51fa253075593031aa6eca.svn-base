<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="changeHouseHoldForm" action="${path}/baseinfo/houseFamily/changeHouseHold.action">
	<pop:token />
		<div class="grid_8 lable-right"><label class="form-lbl">原户主姓名：</label>
		</div>
		<div class="grid_16"><input type="text" id="oldHouseHoldName" value="${houseFamily.censusRegisterFamily.householdName}" maxlength="20" class="form-txt"  disabled="disabled"/>
		</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
				<label class="form-lbl">原户主与新户主关系：</label>
		</div>
		<div class="grid_16">
			<select name="oldHouseHold.relationShipWithHead.id"
				id="oldRelationShipWithHeadId" class="form-select 
				{required:true,messages:{required:'请选择与户主关系'}}" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" exceptInternalIds="@com.tianque.domain.property.RelationShipWithHead@HEADER" defaultValue="${population.relationShipWithHead.id}" />
			</select>
		</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">新户主姓名：</label>
		</div>
		<div class="grid_16">
			<select name="newHouseHold.id" 
					id="newHouseHoldName" class="form-select {required:true,messages:{required:'请选择新户主姓名'}}" >
						<option value="" selected="selected" >请选择</option>
				 		<s:iterator value="householdStaffs" var="householdStaff">
				 			<option value="${householdStaff.id}">${householdStaff.name }</option>
				 		</s:iterator>
				</select>
		</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">新户主类型：</label>
		</div>
		<div class="grid_16">
			<select name="newHouseHold.relationShipWithHead.id"
				id="newHouseHoldrelationShipWithHeadId" class="form-select 
				{required:true,messages:{required:'请选择户主类型'}}" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" exceptInternalIds="@com.tianque.domain.property.RelationShipWithHead@RELATION_SHIP_WITH_HEAD" defaultValue="${population.relationShipWithHead.id}" />
			</select>
		</div>
		<input type="hidden" name="houseFamily.id" value="${houseFamily.censusRegisterFamily.id}">
		<input type="hidden" name="oldHouseHold.idCardNo" id="oldHouseHoldId" value="${houseFamily.censusRegisterFamily.idCardNo}" maxlength="20" class="form-txt"/>
		<input type="hidden" name="orgId" value="${orgId}"/>
		<input type="hidden" id="shardOrgId" name="shardOrgId" value="${houseFamily.organization.id}"/>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	//前端删掉 本人选项
	if(!$("#oldRelationShipWithHeadId")){
	 var count=$("#oldRelationShipWithHeadId").get(0).options.length;
		for(var i=0;i<count;i++){
			if($("#oldRelationShipWithHeadId").get(0).options[i].text == "本人")  
			{
				//$("#population_relationShipWithHead_id").get(0).options[i].selected = true; 
			    var value=	$("#oldRelationShipWithHeadId").get(0).options[i];
			    var indx=value.index;
			    $("#oldRelationShipWithHeadId").get(0).options.remove(indx);
				break;  
			}  
		}
	}
		
		 
			 //儿媳  儿媳  在数据库中的internalid值0 所以显示在这里了  前端控制
			 var count1= $("#newHouseHoldrelationShipWithHeadId").get(0).options.length;
			 for(var i=0;i<count1;i++){
				if(($("#newHouseHoldrelationShipWithHeadId").get(0).options[i].text == "儿媳")||($("#newHouseHoldrelationShipWithHeadId").get(0).options[i].text == "女婿"))  
				{
				    var value=	$("#newHouseHoldrelationShipWithHeadId").get(0).options[i];
				    var indx=value.index;
				    $("#newHouseHoldrelationShipWithHeadId").get(0).options.remove(indx);
				    count1= $("#newHouseHoldrelationShipWithHeadId").get(0).options.length;
				    i=i-1;
				}  
			}
			 
		
	  
	
	$("#changeHouseHoldForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
	     		success: function(data){
	     			if(data != "" && data != null && data != undefined){
						$("#houseFamilyList").trigger("reloadGrid");
						$.messageBox({message:"成功更换户主!"});
					}else{
						$.messageBox({message:"更换户主失败!",level:"warn"});
					}
	     			$("#houseFamilyDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
	  	  	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
});
</script>