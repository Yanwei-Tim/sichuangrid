<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
	<div class="ui-corner-all" id="nav" style="position:relative;">
		<pop:JugePermissionTag ename="addRegionalBuildInfo">
			<a id="addRegionalBuildInfo" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		
		<pop:JugePermissionTag ename="updateRegionalBuildInfo">
			<a id="updateRegionalBuildInfo" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewRegionalBuildInfo">
			<a id="viewRegionalBuildInfo" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteRegionalBuildInfo">
			<a id="deleteRegionalBuildInfo" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchRegionalBuildInfo">
			<a id="searchRegionalBuildInfo" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reloadRegionalBuildInfo"  href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="claimRegionalBuildInfo">
			<a id="claimRegionalBuildInfo" href="javascript:void(0)"><span>认领</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="regionalBuildInfoList"> </table>
		<div id="regionalBuildInfoListPager"></div>
	</div>
	<div id="regionalBuildInfoDialog"></div>
</div>
 
<script type="text/javascript">
	function callback(){
		var dfop = {
			path:'${path}',
			viewRegionalBuildInfo:'<pop:JugePermissionTag ename="viewRegionalBuildInfo">true</pop:JugePermissionTag>'
		}
		TQ.regionalBuildInfoList(dfop)
	}

	$.cacheScript({
		url:'/resource/getScript/partyBuilding/organizations/regionalBuildInfoManage/regionalBuildInfoList.js',
		callback: callback
	})	
	
</script>
