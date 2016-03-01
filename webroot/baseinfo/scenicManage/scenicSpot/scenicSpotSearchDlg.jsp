<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<form id="searchScenicSpotForm">
	<div class="container container_24">
		<div class="grid_5 lable-right">
			<label class="form-lbl">景点名称：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchScenicSpotVo.name" id="name" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">景点地址：</label>
		</div>
		<div class="grid_19">
	    	<input type="text" name="searchScenicSpotVo.address" id="address" class="form-txt" style="width:99%;" maxlength="50"/>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">景点等级：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="grade" name="searchScenicSpotVo.grade" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">景点电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="telephone" name="searchScenicSpotVo.telephone" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">景点负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="personLiable" name="searchScenicSpotVo.personLiable" class="form-txt" />
		</div>    
		<div class="grid_5 lable-right">
			<label class="form-lbl">景点负责人电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="personLiableTelephone" name="searchScenicSpotVo.personLiableTelephone" class="form-txt" />
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
	</div>
</form>
<script type="text/javascript">
$(document).ready(function(){
	//
});
</script>
