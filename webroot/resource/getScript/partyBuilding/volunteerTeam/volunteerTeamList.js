TQ.volunteerTeamList = function (dfop){
	var dialogWidth = 520;
	var dialogHeight = 380;
	
	// 改变组织机构树时加载列表
	function onOrgChanged(orgId,isgrid){
		searchVolunteerTeam({
			"searchVo.orgId": orgId,
			"searchVo.displayLevel":$("#displayLevel").val()
		})
	}
	
	$(function(){
		
		if(isGrid()){
			$("#fastSearchSelect").hide();
		}
		
		function toggleButtonState(){
		  	var selectedIds=$("#volunteerTeamList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){
	
		}
	
		function memberNumFormatter(el,option,rowData){
			if(rowData.memberNum==null){
				return 0;
			}
			return rowData.memberNum;
		}
		
		// 生成列表
		$("#volunteerTeamList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:"organization.id",hidden:true,hidedlg:true},
				{name:'name', index:'name',label:'组织名称', width:200, sortable:true, align:'center', hidden:false}, 	
				{name:'memberNum', index:'memberNum',label:'成员数',formatter:memberNumFormatter, width:100, sortable:false, align:'center', hidden:false}, 	
		    	{name:'serviceDirection', index:'serviceDirection',label:'服务方向', width:200, sortable:true, align:'center', hidden:false}, 	
		    	{name:'organization.orgName', index:'',label:'所属区域', width:200, align:'center', hidden:false}, 	
		    	{name:'remark', index:'remark',label:'备注', width:200, sortable:true, align:'center', hidden:false}	
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewVolunteerTeam=='true'){
					viewVolunteerTeam(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#volunteerTeamList").jqGrid('setFrozenColumns');
		
		$("#fastSearchButton").click(function(){
			var fastSearchVal = $("#searchText").val();
			if(fastSearchVal == '请输入组织名称') return;
			searchVolunteerTeam({
				"searchVo.orgId":getCurrentOrgId(),
				"searchVo.name":fastSearchVal,
				"searchVo.displayLevel":$("#displayLevel").val()
			})
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入组织名称");
		});
		$("#add").click(function(){
			if (getCurrentOrgId()==null||getCurrentOrgId()==""){
				$.notice({level:"warn", message:"请先选择一个部门"});
			}else{
				$("#volunteerTeamDialog").createDialog({
					title:"新增党员志愿者队伍",
					width: dialogWidth,
					height: dialogHeight,
					url:dfop.path+"/volunteerTeamManage/dispatch.action?mode=add&organizationId="+getCurrentOrgId(),
					buttons: {
	   	    		   "保存" : function(){
	   	    		        $("#maintainForm").submit(); 
	   	    		   },
	   	    		   "关闭" : function(){
	  	    		        $(this).dialog("close"); 
	  	    		   }
	   	    		}
				});
			}
		});
		$("#update").click(function(){
			var selectedIds = getSelectedIdsForTeam();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				 return;
			}
			var selectedId = getSelectedIdLastForTeam();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				 return;
			}
			updateOperator(selectedId);
		});
		$("#delete").click(function(){
			var allValue = getSelectedIdsForTeam();
			if(allValue==null||allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			var flag=false;
			for(var i=0;i<allValue.length;i++) {
				var row = $("#volunteerTeamList").getRowData(allValue[i]);
				if(row.memberNum>0){
					flag=true;
					break;
				}
			}
			if(flag){
				$.messageBox({level:'warn',message:"所选的志愿者队伍有党员存在,不允许删除！"});
				return;
			}
			deleteOperator(allValue);
		});
		$("#view").click(function(){
			var selectedIds = getSelectedIdsForTeam();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				 return;
			}
			var selectedId = getSelectedIdLastForTeam();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				 return;
			}
			viewVolunteerTeam(selectedId)
		});
		$("#manageMember").click(function(){
			var selectedId = getSelectedIdsForTeam();
			if(selectedId==null||selectedId==''){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行党员维护！"});
				 return;
			}
			if(selectedId.length>1){
				$.messageBox({level : 'warn',message:"同时只能操作一条记录！"});
				 return;
			}
			var row = $("#volunteerTeamList").getRowData(selectedId);
			$("#volunteerTeamDialog").createDialog({
				width:950,
				height:600,
				title:'党员志愿者队伍成员维护',
	 	 		url:dfop.path+"/partyBuilding/volunteerTeam/volunteerMemberList.jsp?teamId="+row.encryptId+"&orgId="+row["organization.id"],
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				},
				close:function(){
					$("#volunteerTeamList").trigger("reloadGrid");
				}
			});
		})
		
		$("#displayLevel").change(function(event){
			searchVolunteerTeam({
				"searchVo.orgId": getCurrentOrgId(),
				"searchVo.displayLevel":$(this).val()
			})
		});
		
		$("#reload").click(function(){
			$("#searchText").attr("value","请输入组织名称");
			onOrgChanged(getCurrentOrgId(),isGrid());
		}).click();
	});
	
	function viewVolunteerTeam(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData=  $("#volunteerTeamList").getRowData(selectedId);
		$("#volunteerTeamDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看党员志愿者队伍信息",
	 		url:dfop.path+'/volunteerTeamManage/dispatchByEncrypt.action?mode=view&id='+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateOperator(selectedId){
		if(selectedId==null){
			return ;
		}
		var rowData=  $("#volunteerTeamList").getRowData(selectedId);
		$("#volunteerTeamDialog").createDialog({
			title:"修改党员志愿者队伍信息",
			width: dialogWidth,
			height: dialogHeight,
			url:dfop.path+'/volunteerTeamManage/dispatchByEncrypt.action?mode=edit&id='+rowData.encryptId,
			buttons: {
	   		   "保存" : function(){
	   		        $("#maintainForm").submit(); 
	   		   },
	   		   "关闭" : function(){
	  		        $(this).dialog("close"); 
	  		   }
	   		}
		});
	}
	function deleteOperator(allValue){
		if(allValue==null){
			return ;
		}
		var encryptIds=deleteOperatorByEncrypt("volunteerTeam",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/volunteerTeamManage/deleteVolunteerTeamByIdsByEncrypt.action',
	 				type:'post',
	 				data:{
	 					'ids':encryptIds
	 					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该党员志愿者队伍表信息!"});
						$("#volunteerTeamList").trigger("reloadGrid");
					}
				});
			}
		});
	}
	function searchVolunteerTeam(postData){
		$("#volunteerTeamList").setGridParam({
	 		url:dfop.path+'/volunteerTeamManage/findVolunteerTeamPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#volunteerTeamList").setPostData(postData);
		$("#volunteerTeamList").trigger("reloadGrid");
	}
	
	function getSelectedIdLastForTeam(){
		var selectedId;
		var selectedIds = $("#volunteerTeamList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIdsForTeam(){
		var selectedIds = $("#volunteerTeamList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	
	
	
	$("#import").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:dfop.path+"/common/import.jsp?dataType=volunteerTeam&dialog=noticeDialog&startRow=6&templates=VOLUNTEERTEAM&listName=volunteerTeamList",
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
	$("#export").click(function(event){
		if($("#volunteerTeamList").getGridParam("records")>0){
			var postData = $("#volunteerTeamList").getPostData();
			$("#volunteerTeamMaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出党员志愿者队伍表信息",
				url:dfop.path+'/common/exportExcel.jsp',
				postData:{
					gridName:'volunteerTeamList'
	//			 	downloadUrl:dfop.path+'/volunteerTeamManage/downloadVolunteerTeam.action',
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:'warn',message:"列表中没有数据，不能导出!"});
			return;
		}
	});
}