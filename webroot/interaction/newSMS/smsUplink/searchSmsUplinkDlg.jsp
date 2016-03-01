<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" class="container container_24">
		<input type="hidden" id="searchSubmit" value=""/>
		<form id="maintainForm" method="post" action="javascript:(0);">
		 	<div class="grid_5 lable-right">
				<label class="form-lbl">接收者姓名：</label>
		 	</div>
			<div class="grid_7">
				<input type="text" name="searchSmsUplinkVo.receiverName" class="form-txt" />
		 	</div>
		 	<div class="grid_5 lable-right">
				<label class="form-lbl">接收者手机：</label>
		 	</div>
			<div class="grid_7">
				<input type="text" name="searchSmsUplinkVo.receiverMobile" maxlength="11" class="form-txt" />
		 	</div>
			
	 		<div class='clearLine'>&nbsp;</div>
	 		
			<div class="grid_5 lable-right">
				<label class="form-lbl">查获日期 ：</label>
			</div>
			<div class="grid_3">
				<input type="text" id="minSendTime" name="searchSmsUplinkVo.minSendTime" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_1" align="center">—</div>
			<div class="grid_3">
				<input type="text" id="maxSendTime" name="searchSmsUplinkVo.maxSendTime" readonly="readonly" style="width:92.6%" class="form-txt" />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">发送状态：</label>
	 		</div>
	 		<div class="grid_7">
	 			<select id="status" name="searchSmsUplinkVo.status" class="form-select">
	 				<option value=""></option>
					<option value="1">待处理</option>
					<option value="2">已处理</option>
					<option value="3">待发送</option>
					<option value="4">已发送</option>
					<option value="5">发送失败</option>
				</select>
	 		</div>
	 		<div class='clearLine'>&nbsp;</div>
		</form>
		
</div>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			var data=$(form).serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
				dataJson[data[i].name]=data[i].value;
			}
			$("#smsUplinkList").setGridParam({
				url:'${path}/smsUplinkManage/findSmsUplinkPagerBySearchVo.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#smsUplinkList").setPostData(dataJson);
			$("#smsUplinkList").trigger("reloadGrid");
			$("#smsUplinkDialog").dialog("close");
		}
	});
	
	$('#minSendTime').datePicker({
		yearRange:'1930:2060',
		maxDate:'+0d'
	});
	$('#maxSendTime').datePicker({
		yearRange:'1930:2060',
		maxDate:'+0d'
	});

});

</script>


