<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div class="content">
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<@s.include value="/common/orgSelectedComponent.jsp"/>
			<div class="userState">
				<select class="basic-input" id="searchServiceTeamMemberVo_orgScope">
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" selected="selected">仅显示本级</option>
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">所有下辖</option>
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
				</select>
			</div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入<@s.text name="search.population.common.condition"/>" id="searchServiceTeamMemberVo_condition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入<@s.text name="search.population.common.condition"/>':this.value;" onfocus="value=(this.value=='请输入<@s.text name="search.population.common.condition"/>')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
			<@pop.JugePermissionTag ename="searchServiceTeamMember">
			<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</@pop.JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
		<@pop.JugePermissionTag ename="addServiceTeamMember">
		<a id="add" href="javascript:void(0)"><span>新增</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="updateServiceTeamMember">
		<a id="update"  href="javascript:void(0)"><span>修改</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="deleteServiceTeamMember">
		<a id="delete"  href="javascript:void(0)"><span>删除</span></a>
		</@pop.JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="serviceTeamMemberList"></table>
		<div id="serviceTeamMemberListPager"></div>
	</div>
	<div id="_serviceTeamMemberDialog"></div>
	<div id="_serviceTeamMemberLogoutDialog"></div>
</div>
<div style="display: none;">
	<@pop.JugePermissionTag ename="serviceTeamMemberManagement">
		<span id="title"><@s.property value="#request.name" escape=false/></span>
	</@pop.JugePermissionTag>
