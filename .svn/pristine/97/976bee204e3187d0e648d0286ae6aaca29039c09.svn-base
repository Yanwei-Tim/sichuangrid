<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div style="width: 100%;">
	<table id="targeOrgSelectList"></table>
</div>
<script type="text/javascript">
$(document).ready(function(){
	
	var data = {
		"idCardNo":$("#idCardNo").val(),
		"name":$("#name").val()
	}
	jQuery("#targeOrgSelectList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'idCardNo',index:'idCardNo',sortable:false,label:'身份证号码',width:90},
	   		{name:'name',index:'name',sortable:false, label:'姓名',width:90},
	   		{name:'sex',index:'sex', sortable:false, label:'性别',width:90},
	   		{name:'nativePlaceAddress',index:'nativePlaceAddress', sortable:false, label:'户籍地',width:250}
	   	],
	   	width: 480,
		height: 300,
	    rowNum:-1,
	    pager:false
	});

	function onOrgChanged(){
		$("#targeOrgSelectList").setGridParam({
			url:'${path}/commonPopulation/commonPopulationManage/findResidentPopulationsByCardNoAndName.action',
			datatype: "json",
			page:1
		});
		$("#targeOrgSelectList").setPostData({
			"idCardNo":$("#idCardNo").val(),
			"name":$("#name").val()
	   	});
		$("#targeOrgSelectList").trigger("reloadGrid");
	}
	onOrgChanged();
});
</script>
