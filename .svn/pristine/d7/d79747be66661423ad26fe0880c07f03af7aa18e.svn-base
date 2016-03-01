<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.tianque.domain.property.OrganizationLevel"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
	<form action="${path }/sysadmin/orgManage/ajaxAddOrganization.action"
		method="post" id="org-create-form">
		<pop:token />
		<input type="hidden" name="organization.parentOrg.id" value="${organization.parentOrg.id}" id="create-parentOrg-id" />
		<div class="grid_5 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lbl">部门名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="organization.orgName"
				id="create-organization-orgName" class="form-txt" maxlength="20" />
		</div>
		<div class="grid_1"></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="organization.contactWay" class="form-txt"
				maxlength="15" />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">部门类型：</label>
		</div>
		<div class="grid_7">
			<select id="orgType-select" name="organization.orgType.id" class="form-txt">
	              <pop:OptionTag propertyDict="${orgTypes}" notNull="true" defaultValue="${orgType}"/>
	         </select>
			<c:forEach items="${orgTypes}" var="orgType">
			<input type="hidden" id="orgType_${orgType.id}" value="${orgType.internalId}"/>
			</c:forEach>
		</div>
		<div id="parentOrg-select" style="display: none;">
			<div class="grid_1"></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">上级职能部门：</label>
			</div>
			<div class="grid_7">
				<select id="parentFunOrg-select" class="form-txt"
					name="organization.parentFunOrg.id">
					<option></option>
				</select>
			</div>
		</div>
		<div id="orgLevel-select">
			<div class="grid_1"></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">部门级别：</label>
			</div>
			<div class="grid_7">
				<%-- <s:select list="orgLevels" id="orgLevel" value=""
					name="organization.orgLevel.id" listKey="id"
					listValue="displayName" cssClass="form-txt">
				</s:select> --%>
				 <select id="orgLevel" name="organization.orgLevel.id" class="form-txt">
	                <pop:OptionTag propertyDict="${orgLevels}" notNull="true"/>
	            </select>
			</div>
		</div>
		<div id="functionOrg-select" style="display: none;">
			<div class="grid_5 lable-right">
				<em class="form-req">*&nbsp;</em><label class="form-lbl">部门类别：</label>
			</div>
			<div class="grid_7">
			  <select id="functionalOrgType" name="organization.functionalOrgType.id" class="form-txt">
	                <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FUNCTIONAL_ORG_TYPE" />
	            </select>
			</div>
			<div class="clear"></div>
		</div>
		<div class="grid_5 lable-right">
			<em class="form-req">*&nbsp;</em> <label class="form-lbl">部门编号：</label>
		</div>
		<div id="dptnf" class="grid_7">
			<input type="text" id="org-departmentNoF" name="departmentNoF"
				class="form-txt" />
		</div>
		<div id="dptnc" class="grid_7">
			<input type="text" id="org-departmentNoC" name="departmentNoC"
				class="form-txt" />
		</div>
		<div class="grid_1" id="stars"></div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">备注：</label>
		</div>
		<div id="org-remark" class="grid_19">
			<textarea name="organization.remark" class="form-txt"></textarea>
		</div>

	</form>
</div>


<script>

//特殊业务验证
jQuery.validator.addMethod("isSpecialValidation", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^[0-9]{1}[a-zA-Z]{2}$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});

