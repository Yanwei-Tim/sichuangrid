<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleName">ServiceTeam</@s.param>
</@s.include>
<div class="content" >
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<@s.include value="/common/orgSelectedComponent.jsp"/>
			<div class="userState">
				<select id="displayLevel" name="displayLevel" class="basic-input" >
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" selected="selected">仅显示本级</option>
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
					<option value="<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">全部</option>
				</select>
				<select id="teamTypeId" name="teamType.id" class="basic-input" >
					<@pop.OptionTag name="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM" />
				</select>
			</div>
		</div>
		<span class="lineBetween "></span>
		<@pop.JugePermissionTag ename="searchServiceTeam">
			<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="addServiceTeam">
		<a id="add" href="javascript:;"><span>新增团队</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="logoutServiceTeam">
			<a id="logOut" href="javascript:void(0)"><span>解散团队</span></a>
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="downServiceTeam">
			<a id="down" href="javascript:void(0)"><span>导出</span></a>
		</@pop.JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<@pop.JugePermissionTag ename="maintainServiceTeamMembersForServiceTeam">
			<a id="maintainServiceTeamMembers" href="javascript:void(0)"><span>维护成员</span></a>
		</@pop.JugePermissionTag>
	</div>
	<div class="">
		<table id="serviceTeamList"> </table>
		<div id="serviceTeamListPager"></div>
	</div>
	<div id="serviceTeamDialog"></div>
	<div style="display: none;">
		<@pop.JugePermissionTag ename="serviceTeamManagement">
		<span id="title"><@s.property value="#request.name"/></span>
		</@pop.JugePermissionTag>
	</div>
</div>
<input type="hidden" id="records"/>
<script type="text/javascript">
<@pop.formatterProperty name="teamType" domainName="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM" />
var notRun = new Array();
var run = new Array();

//修改团队
function updateOperator(selectedId){
	var serviceTeam =  $("#serviceTeamList").getRowData(selectedId);
	if(serviceTeam.logOut==1 || serviceTeam.logOut=='1'){
		$.messageBox({level:'warn',message:"已解散的服务团队不可再修改！"});
		return;
	}
	$("#serviceTeamDialog").createDialog({
		title:'修改服务团队',
		width: 600,
		height: 320,
		url:'${path}/plugin/serviceTeam/serviceTeamManage/dispatchByEncrypt.action?mode=edit&dailogName=serviceTeamDialog&serviceTeam.id='+serviceTeam.encryptId,
		buttons: {
			"保存并关闭" : function(event){
				$("#serviceTeamForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

//删除团队
function deleteOperator(selectedId){
	var serviceTeam =  $("#serviceTeamList").getRowData(selectedId);
	if(serviceTeam.logOut==1 || serviceTeam.logOut=='1'){
		$.messageBox({level:'warn',message:"已解散的服务团队不可再删除！"});
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/plugin/serviceTeam/serviceTeamManage/deleteServiceTeamByEncrypt.action?mode=delete&selectedIds='+serviceTeam.encryptId,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除服务团队!"});
						$("#serviceTeamList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"请清空组织成员，服务对象，服务记录后再删除团队!",
							level:"warn"
						});
					}
				}
			});
		}
	});
}

//查看团队信息
function viewDialog(id){
	<@pop.JugePermissionTag ename="viewServiceTeam">
	var serviceTeam =  $("#serviceTeamList").getRowData(id);
	$("#serviceTeamDialog").createDialog({
		width:650,
		height:450,
		title:"查看服务团队",
		url:'${path}/plugin/serviceTeam/serviceTeamManage/dispatchByEncrypt.action?mode=view&dailogName=serviceTeamDialog&serviceTeam.id='+serviceTeam.encryptId+'&serviceTeam.logOut='+serviceTeam.logOut,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	</@pop.JugePermissionTag>
}

	//查看团队下成员列表
	function viewMemberList(id){
		var rowData=  $("#serviceTeamList").getRowData(id);
		$("#serviceTeamDialog").createDialog({
			title:"查看在职成员列表",
			width: 600,
			height: 360,
			url:'${path}/plugin/serviceTeam/serviceTeamManage/dispatchByEncrypt.action?mode=viewMemberList&dailogName=serviceTeamDialog&serviceTeamVo.id='+rowData.encryptId+'&serviceTeamMember.onDuty=1&serviceTeamVo.org.id='+getCurrentOrgId(),
			buttons: {
				"关闭" : function(){
		        $(this).dialog("close");
		  		 }
			}
		});
	}



function callback(){
    TQ.serviceTeamList()
}

$.cacheScript({
	url:'/resource/getScript/serviceTeam/template/serviceTeam/serviceTeamManage/serviceTeamList.js',
    callback: callback
})
</script>