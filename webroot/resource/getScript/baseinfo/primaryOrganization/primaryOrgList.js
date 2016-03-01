TQ.primaryOrgList = function (dfop){
	$(document).ready(function(){
		if(isGrid()){
			$("#fastSearchSelect").hide();
		}
		$("#primaryOrgList").jqGridFunction({
			datatype: "local",
			//设置grid单元格可编辑
			cellEdit: true,
			cellurl:PATH+"/baseinfo/primaryOrgManage/setSeq.action",
			cellsubmit: "clientArray",
			colModel:dfop.colModel,
			ondblClickRow:function (rowid){
				if(dfop.hasViewPermission=='true'){
					viewDialog(rowid);
				}
			},
			loadComplete:afterLoad,
			onSelectAll:function(aRowids,status){},
			multiselect:true,
			beforeSubmitCell:function(rowid, cellname, value, iRow, iCol){
				$(".edit-cell.ui-state-highlight").attr("class","");
				
				 $.ajax({
					url:PATH+"/baseinfo/primaryOrgManage/setSeq.action",
					data:{
						"seq":value,
						"id":rowid
					},
					success:function(data){
						$("#primaryOrgList").trigger("reloadGrid");
					}
				});     
			},
			afterEditCell:function(rowid, cellname, value, iRow, iCol){
				$(".edit-cell.ui-state-highlight").attr("class","");
			},
			onSelectRow:function(){setPrimaryOrgOperateButton();}
		});
		//设置grid单元格可编辑
		// $("#primaryOrgList").setGridParam("seq",{cellEdit:true});
		$("#primaryOrgList").jqGrid('setFrozenColumns');
		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
		
		function setPrimaryOrgOperateButton(){
			var selectedCounts = getActualjqGridMultiSelectCount("primaryOrgList");
			var count = $("#primaryOrgList").jqGrid("getGridParam","records");
			if(selectedCounts == count){
				jqGridMultiSelectState("primaryOrgList", true);
			}else{
				jqGridMultiSelectState("primaryOrgList", false);
			}
		}
		
		//高级搜索对话框
		$("#search").click(function(){
			//onOrgChanged(getCurrentOrgId(),isGrid());
			$("#primaryOrgDialog").createDialog({
				title:titleName+"查询-请输入查询条件",
				width: 600,
				height: 240,
				url:PATH+'/baseinfo/primaryOrgManage/dispatch.action?mode=search&dailogName=primaryOrgDialog&teamTypeName='+teamTypeName+'&organizationId='+getCurrentOrgId()+'&name='+dfop.name+"&isCommissionOrganization="+isCommissionOrganization,
				buttons: {
					"查询" : function(event){
						searchPrimaryOrg();
						$(this).dialog("close");
					},
					"关闭" : function(){
			        	$(this).dialog("close");
					}
				}
			});
		});
		
		$("#add").click(function(event){
			/* if(!isCityDownOrganization()){
				$.messageBox({
					message:"请选择县区级及以下",
					level: "warn"
				 });
				return;
			} */
			//if(!isGrid()){
			//	$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
			//	return;
			//}

			if(internalId==1 || internalId==2 ){
				if(!isCountryDownOrganization()){
					$.messageBox({level:"warn",message:"请先选择村社区及以下层级进行新增！"});
					return;
				}
			}else if(internalId==3 || internalId==4){
				if(!($("#currentOrgId").attr("orgLevelInternalId")<=dfop.organizationLevelCity)){
					$.messageBox({level:"warn",message:"请先选择市县及以下层级进行新增！"});
					return;
				}
			}
			if(internalId==3){
				var dialogHeight = 470;
			}else{
				var dialogHeight = 330;
			}
			$("#primaryOrgDialog").createDialog({
				title:"新增"+titleName,
				width: 650,
				height: dialogHeight, 
				url:PATH+'/baseinfo/primaryOrgManage/dispatch.action?mode=add&dailogName=primaryOrgDialog&primaryOrgVo.teamClazz.id='+teamClazzId+"&teamTypeName="+teamTypeName+"&isCommissionOrganization="+isCommissionOrganization,
				buttons: dfop.hasAddPrimaryOrgMember
			});
		});
		
		$("#update").click(function(event){
			var selectedId = getSelectedIds();
			if(selectedId==null || selectedId==""){
				$.messageBox({level:'warn',message:"请选择一条数据再进行修改！"});
		 		return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一条数据进行修改！"});
		 		return;
			}
			updateOperator(event,selectedId);
		});
		
		$("#delete").click(function(event){
			var selectedIds = getSelectedIds();
			if(selectedIds==null || selectedIds==""){
				$.messageBox({level:'warn',message:"请选择一条或多条数据进行删除！"});
		 		return;
			}
			if(name=='Masseduty'&&!validatorIsSynchronized()){
				$.messageBox({level:'warn',message:"存在社会志愿者同步数据，不能进行批量删除！"});
		 		return;
			}
			deleteOperator(event,selectedIds);
		});
		
		$("#view").click(function(){
			var selectedId = getSelectedIds();
			if(selectedId==null || selectedId==""){
				$.messageBox({level:'warn',message:"请选择一条数据再进行查看！"});
		 		return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一条数据进行查看！"});
		 		return;
			}
			viewDialog(selectedId);
		});
		
		$("#export").click(function(event){
			if($("#primaryOrgList").getGridParam("records")>0){
				var postData = $("#primaryOrgList").getPostData();
				$("#primaryOrgList").setPostData($.extend(postData,{
										"primaryOrgVo.org.id":getCurrentOrgId(),
										"primaryOrgVo.displayLevel":$("#displayLevel").val(),
										"primaryOrgVo.teamClazz.id":teamClazzId,
										"primaryOrgVo.teamClazz.internalId":internalId,
										"primaryOrgVo.teamTypeDomainName":teamTypeDomainName,
										"primaryOrgVo.teamClazz.displayName":titleName,
										"primaryOrgVo.isCommissionOrganization":isCommissionOrganization
										}));
				$("#primaryOrgDialog").createDialog({
					width: 250,
					height: 200,
					title:"导出"+titleName,
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'primaryOrgList',
						downloadUrl:PATH+'/baseinfo/primaryOrgManage/downloadPrimaryOrg.action'
					},
					buttons: {
			   			"导出" : function(event){
			   				exceldownloadSubmitForm();
			        		$(this).dialog("close");
		   				},
			   			"关闭" : function(){
			        		$(this).dialog("close");
			   			}
					},
					shouldEmptyHtml:false
				});
			}else{
				$.messageBox({level:'warn',message:"没有可导出的数据！"});
				return;
			}
		});
		
		$("#maintainPrimaryOrgMembers").click(function(event){
			var selectedId = getSelectedIds();
			if(selectedId==null || selectedId==""){
				$.messageBox({level:'warn',message:"请选择一条组织机构维护成员！"});
		 		return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能对一条组织机构维护成员信息！"});
		 		return;
			}
			if(name=='Masseduty'&&!validatorIsSynchronized()){
				$.messageBox({level:'warn',message:"该条数据是社会志愿者同步数据，不能进行维护成员操作！"});
		 		return;
			}
			//获取组织结构id
			var ent =  $("#primaryOrgList").getRowData(selectedId);
			var orgid=	ent["org.id"];
			var detailName =ent.detailName;
			$("#primaryOrgDialog").createDialog({
				width: 850,
				height: 630,
				title:'维护成员信息',
				url:'/primaryOrg/primaryMembersManage/dispatchByEncrypt.action?mode=maintainPrimaryOrgMembers&primaryMemberVo.primaryOrgId='+ent.encryptId+"&name="+name+"&primaryMemberVo.org.id="+orgid+'&primaryMemberVo.isFourLevelPlatform=0&primaryOrgDetailName='+encodeURIComponent(detailName),
				buttons: {
					"关闭" : function(){
			        	$(this).dialog("close");
			        	$("#primaryOrgList").trigger("reloadGrid");
			   		}
				}
			});
		});
		
		/**同步群防群治*/
		$("#synchronizePrimaryOrgMembersToMasseduty").click(function (){
			if(name!='Postulantduty'){
				return;
			}
			var selectedIds = getSelectedIds();
			if(selectedIds==null || selectedIds==""){
				$.messageBox({level:'warn',message:"请选择一条或多条数据进行同步！"});
		 		return;
			}
			if(!validatorIsSynchronized()){
				$.messageBox({level:'warn',message:"所选记录存在已经同步的数据！"});
		 		return;
			}
			synchronizePrimaryOrgMembersToMasseduty(selectedIds);
		});
		
		$("#reload").click(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		
		function afterLoad(){
			isEmphasisFormatter();
			changeColor();
		}
		
		function changeColor(){
			if(notRun==null||notRun.length==0){
				return;
			}
			for(var i=0;i<notRun.length;i++){
				var rowData=$("#primaryOrgList").getRowData(notRun[i]);
				$("#"+notRun[i]).css('background','red');
				$("#primaryOrgList").setSelection(notRun[i]);
			}
			notRun.splice(0,notRun.length);
		}

		function isEmphasisFormatter(){
			var idCollection = new Array();
			idCollection=$("#primaryOrgList").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#primaryOrgList").getRowData(idCollection[i]);
				if(ent.logOut==1){
					$("#"+idCollection[i]+"").css('color','#778899');
				}
			}
		}
		
		function getSelectedIds(){
			var selectedIds = $("#primaryOrgList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}
		
		function getSelectedIdLast(){
			var selectedId="";
			var selectedIds = $("#primaryOrgList").jqGrid("getGridParam", "selarrrow");
			for(var i=0;i<selectedIds.length;i++){
				selectedId = selectedIds[i];
			}
			return selectedId;
		}

		//切换本级及下辖功能
		$("#displayLevel").change(function(event){
			$("#primaryOrgList").setGridParam({
				url:PATH+'/baseinfo/primaryOrgManage/findPrimaryOrgs.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#primaryOrgList").setPostData({
				"primaryOrgVo.org.id":getCurrentOrgId(),
				"primaryOrgVo.displayLevel":$("#displayLevel").val(),
				"primaryOrgVo.teamClazz.id":teamClazzId,
				"primaryOrgVo.teamTypeDomainName":teamTypeDomainName,
				"internalId":internalId,
				"primaryOrgVo.isCommissionOrganization":isCommissionOrganization
				
			});
			$("#primaryOrgList").trigger("reloadGrid");
		});
	});
	
	//同步群防群治
	function synchronizePrimaryOrgMembersToMasseduty(selectedIds){
		/**if(!isCityDownOrganization()){
			$.messageBox({
				message:"请选择县区级及以下",
				level: "warn"
			 });
			return;
		}*/
		var selectedRowIds=deleteOperatorByEncrypt('primaryOrg',selectedIds,'encryptId');
		$.confirm({
			title:"确认同步",
			message:"确定要同步吗?",
			okFunc: function(){
				$.ajax({
					url:PATH+'/baseinfo/primaryOrgManage/synchronizePrimaryOrgMembersToMasseduty.action',
					type:'post',
					data:{
					'mode':'delete',
					'selectedIds':selectedRowIds
					},
					success:function(data){
						if(data>0){
						    $.messageBox({message:"成功同步组织团队!"});
							$("#primaryOrgList").trigger("reloadGrid");
						}
					}
				});
			}
		});
	}
	
	function validatorIsSynchronized(){
		var selectedIds = $("#primaryOrgList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			var rowDataById=$("#primaryOrgList").jqGrid( "getRowData",selectedIds[i]);
			if(rowDataById.isSynchronize >0){
				return false;
			}
		}
		return true;
	}
	
	function isCityDownOrganization(){
		return $("#currentOrgId").attr("orgLevelInternalId") < dfop.organizationLevelCity;
	}
	
	function viewDialog(id){
		var primaryOrg =  $("#primaryOrgList").getRowData(id);
		var encryptId = primaryOrg.encryptId;
		$("#primaryOrgDialog").createDialog({
			width:830,
			height:630,
			title:"查看组织信息",
			url:PATH+'/baseinfo/primaryOrgManage/dispatchByEncrypt.action?mode=view&dailogName=primaryOrgDialog&primaryOrg.id='+encryptId+"&teamTypeName="+teamTypeName+'&primaryMemberVo.primaryOrgId='+primaryOrg.id+'&primaryOrg.org.id='+primaryOrg["org.id"],
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	
	//高级搜索
	function searchPrimaryOrg(){
		$("#primaryOrgList").setGridParam({
			url:PATH+"/baseinfo/primaryOrgManage/findPrimaryOrgs.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchPrimaryOrgForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
	 		if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
		}
		$("#primaryOrgList").setPostData(
				$.extend(dataJson,{
					"primaryOrgVo.org.id":getCurrentOrgId(),
					"primaryOrgVo.displayLevel":$("#displayLevel").val(),
					"primaryOrgVo.teamClazz.id":teamClazzId,
					"primaryOrgVo.teamTypeDomainName":teamTypeDomainName,
					"internalId":internalId,
					"primaryOrgVo.isCommissionOrganization":isCommissionOrganization
			}));
	
		if(isgridBol){
			$("#primaryOrgList").setPostData(
				$.extend(dataJson,{
					"primaryOrgVo.org.id":getCurrentOrgId(),
					//如果是网格层级就只查询本层级的数据
					"primaryOrgVo.displayLevel":"sameGrade",
					"primaryOrgVo.teamClazz.id":teamClazzId,
					"primaryOrgVo.teamTypeDomainName":teamTypeDomainName,
					"internalId":internalId,
					"primaryOrgVo.isCommissionOrganization":isCommissionOrganization
			}));
		}
		
		 $("#primaryOrgList").trigger("reloadGrid");
	}
	

	function onOrgChanged(orgId,isgrid){
		currentOrgId=orgId;
		isgridBol = isgrid;
		$("#primaryOrgList").setGridParam({
			url:PATH+'/baseinfo/primaryOrgManage/findPrimaryOrgs.action',
			datatype: "json",
			page:1
		});

		$("#primaryOrgList").setPostData({
			"primaryOrgVo.org.id":orgId,
			"primaryOrgVo.displayLevel":$("#displayLevel").val(),
			"primaryOrgVo.teamClazz.id":teamClazzId,
			"primaryOrgVo.teamTypeDomainName":teamTypeDomainName,
			"internalId":internalId,
			"primaryOrgVo.isCommissionOrganization":isCommissionOrganization
		});
		$("#primaryOrgList").trigger("reloadGrid");
	}
}