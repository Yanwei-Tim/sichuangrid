TQ.excelImportLogList = function (dfop){
	
	function onOrgChanged(){
		$("#excelImportLogList").setGridParam({
			url:PATH+'/baseinfo/excelImportLogManage/searchImportLog.action',
			datatype: "json",
			page:1
		});
		var initParam = "";
		if($("#isSearch").val()!=0){
			initParam = $("#isSearch").val();
		}
		$("#excelImportLogList").setPostData({
			"status":initParam
		});
		$("#excelImportLogList").trigger("reloadGrid");
	}
	
	$(document).ready(function(){
		$("#reload").click(function(event){
			reloadFun();
		});
		$("#excelImportLogList").jqGridFunction({
			mtype:'post',
			datatype: "local",
		   	colModel:[
		        {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
		        {name:'fileName',index:'fileName',label:'文件名称',sortable:true,width:200},
		        {name:'createDate',index:'createDate',label:'导入时间',sortable:true,width:120},
		        {name:'importDataNum',index:'importDataNum',label:'导入总条数',sortable:true,width:100,align:'right'},
		        {name:'currentDealNum', index:'currentDealNum',label:'已处理条数',sortable:true, width:100,align:'right'},
		        {name:'countNum', index:'countNum',label:'成功条数',sortable:false,formatter:countNumFormatter, width:100,align:'right'},
		        {name:'errorCountNum',index:'errorCountNum',label:'错误条数',sortable:true,formatter:errorCountNumFormatter, width:100,align:'right'},
		        {name:'status',index:'status',label:'当前状态',sortable:true,width:100,align:"center",formatter:dealStateFormatter},
		        {name:'updateDate',index:'updateDate',label:"导入完成时间",sortable:false,width:120,align:'center'},
	            {name:'uuid',index:'uuid',label:'错误分析表下载',sortable:false,align:"center",width:100,formatter:downExcelFormatter},
	            {name:'userName',index:'userName',label:'用户名称',hidden:true,sortable:false,align:"center",width:100},
	            {name:'createUser',index:'createUser',label:'操作账号',sortable:false,align:"center",width:100},
	            {name:'organizationId.id',index:'organizationId.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
	            {name:'organizationId.orgName',index:'organizationId.orgName',label:'操作账号层级',width:150,sortable:false,align:"center"}
			],
			loadComplete: function(data){if(afterLoad){afterLoad(data);}},
			multiselect:true
		});
		$("#excelImportLogList").jqGrid('setFrozenColumns');
		function reloadFun(){
			onOrgChanged();
		}
		
		$("#isSearch").change(function(){
			$("#excelImportLogList").setGridParam({
				url:PATH+'/baseinfo/excelImportLogManage/searchImportLog.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			var initParam = "";
			if($("#isSearch").val()!=0){
				initParam = $("#isSearch").val();
			}
			$("#excelImportLogList").setPostData({
				"status":initParam
			});
		    $("#excelImportLogList").trigger("reloadGrid");
		});
		
		onOrgChanged();
		
		$("#searchExcelImportLog").click(function(){
			$("#excelImportLoglog").createDialog({
				width: 650,
				height: 420,
				datatype: "json",
				title:'导入信息查询',
				url:PATH+'/baseinfo/excelImportLogManage/dispatchOperate.action?mode=search',
				buttons: {
					"查询" : function(event){
							selectExcelimportlog();
							$(this).dialog("close");
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
	});
	
	function selectExcelimportlog(){
		var initParam = {
				"excelImportLogVO.importModuleName":$("#importModuleName").val(),
				"excelImportLogVO.organizationId":getCurrentOrgId(),
				"excelImportLogVO.startTime":$("#startTime").val(),
				"excelImportLogVO.endTime":$("#endTime").val(),
				"excelImportLogVO.operateName":$("#operateName").val(),
				"excelImportLogVO.status":$("#status").val()
			}
		var data = $("#searchform").serializeObject();
		$("#excelImportLogList").setGridParam({
			url:PATH+'/baseinfo/excelImportLogManage/selectExcelimportlog.action',
			datatype :'json',
			page:1
		});
		$("#excelImportLogList").setPostData(data);
		$("#excelImportLogList").trigger("reloadGrid");
	}

	function loadData(listParam) {
		$("#excelImportLogList").setGridParam({
			url:PATH+'/baseinfo/excelImportLogManage/selectExcelimportlog.action',
			datatype: "json",
			page:1
		});
		$("#excelImportLogList").setPostData(listParam);
		$("#excelImportLogList").trigger("reloadGrid");
	}
	function afterLoad(){
	}

	function dealStateFormatter(el, options, rowData){
		if(rowData.status != null){
			if(1 == rowData.status){
				return "导入中";
			}else if(2 == rowData.status){
				return "导入结束";
			}else if(3 == rowData.status){
				return "导入失败";
			}else{
				return "";
			}
		}
	}
	function downExcelFormatter(el, options, rowData){
		if(rowData.status != null){
			if(0<rowData.errorCountNum&&2 == rowData.status){
				return downErrorMsg(rowData.uuid,rowData.fileType);
			}else{
				return "";
			}
		}
	}
	function countNumFormatter(el, options, rowData){
		if(rowData.currentDealNum != null&&rowData.errorCountNum!=null){
			if(rowData.currentDealNum>0&&rowData.errorCountNum>=0){
				return rowData.currentDealNum-rowData.errorCountNum;
			}else{
				return "";
			}
		}
	}
	function errorCountNumFormatter(el, options, rowData){
		if(rowData.errorCountNum!=null){
			if(rowData.errorCountNum>0){
				return "<font color='red'>"+rowData.errorCountNum+"</font>"
			}else{
				return rowData.errorCountNum;
			}
		}
	}
}