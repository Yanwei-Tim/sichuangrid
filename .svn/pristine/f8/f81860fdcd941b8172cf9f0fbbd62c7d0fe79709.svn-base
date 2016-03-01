<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div title="工作台帐维护" class="container container_24">
<input name="mode" id="mode" value="${mode}" type="hidden">
       <input type="hidden"  id="dailyDirectoryId" name="searchKeyAreasOfInvestigationInfoWorkingRecord.dailyDirectory.id" value="${searchKeyAreasOfInvestigationInfoWorkingRecord.dailyDirectory.id}">
         <div class="grid_4 lable-right">
			<label class="form-lbl">排查部门：</label>
		</div>
		<div class="grid_7">
			<input type="text" id="orgName" name="searchKeyAreasOfInvestigationInfoWorkingRecord.investigationOrg.orgName" size="20" class="form-txt" />
		</div>
	    <div class="grid_4 lable-right">
			<label class="form-lbl">地区名称：</label>
		</div>
		<div class="grid_7">
			<input type="text"  size="20" id="areaName" name="searchKeyAreasOfInvestigationInfoWorkingRecord.areaName" class="form-txt" >

		</div>
     <div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">排查日期从：</label>
		</div>
	  <div class="grid_7">
		<input type="text" id="dealFrom" size="20" name="searchKeyAreasOfInvestigationInfoWorkingRecord.investigationMinDate" class="form-txt" readonly/>
	</div>
<div class="grid_4 lable-right">
			<label class="form-lbl">到：</label>
		</div>
	  <div class="grid_7">
		<input type="text" id="dealTo" size="20" name="searchKeyAreasOfInvestigationInfoWorkingRecord.investigationMaxDate" class="form-txt" readonly/>
	</div>
	<div class="grid_4 lable-right">
			<label class="form-lbl">警示或挂牌：</label>
		</div>
    <div class="grid_7 lable-right heightAuto">
		<input type="text" name="searchKeyAreasOfInvestigationInfoWorkingRecord.warningOrListing" maxlength="10" id="warningOrListing" class="form-txt"/>
	</div>
	<div class='clearLine'></div>
	<div class="grid_4 lable-right">
		<label >主要问题：</label>
	</div>
	<div class="grid_18 heightAuto">
		<textarea rows="4" cols="80" name="searchKeyAreasOfInvestigationInfoWorkingRecord.mainProblem" class="form-txt" style="width: 98.5%" id="mainProblem" />
	</div>
     <div class='clearLine'></div>
	<div class="grid_4 lable-right">
			<label class="form-lbl">对策与措施：</label>
		</div>
	  <div class="grid_18 heightAuto">
		<textarea  id="policiesAndMeasures" name="searchKeyAreasOfInvestigationInfoWorkingRecord.policiesAndMeasures" rows="4" cols="80" class="form-txt" style="width: 98.5%" />
	</div>
    <div class='clearLine'></div>

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