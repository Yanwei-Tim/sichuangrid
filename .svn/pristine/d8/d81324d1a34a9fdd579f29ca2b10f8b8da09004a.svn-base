TQ.serviceTeamList = function (){
	$(document).ready(function(){
		//是否进入了网格
		if(isGrid()){
			$("#displayLevel").hide();
		}
		
		//获取选中列的ID
		function getSelectedIds(){
			var selectedIds = $("#serviceTeamList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}
			
		//高级搜索
		function searchServiceTeam(){
			$("#serviceTeamList").setGridParam({
				url:PATH+"/plugin/serviceTeam/serviceTeamManage/findServiceTeams.action",
				datatype: "json",
				page:1,
				mtype:"post"
			});
			var data=$("#searchServiceTeamForm").serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
		 		if (dataJson[data[i].name]) {
					dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
				} else {
					dataJson[data[i].name] = data[i].value;
				}
			}
			$("#serviceTeamList").setPostData(
				$.extend(dataJson,{
					"serviceTeamVo.org.id":getCurrentOrgId(),
					"serviceTeamVo.displayLevel":$("#displayLevel").val(),
					"serviceTeamVo.logOut":$("#conditionLogOutState").val()
			}));
			 $("#serviceTeamList").trigger("reloadGrid");
		}

		//列表过滤功能(层级和类型)
		function changeList(){
			$("#serviceTeamList").setGridParam({
				url:PATH+'/plugin/serviceTeam/serviceTeamManage/findServiceTeams.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#serviceTeamList").setPostData({
				"serviceTeamVo.displayLevel":$("#displayLevel").val(),
				"serviceTeamVo.teamType.id":$("#teamTypeId").val(),
				"serviceTeamVo.org.id":getCurrentOrgId(),
				"serviceTeamVo.logOut":0
			});
		    $("#serviceTeamList").trigger("reloadGrid");
		}

		//加载完成后执行
		function afterLoad(){
			isEmphasisFormatter();
			changeColor();
		}
			
		//改变颜色
		function changeColor(){
			if(notRun==null||notRun.length==0){
				return;
			}
			for(var i=0;i<notRun.length;i++){
				var rowData=$("#serviceTeamList").getRowData(notRun[i]);
				$("#"+notRun[i]).css('background','red');
				$("#serviceTeamList").setSelection(notRun[i]);
			}
			notRun.splice(0,notRun.length);
		}

		//已解散团队样式更改
		function isEmphasisFormatter(){
			var idCollection = new Array();
			idCollection=$("#serviceTeamList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#serviceTeamList").getRowData(idCollection[i]);
				if(ent.logOut==1){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
			}
		}
		
		//如果是县级以上用户不显示对象和记录数量
		var colModel=[
			        {name:"id",index:"id",sortable:false,hidden:true},
			        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			        {name:"organization.id",index:"organization.id",sortable:false,hidden:true},
			        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter_ServiceTeam,width:80,align:"center"},
			        {name:'name',index:"name",label:'团队名称',sortable:true,width:200,formatter:nameFont_ServiceTeam},
			        {name:'teamType',index:'teamType',label:'团队类别',sortable:true,formatter:teamTypeFormatter,width:120},
			        {name:'teamMembers',index:'teamMembers',label:'成员数',sortable:true,width:70,align:"center",formatter:viewMemberFormatter},
			        {name:'buildDate',label:'成立时间',sortable:false,width:80,align:"center"},
			        {name:'logOut',hidden:true,sortable:false,index:"logOut"},
			        {name:'org.orgName',index:'org.orgName',label:'所属区域(网格)',sortable:false,width:350,align:"center"}
				];
		if(isDistrictDownOrganization()){
			colModel.insertAt({name:'serviceObject',label:'服务对象',sortable:false,formatter:viewObjectFormatter,width:150,align:"center"},6);
	        colModel.insertAt({name:'teamRecords',label:'服务记录',sortable:false,width:80,align:"center",formatter:serviceRecordsCountFormatter},7);
		}
		
			$("#serviceTeamList").jqGridFunction({
				datatype: "local",
			   	colModel:colModel,
				multiselect:true,
				showColModelButton:false,
				ondblClickRow:viewDialog,
				loadComplete: function(data){if(afterLoad){afterLoad();}}
			});
			
		//团队列表
		$("#serviceTeamList").jqGrid('setFrozenColumns');
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			getTeamList(getCurrentOrgId());
		}
		
	  	//新增按钮事件
		$("#add").click(function(event){
			$("#serviceTeamDialog").createDialog({
				title:"新增服务团队",
				width: 600,
				height: 320,
				url:PATH+'/plugin/serviceTeam/serviceTeamManage/dispatch.action?mode=add&dailogName=serviceTeamDialog',
				buttons: {
					"保存并关闭" : function(event){
			   			$("#serviceTeamForm").submit();
			   			$("#isSubmit").val("true");
					},
					"保存并继续" : function(event){
			   			$("#serviceTeamForm").submit();
			   			$("#isSubmit").val("false");
					}
				}
			});
		});
		
		//绑定列表切换事件--团队类型
		$("#teamTypeId").change(changeList);
		
		//绑定列表切换事件--层级
		$("#displayLevel").change(changeList);
		
		//高级搜索对话框
		$("#search").click(function(event){
			$("#serviceTeamDialog").createDialog({
				title:"服务团队查询-请输入查询条件",
				width: 600,
				height: 240,
				url:PATH+'/plugin/serviceTeam/serviceTeamManage/dispatch.action?mode=search&dailogName=serviceTeamDialog',
				buttons: {
					"查询" : function(event){
						searchServiceTeam();
						$(this).dialog("close");
					},
					"关闭" : function(){
			        	$(this).dialog("close");
					}
				}

			});
		});
		
		//维护团队成员
		$("#maintainServiceTeamMembers").click(function(event){
			var selectedId = getSelectedIds();
			if(selectedId==null || selectedId==""){
				$.messageBox({level:'warn',message:"请选择一条未解散团队维护成员信息！"});
		 		return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能对一条未解散团队维护成员信息！"});
		 		return;
			}
			var serviceTeam =  $("#serviceTeamList").getRowData(selectedId);
			if(serviceTeam.logOut==1 || serviceTeam.logOut=='1'){
				$.messageBox({level:'warn',message:"已解散团队不可再维护成员信息！"});
				return;
			}
			$("#serviceTeamDialog").createDialog({
				width: 850,
				height: 600,
				title:'维护成员信息',
				url:PATH+'/plugin/serviceTeam/serviceTeamManage/dispatchByEncrypt.action?mode=maintainMembers&dailogName=serviceTeamDialog&serviceTeam.id='+serviceTeam.encryptId,
				buttons: {
					"关闭" : function(){
			        	$(this).dialog("close");
			        	$("#serviceTeamList").trigger("reloadGrid");
			   		}
				},
				close:function(){
		        	$("#serviceTeamList").trigger("reloadGrid");
		   		}
			});
		});
		
		//解散团队
		$("#logOut").click(function(event){
			var selectedId = getSelectedIds();
			if(selectedId==null || selectedId==""){
				$.messageBox({level:'warn',message:"请选择一条数据进行解散操作！"});
		 		return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能对一条数据进行解散操作！"});
		 		return;
			}
			var rowData = $("#serviceTeamList").getRowData(selectedId);
			if(rowData.logOut==1 || rowData.logOut=="1"){
				$.messageBox({level:'warn',message:"此团队已解散！"});
				return;
			}
			
			$("#serviceTeamDialog").createDialog({
				width:450,
				height:210,
				title:'解散团队提示',
				modal:true,
				url:PATH+'/plugin/serviceTeam/serviceTeamManage/dispatchByEncrypt.action?mode=logOut&dailogName=serviceTeamDialog&serviceTeam.id='+rowData.encryptId,
				buttons: {
				   "保存" : function(event){
					   $("#logoutForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});
		
		//下载服务团队
		$("#down").click(function(event){
			if($("#serviceTeamList").getGridParam("records")>0){
				var postData = $("#serviceTeamList").getPostData();
				//$("#serviceTeamList").setPostData($.extend(postData,{}));
				$("#serviceTeamDialog").createDialog({
					width: 250,
					height: 200,
					title:"导出团队信息",
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'serviceTeamList',
						downloadUrl:PATH+'/plugin/serviceTeam/serviceTeamManage/downServiceTeam.action'
					},
					buttons: {
			   			"导出" : function(event){
							$("#exceldownload").submit();
			        		$(this).dialog("close");
		   				},
			   			"关闭" : function(){
			        		$(this).dialog("close");
			   			}
					},
					shouldEmptyHtml:false
				});
			}else{
				$.messageBox({level : 'warn',message:"列表中没有数据，不能导出！"});
				return;
			}
		});
		
	});
	

	//刷新
	$("#reload").click(function(){
			getTeamList(getCurrentOrgId());
	});
		
	//查看团队所属成员数
	function viewMemberFormatter(el, options, rowData){
		if(rowData.teamMembers==0 || rowData.teamMembers=="0"){
			return rowData.teamMembers;
		}else{
			return "<a href='javascript:void(0)' onclick='viewMemberList("+rowData.id+")'>"+rowData.teamMembers+"</a>";
		}
	}

	//显示团队所属记录数
	function serviceRecordsCountFormatter(el, options, rowData){
		var records=0;
		$.ajax({
			async: false,
			url:PATH+'/plugin/serviceTeam/serviceTeamManage/serviceRecordsCount.action?serviceTeam.id='+rowData.id,
			success:function(data){
				records=data;
			}
		});
		if(null==records){
			records = 0;
		}
		return records;
	}

	//查看团队所属对象数
	function viewObjectFormatter(el, options, rowData){
		var objects=0;
		$.ajax({
			async: false,
			url:PATH+'/plugin/serviceTeam/serviceTeamManage/serviceObjectsCount.action?serviceTeamVo.id='+rowData.id,
			success:function(data){
				objects=data;
			}
		});
		if(null==objects[0]){
			objects[0]=0;
		}
		if(null==objects[4]){
			objects[4]=0;
		}
		return "人口："+objects[0]+"，其他："+objects[4];
	}
	
	//获取团队列表数据
	function getTeamList(orgId){
		$("#serviceTeamList").setGridParam({
			url:PATH+'/plugin/serviceTeam/serviceTeamManage/findServiceTeams.action',
			datatype: "json",
			page:1
		});
		$("#serviceTeamList").setPostData({
			"serviceTeamVo.org.id":orgId,
			"serviceTeamVo.displayLevel":$("#displayLevel").val(),
			"serviceTeamVo.logOut":0,
			"serviceTeamVo.teamType.id":$("#teamTypeId").val()
		});
		$("#serviceTeamList").trigger("reloadGrid");
	}


}