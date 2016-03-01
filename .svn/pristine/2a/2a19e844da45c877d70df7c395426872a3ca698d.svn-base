<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="dialog-form" title="红袖套成员维护" class="container container_24">
	 <form id="searchForm" method="post" action="${path }/baseinfo/redCuffTeamManage/findRedCuffTeamForPageResult.action" >
	 <input name="redCuffTeam.organization.id"  type="hidden" value="${redCuffTeam.organization.id}" />
           <input name="redCuffTeam.organization.orgInternalCode"  type="hidden" value="${redCuffTeam.organization.orgInternalCode}" />
        
  		<div class="grid_4 lable-right">
			<label class="form-lbl">姓名： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="redCuffTeam.memeberName" maxlength="10" class="form-txt" style="float:left;" />
  		</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">身份证号码： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="redCuffTeam.idCardNo" id="idCardNo" maxlength="20" class="form-txt" style="float:left;" />
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		
  		<div class="grid_4 lable-right">
			<label class="form-lbl">性别： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="redCuffTeam.gender.id" id="gender" class="form-txt" style="width: 246px;">
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"/>
		    </select>
  		</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">出生年月： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="redCuffTeam.birthDate" id="birthDate"  class="form-txt" style="float:left;" readonly />
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">手机号码： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="redCuffTeam.phoneNumber" maxlength="15"  class="form-txt " style="float:left;" />
  		</div>
  		
  		<div class="grid_4 lable-right">
			<label class="form-lbl">民族： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="redCuffTeam.nation.id" class="form-txt" style="width: 246px;" >
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION"/>
		    </select>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		
  		<div class="grid_4 lable-right">
			<label class="form-lbl">队伍类型： </label>
		</div>
		<div class="grid_8 heightAuto">
		  <select name="redCuffTeam.teamType.id" class="form-txt" id="teamType" style="width: 246px;">
			  <pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_TYPE"
			   		reletionId="subTeamType" reletionName="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_SUB_TYPE" id="teamType" />
		  </select>
  		</div>
  		
  		<div class="grid_4 lable-right">
			<label class="form-lbl">队伍类别： </label>
		</div>
		<div class="grid_8 heightAuto">
			<select id="subTeamType" name="redCuffTeam.subTeamType.id" class="form-txt" style="width: 246px;">
			</select>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">政治面貌： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="redCuffTeam.political.id" class="form-txt" style="width: 246px;">
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" />
		    </select>
  		</div>
  		
  		<div class="grid_4 lable-right">
			<label class="form-lbl">文化程度： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <select name="redCuffTeam.education.id" class="form-txt" style="width: 246px;">
		     	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING"/>
		    </select>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_4 lable-right">
			<label class="form-lbl">职业： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input  name="redCuffTeam.occupation" maxlength="20" class="form-txt" style="float:left;"/>
  		</div>
  		
  		<div class="grid_4 lable-right">
			<label class="form-lbl">是否绑定认证： </label>
		</div>
		<div class="grid_8 heightAuto">
		    <input type="radio" name="redCuffTeam.isCertification" value="0" id = "notCertification"/>否&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="1" id = "yesCertification" name="redCuffTeam.isCertification"/>是
  		</div>
	</form>
</div>	
<script type="text/javascript">
$(document).ready(function(){
	$('#birthDate').datePicker({
		yearRange:'1930:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d'
});
$("#searchForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				var params = $(form).serialize();
				jQuery("#redCuffMemeberList").setPostData({});
				var url = jQuery("#redCuffMemeberList").getGridParam("url");
				jQuery("#redCuffMemeberList").setGridParam({"url":"${path}/baseinfo/redCuffTeamManage/findRedCuffTeamForPageResult.action?"+params});
				$("#redCuffMemeberList").trigger("reloadGrid");
		    }
		});
});


</script>