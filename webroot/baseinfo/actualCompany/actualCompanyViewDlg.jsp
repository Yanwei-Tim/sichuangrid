<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
    <div id="actualCompany" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="${path}/baseinfo/actualCompanyManage/viewActualCompanyByEncrypt.action?location.id=${location.encryptId}">基本信息</a> </li>
			<pop:JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocationByEncrypt.action?populationType=actualCompany&mode=view1&id=${location.encryptId}">治安负责人</a></li>
			</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="serviceRecordManagement">
				<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulationByEncrypt.action?mode=page&fromSource=population&id=${location.encryptId}&populationType=actualCompany">巡场情况</a></li>
				</pop:JugePermissionTag>
			</ul>
			<div id="actualCompanyBaseInfo"></div>
   		</div>
  	</div>
	<script>
	$(function() {
		$( "#tabs" ).tabs({ selected: 0 });
		/* $.ajax({
			url:"${path}/baseinfo/actualCompanyManage/viewActualCompany.action?location.id=${location.id}",
			success:function(data){
				$("#actualCompanyBaseInfo").html("");
				$("#actualCompanyBaseInfo").html(data);
			}
		}); */
	});
	</script>