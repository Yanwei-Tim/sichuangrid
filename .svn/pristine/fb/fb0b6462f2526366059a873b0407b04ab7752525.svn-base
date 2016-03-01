TQ.myContactList = function (dfop){
	$(document).ready(function(){
		$("#myContactList").jqGridFunction({
			url:PATH+"/contact/myContacterManage/findMyContacters.action",
			datatype: "json",
			colNames:['id','encryptId','姓名','联系手机','备注'],
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false},
		    	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:'name',sortable:true,width:200},
		  		{name:"mobileNumber",sortable:true,width:200},
		        {name:'remark',sortable:true,width:400}
			],
			ondblClickRow: function (rowid){
				if(dfop.viewMyContact=='true'){
					viewMyContactInfo(rowid);
				}
			},
			scrollrow:true
		});


		$("#add").click(function(event){
			$("#myContactMaintanceDialog").createDialog({
				width: 550,
				height: 270,
				title:"新增其他联系人信息",
				url:PATH+"/contact/myContacterManage/dispatch.action?mode=add",
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
		
		$("#ctd").click(function(){
			var selectedId = $("#myContactList").getGridParam("selrow");
			if(selectedId==null||selectedId==""){
		 		return;
			}
			
			$("#ctd").attr("href", "ecp:ctd?num="+$("#myContactList").getRowData(selectedId).mobileNumber+"&name=");
		});

		$("#update").click(function(event){
			var selectedId = $("#myContactList").getGridParam("selrow");
			if(selectedId==null||selectedId.length < 1){
				$.messageBox({level:'warn',message:"请选择要修改的联系人！"});
		 		return;
			}
			var myContacter=$("#myContactList").getRowData(selectedId);
			$("#myContactMaintanceDialog").createDialog({
				width: 550,
				height: 270,
				title:"修改其他联系人信息",
				url:PATH+"/contact/myContacterManage/dispatch.action?mode=edit&myContacter.id="+myContacter.encryptId,
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

		$("#view").click(function(){
			var selectedId = $("#myContactList").getGridParam("selrow");
			if(selectedId==null||selectedId.length < 1){
				$.messageBox({level:'warn',message:"请选择要查看的联系人！"});
		 		return;
			}
			viewMyContactInfo(selectedId);
		});


		$("#delete").click(function(){
			var selectedId = $("#myContactList").getGridParam("selrow");
			if(selectedId==null||selectedId==''){
				$.messageBox({level:'warn',message:"请选择要删除的联系人！"});
		 		return;
			}
			$.confirm({
				title:"确认删除",
				message:"联系人信息删除后，将无法恢复,您确认删除该联系人信息吗?",
				width: 400,
				okFunc: deleteMyContact
			});
		});

		$("#reload").click(function(event){
			reloadMyContactList();
		});

		$("#search").click(function(event){
			$("#searchDialog").createDialog({
				width: 550,
				height: 200,
				datatype: "json",
				title:'其他联系人查询-请输入查询条件',
				url:PATH+'/interaction/contact/myContact/searchMyContactDlg.jsp',
				buttons: {
					"查询" : function(event){
						refreshMyContactGrid();
		   				$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	});
	
	
	function reloadMyContactList(){
		$("#myContactList").setGridParam({
			url:PATH+"/contact/myContacterManage/findMyContacters.action",
			datatype: "json",
			page:1
		});
		$("#myContactList").trigger("reloadGrid");
	}

	function viewMyContactInfo(rowid){
		if(rowid==null){
	 		return;
		}
		var myContact =  $("#myContactList").getRowData(rowid);
		$("#myContactMaintanceDialog").createDialog({
			width: 550,
			height: 270,
			title:'查看其他联系人信息',
			modal : true,
			url:PATH+"/contact/myContacterManage/dispatch.action?mode=view&myContacter.id="+myContact.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function deleteMyContact(){
		var selectedId = $("#myContactList").getGridParam("selrow");
		if(selectedId==null||selectedId==''){
			 return;
		}
		var myContact=$("#myContactList").getRowData(selectedId);
		$.ajax({
			url:PATH+'/contact/myContacterManage/deleteMyContacter.action?myContacter.id='+myContact.encryptId,
			success:function(data){
				if(data == true){
					$("#myContactList").delRowData(selectedId);
					$.messageBox({ message:"成功删除该联系人信息!"});
					return;
				}
	            $.messageBox({message:data,level: "error"});
	        }
		});
	}

	function refreshMyContactGrid() {
		var myContactName=$('#myContactName').val();
		var myContactMobileNumber = $("#myContact-mobileNumber").val();
		$("#myContactList").setGridParam({
			url:PATH+'/contact/myContacterManage/searchMyContacters.action',
			postData: {
				"myContacter.name":myContactName,
				"myContacter.mobileNumber":myContactMobileNumber
			}
		});
		$("#myContactList").trigger("reloadGrid");
	}

}