<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewMaintenanceTeam" class="container container_24">

	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
		    <input type="text" value="请输入姓名或手机号" name="searchText" id="searchText" 
		    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名或手机号':this.value;"
		    onfocus="value=(this.value=='请输入姓名或手机号')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a id="fastSearchButton" href="javascript:;"><span>检索</span></a>
<%-- 		<pop:JugePermissionTag ename="addPromaryOrgMemberFourLevelPlatform"> --%>
<%-- 			<a id="addMember" href="javascript:;"><span>新增</span></a> --%>
<%-- 		</pop:JugePermissionTag> --%>
		<input id="isHaveJob0" value="0" type="hidden"/>
	</div>
<div id="addMemberDialog"></div>

	<div id="tabs">
		<ul>
			<li id="liHava"><a id="hava"  href='${path}/fourTeamsManage/maintenanceTeamList.jsp'>队伍信息</a></li>
		</ul>
	</div>
</div>
<script type="text/javascript">
$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
	$(function() {
		$("#tabs").tabs({cache:false});
		//$( "#tabs").tabs();
	});
	
</script>