<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
		<input type="text" value="请输入负责人姓名或单位名称" id="searchText" maxlength="18"
			class="searchtxt"
			onfocus="if(this.value == '请输入负责人姓名或单位名称') this.value = '';"
			onblur="if(this.value == '') this.value = '请输入负责人姓名或单位名称';" />
		<button id="refreshOrgTree1"
			class="ui-icon ui-icon-refresh searchbtnico"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="actualCompanySearchButton"><span><strong
				class="search"></strong>搜索</span> </a>
	<!-- 	<a href="javascript:;" id="addActualCompanyButton" ><span><strong class="search"></strong>新增</span></a>  -->
	</div>
</div>
<div class="content">
	<div style="width: 100%;">
		<!--<table id="housePropertyList">
		</table>
		<div id="housePropertyListPager"></div>-->
		<table id="actualCompanyList">
		</table>
		<div id="actualCompanyListPager">
	</div>
</div>
<div id="actualCompanyMaintanceDialog"></div>
<form action="" id="maintainBindingHouseForm">
<pop:token />
</form>


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
			$("#searchText").val("请输入负责人姓名或单位名称");
		});
	})

	function initButtons() {

		$("#actualCompanySearchButton").click(function(event) {
			searchActualCompany();
		});
		$("#addActualCompanyButton").click(function(event) {
			addActualCompany();
		});
	}
	
	function addActualCompany(){
				$("#actualCompanyMaintanceDialog").createFrameDialog(
				{
					model :"add",
					title:"新增实有 信息",
					width: 800,
					height: 640,
					data:[
						{title:'实有单位信息',url:'${path}/baseinfo/actualCompanyManage/dispatchOperate.action?dailogName=actualCompanyMaintanceDialog&mode=add&orgId='+orgId,buttons:{save:true,saveContinue:true}}
					]
				}
			);
			
	}

	function searchActualCompany() {
		var queryStr = $("#searchText").val();
		if (null != queryStr && queryStr != '请输入房主姓名或常住地址') {
			$("#actualCompanyList")
					.setGridParam(
							{
								url : '${path}/builddatasManage/getUnBoundedActualCompanyList.action',
								datatype : "json",
								page : 1
							});
			$("#actualCompanyList")
					.setPostData(
							{
								"organization.id" : orgId,
								"queryStr" : queryStr
							});
			$("#actualCompanyList").trigger("reloadGrid");
		} else {
			getbindHouseList();
		}
	}

	function initGrid() {
		$("#actualCompanyList").jqGridFunction({
			datatype : "local",
			colNames : [ 'id','负责人', '单位名称', '单位地址' ],
			colModel : [
				{name : "id",index : "id",hidden : true,frozen : true},
				{name : "corporateRepresentative",index : 'corporateRepresentative',sortable : true,width : 100 ,frozen : true},
				{name : 'companyName',index : "companyName",width : 100,frozen : true},
				{name : 'companyAddress',index : "companyAddress",width : 305}
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
								var selectedIds = $("#actualCompanyList")
										.jqGrid("getGridParam", "selarrrow");
								var buildingId = "<s:property value='#parameters.buildingId'/>";
								if (selectedIds.length > 0) {
									for ( var i = 0; i < selectedIds.length; i++) {
										var row = $("#actualCompanyList")
												.getRowData(selectedIds[i]);
									}
									$.ajax({
												async : false,
												datatype : "json",
												method : "post",
												url : "${path}/builddatasManage/updateActualCompanyInfo.action",
												data : {
													"idArr" : selectedIds + '',
 													"builddatas.id" : buildingId
												},
												success : function(data) {
													$.messageBox({
														message : "绑定住房信息成功!"
													});
													getActualCompanyList(buildingId);
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
		$("#actualCompanyList")
				.setGridParam(
						{
							url : '${path}/builddatasManage/getUnBoundedActualCompanyList.action',
							datatype : "json",
							page : 1
						});
		$("#actualCompanyList")
				.setPostData(
						{
							"organization.id" : orgId
							//"type" : '<s:property value="@com.tianque.gis.util.ModuleMap@PLACE_HOUSE_INFO.getModuleType()"/>'
						});
		$("#actualCompanyList").trigger("reloadGrid");
	}

	
</script>