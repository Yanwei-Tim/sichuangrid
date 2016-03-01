<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	request.setAttribute("partyModule",com.tianque.partyBuilding.members.constant.MemberType.STREETPARTY);
	request.setAttribute("partyOrgType",com.tianque.partyBuilding.members.constant.MemberType.TOWN_PARTY_ORG);
	request.setAttribute("branch",com.tianque.domain.property.PartyOrgType.BRANCH_NAME);
%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入党组织名称" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入党组织名称':this.value;" onfocus="value=(this.value=='请输入党组织名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchTownPartyOrg">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addTownPartyOrg">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateTownPartyOrg">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteTownPartyOrg">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewTownPartyOrg">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="manageTownPartyMember">
			<a id="managePartyMember" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护党员</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="manageTownPartyRecord">
			<a id="maintainActivity" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护活动记录</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="townPartyOrgList">
		</table>
		<div id="townPartyOrgListPager"></div>
	</div>
	<div id="townPartyOrgDialog"></div>
	<div id="noticeDialog"></div>
	<div id="townPartyOrgsMaintanceDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"></pop:formatterProperty>

// Formatter
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateTownPartyOrg'><a href='javascript:updateTownPartyOrg("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteTownPartyOrg'><a href='javascript:deleteTownPartyOrg("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}
function maintainActivity(selectedId,organizationName){
	if(selectedId==null||organizationName==null||organizationName==""){
		return;
	}
	var rowData = $("#townPartyOrgList").getRowData(selectedId);
	$("#townPartyOrgDialog").createDialog({
		width:950,
		height:550,
		title:'街道社区党组织活动维护',
	 	url:"${path}/partyBuilding/activityRecordManage/dispatchOperateByEncrypt.action?mode=list&partyModule=${partyModule }&partyOrganizationType=<s:property value='@com.tianque.partyBuilding.activityRecords.constant.OrganizationType@TOWN_PARTY_ORGANIZATION'/>&partyOrganizationId="+rowData.encryptId+"&partyOrganizationName="+encodeURIComponent(organizationName),
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function callback(){
	var dfop = {
		viewTownPartyOrg:'<pop:JugePermissionTag ename="viewTownPartyOrg">true</pop:JugePermissionTag>',
		path:'${path}',
		partyOrgType:'${partyOrgType}',
		branch:'${branch}',
		partyModule:'${partyModule}',
		townPartyOrganization:'<s:property value="@com.tianque.partyBuilding.activityRecords.constant.OrganizationType@TOWN_PARTY_ORGANIZATION"/>'
	}
	TQ.townPartyOrgList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/organizations/townPartyOrgManage/townPartyOrgList.js',
	callback: callback
})	
</script>