TQ.partyresourceList = function (dfop){
	var dialogWidth = 800;
	var dialogHeight = 600;


	// 改变组织机构树时加载列表
	function onOrgChanged(orgId,isgrid){
		var initParam = {
			"searchPartyresourceVo.organization.id": orgId
		}
		$("#partyresourceList").setGridParam({
	 		url:dfop.path+'/partyresourceManage/findPartyresourcePagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#partyresourceList").setPostData(initParam);
		$("#partyresourceList").trigger("reloadGrid");
	}

	$(function(){
		
		initPartyresourceEnable();
		function initPartyresourceEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#add,#update,#delete").hide();
			}
		}
		
		function toggleButtonState(){
		  	var selectedIds=$("#partyresourceList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		
		function afterLoad(){

		}
		
		// 生成列表
		$("#partyresourceList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				{name:"id",index:'id',hidden:true},	
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:'name', index:'name',label:'组织名称', width:200, sortable:true, align:'center', hidden:false}, 	
		    	{name:'telephone', index:'telephone',label:'联系电话', width:100, sortable:true, align:'center', hidden:false}, 	 	
		    	{name:'manager', index:'manager',label:'负责人', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'address', index:'address',label:'地点', width:200, sortable:true, align:'center', hidden:false}, 	
		    	{name:'organization.orgName',index:'organization.orgName',label:'所属区域',width:200,sortable:false},
		    	{name:'remark', index:'remark',label:'备注', width:300, sortable:false, align:'center', hidden:false}	
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewPartyresource=='true'){
					viewPartyresource(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			},
			height:$(".ui-layout-center").height()-320
		});
		jQuery("#partyresourceList").jqGrid('setFrozenColumns');
		
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入区域内主要党组织资源名称");
		});
		$("#add").click(function(){
			if(''==$("#partyOrgInfoId").val()||typeof($("#partyOrgInfoId").val())=='undefined'){
				$.messageBox({level:'warn',message:"请先填写区域党建信息！"});
				return;
			}
			if(getCurrentOrgId() != USER_ORG_ID){
				$.messageBox({level:'warn',message:"您不能给其他层级添加数据！"});
				return;
			}
			$("#partyresourceDialog").createDialog({
				title:"新增区域内主要党组织资源信息",
				width: 600,
				height: 340,
				url:dfop.path+'/partyresourceManage/dispatch.action?mode=add&partyresource.organization.id='+$("#currentOrgId").val(),
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
		$("#update").click(function(){
			if(getCurrentOrgId() != USER_ORG_ID){
				$.messageBox({level:'warn',message:"您不能修改其他层级的数据！"});
				return;
			}
			var selectedIds = $("#partyresourceList").jqGrid("getGridParam", "selarrrow");
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
			if(getCurrentOrgId() != USER_ORG_ID){
				$.messageBox({level:'warn',message:"您不能删除其他层级的数据！"});
				return;
			}
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteOperator(allValue);
		});
		$("#view").click(function(){
			var selectedIds = $("#partyresourceList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			viewPartyresource(selectedId);
		});
		$("#reload").click(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		if(null!=getCurrentOrgId()){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
		
		$("#search").click(function(event){
			$("#partyresourceDialog").createDialog({
				width:650,
				height:250,
				title:'区域内主要党组织资源查询-请输入查询条件',
	 	 		url:dfop.path+'/partyresourceManage/dispatch.action?mode=search',
				buttons: {
			   		"查询" : function(event){
						searchPartyresource();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	});

	function viewPartyresource(selectedId){
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		var rowData = $("#partyresourceList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		$("#partyresourceDialog").createDialog({
			width:600,
			height:300,
			title:"查看区域内主要党组织资源信息",
	 		url:dfop.path+'/partyresourceManage/dispatchByEncrypt.action?mode=view&partyresource.id='+id,
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
		var ent =  $("#partyresourceList").getRowData(selectedId);
		$("#partyresourceDialog").createDialog({
			title:"修改区域内主要党组织资源信息",
			width: 600,
			height: 340,
			url:dfop.path+'/partyresourceManage/dispatchByEncrypt.action?mode=edit&partyresource.id='+ent.encryptId,
			buttons: {
		   		"保存" : function(event){
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
		var encryptIds=deleteOperatorByEncrypt("partyresource",allValue,"encryptId");

		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/partyresourceManage/deletePartyresourceByIdsByEncrypt.action',
	 				type:"post",
	 				data:{
	 					"ids":encryptIds
	 				},
					success:function(data){
					    $.messageBox({message:"已经成功删除该区域内主要党组织资源信息!"});
						$("#partyresourceList").trigger("reloadGrid");
					}
				});
			}
		});
	}
	function searchPartyresource(){
		$("#partyresourceList").setGridParam({
			url:dfop.path+'/partyresourceManage/findPartyresourcePagerBySearchVo.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchPartyresourceForm").serializeArray();
		var searchVo={};
		for(i=0;i<data.length;i++){
			 if (searchVo[data[i].name]) {
	           searchVo[data[i].name]=searchVo[data[i].name]+","+data[i].value;
			} else {
	            searchVo[data[i].name] = data[i].value;
			}
		}
		var postData=$.extend(
				searchVo,{"searchPartyresourceVo.organization.id":getCurrentOrgId()}
			);
		$("#partyresourceList").setPostData(postData);
		$("#partyresourceList").trigger("reloadGrid");
	}
	function search(orgId){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入区域内主要党组织资源名称') return;
		var	postData = {
			 "organizationId":orgId
			// parameters
		}
		$("#partyresourceList").setGridParam({
			datatype: "json",
			page:1
		});
		$("#partyresourceList").setPostData(postData);
		$("#partyresourceList").trigger("reloadGrid");
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#partyresourceList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#partyresourceList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	$("#export").click(function(event){
		if($("#partyresourceList").getGridParam("records")>0){
			var postData = $("#partyresourceList").getPostData();
			$("#partyresourceMaintanceDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出区域内主要党组织资源信息",
				url:dfop.path+'/common/exportExcel.jsp',
				postData:{
					gridName:'partyresourceList'
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