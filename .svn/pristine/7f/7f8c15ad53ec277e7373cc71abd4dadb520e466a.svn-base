<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<div class="grid_7 lable-right">
		<label class="form-lbl">收件人：</label>
	</div>
	<div class="grid_16">
		<input type="text" id="conditionReceiver" maxlength="20" class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_7 lable-right">
		<label class="form-lbl">发送时间 从：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionStartCreateTime"
			readonly="readonly" class="form-txt" />
	</div>
	<div class="grid_2" align="center">
		<label class="form-lbl">到</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionEndCreateTime"
			readonly="readonly" class="form-txt" />
	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$('#conditionStartCreateTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionEndCreateTime").datepicker("option", "minDate",date);
			}
		}
	}); 
        
	$('#conditionEndCreateTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionStartCreateTime").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>


