<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<input id="ids" type="hidden" value="${ids }"/>

<div class="ui-corner-all" id="nav" >
	<div class="btnbanner btnbannerData">
		<pop:JugePermissionTag ename="receiveDocuments">
			<a id="receive" href="javascript:void(0)"><span>签收文件</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="readReceiveDocuments">
			<a id="read" href="javascript:void(0)"><span>阅读</span></a>
		</pop:JugePermissionTag>
   </div>
</div>

<div style="width: 100%;">
	<table id="receiveDocumentsList"> </table>
</div>
<div id="receiveDocumentsMaintanceDialog"></div>

<script type="text/javascript">
$(document).ready(function(){
	loadList();
});

function loadList(){
	$("#receiveDocumentsList").jqGridFunction({
		datatype: "json",
		height:100,
		url:'${path}/documents/receiveDocumentsManage/receiveDocumentsListByIds.action?ids='+$("#ids").val(),
	   	colModel:[
	        {name:'id',label:'id',width:80,hidden:true,key:true},
	        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	        {name:"title",index:'title',label:'文件标题',align:'center',width:150,frozen:true,sortable:false},
	        {name:'documentNo',label:'文件号',align:'center',width:80,frozen:true,sortable:true},
	        {name:"dispatchUnit",label:'发文单位',align:'center',width:150,sortable:true},
	        {name:"dispatchDate",label:'发文日期',width:150,align:'center',sortable:true},
	        {name:"status",label:'主送/抄送',formatter:sendTypeFormatter,width:80,align:'center'},
	        {name:'createDate',index:'createDate',label:'创建时间',width:150,align:'center',hidden:true},
	        {name:'signState',label:'签收状态',width:80,formatter:receiveStateFormatter,align:'center'},
	        {name:'readState',label:'阅读状态',width:80,formatter:readStateFormatter,align:'center'}
		],
		multiselect:true,
		onSelectRow:selectRow,
	    ondblClickRow: function(rowId){var data=$("#receiveDocumentsList").getRowData(rowId);if(viewReceiveDialog){viewReceiveDialog(data.id,data.signState);}}
	});
}

$("#read").click(function(event){
	viewReceiveDoc();
});
function viewReceiveDoc(){
	var selectedIds = $("#receiveDocumentsList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds==null || selectedIds.length!=1){
		$.messageBox({message:"请选择一个文件去阅读！",level:"warn"});
 		return;
	}else{
		var rowData=$("#receiveDocumentsList").getRowData(selectedIds);
		viewReceiveDialog(rowData.id,rowData.signState);
	}
}

function viewReceiveDialog(documentId,signState){
	if("notSign"==signState){
		$.messageBox({message:"签收该文件后，您才能阅读！",level:"warn"});
		return;
   	}else if("未签收"==signState){
   		$.messageBox({message:"签收该文件后，您才能阅读！",level:"warn"});
		return;
   	}
	var rowData= $("#receiveDocumentsList").getRowData(documentId);
	$("#receiveDocumentsMaintanceDialog").createDialog({
		width: 600,
		height: 260,
		title:'阅读收文信息',
		url:'${path}/documents/receiveDocumentsManage/operateReceiveDocuments.action?mode=view&document.id='+rowData.encryptId+"&readState="+rowData.readState,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

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
		width:270,
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

function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("receiveDocumentsList");
	var count = $("#receiveDocumentsList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("receiveDocumentsList", true);
	}else{
		jqGridMultiSelectState("receiveDocumentsList", false);
	}
	
}
function receiveStateFormatter(el, options, rowData){  
	if("notSign"==rowData.signState)
		return "未签收";
	else if("sign"==rowData.signState)
		return "已签收";
	else 
		return "";
}
function sendTypeFormatter(el, options, rowData){ 
	if(rowData.status=='copySend'){
		return "抄送";
	}else{
		return "主送";
	}
}
function readStateFormatter(el, options, rowData){
	if(rowData.readState=='0'){
		return "未阅读";
	}else{
		return "已阅读";
	}
}
</script>