TQ.serviceProjectList = function (dfop){

	$(function(){
		initButtonsEnable();
		function initButtonsEnable(){
			if(isUserVillageUp() &&  isVillageUp() || $("#currentOrgId").val()!=USER_ORG_ID){
				$("#add,#update,#delete ").hide();
			}
		}
		function toggleButtonState(){
		  	var selectedIds=$("#serviceprojectList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		
		// 生成列表
		$("#serviceprojectList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
//	 	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
		    	{name:'projectName', index:'projectName',label:'项目名称', width:120, sortable:true, align:'center', hidden:false},
		    	{name:'projectContent', index:'projectContent',label:'项目内容', width:240, sortable:false, align:'center', hidden:false},
		    	{name:'organization.orgName',index:'orgId',label:'所属区域', width:310,hidden:false,sortable:true},
		    	{name:'claimPlans', index:'claimPlans',label:'拟认领数', width:100, sortable:true, align:'center', hidden:false},
		    	{name:'hasClaimedAmount', index:'hasClaimedAmount',label:'已认领数', width:100, sortable:false, align:'center', hidden:false},
		    	{name:'contractor', index:'contractor',label:'联系人', width:100, sortable:true, align:'center', hidden:false},
		    	{name:'telephone', index:'telephone',label:'联系电话', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'remark', index:'remark',label:'备注', width:110, sortable:true, align:'center', hidden:false}, 	
		    	{name:'createUser', index:'createUser',label:'创建人', width:100, sortable:true, align:'center', hidden:true}, 	
		    	{name:'updateUser', index:'updateUser',label:'修改人', width:100, sortable:true, align:'center', hidden:true},
		    	{name:'createDate', index:'createDate',label:'创建时间', width:100, sortable:true, align:'center', hidden:true}, 	
		    	{name:'updateDate', index:'updateDate',label:'修改时间', width:100, sortable:true, align:'center', hidden:true}
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewServiceproject=='true'){
					viewServiceproject(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#serviceprojectList").jqGrid('setFrozenColumns');
		
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入服务项目名称");
		});
		$("#add").click(function(){
			if (getCurrentOrgId()==null||getCurrentOrgId()==""){
				$.notice({level:"warn", message:"请先选择一个部门"});
			}else{
				$("#serviceprojectDialog").createDialog({
					model :"add",
					title:"新增服务项目信息",
					width: dialogWidth,
					height: dialogHeight,
					url:dfop.path+'/serviceprojectManage/dispatchOperate.action?mode=add&organization.id='+getCurrentOrgId(),
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
			var selectedIds = $("#serviceprojectList").jqGrid("getGridParam", "selarrrow");
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
			deleteOperator(allValue);
		});
		$("#view").click(function(){
			var selectedIds = $("#serviceprojectList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewServiceproject(selectedId);
		});
		$("#reload").click(function(){
			if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
				onOrgChanged(getCurrentOrgId(),isGrid());
			}
		});
		
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}

		$("#search").click(function(event){
			$("#serviceprojectDialog").createDialog({
				width: dialogWidth,
				height: dialogHeight,
				title:'服务项目查询-请输入查询条件',
	 	 		url:dfop.path+'/partyBuilding/doubleRegActivities/serviceProjectManage/serviceProjectSearchDlg.jsp?orgId='+getCurrentOrgId(),
				buttons: {
			   		"查询" : function(event){
						searchServiceproject();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	});

	function viewServiceproject(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#serviceprojectList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		$("#serviceprojectDialog").createFrameDialog({
			width:600,
			height:300,
			title:"查看服务项目信息",
	 		url:dfop.path+'/serviceprojectManage/dispatchOperateByEncrypt.action?mode=view&serviceProject.id='+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateOperator(selectedId){
		var ent =  $("#serviceprojectList").getRowData(selectedId);
		$("#serviceprojectDialog").createDialog({
			model :"edit",
			title:"修改服务项目信息",
			width: dialogWidth,
			height: dialogHeight,
			url:dfop.path+'/serviceprojectManage/dispatchOperateByEncrypt.action?mode=edit&serviceProject.id='+ent.encryptId,
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
		var encryptIds=deleteOperatorByEncrypt("serviceproject",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/serviceprojectManage/deleteServiceProjectByIdsByEncrypt.action',
	 				type:'post',
	 				data:{
	 				'ids':encryptIds
	 				},
					success:function(data){
					    $.messageBox({message:"已经成功删除该服务项目信息!"});
						$("#serviceprojectList").trigger("reloadGrid");
					}
				});
			}
		});
	}
	function parseObj(strData) {
		return (new Function("return " + strData))();
	}
	function searchServiceproject(){
		var formdata = $("#searchServiceProjectForm").serialize();
		if (formdata != '') {
			formdata = formdata.replace(/\+/g," "); 
			formdata = formdata.replace(/=/g, '":"');
			formdata = formdata.replace(/&/g, '","');
			formdata += '"';
		}
		formdata = decodeURIComponent('{"' + formdata + '}');
		$("#serviceprojectList").setGridParam({
			url:dfop.path+'/serviceprojectManage/findServiceProjectPagerBySearchVo.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#serviceprojectList").setPostData(parseObj(formdata));
		$("#serviceprojectList").trigger("reloadGrid");
	}
	function search(orgId){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入服务项目名称' || fastSearchVal==''){
			onOrgChanged(getCurrentOrgId(),isGrid());
			return;
		} 
		var	postData = {
			"organization.id":orgId,
			"searchServiceProjectVo.projectName":fastSearchVal
		}
		$("#serviceprojectList").setGridParam({
	 		url:dfop.path+'/serviceprojectManage/findServiceProjectPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#serviceprojectList").setPostData(postData);
		$("#serviceprojectList").trigger("reloadGrid");
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#serviceprojectList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#serviceprojectList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	//社区级别以上
	function isVillageUp(){
		return $("#currentOrgId").attr("orgLevelInternalId") > dfop.orgLevel;
	}

	//社区级别以上用户
	function isUserVillageUp(){
		return USER_ORG_LEVEL > dfop.orgLevel;
	}
}