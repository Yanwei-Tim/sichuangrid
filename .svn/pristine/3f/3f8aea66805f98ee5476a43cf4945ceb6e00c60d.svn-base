<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content" >
	<div id="search-condition-form" title="查询" class="container container_24">
		<input type="hidden" id="targetOrgId" value="${targetOrgId}"/>
		<input type="hidden" id="mode" value="${mode}"/>
		<input type="hidden" id="adminTarget" value="${adminTarget}"/> 
		<input type="hidden" id="orgid" value="${param.orgId}">
		<div class="grid_3 lable-right">
			<label class="form-lbl">队伍名称：</label>
		</div>
		<div class="grid_6">
			<input name="fourTeamName" type="text" id="fourTeamNameId" maxlength="50" class="form-txt"/>
		</div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">队伍类型：</label>
		</div>
		<div class="grid_6">
			<select id="fourTeamsType" class="form-txt">
				<option value=" ">全部队伍</option>
				<option value="1">基层便民专业化服务队</option>
				<option value="2">网格员服务队</option>
				<option value="3">党员先锋服务队</option>
				<option value="4">社会志愿者服务队</option>
			</select>
		</div>
		<div class="grid_3">
			&nbsp;&nbsp;&nbsp;<input name="search" type="button" style='width:55px;height:25px' value="查询" id="searchDealTarget"  />
		</div>
		<div class="grid_3">
			<input name="search" type="button" style='width:55px;height:25px' value="刷新" id="refresh_btn" />
		</div>
		<div class='clearLine'>&nbsp;</div>
	</div>
	<div style="width: 100%;">
	<table id="fourTeamsInfoList"></table>
	<div id="fourTeamsInfoListPager"></div>
	</div>
</div>
<script>
$(document).ready(function(){

	$("#refresh_btn").click(function(){
			$("#fourTeamNameId").val("");
			$("#fourTeamsType").val("");
			$("#searchDealTarget").click();
		});
	$("#fourTeamsType").change(function(){
			$("#searchDealTarget").click();
		});
	$("#searchDealTarget").click(function(){
		$("#fourTeamsInfoList").setGridParam({
			url:'${path}/fourTeamsManage/searchFourTeamsForIssues.action',
			datatype: "json",
			page:1
		});
		$("#fourTeamsInfoList").setPostData({
			//目标处理部门的orgId
	 		"organizationId":$("#orgid").val(),
	 		"fourTeams.organization.id":$("#orgid").val(),
	 		"fourTeams.names":$("#fourTeamNameId").val(),
	 		"fourTeams.parentTeamId":$("#fourTeamsType").val()
		});
		$("#fourTeamsInfoList").trigger("reloadGrid");
 	});

	$("#fourTeamsInfoList").jqGridFunction({
		datatype: "local",
	   	colModel: [
			{name:"id",index:'id',hidden:true,key:true,frozen:true},
			{name:'names', index:'names',label:'队伍名称', width:250, sortable:true, align:'center', hidden:false}, 
			{name:'parentTeamId', index:'parentTeamId',hidden:true,frozen:true},
			{name:'orgCode', index:'orgCode',hidden:true,frozen:true}, 		
			{name:'comments', index:'comments',label:'备注', width:310, sortable:true, align:'center', hidden:false}	
			   	],
	 	showColModelButton :false,
	   	scrollrows:true,
		multiselect:false,
		beforeSelectRow:selectCorrectOrg,
	    width:300,
	    height:205
	});
	$("#searchDealTarget").click();
	function selectCorrectOrg(rowId,e){
			$("#fourTeamsInfoList").resetSelection();
		return true;
	}
		
});


function fillFourTeamInputer(nameIdDiv,idDiv,realIdDiv){
	var selectedId = $("#fourTeamsInfoList").jqGrid("getGridParam", "selrow");
	if(selectedId.length>0){
		$("#"+nameIdDiv).clearPersonnelComplete();
		var rowData = $("#fourTeamsInfoList").jqGrid("getRowData",selectedId); 
		$("#"+nameIdDiv).val(rowData.names);
		$("#"+idDiv).val(rowData.parentTeamId);
		$("#"+realIdDiv).val(rowData.id);
	}
}

function fillItemName(){
	var selectedId = $("#fourTeamsInfoList").jqGrid("getGridParam", "selrow");
	fillItem(selectedId);
}
</script>

