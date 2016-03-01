<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
<form id="searchPublicNoticeReceiveForm" action="">
	<input id="mode" type="hidden" name="mode" value="${mode }" /> <input
		id="publicNoticeOrgId" type="hidden"
		name="publicNoticeVo.organization.id"
		value="<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().id"/>" />
	<div class="grid_4 lable-right">
		<label class="form-lb1">标题：</label>
	</div>
	<div class="grid_20">
		<input  type="text" id="editorTitle"
			name="publicNoticeVo.editorTitle" class="form-txt" />
	</div>
    
	<div class="grid_4 lable-right">
		<label class="form-lbl">发布者： </label>
	</div>
	<div class="grid_7">
		<input type="text" id="noticeEditor" 
			name="publicNoticeVo.noticeEditor" class="form-txt" />
	</div>

	<div class="grid_4 lable-right" >
		<label class="form-lbl">发布时间：</label>
    </div>
	<div class="grid_4">
		<input type="text"  name="publicNoticeVo.editstartDate"
			id="editstartDate" class="form-txt" readonly="readonly" style="width:86%"/>
	</div>
	<div class="grid_1 " style="text-align: center;">
		<label class="form-lbl">到</label>
	</div>
	<div class="grid_4">
		<input type="text" name="publicNoticeVo.editEndDate" id="editEndDate"
			class="form-txt" readonly="readonly" style="width:86%"/>
	</div>
</form>
</div>
<script type="text/javascript">
	$('#editstartDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#editEndDate").datepicker("option", "minDate", date);
			}
		}
	});
	$('#editEndDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#editstartDate").datepicker("option", "maxDate", date);
			}
		}
	});
</script>