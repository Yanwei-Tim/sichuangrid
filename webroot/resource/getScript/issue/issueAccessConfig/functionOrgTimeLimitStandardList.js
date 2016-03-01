TQ.functionOrgEimeLimitStandardList = function (dfop){
	function onOrgChanged(){
		$("#standardList").setGridParam({
			url:PATH+"/issues/issueStandardForFunOrgManage/findIssueStandardForFunOrgList.action",
			datatype: "json",
			page:1
		});
		$("#standardList").trigger("reloadGrid");
	}

	$(document).ready(function(){
		$("#standardList").jqGridFunction({
			datatype: "local",
			colNames:['id','encryptId','职能部门','项目名称','黄牌受理时限','黄牌办理时限','黄牌验证时限','红牌受理时限','红牌办理时限','红牌验证时限','备注'],
			colModel:[
				{name:"id", index:"id", hidden:true},
				{name:"encryptId",index:'id',frozen:true,hidden:true},
				{name:'organization.orgName',width:180},
				{name:'itemName',index:'itemName',formatter:itemNameFormatter,width:180},
				{name:'yellowLimitAccept',index:'yellowLimitAccept',width:100},
				{name:'yellowLimitHandle',index:'yellowLimitHandle',width:100},
				{name:'yellowLimitVerify',index:'yellowLimitVerify',width:100},
				{name:'redLimitAccept',index:'redLimitAccept',width:100},
				{name:'redLimitHandle',index:'redLimitHandle',width:100},
				{name:'redLimitVerify',index:'redLimitVerify',width:100},
				{name:'remark',width:300}
			],
			multiselect:true
		});
		onOrgChanged();
		
		$("#add").click(function(){
			$("#issueDialog").createDialog({
				width:600,
				height:520,
				title:"新增职能部门时限标准信息",
				url:PATH+"/issues/issueStandardForFunOrgManage/dispatch.action?mode=add",
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
			var selectedId = $("#standardList").jqGrid("getGridParam", "selrow");
			var value = $("#standardList").getRowData(selectedId);
			var allValue = getSelectedIds();
			if(selectedId==null || allValue.length>1){
				$.messageBox({level : 'warn',message:"请选择一条记录，再进行修改！"});
				return;
			}
			if(value["organization.orgName"]==null||value["organization.orgName"]==""){
				var isAdmin = dfop.userName;
				if(isAdmin != 'admin'){
					$.messageBox({level : 'warn',message:"只有系统管理员才能对默认时限进行修改！"});
					return;
				}
			}
			var mode='edit';
			var width=600;
			var height=520;
			var title="职能部门";
			if(value.itemName == null || value.itemName == '' || typeof(value.itemName) == 'undefined'){
				mode='view1';
				height=240;
				title="默认";
			}
				
			$("#issueDialog").createDialog({
				width:width,
				height:height,
				title:"修改"+title+"时限标准信息",
				url:PATH+"/issues/issueStandardForFunOrgManage/dispatch.action?mode="+mode+"&issueStandardForFunOrg.id="+value.encryptId,
				buttons :{
					"保存" : function(){
						$("#maintainForm").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		});
		
		$("#view").click(function(){
			var selectedId =$("#standardList").jqGrid("getGridParam", "selrow");
			if(selectedId==null){
		 		return;
			}
			doubleClickTable(selectedId);
		});
		
		$("#refresh").click(function(){
			onOrgChanged();
		});
		
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level : 'warn',message:"请选择一条或多条记录，再进行删除！"});
				return;
			}
			var isAviable=true;
			$.each(allValue,function(i){
				var value = $("#standardList").getRowData(allValue[i]);
				if(value.itemName == null || value.itemName == '' || typeof(value.itemName) == 'undefined'){
					$.messageBox({level : 'warn',message:"选择删除的数据中包括默认规则，默认规则不允许删除，请重新选择！"});
					isAviable = false;
					return;
				}
			});
			var selectedIds=deleteOperatorByEncrypt("standard",allValue,"encryptId");
			if(isAviable) {
				$.confirm({
						title:"确认删除",
						message:"删除之后数据将无法恢复，确认要删除吗？",
						okFunc: function() {
							$.ajax({
								url:PATH+"/issues/issueStandardForFunOrgManage/deleteIssueStandardForFunOrgs.action",
								type:"post",
								data:{
									"ids":selectedIds
								},
								success:function(data){
									$("#standardList").trigger("reloadGrid");
								    $.messageBox({message:"已经成功删除该职能部门时限标准!"});
							}
						});
					}
				});
			}
		});
	});

	function getSelectedIds(){
		var selectedIds = $("#standardList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	function doubleClickTable(selectedId){
		$("#issueDialog").createDialog({
			width:600,
			height:400,
			title:"查看职能部门时限标准信息",
			url:PATH+"/issues/issueStandardForFunOrgManage/dispatch.action?mode=view",
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

}