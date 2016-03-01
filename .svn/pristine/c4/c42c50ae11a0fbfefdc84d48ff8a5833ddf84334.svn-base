<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/> 

<div class="container container_24">
	<div class="grid_6 lable-right">
		<label class="form-lb1>">姓名：</label>
	</div>
	<div class="grid_5">
		<input type="text"  id="conditionName"  name="druggyTask.name"   value="${(druggyTask.name)!}"   class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lb1>">身份证号码：</label>
	</div>
	<div class="grid_5">
		<input type="text"  id="idCard"  name="druggyTask.idCard"   value="${(druggyTask.idCard)!}"   class="form-txt" />
	</div>
	<div class="grid_6 lable-right">
		<label class="form-lb1>">电话号码：</label>
	</div>
	<div class="grid_5">
		<input type="text"  id="phone"  name="druggyTask.phone"   value="${(druggyTask.phone)!}"   class="form-txt" />
	</div>
	<div class="grid_5 lable-right" >
		<label class="form-lbl">帮扶人员：</label>
	</div>
	<div class="grid_5">
		<input type="text" id="helpPeople" class="form-txt" />
   	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_6 lable-right" >
		<label class="form-lbl">记录起始时间：</label>
	</div>
	<div class="grid_5">
		<input type="text" id="conditionStartDate" class="form-txt" />
   	</div>
	
	<div class="grid_5 lable-right" >
		<label class="form-lbl">记录结束时间：</label>
	</div>
	<div class="grid_5">
			<input type="text" id="conditionEndDate" class="form-txt" />
   	</div>
   	<div class='clearLine'></div>
   	<div class="grid_6 lable-right" >
		<label class="form-lbl">签收起始时间：</label>
	</div>
	<div class="grid_5">
		<input type="text" id="conditionSignStartDate" class="form-txt" />
   	</div>
	
	<div class="grid_5 lable-right" >
		<label class="form-lbl">签收结束时间：</label>
	</div>
	<div class="grid_5">
			<input type="text" id="conditionSignEndDate" class="form-txt" />
   	</div>
   	<div class='clearLine'></div>
   	<div class="grid_6 lable-right">
		<label class="form-lbl">有无异常：</label>
	</div>
	<div class="grid_5">
		<select id="hasException"  class="form-select" >
				<option value=""></option>
				<option value="0">无</option>
				<option value="1">有</option>
		</select>
   	</div>
   	<div class="grid_5 lable-right">
		<label class="form-lbl">是否回复：</label>
	</div>
	<div class="grid_5">
		<select id="hasReplay"  class="form-select" >
				<option value=""></option>
				<option value="0">否</option>
				<option value="1">是</option>
		</select>
   	</div>
	<div class='clearLine'></div>
</div>	
</form>
<script type="text/javascript">
	$('#conditionStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionStartDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#conditionEndDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionEndDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#conditionSignEndDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionSignEndDate").datepicker("option", "minDate",date);
			}
		}
	});
	$('#conditionSignStartDate').datePicker({
		yearRange: '1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect: function(dateText, inst) {
		if(dateText!=null&&dateText!=''){
			var dateUnit=dateText.split('-');
			var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
			$("#conditionSignStartDate").datepicker("option", "minDate",date);
			}
		}
    });

	
</script>

