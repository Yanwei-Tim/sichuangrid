<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
	
		<pop:JugePermissionTag ename="findIssueSkiprule">
			<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addIssueSkiprule">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateIssueSkiprule">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteIssueSkiprule">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="startIssueSkiprule">
			<a id="start" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>启用</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="stopIssueSkiprule">
			<a id="stop" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>停用</span></a>
		</pop:JugePermissionTag>
		
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="issueSkipruleList">
		</table>
		<div id="issueSkipruleListPager"></div>
	</div>
	<div id="issueSkipruleDialog"></div>
	<div id="noticeDialog"></div>
	<div id="issueSkipruleMaintanceDialog"></div>
	<div id="enableIssueSkipruleDialog"></div>
	
</div>
<script type="text/javascript">
var dialogWidth = 600;
var dialogHeight = 400;
<pop:formatterProperty name="issueUrgentLevel" domainName="@com.tianque.domain.property.PropertyTypes@URGENT_LEVEL" />
<pop:formatterProperty name="submitLevel" domainName="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL" />

function callback(){
    var dfop = {
		userOrgIdValue:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>',
		hasUpdateIssueSkiprule:'<pop:JugePermissionTag ename="updateIssueSkiprule">true</pop:JugePermissionTag>',
		hasDeleteIssueSkiprule:'<pop:JugePermissionTag ename="deleteIssueSkiprule">true</pop:JugePermissionTag>',
		viewIssueSkiprule:'<pop:JugePermissionTag ename="viewIssueSkiprule">true</pop:JugePermissionTag>'
    		
    }
    TQ.issueSkipruleList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/issue/issueSkipruleManage/issueSkipruleList.js',
    callback: callback
})

</script>