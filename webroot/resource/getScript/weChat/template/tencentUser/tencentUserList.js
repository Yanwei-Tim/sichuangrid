TQ.tencentUserList = function (dfop){
	var currentOrgId=getCurrentOrgId();
	
	$(document).ready(function(){
		loadList();
		$("#reloadTencentUser").click(function(event){
			loadList();
		});
		$("#refreshSearchKey").click(function(){
			$("#searchByCondition").attr("value","请输入微信号");
		});
		//搜索
		$("#fastSearchButton").click(function(){
			fastSearchFun();
	    });
	    //高级搜索
		$("#search").click(function(){
		   searchFun();
		});	
		 //新增
		$("#addTencentUser").click(function(event){
			addFun();
		});

		function loadList(){
			$("#tencentUserList").jqGridFunction({
				datatype: "local",
				sortname: "TENCENT_USER_ID",
				colModel:[
					{name:"tencentUserId", index:"TENCENT_USER_ID",sortable:false,hidden:true },
					{name:"state", index:"TENCENT_USER_ID",sortable:false,hidden:true },
					{name:"operator", index:'TENCENT_USER_ID',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center'},
					{name:"stateOperator",index:"TENCENT_USER_ID",label:"状态",formatter:stateColor,sortable:false,width:150,align:"center"},
					{name:"weChatUserId",index:"weChatUser_Id",label:"微信连接号",sortable:false,width:150,align:"center"},
					{name:"appId",index:"APP_ID",label:"appID",sortable:false,width:150,align:"center"},
					{name:"appSecret",index:"APP_SECRET",label:"appsecret",sortable:false,width:150,align:"center"},
				    {name:'name',index:"NAME",label:'微信昵称',sortable:true,width:150,align:"center"},
				    {name:'organization.orgName',index:"ORG_ID",label:'所属部门',sortable:true,width:150,align:"center"},
				    {name:'createDate',index:"CREATE_DATE",label:'创建时间',sortable:true,width:150,align:"center"}
				]
			});
			onOrgChanged({"tencentUser.organization.id" : currentOrgId});
		}
		
		function onOrgChanged(obj){
			$("#tencentUserList").setGridParam({
				url:PATH+'/tencentUserManage/getTencentUserList.action',
				datatype: "json",
				page:1
			});
			$("#tencentUserList").setPostData(obj);
			$("#tencentUserList").trigger("reloadGrid");
		}
		
		function fastSearchFun(){
			var searchByCondition=$("#searchByCondition").val();
			if(searchByCondition != null && "请输入微信号" != searchByCondition){
				var queryObj = {
					"tencentUser.organization.id" : currentOrgId,
					"tencentUser.name" : searchByCondition
				};
				onOrgChanged(queryObj);
			}
		}
		
		 function searchFun(){
				$("#tencentUserSearchDialog").createDialog({
					width: 650,
					height: 300,
					title:'微信账号查询-请输入查询条件',
					url:PATH+'/tencentUserManage/dispatch.action?mode=search',
					buttons: {
						"查询" : function(event){
							var postData = {
								"tencentUser.organization.id" : currentOrgId,
								"tencentUser.appId" : $("#tencentUser_appId").val(),
								"tencentUser.appSecret" : $("#tencentUser_appSecret").val(),
								"tencentUser.code" : $("#tencentUser_code").val(),
								"tencentUser.name" : $("#tencentUser_name").val()
							};
							onOrgChanged(postData);
							$(this).dialog("close");
						},
						"关闭" : function(){
							$(this).dialog("close");
						}
					}
				});
		}
		
		function addFun(){
			if (currentOrgId==null||currentOrgId==""){
				$.notice({level:'warn',
						message:'请先选择一个部门'});
			}else{
				$("#tencentUserDialog").createDialog({
					title:"新增微信账号",
					width: 600,
					height: 430,
					url:PATH+'/tencentUserManage/dispatch.action?mode=add&tencentUser.organization.id='+currentOrgId,
					buttons: {
						"保存并关闭" : function(event){
							$("#tencentUserForm").submit();
						},
						"关闭" : function(event){
							$(this).dialog("close");
						}
					}
				});
			}
		}
		
		function deleteFun(){
			var rowIds = $("#tencentUserList").jqGrid("getGridParam", "selarrrow");
			if(rowIds.length ==0){
				 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			$.confirm({
				title:"确认删除",
				message:"确定要删除吗?一经删除将无法恢复",
				okFunc: function(){
					$.ajax({
						url:PATH+"/tencentUserManage/deleteTencentUser.action?idsStr="+rowIds,
						success:function(data){
							$("#tencentUserList").trigger("reloadGrid");
						    $.messageBox({message:"已经成功删除所选微信账号信息!"});
					    }
				    });
			    }
		 	});
		}
	
	});
	

	function stateColor(el,options,rowData){
			var state = rowData.state;
			if(state=='' || state == undefined || state == null)
				return '<span><span style="color:#d6d6d6;">██</span>待机</span>';
			else
				return '<span><span style="color:#99cc00;">██</span>当前登录</span>';
		}

		function updateOperator(event,selectedId){
			$("#tencentUserDialog").createDialog({
		    	title:'修改微信账号',
		    	width:600,
				height:430,
				postData:{
					"tencentUser.organization.id":currentOrgId,
					"tencentUser.tencentUserId":selectedId,
					"mode":"edit"
				},
				url:PATH+'/tencentUserManage/dispatch.action',
				buttons: {
					"保存" : function(event){
						$("#tencentUserForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
		
		function deleteOperator(event,rowIds){
			var rowData = $("#tencentUserList").getRowData(rowIds);
			$.confirm({
				title:"确认删除",
				message:"确定要删除吗?一经删除将无法恢复",
				okFunc: function(){
					$.ajax({
						url:PATH+"/tencentUserManage/deleteTencentUser.action?idsStr="+rowIds,
						success:function(data){
							$("#tencentUserList").trigger("reloadGrid");
						    $.messageBox({message:"已经成功删除所选微信账号信息!"});
					    }
				    });
			    }
		 	});
		}

}