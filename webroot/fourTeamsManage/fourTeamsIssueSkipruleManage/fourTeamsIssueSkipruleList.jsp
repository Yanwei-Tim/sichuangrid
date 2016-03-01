<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
	
		<pop:JugePermissionTag ename="findFourTeamsIssueSkiprule">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addFourTeamsIssueSkiprule">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateFourTeamsIssueSkiprule">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteFourTeamsIssueSkiprule">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="fourTeamsIssueSkipruleList">
		</table>
		<div id="fourTeamsIssueSkipruleListPager"></div>
	</div>
	<div id="fourTeamsIssueSkipruleDialog"></div>
	<div id="noticeDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 600;
var dialogHeight = 400;
<pop:formatterProperty name="issueUrgentLevel" domainName="@com.tianque.domain.property.PropertyTypes@URGENT_LEVEL" />
<pop:formatterProperty name="submitLevel" domainName="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" />

var userOrgId = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>";

// Formatter
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateFourTeamsIssueSkiprule'><a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteFourTeamsIssueSkiprule'><a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}
function messageFlagFormatter(el,options,rowData){
	if(el=="0")
		return "否";
	else
		return "是";
}

$(function(){
	function toggleButtonState(){
	  	var selectedIds=$("#fourTeamsIssueSkipruleList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	function afterLoad(){

	}
	
	// 生成列表
	$("#fourTeamsIssueSkipruleList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"encryptId",index:'id',frozen:true,hidden:true},
	    	//{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:85,frozen:true,sortable:false,align:'center' },
	    	{name:'issueTypeDomainName', index:'issue_Type_Domain_Id',label:'事件大类', width:240, sortable:true, align:'center', hidden:false}, 	
	    	{name:'issueTypeName', index:'issue_Type_Id',label:'事件子类', width:240, sortable:true, align:'center', hidden:false}, 	
	    	{name:'issueUrgentLevel.id', index:'issue_Urgent_Level',label:'紧急等级',formatter:issueUrgentLevelFormatter, width:100, sortable:true, align:'center', hidden:false}, 	
	    	{name:'submitLevel.id', index:'submit_level',label:'上报层级', formatter:submitLevelFormatter, width:240, sortable:true, align:'center', hidden:false}, 	
	    	{name:'messageFlag', index:'message_Flag',label:'短信提醒',formatter:messageFlagFormatter, width:100, sortable:true, align:'center', hidden:false}
	   	],
	  	multiselect:true,
	  	onSelectAll:function(data){
	  		toggleButtonState(data);
	  	},
    	loadComplete: function(data){
    		afterLoad(data);
    	},
	   	<pop:JugePermissionTag ename="viewFourTeamsIssueSkiprule">
	    ondblClickRow: viewIssueSkiprule,
	    </pop:JugePermissionTag>
		onSelectRow: function(data){
			toggleButtonState(data);
		}
	});
	jQuery("#fourTeamsIssueSkipruleList").jqGrid('setFrozenColumns');
	
	
	loadIssueSkiprule();
	
	$("#add").click(function(){
		$("#fourTeamsIssueSkipruleDialog").createDialog({
			title:"新增越级规则设置信息",
			width: dialogWidth,
			height: dialogHeight,
			url:'${path}/fourTeamsIssueSkipruleManage/dispatchOperate.action?mode=add&dailogName=fourTeamsIssueSkipruleDialog',
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
	$("#update").click(function(){
		var selectedIds = $("#fourTeamsIssueSkipruleList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({
				message:"请选择一条越级规则进行修改！",
				level: "warn"
			 });
			return;
		}
		updateOperator(selectedIds);
	});
	$("#delete").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(allValue);
	});
	$("#view").click(function(){
		if($("#view").attr("disabled")){
			return ;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewIssueSkiprule(selectedId);
	});
	$("#reload").click(function(){
		loadIssueSkiprule();
	});

	$("#search").click(function(event){
		$("#fourTeamsIssueSkipruleDialog").createDialog({
			width:520,
			height:220,
			title:'越级规则设置查询-请输入查询条件',
 	 		url:'${path}/fourTeamsIssueSkipruleManage/dispatchOperate.action?mode=search',
			buttons: {
		   		"查询" : function(event){
					searchFourTeamsIssueSkiprule($("#searchFourTeamsIssueSkiprule_form").serializeArray());
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
});

function viewIssueSkiprule(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#fourTeamsIssueSkipruleList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#fourTeamsIssueSkipruleDialog").createFrameDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:"查看越级规则设置信息",
 		url:'${path}/fourTeamsIssueSkipruleManage/dispatchOperate.action?mode=view&id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function updateOperator(selectedId){
	var ent =  $("#fourTeamsIssueSkipruleList").getRowData(selectedId);
	$("#fourTeamsIssueSkipruleDialog").createDialog({
		title:"修改越级规则设置信息",
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/fourTeamsIssueSkipruleManage/dispatchOperate.action?mode=edit&id='+ent.encryptId+'&dailogName=fourTeamsIssueSkipruleDialog',
		buttons: {
	   		"保存" : function(event){
	   			$("#maintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function deleteOperator(allValue){
	var selectedIds=deleteOperatorByEncrypt("fourTeamsIssueSkiprule",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
 				url:'${path}/fourTeamsIssueSkipruleManage/deleteFourTeamsIssueSkipruleByIds.action',
 				type:"post",
				data:{
					"ids":selectedIds
				},
				success:function(data){
				    $.messageBox({message:"已经成功删除该越级规则设置!"});
					$("#fourTeamsIssueSkipruleList").trigger("reloadGrid");
				}
			});
		}
	});
}
function searchFourTeamsIssueSkiprule(postData){
	var dataJson={};
	var arrIndex = 0;
	for(i=0;i<postData.length;i++){
		if(postData[i].name!='searchFourTeamsIssueSkipruleVo.issueUrgentLevels'){
			dataJson[postData[i].name]=postData[i].value;
		}else{
			dataJson[postData[i].name+"["+arrIndex+"]"]=postData[i].value;
			arrIndex++;
		}	
	}
	$("#fourTeamsIssueSkipruleList").setGridParam({
		url:'${path}/fourTeamsIssueSkipruleManage/findFourTeamsIssueSkiprulePagerBySearchVo.action',
		datatype: "json",
		page:1,
		mtype:"post"
	});
	$("#fourTeamsIssueSkipruleList").setPostData(dataJson);
	$("#fourTeamsIssueSkipruleList").trigger("reloadGrid");
}
function loadIssueSkiprule(){
	var	postData = {
		 "searchFourTeamsIssueSkipruleVo.orgId":userOrgId
	}
	$("#fourTeamsIssueSkipruleList").setGridParam({
 		url:'${path}/fourTeamsIssueSkipruleManage/findFourTeamsIssueSkiprulePagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#fourTeamsIssueSkipruleList").setPostData(postData);
	$("#fourTeamsIssueSkipruleList").trigger("reloadGrid");
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#fourTeamsIssueSkipruleList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#fourTeamsIssueSkipruleList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}


</script>