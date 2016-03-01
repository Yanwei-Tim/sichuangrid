var groupFamilyDialog = function (dfop){
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
						doAction(dfop.dailogName ,data.id,data);
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
				url : PATH + "/baseinfo/familyInfo/whetherFamilyMaster.action",
				data : {
					"groupFamily.familyRelation.id":$("#_familyRelation_id").val()
				},
				success : function(data) {
					if(data == true || data == "true"){
						if($("#dailogName").val()=='overseaPersonnelMaintanceDialog'){
							if($("#_masterCardNo").val() != $("#_idCardNo_").val() || $("#_familyMaster").val() != $("#englishName").val()){
								$.messageBox({
									message : "该家庭编号已经存在家长,与家长关系不能选择家长",
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
			url :  PATH + '/baseinfo/familyInfo/findGroupFamilyByOrgIdAndFamilyAccount.action',
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
	function whetherFamilyMasterAjax(){
		$.ajax({
			async : false,
			url :  PATH + "/baseinfo/familyInfo/whetherFamilyMaster.action",
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
		});
	}
	jQuery.validator.addMethod("reduplicateFamilyMaster", function(value, element){
		var flag = true; 
		if(dfop.isEdit != 'true'){
			if(value != dfop.familyRelationId){
				whetherFamilyMasterAjax();
			}
		} else {
			whetherFamilyMasterAjax();
		}
		return flag;
	});
}