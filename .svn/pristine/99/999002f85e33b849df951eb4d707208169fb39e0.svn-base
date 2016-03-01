TQ.primaryMemberslist =function (dfop){

	var dialogWidth=820;
	var dialogHeight=610;
	var currentOrgId=getCurrentOrgId();
	$(document).ready(function(){
		//是否进入了网格
		if(isGrid()){
			$("#displayLevel"+dfop.primaryMembers_isHaveJob).hide();
		}
		//是否在任
		//var isHaveJob='${primaryMembers.isHaveJob}';
		
		if(dfop.primaryMembers_isHaveJob == 1){
			$("#add"+dfop.primaryMembers_isHaveJob+",#update"+dfop.primaryMembers_isHaveJob+",#combinePrimaryMembers").hide();
		}
		
		//服务成员列表
		$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").jqGridFunction({
			mtype:'post',
			datatype: "local",
			colNames:['id','encryptId','orgId','操作','姓名','性别','身份证号','年龄','手机','电话','所在组织','所属区域(网格)'],
			colModel:[
				{name:"id",index:"id",key:true,sortable:true,hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				{name:"organization.id",index:"organization.id",sortable:false,hidden:true,hidedlg:true},
				{name:"operation",index:"id",sortable:false,formatter:operateMemberFormatter,width:120,align:"center"},
				{name:'name',index:"name",width:100,sortable:true,formatter:viewMemberFormatter},
				{name:'gender',index:'gender',formatter:genderFormatter,width:80,sortable:true,align:"center"},
				{name:'idcardNo',index:"idcardNo",width:150,sortable:true},
				{name:'years',index:"years",width:50,sortable:true,align:"center"},
				{name:'mobile',index:"mobile",width:100,sortable:true},
				{name:'telephone',index:'telephone',sortable:true,width:110,align:"center"},
				{name:'primaryOrgName',index:'primaryOrgName',width:80,sortable:false,align:"center"},
				{name:"org.orgName",index:"orgName",sortable:true,width:350}
			],
			multiselect:true,
			showColModelButton:true,
			sortname:'pm.id',
			ondblClickRow:viewPrimaryMember,
			height:$(".ui-layout-center").height()-200
		});
		
		$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").jqGrid('setFrozenColumns');
		    loadMembers();//列表加载
		
		$("#displayLevel"+dfop.primaryMembers_isHaveJob+"").change(function(){
			loadMembers(); //下拉加载...
		});
		$("#refreshSearchKey"+dfop.primaryMembers_isHaveJob+"").click(function(event){
			$("#searchPrimaryMembersVo_condition"+dfop.primaryMembers_isHaveJob).attr("value","请输入姓名");
		});
		$("#fastSearchButton"+dfop.primaryMembers_isHaveJob).click(function(){
			loadMembers(); //快速搜索
		});
		
		//查看成员详细信息
		function viewMemberFormatter(el,options,rowData){
			return "<a href='javascript:;' <pop:JugePermissionTag ename='viewPrimaryMembers'> onclick='viewPrimaryMember("+rowData.id+")' </pop:JugePermissionTag>   >"+rowData.name+"</a>";
		}
		//获取成员列表行数
		function countSelectedIds(){
			var selectedIds = $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").jqGrid("getGridParam", "selarrrow");
			return null == selectedIds ? 0 :selectedIds.length;
		}
		//修改成员
		$("#update"+dfop.primaryMembers_isHaveJob).click(function(){
			updateOperatorByButtons();
		});

		//删除按钮事件
		$("#delete"+dfop.primaryMembers_isHaveJob).click(function(){
			var selectedIds = $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").jqGrid("getGridParam", "selarrrow");
			if(selectedIds=="" || selectedIds==null){
				$.messageBox({level:'warn',message:"请选择一条成员信息进行删除！"});
		 		return;
			}
			deleteOperator(selectedIds);
		});
		//新增按钮事件
		$("#add"+dfop.primaryMembers_isHaveJob).click(function(event){
			$("#primaryMembersDialog").createDialog({
				title:"新增成员",
				width: dialogWidth,
				height: dialogHeight,
				url:PATH+'/primaryOrg/primaryMembersManage/dispatch.action?mode=add&dailogName=primaryMembersDialog&orgId='+currentOrgId+'&displayLevel='+$("#displayLevel"+dfop.primaryMembers_isHaveJob).val()+'&primaryMembers.isHaveJob='+dfop.primaryMembers_isHaveJob,
				buttons: {
					"保存" : function(event){
						$("#primaryMembersForm").submit();
					},
					"取消" : function(event){
						$(this).dialog("close");
					}
				}
			});
		});
		
		//高级搜索对话框
		$("#search"+dfop.primaryMembers_isHaveJob).click(function(){
			$("#primaryMembersDialog").createDialog({
				title:"成员信息查询-请输入查询条件",
				width: 600,
				height: 400,
				url:PATH+'/primaryOrg/primaryMembersManage/dispatch.action?mode=search&primaryMemberVo.org.id='+currentOrgId+'&primaryMembers.isHaveJob='+dfop.primaryMembers_isHaveJob+'&primaryMembers.displayLevel='+$("#displayLevel"+dfop.primaryMembers_isHaveJob).val(),
				buttons: {
					"查询" : function(event){
						var isSubmit = isCanSubmit();
						if(isSubmit){
							loadData($("#serachPrimaryMember_form").serializeArray());
							$(this).dialog("close");
						}else{
							$.messageBox({message : "组织名称不存在, 请重新选择!",level : "error"});
						}
					},
					"关闭" : function(){
			        	$(this).dialog("close");
					}
				}

			});
		});
		
		//刷新按钮事件
		$("#reload"+dfop.primaryMembers_isHaveJob).click(function(){
			$("#searchPrimaryMembersVo_condition"+dfop.primaryMembers_isHaveJob).val('请输入姓名');
			loadMembers();
		});
		//加载成员
		function loadMembers() {
			var condition = $("#searchPrimaryMembersVo_condition"+dfop.primaryMembers_isHaveJob).val();
			var listParam = null;
			listParam = {
				"primaryMemberVo.isHaveJob":dfop.primaryMembers_isHaveJob,
				'primaryMemberVo.org.Id':getCurrentOrgId(),
				"primaryMemberVo.displayLevel":$("#displayLevel"+dfop.primaryMembers_isHaveJob).val(),
				"primaryMemberVo.isPrimaryMember":dfop.primaryMemberVo_isPrimaryMember
			};
			if('请输入姓名' != condition) {
				$.extend(listParam,{'primaryMemberVo.fastSearchKeyWords':condition});
			}
			loadData(listParam);
			$("#primaryMember"+dfop.primaryMembers_isHaveJob+"List").trigger("reloadGrid");
			$("#primaryMember"+dfop.primaryMembers_isHaveJob+"List").jqGrid('setFrozenColumns');
		}
		
		
		//加载服务成员数据
		function loadData(listParam) {
			$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").setGridParam({
				url:PATH+'/primaryOrg/primaryMembersManage/findPrimaryMembers.action',
				datatype: "json",
				page:1
			});
			$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").setPostData(listParam);
			$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").trigger("reloadGrid");
		}
	});
	//修改成员信息
	function updateOperator(selectedId){
		var rowData =  $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").getRowData(selectedId);
		$("#primaryMembersDialog").createDialog({
			title:'修改成员信息',
			width: dialogWidth,
			height: dialogHeight,
			url:PATH+'/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=edit&selectedId='+rowData.encryptId+'&displayLevel='+$("#displayLevel"+dfop.primaryMembers_isHaveJob).val()+'&primaryMembers.isHaveJob='+dfop.primaryMembers_isHaveJob,
			buttons: {
				"保存" : function(event){
		   			$("#primaryMembersForm").submit();
	   			},
	   			"关闭" : function(){
			        	$(this).dialog("close");
					}
			}
		});
	}

	//修改成员信息
	function updateOperatorByButtons(){
		var selectedId = getSelectedIds();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一个成员维护对象！"});
	 		return;
		} 
		var rowData =  $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").getRowData(selectedId);
		$("#primaryMembersDialog").createDialog({
			title:'修改成员信息',
			width: dialogWidth,
			height: dialogHeight,
			url:PATH+'/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=edit&selectedId='+rowData.encryptId+'&displayLevel='+$("#displayLevel"+dfop.primaryMembers_isHaveJob).val()+'&primaryMembers.isHaveJob='+dfop.primaryMembers_isHaveJob,
			buttons: {
				"保存" : function(event){
		   			$("#primaryMembersForm").submit();
	   			},
	   			"关闭" : function(){
			        	$(this).dialog("close");
					}
			}
		});
	}
	//删除成员
	function deleteOperator(selectedId){
		var selectedIds=deleteOperatorByEncrypt('primaryMembers'+dfop.primaryMembers_isHaveJob,selectedId,'encryptId');
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.ajax({
					url:PATH+'/primaryOrg/primaryMembersManage/deletePrimaryMembersByIds.action',
					type:"post",
					data:{
						"ids":selectedIds+""
					},
					success:function(data){
						if(data!=true && data!="true"){
							$(this).dialog("close");
						}else{
							$.messageBox({message:"成员信息删除成功"});	
							$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").trigger("reloadGrid");
						}
					}
				});
			}
		});
	}

	//合并重复服务成员数据
	$("#combinePrimaryMembers").click(function(){
		var selectedId = getSelectedIds();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一个成员维护对象！"});
	 		return;
		} 
		var rowData=  $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").getRowData(selectedId);
		$("#primaryMembersDialog").createDialog({
			width: 850,
			height: 600,
			title:'合并成员库成员信息',
			url:PATH+'/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=combine&id='+rowData.encryptId,
			buttons: {
				"合并" : function(){
					if(getTableNum("combinePrimaryMembersList") == 0){
						$.messageBox({level:'warn',message:"没有需要合并的数据!"});
	 					return;
					}
					var selectedId = $("#combinePrimaryMembersList").jqGrid("getGridParam", "selarrrow");
					if(null == selectedId ||""==selectedId){
						$.messageBox({level:'warn',message:"请选择需要合并的成员!"});
						return;
					}
					$("#isSubmit").val("true");
					$("#combineForm").submit();
		   		},
		   		"关闭" : function(){
		   			$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").trigger("reloadGrid");
		        	$(this).dialog("close");
				}
			}
		});
	});

	//查看服务成员基本信息
	function viewPrimaryMember(selectId){
		if(selectId==null || selectId==""){
			$.messageBox({level:'warn',message:"请选择一条需要查看的数据"});
	 		return;
		}
		var rowData=  $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").getRowData(selectId);
		$("#primaryMembersDialog").createDialog({
			title:"查看成员基本信息",
			width: dialogWidth,
			height: dialogHeight,
			url:PATH+'/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=view&selectedId='+rowData.encryptId,
			buttons: {
				"关闭" : function(){
		        $(this).dialog("close");
		  		 }
			}
		});
	}
		
	function operateFormatter(el,options,rowData){
		return "<pop:JugePermissionTag ename='updatePrimaryMembers'><a href='javascript:' onclick='updateOperator("+rowData.id+")'><span>修改</span></a></pop:JugePermissionTag><pop:JugePermissionTag ename='deletePrimaryMembers'> | <a href='javascript:;' onclick='deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
	}	
	//获得列表数据数量
	function getTableNum(tableid){
	  var num=$("#"+tableid+" tbody tr").size();
	  return num-1;
	}

	//获取选中列的ID
	function getSelectedIds(){
		var selectedIds = $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

	//操作
	function operateMemberFormatter(el,options,rowData){
		var havajobStr = "", _updateStr = "" ,isHaveJob = "";
		if(dfop.primaryMembers_isHaveJob=="0"){
			havajobStr="卸任";
			isHaveJob=1;
			_updateStr = "<pop:JugePermissionTag ename='updatePrimaryMembers'><a id='update'+dfop.primaryMembers_isHaveJob  href='javascript:void(0)' onclick='updateOperator("+rowData.id+")'><span>修改</span></a> </pop:JugePermissionTag>| ";
		}else{
			havajobStr="任职";
			isHaveJob=0;
		}
		return _updateStr+" <pop:JugePermissionTag ename='deletePrimaryMembers'><a id='delete'+dfop.primaryMembers_isHaveJob  href='javascript:void(0)'  onclick='deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>| <pop:JugePermissionTag ename='holdPrimaryMembers'> <a href='javascript:;' onclick='havajobMemberOperator(event,"+rowData.id+","+isHaveJob+",\""+havajobStr+"\")'><span>"+havajobStr+"</span></a> </pop:JugePermissionTag>";
	}

	function havajobMemberOperator(event,selectedIds,isHaveJob,havajobStr){
		if(selectedIds==null || selectedIds==""){
			$.messageBox({level:'warn',message:"请选择需要查看的数据"});
	 		return;
		}
		if(selectedIds.length>1){
			$.messageBox({level:'warn',message:"您只能选择一条数据进行查看"});
	 		return;
		} 
		var rowData=  $("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").getRowData(selectedIds);
		$.confirm({
			title:"确认"+havajobStr,
			message:"确定要"+havajobStr+"吗?",
			okFunc: function(){
				$.ajax({
					url:PATH+'/primaryOrg/primaryMembersManage/havajobPrimaryMemberByEncrypt.action?id='+rowData.encryptId+'&isHaveJob='+isHaveJob,
					success:function(data){
						if(data.id){
						    $.messageBox({message:"成功"+havajobStr+"该成员!"});
							$("#primaryMembers"+dfop.primaryMembers_isHaveJob+"List").trigger("reloadGrid");
						}else{
							$.messageBox({message:data,level: "error"});
						}
					}
				});
			}
		});
	}
};