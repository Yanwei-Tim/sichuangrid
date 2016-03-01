<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="content">
	<div class="ui-corner-all" id="nav" style="left:5px"></div>
	<div class="content" >
		<div style="width:99%;" id="grid_content" >
			<table id="selectContactsList"></table>
			<div id="selectContactsListPager"></div>
		</div>
		<div id="searchDialog"></div>
		<div id="myGroupContacterMaintanceDialog"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#selectContactsList").jqGridFunction({
		url:"${path}/contact/myGroupManage/findMyGroupHasContacters.action",
		datatype: "json",
		postData:{
			"myGroup.id":'<s:property value="#parameters.id" />',
			"myGroup.belongClass":""
		},
		colNames:['id','姓名','联系手机','备注'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'name',sortable:false,width:200},
	  		{name:"mobileNumber",sortable:false,width:200},
	        {name:'remark',sortable:false,width:400}
		],
		height:260,
		multiselect:true,
		showColModelButton:false,
		scrollrow:true,
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
