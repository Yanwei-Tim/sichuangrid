<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<%request.setAttribute("moduleName",request.getParameter("moduleName"));%>
<%request.setAttribute("lowerCaseModuleName",request.getParameter("moduleName").substring(0,1).toLowerCase()+request.getParameter("moduleName").substring(1));%>
<%request.setAttribute("moduleCName",request.getParameter("moduleCName"));%>
<%request.setAttribute("serviceTeamName",request.getParameter("serviceTeamName"));%>
<%request.setAttribute("serviceTeamType",request.getParameter("serviceTeamType"));%>

<div class="container container_24">
<form id="${ lowerCaseModuleName}Form" method="post" action="${path}/baseinfo/serviceTeamManage/maintainServiceTeam.action">
<pop:token />
        <input id="mode" type="hidden" name="mode" value="${mode}" />
        <input type="hidden" name="serviceManageTeam.teamClazz" value='<s:property value="#request.lowerCaseModuleName"/>'/>
		<input id="serviceTeamOrgId" type="hidden"	name="serviceManageTeam.organization.id"		value="${serviceManageTeam.organization.id }" />
		<input type="hidden" name="serviceManageTeam.id" value="${serviceManageTeam.id }" /> 	
	<div class="grid_4 lable-right">
	    <em class="form-req">*</em>
		<label class="form-lb1">所属区域：</label>
	</div>
	<div class="grid_20">
		<input  type="text" id="orgName"
			name="serviceManageTeam.organization.orgName" readonly
			value="${serviceManageTeam.organization.orgName }"
			class="form-txt" />
	</div>
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">${serviceTeamName }：</label>
	</div>
	<div class="grid_20">
		<input  type="text" id="serviceManageTeamTitle" maxlength="50"
			name="serviceManageTeam.name" style="width: 97%"
			value="${serviceManageTeam.name}" title="请输入${serviceTeamName }"
			class='form-txt {required:true,maxlength:50,minlength:2,exculdeParticalChar:true,messages:{required:"请输入${serviceTeamName }",maxlength:$.format("${serviceTeamName }不能多于{0}个字符"),minlength:$.format("${serviceTeamName }不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
	</div>

	<div class="grid_4 lable-right">
	    <em class="form-req">*</em>
		<label class="form-lbl">${serviceTeamType}：</label>
	</div>
	<div class="grid_8">
		<select name="serviceManageTeam.teamType.id"
		id="primaryOrganizationbasicOrgTypeId" class='form-txt {required:true,messages:{required:"请选择${serviceTeamType}"}}'>
			<s:if test="#request.lowerCaseModuleName.equals(@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@autonomousOrganization)">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE"
				defaultValue="${serviceManageTeam.teamType.id}" />
			</s:if>
			<s:if test="#request.lowerCaseModuleName.equals(@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@massForce)">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@MASSEDUTY_TYPE"
				defaultValue="${serviceManageTeam.teamType.id}" />
			</s:if>
			<s:if test="#request.lowerCaseModuleName.equals(@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@socialVolunteerTeam)">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY_TYPE"
				defaultValue="${serviceManageTeam.teamType.id}" />
			</s:if>
			<s:if test="#request.lowerCaseModuleName.equals(@com.tianque.baseInfo.serviceTeamManage.domain.ServiceManageTeam@specialWorkLeadingGroup)">
			<pop:OptionTag
				name="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE"
				defaultValue="${serviceManageTeam.teamType.id}" />
			</s:if>
		</select>
	</div>

	<div class="grid_4 lable-right" >
		<label class="form-lbl">成立时间：</label>
	</div>
	<div class="grid_7 ">
		<input type="text" name="serviceManageTeam.establishDate" id="establishDate" maxlength="32"
			readonly="readonly"  class="form-txt" value='<s:date name="serviceManageTeam.establishDate" format="yyyy-MM-dd" />' />
	</div>
	<div class="clear"></div>
	<div class="grid_4 lable-right" >
		<label class="form-lb1">简介：</label>
	</div>
	<div class="grid_20 heightAuto">
		<textarea id="serviceManageTeamRemark" rows="8" 
		name="serviceManageTeam.remark" 
		title="请输入简介"
		class="form-txt {maxlength:200,messages:{maxlength:$.format('简介最多需要输入{0}个字符')}}">${serviceManageTeam.remark}</textarea>
	</div>
</form>

</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#${lowerCaseModuleName}Form").formValidate({
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
         			if("add" == $("#mode").val()){
						$("#${lowerCaseModuleName}Dialog").dialog("close");
						$.messageBox({message:"成功添加${moduleCName}!"});
						$("#${lowerCaseModuleName}List").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#${lowerCaseModuleName}Dialog").dialog("close");
						$.messageBox({message:"成功修改${moduleCName}!"});
						$("#${lowerCaseModuleName}List").trigger("reloadGrid");
         			}
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
      	  	});
		},
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
	<s:if test='"add".equals(mode)'>
			$("#serviceTeamOrgId").val(USER_ORG_ID);
			$.ajax({
				async: false,
				url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
				data:{
					"organization.id" : $("#currentOrgId").val()
				},
				success:function(responseData){
					$("#orgName").val(responseData);
				}
			});
	</s:if>
});
</script>