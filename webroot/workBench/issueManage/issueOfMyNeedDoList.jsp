<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div>
	<div id="nav">
			<a id="addIssue" href="javascript:;"><span>新增</span></a>
		<a id="refreshIssue" href="javascript:void(0)"><span>刷新</span></a>
		<a id="conceptIssueInList" href="javascript:void(0)"><span>受理</span></a>
		<a id="dealIssueInList" href="javascript:void(0)"><span>办理</span></a>
	</div>
	<div style="width:99%;">
		<table id="issueListForNeed"></table>
	</div>
	<div id="issueForBenchDialog"></div>

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
	$("#issueListForNeed").workBenchGridFunction({
		datatype: "local",
		sortname:'isteps.lastdealdate', 
	   	colModel:[
		{name:'supervisionState',index:'isteps.superviselevel',label:'<img src="${resource_path}/resource/system/images/grayPieces.gif" style="vertical-align:middle" />',formatter:rendSupervise,width:40},
		{name:'urgent',index:'iu.urgent',label:'<img src="${resource_path}/resource/system/images/bang.gif" style="vertical-align:middle" />',formatter:rendUrgent,width:40},
		{name:'issueLogId',hidden:true,key:true},
		{name:'subject',index:'iu.subject',label:'事件名称'},
		{name:'occurOrg.orgName',index:'iu.occurOrg',label:'所属区域'},
		{name:'dealState',index:'isteps.statecode',label:'状态',hidden:true},
		{name:'dealTime',index:'isteps.lastdealdate',label:'录入时间'},
		{name:'issueStepId',hidden:true}
		],
		ondblClickRow:doubleClickTable,
		loadComplete:afterLoad,
		onSelectRow:selectRow
	});

	if(currentOrgId!= null){
		onOrgChanged();
	}
	function doubleClickTable(){
	    var selectedId = $("#issueListForNeed").getGridParam("selrow");
		var ent =  $("#issueListForNeed").getRowData(selectedId);
		$("#issueForBenchDialog").createDialog({
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

	
	$("#addIssue").click(function(event){
		$("#issueForBenchDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight+20,
			title:'新增我的事件',
			url:'${path}/issues/issueManage/dispatchforBench.action?mode=add&keyId='+currentOrgId,
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


	$("#refreshIssue").click(function(event){
		onOrgChanged();
	});



	$("#conceptIssueInList").click(function(event){
		if($("#conceptIssueInList").attr("disabled")=="true" || $("#conceptIssueInList").attr("disabled")=="disabled"){
			return ;
		}
		var selectedId = $("#issueListForNeed").getGridParam("selrow");
		var ent =  $("#issueListForNeed").getRowData(selectedId);
		if(selectedId==null){
	 		return;
		}
		$("#issueForBenchDialog").createDialog({
			width: 600,
			height: 550,
			title: '受理',
			url:'${path}/issue/issueManage/dispatchConceptOrDealOrRead.action?mode=conceptInList&issueLog.id='+selectedId+'&issueStepId='+ent.issueStepId,
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
	$("#dealIssueInList").click(function(event){
		if($("#dealIssueInList").attr("disabled")=="true" || $("#dealIssueInList").attr("disabled")=="disabled"){
			return ;
		}
		var selectedId = $("#issueListForNeed").getGridParam("selrow");
		var ent =  $("#issueListForNeed").getRowData(selectedId);
		if(selectedId==null){
			$.messageBox({level:'error',message:"您尚没有选中一条待办信息，请选择一条信息！"});
	 		return;
		}
		$("#issueForBenchDialog").createDialog({
			width: 900,
			height: 600,
			title: '办理',
			url:'${path}/issue/issueManage/dispatchConceptOrDealOrRead.action?mode=dealInList&issueLog.id='+selectedId+'&issueStepId='+ent.issueStepId,
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
	if(undefined == $("#issueListForNeed").attr("id")){
		return;
	}
	$("#issueListForNeed").setGridParam({
		url:'${path}/issues/issueManage/findMyNeedDo.action',
		datatype: "json"
	});
	$("#issueListForNeed").setPostData({
		"keyId":currentOrgId,
		"page":1
	});
	$("#issueListForNeed").trigger("reloadGrid");
}

function selectIssueByStepId(key){
	$("#issueListForNeed").setSelection(key);	
}
function afterLoad(){
	disableButtons();
}

function disableButtons(){
	$("#conceptIssueInList").buttonDisable();
	$("#dealIssueInList").buttonDisable();
}

function selectRow(){
	var selectedId = $("#issueListForNeed").getGridParam("selrow");
	var rowData =  $("#issueListForNeed").getRowData(selectedId);
	if( 110 == rowData.dealState){
		$("#conceptIssueInList").buttonEnable();
		$("#dealIssueInList").buttonDisable();
		}
	else{
		$("#conceptIssueInList").buttonDisable();
		$("#dealIssueInList").buttonEnable();
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
	$("#issueForBenchDialog").createDialog({
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
	var selectedId = $("#issueListForNeed").getGridParam("selrow");
	var ent =  $("#issueListForNeed").getRowData(selectedId);
	if(selectedId==null){
 		return;
	}
	$("#issueForBenchDialog").createDialog({
		width:900,
		height:600,
		title:'办理',
		url:'${path}/issue/issueManage/dispatchConceptOrDealOrRead.action?mode=dealInList&issueLog.id='+selectedId+'&issueStepId='+ent.issueStepId,
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
