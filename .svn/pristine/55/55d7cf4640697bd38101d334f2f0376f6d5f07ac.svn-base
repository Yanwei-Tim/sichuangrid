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
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueList"></table>
		<div id="issueListPager"></div>
	</div>
	<div id="issueDialog"></div>
</div>

<script type="text/javascript">

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
			{name:'urgent',index:'iu.urgent',label:'<img src="${resource_path}/resource/system/images/bang.gif" style="vertical-align:middle" />',formatter:rendUrgent,width:40,sortable:true},
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
		]
	});

	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}

	$("#refresh").click(function(event){
		onOrgChanged();
	});

});

function onOrgChanged(){
	if(undefined == $("#issueList").attr("id")){
		return;
	}
	$("#issueList").setGridParam({
		url:'${path}/callCenter/issueManage/findDone.action?module=city',
		datatype: "json"
	});
	$("#issueList").setPostData({
		"orgId":$("#currentOrgId").val(),
		"page":1
	});
	$("#issueList").trigger("reloadGrid");
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



</script>
