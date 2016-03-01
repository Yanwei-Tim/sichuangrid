var maintainHouseFamilyMemberDlg = function (dfop){
	$(document).ready(function(){
		loadList();
		$("#addMem").click(function(){
			var newIdCardNo = $("#newIdCardNo").val();
			var newRelationShipWithHeadId = $("#newRelationShipWithHeadId").val();
			var accountNumber = $("#houseFamilyAccountNumber").val();
			if(newRelationShipWithHeadId == '' || newRelationShipWithHeadId == null){
				$.messageBox({ message:"与户主关系字段为空!",level:'warn'});
				return;
			}
			if(newIdCardNo == '' || newIdCardNo == null){
				$.messageBox({ message:"身份证号码字段为空!",level:'warn'});
				return;
			}else{
				$.ajax({
					type:'post',
					url: PATH + '/baseinfo/houseFamily/haveRepatCardOrNo.action',
					data:{'card':newIdCardNo},
					success:function(data){
						if(data){
							addFun();
						}else{
							$.messageBox({ message:"此身份证号码不存在!",level:'warn'});
							return;
						}
					}
				});
			}
		});
		function loadList(){
			$("#houseFamilyMemberList").jqGridFunction({
				width:600,
				height:300,
				datatype: "local",
				colModel:[
					{name:"id",label:"id",hidden:false},
					{name:"name",index:"name",label:"姓名",formatter:nameFont,hidden:false},
					{name:'gender',label:"性别",sortable:true,formatter:genderFormatter,width:70,align:"center"},
					{name:"idCardNo",index:"idCardNo",label:"身份证号码",hidden:false},
					{name:"detailNativeAddress",index:"detailNativeAddress",label:"户籍地",hidden:false},
					{name:'relationShipWithHead',label:"与户主关系",formatter:relationShipWithHeadFormatter,sortable:false,hidden:false},
					{name:'operation',label:"操作",width:80,formatter:operateFormatter,frozen:true,sortable:false,align:"center"}
				],
				multiselect:false
			});
			$("#houseFamilyMemberList").jqGrid('setFrozenColumns');
			var initParam = {
				"orgId": $("#orgId").val(),
				"houseFamily.id": $("#houseFamilyId").val()
			}
			$("#houseFamilyMemberList").setGridParam({
				url: PATH + '/baseinfo/householdStaff/findHouseholdStaffByOrgIdAndId.action?householdStaffVo.logout=0&exceptHead=true',
				datatype: "json",
				page:1
			});
			$("#houseFamilyMemberList").setPostData(initParam);
			$("#houseFamilyMemberList").trigger("reloadGrid");
		}
		function addFun(){
			var newIdCardNo = $("#newIdCardNo").val();
			var newRelationShipWithHeadId = $("#newRelationShipWithHeadId").val();
			var accountNumber = $("#houseFamilyAccountNumber").val();
			$.confirm({
				title:"确认添加",
				message:'添加后将脱离原有家庭,确认添加吗?',
				width: 400,
				okFunc: function(){
					$.ajax({
						url: PATH + '/baseinfo/houseFamily/addFamilyMemberByIdCardNo.action?orgId='+$("#orgId").val()+'&houseFamily.id='+$("#houseFamilyId").val()+'&newFamilyMember.idCardNo='+newIdCardNo+'&newFamilyMember.relationShipWithHead.id='+newRelationShipWithHeadId+'&newFamilyMember.accountNumber='+accountNumber,
						success:function(data){
							if(data == true || data=="true"){
								/**
								var oldNum = $("#houseFamilyMemberList").getGridParam("records");
								$("#houseFamilyMemberList").trigger("reloadGrid");
								var newNum = $("#houseFamilyMemberList").getGridParam("records");
								if(oldNum == newNum){
									$.messageBox({ level:"warn",message:"此人在该网格下不存在或在该户籍下已存在"});
								}else{
									*/
								$.messageBox({ message:"成功新增家庭成员!"});
								$("#houseFamilyMemberList").trigger("reloadGrid");
								$("#houseFamilyList").trigger("reloadGrid");
								return;
							}else{
								$.messageBox({ message:data,level: "error"});
							}
				        }
					});
				}
			});
			
		}
		function operateFormatter(el, options, rowData){
			return "<a href='javascript:removeFun("+rowData.id+")'><U><font color='#6633FF'>移除</font></U></a>";
		}
	});
	function removeFun(rowId){
		if(rowId == null || rowId == undefined){
			return;
		}
		var newIdCardNo = $("#houseFamilyMemberList").getRowData(rowId).idCardNo;
		$("#houseFamilyMemberDialog").createDialog({
			width:400,
			height:300,
			title:'移除成员',
			url: PATH + '/baseinfo/houseFamily/dispatchOperate.action?mode=move&orgId='+$("#orgId").val()+'&houseFamily.id='+$("#houseFamilyId").val()+'&newFamilyMember.idCardNo='+newIdCardNo,
			buttons : {
				"保存" : function(){
					$("#moveHouseMemberForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
}