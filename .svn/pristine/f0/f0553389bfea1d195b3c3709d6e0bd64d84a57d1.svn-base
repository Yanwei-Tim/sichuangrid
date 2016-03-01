<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div style="margin-left:5px;">
	<div class="content" >
		<div class="ui-corner-all" id="nav" >
			<select  id="selectedBelongClass" class="basic-input">
				<option value="">全部联系人</option>
				<option value="workContact">平台内联系人</option>
				<option value="myContact">其他联系人</option>
			</select>
			<pop:JugePermissionTag ename="addGroup">
				<a id="add" href="javascript:;"><span>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="updateGroup">
				<a id="update"  href="javascript:void(0)"><span>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteGroup">
				<a id="delete"  href="javascript:void(0)"><span>删除</span></a>
			</pop:JugePermissionTag>
		</div>
		<div style="clear:both"></div>
		<div class="container container_24" id="content-top" style="height:120px;" >
			<input type="hidden" value="${myGroup.id}" id="myGroupId">
			<div class="grid_3 lable-right">
				<label>群组名称：</label>
			</div>
			<div class="grid_21">
				<input type="text" class="form-txt" id="myGroupName"  value="${myGroup.name}" readonly />
			</div>
			<div style="clear:both"></div>
			<div class="grid_3 lable-right">
				<label >群组描述：</label>
			</div>
			<div class="grid_21">
				<textarea style="height:65px;" readonly id="myGroupRemark"	 class="form-txt">${myGroup.remark}</textarea>
			</div>
			<div class="clearLine">&nbsp;</div>
		</div>
	</div>

	<div style="clear:both"></div>
	<div id="groupHasContacterContext"></div>
	<div id="myGroupDailog"></div>
</div>

<script type="text/javascript">

$(document).ready(function(){
	$("#add").click(function(event){
		$("#myGroupDailog").createDialog({
			width: 660,
			height: 300,
			title:"新增我的群组信息",
			url:"${path}/contact/myGroupManage/dispatch.action?mode=add",
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

	$("#update").click(function(event){
		var selectedId = $("#myGroupId").val();
		if(selectedId==null||selectedId==""){
			$.messageBox({level:'warn',message:"暂无群组,请先新增群组！"});
	 		return;
		}
		$("#myGroupDailog").createDialog({
			width: 660,
			height: 300,
			title:"修改我的群组信息",
			url:"${path}/contact/myGroupManage/dispatch.action?mode=edit&myGroup.id="+selectedId,
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

	$("#delete").click(function(){
		var selectedId = $("#myGroupId").val();
		if(selectedId==null||selectedId==''){
			$.messageBox({level:'warn',message:"暂无群组,请先新增群组！"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"群组信息删除后，将无法恢复,您确认删除该群组信息吗?",
			width: 400,
			okFunc: deleteMyGroup
		});
	});

	loadGroupHasContacter();
});



function deleteMyGroup(){
	var selectedId = $("#myGroupId").val();
	if(selectedId==null||selectedId==''){
		 return;
	}
	$.ajax({
		url:'${path}/contact/myGroupManage/deleteMyGroup.action?myGroup.id='+selectedId,
		success:function(data){
			if(data == true){
				removeMyGroup(selectedId);
				$.messageBox({ message:"成功删除群组信息!"});
				if($("#propertiesDomain>li a").length<1){
					clearFormElement();
					$("#noGroup").append('<span style="color:red;">暂无群组</span>');
				}
				loadGroupHasContacter();
				return;
			}
            $.messageBox({
				message:data,
				level: "error"
			});
        }
	});
}

function refreshFormElement(jsonData){
	$("#myGroupId").val(jsonData.id);
	$("#myGroupName").val($.trim(jsonData.name));
	if(jsonData.remark!=null)
		$("#myGroupRemark").val(jsonData.remark);
	else
		$("#myGroupRemark").val("");
}

function clearFormElement(){
	$("#myGroupId").val("");
	$("#myGroupName").val("");
	$("#myGroupRemark").val("");
}

function loadGroupHasContacter(){
	$("#groupHasContacterContext").load("${path}/interaction/contact/myGroup/groupHasContactList.jsp");

}
</script>
