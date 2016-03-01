<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
<form id="autonomyTeamForm" method="post" action="${path}/baseinfo/serviceTeamManage/maintainServiceTeam.action">
<pop:token />
<input type="hidden" name="serviceManageTeam.teamClazz" value='<s:property value="@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@autonomousOrganization"/>'/>
	<div style="display: none;">                  
		mode:<input id="mode" type="text" name="mode" value="${mode}"/> 
		autonomyTeamOrgId:<input id="autonomyTeamOrgId" type="text"	name="serviceManageTeam.organization.id"		value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
		
		serviceManageTeamId:<input type="text" name="serviceManageTeam.id" value="${serviceManageTeam.id }" /> 	
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">所属区域：</label>
	</div>
	<div class="grid_19">
		<input  type="text" id="commonPopulationOrgName"
			name="serviceManageTeam.organization.orgName" readonly
			value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().orgName"/>"
			class="form-txt" />
	</div>
	<div class="grid_4 lable-right" >
		<label class="form-lb1">组织名称：</label>
	</div>
	<div class="grid_19">
		<input  type="text" id="serviceManageTeamTitle"
			name="serviceManageTeam.name" style="width: 97%"
			value="${serviceManageTeam.name}" title="请输入组织名称"
			class='form-txt {required:true,maxlength:50,minlength:2,exculdeParticalChar:true,messages:{required:"请输入组织名称",maxlength:$.format("组织名称不能多于{0}个字符"),minlength:$.format("组织名称不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
	</div>
    <div class="grid_1">
    	<em class="form-req">*</em>
    </div>

	<div class="grid_4 lable-right">
		<label class="form-lbl">组织类别：</label>
	</div>
	<div class="grid_6">
		<select name="serviceManageTeam.teamType.id"
		id="primaryOrganizationbasicOrgTypeId" class='form-txt {required:true,messages:{required:"请选择组织类别"}}'
			<s:if test='"view".equals(mode)'>disabled="disabled"</s:if>>
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE"
		defaultValue="${serviceManageTeam.teamType.id}" />
		</select>
	</div>
	<div class="grid_1">
    	<em class="form-req">*</em>
    </div>

	<div class="grid_5 lable-right" >
		<label class="form-lbl">成立时间：</label>
	</div>
	<div class="grid_7 ">
		<input type="text" style="width: 92%" name="serviceManageTeam.establishDate" id="establishDate"
			readonly="readonly"  class="form-txt" value='<s:date name="serviceManageTeam.establishDate" format="yyyy-MM-dd" />' />
	</div>
	<div class="grid_4 lable-right" >
		<label class="form-lb1">简介：</label>
	</div>
	<div class="grid_19 heightAuto">
		<textarea id="serviceManageTeamRemark" rows="8" cols="77"
		name="serviceManageTeam.remark" 
		title="请输入简介"
		class='form-txt {maxlength:200,messages:{maxlength:$.format("组织名称不能多于{0}个字符")}}'>${serviceManageTeam.remark}</textarea>
	</div>
</form>
<div class="clear"></div>
</div>
<script type="text/javascript"><!--
$(document).ready(function(){
	/*
	 * 设置默认按钮状态
	 */
	function setDefaultButtonState(){
		if(getCurrentOrgId() == USER_ORG_ID){
			$("#add").buttonEnable();
			$("#update").buttonDisable();
			$("#view").buttonDisable();
			$("#delete").buttonDisable();
			$("#search").buttonEnable();
			$("#reload").buttonEnable();
			$("#export").buttonEnable();
			$("#mantanceAutonomyTeamMember").buttonDisable();
			$("#maintainServiceObject").buttonDisable();
			$("#maintainServiceLog").buttonDisable();
		}else{
			$("#add").buttonDisable();
			$("#update").buttonDisable();
			$("#view").buttonDisable();
			$("#delete").buttonDisable();
			$("#search").buttonEnable();
			$("#reload").buttonEnable();
			$("#export").buttonEnable();
			$("#mantanceAutonomyTeamMember").buttonDisable();
			$("#maintainServiceObject").buttonDisable();
			$("#maintainServiceLog").buttonDisable();
		}
	}
	/*
	 * 设置一行选中按钮状态
	 */
	function setOneRowButtonState(){
		setDefaultButtonState();
		if(getCurrentOrgId() == USER_ORG_ID){
			$("#update").buttonEnable();
			$("#view").buttonEnable();
			$("#delete").buttonEnable();
			$("#mantanceAutonomyTeamMember").buttonEnable();
			$("#maintainServiceObject").buttonEnable();
			$("#maintainServiceLog").buttonEnable();
		}else{
		}
	}
	/*
	 * 设置多行选中按钮状态
	 */
	function setMultiRowsButtonState(){
		setDefaultButtonState();
		if(getCurrentOrgId() == USER_ORG_ID){
			$("#delete").buttonEnable();
		}else{
		}
	}
	/*
	 * 获取选中的最后一行
	 */
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#serviceTeamList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	$("#autonomyTeamForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(!data.id){
           	 			$.messageBox({
							message:data,
							evel: "error"
			 			});
            			return;
					}
					$("#autonomyTeamDialog").dialog("close");
					$.messageBox({message:"成功添加自治组织!"});
					$("#serviceTeamList").trigger("reloadGrid");
					setDefaultButtonState();
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
		  	   		$("#autonomyTeamDialog").dialog("close");
					$.messageBox({message:"提交错误!"});
	      	   	}
      	  	});
		},
		ignore:':hidden',
		rules:{
		},
		messages:{
		}
	});

	//成立时间
	$('#establishDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d'
	});

	//组织名称
	$("#serviceManageTeamTitle").focus();
});
</script>