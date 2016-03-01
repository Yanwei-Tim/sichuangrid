TQ.maintainFunctionOrgTimeLimitStandardDlg = function (dfop){

	//办理时限必须填写一项
	jQuery.validator.addMethod("mustFillOneItem", function(value, element) {
		var isTrue = true;
		isTrue = isTrue && ("" == $("#yellowLimitAccept").val());// || $("#yellowLimitAccept").val() == 0
		isTrue = isTrue && ("" == $("#yellowLimitHandle").val());// || $("#yellowLimitHandle").val() == 0
		isTrue = isTrue && ("" == $("#redLimitAccept").val());// || $("#redLimitAccept").val() == 0
		isTrue = isTrue && ("" == $("#redLimitHandle").val());// || $("#redLimitHandle").val() == 0
		isTrue = isTrue && ("" == $("#yellowLimitVerify").val());
		isTrue = isTrue && ("" == $("#redLimitVerify").val());
		return !isTrue;
	});
	jQuery.validator.addMethod("limitAcceptValidator", function(value, element) {
		var redLimitAccept=$('#redLimitAccept').val();
		var yelloLimitAccept=$('#yellowLimitAccept').val();
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
		var redLimitHandle=$('#redLimitHandle').val();
		var yelloLimitHandle=$('#yellowLimitHandle').val();
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
		var redLimitVerify=$('#redLimitVerify').val();
		var yelloLimitVerify=$('#yellowLimitVerify').val();
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
		if(dfop.ifAdd=='true' || dfop.ifAdd==true){
			$("#itemName").uiMultiselect({
				selectedList:5,
				click:function(event,ui){
					prepareValues(ui.checked,ui.value);
				},
				checkAllText: '',
				header:false,
				uncheckAllText: ''
			});
			$("#itemName").multiselect("uncheckAll");
		}

		var checkedValues=new Array();
		function prepareValues(ifCheck,cheValue){
			if(ifCheck=='true' || ifCheck==true){
				if(cheValue!=""){
					checkedValues.push(cheValue);
				}
			}
			if(ifCheck=='false' || ifCheck==false){
				for(var i in checkedValues){
					if(checkedValues[i]==cheValue){
						checkedValues.splice(i,1);
					}
				}
			}
			$("#itemNameIds").val(checkedValues);
		}
		
		if(dfop.ifAdd=='true'){
			$("#maintainForm").attr("action",PATH+"/issues/issueStandardForFunOrgManage/addIssueStandardForFunOrg.action");
		}
		if(dfop.ifEdit=='true'){
			$("#maintainForm").attr("action",PATH+"/issues/issueStandardForFunOrgManage/updateIssueStandardForFunOrg.action");
		}
		defaultSelect();
		
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				if(dfop.ifAdd=='true'){
					if($("#itemNameIds").val()==""){
						$.messageBox({message:"请选择项目名称!",level:"warn"});
						return;
					}
					
					if(!validateRepeatByOrgIdAndItemNameByMoreItem()){
						$.messageBox({message:"选中标准中有已经存在标准，不允许重复添加!",level:"warn"});
						return;
					}
				}
				
				if(dfop.ifEdit=='true'){
					if(validateRepeatByOrgIdAndItemName()) {
						$.messageBox({message:"已经存在该标准，不允许重复添加!",level:"warn"});
						return;
					}
					if($("#itemName").val()==""){
						$.messageBox({message:"请选择项目名称!",level:"warn"});
						return;
					}
				}
	        	formSubmit(form);
			},
			rules:{
				"issueStandardForFunOrg.itemName.id":{
					//required:true
				},
				"issueStandardForFunOrg.organization.id":{
					required:true
				},
				"issueStandardForFunOrg.yellowLimitAccept":{
					positiveInteger:true,
					required:true,
					mustFillOneItem:true,
					maxlength:2
				},
				"issueStandardForFunOrg.yellowLimitHandle":{
					positiveInteger:true,
					required:true,
					mustFillOneItem:true,
					maxlength:2
				},
				"issueStandardForFunOrg.yellowLimitVerify":{
					positiveInteger:true,
					required:true,
					mustFillOneItem:true,
					maxlength:2
				},
				"issueStandardForFunOrg.redLimitAccept":{
					positiveInteger:true,
					required:true,
					mustFillOneItem:true,
					limitAcceptValidator:true,
					maxlength:2
				},
				"issueStandardForFunOrg.redLimitHandle":{
					positiveInteger:true,
					required:true,
					mustFillOneItem:true,
					limitHandleValidator:true,
					maxlength:2
				},
				"issueStandardForFunOrg.redLimitVerify":{
					positiveInteger:true,
					required:true,
					mustFillOneItem:true,
					limitVerifyValidator:true,
					maxlength:2
				}
			},
			messages:{
				"issueStandardForFunOrg.itemName.id":{
					required:"请选择项目名称"
				},
				"issueStandardForFunOrg.organization.id":{
					required:"请选择职能部门"
				},
				"issueStandardForFunOrg.yellowLimitAccept":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					required:"请输入黄牌受理时限",
					maxlength:"黄牌受理时限不能超过99天"
				},
				"issueStandardForFunOrg.yellowLimitHandle":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					required:"请输入黄牌办理时限",
					maxlength:"黄牌办理时限不能超过99天"
				},
				"issueStandardForFunOrg.yellowLimitVerify":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					required:"请输入黄牌验证时限",
					maxlength:"黄牌验证时限不能超过99天"
				},			
				"issueStandardForFunOrg.redLimitAccept":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					limitAcceptValidator:"红牌受理时限需大于黄牌受理时限",
					required:"请输入红牌受理时限",
					maxlength:"红牌受理时限不能超过99天"
				},
				"issueStandardForFunOrg.redLimitHandle":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					limitHandleValidator:"红牌办理时限需大于黄牌办理时限",
					required:"请输入红牌办理时限",
					maxlength:"红牌办理时限不能超过99天"
				},
				"issueStandardForFunOrg.redLimitVerify":{
					positiveInteger:"请输入正整数",
					mustFillOneItem:"办理时限至少填写一项",
					limitVerifyValidator:"红牌验证时限需大于黄牌验证时限",
					required:"请输入红牌验证时限",
					maxlength:"红牌验证时限不能超过99天"
				}
			}
		});
	});

	function validateRepeatByOrgIdAndItemName() {
		var isTrue = true;
		$.ajax({
			async: false,
			url: PATH+"/issues/issueStandardForFunOrgManage/validateRepeatByOrgIdAndItemName.action",
			data:{
				"issueStandardForFunOrg.organization.id" : $("#organizationId").val(),
				"issueStandardForFunOrg.itemName.id":$("#itemName").val(),
				"issueStandardForFunOrg.id":$("#issueStandardForFunOrgId").val()
			},
			success:function(responseData){
				isTrue = !responseData;
			}
		});
		return isTrue;
	}
	function validateRepeatByOrgIdAndItemNameByMoreItem() {
		var isTrue = true;
		$.ajax({
			async: false,
			url: PATH+"/issues/issueStandardForFunOrgManage/validateRepeatByOrgIdAndItemNameWithMoreItem.action",
			data:{
				"issueStandardForFunOrg.organization.id" : $("#organizationId").val(),
				"issueStandardForFunOrg.itemNameIds":$("#itemNameIds").val(),
				"issueStandardForFunOrg.id":$("#issueStandardForFunOrgId").val()
			},
			success:function(responseData){
				isTrue = !responseData;
			}
		});
		return isTrue;
	}

	function defaultSelect() {
		var count=$("#organizationId  option").length;
		 for(var i=0;i<count;i++){  
			if($("#organizationId ").get(0).options[i].value == dfop.organizationId){  
	            $("#organizationId ").get(0).options[i].selected = true;  
	            break;  
	        }  
	    }
	}

	function formSubmit(form){
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
	                	$.messageBox({message:"新增职能部门时限标准成功!"});
	                	$("#standardList").addRowData(data.id,data,"first");
	                }
	                if("edit"==$("#mode").val()){
	                	$.messageBox({message:"修改职能部门时限标准成功!"});
	                	$("#standardList").setRowData(data.id,data);
	                }
	                $("#issueDialog").dialog("close");
	                $("#standardList").trigger("reloadGrid");
	 	   },
	 	   error: function(XMLHttpRequest, textStatus, errorThrown){
	 	      alert("提交数据时发生错误");
	 	   }
		});
	}

}