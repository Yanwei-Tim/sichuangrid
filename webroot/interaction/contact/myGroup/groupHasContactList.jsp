<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="content">
	<div class="groupNav" id="nav" style="left:5px">
		<pop:JugePermissionTag ename="editHasContacter">
			<a id="editHasContacter" href="javascript:void(0)"><span>编辑群组联系人</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteHasContacter">
			<a id="deleteHasContacter"  href="javascript:void(0)"><span>删除群组联系人</span></a>
		</pop:JugePermissionTag>
	</div>
	<div class="content" >
		<div style="width:99%;" id="grid_content" >
			<table id="groupHasContactertList"></table>
			<div id="groupHasContactertListPager"></div>
		</div>
		<div id="searchDialog"></div>
		<div id="myGroupContacterMaintanceDialog"></div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#groupHasContactertList").jqGridFunction({
		url:"${path}/contact/myGroupManage/findMyGroupHasContacters.action",
		datatype: "json",
		postData:{
			"myGroup.id":function(){
				var myGroupId=$("#myGroupId").val();
				if(myGroupId==null||myGroupId=='')
					myGroupId=0;
				return myGroupId;
			},
			"myGroup.belongClass":function(){
				return $("#selectedBelongClass").val();
			}
		},
		colNames:['id','姓名','联系手机','备注'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'name',sortable:false,width:200},
	  		{name:"mobileNumber",sortable:false,width:200},
	        {name:'remark',sortable:false,width:400}
		],
		scrollrow:true,
		height:$(".ui-layout-west").height()-$(".path").height()-$("#content-top").height()-$("#nav").height()-125
	});

	$("#selectedBelongClass").change(function(){
		filterBelongClass();
	});

	$("#editHasContacter").click(function(){
		var selectedId = $("#myGroupId").val();
		if(selectedId==null||selectedId==''){
			$.messageBox({level:'warn',message:"暂无群组,请先新增群组！"});
	 		return;
		}
		$("#myGroupContacterMaintanceDialog").createDialog({
			width:650,
			height:470,
			title:'编辑群组联系人',
			url:"${path}/contact/myGroupManage/dispatchMyGroupHasContacter.action?mode=edit&myGroup.id="+selectedId,
			buttons :{
				"保存" : function(){
					$("#myGroupHasContacter-Form").submit();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#deleteHasContacter").click(function(){
		if($("#myGroupId").val()==null||$("#myGroupId").val()==''){
			$.messageBox({level:'warn',message:"暂无群组,请先新增群组！"});
	 		return;
		}
		var selectedId = $("#groupHasContactertList").getGridParam("selrow");
		if(selectedId==null||selectedId==''){
			$.messageBox({level:'warn',message:"请选择要删除的群组联系人！"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"群组联系人信息删除后，将无法恢复,您确认删除该群组联系人信息吗?",
			width: 400,
			okFunc: deleteMyGroupHasContacter
		});
	});

});

function filterBelongClass(){
	var myGroupId=$("#myGroupId").val();
	if(myGroupId==null||myGroupId==''){
 		return;
	}
	$("#groupHasContactertList").setGridParam({
		url:"${path}/contact/myGroupManage/findMyGroupHasContacters.action",
		datatype: "json",
		page:1
	});
	$("#groupHasContactertList").setPostData({
		"myGroup.id":function(){
			return myGroupId;
		},
		"myGroup.belongClass":function(){
			return $("#selectedBelongClass").val();
		}
	});
	$("#groupHasContactertList").trigger("reloadGrid");
}


function deleteMyGroupHasContacter(){
	var selectedId = $("#groupHasContactertList").getGridParam("selrow");
	var myGroupId = $("#myGroupId").val();
	if(selectedId==null||selectedId==''){
		 return;
	}
	$.ajax({
		url:'${path}/contact/myGroupManage/deleteMyGroupHasContacter.action?myGroup.id='+myGroupId+"&contacterId="+selectedId,
		success:function(data){
			if(data == true){
				$("#groupHasContactertList").delRowData(selectedId);
				$.messageBox({message:"成功删除群组联系人信息!"});
				return;
			}
            $.messageBox({message:data,level: "error"});
        }
	});
}

</script>
