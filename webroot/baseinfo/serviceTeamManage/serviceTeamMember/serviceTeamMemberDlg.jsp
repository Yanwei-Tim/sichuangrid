<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#textContent a.search{display:inline-block; *display:inline; *zoom:1; cursor:pointer; background:url(/resource/system/images/button-bg.png) left bottom no-repeat; padding-left:15px; height:24px; line-height:24px; margin:0 5px 10px 0; position:relative; color:#333;}
#textContent a.search span{display:inline-block; *display:inline; *zoom:1; background:url(/resource/system/images/button-bg.png) right bottom no-repeat; padding-right:15px; vertical-align:top; *vertical-align:middle;}
#textContent a.search:hover{background-position:left top; color:#fff;}
#textContent a.search:hover span{background-position:right top;}

</style>
<div id="newServiceManageTeam" class="container container_24">
	<form action="${path}/baseinfo/serviceTeamMemberManage/maintainServiceTeamMember.action?mode=${mode}" method="post" id="serviceTeamMemberForm">
		<pop:token />
		<input type="hidden" id="serviceTeamMemberBaseID" name="serviceTeamMemberBase.id" value="${serviceTeamMemberBase.id }">
		<input type="hidden" name="serviceTeamMemberBase.organization.id" value="${serviceTeamMemberBase.organization.id}" />
		<input type="hidden" name="serviceTeamMemberBase.orgInternalCode" value="${serviceTeamMemberBase.orgInternalCode}" />
		<input name="addTeam" id="addTeam" type="hidden"/>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_3">
			<input type="text" style="width: 95%" name="serviceTeamMemberBase.name" id="name" value='${serviceTeamMemberBase.name}'
			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}' style="width:96%"/>
		</div>
		<div class="grid_2 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_3">
			<select name="serviceTeamMemberBase.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${serviceTeamMemberBase.gender.id}" />
			</select>
		</div>
	
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">身份证号码： </label>
		</div>
		<div class="grid_8">
			<input type="text" name="serviceTeamMemberBase.idCardNo" id="idCardNo" value="${serviceTeamMemberBase.idCardNo}" maxlength="18"
			<s:if test='"edit".equals(mode)'>readonly</s:if>
			class="form-txt {required:true,exsistedIdCard:true,idCard:true,messages:{required:'请输入身份证号码',exsistedIdCard:'该身份证号码已经存在，请重新输入',idCard:'请输入一个合法的身份证号码'}}" style="width:96%"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="serviceTeamMemberBase.mobile" id="mobileNumber" maxlength="11" value="${serviceTeamMemberBase.mobile}"
			title="请输入11位以1开头的联系手机  例如：13988888888" class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">固定电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="serviceTeamMemberBase.homePhone" id="homePhone" maxlength="20" value="${serviceTeamMemberBase.homePhone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class='form-txt {telephone:true,messages:{telephone:$.format("固定电话不合法，只能输数字和横杠(-)")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_3 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
		    <textarea rows="2" name="serviceTeamMemberBase.remark" id="remark" class='form-txt {maxlength:300,messages:{maxlength:"备注最多需要输入300个字符"}}'
			style="width: 97.8%">${serviceTeamMemberBase.remark}</textarea>
		</div>
	
		<input type="hidden" name="positionInTeam" id="position_in_team"/>
		<input type="hidden" name="isSubmit" id="isSubmit"/>
	</form>
</div>

<script type="text/javascript">

$(document).ready(function() {

	$("#serviceTeamMemberForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		 $(form).ajaxSubmit({
			success : function(data) {
				if (!data.id) {
					$.messageBox({message : data,level : "error"});
					return;
				}
				if("add"=="${mode}"){
					$.messageBox({message:"新服务团队成员信息新增成功！"});
				}else if("edit"=="${mode}"){
					 $.messageBox({message:"新服务团队成员修改成功！"});
				}
				$("#serviceTeamMemberList").trigger("reloadGrid");
				if($("#isSubmit").val()=="false"){
					document.getElementById("serviceTeamMemberForm").reset();
				}else if($("#isSubmit").val()=="true"){
					$("#${dailogName}").dialog("close");
				}
				if($("#addTeam").val()=="true"){
					$("#${dailogName}").dialog("close");
					$("#${dailogName}").createDialog({
						width:650,
						height:360,
						title:'添加团队职务',
						url:'${path}/baseinfo/serviceTeamMemberManage/serviceTeamMemberDispatch.action?mode=addTeam&dailogName=_serviceTeamMemberDialog&organizationId='+$("#currentOrgId").val()+'&baseId='+data.id,
						buttons: {
							"确定" : function(){
								$("#addTeamPositionForm").submit();
							},
							"关闭" : function(){
								$(this).dialog("close");
							}
						}
					});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{},
		messages:{}
	});
	
	jQuery.validator.addMethod("exsistedIdCard", function(value, element){
		var flag =true;
		if(null == value || value==""){return true;}
		$.ajax({
			async: false,
			url:'${path}<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>',
		   	data:{
		   		"searchServiceTeamMemberVo.orgId":getCurrentOrgId(),
				"searchServiceTeamMemberVo.idCardNo":$('#idCardNo').val(),
				"searchServiceTeamMemberVo.baseId":$('#serviceTeamMemberBaseID').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
});

</script>