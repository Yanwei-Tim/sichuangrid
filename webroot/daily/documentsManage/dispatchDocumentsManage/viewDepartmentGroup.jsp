<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="departmentGroup-form" title="主送对象" class="container container_24">
    
    <div class="grid_24">
        <s:iterator value="organizations" var="organization">
            <div class="grid_6">
			   <span>${organization.orgName }</span>
			</div>
		</s:iterator>
	  </div>
</div>
