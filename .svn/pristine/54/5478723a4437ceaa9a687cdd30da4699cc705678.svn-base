<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">

	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入事件编号或名称" id="searchByCondition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入事件编号或名称':this.value;" onfocus="value=(this.value=='请输入事件编号或名称')?'':this.value;"/>
            	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		</div>
        <span class="lineBetween"></span>

		<pop:JugePermissionTag ename="addItem">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateItem">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteItem">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="itemList"></table>
		<div id="itemListPager"></div>
	</div>
	<div id="itemDialog"></div>
</div>

<script type="text/javascript">
var dialogWidth = 780;
var dialogHeight = 630;
$(document).ready(function(){

	jQuery("#itemList").jqGridFunction({
		datatype:'local',
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true,frozen:true},
   			{name:"操作",formatter:operateFormatter,frozen:true,width:80,sortable:false,align:"center"},
			{name:'itemNumber',index:'itemNumber',sortable:true,label:'事项编号',width:150},
			{name:'itemName',index:'itemName',sortable:true,label:'事项名称',width:360}
		]
	});

	selectItem();
});

function operateFormatter(el, options, rowData){
		return '<pop:JugePermissionTag ename="viewItem"><a href="javascript:viewItem('+rowData.id+')"><U><font color="#6633FF">查看</font></U></a></pop:JugePermissionTag>';
}

function approvalItem(id){
	$("#itemDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'事项申请',
		url:'${path}/approval/approvalItemManage/dispath.action?mode=add&itemId='+id,
		buttons: {
			"保存并关闭" : function(event){
				$("#status").val("<s:property value='@com.tianque.approval.domain.property.ApprovalItemStatus@PENDING_REVIEW' />");
				$("#isSubmit").val("true");
				$("#maintainForm").submit();
				},
			"确定并分流" : function(event){
				$("#status").val("<s:property value='@com.tianque.approval.domain.property.ApprovalItemStatus@IN_PROCESS' />");
				$("#isSubmit").val("true");
				$("#maintainForm").submit();
			},
			"关闭" : function(){
	    		$(this).dialog("close");
			}
		}
	});
}

function viewItem(id){
	$("#itemDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'查看信息',
		url:'${path}/approval/itemManage/dispath.action?mode=view&id='+id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

$("#update").click(function(event){
	var selectedId = getSelectedIdLast();
	if(selectedId==null){
		$.messageBox({level:"warn",message:"没有可修改的数据！"});
		return;
	}
	$("#itemDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'修改事项',
		url:'${path}/approval/itemManage/dispath.action?mode=edit&id='+selectedId,
		buttons: {
			"保存并关闭" : function(event){
	   			$("#isSubmit").val("true");
	   			$("#maintainForm").submit();
   				},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
});

$("#view").click(function(event){
	var selectedId = getSelectedIdLast();
	if(selectedId==null){
		$.messageBox({level:"warn",message:"没有可查看的数据！"});
		return;
	}
	viewItem(selectedId);
});
$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","请输入事件编号或名称");});
$("#refresh").click(function(event){
	selectItem();
});

$("#add").click(function(event){
	$("#itemDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'新增事项',
		url:'${path}/approval/itemManage/dispath.action?mode=add',
		buttons: {
			"保存并关闭" : function(event){
	   			$("#isSubmit").val("true");
	   			$("#maintainForm").submit();
   				},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
});

$("#delete").click(function(event){
	var selectedId = getSelectedIdLast();
	if(selectedId==null){
		$.messageBox({level:"warn",message:"没有可删除的数据！"});
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				async:false,
				url:"${path}/approval/itemManage/deleteItemById.action?id="+selectedId,
				success:function(data){
					if(data == true || data == "true"){
						$("#itemList").delRowData(selectedId);
					    $.messageBox({message:"已成功删除该信息!"});
					}else{
						 $.messageBox({message:data,level: "error"});
					}
				}
			});
		}
	});
});

function selectItem(){
	$("#itemList").setGridParam({
		url:'${path}/approval/itemManage/findItemPage.action',
		datatype: "json",
		page:1
	});
	var initParam = {};
	$("#itemList").setPostData(initParam);
	$("#itemList").trigger("reloadGrid");
}

function getSelectedIdLast(){
	return $("#itemList").getGridParam("selrow");
}

$("#fastSearchButton").click(function(){
	search();
});

function search(){
	var condition = $("#searchByCondition").val();
	if(condition == '请输入事件编号或名称') return;
	var initParam = {
			"searchItem.fastSearchKeyWords":condition
		}

	$("#itemList").setGridParam({
		url:'${path}/approval/itemManage/findItemPage.action',
		datatype: "json",
		page:1
	});
	$("#itemList").setPostData(initParam);
	$("#itemList").trigger("reloadGrid");
}
</script>
