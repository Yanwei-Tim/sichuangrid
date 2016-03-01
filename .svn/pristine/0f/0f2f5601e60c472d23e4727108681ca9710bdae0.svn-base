<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<link rel="stylesheet" type="text/css" href="${resource_path}/resource/system/css/typeSelectForIssue.css" />
<script type="text/javascript" src="${resource_path}/resource/system/js/typeSelectForIssue.js"></script>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<c:if test="${USER_ORG_LEVEL!=0 }">
			<pop:JugePermissionTag ename="addAdministrativeOrgTimeLimitStandard">
					<a id="add" href="javascript:void(0)"><span>新增</span></a>
			</pop:JugePermissionTag>
		</c:if>
		<pop:JugePermissionTag ename="updateAdministrativeOrgTimeLimitStandard">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteAdministrativeOrgTimeLimitStandard">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<!-- 
		<pop:JugePermissionTag ename="searchAdministrativeOrgTimeLimitStandard">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag> -->
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="administrativeOrgTimeLimitStandardList"> </table>
		<div id="administrativeOrgTimeLimitStandardListPager"></div>
	</div>
	<div id="administrativeOrgTimeLimitStandardDialog"></div>
	
</div>

<script type="text/javascript">
var issueDialogWidth=780;
var issueDialogHeight=450;

function callback(){
    var dfop = {
    	userName:'<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>'
    }
    TQ.administrativeOrgEimeLimisStandardList(dfop)
}
$.cacheScript({
    url:'/resource/getScript/issue/issueAccessConfig/administrativeOrgTimeLimitStandardList.js',
    callback: callback
})
</script>
