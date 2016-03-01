<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="userState">
				<select class="basic-input" id="sourceKind" name="sourceKind">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND"  showInternalId="true" exceptInternalIds="@com.tianque.domain.property.IssueSourceType@CALLCENTER_INPUT,@com.tianque.domain.property.IssueSourceType@MANUAL_INPUT,@com.tianque.domain.property.IssueSourceType@CITYMANAGE_INPUT"/>
	            </select>
	        </div>
	        <div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入事件名称" name="searchText" id="searchText" class="searchtxt" onblur="value=(this.value=='')?'请输入事件名称':this.value;" onfocus="value=(this.value=='请输入事件名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchCommandCenterCase">
				<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
	    </div>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="updateDoingCommandCenter">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewDoingCommandCenter">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="separateIssues">
			<a id="separateIssues" href="javascript:void(0)"><span>分流事件</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueList"></table>
		<div id="issueListPager"></div>
	</div>
	<div id="doingIssueDialog"></div>
	<div id="informationDialog"></div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="sourceKind" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
var dialogWidth=780;
var dialogHeight=550;
var dialogWidthDeal=680;
var dialogHeightDeal=460;
var currentOrgId= <%= ThreadVariable.getUser().getOrganization().getId()%>
$(document).ready(function(){
	jQuery("#issueList").jqSubGrid({
		datatype:'local',
		shrinkToFit:true,
		subGrid:false,
        sortname:'isteps.lastdealdate',
        <pop:JugePermissionTag ename="viewDoingCommandCenter">
			ondblClickRow: eventview,
		</pop:JugePermissionTag>
		colModel:[
			{name:'issueId',hidden:true,key:true,sortable:false},
			//{name:'serialNumber',index:'iu.serialNumber',label:'服务单号',width:200,sortable:true},
			{name:'subject',index:'iu.subject',width:200,label:'事件名称',sortable:true},
			{name:'occurDate',index:'iu.occurDate',width:100,label:'发生时间',align:'center',sortable:true},
			{name:'occurOrg.orgName',index:'iu.occurOrg',label:'所属网格',width:200,sortable:true},
			{name:'sourceKind',index:'iu.sourceKind',label:'来源方式',width:100,formatter:sourceKindFormatter,align:'center',sortable:true},
            {name:'supervisionState',hidden:true,sortable:true},
			{name:'urgent',index:'iu.urgent',hidden:true,sortable:true},
			{name:'issueStepId',hidden:true}
		]
	});

	if(currentOrgId!= null){
		onOrgChanged();
	}
	$("#search").click(function(event){
		var status= <s:property value="@com.tianque.issue.state.IssueState@DEALING"/>
		$("#doingIssueDialog").createDialog({
			width: 700,
			height: 400,
			title: "待办诉求查询-请输入查询条件",
			url: "${path}/commandCenterManage/dispatch.action?mode=highsearch&keyId="+$("#currentOrgId").val()+'&status='+status,
			buttons: {
		   		"查询" : function(event){
					searchIssue();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#update").click(function(){
		if (!hasRowSelected() ){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var selectedId = $("#issueList").getGridParam("selrow");
		selectedId = $("#issueList").getRowData(selectedId).issueStepId;
		updateCallCenterIssue(selectedId);
	});
	function updateCallCenterIssue(keyId){
		$("#doingIssueDialog").createDialog({
			width: 780,
			height: 480,
			title: '修改待办诉求',
			url:'${path}/commandCenterManage/dispatch.action?mode=edit&keyId='+keyId,
			buttons: {
		   		"保存" : function(event){
					$("#issueMaintainForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	}

	$("#separateIssues").click(function(){
		var selectedId = $("#issueList").getGridParam("selrow");
		selectedId = $("#issueList").getRowData(selectedId).issueStepId;
		dealIssue(selectedId);
	});
});

$("#view").click(function(){
	var selectedId = $("#issueList").getGridParam("selrow");
	if(null==selectedId||''==selectedId){
		$.messageBox({level:'warn',message:'请选择一条数据再进行操作！'});
		return;
	}
	eventview(selectedId);
});

function eventview(id){
	$("#doingIssueDialog").createDialog({
		width: 700,
		height: 400,
		title: '查看待办诉求',
		url:'${path}/commandCenterManage/dispatch.action?mode=eventview&keyId='+id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

$("#refresh").click(function(){
	onSourceKindChange();
	emptyVal();
});

$("#refreshSearchKey").click(function(){
	emptyVal();
});

$("#sourceKind").change(function(){
	onSourceKindChange();
	});
function onSourceKindChange(){
	if($("#sourceKind").val()==""){
		onOrgChanged();
	}else{
	$("#issueList").setGridParam({
		url:'${path}/commandCenter/searchCommandManage/searchCommandCenterIssue.action',
			datatype: "json"
		});
	$("#issueList").setPostData({
		"searchIssueVo.targeOrgId":currentOrgId,
		"propertyDict.id":$("#sourceKind").val(),
		"page":1
	});
	$("#issueList").trigger("reloadGrid");
	}
}

$("#fastSearchButton").click(function(){
	fastSearchfun(currentOrgId);
});

function fastSearchfun(orgId){
	if($("#searchText").val()=='请输入事件名称'){
		return ;
	}
	$("#issueList").setGridParam({
		url:'${path}/commandCenterManage/searchIssue.action',
		datatype: "json",
		page:1
	});
	$("#issueList").setPostData({
		"searchIssueVo.targeOrgId": orgId,
		"searchIssueVo.occurFrom":$("#occurFrom").val(),
		"searchIssueVo.occurEnd":$("#occurEnd").val(),
		"searchIssueVo.subject":$("#searchText").val(),
		"propertyDict.id":$("#sourceKind").val()
	});
	$("#issueList").trigger("reloadGrid");
}

function emptyVal(){
	$("#searchText").attr("value","请输入事件名称");
}
function onOrgChanged(){
	if(undefined == $("#issueList").attr("id")){
		return;
	}
	$("#issueList").setGridParam({
		url:'${path}/commandCenterManage/findMyNeedDo.action?managementMode=manage&keyId='+USER_ORG_ID,
		datatype: "json"
	});
	$("#issueList").setPostData({
		"organization.id":currentOrgId,
		"page":1
	});
	$("#issueList").trigger("reloadGrid");
}


function hasRowSelected(){
	return null !=$("#issueList").getGridParam("selrow");
}
function dealIssue(selectedId){
	if(selectedId==null){
		$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
 		return;
	}
	$("#informationDialog").createDialog({
		width:dialogWidthDeal,
		height:580,
		title:'分流事件',
		url:'${path}/commandCenterManage/dispatchDeal.action?mode=deal&keyId='+selectedId,
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


function searchIssue(){
	setConditionImportant();
	var data=$("#searchIssueForm").serializeArray();
	var searchIssueVo={};
	for(i=0;i<data.length;i++){
		 if (searchIssueVo[data[i].name]) {
           searchIssueVo[data[i].name]=searchIssueVo[data[i].name]+","+data[i].value;
		} else {
            searchIssueVo[data[i].name] = data[i].value;
		}
	}

	$("#issueList").setGridParam({
		url:'${path}/commandCenterManage/searchIssue.action',
		datatype: "json",
		page:1
	});

	$("#issueList").setPostData(data);
	$("#issueList").trigger("reloadGrid");

}

function setConditionImportant(){
	//是否重大事件
	var conditionImportant = $("#conditionImportant").val();
	if($("#conditionImportant").val()==""){
		$("#conditionImportant").attr("disabled","disabled");
	}
}


$('#occurFrom').datePicker({
	yearRange:'1900:2030',
	dateFormat:'yy-mm-dd',
	maxDate:'+0d',
		onSelect:function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#occurEnd").datepicker("option", "minDate",date);
		}else{
			$("#occurEnd").datepicker("option", "minDate",'+0d');
		}
	}
});

$('#occurEnd').datePicker({
	yearRange: '1900:2030',
	dateFormat:'yy-mm-dd',
	maxDate:'+0d',
    onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#occurFrom").datepicker("option", "maxDate",date);
		}else{
			$("#occurFrom").datepicker("option", "maxDate",'+0d');
		}
	}
});
</script>
