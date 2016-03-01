<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewMaintenanceTeam" class="container container_24">
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
		    <input type="text" value="请输入队伍名称" name="searchText" id="searchText" 
		    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入队伍名称':this.value;"
		    onfocus="value=(this.value=='请输入队伍名称')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a id="fastSearchButton" href="javascript:;"><span>检索</span></a>
 		<pop:JugePermissionTag ename="addFourLevelPlatform"> 
 			<a id="addMember" href="javascript:;"><span>新增</span></a>
 		</pop:JugePermissionTag> 
 		<pop:JugePermissionTag ename="deleteFourLevelPlatform"> 
 			<a id="deleteMember" href="javascript:;"><span>批量删除</span></a>
 		</pop:JugePermissionTag> 
		<input id="isHaveJob0" value="0" type="hidden"/>
	</div>
	<div style="width: 100%;">
			<table id="teamManagementLists">
			</table>
			<div id="teamManagementListsPager"></div>
	</div>
<div id="addMemberDialog"></div>

	<div id="tabs">
		<ul>
			<li id="liHava"><a id="hava"  href='${path}/fourTeamsManage/maintenanceFourTeamList.jsp?id=${param.id}&organizationId=${param.organizationId}'>子队伍信息</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 300;
$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
	$(function() {
		$("#tabs").tabs({cache:false});
		//$( "#tabs").tabs();
});

$("#fastSearchButton").click(function(){
	search(getCurrentOrgId());
});
function search(orgId){
	var conditions = $("#searchText").val();
	if(conditions == '请输入队伍名称') return;
	
	
	if(conditions == '请输入队伍名称') return;
	$("#maintenanceTeamList").setGridParam({
		url:'${path}/fourTeamsManage/searchTeamName.action?names='+conditions,
		datatype: "json",
		page:1
	});
	
	 $("#maintenanceTeamList").trigger("reloadGrid");
}
var selectedSubId = getSelectedId();

	
$(document).ready(function(){
	function getSelectedIds(){
		var selectedIds = $("#maintenanceTeamList").jqGrid("getGridParam", "selarrrow");
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
		
	function refresh(){
		$("#teamManagementLists").setGridParam({
			url:'${path}/fourTeamsManage/teamManagementList.action',
			datatype: "json",
			page:1
		});
		$("#teamManagementLists").trigger("reloadGrid");
	}

	$("#reload").click(function(){
		refresh();
	});

	$("#search").click(function(event){
		$("#teamManagementDialog").createDialog({
			width: 700,
			height: 300,
			title:'查询-请输入查询条件',
			url:'${path}/fourTeamsManage/searchFourTeamsDlg.jsp',
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

	$("#deleteMember").click(function(){
		var selectedRowIds=getSelectedIds();
		if(null==selectedRowIds || ""==selectedRowIds){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"该删除后就不能还原，确认删除？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:"${path}/fourTeamsManage/deleteFourTeams.action?ids="+selectedRowIds+"&id="+${param.id},
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#maintenanceTeamList").trigger("reloadGrid");
						for(var i=0;i<selectedRowIds.length;i++){
							$("#uu").val($("#maintenanceTeamList").getRowData(selectedRowIds[i]).userName+'@hztianque.com');
						}
						$.messageBox({message:"四支队伍删除成功！"});
					}
				});
			}
		});
	});

	$("#addMember").click(function(event){
		if($("#addMember").attr("disabled")){
			return ;
		}
		$("#addMemberDialog").createDialog({
			width: 600,
			height: 350,
			title:'新增队伍',
			url:'${path}/fourTeamsManage/maintainAddList.action',
			postData:{
				"id":${param.id}
			},
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
		
});

</script>