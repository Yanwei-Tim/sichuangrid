<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container container_24">
<form id="primaryOrgForm" method="post" action="${path}/baseinfo/primaryOrgManage/${mode}PrimaryOrg.action">
<pop:token />
        <input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="primaryOrgId" type="hidden" name="primaryOrg.id" value="${primaryOrgVo.id}"/>
		<input id="primaryOrgOrgId" type="hidden"	name="primaryOrg.org.id" value="${primaryOrgVo.org.id}" />
		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
		<input name="appendMember" id="appendMember" type="hidden" value="" /> 
        <input  name="primaryOrg.teamClazz.id" type="hidden" value="${primaryOrgVo.teamClazz.id}" >

	<div class="grid_4 lable-right">
	    <em class="form-req">*</em>
		<label class="form-lb1">所属区域：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="orgName" name="primaryOrg.org.orgName" readonly style="width:440px;"
			value="${primaryOrgVo.org.orgName}" class="form-txt" />
	</div>
	<!--  
	<s:if test="teamTypeName.equals('MASSORGANIZATION_TYPE')">
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
			    <em class="form-req">*</em>
				<label class="form-lbl">组织类型：</label>
			</div>
			<div class="grid_8">
				<select id="teamTypeId" name="primaryOrg.teamType.id" class='form-txt {required:true,messages:{required:"请选择组织类别"}}'>
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@${teamTypeName}" defaultValue="${primaryOrgVo.teamType.id}" />
				</select>
		  	</div>
	</s:if>
	-->
	<c:if test="${teamTypeName!=''}">
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
			    <em class="form-req">*</em>
				<label class="form-lbl">组织类别：</label>
			</div>
			<div class="grid_8">
				<select id="teamTypeId" name="primaryOrg.teamType.id" class='form-txt {required:true,messages:{required:"请选择组织类别"}}'>
					<s:if test="isCommissionOrganization.equals('comprehensive') || isCommissionOrganization.equals('commission') ||isCommissionOrganization.equals('commissionMember')">
						<option value="${typeId }">${teamTypeName }</option>
					</s:if>
					<s:else>
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@${teamTypeName}" defaultValue="${primaryOrgVo.teamType.id}" />
					</s:else>
				</select>
		  	</div>
		  	<div class="grid_7 hidden" id="leaderGroup_type"  >
				<select id="departmentType" name="primaryOrg.departmentType.id"  <c:if test='${mode=="view"}'>disabled</c:if>  class='form-txt {required:true,messages:{required:"请选择组织类别"}}'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@LEADERGROUP_TYPE" defaultValue="${primaryOrgVo.departmentType.id}" />
				</select>
			</div>
			<div class="grid_7 hidden" id="management_type"  >
				<select  id="departmentTypeByManagement" name="primaryOrg.departmentType.id" <c:if test='${mode=="view"}'>disabled</c:if> class='form-txt {required:true,messages:{required:"请选择组织类别"}}'>
			   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MANAGEMENT_TYPE" defaultValue="${primaryOrgVo.departmentType.id}" />
				</select>
			</div>
	</c:if>

	<input  type="hidden" id="primaryOrgName" maxlength="32" name="primaryOrg.name" value="${primaryOrgVo.name}"/>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">组织名称：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="primaryOrgDetailName" maxlength="30" name="primaryOrg.detailName" value="${primaryOrgVo.detailName}" title="请输入组织名称"
			class='form-txt {required:true,maxlength:32,minlength:2,exculdeParticalChar:true,messages:{required:"请输入组织名称",maxlength:$.format("组织名称不能多于{0}个字符"),minlength:$.format("组织名称不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
	</div>
	
	<c:if test="${teamTypeName=='MASSEDUTY_TYPE'}">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right" >
		    <em class="form-req">*</em>
			<label class="form-lb1">业务指导部门：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="guidanceDepartment" maxlength="30" name="primaryOrg.guidanceDepartment" value="${primaryOrgVo.guidanceDepartment}" title="请输入业务指导部门"
				class='form-txt {required:true,maxlength:32,minlength:2,exculdeParticalChar:true,messages:{required:"请输入业务指导部门",maxlength:$.format("业务指导部门不能多于{0}个字符"),minlength:$.format("业务指导部门不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right" >
		    <em class="form-req">*</em>
			<label class="form-lb1">主要职能：</label>
		</div>
		<div class="grid_18 heightAuto">
			<textarea id="mainFunction" style="width:440px;height:100px" maxlength="200" name="primaryOrg.mainFunction" title="请输入主要职能"
			class="form-txt {required:true,maxlength:200,messages:{required:'请输入主要职能',maxlength:$.format('主要职能最多需要输入{0}个字符')}}">${primaryOrgVo.mainFunction}</textarea>
		</div>
	</c:if>
	
	<div class="clear"></div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">备注：</label>
	</div>
	<div class="grid_18 heightAuto">
		<textarea id="primaryOrgRemark" style="width:440px;height:100px" maxlength="200" name="primaryOrg.remark" title="请输入备注"
		class="form-txt {maxlength:200,messages:{maxlength:$.format('备注最多需要输入{0}个字符')}}">${primaryOrgVo.remark}</textarea>
	</div>
