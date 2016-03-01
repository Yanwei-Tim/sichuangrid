<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div style="width:100%;" >
		<table id="_serviceTeamList"></table>
	</div>
</div>


<script type="text/javascript">
function optionFormatter(el,options,rowData){
	return '<a href="javascript:void(0);" onclick="javascript:logout('+rowData.teamId+','+rowData.memberId+')" class="search"><span>注销</span></a>';
}

function logout(teamId, memberId){
	$("#_serviceTeamMemberLogoutDialog").createDialog({
		title:"服务成员注销对话框",
		width: 440,
		height: 180,
		url:'${path}/baseinfo/serviceTeamManage/serviceTeamMember/serviceTeamMemberLogoutDig.jsp',
		postData:{
			'teamId':teamId,
			'memberId':memberId,
			'baseId':${searchServiceTeamMemberVo.baseId}
		},
		buttons: {
			"确认" : function(){
				$("#_serviceTeamMemberLogoutForm").submit();
		   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function objectCountFormatter(el,options,rowData) {
	 return "人员:"
	+rowData.objectFromPopulationCount+"&nbsp;&nbsp;其他:"+rowData.objectFromLocationCount;
	
}

$(document).ready(function(){
	$("#_serviceTeamList").jqGridFunction({
		url:'${path}/baseinfo/serviceTeamMemberManage/findMemberRelationInTeams.action',
		postData:{
			"searchServiceTeamMemberVo.baseId":'${searchServiceTeamMemberVo.baseId}'
		},
		height:100,
	    colNames:['teamId','memberId','所属职责','团队名称','所属团队','团队类别','服务对象','服务记录','操作'],
	   	colModel:[
	   	    {name:"teamId",index:"teamId",key:true,hidden:true},
	        {name:"memberId",index:"memberId",hidden:true},
	        {name:'memberPosition',align:'center',sortable:false,width:150},
	        {name:'teamName',align:'center',width:100,sortable:false},
	        {name:'teamClazz',align:'center',width:60,sortable:false},
	        {name:'teamType',align:'center',sortable:false,width:150},
	        {name:'objectCouut',align:'center',formatter:objectCountFormatter,width:150,sortable:false},
	        {name:'recordCount',align:'center',width:100,sortable:false},
	        {name:'option',align:'center',formatter:optionFormatter,width:100,sortable:false}
		],
		sortName:'teamId',
		ondblClickRow: function(){}
	});
})
</script>