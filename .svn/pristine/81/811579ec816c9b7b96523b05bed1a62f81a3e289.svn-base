<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="messageInfo" class="container container_24">
	<input type="hidden"  id="id" name="msgBean.id" value="${(msgBean.id)!}" />
	<input type="hidden" id="orgCode" name="msgBean.organization.orgInternalCode" value="${(msgBean.organization.orgInternalCode)!}">
	<input type="hidden"  id="orgId" name="msgBean.organization.id" value="${(msgBean.organization.id)!}" />
	<div class="grid_4 lable-right">
	<label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="msgBean.organization.orgName" id="orgName" value="${(msgBean.organization.orgName) ! }"  readonly class="form-txt"   style="width: 105%"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">用户名称：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="msgBean.user.name" value="${(msgBean.user.name) ! }" class="form-txt" style="width: 99%" <@s.if test="'view'.equals(mode)">readonly</@s.if> />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">发送手机：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="msgBean.user.helpLine" value="${(msgBean.user.helpLine) ! }" class="form-txt" <@s.if test="'view'.equals(mode)">readonly</@s.if> style="width: 99%"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">所属分组：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="msgBean.team.teamName" value="${(msgBean.team.teamName) ! }" class="form-txt" <@s.if test="'view'.equals(mode)">readonly</@s.if> style="width: 99%"/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">联户长：</label>
	</div>
	<div class="grid_8">
		<input type="text"  name="msgBean.team.houseMaster" value="${(msgBean.team.houseMaster) ! }" class="form-txt" <@s.if test="'view'.equals(mode)">readonly</@s.if> style="width: 99%"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">发送时间：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="msgBean.sendDate" value="<@s.date name="msgBean.sendDate" format="yyyy-MM-dd HH:mm:ss"/>" class="form-txt" <@s.if test="'view'.equals(mode)">readonly</@s.if> style="width: 99%"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">内容：</label>
	</div>
	<div class="grid_20">
		<textarea  name="msgBean.message.text" class="form-txt" <@s.if test="'view'.equals(mode)">readonly</@s.if> style="width: 99%">${(msgBean.message.text)!}</textarea>
	</div>
</div>
<script type="text/javascript">
</script>