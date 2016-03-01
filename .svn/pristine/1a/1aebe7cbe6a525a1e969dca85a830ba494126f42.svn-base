<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<iframe id="ifr" name="ifr" width="100%" height="500" src=""></iframe>
<script type="text/javascript">
$(document).ready(function(){
	var srcUrl="<s:property value='@com.tianque.openLayersMap.util.GisProperties@GIS_SERVER'/><s:property value='@com.tianque.openLayersMap.util.GisProperties@GIS_BOUND_BUILD_DATA'/>?organizationId=${organization.id}&buildId=<s:property value='#parameters.idArr'/>&sid=<s:property value='@com.tianque.core.util.ThreadVariable@getSession().sessionId'/>"
	$("#ifr").attr("src",srcUrl);
	
});

</script>