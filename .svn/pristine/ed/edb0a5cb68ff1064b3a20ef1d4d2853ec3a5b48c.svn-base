<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<div class="grid_4">
		<label class="form-lbl">来源号码：</label>
	</div>
	<div class="grid_8 lable-left">
		<label> ${smsReceivedBox.sourceMobile}</label>
	</div>
	<div class="grid_4">
		<label class="form-lbl">接收时间：</label>
	</div>
	<div class="grid_8">
		<label><s:date name="smsReceivedBox.receiverTime" format="yyyy-MM-dd HH:mm:ss"/></label>
	</div>
	<div class='clearLine'></div>
	<div class="grid_4">
		<label class="form-lbl">内容：</label>
	</div>
	<div class="grid_20"></div>
	<div style="width:580px;height:100px; overflow-y:scroll; border:1px solid;">${smsReceivedBox.smsContent}</div>
	<div class='clearLine'></div>
	<hr>
	<div class='clearLine'></div>
	<div class="grid_4">
		<label class="form-lbl">处理情况：</label>
	</div>
	<div class="grid_20"></div>
	<div style="width:580px;height:100px; overflow-y:scroll; border:1px solid;">${smsReceivedBox.disposition}</div>
</div>