<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="dangerousChemicalsUnit" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="${path}/baseinfo/newSocietyOrganizationsManage/viewNewSocietyOrganizations.action?location.id=${location.encryptId}">基本信息</a> </li>
				<pop:JugePermissionTag ename="serviceTeamMemberManagement">
		
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocation.action?populationType=NEWSOCIETYORGANIZATIONS&mode=view1&id=${location.id}">治安负责人</a></li>
			
			</pop:JugePermissionTag>
			
				<pop:JugePermissionTag ename="serviceRecordManagement">
				<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?mode=page&fromSource=population&id=${location.id}&populationType=NEWSOCIETYORGANIZATIONS">巡场情况</a></li>
				</pop:JugePermissionTag>
				<%-- <li><a href="${path }/baseinfo/tracks/orgLocationTrackList.jsp?orgLocationOrgId=${newSocietyOrganizations.organization.id}&orgLocationName=${newSocietyOrganizations.name}&width=750&height=370">轨迹信息</a></li> --%>	
			
			</ul>
			<div id="commonLocal"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
		/*$.ajax({
			url:"${path}/baseinfo/newSocietyOrganizationsManage/viewNewSocietyOrganizations.action?location.id=${location.id}",
			success:function(data){
				$("#commonLocal").html("");
				$("#commonLocal").html(data);
			}
		});*/
	});
	</script>