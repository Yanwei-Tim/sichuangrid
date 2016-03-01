<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	.ui-tabs-panel{background:#fff!important;}
	/* .todo{width:20px;height:22px;display:block;margin:0 auto;vertical-align:top;background:url(/resource/images/icon_tbmk.png) no-repeat;} */
	/* .todo.yellow-todo{background-position:0 -27px;} */
	/* .todo.red-todo{background-position:0 -54px;} */
	/* .todo.blue-todo{background-position:0 0px;} */
	/* .event-expedited .expedited{width:14px;height:22px;display:block;margin:0 auto;background:url(/resource/images/icon_tbmk.png) no-repeat -2px -90px;} */
	.myStyle{float:left;width:17px;height:17px;display:inline-block;*display:inline;*zoom:1;margin:0 0 0 10px;}
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
	.dealState.publicltycass{background:url(/resource/system/images/issue/icon_publicltycassForList.png) no-repeat;}
	.handleTop{display: inline-block;width:15px;height:15px;background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAMAAAAMCGV4AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjVDNjkyOEM1NTk3QjExRTJBRUVBRjg3RUU5RDUzN0Q1IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjVDNjkyOEM2NTk3QjExRTJBRUVBRjg3RUU5RDUzN0Q1Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NUM2OTI4QzM1OTdCMTFFMkFFRUFGODdFRTlENTM3RDUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NUM2OTI4QzQ1OTdCMTFFMkFFRUFGODdFRTlENTM3RDUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7IOBiQAAAACVBMVEXMzMzm5ub///9vB0wNAAAAA3RSTlP//wDXyg1BAAAAMUlEQVR42mJgQgUMxPEZGVH4jAwMjEh8IBcmwADjQgUY4FyIAEQ/iIdsHrX5CAAQYACsfwFhFlU4OgAAAABJRU5ErkJggg==) no-repeat;}
</style>
<% request.setAttribute("viewType",request.getParameter("viewType"));%>

<div class="content">
	<jsp:include page="${path}/fourTeamsManage/fourTeamsIssueManage/communityVolunteerServiceTeam/communityVolunteerFourTeamsIssueForViewButtons.jsp"/>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueList"> </table>
		<div id="issueListPager"></div>
	</div>
	<div id="issueDialog"></div>
	<div id="noticeDialog"></div>
	<div id="viewProcessDialog"></div>
	<div id="gradeDialog"></div>
	<input type="hidden" id="jurisdictionsViewType" value="<s:property value='#parameters.viewType'/>"/>
	<input type="hidden" id="jurisdictionsOrgLevel" value="<s:property value='#parameters.orgLevel'/>"/>
	<input type="hidden" id="jurisdictionsKeyId" value="<s:property value='#parameters.keyId'/>"/>
	<input type="hidden" id="jurisdictionsSourceType" value="<s:property value='#parameters.sourceType'/>"/>
	<input type="hidden" id="jurisdictionsFunctionalOrgType" value="<s:property value='#parameters.functionalOrgType'/>"/>
	<input type="hidden" id="_fourtemsIssueStatusType" value="">
</div>

<script type="text/javascript">
<pop:formatterProperty name="sourceKind" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
var viewType={
		name:$("#tabList li[aria-selected='true']").text(),
		value:'<s:property value="#parameters.viewType"/>'
}
$(document).ready(function(){
	function dealStateFormatter(el, options, rowData){
		if(viewType.value=='done'||viewType.value=='follow'){//对于已办事件和待跟进事件状态的处理
			if(rowData.status==1){
				return "办理中";
			}else if(rowData.status==150){
				return "待验证";				
			}else if(rowData.status==300){				
				return "已验证";				
			}
		}else if(rowData.dealState != null){
			if(110 == rowData.dealState){
				return "待处理";
			}else if(140 == rowData.dealState){
				return "待阅读";
			}else if(500 == rowData.dealState){
				return "已办";
			}else if(800 == rowData.dealState){
				return "待验证";
			}else if(1000 == rowData.dealState){
				return "已验证";
			}else {
				return "办理中";
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
			earingWarnStr = "<div class='myStyle' style='width:80'><span style='color:red;'>剩" + rowData.earingWarn + "天时限</span></div>";
		}
		if(1==rowData.supervisionState || 1==el){
			return "<div class='myStyle'><strong class='todo blue-todo' title='普通督办'></strong></div>" + earingWarnStr;
		}else if(100==rowData.supervisionState){
			return "<div class='myStyle'><strong class='todo yellow-todo' title='黄牌督办'></strong></div>" + earingWarnStr;
		}else if(200==rowData.supervisionState){
			return "<div class='myStyle'><strong class='todo red-todo' title='红牌督办'></strong></div>" + earingWarnStr;
		}else{
			return "<div class='myStyle'><strong class='todo'></strong></div>" + earingWarnStr;
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
						iconArray += "<div class='myStyle'><strong class='dealState earingWarn' title='剩" + rowData.earingWarn + "天时限'></strong></div>";
					}else{
						iconArray += "<div class='myStyle'><strong class='dealState dealing-state' title='办理中'></strong></div>";
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
		if(1 == rowData.publicltycass){
			iconArray += "<div class='myStyle'><strong class='dealState publicltycass' title='宣传事例'></strong></div>";
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
	function evaluateTypeFormatter(el,options,rowData){
		if(''==rowData.evaluateType || null==rowData.evaluateType){
			return '未验证';
		}
		return '已验证';
	}
	$("#issueList").jqGridFunction({
		datatype: "local",
		sortname: 'issueId',
		sortorder: "desc",
		colNames:['issueId','issueStepId','encryptIdByIssueId','encryptIdByIssueStepId','publicltycass','topState','earingWarn'
		          <c:if test="${!(viewType==com.tianque.issue.constants.IssueViewType.COMPLETED) && !(viewType==com.tianque.issue.constants.IssueViewType.DONE)}">,'督办'</c:if>,'supervisionState','预警','urgent','事件名称'
		          ,'状态','承办情况','审核情况','反馈时间','承办部门','反馈部门','最后处理时间'
		         //<s:if test="#request.viewType.equals(@com.tianque.issue.constants.IssueViewType@COMPLETED)">,'验证状态'</s:if>
		         ],
		colModel:[
			{name:'issueId',hidden:true},
			{name:'issueStepId',hidden:true,key:true},
			{name:"encryptIdByIssueId",index:'id',frozen:true,hidden:true},
			{name:"encryptIdByIssueStepId",index:'id',frozen:true,hidden:true},
			{name:'publicltycass',hidden:true},
			{name:'topState',hidden:true},
			{name:'earingWarn',hidden:true},
			 <c:if test="${!(viewType==com.tianque.issue.constants.IssueViewType.COMPLETED) && !(viewType==com.tianque.issue.constants.IssueViewType.DONE)}">
			{name:'supervisionStateShow',index:'superviselevel',sortable:true,formatter:rendSupervise,width:120},
			</c:if>
			{name:'supervisionState',index:'superviselevel',hidden:true},
			{name:'urgentShow',index:'iu.urgent',sortable:true,formatter:rendUrgent,width:90},
			{name:'urgent',index:'iu.urgent',hidden:true},
			{name:'subject',index:'iu.subject',sortable:true,formatter:subjectFormatter},
			{name:'status',index:'iu.status',hidden:true},
			{name:'dealState',index:'statecode',formatter:dealStateFormatter,width:60},
			{name:'delayState',index:'delayState',formatter:delayStateFormatter,width:60},
			{name:'occurDate',index:'iu.occurDate',sortable:true},
			{name:'targeOrg.orgName',index:'targetOrg',sortable:true},
			{name:'createOrg.orgName',index:'iu.createOrg',sortable:true,width:200},
			{name:'dealTime',index:'lastdealdate',sortable:true,width:100}
			 //<s:if test="#request.viewType.equals(@com.tianque.issue.constants.IssueViewType@COMPLETED)">
			 //,{name:'evaluateType',index:'evaluateType',formatter:evaluateTypeFormatter,sortable:true,width:100}
			 //s</s:if>
		],
		multiselect:false,
		showColModelButton:false,
		ondblClickRow:viewIssue,
		onSelectRow:function(){
			setTopButton();
			setUrgentButton();
			setSuperviseButtons();
		}
	});
	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}
	initButton();
});
function initButton(){
	issueButtons.show("fourTeamsEvent");
	//var isMyIssue = $("#isMyIssue").val();
	//if(isMyIssue==true||isMyIssue=="true"){
	//	issueButtons.show("my"+viewType.value);
	//}else{
	//	issueButtons.show(viewType.value);
	//}
}

function viewIssue(){
	if (hasRowSelected() && $("#view").isButtonEnabled()){
		var selectedIssue = getSelectedData();
		var statusType = $("#_fourtemsIssueStatusType").val();
		$("#issueDialog").createDialog({
			width: 880,
			height: 500,
			title: "查看事件详情",
			url: "${path}/issues/issueManage/dispatch.action?mode=viewNew&keyId="+selectedIssue.encryptIdByIssueId+"&statusType="+statusType,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}else{
		$.messageBox({level:'warn',message:"没有可查看的数据！"});
		return;
	}
}

function setTopButton(){
	var selectedIssue = getSelectedData();
	if(selectedIssue.topState=='<s:property value="@com.tianque.issue.domain.TopIssue@TOP" />'){
		$("#topIssue").html("<span>取消置顶</span>");
	}else{
		$("#topIssue").html("<span>置顶</span>");
	}
}

function setUrgentButton(){
	var selectedIssue = getSelectedData();
	if(selectedIssue.urgent=='1'){
		$("#urgent").html('<span>取消加急</span>');
		
	}else{
		$("#urgent").html('<span>加急</span>');
	}
}

function onStatusChanged(statusType){
	$("#_fourtemsIssueStatusType").val(statusType);
	onOrgChanged(statusType);
}

function onOrgChanged(isEvaluate){
	var statusType = $("#_fourtemsIssueStatusType").val();
	if(undefined==$("#issueList").attr("id")){return;}
	$("#issueList").setGridParam({
		url:'${path}/fourTeamsIssueManage/findIssueForView.action',
		datatype: "json"
	});
	$("#issueList").setPostData({
		"viewType":viewType.value,
		"orgLevelInternalId":$("#currentOrgId").attr("orgLevelInternalId"),
		"page":1,
		"leaderView":"1",
		"type":$("#jurisdictionsIssueType").val(),
		"keyId":$("#currentOrgId").attr("value"),
		"functionalOrgType":"<s:property value='#parameters.functionalOrgType'/>",
		"sourceTypeInternalId":"<s:property value='#parameters.sourceType'/>",
		"fourTeamsType":"<s:property value='@com.tianque.domain.property.FourTeamsType@COMMUNITYVOLUNTEERSERVICETEAM'/>",
		"seachValue":$("#seachValue").val(),
		"statusType":statusType
	});
	$("#issueList").trigger("reloadGrid");
}


function reloadIssue(){
	onOrgChanged();
}

function hasRowSelected(){
	if(null != $("#issueList").getGridParam("selrow")){
		return true;
	}
	return false;
}
function getSelectedId(){
	return $("#issueList").getGridParam("selrow");
}
function getSelectedData(){
	var selectedId = $("#issueList").getGridParam("selrow"); 
	return $("#issueList").getRowData(selectedId);
}
function getTypeDescById(indexs,id){
	for (var index=0;index<indexs.length;index++){
		if (indexs[index]==id) return index;
	}
	return indexs.length;
}
</script>