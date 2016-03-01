<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewServiceTeam" class="container container_24">
	<input type="hidden" id="serviceManageTeam.logOut" name="serviceManageTeam.logOut" value="${serviceManageTeam.logOut}" />
	<div id=tabs>
		<ul>
			<li><a href='${path}/baseinfo/serviceTeamManage/viewSeriviceTeam.action?dailogName=_serviceTeamDialog&serviceManageTeam.id=${serviceManageTeam.id}'>团队信息</a></li>
			<s:if test="0==serviceManageTeam.logOut">
				<li><a href='${path}/baseinfo/serviceTeamMemberManage/dispatchOperate.action?mode=view&dailogName=_serviceTeamDialog&onDuty=true&teamId=${serviceManageTeam.id}&teamClazzId=${serviceManageTeam.teamClazz.id}'>现有成员信息</a></li>
				<li><a href='${path}/baseinfo/serviceTeamMemberManage/dispatchOperate.action?mode=view&dailogName=_serviceTeamDialog&teamId=${serviceManageTeam.id}&teamClazzId=${serviceManageTeam.teamClazz.id}&onDuty=false'>已注销成员信息</a></li>
			</s:if>
			<s:else>
				<li><a href='${path}/baseinfo/serviceTeamMemberManage/dispatchOperate.action?mode=view&dailogName=_serviceTeamDialog&teamId=${serviceManageTeam.id}&teamClazzId=${serviceManageTeam.teamClazz.id}&onDuty=false'>已注销成员信息</a></li>
			</s:else>
		</ul>
	</div>
</div>
<script>
	$(function() {
		$( "#tabs").tabs({cache:false,beforeLoad:function(ui){
			$("#viewTeamMember").remove();
		}});
	});
</script>