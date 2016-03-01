<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
    <div class="grid_5 lable-right">
       <label class="form-lbl lable-color">显示名称：</label>
    </div>
    <div class="grid_7 view-color">
    	${propertyDict.displayName}
    </div>
	<c:if test="${gridInternalProperty!=null }">
	<div class="grid_5 lable-right">
       	<label class="form-lbl lable-color"> 内置属性：</label>
	</div>
    <div class="grid_7 view-color">
       	<c:forEach items="${gridInternalProperty }" var="gp">
			<c:if test="${propertyDict.internalId==id.identify }">
	            ${gp.displayName}
	      	</c:if>
       	</c:forEach>
	</div>
	</c:if>
</div>
