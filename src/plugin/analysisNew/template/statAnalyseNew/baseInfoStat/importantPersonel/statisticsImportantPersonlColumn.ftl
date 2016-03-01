<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="importantPersonlColumn" class="SigmaReport"></div>
<script type="text/javascript">


$(document).ready(function() {
	
	onOrgChanged();
});

function onOrgChanged(){
	var chartCount=$("#importantPersonlColumn").columnChart({
		url: "${path}/baseInfoStat/statisticsPersonnel/getStatisticsImportantPersonlColumn.action?orgId="+getCurrentOrgId(),
		moduleName:"特殊人群区域分布图",
		quantity:'人数'	
	});
	enableOrDisableColumn(1);
}

</script>