TQ.smsdownlinkList = function (dfop){
	var dialogWidth = 800;
	var dialogHeight = 600;

	// Formatter
	function operatorFormatter(el,options,rowData){
		return "<a href=javascript:returnOperator('"+rowData.sender+"',"+rowData.id+")><span>回复</span></a> | <a href=javascript:deleteOperator('"+rowData.sender+"')><span>删除</span></a>";
	}

	// 改变组织机构树时加载列表
	function onOrgChanged(){
		
		var initParam = {
			"sender":"",
			"isRead":$("#isRead").val()
		}
		$("#smsdownlinkList").setGridParam({
			url:PATH+'/smsdownlinkManage/findSmsdownlinkForPager.action',
			datatype: "json",
			page:1
		});
		$("#smsdownlinkList").setPostData(initParam);
		$("#smsdownlinkList").trigger("reloadGrid");
	}
	
	$(function(){
		$("#isRead").change(function(){
			onOrgChanged();
		});
		
		
		function toggleButtonState(){
		  	var selectedIds=$("#smsdownlinkList").jqGrid("getGridParam", "selarrrow");
		  	var selectedRowCount=selectedIds.length;
		}
		
		// 生成列表
		$("#smsdownlinkList").jqGridFunction({
			mtype:'post',
			datatype: "local",	
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:180,frozen:true,sortable:false,align:'center' },
		    	{name:'content', index:'content',label:'发送内容', width:280, sortable:true, align:'center', hidden:false},
		    	{name:'sendTime', index:'sendTime',label:'发送时间', width:280, sortable:true, align:'center', hidden:false}, 	
		    	{name:'sender', index:'sender',label:'发送者手机号', width:280, sortable:true, align:'center', hidden:false},
		    	{name:'isRead', index:'isRead',label:'',hidden:true,hidedlg:true}
		   	],
		  	multiselect:true,
		  	onSelectAll:function(data){},
	    	loadComplete: function(data){
	    		var datas = $("#smsdownlinkList").getDataIDs();
		  		if(!datas)return;
		  		for(var i=0;i < datas.length;++i){
		  			var rowData = $("#smsdownlinkList").getRowData(datas[i]);
		  			if(rowData.isRead==0)
		  				$("#"+rowData.id+"").find("td").css("font-weight","bold");
		  		}
	    	},
			onSelectRow: function(data){}
		});
		jQuery("#smsdownlinkList").jqGrid('setFrozenColumns');
		
		onOrgChanged();
		
		$("#fastSearchButton").click(function(){
			var fastSearchVal = $("#searchText").val();
			if(fastSearchVal == '请输入发送者手机号' || !fastSearchVal) return;
			
			var initParam = {
				"sender":fastSearchVal,
				"isRead":$("#isRead").val()
			}
			$("#smsdownlinkList").setGridParam({
				url:PATH+'/smsdownlinkManage/findSmsdownlinkForPager.action',
				datatype: "json",
				page:1
			});
			$("#smsdownlinkList").setPostData(initParam);
			$("#smsdownlinkList").trigger("reloadGrid");
			
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入发送者手机号");
		});
		$("#delete").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
				 return;
			}
			var ids= new Array(allValue.length);
			for(var i=0;i<allValue.length;i++){
				var rowData = $("#smsdownlinkList").getRowData(allValue[i]);
				ids[i]=rowData.sender;
			}
			
			deleteOperator(ids);
		});
		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return ;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
		});
		$("#reload").click(function(){
			$("#searchText").attr("value","请输入发送者手机号");
			
			var initParam = {
				"sender":""
			}
			$("#smsdownlinkList").setGridParam({
				url:PATH+'/smsdownlinkManage/findSmsdownlinkForPager.action',
				datatype: "json",
				page:1
			});
			$("#smsdownlinkList").setPostData(initParam);
			$("#smsdownlinkList").trigger("reloadGrid");
			
		});

		$("#search").click(function(event){
			$("#smsdownlinkDialog").createDialog({
				width:350,
				height:220,
				title:'短信收件箱查询-请输入查询条件',
		 		url:PATH+'/interaction/newSMS/smsDownlink/searchdownlinkDlg.jsp',
				buttons: {
			   		"查询" : function(event){
			   			$("#searchdownlinkForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	});
	
	

	function returnOperator(sender,id){
		if(!id){
			return ;
		}
		$("#"+id+"").find("td").css("font-weight","normal");
		$("#smsdownlinkDialog").createDialog({
			title:"短信回复",
			width: 500,
			height: 570,
			url:PATH+'/smsdownlinkManage/dispatchOperate.action?mode=view&sender='+sender,
			buttons: {
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	}

	function deleteOperator(allValue){
		var encryptIds=deleteOperatorByEncrypt("smsdownlink",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
					url:PATH+'/smsdownlinkManage/updateDeleteStatusBySender.action',
					type:"post",
					data:{
						"ids":encryptIds+""
					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该短信收件箱信息!"});
						$("#smsdownlinkList").trigger("reloadGrid");
					}
				});
			}
		});
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#smsdownlinkList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#smsdownlinkList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

}