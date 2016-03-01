<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	request.setAttribute("partyModule",com.tianque.partyBuilding.members.constant.MemberType.ORGANSPARTY);
	request.setAttribute("partyOrgType",com.tianque.partyBuilding.members.constant.MemberType.OFFICE_PARTY_ORG);
	request.setAttribute("branch",com.tianque.domain.property.PartyOrgType.BRANCH_NAME);
%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addOrgansParty">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateOrgansParty">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteOrgansParty">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewOrgansParty">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchOrgansParty">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="manageOrgansPartyMember">
			<a id="manageOrgansPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护党员</span></a>
		</pop:JugePermissionTag>
		<select id="partyOrgType" style="float:right">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE" />
		</select>	
		<select id="belongOrg" style="float:right">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BELONG_ORG" />
		</select>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="organsPartyList">
		</table>
		<div id="organsPartyListPager"></div>
	</div>
	<div id="organsPartyDialog"></div>
	<div id="noticeDialog"></div>
	<div id="organsPartyMaintanceDialog"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"></pop:formatterProperty>
<pop:formatterProperty name="department" domainName="@com.tianque.domain.property.PropertyTypes@BELONG_ORG"></pop:formatterProperty>

// 改变组织机构树时加载列表
function onOrgChanged(searchVo){
	var initParam = {
		"searchOrgansPartyVo.orgid":"<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getId()'/>"
	}
	var	pageData=$.extend(searchVo,initParam);
	$("#organsPartyList").setGridParam({
 		url:'${path}/organsPartyManage/findOrgansPartyPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#organsPartyList").setPostData(pageData);
	$("#organsPartyList").trigger("reloadGrid");
}

function searchByBelongOrgAndPartyOrgType(){
	var data={"searchOrgansPartyVo.department":$('#belongOrg option:selected').val(),
			"searchOrgansPartyVo.type":$('#partyOrgType option:selected').val()
	}
	onOrgChanged(data);
}
function callback(){
	var dfop = {
		path:'${path}',
		viewOrgansParty:'<pop:JugePermissionTag ename="viewOrgansParty">true</pop:JugePermissionTag>',
		partyOrgType:'${partyOrgType}',
		partyModule:'${partyModule}',
		branch:'${branch}'
	}
	TQ.organsPartyList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/organsParty/organsPartyManage/organsPartyList.js',
	callback: callback
})
</script>