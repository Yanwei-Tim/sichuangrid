TQ.maintainSkynetDlg = function (dfop){
	$(document).ready(function(){
		var modeValue=$("#mode").val();
		if($("#gisType").val()=="2D" || gisType=="2D"){
			$("#skynet-centerLon").val(dfop.centerLonTwo);
			$("#skynet-centerLat").val(dfop.centerLatTwo);
		}
		$("#bindMap").click(function(){
			$("#gisskynetDialog").createDialog({
			  zIndex:1020,
	          width: 800,
	          height: 600,
	          title:'绑定建筑物',
	          url:PATH+"/openLayersMap/product_3.0/gisPublicSecurity.jsp?flag=1&dailogName=skynet&organizationId="+getCurrentOrgId()+"&gisType="+$("#gisType").val(),
	          buttons: {
	              "关闭" : function(){
	            	  $("#skynetList").trigger("reloadGrid");
	                 $(this).dialog("close");
	              }
	          },
	          shouldEmptyHtml:false
			});
		});
		$("#cancelBind").click(function(){
			$("#skynet-centerLon").val("");
			$("#skynet-centerLat").val("");
		});
		
		
		jQuery.validator.addMethod("isNumber", validatorNo);
		function validatorNo(value,element){
			if(value==null||value==undefined||value=="" ){return true};
			var patrn=/^[0-9]{3}$/;
			if (!patrn.exec(value.replace(/[ ]/g,""))) return false
			return true
		}
		
		var skynetNoData;
		jQuery.validator.addMethod("exsistedSkynetNo", function(value, element){
			var value=$('#skynetNo').val();
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:PATH+"/skynetManage/hasDuplicateSkynetNo.action",
			   	data:{
					"skynet.skynetNo":$('#skynetNo').val(),
					"skynet.organization.id":$('#organizationId').val(),
					"skynet.id":$('#id').val()
		        },
				success:function(responseData){
					skynetNoData = responseData;
				}
			});
			if(!(skynetNoData==null||skynetNoData=="")){
				return false;
			}
			return true;
		});
		
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
		        $(form).ajaxSubmit({
					success: function(data){
			        	if(!data.id){
							$.messageBox({
								message:data,
								level: "error"
							});
							return;
						}
						if(data.organization){
							data["organization.orgName"]=data.organization.orgName;
						}
						if('edit'==modeValue){
							onOrgChanged(getCurrentOrgId(),isGrid());
							$.messageBox({message:"天网修改成功!"});
							$("#skynetDialog").dialog("close");
						}
						if('add'==modeValue){
							onOrgChanged(getCurrentOrgId(),isGrid());
							$.messageBox({message:"天网新增成功!"});
							$("#skynetDialog").dialog("close");
						}
		      	   	},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		      	    	alert("提交数据时发生错误");
		      	   	}
		      	});
			},
			rules:{
				"skynet.skynetNo":{
					required:true,
					isNumber:true,
					exsistedSkynetNo:true
				},
				"skynet.address":{
					minlength:0,
					maxlength:30
				}
					
					
			},
			messages:{
				"skynet.skynetNo":{
					required:"请输入天网编号",
					isNumber:"天网编号只能输入3位数字",
					exsistedSkynetNo:function(){
						return skynetNoData;
					}
				},
				"skynet.address":{
					minlength:$.format("单位地址至少需要输入{0}个字符"),
					maxlength:$.format("单位地址最多需要输入{0}个字符")
				}
			},
			ignore:':hidden'
		});
	if('add'==modeValue){
		$("#maintainForm").attr("action",PATH+"/skynetManage/addSkynet.action");
	}
	if('edit'==modeValue){
		$("#maintainForm").attr("action",PATH+"/skynetManage/updateSkynet.action");
	}
	if('add'==modeValue){
		$.ajax({
			async: false,
			url: PATH+"/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#skynetOrgName").val(responseData);
			}
		});
	}
	});
}