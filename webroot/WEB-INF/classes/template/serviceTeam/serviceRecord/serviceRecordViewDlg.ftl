<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="viewServiceTeam" class="container container_24">
	
	<div id="tabs">
		<ul>
			<li><a href='${path}/plugin/serviceTeam/serviceRecord/viewSeriviceRecord.action?showRecordType=${(showRecordType)}&dailogName=serviceRecordDialog&serviceRecord.id=${(serviceRecordVo.id)!}'>记录信息</a></li>
		</ul>
	</div>
</div>
<script>
	$(function() {
		$( "#tabs").tabs();
	});
</script>