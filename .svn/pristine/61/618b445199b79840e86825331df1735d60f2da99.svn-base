<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<form action="" method="post" id="searchEstateInformationForm">
<pop:token />
<div id="dialog-form" title="房产信息查询" class="container container_24">
    <input type="hidden" name="orgId" id="orgId" value="${ownerOrg.id}" />
    
    <div class="grid_5 lable-right">
		<label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_19">
		<p id="orgName"/>
	</div>
	<div class="clearLine">&nbsp;</div>
	    
    <div class="grid_5 lable-right" >
		<label class="form-lbl">姓名：</label>
	</div>
	<div class="grid_7">
   		<input type="text" name="searchEstateInformationVo.name" id="searchEstateInformationVo-name" class="form-txt" maxlength="20"/>
	</div>	
	<div  class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">	   
	    <input type="text" name="searchEstateInformationVo.idCardNo" id="searchEstateInformationVo-idCardNo" class="form-txt" maxlength="18"/>   
	</div>
	<div class="clearLine">&nbsp;</div>
	
	<div class="grid_5 lable-right" >
		<label class="form-lbl">房产证号：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="searchEstateInformationVo.proprietaryNo" id="searchEstateInformationVo-proprietaryNo" class="form-txt" maxlength="20"/>   
	</div>
	<div class="grid_5 lable-right" >
		<label class="form-lbl">房产地址：</label>
	</div>
	<div class="grid_7">	   
	    <input type="text" name="searchEstateInformationVo.housePropertyPlace" id="searchEstateInformationVo-housePropertyPlace" class="form-txt" maxlength="50"/>   
	</div>
	<div class="clearLine">&nbsp;</div>
</div>
</form>
<script type="text/javascript">
$(document).ready(function(){

	$("#searchEstateInformationForm").formValidate({
		promptPosition: "bottomLeft", 
		submitHandler: function(form){
			var data=$(form).serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
				dataJson[data[i].name]=data[i].value;
			}
			$("#estateInformationList").setGridParam({url:"${path}/baseinfo/searchEstateInformation/searchEstateInformation.action",datatype:'json'});
			$("#estateInformationList").setPostData(dataJson);
			$("#estateInformationList").trigger("reloadGrid");
			$("#estateInformationDialog").dialog("close");
			return false;
		},
		rules:{
			"searchEstateInformationVo.name":{
				exculdeParticalChar:true
			},
			"searchEstateInformationVo.proprietaryNo":{
				exculdeParticalChar:true
			},
			"searchEstateInformationVo.idCardNo":{
				idCard:true
			}
		},
		messages:{
			"searchEstateInformationVo.name":{
				exculdeParticalChar:"姓名只能输入字母，数字和中文字符"
			},
			"searchEstateInformationVo.proprietaryNo":{
				exculdeParticalChar:"房产证号只能输入字母，数字和中文字符"
			},
			"searchEstateInformationVo.idCardNo":{
				idCard:"请输入一个合法的身份证号码，如果最后一位为[X]请输入大写[X]"
			}
		}
	});
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#orgId").val()
		},
		success:function(responseData){
			$("#orgName").text(responseData);
		}
	});
	
});
</script>