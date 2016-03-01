<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />


<input name="keyType" id = "keyType" value="<@s.property value="#parameters.keyTpe"/>" type="hidden"></input>
<div id="place" class="SigmaReport"></div>

<script type="text/javascript">

$(document).ready(function() {
	onOrgChanged();
});

function onOrgChanged(){
	var keyType=$("#keyType").val();
	var chartCount = $("#place").columnChart({
		url: "${path}/baseinfo/statAnalysePlace/findStatAnalysePlaceForHighchartColumnVo.action?orgId="+getCurrentOrgId()+"&keyType="+keyType,
		quantity:'个数'	
	});
	//chartCount.series[1].hide();

	enableOrDisableColumn(1);
}
</script>