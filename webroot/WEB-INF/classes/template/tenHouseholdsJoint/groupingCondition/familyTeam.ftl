<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
			<@s.include value="/common/orgSelectedComponent.jsp"/>	
			<span class="lineBetween "></span>
			<@pop.JugePermissionTag ename='addGroupingCondition'>
				<a id="addFamilyTeam"  href="javascript:void(0)"><span>新增</span></a>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename='searchGroupingCondition'>
				<a id="searchFamilyTeam"  href="javascript:void(0)"><span>查询</span></a>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename='deleteGroupingCondition'>
				<a id="deleteFamilyTeam"  href="javascript:void(0)"><span>批量删除</span></a>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename='importGroupingCondition'>
				<a id="importFamilyTeam"  href="javascript:void(0)"><span>导入</span></a>
			</@pop.JugePermissionTag>
			<@pop.JugePermissionTag ename='managementGroupingCondition'>
				<a id="managementGroupingCondition"  href="javascript:void(0)"><span>管理组员</span></a>
			</@pop.JugePermissionTag>
			<a id="refresh"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="familyTeamList"></table>
		<div id="familyTeamListPager"></div>
	</div>
	<div id="familyTeamDialog"></div>
	<div id="manageFamilyTeamDialog"></div>
</div>

<script type="text/javascript">
<@pop.formatterProperty name="alarmCenter" domainName="@com.tianque.domain.property.PropertyTypes@ALARMCENTER" />
$(document).ready(function(){

	var orgId=getCurrentOrgId();
	
	$("#familyTeamList").jqGridFunction({
		url:'${path}/tenHouseholdsJoint/groupingCondition/queryFamilyTeamList.action',
		datatype: "json",
		postData:{
			"organizationId":orgId
		},
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:'organization.id',index:'organization.id',hidden:true,frozen:true,width:200,align:"center",hidedlg:true},
			{name:"operation",label:'操作',formatter:operatorFamilyTeamFormatter,frozen:true,width:90,sortable:false,align:"center"},
			{name:'teamCode', index:'teamCode',label:'分组编号', width:100, sortable:false, align:'center'}, 	
			{name:'teamName',index:'teamName',label:'分组名称',sortable:false,width:130,align:"center"},
			{name:'houseHolds',index:'houseHolds',label:'户数',sortable:false,width:30,align:"center"},
			{name:'houseMaster', index:'houseMaster',label:'联户长', width:130, sortable:false, align:'center'},
			{name:'houseMastCertificateNo',index:'houseMastCertificateNo',label:'联户长证件号',sortable:false,width:80,align:"center"},
			{name:'organization.orgName',index:'orgName',label:'所属区域',sortable:false,width:200,align:"center"},
			{name:'alarmCenter',index:'alarmCenter',label:'所属接警中心',formatter:alarmCenterFormatter,sortable:false,width:200,align:"center"},
			{name:'createDate',index:'createDate',label:'创建时间',sortable:false,width:160,align:"center"}
		],
		multiselect:true,
		showColModelButton:true,
		ondblClickRow: viewFamilyTeam
	});
	
		//新增按钮事件
	$("#addFamilyTeam").click(function(event){
		if(!isGrid()){
			$.messageBox({level:"warn",message:"请先选择网格级别组织机构进行新增！"});
			return;
		}
		$("#familyTeamDialog").createDialog({
			title:"新增分组",
			width: 680,
			height: 280,
			url:'${path}/tenHouseholdsJoint/groupingCondition/dispatchOperate.action?mode=add&dailogName=familyTeamDialog&organizationId='+orgId,
			buttons: {
				"保存" : function(event){
					$("#mainFamilyTeamForm").submit();
				},
				"取消" : function(event){
					$(this).dialog("close");
				}
			}
		});
	});
	
	//管理组员
	$("#managementGroupingCondition").click(function(){
		var rowIds = $("#familyTeamList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行操作！"});
			 return;
		}else if(rowIds.length>1){
			 $.messageBox({level:"warn",message:"只能选择一条信息进行操作！"});
			 return;
		}
		var familyTeam = $("#familyTeamList").getRowData(rowIds);
		$("#manageFamilyTeamDialog").createDialog({
			width: 1000,
			height: 540,
			datatype: "json",
			title:'管理组员',
			url:'${path}/tenHouseholdsJoint/groupingCondition/dispatchOperate.action?mode=manage&organizationId='+familyTeam["organization.id"]+'&selectedId='+rowIds,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#deleteFamilyTeam").click(function(){
		deleteFun();
	});
	//删除方法
	function deleteFun(){
		var rowIds = $("#familyTeamList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteFamilyTeam(event,rowIds);
	}
	$("#searchFamilyTeam").click(function(event){
		$("#familyTeamDialog").createDialog({
			width: 680,
			height: 280,
			datatype: "json",
			title:'分组信息查询',
			url:'${path}/tenHouseholdsJoint/groupingCondition/dispatchOperate.action?mode=search&dailogName=familyTeamDialog&organizationId='+orgId,
			buttons: {
				"查询" : function(event){
					searchFamilyTeamInfo();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#refresh").click(function(event){
		reloadFun();
	});
	
})
//操作
function operatorFamilyTeamFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateGroupingCondition'><a href='javascript:;' onclick='updateFamilyTeam(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteGroupingCondition'><a href='javascript:;' onclick='deleteFamilyTeam(event,"+(rowData.id)+");'><span>删除</span></a></pop:JugePermissionTag>";
}

$("#importFamilyTeam").click(function(){
	$("#familyTeamDialog").createDialog({
			width: 400,
				height: 230,
				title:'数据导入',
				url:PATH+'/common/import.jsp?isNew=1&dataType=importTenHouseholdsGroup&dialog=familyTeamDialog&startRow=6&templates=IMPORTTENHOUSEHOLDSGROUP&listName=familyTeamList',
				buttons:{
						"导入" : function(event){
					      $("#mForm").submit();
					   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				},
				shouldEmptyHtml:false
	});
});

//修改成员信息
function updateFamilyTeam(event,id){
	var familyTeam = $("#familyTeamList").getRowData(id);
	$("#familyTeamDialog").createDialog({
		title:'修改分组信息',
		width: 680,
		height: 280,
		url:'${path}/tenHouseholdsJoint/groupingCondition/dispatchOperate.action?mode=edit&selectedId='+familyTeam.id,
		buttons: {
			"保存" : function(event){
	   			$("#mainFamilyTeamForm").submit();
   			},
   			"关闭" : function(){
		        	$(this).dialog("close");
				}
		}
	});
}

//获取选中列的ID
function getSelectedIds(){
	var selectedIds = $("#familyTeamList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

//删除操作
function deleteFamilyTeam(event,rowIds){
	if(rowIds==null){
		$.messageBox({level:'warn',message:'没有选中任何记录！'});
		return ;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除所选的分组信息记录吗?",
		okFunc: function(){
			$.ajax({
				url:PATH+"/tenHouseholdsJoint/groupingCondition/delFamilyTeamOperation.action",
				type:"post",
				data:{
					"mode":"delete",
					"ids":rowIds+""
					},
				success:function(data){
						if(data == true || data =="true"){
							$("#familyTeamList").trigger("reloadGrid");
						    $.messageBox({message:"已经成功删除选中的记录"});
						}else{
							$.messageBox({
								message : data,
								level : 'error'
							});
							return;
						}
			    }
		    });
	   }
 	});
 	
	var evt = event || window.event;
	if(typeof evt!="object"){return false;}
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function viewFamilyTeam(rowid){
	if(rowid==null){
 		return;
	}
	$("#familyTeamDialog").createDialog({
		width: 680,
		height: 280,
		title:'查看分组信息',
		modal : true,
		url:'${path}/tenHouseholdsJoint/groupingCondition/dispatchOperate.action?mode=view&selectedId='+rowid,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function searchFamilyTeamInfo() {
	var initParam = {
		"organizationId":$("#orgId").val(),
		"familyTeam.organization.orgInternalCode":$("#orgCode").val(),
		"familyTeam.teamCode":$("#teamCode").val(),
		"familyTeam.teamName":$("#teamName").val(),
		"familyTeam.houseHolds":$("#houseHolds").val(),
		"familyTeam.houseMaster":$("#houseMaster").val(),
		"familyTeam.houseMastCertificateNo":$("#houseMastCertificateNo").val(),
		"familyTeam.alarmCenter.id":$("#alarmCenter").val(),
	}
	$("#familyTeamList").setGridParam({
			url:'${path}/tenHouseholdsJoint/groupingCondition/searchFamilyTeamInfo.action',
			datatype : 'json',
			page:1
	});
	$("#familyTeamList").setPostData(initParam);
	$("#familyTeamList").trigger("reloadGrid");
}
function reloadFun(){
	var initParam = {
		"organizationId":getCurrentOrgId()
	}
	$("#familyTeamList").setGridParam({
			url:'${path}/tenHouseholdsJoint/groupingCondition/queryFamilyTeamList.action',
			datatype : 'json',
			page:1
	});
	$("#familyTeamList").setPostData(initParam);
	$("#familyTeamList").trigger("reloadGrid");		
}

</script>