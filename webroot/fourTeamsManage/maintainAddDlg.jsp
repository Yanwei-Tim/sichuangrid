<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<div id="dialog-form" title="队伍维护" class="container container_24">
	 <form id="maintainForm" method="post"	action="" >
	 	 <pop:token/>
	 	 <c:if test='${mode=="add"}'>
         <input id="teamType" type="hidden" name="fourTeams.teamType" value="${fourteamsType}" />
         <input id="id"	type="hidden" name="fourTeams.parentTeamId" value="${parentId}" />
         </c:if>
	 
	 	 <input	type="hidden" name="fourTeams.id" value="${fourTeams.id}" />
         <input name="fourTeams.organization.id"  type="hidden" value="${fourTeams.organization.id}" />
         <input id="orgId" type="hidden" value="${organizationId}" />
         <input id="currentOrgId" type="hidden" value="${currentOrgId }"/>
        
		<div class="grid_8 lable-right">
   			<em class="form-req">*</em>
   			<label class="form-lb1">队伍所在网格：</label>
   		</div>
		<div class="grid_12">
		    	<div  title="请选择部门">
					<div class="nav" >
					  <c:if test='${mode=="add"}'>
					 	<input type="text" name="fourTeams.organization.orgName"  value="${organization.orgName }" class="form-txt" readonly="readonly" />
						<input type="hidden" name="fourTeams.orgCode" value="${organization.orgInternalCode }"/>
					  </c:if>
					  <c:if test='${mode=="edit"}'>
					 	<input type="text"  value="${fourTeams.organization.orgName }" class="form-txt"  readonly="readonly"/>
					  </c:if>
					 </div>
					 
				</div>
  		</div>
  		<div class='clearLine'>&nbsp;</div>
  		<div class="grid_8 lable-right">
			<label class="form-lbl">主管部门： </label>
		</div>
		<div class="grid_12" >
		   <select id="competentDepartment" name="fourTeams.competentDepartment.id" class="form-txt">
		   <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@FOURTEAMS_COMPETENT_DEPARTMENT"  defaultValue="${fourTeams.competentDepartment.id }"/>
		   </select>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">队伍名称： </label>
		</div>
		<div class="grid_12">
		   <input type="text"  name="fourTeams.names" id="names" maxlength="30" value="${fourTeams.names}" class="form-txt  {validatorName:true,messages:{validatorName:'队伍名称不能输入特殊字符'}}"/>
  		</div>
  		
  		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">组建部门： </label>
		</div>
		<div class="grid_12" >
				<input id="transferTo"  name="fourTeams.departementName" maxlength="30" value="${fourTeams.departementName }"  class="form-txt"/>
		</div> 
				
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">服务范围： </label>
		</div>
		<div class="grid_12 heightAuto">
		    <textarea rows="5" cols="77" name="fourTeams.serviceArea" maxlength="100" id="serviceArea" class="form-txt" style="float:left;" >${fourTeams.serviceArea }</textarea>
  		</div>
  		
  		<div class="grid_8 lable-right">
			<label class="form-lbl">备注： </label>
		</div>
		<div class="grid_12 heightAuto">
   			<textarea rows="5" cols="77" name="fourTeams.comments" id="comments"  maxlength="100" class="form-txt" >${fourTeams.comments }</textarea>
   		</div>
	</form>
