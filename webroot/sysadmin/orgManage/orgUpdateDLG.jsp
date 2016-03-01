<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
<form id="org-update-form" action="${path }/sysadmin/orgManage/ajaxUpdateOrganization.action" method="post">
	<pop:token/>
	<input type="hidden" name="organization.id" value="${organization.id }" />
	<input type="hidden" id="organization.parentOrg.id" name ="organization.parentOrg.id" value="${organization.parentOrg.id }"/>
	<div class="grid_4 lable-right">
	<em class="form-req">*</em>
		<label class="form-lbl">部门名称：</label>
	</div>
	<div class="grid_7">
		<input type="text" maxlength="20"
		name="organization.orgName" value="${organization.orgName }" class="form-txt" id="update-organization-orgName"/>
	</div>
	<div class="grid_1"></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_7">
		<input type="text"
		name="organization.contactWay" maxlength="15" value="${organization.contactWay }" class="form-txt" id="organization-contactWay"/>
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">部门类型：</label>
	</div>
	<div class="grid_7">
		<input type="hidden" id="orgType-select" value="${organization.orgType.id}" name="organization.orgType.id"/>
		<input type="text" value="${organization.orgType.displayName}" name=""  readonly class="form-txt"/>
	</div>
	<div id="parentOrg-select" style="display: none;">
		<div class="grid_1"></div>
	    <div class="grid_4 lable-right">
	        <label class="form-lbl">所属职能部门：</label>
		</div>
	    <div class="grid_7">
			<select id="parentFunOrg-select" class="form-txt" name="organization.parentFunOrg.id"></select>
		</div>
	</div>
	<div id="orgLevel-select">
		<div class="grid_1"></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">部门级别：</label>
		</div>
		<div class="grid_7">
			<input name="" value="${organization.orgLevel.displayName }" readonly="readonly" class="form-txt"/>
			<input name="organization.orgLevel.id" id="orgLevel" value="${organization.orgLevel.id }" type="hidden" />
		</div>
	</div>
	<div id="functionOrg-select" style="display: none;">
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lbl">部门类别：</label>
		</div>
		<div class="grid_7">
		 <select id="functionalOrgType" name="organization.functionalOrgType.id" class="form-txt">
               <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FUNCTIONAL_ORG_TYPE" defaultValue="${organization.functionalOrgType.id}"/>
           </select>
		</div>
		<div class="clear"></div>
	</div>
	<div class="grid_4 lable-right"><label class="form-lbl">部门编号：</label></div>
    <div class="grid_19">
	    <input type="text" id="org-departmentNo" style="width:98.5%" name="organization.departmentNo" readonly="readonly" class="form-txt" value="${organization.departmentNo }" />
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">备注：</label>
	</div>
	<div id="org-remark" class="grid_19">
		<textarea name="organization.remark" class="form-txt" style="width:98.5%">${organization.remark }</textarea>
	</div>
<div>
</div>
</form>
</div>
<script>
$(document).ready(function(){
	if(parseInt($("#org-departmentNo").val().length) == 15){
		$("#org-departmentNo").attr("readonly","");
	}

	jQuery.validator.addMethod("checkOrgNoLength",function(value,element){
		if( value.length!=0){
			if(isNaN(value) || value.length<2)
		    {
		        return false;
		    }
		}
		return true;
	});
	var parentFunOrgId='${organization.parentFunOrg.id}';
	function afterOrgTypeChange(){
		if(orgTypeInternalIdArray[$("#orgType-select").val()]==<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>){
			$("#parentFunOrg-select").html("");
			$("#parentOrg-select").css("display","block");
			$("#functionOrg-select").css("display","block");
			$("#orgLevel-select").css("display","none");
			if($("#parentFunOrg-select").children().length==0){
				$.ajax({
					url : "${path}/sysadmin/orgManage/findOrganizationsByParentIdAndFunctionalOrgType.action",
					data: {parentId : function(){if($.getSelectedNode(tree).parentNode.parentNode.attributes.id!="orgTree-root"){return $.getSelectedNode(tree).parentNode.parentNode.attributes.id}else{return 0;}}},
					dataType : "json",
					async: false,
					success : function(data){
						$("#parentFunOrg-select").append($("<option value=''></option>"));
						for(var i=0;i<data.length;i++){
							var op=$("<option></option>");
							op.attr("value",data[i].id).text(data[i].orgName);
							if((data[i].id+'')==parentFunOrgId){
								op.attr("selected",true);
							}
							$("#parentFunOrg-select").append(op);
						}
					}
				});
			}
		}else{
			$("#parentFunOrg-select").html("");
			$("#parentOrg-select").css("display","none");
			$("#functionOrg-select").css("display","none");
			$("#orgLevel-select").css("display","block");
		}
	}
	afterOrgTypeChange();

	$("#orgType-select").change(afterOrgTypeChange);

	$("#update-organization-orgType").val("${organization.orgType}");
	
	$("#org-update-form").formValidate({
		promptPosition :"bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
						$.messageBox({
							message:data,
							level: "error"
						});
						return;
					}

					updateOrgDetail(data);

					$.rename(tree,data.id,data.orgName);
					$.setOrgNodeType(tree,data.id,data.orgType.internalId);
					//toggleMaintainsButtons();
					$.messageBox({
						message:"部门更新成功！"
					});
					$("#org-dailog").dialog("close");
				},
				error:function(data){
				}
			});
			return false;
		},
		rules: {
			"organization.orgName": {
				required: true,
				minlength: 2,
				maxlength: 20,
				remote: {
                   data: {
                        "organization.orgName": function(){return $("#update-organization-orgName").val();},
                        "organization.id": '${organization.id}',
                        "parentId" : '${organization.parentOrg.id}'
                   },
            	   url: "${path}/sysadmin/orgManage/validateOrgName.action",
            	   type: "post"
	            }
			},
			"organization.departmentNo": {
				required:true,
				exculdeParticalChar :true,
				maxlength:15,
				remote: {
	                   data: {
	                        "organization.departmentNo": function(){
	                            return $('#org-departmentNo').val();
	                         },
	                         "organization.id":'${organization.id}'
	                   },
	            	   url: "${path}/sysadmin/orgManage/validateRepeatDepartmentNo.action",
	            	   type: "post"
		        }
			},
			"organization.contactWay":{
				telephone:true
			},
			"organization.remark":{
				maxlength: 200
			}
		},
		messages: {
			"organization.orgName": {
				required: "请输入部门名称",
				minlength  : $.format("至少需要{0}个字符"),
				maxlength  : $.format("不能大于{0}个字符"),
				remote : "部门名在所属部门中已经存在"
			},
			"organization.departmentNo": {
				required:"部门编号不能为空",
				exculdeParticalChar : "部门编号不能有特殊字符",
				maxlength:$.format("部门编号输入不能大于{0}个字符"),
				remote:"部门编号已经存在"
			},
			"organization.functionalOrgType.id": {
				required: "请选择部门类别"
			},
			"organization.contactWay":{
				telephone:"请填写正确的联系电话"
			},
			"organization.remark":{
				maxlength  : $.format("部门备注不能大于{0}个字符")
			}
		}
	});
	

});
if($("#functionalOrgType").val()!=null&&$("#functionalOrgType").val()!=""){
	$("select[id='functionalOrgType']").rules("add",{required:true});
}
</script>
