<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content">
	<br><br><div align='center'>您已成功新增越级规则！
	</div><br><br><br>
	<div class="ui-corner-all" id="nav" align='center'>
		<pop:JugePermissionTag ename="startDailyDirectory">
		<a id="startNow" href="javascript:void(0)" ><span>立即启用</span></a>
		</pop:JugePermissionTag>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<pop:JugePermissionTag ename="startDailyDirectory">
		<a id="startLater" href="javascript:void(0)" ><span>稍后启用</span></a>
		</pop:JugePermissionTag>
	</div>
	<div id="startDailyDialog"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#startNow").click(function(){
		var ruleId = "${param.ruleId}";

		$.ajax({
			url:'${path}/issueSkipruleManage/startIssueSkipruleByIds.action',
			type:"post",
			data:{
				"ids":ruleId
			},
			success:function(data){
				if(data==true||data=="true"){
				    $.messageBox({message:"启用成功!"});
					$("#issueSkipruleList").trigger("reloadGrid");
					$("#enableIssueSkipruleDialog").dialog("close");
				}else{
					$.messageBox({
						message:data,
						level:"error"
			        });
				}
			}
		});		
		
	});
	
	$("#startLater").click(function(){
		$("#enableIssueSkipruleDialog").dialog("close");
	});
});
</script>