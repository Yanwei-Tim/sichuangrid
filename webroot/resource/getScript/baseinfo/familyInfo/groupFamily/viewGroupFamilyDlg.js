var viewGroupFamilyDlg = function (dfop){
	function nameFont(el,options,rowData){
		return '<a href="javascript:viewDetail(\''+rowData.id+'\')"><span>'+rowData.population.name+'</span></a>';
	}
	function viewDetail(id){
		viewPopulationInfo(id);
	} 
	function viewPopulationInfo(rowId) {
		var actionUrl;
		var rowData = $("#groupFamilyMemberList").getRowData(rowId)
		var pid = rowData['population.encryptId'];
		var type = rowId.substr(rowId.indexOf("_")+1);
		if(type == dfop.populationType_HOUSEHOLD_STAFF){
			actionUrl = PATH + '/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&population.id='+pid;
		}else if(type == dfop.populationType_FLOATING_POPULATION){
			actionUrl = PATH + '/baseinfo/floatingPopulationManage/dispathByEncrypt.action?mode=view&population.id='+pid;
		}else if(type == dfop.populationType_UNSETTLED_POPULATION){
			actionUrl = PATH + '/baseinfo/unsettledPopulationManage/dispatchOperateByEncrypt.action?mode=view&unsettledPopulation.id='+pid;
		}else if(type == dfop.populationType_OVERSEA_STAFF) {
			actionUrl = PATH + '/baseinfo/overseaPersonnelManage/dispatchByEncrypt.action?mode=view&isHiddenPersonnelTrack=1&overseaPersonnel.id='+pid;
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
	$(document).ready(function(){
		jQuery("#groupFamilyMemberList").jqGridFunction({
			datatype: "local",
		 	colNames:['id','encryptId','人员姓名','与家长的关系','证件号码','性别','出生日期','联系手机','文化程度','常住地址'],
		 	height:$("#groupFamilyDialog").height()-190,
		   	colModel:[
			   {name:'id',index:'id',frozen:true,hidden:true,key:true},
			   {name:'population.encryptId',frozen:true,hidden:true,key:true},
	   	       {name:'population.name',width:80,frozen:true,formatter:nameFont,sortable:false},
	   	       {name:"familyRelation.displayName",width:80,sortable:false},
		       {name:'population.idCardNo',width:150,frozen:true,sortable:false},
		       {name:'population.gender',formatter:genderFormatter,width:40,align:'center',frozen:true,sortable:false},
		       {name:"population.birthday",width:80,sortable:false,align:'center'},
		       {name:'population.mobileNumber',width:100,sortable:false,align:'center'},
		       {name:'population.schooling',formatter:schoolingFormatter,width:80,sortable:false},
		       {name:'population.currentAddress',width:200,sortable:false}
		   	],
		   	showColModelButton:false,
		   	ondblClickRow: function(rowId){
		   		viewPopulationInfo(rowId);
		   	}
		});
		showList();
		
	});
	
	function showList() {
		var initParam = {
			"groupFamily.id": $("#groupFamilyId").val()
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