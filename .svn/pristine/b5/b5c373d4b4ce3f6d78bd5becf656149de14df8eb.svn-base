<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewManageTeamMembers" class="container container_24">

	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
		    <input type="text" value="请输入姓名或拼音" name="searchText" id="searchText" 
		    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名或拼音':this.value;"
		    onfocus="value=(this.value=='请输入姓名或拼音')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a id="fastSearchButton" href="javascript:;"><span>检索</span></a>
 			<a id="addMember" href="javascript:;"><span>新增</span></a> 
 			<a id="importMember" href="javascript:;"><span>导入</span></a> 
 			<a id="deleteMember" href="javascript:;"><span>批量删除</span></a>
 			<a id="refreshMemberList" href="javascript:;"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
			<table id="teamManagementLists">
			</table>
			<div id="teamManagementListsPager"></div>
	</div>
	<div id="addMemberDialogs"></div>

	<div id="tabs">
		<ul>
			<li id="liHava"><a id="hava"  href='${path}/fourTeamsManage/manageFourTeamMemberList.jsp?id=${param.id}'>队伍成员</a></li>
		</ul>
	</div>

</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="politics" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
	function refreshMemberList(){
		$("#maintenanceTeamList").setGridParam({
			url:'${path}/fourTeamsManage/teamMemberManagementList.action',
			postData:{
					"id":${param.id}
			},
			datatype: "json",
			page:1
		});
		$("#searchText").attr("value","请输入姓名或拼音");
		$("#maintenanceTeamList").trigger("reloadGrid");
	}

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

$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
	$(function() {
		$("#tabs").tabs({cache:false});
	});

$("#fastSearchButton").click(function(){
	search(getCurrentOrgId());
});
function search(orgId){
	var conditions = $("#searchText").val();
	if(conditions == '请输入姓名或拼音') return;
	
	$("#maintenanceTeamList").setGridParam({
		url:'${path}/fourTeamsManage/searchTeamMemberName.action?orgId='+orgId+'&names='+encodeURI(conditions),
		datatype: "json",
		page:1
	});
	
	 $("#maintenanceTeamList").trigger("reloadGrid");
}	
$("#addMember").click(function(event){
	if($("#addMember").attr("disabled")){
		return ;
	}
	$("#addMemberDialogs").createDialog({
		width: 820,
		height: 450,
		title:'新增成员',
		url:'${path}/fourTeamsManage/maintainAddMemberList.action',
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
				url:"${path}/fourTeamsManage/deleteFourTeamMembers.action",
				type:"post",
				data:{
					"ids":selectedRowIds+"",
					"id":'${param.id}'
				},
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#maintenanceTeamList").trigger("reloadGrid");
					$("#teamManagementList").trigger("reloadGrid");
					for(var i=0;i<selectedRowIds.length;i++){
						$("#uu").val($("#maintenanceTeamList").getRowData(selectedRowIds[i]).userName+'@hztianque.com');
					}
					$.messageBox({message:"四支队伍成员删除成功！"});
				}
			});
		}
	});
});

//导入
$("#importMember").click(function(event){
		$("#addMemberDialogs").createDialog({
			width: 400,
			height: 230,
			title:"数据导入",
			url:"${path}/common/import.jsp?isNew=1&dataType=fourTeamMembers&dialog=addMemberDialogs&startRow=6&templates=FOURTEAMMEMBERS&listName=maintenanceTeamList&module=${param.id}",
			buttons:{
				"导入" : function(event){
					$("#mForm").submit();
				},
			   	"关闭" : function(){
			   		$(this).dialog("close");
			   		$("#teamManagementList").trigger("reloadGrid");
			   	}
			},
			shouldEmptyHtml:false
		});
	});
	
	$("#refreshMemberList").click(function(){
		refreshMemberList();
	});

</script>