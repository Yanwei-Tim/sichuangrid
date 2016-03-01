<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="positiveinfoPie" class="SigmaReport" ></div>

<script type="text/javascript">
$(document).ready(function() {
	onOrgChanged();
});

function onOrgChanged(){
	$("#positiveinfoPie").pieChart({
		url:"${path}/baseinfo/evaluateStatistic/getEvaluateColumnByOrgId.action?orgId="+getCurrentOrgId(),
		moduleName:"考核评估"
	});
}
</script>