<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="center-right">
	<div class="content">
		<div style="width: 100%;">
			<table id="supervseList"></table>
			<div id="supervseListPager"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="sourceKind" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
var dialogWidth=780;
var dialogHeight=480;
var dialogWidthDeal=680;
var dialogHeightDeal=510;
var dialogHeightComment=400;
var superviseWidth=620;
var superviseHeight=500;

$(document).ready(function(){
	jQuery("#supervseList").jqSubGrid({
		datatype:'local',
		subGridPostData:function(data){return {id:data.issueLogId}},
        subGridUrl:"${path}/issue/issueManage/viewSubDetail.action?managementMode=view",
        sortname:'iu.id',
		colModel:[
			{name:'issueId',hidden:true},
			{name:'issueLogId',sortable:false,hidden:true,key:true},
			{name:'dealState',index:'il.dealState',label:' ',formatter:rendSupervise,width:40},
			{name:'urgent',index:'iu.urgent',label:' ',formatter:rendUrgent,width:40},
			{name:'issueLogId',sortable:false,hidden:true},
			//{name:'serialNumber',index:'iu.serialNumber',label:'服务单号',width:200},
			{name:'subject',index:'iu.subject',label:'事件名称'},
			{name:'dealState',index:'il.dealState',label:'状态',formatter:dealStateFormatter},
			{name:'occurDate',index:'iu.occurDate',label:'发生时间'},
			{name:'currentOrgDisplayName',index:'iu.currentOrgDisplayName',label:'当前处理部门'},
			{name:'occurOrg.orgName',index:'iu.occurOrg',label:'所属网格',width:280},
			{name:'dealTime',index:'il.dealTime',label:'最后处理时间',width:280},
			{name:'sourcePerson',index:'iu.sourcePerson',label:'来源人'},
			{name:'sourceKind',index:'iu.sourceKind',label:'来源方式',formatter:sourceKindFormatter},
			{name:'sourceMobile',index:'iu.sourceMobile',label:'来源手机'},
            {name:'supervisionState',hidden:true},
			{name:'urgent',index:'iu.urgent',hidden:true}
		]
	});
	if(getCurrentOrgId() != null && getCurrentOrgId() != ""){
		onOrgChanged();
	}

});
function rendSupervise(el, options, rowData){
	if(2004==rowData.dealState){
		return "<img src='/resource/system/images/normalcard.gif'>";
	}else if(2005==rowData.dealState){
		return "<img src='/resource/system/images/yellowcard.gif'>";
	}else if(2006==rowData.dealState){
		return "<img src='/resource/system/images/redcard.gif'>";
	}else{
		return "";
	}

}

function onOrgChanged(){
	$("#supervseList").setGridParam({
		url:'${path}/issue/issueManage/findSuperiviseIssue.action',
		datatype: "json"
	});
	$("#supervseList").setPostData({
		"organization.id":getCurrentOrgId(),
		"page":1
	});
	$("#supervseList").trigger("reloadGrid");
}


function dealStateFormatter(el, options, rowData){
	if(rowData.dealState != null){
		if(1 == rowData.dealState){
			return "待受理";
		}else if(2 == rowData.dealState){
			return "待阅读";
		}else if(101 == rowData.dealState){
			return "办理中";
		}else if(1001 == rowData.dealState){
			return "已办";
		}else if(1002 == rowData.dealState){
			return "已阅读";
		}else if(1003 == rowData.dealState){
			return "已完成";
		}else if(2006 == rowData.dealState){
			return "红牌督办";
		}else if(2005 == rowData.dealState){
			return "黄牌督办";
		}else if(2004 == rowData.dealState){
			return "普通督办";
		}
	}
}
</script>
