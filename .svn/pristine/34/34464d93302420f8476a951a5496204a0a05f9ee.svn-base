<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<input type="hidden" id="orgId" value="<s:property value='#parameters.orgId'/>"/>
<div id="introduceTabs">
		
		<ul>
			<li id="userInformation"><a  href="${path}/openLayersMap/introduce/userListForIntroduce.jsp?currentOrgId=<s:property value='#parameters.orgId'/>">辖区帐号</a></li>
			<li id="serviceTeam" ><a  href="${path}/openLayersMap/introduce/serviceTeamListForIntroduce.jsp?currentOrgId=<s:property value='#parameters.orgId'/>">服务团队</a></li>
			<li id="serviceMember" ><a href="${path}/openLayersMap/introduce/serviceTeamMemberListForIntroduce.jsp?currentOrgId=<s:property value='#parameters.orgId'/>">服务成员</a></li>
			
			
		</ul>
</div>

<script type="text/javascript">
$("#introduceTabs").tabFunction({ cache: true });
</script>