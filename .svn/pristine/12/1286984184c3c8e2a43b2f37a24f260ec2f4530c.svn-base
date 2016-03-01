<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
<%-- 			<jsp:include page="${path}/common/orgSelectedComponent.jsp" /> --%>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入手机号码或电话号码" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入手机号码或电话号码':this.value;" onfocus="value=(this.value=='请输入手机号码或电话号码')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="deleteSmstrash">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="smstrashList">
		</table>
		<div id="smstrashListPager"></div>
	</div>
	<div id="smstrashDialog"></div>
	<div id="noticeDialog"></div>
	<div id="smstrashMaintanceDialog"></div>
</div>
<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 600;

// Formatter
function smstrashOperatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename="deleteSmstrash"><a href=javascript:smstrashDeleteOperator('"+rowData.id+"'); ><span>删除</span></a></pop:JugePermissionTag>" + 
	 		"<pop:JugePermissionTag ename="restoreSmstrash"> | <a href=javascript:smstrashRestoreOperator('"+rowData.id+"'); ><span>还原</span></a></pop:JugePermissionTag>";
}
function smstrashTypeFormatter(el,options,rowData){
	if(1 == rowData.fromType){
		return "发件箱";
	}else if(2 == rowData.fromType){
		return "收件箱";
	}
	return "";
}

$(function(){
	// 生成列表
	$("#smstrashList").jqGridFunction({
		url:'${path}/smstrashManage/findSmstrashPagerBySearchVo.action',
		mtype:'post',
		datatype: "json",
		page:1,
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	    	{name:"operator", index:'id',label:'操作',formatter:smstrashOperatorFormatter,width:80,frozen:true,sortable:false,align:'center' },
	    	{name:'fromType', index:'fromType',label:'短信来源',formatter:smstrashTypeFormatter, width:150, sortable:true, align:'center', hidden:false}, 	
	    	{name:'time', index:'time',label:'时间', width:150, sortable:true, align:'center', hidden:false}, 	
	    	{name:'mobile', index:'mobile',label:'号码', width:150, sortable:true, align:'center', hidden:false}, 	
	    	{name:'content', index:'content',label:'短信内容', width:400, sortable:true, align:'center', hidden:false}	
	   	],
	  	multiselect:true,
	  	onSelectAll:function(data){},
    	loadComplete: function(data){},
	    ondblClickRow: viewSmstrash,
		onSelectRow: function(data){}
	});
	jQuery("#smstrashList").jqGrid('setFrozenColumns');
	
	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());
	});
	$("#searchText").focus(function(){
        this.select();
	 });
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入手机号码或电话号码");
	});
	$("#delete").click(function(){
		var allValue = getSelectedIds();
		if(allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		smstrashDeleteOperator(allValue);
	});
	$("#reload").click(function(){
		$("#searchText").attr("value","请输入手机号码或电话号码");
		$("#smstrashList").setGridParam({
			url:'${path}/smstrashManage/findSmstrashPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#smstrashList").trigger("reloadGrid");
	});

	$("#search").click(function(event){
		$("#smstrashDialog").createDialog({
			width:650,
			height:320,
			title:'垃圾短信箱查询-请输入查询条件',
	 		url:'${path}/interaction/newSMS/smstrashManage/searchSmstrashDlg.jsp',
			buttons: {
		   		"查询" : function(event){
		   			$("#searchsmstrasForm").submit();
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
});

function viewSmstrash(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#smstrashList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#smstrashDialog").createDialog({
		width:700,
		height:300,
		title:"查看垃圾短信信息",
		url:'${path}/smstrashManage/dispatchOperate.action?mode=view&idstr='+rowData.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function smstrashRestoreOperator(allValue){
	if(!allValue){
		return ;
	}
	var encryptIds=deleteOperatorByEncrypt("smstrash",allValue,"encryptId");
	$.ajax({
		url:'${path}/smstrashManage/restoreSMSById.action?idstr='+encryptIds,
		success:function(data){
			if(data){
				$.messageBox({message:"已经成功还原短信信息!"});
			}else{
				$.messageBox({message:"操作失败!",level:"error"});
			}
			$("#smstrashList").trigger("reloadGrid");
		}
	});
}
function smstrashDeleteOperator(allValue){
	var encryptIds=deleteOperatorByEncrypt("smstrash",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/smstrashManage/deleteSmstrashByIds.action',
				type:"post",
				data:{
					"ids":encryptIds+""
				},
				success:function(data){
				    $.messageBox({message:"已经成功删除该垃圾短信箱信息!"});
					$("#smstrashList").trigger("reloadGrid");
				}
			});
		}
	});
}
function search(orgId){
	var fastSearchVal = $("#searchText").val();
	if(fastSearchVal == '请输入手机号码或电话号码') return;
	var	postData = {
		"searchSmstrashVo.mobile":fastSearchVal
	}
	$("#smstrashList").setGridParam({
		url:'${path}/smstrashManage/findSmstrashPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#smstrashList").setPostData(postData);
	$("#smstrashList").trigger("reloadGrid");
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#smstrashList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#smstrashList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}

</script>