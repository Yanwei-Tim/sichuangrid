<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	.ui-tabs-panel{background:#fff!important;}
	/* .todo{width:20px;height:22px;display:block;margin:0 auto;vertical-align:top;background:url(${resource_path}/resource/images/icon_tbmk.png) no-repeat;} */
	/* .todo.yellow-todo{background-position:0 -27px;} */
	/* .todo.red-todo{background-position:0 -54px;} */
	/* .todo.blue-todo{background-position:0 0px;} */
	/* .event-expedited .expedited{width:14px;height:22px;display:block;margin:0 auto;background:url(${resource_path}/resource/images/icon_tbmk.png) no-repeat -2px -90px;} */
	.myStyle{float:left;width:17px;height:17px;display:inline-block;*display:inline;*zoom:1;margin:0 0 0 10px;}
	.todo{width:22px;height:22px;display:block;margin:0 auto;vertical-align:top;}
	.todo.yellow-todo{background:url(${resource_path}/resource/system/images/issue/icon_yHandleForList.png) no-repeat;}
	.todo.red-todo{background:url(${resource_path}/resource/system/images/issue/icon_rHandleForList.png) no-repeat;}
	.todo.blue-todo{background:url(${resource_path}/resource/system/images/issue/icon_bHandleForList.png) no-repeat;}
	.event-expedited.expedited{width:22px;height:22px;display:block;margin:0 auto;background:url(${resource_path}/resource/system/images/issue/icon_EmerignceForList.png) no-repeat;}
	.dealState{width:22px;height:22px;display:block;margin:0 auto;vertical-align:top;}
	.dealState.unconcepted-state{background:url(${resource_path}/resource/system/images/issue/icon_unconceptedForList.png) no-repeat;}
	.dealState.unread-state{background:url(${resource_path}/resource/system/images/issue/icon_unreadForList.png) no-repeat;}
	.dealState.complete-state{background:url(${resource_path}/resource/system/images/issue/icon_bLampForList.png) no-repeat;}
	.dealState.dealing-state{background:url(${resource_path}/resource/system/images/issue/icon_gLampForList.png) no-repeat;}
	.dealState.earingWarn{background:url(${resource_path}/resource/system/images/issue/icon_yLampForList.png) no-repeat;}
	.dealState.publicltycass{background:url(${resource_path}/resource/system/images/issue/icon_publicltycassForList.png) no-repeat;}
	.handleTop{display: inline-block;width:15px;height:15px;background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA8AAAAPCAMAAAAMCGV4AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjVDNjkyOEM1NTk3QjExRTJBRUVBRjg3RUU5RDUzN0Q1IiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjVDNjkyOEM2NTk3QjExRTJBRUVBRjg3RUU5RDUzN0Q1Ij4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NUM2OTI4QzM1OTdCMTFFMkFFRUFGODdFRTlENTM3RDUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NUM2OTI4QzQ1OTdCMTFFMkFFRUFGODdFRTlENTM3RDUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz7IOBiQAAAACVBMVEXMzMzm5ub///9vB0wNAAAAA3RSTlP//wDXyg1BAAAAMUlEQVR42mJgQgUMxPEZGVH4jAwMjEh8IBcmwADjQgUY4FyIAEQ/iIdsHrX5CAAQYACsfwFhFlU4OgAAAABJRU5ErkJggg==) no-repeat;}
</style>
<% request.setAttribute("viewType",request.getParameter("viewType"));%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false"
	namespace="/sessionManage" />
<style>
	.ui-layout-center{overflow:auto;}
</style>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueList"> </table>
		<div id="issueListPager"></div>
	</div>
	<input type="hidden" id="isMyIssue" />
	<input type="hidden" id="jurisdictionsViewType" value="<s:property value='#parameters.viewType'/>"/>
	<input type="hidden" id="jurisdictionsOrgLevel" value="<s:property value='#parameters.orgLevel'/>"/>
	<input type="hidden" id="jurisdictionsKeyId" value="<s:property value='#parameters.keyId'/>"/>
	<input type="hidden" id="jurisdictionsSourceType" value="<s:property value='#parameters.sourceType'/>"/>
	<input type="hidden" id="jurisdictionsFunctionalOrgType" value="<s:property value='#parameters.functionalOrgType'/>"/>
	<input type="hidden" id="skipPageType" value="<s:property value='#parameters.skipPageType'/>"/>
	<input type="hidden" id="issueType" value="<s:property value='#parameters.issueType'/>"/>
</div>
<div class="accountType" style="overflow:auto;height:250px;" >
	<div id="gridbox12" class="SigmaReport" style="overflow-x:auto;"></div>
</div>
<script type="text/javascript">

<pop:formatterProperty name="createTableType" domainName="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_PEOPLEASPIRATION_CREATE_TABLE_TYPE" />
<pop:formatterProperty name="securityType" domainName="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_SECURITY_TYPE" />
<pop:formatterProperty name="requiredType" domainName="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_POOR_PEOPLE_SPECIFIC_NEED" />

var viewType={
		name:$("#tabList li[aria-selected='true']").text(),
		value:'<s:property value="#parameters.viewType"/>'
}

$(document).ready(function(){
	var primaryOrgStatGrid = initReportGrid("首页统计",false);
	getPrimaryOrgStat(primaryOrgStatGrid);
	
	
	var issueType = parseInt($("#issueType").val());
	if(issueType == 2){
		initLedgerPoorPeopleGrid();
	}else if(issueType == 3){
		initLedgerSteadyWorkGrid();
	}else{
		initPeopleAspirationGrid();
	}
});

function initPeopleAspirationGrid(){
	$("#issueList").jqGridFunction({
		height:300,
		datatype: "local",
		sortname: 'issueId',
		sortorder: "desc",
		colNames:['issueId','issueStepId','encryptIdByIssueId','encryptIdByIssueStepId','publicltycass','earingWarn',
		          'supervisionState','urgent','编号','反映人姓名','主要诉求','联系电话','类型'
		          ,'状态','反馈时间','createOrg.id','targetOrg.id','承办部门','反馈部门','最后处理时间'
		         ],
		colModel:[
			{name:'issueId',frozen:true,hidden:true,hidedlg:true},
			{name:'issueStepId',frozen:true,hidden:true,hidedlg:true,key:true},
			{name:"encryptIdByIssueId",index:'id',frozen:true,frozen:true,hidden:true,hidedlg:true},
			{name:"encryptIdByIssueStepId",index:'id',frozen:true,frozen:true,hidden:true,hidedlg:true},
			{name:'publicltycass',frozen:true,hidden:true,hidedlg:true},
			{name:'earingWarn',frozen:true,hidden:true,hidedlg:true},
			{name:'supervisionState',index:'superviselevel',frozen:true,hidden:true,hidedlg:true},
			{name:'urgent',index:'lpa.urgent',frozen:true,hidden:true,hidedlg:true},
			{name:'serialNumber',index:'lpa.serialNumber',sortable:true},
			{name:'name',index:'lpa.name',sortable:true},
			{name:'appealContent',index:'lpa.appealContent',sortable:false},
			{name:'mobileNumber',index:'lpa.mobileNumber',sortable:true},
			{name:'createTableType',index:'lpa.createTableType',sortable:true,formatter:createTableTypeFormatter},
			{name:'status',index:'lpa.status',frozen:true,hidden:true,hidedlg:true},
			{name:'occurDate',index:'lpa.occurDate',sortable:true},
			{name:'createOrg.id',index:'createOrg.id',frozen:true,hidden:true,hidedlg:true},
			{name:'targetOrg.id',index:'targetOrg.id',frozen:true,hidden:true,hidedlg:true},
			{name:'targetOrg.orgName',index:'targetOrg',sortable:true},
			{name:'occurOrg.orgName',index:'occurOrg',sortable:true,width:200},
			{name:'dealTime',index:'lastdealdate',sortable:true,width:100}
		],
		loadComplete: function(){
			var count=$("#issueList").getGridParam("records");
			$("#tabList li[aria-selected=true]").find(".countTip").html(count);
		},
		multiselect:false,
		showColModelButton:true
	});
	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}
}



function initLedgerPoorPeopleGrid(){
	$("#issueList").jqGridFunction({
		height:300,
		datatype: "local",
		colModel:[
			        {name:"id",index:"id",sortable:false,frozen:true,hidden:true,hidedlg:true},
			        {name:"encryptIdByIssueId",index:"encryptIdByIssueId",hidedlg:true,frozen:true,frozen:true,hidden:true,hidedlg:true},  
			        {name:"stepId",label:'stepId',index:'id',frozen:true,hidden:true,hidedlg:true}, 
			        {name:"encryptIdByIssueStepId",label:'encryptIdByIssueId',index:'id',frozen:true,hidden:true,hidedlg:true},
			        {name:'publicltycass',label:'publicltycass',frozen:true,hidden:true,hidedlg:true},
			        {name:'earingWarn',label:'earingWarn',frozen:true,hidden:true,hidedlg:true},
					{name:'supervisionState',label:'supervisionState',index:'superviselevel',frozen:true,hidden:true,hidedlg:true},
					{name:'urgent',label:'urgent',index:'lpp.urgent',frozen:true,hidden:true,hidedlg:true},
					{name:'serialNumber',label:'编号',index:'serialNumber'},
					{name:'name',label:'姓名',index:'lpp.name',sortable:true,align:'center'},
					{name:'mobileNumber',label:'联系电话',width:100,align:'center'},
					{name:'requiredType',label:'诉求',align:'center',width:140,formatter:requiredTypeFormatter},
					{name:"owner",label:'是否户主',align:'center',width:100,frozen:true,hidden:true,hidedlg:true},
			      //{name:'birthDay',label:'出生年月',width:100,align:'center'},
			        {name:'sourceKind',label:'数据来源',width:100,align:'center',frozen:true,hidden:true,hidedlg:true},
			        {name:'securityType',label:'保障类型',align:'center',width:140,formatter:securityTypeFormatter},
					{name:'createTableType',label:'建卡类型',index:'lpp.createTableType',sortable:true,formatter:createTableTypeFormatter},
					{name:'status',label:'状态',index:'lpp.status',frozen:true,hidden:true,hidedlg:true},
					{name:'occurDate',label:'反馈时间',index:'lpp.occurDate',sortable:true},
					{name:'createOrg.id',index:'createOrg.id',frozen:true,hidden:true,hidedlg:true},
					{name:'targetOrg.id',index:'targetOrg.id',frozen:true,hidden:true,hidedlg:true},
					{name:'targetOrg.orgName',label:'承办部门',index:'targetOrg',sortable:true},
					{name:'occurOrg.orgName',label:'反馈部门',index:'occurOrg',sortable:true,width:200},
					{name:'lastdealDate',label:'最后处理时间',index:'lastdealdate',sortable:true,width:100}
				],
				loadComplete: function(){
					var count=$("#issueList").getGridParam("records");
					$("#tabList li[aria-selected=true]").find(".countTip").html(count);
				},
				multiselect:false,
				showColModelButton:true
	});
	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}
}

