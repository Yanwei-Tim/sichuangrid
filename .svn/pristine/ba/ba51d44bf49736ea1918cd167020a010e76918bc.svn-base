<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
	<!-- 房屋 -->
	<div id="house">
		<div class="ui-corner-all" id="nav">
			<div>
				<a id="addHouse" href="javascript:void(0)"><span><strong
						class="ui-ico-xz"></strong>绑定</span> </a> <a id="deleteHouse"
					href="javascript:void(0)"><span><strong
						class="ui-ico-sc"></strong>解除绑定</span> </a>
			</div>
		</div>
		<h3 style="height:25px;line-height:25px;">已绑定房屋:</h3>
		<div style="width: 100%;">
			<table id="houseInfoList">
			</table>
			<div id="houseInfoListPager"></div>
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
		$("#addHouse").click(function(event) {
			bindingHouse();
		});

		initGrid();
		getHouseList();

	})

	function bindingHouse() {
		$("#bindhouseDialog").createDialog({
			width : 600,
			height : 360,
			title : "住房绑定",
			url : PATH
					+ "/baseinfo/buildDatas/maintainHouseManageDlg.jsp?orgId="
					+ orgId + "&buildingId=" + buildingid,
			buttons : {
				"绑定并关闭" : function(event) {
					$("#maintainBindingHouseForm").submit();
					
				},
				"关闭" : function() {
					jQuery(this).dialog("close");
				}
			}
		});
	}

	$("#deleteHouse").click(function(){
		var selectedIds = $("#houseInfoList").jqGrid(
				"getGridParam", "selarrrow");
		if (selectedIds.length == 0) {
			$.messageBox({level:"warn",message:"请选择要解除绑定的数据！"});
			return;
		}
		var idArr = selectedIds + ',';
		var encryptIds=deleteOperatorByEncrypt("houseInfo",selectedIds,"encryptId");
		$.confirm({
			title : "确认解除",
			message : "您确定要解除此住房信息的楼宇绑定吗?",
			okFunc : function() {
				$.ajax({
					url : "${path}/builddatasManage/updateHouseInfoByEncrypt.action?idArr="+ encryptIds,
					success : function(data) {
						if (data == true) {
							$("#houseInfoList").delRowData(selectedIds);
							$.messageBox({message : "解除楼宇住房绑定成功!"});
							getHouseList();
						} else {
							$.messageBox({message : data});
						}
					}
				});
			}
		});
	});

	function initGrid() {
		$("#houseInfoList").jqGridFunction({
			datatype : "local",
			colNames : [ 'id','encryptId', '住房编号', '代表人/业主', '常住地址' ],
			colModel : [
				{name : "id",index : "id",hidden : true,frozen : true},
				{name : "encryptId",index : "encryptId",hidden : true,frozen : true},
				{name : 'houseCode',index : "houseCode",width : 100,frozen : true},
				{name : 'houseOwner',index : "houseOwner",width : 110,frozen : true},
				{name : "address",index : 'address',sortable : false,width : 305}
			],
			multiselect : true,
			showColModelButton : false,
			width : 560,
			height : 135
		});
	}

	function getHouseList() {
		var address = '';
		$("#houseInfoList").setGridParam({
			url : '${path}/builddatasManage/houseInfoListByBuildingId.action',
			datatype : "json",
			page : 1
		});
		$("#houseInfoList").setPostData({
			"orgId" : orgId,
			"builddatas.id" : buildingid
		});
		$("#houseInfoList").trigger("reloadGrid");
	}
</script>