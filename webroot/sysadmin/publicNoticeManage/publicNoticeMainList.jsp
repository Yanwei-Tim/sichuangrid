<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
  <div class="content">
	<div class="ui-corner-all" id="nav">
	 <div class="btnbanner btnbannerData">
	    <div class="ui-widget">
	      <div class="userState">
		    <select name="publicNoticeLevel" id="publicNoticeLevel" class="basic-input">
		       <option value="0">本级</option>
		       <option value="1">所有下辖</option>
		    </select>
	      </div>
	 	  <div class="autosearch">
	 		<input type="text" class="basic-input" value="请输入标题关键字条件" name="searchtxt" id="searchtxt" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入标题关键字条件':this.value;" onfocus="value=(this.value=='请输入标题关键字条件')?'':this.value;"/>
			<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
	 	  </div>
	 	  <a href="javascript:;" id="searchByConditionButton"><span>搜索</span></a>
		  <pop:JugePermissionTag ename="searchPublicNotice">
			<a id="search" href="javascript:void(0)" ><span>高级搜索</span></a>
		  </pop:JugePermissionTag>
	    </div>
     </div>
     <span class="lineBetween "></span>
     <pop:JugePermissionTag ename="addPublicNotice">
		<a id="add" href="javascript:void(0)" ><span>新增</span></a>
	 </pop:JugePermissionTag>
	 <pop:JugePermissionTag ename="updatePublicNotice">
	    <a id="update" href="javascript:void(0)"><span>修改</span></a>
	 </pop:JugePermissionTag>
	 <pop:JugePermissionTag ename="viewPublicNotice">
	  	<a id="view" href="javascript:void(0)"><span>查看</span></a>
	 </pop:JugePermissionTag>
	 <pop:JugePermissionTag ename="deletePublicNotice">
	    <a id="delete" href="javascript:void(0)"><span>删除</span></a>
	 </pop:JugePermissionTag>
	<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
 	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="publicNoticeList"> </table>
		<div id="publicNoticeListPager"></div>
	</div>
<div id="publicNoticeDialog"></div>
<script type="text/javascript">
 var dialogWidth=580;
 var dialogHeight=540;
 var currentOrgId;

 function onOrgChanged(){
	 $("#publicNoticeList").setGridParam({
		 url:"${path}/sysadmin/publicNoticeManage/publicNoticeList.action?publicNoticeLevel="+$("#publicNoticeLevel").val(),
		 datatype:"json",
		 page:1
	 });
	 $("#publicNoticeList").setPostData({});
	 $("#publicNoticeList").trigger("reloadGrid");
 }

 //回车
	$("#searchtxt").keydown(function(event){//检索条件
		if (event.keyCode == 13){ $("#searchByConditionButton").click();}
	});

 function showDailyLogAttachFile(){
	$.each($(".popUpMore"), function(i, n){
		$.ajax({
			url:"${path}/sysadmin/publicNoticeManage/getPublicNoticeAttachFilesById.action?publicNotice.id="+$(n).attr("publicNoticeId"),
			success:function(publicNotice){
				var popMoreData = [];
				for(var j = 0; j < publicNotice.noticeFiles.length; j++){
					popMoreData[j]={id:publicNotice.noticeFiles[j].id,
					url:'${path}/sysadmin/publicNoticeManage/downloadPublicNoticeAttachFile.action?publicNoticeAttachFile.id='+publicNotice.noticeFiles[j].id, text:publicNotice.noticeFiles[j].fileName,
							clickFun:function(){}};
				}
				$(n).popUpMore({data: popMoreData});
			}
		});
	});
}
function updateOperator(selectedId){
	var publicNotice=$("#publicNoticeList").getRowData(selectedId);
	$("#publicNoticeDialog").createDialog({
		width:650,
		height:580,
		title:'修改通知通报',
		url:'${path}/sysadmin/publicNoticeManage/dispatch.action?mode=edit&publicNotice.id='+publicNotice.encryptId,
		buttons: {
			"保存" : function(){
				    $.confirm({
					title:"确认有效期",
					message:$("#overdueDate").val()?"当前有效期是："+$("#overdueDate").val():"当前有效期是：不限",
					okFunc: function() {
						if(!validateNull()){
	                         $.messageBox({message:"请输入内容！",level:"error"});
	                         return false;
	                     }
	             	    if(!validateLength()){
	                         $.messageBox({message:"内容最多允许输入500个字符！",level:"error"});
	                 	    return false;
	             	    }
						$("#publicNoticeForm").submit();
					}
				});
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function deleteOperator(selectedIds){
	var encryptIds=deleteOperatorByEncrypt("publicNotice",selectedIds,"encryptId");
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:"${path}/sysadmin/publicNoticeManage/deletePublicNoticeByEncrypt.action?publicNoticeIds="+encryptIds,
				success:function(data){
				    $.messageBox({message:"已经成功删除通知通报信息!"});
					$("#publicNoticeList").trigger("reloadGrid");
// 					setPublicNoticeOperateButton();
				}
			});
		}
	});
}

function viewDialog(id){
	var publicNotice=$("#publicNoticeList").getRowData(id);
	$("#publicNoticeDialog").createDialog({
		width:800,
		height:500,
		title:"查看通知通报信息",
		url:"${path}/sysadmin/publicNoticeManage/dispatch.action?mode=view&publicNotice.id="+publicNotice.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function formatterAttachFile(el,options,rowData){
	if(rowData.noticeFiles.length>0){
		return "<div style='clear:both' align='center'><a href='javascript:void(0)'  style='color:#666666' class='popUpMore' publicNoticeId='"+rowData.id+"' >附件>></div>";
	}
	return "无";
}

function validateNull(){
	if($("#publicNoticeMatter").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"") == '' || $("#publicNoticeMatter").val().replace(/[&nbsp;,<p>,</p>]/g,"").replace(/[ ]/g,"").length == 0){
		return false;
 }
 return true;
}

function validateLength(){
    if($("#publicNoticeMatter").val().length >500){
        return false;
    }
    return true;
}

function callback(){
    var dfop = {
    		
    }
    TQ.publicNoticeMainList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/sysadmin/publicNoticeManage/publicNoticeMainList.js',
    callback: callback
})


</script>