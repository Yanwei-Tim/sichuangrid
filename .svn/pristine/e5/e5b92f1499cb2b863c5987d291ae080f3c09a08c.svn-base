<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<style type="text/css">
	.ui-tabs-panel{background:#fff!important;}
	/* .todo{width:20px;height:22px;display:block;margin:0 auto;vertical-align:top;background:url(/resource/images/icon_tbmk.png) no-repeat;} */
	/* .todo.yellow-todo{background-position:0 -27px;} */
	/* .todo.red-todo{background-position:0 -54px;} */
	/* .todo.blue-todo{background-position:0 0px;} */
	/* .event-expedited .expedited{width:14px;height:22px;display:block;margin:0 auto;background:url(/resource/images/icon_tbmk.png) no-repeat -2px -90px;} */
	.myStyle{float:left;width:10%;height:17px;display:inline-block;*display:inline;*zoom:1;margin:0 0 0 10px;}
	.todo{width:22px;height:22px;display:block;margin:0 auto;vertical-align:top;}
	.todo.yellow-todo{background:url(/resource/system/images/issue/icon_yHandleForList.png) no-repeat;}
	.todo.red-todo{background:url(/resource/system/images/issue/icon_rHandleForList.png) no-repeat;}
	.todo.blue-todo{background:url(/resource/system/images/issue/icon_bHandleForList.png) no-repeat;}
	.event-expedited.expedited{width:22px;height:22px;display:block;margin:0 auto;background:url(/resource/system/images/issue/icon_EmerignceForList.png) no-repeat;}
	.dealState{width:22px;height:22px;display:block;margin:0 auto;vertical-align:top;}
	.dealState.unconcepted-state{background:url(/resource/system/images/issue/icon_unconceptedForList.png) no-repeat;}
	.dealState.unread-state{background:url(/resource/system/images/issue/icon_unreadForList.png) no-repeat;}
	.dealState.complete-state{background:url(/resource/system/images/issue/icon_bLampForList.png) no-repeat;}
	.dealState.dealing-state{background:url(/resource/system/images/issue/icon_gLampForList.png) no-repeat;}
	.dealState.earingWarn{background:url(/resource/system/images/issue/icon_yLampForList.png) no-repeat;}
	.handleTop{display: inline-block;width:15px;height:15px;background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAMAAAAMCGV4AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjVDNjkyOEM1NTk3QjExRTJBRUVBRjg3RUU5RDUzN0Q1IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjVDNjkyOEM2NTk3QjExRTJBRUVBRjg3RUU5RDUzN0Q1Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NUM2OTI4QzM1OTdCMTFFMkFFRUFGODdFRTlENTM3RDUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NUM2OTI4QzQ1OTdCMTFFMkFFRUFGODdFRTlENTM3RDUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7IOBiQAAAACVBMVEXMzMzm5ub///9vB0wNAAAAA3RSTlP//wDXyg1BAAAAMUlEQVR42mJgQgUMxPEZGVH4jAwMjEh8IBcmwADjQgUY4FyIAEQ/iIdsHrX5CAAQYACsfwFhFlU4OgAAAABJRU5ErkJggg==) no-repeat;}
</style>
<div class="center-right center-content">
	<div  style="clear: both;"></div>
	<div  style="width:99%;">
		<table id="maxIssueListForNeed"></table>
		<div id="maxIssueListForNeedPager"></div>
	</div>
	<div id="issueForBenchDialog"></div>
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
		sortname: 'issueId',
		sortorder: "desc",
		colNames:['issueId','issueStepId','publicltycass','topState','earingWarn','督办','supervisionState','预警','urgent','事件名称'
		          ,'承办情况','审核情况','反馈时间','承办部门','反馈部门','最后处理时间'],
		colModel:[
			{name:'issueId',hidden:true},
			{name:'issueStepId',hidden:true,key:true},
			{name:'publicltycass',hidden:true},
			{name:'topState',hidden:true},
			{name:'earingWarn',hidden:true},
			{name:'supervisionStateShow',index:'isteps.superviselevel',formatter:rendSupervise,width:100},
			{name:'supervisionState',index:'isteps.superviselevel',hidden:true},
			{name:'urgentShow',index:'iu.urgent',formatter:rendUrgent,width:80},
			{name:'urgent',index:'iu.urgent',hidden:true},
			{name:'subject',index:'iu.subject',formatter:subjectFormatter},
			{name:'dealState',index:'isteps.statecode',formatter:dealStateFormatter,width:60},
			{name:'delayState',index:'isteps.delayState',formatter:delayStateFormatter,width:60},
			{name:'occurDate',index:'iu.occurDate'},
			{name:'currentOrgDisplayName',index:'iu.currentOrgDisplayName'},
			{name:'occurOrg.orgName',index:'iu.occurOrg',width:200},
			{name:'dealTime',index:'cstep.lastdealdate',width:100}
		],
		ondblClickRow:doubleClickTable,
		loadComplete:afterLoad,
		onSelectRow:maxSelectRow,
		height:250,
		showColModelButton:false,
		rowNum:15
	});
	if(currentOrgId!= null){
		onOrgChanged();
	}
	function doubleClickTable(){
	    var selectedId = $("#maxIssueListForNeed").getGridParam("selrow");
		var ent =  $("#maxIssueListForNeed").getRowData(selectedId);
		$("#issueForBenchDialog").createDialog({
			width:750,
			height:500,
			title:"查看我的待办事项信息",
			url:"${path}/issues/issueManage/dispatch.action?mode=viewNew&keyId="+ent.issueId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	

});

function onOrgChanged(){
	if(undefined == $("#maxIssueListForNeed").attr("id")){
		return;
	}
	$("#maxIssueListForNeed").setGridParam({
		url:'${path}/issues/issueManage/findIssueForView.action',
		datatype: "json"
	});
	$("#maxIssueListForNeed").setPostData({
		"keyId":currentOrgId,
		"page":1,
		"viewType":"need",
		"orgLevelInternalId":"<s:property value='#getLoginOrg.orgLevel.id'/>",
		"leaderView":"1"
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
function dealIssue(issueStepId){
	if(issueStepId==null){
 		return;
	}
	$("#issueForBenchDialog").createDialog({
		width:680,
		height:510,
		title:'办理',
		url:'${path}/issues/issueManage/dispatchDeal.action?mode=deal&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function simpleDealIssue(issueStepId,dealType){
	if(issueStepId==null){
 		return;
	}
	$("#issueForBenchDialog").createDialog({
		width:350,
		height:200,
		title:'受理',
		url:'${path}/issues/issueManage/dispatchDeal.action?dealCode='+dealType+'&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function readIssue(issueStepId,dealType){
	if(issueStepId==null){
 		return;
	}
	$("#issueForBenchDialog").createDialog({
		width:350,
		height:200,
		title:'阅读',
		url:'${path}/issues/issueManage/dispatchDeal.action?dealCode='+dealType+'&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function reloadIssue(){
	
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
function operateFormatter(el, options, rowData){
	if(rowData.canDoList != null){
		for(var i=0;i<rowData.canDoList.length;i++){
			var x = rowData.canDoList[i].code;
			if(x==<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>){
        		return "<a href='javascript:void(0)' onclick=simpleDealIssue("+rowData.issueStepId+","+<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>+") class='selectWay'><span><strong class='ui-ico-shouli'></strong>受理</span></a>";
			}
			if(x==<s:property value="@com.tianque.issue.state.IssueOperate@COMMENT.code"/>){
    			return "<a href='javascript:void(0)' onclick=dealIssue("+rowData.issueStepId+") class='selectWay'><span><strong class='ui-ico-banli'></strong>办理</span></a>";
			}
			if(x==<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>){
    			return "<a href='javascript:void(0)' onclick=readIssue("+rowData.issueStepId+","+<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>+") class='selectWay'><span><strong class='ui-ico-shouli'></strong>阅读</span></a>";
			}
			return "";
		}
	}
}

function delayStateFormatter(el,options,rowData) {
	if(rowData.delayState != null){
		if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@NOT_APPLY"/>' == rowData.delayState){
			return '';
		}else if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPIYING"/>' == rowData.delayState){
			if(200==rowData.supervisionState){
				return '<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_FAILURE_NAME"/>';
			}else{
				return '<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPIYING_NAME"/>';
			}
		}else if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_PASSING"/>' == rowData.delayState){
			return '<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_PASSING_NAME"/>';
		}else if('<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_NOT_PASSING"/>' == rowData.delayState){
			return '<s:property value="@com.tianque.issue.constants.IssueApplyDelayState@APPLY_NOT_PASSING_NAME"/>';
		}
	} else {
		return "";
	}
}

function rendSupervise(el, options, rowData){	
	var earingWarnStr = "";
	if(rowData.earingWarn > 0) {
		earingWarnStr = "<div class='myStyle'><span style='color:red;'>剩" + rowData.earingWarn + "天时限</span></div>";
	}
	if(1==rowData.supervisionState || 1==el){
		return "<div class='myStyle'><strong class='todo blue-todo' title='普通督办'></strong></div>" + earingWarnStr;
	}else if(100==rowData.supervisionState){
		return "<div class='myStyle'><strong class='todo yellow-todo' title='黄牌督办'></strong></div>" + earingWarnStr;
	}else if(200==rowData.supervisionState){
		return "<div class='myStyle'><strong class='todo red-todo' title='红牌督办'></strong></div>" + earingWarnStr;
	}else{
		return "" + earingWarnStr;
	}	
}

function rendUrgent(el, options, rowData){
	var iconArray = "";
	if(200 != rowData.supervisionState && 100!=rowData.supervisionState && 1!=rowData.supervisionState){
		//不是督办督办
		if(rowData.dealState != null){
			if(500 == rowData.dealState){
				iconArray += "<div class='myStyle'><strong class='dealState complete-state' title='已处理'></strong></div>";
			}else if(800 == rowData.dealState){
				iconArray += "<div class='myStyle'><strong class='dealState complete-state' title='待验证'></strong></div>";
			}else if(1000 == rowData.dealState){
				iconArray += "<div class='myStyle'><strong class='dealState complete-state' title='已验证'></strong></div>";
			}else {
				if(rowData.earingWarn > 0) {
					if(rowData.earingWarnType == 1){
						iconArray += "<div class='myStyle'><strong class='dealState earingWarn' title='剩" + rowData.earingWarn + "天时限'></strong></div>";
					}else{
						iconArray += "<div class='myStyle'><strong class='dealState dealing-state' title='办理中,剩" + rowData.earingWarn + "天时限'></strong></div>";
					}
				}
			}
		}
	}
	//待处理和待阅读会亮灯
	if(110 == rowData.dealState){
		iconArray += "<div class='myStyle'><strong class='dealState unconcepted-state' title='待处理'></strong></div>";
	}else if(140 == rowData.dealState){
		iconArray += "<div class='myStyle'><strong class='dealState unread-state' title='待阅读'></strong></div>";
	}
	if(1==rowData.urgent || el==true){
		iconArray += "<div class='myStyle'><strong class='event-expedited expedited' title='加急'></strong></div>";
	}
	return iconArray;
}
function subjectFormatter(el,options,rowData){
	if(rowData.topState=='<s:property value="@com.tianque.issue.domain.TopIssue@TOP" />'){
		return "<div class='myStyle'><strong class='handleTop' title='置顶'></strong></div>"+rowData.subject;
	}else{
		return rowData.subject;
	}
}
</script>
