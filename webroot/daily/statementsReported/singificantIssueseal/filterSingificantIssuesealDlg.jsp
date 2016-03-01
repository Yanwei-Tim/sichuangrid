<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form" title="工作台帐维护" class="container container_24">
    <input type="hidden" id="dailyDirectoryId" value="${searchSignificantIssuedealWorkingRecord.dailyDirectory.id}">
	 <div class="grid_4 lable-right">
			<label class="form-lbl">排查部门：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="orgName" size="20" class="form-txt" />
		</div>
	    <div class="grid_4 lable-right">
			<label class="form-lbl">单位地点：</label>
		</div>
		<div class="grid_7">
			<input type="text" size="20" id="address" class="form-txt" >

		</div>
	 <div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label >时间从：</label>
	</div>
	<div class="grid_7">
		<input type="text" class="form-txt" maxlength="40"  readonly id="dealFrom" />
	</div>
	<div class="grid_4 lable-right">
		<label >到：</label>
	</div>
	<div class="grid_7">
		<input type="text"  class="form-txt" maxlength="40"  readonly id="dealTo" />
	</div>
	 <div class='clearLine'></div>
	  <div class="grid_4 lable-right">
			<label class="form-lbl">责任领导：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="accountabilityLeading" size="20" class="form-txt" />
		</div>
	    <div class="grid_4 lable-right">
			<label class="form-lbl">责任单位：</label>
		</div>
		<div class="grid_7">
			<input type="text"  size="20" id="accountabilityUnit" class="form-txt" >

		</div>
     <div class='clearLine'></div>
     <div class="grid_4 lable-right">
			<label class="form-lbl">矛盾纠纷简况：</label>
		</div>
		<div class="grid_19  heightAuto">
			<textarea  rows="4" cols="80" class="form-txt" style="width: 98%"
					id="significantIssuedealReason" />
		</div>
		<div class='clearLine'></div>

		<div class="grid_4 lable-right heightAuto">
			<label class="form-lbl">调处情况：</label>
		</div>
		<div class="grid_19 heightAuto">
			<textarea rows="4" cols="80" class="form-txt" style="width: 98%"
					 id="remarks" />
		</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#dealFrom").datePicker({
		yearRange: "1990:2060",
		dateFormat:"yy-mm-dd",
		maxDate:"+0d",
        onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#dealTo").datepicker("option", "minDate",date);
			}
		}
	});

	$("#dealTo").datePicker({
		yearRange: "1990:2060",
		dateFormat:"yy-mm-dd",
		maxDate:"+0d",
		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#dealFrom").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>