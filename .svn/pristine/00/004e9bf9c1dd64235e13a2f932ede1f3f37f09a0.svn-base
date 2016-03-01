<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24" id="viewMaintenanceTeama">
	<div class='clearLine'></div>
	<div style="width: 99.5%;height: 50%;margin-top:6px">
		<table id="maintenanceTeamList"> </table>
		<div id="maintenanceTeamListPager"></div>
	</div>
</div>

<div id="maintenanceTeamDialog"></div>
<script type="text/javascript">

$(function(){
	$("#maintenanceTeamList").jqGridFunction({
		url:'${path}/fourTeamsManage/subTeamManagementList.action',
		postData:{
				"id":${param.id}
			},
		datatype: "json",
		colModel:[
			{name:"id",index:'id',hidden:true},
	    	{name:'names', index:'names',label:'队伍名称', width:260, sortable:true, align:'center', hidden:false}, 
	    	{name:'orgCode', index:'orgCode',label:'所在网格', width:220, sortable:true, align:'center', hidden:false}, 		
	    	{name:'comments', index:'comments',label:'备注', width:232, sortable:true, align:'center', hidden:false}	
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:false,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){}
	});
});


</script>