<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<div class="grid_7 lable-right">
		<label class="form-lbl">来源手机：</label>
	</div>
	<div class="grid_16">
		<input type="text" id="conditionSourceMobile" maxlength="20"
			class="form-txt" value="${searchSmsReceivedBoxVo.sourceMobile}"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_7 lable-right">
		<label class="form-lbl">发送时间 从：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionStartReceiverTime" readonly="readonly"
			class="form-txt" value="${searchSmsReceivedBoxVo.startReceiverTime}"/>
	</div>
	<div class="grid_2" align="center">
		<label class="form-lbl">到</label>
	</div>
	<div class="grid_7">
		<input type="text" id="conditionEndReceiverTime" readonly="readonly"
			class="form-txt" value="${searchSmsReceivedBoxVo.endReceiverTime}"/>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$('#conditionStartReceiverTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionEndReceiverTime").datepicker("option", "minDate",date);
			}
		}
	}); 
        
	$('#conditionEndReceiverTime').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
        maxDate:'+0d',
        onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#conditionStartReceiverTime").datepicker("option", "maxDate",date);
			}
		}
	});
});
</script>


