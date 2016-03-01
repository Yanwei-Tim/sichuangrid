<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="container container_24">
	<form id="maintainForm" action="" method="post">
		<input type="hidden" id="type" name="fourTeams.teamType" value="${param.teamType }"/>
		<input type="hidden" id="orgId" name="fourTeams.organization.id" value="${param.currentOrgId }"/>
		<input type="hidden" id="screeningLevel" name="screeningLevel" value="${param.screeningLevel }"/>
		<div class="grid_4 lable-right">
			<label class="form-lbl">队伍名称： </label>
		</div>
		<div class="grid_8">
		    	<input type="text" name="fourTeams.names" id="names" maxlength="32" class="form-txt"/>
  		</div>
  		<div class="grid_4 lable-right">
  			<label class="form-lbl">组建部门： </label>
  		</div>
  		<div class="grid_8" >
						<input type="text" name="fourTeams.departementName" class="form-txt"/>
							
				</div> 
  		<div class="grid_4 lable-right" >
			<label class="form-lbl">队伍成员数：</label>
		</div>
		<div class="grid_8" style="*width:33.66%">
			<input type="text" id="memberNumberMin" name="fourTeams.memberNumberMin"  maxlength="10" class="form-txt {positiveInteger:true,messages:{positiveInteger:'請輸入正整數'}}" style="width:83px;" />
			-
			<input type="text" id="memberNumberMax" name="fourTeams.memberNumberMax" maxlength="10" class="form-txt {positiveInteger:true,messages:{positiveInteger:'請輸入正整數'}}" style="width:83px;" />
		</div>
  		
	</form>
</div>

<script type="text/javascript">
function getSelected(){
	var datas;
	$.ajax({
		async:false,
		url:"${path}/account/accountLogsManage/searchFunctionTargetForList.action",
		type:"post",
		data:{ 	
		},
		success:function(data){
			if(data!=null){
				datas = eval(data);
			}
	    }
    });
	if(datas!=null && datas!=undefined){
		for(var i=0;i<datas.length;i++){
			$("#fourteamsDepartement").append("<option value='"+datas[i].label+"'>"+datas[i].label+"</option>");
		}
	}
}
$(document).ready(function(){
	//给部门下拉框赋值
	getSelected();
	jQuery.validator.addMethod("memberNumberMaxDayuMemberNumberMin", function(value, element){
		var str1 = $("#memberNumberMin").val();
		var str2 = $("#memberNumberMax").val();
		if((str1==null||str1==undefined||str1=="")||(str2==null||str2==undefined||str2=="")){return true}
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
			jQuery("#teamManagementList").setGridParam({"url":"${path}/fourTeamsManage/searchFourTeamMembers.action?"+params});
			$("#teamManagementList").trigger("reloadGrid");
		},
		rules:{
			"fourTeams.memberNumberMax":{
				memberNumberMaxDayuMemberNumberMin:true
				}
			},
		messages:{
			"fourTeams.memberNumberMax":{
				memberNumberMaxDayuMemberNumberMin:"人数起始值不能大于最大值"
			
			}
		}
	});
});
</script>
