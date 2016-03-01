<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div  class="container container_24">
	<form action="${path}/baseinfo/primaryOrgMemberManage/${mode}PrimaryOrgMember.action" method="post" id="serviceTeamMemberForm">
		<pop:token />
		<input type="hidden" id="primaryOrgMemberID" name="primaryOrgMember.id" value="${primaryOrgMemberVo.id}">
		<input type="hidden" id="primaryOrgId" name="primaryOrgMember.primaryOrgId" value="${primaryOrgMemberVo.primaryOrgId}">
		<input type="hidden"  id="fatesonOrgId" name="primaryOrgMember.org.id" value="${primaryOrgMemberVo.org.id}" />
		<input type="hidden" name="primaryOrgMember.org.orgInternalCode" value="${primaryOrgMemberVo.org.orgInternalCode}" />
		<input type="hidden" id="isFourLevelPlatform" name="primaryOrgMember.isFourLevelPlatform" value="${primaryOrgMemberVo.isFourLevelPlatform}" />
		<input name="addTeam" id="addTeam" type="hidden"/>
		
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="primaryOrgMember.name" id="name" maxlength="10" value='${primaryOrgMemberVo.name}'  <s:if test='"edit".equals(mode)'>readonly</s:if>  
			class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:10,messages:{required:"请输入姓名",exculdeParticalChar:"不能输入非法字符",minlength:$.format("姓名至少需要输入{0}个字符"),maxlength:$.format("姓名最多需要输入{0}个字符")}}' style="width:96%"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_8">
			<select name="primaryOrgMember.gender.id" id="gender" class='form-txt {required:true,messages:{required:"请选择性别"}}'>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="${primaryOrgMemberVo.gender.id}" />
			</select>
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">职务：</label>
		</div>
		<div class="grid_8">
			<select name="primaryOrgMember.dutyId.id"  id="dutyId" class='form-txt {required:true,messages:{required:"请选择职务"}}'>
				<s:if     
					test="internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_ORGANIZATION">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@COMPOSITEDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@PERMARY_PARTY">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@PARTYDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@AUTONOMY_ORG">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@UTONOMYDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@MASS_TREAT_TEAM">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@MASSESDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@VOLUNTARY_TEAM">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@POSTULANTDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@LEADER_GROUP">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@OTHER">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@LEADERGROUPDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@BASICLEVEL_PARTY">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@BASICLEVELPARTYDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@DEPARTMENT_PARTY">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTYDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@GOVERNMENT_DEPARTMENT">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@GOVERNMENTDEPARTMENTDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@MASS_ORGANIZATION">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@MASSORGMANAGEMENTDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
				<s:if test="internalId==null">
					<pop:OptionTag
						name="@com.tianque.domain.property.PropertyTypes@PLATFORMDUTY"
						defaultValue="${primaryOrgMemberVo.dutyId.id}" />
				</s:if>
			</select>
		</div>
		<div class="grid_4 lable-right">
			<s:if test="name == 'Masseduty'">
				<em class="form-req">*</em>
			</s:if>
			<label class="form-lb1">身份证号码： </label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="primaryOrgMember.idcardNo" id="idcardNo" value='${primaryOrgMemberVo.idcardNo}' maxlength="18" 
				<s:if test="name == 'Masseduty'">
					class='form-txt {required:true,idCard:true,messages:{required:"请输入身份证号码",idCard:"请输入正确的身份证号码"}}'
				</s:if>
				<s:else>
					class='form-txt {idCard:true,messages:{idCard:"请输入正确的身份证号码"}}'
				</s:else> />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<%-- <div class="grid_4 lable-right">
			<label class="form-lb1">职位： </label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="primaryOrgMember.position" id="job" value='${primaryOrgMemberVo.position}' class='form-txt {exculdeParticalChar:true,minlength:2,maxlength:10,messages:{exculdeParticalChar:"不能输入非法字符",minlength:$.format("职位至少需要输入{0}个字符"),maxlength:$.format("职位最多需要输入{0}个字符")}}' />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">出生年份 ：</label>
		</div>
		<div class="grid_8">
			<select id="year" class="basic-input" name="primaryOrgMember.year"  >
				<option value="" selected>-请选择-</option>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div> --%>
		<div class="grid_4 lable-right">
			<label class="form-lb1">手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="primaryOrgMember.mobile" id="mobileNumber" maxlength="11" value="${primaryOrgMemberVo.mobile}"
			title="请输入11位以1开头的联系手机  例如：13988888888" class='form-txt {mobile:true,messages:{mobile:"手机号码输入只能是以1开头的11位数字"}}' />
		</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">电话：</label>
		</div>
		<div class="grid_8">
			<input type="text"  name="primaryOrgMember.telephone" id="telephone" maxlength="13"  value="${primaryOrgMemberVo.telephone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class='form-txt {telephone:true,messages:{telephone:"固定电话不合法，只能输数字和横杠(-)"}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
	
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
		    <textarea rows="2" name="primaryOrgMember.remark" id="remark"  maxlength="200" class='form-txt {maxlength:200,messages:{maxlength:"备注最多需要输入200个字符"}}'
			style="width: 97.8%">${primaryOrgMemberVo.remark}</textarea>
		</div>
		<input type="hidden" name="positionInTeam" id="position_in_team"/>
		<input type="hidden" name="isSubmit" id="isSubmit"/>
	</form>
