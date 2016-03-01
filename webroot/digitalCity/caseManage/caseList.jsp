<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addCityCase">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateCityCase">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteCityCase">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="spuerviseCityCase">
			<a id="redCardIssue" href="javascript:void(0)"><span>督办</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueList"></table>
		<div id="issueListPager"></div>
	</div>
	<div id="issueDialog"></div>
</div>

<script type="text/javascript">
var dialogWidth=780;
var dialogHeight=530;
var dialogWidthDeal=680;
var dialogHeightDeal=510;
var dialogHeightComment=400;
var superviseWidth=620;
var superviseHeight=500;

$(document).ready(function(){
	jQuery("#issueList").jqSubGrid({
		datatype:'local',
		subGridPostData:function(data){return {id:data.issueLogId}},
        subGridUrl:"${path}/callCenter/issueManage/viewDetail.action?module=city",
        sortname:'isteps.lastdealdate',
		colModel:[
			{name:'issueLogId',hidden:true,key:true,sortable:true},
			{name:'issueId',hidden:true,sortable:true},
			{name:'supervisionState',index:'isteps.superviselevel',label:'<img src="${resource_path}/resource/system/images/grayPieces.gif" style="vertical-align:middle" />',formatter:rendSupervise,width:40,sortable:true},
			{name:'urgent',index:'iu.urgent',label:'<img src="${resource_path}/resource/system/images/bang.gif" style="vertical-align:middle" />',formatter:rendUrgent,width:40},
			//{name:'serialNumber',index:'iu.serialNumber',label:'服务单号',width:200,sortable:true},
			{name:'subject',index:'iu.subject',label:'事件名称',sortable:true},
			{name:'dealState',index:'isteps.statecode',label:'状态',formatter:dealStateFormatter,sortable:true},
			{name:'occurDate',index:'iu.occurDate',label:'发生时间',sortable:true},
			{name:'currentOrgDisplayName',index:'iu.currentOrgDisplayName',label:'当前处理部门',sortable:true},
			{name:'occurOrg.orgName',index:'iu.occurOrg',label:'所属网格',width:280,sortable:false},
			{name:'dealTime',index:'isteps.lastdealdate',label:'最后处理时间',width:280,sortable:true},
//			{name:'sourcePerson',index:'iu.sourcePerson',label:'来源人'},
//			{name:'sourceKind',index:'iu.sourceKind',label:'来源方式',formatter:sourceKindFormatter},
//			{name:'sourceMobile',index:'iu.sourceMobile',label:'来源手机'},
            {name:'supervisionState',hidden:true,sortable:true},
			{name:'urgent',index:'iu.urgent',hidden:true,sortable:true}
		],
		loadComplete:afterLoad,
		onSelectRow:selectRow
	});

	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}

	$("#add").click(function(event){
		$("#issueDialog").createDialog({
			width:600,
			height:480,
			title:'新增事件',
			url:'${path}/callCenter/issueManage/dispatch.action?mode=add&module=city&orgId='+$("#currentOrgId").val(),
			buttons: {
		   		"保存" : function(event){
		   			$("#issueMaintainForm").submit();
		   		},
   				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#delete").click(function(event){
		if($("#delete").attr("disabled")){
			return ;
		}
		var selectedId = $("#issueList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"该事件处理信息删除后,将无法恢复,您确认删除该事件处理信息吗?",
			width:400,
			okFunc:deleteIssue
		});
	});

	$("#refresh").click(function(event){
		onOrgChanged();
	});

	$("#update").click(function(event){
		if($("#update").attr("disabled")){
			return ;
		}
		var selectedId = $("#issueList").getGridParam("selrow");
		if(selectedId == null){
	 		return;
		}
		var rowData = $("#issueList").getRowData(selectedId);
		$("#issueDialog").createDialog({
			width:600,
			height:480,
			title:'修改事件',
			url:'${path}/callCenter/issueManage/dispatch.action?mode=edit&module=city&id='+rowData.issueLogId,
			buttons: {
		   		"保存" : function(event){
		   			$("#issueMaintainForm").submit();
		   		},
   				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});


	$("#redCardIssue").click(function(event){
		if($("#redCardIssue").attr("disabled")){
			return ;
		}
		redCardIssue();
	});

});

function onOrgChanged(){
	if(undefined == $("#issueList").attr("id")){
		return;
	}
	$("#issueList").setGridParam({
		url:'${path}/callCenter/issueManage/findNeedDo.action?module=city',
		datatype: "json"
	});
	$("#issueList").setPostData({
		"orgId":$("#currentOrgId").val(),
		"page":1
	});
	$("#issueList").trigger("reloadGrid");
}

function afterLoad(){
	disableButtons();
}

function disableButtons(){
	$("#update").buttonDisable();
	$("#delete").buttonDisable();
	$("#redCardIssue").buttonDisable();
}

function selectRow(){
	$("#update").buttonEnable();
	$("#delete").buttonEnable();
//	setUrgentButton("issueList","urgent","issueDialog");
	setSuperviseButtons();
	$("#printIssue").buttonEnable();
}


function dealStateFormatter(el, options, rowData){
	if(rowData.dealState != null){
		if(110 == rowData.dealState){
			return "待受理";
		}else if(140 == rowData.dealState){
			return "待阅读";
		}else if(500 == rowData.dealState){
			return "已办";
		}else if(1000 == rowData.dealState){
			return "已完成";
		}else {
			return "办理中";
		}
	}
}

function currentUserFormatter(el, options, rowData){
	if(rowData.currentUserName != null){
		return rowData.currentUserName;
	}
}



function dealIssue(selectedId){
	if(selectedId==null){
 		return;
	}
	$("#issueDialog").createDialog({
		width:dialogWidthDeal,
		height:dialogHeightDeal,
		title:'办理',
		url:'${path}/issue/issueBusinessManage/dispatchDeal.action?stepId='+selectedId,
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

function reportToIssue(selectedId){
	if(selectedId==null){
 		return;
	}
	$("#issueDialog").createDialog({
		width:350,
		height:200,
		title:'办理',
		url:'${path}/issue/issueBusinessManage/dispatchDeal.action?dealType=<s:property value="@com.tianque.issue.state.IssueOperate@REPORT_TO.code"/>&stepId='+selectedId,
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

function conceptIssue(selectedId){
	if(selectedId==null){
 		return;
	}
	$("#issueDialog").createDialog({
		width:350,
		height:200,
		title:'受理',
		url:'${path}/issue/issueBusinessManage/dispatchDeal.action?dealType=<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>&stepId='+selectedId,
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

</script>
