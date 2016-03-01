<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
	<form id="serviceTeamMember_form" method="post" action="" >
		<input type="hidden" name="serviceTeamMemberVo.org.id" value="${(serviceTeamMemberVo.org.id)!}">
		<input type="hidden" name="serviceTeamMemberVo.orgScope" value='<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>'>	
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width:95%" name="serviceTeamMemberVo.name" class="form-txt"/>
		</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">性别：</label>
		</div>
		<div class="grid_8">
			<select name="serviceTeamMemberVo.gender.id" class="form-select">
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="serviceTeamMemberVo.mobile" maxlength="11" style="width:95%" class="form-txt" />
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamMemberVo.homePhone" class="form-txt" maxlength="20"/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
   	</form>
</div>