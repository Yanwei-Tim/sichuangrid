TQ.teamManagementList = function (dfop){
	$(document).ready(function(){
		$("#ScreeningFourteams").change(function(){
			var level = $("#ScreeningFourteams").val();
			if(level==null || level=='' || level==undefined){
				return;
			}
			$("#teamManagementList").setGridParam({
				url:PATH+'/fourTeamsManage/serviceTeamManagementList.action',
				postData:{
					"fourTeams.teamType":$("#teamType").val(),
					"organizationId":orgId,
					"screeningLevel":level
				},
				datatype: "json",
				page:1
			});
			$("#teamManagementList").trigger("reloadGrid");
		});
		
		function refresh(){
			$("#teamManagementList").setGridParam({
				url:PATH+'/fourTeamsManage/serviceTeamManagementList.action',
				postData:{
					"fourTeams.teamType":$("#teamType").val(),
					"organizationId":orgId,
					"screeningLevel": $("#ScreeningFourteams").val()
				},
				datatype: "json",
				page:1
			});
			$("#teamManagementList").trigger("reloadGrid");
		}

		$("#reload").click(function(){
			refresh();
		});

		$("#search").click(function(event){
			$("#teamManagementDialog").createDialog({
				width: 600,
				height: 200,
				title:'队伍查询-请输入查询条件',
				url:PATH+'/fourTeamsManage/searchFourTeamMembersDlg.jsp',
				postData:{
					"teamType":$("#teamType").val(),
					"currentOrgId":getCurrentOrgId(),
					"screeningLevel":$("#ScreeningFourteams").val()
				},
				buttons: {
			   		"查询" : function(event){
			   			$("#maintainForm").submit();
			   			$(this).dialog("close");
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		
		$("#addPioneerServiceTeam").click(function(event){
			if($("#addPioneerServiceTeam").attr("disabled")){
				return ;
			}
			$("#addMemberDialog").createDialog({
				width: 600,
				height: 500,
				title:'新增队伍',
				url:PATH+'/fourTeamsManage/maintainAddList.action',
				postData:{
					"fourTeams.teamType":$("#teamType").val(),
					"mode":"add",
					"currentOrgId":getCurrentOrgId()
				},
				buttons: {
			   		"保存" : function(event){
			   			$("#maintainForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});	
		
		$("#deletePioneerServiceTeam").click(function(){
			var selectedId =$("#teamManagementList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null || selectedId==undefined || selectedId==''){
				$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		 		return;
			}
			var fourteams=$("#teamManagementList").getRowData(selectedId[0]);
			var encryptIds=deleteOperatorByEncrypt("teamManagement",selectedId,"encryptId");
			$.confirm({
				title:"确认删除",
				message:"删除后数据无法回复，确定要删除吗?",
				okFunc: function(){
					$.ajax({
						url:PATH+"/fourTeamsManage/deleteFourTeams.action",
						type:"post",
						data:{ 	
							"ids":encryptIds+"",
							"parentId":fourteams.parentTeamId
						},
						success:function(data){
							if(data!=true){
								$.messageBox({
									message : data,
									level : 'error'
								});
								return;
							}
							$("#teamManagementList").trigger("reloadGrid");
						    $.messageBox({message:"已经成功删除该队伍信息!"});
					    }
				    });
			   }
		 	});
			
		});

		$("#look").click(function(event){
			
			var selectedId =getSelectedId();
			if(selectedId==null){
				$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		 		return;
			}
			viewUserInfo(selectedId);
		});
		$("#teamManagementList").jqGridFunction({
			url:PATH+'/fourTeamsManage/serviceTeamManagementList.action',
			datatype: "json",
			postData:{
				"fourTeams.teamType":$("#teamType").val(),
				"organizationId":orgId,
				"screeningLevel":"sameLevel"
			},
		   	colModel:dfop.colModel,
			multiselect:true,
			onSelectAll:function(aRowids,status){},
			showColModelButton:true,
			ondblClickRow: viewFourTeamsOperator,
			onSelectRow:selectRow
		});
		$("#manageTeamMember").click(function(){
			
			var selectedId=getSelectedId();
			var selectedIds = $("#teamManagementList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null){
		 		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		 		return;
			}
			if(selectedIds!=null && selectedIds.length > 1){
		 		$.messageBox({level:"warn",message:"请只能选择一条数据再进行操作！"});
		 		return;
			}
			$("#teamManagementDialog").createDialog({
				width: 850,
				height: 600,
				title:'维护成员信息',
				url:'/fourTeamsManage/manageFourTeamMemberDlg.jsp?id='+selectedId,
				buttons: {
					"关闭" : function(){
			        	$(this).dialog("close");
			        	$("#teamManagementList").trigger("reloadGrid");
			   		}
				}
			});
		});
	});

	


	function selectRow() {
		var count = $("#teamManagementList").jqGrid("getGridParam", "records");
			var selectedCounts = getActualjqGridMultiSelectCount("teamManagementList");
			if (selectedCounts == count) {
				jqGridMultiSelectState("teamManagementList", true);
			} else {
				jqGridMultiSelectState("teamManagementList", false);
			}

	}

	function getSelectedIds(){
		var selectedIds = $("#teamManagementList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	function getSelectedId(){
	    var selectedIdLast = null;
	    var selectedIds = $("#teamManagementList").jqGrid("getGridParam", "selarrrow");

	    for(var i=0;i<selectedIds.length;i++){
			selectedIdLast = selectedIds[i];
	   }
	   return selectedIdLast;
	}
}