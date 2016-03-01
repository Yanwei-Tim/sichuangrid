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
var dialogWidth = 800;
var dialogHeight = 300;
var rowData = getSelectedId();
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | <a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a>";
}
$(function(){
	$("#maintenanceTeamList").jqGridFunction({
		url:'${path}/fourTeamsManage/subTeamManagementList.action',
		postData:{
				"id":${param.id},
				"organizationId":${param.organizationId}
			},
		datatype: "json",
		colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"operation",index:"id",label:"操作",sortable:false,formatter:operatorFormatter,width:120,align:"center"},
	    	{name:'names', index:'names',label:'队伍名称', width:200, sortable:true, align:'center', hidden:false}, 
	    	{name:'orgCode', index:'orgCode',label:'所在网格', width:187, sortable:true, align:'center', hidden:false}, 		
	    	{name:'comments', index:'comments',label:'备注', width:200, sortable:true, align:'center', hidden:false}	
		],
		multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:false,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){}
	});
});
function updateOperator(selectedId){

	$("#maintenanceTeamDialog").createDialog({
		width: 600,
		height: 350,
		title:'修改四支队伍',
		url:'${path}/fourTeamsManage/maintainAddList.action?id='+selectedId+'&mode=edit',
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

function deleteOperator(selectedId){
	
	$.confirm({
		title:"确认删除",
		message:"该删除后就不能还原，确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/fourTeamsManage/deleteFourTeam.action?id="+selectedId+"&parentId="+${param.id},
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#maintenanceTeamList").trigger("reloadGrid");
					$.messageBox({message:"四支队伍删除成功！"});
				}
			});
		}
	});

}

</script>