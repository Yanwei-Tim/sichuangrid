var viewGroupFamilyMembers = function (dfop){
	$(document).ready(function(){
		jQuery("#groupFamilyMemberList").jqGridFunction({
			datatype: "local",
		 	colNames:['id','人员姓名','证件号码','性别','出生日期','联系手机','文化程度','常住地址'],
		 	height:$("#groupFamilyDialog").height()-100,
		   	colModel:[
			   {name:'id',index:'id',hidden:true,key:true,frozen:true},
	   	       {name:'population.name',width:80,frozen:true,sortable:false},
		       {name:'population.idCardNo',width:120,frozen:true,sortable:false},
		       {name:'population.gender',formatter:genderFormatter,width:40,frozen:true,sortable:false},
		       {name:"population.birthday",width:80,sortable:false},
		       {name:'population.mobileNumber',width:100,sortable:false},
		       {name:'population.schooling',formatter:schoolingFormatter,width:80,sortable:false},
		       {name:'population.currentAddress',width:200,sortable:false}
		   	],
		   	showColModelButton:false,
		   	ondblClickRow: function(rowId){
		   		viewPopulationInfo(rowId);
		   	}
		});
		jQuery("#groupFamilyMemberList").jqGrid('setFrozenColumns');
		showList();
		
		function viewPopulationInfo(rowId) {
			var actionUrl;
			var pid = rowId.substr(0, rowId.indexOf("_"));
			var type = rowId.substr(rowId.indexOf("_")+1);
			if(type == dfop.populationType_HOUSEHOLD_STAFF){
				actionUrl = PATH + '/baseinfo/householdStaff/dispath.action?mode=view&population.id='+pid;
			}else if(type == dfop.populationType_FLOATING_POPULATION){
				actionUrl = PATH + '/baseinfo/floatingPopulationManage/dispath.action?mode=view&population.id='+pid;
			}else if(type == dfop.populationType_UNSETTLED_POPULATION){
				actionUrl = PATH + '/baseinfo/unsettledPopulationManage/dispatchOperate.action?mode=view&unsettledPopulation.id='+pid;
			}else if(type == dfop.populationType_OVERSEA_STAFF) {
				actionUrl = PATH + '/baseinfo/overseaPersonnelManage/dispatch.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+pid;
			}else return;
			$("#groupFamilyPopulationDialog").createDialog({
				width: 800,
				height: 600,
				title:'查看家庭成员信息',
				modal : true,
				url:actionUrl,
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		}
	});
	
	function showList() {
		var initParam = {
			"groupFamily.id": dfop.familyId
		};
		$("#groupFamilyMemberList").setGridParam({
			url: PATH + '/baseinfo/familyInfo/findFamilyMembersByFamilyId.action',
			datatype: "json",
			page:1
		});
		$("#groupFamilyMemberList").setPostData(initParam);
		$("#groupFamilyMemberList").trigger("reloadGrid");
	}
}