TQ.regionalPartyMemberList = function (dfop){
	//改变组织机构树时加载列表
	function onOrgChanged(orgId,isgrid){
		var initParam = {
			"regionalPartyMemberVo.organization.id": orgId
		}
		$("#regionalPartyMemberList").setGridParam({
	 		url:dfop.path+'/partyBuilding/regionalPartyMemberManage/findRegionalPartyMemberPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#regionalPartyMemberList").setPostData(initParam);
		$("#regionalPartyMemberList").trigger("reloadGrid");
	}

	$(function(){
		
		initRegionalPartyMemberEnable();
		function initRegionalPartyMemberEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#addRegionalPartyMember,#updateRegionalPartyMember,#deleteRegionalPartyMember,#importRegionalPartyMember,#exportRegionalPartyMember").hide();
			}
		}
		
		function toggleButtonState(){
		  	var selectedIds=$("#regionalPartyMemberList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		
		// 生成列表
		$("#regionalPartyMemberList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				// {name:"organization.id",index:'organization.id',hidden:true},
				//{name:"organization.orgInternalCode",index:'organization.orgInternalCode',hidden:true}, 
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:'name', index:'name',label:'姓名', width:120, sortable:true, align:'center', hidden:false},
				{name:'gender.id', index:'gender',label:'性别', formatter:genderFormatter,width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'partyOrgDuty.id', index:'partyOrgDuty',label:'区域党委职务',formatter:partyOrgDutyFormatter, width:200, sortable:true, align:'center', hidden:false}, 	
		    	{name:'unit', index:'unit',label:'所属单位', width:120, sortable:true, align:'center', hidden:false}, 	 	
		    	{name:'unitDuty', index:'unitDuty',label:'所属单位职务', width:120, sortable:true, align:'center', hidden:false}, 	
		    	{name:'mobileNumber', index:'mobileNumber',label:'联系手机', width:200, sortable:true, align:'center', hidden:false}, 	
		    	{name:'telephone', index:'telephone',label:'固定电话', width:100, sortable:true, align:'center', hidden:false}	 	
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewRegionalPartyMember=='true'){
					viewRegionalPartyMember(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			},
			height:$(".ui-layout-center").height()-320
		});
		jQuery("#regionalPartyMemberList").jqGrid('setFrozenColumns');
		
		$("#addRegionalPartyMember").click(function(){
			if(''==$("#partyOrgInfoId").val()||typeof($("#partyOrgInfoId").val())=='undefined'){
				$.messageBox({level:'warn',message:"请先填写区域党建信息！"});
				return;
			}
			if(getCurrentOrgId() != USER_ORG_ID){
				$.messageBox({level:'warn',message:"您不能给其他层级添加数据！"});
				return;
			}
			$("#regionalPartyMemberDialog").createDialog({
				title:"新增区域党委成员信息",
				width: 600,
				height: 300,
				url:dfop.path+'/partyBuilding/regionalPartyMemberManage/dispatch.action?mode=add&regionalPartyMember.organization.id='+$("#currentOrgId").val(),
				buttons: {
			   		"保存" : function(event){
			   			$("#regionalPartyMemberForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}			
			});
		});
		$("#updateRegionalPartyMember").click(function(){
			if(getCurrentOrgId() != USER_ORG_ID){
				$.messageBox({level:'warn',message:"您不能修改其他层级的数据！"});
				return;
			}
			var selectedIds = $("#regionalPartyMemberList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				return;
			}
			var selectedId = getRegionalPartyMemberSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				 return;
			}
			var rowData=  $("#regionalPartyMemberList").getRowData(selectedId);
			$("#regionalPartyMemberDialog").createDialog({
				title:"修改区域党委成员信息",
				width: 600,
				height: 300,
				url:dfop.path+'/partyBuilding/regionalPartyMemberManage/dispatchByEncrypt.action?mode=edit&regionalPartyMember.organization.id='+$("#currentOrgId").val()+'&regionalPartyMember.id='+rowData.encryptId,
				buttons: {
			   		"保存" : function(event){
			   			$("#regionalPartyMemberForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}			
			});
		});
		$("#deleteRegionalPartyMember").click(function(){
			if(getCurrentOrgId() != USER_ORG_ID){
				$.messageBox({level:'warn',message:"您不能删除其他层级的数据！"});
				return;
			}
			var allValue = getSelectedIdsForRegionalPartyMember();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteRegionalPartyMember(allValue);
		});
		$("#viewRegionalPartyMember").click(function(){
			var selectedIds = $("#regionalPartyMemberList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			var selectedId = getRegionalPartyMemberSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			viewRegionalPartyMember(selectedId);
		});
		$("#reloadRegionalPartyMember").click(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		if(null!=getCurrentOrgId()){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
		
		$("#searchRegionalPartyMember").click(function(event){
			
				$("#regionalPartyMemberDialog").createDialog({
					width:650,
					height:320,
					title:'区域党委成员查询-请输入查询条件',
		 	 		url:dfop.path+'/partyBuilding/regionalPartyMemberManage/dispatch.action?mode=search&regionalPartyMemberVo.organization.id='+$("#currentOrgId").val(),
					buttons: {
				   		"查询" : function(event){
				   			searchRegionalPartyMember($("#searchRegionalPartyMemberForm").serializeObject());
				        	$(this).dialog("close");
			   			},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			});

	});


	function searchRegionalPartyMember(serializeObject){
		$("#regionalPartyMemberList").setGridParam({
	 		url:dfop.path+'/partyBuilding/regionalPartyMemberManage/findRegionalPartyMemberPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#regionalPartyMemberList").setPostData(serializeObject);
		$("#regionalPartyMemberList").trigger("reloadGrid");
	}

	function deleteRegionalPartyMember(allValue){
		if(allValue==null){
			return ;
		}
		var encryptIds=deleteOperatorByEncrypt("regionalPartyMember",allValue,"encryptId");

		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/partyBuilding/regionalPartyMemberManage/deleteRegionalPartyMemberByIdsByEncrypt.action',
	 				type:'post',
	 				data:{
	 					'ids':encryptIds
	 				},
					success:function(data){
					    $.messageBox({message:"已经成功删除该区域党委成员信息!"});
						$("#regionalPartyMemberList").trigger("reloadGrid");
					}
				});
			}
		});
	}

	function viewRegionalPartyMember(selectedId){
		if(selectedId==null){
			return ;
		}
		var rowData=  $("#regionalPartyMemberList").getRowData(selectedId);
		$("#regionalPartyMemberDialog").createDialog({
			title:"查看区域党委成员信息",
			width: 600,
			height: 300,
			url:dfop.path+'/partyBuilding/regionalPartyMemberManage/dispatchByEncrypt.action?mode=view&regionalPartyMember.id='+rowData.encryptId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}			
		});
	}

	function getRegionalPartyMemberSelectedIdLast() {
		var selectedId;
		var selectedIds = $("#regionalPartyMemberList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	} 

	function getSelectedIdsForRegionalPartyMember(){
		var selectedIds = $("#regionalPartyMemberList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}