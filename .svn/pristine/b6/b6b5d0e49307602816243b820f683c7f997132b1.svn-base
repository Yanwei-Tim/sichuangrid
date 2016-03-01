<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="我的联系人查询"  class="container container_24">
	  <div class="grid_4 lable-right">
		<label >姓名：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="workContactVo.name" class="form-txt" maxlength="20" 
		 id="workContactVoName" value="${workContactVo.name}"/>
	</div>
	<div class="grid_4 lable-right">
		<label>联系手机：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="workContactVo.mobileNumber" class="form-txt numInput" maxlength="11"
		 id="workContactVoMobileNumber" value="${workContactVo.mobileNumber}" />
	</div>
	<div style="clear:both"></div>
	<div class="grid_4 lable-right">
		<label>所在部门：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="workContacterOrgName" class="form-txt" readonly value="请点击选择"/>
		<input type="hidden" name="workContactOrgId" id="workContactOrgId"/>
	</div>
	<div class="grid_4 lable-right">
		<label>固定电话：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="workContactVo.workPhone" class="form-txt numInput" maxlength="11"
		 id="workContactPhone" />
	</div>
</div>

<script type="text/javascript">
var provienceId;
$(document).ready(function(){
	provienceId = getprovienceOrgId();
	$("#workContacterOrgName").one("click", function(){
		$(this).treeSelect({
			allOrg:false,
			rootId:provienceId,
			isFuncDep:true,
			inputName:"workContactOrgId"
		});
	});

	$(".numInput").bind("blur",function(){
		var inputObject=$(this);
		var name=inputObject.attr("name");
		var errorMsgs=new Array();
		
		if(isNaN(inputObject.val())){
			errorMsgs.push("请输入数字");
		}
		if(errorMsgs.length>0){
			inputObject.poshytip('hide');
			inputObject.dialogtip({
				content:"<div class='inputName' inputName='"+name+"'><span class='error'></span>"+errorMsgs.pop()+"</div>"
			});
			inputObject.poshytip('show');
			inputObject.focus();
		}else{
			inputObject.poshytip('hide');
		}
	});

});
/**
 * 通过当前用户的ID去查询该用户省级网格ID
 */
function getprovienceOrgId(){
		$.ajax({
			url:"${path}/sysadmin/orgManage/getOrgProvinceByOrgId.action",
			async:false,
			success:function(data){
				rootId = eval(data);
			}
		});
	return rootId;
}

</script>