<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="工作日志信息查询" class="container container_24">
	<div>
		<div class="grid_5 lable-right">
  			<label class="form-lbl">工作人员：</label>
  		</div>
  		<div class="grid_7 form-left">
  			<input type="text" id="conditionWorkUserName" maxlength="20"
	  				class="form-txt" />
  		</div>

		<div class="grid_4 lable-right">
  			<label class="form-lbl">日志类型：</label>
  		</div>
  		<div class="grid_7">
  			<select id="conditionDiaryType"  class="dialogtip form-txt" title="" style="width:128px">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKDIARY_DIARY_TYPE" />
			</select>
  		</div>
   		<div class="clearLine">&nbsp;</div>

   		<div class="grid_5 lable-right">
   			<label class="form-lb1">开始时间：</label>
   		</div>
   		<div class="grid_7 form-left" id="workTimeDiv">
   			<input type="text" id="conditionWorkTimeStart" maxlength="32" readonly
  				class="form-txt" />
   		</div>

   		<div class="grid_4 lable-right">
   			<label class="form-lb1">结束时间：</label>
   		</div>
   		<div class="grid_7 form-left" id="workTimeDiv">
   			<input type="text" id="conditionWorkTimeEnd" maxlength="32" readonly
  				class="form-txt" />
   		</div>
   		<div class="clearLine">&nbsp;</div>

   		<div class="grid_5 lable-right">
   			<label class="form-lb1">地点：</label>
   		</div>
   		<div class="grid_19 form-left">
   			<input type="text" id="conditionWorkPlace" maxlength="50" class="form-txt" />
   		</div>
   		<div class="clearLine">&nbsp;</div>
   		<div class="grid_5 lable-right">
   			<label class="form-lb1">工作内容：</label>
   		</div>
   		<div class="grid_19 form-left">
   			<textarea rows="3" cols="5" id="conditionworkContent" class="form-txt" maxlength="50"></textarea>
   		</div>
	</div>
</div>

<script type="text/javascript">
$('#conditionWorkTimeStart').datePicker({
	yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
       maxDate:'+0d',
       onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#conditionWorkTimeEnd").datepicker("option", "minDate", date);
			}
		}
});

$('#conditionWorkTimeEnd').datePicker({
	yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
       maxDate:'+0d',
    onSelect : function(dateText, inst) {
		if (dateText != null && dateText != '') {
			var dateUnit = dateText.split('-');
			var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
			$("#conditionWorkTimeStart").datepicker("option", "maxDate", date);
		}
	}
});
</script>
