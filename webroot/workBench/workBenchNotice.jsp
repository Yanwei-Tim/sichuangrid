<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<s:action name="getTitleProvinceName" var="getTitleProvinceName" executeResult="false" namespace="/sysadmin/orgManage" />

<div class="workMemos">
	<h3 class="btitle">通知通报</h3>
	<div>
		<ul id="noticeList">
		</ul>
	</div>
</div>
<script>
$(function(){
	$.ajax({
		url:'${path}/sysadmin/publicNoticeManage/publicNoticeList.action?publicNoticeLevel=0&organizationId=<s:property value="@com.tianque.core.util.ThreadVariable@getUser().organization.id"/>',
		success:function(data){
			for(var i=0;i<data.rows.length;i++){
				$("#noticeList").append("<li><a href='javascript:void(0)' onclick=showPageByTopMenu('systemManagement-publicNoticeManagement')>"+data.rows[i].publicNoticeTitle+"</a></li>");
			}
		}
	});
});
</script>