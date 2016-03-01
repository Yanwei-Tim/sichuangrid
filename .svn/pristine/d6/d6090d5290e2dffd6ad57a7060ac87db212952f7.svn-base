<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container container_24">
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">接收者手机：</label>
	 	</div>
	 	<div class="grid_7"   >
			<input type="text" value="${smsUplink.receiverMobile}" readonly class="form-txt">
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">发送状态：</label>
	 	</div>
	 	<div class="grid_7" >
			<input type="text" id="smsSubStatus" value="${smsUplink.status}" readonly class="form-txt">
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">发送者姓名：</label>
	 	</div>
	 	<div class="grid_7"  >
			<input type="text" value="${smsUplink.senderName}" readonly class="form-txt">
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">发送时间：</label>
	 	</div>
	 	<div class="grid_7" >
			<input type="text" value='<fmt:formatDate value="${smsUplink.sendTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />' readonly class="form-txt">
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">发送内容：</label>
	 	</div>
		<div class="grid_20" style="height:30px;width:78.8%" >
			<textarea rows="6" readonly class="form-txt" >${smsUplink.content}</textarea>
		</div>
</div>

<script type="text/javascript">

$(function(){
	var status = $("#smsSubStatus").val();
	if(status == 1){
		$("#smsSubStatus").val("待处理");
	}else if(status == 2){
		$("#smsSubStatus").val("处理中");
	}else if(status == 3){
		$("#smsSubStatus").val("待发送");
	}else if(status == 4){
		$("#smsSubStatus").val("发送成功");
	}else if(status == 5){
		$("#smsSubStatus").val("发送失败");
	}else{
		$("#smsSubStatus").val("");
	}
});

</script>


