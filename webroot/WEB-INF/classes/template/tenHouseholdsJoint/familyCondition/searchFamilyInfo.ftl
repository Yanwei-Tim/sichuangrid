<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/> 
<form action="" method="post" id="searchMaintainForm">
	<input type="hidden" name="familyInfo.organization.id" value="${organizationId}"/>
	<div class="grid_5 lable-right">
		<label>用户姓名：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="familyInfo.name" id="name" class="form-txt"/>
	</div>
	
	<div class="grid_5 lable-right">
		<label>求助电话：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="familyInfo.helpLine" id="helpLine" class="form-txt"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label>证件号码：</label>
	</div>
	<div class="grid_4">
		<input type="text" name="familyInfo.certificateNumber" id="certificateNumber" class="form-txt"/>
	</div>
	
	<div class="grid_5 lable-right">
		<label>接警中心：</label>
	</div>
	<div class="grid_4">
		<select name="familyInfo.alarmCenter.id" id="alarmCenter" class="form-txt" style="height: 22px;width:137px;">
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ALARMCENTER"/>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label>所属分组：</label>
	</div>
	<div class="grid_4">
		<select name="familyInfo.teamId" id="" class="form-txt" style="height: 22px;width:137px;">
			<option value="">请选择</option>
			<@s.iterator value="teamList" id="team">
				<option value="${(team.id)!}">${(team.teamName)!}</option>
			</@s.iterator>
		</select>
	</div>
	
	<div class="grid_5 lable-right" style="margin-left: 5px;">
		<label>用户状态：</label>
	</div>
	<div class="grid_4">
		<select name="familyInfo.familyState" style="height: 22px;width:137px;">
		<option value="">请选择</option>
		<option value="0">在线</option>
		<option value="1">不在线</option>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right" style="margin-left: 11px;">
		<label>警务室：</label>
	</div>
	<div class="grid_4">
		<select name="familyInfo.policeRoom.id" id="familyInfo.policeRoom.id" class="form-txt" style="height: 22px;width:137px;">
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@POLICEROOM"/>
		</select>
	</div>
	<div class="grid_5 lable-right" style="margin-left: 5px;">
		<label>注销状态：</label>
	</div>
	<div class="grid_4">
		<select name="familyInfo.logOut" style="height: 22px;width:137px;">
		<option value="0">正常</option>
		<option value="1">注销</option>
		</select>
	</div>
</form>

<script type="text/javascript">
    $("#searchMaintainForm").formValidate({
		promptPosition: "bottomRight",
		submitHandler: function(form){
			var params = $(form).serialize();
			jQuery("#familyInfoList").setPostData({});
			var url = jQuery("#familyInfoList").getGridParam("url");
			jQuery("#familyInfoList").setGridParam({"url":"${path}/tenHouseholdsJoint/familyCondition/searchFamilyInByCondition.action?"+params});
			$("#familyInfoList").trigger("reloadGrid");
		}
	});
</script>