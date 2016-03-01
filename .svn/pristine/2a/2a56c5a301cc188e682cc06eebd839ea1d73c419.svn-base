<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
		<input type="text" value="请输入重点场所名称或地址" id="searchText" maxlength="18"
			class="searchtxt"
			onfocus="if(this.value == '请输入重点场所名称或地址') this.value = '';"
			onblur="if(this.value == '') this.value = '请输入重点场所名称或地址';" />
		<button id="refreshOrgTree1"
			class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="keyPlaceSearchButton"><span><strong
				class="search"></strong>搜索</span> </a>
	<!--<a href="javascript:;" id="addkeyPlaceButton" ><span><strong class="search"></strong>新增</span></a>  -->	
	</div>
</div>
<div class="content">
	<div style="width: 100%;">
		<!--<table id="housePropertyList">
		</table>
		<div id="housePropertyListPager"></div>-->
		<table id="keyPlaceList">
		</table>
		<div id="keyPlaceListPager">
	</div>
</div>
<div id="keyPlaceMaintanceDialog"></div>
<form id="maintainBindingHouseForm" method="post" action=""><pop:token /></form>


<script type="text/javascript">
	var dialogWidth = 470;
	var dialogHeight = 240;
	var orgId = "<s:property value='#parameters.orgId'/>";

	$(document).ready(function() {
		initButtons();
		initGrid();
		getbindkeyPlaceList();
		formValid();
		$("#refreshOrgTree1").click(function(){
			$("#searchText").val("请输入重点场所名称或地址");
		});
	})

	function initButtons() {

		$("#keyPlaceSearchButton").click(function(event) {
			searchkeyPlace();
		});
		$("#addkeyPlaceButton").click(function(event) {
			addkeyPlace();
		});
	}
	
	function addkeyPlace(){
				$("#keyPlaceMaintanceDialog").createFrameDialog(
				{
					model :"add",
					title:"新增房屋信息",
					width: 800,
					height: 640,
					data:[
						{title:'房屋信息',url:'${path}/baseinfo/keyPlaceManage/dispatchOperate.action?dailogName=keyPlaceMaintanceDialog&mode=add&orgId='+orgId,buttons:{save:true,saveContinue:true}}
					]
				}
			);
			
	}

	function searchkeyPlace() {
		var queryStr = $("#searchText").val();
		if (null != queryStr && queryStr != '请输入房主姓名或常住地址') {
			$("#keyPlaceList")
					.setGridParam(
							{
								url : '${path}/builddatasManage/findUnboundKeyPlace.action',
								datatype : "json",
								page : 1
							});
			$("#keyPlaceList")
					.setPostData(
							{
								"organization.id" : orgId,
								"queryStr" : queryStr
							});
			$("#keyPlaceList").trigger("reloadGrid");
		} else {
			getbindkeyPlaceList();
		}
	}

	function initGrid() {
		$("#keyPlaceList").jqGridFunction({
			datatype : "local",
			colNames : [ 'id_key', '名称', '类型', '场所地址' ],
			colModel : [
				{name : "id_key",index : "id_key",hidden : true,frozen : true},
				{name : 'name',index : "name",width : 100,frozen : true},
				{name : 'type',index : "type",width : 110,frozen : true},
				{name : "address",index : 'address',sortable : false,width : 305}
			],
			multiselect : true,
			showColModelButton : false,
			width : 560,
			height : 145
		});
	}

	function formValid() {
		$("#maintainBindingHouseForm")
				.formValidate(
						{
							promptPosition : "bottomLeft",
							submitHandler : function(form) {
								var selectedIds = $("#keyPlaceList")
										.jqGrid("getGridParam", "selarrrow");
								var buildingId = "<s:property value='#parameters.buildingId'/>";
								if (selectedIds.length > 0) {
									for ( var i = 0; i < selectedIds.length; i++) {
										var row = $("#keyPlaceList")
												.getRowData(selectedIds[i]);
									}
									$.ajax({
										async : false,
										datatype : "json",
										method : "post",
										url : "${path}/builddatasManage/boundKeyPlace.action",
										data : {
											"idArr" : selectedIds + '',
												"builddatas.id" : buildingId
										},
										success : function(data) {
											$.messageBox({
												message : "绑定住房信息成功!"
											});
											getPlaceList(buildingId);
											$("#bindkeyPlaceDialog").dialog("close");
										},
										error : function(
												XMLHttpRequest,
												textStatus, errorThrown) {
											alert("提交错误");
										}
									});
								} else {
									$.messageBox({level:"warn",message:"请选择您要绑定的数据！"});
									
									return false;
								}
							},
							rules : {},
							messages : {}
						});
	}

	function getbindkeyPlaceList() {
		var address = '';
		$("#keyPlaceList")
				.setGridParam(
						{
							url : '${path}/builddatasManage/findUnboundKeyPlace.action',
							datatype : "json",
							page : 1
						});
		$("#keyPlaceList")
				.setPostData(
						{
							"organization.id" : orgId
							//"type" : '<s:property value="@com.tianque.gis.util.ModuleMap@PLACE_HOUSE_INFO.getModuleType()"/>'
						});
		$("#keyPlaceList").trigger("reloadGrid");
	}

	
</script>