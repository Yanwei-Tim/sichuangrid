<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="系统党建" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="systemParty.id" value="${systemParty.id }">
		<input type="hidden" name="systemParty.partyOrgType" value="${systemParty.partyOrgType}">
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">党组织名称：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="systemParty.partyName" id="partyName" style="width: 99%" maxlength="30" class='form-txt {required:true,maxlength:30,exsistedSystemParty:true,messages:{required:"请输入组织名称",maxlength:$.format("组织名称不能多于{0}个字符"),exsistedSystemParty:"该组织名称已存在"}}' value="${systemParty.partyName}"/>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">联系人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="systemParty.contact" id="contact"  maxlength="20" class='form-txt {maxlength:20,minlength:2,exculdeParticalChar:true,messages:{maxlength:$.format("联系手机不能多于{0}个字符"),minlength:$.format("联系人手机不能少于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' value="${systemParty.contact}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系手机：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="systemParty.mobile" id="mobile"  maxlength="11" class='form-txt {maxlength:11,mobile:true,messages:{maxlength:$.format("联系手机不能多于{0}个字符"),mobile:"手机号码输入只能是以1开头的11位数字"}}' value="${systemParty.mobile}"  title="请输入11位以1开头的联系手机  例如：13988888888"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="systemParty.telephone" id="telephone"  maxlength="15" class='form-txt {maxlength:15,telephone:true,messages:{maxlength:$.format("联系电话不能多于{0}个字符"),telephone:$.format("联系电话不合法，只能输数字和横杠(-)")}}' value="${systemParty.telephone}"  title="请输入由数字和-组成的联系电话,例如：0577-88888888"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_18">
			<textarea id="remark" style="width: 99%" name="systemParty.remark" class='form-txt {maxlength:200,messages:{maxlength:$.format("备注不能多于{0}个字符")}}' rows="10">${systemParty.remark}</textarea>
		</div>
	</form>
	<div id="searchSystemPartyDialog"></div>
	
</div>
<script type="text/javascript">
var partyName = '${systemParty.partyName}';
$(document).ready(function(){
	jQuery.validator.addMethod("exsistedSystemParty", exsistedSystemParty);
	$("#maintainForm").formValidate({
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
		        	<c:if test='${mode=="edit"}'>
				    	$.messageBox({message:"修改"+formatterTitle()+"成功!"});
					</c:if>
					<c:if test='${mode=="add"}'>
						$.messageBox({message:"新增"+formatterTitle()+"成功!"});
					</c:if>
					$("#systemPartyDialog").dialog("close");
					$("#systemPartyList").trigger("reloadGrid");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});
	<c:if test='${mode=="add"}'>
	    $("#maintainForm").attr("action","${path}/partyBuildng/systemPartyManage/addSystemParty.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
	    $("#maintainForm").attr("action","${path}/partyBuildng/systemPartyManage/updateSystemParty.action");
	</c:if>
	<c:if test='${mode=="view"}'>
	 	$(".form-txt").attr("disabled","disabled");
	</c:if>
});

function exsistedSystemParty(value, element){
	if(value==null||value==undefined||value==""
			||("edit"==$("#mode").val()&&partyName==$("#partyName").val())){
		return true;
	}
	var flag = true;
	$.ajax({
		async: false,
		url:'${path}/partyBuildng/systemPartyManage/exsistedSystemParty.action',
	   	data:{
	   		"systemParty.partyOrgType":"${systemParty.partyOrgType }",
			"systemParty.partyName":$("#partyName").val()
        },
		success:function(responseData){
			if(responseData!=false && responseData!=true){
				$.messageBox({
                    message:responseData,
                    level:"error"
                });
    			return;
			}
			flag = !responseData;
		}
	});
	return flag;
}
</script>


