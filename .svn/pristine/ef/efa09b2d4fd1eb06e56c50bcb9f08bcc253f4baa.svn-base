TQ.maintainSnapshotSystemDlg = function (dfop){
	$(document).ready(function(){
		var modeValue=$("#mode").val();
		if($("#gisType").val()=="2D" || gisType=="2D"){
			$("#snapshotSystem-centerLon").val(dfop.centerLonTwo);
			$("#snapshotSystem-centerLat").val(dfop.centerLatTwo);
		}
		$("#bindMap").click(function(){
			$("#gissnapshotSystemDialog").createDialog({
			  zIndex:1020,
	          width: 800,
	          height: 600,
	          title:'绑定建筑物',
	          url:PATH+"/openLayersMap/product_3.0/gisPublicSecurity.jsp?flag=1&dailogName=snapshotSystem&organizationId="+getCurrentOrgId()+"&gisType="+$("#gisType").val(),
	          buttons: {
	              "关闭" : function(){
	            	  $("#snapshotSystemList").trigger("reloadGrid");
	                 $(this).dialog("close");
	              }
	          },
	          shouldEmptyHtml:false
			});
		});
		$("#cancelBind").click(function(){
			$("#snapshotSystem-centerLon").val("");
			$("#snapshotSystem-centerLat").val("");
		});
		
		
		
		jQuery.validator.addMethod("isNumber", validatorNo);
		function validatorNo(value,element){
			if(value==null||value==undefined||value=="" ){return true};
			var patrn=/^[0-9]{3}$/;
			if (!patrn.exec(value.replace(/[ ]/g,""))) return false
			return true
		}
		
		var snapshotNoData;
		jQuery.validator.addMethod("exsistedSnapshotNo", function(value, element){
			var value=$('#snapshotNo').val();
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async: false ,
				url:PATH+"/snapshotSystemManage/hasDuplicateSnapshotNo.action",
			   	data:{
					"snapshotSystem.snapshotNo":$('#snapshotNo').val(),
					"snapshotSystem.organization.id":$('#organizationId').val(),
					"snapshotSystem.id":$('#id').val()
		        },
				success:function(responseData){
					snapshotNoData = responseData;
				}
			});
			if(!(snapshotNoData==null||snapshotNoData=="")){
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
							$.messageBox({message:"抓拍系统修改成功!"});
							$("#snapshotSystemDialog").dialog("close");
						}
						if('add'==modeValue){
							onOrgChanged(getCurrentOrgId(),isGrid());
							$.messageBox({message:"抓拍系统新增成功!"});
							$("#snapshotSystemDialog").dialog("close");
						}
		      	   	},
		      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		      	    	alert("提交数据时发生错误");
		      	   	}
		      	});
			},
			rules:{
				"snapshotSystem.snapshotNo":{
					required:true,
					isNumber:true,
					exsistedSnapshotNo:true
				},
				"snapshotSystem.address":{
					minlength:0,
					maxlength:30
				}
					
					
			},
			messages:{
				"snapshotSystem.snapshotNo":{
					required:"请输入抓拍系统编号",
					isNumber:"抓拍系统编号只能输入3位数字",
					exsistedSnapshotNo:function(){
						return snapshotNoData;
					}
				},
				"snapshotSystem.address":{
					minlength:$.format("单位地址至少需要输入{0}个字符"),
					maxlength:$.format("单位地址最多需要输入{0}个字符")
				}
			},
			ignore:':hidden'
		});
	if('add'==modeValue){
		$("#maintainForm").attr("action",PATH+"/snapshotSystemManage/addSnapshotsystem.action");
	}
	if('edit'==modeValue){
		$("#maintainForm").attr("action",PATH+"/snapshotSystemManage/updateSnapshotsystem.action");
	}
	if('add'==modeValue){
		$.ajax({
			async: false,
			url: PATH+"/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#snapshotSystemOrgName").val(responseData);
			}
		});
	}
	});
}