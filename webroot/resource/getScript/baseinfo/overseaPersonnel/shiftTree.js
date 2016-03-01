TQ.overseaPersonnelShiftTree = function (dfop){
	var orgSelector;
	function closeDialog(){
		var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
		if (selectOrgId!=null){
			$("#shiftOrgId").val(selectOrgId);
		}
	}
	$(document).ready(function(){
		var orgPangtId = $("#orgPangtId").val();
		var orgId = $("#orgId").val();
		orgSelector=$("#organizationTree").initTree({rootId:orgPangtId,
			loadCom:function(){
				$.disableNode(orgSelector,orgId);
				$.disableNode(orgSelector,orgPangtId);
		}
			});
		$.addClick(orgSelector,closeDialog);
		jQuery.validator.addMethod("isGridOrganization", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			$.ajax({
				async:false,
				url: PATH + "/sysadmin/orgManage/isGridOrganization.action",
				data:{
					"organization.id":$("#shiftOrgId").val()
				},
				success:function(responseData){
					bol = responseData;
				}
			});
			return bol;
		});
	
		jQuery.validator.addMethod("isModernOrg", function(value, element){
			if($("#orgId").val()==$("#shiftOrgId").val()){
				return false;
			}
			return true;
		});
		
		$("#maintainShiftForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
	                     if(data){
	                    	 $.messageBox({message:"数据转移成功!"});
	                    	 $("#overseaPersonnelList").trigger("reloadGrid");
	     					$("#overseaPersonnelMaintanceDialog").dialog("close");
	                     }
	      	   },
		      error: function(data){
	      		 $.messageBox({
						message:data,
						level: "error"
					 });
		      	   }
		       });
			},
			rules:{"overseaPersonnel.organization.id":{isGridOrganization:true,isModernOrg:true}},
			messages:{"overseaPersonnel.organization.id":{isGridOrganization:"数据只能转移得到网格",isModernOrg:"数据不能转移到当前网格"}}
		});
	});
}