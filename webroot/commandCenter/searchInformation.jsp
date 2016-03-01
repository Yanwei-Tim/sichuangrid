<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
<form id="searchInformation" action="">
	<input id="mode" type="hidden" name="mode" value="${mode }" /> 
	<div class="grid_4 lable-right" >
		<label class="form-lbl">发生时间：</label>
    </div>
	<div class="grid_6">
		<input type="text"  name="eventSourceVo.startDate"
			id="sourceStartDate" class="form-txt" readonly="readonly" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">到：</label>
	</div>
	<div class="grid_8">
		<input type="text" name="eventSourceVo.endDate" id="sourceEndDate"
			class="form-txt" readonly="readonly" style="width:94%"/>
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lb1">来源人：</label>
	</div>
	<div class="grid_6">
		<input  type="text" id="sourcePeople"
			name="eventSourceVo.sourcePeople" class="form-txt" />
	</div>
	<div class="grid_4 lable-right" >
		<label class="form-lbl">联系电话：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="telephone"
			name="eventSourceVo.telephone" class="form-txt" style="width:94%"/>
	</div>  
	
		<div class="grid_4 lable-right">
		<label class="form-lbl">发生地点：</label>
	</div>
	<div class="grid_6">
		<input  type="text" id="orgName"
			name="eventSourceVo.orgName" class="form-txt" />
	</div>	
	
	<div class="grid_4 lable-right" >
		<label class="form-lbl">标题：</label>
	</div>
	<div class="grid_8">
		<input  type="text" id="title"
			name="eventSourceVo.title" class="form-txt" style="width:94%"/>
	</div>
	
	<div class="grid_4 lable-right" ><label class="form-lb1">消息内容：</label></div>
	<div class="grid_18 ">
		<textarea id="content" rows="5" cols="77" name="eventSourceVo.content" 	title="请输入内容" class='form-txt {required:true,messages:{required:"请输入内容"}}'></textarea>
   	</div>



</form>
</div>
<script type="text/javascript">

	$('#sourceStartDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#sourceEndDate").datepicker("option", "minDate", date);
			}
		}
	});

	$('#sourceEndDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#sourceStartDate").datepicker("option", "maxDate", date);
			}
		}
	});
   

</script>










