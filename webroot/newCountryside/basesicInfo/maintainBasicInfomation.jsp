<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24">
	<form id="maintainForm" method="post" action="${path }/baseinfo/basicInfomationManage/maintainBasicInfomation.action">
		<input type="hidden" name="basicInfoMation.id" value="${basicInfoMation.id }" />
	<input type="hidden" name="organization.id" value="${organization.id }" />
	<input type="hidden" name="basicInfoMation.organization.id" value="${basicInfoMation.organization.id }" />
		<div class="grid_3 lable-right">
			<label>名称：</label>
		</div>
		<div class="grid_5" >
			<label id="orgName">${basicInfoMation.organization.orgName}</label>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_3 lable-right">
			<label>地理位置：</label>
		</div>
		<div class="grid_18">
			<input type="text" name="basicInfoMation.geographicalPosition" value="${basicInfoMation.geographicalPosition}" id="geographicalPosition" maxlength="30" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<label>气候：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="basicInfoMation.climate" value="${basicInfoMation.climate}" id="climate" maxlength="20" class="form-txt" />
		</div>
		
		<div class="grid_3 lable-right">
			<label>总面积：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="basicInfoMation.totalArea" value="${basicInfoMation.totalArea}" id="totalArea" maxlength="13" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<label>耕地面积：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="basicInfoMation.cultivatedLandArea" value="${basicInfoMation.cultivatedLandArea}" id="cultivatedLandArea" maxlength="13" class="form-txt" />
		</div>
		
		<div class="grid_3 lable-right">
			<label>林地面积：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="basicInfoMation.woodlandArea" value="${basicInfoMation.woodlandArea}" id="woodlandArea" maxlength="13" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<label>荒地面积：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="basicInfoMation.wastelandArea" value="${basicInfoMation.wastelandArea}" id="wastelandArea" maxlength="13" class="dialogtip form-txt" />
		</div>
		
		<div class="grid_3 lable-right">
			<label>海拔：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="basicInfoMation.altitude" value="${basicInfoMation.altitude}" id="altitude" class="dialogtip form-txt" maxlength="4"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_3 lable-right">
			<label>土壤：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="basicInfoMation.soilProperties" value="${basicInfoMation.soilProperties}" id="soilProperties"  class="dialogtip form-txt" maxlength="10" />
		</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
						$.messageBox({
							message: data,
							level: "error"
						});
						return;
					}
					$("#basicInfoMationId").val(data.id);
					$("#geographicalPosition").empty().append(data.geographicalPosition);
					$("#climate").empty().append(data.climate);
					$("#totalArea").empty().append(data.totalArea);
					$("#cultivatedLandArea").empty().append(data.cultivatedLandArea);
					$("#woodlandArea").empty().append(data.woodlandArea);
					$("#wastelandArea").empty().append(data.wastelandArea);
					$("#altitude").empty().append(data.altitude);
					$("#soilProperties").empty().append(data.soilProperties);
					$.messageBox({message:"已成功编辑基本信息"});
				 	$("#villageProfileDialog").dialog("close");
	   		    }
			});
		}
	});
})
</script>

