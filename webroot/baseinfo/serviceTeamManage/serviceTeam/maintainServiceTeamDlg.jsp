<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
<form id="serviceTeamForm" method="post" action="${path}/baseinfo/serviceTeamManage/maintainServiceTeam.action">
<pop:token />
        <input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="serviceTeamOrgId" type="hidden"	name="serviceManageTeam.organization.id" value="${serviceManageTeam.organization.id }" />
		<input id="serviceManageTeamId" type="hidden" name="serviceManageTeam.id" value="${serviceManageTeam.id }" />
		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
		<input name="appendMember" id="appendMember" type="hidden" value="" /> 	
	<div class="grid_4 lable-right">
	    <em class="form-req">*</em>
		<label class="form-lb1">所属区域：</label>
	</div>
	<div class="grid_7">
		<input  type="text" id="orgName" name="serviceManageTeam.organization.orgName" readonly style="width:440px;"
			value="${serviceManageTeam.organization.orgName }" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<s:if test='"edit".equals(mode)'>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">所属团队：</label>
		</div>
		<div class="grid_7">
			<input type="hidden" name="serviceManageTeam.teamClazz.id" value="${serviceManageTeam.teamClazz.id}"/>
			<select id="teamClazz" name="serviceManageTeam.teamClazz.id" class="form-select" disabled>
		   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" defaultValue="${serviceManageTeam.teamClazz.id}" 
		   		reletionId="teamTypeId" reletionName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" id="teamClazz" />
			</select>
		</div>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">团队类别：</label>
		</div>
		<div class="grid_8">
			<select id="teamTypeId" name="serviceManageTeam.teamType.id" class="form-select" disabled></select>
	  	</div>
	</s:if>
	<s:else>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">所属团队：</label>
		</div>
		<div class="grid_7">
			<select id="teamClazz" name="serviceManageTeam.teamClazz.id" class="form-select" >
		   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" defaultValue="${serviceManageTeam.teamClazz.id}" 
		   		reletionId="teamTypeId" reletionName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" id="teamClazz" />
			</select>
		</div>
		<div class="grid_4 lable-right">
		    <em class="form-req">*</em>
			<label class="form-lbl">团队类别：</label>
		</div>
		<div class="grid_8">
			<select id="teamTypeId" name="serviceManageTeam.teamType.id" class="form-select" ></select>
	  	</div>
	</s:else>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right" >
	    <em class="form-req">*</em>
		<label class="form-lb1">团队名称：</label>
	</div>
	<div class="grid_7">
		<input  type="text" id="serviceManageTeamTitle" maxlength="50" name="serviceManageTeam.name" value="${serviceManageTeam.name}" title="请输入团队名称"
			class='form-txt {required:true,maxlength:50,minlength:2,exculdeParticalChar:true,messages:{required:"请输入团队名称",maxlength:$.format("团队名称不能多于{0}个字符"),minlength:$.format("团队名称不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' />
	</div>
	<div class="grid_4 lable-right" >
		<label class="form-lbl">成立时间：</label>
	</div>
	<div class="grid_8 ">
		<input type="text" name="serviceManageTeam.establishDate" id="establishDate" maxlength="32"
			readonly="readonly"  class="form-txt" value='<s:date name="serviceManageTeam.establishDate" format="yyyy-MM-dd" />' />
	</div>
	<div class="clear"></div>
	<div class="grid_4 lable-right" >
		<label class="form-lb1">简介：</label>
	</div>
	<div class="grid_20 heightAuto">
		<textarea id="serviceManageTeamRemark" style="width:440px;height:100px" name="serviceManageTeam.remark" title="请输入简介"
		class="form-txt {maxlength:200,messages:{maxlength:$.format('简介最多需要输入{0}个字符')}}">${serviceManageTeam.remark}</textarea>
	</div>
</form>

</div>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#serviceTeamForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
         			if(!data.id){
           	 			$.messageBox({
							message:data,
							level: "error"
			 			});
            			return;
					}
         			if("add" == $("#mode").val()){
						$.messageBox({message:"成功添加团队!"});
						if($("#isSubmit").val()=="true"){
	                	 	$("#_serviceTeamDialog").dialog("close");
	                	}else{
	                		emptyObject();
		                }
		                if($("#appendMember").val()=="true"){
		                	$("#_serviceTeamDialog").dialog("close");
		                	$("#_serviceTeamDialog").createDialog({
								width:650,
								height:360,
								title:'新增成员信息',
								url:'${path}/baseinfo/serviceTeamMemberManage/dispatchOperate.action?mode=add&dailogName=_serviceTeamDialog&organizationId='+$("#currentOrgId").val()+'&teamId='+data.id,
								buttons: {
										"保存并关闭" : function(event){
								   			$("#maintainForm").submit();
								   			$("#_isSubmit").val("true");
							   			},
							   			"保存并继续" : function(event){
								   			$("#maintainForm").submit();
								   			$("#_isSubmit").val("false");
							   			}
								}
							});
		                }
						$("#_serviceTeamList").trigger("reloadGrid");
         			}
         			if("edit" == $("#mode").val()){
         				$("#_serviceTeamDialog").dialog("close");
						$.messageBox({message:"成功修改团队!"});
						$("#_serviceTeamList").trigger("reloadGrid");
         			}
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	  	   		     alert("提交错误");
	      	   	}
      	  	});
		},
		rules:{
			"serviceManageTeam.teamClazz.id":{
				required:true
			}
		},
		messages:{
			"serviceManageTeam.teamClazz.id":{
				required:"请选择团队类别"
			}
		}
	});

	function emptyObject() {
		$("#teamClazz").val("");
		$("#establishDate").val("");
		$("#teamTypeId").empty();
		$("#serviceManageTeamTitle").val("");
		$("#serviceManageTeamRemark").val("");
	}

	//成立时间
	$('#establishDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d'
	});
	<s:if test='"add".equals(mode)'>
			$("#serviceTeamOrgId").val(USER_ORG_ID);
			$.ajax({
				async: false,
				url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
				data:{
					"organization.id" : $("#currentOrgId").val()
				},
				success:function(responseData){
					$("#orgName").val(responseData);
				}
			});
	</s:if>
	
});
</script>