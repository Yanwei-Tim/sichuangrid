<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="newEconomicOrganizations" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="${path}/baseinfo/newEconomicOrganizationsManage/viewNewEconomicOrganizationsByEncrypt.action?newEconomicOrganizations.id=${newEconomicOrganizations.encryptId}">基本信息</a> </li>
				<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocationByEncrypt.action?populationType=NEWECONOMICORGANIZATIONS&mode=view1&id=${newEconomicOrganizations.encryptId}">治安负责人</a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="serviceRecordManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?mode=page&fromSource=population&id=${newEconomicOrganizations.id}&populationType=NEWECONOMICORGANIZATIONS">巡场情况</a></li>
			</pop:JugePermissionTag>
			<%-- <li><a href="${path }/baseinfo/tracks/orgLocationTrackList.jsp?orgLocationOrgId=${newEconomicOrganizations.id}&orgLocationName=${newEconomicOrganizations.name}&width=750&height=370">轨迹信息</a></li> --%>	
	
			</ul>
			<div id="commonLocal"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
		/*$.ajax({
			url:"${path}/baseinfo/newEconomicOrganizationsManage/viewNewEconomicOrganizations.action?newEconomicOrganizations.id=${newEconomicOrganizations.id}",
			success:function(data){
				$("#commonLocal").html("");
				$("#commonLocal").html(data);
			}
		});	*/
	});
	</script>