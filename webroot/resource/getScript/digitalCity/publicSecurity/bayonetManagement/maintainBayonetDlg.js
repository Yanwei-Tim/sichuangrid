TQ.maintainBayonetDlg = function (dfop){
	$(document).ready(function(){
		var modeValue=$("#mode").val();
		if($("#gisType").val()=="2D" || gisType=="2D"){
			$("#bayonet-centerLon").val(dfop.centerLonTwo);
			$("#bayonet-centerLat").val(dfop.centerLatTwo);
		}
		$("#bindMap").click(function(){
			$("#gisbayonetDialog").createDialog({
			  zIndex:1020,
	          width: 800,
	          height: 600,
	          title:'绑定建筑物',
	          url:PATH+"/openLayersMap/product_3.0/gisPublicSecurity.jsp?flag=1&dailogName=bayonet&organizationId="+getCurrentOrgId()+"&gisType="+$("#gisType").val(),
	          buttons: {
	              "关闭" : function(){
	            	  $("#bayonetList").trigger("reloadGrid");
	                 $(this).dialog("close");
	              }
	          },
	          shouldEmptyHtml:false
			});
		});
		$("#cancelBind").click(function(){
			$("#bayonet-centerLon").val("");
			$("#bayonet-centerLat").val("");
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
							$.messageBox({message:"卡口修改成功!"});
							$("#bayonetDialog").dialog("close");
						}
						
						if('add'==modeValue){
							onOrgChanged(getCurrentOrgId(),isGrid());
							$.messageBox({message:"卡口新增成功!"});
							$("#bayonetDialog").dialog("close");
						}
		      	   	},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		      	    	alert("提交数据时发生错误");
		      	   	}
		      	});
			},
			rules:{
				"bayonet.bayonetNo":{
					required:true,
					isNumber:true,
					exsistedBayonetNo:true
				},
				"bayonet.address":{
					minlength:0,
					maxlength:30
				}
			},
			messages:{
				"bayonet.bayonetNo":{
					required:"请输入卡口编号",
					isNumber:"卡口编号只能输入3位数字",
					exsistedBayonetNo:function(){
						return bayonetNoData;
					}
				},
				"bayonet.address":{
					minlength:$.format("单位地址至少需要输入{0}个字符"),
					maxlength:$.format("单位地址最多需要输入{0}个字符")
				}
			},
			ignore:':hidden'
		});
		jQuery.validator.addMethod("isNumber", validatorNo);
		function validatorNo(value,element){
			if(value==null||value==undefined||value=="" ){return true};
			var patrn=/^[0-9]{3}$/;
			if (!patrn.exec(value.replace(/[ ]/g,""))) return false
			return true
		}
		var bayonetNoData;
		jQuery.validator.addMethod("exsistedBayonetNo", function(value, element){
			var value=$('#bayonetNo').val();
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:PATH+"/bayonetManage/hasDuplicateBayonetNo.action",
			   	data:{
					"bayonet.bayonetNo":$('#bayonetNo').val(),
					"bayonet.organization.id":$('#organizationId').val(),
					"bayonet.id":$('#id').val()
		        },
				success:function(responseData){
					bayonetNoData = responseData;
				}
			});
			if(!(bayonetNoData==null||bayonetNoData=="")){
				return false;
			}
			return true;
		});
		
	if('add'==modeValue){
		$("#maintainForm").attr("action",PATH+"/bayonetManage/addBayonet.action");
	}
	if('edit'==modeValue){
		$("#maintainForm").attr("action",PATH+"/bayonetManage/updateBayonet.action");
	}
	
	if('add'==modeValue){
		$.ajax({
			async: false,
			url: PATH+"/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#bayonetOrgName").val(responseData);
			}
		});
	}
	
	});

}


