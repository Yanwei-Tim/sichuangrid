<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
	<input type="hidden" id="organizationId" name="evaluate.organization.id" value="<s:property value="#parameters.orgId"/>" />
	
	<div class="grid_6 lable-right">
		<label class="form-lbl">年度：</label>
	</div>
	<div class="grid_16">
		<input type="text" id="evaluateYear" name="evaluate.year" class="form-txt" />
	</div>
	
	<div style="clear: both;"></div>
	<div class="grid_6 lable-right">
		<label class="form-lbl">标题：</label>
	</div>
	<div class="grid_16">
    	<input type="text" name="evaluate.title" id="evaluateTitle" class="form-txt" />
	</div>
</div>
