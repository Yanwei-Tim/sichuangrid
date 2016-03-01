<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<@s.include value="${path}/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<@s.param name="moduleNameTemp">SreviceTeamMember</@s.param>
</@s.include>
<style type="text/css">
#load {
	width: 800px;
	height: 600px;
	text-align: center;
}
</style>
<div  title="请选择部门">
		<div id="organizationTree" style="clear: both;"></div>
	</div>
<form id="changeOrgForm" method="post" action="${path}/plugin/serviceTeam/serviceTeamMember/changeOrg.action">
	<input name="orgId" id="orgId" value="" type="hidden" />
	<input name="selectedIds" id="selectedIds" value="${(selectedIds)!}" type="hidden" />
</form >

<script type="text/javascript" >
var orgSelector;
function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		$("#orgId").val(selectOrgId);
	}
}
$(document).ready(function(){
	orgSelector=$("#organizationTree").initTree({rootId:USER_ORG_ID});
	$.addClick(orgSelector,closeDialog);
	//表单验证
	$("#changeOrgForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
		         success: function(data){
					$.messageBox({message:"成功转移层级!"});
					$("#serviceTeamMemberList").trigger("reloadGrid");
					$("#_serviceTeamMemberDialog").dialog("close");
				},
			    error: function(XMLHttpRequest, textStatus, errorThrown){
		         	$.messageBox({message:"提交错误",level: "error"});
			    }
			});
		}
	});
});

</script>