$(document).ready(function(){
	<% 
	Integer orgLevels =  (Integer)request.getAttribute("number");
	if(orgLevels==OrganizationLevel.TOWN||
			orgLevels==OrganizationLevel.GRID||
			orgLevels==OrganizationLevel.VILLAGE){
	%>
	    var publicMin = 3;
		var publicMax = 3;
	 <%
	 }else{
	 %>
	    var publicMin = 2;
		var publicMax = 2;
	 <%
	 }
	 %>
	
	if(parseInt(${number}) == <%=OrganizationLevel.GRID%>){
		$('#dptnc').hide();
		$('#dptnf').attr("class","grid_18");
	}
	
	//var parentDepartmentNo = '<s:property value="#getParentOrgById.organization.departmentNo" />';
	var parentDepartmentNo = '${organization.parentOrg.departmentNo}';
	
	if(parseInt(${number}) != <%=OrganizationLevel.PROVINCE%>){
		$('#org-departmentNoF').attr("readonly",true);
		$.ajax({
			async:false,
			url: "${path }/sysadmin/orgManage/getMaxDepartmentNoByParentId.action",
			data:{
				"parentId" : function(){
	                return $("#orgDetail-orgId").val();
	            },
	            "orgLevel":function(){
	            	return parseInt(${number});
			    }
			},
			success:function(responseData){
				$("#org-departmentNoF").val(responseData);
			}
		});
	}else{
		$('#dptnf').hide();
		$('#dptnc').attr("class","grid_18");
	}
	
	jQuery.validator.addMethod("checkOrgNoLength",function(value,element){
		if( value.length!=0){
			if(isNaN(value) || value.length < 2)
		    {
		        return false;
		    }
		}
		return true;
	});
	
	if(orgTypeInternalIdArray[$("#orgType-select").val()]==<%=com.tianque.domain.property.OrganizationType.FUNCTIONAL_ORG%>){
		$("#org-remark").removeClass("grid_19").addClass("grid_19");
		$("#parentOrg-select").css("display","block");
		$("#functionOrg-select").css("display","block");
		$("#orgLevel-select").css("display","none");
		$("#parentFunOrg-select").html("");
		if($("#parentFunOrg-select").children().length==0){
			$.ajax({
				url : "${path}/sysadmin/orgManage/findOrganizationsByParentIdAndFunctionalOrgType.action",
				data: {parentId : function(){if($.getSelectedNode(tree).parentNode.attributes.id!="orgTree-root"){return $.getSelectedNode(tree).parentNode.attributes.id}else{return 0;}}},
				dataType : "json",
				async: false,
				success : function(data){
					$("#parentFunOrg-select").append($("<option></option>"));
					for(var i=0;i<data.length;i++){
						var op=$("<option></option>");
						op.attr("value",data[i].id).text(data[i].orgName);
						$("#parentFunOrg-select").append(op);
					}
				}
			});
		}
	}else{
		$("#parentFunOrg-select").html("");
		$("#org-remark").removeClass("grid_7").addClass("grid_19");
		$("#parentOrg-select").css("display","none");
		$("#functionOrg-select").css("display","none");
		$("#orgLevel-select").css("display","block");
	}
	
	$("#orgType-select").change(function(){
		$("#org-dailog").createDialog({
			width:680,
			height:300,
			title:'新建部门',
			url:PATH+'/sysadmin/orgManage/orgCreateJsp.action?organization.parentOrg.id='+$("#orgDetail-orgId").val()+"&orgType="+$("#orgType-select").find("option:selected").val()+"&orgLevel=" + ${organization.parentOrg.orgLevel.internalId},
			buttons :{
				"保存" : function(){
					$("#org-create-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$.validator.setDefaults({
		highlight: function(input) {
			$(input).addClass("ui-state-highlight");
		},
		unhighlight: function(input) {
			$(input).removeClass("ui-state-highlight");
		}
	});
	
	if($("#orgType_"+$("#orgType-select").val()).val() == '<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>'){
		if(parseInt(${orgLevel}) > parseInt('<s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>')){
			$("#org-create-form").formValidate({
				ignore:':hidden',
				promptPosition :"bottomLeft",
				submitHandler: function(form) {
					if(parseInt(${number}) != 0){
						if($("#org-departmentNoC").val() == "" || null == $("#org-departmentNoC").val()){
							$.messageBox({message:"请保持部门编号不为空!",level:"error"})
							return;
						}
					}
					$("#org-create-form").ajaxSubmit({
						success:function(data){
							if(data.organization){
								$.addNodeToLast(tree,data.extTreeData,$("#create-parentOrg-id").val());
								$("#orgChildren-dataGrid").addRowData(data.organization.id,data.organization,"last");
								$("#orgChildren-dataGrid").setSelection(data.organization.id);
								$.messageBox({
									message:"部门添加成功！"
								});
								$("#orgChildren-dataGrid").trigger("reloadGrid");
								$("#org-dailog").dialog("close");
								return ;
							}else{
								$.messageBox({
									message:data,
									level:"error"
			                    });
								return ;
							}
						}
					});
					return false;
				},
				rules: {
					"organization.orgName": {
						required: true,
						minlength: 2,
						exculdeParticalChar:true,
						remote: {
		                   data: {
		                        "organization.orgName": function(){
		                            return $('#create-organization-orgName').val();
		                        },
		                        "parentId" : function(){
		                            return $("#orgDetail-orgId").val();
		                        }
		                   },
		            	   url: "${path}/sysadmin/orgManage/validateOrgName.action",
		            	   type: "post"
			            }
					},
					"departmentNoC": {
						required:true,
						exculdeParticalChar:true,
						isSpecialValidation:true,
						minlength: 3,
						maxlength: 3,
						remote: {
		                   data: {
		                        "departmentNoF": function(){
		                            return $('#org-departmentNoF').val();
		                        },
		                        "departmentNoC": function(){
		                            return $('#org-departmentNoC').val();
		                        }
		                   },
		            	   url: "${path}/sysadmin/orgManage/validateRepeatDepartmentNo.action",
		            	   type: "post"
			            }
					},
					"organization.contactWay":{
						telephone:true
					},
					"organization.functionalOrgType.id":{
						required:true
					},
					"organization.remark":{
						maxlength: 200
					}

				},
				messages: {
					"organization.orgName": {
						required: "请输入部门名称",
						minlength  : $.format("至少需要{0}个字符"),
						exculdeParticalChar: "部门名称不能有特殊字符",
						remote:"部门名已经存在"
					},
					"departmentNoC": {
						required:"请输入部门编号",
						exculdeParticalChar : "部门编号不能有特殊字符",
						isSpecialValidation:"职能部门部门编号只能输入1位数字后加2位字母",
						minlength: $.format('至少需要{0}个字符'),
						maxlength: $.format('最多输入{0}个字符'),
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
		}else{
			$("#org-create-form").formValidate({
				ignore:':hidden',
				promptPosition :"bottomLeft",
				submitHandler: function(form) {
					if(parseInt(${number}) != 0){
						if($("#org-departmentNoC").val() == "" || null == $("#org-departmentNoC").val()){
							$.messageBox({message:"请保持部门编号不为空!",level:"error"})
							return;
						}
					}
					$("#org-create-form").ajaxSubmit({
						success:function(data){
							if(data.organization){
								$.addNodeToLast(tree,data.extTreeData,$("#create-parentOrg-id").val());
								$("#orgChildren-dataGrid").addRowData(data.organization.id,data.organization,"last");
								$("#orgChildren-dataGrid").setSelection(data.organization.id);
								$.messageBox({
									message:"部门添加成功！"
								});
								$("#orgChildren-dataGrid").trigger("reloadGrid");
								$("#org-dailog").dialog("close");
								return ;
							}else{
								$.messageBox({
									message:data,
									level:"error"
			                    });
								return ;
							}
						}
					});
					return false;
				},
				rules: {
					"organization.orgName": {
						required: true,
						minlength: 2,
						exculdeParticalChar:true,
						remote: {
		                   data: {
		                        "organization.orgName": function(){
		                            return $('#create-organization-orgName').val();
		                        },
		                        "parentId" : function(){
		                            return $("#orgDetail-orgId").val();
		                        }
		                   },
		            	   url: "${path}/sysadmin/orgManage/validateOrgName.action",
		            	   type: "post"
			            }
					},
					"departmentNoC": {
						required:true,
						exculdeParticalChar:true,
						isSpecialValidation:true,
						minlength: publicMin,
						maxlength: 3,
						remote: {
		                   data: {
		                        "departmentNoF": function(){
		                            return $('#org-departmentNoF').val();
		                        },
		                        "departmentNoC": function(){
		                            return $('#org-departmentNoC').val();
		                        }
		                   },
		            	   url: "${path}/sysadmin/orgManage/validateRepeatDepartmentNo.action",
		            	   type: "post"
			            }
					},
					"organization.functionalOrgType.id":{
						required:true
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
						exculdeParticalChar: "部门名称不能有特殊字符",
						remote:"部门名已经存在"
					},
					"departmentNoC": {
						required:"请输入部门编号",
						exculdeParticalChar : "部门编号不能有特殊字符",
						isSpecialValidation:"职能部门部门编号只能输入1位数字后加2位字母",
						minlength: $.format('至少需要{0}个字符'),
						maxlength: $.format('最多输入{0}个字符'),
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
		}
	}else{
		$("#org-create-form").formValidate({
			ignore:':hidden',
			promptPosition :"bottomLeft",
			submitHandler: function(form) {
				if(parseInt(${number}) != 0){
					if($("#org-departmentNoC").val() == "" || null == $("#org-departmentNoC").val()){
						$.messageBox({message:"请保持部门编号不为空!",level:"error"})
						return;
					}
				}
				$("#org-create-form").ajaxSubmit({
					success:function(data){
						if(data.organization){
							$.addNodeToLast(tree,data.extTreeData,$("#create-parentOrg-id").val());
							$("#orgChildren-dataGrid").addRowData(data.organization.id,data.organization,"last");
							$("#orgChildren-dataGrid").setSelection(data.organization.id);
							$.messageBox({
								message:"部门添加成功！"
							});
							$("#orgChildren-dataGrid").trigger("reloadGrid");
							$("#org-dailog").dialog("close");
							return ;
						}else{
							$.messageBox({
								message:data,
								level:"error"
		                    });
							return ;
						}
					}
				});
				return false;
			},
			rules: {
				"organization.orgName": {
					required: true,
					//exculdePartical:true,
					minlength: 2,
					exculdeParticalChar:true,
					remote: {
	                   data: {
	                        "organization.orgName": function(){
	                            return $('#create-organization-orgName').val();
	                        },
	                        "parentId" : function(){
	                            return $("#orgDetail-orgId").val();
	                        }
	                   },
	            	   url: "${path}/sysadmin/orgManage/validateOrgName.action",
	            	   type: "post"
		            }
				},
				"departmentNoC": {
					required:true,
					exculdeParticalChar:true,
					nonNegativeInteger:true,
					minlength: publicMin,
					maxlength: publicMax,
					remote: {
	                   data: {
	                        "departmentNoF": function(){
	                            return $('#org-departmentNoF').val();
	                        },
	                        "departmentNoC": function(){
	                            return $('#org-departmentNoC').val();
	                        }
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
					//exculdePartical:"部门名称只能输入字母，数字和中文字符",
					minlength  : $.format("至少需要{0}个字符"),
					exculdeParticalChar: "部门名称不能有特殊字符",
					remote:"部门名已经存在"
				},
				"departmentNoC": {
					required:"请输入部门编号",
					exculdeParticalChar : "部门编号不能有特殊字符",
					nonNegativeInteger:"部门编号只能输入数字",
					minlength: $.format('至少需要{0}个字符'),
					maxlength: $.format('最多输入{0}个字符'),
					remote:"部门编号已经存在"
				},
				"organization.contactWay":{
					telephone:"请填写正确的联系电话"
				},
				"organization.remark":{ 
					maxlength  : $.format("部门备注不能大于{0}个字符")
				}
			}
		});
	}
});
</script>
