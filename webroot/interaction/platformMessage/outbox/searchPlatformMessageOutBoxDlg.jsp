<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<div class="grid_4 lable-right">
		<label class="form-lbl">主题：</label>
	</div>
	<div class="grid_8">
		<input type="text"  id="title"  class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">内容：</label>
	</div>
	<div class="grid_8">
		<input type="text"  id=content  class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>	
	<div class="grid_4 lable-right">
		<label class="form-lbl">发送时间 从：</label>
	</div>
	<div class="grid_8">
		<input type="text" id="startTime"  class="form-txt" />
	</div>
		<div class="grid_4 lable-right">
		<label class="form-lbl">到：</label>
	</div>
	<div class="grid_8">
		<input type="text"  id="endTime"  class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">收件人：</label>
	</div>
	<div class="grid_8">
		<input type="text"  id=receiverNames  class="form-txt" />
	</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">是否有附件：</label>
	</div>
	<div class="grid_8">
		<select id="hasAttach" class="form-txt">
			<option value="">请选择</option>
			<option value="1">是</option>
			<option value="0">否</option>
		</select>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$('#startTime').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'}); 
	$('#endTime').datePicker({
		yearRange: '1900:2020',
		dateFormat: 'yy-mm-dd',
        maxDate:'+1d'}); 
	});
</script>


