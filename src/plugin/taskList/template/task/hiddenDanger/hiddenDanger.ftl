<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<@s.include value="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">HiddenDanger</@s.param>
</@s.include>
<script type="text/javascript">
	<@s.set var="type" value="#parameters.type[0].substring(0,1).toUpperCase()+#parameters.type[0].substring(1)" />
</script>
<style>
    .greenLimitWarn {
        width: 22px;
        height: 22px;
        display: block;
        margin: 0 auto;
        vertical-align: top;
        background: url(/resource/system/images/issue/icon_gLamp.png) no-repeat;
    }
    .yellowLimitWarn {
        width: 22px;
        height: 22px;
        display: block;
        margin: 0 auto;
        vertical-align: top;
        background: url(/resource/system/images/issue/icon_yLamp.png) no-repeat;
    }
    .redLimitWarn {
        width: 22px;
        height: 22px;
        display: block;
        margin: 0 auto;
        vertical-align: top;
        background: url(/resource/system/images/issue/icon_rLamp.png) no-repeat;
    }
</style>
<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			
	<div class="btnbanner">
		 <#-- <@s.include value="/common/orgSelectedComponent.jsp"/>-->
		<@s.include value="/common/orgSelectedTaskListComponent.jsp"/>
	</div>
	

		<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入地点" id="searchByCondition" maxlength="18" style="width: 150px;" onblur="value=(this.value=='')?'请输入地点':this.value;" onfocus="value=(this.value=='请输入地点')?'':this.value;">
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<@pop.JugePermissionTag ename="searchHiddenDanger${(type)!}">
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="searchHiddenDanger${(type)!}">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</@pop.JugePermissionTag>
        <span class="lineBetween"></span>
   		<@pop.JugePermissionTag ename="addHiddenDanger${(type)!}">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</@pop.JugePermissionTag>
		
		
		
		<@pop.JugePermissionTag ename="viewHiddenDanger${(type)!}">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</@pop.JugePermissionTag>
	
		<@pop.JugePermissionTag ename="deleteHiddenDanger${(type)!}">
		<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</@pop.JugePermissionTag>
		
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		
	
		<input id="exceptionType" type="hidden" name="type" value="<@s.property value='#parameters.type[0]' />"/>
	</div>


	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="hiddenDangerList"> </table>
		<div id="hiddenDangerListPager"> </div>
	</div>
	<div id="hiddenDangeDialog"> </div>
	<div id="hiddenDangerMaintanceDialog"> </div>
	<div id="scanHeaderImage"> </div>
<div id="addTaskListReplyDlg"></div>
<script type="text/javascript">
var dialogWidth=700;
var dialogHeight=480;
var title="发现治安隐患信息";

<@pop.formatterProperty name="exceptionType" domainName="@com.tianque.domain.property.PropertyTypes@DANGER_EXCEPTION_TYPE" />

function operatorFormatter(el,options,rowData){
	return "<@pop.JugePermissionTag ename='updateHiddenDanger${(type)!}'><a href='javascript:updateHiddenDanger(event,"+rowData.id+")'><span>修改</span></a> | </@pop.JugePermissionTag><@pop.JugePermissionTag ename='deleteHiddenDanger${(type)!}'><a href='javascript:deleteOperator(event,"+rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>";
}

function nameFont(el,options,rowData){
		return "<@pop.JugePermissionTag ename='viewHiddenDanger${(type)!}'><a href='javascript:viewHiddenDanger("+rowData.id+")'></@pop.JugePermissionTag>"+rowData.name+"<@pop.JugePermissionTag ename='viewHiddenDanger${(type)!}'></a></@pop.JugePermissionTag>";
}

