TQ.volunteerJobsList = function (dfop){
	$(function(){
		initButtonsEnable();
		function initButtonsEnable(){
			if(isUserVillageUp() &&  isVillageUp() || $("#currentOrgId").val()!=USER_ORG_ID){
				$("#add,#update,#delete").hide();
			}
		}
		function toggleButtonState(){
		  	var selectedIds=$("#volunteerjobsList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		
		// 生成列
		$("#volunteerjobsList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
//	 	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
		    	{name:'name', index:'name',label:'志愿者岗位', width:110, sortable:true, align:'center', hidden:false}, 	
		    	{name:'content', index:'content',label:'专长要求', width:240, sortable:false, align:'center', hidden:false},
		    	{name:'organization.orgName',index:'orgId',label:'所属区域', width:310,hidden:false,sortable:true},
		    	{name:'claimPlans', index:'claimPlans',label:'拟认领数', width:100, sortable:true, align:'center', hidden:false},
		    	{name:'hasClaimedAmount', index:'hasClaimedAmount',label:'已认领数', width:100, sortable:false, align:'center', hidden:false},
		    	{name:'contractor', index:'contractor',label:'联系人', width:100, sortable:true, align:'center', hidden:false}, 
		    	{name:'telephone', index:'telephone',label:'联系电话', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'remark', index:'remark',label:'备注', width:120, sortable:true, align:'center', hidden:false}, 	
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
					viewVolunteerjobs(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#volunteerjobsList").jqGrid('setFrozenColumns');
		
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入志愿者岗位名称");
		});
		$("#add").click(function(){
			if (getCurrentOrgId()==null||getCurrentOrgId()==""){
				$.notice({level:"warn", message:"请先选择一个部门"});
			}else{
				$("#volunteerjobsDialog").createDialog({
					model :"add",
					title:"新增志愿者岗位信息",
					width: dialogWidth,
					height: dialogHeight,
					url:dfop.path+'/volunteerjobsManage/dispatchOperate.action?mode=add&organization.id='+getCurrentOrgId(),
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
			var selectedIds = $("#volunteerjobsList").jqGrid("getGridParam", "selarrrow");
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
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedIds = $("#volunteerjobsList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewVolunteerjobs(selectedId);
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
			$("#volunteerjobsDialog").createDialog({
				width: dialogWidth,
				height: dialogHeight,
				title:'志愿者岗位查询-请输入查询条件',
	 	 		url:dfop.path+'/partyBuilding/doubleRegActivities/volunteerJobsManage/voluteerJobsSearchDlg.jsp?orgId='+getCurrentOrgId(),
				buttons: {
			   		"查询" : function(event){
						searchVolunteerjobs();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	});

	function viewVolunteerjobs(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#volunteerjobsList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		$("#volunteerjobsDialog").createFrameDialog({
			width:600,
			height:300,
			title:"查看志愿者岗位信息",
	 		url:dfop.path+'/volunteerjobsManage/dispatchOperateByEncrypt.action?mode=view&volunteerJobs.id='+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateOperator(selectedId){
		var ent =  $("#volunteerjobsList").getRowData(selectedId);
		$("#volunteerjobsDialog").createDialog({
			model :"edit",
			title:"修改志愿者岗位信息",
			width: dialogWidth,
			height: dialogHeight,
			url:dfop.path+'/volunteerjobsManage/dispatchOperateByEncrypt.action?mode=edit&volunteerJobs.id='+ent.encryptId,
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
		var encryptIds=deleteOperatorByEncrypt("volunteerjobs",allValue,"encryptId");

		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/volunteerjobsManage/deleteVolunteerJobsByIdsByEncrypt.action',
	 				type:'post',
	 				data:{
	 					'ids':encryptIds
	 					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该志愿者岗位信息!"});
						$("#volunteerjobsList").trigger("reloadGrid");
					}
				});
			}
		});
	}

	function parseObj(strData) {
		return (new Function("return " + strData))();
	}
	function searchVolunteerjobs(){
		var formdata = $("#searchVolunteerJobsForm").serialize();
		if (formdata != '') {
			formdata = formdata.replace(/\+/g," "); 
			formdata = formdata.replace(/=/g, '":"');
			formdata = formdata.replace(/&/g, '","');
			formdata += '"';
		}
		formdata = decodeURIComponent('{"' + formdata + '}');
		$("#volunteerjobsList").setGridParam({
			url:dfop.path+'/volunteerjobsManage/findVolunteerJobsPagerBySearchVo.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#volunteerjobsList").setPostData(parseObj(formdata));
		$("#volunteerjobsList").trigger("reloadGrid");
	}
	function search(orgId){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入志愿者岗位名称' || fastSearchVal==''){
			onOrgChanged(getCurrentOrgId(),isGrid());
			return;
		} 
		var	postData = {
			 "organization.id":orgId,
			 "searchvolunteerJobsVo.name":fastSearchVal
		}
		$("#volunteerjobsList").setGridParam({
	 		url:dfop.path+'/volunteerjobsManage/findVolunteerJobsPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#volunteerjobsList").setPostData(postData);
		$("#volunteerjobsList").trigger("reloadGrid");
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#volunteerjobsList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#volunteerjobsList").jqGrid("getGridParam", "selarrrow");
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