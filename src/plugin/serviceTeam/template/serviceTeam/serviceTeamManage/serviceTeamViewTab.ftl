<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div style="display: none;">
mode:<input id="mode" type="hidden" name="mode" value="${mode! }" /> 
orgId:<input id="serviceTeamOrgId" type="hidden" name="serviceTeam.org.id" value="<@s.property value="@com.tianque.util.ThreadVariable@getOrganization().id"/>" />
teamId<input type="hidden" name="serviceTeam.id" value="${(serviceTeamVo.id)! }" /> 
</div>
<table class="tablelist">
	<tr>
		<td class="title"><label>所属区域：</label></td>
		<td class="content" colspan="3">${(serviceTeamVo.org.orgName)!}</td>
	</tr>
	<tr>
		<td class="title"><label>团队名称：</label></td>
		<td class="content" colspan="3"><span>${(serviceTeamVo.teamName)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>团队类别：</label></td>
		<td class="content" colspan="3" ><span><@pop.PropertyDictViewTag name="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM"  defaultValue="${(serviceTeamVo.teamType.id)!}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>成立时间：</label></td>
		<td class="content" colspan="3"><span><@s.date name="serviceTeamVo.buildDate" format="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>简介：</label></td>
		<td class="content" colspan="3"><span>${(serviceTeamVo.remark)!}</span></td>
	</tr>
	<@s.if test="serviceTeamVo.logOut==1">
	<tr>
		<td class="title"><label>解散原因：</label></td>
		<td class="content" colspan="3" ><span>${(serviceTeamVo.logOutReason)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>解散时间：</label></td>
		<td class="content" colspan="3"><span><@s.date name="serviceTeamVo.logOutTime" format="yyyy-MM-dd" /></span></td>
	</tr>
	</@s.if>
</table>
