TQ.sendObjectList = function (dfop){
	
	$(document).ready(function(){
		$("#sendObjectList").jqGridFunction({
			url:PATH+"/smsSendObjectManage/findSmsSendObjectPager.action",
			datatype: "json",
			colModel:[
		    	{name:"id",index:"id",hidden:true},
		    	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	{name:"operator", index:'id',label:'操作',formatter:sendObjectFormatter,width:160,frozen:true,sortable:false,align:'center' },
		    	{name:"name",index:"name",width:190,align:'center',label:"发送对象名称"},
	  			{name:"description",index:"description",width:230,label:"描述"},
	  			{name:"createUser",index:"createUser",width:140,align:'center',label:"创建人"},
	  			{name:"createDate",index:"createDate",width:140,align:'center',label:"创建时间"},
	  			{name:"updateUser",index:"updateUser",width:140,align:'center',label:"修改人"},
	  			{name:"updateDate",index:"updateDate",width:140,align:'center',label:"修改时间"},
	  			{name:"enable",index:"enable",width:230,label:"",hidden:true,hidedlg:true}
		  	],
		  	multiselect:true,
		  	loadComplete: function(){
		  		var datas = $("#sendObjectList").getDataIDs();
		  		if(!datas)return;
		  		for(var i=0;i < datas.length;++i){
		  			var rowData = $("#sendObjectList").getRowData(datas[i]);
		  			if(rowData.enable==false || rowData.enable=="false")
		  				$("#"+rowData.id+"").css("color","red");
		  		}
		  		
		  	},
			ondblClickRow: viewSmsSendObject
		});
		
		$("#fastSearchButton").click(function(){
			search(getCurrentOrgId());
		});
		$("#searchText").focus(function(){
	        this.select();
		 });
		$("#refreshSearchKey").click(function(){
			$("#searchText").attr("value","请输入发送对象名称");
		});
		
		$("#add").click(function(event){
			$("#sendObjectDialog").createDialog({
				width: 850,
				height: 530,
				title:'新增',
				url:PATH+"/smsSendObjectManage/dispatchOperate.action?mode=add",
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
		
		$("#delete").click(function(){
			var ids = $("#sendObjectList").getGridParam("selarrrow");
			if(!ids || ids.length <= 0){
				$.messageBox({
					message:"请选择一条记录！",
					level: "error"	
				});	
				return;
			}
			deleteSendObject(ids);
		});
		
		$("#reload").click(function(){
			$("#sendObjectList").setGridParam({
				url:PATH+"/smsSendObjectManage/findSmsSendObjectPager.action",
				datatype: "json",
				page:1
			});
			$("#sendObjectList").setPostData({
		   	});
			$("#sendObjectList").trigger("reloadGrid");
		});
	});

	
	function search(orgId){
		var fastSearchVal = $("#searchText").val();
		if(fastSearchVal == '请输入发送对象名称') return;
		var	postData = {
			"smsSendObject.name":fastSearchVal
		}
		$("#sendObjectList").setGridParam({
			url:PATH+"/smsSendObjectManage/findSmsSendObjectPager.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#sendObjectList").setPostData(postData);
		$("#sendObjectList").trigger("reloadGrid");
	}


	function viewSmsSendObject(selectedId){
		if(!selectedId){
			$.messageBox({
				message:"请选择一条记录！",
				level: "error"	
			});	
			return;
		}
		var rowData = $("#sendObjectList").getRowData(selectedId);
		var smsSendObject=$("#sendObjectList").getRowData(rowData);
		$("#sendObjectDialog").createDialog({
			width: 850,
			height: 640,
			title:rowData.name+'发送对象',
			url:PATH+"/smsSendObjectManage/dispatchOperate.action?mode=view&id="+smsSendObject.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}

}