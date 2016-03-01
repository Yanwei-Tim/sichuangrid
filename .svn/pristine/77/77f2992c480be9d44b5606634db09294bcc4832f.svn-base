<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>

<div id="dialog-form" title="用户维护" class="container container_24">
	 <form id="maintainForm" method="post"	action="" >
	
        <input id="id"	type="hidden" name="fourTeams.id" value="${fourTeams.id}" />
        <input id="orgId"	type="hidden" name="organizationId" value="${fourTeams.organization.id}"/>
		 <div class="grid_8 lable-right">
			<label class="form-lbl">当前队伍： </label>
		</div>
		<div class="grid_12">
			<label class="form-lbl">${fourTeams.parentNames} </label>
		</div>
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">队伍所在网格：</label>
   		</div>
		<div class="grid_12">
		    	<div  title="请选择部门">
					<div class="nav">
						<input type="text" name="fourTeams.orgCode" id="orgCode"  class="form-txt" value="${fourTeams.organization.id}"/>
					 </div>
				</div>
  		</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">子队伍名： </label>
		</div>
		<div class="grid_12">
		    	<input type="text" name="fourTeams.names" id="names" maxlength="32" class="form-txt" value="${fourTeams.names}"/>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_8 lable-right">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_12">
   			<textarea rows="5" cols="77" name="fourTeams.comments" id="comments" class="form-txt">${fourTeams.comments }</textarea>
   		</div>
	</form>
</div>
<script type="text/javascript">
var scoreResult=0;
function getDefaultOccurOrg(){
	<s:if test="null!=fourTeams.organization && null!=fourTeams.organization.id">
		return "${fourTeams.organization.id}";
	</s:if>
	<s:else>
		return -1;
	</s:else>
}
$(document).ready(function(){
	
	var tree=$("#orgCode").treeSelect({
		inputName:"fourTeams.organization.id",
		loadCom:function(){
				$.setTreeValue(getDefaultOccurOrg(),tree); 
		}
	});

		jQuery.validator.addMethod("commentsLength", function(value, element){
				if(value==null||value==undefined||value==""){return true}
				var str = $("#comments").val();
				if(str.length>300){
					return false;
				}
				return true;
			return true;
		});
	
		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
	         $(form).ajaxSubmit({
	             success: function(data){
			        	 if(data==null || !data.id){
		                	 $.messageBox({
								message:data,
								level: "warn"
							 });
		                 	return;
		                 }
			        	 $("#maintenanceTeamList").setRowData(data.id,data);
						    $.messageBox({message:data.id+"成功保存四支队伍修改!"});
					     
					     $("#maintenanceTeamDialog").dialog("close");
					     $("#maintenanceTeamList").setSelection(data.id);
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
			rules:{
			
				"fourTeams.comments":{
					commentsLength:true
				},
				"fourTeams.orgCode":{
					required:true
				},
				"fourTeams.names":{
					required:true
				}
	
			},
			messages:{
				"fourTeams.comments":{
					commentsLength:"备注最多需要输入300个字符!"
				},
				"fourTeams.orgCode":{
					required:"请选择所在网格!"
				},
				"fourTeams.names":{
					required:"请输入队伍名!"
				}
			}
		});

		$("#maintainForm").attr("action","${path}/fourTeamsManage/editTeam.action?id="+${param.id});

});

</script>