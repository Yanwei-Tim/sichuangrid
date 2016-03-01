TQ.memberEnrollList = function (dfop){
	function initButtonsEnable(){
		if(isUserVillageUp() && isVillageUp() || $("#currentOrgId").val()!=USER_ORG_ID){
			$("#addMemberEnroll,#updateMemberEnroll,#deleteMemberEnroll,#signMemberEnroll,#setLogoutStatus,#downMemberEnroll").hide();
		}
	}
	function loadMemberData(conditions) {
		var lStatus = $('#logoutStatus option:selected').val();
		/* if(lStatus == 0){
			//未注销
			$("#logoutMemberEnroll").show();
			$("#unLogoutMemberEnroll").hide();
			$("#signMemberEnroll").show();
		}else if(lStatus == 1){
			//已注销
			$("#logoutMemberEnroll").hide();
			$("#unLogoutMemberEnroll").show();
			$("#signMemberEnroll").hide();
		}else{
			$("#logoutMemberEnroll").hide();
			$("#unLogoutMemberEnroll").hide();
			$("#signMemberEnroll").hide();
		} */
		initButtonsEnable();
		
		var initParam = {
				"membersEnroll.regActivitiesType":regActivitiesType,
				"orgId":getCurrentOrgId(),
				"membersEnroll.member.idCardNo":conditions,
				"membersEnroll.logoutStatus":$('#logoutStatus option:selected').val()
			}
		
		$("#membersErollList").setGridParam({
			url:dfop.path+"/partyBuildng/doubleRegActivities/findMembersEnrollForPageByCondition.action",
			datatype: "json",
			page:1
		});
		$("#membersErollList").setPostData(initParam);
		$("#membersErollList").trigger("reloadGrid");
	}

	//社区级别以上
	function isVillageUp(){
		return $("#currentOrgId").attr("orgLevelInternalId") > dfop.orgLevel;
	}

	//社区级别用户以上
	function isUserVillageUp(){
		return USER_ORG_LEVEL > dfop.orgLevel;
	}

	$(function (){
		$("#membersErollList").jqGridFunction({
			datatype: "local",
			height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-100,
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false},
		        {name:"member.name",index:"pm.name",sortable:true,label:'姓名',width:100,formatter:nameFormatter},
		        {name:"member.gender",index:"pm.gender",sortable:true,label:'性别',width:100,formatter:genderFormatter},
		        {name:"member.idCardNo",index:"pm.idCardNo",sortable:true,label:'身份证号',width:150},
		        {name:"member.name",index:"pm.name",sortable:true,label:'联系人',width:150},
		        {name:"member.telephone",index:"pm.telephone",sortable:true,label:'联系电话',width:150},
		        {name:"member.belongOrg",index:"pm.belongOrg",sortable:true,label:'所属部门',width:120,formatter:belongOrgFormatter},
		        {name:"isEnroll",index:"pme.isEnroll",sortable:true,label:'是否报到',width:70,formatter:getStatus},
		        {name:"logoutStatus",index:"pme.logoutStatus",sortable:true,label:'是否注销',width:70,formatter:getLogoutStatus},
		        {name:"logoutStatus",sortable:false,hidden:true,hidedlg:true},
		        {name:"organization.orgName",index:"org.orgName",sortable:true,label:'所属区域',width:150}
			],
			multiselect: true,
		    ondblClickRow: viewMember
		});
		
		initButtonsEnable();
		
		$("#fastSearchButton").click(function(){
			search();
		});
		$("#searchTextMember").focus(function(){
	        this.select();
		 });
		$("#refreshMemberEnroll").click(function(){
			$("#searchTextMember").attr("value","请输入身份证号码");
			onOrgChanged(getCurrentOrgId(),isGrid());
		}).click();
		$("#refreshSearchKey").click(function(){
			$("#searchTextMember").attr("value","请输入身份证号码");
		});
		
		// 改变组织机构树时加载列表
		function onOrgChanged(orgId,isgrid){
			search({ 
				"orgId":orgId 
			});
			loadMemberData();
		}
		
		$("#logoutStatus").change(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		
		function search(){
			var conditions = $("#searchTextMember").val();
			if(conditions == '请输入身份证号码') return;
			loadMemberData(conditions);
		}
		
		$("#viewMember").click(function(){
			if($("#viewMember").attr("disabled")){
				return ;
			}
			var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
			var selectedId = getSelectedIdLast();
			if(selectedId==null || selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewMember(selectedId);
		});
		
		loadMemberData();
		
		$("#reloadMember").click(function(){
			$("#searchTextMember").attr("value","请输入身份证号码");
			loadMemberData();
		});
		
	});

	function viewMember(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#memberList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#memberDialog").createTabDialog({
			title:"查看成员信息",
			width: dialogWidthMember,
			height: dialogHeightMember,
			postData:{
				imageType:"population"
			},
			tabs:[
					{title:'成员信息',url:dfop.path+'/partyBuildng/memberManage/dispatch.action?mode=view&member.partyOrgType='+partyOrgType+'&member.id='+id}
				]
		});
	}

	$("#updateMemberEnroll").click(function(){
		var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			 return;
		}
		var updateMemberEnroll=new Array();
		for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#membersErollList").getRowData(selectedIds[i]);
			if(rowData.logoutStatus==0){
				updateMemberEnroll.push(selectedIds[i]);
			}
		}
		selectedIds=updateMemberEnroll;
		if(selectedIds==null||selectedIds.length==0){
			$.messageBox({level:'warn',message:"注销的数据，不能进行修改!"});
			return;
		}
		updateOperator(selectedId);
	});

	function updateOperator(selectedId,event){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#membersErollList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#memberErollDialog").createTabDialog({
			title:"修改党员报名情况信息",
			width: dialogWidthMember,
			height: dialogHeightMember,
			postData:{
				imageType:"population"
			},
			tabs:[
					{title:'党员报到情况',url:dfop.path+'/partyBuildng/doubleRegActivities/dispatch.action?mode=edit&membersEnroll.regActivitiesType='+regActivitiesType+'&membersEnroll.id='+id}
					
				]
		});
	}

	$("#viewMemberEnroll").click(function(){
		if($("#viewMemberEnroll").attr("disabled")){
			return ;
		}
		var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		viewMember(selectedId);
	});

	function viewMember(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#membersErollList").getRowData(selectedId);
		var isEnroll=rowData.isEnroll;
		if(isEnroll=="" || isEnroll=="否"){
			$.messageBox({level:'warn',message:"该党员未双报到，不能查看详情!"});
	 		return;
		}
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#memberErollDialog").createTabDialog({
			title:"查看党员报名情况信息",
			width: dialogWidthMember,
			height: dialogHeightMember,
			postData:{
				imageType:"population"
			},
			tabs:[
					{title:'党员报到情况',url:dfop.path+'/partyBuildng/doubleRegActivities/dispatch.action?mode=view&membersEnroll.regActivitiesType='+regActivitiesType+'&membersEnroll.id='+id}
				]
		});
	}

	$("#addMemberEnroll").click(function(){
		$("#memberErollDialog").createTabDialog({
			title:"新增党员双报到信息",
			width: dialogWidthMember,
			height: dialogHeightMember,
			postData:{
				imageType:"population"
			},
			tabs:[
					{title:'党员报到信息',url:dfop.path+'/partyBuildng/doubleRegActivities/dispatch.action?mode=add&membersEnroll.regActivitiesType='+regActivitiesType}
				]
		});
	});

	$("#deleteMemberEnroll").click(function(){
		var selectedIds = getSelectedIds();
		if(selectedIds ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(selectedIds);
	});

	function deleteOperator(selectedIds,event){
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
					url:dfop.path+'/partyBuildng/doubleRegActivities/deleteByIds.action?ids='+selectedIds+'&membersEnroll.regActivitiesType='+regActivitiesType,
					success:function(data){
						if(data!= null) {
							$.messageBox({message:data,level:"error"});
						} else {
							$("#membersErollList").trigger("reloadGrid");
							$.messageBox({message:"已经成功删除该党员信息!"});
						}
					}
				});
			}
		});
	}

	//报到
	$("#signMemberEnroll").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行报到！"});
			 return;
		}
		var signMemberEnroll=new Array();
		var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行报到！"});
			return;
		}
		for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#membersErollList").getRowData(selectedIds[i]);
			if(rowData.logoutStatus==0){
				signMemberEnroll.push(selectedIds[i]);
			}
		}
		selectedIds=signMemberEnroll;
		if(selectedIds==null||selectedIds.length==0){
			$.messageBox({level:'warn',message:"注销状态不能进行报到！"});
			return;
		}
		$.ajax({
			url:dfop.path+"/partyBuildng/doubleRegActivities/enroll.action",
			data:{
				"membersEnroll.regActivitiesType":regActivitiesType,
				"membersEnroll.id":selectedId,
				"membersEnroll.isEnroll":0
			},
			success:function(data){
				if(data!= null){
					$.messageBox({message:data,level:"error"});
				}else{
					$("#membersErollList").trigger("reloadGrid");
					$.messageBox({message:"报到成功!"});
				}
			}
		});
	});

	//注销
	$("#logoutMemberEnroll").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行注销！"});
			 return;
		}
		var logoutMemberEnroll=new Array();
		var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds == null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行注销！"});
			return;
		}
		for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#membersErollList").getRowData(selectedIds[i]);
			if(rowData.logoutStatus==0){
				logoutMemberEnroll.push(selectedIds[i]);
			}
		}
		selectedIds=logoutMemberEnroll;
		if(selectedIds==null||selectedIds.length==0){
			$.messageBox({level:'warn',message:"没有可注销的数据！"});
			return;
		}
		$.confirm({
			title:"确认注销",
			message:"确定要注销吗?",
			width: 300,
			okFunc: function(){
			$.ajax({
				url:dfop.path+"/partyBuildng/doubleRegActivities/logout.action?id="+selectedIds+"&logoutStatus=1&isEnroll=",
				success:function(responseData){
					$("#membersErollList").trigger("reloadGrid");
					$.messageBox({message:"注销成功！"});
				}
			});
		}
		});
	});

	//取消注销
	$("#unLogoutMemberEnroll").click(function(){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行注销！"});
			 return;
		}
		var unLogoutMemberEnroll=new Array();
		var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds == null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行取消注销！"});
			return;
		}
		for(var i=0;i<selectedIds.length;i++){
			var rowData = $("#membersErollList").getRowData(selectedIds[i]);
			if(rowData.logoutStatus==1){
				unLogoutMemberEnroll.push(selectedIds[i]);
			}
		}
		selectedIds=unLogoutMemberEnroll;
		if(selectedIds==null||selectedIds.length==0){
			$.messageBox({level:'warn',message:"没有可取消注销的数据！"});
			return;
		}
		$.confirm({
			title:"确认取消注销",
			message:"确定要取消注销吗?",
			width: 300,
			okFunc: function(){
			$.ajax({
				url:dfop.path+"/partyBuildng/doubleRegActivities/logout.action?id="+selectedIds+"&logoutStatus=0&isEnroll=0",
				success:function(responseData){
					$("#membersErollList").trigger("reloadGrid");
					$.messageBox({message:"取消注销成功！"});
				}
			});
		}
		});
	});

	$("#searchMemberEnroll").click(function(){
		var searchTextMember = $("searchTextMember").val();
	});

	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	function getSelectedIds(){
		var selectedIds = $("#membersErollList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	function nameFormatter(el,options,rowData){
		if(null == rowData.member.name) {
			return "&nbsp;&nbsp;"
		}else if(rowData.logoutStatus==1){
			return '<font color="#778899">'+rowData.member.name+'</font>';
		}
		return '<font color="#000000">'+rowData.member.name+'</font>';
	}

	//得到对应的status值
	function getStatus(val){
		if(val == 0){return dfop.yesName;
		}else if(val == 1){
			return dfop.noName;
		}else{return "";}
	}
	function getLogoutStatus(val){
		if(val == 0){return dfop.noName;
		}else if(val == 1){
			return dfop.yesName;
		}else{return "";}
	}
}