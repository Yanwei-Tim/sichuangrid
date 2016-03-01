<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="newServiceTeamMember" class="container container_24">
	<form action="${path}/baseinfo/serviceTeamMemberManage/maintainServiceTeamMemberForTeam.action" method="post" id="maintainForm">
	<pop:token />
		<input id="mode" type="hidden" name="mode" value="${mode}" /> 
		<input name="_isSubmit" id="_isSubmit" type="hidden" value="" />
		<input id="_teamId" type="hidden" name="serviceTeamMemberDetails.teamId" value="${teamId}" />
		<input id ="_baseId" type="hidden" name="serviceTeamMemberBase.id" />
		<input type="hidden" name="serviceTeamMemberBase.organization.id" value="${organizationId}" />
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">身份证号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="serviceTeamMemberBase.idCardNo" id="_idCardNo" value="${serviceTeamMemberBase.idCardNo}" maxlength="18" 
				<s:if test='"edit".equals(mode)'>readonly</s:if>
				class="form-txt {required:true,exsistedIdCard:true,idCard:true,messages:{required:'请输入身份证号码',idCard:'请输入一个合法的身份证号码',exsistedIdCard:'该身份证号码已经存在，请重新输入'}}" />
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> 
			<label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_7">
			<input type="text" style="width: 95%" name="serviceTeamMemberBase.name" id="_name" value='${serviceTeamMemberBase.name}'
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> 
			<label class="form-lb1">性别：</label>
		</div>
		<div class="grid_7">
			<select name="serviceTeamMemberBase.gender.id" id="_gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${serviceTeamMemberBase.gender.id}" />
			</select>
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">职务：</label>
		</div>
		<div class="grid_7">
			<select name="serviceTeamMemberDetails.position.id" class='form-txt {required:true,messages:{required:"请选择职务"}}'>
				<s:if
					test="teamClazz.internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_ORGANIZATION">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@COMPOSITEDUTY"
						defaultValue="${serviceTeamMemberDetails.position.id}" />
				</s:if>
				<s:if
					test="teamClazz.internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_PARTY">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@PARTYDUTY"
						defaultValue="${serviceTeamMemberDetails.position.id}" />
				</s:if>
				<s:if
					test="teamClazz.internalId==@com.tianque.domain.property.BasicOrgType@AUTONOMY_ORG">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@COMPOSITEDUTY"
						defaultValue="${serviceTeamMemberDetails.position.id}" />
				</s:if>
				<s:if
					test="teamClazz.internalId==@com.tianque.domain.property.BasicOrgType@MASS_TREAT_TEAM">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@MASSESDUTY"
						defaultValue="${serviceTeamMemberDetails.position.id}" />
				</s:if>
				<s:if
					test="teamClazz.internalId==@com.tianque.domain.property.BasicOrgType@VOLUNTARY_TEAM">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY"
						defaultValue="${serviceTeamMemberDetails.position.id}" />
				</s:if>
				<s:if
					test="teamClazz.internalId==@com.tianque.domain.property.BasicOrgType@LEADER_GROUP">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY"
						defaultValue="${serviceTeamMemberDetails.position.id}" />
				</s:if>
				<s:if
					test="teamClazz.internalId==@com.tianque.domain.property.BasicOrgType@OTHER">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY"
						defaultValue="${serviceTeamMemberDetails.position.id}" />
				</s:if>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="serviceTeamMemberBase.mobile" id="_mobile" maxlength="11" value="${serviceTeamMemberBase.mobile}"
				title="请输入11位以1开头的联系手机  例如：13988888888" class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">固定电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" style="width: 95%" name="serviceTeamMemberBase.homePhone" id="_homePhone" maxlength="20"
				value="${serviceTeamMemberBase.homePhone }" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
				class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' /></div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_18">
			<textarea rows="4" name="serviceTeamMemberBase.remark" id="_remark" class='form-txt {maxlength:300,messages:{maxlength:"备注最多需要输入300个字符"}}'
			style="width: 98%">${serviceTeamMemberBase.remark}</textarea>
		</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		 $(form).ajaxSubmit({
			 success : function(data) {
				if (!data.id) {
					$.messageBox({
						message : data,
						level : "error"
					});
					return;
				}
				 if("add"==$("#mode").val()){
                	if($("#_isSubmit").val()=="true"){
                		$.messageBox({message:"新服务团队成员信息新增成功！"});
                	 	$("#${dailogName}").dialog("close");
                	}else{
                		$.messageBox({message:"新服务团队成员信息新增成功！"});
                		emptyObject();
	                }
	                if($("#_serviceTeamList")){
	                	$("#_serviceTeamList").trigger("reloadGrid");
		            }
	                if($("#mainTeamMemberList")){
	                	$("#mainTeamMemberList").trigger("reloadGrid");
		            }
				 }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
		},
		messages:{
		}
	});

	function emptyObject() {
		$("#maintainForm")[0].reset();
		$("#_baseId").val("");
		$("#_remark").text("");
	}
	
	$("#_idCardNo").blur(function(){
		if($(this).attr("createMsg")=="false") {
			searchAndSynData($(this).val(), getCurrentOrgId());
		}
	});
	
	function searchAndSynData(idCardNo, orgId) {
		$.ajax({
			url:"${path}/baseinfo/serviceTeamMemberManage/getTeamMemberByIdCard.action",
			data:{
				"searchServiceTeamMemberVo.orgId":orgId,
				"searchServiceTeamMemberVo.idCardNo":idCardNo
	        },
	        success:function(data){
	        	if(null == data) {
	        		//emptyObject();
	        		//$("#_idCardNo").val(idCardNo);
	        	} else{
			        $("#_name").val(data.name);
			        $("#_gender").val(data.gender.id);
			        $("#_mobile").val(data.mobile);
			        $("#_homePhone").val(data.homePhone);
			        $("#_remark").text(data.remark);
			        $("#_baseId").val(data.id);
	        	}
		    }
		});
	}
	
	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true;}
		$.ajax({
			async: false,
			url:'${path}<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>',
		   	data:{
		   		"searchServiceTeamMemberVo.orgId":${organizationId},
				"searchServiceTeamMemberVo.idCardNo":$('#_idCardNo').val(),
				"searchServiceTeamMemberVo.teamId":${teamId}
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
});
</script>