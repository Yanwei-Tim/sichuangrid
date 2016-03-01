<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<div class="grid_4 lable-left">
		<label class="form-lbl">收件人：</label>
	</div>
	<div class="grid_20 lable-left">
		<label> ${smsSendBox.receiver}</label>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_24"><hr></div>
	<div class="grid_24">
		<label> ${smsSendBox.smsContent}</label>
	</div>
</div>