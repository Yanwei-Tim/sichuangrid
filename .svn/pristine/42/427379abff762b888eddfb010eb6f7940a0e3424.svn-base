<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

	<!-- 单位 -->
	<div id="unit">
		<div class="ui-corner-all" id="nav">
			<a id="addUnit" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>绑定</span> </a>
			<a id="deleteUnit"	href="javascript:void(0)"><span><strong	class="ui-ico-sc"></strong>解除绑定</span> </a>
		</div>
		<h3 style="height:25px;line-height:25px;">已绑定单位:</h3>
		<div style="width: 100%;">
			<table id="uniteInfoList">
			</table>
			<div id="uniteInfoListPager"></div>
		</div>
	</div>

<script type="text/javascript">
	var dialogWidth = 470;
	var dialogHeight = 240;
	var orgId = "<s:property value='#parameters.orgId'/>";
	var buildingid = "<s:property value='#parameters.buildingid'/>";
	var pointX = "<s:property value='#parameters.centerx'/>";
	var pointY = "<s:property value='#parameters.centery'/>";
	$(document).ready(function() {
		$("#addUnit").click(function(event) {
			bindingHouse();
		});

		initGrid();
		getActualCompanyList();

	})

	function bindingHouse() {
		$("#bindhouseDialog").createDialog({
			width : 600,
			height : 360,
			title : "单位绑定",
			url : PATH
					+ "/baseinfo/buildDatas/maintainActualCompanyManageDlg.jsp?orgId="
					+ orgId + "&buildingId=" + buildingid,
			buttons : {
				"绑定并关闭" : function(event) {
					jQuery("#maintainBindingHouseForm")
							.submit();
					//$(this).dialog("close");
					
				},
				"关闭" : function() {
					jQuery(this).dialog("close");
				}
			}
		});
	}

	$("#deleteUnit").click(function(){
		var selectedIds = $("#uniteInfoList").jqGrid(
				"getGridParam", "selarrrow");
		if (selectedIds.length == 0) {
			$.messageBox({level:"warn",message:"请选择要解除绑定的数据！"});
			return;
		}
		var idArr = selectedIds + ',';
		$.confirm({
			title : "确认解除",
			message : "您确定要解除此住房信息的楼宇绑定吗?",
			okFunc : function() {
				$.ajax({
					url : "${path}/builddatasManage/unboundActualCompany.action?idArr="+ idArr,
					success : function(data) {
						if (data == true) {
							$("#uniteInfoList").delRowData(selectedIds);
							$.messageBox({message : "解除楼宇住房绑定成功!"});
							$("#uniteInfoList").trigger("reloadGrid");
						} else {
							$.messageBox({message : data});
						}
					}
				});
			}
		});
	});

	function initGrid() {
		$("#uniteInfoList").jqGridFunction({
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
			height : 135
		});
	}

	function getActualCompanyList() {
		var address = '';
		$("#uniteInfoList").setGridParam({
			url : '${path}/builddatasManage/findUnitInfosByBuildingIdForPage.action',
			datatype : "json",
			page : 1
		});

		$("#uniteInfoList").setPostData({
			"orgId" : orgId,
			"builddatas.id" : buildingid
		});
		$("#uniteInfoList").trigger("reloadGrid");
	}
	
</script>