<%@	page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
	<!-- 场所 -->
	<div id="place">
		<div class="ui-corner-all" id="nav">
			<div>
				<a id="addPlace" href="javascript:void(0)"><span><strong
						class="ui-ico-xz"></strong>绑定</span> </a> <a id="deletekeyPlace"
					href="javascript:void(0)"><span><strong
						class="ui-ico-sc"></strong>解除绑定</span> </a>
			</div>
		</div>
		<h3 style="height:25px;line-height:25px;">已绑定场所:</h3>
		<div style="width: 100%;">
			<table id="placeInfoList">
			</table>
			<div id="placeInfoListPager"></div>
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
		$("#addPlace").click(function(event) {
			bindingKeyPlace();
		});

		initGrid();
		getPlaceList();

	})

	function bindingKeyPlace() {
		$("#bindkeyPlaceDialog").createDialog({
			width : 600,
			height : 360,
			title : "场所绑定",
			url : PATH
					+ "/baseinfo/buildDatas/maintainKeyPlaceManageDlg.jsp?orgId="
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

	$("#deletekeyPlace").click(function(){
		var selectedIds = $("#placeInfoList").jqGrid(
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
					url : "${path}/builddatasManage/unboundKeyPlace.action?idArr="+ idArr,
					success : function(data) {
						if (data == true) {
							$("#placeInfoList").delRowData(selectedIds);
							$.messageBox({message : "解除楼宇住房绑定成功!"});
							$("#placeInfoList").trigger("reloadGrid");
						} else {
							$.messageBox({message : data});
						}
					}
				});
			}
		});
	});

	function initGrid() {
		$("#placeInfoList").jqGridFunction({
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
			height : 135
		});
	}

	function getPlaceList() {
		var address = '';
		$("#placeInfoList").setGridParam({
			url : '${path}/builddatasManage/findBoundKeyPlace.action',
			datatype : "json",
			page : 1
		});

		$("#placeInfoList").setPostData({
			"orgId" : orgId,
			"builddatas.id" : buildingid
		});
		$("#placeInfoList").trigger("reloadGrid");
	}
	
</script>