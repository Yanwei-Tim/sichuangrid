<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	jQuery("#upgradeContentList").jqGridFunction({
	   	url:'${path}/sysadmin/upgradeContentManage/queryUpgradeContentList.action',
		datatype: "json",
	   	colModel:[
			{name:"id",index:"id",hidden:true,sortable:false},
	   	    {name:'升级时间',label:'升级时间',index:'upgradeDate',name:'upgradeDate', sortable:false},
	   		{name:'升级内容',label:'升级内容',index:'upgradeContent',name:'upgradeContent',width:350, sortable:false},
	   		{name:'创建用户',label:'创建用户',index:'createUser',name:'createUser',sortable:true,align:'center'},
	   		{name:'最后更新时间',label:'最后更新时间',index:'updateDate',name:'updateDate',sortable:true,align:'center'},
	   		{name:'是否展示',label:'是否展示',index:'isUpgrad_name',name:'isUpgrad',sortable:true,align:'center',formatter:getIsUpgrad},
	   	 	{name:"isUpgrad",sortable:false,hidden:true,frozen:false,hidedlg:true}
	   	],
	   	multiselect: true
	   	<pop:JugePermissionTag ename="viewUpgradeContent">
	   		,ondblClickRow: viewUpgradeContent
	   	</pop:JugePermissionTag>
	});

	$("#reload").click(function(){
		$("#upgradeContentList").trigger("reloadGrid");
	});

	$("#add").click(function(event){
		$("#upgradeContentManageDialog").createDialog({
			width:700,
			height:420,
			title:'新增升级内容',
			url:'${path}/sysadmin/upgradeContentManage/dispatch.action?mode=add',
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
		var selectedId = $("#upgradeContentList").jqGrid("getGridParam", "selarrrow");
		if(selectedId == null || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行操作！"});
			 return;
		}
		if(selectedId.length > 1){
			$.messageBox({level:'warn',message:"只能选择一条数据，进行操作!"});
			 return;
		}
		$("#upgradeContentManageDialog").createDialog({
			width:700,
			height:420,
			title:'修改升级内容',
			modal : true,
			resizable : true,
			url:'${path}/sysadmin/upgradeContentManage/dispatch.action?mode=edit&id='+ selectedId,
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

	$("#view").click(function(){
		var selectedId = $("#upgradeContentList").jqGrid("getGridParam", "selarrrow");
		if(selectedId == null || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行操作！"});
			 return;
		}
		if(selectedId.length > 1){
			$.messageBox({level:'warn',message:"只能选择一条数据，进行操作!"});
			 return;
		}
		viewUpgradeContent(selectedId);
	});
	
	$("#delete").click(function(){
		var selectedIds = $("#upgradeContentList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds == 0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(selectedIds);
	});

	$("#export").click(function(event){
		var selectedId = $("#upgradeContentList").jqGrid("getGridParam", "selarrrow");
		if(selectedId == null || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行操作！"});
			 return;
		}
		if(selectedId.length > 1){
			$.messageBox({level:'warn',message:"只能选择一条数据，进行操作!"});
			 return;
		}
		$("#upgradeContentManageDialog").createDialog({
			width:700,
			height:420,
			title:'升级内容-导出Word',
			modal : true,
			resizable : true,
			url:'${path}/sysadmin/upgradeContentManage/dispatch.action?mode=printWord&id='+ selectedId,
			buttons: {
				"导出word" : function(event){
					exportWord(selectedId);
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
	   });
	});
	
	$("#isUpgrad").click(function(){
		var selectedId = $("#upgradeContentList").jqGrid("getGridParam", "selarrrow");
		if(selectedId == null || selectedId.length == 0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行操作！"});
			 return;
		}
		if(selectedId.length > 1){
			$.messageBox({level:'warn',message:"只能选择一条数据，进行操作!"});
			 return;
		}
		isUpgradOperator(selectedId);
	});
});

function viewUpgradeContent(selectedId){
	$("#upgradeContentManageDialog").createDialog({
		width:700,
		height:520,
		title:'查看升级内容',
		modal : true,
		resizable : true,
		url:'${path}/sysadmin/upgradeContentManage/dispatch.action?mode=view&id='+ selectedId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function deleteOperator(selectedIds){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/sysadmin/upgradeContentManage/deleteUpgradeContent.action?ids='+selectedIds,
				type:"post",
				success:function(data){
					if(data != null) {
						$.messageBox({message:data,level:"error"});
					} else {
						$("#upgradeContentList").trigger("reloadGrid");
						$.messageBox({message:"删除成功!"});
					}
				}
			});
		}
	});
}

function exportWord(selectedId){
	this.location="${path}/sysadmin/upgradeContentManage/dispatch.action?mode=printWord&id="+ selectedId;
}

function isUpgradOperator(selectedId){
	var versions =  $("#upgradeContentList").getRowData(selectedId);
	var title = "";
	var message = "";
    var isUpgrad = "";
	if(versions.isUpgrad == 1){
		title = "确认展示";
		message	= "确定要将升级内容取消展示吗？";
		isUpgrad = 0;
	}else{
		title = "确认展示";
		message	= "确定要将升级内容进行展示吗？";
		isUpgrad = 1;
	}
	$.confirm({
		title: title,
		message: message,
		okFunc: function() {
			$.ajax({
				url:'${path}/sysadmin/upgradeContentManage/updateIsUpgradUpgradeContent.action?upgradeContent.id='+ selectedId+'&upgradeContent.isUpgrad='+isUpgrad,
				type:"post",
				data:{
					"userId":USER_ID
				},
				success:function(data){
					if(data != null) {
						$("#upgradeContentList").trigger("reloadGrid");
						$.messageBox({message:"修改成功!"});
					} else {
						$.messageBox({message:data,level:"error"});
					}
				}
			});
		}
	});
}

function getIsUpgrad(val){
	if(val == 1){
		return "展示";
	}else{
		return "不展示";
	}
}
</script>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addUpgradeContent">
				<a id="add" href="javascript:void(0)"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateUpgradeContent">
				<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteUpgradeContent">
				<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewUpgradeContent">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="downUpgradeContent">
				<a id="export" href="javascript:void(0)"><span>导出Word</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="isUpgradUpgradeContent">
				<a id="isUpgrad" href="javascript:void(0)"><span>是否展示</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%">
		<table id="upgradeContentList"></table>
		<div id="upgradeContentListPager"></div>
	</div>
	<div id="upgradeContentManageDialog"></div>
</div>

