<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<form id="searchExceptionalSituationRecordForm" class="container container_24">
    <div  class="grid_5 lable-right">
  		<label class="form-lbl">地点：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="address" name="exceptionalSituationRecordVo.address" class="form-txt" maxlength="50"/>
   	</div>
	<div class="grid_5 lable-right">
        <label class="form-lbl">异常类型：</label>
    </div>
    <div class="grid_7">
        <select id="exceptionSituation" name="exceptionalSituationRecordVo.exceptionSituation.id" class="form-txt">
            <@pop.OptionTag name="@com.tianque.domain.property.PropertyTypes@EXCEPTION_SITUATION_TYPE" />
        </select>
    </div>
   	<div class='clearLine'></div>
	<div  class="grid_5 lable-right">
  		<label class="form-lbl">记录起始时间：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="recordStartDate" name="exceptionalSituationRecordVo.recordStartDate" class="form-txt" readonly="readonly"/>
   	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">记录截止时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="recordEndDate" name="exceptionalSituationRecordVo.recordEndDate" class="form-txt" readonly="readonly"/>
   	</div>
   	<div class='clearLine'></div>
   	<div class="grid_5 lable-right">
		<label class="form-lbl">是否签收：</label>	
	</div>
	<div class="grid_7">
		<select id="status" name="exceptionalSituationRecordVo.status" class="form-select" >
				<option value=""></option>
				<option value="0">否</option>
				<option value="1">是</option>
		</select>
    </div>
   	<div  class="grid_5 lable-right">
  		<label class="form-lbl">签收人姓名：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="signMemberName" name="exceptionalSituationRecordVo.signMemberName" class="form-txt" maxlength="100"/>
   	</div>
   	<div class='clearLine'></div>
	<div  class="grid_5 lable-right">
  		<label class="form-lbl">签收起始时间：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="signStartDate" name="exceptionalSituationRecordVo.signStartDate" class="form-txt" readonly="readonly"/>
   	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">签收截止时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="signEndDate" name="exceptionalSituationRecordVo.signEndDate" class="form-txt" readonly="readonly"/>
   	</div>
	<div class='clearLine'></div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">是否回复：</label>
	</div>
	<div class="grid_7">
		<select  name="exceptionalSituationRecordVo.hasReplay" class="form-select" >
				<option value=""></option>
				<option value="0">否</option>
				<option value="1">是</option>
		</select>
   	</div>
	<div class='clearLine'></div>

</form>
<script type="text/javascript">
$(document).ready(function(){

	$('#recordStartDate').dateTimePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
		timeFormat: 'HH:mm:ss',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!= null && dateText != ''){
			$("#recordEndDate").datepicker("option", "minDate",dateText);
			}
		}
	});
	
	$('#recordEndDate').dateTimePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
		timeFormat: 'HH:mm:ss',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText != null && dateText != ''){
			$("#recordStartDate").datepicker("option", "maxDate",dateText);
			}
		}
	});
	
	$('#signStartDate').dateTimePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
		timeFormat: 'HH:mm:ss',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText != null && dateText != ''){
			$("#signEndDate").datepicker("option", "minDate", dateText);
			}
		}
	});
	
	$('#signEndDate').dateTimePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
		timeFormat: 'HH:mm:ss',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText != null && dateText != ''){
			$("#signStartDate").datepicker("option", "maxDate",dateText);
			}
		}
	});

	
});
</script>
