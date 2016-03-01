<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24" id="viewMaintenanceTeama">
	<div class='clearLine'></div>
	<div style="width: 99.5%;margin-top:6px">
		<table id="maintenanceTeamList"> </table>
		<div id="maintenanceTeamListPager"></div>
	</div>
</div>

<div id="maintenanceTeamDialog"></div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="politics" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
$(function(){
	$("#maintenanceTeamList").jqGridFunction({
		url:'${path}/fourTeamsManage/teamMemberManagementList.action',
		postData:{
				"id":${param.id}
			},
		datatype: "json",
		colModel:[
			{name:"id",index:'id',hidden:true},
			{name:'duty',index:"duty",label:'职务',sortable:true,width:100,align:"center"},
			{name:'names',index:"names",label:'姓名',sortable:true,width:100,align:"center"},
			{name:'gender.id',index:'gender',label:'性别',sortable:true,width:60,align:"center",formatter:genderFormatter},
			{name:'memberDepartement',index:"memberDepartement",label:'所在单位',sortable:true,width:100,align:"center",hidden:true},
			{name:'politics.id',index:'politics',label:'政治面貌',sortable:true,width:120,align:"center",formatter:politicsFormatter,hidden:true},
	        {name:'specialty',index:'specialty',label:'特长',sortable:true,width:110,align:"center",hidden:true},
	        {name:'mobile',index:'mobile',label:'联系手机',sortable:true,width:70,align:"center"},
	        {name:'birthday',index:'birthday',label:'出生日期',sortable:true,width:100,align:"center"},
	        {name:'telephone',index:'telephone',label:'联系电话',sortable:true,width:100,align:"center",hidden:true},
	        {name:'serviceName',index:'serviceName',label:'服务网格',sortable:true,width:100,align:"center"},
	        {name:'orgAdminName',index:'orgAdminName',label:'网格管理员姓名',sortable:true,width:100,align:"center",hidden:true},
	        {name:'orgAdminTelephone',index:'orgAdminTelephone',label:'网格管理员联系电话',sortable:true,width:100,align:"center",hidden:true},
	        {name:'comments',index:'comments',label:'备注',sortable:true,width:130,align:"center"}
		],
		height:320,
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		ondblClickRow: doubleClickTable,
		onSelectRow:function(){}
	});
});
</script>