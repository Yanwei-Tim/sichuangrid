<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
		
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="teamManagementList">
		</table>
		<div id="teamManagementListPager"></div>
	</div>
	<div id="teamManagementDialog"></div>
	<div id="noticeDialog"></div>
	<div id="teamManagementMaintanceDialog"></div>
</div>
<script type="text/javascript">
var org=getCurrentOrgId();
function onOrgChanged(org,isgrid){
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#teamManagementList").setGridParam({
		url:'${path}/fourTeamsManage/teamManagementList.action',
		datatype: "json",
		page:1
	});
	$("#teamManagementList").setPostData({
		"orgId":function(){return currentOrgId;}
	});
	$("#teamManagementList").trigger("reloadGrid");
}
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:viewTeamMenber("+rowData.id+")'><span>"+rowData.subTeamNumber+"</span></a>";
}
$(document).ready(function(){

	var orgId=getCurrentOrgId();
	
	function refresh(){
		$("#teamManagementList").setGridParam({
			url:'${path}/fourTeamsManage/teamManagementList.action',
			datatype: "json",
			page:1
		});
		$("#teamManagementList").trigger("reloadGrid");
	}

	$("#reload").click(function(){
		refresh();
	});

	$("#search").click(function(event){
		$("#teamManagementDialog").createDialog({
			width: 600,
			height: 200,
			title:'队伍查询-请输入查询条件',
			url:'${path}/fourTeamsManage/searchFourTeamsDlg.jsp?organizationId='+orgId,
			buttons: {
		   		"查询" : function(event){
		   			$("#maintainForm").submit();
		   			$(this).dialog("close");
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#look").click(function(event){
		
		var selectedId =getSelectedId();
		if(selectedId==null){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		viewUserInfo(selectedId);
	});
	
	$("#teamManagementList").jqGridFunction({
		url:'${path}/fourTeamsManage/teamManagementList.action',
		datatype: "json",
		postData:{
			"organizationId":orgId
		},
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:'names', index:'names',label:'队伍名称', width:260, sortable:true, align:'center', hidden:false}, 	
			{name:'subTeamNumber',index:'subTeamNumber',label:'子队伍数量',formatter:operatorFormatter,sortable:true,width:270,align:"center"},
			{name:'indexId',index:'indexId',label:'排序号',sortable:true,width:270,align:"center"},
			{name:'comments', index:'comments',label:'备注', width:300, sortable:true, align:'center', hidden:false}	
		],
		sortname:'indexId',
		sortorder: 'asc', 
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:false,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){}
	});
	$("#maintenanceTeam").click(function(){
		
		var selectedId=getSelectedId();
		var selectedIds = $("#teamManagementList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null){
	 		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		if(selectedIds!=null && selectedIds.length > 1){
	 		$.messageBox({level:"warn",message:"请只能选择一条数据再进行操作！"});
	 		return;
		}
		$("#teamManagementDialog").createDialog({
			width: 800,
			height: 638,
			title:'维护队伍信息',
			url:'/fourTeamsManage/maintenanceFourTeamDlg.jsp?id='+selectedId+'&organizationId='+orgId,
			buttons: {
				"关闭" : function(){
		        	$(this).dialog("close");
		        	$("#teamManagementList").trigger("reloadGrid");
		   		}
			}
		});
	});
});

function viewTeamMenber(selectedId){
	$("#teamManagementDialog").createDialog({
		width: 800,
		height: 668,
		title:'查看维护队伍信息',
		url:'/fourTeamsManage/viewFourTeam.action?id='+selectedId,
		buttons: {
			"关闭" : function(){
	        	$(this).dialog("close");
	        	$("#teamManagementList").trigger("reloadGrid");
	   		}
		}
	});
}
function getSelectedIds(){
	var selectedIds = $("#teamManagementList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

function getSelectedId(){
    var selectedIdLast = null;
    var selectedIds = $("#teamManagementList").jqGrid("getGridParam", "selarrrow");

    for(var i=0;i<selectedIds.length;i++){
		selectedIdLast = selectedIds[i];
   }
   return selectedIdLast;
}
</script>