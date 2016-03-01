<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div style="height: 100%; overflow: auto; overflow-x: hidden; position: relative;">
	<div class="content" >
		<div class="ui-corner-all" id="nav"></div>
		<div style="width: 96%;margin-left:10px;">
			<table id="selectContactsList"></table>
			<div id="selectContactsListPager"></div>
		</div>
		<div id="myContactMaintanceDialog"></div>

	</div>
</div>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#selectContactsList").jqGridFunction({
		url:"${path}/contact/myContacterManage/findMyContacters.action",
		datatype: "json",
		colNames:['id','姓名','联系手机'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'name',sortable:false,width:150},
	  		{name:"mobileNumber",sortable:false,width:160}
		],
		multiselect:true,
		showColModelButton:false,
		viewrecords: true,
		scrollrow:true,
		height:260,
		loadComplete:function(){
		},
		onSelectAll:function(rowIds,status){
			for(var i=0;i<rowIds.length;i++){
				selectRowData(rowIds[i],status);
			}
		},
		onSelectRow:function(rowId,status){
			selectRowData(rowId,status);
		}
	});

});

</script>
