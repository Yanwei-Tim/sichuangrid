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
        <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="itemList"></table>
		<div id="itemListPager"></div>
	</div>
	<div id="itemDialog"></div>
	<div id="itemToIssueDlg"></div>
</div>

<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 640;
$(document).ready(function(){

	jQuery("#itemList").jqGridFunction({
		datatype:'local',
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true,frozen:true},
   			{name:"操作",formatter:operateFormatter,width:80,sortable:false,align:"center"},
			{name:'itemNumber',index:'itemNumber',sortable:true,label:'事项编号',width:150},
			{name:'itemName',index:'itemName',sortable:true,label:'事项名称',width:360}
		]
	});

	selectItem();
	
});

function operateFormatter(el, options, rowData){
	
		return '<pop:JugePermissionTag ename="applyItem"><a href="javascript:approvalItem('+rowData.id+')"><U><font color="#6633FF">申请</font></U></a>|</pop:JugePermissionTag><pop:JugePermissionTag ename="viewItemApply"><a href="javascript:viewItem('+rowData.id+')"><U><font color="#6633FF">查看</font></U></a></pop:JugePermissionTag>';
	
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
function approvalItem(id){
	$("#itemDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'事项申请',
		url:'${path}/approval/approvalItemManage/dispath.action?mode=add&itemId='+id,
		buttons: {
			"保存并关闭" : function(event){
				$("#status").val('<s:property value="@com.tianque.approval.domain.property.ApprovalItemStatus@PENDING_REVIEW"/>');
				$("#maintainForm").submit();
			},
			"转入事件" : function(event){
				$("#status").val('<s:property value="@com.tianque.approval.domain.property.ApprovalItemStatus@IN_PROCESS"/>');
				$("#maintainForm").submit();
			},
			"关闭" : function(){
	    	$(this).dialog("close");
			}
		}
	});
}
$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","请输入事件编号或名称");});
$("#refresh").click(function(event){
	selectItem();
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
