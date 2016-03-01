TQ.administrativeOrgEimeLimisStandardList = function (dfop){

	$(document).ready(function(){
		$("#administrativeOrgTimeLimitStandardList").jqGridFunction({
			datatype: "local",
			colModel:[
						{name:"id", index:"id", hidden:true},
						{name:"encryptId",index:'id',frozen:true,hidden:true},
						{name:'displayName',label:'行政部门',formatter:defaultName,width:100,align:"center"},
						{name:'issueDomainDisplayName',label:'事件类型',width:100,align:"center"},
						{name:'issueTypeDisplayName',label:'事件子类',width:100,align:"center"},
						{name:'yellowLimitAccept',index:'yellowLimitAccept',label:'黄牌受理时限',width:100,align:"center"},
						{name:'yellowLimitHandle',index:'yellowLimitHandle',label:'黄牌办理时限',width:100,align:"center"},
						{name:'yellowLimitVerify',index:'yellowLimitVerify',label:'黄牌验证时限',width:100,align:"center"},
						{name:'redLimitAccept',index:'redLimitAccept',label:'红牌受理时限',width:100,align:"center"},
						{name:'redLimitHandle',index:'redLimitHandle',label:'红牌办理时限',width:100,align:"center"},
						{name:'redLimitVerify',index:'redLimitVerify',label:'红牌验证时限',width:100,align:"center"},
						{name:'remark',index:'remark',label:'备注'}
					],
			multiselect:true
		});
		onLoad();

		$("#add").click(function(event){
			$("#administrativeOrgTimeLimitStandardDialog").createDialog({
				width: 700,
				height: 600,
				title: "添加行政部门时限标准",
				url: PATH+"/administrativeOrgTimeLimitStandardManage/dispatch.action?mode=add",
				buttons: {
			   		"保存" : function(event){
						$("#administrativeOrgTimeLimitStandardForm").submit();
			   		},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		$("#update").click(function(event){
			var selectedId = $("#administrativeOrgTimeLimitStandardList").jqGrid("getGridParam", "selrow");
			var value = $("#administrativeOrgTimeLimitStandardList").getRowData(selectedId);
			var allValue = getSelectedIds();
			if(value.displayName == '' || typeof(value.displayName) == 'undefined') {
				var isAdmin = dfop.userName;
				if(isAdmin != 'admin'){
					$.messageBox({level:'warn',message:"只有系统管理员才能对默认时限进行修改！"});
					return;
				}
			}
			if(value!=null && allValue.length==1){
				var mode='edit';
				var width=600;
				var height=520;
				var title="行政部门";
				if(value.issueDomainDisplayName == null || value.issueDomainDisplayName == '' || typeof(value.issueDomainDisplayName) == 'undefined'){
					mode='view1';
					height=240;
					title="默认";
				}
				$("#administrativeOrgTimeLimitStandardDialog").createDialog({
					width: width,
					height: height,
					title: "修改"+title+"时限标准",
					url: PATH+"/administrativeOrgTimeLimitStandardManage/dispatch.action?mode="+mode+"&administrativeOrgTimeLimitStandard.id="+value.encryptId,
					buttons: {
				   		"保存" : function(event){
							$("#administrativeOrgTimeLimitStandardForm").submit();
				   		},
				   		"关闭" : function(){
				        	$(this).dialog("close");
				   		}
					}
				});
			}else{
				$.messageBox({level:'warn',message:"请选择一条数据再修改！"});
				return;
			}
			
		});
		$("#delete").click(function(event){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level : 'warn',message:"请选择一条或多条记录，再进行删除！"});
				return;
			}
			var isAviable=true;
			$.each(allValue,function(i){
				var value = $("#administrativeOrgTimeLimitStandardList").getRowData(allValue[i]);
				if(value.issueDomainDisplayName == null || value.issueDomainDisplayName == '' || typeof(value.issueDomainDisplayName) == 'undefined'){
					$.messageBox({level : 'warn',message:"选择删除的数据中包括默认规则，默认规则不允许删除，请重新选择！"});
					isAviable = false;
					return;
				}
			});
			var selectedIds=deleteOperatorByEncrypt("administrativeOrgTimeLimitStandard",allValue,"encryptId");
			if (isAviable){
				$.confirm({
					title:"确认删除",
					message:"该信息删除后,将无法恢复,您确认删除该信息吗?",
					width:400,
					okFunc:function(){
						$.ajax({
							url:PATH+"/administrativeOrgTimeLimitStandardManage/deleteAdministrativeOrgTimeLimitStandardByIds.action",
							type:"post",
							data:{
								"selectedIds":selectedIds
							},

							success:function(data){
								if(data == true){
									$.messageBox({message:"已经成功删除该信息!"});
									$("#administrativeOrgTimeLimitStandardList").trigger("reloadGrid");
								}else{
									$.messageBox({message:data,level : 'error'});
								}  
							}
						});
					}
				});
			}
		});


		$("#refresh").click(function(event){
			reload();
		});
	});

	function getSelectedIds(){
		var selectedIds = $("#administrativeOrgTimeLimitStandardList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	function onLoad(){
		$("#administrativeOrgTimeLimitStandardList").setGridParam({
			url:PATH+'/administrativeOrgTimeLimitStandardManage/findAdministrativeOrgTimeLimitStandardList.action',
			datatype: "json"
		});
		$("#administrativeOrgTimeLimitStandardList").setPostData({
			"userOrganization.id":USER_ORG_ID,
			"page":1
		});
		$("#administrativeOrgTimeLimitStandardList").trigger("reloadGrid");
	}

	function reload(){
		onLoad();
	}

	function defaultName(el, options, rowData){	
		if(''==rowData.useLevel.displayName || undefined== rowData.useLevel.displayName){
			return "";
		}else{
			return rowData.useLevel.displayName;
		}
	}

}