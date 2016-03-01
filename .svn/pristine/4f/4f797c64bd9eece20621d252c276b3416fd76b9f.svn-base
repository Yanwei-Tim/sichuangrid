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
		<pop:JugePermissionTag ename="searchPioneerServiceTeam">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="managePioneerTeamMember">
			<a id="manageTeamMember" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
		
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="pioneerServiceTeamList">
		</table>
		<div id="pioneerServiceTeamListPager"></div>
	</div>
	<div id="pioneerServiceTeamDialog"></div>
	<div id="noticeDialog"></div>
	<div id="pioneerServiceTeamMaintanceDialog"></div>
</div>
<script type="text/javascript">
$(function(){
	// 生成列
	$("#pioneerServiceTeamList").jqGridFunction({
		datatype: "local",
		colModel:[
			{name:"id",index:'id',hidden:true},
	    	{name:'name', index:'name',label:'党员先锋服务队名称', width:300, sortable:true, align:'center', hidden:false}, 	
	    	{name:'memberNum',index:'memberNum',label:'党员先锋服务队成员数',sortable:true,width:300,align:"center"},
	    	{name:'remark', index:'remark',label:'备注', width:500, sortable:true, align:'center', hidden:false}	
	    	//{name:'seq', index:'seq',label:'排序', width:100, sortable:true, align:'center', hidden:false}, 	
// 	    	{name:'createUser', index:'createUser',label:'创建人', width:100, sortable:true, align:'center', hidden:true}, 	
// 	    	{name:'updateUser', index:'updateUser',label:'修改人', width:100, sortable:true, align:'center', hidden:true}, 
// 	    	{name:'createDate', index:'createDate',label:'创建时间', width:100, sortable:true, align:'center', hidden:true}, 	
// 	    	{name:'updateDate', index:'updateDate',label:'修改时间', width:100, sortable:true, align:'center', hidden:true}
		],
		multiselect:true
	});
	
	jQuery("#pioneerServiceTeamList").jqGrid('setFrozenColumns');
	
	var pioneerdata = [
   		{id:"1",name:"县委组织部党员先锋服务队",memberNum:"0",remark:""},
   		{id:"2",name:"县国土局党员先锋服务队",memberNum:"0",remark:""}
   	];
   	for(var i=0;i<=pioneerdata.length;i++){
   		jQuery("#pioneerServiceTeamList").jqGrid('addRowData',i+1,pioneerdata[i]);
   	}
	
	$("#manageTeamMember").click(function(){
		var rowIds = $("#pioneerServiceTeamList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length == 0){
			$.messageBox({level:'warn',message:'没有选中任何记录！'});
			return ;
		}
		if(rowIds.length > 1){
			$.messageBox({level:'warn',message:'只能选中一条记录！'});
			return ;
		}
		$("#pioneerServiceTeamDialog").createDialog({
			width: 850,
			height: 630,
			title:'维护成员信息',
			url:'/fourTeamsManage/manageTeamMemberDlg.jsp',
			buttons: {
				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
});
</script>