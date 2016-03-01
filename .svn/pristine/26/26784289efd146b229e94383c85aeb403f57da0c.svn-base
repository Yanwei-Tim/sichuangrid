<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" align="center">
	<div class="ui-corner-all" id="nav" align="left">
		<a id="addSquery" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		<a id="deleteSquery" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		<a id="reloadSquery" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 99%;">
		<table id="smsqueryconditionList">
		</table>
		<div id="smsqueryconditionListPager"></div>
	</div>
	<div id="smsqueryconditionDialog"></div>
	<div id="noticeDialog"></div>
	<div id="smsqueryconditionMaintanceDialog"></div>
</div>
<script type="text/javascript">

// Formatter
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:updateSmsSquery("+rowData.id+")'><span>修改</span></a> | <a href='javascript:deleteSmsSquery("+rowData.id+")'><span>删除</span></a>";
}
function isNullFormatter(el,options,rowData){
	if(rowData.isNull == true || rowData.isNull == 'true'){
		return "是";
	}else{
		return "否";
	}
}

$(function(){
	function onSmsQueryChanged(){
		var initParam = {
				'smsquerycondition.smsSendObject.id':'<s:property value="#parameters.objectId"/>'
		}
		$("#smsqueryconditionList").setGridParam({
			url:'${path}/smsqueryconditionManage/findSmsqueryconditionPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#smsqueryconditionList").setPostData(initParam);
		$("#smsqueryconditionList").trigger("reloadGrid");
	}
	
	function toggleButtonState(){
	  	var selectedIds=$("#smsqueryconditionList").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	}
	
	// 生成列表
	$("#smsqueryconditionList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		height:380,
	   	colModel:[
			{name:"id",index:'id',hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:100,frozen:true,sortable:false,align:'center' },
	    	{name:"key",index:"key",width:100,align:'center',label:"中间key"},
	    	{name:"sqlTemplate",index:"sqlTemplate",width:200,align:'center',label:"sql语句模板"},
  			{name:"isNull",index:"isNull",formatter:isNullFormatter,width:100,align:'center',label:"是否必填"},
  			{name:"description",index:"description",width:150,label:"描述"}
	   	],
	  	multiselect:true,
	  	onSelectAll:function(data){
	  		toggleButtonState(data);
	  	},
    	loadComplete: function(data){},
	    ondblClickRow: viewSmsquerycondition,
		onSelectRow: function(data){
			toggleButtonState(data);
		}
	});
	jQuery("#smsqueryconditionList").jqGrid('setFrozenColumns');
	
	onSmsQueryChanged();
	
	$("#addSquery").click(function(){
		$("#smsqueryconditionDialog").createDialog({
			title:"新增查询条件管理信息",
			width: 650,
			height: 430,
			url:'${path}/smsqueryconditionManage/dispatchOperate.action?mode=add&objectId='+'<s:property value="#parameters.objectId"/>',
			buttons: {
				"确定" : function(event){
			    	$("#smsQueryForm").submit();
			    },
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#deleteSquery").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteSmsSquery(allValue);
	});
	$("#reloadSquery").click(function(){
		onSmsQueryChanged();
	});
	
});

function viewSmsquerycondition(selectedId){
	if(!selectedId){
 		return;
	}
	var rowData = $("#smsqueryconditionList").getRowData(selectedId);
	var id = rowData.id;
	if(!id){
		 return;
	}
	$("#smsqueryconditionDialog").createFrameDialog({
		width: 650,
		height: 430,
		title:"查看查询条件管理信息",
		url:'${path}/smsqueryconditionManage/dispatchOperate.action?mode=view&id='+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function updateSmsSquery(selectedId){
	$("#smsqueryconditionDialog").createDialog({
		title:"修改查询条件管理信息",
		width: 650,
		height: 430,
		url:'${path}/smsqueryconditionManage/dispatchOperate.action?mode=edit&id='+selectedId,
		buttons: {
			"确定" : function(event){
		    	$("#smsQueryForm").submit();
		    },
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function deleteSmsSquery(allValue){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/smsqueryconditionManage/deleteSmsqueryconditionByIds.action?ids='+allValue,
				success:function(data){
				    $.messageBox({message:"已经成功删除该查询条件管理信息!"});
					$("#smsqueryconditionList").trigger("reloadGrid");
				}
			});
		}
	});
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#smsqueryconditionList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#smsqueryconditionList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

</script>