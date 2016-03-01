var viewHouseFamilyDlg = function (dfop){
	$(document).ready(function(){
		loadList();
		function loadList(){
			$("#houseFamilyMemberList").jqGridFunction({
				width:400,
				height:200,
				datatype: "local",
				colModel:[
					{name:"id",index:"id",sortable:false,hidden:true,frozen:true},
					{name:"encryptId",index:"id",sortable:false,hidden:true,frozen:true},
					{name:"name",index:"name",label:"姓名",formatter:nameFont,hidden:false},
					{name:'gender',label:"性别",sortable:true,formatter:genderFormatter,width:70,align:"center"},
					{name:"idCardNo",index:"idCardNo",label:"身份证号码",hidden:false},
					{name:"detailNativeAddress",index:"detailNativeAddress",label:"户籍地",hidden:false},
					{name:'relationShipWithHead',label:"与户主关系",formatter:relationShipWithHeadFormatter,sortable:false,hidden:false}
				],
				multiselect:false
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
	
	function viewDialog(event,rowid){
		if(null == rowid){
	 		return;
		}
		var rowData = $("#houseFamilyMemberList").getRowData(rowid);
		var rowId = rowData.encryptId;
		$("#houseHoldStaffViewDialog").createDialog({
			width: 800,
			height: 600,
			title:'查看户籍人口详细信息',
			modal : true,
			url: PATH + '/baseinfo/householdStaff/dispathByEncrypt.action?mode=view&population.id='+rowId,
			buttons: {
				"打印" : function(){
					printChoose(rowid);
		  	   	},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
		var evt = event || window.event;
		if (window.event) { 
		evt.cancelBubble=true; 
		} else { 
		evt.stopPropagation(); 
		} 
	}
}