</div>

<script type="text/javascript">

$(document).ready(function() {
	//获取年份的选择下拉框
	$.ajax({
		async: false,
		url: "${path }/plugin/serviceTeam/serviceTeamMember/getCurrentTimeForIntegrativeQueryYear.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#year").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>");
			}
		}
	});
	
 
	if(${primaryOrgMemberVo.year!=null}){
		$("#year").val(${primaryOrgMemberVo.year});
	}
	

	//表单验证
	$("#serviceTeamMemberForm").formValidate({ 	
		submitHandler: function(form){
		 $(form).ajaxSubmit({
			success : function(data) {
			var dataString=data+"";
			var  results= dataString.split("###");
			var result=	results[0];
				if(result==1){
				 var selectedIds=	results[1];
					//说明已经存在并且 卸任
					$.confirm({
						title:"确认任职",
						message:"该用户在该组织机构已经存在，但处于卸任状态。是否重新任职?",
						width: 300,
						okFunc: function(){
						  //修改卸任状态
							$.ajax({
								async: false,
								url:'${path}/baseinfo/primaryOrgMemberManage/havajobPrimaryOrgMember.action?selectedIds='+selectedIds+'&primaryOrgMember.isHaveJob=1',
								success:function(data){
									if(data>0){
										    $("#${mode}MemberDialog").dialog("close");
											$("#primaryOrgMember0List").trigger("reloadGrid");
											$("#primaryOrgMember1List").trigger("reloadGrid");
											 $("#primaryOrgList").trigger("reloadGrid");
											$("#fourlevelplatformList").trigger("reloadGrid");
									}else{
										$.messageBox({message:data,level: "error"});
									}
								}
							});
							
						}
					});
				}else if(result==0){
					$.messageBox({message:"该用户在该组织机构已经存在，并且处于任职状态"});
				}else{
				if("add"=="${mode}"){
					$.messageBox({message:"组织成员新增成功！"});
					$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").addRowData(data.id,data,"first");
				}else if("edit"=="${mode}"){
				  $.messageBox({message:"组织成员修改成功！"});
				  $("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").setRowData(data.id,data);
				}
				if($("#isSubmit").val()=="false"){
					document.getElementById("serviceTeamMemberForm").reset();
				}else if($("#isSubmit").val()=="true"){
					if("add"=="${mode}"){
						$("#${mode}MemberDialog").dialog("close");
					}else if("edit"=="${mode}"){
					  $("#${mode}Member${primaryOrgMemberVo.isHaveJob}Dialog").dialog("close");
					}
				}$("#primaryOrgMember${primaryOrgMemberVo.isHaveJob}List").trigger("reloadGrid");
		        $("#primaryOrgList").trigger("reloadGrid");
		        $("#fourlevelplatformList").trigger("reloadGrid");
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
	
	
	<s:if test="'add'==mode">
	//给姓名绑定联想输入
	$("#name").primaryOrgMemberAutoComplete({
		search:function(){""},
		searchByMemberName:true,
		select:function(event,ui){
			orgMemberFromADD(ui.item);
		}
	});
	function orgMemberFromADD(member){
		 
		if(member==null){
			//除姓名之外其他都清空
			clearContent();
			return;
		}
		 
		//联想输入 其他信息
		$("#gender").val(member.genderId);
		$("#dutyId").val(member.dutyId);
		$("#job").val(member.position)	;			
		$("#year").val(member.year)		;		
		$("#mobileNumber").val(member.mobile);
		$("#telephone").val(member.telephone);
		$("#remark").val(member.remark);
		 
	}
	</s:if>
});
	/* if($("#birthday").val()){
		$("#year").val($("#birthday").val());
	} */
</script>