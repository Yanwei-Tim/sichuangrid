TQ.volunteerMemberList = function (dfop){
	function searchMemberData(postData) {
		$("#memberList").setGridParam({
			url:dfop.path+"/volunteerMemberManage/findMemberPagerBySearchVo.action",
			datatype: "json",
			page:1
		});
		$("#memberList").setPostData(postData);
		$("#memberList").trigger("reloadGrid");
	}

	function search(){
		var conditions = $("#searchTextMember").val();
		if(conditions == '请输入姓名或身份证号码')  conditions="";
		loadMemberData(conditions);
	}

	function addressFormatter(el, options, rowData){
		if(rowData.province==null){
			return '';
		}
		if(rowData.city==null){
			return rowData.province;
		}
		if(rowData.district==null){
			return rowData.province+'-'+rowData.city;
		}
		return rowData.province+'-'+rowData.city+'-'+rowData.district;
	}

	$(function (){
		if(partyOrgType==dfop.regionPartyOrg){
			autoHeight=220;
		}
		$("#memberList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-autoHeight,
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false},
		        {name:"idCardNo",sortable:true,label:'身份证号',width:150},
		        {name:"name",sortable:true,label:'姓名',width:100},
		        {name:"gender",sortable:true,label:'性别',width:100,formatter:genderFormatter},
		        {name:"address",sortable:false,label:'户籍地',width:180,formatter:addressFormatter},
		        {name:"nativePlaceAddress",sortable:false,label:'现居地址',width:180}
			],
			multiselect: true,
		    ondblClickRow: viewMember
		});
		
		$("#fastSearchButtonMember").click(function(){
			var conditions = $("#searchTextMember").val();
			if(conditions == '请输入姓名或身份证号码') conditions="";
			searchMemberData({
				"searchVo.teamId":dfop.teamId,
				"searchVo.orgId":dfop.orgId,
				"searchVo.searchValue":conditions
			})
		});
		$("#searchTextMember").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKeyMember").click(function(){
			$("#searchTextMember").attr("value","请输入姓名或身份证号码");
		});
		
		
		$("#addMember").click(function(){
			$("#memberDialog").createDialog({
				title:"新增成员信息",
				width: dialogWidthMember,
				height: dialogHeightMember,
				url:dfop.path+"/volunteerMemberManage/dispatch.action?mode=add&orgId="+dfop.orgId+"&teamId="+dfop.teamId,
				buttons: {
		    		   "保存" : function(){
		    		        $("#maintainForm").submit(); 
		    		   },
		    		   "关闭" : function(){
		    		        $(this).dialog("close"); 
		    		   }
		    		}
			});
		});
		
		$("#deleteMember").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			$.confirm({
				title:"确认删除",
				message:"确定要删除吗?一经删除将无法恢复",
				okFunc: function() {
					$.ajax({
						url:dfop.path+'/volunteerMemberManage/deleteByTeamIdAndMemberId.action?teamId='+dfop.teamId+'&memberIds='+allValue,
						success:function(data){
							if(data!=null && data==true) {
								$.messageBox({message:"已经成功删除该成员信息!"});
								$("#memberList").trigger("reloadGrid");
							} else {
								$.messageBox({message:data,level:"error"});
							}
						}
					});
				}
			});
		});
		
		$("#viewMember").click(function(){
			var selectedIds = getSelectedIds();
			var selectedId = getSelectedIdLast();
			if(selectedId==null || selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewMember(selectedId);
		});
		
		
		$("#importMember").click(function(event){
			$("#memberDialog").createDialog({
				width: 400,
				height: 230,
				title:"数据导入",
				url:dfop.path+"/common/import.jsp?isNew=1&dataType=member&dialog=memberDialog&startRow=5&templates=member&listName=memberList&module="+getModule(),
				buttons:{
					"导入" : function(event){
						$("#mForm").submit();
					},
				   	"关闭" : function(){
				    	$(this).dialog("close");
				   	}
				},
				shouldEmptyHtml:false
			});
		});
		
		$("#reloadMember").click(function(){
			$("#searchTextMember").attr("value","请输入姓名或身份证号码");
			searchMemberData({
				"searchVo.teamId":dfop.teamId,
				"searchVo.orgId":dfop.orgId
			})
		}).click();
	});

	function getModule() {
		if(partyOrgType==dfop.officePartyOrg){
			return dfop.officePartyOrg;
		}
		if(partyOrgType==dfop.townPartyOrg){
			return dfop.townPartyOrg;
		}
		if(partyOrgType==dfop.businessOrg){
			return dfop.businessOrg;
		}
		if(partyOrgType==dfop.collectiveOrg){
			return dfop.collectiveOrg;
		}
		if(partyOrgType==dfop.twoNewParty){
			return dfop.twoNewParty;
		}
		if(partyOrgType==dfop.twoNewPartyOrg){
			return dfop.twoNewPartyOrg;
		}
		if(partyOrgType==dfop.systemPartyOrg){
			return dfop.systemPartyOrg;
		}
		if(partyOrgType==dfop.regionPartyOrg){
			return dfop.regionPartyOrg;
		}
		if(partyOrgType==dfop.doubleReg){
			return dfop.doubleReg;
		}
	}

	function viewMember(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#memberList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#memberDialog").createTabDialog({
			title:"查看成员信息",
			width: dialogWidthMember,
			height: dialogHeightMember-50,
			postData:{
				imageType:"population"
			},
			tabs:[
					{title:'成员信息',url:dfop.path+'/volunteerMemberManage/dispatch.action?mode=view&memberIds='+selectedId}
				]
		});
	}

	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	function getSelectedIds(){
		var selectedIds = $("#memberList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}