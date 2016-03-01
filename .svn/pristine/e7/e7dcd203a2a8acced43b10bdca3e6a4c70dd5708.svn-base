TQ.mainDustbinDlg = function (dfop){
	if($("#partNameId").val()!=""){
		$.ajax({
			async: false,
			url: PATH+"/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDict.id" :$("#partNameId").val()
			},
			success:function(responseData){
				if(responseData.displayName=="监控电子眼"){
					$("#hasVideoShow").show();
				}
			}
		});
	}
	
	$("#partNameId").change(function(){
		$.ajax({
			async: false,
			url: PATH+"/sysadmin/propertyManage/getPropertyDictById.action",
			data:{
				"propertyDict.id" :$("#partNameId").val()
			},
			success:function(responseData){
				if(responseData.displayName=="监控电子眼"){
					$("#hasVideoShow").show();
				}else{
					$("#hasVideoShow").hide();
					$("#hasVideo").attr("checked",false);
				}
			}
		});
	});
	
	$("#partType").change(function(){
		$("#hasVideoShow").hide();
		$("#hasVideo").attr("checked",false);
	});
	
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src",PATH+"/"+$("#_imgUrl").val()+"?r="+Math.random());
	};
	
	
	
	$(document).ready(function(){
		if(""!=$("#_imgUrl").val()){
			$("#img").attr("src",$("#_imgUrl").val());
		}
		
		$("#maintainForm").formValidate({
			showErrors:showErrorsForTab,
			promptPosition: "bottomLeft",
			submitHandler: function(form){
			$("select[disabled]").removeAttr("disabled");
			$("#_imgUrl").attr("src",$("#_imgUrl").val());
				$(form).ajaxSubmit({
					success:function(data){
						if(!data.id){
							$.messageBox({
								message:data,
								level: "error"
				 			});
	            			return;
						}
						if("add"==$("#mode").val()){
							 $.messageBox({message:"部件信息新增成功"});
					         doAction("dustbinMaintanceDialog",data.id);
							 $("#dustbinList").trigger("reloadGrid");
						 }
						 if("edit"==$("#mode").val()){
							 $("#dustbinList").setRowData(data.id,data);
							 $.messageBox({message:"部件信息修改成功"});
						 } 
						 doAction(dfop.dailogName,data.id);
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
	  	      			alert("提交数据时发生错误");
		   		    }
				});
			},
			rules:{
			"dustbin.dustbinCode":{
				required:true,
				isCodeValidate:true,
				minlength:1,
				maxlength:20
			},
			"dustbin.partType.id":{
				required:true
			},
			"dustbin.partName.id":{
				required:true
			},
			"dustbin.address":{
				minlength:0,
				maxlength:30
			},
			"dustbin.deptName":{
				required:true,
				minlength:1,
				maxlength:20
			},
			"dustbin.ownershipUnitName":{
				minlength:1,
				maxlength:20
			},
			"dustbin.maintenanceUnitName":{
				minlength:1,
				maxlength:20
			},
			"dustbin.remark":{
				maxlength:300
			}
		},
			messages:{
			"dustbin.dustbinCode":{
				required:"请输入部件标识码",
				isCodeValidate:$.format("部件标识码只能输入英文和数字"),
				minlength:$.format("部件标识码至少需要输入{0}个字符"),
				maxlength:$.format("部件标识码最多需要输入{0}个字符")
			},
			"dustbin.partType.id":{
				required:"请输入部件类型"
			},
			"dustbin.partName.id":{
				required:"请输入部件名称"
			},
			"dustbin.address":{
				minlength:$.format("单位地址至少需要输入{0}个字符"),
				maxlength:$.format("单位地址最多需要输入{0}个字符")
			},
			"dustbin.deptName":{
				required:"请输入主管部门名称",
				minlength:$.format("主管部门名称至少需要输入{0}个字符"),
				maxlength:$.format("主管部门名称最多需要输入{0}个字符")
			},
			"dustbin.ownershipUnitName":{
				minlength:$.format("权属单位名称至少需要输入{0}个字符"),
				maxlength:$.format("权属单位名称最多需要输入{0}个字符")
			},
			"dustbin.maintenanceUnitName":{
				minlength:$.format("养护单位名称至少需要输入{0}个字符"),
				maxlength:$.format("养护单位名称最多需要输入{0}个字符")
			},
			"dustbin.remark":{
				maxlength:$.format("备注最多需要输入{0}个字符")
			}
		}
		});
	});
}