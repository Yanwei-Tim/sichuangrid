<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchScenicEquipmentForm">
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">名称：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchScenicEquipmentVo.equipName" id="equipName" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">地址：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchScenicEquipmentVo.equipAddress" id="conditionequipAddress" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">类型：</label>
		</div>
		<div class="grid_7">
			<select name="searchScenicEquipmentVo.equipType" id="location-equipType" class="form-txt">
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@SCENICEQUIP_TYPES" />
			</select>
		</div>   
		<div class="grid_5 lable-right">
			<label class="form-lbl">联系电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="conditionMobile" name="searchScenicEquipmentVo.mobile" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="manager" name="searchScenicEquipmentVo.manager" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">负责人电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="managerMobile" name="searchScenicEquipmentVo.managerMobile" class="form-txt" />
		</div>    
       	<div class="grid_5 lable-right">
  			<label class="form-lb1">关注状态：</label>
	   	</div>
	   	<div class="grid_7">
			<select id="isLock" class="form-txt" >
				<option value="" selected="selected">全部</option>
				<option value="false">现在关注</option>
				<option value="true">曾经关注</option>
			</select>
		</div>
		<div class="grid_5 lable-right hidden" >
		<label class="form-lbl">周边景点：</label>
		</div>
		<div class="grid_7 hidden">
			<input type="text" id="aroundScenic" name="searchScenicEquipmentVo.aroundScenic" class="form-txt" />
		</div>  
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){


	
});

</script>
