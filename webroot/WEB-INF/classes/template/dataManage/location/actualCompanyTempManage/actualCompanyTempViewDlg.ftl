<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
    <div id="actualCompany" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="#actualCompanyBaseInfo">基本信息</a> </li>
			<@pop.JugePermissionTag ename="serviceTeamMemberManagement">
			<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceMemberForLocation.action?populationType=actualCompany&mode=view1&id=${location.id}">治安负责人</a></li>
			</@pop.JugePermissionTag>
				<@pop.JugePermissionTag ename="serviceRecordManagement">
				<li><a href="/plugin/serviceTeam/router/routerManage/maintainServiceRecordForPopulation.action?mode=page&fromSource=population&id=${location.id}&populationType=actualCompany">巡场情况</a></li>
				</@pop.JugePermissionTag>
			</ul>
			<div id="actualCompanyBaseInfo"></div>
   		</div>
  	</div>
	<script>
	$(function() {
		$( "#tabs" ).tabs({ selected: 0 });
		$.ajax({
			url:"${path}/baseinfo/actualCompanyManage/viewActualCompany.action?location.id=${location.id}",
			success:function(data){
				$("#actualCompanyBaseInfo").html("");
				$("#actualCompanyBaseInfo").html(data);
			}
		});
	});
	</script>