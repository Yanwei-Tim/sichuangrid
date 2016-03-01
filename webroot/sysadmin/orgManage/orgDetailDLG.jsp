<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%> 
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
	<div class="grid_4 lable-right">
		<label class="form-lbl lable-color">部门名称：</label>
	</div>
	<div class="grid_8 view-color">
		${organization.orgName }
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl lable-color">联系电话：</label>
	</div>
	<div class="grid_8 view-color">
		${organization.contactWay }
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl lable-color">上级部门：</label>
	</div>
	<div class="grid_8 view-color">
		${organization.parentOrg.orgName}
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl lable-color">部门类型：</label>
	</div>
	<div class="grid_8 view-color">
		${organization.orgType.displayName }
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl lable-color">部门级别：</label>
	</div>
	<div class="grid_8 view-color">
		${organization.orgLevel.displayName }
	</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl lable-color">备注：</label>
	</div>
	<div class="grid_8 view-color">
		${organization.remark }
	</div>
	<s:if test="organization.functionalOrgType.id" >
		<div class="grid_4 lable-right">
			<label class="form-lbl lable-color">部门类别：</label>
		</div>
		<div class="grid_8 view-color">
		<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@FUNCTIONAL_ORG_TYPE" defaultValue="${organization.functionalOrgType.id}" />
		</div>
	</s:if>
</div>
