<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<% String serviceTeamId = request.getParameter("serviceTeamId"); %>
<% String orgId = request.getParameter("orgId"); %>
<div class="container container_24">
<s:action name="ajaxOrganization" var="findById" namespace="/sysadmin/orgManage" executeResult="false">
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getOrganization().id"></s:param>
</s:action>
<form id="searchServiceObjectForm" method="post">
<pop:token />
<input type="hidden" id="currOrganizationId" name="searchServiceObjectVo.orgId" value="<s:property value="#findById.organization.id"/>" />
<input type="hidden" id="currOrganizationCode" name="searchServiceObjectVo.orgInternalCode" value="<s:property value="#findById.organization.orgInternalCode"/>" />
<input id="autonomyTeamOrgId" type="hidden" name="serviceManageTeam.organization.id"
	value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
<input id="serviceTeamId" name="searchServiceObjectVo.serviceTeamId" type="hidden" value="<%=serviceTeamId %>"/>
<div class="grid_4 lable-right"><label class="form-lb1">所属区域：</label></div>
<div class="grid_20">
<input type="text" id="OrganizationName" name="searchServiceObjectVo.orgName"
				value="<s:property value="#findById.organization.orgName"/>" />
</div>
<div class='clearLine'></div>
<div class="grid_4 lable-right"><label class="form-lb1">姓名：</label></div>
<div class="grid_8"><input type="text"
	id="name"
	name="searchServiceObjectVo.name" style="width: 97%"
	value="${searchServiceObjectVo.name}" title="请输入姓名"
	class='form-txt {maxlength:10,messages:{maxlength:$.format("组织名称最多输入{0}个字符")}}' />
</div>
<div class="grid_4 lable-right"><label class="form-lb1">身份证号码：</label></div>
<div class="grid_8"><input type="text"
	id="idCardNumber" maxlength="18"
	name="searchServiceObjectVo.idCardNumber" style="width: 97%"
	value="${searchServiceObjectVo.idCardNumber}" title="请输入身份证号码"
	class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:18,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("身份证号码至少需要输入{0}个字符"),maxlength:$.format("身份证号码最多需要输入{0}个字符")}}' />
</div>
<div class='clearLine'></div>
<div class="grid_4 lable-right">
	<label class="form-lbl">出生日期从：</label>
</div>
<div class="grid_8 " id="expiryDateDiv">
	<input type="text" name="searchServiceObjectVo.startDate" id="conditionStartBirthday"
		maxlength="30" readonly class="form-txt" />
</div>
<div class="grid_4 lable-right">
	至：
</div>
<div class="grid_8" id="expiryDateDiv">
	<input type="text" name="searchServiceObjectVo.endDate" id="conditionEndBirthday" maxlength="50" readonly class="form-txt " />
</div>
<div class='clearLine'></div>
<div class="grid_4 lable-right">
	<label class="form-lbl">联系手机：</label>
</div>
<div class="grid_8">
	<input type="text" id="conditionMobileNumber" name="searchServiceObjectVo.phoneNumber" maxlength="11" class="form-txt" />
</div>
<div class="grid_4 lable-right">
	<label class="form-lbl">性别：</label>
</div>
<div class="grid_8">
	<select id="conditionGender" class="form-txt" name="searchServiceObjectVo.genderId">
		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" />
	</select>
</div>
<div class='clearLine'></div>
<div class="grid_4 lable-right">
	<label class="form-lbl">常住地址：</label>
</div>
<div class="grid_20">
	<input type="text" id="conditionCurrentlyAddress" name="searchServiceObjectVo.address" maxlength="50" class="form-txt" style="width: 99%"/>
</div>
</form>
</div>
<script>
	function onOrgChanged(){
	}
$(document).ready(function(){
	onOrgChanged();
	var tree=$("#OrganizationName").treeSelect({
		inputName:"searchServiceObjectVo.orgId",
		listWidth:260,
		maxHeight:300,
		rootId:'<s:if test="#findById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"><s:property value="#findById.organization.parentOrg.id"/></s:if><s:else><s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/></s:else>',
		onSelect:function(){
			if(typeof(onOrgChanged) != 'undefined'){
				onOrgChanged.call(null,$("#currOrganizationId").attr("value"));
			}
		}
	});
	jQuery.validator.addMethod("isSelect", function(value, element){
	    if(value == null  || value ==-1){
            return false;
		  }
		return true;
	});
	$('#conditionStartBirthday').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionEndBirthday").datepicker("option", "minDate",date);
			}
		}
	});

	$('#conditionEndBirthday').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionStartBirthday").datepicker("option", "maxDate",date);
			}
		}
	});
	
});

</script>