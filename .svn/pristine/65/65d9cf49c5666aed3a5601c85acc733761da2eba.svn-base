<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-corner-all" id="nav" >
	<div class="btnbanner btnbannerData">
		<select id="dispatchState" style="height: 23px" >
			<option value="" >全部</option>
			<option value="unSend" selected="selected">未发</option>
			<option value="sended" >已发</option>
		</select>
		<input type="text" value="请输入文件标题" name="searchByCondition" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入文件标题':this.value;" onfocus="value=(this.value=='请输入文件标题')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 270px; cursor: pointer; outline: none;"></button>
		<a href="javascript:;" id="fastSearch"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchdispatchDocuments">
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween"></span>
		<pop:JugePermissionTag ename="adddispatchDocuments">
		<a id="add" href="javascript:void(0)" ><span>写发文</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="senddispatchDocuments">
		<a id="send" href="javascript:void(0)"><span>发文</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletedispatchDocuments">
		<a id="delete" href="javascript:void(0)"><span>批量删除</span></a>
		</pop:JugePermissionTag>
		<a id="historyDocuments"  href="javascript:void(0)"><span>历史数据查询</span></a>
		<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="synchrodispatchDocuments">
		<a id="synchrodispatchDoc" href="javascript:void(0)"><span>同步资料库</span></a>
		</pop:JugePermissionTag>
	</div>
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="dispatchDocumentsList"> </table>
	<div id="dispatchDocumentsListPager"></div>
</div>
<div id="dispatchDocumentsMaintanceDialog"></div>
<div id="scanHeaderImage"></div>
<div id="ObjDialog"></div>
<div id="copyObjDialog"></div>
<div id="readStateDialog"></div>
<div id="fileSharingDailog"></div>
<div id="selectedOrgNamesDetail"></div>
<div id="editClassifiedDialog"></div>
<div id="viewDepartmentGroupDialog"></div>
<div id="historyDocumentsDlg"></div>
<div style="display: none;">
	<span id="title">发文</span>
