<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div id="dialog-form" class="container container_24">
	<form id="searchDustbinFrom">
      	<input  type="hidden" id="orgId" name="orgId" value="${orgId}" /> 
        <div class="grid_4 lable-right">
	     	<label class="form-lbl">部件类型：</label>
	  	</div>
	  	<div class="grid_8">
	  <select name="searchDustbinVo.partType" <c:if test="${'edit'==mode }">disabled</c:if> class="form-txt" id="partType">
		  <pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_TYPE" 
		   		reletionId="partNameId" reletionName="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME" id="partType" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">部件名称：</label>
		</div>
		<div class="grid_8">
			<select id="partNameId" name="searchDustbinVo.partName" class="form-txt">
			<pop:OptionTag name="@com.tianque.domain.property.PropertyTypesYinchuan@PART_NAME"   />
			</select>
	  	</div>
	  	
	  	<div class="grid_4 lable-right">
	  		<label class="form-lbl">部件标识码：</label> 
	  	</div>
	    <div class="grid_8">
	   		<input type="text"  id="dustbinCode"  name="searchDustbinVo.dustbinCode"  class="form-txt" maxlength="20"/>
	   
	   </div>
	   <div class="grid_4 lable-right">
  			<label class="form-lb1">注销状态：</label>
	   	</div>
	   	<div class="grid_8">
			<select id="isEmphasis" name="searchDustbinVo.isEmphasis" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
		</div>
		<div class="grid_4 lable-right">
		  	<label class="form-lbl">地址：</label> 
		</div>
		<div class="grid_8">
	  	     <input type="text"  id="address"  name="searchDustbinVo.address"  class="form-txt" maxlength="20"/>
  	    </div>
  	    <div class="grid_4 lable-right">
		  	<label class="form-lbl">是否有视频流：</label> 
		</div>
		<div class="grid_8">
	  	     <select id="isEmphasis" name="searchDustbinVo.hasVideo" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
  	    </div>
	   	<div class="grid_4 lable-right">
		  	<label class="form-lbl">主管部门：</label> 
		</div>
		<div class="grid_20">
	  	     <input type="text"  id="deptName"  name="searchDustbinVo.deptName"  class="form-txt" maxlength="20"/>
  	    </div>
	  	 <div class="grid_4 lable-right">
	     	<label class="form-lbl">权属单位：</label>
	  	</div>
	  	<div class="grid_20">
  	  	  <input type="text"  id="ownershipUnitName"  name="searchDustbinVo.ownershipUnitName"  class="form-txt" maxlength="20"/>
  	   </div>
	   <div class="grid_4 lable-right">
	     	<label class="form-lbl">护养单位：</label>
	  	</div>
	  	<div class="grid_20">
	  	  <input type="text"  id="maintenanceUnitName"  name="searchDustbinVo.maintenanceUnitName"  class="form-txt" maxlength="20"/>
  	   </div>
	</form>
</div>
<script type="text/javascript">
</script>