<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="ui-corner-all" id="nav" >
	<div class="btnbanner btnbannerData">
		<input type="text" value="请输入文件标题" name="searchByConditionReceiveHistory" id="searchByConditionReceiveHistory" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入文件标题':this.value;" onfocus="value=(this.value=='请输入文件标题')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 270px; cursor: pointer; outline: none;"></button>
		<a href="javascript:;" id="fastSearchReceiveHistory"><span>搜索</span></a>
		<a id="deleteReceiveHistory" href="javascript:void(0)"><span>批量删除</span></a>
		<a id="refrensh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="histroyReceiveDocumentsList"> </table>
	<div id="histroyReceiveDocumentsListPager"></div>
</div>
<script type="text/javascript">
var dialogWidth=600;
var dialogHeight=480;
function loadDispatchDocList(){
	$("#histroyReceiveDocumentsList").setGridParam({
		url:'${path}/documents/receiveDocumentsManage/receiveDocumentsListHistory.action',
		datatype: "json",
		page:1
	});
	$("#histroyReceiveDocumentsList").setPostData({
		"document.dispatchState":$("#dispatchState").val()
	});
	$("#histroyReceiveDocumentsList").trigger("reloadGrid");
}

$("#refrensh").click(function(){
	$("#searchByConditionReceiveHistory").attr("value","请输入文件标题");
	loadDispatchDocList();
});

$("#fastSearchReceiveHistory").click(function(){
		var documentTitle = $("#searchByConditionReceiveHistory").val();
		if(""==documentTitle || "请输入文件标题"==documentTitle){
			$.messageBox({message:"请输入文件标题",level:"warn"});
			return;
		}
		var	initParam = {
			"document.title":documentTitle
		}	
		$("#histroyReceiveDocumentsList").setGridParam({
			url:'${path}/documents/receiveDocumentsManage/receiveDocumentsListHistory.action',
			datatype: "json",
			page:1
		});
		$("#histroyReceiveDocumentsList").setPostData(initParam);
		$("#histroyReceiveDocumentsList").trigger("reloadGrid");
});
$(document).ready(function(){
	$("#histroyReceiveDocumentsList").jqGridFunction({
		datatype: "local",
		height:400,
		sortname: 'createDate',
		sortorder: "desc",
		colModel:[
			{name:'id',label:'id',width:80,hidden:true,key:true},
	        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	        {name:"title",index:'title',label:'文件标题',width:150,formatter:historyTitleFont,frozen:true,sortable:false},
	        {name:'documentNo',label:'文件号',width:80,frozen:true,sortable:true},
	        {name:"dispatchUnit",label:'发文单位',width:150,sortable:true},
	        {name:"dispatchDate",label:'发文日期',width:150,align:'center',sortable:true},
	        {name:"status",label:'主送/抄送',formatter:sendTypeFormatter,width:80,align:'center'},
	        {name:'readState',label:'阅读状态',formatter:readFormatter,width:80,align:'center'},
	        {name:'createDate',index:'createDate',label:'创建时间',width:150,align:'center',hidden:true},
	        {name:'signer',index:'signer',label:'签收人',width:120}
		],
		multiselect:true,
		onSelectRow:selectRow
	});
	loadDispatchDocList();
	jQuery("#histroyReceiveDocumentsList").jqGrid('setFrozenColumns');
	
	$("#deleteReceiveHistory").click(function(){
		var selectedIds = $("#histroyReceiveDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null ||selectedIds.length==0){
			$.messageBox({message:"请至少选择一条发文",level:"warn"});
			return;
		}
		$.confirm({
				title:"确认移除",
				message:"确定要移除吗?",
				okFunc: function() {
					$.ajax({
					  url:"${path}/documents/receiveDocumentsManage/deleteReceiveDocForHistory.action",
					  type:'post',
					  data:{
						  "selectedIds":selectedIds+""
					  	},
					  success:function(data){
						if(null==data){
							$("#histroyReceiveDocumentsList").trigger("reloadGrid");
							 $.messageBox({message:"已经成功移除该发文信息!"});
						}else{
						 $.messageBox({message:"发文移除失败！",level:"error"});
						 
						}
					}
				});
			}
		});
	});
	
});

function viewHistoryDialog(documentId){
	var rowData=  $("#histroyReceiveDocumentsList").getRowData(documentId);
	$("#dispatchDocumentsMaintanceDialog").createDialog({
		width: 600,
		height: 300,
		title:'查看'+title+'信息',
		url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=historyView&document.id='+rowData.encryptId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("histroyReceiveDocumentsList");
	var count = $("#histroyReceiveDocumentsList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("histroyReceiveDocumentsList", true);
	}else{
		jqGridMultiSelectState("histroyReceiveDocumentsList", false);
	}
}
function readFormatter(el, options, rowData){ 
	if(rowData.readState=='0'){
		return "未阅读";
	}else{
		return "已阅读";
	}
}
function historyTitleFont(el, options, rowData){
	return "<a href='javascript:viewHistoryDialog("+rowData.id+")'>"+rowData.title+"</a>";
}
function sendTypeFormatter(el, options, rowData){ 
	if(rowData.status=='copySend'){
		return "抄送";
	}else{
		return "主送";
	}
}
function historyHispatchStateFormatter(el, options, rowData){
	if("unSend"==rowData.dispatchState)
		return "未发";
	else 
		return "已发";
}
</script>

