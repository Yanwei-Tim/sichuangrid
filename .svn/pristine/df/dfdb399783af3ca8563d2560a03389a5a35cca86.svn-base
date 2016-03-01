<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
 <input id="listDealOrgId" name="dealOrgId" type="hidden" value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>">
 <input id="isFlow" type="hidden" value="">
 <input id="eventSourceId" type="hidden" value="" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="userState">
				<select class="basic-input" id="eventSourceKind" name="eventSourceVo.sourceType.id">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND"  showInternalId="true"  exceptInternalIds="@com.tianque.domain.property.IssueSourceType@CALLCENTER_INPUT,@com.tianque.domain.property.IssueSourceType@MANUAL_INPUT,@com.tianque.domain.property.IssueSourceType@CITYMANAGE_INPUT"/>
	            </select>
		        <select class="basic-input" name="dealState" id="dealState">
					<option value="-1">全部</option>
					<option value="0" selected="selected">未处理</option>
					<option value="1" >已处理</option>
				</select>
				<select class="basic-input" id="eventSourceState" name="eventSourceVo.state.id" style="display: none;">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@EVENTSOURCE_STATE"/>
	            </select>
			</div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入标题关键字条件" name="searchText" id="searchText" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入标题关键字条件':this.value;" onfocus="value=(this.value=='请输入标题关键字条件')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchCommandCenterCase">
				<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
	    <a id="add" href="javascript:void(0)"><span>新增</span></a>
		<pop:JugePermissionTag ename="viewCommandCenterCase">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="NoShiftInCallCenterCase">
			<a id="NoShiftInCall" href="javascript:void(0)"><span>标记为无需转入事件</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="ShiftInCallCenterCase">
			<a id="ShiftInCall" href="javascript:void(0)"><span>转入事件</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="informationList"></table>
		<div id="informationListPager"></div>
	</div>
	<div id="informationDialog"></div>
</div>

<script type="text/javascript">

<pop:formatterProperty name="sourceType" domainName="@com.tianque.domain.property.PropertyTypes@SOURCE_KIND" />
<pop:formatterProperty name="state" domainName="@com.tianque.domain.property.PropertyTypes@EVENTSOURCE_STATE" />

