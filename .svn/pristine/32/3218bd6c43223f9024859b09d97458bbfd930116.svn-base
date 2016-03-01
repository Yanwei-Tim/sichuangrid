<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="ui-corner-all" id="nav" >
	<div class="btnbanner btnbannerData">
		<input type="text" value="请输入文件标题" name="searchByConditionHistory" id="searchByConditionHistory" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入文件标题':this.value;" onfocus="value=(this.value=='请输入文件标题')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 270px; cursor: pointer; outline: none;"></button>
		<a href="javascript:;" id="fastSearchHistory"><span>搜索</span></a>
		<a id="deleteHistory" href="javascript:void(0)"><span>批量删除</span></a>
		<a id="refrensh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="histroyDocumentsList"> </table>
	<div id="histroyDocumentsListPager"></div>
</div>
<script type="text/javascript">
var dialogWidth=600;
var dialogHeight=480;
function loadDispatchDocList(){
	$("#histroyDocumentsList").setGridParam({
		url:'${path}/documents/dispatchDocumentsManage/histroyDocumentsList.action',
		datatype: "json",
		page:1
	});
	$("#histroyDocumentsList").setPostData({
		"document.dispatchState":$("#dispatchState").val()
	});
	$("#histroyDocumentsList").trigger("reloadGrid");
}

$("#refrensh").click(function(){
	$("#searchByConditionHistory").attr("value","请输入文件标题");
	loadDispatchDocList();
});

$("#fastSearchHistory").click(function(){
		var documentTitle = $("#searchByConditionHistory").val();
		if(""==documentTitle || "请输入文件标题"==documentTitle){
			$.messageBox({message:"请输入文件标题",level:"warn"});
			return;
		}
		var	initParam = {
			"document.title":documentTitle
		}	
		$("#histroyDocumentsList").setGridParam({
			url:'${path}/documents/dispatchDocumentsManage/histroyDocumentsList.action',
			datatype: "json",
			page:1
		});
		$("#histroyDocumentsList").setPostData(initParam);
		$("#histroyDocumentsList").trigger("reloadGrid");
});
$(document).ready(function(){
	$("#histroyDocumentsList").jqGridFunction({
		datatype: "local",
		height:400,
		sortname: 'createDate',
		sortorder: "desc",
		colModel:[
			{name:"id",index:"id",hidden:true,sortable:false,hidedlg:true,frozen:true},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			{name:"title",index:'title',label:'文件标题',formatter:historyTitleFont,sortable:true,width:160,frozen:true,align:'center'},
			{name:'documentNo',label:'文件号',sortable:true,width:120,frozen:true,align:'center'},
			{name:'dispatchState',label:'发文状态',width:80,formatter:historyHispatchStateFormatter,sortable:true,align:'center'},
			{name:"dispatchDate",label:'发文日期',sortable:true,width:140,align:'center'},
			{name:'viewSignStatus',label:'签收信息',sortable:false,width:120,align:'center'},
			{name:'createDate',index:'createDate',sortable:true,label:'创建时间',width:140,align:'center'}
		],
		multiselect:true,
		onSelectRow:selectRow
	});
	loadDispatchDocList();
	jQuery("#histroyDocumentsList").jqGrid('setFrozenColumns');
	
	$("#deleteHistory").click(function(){
		var selectedIds = $("#histroyDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null ||selectedIds.length==0){
			$.messageBox({message:"请至少选择一条发文",level:"warn"});
			return;
			}
		for(var i = 0;i<selectedIds.length;i++){
			var rowData=$("#histroyDocumentsList").getRowData(selectedIds[i]);
			if(rowData.dispatchState=='已发'){
			$.messageBox({message:"已发送的文件不能移除！",level:"warn"});
			return;
		   }
		}
		var allValue=deleteOperatorByEncrypt('histroyDocumentsList',selectedIds,'encryptId');
		$.confirm({
				title:"确认移除",
				message:"确定要移除吗?",
				okFunc: function() {
					$.ajax({
					  url:"${path}/documents/dispatchDocumentsManage/deleteHistoryDocumentForHistory.action",
					  type:'post',
					  data:{
						  'selectedIds':allValue
					  	},
					  success:function(data){
						if(null==data){
							$("#histroyDocumentsList").trigger("reloadGrid");
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
	var rowData=  $("#histroyDocumentsList").getRowData(documentId);
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
	var selectedCounts = getActualjqGridMultiSelectCount("histroyDocumentsList");
	var count = $("#histroyDocumentsList").jqGrid("getGridParam","records");
	if(selectedCounts == count){
		jqGridMultiSelectState("histroyDocumentsList", true);
	}else{
		jqGridMultiSelectState("histroyDocumentsList", false);
	}
}

function historyTitleFont(el, options, rowData){
	return "<a href='javascript:viewHistoryDialog("+rowData.id+")'>"+rowData.title+"</a>";
}

function historyHispatchStateFormatter(el, options, rowData){
	if("unSend"==rowData.dispatchState)
		return "未发";
	else 
		return "已发";
}
</script>

