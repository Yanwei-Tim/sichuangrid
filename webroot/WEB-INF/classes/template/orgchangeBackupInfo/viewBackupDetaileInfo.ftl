<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<input type="hidden" id="details" value="${(details)!}"/>
	<div id="logDetailedDlg"><span id="backupDetailedInfo"></span></div>
	
<script type="text/javascript" >
$(document).ready(function(){
	var details = $("#details").val();
	if(details!=null &&　details!=""){
		$("#backupDetailedInfo").html(details);
	}else{
		$("#backupDetailedInfo").html("无详细信息");
	}
});
</script>