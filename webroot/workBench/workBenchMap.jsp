<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="workbenchMap" >
	<iframe id="ifr" name="ifr" width="100%"  height="300px" src=""></iframe>
</div>
 
<script type="text/javascript">
var gisType="2D";
var useOrg = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getDepartmentNo()'/>";
$(function(){
	$("#workbenchMap").load("${path}/openLayersMap/product_3.0/workBenchGis.jsp?organizationId="+getCurrentOrgId())
	/**
	//var src="<s:property value='@com.tianque.core.util.GridProperties@GIS_SERVER'/><s:property value='@com.tianque.core.util.GridProperties@GIS_INDEX_MAP'/>?sid=<s:property value='@com.tianque.core.util.ThreadVariable@getSession().sessionId'/>&organizationId="+getCurrentOrgId();
	var src="${path}/openLayersMap/product_3.0/map.jsp?organizationId="+getCurrentOrgId()+"&gisType="+gisType;
	$("#ifr").attr("src",src);
	*/
});
</script>
