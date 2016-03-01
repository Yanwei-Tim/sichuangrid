<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="户籍信息" class="container container_24">
	<pop:token />
		<div id="perUuid"></div>
		<input type="hidden" name="population.houseId" id="houseId" value="${population.houseId}"/>
		<input type="hidden" id="populationAccountNumber" value="${population.accountNumber}"/>
		<input name="mode" id="mode" value="${mode}" type="hidden">
		<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
		<input type="hidden" name="contextId"  value="${contextId}" />
		<input id="populationId" type="hidden" name="population.id" value="${population.id}" />
		<input id="population_idCardNo" type="hidden" value="${population.idCardNo }" />
		<input id="population_name" type="hidden" value="${population.name }" />
		<input id="population_mobileNumber" type="hidden" value="${population.mobileNumber }" />
		<input id="population_telephone" type="hidden" value="${population.telephone}" />
		<input id="population_homePhone" type="hidden" value="${population.homePhone}" />
		<input id="provinceValue"	type="hidden" name="" value="${population.outProvince}" />
		<input id="cityValue"	type="hidden" name="" value="${population.outCity}" />
		<input id="districtValue"	type="hidden" name="" value="${population.outDistrict}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden"  value="${population.imgUrl}"/>
		<input type="hidden" name="population.attentionPopulationType" value="${population.attentionPopulationType}"/>
		<input name="population.settleTime" id="population_settleTime" type="hidden" value="${population.settleTime}"/>
		<input type="hidden" name="population.organization.id" value="${population.organization.id }">
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">户口号：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="population.accountNumber"
			id="accountNumber" maxlength="20"
			value="${population.accountNumber}"  onkeyup="getCensusRegisterFamily()"
			class="form-txt dialogtip {required:true,maxlength:20,messages:{required:'请输入户口号',maxlength:$.format('户口号最多需要输入{0}个字符')}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">与户主关系：</label>
		</div>
		<div class="grid_7">
			<select onchange="whetherHousehold()" name="population.relationShipWithHead.id"
				id="population_relationShipWithHead_id" class="form-select 
				{required:true,messages:{required:'请选择与户主关系'}}" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RELATION_SHIP_WITH_HEAD" defaultValue="${population.relationShipWithHead.id}" />
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">户主身份证号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="_idCardNo" disabled class="form-txt dialogtip" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">户主姓名：</label>
		</div> 
		<div class="grid_7">
			<input type="text" id="_householdName" disabled class="form-txt dialogtip" />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">户主手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="_mobileNumber" disabled 
			class="form-txt dialogtip {telephone:true,messages:{telephone:$.format('固定电话不合法，只能输数字和横杠(-)')}}" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">固定电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="_telephone" disabled class="form-txt" />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">住宅电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="population.homePhone" maxlength="20" id="_homePhone" class="form-txt dialogtip" disabled="disabled" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">人户状态：</label>
		</div>
		<div class="grid_7">
			<select name="population.residentStatus.id" class="form-select" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENT_STATUS" defaultValue="${population.residentStatus.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">是否外出：</label>
		</div>
		<div class="grid_1">
			<input type="checkbox" id="outGone" name="population.outGone" value="true" <s:if test='true==population.outGone'>checked="checked"</s:if>  />
		</div>
		<div class="grid_10 lable-right">
			<label class="form-lbl">户口类别：</label>
		</div>
		<div class="grid_7">
   			<select name="population.residenceType.id" id="residenceType" class="form-txt">
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RESIDENCE_TYPE" defaultValue="${population.residenceType.id}" />
			</select>
   		</div>
		<div class='clearLine' style="height:10px"></div>
		<div id="detailConditionItems" class="container container_24" style="display:none;">
			<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">外出原因：</label>
			</div>
			<div class="grid_7">
				<select id="outReasonsId" name="population.outReasons.id" class="form-select {exsistedOutGone:true,messages:{exsistedOutGone:'请输入外出原因'}}">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@OUT_REASON" defaultValue="${population.outReasons.id}" />
				</select>
			</div>
			<div class="grid_4 lable-right">
				<em class="form-req">*</em>
				<label class="form-lbl">外出时间：</label>
			</div>
			<div class="grid_7">
				<input type="text" name="population.reasonsDate" id="reasonsDate" readonly  value='<s:date name="population.reasonsDate" format="yyyy-MM-dd" />' 
				class="form-txt {exsistedOutGone2:true,messages:{exsistedOutGone2:'请输入外出时间'}}" />
			</div>
			
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">外出去向：</label>
			</div>
			<div class="grid_5">
				<select name="population.outProvince" id="outProvince" class="form-txt" >
				</select>
	  		</div>
	   		<div class="grid_1">
	   		  <label class="form-lbl">省</label>
	   		</div>
	   		<div class="grid_6">
				<select name="population.outCity" id="outCity" class="form-txt" >
				</select>
	 		</div>
	   		<div class="grid_1">
	   		  <label class="form-lbl">市</label>
	   		</div>
	   		<div class="grid_5">
				<select name="population.outDistrict" id="outDistrict" class="form-txt" >
				</select>
	  		</div>
	   		<div class="grid_1">
	   		  <label class="form-lbl">县</label>
	   		</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">外出详址：</label>
			</div>
			<div class="grid_18">
				<input type="text" maxlength="50" id="goOutDetailedAddress" value="${population.goOutDetailedAddress }" name="population.goOutDetailedAddress" 
				class="form-txt dialogtip {maxlength:50,messages:{maxlength:'外出详址最多只能输入50个字符'}}" />
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		   <label class="form-lbl">家庭称号：</label>
		</div>
         <div class="grid_18" id="houseMarchType">
	        <pop:PropertyDictMultiCheckbox name="houseMarchTypeList" column="3" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_MARCH_TYPE" listVal="${population.houseMarchType}" />
         </div>
         <div class='clearLine'>&nbsp;</div>
	<input type="hidden" id="_populationOrgId" value="${population.organization.id}">
</div>
<script type="text/javascript">


	//判断户口号是否存在户主
	function accountNumberHasHouseHold(){
	    var isAccountNumberHasHouseHold = false;
		$.ajax({
			async : false,
			url : "${path }/baseinfo/householdStaff/getCensusRegisterFamilyByOrgIdAndAccountNumber.action?orgId="+$("#_populationOrgId").val()+"&householdStaffVo.accountNumber="+$("#accountNumber").val(),
			success : function(data) {
				if(data != null){
					if(data.idCardNo != null && data.idCardNo != ''){
						isAccountNumberHasHouseHold = true;
					}
				}
			}
		});
		return isAccountNumberHasHouseHold;
	}

	//判断户口号是否已占用
    var isAccountNumberHasHouseHold = false;
    //by wangzhen add 
    //原始是否是本人
    var originalRelationShip = false;
    if($("#mode").val()=='edit'){
		var	oldRelationShip = getOriginalRelationShip()
		if(oldRelationShip){
			$("#_homePhone").attr("disabled",false);
		}
		whetherHousehold();
	}
  	
  	$("#accountNumber").blur(function(){
		   asynGetFamilyType();
	});
  	
	var settleTime = $("#population_settleTime").val();
  $(function(){
	  var column=3;
	  var widthString = column==-1 ? "" : (100/column+"%");
  });
    
  //判断选择的与户主关系是不是户主（户主和小集体户主）
  function getOriginalRelationShip(){
  	var originalRelationShip = false;
	$("#_homePhone").val($("#population_homePhone").val());
 		$.ajax({
		async : false,
		url : "${path}/baseinfo/householdStaff/whetherHousehold.action",
		data : {
			"population.relationShipWithHead.id":$("#population_relationShipWithHead_id").val()
		},
		success : function(data) {
			if(data==true || data=="true"){
				originalRelationShip = true;
			}
		}
	});
	return originalRelationShip;
  }
  
  //获得家庭称号
  function  asynGetFamilyType(){
	  setFamilyTypeChecked(false);
	  $.ajax({
			async : true,
			url : "${path}/baseinfo/householdStaff/reflushHouseHoldData.action",
			data :{"population.accountNumber":$("#accountNumber").val(),"population.organization.id":getCurrentOrgId()},
			success : function(data) {
				if(data != false || data != "false"){
					if(data.houseMarchType!=null){
						 $("[name='houseMarchTypeList']").each(function(){
							 for(var i=0;i<data.houseMarchType.length;i++){
									if(data.houseMarchType[i].id==$(this).attr("value")){
										 $(this).attr("checked",true);
									}
								}
					     });
					}	
			    }		
			}
		 });
	}

  
  //by wangzhen add
  function setFamilyTypeDisable(flag){
	  	$("[name='houseMarchTypeList']").each(function(){
			 $(this).attr("disabled",flag);
		});
  }
  
  function setFamilyTypeChecked(flag){
  	  $("[name='houseMarchTypeList']").each(function(){
			 $(this).attr("checked",flag);
	  });
  }
   
	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	}
	
	
	$('#reasonsDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
	    maxDate:'+0d'});
	
	threeSelect({
		province:'outProvince',
		city:'outCity',
		district:'outDistrict',
		provinceValue:$('#provinceValue').val(),
		cityValue:$('#cityValue').val(),
		districtValue:$('#districtValue').val()
	});

	$("input[name='population.outGone']",$("#dialog-form")).click(function(){
		$(this).attr("checked") ? $("#detailConditionItems").show() :$("#detailConditionItems").hide();
	});
	
	//by wangzhen override
	//选择更换与户主关系时触发
	function whetherHousehold(){
		$.ajax({
			async : false,
			url : "${path}/baseinfo/householdStaff/whetherHousehold.action",
			data : {
				"population.relationShipWithHead.id":$("#population_relationShipWithHead_id").val()
			},
			success : function(data) {
				setFamilyTypeDisable(!data);
				//如果是户主本人
				if(data==true || data=="true")
				{
					if($("#mode").val()=='add'){
						if(accountNumberHasHouseHold()){
							$.messageBox({message:"该户口号已有户主!",level:"error"});
							return;
						}
						if(!checkHasCensusRegisterFamliyData()){
							getCensusRegisterFamilyByCensusRegister();
							$("#_homePhone").val(data.homePhone);
						}
					} else if ($("#mode").val()=='edit'){
						if(!oldRelationShip && accountNumberHasHouseHold()){
							$.messageBox({message:"该户口号已有户主!",level:"error"});
							return;
						}else{
							getCensusRegisterFamilyByCensusRegister();
							$("#_homePhone").val($("#population_homePhone").val());
						}
					}
					$("#_homePhone").attr("disabled",false);
				}
				//如果不是户主本人
				else {
					if($("#mode").val()=='add'){
						if(!accountNumberHasHouseHold()){
							cleanCensusRegisterFamily();
						}
					} else if ($("#mode").val()=='edit'){
						if(oldRelationShip){
							cleanCensusRegisterFamily();
						}else{
							getCensusRegisterFamilyInfo();
						}
					}
					$("#_homePhone").attr("disabled",true);
				}
			}
		});
	}
	function checkHasCensusRegisterFamliyData(){
		return $("#_idCardNo").val()!="" && $("#_householdName").val()!="";
	}
	
	function getCensusRegisterFamilyByCensusRegister(){
		$("#_idCardNo").val($("#population_idCardNo").val());
		$("#_householdName").val($("#population_name").val());
		$("#_mobileNumber").val($("#population_mobileNumber").val());
		$("#_telephone").val($("#population_telephone").val());
		asynGetFamilyType();
	}
	
	function cleanCensusRegisterFamily(){
		$("#_idCardNo").val("");
		$("#_householdName").val("");
		$("#_homePhone").val("");
		$("#_mobileNumber").val("");
		$("#_telephone").val("");
		setFamilyTypeChecked(false);
	}
	
	//户主信息回填
	$(document).ready(function(){
		getCensusRegisterFamilyInfo();
	});
	
	//输入户口号，拉取户主数据
	function getCensusRegisterFamily(){
		$("#population_relationShipWithHead_id").val('');
		getCensusRegisterFamilyInfo();
	}
	//取户主数据
	function getCensusRegisterFamilyInfo(){
		$.ajax({
			async : false,
			url : "${path }/baseinfo/householdStaff/getCensusRegisterFamilyByOrgIdAndAccountNumber.action?orgId="+$("#_populationOrgId").val()+"&householdStaffVo.accountNumber="+$("#accountNumber").val(),
			success : function(data) {
				if(data != null){
					$("#_idCardNo").val(data.idCardNo);
					$("#_householdName").val(data.householdName);
					$("#_homePhone").val(data.homePhone);
					$("#_mobileNumber").val(data.mobileNumber);
					$("#_telephone").val(data.telePhone);
				}else{
					cleanCensusRegisterFamily();
				}
			}
		});
	}
	
	
	<s:if test='"householdStaffPopulationDialog".equals(dailogName)'>
		$("#maintainForm").attr("action","${path}/baseinfo/householdStaff/maintainHouseholdStaffBusinessInfo.action");
	</s:if>
	
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			$("#_imgUrl").val($("#imgUrl").val());
			$(form).ajaxSubmit({
				success : function(data) {
					if (!data.id) {
						$.messageBox({
							message : data,
							level : "error"
						});
						return;
					}
					if($("#mode").val() == "add"){ 
						if(data.name) {
							 if(($("#isLock").val()=='1'&&$("#logout").val()!="1")||($("#isLock").val()=='2'&&$("#_isDeath").val()!="1")){
						 		$("#householdStaffList").addRowData(data.id,data,"first");
						 		$("#householdStaffList").setSelection(data.id);
					 		}
							$("#householdStaffList").trigger("reloadGrid");
							<s:if test='!"".equals(settleTime)'>
							updateEmphasiseUnsettledPopulation();
							</s:if>
							$.messageBox({message:"户籍人口新增成功!"});
						} 
					} else {
						if(data.name) {
							if(data.death == true || data.death == "true"){
						 		if(($("#isLock").val()=='1'&&$("#logout").val()=="0")||($("#isLock").val()=='2'&&$("#_isDeath").val()=="0")){
							 		$("#householdStaffList").delRowData(data.id);
						 		}else{
							 		$("#householdStaffList").setRowData(data.id,data);
							 		$("#"+data.id+"").css('color','#778899');
							 		$("#householdStaffList").setSelection(data.id);
						 		}
					 		}else{
						        if($("#isLock").val()=='2'&&$("#_isDeath").val()=="1"){
							 		$("#householdStaffList").delRowData(data.id);
								}else{
							 		$("#householdStaffList").setRowData(data.id,data);
							 		$("#householdStaffList").setSelection(data.id);
						 		}
					 		}
							$("#householdStaffList").trigger("reloadGrid");
							$.messageBox({message:"户籍人口修改成功!"});
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				}
			});
		},
		rules:{
			
		},
		messages:{
			
		}
	});
	
	isNextBtn();
	function isNextBtn(){
		var outGone = document.getElementsByName("population.outGone");
		for(var i = 0; i < outGone.length; i++){
			if(outGone[i].checked == false){
				$("#detailConditionItems").hide();
			}else{
				$("#detailConditionItems").show();
			}
		}
	}
	function updateEmphasiseUnsettledPopulation(){
		$("#unsettledPopulationList").trigger("reloadGrid");
		
	}
	jQuery.validator.addMethod("exsistedOutGone", function(value, element){
		var boo = false;
		var outGone = document.getElementsByName("population.outGone");
		for(var i = 0; i < outGone.length; i++){
			if(outGone[i].checked){
				boo=true;
			}
		}
		if(boo){
			if($("#outReasonsId").val() == null || $("#outReasonsId").val() == ""){
				return false;
			}
		}
		return true;
	});
	jQuery.validator.addMethod("exsistedOutGone2", function(value, element){
		var boo = false;
		var outGone = document.getElementsByName("population.outGone");
		for(var i = 0; i < outGone.length; i++){
			if(outGone[i].checked){
				boo=true;
			}
		}
		if(boo){
			if($("#reasonsDate").val() == null || $("#reasonsDate").val() == ""){
				return false;
			}
		}
		return true;
	});

	$(document).ready(function(){
		if($("#populationAccountNumber").val() != null && $("#populationAccountNumber").val() != ""){
 			$('#accountNumber').attr("readonly","readonly");
		}
	});
</script>


