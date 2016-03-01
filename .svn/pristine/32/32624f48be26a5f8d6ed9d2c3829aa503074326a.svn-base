<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="userState">       
				<select id="stateKind" class="basic-input">
					<option value="-1" selected="selected">解决情况</option>
					<option value="300">已解决</option>
					<option value="1">未解决</option>
				</select>
		    </div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" class="searchtxt" value="请输入事件名称" name="searchText" id="searchText" 
					onblur="value=(this.value=='')?'请输入事件名称':this.value;"
					onfocus="value=(this.value=='请输入事件名称')?'':this.value;" />
				<button id="refreshSearchKey-done" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearch"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchCommandCenterCase">
				<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="viewDoneCommandCenter">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			</pop:JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="doneIssueList"></table>
		<div id="doneIssueListPager"></div>
	</div>
	<div id="doneIssueListlog"></div>
	<div id="doneIssueDialog"></div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="sourceKind" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
var dialogWidth=780;
var dialogHeight=530;
var dialogWidthDeal=680;
var dialogHeightDeal=510;
var dialogHeightComment=400;
var superviseWidth=620;
var superviseHeight=500;

var currentOrgId= <%= ThreadVariable.getUser().getOrganization().getId()%>
$(document).ready(function(){
	jQuery("#doneIssueList").jqSubGrid({
		datatype:'local',
		subGrid:false,
		<pop:JugePermissionTag ename="viewDoneCommandCenter">
			ondblClickRow: ondbclickrow,
		</pop:JugePermissionTag>
		colModel:[
			{name:'issueId',hidden:true,key:true,sortable:false},
			//{name:'serialNumber',index:'iu.serialNumber',label:'服务单号',width:200,sortable:false},
			{name:'subject',index:'iu.subject',width:200,label:'事件名称',sortable:false},
			{name:'occurDate',index:'iu.occurDate',width:100,label:'发生时间',align:'center',sortable:false},
			{name:'occurOrg.orgName',index:'iu.occurOrg',label:'所属网格',width:200,sortable:false},
			{name:'sourceKind',index:'iu.sourceKind',sortable:false,label:'来源方式',width:100,formatter:sourceKindFormatter,align:'center'},
            {name:'supervisionState',hidden:true,sortable:false},
			{name:'urgent',index:'iu.urgent',hidden:true,sortable:false}
		]
	});

	if(currentOrgId!= null){
		onOrgChanged();
	}
	
	$("#refresh").click(function(event){
		onOrgChanged();
		emptyVal();
	});
});
function onOrgChanged(){
	if(undefined == $("#doneIssueList").attr("id")){
		return;
	}
	var stateKind = $("#stateKind").val();
	$("#doneIssueList").setGridParam({
		url:'${path}/commandCenterManage/findMyDone.action?managementMode=manage&stateKind='+stateKind+'&keyId='+USER_ORG_ID,
		datatype: "json"
	});
	$("#doneIssueList").setPostData({
		"organization.id":currentOrgId,
		"page":1
	});
	$("#doneIssueList").trigger("reloadGrid");
}

$("#stateKind").change(function(){
	onOrgChanged(currentOrgId);
});
$("#fastSearch").click(function(){
	fastSearchfun(currentOrgId);
});
$("#refreshSearchKey-done").click(function(){
	emptyVal();
});
function fastSearchfun(orgId){
	if($("#searchText").val()=='请输入事件名称'){
		$("#searchText").val("");
		}
	$("#doneIssueList").setGridParam({
		url:'${path}/commandCenterManage/searchDoneIssues.action',
		datatype: "json",
		page:1
	});
	$("#doneIssueList").setPostData({
		"searchIssueVo.targeOrgId": orgId,
		"searchIssueVo.occurFrom":$("#occurFrom").val(),
		"searchIssueVo.occurEnd":$("#occurEnd").val(),
		"searchIssueVo.subject":$("#searchText").val(),
		"searchIssueVo.status":$("#stateKind").val()
	});
	$("#doneIssueList").trigger("reloadGrid");
}

$("#view").click(function(){
	var selectedId = $("#doneIssueList").getGridParam("selrow");
	if(null==selectedId||''==selectedId){
		$.messageBox({level:'warn',message:'请选择一条数据再进行操作！'});
		return;
	}
	ondbclickrow(selectedId);
});

function ondbclickrow(id){
	$("#doingIssueDialog").createDialog({
		width: 700,
		height: 400,
		title: '查看已办诉求',
		url:'${path}/commandCenterManage/dispatch.action?mode=eventview&keyId='+id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function emptyVal(){
	$("#searchText").attr("value","请输入事件名称");
}

$("#search").click(function(event){
	$("#doneIssueListlog").createDialog({
		width: 700,
		height: 400,
		title: "已办诉求查询-请输入查询条件",
		url: "${path}/commandCenterManage/dispatch.action?mode=highsearch&keyId="+$("#currentOrgId").val()+'&status='+status,
		buttons: {
	   		"查询" : function(event){
				searchDoneIssues();
	        	$(this).dialog("close");
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
});


function searchDoneIssues(){
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

	$("#doneIssueList").setGridParam({
		url:'${path}/commandCenterManage/searchDoneIssues.action',
		datatype: "json",
		page:1
	});

	$("#doneIssueList").setPostData(data);
	$("#doneIssueList").trigger("reloadGrid");
}

function setConditionImportant(){
	//是否重大事件
	var conditionImportant = $("#conditionImportant").val();
	if($("#conditionImportant").val()==""){
		$("#conditionImportant").attr("disabled","disabled");
	}
}

function dealStateFormatter(el, options, rowData){
	if(rowData.status != null){
		if(1 == rowData.status){
			return "办理中";
		}else if(250 == rowData.status){
			return "关闭";
		}else if(200 == rowData.status){
			return "已处理";
		}else if(300 == rowData.status){
			return "已完成";
		}else {
			return "办理中";
		}
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
