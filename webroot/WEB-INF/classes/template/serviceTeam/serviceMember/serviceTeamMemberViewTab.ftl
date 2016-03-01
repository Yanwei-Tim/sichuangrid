<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<table class="tablelist">
	<tr>
		<td class="title"><label>所属网格：</label></td>
		<td class="content" colspan="3">${(serviceTeamMemberVo.org.orgName)!}</td>
	</tr>
	<tr>
		<td class="title"><label>姓名：</label></td>
		<td class="content"><span>${(serviceTeamMemberVo.name)!}</span></td>
		<td class="title"><label>性别：</label></td>
		<td class="content"><span><@pop.PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${(serviceTeamMemberVo.gender.id)!}" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>联系手机：</label></td>
		<td class="content"><span>${(serviceTeamMemberVo.mobile)!}</span></td>
		<td class="title"><label>固定电话：</label></td>
		<td class="content"><span>${(serviceTeamMemberVo.homePhone)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>职位：</label></td>
		<td class="content"><span>${(serviceTeamMemberVo.job)!}</span></td>
		<td class="title"><label>出生年份：</label></td>
		<td class="content"><span>${(serviceTeamMemberVo.birthday)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>备注：</label></td>
		<td class="content" colspan="3" rowspan="3"><span>${(serviceTeamMemberVo.remark)!}</span></td>
	</tr>
</table>
