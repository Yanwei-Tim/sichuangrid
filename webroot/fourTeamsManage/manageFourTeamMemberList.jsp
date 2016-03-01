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
var dialogWidth = 850;
var dialogHeight = 300;
var rowData = getSelectedId();
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:updateOperator("+rowData.id+")'><span>修改</span></a> | <a href='javascript:deleteOperator("+rowData.id+")'><span>删除</span></a>";
}
$(function(){
	$("#maintenanceTeamList").jqGridFunction({
		url:'${path}/fourTeamsManage/teamMemberManagementList.action',
		postData:{
				"id":${param.id}
			},
		datatype: "json",
		colModel:[
			{name:"id",index:'id',hidden:true,frozen:true},
			{name:"operation",index:"id",label:"操作",sortable:false,formatter:operatorFormatter,width:80,align:"center"},
			{name:'duty',index:"duty",label:'职务',sortable:true,width:100,align:"center"},
			{name:'names',index:"names",label:'姓名',sortable:true,width:100,align:"center"},
			{name:'gender.id',index:'gender',label:'性别',sortable:true,width:60,align:"center",formatter:genderFormatter},
			{name:'memberDepartement',index:"memberDepartement",label:'所在单位',sortable:true,width:100,align:"center",hidden:true},
			{name:'politics.id',index:'politics',label:'政治面貌',sortable:true,width:120,align:"center",formatter:politicsFormatter,hidden:true},
	        {name:'specialty',index:'specialty',label:'特长',sortable:true,width:110,align:"center",hidden:true},
	        {name:'mobile',index:'mobile',label:'联系手机',sortable:true,width:80,align:"center"},
	        {name:'birthday',index:'birthday',label:'出生日期',sortable:true,width:70,align:"center"},
	        {name:'telephone',index:'telephone',label:'联系电话',sortable:true,width:100,align:"center",hidden:true},
	        {name:'serviceName',index:'serviceName',label:'服务网格',sortable:true,width:100,align:"center"},
	        {name:'orgAdminName',index:'orgAdminName',label:'网格管理员姓名',sortable:true,width:100,align:"center",hidden:true},
	        {name:'orgAdminTelephone',index:'orgAdminTelephone',label:'网格管理员联系电话',sortable:true,width:100,align:"center",hidden:true},
	        {name:'comments',index:'comments',label:'备注',sortable:true,width:130,align:"center"}
		],
		multiselect:true,
		height:350,
		onSelectAll:function(aRowids,status){},
		showColModelButton:true,
		onSelectRow:selectRow
	});
});
function updateOperator(selectedId){
	$("#maintenanceTeamDialog").createDialog({
		width: 820,
		height: 450,
		title:'修改成员信息',
		url:'${path}/fourTeamsManage/maintainMemberEditList.action?id='+selectedId,
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


function selectRow() {
	var count = $("#maintenanceTeamList").jqGrid("getGridParam", "records");
		var selectedCounts = getActualjqGridMultiSelectCount("maintenanceTeamList");
		if (selectedCounts == count) {
			jqGridMultiSelectState("maintenanceTeamList", true);
		} else {
			jqGridMultiSelectState("maintenanceTeamList", false);
		}

}


function deleteOperator(selectedId){

	$.confirm({
		title:"确认删除",
		message:"该删除后就不能还原，确认删除？",
		width: 300,
		okFunc: function(){
			$.ajax({
				url:"${path}/fourTeamsManage/deleteFourTeamMember.action?id="+selectedId+"&parentId="+${param.id},
				success:function(data){
					if(data!=true && data!="true" ){
						$.messageBox({message:data,level:"error"});
						return ;
					}
					$("#maintenanceTeamList").trigger("reloadGrid");
					$.messageBox({message:"四支队伍成员删除成功！"});
				}
			});
		}
	});

}

</script>