<!DOCTYPE>
<html>
	<head>	
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
		<%@ taglib prefix="s" uri="/struts-tags"%>
		<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
		<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
		<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
		<%
			GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
			String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
			String sysHeaderPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_HEADER_PAGE);
			String simpleRelease = globalSettingService.getGlobalValue(GlobalSetting.SIMPLE_RELEASE);
			request.setAttribute("sysHeaderPage",sysHeaderPage);
			request.setAttribute("simpleRelease",simpleRelease);
			request.setAttribute("sysTitle",sysTitle);
		%>
		<title><%=sysTitle %></title>
		<jsp:include page="/includes/baseInclude.jsp" />
		<jsp:include page="/includes/jsInclude.jsp" />
		<jsp:include page="/uba/includes.jsp" />
		<jsp:include page="/includes/baseJs.jsp" />
		<%-- 新版本介绍暂不需要注释
		<s:action name="getVersionIsReaded" var="getHasRead" executeResult="false" namespace="/versionCheckManage" >
		</s:action>
		--%>
	</head>
	<body>
		<input type="hidden" id="isSwitchover" value="<s:property value='#parameters.isSwitchover'/>">	
		<input type="hidden" id="isSwitchover" value="<s:property value='#parameters.isSwitchover'/>">
		<div id="loadingMain" style="top: 20%; width:300px; height: 100px; left: 40%; position: absolute;">
			<div id="mainProgressbar" style="height: 30px;"></div>
			<div id="mainProgressbarText" style="color:#000; text-align: center; height:20px; line-height:20px;top: 6px; width:100%; font-size: 12px; left: 10px; position: absolute;">正在准备数据，请稍后...</div>
		</div>
		<script type="text/javascript">
				$("#mainProgressbar").progressbar({value: 100});
				$("#mainProgressbarText").css({'color':'#fff'}).text('加载完成！跳转中...');
		</script>
		<div class="ui-layout-north">
			<input type="hidden" id="CURRENT_SYSTEM_NAME" user="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>" orgName="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()"/>" value="<s:property value="@com.tianque.core.util.GridProperties@CURRENT_PROJECT" />">
			<s:action namespace="/sysadmin/menuManage" name="getNavigationList"  executeResult="true" ></s:action>		
			<input type="hidden" id="currentOrgId"
			 <%-- 如果是职能部门获取它所在行政单位的相关信息 --%>
			 <s:if test="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgType().getInternalId()==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG">
			 	 orglevelinternalid="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgLevel().getInternalId()" />" 
				 text="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgName()" />" 
				 leafNum="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getSubCount()" />" 
				 value='<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getId()"/>' />
			 </s:if>
			 <s:else>
			 	orglevelinternalid="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().getInternalId()" />" 
			 	text="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()" />" 
				leafNum="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getSubCount()" />" 
			 	value='<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>' 
			 </s:else>
			 />
		</div>
		
		<div class="ui-layout-west"></div>
		<div class="ui-layout-center">
			<div class="slideResizer"><div class="slideToggler" title="缩进"></div></div>
			<jsp:include page="/includes/breadcrumbTrail.jsp"></jsp:include>
			<div class="content">
				<div id="loading" style="display: none;color:#999;text-align:center;height:32px;line-height:32px;margin-top:200px;"><img
					src="${resource_path}/resource/images/loading.gif" alt="加载中..." style="vertical-align:middle;margin-right:5px;" />加载中，请稍候...</div>
				<div id="contentDiv"></div>
			</div>
		</div>
		<div class="ui-layout-south">
			<div id="bottom">
				<jsp:include page="/includes/footer.jsp"></jsp:include>
			</div>
		</div>
		<div id="jGrowl"></div>
		<div id="baseLine"></div>
		<div id="globalOrgBox"></div>
	</body>
	<!--[if IE 6]>
	<script type="text/javascript">
		$(function(){
			DD_belatedPNG.fix('.ui-icon,.ui-widget-content .ui-jqgrid-btable .ui-icon,.issueList li.current span.arrow');
		})
	</script>
	<![endif]-->
	
</html>