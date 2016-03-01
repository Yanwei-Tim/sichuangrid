<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addPlan">
			<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewPlan">
		    <a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updatePlan">
		    <a id="update"  href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePan">
		    <a id="delete"  href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="examineList"> </table>
		<div id="examineListPager"></div>
	</div>
	<div id="examineMaintanceDialog"></div>
</div>
<script type="text/javascript">

var dialogWidth = 850;
var dialogHeight = 600;

$(document).ready(function(){

	$("#examineList").jqGridFunction({
		url:"${path}/examine/examinePlanManage/findExaminePlans.action",
		datatype: "json",
	   	colModel:[
	        {name:"id", index:"id", hidden:true},
	        {name:"year", index:'name',   label:'年度', width:80 },
	        {name:'title', sortable:false, label:'标题', width:160},
	        {name:'draftOrganization', sortable:false, label:'制定部门', width:160}
		],
		ondblClickRow: viewExaminePlan,
		loadComplete: afterLoad,
		onSelectRow: selectRow
	});

	$("#add").click(function(event){
		$.ajax({
			url:'${path}/examine/examinePlanManage/validateExaminePlanYearHasAvailable.action',
			success:function(data){
				if(data == true){
					$("#examineMaintanceDialog").createDialog({
						width: dialogWidth,
						height: dialogHeight,
						title:'新增考核细则',
						url:'${path}/examine/examinePlanManage/dispatch.action?mode=add',
						buttons: {
					   		"保存" : function(event){
					   			$("#maintainForm").submit();
					   		},
			   				"关闭" : function(){
					        	$(this).dialog("close");
					   		}
						}
					});
				}else{
					$.messageBox({ message:"所有年度都已制定了考核细则!",level: "error"	});
				}
			}
		});

	});

	$("#view").click(function(){
		var selectedId = $("#examineList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		viewExaminePlan(selectedId);
	});

	$("#update").click(function(event){
		var selectedId = $("#examineList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}

		$("#examineMaintanceDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'修改考核细则',
			url:'${path}/examine/examinePlanManage/dispatch.action?mode=edit&examinePlan.id='+selectedId,
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
   				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#delete").click(function(event){
		$.confirm({
			title:"确认删除",
			message:"考核细则信息删除后，将无法恢复，您确认删除该考核细则信息吗？",
			width:400,
			okFunc:deleteExaminePlan
		});
	});
});

function deleteExaminePlan(){
	var selectedId = $("#examineList").getGridParam("selrow");
	if(selectedId==null){
		 return;
	}
	var examine =  $("#examineList").getRowData(selectedId);
	$.ajax({
		url:'${path}/examine/examinePlanManage/deleteExaminePlanById.action?examinePlan.id='+ examine.id,
		success:function(data){
			if(data==true){
				$("#examineList").delRowData(examine.id);
				$.messageBox({ message:"成功删除考核细则信息!"});
				return;
			}
            $.messageBox({
				message:data,
				level: "error"
			});
        }
	});
}

function viewExaminePlan(selectedId){
	$("#examineMaintanceDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看考核细则',
		modal : true,
		url:'${path}/examine/examinePlanManage/dispatch.action?mode=view&examinePlan.id='+selectedId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function afterLoad(){
	disableButtons();
}

function disableButtons(){
	$("#update").buttonDisable();
	$("#delete").buttonDisable();
	$("#view").buttonDisable();
}

function selectRow(){
	$("#update").buttonEnable();
	$("#delete").buttonEnable();
	$("#view").buttonEnable();
}
</script>
