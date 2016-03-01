<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<div class="container container_24">
<form method="post" id="searchform">
	<div class="grid_5 lable-right">
		<label class="form-lbl">辖区范围：</label>
	</div>
	<div class="grid_7">
		<input type="text" id="organizationId"  name="organizationName" value="" class="form-txt" />
		<input type="hidden" id="excelImportLogVO.organizationId.id"  name="excelImportLogVO.organizationId.id" value="" class="form-txt" />
    </div>
	<div class="grid_1"></div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">操作账号：</label>
	</div>
	<div class="grid_7">
	<input type="text" id="operateName" name="excelImportLogVO.operateName" class="form-txt" maxlength="15" value="" /></div>	
	
	<div class="clearLine">&nbsp;</div>
   	<div class="grid_5 lable-right">
   			<label class="form-lb1">开始时间：</label>
   	</div>
   	<div class="grid_7 form-left">
		<input type="text" id="startTime"  name="excelImportLogVO.startTime" class="form-txt" readonly />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">结束时间：</label>
	</div>
	<div class="grid_7 form-left">
		<input type="text" id="endTime" name="excelImportLogVO.endTime" class="form-txt" readonly/>
	</div>
<div class="clearLine">&nbsp;</div>

<div id="thingsType-select">
	<div class="grid_1"></div>
	<div class="grid_4 lable-right"><label class="form-lbl">有无错误分析表：</label>
	</div>
	<div class="grisd_7">
		<select class="form-select" style="width:180px" id="statusSelect" name="excelImportLogVO.status" >
			<option value="">请选择</option>
			<option value="1">有</option>
			<option value="0">无</option>
		</select>
	</div>
</div>
	
</form>
</div>

<Script type="text/javascript">
$(document).ready(function(){
	$('#startTime').datePicker({
		yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
	       maxDate:'+0d',
	       onSelect : function(dateText, inst) {
				if (dateText != null && dateText != '') {
					var dateUnit = dateText.split('-');
					var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
					$("#endTime").datepicker("option", "minDate", date);
				}
			}
	});

	$('#endTime').datePicker({
		yearRange: '1900:2030',
			dateFormat: 'yy-mm-dd',
	       maxDate:'+0d',
	    onSelect : function(dateText, inst) {
			if (dateText != null && dateText != '') {
				var dateUnit = dateText.split('-');
				var date = new Date(dateUnit[0], dateUnit[1] - 1, dateUnit[2]);
				$("#startTime").datepicker("option", "maxDate", date);
			}
		}
	});
	
	var tree=$("#organizationId").treeSelect({
		inputName:"excelImportLogVO.organizationId.id",
		inputCodeName:"serviceTeamMemberVo.org.orgInternalCode",
		rootId:USER_ORG_ID
	});
});






</Script>
