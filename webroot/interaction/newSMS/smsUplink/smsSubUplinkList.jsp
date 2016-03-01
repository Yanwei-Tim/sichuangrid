<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入接收者手机" name="searchText" id="searchTextSub" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入接收者手机':this.value;" onfocus="value=(this.value=='请输入接收者手机')?'':this.value;" />
				<button id="refreshSearchKeySub" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a id="fastSearchButtonSub" href="javascript:;"><span>搜索</span></a>
		<a id="searchSub" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="deleteSmsUplink">
			<a id="deleteSub" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>删除</span></a>
		</pop:JugePermissionTag>
		<a id="reloadSub" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="smsSubUplinkList">
		</table>
		<div id="smsSubUplinkListPager"></div>
	</div>
</div>
<script type="text/javascript">
var dialogWidth = 800;
var dialogHeight = 600;

//Formatter
function statusFormatter(el,options,rowData){
	if(rowData.status == 1){
		return "待处理";
	}else if(rowData.status == 2){
		return "处理中";
	}else if(rowData.status == 3){
		return "待发送";
	}else if(rowData.status == 4){
		return "发送成功";
	}else if(rowData.status == 5){
		return "发送失败";
	}else{
		return "";
	}
}

$(function(){
	
	// 生成列表
	$("#smsSubUplinkList").jqGridFunction({
		url:"${path}/smsUplinkManage/findSubSmsUplinksBySearchVo.action",
		postData:{
			"searchSmsUplinkVo.parentId":"${id}"
		},
		datatype: "json",
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
	    	{name:'content', index:'content',label:'发送内容', width:350, sortable:true, align:'center', hidden:false},
			{name:'receiverMobile', index:'receiverMobile',label:'接收者手机', width:150, sortable:true, align:'center', hidden:false}, 	
	    	{name:'status', index:'status',label:'发送状态', formatter:statusFormatter,width:150, sortable:true, align:'center', hidden:false},	
	    	{name:'sendTime', index:'sendTime',label:'发送时间', width:200, sortable:true, align:'center', hidden:false}, 	
	    	{name:'senderName', index:'senderName',label:'发送者姓名', width:150, sortable:true, align:'center', hidden:false}
	   	],
	   	multiselect:true,
	  	onSelectAll:function(data){},
    	loadComplete: function(data){},
		onSelectRow: function(data){},
		ondblClickRow: viewSmsSubUplink
	});
	jQuery("#smsSubUplinkList").jqGrid('setFrozenColumns');
	
	$("#reloadSub").click(function(){
		$("#searchTextSub").attr("value","请输入接收者手机");
		$("#smsSubUplinkList").setGridParam({
			url:"${path}/smsUplinkManage/findSubSmsUplinksBySearchVo.action",
			datatype: "json",
			page:1
		});
		$("#smsSubUplinkList").setPostData({"searchSmsUplinkVo.parentId":"${id}"});
		$("#smsSubUplinkList").trigger("reloadGrid");
	});
	
	$("#searchSub").click(function(event){
		$("#smsUplinkDialog").createDialog({
			width:400,
			height:200,
			title:'请输入查询条件',
	 		url:'${path}/interaction/newSMS/smsUplink/searchSmsSubUplinkDlg.jsp?id=${id}',
			buttons: {
		   		"查询" : function(event){
		        	$("#searchSmsSubForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#fastSearchButtonSub").click(function(){
		var fastSearchVal = $("#searchTextSub").val();
		if(fastSearchVal == '请输入接收者手机') return;
		var	postData = {
			"searchSmsUplinkVo.parentId":"${id}",
			"searchSmsUplinkVo.receiverMobile":fastSearchVal
		}
		$("#smsSubUplinkList").setGridParam({
			url:"${path}/smsUplinkManage/findSubSmsUplinksBySearchVo.action",
			datatype: "json",
			page:1
		});
		$("#smsSubUplinkList").setPostData(postData);
		$("#smsSubUplinkList").trigger("reloadGrid");
	});
	$("#searchTextSub").focus(function(){
        this.select();
	 });
	$("#refreshSearchKeySub").click(function(){
		$("#searchTextSub").attr("value","请输入接收者手机");
	});
	
	$("#deleteSub").click(function(){
		var allValue = $("#smsSubUplinkList").jqGrid("getGridParam", "selarrrow");
		if(!allValue || allValue.length ==0){
			$.messageBox({level:'warn',message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}
		var list = $("#smsSubUplinkList");
		for(var i=0; i<allValue.length; i++){
			var rowData = list.getRowData(allValue[i]);
			if(!rowData){
				continue ;
			}
			if(rowData.status == "待发送" || rowData.status == "处理中"){
				$.messageBox({message:"待发送或处理中的短信不能被删除!",level:"error"});
				return ;
			}
		}
		var encryptIds=deleteOperatorByEncrypt("smsSubUplink",allValue,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function() {
				$.ajax({
					url:'${path}/smsUplinkManage/deleteSMS.action?ids='+encryptIds,
					success:function(data){
						if(data == true || data=="true"){
						    $.messageBox({message:"已经成功删除该短信信息!"});
							$("#smsSubUplinkList").trigger("reloadGrid");
						}else{
							$.messageBox({message:"已经成功删除该短信信息!",level:"error"});
						}
					}
				});
			}
		});
	});
	
	function viewSmsSubUplink(selectedId){
		if(!selectedId){
			$.messageBox({
				message:"请选择一条记录！",
				level: "error"	
			});	
			return;
		}
		var rowData = $("#sendObjectList").getRowData(selectedId);
		var smsSubUpLink=$("#smsSubUplinkList").getRowData(selectedId);
		$("#sendObjectDialog").createDialog({
			width: 550,
			height: 350,
			title:'查看短信',
			url:"${path}/smsUplinkManage/dispatchOperate.action?mode=viewSub&id="+smsSubUpLink.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close"); 
			   }
			}
		});
	}

});




</script>