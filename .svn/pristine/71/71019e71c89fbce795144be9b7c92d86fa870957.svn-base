<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addTimeLimit">
			<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateTimeLimit">
			<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteTimeLimit">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewTimeLimit">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="parametertimelimitList">
		</table>
		<div id="parametertimelimitListPager"></div>
	</div>
	<div id="parametertimelimitDialog"></div>
	<div id="noticeDialog"></div>
	<div id="parametertimelimitMaintanceDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 600;

<pop:formatterProperty name="departmentlevel" domainName="@com.tianque.domain.property.PropertyTypes@ACCOUNT_TIME_LIMIT_LEVEL" />



// 改变组织机构树时加载列表
function onOrgChanged(orgId,isgrid){
	var initParam = {
		"organizationId": orgId
	}
	$("#parametertimelimitList").setGridParam({
 		url:'${path}/parametertimelimitManage/findParametertimelimitPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#parametertimelimitList").setPostData(initParam);
	$("#parametertimelimitList").trigger("reloadGrid");
}

$(function(){
	
	// 生成列表
	$("#parametertimelimitList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"encryptId",index:'id',frozen:true,hidden:true},
	    	{name:'departmentlevel.id', index:'departmentlevel.id',label:'部门',formatter:departmentlevelFormatter,width:100,sortable:false,align:'center', hidden:false}, 
		    //{name:'uselevel.id', index:'uselevel.id',label:'应用层级',formatter:uselevelFormatter,width:100,sortable:true,align:'center', hidden:false}, 
		    //{name:'orgtype.id', index:'orgtype.id',label:'部门类型',formatter:orgtypeFormatter,width:100,sortable:true,align:'center', hidden:false}, 
	    	{name:'handlelimit', index:'handlelimit',label:'办理时限', width:100, sortable:true, align:'center', hidden:false}, 	
	    	{name:'transferredlimit', index:'transferredlimit',label:'办结时限', width:100, sortable:true, align:'center', hidden:false}, 	
	    	{name:'circulationlimit', index:'circulationlimit',label:'流转时限', width:100, sortable:true, align:'center', hidden:false}	
	   	],
	  	multiselect:true,
	   	<pop:JugePermissionTag ename="viewTimeLimit">
	    	ondblClickRow: viewParametertimelimit,
	    </pop:JugePermissionTag>
	});
	onOrgChanged(getCurrentOrgId(),isGrid());
	jQuery("#parametertimelimitList").jqGrid('setFrozenColumns');
	$("#add").click(function(){
		$("#parametertimelimitDialog").createDialog({
			title:"新增三本台账时限标准信息",
			width: 400,
			height: 250,
			url:"${path}/parametertimelimitManage/dispatch.action?mode=add",
			buttons: {
	    		   "保存" : function(){
	    		        $("#maintainForm").submit(); 
	    		   },
	    		   "关闭" : function(){
	    		        $(this).dialog("close"); 
	    		   }
	    		}
		});
	});
	$("#update").click(function(){
		var selectedId = $("#parametertimelimitList").jqGrid("getGridParam", "selrow");
		var selectedIds = $("#parametertimelimitList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds=="" || selectedIds.length>1){
			$.messageBox({level : 'warn',message:"选择请选择一条记录再修改！"});
			 return;
		}
		var isAviable=true;
		$.each(selectedIds,function(i){
			var value = $("#parametertimelimitList").getRowData(selectedIds[i]);
			if(value["departmentlevel.id"] == null || value["departmentlevel.id"] == '' || typeof(value["departmentlevel.id"]) == 'undefined'){
				$.messageBox({level : 'warn',message:"默认时限不允许修改！"});
				isAviable=false;
				return;
			}
		});
		if(isAviable){
			updateOperator(selectedId);
		}
		
	});
	$("#delete").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		deleteOperator(allValue);
	});
	$("#view").click(function(){
		var selectedId = $("#parametertimelimitList").jqGrid("getGridParam", "selrow");
		var selectedIds = $("#parametertimelimitList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds=="" || selectedIds.length>1){
			$.messageBox({level : 'warn',message:"选择请选择一条记录再修改！"});
			 return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewParametertimelimit(selectedId);
	});
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	
	
});

function viewParametertimelimit(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#parametertimelimitList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#parametertimelimitDialog").createDialog({
		title:"查看三本台账时限标准信息",
		width: 600,
		height: 250,
		url:"${path}/parametertimelimitManage/dispatch.action?mode=view&parametertimelimit.id="+id,
		buttons: {
    		   "关闭" : function(){
    		        $(this).dialog("close"); 
    		   }
    		}
	});
}
function updateOperator(selectedId){
	var ent =  $("#parametertimelimitList").getRowData(selectedId);
	$("#parametertimelimitDialog").createDialog({
		title:"修改三本台账时限标准信息",
		width: 400,
		height: 250,
		url:"${path}/parametertimelimitManage/dispatch.action?mode=edit&parametertimelimit.id="+ent.encryptId,
		buttons: {
    		   "保存" : function(){
    		        $("#maintainForm").submit(); 
    		   },
    		   "关闭" : function(){
    		        $(this).dialog("close"); 
    		   }
    		}
	});
}
function deleteOperator(allValue){
	var isAviable=true;
	$.each(allValue,function(i){
		var value = $("#parametertimelimitList").getRowData(allValue[i]);
		if(value["departmentlevel.id"] == null || value["departmentlevel.id"] == '' || typeof(value["departmentlevel.id"]) == 'undefined'){
			$.messageBox({level : 'warn',message:"选择删除的数据中包括默认规则，默认规则不允许删除，请重新选择！"});
			isAviable = false;
			return;
		}
	});
	if(isAviable){
		var selectedIds=deleteOperatorByEncrypt("parametertimelimit",allValue,"encryptId");
		$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
 				url:'${path}/parametertimelimitManage/deleteParametertimelimitByIds.action',
 				type:"post",
				data:{
					"ids":selectedIds
				},
				success:function(data){
				    $.messageBox({message:"已经成功删除该三本台账时限标准表信息!"});
					$("#parametertimelimitList").trigger("reloadGrid");
				}
			});
		}
		});
	}
}
function searchParametertimelimit(){
	$("#parametertimelimitList").setGridParam({
		url:'${path}/parametertimelimitManage/findParametertimelimitPagerBySearchVo.action',
		datatype: "json",
		page:1,
		mtype:"post"
	});
	var postData=$.extend(
			// parameters
		);
	$("#parametertimelimitList").setPostData(postData);
	$("#parametertimelimitList").trigger("reloadGrid");
}
function search(orgId){
	var fastSearchVal = $("#searchText").val();
	if(fastSearchVal == '请输入三本台账时限标准表名称') return;
	var	postData = {
		 "organizationId":orgId
		// parameters
	}
	$("#parametertimelimitList").setGridParam({
// 		url:'${path}/parametertimelimitManage/fastSearch.action',
		datatype: "json",
		page:1
	});
	$("#parametertimelimitList").setPostData(postData);
	$("#parametertimelimitList").trigger("reloadGrid");
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#parametertimelimitList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#parametertimelimitList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}


</script>