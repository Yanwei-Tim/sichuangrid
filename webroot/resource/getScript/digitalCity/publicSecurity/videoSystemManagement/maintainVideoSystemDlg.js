TQ.maintainVideoSystemDlg = function (dfop){
	$(document).ready(function(){
		var modeValue=$("#mode").val();
		if($("#gisType").val()=="2D" || gisType=="2D"){
			$("#videoSystem-centerLon").val(dfop.centerLonTwo);
			$("#videoSystem-centerLat").val(dfop.centerLatTwo);
		}
		$("#bindMap").click(function(){
			$("#gisvideoSystemDialog").createDialog({
			  zIndex:1020,
	          width: 800,
	          height: 600,
	          title:'绑定建筑物',
	          url:PATH+"/openLayersMap/product_3.0/gisPublicSecurity.jsp?flag=1&dailogName=videoSystem&organizationId="+getCurrentOrgId()+"&gisType="+$("#gisType").val(),
	          buttons: {
	              "关闭" : function(){
	            	  $("#videoSystemList").trigger("reloadGrid");
	                 $(this).dialog("close");
	              }
	          },
	          shouldEmptyHtml:false
			});
		});
		$("#cancelBind").click(function(){
			$("#videoSystem-centerLon").val("");
			$("#videoSystem-centerLat").val("");
		});
		
		
		
		jQuery.validator.addMethod("isNumber", validatorNo);
		function validatorNo(value,element){
			if(value==null||value==undefined||value=="" ){return true};
			var patrn=/^[0-9]{3}$/;
			if (!patrn.exec(value.replace(/[ ]/g,""))) return false
			return true
		}
		
		var videoNoData;
		jQuery.validator.addMethod("exsistedVideoNo", function(value, element){
			var value=$('#videoNo').val();
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:PATH+"/videoSystemManage/hasDuplicateVideoNo.action",
			   	data:{
					"videoSystem.videoNo":$('#videoNo').val(),
					"videoSystem.organization.id":$('#organizationId').val(),
					"videoSystem.id":$('#id').val()
		        },
				success:function(responseData){
					videoNoData = responseData;
				}
			});
			if(!(videoNoData==null||videoNoData=="")){
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
							$.messageBox({message:"视频监控修改成功!"});
							$("#videoSystemDialog").dialog("close");
						}
						if('add'==modeValue){
							onOrgChanged(getCurrentOrgId(),isGrid());
							$.messageBox({message:"视频监控新增成功!"});
							$("#videoSystemDialog").dialog("close");
						}
		      	   	},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		      	    	alert("提交数据时发生错误");
		      	   	}
		      	});
			},
			rules:{
				"videoSystem.videoNo":{
					required:true,
					exsistedVideoNo:true
				},
				"videoSystem.address":{
					required:true,
					minlength:0,
					maxlength:30
				},
				"videoSystem.url":{
					required:true
				},
				"videoSystem.account":{
					required:true
				},
				"videoSystem.password":{
					required:true
				}
					
					
			},
			messages:{
				"videoSystem.videoNo":{
					required:"请输入视频监控编号",
					exsistedVideoNo:function(){
						return videoNoData;
					}
				},
				"videoSystem.address":{
					required:"请输入视频监控地址",
					minlength:$.format("单位地址至少需要输入{0}个字符"),
					maxlength:$.format("单位地址最多需要输入{0}个字符")
				},
				"videoSystem.url":{
					required:"请输入网络地址"
				},
				"videoSystem.account":{
					required:"请输账号"
				},
				"videoSystem.password":{
					required:"请输入密码"
				}
			},
			ignore:':hidden'
		});
	if('add'==modeValue){
		$("#maintainForm").attr("action",PATH+"/videoSystemManage/addVideosystem.action");
	}
	if('edit'==modeValue){
		$("#maintainForm").attr("action",PATH+"/videoSystemManage/updateVideosystem.action");
	}
	if('add'==modeValue){
		$.ajax({
			async: false,
			url: PATH+"/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#videoSystemOrgName").val(responseData);
			}
		});
	}
	});
}