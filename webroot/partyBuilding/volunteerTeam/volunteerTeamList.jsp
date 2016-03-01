<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	request.setAttribute("partyModule",com.tianque.partyBuilding.members.constant.MemberType.VOLUNTEERTEAM);
	request.setAttribute("partyOrgType",com.tianque.partyBuilding.members.constant.MemberType.VOLUNTEER_TEAM);
%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="${path}/common/orgSelectedComponent.jsp" />
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入组织名称" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入组织名称':this.value;" onfocus="value=(this.value=='请输入组织名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<div class="btnbanner btnbannerData">
			<div class="userState" id="fastSearchSelect">
				<select id="displayLevel" name="displayLevel" class="basic-input" >
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" selected="selected">仅显示本级</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">全部</option>
				</select>
			</div>
		</div>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addVolunteerTeam">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateVolunteerTeam">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteVolunteerTeam">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewVolunteerTeam">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downVolunteerTeam">
			<!--<a id="export" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出</span></a>-->
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="manageVolunteerTeamMember">
			<a id="manageMember" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="volunteerTeamList"></table>
		<div id="volunteerTeamListPager"></div>
	</div>
	<div id="volunteerTeamDialog"></div>
	<div id="noticeDialog"></div>
	<div id="volunteerTeamMaintanceDialog"></div>
</div>
<script type="text/javascript">
function callback(){
	var dfop = {
			path:'${path}',
			viewVolunteerTeam:'<pop:JugePermissionTag ename="viewVolunteerTeam">true</pop:JugePermissionTag>'
			
		}
	TQ.volunteerTeamList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/volunteerTeam/volunteerTeamList.js',
	callback: callback
})
</script>