<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style type="text/css">
#textContent a.search{display:inline-block; *display:inline; *zoom:1; cursor:pointer; background:url(/resource/system/images/button-bg.png) left bottom no-repeat; padding-left:15px; height:24px; line-height:24px; margin:0 5px 10px 0; position:relative; color:#333;}
#textContent a.search span{display:inline-block; *display:inline; *zoom:1; background:url(/resource/system/images/button-bg.png) right bottom no-repeat; padding-right:15px; vertical-align:top; *vertical-align:middle;}
#textContent a.search:hover{background-position:left top; color:#fff;}
#textContent a.search:hover span{background-position:right top;}
</style>
<div id="newPrimaryMembers" class="container container_24">
	<form action="${path}/primaryOrg/primaryMembersManage/${mode}PrimaryMembers.action" method="post" id="primaryMembersForm">
		<pop:token/>
		<input type="hidden" id="displayLevel" name="displayLevel" value="${displayLevel}">
		<input type="hidden" id="orgCode" name="primaryMembers.orgCode" value="${primaryMembers.org.orgInternalCode}">
		<input type="hidden"  id="orgId" name="primaryMembers.org.id" value="${primaryMembers.org.id}" />
		<input type="hidden" id="id" name="primaryMembers.id" value="${primaryMembers.id}">
		<input type="hidden" id="isHaveJob" name="primaryMembers.isHaveJob" value="${primaryMembers.isHaveJob}">
		<input type="hidden" id="isFourLevelPlatform" name="isFourLevelPlatform" value="${isFourLevelPlatform}">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
   			<label class="form-lb1">所属网格：</label>
   		</div>
   		<div class="grid_20">
   			<input type="text"  id="commonPrimaryMembersOrgName"  name="primaryMembers.org.orgName"  readonly="readonly" value="${primaryMembers.org.orgName}" style="width: 99%" class="form-txt" />
   		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="primaryMembers.name" id="name" maxlength="10" <c:if test="${mode=='view'}"> readonly="readonly"</c:if> value="${primaryMembers.name}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">身份证号：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="primaryMembers.idcardNo" id="idcardNo" maxlength="18" <c:if test="${mode=='view'}"> readonly="readonly"</c:if> value="${primaryMembers.idcardNo}" style="width: 95%" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMembers.gender.id" id="gender" class='form-txt'  style="width: 96.5%" <c:if test="${mode=='view'}">disabled="disabled"</c:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER"  defaultValue="${ primaryMembers.gender.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">出生日期： </label>
		</div>
		<div class="grid_8" id="birthdayDiv">
		<input type="text" style="width: 95%" name="primaryMembers.birthday" readonly="readonly" id="birthday" maxlength="20" value="<fmt:formatDate value="${primaryMembers.birthday}" type="date" pattern="yyyy-MM-dd" />" class="form-txt" <c:if test="${mode=='view'}">disabled="disabled"</c:if> />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">民族：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMembers.nation.id" id="nation" class='form-txt'  style="width: 96.5%" <c:if test="${mode=='view'}" >disabled="disabled"</c:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="${primaryMembers.nation.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">政治面貌：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMembers.politicalBackground.id" id="politicalBackground" class='form-txt'  style="width: 97%" <c:if test="${mode=='view'}">disabled="disabled"</c:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="${primaryMembers.politicalBackground.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">文化程度：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMembers.schooling.id" id="schooling" class='form-txt'  style="width: 96.5%" <c:if test="${mode=='view'}">disabled="disabled"</c:if>>
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="${primaryMembers.schooling.id}" />
			</select>
		</div>
		<c:if test="${(internalId==3 ||internalId==9)&& isFourLevelPlatform==0 } ">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">职务：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMembers.dutyId.id"  id="dutyId" style="width: 96.5%" class='form-txt {required:true,messages:{required:"请选择职务"}}' <c:if test="${mode=='view'}">disabled="disabled"</c:if>>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@MASS_TREAT_TEAM">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@MASSESDUTY" defaultValue="${primaryMembers.dutyId.id}" />
				</s:if>
				<s:if
					test="internalId==@com.tianque.domain.property.BasicOrgType@DEPARTMENT_PARTY">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTYDUTY" defaultValue="${primaryMembers.dutyId.id}" />
				</s:if>
			</select>
		</div>
		</c:if>
		<c:if test="${internalId==9 && isFourLevelPlatform==0 }">
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lb1">级别：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMembers.departmentPartyLevel.id"  id="departmentPartyLevel" style="width: 96.5%" class='form-txt {required:true,messages:{required:"请选择级别"}}' <c:if test="${mode=='view'}">disabled="disabled"</c:if>>
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@DEPARTMENTPARTY_LEVEL" defaultValue="${primaryMembers.departmentPartyLevel.id}" />
			</select>
		</div>
		</c:if>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="primaryMembers.mobile" id="mobile" maxlength="11" value="${primaryMembers.mobile }"
			title="请输入11位以1开头的联系手机  例如：13988888888" class="form-txt"  style="width: 95%"  <c:if test="${mode=='view'}">readonly="readonly"</c:if>/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="primaryMembers.telephone" id="telephone" maxlength="15" value="${primaryMembers.telephone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class="form-txt" title="请输入由数字和-组成的联系电话,例如：0577-88888888" <c:if test="${mode=='view'}">readonly="readonly"</c:if>/>
		</div>

		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">所在组织：</label>
		</div>
		<div class="grid_20 heightAuto">
			<input type="hidden" id="optionOrgIds" name="optionOrgIds" value="${primaryMembers.primaryOrgIds}">
			<textarea rows="4" name="optionOrgNames" id="optionOrgNames" class="form-txt"
			style="width: 85%" readonly="readonly">${primaryMembers.primaryOrgNames}</textarea>
			<div class="btnbanner">
			<c:if test="${primaryOrgDetailName==null && mode!='view'}">
				<a href="javascript:;" id="orgOption" ><span>组织选择</span></a>
			</c:if>
			</div>  
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">备注：</label>
		</div>
		<div class="grid_20 heightAuto">
		    <textarea rows="4" name="primaryMembers.remark" id="remark" class="form-txt" maxlength="300" 
			style="width: 97.8%" <c:if test="${mode=='view'}">readonly="readonly"</c:if>>${primaryMembers.remark}</textarea>
		</div>
		
		
		<input type="hidden" name="isSubmit" id="isSubmit" />
	</form>
	<div id="primaryMembersOrgOptionDialog"></div>
</div>
<script type="text/javascript">

if("${mode}"=='edit'){
	if ($('#idcardNo').val()!="" && $('#idcardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' style='width: 95%' name='primaryMembers.birthday' id='birthday' class='form-txt' value='"+$('#birthday').val()+"' disabled />");
	}else{
		$('#birthday').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
		}
}

$('#birthday').datePicker({
	yearRange: '1900:2030',
	dateFormat: 'yy-mm-dd',
    maxDate:'+0d'
}); 
$("#idcardNo").blur(function(){
	var text=$('#idcardNo').val();
	text=getBirthDayTextFromIdCard(text);
	resetBirthdayField(text);
});
function resetBirthdayField(text){
	if (text!="" && $('#idcardNo').val()!=""){
		$("#birthdayDiv").html("<input type='text' style='width: 95%' name='primaryMembers.birthday' id='birthday' class='form-txt' value='"+text+"' readonly/>");
	}else{
		$('#birthday').datePicker({
			yearRange: '1900:2030',
    		dateFormat: 'yy-mm-dd',
            maxDate:'+0d'});
		$('#birthday').attr("disabled",false);
	}
}
function getBirthDayTextFromIdCard(idCard){
	if(idCard!=null&&idCard.length==18){
		idCard=idCard.substring(6,14);
		if(idCard.substring(4,6)<=0||idCard.substring(4,6)>12){
			return "";
		}else if(idCard.substring(6,8)<=0||idCard.substring(6,8)>31){
			return "";
		}else{
			return idCard.substring(0,4)+"-"+idCard.substring(4,6)+"-"+idCard.substring(6,8);
		}
	}else if(idCard!=null&&idCard.length==15){
		idCard=idCard.substring(6,12);
		if(idCard.substring(2,4)<=0||idCard.substring(2,4)>12){
			return "";
		}else if(idCard.substring(4,6)<=0||idCard.substring(4,6)>31){
			return "";
		}else{
			return "19"+idCard.substring(0,2)+"-"+idCard.substring(2,4)+"-"+idCard.substring(4,6);
		}
	}
	return "";
}
$("#primaryMembersForm").formValidate({
	promptPosition: "bottomLeft",
	submitHandler: function(form){
	 $(form).ajaxSubmit({
		success : function(data) {
			if (!data.id) {
				$.messageBox({message : data,level : "error"});
				return;
			}
			if("add"=="${mode}"){
				$.messageBox({message:"成员新增成功！"});
			}
			if("edit"=="${mode}"){
				$.messageBox({message:"成员信息修改成功！"});
			}
			//如果$("#isSubmit").val()为null或者undefind，不做任何操作
			if($("#isSubmit").val()==""){
				$("#primaryMembersDialog${primaryMembers.isHaveJob}").dialog("close");
			}else if($("#isSubmit").val()=="false"){
				document.getElementById("primaryMembersForm").reset();
				$("#birthday").val("");
				$('#birthday').datePicker({
					yearRange: '1900:2030',
					dateFormat: 'yy-mm-dd',
				    maxDate:'+0d'
				}); 
				$("#birthday").addClass("hasDatepicker");
			}else if($("#isSubmit").val()=="true"){
				$("#addMemberDialog").dialog('close');
			}
			//成员库列表
			$("#primaryMembers${primaryMembers.isHaveJob}List").trigger("reloadGrid");
			//组织成员列表
			$("#primaryMember${primaryMembers.isHaveJob}List").trigger("reloadGrid");
			//组织列表
	        $("#primaryOrgList").trigger("reloadGrid");
	        //四级平台列表
	        $("#fourlevelplatformList").trigger("reloadGrid")
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("提交错误");
		}
		});
	},
	rules:{
		"primaryMembers.name":{
			required:true,
			exculdeParticalChar:true,
			minlength:2,
			maxlength:20
		},
		"primaryMembers.idcardNo":{
			idCard:true
		},
		"primaryMembers.gender.id":{
			required:true
		},
		"primaryMembers.birthday":{
			required:true
		},
		"primaryMembers.nation.id":{
			required:true
		},
		"primaryMembers.politicalBackground.id":{
			required:true
		},
		"primaryMembers.schooling.id":{
			required:true
		},
		"primaryMembers.mobile":{
			mobile:true
		},
		"primaryMembers.telephone":{
			telephone:true
		},
		"primaryMembers.remark":{
			maxlength:300
		}
	},
	messages:{
		"primaryMembers.name":{
			required:"请输入姓名",
			exculdeParticalChar:"不能输入非法字符",
			minlength:$.format("姓名至少需要输入{0}个字符"),
			maxlength:$.format("姓名最多需要输入{0}个字符")
		},
		"primaryMembers.idcardNo":{
			idCard:"请输入一个合法的身份证号码"
		},
		"primaryMembers.gender.id":{
			required:"请选择性别"
		},
		"primaryMembers.birthday":{
			required:"请输入出生日期"
		},
		"primaryMembers.nation.id":{
			required:"请选择民族"
		},
		"primaryMembers.politicalBackground.id":{
			required:"请选择政治面貌"
		},
		"primaryMembers.schooling.id":{
			required:"请选文化程度"
		},
		"primaryMembers.mobile":{
			mobile:"手机号码输入只能是以1开头的11位数字"
		},
		"primaryMembers.telephone":{
			telephone:"固定电话不合法，只能输数字和横杠(-)"
		},
		"primaryMembers.remark":{
			maxlength:"备注最多需要输入300个字符"
		}
	}
});
$("#orgOption").click(function(event){
	var primaryOrgNames = $("#optionOrgNames").val();
	$("#primaryMembersOrgOptionDialog").createDialog({
		title:"组织选择",
		width: 580,
		height: 430,
		url:'${path}/primaryOrg/primaryMembersManage/dispatch.action?mode=primaryMembersOrgOption&primaryMembers.org.id='+$("#orgId").val()+'&primaryMembers.primaryOrgIds='+$("#optionOrgIds").val()+'&primaryMembers.primaryOrgNames='+encodeURIComponent(primaryOrgNames),
		buttons: {
			"确定" : function(event){
			$("#optionOrgIds").val($("#optionOrgOperateIds").val());
			$("#optionOrgNames").val($("#optionOrgOperateNames").val());
				$(this).dialog("close");
			},
			"取消" : function(event){
				$(this).dialog("close");
			}
		}
	});
});
</script>