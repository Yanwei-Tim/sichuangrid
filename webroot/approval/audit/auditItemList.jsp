<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">

	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入事项编号或事项名称" id="searchByCondition" name="searchtxt" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入事项编号或事项名称':this.value;" onfocus="value=(this.value=='请输入事项编号或事项名称')?'':this.value;"/>
            	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		</div>
        <span class="lineBetween"></span>

		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="auditItemList"></table>
		<div id="auditItemListPager"></div>
	</div>
	<div id="auditItemDialog"></div>
	<div id="auditToIssueDialog"></div>
</div>

<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 640;
$(document).ready(function(){

	jQuery("#auditItemList").jqGridFunction({
		datatype:'local',
		colModel:[
			{name:"id",index:"id",sortable:false,hidden:true,frozen:true},
			{name:"操作",formatter:operateFormatter,frozen:true,width:70,sortable:false,align:"center"},
			{name:'approvalNumber',index:'approvalNumber',label:'事项编号',width:150},
			{name:'itemName',index:'itemName',label:'事项名称',sortable:false,width:150},
			{name:'name',index:'name',label:'申请人名称',sortable:true,width:80},
			{name:'idCardNo',index:'idCardNo',sortable:true,label:'身份证号码',width:150},
			{name:'currentAddress',index:'currentAddress',sortable:true,label:'常住地址',width:200}
		]
	});

	selectAuditItem();
});

function operateFormatter(el, options, rowData){
		return "<pop:JugePermissionTag ename='checkItem'><a href='javascript:auditItem("+rowData.id+")'><U><font color='#6633FF'>审核</font></U></a></pop:JugePermissionTag><pop:JugePermissionTag ename='viewItem'>|<a href='javascript:;' class='viewAuditItem' auditItemId='"+rowData.id+"'><U><font color='#6633FF'>查看</font></U></a></pop:JugePermissionTag>";
	
}
$("#auditItemList").delegate(".viewAuditItem","click",function(){
	var auditItemId = $(this).attr("auditItemId");
	viewApprovalItem(auditItemId);
});

function auditItem(id){
	$("#auditItemDialog").createDialog({
		width:550,
		height:300,
		title:'事项审核',
		url:'${path}/approval/approvalItemManage/dispath.action?mode=audit&id='+id,
		buttons: {
			"确定" : function(event){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
	    	$(this).dialog("close");
			}
		}
	});
}

function viewApprovalItem(id){
	$("#auditItemDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'查看信息',
		url:'${path}/approval/approvalItemManage/dispath.action?mode=view&id='+id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

$("#view").click(function(event){
	var selectedId = getSelectedIdLast();
	if(selectedId==null){
		$.messageBox({level:"warn",message:"没有可查看的数据！"});
		return;
	}
	viewApprovalItem(selectedId);
});

$("#refresh").click(function(event){
	$("#searchByCondition").attr("value","请输入事项编号或事项名称");
	selectAuditItem();
});
$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","请输入事项编号或事项名称");});

function selectAuditItem(){
	$("#auditItemList").setGridParam({
		url:'${path}/approval/approvalItemManage/findApprovalItemPage.action',
		datatype: "json",
		page:1
	});
	var initParam = {
		"approvalItemVo.status":"<s:property value='@com.tianque.approval.domain.property.ApprovalItemStatus@PENDING_REVIEW'/>",
		"mode":"audit"
	};
	$("#auditItemList").setPostData(initParam);
	$("#auditItemList").trigger("reloadGrid");
}

function getSelectedIdLast(){
	return $("#auditItemList").getGridParam("selrow");
}

$("#fastSearchButton").click(function(){
	auditSearch();
});

function auditSearch(){
	var condition = $("#searchByCondition").val();
	if(condition == '请输入事项编号或事项名称') return;
	var initParam = {
			"searchtxt":condition,
			"approvalItemVo.status":"<s:property value='@com.tianque.approval.domain.property.ApprovalItemStatus@PENDING_REVIEW'/>",
			"mode":"audit"
	};
	
	$("#auditItemList").setGridParam({
		url:'${path}/approval/approvalItemManage/findApprovalItemPage.action',
		datatype: "json",
		page:1
	});
	$("#auditItemList").setPostData(initParam);
	$("#auditItemList").trigger("reloadGrid");
}
</script>
