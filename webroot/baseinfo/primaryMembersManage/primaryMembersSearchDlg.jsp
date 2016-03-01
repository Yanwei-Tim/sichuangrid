<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
#textContent a.search{display:inline-block; *display:inline; *zoom:1; cursor:pointer; background:url(/resource/system/images/button-bg.png) left bottom no-repeat; padding-left:15px; height:24px; line-height:24px; margin:0 5px 10px 0; position:relative; color:#333;}
#textContent a.search span{display:inline-block; *display:inline; *zoom:1; background:url(/resource/system/images/button-bg.png) right bottom no-repeat; padding-right:15px; vertical-align:top; *vertical-align:middle;}
#textContent a.search:hover{background-position:left top; color:#fff;}
#textContent a.search:hover span{background-position:right top;}
</style>
<div id="serachPrimaryMembers" class="container container_24">
	<form action="${path}/primaryOrg/primaryMembersManage/findPrimaryMembers.action" method="post" id="serachPrimaryMember_form">
		<input type="hidden"  id="displayLevel" name="primaryMemberVo.displayLevel" value="${primaryMembers.displayLevel}" />
		<input type="hidden"  id="orgid" name="primaryMemberVo.org.id" value="${primaryMemberVo.org.id}" />
		<input type="hidden"  id="isHaveJob" name="primaryMemberVo.isHaveJob" value="${primaryMembers.isHaveJob}" />
		<input type="hidden"  id="isPrimaryMember" name="primaryMemberVo.isPrimaryMember" value="<s:property value="@com.tianque.baseInfo.primaryOrg.primaryMembers.constant.PrimaryMemberType@ISPRIMARYMEMBER"/>" />
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em> <label class="form-lb1">姓名：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="primaryMemberVo.name" id="name" maxlength="20" value='' class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">身份证号：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="primaryMemberVo.idcardNo" id="idcardNo" maxlength="18" value="" style="width: 95%" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em> <label class="form-lb1">性别：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMemberVo.gender.id" id="gender" class='form-txt'  style="width: 96.5%" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@GENDER" defaultValue="" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em> <label class="form-lb1">民族：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMemberVo.nation.id" id="nation" class='form-txt'  style="width: 96.5%" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@NATION" defaultValue="" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em> <label class="form-lb1">政治面貌：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMemberVo.politicalBackground.id" id="politicalBackground" class='form-txt'  style="width: 97%" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@POLITICAL_BACKGROUND" defaultValue="" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em> <label class="form-lb1">文化程度：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMemberVo.schooling.id" id="schooling" class='form-txt'  style="width: 96.5%" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCHOOLING" defaultValue="" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="primaryMemberVo.mobile" id="mobile" maxlength="11" value=""
			title="请输入11位以1开头的联系手机  例如：13988888888" class="form-txt"  style="width: 95%" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" style="width: 95%" name="primaryMemberVo.telephone" id="telephone" maxlength="15" value="" title="请输入由数字和-组成的联系电话,例如：0577-88888888"
			class="form-txt" title="请输入由数字和-组成的联系电话,例如：0577-88888888"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em> <label class="form-lb1">所在组织类别：</label>
		</div>
		<div class="grid_8">
			<select name="primaryMemberVo.teamClazz.internalId" id="teamClazz" class='form-txt'  style="width: 96.5%" >
				<option value="-1">全部</option>
				<option value="8">基层党委</option>
	 			<option value="9">部门党委</option>
	 			<option value="10">政府部门</option>
	 			<option value="11">群团组织</option>
	 			<option value="2">基层自治组织</option>
	 			<option value="3">群防群治组织</option>
	 			<option value="4">社会志愿者队伍</option>
	 			<option value="100">四级平台</option>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">所在组织：</label>
		</div>
		<div class="grid_20 heightAuto" id="primaryMemberText" style="margin-top:5px;">
			<input type="text" id="primaryMembers" name="primaryMemberVo.primaryorgInfo" class='form-txt' />
		</div>
	</form>
</div>
<script>
function isCanSubmit(){
	 var primary=$("#input_primaryMembers").val();
		if(primary!=null && primary!=undefined && primary!="" && primary !="undefined" ){
			return false;
			}
		
		return true;
}
$(document).ready(function(){
	
	$("#primaryMembers").primaryMemberComplete({
		multiple: true,
		height:100,
		param:"primaryOrgVo.detailName",
		url:"/primaryOrg/primaryMembersManage/getPrimaryOrgInfoByDetailName.action",
		postDataFunction: function(){
			return {"primaryOrgVo.org.id":"${primaryMemberVo.org.id}"};
		}/* ,
		itemClose : function(data){
			$("#primaryMembers").val("");
	    } */
	});	
}); 
</script>
