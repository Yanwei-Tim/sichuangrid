TQ.issueSkipruleList = function (dfop){
	var userOrgId = dfop.userOrgIdValue;
	// Formatter
	function operatorFormatter(el,options,rowData){
		return (dfop.hasUpdateIssueSkiprule=='true' ? "<a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a>" +"&nbsp;|&nbsp;" : '' ) +
				(dfop.hasDeleteIssueSkiprule=='true' ? "<a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a>" :'')
	}
	function messageFlagFormatter(el,options,rowData){
		if(el=="0")
			return "否";
		else
			return "是";
	}

	function statusFlagFormatter(el,options,rowData){
		if(el=="0")
			return "否";
		else
			return "是";
	}

	$(document).ready(function(){
		function toggleButtonState(){
		  	var selectedIds=$("#issueSkipruleList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		function afterLoad(){

		}
		
		// 生成列表
		$("#issueSkipruleList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:'id',frozen:true,hidden:true},
		    	//{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:85,frozen:true,sortable:false,align:'center' },
		    	{name:'issueTypeDomainName', index:'issue_Type_Domain_Id',label:'事件大类', width:240, sortable:true, align:'center', hidden:false}, 	
		    	{name:'issueTypeName', index:'issue_Type_Id',label:'事件子类', width:240, sortable:true, align:'center', hidden:false}, 	
		    	{name:'issueUrgentLevel.id', index:'issue_Urgent_Level',label:'紧急等级',formatter:issueUrgentLevelFormatter, width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'submitLevel.id', index:'submit_level',label:'上报层级', formatter:submitLevelFormatter, width:240, sortable:true, align:'center', hidden:false}, 	
		    	{name:'messageFlag', index:'MESSAGE_FLAG',label:'短信提醒',formatter:messageFlagFormatter, width:100, sortable:true, align:'center', hidden:false},
		    	{name:'status', index:'status',label:'是否启用',formatter:statusFlagFormatter, width:100, sortable:true, align:'center', hidden:false}
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){
		  		toggleButtonState(data);
		  	},
	    	loadComplete: function(data){
	    		afterLoad(data);
	    	},
	    	ondblClickRow:function (rowid){
				if(dfop.viewIssueSkiprule=='true'){
					viewIssueSkiprule(rowid);
				}
			},
		    
			onSelectRow: function(data){
				toggleButtonState(data);
			}
		});
		jQuery("#issueSkipruleList").jqGrid('setFrozenColumns');
		
		
		loadIssueSkiprule();
		
		$("#add").click(function(){
			$("#issueSkipruleDialog").createDialog({
				title:"新增越级规则设置信息",
				width: dialogWidth,
				height: dialogHeight,
				url:PATH+'/issueSkipruleManage/dispatchOperate.action?mode=add&dailogName=issueSkipruleDialog',
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
			var selectedIds = $("#issueSkipruleList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null || selectedIds.length>1){
				$.messageBox({message:"只能选择一条条记录进行修改！",level:"warn"});
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				 return;
			}
			var rowData = $("#issueSkipruleList").getRowData(selectedId);
			if(rowData.status == '是'){
				$.messageBox({message:"该越级规则已启用,请停用后再修改!",level:"warn"});
				return ;
			}		
			updateOperator(selectedId);
		});
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			if(checkStatusOn(allValue)){
				$.messageBox({level:'warn',message:"选中的记录中存在已启用的规则，请停用后再进行删除！"});
				 return;
			}
			deleteOperator(allValue);
		});
		$("#start").click(function(){
			var selectedIds = $("#issueSkipruleList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null||selectedIds.length==0){
				$.messageBox({level:'warn',message:"请选择一条记录！"});
				 return;
			}
			if(checkStatusOn(selectedIds)){
				$.messageBox({level:'warn',message:"选中的记录中存在已启用的规则,请不要重复操作"});
				 return;
			}
			selectedIds=selectedIds+"";
			$.ajax({
					url:PATH+'/issueSkipruleManage/startIssueSkipruleByIds.action',
					type:"post",
				data:{
					"ids":selectedIds
				},
				success:function(data){
					if(data==true||data=="true"){
					    $.messageBox({message:"已经成功启用!"});
						$("#issueSkipruleList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:data,
							level:"error"
				        });
					}
				}
			});
		});	
		$("#stop").click(function(){
			var selectedIds = $("#issueSkipruleList").jqGrid("getGridParam", "selarrrow");
			if(selectedIds==null||selectedIds.length==0){
				$.messageBox({level:'warn',message:"请选择一条记录！"});
				 return;
			}
			if(checkStatusOff(selectedIds)){
				$.messageBox({level:'warn',message:"选中的记录中存在已停用的规则，请不要重复操作"});
				 return;
			}
			selectedIds=selectedIds+"";
			$.ajax({
					url:PATH+'/issueSkipruleManage/stopIssueSkipruleByIds.action',
					type:"post",
				data:{
					"ids":selectedIds
				},
				success:function(data){
					if(data==true||data=="true"){
					    $.messageBox({message:"已经成功停用!"});
						$("#issueSkipruleList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:data,
							level:"error"
				        });
					}
				}
			});
		});	
		
		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			viewIssueSkiprule(selectedId);
		});
		$("#reload").click(function(){
			loadIssueSkiprule();
		});

		$("#search").click(function(event){
			$("#issueSkipruleDialog").createDialog({
				width:520,
				height:220,
				title:'越级规则设置查询-请输入查询条件',
	 	 		url:PATH+'/issueSkipruleManage/dispatchOperate.action?mode=search',
				buttons: {
			   		"查询" : function(event){
						searchIssueSkiprule($("#searchIssueSkiprule_form").serializeArray());
			        	$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	});

	function viewIssueSkiprule(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#issueSkipruleList").getRowData(selectedId);
		var id = rowData.encryptId;
		if(id==null){
			 return;
		}
		$("#issueSkipruleDialog").createFrameDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看越级规则设置信息",
	 		url:PATH+'/issueSkipruleManage/dispatchOperate.action?mode=view&id='+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function updateOperator(selectedId){
		var ent =  $("#issueSkipruleList").getRowData(selectedId);
		$("#issueSkipruleDialog").createDialog({
			title:"修改越级规则设置信息",
			width: dialogWidth,
			height: dialogHeight,
			url:PATH+'/issueSkipruleManage/dispatchOperate.action?mode=edit&id='+ent.encryptId+'&dailogName=issueSkipruleDialog',
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}
	function deleteOperator(allValue){
		var selectedIds=deleteOperatorByEncrypt("issueSkiprule",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
	 				url:PATH+'/issueSkipruleManage/deleteIssueSkipruleByIds.action',
	 				type:"post",
					data:{
						"ids":selectedIds
					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该越级规则设置!"});
						$("#issueSkipruleList").trigger("reloadGrid");
					}
				});
			}
		});
	}
	function searchIssueSkiprule(postData){
		var dataJson={};
		var arrIndex = 0;
		for(i=0;i<postData.length;i++){
			if(postData[i].name!='searchIssueSkipruleVo.issueUrgentLevels'){
				dataJson[postData[i].name]=postData[i].value;
			}else{
				dataJson[postData[i].name+"["+arrIndex+"]"]=postData[i].value;
				arrIndex++;
			}	
		}
		$("#issueSkipruleList").setGridParam({
			url:PATH+'/issueSkipruleManage/findIssueSkiprulePagerBySearchVo.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#issueSkipruleList").setPostData(dataJson);
		$("#issueSkipruleList").trigger("reloadGrid");
	}
	function loadIssueSkiprule(){
		var	postData = {
			 "searchIssueSkipruleVo.orgId":userOrgId
			// parameters
		}
		$("#issueSkipruleList").setGridParam({
	 		url:PATH+'/issueSkipruleManage/findIssueSkiprulePagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#issueSkipruleList").setPostData(postData);
		$("#issueSkipruleList").trigger("reloadGrid");
	}
	function checkStatusOn(ids){
		if(ids.length ==0){
			$.messageBox({level:'warn',message:"没有选中任何记录"});	
		}
		var flag = false;
		for(var i=0;i<ids.length;i++){
			var rowData=$("#issueSkipruleList").getRowData(ids[i]);
			if(rowData.status == '是'){
				//$("#"+ids[i]+ " td").css("background-color","#FFFFCC");
				flag = true ;
			}else{
				//$("#"+ids[i] + " td").css("background-color","#E3F0F9");
			}
		}
		return flag ;
	}
	function checkStatusOff(ids){
		if(ids.length ==0){
			$.messageBox({level:'warn',message:"没有选中任何记录"});	
		}
		var flag = false;
		for(var i=0;i<ids.length;i++){
			var rowData=$("#issueSkipruleList").getRowData(ids[i]);
			if(rowData.status == '否'){
				//$("#"+ids[i]+ " td").css("background-color","#FFFFCC");
				flag = true ;
			}else{
				//$("#"+ids[i] + " td").css("background-color","#E3F0F9");
			}
		}
		return flag ;
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#issueSkipruleList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#issueSkipruleList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

}