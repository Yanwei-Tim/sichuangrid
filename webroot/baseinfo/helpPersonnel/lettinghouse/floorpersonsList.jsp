<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>

<input type="hidden" id="domainId" name="personInCharges.placeId" value="<s:property value="#parameters.domainId"/>"/>
<input type="hidden" id="keyType" name="personInCharges.placeType" value="<s:property value="#parameters.keyType"/>"/>

<div class="content">
	<div class="ui-cornRr-all" id="nav">
			<a id="addFloor" href="javascript:void(0)" >新增</span></a>
			<a id="updateFloor" href="javascript:void(0)"><span>修改</span></a>
			<a id="viewFloor" href="javascript:void(0)"><span>查看</span></a>
		   <a id="deleteFloor" href="javascript:void(0)"><span>删除</span></a>
	</div>
</div>

<div style="width: 100%;">
	<table id="floorpersonList"></table>
	<div id="floorpersonListPager"></div>
</div>

<script type="text/javascript">
	var keyType=$("#keyType").val();
	var domainId=$("#domainId").val();

	function loadList(){
		$("#floorpersonList").setGridParam({
			url:'${path}/baseinfo/floorpersons/findFloorperson.action',
			datatype: "json",
			page:1
		});
		$("#floorpersonList").setPostData({
			"placeType":keyType,
			"placeId":domainId
		});
		$("#floorpersonList").trigger("reloadGrid");
	}
	$(document).ready(function(){
		if("view" == $("#mode").val()){
			$("#addFloor").buttonDisable();
		}
		$("#placeTypeNames").val($("#placeTypeName").val());
		$("#floorpersonList").jqGridFunction({
			datatype: "local",
			colModel:[
				{name:"id", index:"id", hidden:true },
				{name:"floorpersonsTime", sortable:false,index:"floorpersonsTime", label: '巡查时间',width:100},
				{name:"address", sortable:false, label:'巡查地点', width:100},
				{name:"personInChargeName", sortable:false, label: '巡查人员',width:200},
				{name:"events", sortable:false, label: '巡查事件'}
			],
			loadComplete: afterFloorLoad,
			width: <s:property value="#parameters.width"/>,
			height: <s:property value="#parameters.height"/>,
			ondblClickRow: viewlettinghouseHelp,
			onSelectRow:selectFloorRow,
			showColModelButton:false
		});
		loadList();
		$("#addFloor").click(function(){
			var domainId = $("#domainId").val();
			if(domainId==null || domainId==""){
				domainId=0;
			}
			$("#floorpersonDialog").createDialog({
				width: 400,
				height: 400,
				title:'新增巡场情况',
				url:'${path}/baseinfo/floorpersons/dispatch.action?placeId='+domainId+'&placeType=<s:property value="#parameters.keyType"/>&mode=add',
				buttons: {
					"保存" : function(event){
				     $("#floorpersonForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#updateFloor").click(function(){
			var selectedId = $("#floorpersonList").getGridParam("selrow");
			if(selectedId==null){
			  return;
			}
			$("#floorpersonDialog").createDialog({
				width: 400,
				height: 400,
				title:'修改巡查情况',
				url:'${path}/baseinfo/floorpersons/dispatch.action?floorperson.id='+selectedId+'&mode=edit',
				buttons: {
					"保存" : function(event){
				      $("#floorpersonForm").submit();
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});

		$("#viewFloor").click(function(){
			viewlettinghouseHelp();
		});

		$("#deleteFloor").click(function(){
			var selectedId = $("#floorpersonList").getGridParam("selrow");
			if(selectedId == null){
				return;
			}
			$.confirm({
				title:"确认删除",
				message:"该巡场情况信息删除后，将无法恢复,您确认删除该巡场情况吗?",
				width: 300,
				okFunc:deleteFloor
			});
		});


	});

	function viewlettinghouseHelp(){
		var selectedId = $("#floorpersonList").getGridParam("selrow");
		if(selectedId==null){
		  return;
		}
		$("#floorpersonDialog").createDialog({
			width: 400,
			height: 400,
			title:'查看巡查信息',
			url:'${path}/baseinfo/floorpersons/dispatch.action?floorperson.id='+selectedId+'&mode=view',
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function deleteFloor(){
		var selectedId = $("#floorpersonList").getGridParam("selrow");
		if(selectedId==null){
			 return;
		}
		$.ajax({
			url:"${path}/baseinfo/floorpersons/deleteFloorpersonById.action",
			data:{
				"floorperson.id":selectedId
			},
			success:function(responseData){
				if(responseData){
					$("#floorpersonList").delRowData(selectedId);
					$.messageBox({message:"巡场情况删除成功！"});
					disableFloorButton();
				}
			}
		});
	}

	function afterFloorLoad(){
		disableFloorButton();
	}

	function selectFloorRow(){
		if("view" == $("#mode").val()){
			$("#addFloor").buttonDisable();
			$("#updateFloor").buttonDisable();
			$("#deleteFloor").buttonDisable();
		}else{
			$("#updateFloor").buttonEnable();
			$("#viewFloor").buttonEnable();
			$("#deleteFloor").buttonEnable();
		}
		$("#viewFloor").buttonEnable();
	}

	function disableFloorButton(){
		$("#updateFloor").buttonDisable();
		$("#viewFloor").buttonDisable();
		$("#deleteFloor").buttonDisable();
	}

</script>