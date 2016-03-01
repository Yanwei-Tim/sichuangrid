var viewHouseFamilyPrintDlg = function (dfop){
	$(document).ready(function(){
		loadList();
		function loadList(){
			$("#houseFamilyMemberList").jqGridFunction({
				width:400,
				height:200,
				datatype: "local",
				colModel:[
					{name:"name",index:"name",label:"姓名",hidden:false},
					{name:'gender',label:"性别",sortable:true,formatter:genderFormatter,width:70,align:"center"},
					{name:"idCardNo",index:"idCardNo",label:"身份证号码",hidden:false},
					{name:"detailNativeAddress",index:"detailNativeAddress",label:"户籍地",hidden:false},
					{name:'relationShipWithHead',label:"与户主关系",formatter:relationShipWithHeadFormatter,sortable:false,hidden:false}
				],
				multiselect:false,
				rowNum:Math.pow(2,31)-1,
			    shrinkToFit:true,
			    height:'auto'
			});
			$("#houseFamilyMemberList").jqGrid('setFrozenColumns');
			var initParam = {
				"orgId": $("#orgId").val(),
				"houseFamily.id": $("#houseFamilyId").val()
			}
			$("#houseFamilyMemberList").setGridParam({
				url: PATH + '/baseinfo/householdStaff/findHouseholdStaffByOrgIdAndId.action?householdStaffVo.logout=0',
				datatype: "json",
				page:1
			});
			$("#houseFamilyMemberList").setPostData(initParam);
			$("#houseFamilyMemberList").trigger("reloadGrid");
		}
	});
}