<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<input type="hidden" name="population.nation.id" id="usedName" maxlength="20" value="${population.nation.id}" readonly disabled class="form-txt" />
<input type="hidden" name="dailogName" id="dailogName" value="${dailogName }">
<input type="hidden" name="englishName" id="englishName" value="${population.usedName}">
<div class="grid_4 lable-right">
	<label class="form-lb1">所属网格：</label>
</div>
<div class="grid_18">
	<input type="text"  id="commonPopulationOrgName"  name="population.organization.orgName"   value="${population.organization.orgName}" style="width: 99%" readonly	class="form-txt" />
</div>
<div class='clearLine'>&nbsp;</div>
<div class="grid_4 lable-right">
	<label class="form-lb1"><s:if test='"overseaPersonnelMaintanceDialog".equals(#parameters.dailogName[0])'>证件号</s:if>
	<s:else>身份证号码：</s:else></label>
</div>
<div class="grid_7">
	<input type="text" name="population.idCardNo" id="_idCardNo_" maxlength="18" value="${population.idCardNo}" readonly  class="form-txt"/>
</div>
<s:if test='"overseaPersonnelMaintanceDialog".equals(#parameters.dailogName[0])'>
	<div class="grid_4 lable-right">
		<label class="form-lb1">中文名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.name" id="_name_" maxlength="20" value="${population.name}" readonly disabled class="form-txt" />
	</div>
</s:if>
<s:else>
	<div class="grid_4 lable-right">
		<label class="form-lb1">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.name" id="_name_" maxlength="20" value="${population.name}" readonly disabled class="form-txt" />
	</div>
</s:else>
<div class='clearLine'>&nbsp;</div>
	<div  class="grid_4 lable-right">
		<label class="form-lb1">性别：</label>
</div>
<div class="grid_7">
    <select name="population.gender.id" id="gender" class="form-txt"  disabled > 
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${population.gender.id}" />
</select>
</div>
<s:if test='"overseaPersonnelMaintanceDialog".equals(#parameters.dailogName[0])'>
	<div class="grid_4 lable-right">
		<label class="form-lb1">英文名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.usedName" id="usedName"  maxlength="20" value="${population.usedName}" readonly disabled class="form-txt" />
	</div>
</s:if>
<s:else>
	<div class="grid_4 lable-right">
		<label class="form-lb1">曾用名/别名：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="population.usedName" id="usedName" maxlength="20" value="${population.usedName}" readonly disabled class="form-txt" />
	</div>
</s:else>
<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
	<label class="form-lb1">联系手机：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.mobileNumber" id="_mobileNumber_" maxlength="20"  value="${population.mobileNumber}"  readonly disabled class="form-txt" />
</div>
<div class="grid_4 lable-right">
	<label class="form-lb1">固定电话：</label>
</div>
<div class="grid_7">
	<input type="text" name="population.telephone" id="_telephone_" maxlength="20"  value="${population.telephone }"  readonly disabled class="form-txt" />
</div>
<div class='clearLine'>&nbsp;</div>

<script type="text/javascript">
<s:if test='null == population'>
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id" : $("#currentOrgId").val()
		},
		success:function(responseData){
			$("#commonPopulationOrgName").val(responseData);
		}
	});
</s:if>
</script>