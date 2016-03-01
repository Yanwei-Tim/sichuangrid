<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div>
	<form id="searchWorkingRecordForm" method="post" action="${path}/workingRecord/workingRecordManage/findWorkingRecordsByQueryCondition.action?dialogName=workingRecordDialog">
		<input type="hidden" name="mode" id="mode" value="${mode}"/>
		<div id="search-condition-form" title="查询台账" class="container container_24">
			<div class="grid_4 lable-right"><label class="form-lbl">类型：</label>
			</div>
			<div class="grid_20">
				<select name="searchWorkingRecordVo.workingRecordType" id="workingRecordType" class="form-txt">
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE"/>
				</select>
			</div>
			<div class='clearLine'></div>
			<div id="includeDiv"></div>
			<div class='clearLine'></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">名称：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchWorkingRecordVo.name" id="searchVoName" class="form-txt"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">创建时间：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="searchWorkingRecordVo.createDateBegin" id="createDateBegin"
				readonly="readonly" class="form-txt"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">至：</label>
			</div>
			<div class="grid_4">
				<input type="text" name="searchWorkingRecordVo.createDateEnd" id="createDateEnd"
				readonly="readonly" class="form-txt"/>
			</div>
			<div class="clearLine"></div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">有无附件：</label>
			</div>
			<div class="grid_4">
				<select name="searchWorkingRecordVo.hasAttach" id="hasAttach" class="form-txt">
					<option value="-1">请选择</option>
					<option value="1">有附件</option>
					<option value="0">无附件</option>
				</select>
			</div>
		</div>
	</form>
	<div id="temp">
		<div id="div1" style="display: none;">
			<div class="grid_4 lable-right">
				<label class="form-lbl">会议地点：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchWorkingRecordVo.proceedSite" id="workingRecordProceedSite" class='form-txt {maxlength:50,messages:{maxlength:$.format("会议地点不能多于{0}个字符")}}'/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">会议主题：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchWorkingRecordVo.subject" id="workingRecordSubject" class='form-txt {maxlength:200,messages:{maxlength:$.format("会议主题不能多于{0}个字符")}}'/>
			</div>
		</div>
		<div id="div2" style="display: none;">
			<div class="grid_4 lable-right">
				<label class="form-lbl">发文单位：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchWorkingRecordVo.workingUnit" id="workingRecordWorkingUnit" class='form-txt {maxlength:200,messages:{maxlength:$.format("发文单位不能多于{0}个字符")}}'/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">文件主题：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchWorkingRecordVo.subject" id="workingRecordSubject" class='form-txt {maxlength:200,messages:{maxlength:$.format("文件主题不能多于{0}个字符")}}'/>
			</div>
		</div>
		<div id="div3" style="display: none;">
			<div class="grid_4 lable-right">
				<label class="form-lbl">活动地点：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchWorkingRecordVo.proceedSite" id="workingRecordProceedSite" class='form-txt {maxlength:50,messages:{maxlength:$.format("活动地点不能多于{0}个字符")}}'/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">活动主题：</label>
			</div>
			<div class="grid_20">
				<input type="text" name="searchWorkingRecordVo.subject" id="workingRecordSubject" class='form-txt {maxlength:200,messages:{maxlength:$.format("文件主题不能多于{0}个字符")}}'/>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#workingRecordType").change(function(){
		var index=$("#workingRecordType").get(0).selectedIndex;
		var div=$("#div"+index).clone();
		$("#includeDiv").empty();
		$("#includeDiv").append(div);
		$("form div").show();
	});
	$('#createDateBegin').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#createDateEnd").datepicker("option", "minDate",date);
			}
		}
	});
	$('#createDateEnd').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate : '+0d',
		onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				$("#createDateBegin").datepicker("option", "minDate",date);
			}
		}
	});
});
</script>