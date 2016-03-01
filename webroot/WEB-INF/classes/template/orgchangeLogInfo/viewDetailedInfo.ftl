<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<input type="hidden" id="detailedInfo" value="${(detailedInfo)!}"/>
	<div id="logDetailedDlg"><span id="logDetailedInfo"></span></div>
	
<script type="text/javascript" >
$(document).ready(function(){
	var detailedInfo = $("#detailedInfo").val();
	if(detailedInfo!=null &&　detailedInfo!=""){
		$("#logDetailedInfo").html(detailedInfo);
	}else{
		$("#logDetailedInfo").html("无详细信息");
	}
});
</script>