function isHandleFormatter(el,options,rowData){
	var flag = "<@pop.JugePermissionTag ename='signHiddenDanger${(type)!}'>true</@pop.JugePermissionTag>";
	if(0==rowData.ishandle && flag == 'true'){
		return "<a href='javascript:;' onclick='updateIsHandle(event,"+rowData.id+")'><span style='color:#ff0000;'>签收</span></a>";
	}else if(0==rowData.ishandle && flag == ''){
		return "<span>否</span>";
	}else {
		return "<span>是</span>";
	}
}
function addTaskListReplyFormatter(el, options, rowData){
	var flag = '<@pop.JugePermissionTag ename="replyHiddenDanger${(type)!}">true</@pop.JugePermissionTag>';
	if(rowData.ishandle == 1 && flag == 'true'){
		if(rowData.hasReplay==1){
			return "<a href='javascript:' onclick='addTaskListReply("+rowData.id+")'><span style='color:#999999;'>已回复</span></a>";
		}
		return "<a href='javascript:' onclick='addTaskListReply("+rowData.id+")'><span style='color:#ff0000;'>回复</span></a>";
	}else if(rowData.hasReplay == 0 && flag != 'true'){
		return "否";
	}
	if(rowData.hasReplay == 1){
		return "是";
	}
	return "";
}

function onOrgChanged(orgId, isgrid){
    getTaskListTimeStandardByItemName();
	var initParam = {
	    "hiddenDangerVo.organization.id":selectConfigTaskOrg(),
	    "exceptionType":$("#exceptionType").val()
	}
	if(isConfigTaskSelect()){
		$.extend(initParam,{"hiddenDangerVo.mode":"gridConfigTask","hiddenDangerVo.funOrgId": $("#funOrgId").val()})
	}
	$("#hiddenDangerList").setGridParam({
		url:"${path}/baseInfo/hiddenDangerManage/searchHiddenDangerByVo.action",
		datatype:'json',
		page:1
	});
	
	$("#hiddenDangerList").setPostData(initParam);
	$("#hiddenDangerList").trigger("reloadGrid");
}

function afterLoad(){
	onOrgChanged(selectConfigTaskOrg(),isConfigTaskGrid());
}

function deleteOperator(event,selectedId){

	$.confirm({
		title:"确认删除该"+title,
		message:"该"+title+"删除后，将不可恢复，您确定要删除该"+title+"吗？",
		width: 400,
		okFunc: function(){deleteHiddenDanger(selectedId);}
	});
	var evt = event || window.event;
	if (window.event) {
	evt.cancelBubble=true;
	} else {
	evt.stopPropagation();
	}
}

function deleteHiddenDanger(allValue) {
	$.ajax({
		url : "${path}/baseInfo/hiddenDangerManage/deleteHiddenDanger.action?ids="+allValue,
		success : function(data) {
			
			afterLoad();
		}
	});
}

function refreshList(searchText){
    getTaskListTimeStandardByItemName();
	$("#hiddenDangerList").setGridParam({
		url:"${path}/baseInfo/hiddenDangerManage/searchHiddenDangerByVo.action",
		datatype: "json",
		page:1
	});
	var postData={
		"hiddenDangerVo.organization.id":selectConfigTaskOrg(),
		"exceptionType":$("#exceptionType").val()
	};
	if(isConfigTaskSelect()){
		$.extend(postData,{"hiddenDangerVo.mode":"gridConfigTask","hiddenDangerVo.funOrgId": $("#funOrgId").val()})
	}
	$("#hiddenDangerList").setPostData(postData);
	$("#hiddenDangerList").trigger("reloadGrid");
}


