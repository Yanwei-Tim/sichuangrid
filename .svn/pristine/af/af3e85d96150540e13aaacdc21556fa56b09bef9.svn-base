<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="redCuffTeamMemberSelectDialog" class="container container_24">
		
  		<div class="grid_10 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">队伍类型： </label>
		</div>
		<div class="grid_10 heightAuto">
		  <select name="redCuffTeam.teamType.id"   id="redCuffTeamMemberTeamType" class="form-txt " >
			  <pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_TYPE"  
			   		reletionId="redCuffTeamMemberSubTeamType" reletionName="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_SUB_TYPE" id="redCuffTeamMemberTeamType" />
			   <option value="">所有</option>
		  </select>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_10 lable-right">
  			<em class="form-req" id="maritalStateStat"  >*</em>
			<label class="form-lbl">队伍类别： </label>
		</div>
		<div class="grid_10 heightAuto">
			<select id="redCuffTeamMemberSubTeamType" name="redCuffTeam.subTeamType.id" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_SUB_TYPE"  />
			</select>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
</div>
<script type="text/javascript">
$(function(){
	$("#redCuffTeamMemberTeamType").change(function(){
		$("#redCuffTeamMemberSubTeamType").append('<option value="" id="subTeamOptionAll">所有</option>');
	});
})
</script>