<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-cornRr-all" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入发送对象名称" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
					onblur="value=(this.value=='')?'请输入发送对象名称':this.value;" onfocus="value=(this.value=='请输入发送对象名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="addSmsSendObject">
			<a id="add" href="javascript:void(0)" ><span><strong class="ui-ico-cx"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteSmsSendObject">
			<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="sendObjectList"></table>
		<div id="sendObjectListPager"></div>
	</div>
	<div id="sendObjectDialog"></div>
</div>
<script type="text/javascript">
//Formatter
function sendObjectFormatter(el,options,rowData){
	var str = "启用";
	if(rowData.enable == "true" || rowData.enable == true){
		str="禁用";
	}
	return "<a href='javascript:enableSendObject("+rowData.id+")'><span>"+str+"</span></a> | "
			+"<pop:JugePermissionTag ename='updateSmsSendObject'><a href='javascript:updateSendObject("+rowData.id+")'><span>修改</span></a></pop:JugePermissionTag> | "
			+"<pop:JugePermissionTag ename='deleteSmsSendObject'><a href='javascript:deleteSendObject("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}

function enableSendObject(selectedId){
	if(!selectedId){
		return ;
	}
	var rowData = $("#sendObjectList").getRowData(selectedId);
	var value = false;
	if(rowData.enable == "false" || rowData.enable == false){
		value = true;
	}
	$.ajax({
		url:PATH+"/smsSendObjectManage/enableSmsSendObject.action",
		data:{
			"bol":value,
			"id":rowData.encryptId
		},
		success:function(data){
			if(!data.id){
				$.messageBox({ message:"操作失败，请联系管理员！",level:"error"});
				return ;
			}
			if(data.enable){
				$.messageBox({ message:"成功启用该发送对象!"});
			}else{
				$.messageBox({ message:"成功禁用该发送对象!"});
			}
			$("#reload").click();
        }
	});
}

function updateSendObject(selectedId){
	var rowData = $("#sendObjectList").getRowData(selectedId);
	var smsSendObject=$("#sendObjectList").getRowData(selectedId);
	$("#sendObjectDialog").createDialog({
		width: 850,
		height: 640,
		title:rowData.name+'发送对象',
		url:PATH+"/smsSendObjectManage/dispatchOperate.action?mode=update&id="+smsSendObject.encryptId,
		buttons: {
			"保存" : function(event){
		    	$("#maintainForm").submit();
		    },
			"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function deleteSendObject(allValue){
	var encryptIds=deleteOperatorByEncrypt("sendObject",allValue,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:PATH+"/smsSendObjectManage/deleteSmsSendObjectById.action",
				type:"post",
				data:{
					"ids":encryptIds+""
				},
				success:function(data){
					if(data){
						$.messageBox({ message:"成功删除该发送对象!"});
						$("#reload").click();
						return;
					}
		            $.messageBox({
						message:data,
						level: "error"	
					});						
		        }
			});
		}
	});
}

function callback(){
    var dfop = {
    		
    }
    TQ.sendObjectList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/newSMS/sendObjectManagement/sendObjectList.js',
    callback: callback
})
</script>