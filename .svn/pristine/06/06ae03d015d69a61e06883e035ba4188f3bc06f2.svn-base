<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="container_24">
<input type="hidden" id="houseId" value="${houseInfo.id}" />
<input type="hidden" id="orgId" value="${houseInfo.organization.id}" />
<input type="hidden" id="address" value="${houseInfo.address}" />
	<div class='clearLine'></div>
	<div class='clearLine'></div>
	<div class='grid_24'>
		<b>需要合并的房屋信息</b>
	</div>
	 <div class="content">
		<div style="clear: both;"></div>
		<div style="width: 100%;">
			<table id="checkedHouseList"></table>
<!-- 			<div id="checkedHouseListPager"></div> -->
		</div>
<!-- 		<div id="sameMembersDialog"></div> -->
	</div>
	
	<div class='grid_24'>
		<b>房屋地址重复信息</b>
	</div>
	 <div class="content">
		<div style="clear: both;"></div>
		<div style="width: 100%;">
			<table id="sameHouseList"></table>
			<div id="sameHouseListPager"></div>
		</div>
<!-- 		<div id="sameMembersDialog"></div> -->
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="houseUses" domainName="@com.tianque.domain.property.PropertyTypes@HOUSE_USES" />
$.showTxtValue();  
$(document).ready(function(){
// 	alert($("#address").val());
	$("#checkedHouseList").jqGridFunction({
		url:'${path}/baseinfo/actualHouseManage/getHouseInfoById.action?houseInfo.id='+$("#houseId").val(),
		datatype: "json",
		postData: {
        },
		colModel:[
			{name:"id",index:"id",sortable:true,hidden:true},
			{name:"organization.id",index:"organization.id",sortable:false,hidden:true},
			{name:'houseCode',index:'houseCode',label:'房屋编号',width:100,hidden:false,frozen:true},
			{name:'address',index:"address",label:"准确地址",width:200,frozen:true,sortable:true},
			{name:'isRentalHouse',index:'isRentalHouse',label:"是否出租房",width:80,formatter:isRentalHouse,sortable:true,align:"center"},
			{name:'houseUses',index:"houseUses",label:"房屋用途",width:100,frozen:true,sortable:true,formatter:houseUsesFormatter},
			{name:'updateDate',index:'updateDate',label:"数据更新时间",sortable:true,width:200,align:"center"}
		],
		height:100,
	    showColModelButton:false
	});
	
	$("#sameHouseList").jqGridFunction({
		url:'${path}/baseinfo/actualHouseManage/findHouseInfoByAddressAndOrgId.action',
		datatype: "json",
		postData: {
			"houseInfo.address":$("#address").val(),
			"orgId":$("#orgId").val(),
			"houseInfo.id":$("#houseId").val()
// 			orgId:function(){return $("#address").val();}
        },
		colModel:[
			{name:"id",index:"id",sortable:true,hidden:true},
			{name:"organization.id",index:"organization.id",sortable:false,hidden:true},
			{name:'houseCode',index:'houseCode',label:'房屋编号',width:100,hidden:false,frozen:true},
			{name:'address',index:"address",label:"准确地址",width:200,frozen:true,sortable:true},
			{name:'isRentalHouse',index:'isRentalHouse',label:"是否出租房",width:80,formatter:isRentalHouse,sortable:true,align:"center"},
			{name:'houseUses',index:"houseUses",label:"房屋用途",width:100,frozen:true,sortable:true,formatter:houseUsesFormatter},
			{name:'updateDate',index:'updateDate',label:"数据更新时间",sortable:true,width:200,align:"center"}
		],
		height:200,
	    showColModelButton:false,
	    multiselect:true,
		onSelectAll:function(aRowids,status){},
		showColModelButton:false
	});
});

function isRentalHouse(isRentalHouse){
	if(isRentalHouse){
		return "是";
	}
	return "否";
}
</script>