TQ.partyOrgReportList = function (dfop){
	function isClaimFormatter(el,options,rowData){
		if(rowData.claimServiceProjectName=="" || rowData.claimServiceProjectName==null){
			return rowData.isClaimProject = "否";
		}else{
			return rowData.isClaimProject = "是";
		}
	}

	function nameFormatter(el,options,rowData){
		if(null == rowData.name) {
			return "&nbsp;&nbsp;"
		}else if(rowData.isEmphasis==1){
			return '<font color="#778899">'+rowData.name+'</font>';
		}
		return '<font color="#000000">'+rowData.name+'</font>';
	}

	$(function(){
		initButtonsEnable();
		function initButtonsEnable(){
			if(isUserVillageUp() &&  isVillageUp() || $("#currentOrgId").val()!=USER_ORG_ID){
				$("#add,#update,#delete,#setEmphasise").hide();
			}
		}
		function toggleButtonState(){
		  	var selectedIds=$("#partyorgReportList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		// 生成列表
		$("#partyorgReportList").jqGridFunction({
			mtype:'post',
			datatype: "local",
			height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$("#globalOrgBtnMod").outerHeight()-$(".ui-tabs-nav").outerHeight()-100,
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	//{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:65,frozen:true,sortable:false,align:'center' },
		    	{name:'name', index:'name',label:'单位名称', width:100, sortable:true, align:'center', hidden:false,formatter:nameFormatter},
		    	{name:'organization.orgName',index:'orgId',label:'所属区域', width:360,hidden:false,sortable:true},
		    	{name:'address', index:'address',label:'地址', width:100, sortable:true, align:'center', hidden:false},
		    	{name:'partyOrgType', index:'partyOrgType',label:'党组织类别', formatter:partyOrgTypeFormatter, width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'telephone', index:'telephone',label:'联系电话', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'contractor', index:'contractor',label:'联系人', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'isClaimProject', index:'isClaimProject',label:'是否认领项目', formatter:isClaimFormatter, width:100, sortable:false, align:'center', hidden:false},
		    	{name:'remark', index:'remark',label:'备注', width:100, sortable:true, align:'center', hidden:false},
		    	{name:"isEmphasis",label:"是否注销",hidden:true,hidedlg:true,width:100},
		    	{name:'createUser', index:'createUser',label:'创建人', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'updateUser', index:'updateUser',label:'修改人', width:100, sortable:true, align:'center', hidden:false},
		    	{name:'createDate', index:'createDate',label:'创建时间', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'updateDate', index:'updateDate',label:'修改时间', width:100, sortable:true, align:'center', hidden:false}
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewPartyOrgReport=='true'){
					viewPartyOrgReport(rowid);
				}
			},
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#partyorgReportList").jqGrid('setFrozenColumns');
		//$("#partyorgReportList").closest(".ui-jqgrid-bdiv").css({ "height" : "360" });
		
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入党组织报到名称");
		});
		$("#add").click(function(){
			if (getCurrentOrgId()==null||getCurrentOrgId()==""){
				$.notice({level:"warn", message:"请先选择一个部门"});
			}else{
				$("#partyorgReportDialog").createDialog({
					model :"add",
					title:"新增党组织报到信息",
					width: dialogWidth,
					height: dialogHeight,
					url:dfop.path+'/partyorgReportManage/dispatchOperate.action?mode=add&organization.id='+getCurrentOrgId()+'&type='+type,
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
			var selectedIds = $("#partyorgReportList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改!"});
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行修改!"});
				 return;
			}
			updateOperator(selectedId);
		});
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			deleteOperator(allValue);
		});
		$("#view").click(function(){
			var selectedIds = $("#partyorgReportList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
				return;
			}
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
		 		return;
			}
			viewPartyOrgReport(selectedId);
		});
		$("#reload").click(function(){
			if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
				onOrgChanged(getCurrentOrgId(),isGrid());
			}
		});

		if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
			onOrgChanged(getCurrentOrgId(),isGrid());
		}
		
		$("#isEmphasise").change(function(){
			onOrgChanged(getCurrentOrgId(),isGrid());
		});
		
		$("#search").click(function(event){
			$("#partyorgReportDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:'党组织报到查询-请输入查询条件',
	 	 		url:dfop.path+'/partyBuilding/doubleRegActivities/partyorgReportManage/partyOrgReportSearchDlg.jsp?orgId='+getCurrentOrgId()+'&type='+type,
				buttons: {
			   		"查询" : function(event){
						searchPartyorgReport();
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		$("#cancelEmphasise").click(function(event){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var selectedId =getSelectedIds();
			var cancelEmphasise=new Array();
			var temp=new Array();
			if(selectedId == null || selectedId.length<=0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行取消关注！"});
				return;
			}
			for(var i=0;i<selectedId.length;i++){
				var rowData = $("#partyorgReportList").getRowData(selectedId[i]);
				if(rowData.isEmphasis==false || rowData.isEmphasis=="false"){
					cancelEmphasise.push(selectedId[i]);
				}else{
					temp.push(selectedId[i]);
				}
			}
			selectedId=cancelEmphasise;
			if(selectedId==null||selectedId.length==0){
				$.messageBox({level:'warn',message:"没有可取消关注的数据！"});
				return;
			}
			var selectedIds="";
			for(var i=0;i<selectedId.length;i++){
				selectedIds+=selectedId[i]+",";
			}
			if(selectedIds.length=2){
				selectedIds=selectedId;
			}
			var encryptIds=deleteOperatorByEncrypt("partyorgReport",selectedIds,"encryptId");
			$("#partyorgReportDialog").createDialog({
				width:450,
				height:210,
				title:'取消关注提示',
				modal:true,
				url:dfop.path+'/partyBuilding/doubleRegActivities/partyorgReportManage/emphasiseConditionDlg.jsp?locationIds='+encryptIds+'&isEmphasis=true&dailogName=partyOrgReport&temp='+temp,
				buttons: {
				   "保存" : function(event){
					   $("#emphasisForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});
		
		$("#reEmphasise").click(function(){
			if($(this).attr("disabled")=="disabled"){
				return;
			}
			var selectedId = getSelectedIds();
			var reEmphasise=new Array();
			var temp=new Array();
			if(selectedId == null || selectedId.length<=0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再重新关注！"});
				return;
			}
			for(var i=0;i<selectedId.length;i++){
				var rowData = $("#partyorgReportList").getRowData(selectedId[i]);
				if(rowData.isEmphasis==true||rowData.isEmphasis=="true"){
					reEmphasise.push(selectedId[i]);
				}else{
					temp.push(selectedId[i]);
				}
			}
			selectedId=reEmphasise;
			if(selectedId==null||selectedId.length==0){
				$.messageBox({level:'warn',message:"没有可重新关注的数据！"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("partyorgReport",selectedId,"encryptId");
			$.ajax({
				url:dfop.path+"/partyorgReportManage/updateEmphasiseByIdByEncrypt.action",
				type:"post",
				data:{
					"ids": encryptIds,
					"partyOrgReport.isEmphasis":0
				},
				success:function(responseData){
					if(null==temp || temp.length==0){
						$.messageBox({message:"重新关注成功 ！ "});
					}else{
						$.messageBox({level:'warn',message:"除选中的红色数据外,其余重新关注成功 ！ "});
					}
					notExecute=temp;
					$("#partyorgReportList").trigger("reloadGrid");
				}
			});
		});
		
	});

	function viewPartyOrgReport(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#partyorgReportList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#partyorgReportDialog").createDialog({
			width:600,
			height:300,
			title:"查看党组织报到信息",
	 		url:dfop.path+'/partyorgReportManage/dispatchOperateByEncrypt.action?mode=view&partyOrgReport.id='+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateOperator(selectedId){
		var ent =  $("#partyorgReportList").getRowData(selectedId);
		if(ent.isEmphasis==true || ent.isEmphasis=="true"){
			 $.messageBox({level : 'warn',message:"该党组织报到信息已经注销，不能修改!"});
			 return;
		}
		$("#partyorgReportDialog").createDialog({
			model :"edit",
			title:"修改党组织报到信息",
			width: dialogWidth,
			height: dialogHeight,
			url:dfop.path+'/partyorgReportManage/dispatchOperateByEncrypt.action?mode=edit&partyOrgReport.id='+ent.encryptId,
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
	function deleteOperator(allValue){
		if(allValue==null){
			return ;
		}
		var encryptIds=deleteOperatorByEncrypt("partyorgReport",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:dfop.path+'/partyorgReportManage/deletePartyorgReportByIdsByEncrypt.action',
	 				type:'post',
	 				data:{
	 					'ids':encryptIds,
	 					'type':type
	 					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该党组织报到信息!"});
						$("#partyorgReportList").trigger("reloadGrid");
					}
				});
			}
		});
	}
	function parseObj(strData) {
		return (new Function("return " + strData))();
	}
	function searchPartyorgReport(){
		var formdata = $("#searchPartyOrgReportForm").serialize();
		if (formdata != '') {
			formdata = formdata.replace(/\+/g," "); 
			formdata = formdata.replace(/=/g, '":"');
			formdata = formdata.replace(/&/g, '","');
			formdata += '"';
		}
		formdata = decodeURIComponent('{"' + formdata + '}');
		$("#partyorgReportList").setGridParam({
			url:dfop.path+'/partyorgReportManage/findPartyorgReportPagerBySearchVo.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#partyorgReportList").setPostData(parseObj(formdata));
		$("#partyorgReportList").trigger("reloadGrid");
	}
	function search(orgId){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入党组织报到名称'  || fastSearchVal==''){
			onOrgChanged(getCurrentOrgId(),isGrid());
			return;
		}
			
		var	postData = {
			 "organization.id":orgId,
			 "searchPartyOrgReportVo.name":fastSearchVal,
			 "searchPartyOrgReportVo.type":type
		}
		$("#partyorgReportList").setGridParam({
	 		url:dfop.path+'/partyorgReportManage/findPartyorgReportPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#partyorgReportList").setPostData(postData);
		$("#partyorgReportList").trigger("reloadGrid");
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#partyorgReportList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#partyorgReportList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
}