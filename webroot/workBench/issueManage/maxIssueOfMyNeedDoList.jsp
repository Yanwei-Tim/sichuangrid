<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-right center-content">
	<div id="nav">
			<a id="maxAddIssue" href="javascript:;"><span>新增</span></a>
		<a id="maxRefreshIssue" href="javascript:void(0)"><span>刷新</span></a>
		<a id="maxConceptIssueInList" href="javascript:void(0)"><span>受理</span></a>
		<a id="maxDealIssueInList" href="javascript:void(0)"><span>办理</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="maxIssueListForNeed"></table>
		<div id="maxIssueListForNeedPager"></div>
	</div>
	<div id="maxIssueForBenchDialog"></div>

	<div id="addPlaceAndPersonnelDialog"></div>
	<div id="addPlaceDialog"></div>
	<div id="mapDialog"></div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="sourceKind" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
var dialogWidth=780;
var dialogHeight=530;
var dialogWidthDeal=680;
var dialogHeightDeal=530;
var dialogHeightComment=400;
var superviseWidth=620;
var superviseHeight=500;

var currentOrgId= <%= ThreadVariable.getUser().getOrganization().getId()%>
$(document).ready(function(){
	jQuery("#maxIssueListForNeed").jqGridFunction({
		datatype: "local",
		sortname:'cstep.lastdealdate', 
		colModel:[
		{name:'supervisionState',index:'isteps.superviselevel',label:'<img src="${resource_path}/resource/system/images/grayPieces.gif" style="vertical-align:middle" />',formatter:rendSupervise,width:40},
		{name:'urgent',index:'iu.urgent',label:'<img src="${resource_path}/resource/system/images/bang.gif" style="vertical-align:middle" />',formatter:rendUrgent,width:40},
		{name:'issueLogId',hidden:true,key:true},
		//{name:'serialNumber',index:'iu.serialNumber',label:'服务单号',width:200},
		{name:'subject',index:'iu.subject',label:'事件名称'},
		{name:'occurOrg.orgName',index:'iu.occurOrg',label:'所属区域'},
		{name:'currentOrgDisplayName',index:'iu.currentOrgDisplayName',label:'当前处理部门'},
		{name:'occurDate',index:'iu.occurDate',label:'发生时间'},
		{name:'dealState',index:'isteps.statecode',label:'状态',formatter:dealStateFormatter},
		{name:'dealTime',index:'isteps.lastdealdate',label:'录入时间'},
		{name:'sourcePerson',index:'iu.sourcePerson',label:'来源人'},
		{name:'sourceKind',index:'iu.sourceKind',label:'来源方式',formatter:sourceKindFormatter},
		{name:'sourceMobile',index:'iu.sourceMobile',label:'来源手机'},
		{name:'issueStepId',hidden:true}
		],
		ondblClickRow:doubleClickTable,
		loadComplete:afterLoad,
		onSelectRow:maxSelectRow,
		height:500
	});

	if(currentOrgId!= null){
		onOrgChanged();
	}
	function doubleClickTable(){
	    var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
		var ent =  $("#maxIssueListForNeed").getRowData(selectedId);
		$("#maxIssueForBenchDialog").createDialog({
			width:750,
			height:500,
			title:"查看日志信息",
			url:"${path}/issues/issueManage/viewSubDetail.action?mode=view&keyId="+ent.issueStepId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	
	$("#maxAddIssue").click(function(event){
		$("#maxIssueForBenchDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight+20,
			title:'新增我的事件',
			url:'${path}/issues/issueManage/dispatchforBench.action?mode=add&DialogName=maxAdd&keyId='+currentOrgId,
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


	$("#maxRefreshIssue").click(function(event){
		onOrgChanged();
	});



	$("#maxConceptIssueInList").click(function(event){
		if($("#maxConceptIssueInList").attr("disabled")=="true" || $("#maxConceptIssueInList").attr("disabled")=="disabled"){
			return ;
		}
		var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
		var ent =  $("#maxIssueListForNeed").getRowData(selectedId);
		if(selectedId==null){
	 		return;
		}
		$("#maxIssueForBenchDialog").createDialog({
			width: 600,
			height: 550,
			title: '受理',
			url:'${path}/issue/issueManage/dispatchConceptOrDealOrRead.action?mode=conceptInList&DialogName=maxConcept&issueLog.id='+selectedId+'&issueStepId='+ent.issueStepId,
			buttons: {
				"确定" : function(event){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#maxDealIssueInList").click(function(event){
		if($("#maxDealIssueInList").attr("disabled")=="true" || $("#maxDealIssueInList").attr("disabled")=="disabled"){
			return ;
		}
		var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
		var ent =  $("#maxIssueListForNeed").getRowData(selectedId);
		if(selectedId==null){
			$.messageBox({level:'error',message:"您尚没有选中一条待办信息，请选择一条信息！"});
	 		return;
		}
		$("#maxIssueForBenchDialog").createDialog({
			width: 900,
			height: 600,
			title: '办理',
			url:'${path}/issue/issueManage/dispatchConceptOrDealOrRead.action?mode=dealInList&DialogName=maxDeal&issueLog.id='+selectedId+'&issueStepId='+ent.issueStepId,
			buttons: {
				"确定" : function(event){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

});

function onOrgChanged(){
	if(undefined == $("#maxIssueListForNeed").attr("id")){
		return;
	}
	$("#maxIssueListForNeed").setGridParam({
		url:'${path}/issues/issueManage/findMyNeedDo.action',
		datatype: "json"
	});
	$("#maxIssueListForNeed").setPostData({
		"keyId":currentOrgId,
		"page":1
	});
	$("#maxIssueListForNeed").trigger("reloadGrid");
}

function selectIssueByStepId(key){
	$("#maxIssueListForNeed").setSelection(key);	
}
function afterLoad(){
	disableButtons();
}

function disableButtons(){
	$("#maxConceptIssueInList").buttonDisable();
	$("#maxDealIssueInList").buttonDisable();
}

function maxSelectRow(){
	var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
	var rowData =  $("#maxIssueListForNeed").getRowData(selectedId);
	if( "待受理" == rowData.dealState){
		$("#maxConceptIssueInList").buttonEnable();
		$("#maxDealIssueInList").buttonDisable();
		}
	else{
		$("#maxConceptIssueInList").buttonDisable();
		$("#maxDealIssueInList").buttonEnable();
		}
}
function isLargeEvent(el, options, rowData){
	if(true == rowData.isLargeEvent){
		return "是";
	}else{
		return "否";
	}
}
function conceptIssue(selectedId){
	if(selectedId==null){
 		return;
	}
	$("#maxIssueForBenchDialog").createDialog({
		width:350,
		height:200,
		title:'受理',
		url:'${path}/issue/issueBusinessManage/dispatchConcept.action?mode=concept&issueLog.id='+selectedId,
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
function dealIssue(selectedId){
	var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
	var ent =  $("#maxIssueListForNeed").getRowData(selectedId);
	if(selectedId==null){
 		return;
	}
	$("#maxIssueForBenchDialog").createDialog({
		width:900,
		height:600,
		title:'办理',
		url:'${path}/issue/issueManage/dispatchConceptOrDealOrRead.action?mode=dealInList&DialogName=maxDeal&issueLog.id='+selectedId+'&issueStepId='+ent.issueStepId,
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

</script>
