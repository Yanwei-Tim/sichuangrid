<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="tabs">
	<ul>
		<li><a href='${path}/plugin/serviceTeam/serviceTeamMember/viewServiceMember.action?dailogName=_serviceTeamMemberDialog&serviceTeamMemberBase.id=${(serviceTeamMemberVo.baseId)!}'>成员基本信息</a></li>
	</ul>
</div>
<script type="text/javascript">
	$(function() {
		$( "#tabs").tabs({cache:false,beforeLoad:function(ui){
			$("#viewServiceMember").remove();
		}});
	});
</script>
