TQ.organsPartyList = function (dfop){
	var dialogWidth = 600;
	var dialogHeight = 360;
	$(function(){
		$("#belongOrg option:first").text("全部");
		$("#belongOrg option:first").val("");
		$("#partyOrgType option:first").text("全部");
		$("#partyOrgType option:first").val("");
		
		function toggleButtonState(){
		  	var selectedIds=$("#organsPartyList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		
		// 生成列表
		$("#organsPartyList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	{name:'type', index:'type',label:'党组织类型',formatter:typeFormatter, width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'name', index:'name',label:'党组织名称', width:100, sortable:true, align:'center', hidden:false},
		    	{name:'department', index:'department',label:'所属部门',formatter:departmentFormatter, width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'contact', index:'contact',label:'联系人', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'telephone', index:'telephone',label:'联系电话', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'juniorParty', index:'juniorParty',label:'下辖组织', width:200, sortable:false, align:'center', hidden:false},
		    	{name:'memberCount', index:'memberCount',label:'党员数', width:100, sortable:false, align:'center', hidden:false},
		    	{name:'superior.name', index:'superior.name',label:'上级党组织', width:100, sortable:false, align:'center', hidden:false}, 	
		    	{name:'remark', index:'remark',label:'备注', width:180, sortable:true, align:'center', hidden:false}	
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewOrgansParty=='true'){
					viewOrgansParty(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#organsPartyList").jqGrid('setFrozenColumns');
		searchByBelongOrgAndPartyOrgType();
		$("#add").click(function(){
			$("#organsPartyDialog").createDialog({
				title:"新增机关党组织信息",
				width: dialogWidth,
				height: dialogHeight,
				url:dfop.path+"/organsPartyManage/dispatch.action?mode=add",
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
		$("#update").click(function(){
			var selectedIds = $("#organsPartyList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"}); 
				return;
			}
			updateOperator(selectedId);
		});
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			var partyOrgArray="";
			for(var i=0;i<allValue.length;i++){
				var rowData = $("#organsPartyList").getRowData(allValue[i]);
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
					deleteOperator(allValue);
				}
			 });
			
		});
		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedIds = $("#organsPartyList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewOrgansParty(selectedId);
		});
		$("#reload").click(function(){
			searchByBelongOrgAndPartyOrgType();
		});

		$("#search").click(function(event){
			$("#organsPartyDialog").createDialog({
				width:650,
				height:320,
				title:'机关党组织查询-请输入查询条件',
	 	 		url:dfop.path+'/organsPartyManage/dispatch.action?mode=search',
				buttons: {
			   		"查询" : function(event){
						searchOrgansParty();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		$("#belongOrg").change(function(){
			searchByBelongOrgAndPartyOrgType();
		});
		
		$("#partyOrgType").change(function(){
			searchByBelongOrgAndPartyOrgType();
		});
		
		$("#manageOrgansPartyMember").click(function(event){
			var selectedIds = $("#organsPartyList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行党员维护！"});
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行党员维护！"});
		 		return;
			}
			var rowData = $("#organsPartyList").getRowData(selectedId);
			if(rowData.type != dfop.branch){
				$.messageBox({level:'warn',message:"请选择一条是党支部类型的记录，再进行党员维护！"});
				return;
			}
			$("#organsPartyDialog").createDialog({
				width:980,
				height:550,
				title:'机关党组织党员维护',
	 	 		url:dfop.path+"/partyBuilding/members/memberForTownList.jsp?partyModule="+dfop.partyModule+"&partyOrgType="+dfop.partyOrgType+"&partyOrg="+encodeURI(encodeURI(rowData.name)),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				},
				close:function(){
					$("#organsPartyList").trigger("reloadGrid");
				}
			});
		});
	});

	
	function viewOrgansParty(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#organsPartyList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#organsPartyDialog").createFrameDialog({
			width:dialogWidth,
			height:250,
			title:"查看机关党组织表信息",
	 		url:dfop.path+'/organsPartyManage/dispatchByEncrypt.action?mode=view&organsParty.id='+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateOperator(selectedId){
		var ent =  $("#organsPartyList").getRowData(selectedId);
		$("#organsPartyDialog").createDialog({
			title:"修改机关党组织信息",
			width: dialogWidth,
			height: dialogHeight,
			url:dfop.path+"/organsPartyManage/dispatchByEncrypt.action?mode=edit&organsParty.id="+ent.encryptId,
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
		var encryptIds=deleteOperatorByEncrypt("organsParty",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
					url:dfop.path+'/organsPartyManage/deleteOrgansPartyByIdsEncrypt.action',
					type:'post',
					data:{
						"ids":encryptIds
					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该机关党组织表信息!"});
						$("#organsPartyList").trigger("reloadGrid");
					}
				});
			}
		});
	}

	function searchOrgansParty(){
		var data=$("#searchMaintainForm").serializeArray();
		var searchVo={};
		for(i=0;i<data.length;i++){
			 if (searchVo[data[i].name]) {
	           searchVo[data[i].name]=searchVo[data[i].name]+","+data[i].value;
			} else {
	            searchVo[data[i].name] = data[i].value;
			}
		}
		onOrgChanged(searchVo);
	}

	function search(orgId){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入机关党组织表名称') return;
		var	postData = {
			 "organizationId":orgId
			// parameters
		}
		$("#organsPartyList").setGridParam({
//	 		url:dfop.path+'/organsPartyManage/fastSearch.action',
			datatype: "json",
			page:1
		});
		$("#organsPartyList").setPostData(postData);
		$("#organsPartyList").trigger("reloadGrid");
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#organsPartyList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#organsPartyList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}



	$("#import").click(function(event){
		$("#noticeDialog").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:dfop.path+"/common/import.jsp?dataType=organsParty&dialog=noticeDialog&startRow=6&templates=ORGANSPARTY&listName=organsPartyList",
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
		if($("#organsPartyList").getGridParam("records")>0){
			var postData = $("#organsPartyList").getPostData();
			$("#organsPartyMaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出机关党组织表信息",
				url:dfop.path+'/common/exportExcel.jsp',
				postData:{
					gridName:'organsPartyList'
//				 	downloadUrl:dfop.path+'/organsPartyManage/downloadOrgansParty.action',
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