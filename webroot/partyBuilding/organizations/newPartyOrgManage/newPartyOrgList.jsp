<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	request.setAttribute("partyModule",com.tianque.partyBuilding.members.constant.MemberType.TWONEWPARTYORGANIZATION);
	request.setAttribute("partyOrgType",com.tianque.partyBuilding.members.constant.MemberType.TWO_NEW_ORGNIZATION_PARTY_ORG);
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
		<pop:JugePermissionTag ename="searchNewPartyOrg">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addNewPartyOrg">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateNewPartyOrg">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteNewPartyOrg">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewNewPartyOrg">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="manageNewPartyMember">
			<a id="managePartyMember" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="manageNewPartyRecord">
			<a id="maintainActivity" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护记录</span></a>
		</pop:JugePermissionTag>
			
		
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="newPartyOrgList">
		</table>
		<div id="newPartyOrgListPager"></div>
	</div>
	<div id="newPartyOrgDialog"></div>
	<div id="noticeDialog"></div>
	<div id="newPartyOrgMaintanceDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"></pop:formatterProperty>
// Formatter
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateNewPartyOrg'><a href='javascript:updateNewPartyOrg("+rowData.id+")'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteNewPartyOrg'><a href='javascript:deleteNewPartyOrg("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}

function maintainActivity(selectedId,organizationName){
	if(selectedId==null||organizationName==null||organizationName==""){
		return;
	}
	var rowData=  $("#newPartyOrgList").getRowData(selectedId);
	$("#newPartyOrgDialog").createDialog({
		width:950,
		height:550,
		title:'两新组织党组织活动维护',
	 		url:"${path}/partyBuilding/activityRecordManage/dispatchOperateByEncrypt.action?mode=list&partyModule=${partyModule }&partyOrganizationType=<s:property value='@com.tianque.partyBuilding.activityRecords.constant.OrganizationType@NEW_PARTY_ORGANIZATION'/>&partyOrganizationId="+rowData.encryptId+"&partyOrganizationName="+encodeURIComponent(organizationName),
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function callback(){
	var dfop = {
		path:'${path}',
		viewNewPartyOrg:'<pop:JugePermissionTag ename="viewNewPartyOrg">true</pop:JugePermissionTag>',
		partyOrgType:'${partyOrgType}',
		branch:'${branch}',
		partyModule:'${partyModule}',
		partyOrganizationType:'<s:property value="@com.tianque.partyBuilding.activityRecords.constant.OrganizationType@NEW_PARTY_ORGANIZATION"/>'
	}
	TQ.newPartyOrgList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/organizations/newPartyOrgManage/newPartyOrgList.js',
	callback: callback
})
</script>