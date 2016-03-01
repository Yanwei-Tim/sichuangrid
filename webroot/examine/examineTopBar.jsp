<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<div class="submenu">
	<div class="subnav" id="administerLi1">
		<ul class="tabnav">
			<pop:JugePermissionTag ename="examineScrose">
				<li><span>督办列表</span></li>
			</pop:JugePermissionTag>
		</ul>
		<div class="tabbox">
			<pop:JugePermissionTag ename="examineScrose">
				<ul class="subnavbar">
					<li ><a onclick="asyncMiddleLevelOpen(this,'${path}/examine/superviseList/superviseList.jsp?marker=1')">督办列表</a></li>
				</ul>
			</pop:JugePermissionTag>
			
		</div>
	</div>
</div>


