
<%@page import="com.tianque.core.util.ThreadVariable"%>
<%@page import="com.tianque.core.util.GlobalValue"%><%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
request.setAttribute("path",path);
String resource_path=path;
if("production".equals(GlobalValue.environment)){
	resource_path=GridProperties.RESOURCE_PATH;
}
request.setAttribute("resource_path",resource_path);
request.setAttribute("current_project",GridProperties.CURRENT_PROJECT);
request.setAttribute("currentSession",ThreadVariable.getSession());
if(ThreadVariable.getUser()!=null){
	request.setAttribute("USER_ORG_ID",ThreadVariable.getUser().getOrganization().getId());
	request.setAttribute("USER_ID",ThreadVariable.getUser().getId());
}
if(ThreadVariable.getOrganization()!=null){
	request.setAttribute("USER_ORG_LEVEL",ThreadVariable.getOrganization().getOrgLevel().getInternalId());
}
%>

<%@page import="com.tianque.core.util.GridProperties"%>
<script type="text/javascript">
    var PATH='${path}';
    var RESOURCE_PATH='${resource_path}';
    var USER_ORG_ID='${USER_ORG_ID}';
    var USER_ORG_LEVEL='${USER_ORG_LEVEL}';
    var listUrl;
    var USER_ID="${USER_ID}";

    var version = '?1.0';
    var development = true;
</script>