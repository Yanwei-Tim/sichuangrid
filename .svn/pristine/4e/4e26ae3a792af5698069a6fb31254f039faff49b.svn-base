<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div id="dialog-form" class="container container_24">
	<form id="searchSnapshotSystemForm">
      	<input type="hidden" id="organization" name="searchSnapshotSystemVo.organization.id" value="${param.orgId}"/>
		<input type="hidden" id="organization.id" name="organization.id" value="${param.orgId}"/>
		<div class="grid_4 lable-right">
			<label class="form-lbl">编号：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="searchSnapshotSystemVo.snapshotNo" id="snapshotNo" value=""   class="form-txt" maxlength="3" />
	  	</div>
	  	
	  	<div class="grid_4 lable-right">
	  		<label class="form-lbl">地址：</label> 
	  	</div>
	    <div class="grid_8">
	   		<input type="text"  id="address"  name="searchSnapshotSystemVo.address"  class="form-txt" maxlength="30"/>
	   
	   </div>
	   <div class="grid_4 lable-right">
  			<label class="form-lb1">注销状态：</label>
	   	</div>
	   	<div class="grid_8">
			<select id="isEmphasis" name="searchSnapshotSystemVo.isEmphasis" class="form-txt">
				<option value="-1" selected="selected">全部</option>
				<option value="0">现在关注</option>
				<option value="1">曾经关注</option>
			</select>
		</div>
		<div class="grid_4 lable-right">
				<label class="form-lbl">绑定状态：</label>
		</div>
		<div class="grid_7 lable-right">
			<select id="isBind" name="" class="form-select">
 					<option value>全部</option>
	 				<option value="false" <c:if test="${!searchSnapshotSystemVo.isBind }">selected</c:if>>未绑</option>
	 				<option value="true" <c:if test="${searchSnapshotSystemVo.isBind }">selected</c:if>>已绑</option>
	 		</select>
		</div>
	</form>
</div>
<script type="text/javascript">
$("#isBind").change(function(){
	var value = $(this).val();
	if(value==null || value=="" || value=="全部"){
		 $(this).attr("name","")
	}else{
		$(this).attr("name","searchSnapshotSystemVo."+this.id)
	}
}).change();
</script>