</div>
<script type="text/javascript">
<@pop.formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
var dialogWidth=820;
var dialogHeight=610;
function checkedOrgId(orgID){
	if(orgID != USER_ORG_ID){
		$.messageBox({
			message:"当前用户只能操作所在层级的组织！",
			level: "warn"
		 });
		return false;
	}else return true;
}
function operateFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='xx(event,"+rowData.id+")'><span>服务对象</span></a> | <a href='javascript:;' onclick='xx(event,"+rowData.id+");'><span>所属团队</span></a>";
}
function viewServiceTeamMember(selectedId) {
	$("#_serviceTeamMemberDialog").createDialog({
		title:"查看服务成员信息",
		width: 860,
		height: 500,
		url:'${path}/baseinfo/serviceTeamManage/serviceTeamMember/viewServiceTeamMemberDlg.jsp?baseId='+selectedId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function updateOperator(event,selectedId){
	if(!checkedOrgId(getCurrentOrgId())){
		return;
	}
	$("#_serviceTeamMemberDialog").createDialog({
		title:'修改服务成员',
		width: 680,
		height: 260,
		url:'${path}/plugin/serviceTeamMemberManage/serviceTeamMemberDispatch.action?mode=edit&dailogName=_serviceTeamMemberDialog&serviceTeamMemberBase.id='+selectedId,
		buttons: {
			"保存并关闭" : function(event){
	   			$("#serviceTeamMemberForm").submit();
	   			$(this).dialog("close");
   			}
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function deleteOperator(event,selectedIds){
	if(!checkedOrgId(getCurrentOrgId())){
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/plugin/serviceTeamMemberManage/deleteTeamMemberBases.action?&selectedIds='+selectedIds,
				success:function(data){
					if(data.bol) {
						$.messageBox({message:data.msg});
					}else{
						$.messageBox({message:data.msg, level:'error'});
					}
					$("#serviceTeamMemberList").trigger("reloadGrid");
				}
			});
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
$(document).ready(function(){
	$("#serviceTeamMemberList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colNames:['id','orgId',"操作",'姓名','性别','所在团队数量','服务记录数','联系手机'],
		colModel:[
			{name:"id",index:"id",key:true,sortable:false,hidden:true},
			{name:"organization.id",index:"organization.id",sortable:false,hidden:true},
			{name:"operation",index:"id",sortable:false,formatter:operateFormatter,width:150,align:"center"},
			{name:'name',index:"name",width:100,frozen:true,sortable:true},
			{name:'gender',index:'gender',width:80,formatter:genderFormatter,sortable:true,align:"center"},
			{name:"",index:"",sortable:false,align:"center"},
			{name:"",index:"",sortable:false,align:"center"},
			{name:'mobile',index:'mobile',sortable:true,width:150,align:"center"}
		],
		loadComplete: afterLoad,
		multiselect:true,
		onSelectRow:selectRow,
		showColModelButton:false,
	});
	$("#serviceTeamMemberList").jqGrid('setFrozenColumns');
	loadTeamMembers();
	function afterLoad() {
	}
	function selectRow(){
		var selectedCounts = getActualjqGridMultiSelectCount("serviceTeamMemberList");
		var count = $("#serviceTeamMemberList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("serviceTeamMemberList", true);
		}else{
			jqGridMultiSelectState("serviceTeamMemberList", false);
		}
	}
	$("#searchServiceTeamMemberVo_orgScope").change(function(){
		loadTeamMembers();
	});
	$("#searchServiceTeamMemberVo_teamClassId").change(function(){
		loadTeamMembers();
	});
	$("#refreshSearchKey").click(function(event){
		$("#searchServiceTeamMemberVo_condition").attr("value","请输入姓名或身份证号码");
	});
	$("#fastSearchButton").click(function(){
		loadTeamMembers();
	});
	$("#search").click(function(){
		$("#_serviceTeamMemberDialog").createDialog({
			title:"服务成员查询-请输入查询条件",
			width: 600,
			height: 240,
			url:'${path}/plugin/serviceTeamMemberManage/serviceTeamMemberDispatch.action?mode=search&searchServiceTeamMemberVo.orgId='+getCurrentOrgId(),
			buttons: {
				"查询" : function(event){
					loadData($("#serviceTeamMember_form").serializeArray());
					$(this).dialog("close");
				},
				"关闭" : function(){
		        	$(this).dialog("close");
				}
			}

		});
	});
	$("#add").click(function(event){
		if(!checkedOrgId(getCurrentOrgId())){
			return;}
		$("#_serviceTeamMemberDialog").createDialog({
			title:"新增服务成员",
			width: 680,
			height: 260,
			url:'${path}/plugin/serviceTeamMemberManage/serviceTeamMemberDispatch.action?mode=add&dailogName=_serviceTeamMemberDialog&serviceTeamMemberBase.organization.id='+getCurrentOrgId(),
			buttons: {
				"保存并继续" : function(event){
		   			$("#serviceTeamMemberForm").submit();
		   			$("#isSubmit").val("false");
				},
				"保存并关闭" : function(event){
					$("#serviceTeamMemberForm").submit();
		   			$("#isSubmit").val("true");
				}
			}
		});
	});
	$("#update").click(function(){
		if(countSelectedIds() != 1) {
			$.messageBox({level:'warn',message:"请选择一条服务成员信息进行修改！"});
			return;
		}
		var selectedId = $("#serviceTeamMemberList").jqGrid("getGridParam", "selrow");
		if(null == selectedId){
			$.messageBox({level:'warn',message:"请选择需要修改的服务成员信息！"});
			return;
		}
		updateOperator(event,selectedId);
	});
	$("#delete").click(function(){
		if(countSelectedIds() != 1) {
			$.messageBox({level:'warn',message:"请选择一条服务成员记录进行删除！"});
			return;
		}
		var selectedIds = $("#serviceTeamMemberList").jqGrid("getGridParam", "selarrrow");
			var serviceTeam =  $("#serviceTeamMemberList").getRowData(selectedIds[0]);
		deleteOperator(event,selectedIds);
	});
	
	$("#export").click(function(){
		if ($("#serviceTeamMemberList").getGridParam("records")>0){
			$("#_serviceTeamMemberDialog").createDialog({
				width: 250,
				height: 200,
				title:'导出服务成员信息',
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'serviceTeamMemberList',
					downloadUrl:'${path}/baseinfo/serviceTeamMemberManage/downloadServiceTeamMembers.action'
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			$.messageBox({level:'warn',message:"没有可导出的数据！"});
			return;
		}
	});
	$("#relation").click(function(){
		if(countSelectedIds() != 1) {
			$.messageBox({level:'warn',message:"只能选择一条记录维护与团队关系！"});
			return;
		}
		var selectedIds = $("#serviceTeamMemberList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			var serviceTeam =  $("#serviceTeamMemberList").getRowData(selectedIds[i]);
			if(!checkedOrgId(serviceTeam["organization.id"])){
				return;
			}
		}
		$("#_serviceTeamMemberDialog").createDialog({
			title:'维护与团队关系',
			width: 840,
			height: 450,
			url:'${path}/baseinfo/serviceTeamMemberManage/serviceTeamMemberDispatch.action?mode=relation&dailogName=_serviceTeamMemberDialog&selectedIds='+selectedIds+'&organizationId='+getCurrentOrgId(),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#maintain").click(function(){
		if(countSelectedIds() < 1) {
			$.messageBox({level:'warn',message:"请选择一条或多条服务成员记录进行维护服务记录！"});
			return;
		}
		var selectedIds = $("#serviceTeamMemberList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			var serviceTeam =  $("#serviceTeamMemberList").getRowData(selectedIds[i]);
			if(!checkedOrgId(serviceTeam["organization.id"])){
				return;
			}
		}
		$("#_serviceTeamMemberDialog").createDialog({
			title:'维护服务记录',
			width: 650,
			height: 550,
			url:'${path}/baseinfo/serviceTeamMemberManage/dispatchForServiceMember.action?mode=add&dailogName=_serviceTeamMemberDialog&fromSource=fromMember&selectedIds='+selectedIds,
			buttons: {
				"保存并关闭" : function(event){
				$("#serviceRecord_form").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#reload").click(function(){
		$("#searchServiceTeamMemberVo_orgScope").val($("#searchServiceTeamMemberVo_orgScope").find(":eq(0)").val());
		$("#searchServiceTeamMemberVo_teamClassId").val($("#searchServiceTeamMemberVo_teamClassId").find(":eq(0)").val());
		$("#searchServiceTeamMemberVo_condition").val('请输入<@s.text name="search.population.common.condition"/>');
		loadTeamMembers();
	});
	$("#view").click(function(){
		if(countSelectedIds() != 1) {
			$.messageBox({level:'warn',message:"请选择一条服务成员信息进行查看！"});
			return;
		}
		var selectedId = $("#serviceTeamMemberList").jqGrid("getGridParam", "selrow");
		if(null == selectedId){
			$.messageBox({level:'warn',message:"请选择需要查看的服务成员信息，或在记录行上直接双击！"});
			return;
		}
		viewServiceTeamMember(selectedId);
	});
	function countSelectedIds(){
		var selectedIds = $("#serviceTeamMemberList").jqGrid("getGridParam", "selarrrow");
		return null == selectedIds ? 0 :selectedIds.length;
	}
	function loadTeamMembers() {
		var condition = $("#searchServiceTeamMemberVo_condition").val();
		var listParam = null;
		if('请输入<@s.text name="search.population.common.condition"/>' != condition) {
			listParam = {
				'searchServiceTeamMemberVo.orgScope':$("#searchServiceTeamMemberVo_orgScope").val(),
				'searchServiceTeamMemberVo.teamClassId':$("#searchServiceTeamMemberVo_teamClassId").val(),
				'searchServiceTeamMemberVo.orgId':getCurrentOrgId(),
				'searchServiceTeamMemberVo.cardOrName':condition
			};
		}else listParam = {
			'searchServiceTeamMemberVo.orgScope':$("#searchServiceTeamMemberVo_orgScope").val(),
			'searchServiceTeamMemberVo.teamClassId':$("#searchServiceTeamMemberVo_teamClassId").val(),
			'searchServiceTeamMemberVo.orgId':getCurrentOrgId()
		}
		loadData(listParam);
	}
	function loadData(listParam) {
		$("#serviceTeamMemberList").setGridParam({
			url:'${path}/plugin/serviceTeamMemberManage/pageServiceTeamMembers.action',
			datatype: "json",
			page:1
		});
		$("#serviceTeamMemberList").setPostData(listParam);
		$("#serviceTeamMemberList").trigger("reloadGrid");
	}
	function checkedOrgId(orgID){
		if(orgID != USER_ORG_ID){
			$.messageBox({
				message:"当前用户只能操作所在层级的组织！",
				level: "warn"
			});
			return false;
		}else{
			return true;
		}
	}
})
</script>