</div>
<script type="text/javascript">
function showSelectOrgDetail(id,level,type){
	$("#selectedOrgNamesDetail").createDialog({
		width: 650,
		height: 500,
		title:'查看选择对象的详细',
		url:PATH+'/viewObject/ajaxGetselectedOrgNamesByIdAndLevelAndType.action?id='+id+'&level='+level+'&type='+type,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
var dialogWidth=600;
var dialogHeight=480;
var title=$("#title").html();
function loadDispatchDocList(){
	$("#dispatchDocumentsList").setGridParam({
		url:'${path}/documents/dispatchDocumentsManage/dispatchDocumentsList.action',
		datatype: "json",
		page:1
	});
	$("#dispatchDocumentsList").setPostData({
		"document.dispatchState":$("#dispatchState").val()
	});
	$("#dispatchDocumentsList").trigger("reloadGrid");
}
function viewDialog(id){
	var rowData=  $("#dispatchDocumentsList").getRowData(id);
	$("#dispatchDocumentsMaintanceDialog").createDialog({
		width: 600,
		height: 300,
		title:'查看'+title+'信息',
		url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=view&document.id='+rowData.encryptId,
		buttons: {
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function viewSignInfo(selectId){
	if(selectId==null || selectId==""){
		$.messageBox({message:"请选择一条公文进行查看签收信息！",level:"warn"});
		return;
	}
	var rowData=$("#dispatchDocumentsList").getRowData(selectId);
	if(rowData.dispatchState=="未发"){
		$.messageBox({message:"请先发送公文！",level:"warn"});
		return;
	}
	$("#dispatchDocumentsMaintanceDialog").createDialog({
		width: 900,
		height: 450,
		title:'签收情况',
		url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=sign&document.id='+rowData.encryptId,
		buttons: {
			"确定" : function(){
				$(this).dialog("close");
			}
		}
	});
	
}
function deleteDispatch(event,id){
	var rowData=$("#dispatchDocumentsList").getRowData(id);
	if(rowData.dispatchState=='已发'){
		$.messageBox({message:"已发送的文件不能移除！",level:"warn"});
		return;
	}
	
	$.confirm({
		title:"确认移除",
		message:"确定要移除吗?",
		okFunc: function() {
			$.ajax({
				url:"${path}/documents/dispatchDocumentsManage/deleteDispatchDocById.action?selectedIds="+rowData.encryptId,
				success:function(data){
				if(null==data){
					$("#dispatchDocumentsList").trigger("reloadGrid");
					$.messageBox({message:"已经成功移除该发文信息!"});
				}else{
					$.messageBox({message:"发文移除失败！",level:"error"});
				}
			}
		});
	}
});
	var evt = event || window.event;
	if (window.event) {
		evt.cancelBubble=true; 
	} else { 
		evt.stopPropagation(); 
	}
}
function updateDispatch(event,id){
	var rowData=$("#dispatchDocumentsList").getRowData(id);
	if(rowData.dispatchState=='已发'){
		$.messageBox({message:"已发送的文件不能修改！",level:"warn"});
		return;
	}
	$("#dispatchDocumentsMaintanceDialog").createDialog({
		width:800,
		height:530,
		title:'修改发文',
		url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=edit&document.id='+rowData.encryptId,
		buttons: {
			"保存" : function(event){
				$("#dispatchDocumentsForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
	   		}
		}
	});
	var evt = event || window.event;
	if (window.event) { 
		evt.cancelBubble=true; 
	} else { 
		evt.stopPropagation(); 
	} 
}
$(document).ready(function(){
	$("#dispatchDocumentsList").jqGridFunction({
		datatype: "local",
		sortname: 'createDate',
		sortorder: "desc",
		colModel:[
			{name:"id",index:"id",hidden:true,sortable:false,hidedlg:true,frozen:true},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			{name:"操作",index:'id',align:'center',width:70,formatter:operateFormatter,sortable:false},
			{name:"title",index:'title',label:'文件标题',sortable:true,width:160,formatter:titleFont,frozen:true,align:'center'},
			{name:'documentNo',label:'文件号',sortable:true,width:120,frozen:true,align:'center'},
			{name:'dispatchState',label:'发文状态',width:80,formatter:dispatchStateFormatter,sortable:true,align:'center'},
			{name:"dispatchDate",label:'发文日期',sortable:true,width:140,align:'center'},
			{name:'viewSignStatus',label:'签收信息',sortable:false,formatter:detailsFormatter,width:120,align:'center'},
			{name:'createDate',index:'createDate',sortable:true,label:'创建时间',width:140,align:'center'}
		],
		multiselect:true,
		<pop:JugePermissionTag ename="viewdispatchDocuments">
		ondblClickRow:viewDialog,
		</pop:JugePermissionTag>
		onSelectRow:selectRow
	});
	loadDispatchDocList();
	jQuery("#dispatchDocumentsList").jqGrid('setFrozenColumns');
	$("#dispatchState").change(function(event){
		loadDispatchDocList();
	});
	$("#refreshSearchKey").click(function(event){
		$("#searchByCondition").attr("value","");
	});
	$("#fastSearch").click(function(event){
		fastSearchfun(getCurrentOrgId());
	});
	
	$("#historyDocuments").click(function(event){
		$("#historyDocumentsDlg").createDialog({
			width:950,
			height:550,
			title:'发公文历史数据',
			url:'${path}/daily/documentsManage/dispatchDocumentsManage/documentsHistoryList.jsp',
			buttons:{
				"关闭":function(event){
					$(this).dialog("close");
				}
			}
		});
	});
	
	$("#search").click(function(event){
		$("#dispatchDocumentsMaintanceDialog").createDialog({
			width: 640,
			height: 400,
			title:title+'查询-请输入查询条件',
			url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=search&searchDocumentVo.dispatchState='+$("#dispatchState").val(),
			buttons: {
				"查询" : function(event){
					searchDispatchDocuments();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#add").click(function(event){
		$("#dispatchDocumentsMaintanceDialog").createDialog({
			width:800,
			height:530,
			title:'写发文',
			url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=add',
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
	$("#send").click(function(event){
		var selectedIds = $("#dispatchDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null){
			$.messageBox({message:"请至少选中一个文件",level:"warn"});
			return;
			}
		
		for(var i = 0;i<selectedIds.length;i++){
			var rowData=$("#dispatchDocumentsList").getRowData(selectedIds[i]);
			if(rowData.dispatchState=='已发'){
			$.messageBox({message:"已发送的文件不能再发送！",level:"warn"});
			return;
		   }
		}	
		var allValue=deleteOperatorByEncrypt('dispatchDocuments',selectedIds,'encryptId');
			$.confirm({
				title:"确认发文",
				message:"确定要发文吗?发文之后该文件将不能再修改",
				okFunc: function() {
					  $.ajax({
						  	url:"${path}/documents/dispatchDocumentsManage/sendDocuments.action",
						  	type:'post',
						  	data:{
						  		'mode':'send',
								'selectedIds':allValue
						  		},
						 	success:function(data){
							if(null==data){
							 $("#dispatchDocumentsList").trigger("reloadGrid");
							 $.messageBox({message:"发送成功"});
							}else{
							  $.messageBox({message:data,level:"error"});
						  }
						}
					});
				}
			});
	});
	$("#delete").click(function(event){
		var selectedIds = $("#dispatchDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null ||selectedIds.length==0){
			$.messageBox({message:"请至少选择一条发文",level:"warn"});
			return;
			}
		for(var i = 0;i<selectedIds.length;i++){
			var rowData=$("#dispatchDocumentsList").getRowData(selectedIds[i]);
			if(rowData.dispatchState=='已发'){
			$.messageBox({message:"已发送的文件不能移除！",level:"warn"});
			return;
		   }
		}
		var allValue=deleteOperatorByEncrypt('dispatchDocuments',selectedIds,'encryptId');
		$.confirm({
				title:"确认移除",
				message:"确定要移除吗?",
				okFunc: function() {
					$.ajax({
					  url:"${path}/documents/dispatchDocumentsManage/deleteDispatchDocById.action",
					  type:'post',
					  data:{
						  'selectedIds':allValue
					  	},
					  success:function(data){
						if(null==data){
							$("#dispatchDocumentsList").trigger("reloadGrid");
							 $.messageBox({message:"已经成功移除该发文信息!"});
						}else{
						 $.messageBox({message:"发文移除失败！",level:"error"});
						 
						}
					}
				});
			}
		});
	});
	$("#reload").click(function(event){
		$("#searchByCondition").attr("value","请输入文件标题");
		loadDispatchDocList();
	});
	$("#synchrodispatchDoc").click(function(){
		var selectedIds = $("#dispatchDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length<1){
			return  $.messageBox({message:"请选择一条公文 ",level:"warn"});;
		}
		var rowData=  $("#dispatchDocumentsList").getRowData(selectedIds);
		
		$("#fileSharingDailog").createDialog({
			width:650,
			height:500,
			title:'同步资料库',
			url:'${path}/documents/dispatchDocumentsManage/dispatchOperateDocuments.action?mode=sharing&selectedIds='+rowData.encryptId+"&identification=docSynchro",
			buttons :{
				"保存" : function(){
					$("#fileSharing-form").submit();
					$("#dispatchDocumentsList").trigger("reloadGrid");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#view").click(function(event){
		viewDispatchDoc();
	});
	function searchDispatchDocuments(){
		setSpecialElement();
		var data=$("#searchDispatchDocForm").serializeArray();
		$("#dispatchDocumentsList").setGridParam({
			url:'${path}/documents/dispatchDocumentsManage/searchDispatchDocuments.action',
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#dispatchDocumentsList").setPostData(data);
		$("#dispatchDocumentsList").trigger("reloadGrid");
	}
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
	function fastSearchfun(orgId){
		var condition = $("#searchByCondition").val();
		if('请输入文件标题'== condition) return;
		var	initParam = {
			"searchDocumentVo.title":condition,
			"searchDocumentVo.organizationId":orgId,
			"searchDocumentVo.dispatchState":$("#dispatchState").val()
		}	
		$("#dispatchDocumentsList").setGridParam({
			url:'${path}/documents/dispatchDocumentsManage/searchDispatchDocuments.action',
			datatype: "json",
			page:1
		});
		$("#dispatchDocumentsList").setPostData(initParam);
		$("#dispatchDocumentsList").trigger("reloadGrid");
	}
	
	
	function titleFont(el,options,rowData){
		return "<pop:JugePermissionTag ename='viewdispatchDocuments'><a href='javascript:viewDialog("+rowData.id+")'></pop:JugePermissionTag>"+rowData.title+"<pop:JugePermissionTag ename='viewdispatchDocuments'></a></pop:JugePermissionTag>";
	}
	function operateFormatter(el, options, rowData){
		return "<pop:JugePermissionTag ename='updatedispatchDocuments'><a href='javascript:;' onclick='updateDispatch(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deletedispatchDocuments'><a href='javascript:;' onclick='deleteDispatch(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
	}
	function detailsFormatter(el, options, rowData){
		return "<a href='javascript:viewSignInfo("+rowData.id+")'>查看签收信息</a>";
	}
	function dispatchStateFormatter(el, options, rowData){  
		if("unSend"==rowData.dispatchState)
			return "未发";
		else 
			return "已发";
	}
	
	
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("dispatchDocumentsList");
		var count = $("#dispatchDocumentsList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("dispatchDocumentsList", true);
		}else{
			jqGridMultiSelectState("dispatchDocumentsList", false);
		}
	}
	function viewDispatchDoc(){
		var selectedIds = $("#dispatchDocumentsList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length!=1){
	 		return  $.messageBox({message:"请选择一条文件信息查看",level:"warn"});;
		}
		viewDialog(selectedIds);
	}
	function getSelectedIds(){
		var selectedIds = $("#dispatchDocumentsList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#dispatchDocumentsList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function certificateNoFont(el,options,rowData){
		if(rowData.logOut==1){
			return "<font color='#778899'>"+rowData.certificateNo+"</font>";
		}
		return "<font color='#000'>"+rowData.certificateNo+"</font>";
	}
});
</script>

