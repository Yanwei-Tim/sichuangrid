TQ.maintainAdministrativeOrgTimeLimitStandardDlg = function (dfop){

	//办理时限必须填写一项
	jQuery.validator.addMethod("mustFillOneItem", function(value, element) {
		var isTrue = true;
		isTrue = isTrue && ("" == $("#administrativeOrgTimeLimitStandardYellowLimitAccept").val());// || $("#yellowLimitAccept").val() == 0
		isTrue = isTrue && ("" == $("#administrativeOrgTimeLimitStandardYellowLimitHandle").val());// || $("#yellowLimitHandle").val() == 0
		isTrue = isTrue && ("" == $("#administrativeOrgTimeLimitStandardRedLimitAccept").val());// || $("#redLimitAccept").val() == 0
		isTrue = isTrue && ("" == $("#administrativeOrgTimeLimitStandardRedLimitHandle").val());// || $("#redLimitHandle").val() == 0
		isTrue = isTrue && ("" == $("#administrativeOrgTimeLimitStandardYellowLimitVerify").val());
		isTrue = isTrue && ("" == $("#administrativeOrgTimeLimitStandardRedLimitVerify").val());
		return !isTrue;
	});
	jQuery.validator.addMethod("limitAcceptValidator", function(value, element) {
		var redLimitAccept=$('#administrativeOrgTimeLimitStandardRedLimitAccept').val();
		var yelloLimitAccept=$('#administrativeOrgTimeLimitStandardYellowLimitAccept').val();
		if(redLimitAccept!=null&&redLimitAccept!=""&&yelloLimitAccept!=null&&yelloLimitAccept!=""){
			redLimitAccept=parseInt(redLimitAccept);
			yelloLimitAccept=parseInt(yelloLimitAccept);
			if(redLimitAccept<=yelloLimitAccept){
				return false;
			}
		}
		return true;
	});
	jQuery.validator.addMethod("limitHandleValidator", function(value, element) {
		var redLimitHandle=$('#administrativeOrgTimeLimitStandardRedLimitHandle').val();
		var yelloLimitHandle=$('#administrativeOrgTimeLimitStandardYellowLimitHandle').val();
		if(redLimitHandle!=null&&redLimitHandle!=""&&yelloLimitHandle!=null&&yelloLimitHandle!=""){
			redLimitHandle=parseInt(redLimitHandle);
			yelloLimitHandle=parseInt(yelloLimitHandle);
			if(redLimitHandle<=yelloLimitHandle){
				return false;
			}
		}
		return true;
	});
	jQuery.validator.addMethod("limitVerifyValidator", function(value, element) {
		var redLimitVerify=$('#administrativeOrgTimeLimitStandardRedLimitVerify').val();
		var yelloLimitVerify=$('#administrativeOrgTimeLimitStandardYellowLimitVerify').val();
		if(redLimitVerify!=null&&redLimitVerify!=""&&yelloLimitVerify!=null&&yelloLimitVerify!=""){
			redLimitVerify=parseInt(redLimitVerify);
			yelloLimitVerify=parseInt(yelloLimitVerify);
			if(redLimitVerify<=yelloLimitVerify){
				return false;
			}
		}
		return true;
	});
	$(document).ready(function(){
		
		if(dfop.ifAdd=='true'){
			$("#administrativeOrgTimeLimitStandardForm").attr("action",PATH+"/administrativeOrgTimeLimitStandardManage/addAdministrativeOrgTimeLimitStandard.action");
		}
		if(dfop.ifEdit=='true'){
			$("#administrativeOrgTimeLimitStandardForm").attr("action",PATH+"/administrativeOrgTimeLimitStandardManage/updateAdministrativeOrgTimeLimitStandard.action");
		}
		$("#administrativeOrgTimeLimitStandardForm").formValidate({
			submitHandler: function(form) {
				if(dfop.ifAdd=='true'){
					if($("#itemNameIds").val()==""){
						$.messageBox({message:"请选择事件类型!",level:"warn"});
						return;
					}
					if(!validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeIdWithMoreId()){
						$.messageBox({message:"选中标准中有已经存在标准，不允许重复添加!",level:"warn"});
						return;
					}
				}
				if(dfop.ifEdit=='true'){
					if(validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId()) {
						$.messageBox({message:"已经存在该标准，不允许重复添加!",level:"warn"});
						return;
					}
				}
				$(form).ajaxSubmit({
	         		success: function(data){
	         			if(!data.id){
	           	 			$.messageBox({
								message:data,
								level: "error"
				 			});
	            			return;
						}
	         			if("add"==$("#mode").val()){
	         				$.messageBox({message:"新增行政部门时限标准成功!"});
	                    	//$("#administrativeOrgTimeLimitStandardList").addRowData(data.id,data,"first");
	                    }
	                    if("edit"==$("#mode").val()){
	                    	$.messageBox({message:"修改行政部门时限标准成功!"});
	                    	$("#administrativeOrgTimeLimitStandardList").setRowData(data.id,data);
	                    }
						$("#administrativeOrgTimeLimitStandardDialog").dialog("close");	               
						$("#administrativeOrgTimeLimitStandardList").trigger("reloadGrid");
	         			
		  	   		},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		  	   		     alert("提交错误");
		      	   	}
	      	  	});
			},
			rules:{
				"administrativeOrgTimeLimitStandard.yellowLimitAccept":{
					positiveInteger:true,
					mustFillOneItem:true,
					required:true,
					maxlength:2
				},
				"administrativeOrgTimeLimitStandard.yellowLimitHandle":{
					positiveInteger:true,
					mustFillOneItem:true,
					required:true,
					maxlength:2
				},
				"administrativeOrgTimeLimitStandard.yellowLimitVerify":{
					positiveInteger:true,
					mustFillOneItem:true,
					required:true,
					maxlength:2
				},
				"administrativeOrgTimeLimitStandard.redLimitAccept":{
					positiveInteger:true,
					mustFillOneItem:true,
					limitAcceptValidator:true,
					required:true,
					maxlength:2
				},
				"administrativeOrgTimeLimitStandard.redLimitHandle":{
					positiveInteger:true,
					mustFillOneItem:true,
					limitHandleValidator:true,
					required:true,
					maxlength:2
				},
				"administrativeOrgTimeLimitStandard.redLimitVerify":{
					positiveInteger:true,
					mustFillOneItem:true,
					limitVerifyValidator:true,
					required:true,
					maxlength:2
				}
			},
			messages:{
				"administrativeOrgTimeLimitStandard.yellowLimitAccept":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					required:"请输入黄牌受理时限",
					maxlength:"黄牌受理时限不能超过99天"
				},
				"administrativeOrgTimeLimitStandard.yellowLimitHandle":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					required:"请输入黄牌办理时限",
					maxlength:"黄牌办理时限不能超过99天"
				},
				"administrativeOrgTimeLimitStandard.yellowLimitVerify":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					required:"请输入黄牌验证时限",
					maxlength:"黄牌验证时限不能超过99天"
				},
				"administrativeOrgTimeLimitStandard.redLimitAccept":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					limitAcceptValidator:"红牌受理时限需大于黄牌受理时限",
					required:"请输入红牌受理时限",
					maxlength:"红牌受理时限不能超过99天"
				},
				"administrativeOrgTimeLimitStandard.redLimitHandle":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					limitHandleValidator:"红牌办理时限需大于黄牌办理时限",
					required:"请输入红牌办理时限",
					maxlength:"红牌办理时限不能超过99天"
				},
				"administrativeOrgTimeLimitStandard.redLimitVerify":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					limitVerifyValidator:"红牌验证时限需大于黄牌验证时限",
					required:"请输入红牌验证时限",
					maxlength:"红牌验证时限不能超过99天"
				}
			}
		});
		
	});

	function validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId() {
		var isTrue = true;
		$.ajax({
			async: false,
			url: PATH+"/administrativeOrgTimeLimitStandardManage/validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeId.action",
			data:{
				"administrativeOrgTimeLimitStandard.useLevel.id" : $("#useLevelId").val(),
				"administrativeOrgTimeLimitStandard.issueDomainId":$("#issueDomain").val(),
				"administrativeOrgTimeLimitStandard.issueTypeId":$("#issueTypeId").val(),
				"administrativeOrgTimeLimitStandard.id":$("#administrativeOrgTimeLimitStandardId").val()
			},
			success:function(responseData){
				isTrue = !responseData;
			}
		});
		return isTrue;
	}
	function validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeIdWithMoreId() {
		var isTrue = true;
		$.ajax({
			async: false,
			url: PATH+"/administrativeOrgTimeLimitStandardManage/validateRepeatByUseLevelAndIssueDomainIdAndIssueTypeIdWithMoreId.action",
			data:{
				"administrativeOrgTimeLimitStandard.useLevel.id" : $("#useLevelId").val(),
				"administrativeOrgTimeLimitStandard.issueDomainId":$("#issueDomain").val(),
				"administrativeOrgTimeLimitStandard.itemIds":$("#itemNameIds").val(),
				"administrativeOrgTimeLimitStandard.id":$("#administrativeOrgTimeLimitStandardId").val()
			},
			success:function(responseData){
				isTrue = !responseData;
			}
		});
		return isTrue;
	}
}