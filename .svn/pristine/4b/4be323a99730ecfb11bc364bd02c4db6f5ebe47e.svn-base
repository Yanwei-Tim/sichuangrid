<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content">
	<br><br><div align='center'>您已成功新增台账目录信息！
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
		var dailyYearId = "${param.dailyYearId}";
		$.ajax({
			url:'${path}/daily/dailyYearManage/startDailyYear.action?dailyYear.id='+dailyYearId,
			success:function(data){
				if(parseInt(data.id)>0){
					$.messageBox({message:"该台账目录成功启用!"});
					$("#dailyYearList").trigger("reloadGrid");
					$("#dailyDirectoryDialog").dialog("close");
					$("#dailyTrunkDirectoryDailog").dialog("close");
					
				}else{
					$.messageBox({message:msg,level:"error"});
				}
				return ;
			}
		});
	});
	
	$("#startLater").click(function(){
		var dailyYearId = "${param.dailyYearId}";
		$("#dailyDirectoryDialog").createDialog({
			width:330,
			height:190,
			title:"设置启用提醒",
			url:"${path}/daily/dailyDirectory/startDailyLater.jsp?dailyYearId="+dailyYearId,
			buttons :{
				"保存" : function(){
					$("#startDailyLater").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
});
</script>