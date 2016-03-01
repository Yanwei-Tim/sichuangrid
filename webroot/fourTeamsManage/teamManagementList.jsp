<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
<!-- 			<div class="userState" id="fastSearchSelect"> -->
<%-- 				<select id="displayLevel" name="displayLevel" class="basic-input" > --%>
<%-- 					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>"  selected="selected">全部</option> --%>
<%-- 					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>">仅显示本级</option> --%>
<%-- 					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option> --%>
<%-- 				</select> --%>
<!-- 			</div> -->
		</div>
		<pop:JugePermissionTag ename="searchTeam">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="maintenanceTeam">
			<a id="maintenanceTeam" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护队伍</span></a>
		</pop:JugePermissionTag>
		
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

function operatorFormatter(el,options,rowData){
	return "<a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | <a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a>";
}

$(function(){
	// 生成列
	
	$("#teamManagementList").jqGridFunction({
		datatype: "local",
		colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"operation",index:"id",label:"操作",sortable:false,formatter:operatorFormatter,width:120,align:"center"},
	    	{name:'name', index:'name',label:'队伍名称', width:300, sortable:true, align:'center', hidden:false}, 	
	    	{name:'memberNum',index:'memberNum',label:'子队伍数量',sortable:true,width:300,align:"center"},
	    	{name:'remark', index:'remark',label:'备注', width:380, sortable:true, align:'center', hidden:false}	
	    	//{name:'seq', index:'seq',label:'排序', width:100, sortable:true, align:'center', hidden:false}, 	
// 	    	{name:'createUser', index:'createUser',label:'创建人', width:100, sortable:true, align:'center', hidden:true}, 	
// 	    	{name:'updateUser', index:'updateUser',label:'修改人', width:100, sortable:true, align:'center', hidden:true}, 
// 	    	{name:'createDate', index:'createDate',label:'创建时间', width:100, sortable:true, align:'center', hidden:true}, 	
// 	    	{name:'updateDate', index:'updateDate',label:'修改时间', width:100, sortable:true, align:'center', hidden:true}
		],
		multiselect:true
	});
	
	jQuery("#teamManagementList").jqGrid('setFrozenColumns');
	
	var str='<table id="teamManagementList" tabindex="1" cellspacing="0" cellpadding="0" border="0" role="grid" aria-multiselectable="true" aria-labelledby="gbox_teamManagementList" class="ui-jqgrid-btable" style="width: 1125px;">'
		+'<tbody><tr class="jqgfirstrow" role="row" style="height:auto">'
		+'<td role="gridcell" style="height:0px;width:25px;"></td><td role="gridcell" style="height:0px;width:150px;display:none;"></td>'
		+'<td role="gridcell" style="height:0px;width:120px;"></td>'
		+'<td role="gridcell" style="height:0px;width:300px;"></td><td role="gridcell" style="height:0px;width:300px;"></td>'
		+'<td role="gridcell" style="height:0px;width:500px;"></td></tr>'
		+'<tr id="1" tabindex="-1" role="row" class="ui-widget-content jqgrow ui-row-ltr">'
		+'<td role="gridcell" style="text-align:center;width: 25px;" aria-describedby="teamManagementList_cb">'
		+'<input role="checkbox" type="checkbox" id="jqg_teamManagementList_1" class="cbox" name="jqg_teamManagementList_1"></td>'
		+'<td role="gridcell" style="display:none;" title="1" aria-describedby="teamManagementList_id">1</td>'
		+'<td role="gridcell" style="text-align:center;" title=" 修改 |   删除 " aria-describedby="teamManagementList_operation">'
		+' <a href="javascript:;" onclick="updateOperator(1);"><span>修改</span></a> |   <a href="javascript:;" onclick="deleteOperator(1)"><span>删除</span></a></td>'
		+'<td role="gridcell" style="text-align:center;" title="基层便民专业化服务队" aria-describedby="teamManagementList_name">基层便民专业化服务队</td>'
		+'<td role="gridcell" style="text-align:center;" title="2" aria-describedby="teamManagementList_memberNum">2</td>'
		+'<td role="gridcell" style="text-align:center;" title="" aria-describedby="teamManagementList_remark"></td></tr>'
		+'<tr id="2" tabindex="-1" role="row" class="ui-widget-content jqgrow ui-row-ltr ">'
		+'<td role="gridcell" style="text-align:center;" aria-describedby="teamManagementList_cb">'
		+'<input role="checkbox" type="checkbox" id="jqg_teamManagementList_2" class="cbox" name="jqg_teamManagementList_2"></td>'
		+'<td role="gridcell" style="display:none;" title="2" aria-describedby="teamManagementList_id">2</td>'
		+'<td role="gridcell" style="text-align:center;" title=" 修改 |   删除 " aria-describedby="teamManagementList_operation">'
		+' <a href="javascript:;" onclick="updateOperator(2);"><span>修改</span></a> |   <a href="javascript:;" onclick="deleteOperator(2)"><span>删除</span></a></td>'
		+'<td role="gridcell" style="text-align:center;" title="村（社区）网格员服务队" aria-describedby="teamManagementList_name">村（社区）网格员服务队</td>'
		+'<td role="gridcell" style="text-align:center;" title="3" aria-describedby="teamManagementList_memberNum">3</td>'
		+'<td role="gridcell" style="text-align:center;" title="" aria-describedby="teamManagementList_remark"></td></tr>'
		+'<tr id="3" tabindex="-1" role="row" class="ui-widget-content jqgrow ui-row-ltr ">'
		+'<td role="gridcell" style="text-align:center;" aria-describedby="teamManagementList_cb">'
		+'<input role="checkbox" type="checkbox" id="jqg_teamManagementList_3" class="cbox" name="jqg_teamManagementList_3"></td>'
		+'<td role="gridcell" style="display:none;" title="3" aria-describedby="teamManagementList_id">3</td>'
		+'<td role="gridcell" style="text-align:center;" title=" 修改 |   删除 " aria-describedby="teamManagementList_operation">'
		+' <a href="javascript:;" onclick="updateOperator(3);"><span>修改</span></a> |   <a href="javascript:;" onclick="deleteOperator(3)"><span>删除</span></a></td>'
		+'<td role="gridcell" style="text-align:center;" title="党员先锋服务队" aria-describedby="teamManagementList_name">党员先锋服务队</td>'
		+'<td role="gridcell" style="text-align:center;" title="2" aria-describedby="teamManagementList_memberNum">2</td>'
		+'<td role="gridcell" style="text-align:center;" title="" aria-describedby="teamManagementList_remark"></td></tr>'
		+'<tr id="4" tabindex="-1" role="row" class="ui-widget-content jqgrow ui-row-ltr ">'
		+'<td role="gridcell" style="text-align:center;" aria-describedby="teamManagementList_cb">'
		+'<input role="checkbox" type="checkbox" id="jqg_teamManagementList_4" class="cbox" name="jqg_teamManagementList_4"></td>'
		+'<td role="gridcell" style="display:none;" title="4" aria-describedby="teamManagementList_id">4</td>'
		+'<td role="gridcell" style="text-align:center;" title=" 修改 |   删除 " aria-describedby="teamManagementList_operation">'
		+' <a href="javascript:;" onclick="updateOperator(4);"><span>修改</span></a> |   <a href="javascript:;" onclick="deleteOperator(4)"><span>删除</span></a></td>'
		+'<td role="gridcell" style="text-align:center;" title="社会志愿者服务队" aria-describedby="teamManagementList_name">社会志愿者服务队</td>'
		+'<td role="gridcell" style="text-align:center;" title="1" aria-describedby="teamManagementList_memberNum">1</td>'
		+'<td role="gridcell" style="text-align:center;" title="" aria-describedby="teamManagementList_remark"></td></tr>'
		+'</tbody></table>';
	
	
	jQuery("#teamManagementList").html(str);
	
	$("#maintenanceTeam").click(function(){
		$("#teamManagementDialog").createDialog({
			width: 850,
			height: 630,
			title:'维护队伍信息',
			url:'/fourTeamsManage/maintenanceTeamDlg.jsp',
			buttons: {
				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
});

function updateOperator(selectedId){
	
}

function deleteOperator(selectedId){
	
}
</script>