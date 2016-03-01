<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="container container_24">
	<form id="serviceTeamMember_form" method="post" action="" >
		<input type="hidden" name="searchServiceTeamMemberVo.orgId" value="${(searchServiceTeamMemberVo.orgId)!}">
		<input type="hidden" name="searchServiceTeamMemberVo.orgScope" value='<@s.property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>'>	
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" style="width:95%" name="searchServiceTeamMemberVo.name" class="form-txt"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">身份证号码： </label>
		</div>
		<div class="grid_9">
			<input type="text"  name="searchServiceTeamMemberVo.idCardNo" maxlength="18" style="width:95%" class="form-txt"/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">性别：</label>
		</div>
		<div class="grid_7">
			<select name="searchServiceTeamMemberVo.gender" class="form-select">
				<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"/>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_9">
			<input type="text" name="searchServiceTeamMemberVo.mobile" maxlength="11" style="width:95%" class="form-txt" />
		</div>
	
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" style="width: 95%" name="searchServiceTeamMemberVo.homePhone" class="form-txt" maxlength="20"/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
   	</form>
</div>