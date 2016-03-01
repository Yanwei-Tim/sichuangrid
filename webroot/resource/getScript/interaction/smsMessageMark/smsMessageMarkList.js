TQ.smsMessageMarkList = function (dfop){
	$(document).ready(function(){
		$("#smsMessageMarkList").jqGridFunction({
			url:PATH+"/smsMessageMarkManage/findSmsMessageMarks.action",
			datatype: "json",
			colNames:['id','operationtType','dealType','操作类型','摸版'],
		   	colModel:[
		        {name:"id",index:"id",hidden:true,sortable:false},
		        {name:"operationtType",index:"operationtType",sortable:false,hidedlg:true,frozen:true,hidden:true},
		    	{name:"dealType",index:"dealType",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:'type',formatter:typeStateFormatter,sortable:false,width:200},
		        {name:'model',sortable:false,width:600}
			],
			ondblClickRow: function (rowid){
				if(dfop.viewSmsMessageMark=='true'){
					viewSmsMessageMark(rowid);
				}
			},
			scrollrow:true
		});


		$("#add").click(function(event){
			$("#smsMessageMarkMaintanceDialog").createDialog({
				width: 650,
				height: 400,
				title:"新增短信提示语模版",
				url:PATH+"/smsMessageMarkManage/dispatch.action?mode=add",
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
		

		$("#update").click(function(event){
			var selectedId = $("#smsMessageMarkList").getGridParam("selrow");
			if(selectedId==null||selectedId.length < 1){
				$.messageBox({level:'warn',message:"请选择要修改的短信提示语模版！"});
		 		return;
			}
			var smsMessageMark=$("#smsMessageMarkList").getRowData(selectedId);
			$("#smsMessageMarkMaintanceDialog").createDialog({
				width: 550,
				height: 270,
				title:"修改短信提示语模版",
				url:PATH+"/smsMessageMarkManage/dispatch.action?mode=edit&smsMessageMark.id="+smsMessageMark.id,
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
			var selectedId = $("#smsMessageMarkList").getGridParam("selrow");
			if(selectedId==null||selectedId.length < 1){
				$.messageBox({level:'warn',message:"请选择要查看的短信提示语模版！"});
		 		return;
			}
			viewSmsMessageMark(selectedId);
		});


		$("#delete").click(function(){
			var selectedId = $("#smsMessageMarkList").getGridParam("selrow");
			if(selectedId==null||selectedId==''){
				$.messageBox({level:'warn',message:"请选择要删除的短信提示语模版！"});
		 		return;
			}
			$.confirm({
				title:"确认删除",
				message:"短信提示语模版删除后，将无法恢复,您确认删除该短信提示语模版信息吗?",
				width: 400,
				okFunc: deleteSmsMessageMark
			});
		});

		$("#reload").click(function(event){
			reloadMyContactList();
		});
	});
	
	
	function typeStateFormatter(el, options, rowData){
		if(rowData.operationtType==1){//短信提示语的操作类型为办理
			var typeshow = "办理-";
			if(rowData.dealType==1001){
				return typeshow+"办理中";
			}else if(rowData.dealType==1011){
				return typeshow+"交办";				
			}else if(rowData.dealType==1021){				
				return typeshow+"上报";				
			}else if(rowData.dealType==1031){				
				return typeshow+"结案";				
			}else if(rowData.dealType==1041){				
				return typeshow+"验证";				
			}else if(rowData.dealType==1051){				
				return typeshow+"回退";				
			}else if(rowData.dealType==1061){				
				return typeshow+"评分";				
			}
		}else{
			if(rowData.operationtType==11){
				return "领导批示";
			}else if(rowData.operationtType==21){
				return "受理";
			}else if(rowData.operationtType==31){
				return "阅读";
			}else if(rowData.operationtType==41){
				return "普通督办";
			}else if(rowData.operationtType==51){
				return "黄牌督办 ";
			}else if(rowData.operationtType==61){
				return "红牌督办 ";
			}else if(rowData.operationtType==71){
				return "取消督办 ";
			}
		}
	}
	
	function reloadMyContactList(){
		$("#smsMessageMarkList").setGridParam({
			url:PATH+"/smsMessageMarkManage/findSmsMessageMarks.action",
			datatype: "json",
			page:1
		});
		$("#smsMessageMarkList").trigger("reloadGrid");
	}

	function viewSmsMessageMark(rowid){
		if(rowid==null){
	 		return;
		}
		var smsMessageMark =  $("#smsMessageMarkList").getRowData(rowid);
		$("#smsMessageMarkMaintanceDialog").createDialog({
			width: 550,
			height: 270,
			title:'查看短信提示语模版信息',
			modal : true,
			url:PATH+"/smsMessageMarkManage/dispatch.action?mode=view&smsMessageMark.id="+smsMessageMark.id,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function deleteSmsMessageMark(){
		var selectedId = $("#smsMessageMarkList").getGridParam("selrow");
		if(selectedId==null||selectedId==''){
			 return;
		}
		var smsMessageMark=$("#smsMessageMarkList").getRowData(selectedId);
		$.ajax({
			url:PATH+'/smsMessageMarkManage/deleteSmsMessageMark.action?smsMessageMark.id='+smsMessageMark.id,
			success:function(data){
				if(data == true){
					$("#smsMessageMarkList").delRowData(selectedId);
					$.messageBox({ message:"成功删除该短信提示语模版信息!"});
					return;
				}
	            $.messageBox({message:data,level: "error"});
	        }
		});
	}
}