<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="viewServiceTeam" class="container container_24">
	<input type="hidden" id="serviceTeam.logOut" name="serviceTeam.logOut" value="${(serviceTeamVo.logOut)!}" />
	<div id=tabs>
		<ul>
			<li><a href='${path}/plugin/serviceTeam/serviceTeamManage/viewSeriviceTeam.action?dailogName=serviceTeamDialog&serviceTeam.id=${(serviceTeamVo.id)!}'>团队信息</a></li>
			<li><a href='${path}/plugin/serviceTeam/serviceTeamManage/dispatch.action?mode=viewMemberList&dailogName=serviceTeamDialog&serviceTeamVo.id=${(serviceTeamVo.id)!}&serviceTeamMember.onDuty=1'>在职成员列表</a></li>
			<li><a href='${path}/plugin/serviceTeam/serviceTeamManage/dispatch.action?mode=viewMemberList&dailogName=serviceTeamDialog&serviceTeamVo.id=${(serviceTeamVo.id)!}&serviceTeamMember.onDuty=0'>离职成员列表</a></li>
		</ul>
	</div>
</div>
<script>
	$(function() {
		$( "#tabs").tabs({cache:false,beforeLoad:function(ui){
			$("#memberList").remove();
		}});
	});
</script>