<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addRegionalPartyMember">
			<a id="addRegionalPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateRegionalPartyMember">
			<a id="updateRegionalPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewRegionalPartyMember">
			<a id="viewRegionalPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteRegionalPartyMember">
			<a id="deleteRegionalPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchRegionalPartyMember">
			<a id="searchRegionalPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reloadRegionalPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<pop:JugePermissionTag ename="importRegionalPartyMember">
			<%-- 	<a id="importRegionalPartyMember" href="javascript:void(0)"><span>导入</span></a> --%>
			</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downRegionalPartyMember">
			<%-- <a id="exportRegionalPartyMember" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出</span></a> --%>
		</pop:JugePermissionTag>
		
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="regionalPartyMemberList">
		</table>
		<div id="regionalPartyMemberListPager"></div>
	</div>
	<div id="regionalPartyMemberDialog"></div>
	<div id="noticeDialog"></div>
	<div id="regionalPartyMemberMaintanceDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 600;
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="partyOrgDuty" domainName="@com.tianque.domain.property.PropertyTypes@PARTYORGDUTY" />
function callback(){
	var dfop = {
		path:'${path}',
		viewRegionalPartyMember:'<pop:JugePermissionTag ename="viewRegionalPartyMember">true</pop:JugePermissionTag>'
	}
	TQ.regionalPartyMemberList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/organizations/regionalPartyMemberManage/regionalPartyMemberList.js',
	callback: callback
})	
</script>