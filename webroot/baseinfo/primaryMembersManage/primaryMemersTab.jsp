<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<s:include value="/common/orgSelectedComponent.jsp"/>
<div id="viewPrimaryMemer" class="container container_24">
	<div id="PrimaryMemer" style="padding: 5px 0;">
		<div id=tabs>
			<ul>
				<li><a id="hava"  href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryMembersList&primaryMembers.isHaveJob=0'>现有成员</a></li>
				<li><a id="nothava" href='${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryMembersList&primaryMembers.isHaveJob=1'>卸任成员</a></li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs({cache:false});
	});
</script>