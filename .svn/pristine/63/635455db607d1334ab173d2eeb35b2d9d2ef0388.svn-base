<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div  style="width:100%;overflow:hidden;" >
	<table id="currentServiceTeamList"></table>
	<div id="currentServiceTeamListPager"></div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#currentServiceTeamList").jqGridFunction({
		datatype: "local",
		height:290,
		colNames:['teamId','positionId','所属团队','团队类别','团队名称','职务'],
		colModel:[
			{name:"teamId",index:'teamId', hidden:true,key:true},
			{name:"positionId",hidden:true},
	        {name:'teamClazz',width:150,align:"center"},
	        {name:'teamType',width:150,align:"center"},
	        {name:'teamName', width:150,align:"center"},
	        {name:'memberPosition',width:120,align:"center",sortable:false}
	    ],
	    sortname: 'teamId',
	    showColModelButton:false
	});
	
	$("#currentServiceTeamList").setGridParam({
		url:'${path}/baseinfo/serviceTeamMemberManage/pageMemberRelationInTeams.action',
		datatype: "json",
		page:1
		
	});
	$("#currentServiceTeamList").setPostData({'memberRelationInteamVo.baseId':<s:property value="#parameters.baseId"/>,'memberRelationInteamVo.onDuty':true});
	$("#currentServiceTeamList").trigger("reloadGrid");
	
});
</script>