</div>	
<script type="text/javascript">
$("#selectTransferTo").click(function(){
		createOrgSearchDialog("${path}/account/accountLogsManage/dispatchOperate.action?mode=fourTeamType&targetOrgId="+$("#orgId").val()
				,"transferTo");
	});
	
	function createOrgSearchDialog(searchUrl,inputId){
		$("#orgSelectDialog").createDialog({
			width:500,
			height:430,
			title:'选择部门',
			url: searchUrl,
			buttons: {
				"确定" : function(event){
						fillOrgInputer(inputId);
						fillItemName();
						$(this).dialog("close");
				},
				"关闭" : function(){
						$(this).dialog("close");
				}
			}
		});
	}
	
	function fillItem(selectedId) {
			if(selectedId == null) {
				return;
			} else {
				$.ajax({
					url:"${path}/issues/issueManage/findItemTypeByDealOrgId.action?funOrgId="+selectedId,
					async:false,
					type:'post',
					success:function(data){
						$("#itemTypeId option").remove();
						$.each(data,function(name,value) {
							$("<option>").attr("value",name).html(value).appendTo($("#itemTypeId"));
						});
						$("<option>").attr("value","").attr("selected",true).html("").prependTo($("#itemTypeId"));
					}
				});
		}
	}
	
	jQuery.validator.addMethod("exsistedName", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:'${path}/fourTeamsManage/repeatTeamName.action',
		   	data:{
		   		"fourTeams.id":'${fourTeams.id}',
				"fourTeams.names":value,
				<c:if test='${mode=="add"}'>
				"fourTeams.organization.id":'${organization.id}'
				</c:if>
			   	<c:if test='${mode=="edit"}'>
			   	"fourTeams.organization.id":'${fourTeams.organization.id}'
			   	</c:if>
				},
			success:function(responseData){
				flag=!eval(responseData);
			}
		});
		return flag;
	});
	
	jQuery.validator.addMethod("validatorName", validatorSpecialWord);

	function validatorSpecialWord(value,element){
		if(value==null||value==undefined||value==""){return true}
		var pattern = new RegExp("[`~!@%#$^&*()=|{}':;',　\\[\\]<>/? \\.；：%……+￥（）【】‘”“'。，、？ ！-]");
		return this.optional(element)||!pattern.test(value) ; 
	}
	
	jQuery.validator.addMethod("commentsLength", function(value, element){
			if(value==null||value==undefined||value==""){return true}
			var str = $("#comments").val();
			if(str.length>300){
				return false;
			}
			return true;
		return true;
	});
	
	jQuery.validator.addMethod("serviceAreaLength", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var str = $("#serviceArea").val();
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
								level: "error"
							 });
		                 	return;
		                 }
			        	 <c:if test='${mode=="add"}'>
					     $("#maintenanceTeamList").addRowData(data.id,data,"first");
						    $.messageBox({message:"成功保存信息!"});
					     </c:if>
					     <c:if test='${mode=="edit"}'>
						    $.messageBox({message:"成功保存修改信息!"});
					     </c:if>
					     $("#addMemberDialog").dialog("close");
					     $("#maintenanceTeamList").setSelection(data.id);
					     $("#teamManagementList").trigger("reloadGrid");
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	      	  });
		},
			rules:{
				"fourTeams.serviceArea":{
					serviceAreaLength:true
				},
				"fourTeams.comments":{
					commentsLength:true
				},
				"fourTeams.organization.orgName":{
					required:true
				},
				"fourTeams.names":{
					required:true,
					minlength:2,
					isLawful:true,
					exculdeParticalChar:true,
					exsistedName:true
				}

			},
			messages:{
				"fourTeams.serviceArea":{
					serviceAreaLength:"服务范围最多输入300个字符!"
				},
				"fourTeams.comments":{
					commentsLength:"备注最多需要输入300个数字!"
				},
				"fourTeams.organization.orgName":{
					required:"请选择所在网格!"
				},
				"fourTeams.names":{
					required:"请输入队伍名!",
					exsistedName:"队名已存在",
					isLawful:'您输入了非法脚本，请重新输入！',
					minlength:$.format('队伍名称至少需要输入{0}个字符'),
					exculdeParticalChar:'不能输入非法字符'
				}
			}
		});
		<c:if test='${mode=="add"}'>
		$("#maintainForm").attr("action","${path}/fourTeamsManage/addTeam.action?id="+$("#id").val());
		</c:if>
		<c:if test='${mode=="edit"}'>
		$("#maintainForm").attr("action","${path}/fourTeamsManage/updateTeam.action");
		</c:if>


</script>