</form>
<input type="hidden" id="orgNameTmp" value="${primaryOrgVo.org.orgName}">
</div>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test='${mode=="edit"}'>
		var checkText=$("#teamTypeId").find("option:selected").text();
		if(checkText=="专项工作领导小组"){
			$("#leaderGroup_type").css("display","block");
		}else if(checkText=="综治办"){
			$("#management_type").css("display","block");
		}
	</c:if>
	$("#teamTypeId").change(function(){
		change();
	});
	change();

	//表单验证
	$("#primaryOrgForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(!data.id){
           	 			$.messageBox({
							message:data,
							level: "error"
			 			});
            			return;
					}
         			if("add" == $("#mode").val()){
						$.messageBox({message:"成功添加组织!"});
						if($("#isSubmit").val()=="true"){
	                	 	$("#primaryOrgDialog").dialog("close");
	                	}else{
	                		emptyObject();
		                }
						$("#primaryOrgList").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#primaryOrgDialog").dialog("close");
						$.messageBox({message:"成功修改组织!"});
						$("#primaryOrgList").trigger("reloadGrid");
         			}
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
         				$.messageBox({message:"提交错误",level: "error"});
	      	   	},
	      	 	ignore:":hidden",
	  			rules:{
	  				
	  			}
      	  	});
		}
	});
	//继续新增时清空内容
	function emptyObject() {
		$("#primaryOrgForm").resetForm();
		//$("#orgName").val($("#currentOrgId").attr("text"));
		$("#orgName").val($("#orgNameTmp").val());
		$("#primaryOrgOrgId").val(getCurrentOrgId());
		$("#primaryOrgName").val($("#currentOrgId").attr("text")+titleName);
	}
	<c:if test='${mode=="add"}'>
			$("#primaryOrgOrgId").val(getCurrentOrgId());
			$("#primaryOrgName").val($("#currentOrgId").attr("text")+titleName);
			$.ajax({
				async: false,
				url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
				data:{
					"organization.id" : $("#currentOrgId").val()
				},
				success:function(responseData){
					$("#orgName").val(responseData);
					$("#orgNameTmp").val(responseData);
				}
			});
	</c:if>
	
});
function change(){
	var checkText=$("#teamTypeId").find("option:selected").text();
	if(checkText=="专项工作领导小组"){
		$("#departmentType").attr("disabled",false);
		$("#departmentTypeByManagement").attr("disabled",true);
		$("#management_type").hide();
		$("#leaderGroup_type").show();
	}else if(checkText=="综治办"){
		$("#departmentType").attr("disabled",true);
		$("#departmentTypeByManagement").attr("disabled",false);
		$("#management_type").show();
		$("#leaderGroup_type").hide();
	}else{
		$("#departmentType").attr("disabled",true);
		$("#departmentTypeByManagement").attr("disabled",true);
		$("#management_type").hide();
		$("#leaderGroup_type").hide();
	}
}
</script>