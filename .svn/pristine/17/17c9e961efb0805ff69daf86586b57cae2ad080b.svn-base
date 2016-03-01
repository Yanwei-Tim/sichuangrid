<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" class="container container_24">
		<input type="hidden" id="searchSubmit" value=""/>
		<form id="searchdownlinkForm" method="post" action="javascript:(0);">
		 	<div class="grid_7 lable-right">
				<label class="form-lbl">发送者姓名：</label>
		 	</div>
			<div class="grid_15">
				<input type="text" name="searchSmsdownlinkVo.senderName" class="form-txt" />
		 	</div>
		 	<div class='clearLine'>&nbsp;</div>
			<div class="grid_7 lable-right">
				<label class="form-lbl">发送时间 ：</label>
			</div>
			<div class="grid_7">
				<input type="text" id="minSendTime" name="searchSmsdownlinkVo.minSendTime" readonly="readonly" class="form-txt" />
			</div>
			<div class="grid_1" align="center">—</div>
			<div class="grid_7">
				<input type="text" id="maxSendTime" name="searchSmsdownlinkVo.maxSendTime" readonly="readonly" style="width:92.6%" class="form-txt" />
			</div>
		</form>
		
</div>
<script type="text/javascript">

$(document).ready(function(){
	
	$("#searchdownlinkForm").formValidate({
		submitHandler: function(form) {
			var data=$(form).serializeArray();
			var dataJson={};
			for(i=0;i<data.length;i++){
				dataJson[data[i].name]=data[i].value;
			}
			$("#smsdownlinkList").setGridParam({
				url:'${path}/smsdownlinkManage/findSmsdownlinkPagerBySearchVo.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#smsdownlinkList").setPostData(dataJson);
			$("#smsdownlinkList").trigger("reloadGrid");
			$("#smsdownlinkDialog").dialog("close");
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


