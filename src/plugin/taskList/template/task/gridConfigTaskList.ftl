<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<div class="content">
<input type="hidden" id="type" value="<@s.property value='#parameters.type[0]'/>">

	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="btnbanner">
				<@s.include value="/common/orgSelectedComponent.jsp"/>
		    </div>
<!-- 		    <div class="ui-widget autosearch"> -->
<!-- 					<input class="basic-input" type="text" value="请输入职能部门名称" id="searchByCondition" maxlength="18" style="width: 150px;" onblur="value=(this.value=='')?'请输入职能部门名称':this.value;" onfocus="value=(this.value=='请输入职能部门名称')?'':this.value;"/> -->
<!-- 					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button> -->
<!-- 			</div> -->
<!-- 			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a> -->
			 <@pop.JugePermissionTag ename="addGridConfig${(parameters.type[0])!}">
				<a id="addConfigTask" href="javascript:;"><span>配置</span></a>
			</@pop.JugePermissionTag>
			
			 <@pop.JugePermissionTag ename="updateGridConfig${(parameters.type[0])!}">
				<a id="updateConfigTask" href="javascript:void(0)"><span>修改配置</span></a>
				</@pop.JugePermissionTag>
				
			<@pop.JugePermissionTag ename="deleteGridConfig${(parameters.type[0])!}">
			   <a id="deleteConfigTask" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除配置</span></a>
			</@pop.JugePermissionTag>
			
			<@pop.JugePermissionTag ename="viewGridConfig${(parameters.type[0])!}">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			</@pop.JugePermissionTag>
		
			<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		</div>
	</div>
    <div style="width: 100%;">
		<table id="gridConfigTaskList"></table>
		<div id="gridConfigTaskListPager"></div>
	</div>
	<div id="gridConfigTaskDialog"></div>
</div>

<script type="text/javascript">
<@pop.formatterProperty name="orgType" domainName="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_TYPE" />
<@pop.formatterProperty name="orgLevel" domainName="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" />
var type= $("#type").val();
$(document).ready(function(){
	$("#gridConfigTaskList").jqGridFunction({
		url:"${path}/baseInfo/gridConfigTaskManage/findGridConfigTasks.action",
// 		url:"/sysadmin/orgManage/ajaxOrgPage.action",
		datatype: "json",
		postData: {
	      "organization.id":getCurrentOrgId(),
	      "type":type
        },
// 	   	colNames:['id','orgLevelCopy','子部门名称',  '子部门类型','子部门级别','配置状态'],
	   	colModel:[
	   		{name:'organization.id',index:'id',hidden:true,sortable:false},
	   		{name:'orgLevelCopy',hidden:true,formatter:orgLevelCopyFormatter},
	   		{name:'organization.orgName',label:'职能部门名称',index:'orgName',sortable:false},
	   		{name:'orgType',label:'职能部门类型',index:'orgType',sortable:false,formatter:orgTypeFormatter},
	   		{name:'orgLevel',label:'职能部门级别',index:'orgLevel', sortable:false,formatter:orgLevelFormatter},
	   		{name:'isHasConfig',label:'配置状态',sortable:false,formatter:countSelectOrgsFormatter},
	   	],
		multiselect:true,
		viewrecords:true,
		onSelectAll:function(aRowids,status){ locationOperator(event,aRowids);}
		
	});
	function countSelectOrgsFormatter(el, options, rowData){
		if(rowData.isHasConfig){
			return "已配置";
		}
		return "未配置";
	}
	function orgLevelCopyFormatter(el, options, rowData){
		return rowData.organization.orgLevel.id;
	}
	
	$("#addConfigTask").click(function(e){
		var rowId=$('#gridConfigTaskList').jqGrid('getGridParam','selrow');
		var rowData = $('#gridConfigTaskList').jqGrid('getRowData',rowId);
		if(rowData.isHasConfig=="已配置"){
			$.messageBox({level:'warn',message:"请选择一条未配置数据新增！"});
			return;
		}
		addOrUpdate("add");
	});
	
	$("#updateConfigTask").click(function(e){
		var rowId=$('#gridConfigTaskList').jqGrid('getGridParam','selrow');
		var rowData = $('#gridConfigTaskList').jqGrid('getRowData',rowId);
		if(rowData.isHasConfig=="未配置"){
			$.messageBox({level:'warn',message:"请选择一条已配置数据修改！"});
			return;
		}
		addOrUpdate("update");
	});
	//新增修改调用
	function addOrUpdate(mode){
		var selectedId =$("#gridConfigTaskList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null || selectedId==undefined || selectedId==''){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:"warn",message:"只能选择一条数据进行操作！"});
	 		return;
		}
		var selectOrg=$('#gridConfigTaskList').jqGrid('getRowData',selectedId[0]);
		$("#gridConfigTaskDialog").createDialog({
			width: 550,
			height: 520,
			title : '网格员配置',
			url : '${path}/task/gridConfigTask/organizationMutilSelectDialog.jsp?orgId='+selectedId[0]+"&orgLevel="+selectOrg.orgLevelCopy+"&mode="+mode+"&type="+type,
			buttons : {
				"保存" : function() {
					$("#configTaskSelectForm").submit();
				},
				"关闭" : function() {
					$(this).dialog("close");
				}
			}
		});
	}
	//查看
	$("#view").click(function(e){
		var selectedId =$("#gridConfigTaskList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null || selectedId==undefined || selectedId==''){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:"warn",message:"只能选择一条数据进行操作！"});
	 		return;
		}
		var rowId=$('#gridConfigTaskList').jqGrid('getGridParam','selrow');
		var rowData = $('#gridConfigTaskList').jqGrid('getRowData',rowId);
		if(rowData.isHasConfig=="未配置"){
			$.messageBox({level:'warn',message:"请选择一条已配置数据查看！"});
			return;
		}
		addOrUpdate("view");
	});
	
// 	$("#refreshSearchKey").click(function(){
// 	    $("#searchByCondition").attr("value","请输入职能部门名称");
// 	});
	//刷新
	$("#reload").click(function(){
		reloads();
	});
	
	$("#deleteConfigTask").click(function(e){
		var selectedId =$("#gridConfigTaskList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null || selectedId==undefined || selectedId==''){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		for(var i=0;i<selectedId.length;i++){
			var rowData = $('#gridConfigTaskList').jqGrid('getRowData',selectedId[0]);
			if(rowData.isHasConfig=="未配置"){
				$.messageBox({level:'warn',message:"请选择已配置的数据删除！"});
				return;
			}
		}
		$.confirm({
			title:"确认删除",
			message:"配置信息删除后，将无法恢复,您确认删除所选中的职能部门的配置信息吗?",
			okFunc: function() {
				$.ajax({
					url:'${path}/baseInfo/gridConfigTaskManage/deleteCheckedOrg.action?ids='+selectedId+'&type='+type,
					success:function(data){
						if(typeof(data)=='boolean'&&data == true){
							$.messageBox({message:"已经成功删除配置信息!"});
							reloads();
						} else{
							$.messageBox(data);
						}
					}
				});
			}
		});
	});
});

//刷新
function reloads(){
	$("#gridConfigTaskList").setGridParam({
		url:"${path}/baseInfo/gridConfigTaskManage/findGridConfigTasks.action",
		datatype: "json",
		page:1
	});
	var postData={'organization.id': $('#currentOrgId').val(),'type':type};
	$("#gridConfigTaskList").setPostData(postData);
	$("#gridConfigTaskList").trigger("reloadGrid");
// 	$("#searchByCondition").attr("value","请输入职能部门名称");
}
</script>