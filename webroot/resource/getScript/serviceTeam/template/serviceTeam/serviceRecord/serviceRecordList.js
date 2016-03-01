TQ.serviceRecordList = function (){
	$(document).ready(function(){
		//是否进入了网格
		if(isGrid()){
			$("#displayLevel").hide();
		}
		loadDisplayYear();
		$("#serviceRecordList").jqGridFunction({
			datatype: "local",
			multiselect:true,
			colModel:[
				{name:"id",index:"id",sortable:false,hidden:true},
				{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			    {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter_ServiceRecord,width:80,align:"center"},
			    {name:'occurDate',index:"occurDate",label:'服务时间',sortable:true,width:100,align:"center"},
			    {name:'occurPlace',index:"occurPlace",label:'服务地点',sortable:true,width:150},
			    {name:'serviceJoiners',index:'serviceJoiners',label:'服务参与人',sortable:false,width:120},
			    {name:'serviceObjects',label:'服务对象',sortable:false,width:150},
			    {name:'serviceMembers',label:'记录所属人',sortable:false,width:120},
			    //{name:'teamName',label:'所属服务团队',sortable:false, width:150},
			    {name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:350},
			    {name:'serviceRecordAttachments',label:'附件',sortable:false, width:200, formatter:formatterAttachFile}
			],
			loadComplete: afterLoad,
			ondblClickRow: viewServiceRecord
		});
		$("#serviceRecordList").jqGrid('setFrozenColumns');
		getServiceRecordList();
		
		function loadDisplayYear()
		{
			$.ajax({
				async: false,
				url: PATH+"/plugin/serviceTeam/serviceRecord/findDisplayYear.action",
				data:{},
				success:function(yearList){
					for(var j = 0 ;j<=yearList.length;j++){
						var map = yearList[j];
						for(var i in map){ 
							$("#displayYear").append("<option value='"+i+"'>"+map[i]+"</option>");
						}
					}
				}
			});
		}
		//新增按钮事件
		$("#addServiceRecord").click(function(event){

			$("#serviceRecordDialog").createDialog({
				title:"新增服务记录",
				width: 600,
				height: 500,
				url:PATH+'/plugin/serviceTeam/serviceRecord/dispatch.action?mode=add&dailogName=serviceRecordDialog&showRecordType=2',
				buttons: {
					"保存并关闭" : function(event){
						$("#isSubmit").val("true");
			   			$("#serviceRecordForm").submit();
					},
					"保存并继续" : function(event){
						$("#isSubmit").val("false");
			   			$("#serviceRecordForm").submit();
					}
				}
			});
		});
		//快速过滤事件绑定
		$("#displayLevel").change(getServiceRecordList);
		$("#displayYear").change(getServiceRecordList);
		//刷新按钮事件绑定
		$("#reloadServiceRecord").click(function(event){
			getServiceRecordList();
		});
		
		//高级搜索对话框
		$("#searchServiceRecord").click(function(event){
			$("#serviceRecordDialog").createDialog({
				title:"服务记录查询-请输入查询条件",
				width: 600,
				height: 300,
				url:PATH+'/plugin/serviceTeam/serviceRecord/dispatch.action?mode=search&dailogName=serviceRecordDialog',
				buttons: {
					"查询" : function(event){
						searchServiceRecords();
						$(this).dialog("close");
						$("#displayYear").html("");
						loadDisplayYear();
						$("#displayYear").append("<option selected='selected'>请选择</option>");
					},
					"关闭" : function(){
			        	$(this).dialog("close");
					}
				}

			});
		});
		
		$("#exportServiceRecord").click(function(event){
			if($("#serviceRecordList").getGridParam("records")>0){
				var postData = $("#serviceRecordList").getPostData();
				$("#serviceRecordDialog").createDialog({
					width: 250,
					height: 200,
					title:"导出服务记录信息",
					url:PATH+'/common/exportExcel.jsp',
					postData:{
						gridName:'serviceRecordList',
						downloadUrl:PATH+'/plugin/serviceTeam/serviceRecord/downloadServiceRecord.action'
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
				$.messageBox({level : 'warn',message:"列表中没有数据，不能导出！"});
				return;
			}
		});
		
		$("#developPeopleLogForServiceRecord").click(function(){
			var selectedId = $("#serviceRecordList").jqGrid("getGridParam", "selarrrow");
			if(null == selectedId || selectedId.length == 0){
				$.messageBox({level:'warn',message:"请选择一条数据再进行生成民情日志！"});
				return;
			}
			if(selectedId.length > 1){
				$.messageBox({level:'warn',message:"只能选择一条数据进行生成民情日志！"});
				return;
			}
			developPeopleLogOperator(selectedId);
		});
		
	});
	

	//列表显示（包括快速过滤）
	function getServiceRecordList(){
		$("#serviceRecordList").setGridParam({
			url:PATH+'/plugin/serviceTeam/serviceRecord/findServiceRecords.action',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#serviceRecordList").setPostData({
			"serviceRecordVo.organization.id":getCurrentOrgId(),
			"serviceRecordVo.displayLevel":$("#displayLevel").val(),
			"serviceRecordVo.displayYear":$("#displayYear").val()
		});
		$("#serviceRecordList").trigger("reloadGrid"); 
	}
	
	//高级搜索
	function searchServiceRecords()
	{
		$("#serviceRecordList").setGridParam({
			url:PATH+"/plugin/serviceTeam/serviceRecord/findServiceRecords.action?showRecordType=2",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchServiceRecordForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
	 		if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
		}
		$("#serviceRecordList").setPostData(
			$.extend(dataJson,{
				"serviceRecordVo.organization.id":getCurrentOrgId(),
				"serviceRecordVo.displayLevel":$("#displayLevel").val()
		}));
		$("#serviceRecordList").trigger("reloadGrid");
	}
	//加载完时执行的方法
	function afterLoad() {
		loadFiles();
	}

	//加载文件
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			var popMoreData = [];
			$.each($("#serviceRecordList").data($(n).attr("serviceRecordId")),function(ind, fn){
				popMoreData[ind]={id:fn.id, url:PATH+'/plugin/serviceTeam/serviceRecord/downloadServiceRecordAttachment.action?attachmentId='+fn.id, text:fn.fileName,clickFun:function(){}};
			});
			$(n).popUpMore({data: popMoreData});
		});
	}
	function formatterAttachFile(el,options,rowData){
		if(rowData.serviceRecordAttachments.length>0){
			$("#serviceRecordList").data(String(rowData.id), rowData.serviceRecordAttachments);
			return "<div style='clear:both' align='center'><a href='javascript:;' id='serviceRecord_"+rowData.id+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.id+"' >附件>></a></div>";
		}
		return "";
	}
	
	function developPeopleLogOperator(selectedId) {
		var allValue=deleteOperatorByEncrypt('serviceRecord',selectedId,'encryptId');
		$("#serviceRecordDialog").createDialog({
			width: 720,
			height: 600,
			title: '由服务记录转成民情日志',
			url:PATH+'/plugin/serviceTeam/serviceRecord/dispatchByEncrypt.action?mode=developPeopleLog&dialogName=serviceRecordDialog&serviceRecord.id='+allValue,
			buttons: {
		   		"保存" : function(event){
					$("#maintainPeopleLogForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}	
}