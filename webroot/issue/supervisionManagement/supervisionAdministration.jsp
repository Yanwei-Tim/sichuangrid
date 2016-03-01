<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
#maintainIntroduction_p{
	height: 500px;
	overflow: auto;
}
</style>

<div class="gridDiv" style="overflow:hidden;overflow-y:auto;position:relative;">
	<div>
		<h1 class="infonav-title">日常监督管理<div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editIntroduction"><span>编辑</span></a></div></h1>
		<div class="accdContent ui-widget-border">
			 <p id="maintainIntroduction_p"></p>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="supervisionAdministrationDialog"></div>
<script type="text/javascript">

$(function(){
	$.ajax({
		async:false,
		url:"${path}/supervisionAdministrationManage/dispatch.action",
		data:{
			"supervisionAdministration.orgId":USER_ORG_ID,
			"mode":"view"
		},
		success:function(responseData){
			if(null!=responseData && typeof(responseData) != "undefined"){
				$("#maintainIntroduction_p").empty().append(responseData.content);	
			}	
		}
	});

	$("#editIntroduction").click(function(event){
		$("#supervisionAdministrationDialog").createDialog({
			width: 520,
			height: 450,
			title:"编辑日常监督管理",
			url:"${path}/supervisionAdministrationManage/dispatch.action?supervisionAdministration.orgId="+USER_ORG_ID,
			buttons:{
				"保存" : function(event){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	


})
</script>