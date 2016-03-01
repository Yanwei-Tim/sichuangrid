<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="TeamRelation" class="container container_24">
	<input id="_orgId" type="hidden" name="searchServiceManageTeamVo.orgId" value="${organizationId}" />
	<input id="_memberId" type="hidden" name="searchServiceManageTeamVo.memberId" value="${selectedIds}" />
	<div class="content" >
		<table id="holdServiceTeamList"></table>
		<div id="holdServiceTeamListPager"></div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="teamClazz" domainName="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />

function positionFormatter(el,options,rowData){
	var content = '<select name="position" id="_position_'+rowData.teamId+'">';
	content = content.concat("<option value='0' selected='selected'>请选择</option>");
	$.ajax({
		url:"${path}/baseinfo/serviceTeamMemberManage/findPropertyDicts.action?teamClazzId="+rowData.teamClazz.id,
		cache: false,
		async: false,
		success:function(dicts){
			if(null != dicts) {
				$.each(dicts, function(ind, val){
					content = content.concat("<option value="+val.id+" >"+val.displayName+"</option>");
				});
			}
		}
	});
	content = content.concat("</select>");
	return content;
}

function operationFormatter(el,options,rowData){
	return '<a href="javascript:void(0);" onclick="javascript:emitRelation('+rowData.teamId+')" class="search"><span>解除关系</span></a> | <a href="javascript:void(0);" onclick="javascript:logout('+rowData.teamId+','+rowData.memberId+')" class="search"><span>注销</span></a>';
}

function emitRelation(teamId) {
	$.ajax({
		async: false,
		url:"${path}/baseinfo/serviceTeamMemberManage/emitRelation.action",
	   	data:{
	   		"searchServiceTeamMemberVo.baseId":$("#_memberId").val(),
			"searchServiceTeamMemberVo.teamId":teamId
        },
		success:function(responseData){
			if(responseData) 
				$("#holdServiceTeamList").delRowData(teamId);
			else {
				 $.messageBox({message:responseData, level:"error"});	
			}
		}
	});
	$("#holdServiceTeamList").delRowData(teamId);
}

function logout(teamId, memberId){
	$("#_serviceTeamMemberLogoutDialog").createDialog({
		title:"服务成员注销对话框",
		width: 440,
		height: 180,
		url:'${path}/baseinfo/serviceTeamManage/serviceTeamMember/serviceTeamMemberLogoutDig.jsp',
		postData:{
			'teamId':teamId,
			'memberId':memberId
		},
		buttons: {
			"确定" : function(){
				$("#_serviceTeamMemberLogoutForm").submit();
		    },
		    "关闭" : function(){
		        $(this).dialog("close");
		    }
		}
	});
}
function objectCountFormatter(el,options,rowData) {
	 return "人员:"+rowData.objectFromPopulationCount+"&nbsp;&nbsp;其他:"+rowData.objectFromLocationCount;
}
$(document).ready(function() {
	
	$("#holdServiceTeamList").jqGridFunction({
		datatype: "local",
		colNames:['teamId','memberId','positionId','所属团队','团队类别','团队名称','职务','服务记录','服务对象','操作'],
		colModel:[
			{name:"teamId",index:'teamId', hidden:true,key:true},
			{name:"memberId",index:"memberId",hidden:true},
			{name:"positionId",hidden:true},
	        {name:'teamClazz',width:100},
	        {name:'teamType',width:130},
	        {name:'teamName', width:150},
	        {name:'memberPosition',width:80,sortable:false},
	        {name:'recordCount',align:'center',width:80,sortable:false},
	        {name:'objectCouut',align:'center',formatter:objectCountFormatter,width:150,sortable:false},
	        {name:'operation',formatter:operationFormatter,width:90,sortable:false}
	    ],
	    height:280,
	    showColModelButton:false
	});
	jQuery("#holdServiceTeamList").jqGrid('setFrozenColumns');
	
	$("#holdServiceTeamList").setGridParam({
		url:'${path}/baseinfo/serviceTeamMemberManage/findMemberRelationInTeams.action',
		datatype: "json",
		page:1
	});
	$("#holdServiceTeamList").setPostData({
		"searchServiceTeamMemberVo.baseId":$("#_memberId").val()
   	});
	$("#holdServiceTeamList").trigger("reloadGrid");
});

</script>