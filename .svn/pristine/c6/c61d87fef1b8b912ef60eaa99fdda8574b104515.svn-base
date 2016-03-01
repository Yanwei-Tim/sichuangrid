<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24" id="viewMaintenanceTeam">
	<div class='clearLine'></div>
	<div style="width: 100%;margin-top:6px">
		<table id="maintenanceTeamList"> </table>
		<div id="maintenanceTeamListPager"></div>
	</div>
</div>

<div id="maintenanceTeamDialog"></div>
<script type="text/javascript">

function operatorFormatter(el,options,rowData){
	return "<a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | <a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a>";
}

$(function(){
	$("#maintenanceTeamList").jqGridFunction({
		datatype: "local",
		height: 390,
		colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"operation",index:"id",label:"操作",sortable:false,formatter:operatorFormatter,width:120,align:"center"},
	    	{name:'name', index:'name',label:'社会志愿者服务队名称', width:300, sortable:true, align:'center', hidden:false}, 	
	    	{name:'remark', index:'remark',label:'备注', width:345, sortable:true, align:'center', hidden:false}	
		],
		multiselect:true
	});
	
	jQuery("#maintenanceTeamList").jqGrid('setFrozenColumns');
	
	
	var str='<table id="maintenanceTeamList" tabindex="1" cellspacing="0" cellpadding="0" border="0" role="grid" aria-multiselectable="true" aria-labelledby="gbox_maintenanceTeamList" class="ui-jqgrid-btable" style="width: 1125px;">'
		+'<tbody><tr class="jqgfirstrow" role="row" style="height:auto">'
		+'<td role="gridcell" style="height:0px;width:25px;"></td><td role="gridcell" style="height:0px;width:150px;display:none;"></td>'
		+'<td role="gridcell" style="height:0px;width:120px;"></td>'
		+'<td role="gridcell" style="height:0px;width:300px;"></td>'
		+'<td role="gridcell" style="height:0px;width:345px;"></td></tr>'
		+'<tr id="1" tabindex="-1" role="row" class="ui-widget-content jqgrow ui-row-ltr">'
		+'<td role="gridcell" style="text-align:center;width: 25px;" aria-describedby="maintenanceTeamList_cb">'
		+'<input role="checkbox" type="checkbox" id="jqg_maintenanceTeamList_1" class="cbox" name="jqg_maintenanceTeamList_1"></td>'
		+'<td role="gridcell" style="display:none;" title="1" aria-describedby="maintenanceTeamList_id">1</td>'
		+'<td role="gridcell" style="text-align:center;" title=" 修改 |   删除 " aria-describedby="maintenanceTeamList_operation">'
		+' <a href="javascript:;" onclick="updateOperator(1);"><span>修改</span></a> |   <a href="javascript:;" onclick="deleteOperator(1)"><span>删除</span></a></td>'
		+'<td role="gridcell" style="text-align:center;" title="电力公司便民服务队" aria-describedby="maintenanceTeamList_name">电力公司便民服务队</td>'
		+'<td role="gridcell" style="text-align:center;" title="" aria-describedby="maintenanceTeamList_remark"></td></tr>'
		+'<tr id="2" tabindex="-1" role="row" class="ui-widget-content jqgrow ui-row-ltr ">'
		+'<td role="gridcell" style="text-align:center;" aria-describedby="maintenanceTeamList_cb">'
		+'<input role="checkbox" type="checkbox" id="jqg_maintenanceTeamList_2" class="cbox" name="jqg_maintenanceTeamList_2"></td>'
		+'<td role="gridcell" style="display:none;" title="2" aria-describedby="maintenanceTeamList_id">2</td>'
		+'<td role="gridcell" style="text-align:center;" title=" 修改 |   删除 " aria-describedby="maintenanceTeamList_operation">'
		+' <a href="javascript:;" onclick="updateOperator(2);"><span>修改</span></a> |   <a href="javascript:;" onclick="deleteOperator(2)"><span>删除</span></a></td>'
		+'<td role="gridcell" style="text-align:center;" title="自来水公司便民服务队" aria-describedby="maintenanceTeamList_name">自来水公司便民服务队</td>'
		+'<td role="gridcell" style="text-align:center;" title="" aria-describedby="maintenanceTeamList_remark"></td></tr>'
		+'</tbody></table>';
		//ui-state-hover
	jQuery("#maintenanceTeamList").html(str);
	
});
function updateOperator(selectedId){
	
}

function deleteOperator(selectedId){
	
}

</script>