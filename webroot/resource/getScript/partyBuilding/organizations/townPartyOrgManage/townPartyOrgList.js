TQ.townPartyOrgList = function (dfop){
	var dialogWidth = 850;
	var dialogHeight = 400;
	
	
	
	function membersFormatter(el,options,rowData){
		var members = rowData.members;
		var str = "";
		for(var i=0;i<members.length;i++){
			str += members[i].name;
			if(i<members.length-1){
				str += ",";
			}
		}
		return str;
	}
	
	
	// 改变组织机构树时加载列表
	function onOrgChanged(orgId,isgrid){
		search({ 
			"orgId":orgId 
		});
	}
	
	$(function(){
		initButtonsEnable();
		function initButtonsEnable(){
			if($("#currentOrgId").val()!=USER_ORG_ID){
				$("#add,#update,#delete,#managePartyMember,#maintainActivity").hide();
			}
		}
		
		function toggleButtonState(){
		  	var selectedIds=$("#townPartyOrgList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){
	
		}
		
		// 生成列表
		$("#townPartyOrgList").jqGridFunction({
			mtype:'post',
			datatype: "local",
			height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-100,
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	// {name:"operator",
				// index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center'
				// },
		    	{name:'type', index:'type',label:'党组织类型',formatter:typeFormatter, width:100, sortable:true,hidden:false}, 	
		    	{name:'name', index:'name',label:'党组织名称', width:100,hidden:false,sortable:true}, 	
		    	{name:'foundDate', index:'foundDate',label:'成立时间', width:100, align:'center', hidden:true}, 
		    	{name:'secretary', index:'secretary',label:'党支部书记', width:100, hidden:true}, 
		    	{name:'idCardNo', index:'idCardNo',label:'身份证号码', width:100, align:'center', hidden:true}, 	
		    	{name:'telephone', index:'telephone',label:'联系电话', width:100, align:'center', hidden:true}, 	
		    	{name:'mobileNumber', index:'mobileNumber',label:'联系手机', width:100, align:'center', hidden:true}, 
		    	{name:'members', index:'',label:'班子成员',formatter:membersFormatter, width:150, hidden:false},
		    	{name:'memberNum', index:'memberNum',label:'党员人数', width:100, align:'center', hidden:false},
		    	{name:'address', index:'address',label:'地址', width:150, hidden:false,sortable:true},
		    	{name:'', index:'',label:'活动记录', formatter:activityFormatter,width:100, align:'center', hidden:false},
		    	{name:'organization.orgName', index:'orgId',label:'所属区域 ', width:150, sortable:true, align:'center', hidden:false}
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewTownPartyOrg=='true'){
					viewTownPartyOrg(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#townPartyOrgList").jqGrid('setFrozenColumns');
		
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
				$("#townPartyOrgDialog").createDialog({
					title:"新增街道社区党组织信息",
					width: dialogWidth,
					height: dialogHeight,
					url:dfop.path+"/townPartyOrgManage/dispatch.action?mode=add&orgId="+getCurrentOrgId(),
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
			var selectedIds = getSelectedIdsForTowPartyOrg();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				return;
			}
			var selectedId = getSelectedIdLastForTowPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
				 return;
			}
			updateTownPartyOrg(selectedId);
		});
		$("#delete").click(function(){
			var allValue = getSelectedIdsForTowPartyOrg();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			var partyOrgArray="";
			for(var i=0;i<allValue.length;i++){
				var rowData = $("#townPartyOrgList").getRowData(allValue[i]);
				partyOrgArray = partyOrgArray+rowData.name;
				if(i!=allValue.length-1){
					partyOrgArray=partyOrgArray+",";
				}
			}
			$.ajax({
				async : true,
				url : dfop.path+"/partyBuildng/memberManage/exsistedMember.action",
				data: {"orgId":getCurrentOrgId(),"partyOrgType":"${partyOrgType}","partyOrg":partyOrgArray},
				success : function(data) {
					if(data>0){
						$.messageBox({level:'warn',message:"您选择的组织下已经存在党员，不能删除！"});
						 return;
					}
					deleteTownPartyOrg(allValue);
				}
			 });
			
		});
		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedIds = $("#townPartyOrgList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds == null || selectedIds.length > 1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			var selectedId = getSelectedIdLastForTowPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewTownPartyOrg(selectedId);
		});
		$("#reload").click(function(){
			$("#searchText").attr("value","请输入党组织名称");
			onOrgChanged(getCurrentOrgId(),isGrid());
		}).click();
	
		$("#search").click(function(event){
			$("#townPartyOrgDialog").createDialog({
				width:650,
				height:320,
				title:'街道社区党组织表查询-请输入查询条件',
	 	 		url:dfop.path+'/townPartyOrgManage/dispatch.action?mode=search',
				buttons: {
			   		"查询" : function(event){
						searchTownPartyOrg($("#maintainForm").serializeObject());
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	
		$("#maintainActivity").click(function(){
			var selectedIds = getSelectedIdsForTowPartyOrg();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择且仅选择一条记录，再进行维护！"});
				return;
			}
			var selectedId = getSelectedIdLastForTowPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行维护！"});
				 return;
			}
			var rowData = $("#townPartyOrgList").getRowData(selectedId);
			var organizationName=rowData.name;
			maintainActivity(selectedId,organizationName);
		});	
	
		$("#managePartyMember").click(function(){
			var selectedIds = getSelectedIdsForTowPartyOrg();
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择且仅选择一条记录，再进行党员维护！"});
				return;
			}
			var selectedId = getSelectedIdLastForTowPartyOrg();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行党员维护！"});
		 		return;
			}
			var rowData = $("#townPartyOrgList").getRowData(selectedId);
			if(rowData.type != dfop.branch){
				$.messageBox({level:'warn',message:"请选择一条是党支部类型的记录，再进行党员维护！"});
				return;
			}
			$("#townPartyOrgDialog").createDialog({
				width:950,
				height:600,
				title:'街道社区党组织党员维护',
	 	 		url:dfop.path+"/partyBuilding/members/memberForTownList.jsp?partyModule="+dfop.partyModule+"&partyOrgType="+dfop.partyOrgType+"&partyOrg="+encodeURI(encodeURI(rowData.name)),
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				},
				close:function(){
					$("#townPartyOrgList").trigger("reloadGrid");
				}
			});
		})
	
	});
	
	
	
	function viewTownPartyOrg(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#townPartyOrgList").getRowData(selectedId);
		if(rowData==null){
			 return;
		}
		$("#townPartyOrgDialog").createFrameDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看街道社区党组织信息",
	 		url:dfop.path+'/townPartyOrgManage/dispatchByEncrypt.action?mode=view&id='+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateTownPartyOrg(selectedId){
		if(selectedId==null){
			return ;
		}
		var rowData =  $("#townPartyOrgList").getRowData(selectedId);
		$("#townPartyOrgDialog").createDialog({
			title:"修改街道社区党组织信息",
			width: dialogWidth,
			height: dialogHeight,
			url:dfop.path+"/townPartyOrgManage/dispatchByEncrypt.action?mode=edit&id="+rowData.encryptId,
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
	function deleteTownPartyOrg(allValue){
		if(allValue==null){
			return ;
		}
		var encryptIds=deleteOperatorByEncrypt("townPartyOrg",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/townPartyOrgManage/deleteTownPartyOrgByIdsByEncrypt.action',
	 				type:'post',
	 				data:{
	 					"ids":encryptIds
	 				},
					success:function(data){
					    $.messageBox({message:"已经成功删除该街道社区党组织信息!"});
						$("#townPartyOrgList").trigger("reloadGrid");
					}
				});
			}
		});
	}
	function searchTownPartyOrg(serializeObject){
		search($.extend({
				'orgId':getCurrentOrgId()
		},serializeObject));
	}
	function search(postData){
		$("#townPartyOrgList").setGridParam({
	 		url:dfop.path+'/townPartyOrgManage/findTownPartyOrgPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#townPartyOrgList").setPostData(postData);
		$("#townPartyOrgList").trigger("reloadGrid");
	}
	function getSelectedIdLastForTowPartyOrg(){
		var selectedId;
		var selectedIds = $("#townPartyOrgList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;selectedIds!=null && i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIdsForTowPartyOrg(){
		var selectedIds = $("#townPartyOrgList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}

function activityFormatter(el,options,rowData){
	
	return "<a href='javascript:maintainActivity("+rowData.id+",\""+rowData.name+"\")'><span>详情</span></a>";
}

