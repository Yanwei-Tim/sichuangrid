<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
		<input type="text" value="请输入房主姓名或常住地址" id="searchText" maxlength="18"
			class="searchtxt"
			onfocus="if(this.value == '请输入房主姓名或常住地址') this.value = '';"
			onblur="if(this.value == '') this.value = '请输入房主姓名或常住地址';" />
		<button id="refreshOrgTree1"
			class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="houseSearchButton"><span><strong
				class="search"></strong>搜索</span> </a>
	<!-- 	<a href="javascript:;" id="addHouseButton" ><span><strong class="search"></strong>新增</span></a> -->
	</div>
</div>
<div class="content">
	<div style="width: 100%;">
		<!--<table id="housePropertyList">
		</table>
		<div id="housePropertyListPager"></div>-->
		<table id="actualHouseList">
		</table>
		<div id="actualHouseListPager">
	</div>
</div>
<div id="actualHouseMaintanceDialog"></div>
<form id="maintainBindingHouseForm" method="post" action="">
<pop:token /></form>


<script type="text/javascript">
	var dialogWidth = 470;
	var dialogHeight = 240;
	var orgId = "<s:property value='#parameters.orgId'/>";

	$(document).ready(function() {
		initButtons();
		initGrid();
		getbindHouseList();
		formValid();
		$("#refreshOrgTree1").click(function(){
			$("#searchText").val("请输入房主姓名或常住地址");
		});
	})

	function initButtons() {

		$("#houseSearchButton").click(function(event) {
			searchHouse();
		});
		$("#addHouseButton").click(function(event) {
			addHouse();
		});
	}
	
	function addHouse(){
				$("#actualHouseMaintanceDialog").createFrameDialog(
				{
					model :"add",
					title:"新增房屋信息",
					width: 800,
					height: 640,
					data:[
						{title:'房屋信息',url:'${path}/baseinfo/actualHouseManage/dispatchOperate.action?dailogName=actualHouseMaintanceDialog&mode=add&orgId='+orgId,buttons:{save:true,saveContinue:true}}
					]
				}
			);
			
	}

	function searchHouse() {
		var queryStr = $("#searchText").val();
		if (null != queryStr && queryStr != '请输入房主姓名或常住地址') {
			$("#actualHouseList")
					.setGridParam(
							{
								url : '${path}/builddatasManage/getUnBoundedLocationList.action',
								datatype : "json",
								page : 1
							});
			$("#actualHouseList")
					.setPostData(
							{
								"organization.id" : orgId,
								"queryStr" : queryStr
							});
			$("#actualHouseList").trigger("reloadGrid");
		} else {
			getbindHouseList();
		}
	}

	function initGrid() {
		$("#actualHouseList").jqGridFunction({
			datatype : "local",
			colNames : [ 'id', '住房编号', '代表人/业主', '常住地址' ],
			colModel : [ {
				name : "id",
				index : "id",
				hidden : true,
				frozen : true
			}, {
				name : 'houseCode',
				index : "houseCode",
				width : 100,
				frozen : true
			},{name : 'houseOwner',index : "houseOwner",width : 110,frozen : true}, {
				name : "address",
				index : 'address',
				sortable : false,
				width : 305
			} ],
			multiselect : true,
			showColModelButton : false,
			width : 560,
			height : 145
		});
	}

	function formValid() {
		$("#maintainBindingHouseForm").formValidate(
						{
							promptPosition : "bottomLeft",
							submitHandler : function(form) {
								var selectedIds = $("#actualHouseList")
										.jqGrid("getGridParam", "selarrrow");
								var buildingId = "<s:property value='#parameters.buildingId'/>";
								if (selectedIds.length > 0) {
									for ( var i = 0; i < selectedIds.length; i++) {
										var row = $("#actualHouseList")
												.getRowData(selectedIds[i]);
									}
									$.ajax({
												async : false,
												datatype : "json",
												method : "post",
												url : "${path}/builddatasManage/updateHouseInfo.action",
												data : {
													"idArr" : selectedIds + '',
 													"builddatas.id" : buildingId
												},
												success : function(data) {
													$.messageBox({
														message : "绑定住房信息成功!"
													});
													getHouseList(buildingId);
													$("#bindhouseDialog").dialog("close");
												},
												error : function(
														XMLHttpRequest,
														textStatus, errorThrown) {
													alert("提交错误");
												}
											});
								} else {
									$.messageBox({level:"warn",message:"请选择您要绑定的数据！"});
								}
							},
							rules : {},
							messages : {}
						});
	}

	function getbindHouseList() {
		var address = '';
		$("#actualHouseList")
				.setGridParam(
						{
							url : '${path}/builddatasManage/getUnBoundedLocationList.action',
							datatype : "json",
							page : 1
						});
		$("#actualHouseList")
				.setPostData(
						{
							"organization.id" : orgId
							//"type" : '<s:property value="@com.tianque.gis.util.ModuleMap@PLACE_HOUSE_INFO.getModuleType()"/>'
						});
		$("#actualHouseList").trigger("reloadGrid");
	}

	
</script>