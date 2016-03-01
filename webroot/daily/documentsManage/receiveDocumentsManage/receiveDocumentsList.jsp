<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-corner-all" id="nav" >
	<div class="btnbanner btnbannerData">
		<select id="signState" style="height: 23px">
			<option value="" selected="selected">全部</option>
			<option value="0">未签</option>
			<option value="1" >已签</option>
		</select>
		<input type="text" value="请输入文件标题" name="searchByCondition" class="searchtxt"   id="searchByCondition" maxlength="18"  onblur="value=(this.value=='')?'请输入文件标题':this.value;" onfocus="value=(this.value=='请输入文件标题')?'':this.value;"/>
		<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 270px; cursor: pointer; outline: none;"></button>
		<pop:JugePermissionTag ename="searchReceiveDocuments">
		<a href="javascript:;" id="fastSearch"><span>搜索</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchReceiveDocuments">
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween"></span>
		<pop:JugePermissionTag ename="receiveDocuments">
		<a id="receive" href="javascript:void(0)"><span>签收文件</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="readReceiveDocuments">
		<a id="read" href="javascript:void(0)"><span>阅读</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="transpondReceiveDocuments">
		<a id="transmit" href="javascript:void(0)"><span>转发</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteReceiveDocuments">
		<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
		</pop:JugePermissionTag>
		<a id="receiveHistoryDocuments"  href="javascript:void(0)"><span>历史数据查询</span></a>
		<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="synchrodispatchDocuments">
		<a id="synchroReceiveDoc" href="javascript:void(0)"><span>同步资料库</span></a>
		</pop:JugePermissionTag>
	</div>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="receiveDocumentsList"> </table>
	<div id="receiveDocumentsListPager"></div>
</div>
<div id="receiveDocumentsMaintanceDialog"></div>
<div id="scanHeaderImage"></div>
<div id="ObjDialog"></div>
<div id="copyObjDialog"></div>
<div id="fileSharingDailog"></div>
<div id="historyDocumentsDlg"></div>
<div style="display: none;">
	<span id="title">收文</span>
</div>
<script type="text/javascript">
var dialogWidth=600;
var dialogHeight=480;
var title=$("#title").html();
function deleteReceiveDocuments(id){
	var rowData= $("#receiveDocumentsList").getRowData(id);
	if("未阅读"==rowData.readState||"未签收"==rowData.signState){
		 $.messageBox({message:"未签收或未阅读的文件不能删除",level:"warn"});
		 return;
    }
	var allValue=deleteOperatorByEncrypt('receiveDocuments',id,'encryptId');
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?数据一旦删除无法恢复!",
		okFunc: function() {
			$.ajax({
			  url:"${path}/documents/receiveDocumentsManage/deleteReceiveDoc.action",
			  type:'post',
			  data:{
				  'selectedIds':allValue
			  	},
			  success:function(data){
				if(data==null){
					$("#receiveDocumentsList").trigger("reloadGrid");
					 $.messageBox({message:"已经成功删除文件 !"});
				}else{
				 $.messageBox({message:data,level:"error"});
				}
			 }
		  });
	 }
  });
}

