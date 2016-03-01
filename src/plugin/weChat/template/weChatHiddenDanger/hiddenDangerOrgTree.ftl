<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div  title="请选择部门" id="beforeMove">
		<div id="organizationTree" style="clear: both;"></div>
</div>

<form id="maintainShiftForm" method="post" action="${path}/weChatHiddenDangerManage/assignWeChatHiddenDanger.action" >

	<input name="orgId" id="orgId" value="${weChatHiddenDanger.orgId}" type="hidden"/>
	<input name="toOrgId" id="shiftOrgId" value="" type="hidden" class='form-txt' style="width:0px;border:0px;height:0px;overflow:hidden;position:absolute;top:25px;left:70px;"/>
	<input name="ids" id="ids" value="${weChatHiddenDanger.ids}" type="hidden"/>
</form>
<div id="errorList"></div>
<script type="text/javascript">
var orgId = $("#orgId").val();
var orgSelector;


function closeDialog(){
	var selectOrgId=$.getSelectedNode(orgSelector).attributes.id;
	if (selectOrgId!=null){
		$("#shiftOrgId").val(selectOrgId);
	}
}
function initRootOrgId(){
		orgPangtId=USER_ORG_ID;
	
}
jQuery.validator.addMethod("isGridOrganization", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async:false,
			url:"${path}/sysadmin/orgManage/isGridOrganization.action",
			data:{
				"organization.id":$("#shiftOrgId").val()
			},
			success:function(responseData){
				bol = responseData;
			}
		});
		if(!bol){
			$.messageBox({level:'warn',message:"数据只能转移到网格"});
		}
		return bol;
	});
$(document).ready(function(){
	initRootOrgId();
	orgSelector=$("#organizationTree").initAdministrativeTree({
		rootId:orgPangtId,
		loadCom:function(){
			$.disableNode(orgSelector,orgPangtId);
			$.ajax({
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+orgId,
				success:function(data){
					$.searchChild(orgSelector,data,function(){
						$.disableNode(orgSelector,orgId);
					});
				}
			});
	}});
	$.addClick(orgSelector,closeDialog);
	
	$("#maintainShiftForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			$(form).ajaxSubmit({
				success: function(data){
				var str=data+"";
					if(data!="true"){
						$.messageBox({level:"error",message:data});
						return;
					}
					$.messageBox({message:"指派到任务清单成功!"});
					$("#weChatHiddenDangerDialog").dialog("close");
					 findList({
                               "weChatHiddenDanger.orgId" :getCurrentOrgId()
                     });
				},
				error: function(data){
					$.messageBox({
						message:data,
						level: "error"
					});
				}
			});
		},
		rules:{
			"toOrgId":{
				isGridOrganization:true
			}
		},
		messages:{
			"toOrgId":{
				isGridOrganization:"数据只能指派到网格"
				}
		}
	});
});

</script>