<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
	<form id="maintainForm" action="" method="post">
		<input id="orgId" type="hidden" name="organizationId" value="${param.organizationId}" />
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">队伍名： </label>
		</div>
		<div class="grid_8">
		    	<input type="text" name="fourTeams.names" id="names" maxlength="32" class="form-txt"/>
  		</div>
  		<div class="grid_9">
  		</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">子队伍数：从</label>
		</div>
		<div class="grid_8">
			<input type="text" id="subTeamNumberMin" name="fourTeams.subTeamNumberMin" maxlength="10" class="form-txt" />
		</div>
		<div class="grid_4 lable-right" >
			<label class="form-lbl">至：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="subTeamNumberMax" name="fourTeams.subTeamNumberMax" maxlength="10" class="form-txt" />
		</div>
  		
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	jQuery.validator.addMethod("subTeamNumberMaxDayuSubTeamNumberMin", function(value, element){
			
			if(value==null||value==undefined||value==""){return true}
			var str1 = $("#subTeamNumberMin").val();
			var str2 = $("#subTeamNumberMax").val();
			if(str1>str2){
				return false;
			}
			return true;
		});

	
    $("#maintainForm").formValidate({
		promptPosition: "bottomRight",
		submitHandler: function(form){
			var params = $(form).serialize();
			jQuery("#teamManagementList").setPostData({});
			var url = jQuery("#teamManagementList").getGridParam("url");
			jQuery("#teamManagementList").setGridParam({"url":"${path}/fourTeamsManage/searchFourTeams.action?"+params});
			$("#teamManagementList").trigger("reloadGrid");
		},
		rules:{
				"fourTeams.subTeamNumberMax":{
				subTeamNumberMaxDayuSubTeamNumberMin:true
					
				}
					
			},
		messages:{
				"fourTeams.subTeamNumberMax":{
				subTeamNumberMaxDayuSubTeamNumberMin:"子队伍数To不能小于子队伍数From"
			
			}
		}
	});
    
});
</script>
