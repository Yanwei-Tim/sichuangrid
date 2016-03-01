TQ.newPartyOrgList = function (dfop){
	var dialogWidth = 580;
	var dialogHeight = 350;

	
	// 改变组织机构树时加载列表
	function onOrgChanged(orgId,isgrid){
		search({"orgId": orgId})
	}

	$(function(){
		initButtonsEnable();
		function initButtonsEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#add,#update,#delete,#managePartyMember,#maintainActivity").hide();
			}
		}
		
		function toggleButtonState(){
		  	var selectedIds=$("#newPartyOrgList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		
		// 生成列表
		$("#newPartyOrgList").jqGridFunction({
			mtype:'post',
			datatype: "local",
			height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-100,
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	//{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,align:'center' },
		    	{name:'type', index:'type',label:'党组织类别',formatter:typeFormatter, sortable:true, width:100, align:'center', hidden:false},
		    	{name:'name', index:'name',label:'两新组织名称', width:150, align:'center', hidden:false,sortable:true},
		    	{name:'memberNum', index:'memberNum',label:'党员人数', width:100, align:'center', hidden:false}, 	
		    	{name:'address', index:'address',label:'地址', width:150, align:'center', hidden:false,sortable:true}, 	
		    	{name:'', index:'',label:'活动记录', formatter:activityFormatter,width:100, align:'center', hidden:false}, 	
		    	{name:'organization.orgName', index:'orgId',label:'所属网格', width:150, sortable:true, align:'center', hidden:false}	
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewNewPartyOrg=='true'){
					viewNewPartyOrg(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#newPartyOrgList").jqGrid('setFrozenColumns');
		
		$("#fastSearchButton").click(function(){
			var fastSearchVal = $("#searchText").val();
			if(fastSearchVal == '请输入党组织名称') return;
			search({
				"orgId":getCurrentOrgId(),
				"searchVo.name":fastSearchVal
			});
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入党组织名称");
		});
		$("#add").click(function(){
			if (getCurrentOrgId()==null||getCurrentOrgId()==""){
				$.notice({level:"warn", message:"请先选择一个部门"});
			}else{
				$("#newPartyOrgDialog").createDialog({
					title:"新增两新组织党组织信息",
					width: dialogWidth,
					height: dialogHeight,
					url:dfop.path+"/newPartyOrgManage/dispatch.action?mode=add&orgId="+getCurrentOrgId(),
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
			var selectedIds = getSelectedIdsForNewPartyOrg();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				return;
			}
			var selectedId = getSelectedIdLastForNewPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"}); 
				return;
			}
			updateNewPartyOrg(selectedId);
		});
		$("#delete").click(function(){
			var allValue = getSelectedIdsForNewPartyOrg();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			var partyOrgArray="";
			for(var i=0;i<allValue.length;i++){
				var rowData = $("#newPartyOrgList").getRowData(allValue[i]);
				partyOrgArray = partyOrgArray+rowData.name;
				if(i!=allValue.length-1){
					partyOrgArray=partyOrgArray+",";
				}
			}
			$.ajax({
				async : true,
				url : dfop.path+"/partyBuildng/memberManage/exsistedMember.action",
				data: {"orgId":getCurrentOrgId(),"partyOrgType":dfop.partyOrgType,"partyOrg":partyOrgArray},
				success : function(data) {
					if(data>0){
						$.messageBox({level:'warn',message:"您选择的组织下已经存在党员，不能删除！"});
						 return;
					}
					deleteNewPartyOrg(allValue);
				}
			 });
		});
		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedIds = getSelectedIdsForNewPartyOrg();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			var selectedId = getSelectedIdLastForNewPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			viewNewPartyOrg(selectedId);
		});
		$("#reload").click(function(){
			$("#searchText").attr("value","请输入党组织名称");
			onOrgChanged(getCurrentOrgId(),isGrid());
		}).click();

		$("#search").click(function(event){
			$("#newPartyOrgDialog").createDialog({
				width:550,
				height:320,
				title:'两新组织党组织查询-请输入查询条件',
	 	 		url:dfop.path+'/newPartyOrgManage/dispatch.action?mode=search',
				buttons: {
			   		"查询" : function(event){
	 	 				search($.extend({
	 	 					'orgId':getCurrentOrgId()
	 	 	 			},$("#maintainForm").serializeObject()));
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
			
		$("#maintainActivity").click(function(){
			var selectedIds = getSelectedIdsForNewPartyOrg();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行维护！"});
				return;
			}
			var selectedId = getSelectedIdLastForNewPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行维护！"});
				 return;
			}
			var rowData = $("#newPartyOrgList").getRowData(selectedId);
			var organizationName=rowData.name;
			maintainActivity(selectedId,organizationName);
		});	
		
		$("#managePartyMember").click(function(){
			var selectedIds = getSelectedIdsForNewPartyOrg();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行成员维护！"});
				return;
			}
			var selectedId = getSelectedIdLastForNewPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行成员维护！"});
		 		return;
			}
			var rowData = $("#newPartyOrgList").getRowData(selectedId);
			if(rowData.type != dfop.branch){
				$.messageBox({level:'warn',message:"请选择一条是党支部类型的记录，再进行成员维护！"});
				return;
			}
			$("#newPartyOrgDialog").createDialog({
				width:950,
				height:600,
				title:'两新组织党组织成员维护',
	 	 		url:dfop.path+"/partyBuilding/members/memberForTownList.jsp?partyModule="+dfop.partyModule+"&partyOrgType="+dfop.partyOrgType+"&partyOrg="+encodeURI(encodeURI(rowData.name)),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			        	$("#newPartyOrgList").trigger("reloadGrid");
			   		}
				}
			});
		})
		
	});

	

	function viewNewPartyOrg(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#newPartyOrgList").getRowData(selectedId);
		if(rowData==null){
			 return;
		}
		$("#newPartyOrgDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看两新组织党组织表信息",
	 		url:dfop.path+'/newPartyOrgManage/dispatchByEncrypt.action?mode=view&id='+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateNewPartyOrg(selectedId){
		if(selectedId==null){
			return ;
		}
		var rowData=  $("#newPartyOrgList").getRowData(selectedId);
		$("#newPartyOrgDialog").createDialog({
			title:"修改两新组织党组织信息",
			width: dialogWidth,
			height: dialogHeight,
			url:dfop.path+"/newPartyOrgManage/dispatchByEncrypt.action?mode=edit&id="+rowData.encryptId,
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
	function deleteNewPartyOrg(allValue){
		if(allValue==null){
			return ;
		}
		var encryptIds=deleteOperatorByEncrypt("newPartyOrg",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/newPartyOrgManage/deleteNewPartyOrgByIdsByEncrypt.action',
	 				type:"post",
	 				data:{
	 					"ids":encryptIds
	 				},
					success:function(data){
					    $.messageBox({message:"已经成功删除该两新组织党组织表信息!"});
						$("#newPartyOrgList").trigger("reloadGrid");
					}
				});
			}
		});
	}

	function search(postData){
		$("#newPartyOrgList").setGridParam({
	 		url:dfop.path+'/newPartyOrgManage/findNewPartyOrgPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#newPartyOrgList").setPostData(postData);
		$("#newPartyOrgList").trigger("reloadGrid");
	}
	function getSelectedIdLastForNewPartyOrg(){
		var selectedId;
		var selectedIds = $("#newPartyOrgList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;selectedIds!=null && i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIdsForNewPartyOrg(){
		var selectedIds = $("#newPartyOrgList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}


function activityFormatter(el,options,rowData){
	
	return "<a href='javascript:maintainActivity("+rowData.id+",\""+rowData.name+"\")'><span>详情</span></a>";
}

