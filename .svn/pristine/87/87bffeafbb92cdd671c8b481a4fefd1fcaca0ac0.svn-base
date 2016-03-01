<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceTeamMember</@s.param>
</@s.include>
<div class="content">
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<@s.include value="/common/orgSelectedComponent.jsp"/>
			<div class="userState">
				<select class="basic-input" id="serviceTeamMemberVo_orgScope">
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" selected="selected">仅显示本级</option>
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">全部</option>
				</select>
			</div>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名" id="serviceTeamMemberVo_condition" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名':this.value;" onfocus="value=(this.value=='请输入姓名')?'':this.value;"/>
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
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<@pop.JugePermissionTag ename="maintainServiceObjectsForServiceTeamMember">
			<a id="maintainServiceObjectsForServiceTeamMember" href="javascript:void(0)"><span>维护对象</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="combine">
			<a id="combine" href="javascript:void(0)"><span>检测查重</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="changeOrg">
			<a id="changeOrg" href="javascript:void(0)"><span>层级转移</span></a>
		</@pop.JugePermissionTag>
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



//修改服务成员对话框
function updateOperator(selectedId){
	var rowData=  $("#serviceTeamMemberList").getRowData(selectedId);
	$("#_serviceTeamMemberDialog").createDialog({
		title:'修改服务成员',
		width: 680,
		height: 280,
		url:PATH+'/plugin/serviceTeam/serviceTeamMember/dispatchByEncrypt.action?mode=edit&dailogName=_serviceTeamMemberDialog&serviceTeamMemberBase.id='+rowData.encryptId,
		buttons: {
			"保存并关闭" : function(event){
	   			$("#serviceTeamMemberForm").submit();
   			},
   			"关闭" : function(){
		        	$(this).dialog("close");
				}
		}
	});
}
//删除服务成员
function deleteOperator(selectedId){
	var rowData =  $("#serviceTeamMemberList").getRowData(selectedId);
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:PATH+'/plugin/serviceTeam/serviceTeamMember/deleteServiceTeamMemberByEncrypt.action?mode=delete&selectedIds='+rowData.encryptId,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除服务成员!"});
						$("#serviceTeamMemberList").trigger("reloadGrid");
					}else{
					$.messageBox({level:'warn',message:data});
					}
				}
			});
		}
	});
}
	//查看服务成员基本信息
	function viewServiceMember(baseId){
	<@pop.JugePermissionTag ename="viewServiceTeamMember">
		var rowData=  $("#serviceTeamMemberList").getRowData(baseId);
		$("#_serviceTeamMemberDialog").createDialog({
			title:"查看成员基本信息",
			width: 600,
			height: 320,
			url:PATH+'/plugin/serviceTeam/serviceTeamMember/viewServiceMemberByEncrypt.action?mode=view&dailogName=_serviceTeamMemberDialog&serviceTeamMemberBase.id='+rowData.encryptId,
			buttons: {
				"关闭" : function(){
		        $(this).dialog("close");
		  		 }
			}
		});
	</@pop.JugePermissionTag>
	}	

function callback(){
    TQ.serviceTeamMemberList()
}

$.cacheScript({
	url:'/resource/getScript/serviceTeam/template/serviceTeam/serviceMember/serviceTeamMemberList.js',
    callback: callback
})
</script>