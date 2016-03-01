<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div  style="width:100%; overflow:hidden;" >
	<table id="priorServiceTeamList"></table>
	<div id="priorServiceTeamListPager"></div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	
	$("#priorServiceTeamList").jqGridFunction({
		datatype: "local",
		height:290,
		colNames:['teamId','positionId','所属团队','团队类别','团队名称','职务','注销原因','注销日期'],
		colModel:[
			{name:"teamId",index:'teamId', hidden:true,key:true},
			{name:"positionId",hidden:true},
	        {name:'teamClazz',width:110,align:"center"},
	        {name:'teamType',width:110,align:"center"},
	        {name:'teamName', width:140,align:"center"},
	        {name:'memberPosition',width:100,align:"center",sortable:false},
	        {name:'logOutReason',width:200,align:"center",sortable:false},
	        {name:'logOutTime',width:140,align:"center",sortable:false}
	    ],
	    sortname: 'teamId',
	    showColModelButton:false
	});
	
	$("#priorServiceTeamList").setGridParam({
		url:'${path}/baseinfo/serviceTeamMemberManage/pageMemberRelationInTeams.action',
		datatype: "json",
		page:1
		
	});
	$("#priorServiceTeamList").setPostData({'memberRelationInteamVo.baseId':<s:property value="#parameters.baseId"/>,'memberRelationInteamVo.onDuty':false});
	$("#priorServiceTeamList").trigger("reloadGrid");
	
});
</script>