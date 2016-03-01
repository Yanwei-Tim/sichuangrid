TQ.newMyGroupList = function (dfop){
	$(document).ready(function(){
		loadList();
	});
	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入群组名称");
	});
	function loadList(){
		$("#myGroupList").jqGridFunction({
			datatype: "local",
			colNames:['id','操作','群组名称','群组成员数','群组描述'],
		   	colModel:[
		         {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
		         {name:"operator", index:'operator', label:'操作',formatter:operatorFormatter,width:120,frozen:true,sortable:false,align:'center' },
		         {name:'name',index:'name',label:'群组名称',width:200,sortable:true},
		         {name:'member',index:'member',label:'群组成员数',formatter:viewMyGroup,sortable:true,width:100,align:'center'},
		         {name:'remark',index:'remark',label:'群组描述',width:300,sortable:true}
			],
			scrollrow:true,
			onSelectAll:function(aRowids,status){ },
			multiselect:true
			
		});
		onOrgChanged();
	}

	function onOrgChanged(){
		$("#myGroupList").setGridParam({
			url:PATH+"/contact/myGroupManage/findMyGroupsForPage.action",
			datatype: "json",
			page:1
		});
		$("#myGroupList").trigger("reloadGrid");
	}

	function operatorFormatter(el,options,rowData){
		return "<pop:JugePermissionTag ename='updateGroup'><a href='javascript:;' onclick='updateMyGroup(event,"+rowData.id+");'><span>修改</span></a></pop:JugePermissionTag><pop:JugePermissionTag ename='deleteGroup'> | <a href='javascript:;' onclick='deleteMyGroup(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
	}
	function viewMyGroup(el,options,rowData){
		if(rowData.member==undefined){
			return "";
		}
		return "<a href='javascript:' onclick='viewMember(event,"+rowData.id+");'><span>"+rowData.member+"</span></a>";
	}

	$("#deleteAllGroup").click(function(){
		var selectedIds = getSelectedIdList();
		if(selectedIds==null || selectedIds=='undefined' || selectedIds.length==0){
			$.messageBox({level:'warn',message:"请至少选择一条数据进行删除"});
			return;
		}
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除，群组信息无法恢复，继续删除?",
			okFunc: function(){
				$.ajax({
					url:PATH+'/contact/myGroupManage/deleteAllGroup.action?groupIds='+selectedIds,
					success:function(data){
						if(data == true){
							$.messageBox({ message:"成功删除群组信息!"});
							$("#myGroupList").trigger("reloadGrid");
							return;
						}
			            $.messageBox({
							message:data,
							level: "error"
						});
			        }
				});
		   }
	 	});
	});

	$("#addGroup").click(function(){
		$("#maintainMyGroupDlg").createDialog({
			width: 650,
			height: 360,
			title:"新增群组",
			url:PATH+"/contact/myGroupManage/dispatch.action?mode=add",
			buttons:{
				"保存":function(){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#searchByConditionButton").click(function(event){
		var condition = $("#searchByCondition").val();
		if(condition == '请输入群组名称'){
			return;
		}
		$("#myGroupList").setGridParam({
			url:PATH+'/contact/myGroupManage/searchMyGroupByCondition.action?contacterVo.name='+encodeURIComponent(condition)+'&belongClass=myGroup',
			datatype: "json",
			page:1
		});
		$("#myGroupList").trigger("reloadGrid");
	});

	$("#editHasContacter").click(function(event){
		var selectId =  $("#myGroupList").jqGrid("getGridParam", "selarrrow");
		if(selectId==null || selectId=='' || selectId==undefined){
			$.messageBox({level:'warn',message:"请选择一条数据进行维护！"});
			return;	
		}
		if(selectId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行维护！"});
			return;
		}
		$("#maintainMemberDlg").createDialog({
			width: 850,
			height: 630,
			title:"成员信息",
			url:PATH+"/contact/myGroupManage/dispatchMyGroupHasContacter.action?contacterId="+selectId+"&mode=myGroupEditMember",
			buttons:{
				"关闭" : function(){
					$("#myGroupList").trigger("reloadGrid");
					$(this).dialog("close");
				}
			}
		});
	});

	$("#reload").click(function(){
		onOrgChanged();
		$("#searchByCondition").attr("value","请输入群组名称");
	});

	
	$("#searchMyGroupByConditions").click(function(){
		$("#searchMyGroupDlg").createDialog({
			width: 600,
			height: 200,
			title:"成员信息",
			url:"/interaction/contact/myGroup/searchMyGroupByCondition.jsp",
			buttons:{
				"查询" : function(){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
					$("#myGroupList").trigger("reloadGrid");
					$(this).dialog("close");
				}
			}
		});
	});

	function getSelectedIdList(){
		var selectedIds = $("#myGroupList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}