function initLedgerSteadyWorkGrid(){
	$("#issueList").jqGridFunction({
		height:300,
		datatype: "local",
		colModel:[
			        {name:"id",index:"id",sortable:false,frozen:true,hidden:true,hidedlg:true},
			        {name:"stepId",label:'stepId',index:'id',frozen:true,hidden:true}, 
			        {name:"encryptIdByIssueId",label:'issueStepId',index:'id',frozen:true,hidden:true},  
			        {name:"encryptIdByIssueStepId",label:'encryptIdByIssueId',index:'id',frozen:true,hidden:true},
			        {name:'publicltycass',label:'publicltycass',hidden:true,frozen:true},
			        {name:'earingWarn',label:'earingWarn',frozen:true,hidden:true,hidedlg:true},
					{name:'supervisionState',label:'supervisionState',index:'superviselevel',frozen:true,hidden:true,hidedlg:true},
					{name:'urgent',label:'urgent',index:'lsw.urgent',frozen:true,hidden:true,hidedlg:true},
					{name:'serialNumber',label:'编号',index:'serialNumber'},
					{name:'name',label:'姓名',index:'lsw.name',sortable:true,align:'center'},
					{name:'mobileNumber',label:'联系电话',width:100,align:'center'},
					{name:'involvingSteadyInfo',label:'涉稳事项',align:'center',width:140},
					{name:"owner",label:'是否户主',align:'center',width:100,frozen:true,hidden:true,hidedlg:true},
			        {name:'birthDay',label:'出生年月',width:100,align:'center'},
			        {name:'sourceKind',label:'数据来源',width:100,align:'center',frozen:true,hidden:true,hidedlg:true},
					{name:'createTableType',label:'建卡类型',index:'lsw.createTableType',sortable:true,formatter:createTableTypeFormatter},
					{name:'status',label:'状态',index:'lsw.status',frozen:true,hidden:true,hidedlg:true},
					{name:'occurDate',label:'反馈时间',index:'lsw.occurDate',sortable:true},
					{name:'createOrg.id',index:'createOrg.id',frozen:true,hidden:true,hidedlg:true},
					{name:'targetOrg.id',index:'targetOrg.id',frozen:true,hidden:true,hidedlg:true},
					{name:'targetOrg.orgName',label:'承办部门',index:'targetOrg',sortable:true},
					{name:'occurOrg.orgName',label:'反馈部门',index:'occurorgID',sortable:true,width:200},
					{name:'lastdealDate',label:'最后处理时间',index:'lastdealdate',sortable:true,width:100}
				],
				loadComplete: function(){
					var count=$("#issueList").getGridParam("records");
					$("#tabList li[aria-selected=true]").find(".countTip").html(count);
				},
				multiselect:false,
				showColModelButton:true
	});
	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}
}

