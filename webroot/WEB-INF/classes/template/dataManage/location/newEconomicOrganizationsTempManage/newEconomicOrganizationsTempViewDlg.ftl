<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
    <div id="newEconomicOrganizations" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="#commonLocal">基本信息</a> </li>
				<@pop.JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocation.action?populationType=NEWECONOMICORGANIZATIONS&mode=view1&id=${newEconomicOrganizations.id}">治安负责人</a></li>
			</@pop.JugePermissionTag>
				<@pop.JugePermissionTag ename="serviceRecordManagement">
				<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?mode=page&fromSource=population&id=${newEconomicOrganizations.id}&populationType=NEWECONOMICORGANIZATIONS">巡场情况</a></li>
				</@pop.JugePermissionTag>
			</ul>
			<div id="commonLocal"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
		$.ajax({
			url:"${path}/baseinfo/newEconomicOrganizationsManage/viewNewEconomicOrganizations.action?newEconomicOrganizations.id=${newEconomicOrganizations.id}",
			success:function(data){
				$("#commonLocal").html("");
				$("#commonLocal").html(data);
			}
		});	
	});
	</script>