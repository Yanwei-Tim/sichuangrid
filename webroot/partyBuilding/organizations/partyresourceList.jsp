<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addPartyresource">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePartyresource">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewPartyresource">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePartyresource">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchPartyresource">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		<!-- 
		<pop:JugePermissionTag ename="downPartyresource">
			<a id="export" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>导出</span></a>
		</pop:JugePermissionTag>
		 -->
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="partyresourceList">
		</table>
		<div id="partyresourceListPager"></div>
	</div>
	<div id="partyresourceDialog"></div>
	<div id="noticeDialog"></div>
	<div id="partyresourceMaintanceDialog"></div>
</div>
<script type="text/javascript">
function callback(){
	var dfop = {
		viewPartyresource:'<pop:JugePermissionTag ename="viewPartyresource">true</pop:JugePermissionTag>',
		path:'${path}'
	}
	TQ.partyresourceList(dfop)
}

$.cacheScript({
	url:'/resource/getScript/partyBuilding/organizations/partyresourceList.js',
	callback: callback
})
</script>