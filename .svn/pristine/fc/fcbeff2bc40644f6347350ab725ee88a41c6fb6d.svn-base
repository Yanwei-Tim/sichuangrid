<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <div id="floatingPopulation" class="container container_24">
		
		<form action="${path}/baseinfo/floatingPopulationManage/maintainInflowingInfomationForBusinessPopulation.action" method="post" id="maintainForm">
		<pop:token />
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input type="hidden" name="cacheId.houseInfoId" value="${cacheId.houseInfoId}" />
		<input type="hidden" name="cacheId.id" value="${cacheId.id}" />
		<input type="hidden" name="contextId" value="${contextId}" />
		<input id="provinceValue"	type="hidden" name="" value="${population.inflowingProvince}" />
		<input id="cityValue"	type="hidden" name="" value="${population.inflowingCity}" />
		<input id="districtValue"	type="hidden" name="" value="${population.inflowingDistrict}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<input id="populationOrganizationId" type="hidden" name="population.organization.id" value="${population.organization.id }" />
		<input id="logOut"	type="hidden" name="population.logOut" value="${population.logOut}" />
		<input id="populationStatu" type="hidden" name="population.death" value="${population.death}" />
		<input type="hidden" name="population.houseId" value="${population.houseId}" />
		<input type="hidden" name="population.attentionPopulationType" value="${population.attentionPopulationType}"/>
		<jsp:include page="/baseinfo/commonPopulation/populationBaseInfo.jsp"/>
		
		<hr width="90%"></hr>
		<div  class="grid_4 lable-right">
			<em class="form-req">*</em>
  			<label class="form-lbl">流入原因：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.inflowingReason.id" id="inflowingReason" class="form-txt {required:true,messages:{required:'流入原因必须输入'}}" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@INFLOWING_REASON" defaultValue="${population.inflowingReason.id}" />
			</select>
   		</div>
   		<div class="grid_5 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">流入时间：</label>
   		</div>
   		<div class="grid_6">
   			<input type="text" name="population.inflowingDate" id="inflowingDate" maxlength="32" readonly  value='<s:date name="population.inflowingDate" format="yyyy-MM-dd" />'
   			class="form-txt {required:true,messages:{required:'流入时间必须输入'}}" />
   		</div>
		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">流入来源：</label>
   		</div>
   		<div class="grid_5">
			<select name="population.inflowingProvince" id="inflowingProvince" class="form-txt" >
			</select>
  		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">省</label>
   		</div>
   		<div class="grid_5">
			<select name="population.inflowingCity" id="inflowingCity" class="form-txt" >
			</select>
 		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">市</label>
   		</div>
   		<div class="grid_5">
			<select name="population.inflowingDistrict" id="inflowingDistrict" class="form-txt" >
			</select>
  		</div>
   		<div class="grid_1">
   		  <label class="form-lbl">县</label>
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">登记证情况：</label>
  		</div>
		<div class="grid_7">
		   <select name="population.registrationType.id" id="registrationType" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@REGISTRATIONTYPE" defaultValue="${population.registrationType.id}" />
			</select>
   		</div>
   		<div  class="grid_5 lable-right">
  			<label class="form-lbl">登记证编号：</label>
  		</div>
		<div class="grid_6">
   			<input type="text" name="population.certificateNumber" id="certificateNumber" maxlength="50"  value='${population.certificateNumber }'class="form-txt" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div  class="grid_4 lable-right">
  			<label class="form-lbl">登记日期：</label>
  		</div>
		<div class="grid_7">
   			<input type="text" name="population.registerDate" id="registerDate" maxlength="32" readonly  value='<s:date name="population.registerDate" format="yyyy-MM-dd" />'
   			class="form-txt {registerDate:true,messages:{registerDate:'登记日期不能小于流入时间'}}" />
   		</div>
		<div  class="grid_5 lable-right">
			<em class="form-req">*</em>
  			<label class="form-lbl">预计到期日期:</label>
  		</div>
		<div class="grid_6">
   			<input type="text" name="population.expectedDatedue" id="expectedDatedue" maxlength="32" readonly  value='<s:date name="population.expectedDatedue" format="yyyy-MM-dd" />'
   			class="form-txt {required:true,expectedDatedue:true,messages:{required:'请输入预计到期日期',expectedDatedue:'预计到期日期不能小于登记日期'}}"" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		<div  class="grid_4 lable-right">
   		 <em class="form-req">*</em>
  			<label class="form-lbl">暂住处所：</label>
  		</div>
		<div class="grid_7">
			<select name="population.stayLocationType.id" id="stayLocationType" class="form-txt {required:true,messages:{required:'此项为必选项'}}" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@STAY_LOCATION_TYPE" defaultValue="${population.stayLocationType.id}" />
			</select>
		</div>
		<div  class="grid_5 lable-right">
			<label class="form-lbl">经济来源：</label>
		</div>
		<div class="grid_6">
			<select name="population.economySource.id" id="economySource" class="form-txt" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ECONOMY_SOURCE" defaultValue="${population.economySource.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div  class="grid_4 lable-right">
			<label class="form-lbl">已居住时限：</label>
		</div>
		<div class="grid_7">
			<select name="population.stayTimeLimit.id" id="stayTimeLimit" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@STAY_TIME_LIMIT" defaultValue="${population.stayTimeLimit.id}" />
			</select>
		</div>
		<div  class="grid_5 lable-right">
			<label class="form-lbl">预居住时限：</label>
		</div>
		<div class="grid_6">
			<select name="population.preparedStayTimeLimit.id" id="preparedStayTimeLimit" class="form-txt" >
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PREPARED_STAY_TIME_LIMIT" defaultValue="${population.preparedStayTimeLimit.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div  class="grid_7 lable-right">
			<input type="checkbox" name="population.hasMarriedProve" value="true" <c:if test="${population.hasMarriedProve==true}">checked</c:if>/>是否有婚育证明
		</div>
		<div class='clearLine'>&nbsp;</div>
		</form>
   </div>
<script type="text/javascript">
$('#inflowingDate').datePicker({
	yearRange: '1900:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'+0y'
});
$('#registerDate').datePicker({
	yearRange: '1900:2060',
	dateFormat:'yy-mm-dd',
	maxDate:'+0y'
});
$('#expectedDatedue').datePicker({
	yearRange: '1900:2060',
	dateFormat:'yy-mm-dd'
});

$(document).ready(function(){

	threeSelect({province:'inflowingProvince',
		city:'inflowingCity',
		district:'inflowingDistrict',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});

	jQuery.validator.addMethod("registerDate", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var inflowingDate = $("#inflowingDate").val();
		if(value<inflowingDate){
			return false;
		}else{
			return true;
		}
	});
	
	jQuery.validator.addMethod("expectedDatedue", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var registerDate = $("#registerDate").val();
		if(registerDate==null||registerDate==undefined||registerDate==""){return true}
		if(value<registerDate){
			return false;
		}else{
			return true;
		}
	});

	jQuery.validator.addMethod("expectedDatedue1", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var inflowingDate = $("#inflowingDate").val();
		if(inflowingDate==null||inflowingDate==undefined||inflowingDate==""){return true}
		if(value<inflowingDate){
			return false;
		}else{
			return true;
		}
	});

	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	}

	<c:if test='${dailogName=="floatingPopulationDialog"}'>
	$("#maintainForm").attr("action","${path}/baseinfo/floatingPopulationManage/saveFloatingPopulationInfo.action");
	</c:if>
});
$("#maintainForm").formValidate({
	submitHandler: function(form){
	$("#_imgUrl").val($("#imgUrl").val());
	$(form).ajaxSubmit({
		async:false,
		success : function(data) {
			if (!data.id) {
				$.messageBox({
					message : data,
					level : "error"
				});
				return;
			}
			if("add"==$("#mode").val()){
				 if(data.name) {
					 if(($("#isLock").val()=='1'&&$("#logOut").val()!="1")||($("#isLock").val()=='2'&&$("#isDeath").val()!="true")){
						 $("#floatingPopulationList").addRowData(data.id,data,"first");
						 $("#floatingPopulationList").setSelection(data.id);
					 }
					 $("#floatingPopulationList").trigger("reloadGrid");
				 	 $.messageBox({message:"流动人口新增成功！"});
				 }
			 }
			 else if("edit"==$("#mode").val()){
				 if(data.name) {
					 if(data.death == true || data.death == "true"){
						 if(($("#isLock").val()=='1'&&$("#logOut").val()=="0")||($("#isLock").val()=='2'&&$("#isDeath").val()=="false")){
							 $("#floatingPopulationList").delRowData(data.id);
						 }else{
							 $("#floatingPopulationList").setRowData(data.id,data);
							 $("#"+data.id+"").css('color','#778899');
							 $("#floatingPopulationList").setSelection(data.id);
						 }
					 }else{
						 if($("#isLock").val()=='2'&&$("#isDeath").val()=="true"){
							 $("#floatingPopulationList").delRowData(data.id);
						 }else{
							 $("#floatingPopulationList").setRowData(data.id,data);
							 $("#floatingPopulationList").setSelection(data.id);
						 }
					 }
					 $("#floatingPopulationList").trigger("reloadGrid");
					 //	disableButtons();
					 //checkExport();
				 	 $.messageBox({message:"流动人口修改成功！"});
				 }
			 }
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("提交错误");
		}
		});
	},
	rules:{
		"population.certificateNumber":{
			minlength:2,
			maxlength:50
		}
	},
	messages:{	
		"population.certificateNumber":{
			minlength:$.format("登记证编号至少需要输入{0}个字符"),
			maxlength:$.format("登记证编号最多需要输入{0}个字符")
		}
	}
});
</script>