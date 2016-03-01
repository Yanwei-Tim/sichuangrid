<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<div>
<div style="display: none;">
mode:<input id="mode" type="text" name="mode" value="${mode}" /> 
orgId:<input id="autonomyTeamOrgId" type="text"	name="serviceManageTeam.organization.id" value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
teamId<input type="text" name="serviceManageTeam.id" value="${serviceManageTeam.id }" /> 
</div>
<table width="200" class="tablelist" id="serviceMList"></table>
</div>
<div id="serviceMListPager"></div>

<script>

$(document).ready(function(){

	$("#serviceMList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','orgId','职务','姓名','性别','身份证号码','联系手机','服务对象','服务记录','最新服务时间'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"orgId",index:"orgId",hidden:true,frozen:true},
	        {name:"position",index:"position",hidden:true,frozen:true},
	        {name:'name',index:"name",width:50},
	        {name:'sex',index:'sex',width:50,frozen:true},
	        {name:'idCard',index:'idCard',width:50,frozen:true},
	        {name:'mobile',index:'mobile',sortable:false,width:200},
	        {name:'bak1', index:'bak1', width:50},
	        {name:'bak',index:'bak', width:60,align:"center"},
	        {name:'bak',index:'bak', width:60,align:"center"}
		],
		multiselect:false,
		shrinkToFit:true,
		showColModelButton:false
	});
	$("#serviceMList").setGridParam({
		url:"${path}/baseinfo/serviceTeamMember/findServiceTeamMemberInTeam.action",
		datatype: "json",
		page:1
	});
	$("#serviceMList").setPostData({
		"teamId":"${serviceManageTeam.id}",
   	});
	$("#serviceMList").trigger("reloadGrid");
	jQuery("#serviceMList").jqGrid('setFrozenColumns');

	
	
});

</script>
