<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />
<form id="searchTermerRecordForm" class="container container_24">
 	<div  class="grid_5 lable-right">
  		<label class="form-lbl">姓名：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="name" name="termerRecordVo.name" class="form-txt" maxlength="100"/>
   	</div>
   	<div class="grid_5 lable-right">
		<label class="form-lb1>">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input type="text"  id="idCard"  name="termerRecordVo.idCard"   value="${(termerRecord.idCard)!}"   class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1>">电话号码：</label>
	</div>
	<div class="grid_7">
		<input type="text"  id="phone"  name="termerRecordVo.phone"   value="${(termerRecord.phone)!}"   class="form-txt" />
	</div>
   	<div class="grid_5 lable-right">
		<label class="form-lbl">是否签收：</label>	
	</div>
	<div class="grid_7">
		<select id="status" name="termerRecordVo.status" class="form-select" >
				<option value=""></option>
				<option value="0">否</option>
				<option value="1">是</option>
		</select>
    </div>
   	<div class='clearLine'></div>
	<div  class="grid_5 lable-right">
  		<label class="form-lbl">记录起始时间：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="recordStartDate" name="termerRecordVo.recordStartDate" class="form-txt" readonly="readonly"/>
   	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">记录截止时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="recordEndDate" name="termerRecordVo.recordEndDate" class="form-txt" readonly="readonly"/>
   	</div>
   	<div class='clearLine'></div>
   	<div  class="grid_5 lable-right">
  		<label class="form-lbl">地点：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="address" name="termerRecordVo.address" class="form-txt" maxlength="50"/>
   	</div>
   	<div  class="grid_5 lable-right">
  		<label class="form-lbl">签收人姓名：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="signMemberName" name="termerRecordVo.signMemberName" class="form-txt" maxlength="100"/>
   	</div>
   	<div class='clearLine'></div>
	<div  class="grid_5 lable-right">
  		<label class="form-lbl">签收起始时间：</label>
  	</div>
	<div class="grid_7">
		<input type="text" id="signStartDate" name="termerRecordVo.signStartDate" class="form-txt" readonly="readonly"/>
   	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">签收截止时间：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="signEndDate" name="termerRecordVo.signEndDate" class="form-txt" readonly="readonly"/>
   	</div>
	   	<div class='clearLine'></div>
	<div class="grid_5 lable-right" >
		<label class="form-lbl">帮扶人员：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="helpPeople" name="termerRecordVo.helpPeople" class="form-txt" />
   	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">有无异常：</label>
	</div>
	<div class="grid_7">
		<select name="termerRecordVo.hasException"  class="form-select" >
				<option value=""></option>
				<option value="0">无</option>
				<option value="1">有</option>
		</select>
   	</div>
   	<div class='clearLine'></div>
   	
   	<div class="grid_5 lable-right">
		<label class="form-lbl">是否回复：</label>
	</div>
	<div class="grid_7">
		<select name="termerRecordVo.hasReplay"  class="form-select" >
				<option value=""></option>
				<option value="0">否</option>
				<option value="1">是</option>
		</select>
   	</div>
	<div class='clearLine'></div>
</form>
<script type="text/javascript">
$(document).ready(function(){

	$('#recordStartDate').datePicker({
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
	
	$('#recordEndDate').datePicker({
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
	
	$('#signStartDate').datePicker({
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
	
	$('#signEndDate').datePicker({
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
