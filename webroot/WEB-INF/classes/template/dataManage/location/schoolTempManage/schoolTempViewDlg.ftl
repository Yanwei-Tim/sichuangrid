<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
    <div id="school" class="container container_24">
    <input id="populationId" type="hidden" name="population.populationId" value="${location.id}" />
	<input id="populationType" type="hidden" name="population.populationType" value="${locationType}" />
		<div id=tabs>
			<ul>
				<li><a href="#commonLocal">基本信息</a> </li>
				<@pop.JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocation.action?populationType=${locationType}&mode=view1&id=${location.id}">治安负责人</a></li>
			</@pop.JugePermissionTag>
				<@pop.JugePermissionTag ename="serviceRecordManagement">
				<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?mode=page&fromSource=population&id=${location.id}&populationType=${locationType}">巡场情况</a></li>
				</@pop.JugePermissionTag>
			</ul>
			<div id="commonLocal"></div>
   		</div>
  </div>
	<script>
	$(function() {
		$( "#tabs" ).tabs();
		$.ajax({
			url:"${path}/baseinfo/schoolManage/lookSchoolAction.action?location.id=${location.id}&mode=view&school.id=${location.id}&orgId=" + getCurrentOrgId(),
			success:function(data){
				$("#commonLocal").html("");
				$("#commonLocal").html(data);
			}
		});	
	});
	</script>