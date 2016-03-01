<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" class="container container_24">
	
	<form id="searchsmstrasForm" method="post" action="javasript:void(0);">
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">短信来源：</label>
	 	</div>
		<div class="grid_7">
			<select name="searchSmstrashVo.fromType" class="form-select">
				<option value="">请选择</option>
				<option value="1">发件箱</option>
				<option value="2">收件箱</option>
			</select>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">号码：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="searchSmstrashVo.mobile" maxlength="20" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">起始时间：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" readonly id="smstrasMinTime" name="searchSmstrashVo.minTime" maxlength="20" class="form-txt"/>
		</div>		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">结束时间：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" readonly id="smstrasMaxTime" name="searchSmstrashVo.maxTime" maxlength="30" class="form-txt"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#searchsmstrasForm").formValidate({
		submitHandler: function(form) {
        	var data=$(form).serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
				dataJson[data[i].name]=data[i].value;
			}
			$("#smstrashList").setGridParam({
				url:'${path}/smstrashManage/findSmstrashPagerBySearchVo.action',
				datatype: "json",
				page:1
			});
			$("#smstrashList").setPostData(dataJson);
			$("#smstrashList").trigger("reloadGrid");
		},
		rules:{},
		messages:{}
	});
	$('#smstrasMinTime').datePicker({
		yearRange:'1930:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'+0y'
	});
	$('#smstrasMaxTime').datePicker({
		yearRange:'1930:2060',
		dateFormat:'yy-mm-dd',
		maxDate:'+30y'
	});
	
});
</script>


