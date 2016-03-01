<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="dangerousChemicalsUnit" class="container container_24">
    <input id="populationId" type="hidden" name="population.populationId" value="${location.id}" />
	<input id="populationType" type="hidden" name="population.populationType" value="${locationType}" />
		<div id=tabs>
			<ul>
				<li><a href="${path}/baseinfo/dangerousChemicalsUnitManage/viewDangerousChemicalsUnit.action?location.id=${dangerousChemicalsUnit.encryptId}">基本信息</a> </li>
				<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocationByEncrypt.action?populationType=${locationType}&mode=view1&id=${dangerousChemicalsUnit.encryptId}">治安负责人</a></li>
			</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="serviceRecordManagement">
				<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulationByEncrypt.action?mode=page&fromSource=population&id=${dangerousChemicalsUnit.encryptId}&populationType=${locationType}">巡场情况</a></li>
				</pop:JugePermissionTag>
				 <!--  <li><a href="${path }/baseinfo/tracks/orgLocationTrackList.jsp?orgLocationOrgId=${dangerousChemicalsUnit.organization.id}&orgLocationName=${dangerousChemicalsUnit.unitName}&width=750&height=370">轨迹信息</a></li>-->	
			</ul>
			<div id="commonLocal"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
		/*$.ajax({
			url:"${path}/baseinfo/dangerousChemicalsUnitManage/viewDangerousChemicalsUnit.action?location.id=${location.id}",
			success:function(data){
				$("#commonLocal").html("");
				$("#commonLocal").html(data);
			}
		});	*/
	});
	</script>