function updateIsHandle(event,selectedId){
	$("#hiddenDangerDialog").createDialog({
		width: 700,
		height: 500,
		title: '签收',
		url:'${path}/baseInfo/hiddenDangerManage/updatePage.action?id='+selectedId,
		buttons: {
			"签收" : function(){
				$("#maintainFormForHiddenDanger").submit();
				
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
	var evt = event || window.event;
	if (window.event) {
	evt.cancelBubble=true;
	} else {
	evt.stopPropagation();
	}
}

function updateHiddenDanger(event,selectedId){
	$("#hiddenDangerDialog").createDialog({
		width:800,
		height:500,
		title:'修改信息',
		url:'${path}/baseInfo/hiddenDangerManage/updatePageForAll.action?id='+selectedId,
		buttons: {
			"保存" : function(){
				$("#maintainFormForhiddenDangerAdd").submit();
			
			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function viewHiddenDanger(rowId) {
	if (rowId == null) {
		return;
	}
	var id=$('#hiddenDangerList').getRowData(rowId).id;
	$("#hiddenDangerDialog").createDialog({
		width: 800,
		height: 500,
		title : '治安隐患信息详情' ,
		url : '${path}/baseInfo/hiddenDangerManage/viewHiddenDanger.action?hiddenDanger.id=' + id,
		buttons : {
			"关闭" : function() {
				$(this).dialog("close");
			}
		}
	});
}

function fastSearch(orgId){
	var condition = $("#searchByCondition").val();

	if(condition == '请输入地点') return;
	var initParam = {
	"hiddenDangerVo.organization.id":orgId,
	"hiddenDangerVo.fastSearchKeyWords":condition,
	"exceptionType":$("#exceptionType").val()
	}
	if(isConfigTaskSelect()){
		$.extend(initParam,{"hiddenDangerVo.mode":"gridConfigTask","hiddenDangerVo.funOrgId": $("#funOrgId").val()})
	}
	$("#hiddenDangerList").setGridParam({
		url:'${path}/baseInfo/hiddenDangerManage/searchHiddenDangerByVo.action',
		datatype: "json",
		page:1
	});
	$("#hiddenDangerList").setPostData(initParam);
	$("#hiddenDangerList").trigger("reloadGrid");
}

function searchHiddenDanger() {

	var conditionExceptionType = $("#conditionExceptionType").val();
	var conditionSituation = $("#conditionSituation").val();
	var conditionStartDate = $("#conditionStartDate").val();
	var conditionEndDate = $("#conditionEndDate").val();
	var conditionAddress = $("#conditionAddress").val();
	var conditionCellName = $("#conditionCellName").val();
	var conditionSignName =  $("#conditionSignName").val();
	var conditionStartSign =  $("#conditionStartSign").val();
	var conditionEndSign =  $("#conditionEndSign").val();
	var conditionSign =  $("#conditionSign").val();
	var hasReplay =  $("#hasReplay").val();
	
	
	var initParam = {
		"hiddenDangerVo.organization.id": selectConfigTaskOrg(),
		"hiddenDangerVo.exceptionTypeId":conditionExceptionType,
		"hiddenDangerVo.exceptionSituation":conditionSituation,
		"hiddenDangerVo.discoverDateStart":conditionStartDate,
	    "hiddenDangerVo.discoverDateEnd":conditionEndDate,
		"hiddenDangerVo.address":conditionAddress,
		"hiddenDangerVo.cellName":conditionCellName,
		               
		"hiddenDangerVo.signUserName":conditionSignName,
		"hiddenDangerVo.signDateStart":conditionStartSign,
		"hiddenDangerVo.signDateEnd":conditionEndSign,
		"hiddenDangerVo.ishandle":conditionSign,
		"hiddenDangerVo.hasReplay":hasReplay,
		"exceptionType":$("#exceptionType").val(),
		"hiddenDangerVo.isGrid":isConfigTaskGrid()
		
	}
	if(isConfigTaskSelect()){
		$.extend(initParam,{"hiddenDangerVo.mode":"gridConfigTask","hiddenDangerVo.funOrgId": $("#funOrgId").val()})
	}
	$("#hiddenDangerList").setGridParam({
		url:'${path}/baseInfo/hiddenDangerManage/searchHiddenDangerByVo.action',
		datatype: "json",
		page:1
	});
	$("#hiddenDangerList").setPostData(initParam);
	$("#hiddenDangerList").trigger("reloadGrid");
}




var taskListTimeStandard;
var serverTime = new Date().getTime();
function getTaskListTimeStandardByItemName(){
    $.get(PATH + "/taskListTimeStandardManage/getTaskListTimeStandardByItemName.action", {
        'orgId':selectConfigTaskOrg(),
        'itemNameDictInternalId':<@pop.static value="@com.tianque.plugin.taskList.constant.TaskListItemNameInternalId@HIDDEN_DANGER"/>
    }, function (data) {
        taskListTimeStandard = data[0];
        serverTime = taskListTimeStandard?taskListTimeStandard.sysCurrTime:new Date().getTime();
        // 有配置的才显示亮牌
        if(taskListTimeStandard){
            $("#hiddenDangerList").jqGrid("showCol", ["signDate","replayDate"]);
        }
    });
}
$(document).ready(function () {
    getTaskListTimeStandardByItemName();
	var postData={
		"hiddenDangerVo.organization.id":selectConfigTaskOrg(),
		"exceptionType":$("#exceptionType").val()
     };
	if(isConfigTaskSelect()){
		$.extend(postData,{"hiddenDangerVo.mode":"gridConfigTask","hiddenDangerVo.funOrgId": $("#funOrgId").val()})
	}
	$("#hiddenDangerList").jqGridFunction({
		url:"${path}/baseInfo/hiddenDangerManage/searchHiddenDangerByVo.action",
		dataType: "local",
		postData: postData,
      
        colModel:[
	    {name:"id", index:"id", hidden:true,frozen:true},
		<@pop.JugePermissionTag ename="signHiddenDanger${(type)!}">
            {name:'signDate',label:'签收督办',sortable:true,formatter:signSuperviseFormatter,align:'center',hidden:true,hidedlg:true,width:130},
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="replyHiddenDanger${(type)!}">
            {name:'replayDate',label:'回复督办',sortable:true,formatter:replaySuperviseFormatter,align:'center',hidden:true,hidedlg:true,width:130},
		</@pop.JugePermissionTag>
		{name:"discoverDate", index:'discoverDate', sortable:true,label:'时间',align:'center', width:150 ,align:'center'},
		{name:'address', sortable:true, label:'地点', width:280 ,align:'center'},
	    {name:"exceptionType",sortable:true,label:"异常类型",formatter:exceptionTypeFormatter,width:100,align:'center' },
		{name:'exceptionSituation', sortable:true, label:'异常情况', width:243 ,align:'center'},
	    {name:'ishandle', sortable:true, label:'是否签收',formatter:isHandleFormatter, width:110 ,align:'center'},
	    {name:'hasReplay',label:'是否回复',sortable:false,align:"center", width:110,formatter:addTaskListReplyFormatter},
	    {name:'remark', sortable:true, label:'备注', width:200 ,align:'center'},
		{name:'createDate',label:'创建时间',sortable:true,align:'center',hidden:true,hidedlg:true,width:120}
	],
		multiselect:true,
	    viewrecords:true,
		ondblClickRow: viewHiddenDanger
	});
    function signSuperviseFormatter(el,options,rowData){
        if(rowData.ishandle==1){
            return "-";
        }else{
            if(!taskListTimeStandard){
                return '<span title="未配置">err</span>';
            }
            var createDateStr =rowData.createDate;
            var useTime =(serverTime - Date.parse(createDateStr.replace(/-/g, "/")))/(1000*60*60);
            if(useTime>taskListTimeStandard.signRedLimit){
                return '<strong class="redLimitWarn" title="红牌超时"/>';
            }else if(useTime>taskListTimeStandard.signYellowLimit){
                return '<strong class="yellowLimitWarn" title="黄牌超时"/>';
            }else{
                var title = "剩"+Math.ceil(taskListTimeStandard.signYellowLimit-useTime)+'小时时限';
                return '<strong class="greenLimitWarn" title="'+title+'"/>';
            }
        }
    }
    function replaySuperviseFormatter(el,options,rowData) {
        if (rowData.hasReplay == 1) {
            return "-";
        }
        if (rowData.ishandle != 1) {
            return '<span title="未签收">...</span>';
        }
        if (!taskListTimeStandard) {
            return '<span title="未配置">err</span>';
        }
        var signDateStr = rowData.signDate;
        var useTime = (serverTime - Date.parse(signDateStr.replace(/-/g, "/"))) / (1000 * 60 * 60);
        if (useTime > taskListTimeStandard.replayRedLimit) {
            return '<strong class="redLimitWarn" title="红牌超时"/>';
        } else if (useTime > taskListTimeStandard.replayYellowLimit) {
            return '<strong class="yellowLimitWarn" title="黄牌超时"/>';
        } else {
            var title = "剩" + Math.ceil(taskListTimeStandard.replayYellowLimit - useTime) + '小时时限';
            return '<strong class="greenLimitWarn" title="' + title + '"/>';
        }

    }
    $("#add").click(function(event){
        if(!isConfigTaskGrid()){
			$.messageBox({level:'warn',message:"请先选择网格级别组织机构进行新增！"});
			return;
		}
		$("#hiddenDangerDialog").createDialog({
			width: 600,
			height: 450,
			postData:{
			       "hiddenDanger.organization.id":selectConfigTaskOrg()
			},
			title:"新增治安隐患信息",
			url: '${path}/baseInfo/hiddenDangerManage/addPage.action?exceptionType='+$("#exceptionType").val(),
			buttons: {
				"保存" : function(event){
				$("#maintainFormForhiddenDangerAdd").submit();
				
				 
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
    
    
    
					
		
	});
	
		
	$("#search").click(function(event){
	
		$("#hiddenDangerDialog").createDialog({
			width: 650,
			height: 400,
			postData:{
			},
			title:'查询-请输入查询条件',
			url: '${path}/baseInfo/hiddenDangerManage/searchPage.action?exceptionType='+$("#exceptionType").val(),
			buttons: {
				"查询" : function(event){
				searchHiddenDanger();
				$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	
	$("#reload").click(function(){
		
		$("#searchByCondition").attr("value","请输入地点");
	
		if(selectConfigTaskOrg()!="" && selectConfigTaskOrg()!=null){
			onOrgChanged(selectConfigTaskOrg());
		}
	});
	
	$("#delete").click(function(){
		var selectedId = $("#hiddenDangerList").jqGrid("getGridParam", "selarrrow");
		if(selectedId.length==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(event,selectedId);
	});
	
	$("#update").click(function(e){
		var selectedId =$("#hiddenDangerList").jqGrid("getGridParam", "selarrrow");
		
	   if(selectedId.length>1){
		$.messageBox({level:"warn",message:"只能选择一条数据进行操作！"});
 		return;
	}
	if(selectedId==null || selectedId==undefined || selectedId==''){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
		updateHiddenDanger(event,selectedId);
	});
	
	$("#view").click(function(e){
	
		var selectedId =$("#hiddenDangerList").jqGrid("getGridParam", "selarrrow");
		
	   if(selectedId.length>1){
		$.messageBox({level:"warn",message:"只能选择一条数据进行操作！"});
 		return;
	}
	if(selectedId==null || selectedId==undefined || selectedId==''){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
	
		viewHiddenDanger(selectedId);
	});
	
	$("#fastSearchButton").click(function(e){
	var orgId=selectConfigTaskOrg();
	fastSearch(orgId);
	});
	
	
	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入地点");
	});
	
	
	
	});
function addTaskListReply(id){
	$("#addTaskListReplyDlg").createDialog({
		width: 600,
		height: 400,
		title: '异常情况报告回复',
		url:"${path}/plugin/taskListManage/common/addTaskListReplyDlg.action?taskListReply.moduleKey=reply_hiddenDanger&taskListReply.taskId="+id,
		buttons: {
			"回复" : function(){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
</script>