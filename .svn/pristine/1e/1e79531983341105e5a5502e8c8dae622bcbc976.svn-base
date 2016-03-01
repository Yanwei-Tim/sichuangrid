<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewManageTeamMembers" class="container container_24">

	<div class="btnbanner btnbannerData">
		<div  class="grid_6 lable-right">
			<label class="form-lbl" style="display:block;">当前队伍名称： </label>
		</div>
		<div class="grid_16" id="userDiv" style="*width:90%">
		    	<input type="text" name="fourTeams.parentNames"  id="parentNames" disabled maxlength="32" class="form-txt" value="${fourTeams.parentNames}"/>
  		</div>
  		<div class="grid_6 lable-right" style="*margin-left:48px">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_16">
			<input type="text" name="fourTeams.parentComments" style="*width:690px;" id="parentComments" disabled maxlength="32" class="form-txt" value="${fourTeams.parentComments}"/>
   		</div>
	</div>
	<div style="width: 100%;">
			<table id="teamManagementLists">
			</table>
			<div id="teamManagementListsPager"></div>
	</div>
	<div id="addMemberDialog"></div>

	<div id="tabs">
		<ul>
			<li id="liHava"><a id="hava"  href='${path}/fourTeamsManage/viewManageFourTeamMemberList.jsp?id=${param.id}'>队伍成员</a></li>
		</ul>
	</div>

</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
<pop:formatterProperty name="politics" domainName="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />

$("#refreshSearchKey").click(function(){$("#searchText").attr("value","");});
$(function() {
	$("#tabs").tabs({cache:false});
	//$( "#tabs").tabs();
});

$("#fastSearchButton").click(function(){
search(getCurrentOrgId());
});

</script>