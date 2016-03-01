<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="container1" class="SigmaReport"></div>

<script type="text/javascript">
$(document).ready(function() {
	$.gridboxHeight();
	onOrgChanged();
});

function onOrgChanged(){
	$("#container1").lineChart({
		url: "${path}/baseInfoStat/tendencyChart/showTendencyChartForPositiveinfo.action?organization.id="+getCurrentOrgId()+"&type="+'<@s.property value="#parameters.tableNameKey"/>',
		moduleName:""
	});
	enableOrDisableColumn(3);
}
</script>