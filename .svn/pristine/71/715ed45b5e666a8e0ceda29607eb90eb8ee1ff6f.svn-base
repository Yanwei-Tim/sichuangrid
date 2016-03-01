<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
	<div id="dustbin" class="container container_24">
		<form action="${path}/baseinfo/dustbinManage/addDustbin.action" method="post" id="maintainForm">
		<c:if test="${(mode)=='add' }">
			<pop:token/>
		</c:if>
		<div id="perUuid"></div>
		<input id="_imgUrl" type="hidden" name="dustbin.imgUrl" value="${dustbin.imgUrl}">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="dustbin.id" id="dustbin-id" value="${dustbin.id}" />
		<input id="organizationId"	type="hidden" name="dustbin.organization.id" value="${dustbin.organization.id}" />
		<input id="keyType" type="hidden" name="" value="${keyType}" />
		<input id="personTypeName" type="hidden" name="personTypeName" value="${personTypeName}">
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">所属网格：</label>
		</div>
		<div class="grid_19">
			<input type="text"  name="dustbin.organization.orgName" id="orgName"  readonly class="form-txt" value="${dustbin.organization.orgName}"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件标识码：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="dustbinCode" name="dustbin.dustbinCode" class="form-txt" value="${dustbin.dustbinCode}" maxlength="20"/>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">地址：</label>
		</div>
		<div class="grid_8">
			<input type="text" id="address" name="dustbin.address" class="form-txt" value="${dustbin.address}" maxlength="30"/>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件类型：</label>
		</div>
		<div class="grid_7">
		  <select name="dustbin.partType.id" <c:if test="${'edit'==mode }">disabled</c:if> class="form-txt" id="partType">
		  <pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" defaultValue="${dustbin.partType.id}" 
		   		reletionId="partNameId" reletionName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" id="partType" />
			</select>
		</div>
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">部件名称：</label>
		</div>
		<div class="grid_8">
			<select id="partNameId" name="dustbin.partName.id" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME"  defaultValue="${dustbin.partName.id }"  />
			</select>
		</div>
		<div class="grid_5 lable-right">
		<em class="form-req">*</em>
			<label class="form-lbl">主管部门名称：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="deptName" name="dustbin.deptName" class="form-txt" value="${dustbin.deptName}" maxlength="20"/>
		</div>
		<div class="grid_8 lable-right" id="hasVideoShow" style="display:none" >
			<input type="checkbox" id="hasVideo" name="dustbin.hasVideo" value="true"   <c:if test="${true==dustbin.hasVideo }">checked="checked"</c:if> 
			/>
			<label class="form-check-text">是否有视频流 </label>
		</div>
		<div class='clearLine'></div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">权属单位名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="ownershipUnitName" name="dustbin.ownershipUnitName" class="form-txt" value="${dustbin.ownershipUnitName}" maxlength="20"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">护养单位名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" id="maintenanceUnitName" name="dustbin.maintenanceUnitName" class="form-txt" value="${dustbin.maintenanceUnitName}" maxlength="20"/>
		</div>
       	<div class='clearLine'></div>
        <div class="grid_5 lable-right">
            <label class="form-lbl">备注：</label>
        </div>
        <div class="grid_19">
            <textarea rows="4" name="dustbin.remark"  cols="" class="form-txt">${dustbin.remark}</textarea>
        </div>
		
		<div class='clearLine'>&nbsp;</div>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
			</form>
	 </div>	
<script>





<c:if test="${'add'==mode}">
$("#organizationId").val($("#currentOrgId").val());
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
</c:if>

function callback(){
    var dfop = {
    		dailogName: '${parameters.dailogName[0]}'
    }
    TQ.mainDustbinDlg(dfop)
}

$.cacheScript({
    url:'/resource/getScript/digitalCity/dustbinManagement/mainDustbinDlg.js',
    callback: callback
})


</script>
