<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
	<form id="serviceRecord_form" method="post" action="" >
	<pop:token />
		<input type="hidden" name="searchServiceRecordVo.teamId" value="${searchServiceRecordVo.teamId}">
	    <input type="hidden" name="searchServiceRecordVo.populationId" value="${population.id}">
	    <input type="hidden" name="searchServiceRecordVo.populationType" value="${population.attentionPopulationType}">
	    <input type="hidden" name="searchServiceRecordVo.locationId" value="${locationId}">
	    <input type="hidden" name="searchServiceRecordVo.locationType" value="${locationType}">
	    
		<div class="grid_4 lable-right">
   			<label class="form-lb1">服务时间：</label>
   		</div>
   		<div class="grid_1"><label>&nbsp;&nbsp;从</label></div>
   		<div class="grid_8">
   			<input type="text" id="startOccurDate" name="searchServiceRecordVo.startOccurDate" readonly="readonly" class="form-txt"/>
   		</div>
   		<div class="grid_1"><label>&nbsp;&nbsp;至</label></div>
   		<div class="grid_8">
   			<input type="text" id="endOccurDate" name="searchServiceRecordVo.endOccurDate" readonly="readonly" class="form-txt"/>
   		</div>
   		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
   			<label class="form-lb1">发生地点：</label>
   		</div>
   		<div class="grid_18">
   			<input type="text" name="searchServiceRecordVo.occurPlace" class="form-txt" style="width: 99%" />
   		</div>
   		<div class='clearLine'>&nbsp;</div>
   		
		
    	<div class="grid_4 lable-right">
    		<label class="form-lb1">服务对象：</label>
   		</div>
   		<div class="grid_18">
			<input type="text" name="searchServiceRecordVo.serviceObjects" class="form-txt" style="width: 99%" />
    	</div>    	
    	<div class='clearLine'>&nbsp;</div>
    	
		<div class="grid_4 lable-right">
			<label class="form-lb1">服务人员：</label>
   		</div>
   		<div class="grid_18">
			<input type="text" name="searchServiceRecordVo.serviceMembers" class="form-txt" style="width: 99%" />
    	</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
   			<label class="form-lb1">内     容：</label>
   		</div>
   		<div class="grid_18">
			<input type="text" name="searchServiceRecordVo.recordContent" class="form-txt" style="width: 99%" />
    	</div>
		<div class='clearLine'>&nbsp;</div>
   	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#startOccurDate, #endOccurDate").datePicker({
		yearRange: '2000:2030',
		defaultDate: "+1d",
		onSelect: function( selectedDate ) {
			var option = this.id == "startOccurDate" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(
						instance.settings.dateFormat ||
						$.datepicker._defaults.dateFormat,
						selectedDate, instance.settings );
			$("#startOccurDate, #endOccurDate").not( this ).datepicker( "option", option, date);
		}
	});
})
</script>