function onOrgChanged(isEvaluate){
	var url="";
	var issueType = $("#issueType").val();
	
	if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_WATER' />"){//水利
		url="${path}/threeRecords/water/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_TRAFFIC' />"){//交通
		url="${path}/threeRecords/traffic/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_EDUCATION' />"){//教育
		url="${path}/threeRecords/education/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_MEDICAL' />"){//医疗
		url="${path}/threeRecords/medical/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_AGRICULTURE' />"){//农业
		url="${path}/threeRecords/agriculture/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_ENERGY' />"){//能源
		url="${path}/threeRecords/energy/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_LABOR' />"){//劳动
		url="${path}/threeRecords/labor/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_ENVIRONMENT' />"){// 环境信息
		url="${path}/threeRecords/environment/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_TOWN' />"){//城乡规划建设信息
		url="${path}/threeRecords/town/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_SCIENCE' />"){//科技文体
		url="${path}/threeRecords/science/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@PEOPLEASPIRATION_OTHER' />"){//其它信息
		url="${path}/threeRecords/other/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@POORPEOPLE' />"){//困难群众
		url="${path}/threeRecordsIssue/ledgerPoorPeopleManage/findIssueForView.action";
	}else if(issueType=="<s:property value='@com.tianque.plugin.account.constants.LedgerConstants@STEADYWORK' />"){//稳定
		url="${path}/threeRecordsIssue/ledgerSteadyWorkManage/findIssueForView.action";
	}
	
	$("#issueList").setGridParam({
		url:url,
		datatype: "json"
	});
	$("#issueList").setPostData({
		"viewType":viewType.value,
		"statusType": "completed" == $("#jurisdictionsViewType").val() ? (isEvaluate == null ? $("#_statusTypeSelect").val() : isEvaluate) : '',
		"orgLevelInternalId":$("#currentOrgId").attr("orgLevelInternalId"),
		"page":1,
		"leaderView":"0",
		"type":$("#jurisdictionsIssueType").val(),
		"keyId":$("#currentOrgId").val(),
		"functionalOrgType":"<s:property value='#parameters.functionalOrgType'/>",
		"sourceTypeInternalId":"<s:property value='#parameters.sourceType'/>",
		"seachValue":"present"
	});
	
	$("#issueList").trigger("reloadGrid");
	
	queryHomePageNeedDoCount();
}

function getPrimaryOrgStat(statisticalGrid){
	$.ajax({
		url:'${path}/threeRecordsIssue/ledgerAccountReportManage/findHomePageAccountReportVo.action',
		data:{
			"accountReport.organization.id":getCurrentOrgId(),
			"accountReport.accountType":0,
			"accountReport.reportType":$("#reportType").val(),
			"accountReport.orgLevelInternalId":$("#currentOrgId").attr("orgLevelInternalId")
		},
		success:function(data){
			var dataObj = $.parseJSON(data);
			if(data == null || dataObj != null){
				var message = (data == null) ? '获取报表失败' : dataObj.message;
				$.messageBox({
					message: message,
					level: "error"
				});
			}
			
			statisticalGrid.bindData(data);
		}
	})
}

</script>