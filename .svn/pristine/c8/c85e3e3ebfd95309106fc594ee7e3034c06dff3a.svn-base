<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp" />

<div id="dialog-form" title="高级搜索" class="container container_24">
<form id="maintainForm"  method="post" action="">
   <div class="grid_5 lable-right">
	     <label class="form-lbl">用户名：</label>
    </div>
	<div class="grid_7">
	    <input type="text" name="mobileErrorLogs.name" id="name" maxlength="20"  class="form-txt" />
	</div>
    <div class="grid_4 lable-right">
	     <label class="form-lbl">错误标题：</label>
    </div>
	<div class="grid_7">
	    <input type="text" name="mobileErrorLogs.errorLogsName" id="errorLogsName" maxlength="20"  class="form-txt" />
	</div>
    <div class='clearLine'>&nbsp;</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">发生时间：</label>
	</div>
	<div class="grid_7 form-left">
		 <input type="text" id="conditionOccurFrom" name="mobileErrorLogs.occurFromDate" value="" readonly="readonly" class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">到：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="conditionOccurEnd" name="mobileErrorLogs.occurEndDate" value="" readonly="readonly" class="form-txt" />
	</div>
</form>
</div>
<script type="text/javascript">
$("#conditionOccurFrom").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
   		maxDate:"+0d",
       	onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurEnd").datepicker("option","minDate",date);
			}
		}
    }); 
	
	$("#conditionOccurEnd").datePicker({
		yearRange:"1900:2030",
		dateFormat:"yy-mm-dd",
    	maxDate:"+0d",
    	timeFormat: 'HH:mm:ss',
   		onSelect: function(dateText, inst) {
			if(dateText!=null&&dateText!=""){
				var dateUnit=dateText.split("-");
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionOccurFrom").datepicker("option", "maxDate",date);
			}
		}
	});
</script>