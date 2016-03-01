<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="familyTeamInfo" class="container container_24">
	<@s.if test='"edit".equals(mode)'>
		<form id="mainFamilyTeamForm" method="post"
			action="${path}/tenHouseholdsJoint/groupingCondition/updateFamilyTeam.action">
			<@pop.token />
	</@s.if>
	<@s.if test='"add".equals(mode) '>
		<form id="mainFamilyTeamForm" method="post"
			action="${path}/tenHouseholdsJoint/groupingCondition/addFamilyTeam.action">
			<@pop.token />
	</@s.if>
	<input type="hidden"  id="id" name="familyTeam.id" value="${(familyTeam.id)!}" />
	<input type="hidden" id="orgCode" name="familyTeam.organization.orgInternalCode" value="${(familyTeam.organization.orgInternalCode)!}">
	<input type="hidden"  id="orgId" name="familyTeam.organization.id" value="${(familyTeam.organization.id)!}" />
	<div class="grid_4 lable-right">
		<@s.if test='!"search".equals(mode)'><em class="form-req">*&nbsp;</em></@s.if>
		<label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="familyTeam.organization.orgName" id="orgName" value="${(familyTeam.organization.orgName) ! }"  readonly class="form-txt"   style="width: 105%"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<@s.if test='!"search".equals(mode)'><em class="form-req">*&nbsp;</em></@s.if>
		<label class="form-lbl">联防分组名称:</label>
	</div>
	<div class="grid_8">
		<input type="text" id="teamName" maxlength="20" name="familyTeam.teamName" value="${(familyTeam.teamName) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if>   class="form-txt" style="width: 99%" />
	</div>
	<div class="grid_4 lable-right">
		<@s.if test='!"search".equals(mode)'><em class="form-req">*&nbsp;</em></@s.if>
		<label class="form-lbl">分组编码:</label>
	</div>
	<div class="grid_8">
		<input type="text" id="teamCode" maxlength="20" name="familyTeam.teamCode" value="${(familyTeam.teamCode) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" style="width: 99%"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<@s.if test='!"search".equals(mode)'><em class="form-req">*&nbsp;</em></@s.if>
		<label class="form-lbl">联户长:</label>
	</div>
	<div class="grid_8">
		<input type="text" id="houseMaster" maxlength="20" name="familyTeam.houseMaster" value="${(familyTeam.houseMaster) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" style="width: 99%"/>
	</div>
	<div class="grid_4 lable-right">
		<@s.if test='!"search".equals(mode)'><em class="form-req">*&nbsp;</em></@s.if>
		<label class="form-lbl">联户长证件号:</label>
	</div>
	<div class="grid_8">
		<input type="text" id="houseMastCertificateNo" maxlength="20" name="familyTeam.houseMastCertificateNo" value="${(familyTeam.houseMastCertificateNo) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" style="width: 99%"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<@s.if test='!"search".equals(mode)'><em class="form-req">*&nbsp;</em></@s.if>
		<label class="form-lbl">户数:</label>
	</div>
	<div class="grid_8">
		<input type="text" id="houseHolds" maxlength="20" name="familyTeam.houseHolds" value="${(familyTeam.houseHolds) ! }" <@s.if test='"view".equals(mode)'>readonly</@s.if> class="form-txt" style="width: 99%"/>
	</div>
	<div class="grid_4 lable-right">
		<@s.if test='!"search".equals(mode)'><em class="form-req">*&nbsp;</em></@s.if>
		<label class="form-lbl">接警中心:</label>
	</div>
	<div class="grid_8">
		<select name="familyTeam.alarmCenter.id" id ="alarmCenter" class="form-select" <@s.if test='"view".equals(mode)'>disabled</@s.if>>
			<@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@ALARMCENTER" defaultValue="${(familyTeam.alarmCenter.id) ! }" />
		</select>
	</div>
	<@s.if test='!"view".equals(mode)'>
		</form>
	</@s.if>
</div>
<script type="text/javascript">
	$("#mainFamilyTeamForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form){
	 $(form).ajaxSubmit({
		success : function(data) {
			if (!data) {
				$.messageBox({message : data,level : "error"});
				return;
			}
			if("add"=="${mode}"){
				$.messageBox({message:"分组新增成功！"});
			}
			if("edit"=="${mode}"){
				$.messageBox({message:"分组信息修改成功！"});
			}
			$("#familyTeamDialog").dialog('close');
	        $("#familyTeamList").trigger("reloadGrid")
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("提交错误");
		}
		});
	},
	rules:{
		"familyTeam.teamName":{
			required: true,
			minlength: 2,
			maxlength: 20
		},
		"familyTeam.teamCode":{
			required: true,
			minlength: 2,
			maxlength: 20,
			ishasSameTeamCode:true
		},
		"familyTeam.houseMaster":{
			required: true,
			minlength: 2,
			maxlength: 20
		},
		"familyTeam.houseMastCertificateNo":{
			required: true,
			minlength: 2,
			maxlength: 20
		},
		"familyTeam.houseHolds":{
			required: true,
			minlength: 2,
			maxlength: 10,
			positiveInteger :true
		},
		"familyTeam.alarmCenter.id":{
			required: true
		}
	},
	messages:{
		"familyTeam.teamName":{
			required: "请输入联防分组名称",
			minlength  : $.format("至少需要{0}个字符"),
	    	maxlength  : $.format("最多只能输入{0}个字符")
		},
		"familyTeam.teamCode":{
			required: "请输入分组编码",
			minlength  : $.format("至少需要{0}个字符"),
	    	maxlength  : $.format("最多只能输入{0}个字符"),
	    	ishasSameTeamCode:"该网格下已存在相同的分组编码"
		},
		"familyTeam.houseMaster":{
			required: "请输入联户长",
			minlength  : $.format("至少需要{0}个字符"),
	    	maxlength  : $.format("最多只能输入{0}个字符")
		},
		"familyTeam.houseMastCertificateNo":{
			required: "请输入联户长证件号",
			minlength  : $.format("至少需要{0}个字符"),
	    	maxlength  : $.format("最多只能输入{0}个字符")
		},
		"familyTeam.houseHolds":{
			required: "请输入户数",
			minlength  : $.format("至少需要{0}个字符"),
	    	maxlength  : $.format("最多只能输入{0}个字符"),
	    	positiveInteger : "户数只能输入正整数"
		},
		"familyTeam.alarmCenter.id":{
			required: "请选择接警中心"
		}
	}
});
jQuery.validator.addMethod("ishasSameTeamCode", function(value, element){
		var flag=true;
		$.ajax({
			async:false,
			url:"${path}/tenHouseholdsJoint/groupingCondition/checkFamilyTeam.action",
			data:{
				"familyTeam.organization.id": function(){ return $('#orgId').val()},
				"familyTeam.teamCode" : function(){ return $('#teamCode').val()},
				"familyTeam.id" : function(){ return $('#id').val()}
			},
			success:function(responseData){
				flag = responseData;
			}
		});
		return flag;
	});

</script>