function loadReceiveDocList(){
	$("#receiveDocumentsList").setGridParam({
		url:'${path}/documents/receiveDocumentsManage/receiveDocumentsList.action',
		datatype: "json",
		page:1
	});

	$("#receiveDocumentsList").setPostData({
		"document.signState":$("#signState").val()
   	});
	$("#receiveDocumentsList").trigger("reloadGrid");
}
function viewReceiveDialog(documentId){
	var rowData= $("#receiveDocumentsList").getRowData(documentId);
	if("0"==rowData.signState){
		$.messageBox({message:"签收该文件后，您才能阅读！",level:"warn"});
		return;
   	}else if("未签收"==rowData.signState){
   		$.messageBox({message:"签收该文件后，您才能阅读！",level:"warn"});
		return;
   	}
	var readState = 0;
	if("已阅读"==rowData.readState){
		readState=1;
	}
	$("#receiveDocumentsMaintanceDialog").createDialog({
		width: 600,
		height: 460,
		title:'阅读收文信息',
		url:'${path}/documents/receiveDocumentsManage/operateReceiveDocuments.action?mode=view&document.id='+rowData.encryptId+'&readState='+readState,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function getDocumentId(selectedIds){
	var documentIds="";
	var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds.length==1){
		var rowData=$("#receiveDocumentsList").getRowData(selectedIds);
		return rowData.id;
	}else{
		for(var i = 0;i<selectedIds.length;i++){
			var rowData=$("#receiveDocumentsList").getRowData(selectedIds[i]);
			   documentIds+=rowData.id+",";
			   documentIds=documentIds.substring(0,documentIds.length-1);
			   return documentIds;
		}
	}
}

function getDocumentEncryptId(selectedIds){
	var documentIds="";
	var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds.length==1){
		var rowData=$("#receiveDocumentsList").getRowData(selectedIds);
		return rowData.encryptId;
	}else{
		for(var i = 0;i<selectedIds.length;i++){
			var rowData=$("#receiveDocumentsList").getRowData(selectedIds[i]);
			   documentIds+=rowData.encryptId+",";
			   documentIds=documentIds.substring(0,documentIds.length-1);
			   return documentIds;
		}
	}
}

$(document).ready(function(){
	
	function readFormatter(el, options, rowData){ 
		if(rowData.readState=='0'){
			return "未阅读";
		}else{
			return "已阅读";
		}
	}

	function sendTypeFormatter(el, options, rowData){ 
		if(rowData.status=='copySend'){
			return "抄送";
		}else{
			return "主送";
		}
	}
	
	$("#receiveHistoryDocuments").click(function(){
		$("#historyDocumentsDlg").createDialog({
			width:950,
			height:550,
			title:'收公文历史数据',
			url:'${path}/daily/documentsManage/receiveDocumentsManage/receiveDocumentsHistoryList.jsp',
			buttons:{
				"关闭":function(event){
					$(this).dialog("close");
				}
			}
		});
	});

	
	
	$("#receive").click(function(event){
		var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null){
			$.messageBox({message:"请至少选择一个文件才能签收！",level:"warn"});
			return;
		}
		for(var i = 0;i<selectedIds.length;i++){
			var rowData=$("#receiveDocumentsList").getRowData(selectedIds[i]);
			if(rowData.signState=='已签收'){
				$.messageBox({message:"您选中的文件中含有已签收的文件，请重新选择",level:"warn"});
				return;
		   }
		}
		$("#receiveDocumentsMaintanceDialog").createDialog({
			width:400,
			height:240,
			title:'签收确认',
			url:'${path}/documents/receiveDocumentsManage/operateReceiveDocuments.action?mode=receiveState&selectedIds='+selectedIds,
			buttons: {
		   		"保存" : function(event){
		   			$("#receiveStateForm").submit();
		   		},
   				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	function getDocumentsHasOrgId(){
		var documentIds="";
		var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==1){
			var rowData=$("#receiveDocumentsList").getRowData(selectedIds);
			return rowData.documentsHasOrgId;
		}else{
			for(var i = 0;i<selectedIds.length;i++){
				var rowData=$("#receiveDocumentsList").getRowData(selectedIds[i]);
				   documentIds+=rowData.documentsHasOrgId+",";
			}
			  documentIds=documentIds.substring(0,documentIds.length-1);
			  return documentIds;
		}
	}

	$("#transmit").click(function(){
		var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length!=1){
	 		return  $.messageBox({message:"请选择一条公文再转发 ",level:"warn"});;
		}
		var rowData=$("#receiveDocumentsList").getRowData(selectedIds);
		if(rowData.signState=="未签收"||rowData.signState=='0'){
			return  $.messageBox({message:"请先签收文件后再转发 ",level:"warn"});;
		}
		if("已阅读"!=rowData.readState){
			$.messageBox({message:"未阅读的公文不能转发",level:"warn"});
			return;
	    }
		$("#receiveDocumentsMaintanceDialog").createDialog({
			width:800,
			height:530,
			title:'转发公文 ',
			url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=transmit&document.id='+rowData.encryptId,
			buttons: {
		   		"保存" : function(event){
		   			$("#dispatchDocumentsForm").submit();
		   		},
   				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#synchroReceiveDoc").click(function(){
		var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length<1){
	 		return  $.messageBox({message:"请选择一条公文 ",level:"warn"});;
		}
		var rowData=$("#receiveDocumentsList").getRowData(selectedIds[0]);
	
		if("已阅读"!=rowData.readState){
			$.messageBox({message:"未阅读的公文不能同步到资料库",level:"warn"});
			return;
	   }
		$("#fileSharingDailog").createDialog({
		width:650,
		height:500,
		title:'同步资料库',
		url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=sharing&selectedIds='+getDocumentEncryptId(selectedIds)+"&identification=docSynchro",
		buttons :{
			"保存" : function(){
				$("#fileSharing-form").submit();
				$("#receiveDocumentsList").trigger("reloadGrid");
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	 });
	});
	
	
	
	$("#delete").click(function(){
		var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null){
			$.messageBox({message:"没有文件可以删除！",level:"warn"});
			return;
		}
		for(var i = 0;i<selectedIds.length;i++){
			var rowData=$("#receiveDocumentsList").getRowData(selectedIds[i]);
			if('未阅读'==rowData.readState||"未签收"==rowData.signState){
			 $.messageBox({message:"未签收或未阅读的文件不能删除",level:"warn"});
			 return;
		   }
		}
		$.confirm({
				title:"确认删除",
				message:"确定要删除吗?",
				okFunc: function() {
					$.ajax({
					  url:"${path}/documents/receiveDocumentsManage/deleteReceiveDoc.action?selectedIds="+selectedIds,
					  success:function(data){
						if(data==null){
							$("#receiveDocumentsList").trigger("reloadGrid");
							 $.messageBox({message:"已经成功删除文件 !"});
						}else{
						 $.messageBox({message:data,level:"error"});
						}
					}
				});
			}
		});
	});
	
	function getDocumentsHasUserId(){
		var documentIds="";
		var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds.length==1){
			var rowData=$("#receiveDocumentsList").getRowData(selectedIds);
			return rowData.documentsHasUserId;
		}else{
			for(var i = 0;i<selectedIds.length;i++){
				var rowData=$("#receiveDocumentsList").getRowData(selectedIds[i]);
				   documentIds+=rowData.documentsHasUserId+",";
				   documentIds=documentIds.substring(0,documentIds.length-1);
				   return documentIds;
			}
		}
	}
	
	$("#fastSearch").click(function(){
		fastSearchfun(getCurrentOrgId());
	});

	$("#reload").click(function(event){
		$("#searchByCondition").attr("value","请输入文件标题");
		loadReceiveDocList();
	});

	$("#read").click(function(event){
		viewReceiveDoc();
	});
	
	$("#signState").change(function(){
		loadReceiveDocList();
	});
	function setSpecialElement(){
		//是否同步资料库	
		if($("#synchroDocs").val()==""){
			$("#synchroDocs").attr("disabled","disabled");
		}
		//是否有附件 
		if($("#attachFiles").val()==""){
			$("#attachFiles").attr("disabled","disabled");
		}
	}
	
	$("#search").click(function(event){
		$("#receiveDocumentsMaintanceDialog").createDialog({
			width: 640,
			height: 400,
			title:title+'查询-请输入查询条件',
			url:'${path}/documents/receiveDocumentsManage/operateReceiveDocuments.action?mode=search&searchDocumentVo.signState='+$("#signState").val(),
			buttons: {
		   		"查询" : function(event){
				searchReceiveDocuments();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	
	});
	function searchReceiveDocuments(){
		setSpecialElement();
		var data=$("#searchReceiveDocForm").serializeArray();
		$("#receiveDocumentsList").setGridParam({
				url:'${path}/documents/receiveDocumentsManage/searchReceiveDocuments.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#receiveDocumentsList").setPostData(data);
		    $("#receiveDocumentsList").trigger("reloadGrid");
	}
	
	function fastSearchfun(orgId){
		var condition = $("#searchByCondition").val();
		if('请输入文件标题'== condition ) return;
		var	initParam = {
				"searchDocumentVo.title":condition,
				"searchDocumentVo.organizationId":orgId,
				"searchDocumentVo.signState":$("#signState").val()
				}	
		$("#receiveDocumentsList").setGridParam({
			url:'${path}/documents/receiveDocumentsManage/searchReceiveDocuments.action',
			datatype: "json",
			page:1
		});
		$("#receiveDocumentsList").setPostData(initParam);
		$("#receiveDocumentsList").trigger("reloadGrid");
	}
	
	

	function receiveStateFormatter(el, options, rowData){  
		if("0"==rowData.signState)
			return "未签收";
		else if("1"==rowData.signState)
			return "已签收";
		else 
			return "";
	}
	
	function signDateFormatter(el, options, rowData){  
		if(null == rowData.signDate)
			return "---";
		else 
			return rowData.signDate;
	}

	
	
	
   function operateFormatter(el, options, rowData){
	   
		return "<pop:JugePermissionTag ename='deleteReceiveDocuments'><a href='javascript:deleteReceiveDocuments("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
	}
	function titleFont(el,options,rowData){
		return "<pop:JugePermissionTag ename='readReceiveDocuments'><a href='javascript:viewReceiveDialog("+rowData.id+")'></pop:JugePermissionTag>"+rowData.title+"<pop:JugePermissionTag ename='readReceiveDocuments'></a></pop:JugePermissionTag>";
	}
	$("#receiveDocumentsList").jqGridFunction({
		datatype: "local",
		sortname: 'dispatchDate',
	    sortorder: "desc",
	   	colModel:[
	        {name:'id',label:'id',width:80,hidden:true,frozen:true,key:true},
	        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	        {name:"操作",index:'operate',width:50,formatter:operateFormatter,sortable:false,frozen:true,align:'center'},
	        {name:'documentsHasUserId',label:'documentsHasUserId',width:80,frozen:true,hidden:true},
	        {name:"title",index:'title',label:'文件标题',width:150,formatter:titleFont,frozen:true,sortable:false,align:'center'},
	        {name:'documentNo',label:'文件号',width:80,frozen:true,sortable:true},
	        {name:"dispatchUnit",label:'发文单位',width:150,sortable:true},
	        {name:"dispatchDate",label:'发文日期',width:150,align:'center',sortable:true},
	        {name:"status",label:'主送/抄送',formatter:sendTypeFormatter,width:80,align:'center'},
	        {name:'readState',label:'阅读状态',formatter:readFormatter,width:80,align:'center'},
	        {name:'createDate',index:'createDate',label:'创建时间',width:150,align:'center',hidden:true},
	        {name:'signState',label:'签收状态',width:80,formatter:receiveStateFormatter,align:'center'},
	        {name:'signDate',index:'signDate',label:'签收时间',width:150,formatter:signDateFormatter,align:'center'},
	        {name:'signer',index:'signer',label:'签收人',width:120},
	        {name:'receiptContent',index:'receiptContent',label:'签收回执',width:120}
		],
	    multiselect:true,
	    <pop:JugePermissionTag ename="viewReceiveDocuments">
	    	ondblClickRow: function(rowId){var data=$("#receiveDocumentsList").getRowData(rowId);if(viewReceiveDialog){viewReceiveDialog(data.id);}},
	    </pop:JugePermissionTag>
	    onSelectRow:selectRow
	});
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("receiveDocumentsList");
		var count = $("#receiveDocumentsList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("receiveDocumentsList", true);
		}else{
			jqGridMultiSelectState("receiveDocumentsList", false);
		}
		
	}
	loadReceiveDocList();
	$("#receiveDocumentsList").jqGrid('setFrozenColumns');
	
	
	function viewReceiveDoc(){
		var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length!=1){
			$.messageBox({message:"请选择一个文件去阅读！",level:"warn"});
	 		return;
		}else{
			var rowData=$("#receiveDocumentsList").getRowData(selectedIds);
			viewReceiveDialog(rowData.id);
		}
	}

	function certificateNoFont(el,options,rowData){
		if(rowData.logOut==1){
			return "<font color='#778899'>"+rowData.certificateNo+"</font>";
		}
		return "<font color='#000'>"+rowData.certificateNo+"</font>";
	}
	$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","");});
});

</script>

