<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%
	request.setAttribute("teamId",request.getParameter("teamId"));
	request.setAttribute("orgId",request.getParameter("orgId"));
%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入姓名或身份证号码" name="searchText" id="searchTextMember" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入姓名或身份证号码':this.value;" onfocus="value=(this.value=='请输入姓名或身份证号码')?'':this.value;" />
				<button id="refreshSearchKeyMember" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButtonMember"><span>搜索</span></a>
	    <span class="lineBetween "></span>
	    <pop:JugePermissionTag ename="addVolunteerTeamMember">
			<a id="addMember" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewVolunteerTeamMember">
			<a id="viewMember" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteVolunteerTeamMember">
			<a id="deleteMember" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<a id="reloadMember" href="javascript:void(0);"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="memberList"></table>
		<div id="memberListPager"></div>
	</div>
	<div id="memberDialog" style="overflow: auto"></div>
</div>
<script type="text/javascript">
var dialogWidthMember = 800;
var dialogHeightMember = 550;
var autoHeight=100;
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />

var partyOrgType='${param.partyOrgType}';
function callback(){
	var dfop = {
			path:'${path}',
			teamId:'${teamId}',
			orgId:'${orgId}',
			regionPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@REGION_PARTY_ORG"/>',
			officePartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@OFFICE_PARTY_ORG"/>',
			townPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@TOWN_PARTY_ORG"/>',
			twoNewPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@TWO_NEW_ORGNIZATION_PARTY_ORG"/>',
			systemPartyOrg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@SYSTEM_PARTY_ORG"/>',
			doubleReg:'<s:property value="@com.tianque.partyBuilding.members.constant.MemberType@DOUBLE_REG_ACTIVITIES"/>',
			businessOrg:'<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@BUSINESS_ORG"/>',
			collectiveOrg:'<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@COLLECTIVE_ORG"/>',
			twoNewParty:'<s:property value="@com.tianque.partyBuilding.systemPartys.constant.SystemPartysType@TWO_NEW_PARTY"/>'
			
		}
	TQ.volunteerMemberList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/volunteerTeam/volunteerMemberList.js',
	callback: callback
})

</script>