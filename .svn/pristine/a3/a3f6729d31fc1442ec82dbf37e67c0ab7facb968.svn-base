TQ.smsUplinkList = function (dfop){

	var dialogWidth = 800;
	var dialogHeight = 600;
	
	$(function(){
		var changeOrg = dfop.changeOrg;
		$("#smsOrgBtn").html(dfop.orgName);
		
		jQuery("#smsUplinkList").jqSubGrid({
			url:PATH+"/smsUplinkManage/findSmsUplinkPagerBySearchVo.action",
			postData:{
				"searchSmsUplinkVo.senderOrgId":changeOrg
			},
			datatype: "json",
		   	colModel:[
				{name:"id",index:'id',hidden:true},
				{name:'counts', index:'counts',label:'发送数', width:100, sortable:true, align:'center', hidden:false},
		    	{name:'content', index:'content',label:'发送内容', width:200, sortable:true, align:'center', hidden:false},
		    	{name:'senderName', index:'senderName',label:'发送者姓名', width:100, sortable:true, align:'center', hidden:false}, 	
		    	{name:'sendTime', index:'sendTime',label:'发送时间', width:200, sortable:true, align:'center', hidden:false}
		   	],
		  	subGridPostData:function(data){return {"searchSmsUplinkVo.parentId":data.id}},
		    subGridRowColapsed:function(subgrid_id, row_id) {
				var row = $("#"+row_id);
				row.find("td").removeClass("ui-widget-border").css({"border-width":"1px"});
				row.find("td:first").css({"border-left-width":"1px"});
				row.find("td:last").css({"border-right-width":"1px","border-left-width":"1px","border-right-color":"#a6c9e2"});
				$("#view").buttonDisable();
			},
		    gridComplete:function(){},
		    subGridUrl:PATH+"/smsUplinkManage/dispatchOperate.action?mode=sub"
		});
		jQuery("#smsUplinkList").jqGrid('setFrozenColumns');
		
		$("#fastSearchButton").click(function(){
			var fastSearchVal = $("#searchText").val();
			if(fastSearchVal == '请输入发送者姓名') return;
			var	postData = {
				 "searchSmsUplinkVo.senderName":fastSearchVal,
				 "searchSmsUplinkVo.senderOrgId":changeOrg
			}
			$("#smsUplinkList").setGridParam({
				url:PATH+"/smsUplinkManage/findSmsUplinkPagerBySearchVo.action",
				datatype: "json",
				page:1
			});
			$("#smsUplinkList").setPostData(postData);
			$("#smsUplinkList").trigger("reloadGrid");
		});
		
		$("#searchText").focus(function(){
	        this.select();
		 });
		
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入发送者姓名");
		});
		
		$("#add").click(function(){
			$("#smsUplinkDialog").createDialog({
				width: 600,
				height: 400,
				title:'新增短信信息',
				url:PATH+'/smsUplinkManage/dispatchOperate.action?mode=add',
				buttons: {
					"发送" : function(event){
				    	$("#maintainForm").submit();
				    },
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		$("#view").click(function(){
			if($("#view").attr("disabled")){
				return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
		 		return;
			}
			viewSmsUplink(selectedId);
		});
		
		$("#reload").click(function(){
			$("#searchText").attr("value","请输入发送者姓名");
			$("#smsUplinkList").setGridParam({
				url:PATH+"/smsUplinkManage/findSmsUplinkPagerBySearchVo.action",
				datatype: "json",
				page:1
			});
			$("#smsUplinkList").setPostData({"searchSmsUplinkVo.senderOrgId":changeOrg});
			$("#smsUplinkList").trigger("reloadGrid");
		});
		
		$("#smsOrgBtn").click(function(){
			$("#smsUplinkDialog").createDialog({
				url:PATH+'/sysadmin/orgManage/orgSelectComponent.action?id='+changeOrg,
				width:550,
				height:'auto',
				title:'辖区选择',
				buttons: {
					"确定" : function(event){
						var selectInput=$("#orgSelectInput");
						changeOrg=selectInput.val();
						$("#smsOrgBtn").html(selectInput.attr("text"));
						$("#smsUplinkList").setPostData({"searchSmsUplinkVo.senderOrgId":changeOrg});
						$("#smsUplinkList").trigger("reloadGrid");
						$(this).dialog("close");
					},
					"取消" : function(){
						$(this).dialog("close");
					}
				}
			});
		});
				
	});
	

	function viewSmsUplink(selectedId){
		if(selectedId==null){
	 		return;
		}
		var rowData = $("#smsUplinkList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#smsUplinkDialog").createFrameDialog({
			width:dialogWidth,
			height:600,
			title:"查看短信发件箱信息",
			url:PATH+'/smsUplinkManage/dispatchOperate.action?mode=view&id='+id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#smsUplinkList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function getSelectedIds(){
		var selectedIds = $("#smsUplinkList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

}