var gridOption={
	colModel:[
		{name:'id', index:'id',hidden:true,frozen : true,sortable:false},
		{name:'title',index:'title',label:'标题',width:200,sortable:true},
		{name:'sourceDate',index:'sourceDate',label:'发生时间',width:100,align:'center',sortable:true,formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
		{name:'sourcePeople',index:'sourcePeople',label:'来源人',width:200,sortable:true},
		{name:'telephone',index:'telephone',label:'联系电话',width:130,align:'center',sortable:true},
		{name:'orgName',index:'orgName',label:'发生地点',width:150,sortable:false},
		{name:'sourceType',index:'sourceType',label:'来源方式',width:100,align:'center',sortable:true,formatter:sourceTypeFormatter},
    	{name:'state',index:'state',label:'事件状态',width:200,sortable:true,formatter:stateFormatter}
	]
};

function onOrgChanged(){
	$("#informationList").setGridParam({
		url:"${path}/commandCenterManage/informationList.action",
		datatype: "json"
	});
    
	if($("#searchText").val() =="请输入标题关键字条件"){
		$("#informationList").setPostData({
			"eventSourceVo.startDate":$("#startDate").val(),
			"eventSourceVo.endDate":$("#endDate").val(),
			"eventSourceVo.dealState":$("#dealState").val(),
			"eventSourceVo.sourceType.id":$("#eventSourceKind").val(),
			"eventSourceVo.state.id":$("#eventSourceState").val(),
			"eventSourceVo.sourceType.internalId":$("#eventSourceKind option:selected").attr("internalId"),
			"page":1
		});
	}else{
	$("#informationList").setPostData({
		"eventSourceVo.title":$("#searchText").val(),
		"eventSourceVo.startDate":$("#startDate").val(),
		"eventSourceVo.endDate":$("#endDate").val(),
		"eventSourceVo.dealState":$("#dealState").val(),
		"eventSourceVo.sourceType.id":$("#eventSourceKind").val(),
		"eventSourceVo.state.id":$("#eventSourceState").val(),
		"eventSourceVo.sourceType.internalId":$("#eventSourceKind option:selected").attr("internalId"),
		"page":1
	});
	
	}
	$("#informationList").trigger("reloadGrid");
}


$(document).ready(function(){
	$("#informationList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
		colNames: gridOption.colNames,
	  	multiselect:true,
		shrinkToFit:true,
		<pop:JugePermissionTag ename="viewCommandCenterCase">
			ondblClickRow:viewDialog,
		</pop:JugePermissionTag>
		showColModelButton:false
	});
	jQuery("#informationList").jqGrid('setFrozenColumns');
	onOrgChanged();
	$("#add").click(function(){
		$("#informationDialog").createDialog({
			width:650,
			height:350,
			title:'新增信息列表',
			url:"${path}/commandCenter/addEventSource.jsp",
			buttons: {
				"保存" : function(event){
				$("#maintainForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});

	});
	
	$("#search").click(function(event){
		$("#informationDialog").createDialog({
			width:650,
			height:320,
			title:'信息列表查询-请输入查询条件',
			url:"${path}/commandCenterManage/dispatch.action?mode=search",
					
			buttons: {
				"查询" : function(event){
					searchInformation();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	
	$("#NoShiftInCall").click(function(){
		var selectedIds=getSelectedIds();
		if(selectedIds==null || selectedIds==""){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var flag = true ; 
		for(var i=0;i<selectedIds.length;i++){
			var state=$("#informationList").getRowData(selectedIds[i]).state;
			if(state == "" || state == "undefined" || state == null ){
				flag =  false ;
			}
		}
		if(flag){
			$.messageBox({level:'warn',message:"没有可标记为无需转入事件的数据！"});
	 		return;
		}
		$.confirm({
			title:"确认标记为无需转入事件",
			message:"确认标记为无需转入事件",
			okFunc: function() {
				$.ajax({
					url:"${path}/commandCenterManage/updateInformationForNoShift.action?internalId=3&eventSourceSelectIds="+selectedIds,
					success:function(datas){
						
					  $.messageBox({message:"已经标记为无需转入事件!"});
					  $("#informationList").trigger("reloadGrid");
					}
				});
			}
		});
	});
	
	$("#ShiftInCall").click(function(){
		var selectedIds=getSelectedIds();
		if(selectedIds==null || selectedIds==""){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var flag = false ; 
		for(var i=0;i<selectedIds.length;i++){
			var state=$("#informationList").getRowData(selectedIds[i]).state;
			if(state == "已转入事件并分流" || state == "已转入事件但未分流" || state == "无需转入事件" ){
				flag = true ;
			}else{
				flag = false ; 
			}
		}
		if(flag){
			$.messageBox({level:'warn',message:"没有可转入事件的数据！"});
	 		return;
		}else{
			if(selectedIds.length > 1){
				$.messageBox({level:'warn',message:"每次只能把一条数据转入事件！"});
		 		return;
			}
		}
		$("#informationDialog").createDialog({
			width:800,
			height:480,
			title:'转入事件',
			url:"${path}/commandCenterManage/dispatch.action?mode=event&eventSource.id="+selectedIds+"&dealOrgId="+$("#listDealOrgId").val()+"&keyId="+$("#currentOrgId").val()+"&issue.subject=",
			buttons: {
				"确定" : function(event){
				    $("#issueMaintainForm").attr("action","${path}/commandCenterManage/addIssue.action?mode=tranevent");
					$("#issueMaintainForm").submit();
				},
				"确定并分流" : function(event){
					$("#issueMaintainForm").attr("action","${path}/commandCenterManage/addIssue.action?mode=traneventanddone");
					$("#issueMaintainForm").submit();
				}
			}
		});
	});
	
	$("#view").click(function(){
		var selectedId=getSelectedLast();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewDialog(selectedId);
	});
	
	function viewDialog(selectedId){
		$("#informationDialog").createDialog({
			width:780,
			height:300,
			title:"查看信息列表",
			url:"${path}/commandCenterManage/dispatch.action?mode=view&eventSource.id="+selectedId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	
	function searchInformation(){
		$("#informationList").setGridParam({
            url:"${path}/commandCenterManage/searchInformation.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchInformation").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
			 if (dataJson[data[i].name]) {
		      dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value; 
			} else {dataJson[data[i].name] = data[i].value;
			}
		}
		
		$("#informationList").setPostData($.extend({"eventSourceVo.sourceType.id":$("#eventSourceKind").val(),"eventSourceVo.dealState":$("#dealState").val(),"eventSourceVo.state.id":$("#eventSourceState").val(),"eventSourceVo.sourceType.internalId":$("#eventSourceKind option:selected").attr("internalId")},dataJson));
	    $("#informationList").trigger("reloadGrid");
	}

	function dealIssue(selectedId){
		if(selectedId==null){
			$.messageBox({level:'error',message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$("#issueDialog").createDialog({
			width:dialogWidthDeal,
			height:dialogHeightDeal,
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
	
	$("#eventSourceState").change(function(){
		onOrgChanged();
	});
	
	$("#eventSourceKind").change(function(){
		onOrgChanged();
		
	});
	
	$("#dealState").change(function(){
		if($("#dealState").val()!="1"){
			$("#eventSourceState").hide();	
			$("#eventSourceState").val("");
			onOrgChanged();
		}else{
			$("#eventSourceState").show();
			onOrgChanged();
		}
	});

	$("#refresh").click(function(){
		$("#searchText").attr("value","请输入标题关键字条件");
		$("#eventSourceState").hide();
		onOrgChanged();
	});
});
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入标题关键字条件");
	});

	$("#fastSearchButton").click(function(){
		onOrgChanged();
	});

	function getSelectedLast(){
		var selecedIdLast;
		var selectedIds=$("#informationList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selecedIdLast=selectedIds[i];
		}
		return selecedIdLast;
	}
	
	function getSelectedIds(){
		var selectedIds = $("#informationList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

</script>
