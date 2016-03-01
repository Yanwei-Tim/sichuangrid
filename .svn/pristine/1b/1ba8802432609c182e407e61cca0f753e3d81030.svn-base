<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
  <div class="content">
	<div class="ui-corner-all" id="nav">
	 <div class="btnbanner btnbannerData">
	    <div class="ui-widget">
	 	  <div class="autosearch">
	 		<input type="text" class="basic-input" value="请输入标题关键字条件" name="searchtxt" id="searchtxt_Receive" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入标题关键字条件':this.value;" onfocus="value=(this.value=='请输入标题关键字条件')?'':this.value;"/>
			<button id="refreshOrgTree1_Receive" class="ui-icon ui-icon-refresh searchbtnico"></button>
	 	  </div>
	 	  <a href="javascript:;" id="searchByConditionButton_Receive"><span>搜索</span></a>
		  <pop:JugePermissionTag ename="searchPublicNotice">
			<a id="search_Receive" href="javascript:void(0)" ><span>高级搜索</span></a>
		  </pop:JugePermissionTag>
	    </div>
     </div>
     <span class="lineBetween "></span>
	<a id="readReceive" href="javascript:void(0)" ><span>阅读</span></a>
	<a id="refresh_Receive" href="javascript:void(0)"><span>刷新</span></a>
 	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="publicNoticeList_Receive"> </table>
		<div id="publicNoticeList_ReceivePager"></div>
	</div>
<div id="publicNotice_ReceiveDialog"></div>
</div>
<script type="text/javascript">
 //回车
$("#searchtxt_Receive").keydown(function(event){//检索条件
	if (event.keyCode == 13){ $("#searchByConditionButton_Receive").click();}
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

function viewDialog(id){
	var publicNotice=$("#publicNoticeList_Receive").getRowData(id);
	$("#publicNotice_ReceiveDialog").createDialog({
		width:800,
		height:500,
		title:"查看通知通报信息",
		url:"${path}/sysadmin/publicNoticeManage/dispatch.action?mode=readReceive&publicNotice.id="+publicNotice.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		},
		loadComplete: function(){
			setTimeout(function (){search_Receive();}, 1000);
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

function search_Receive(){
	$("#publicNoticeList_Receive").setGridParam({
		url:"${path}/sysadmin/publicNoticeManage/publicNoticeReceiveList.action",
		datatype:"json",
		page:1
	});
	if($("#searchtxt_Receive").val() =="请输入标题关键字条件"){
		$("#publicNoticeList_Receive").setPostData({ });
	}else{
		$("#publicNoticeList_Receive").setPostData({
			"publicNoticeVo.editorTitle": $("#searchtxt_Receive").val()
		});
	}
	$("#publicNoticeList_Receive").trigger("reloadGrid");
}

function validateLength(){
    if($("#publicNoticeMatter").val().length >500){
        return false;
    }
    return true;
}
$(document).ready(function(){
	$("#publicNoticeList_Receive").jqGridFunction({
	    url:"${path}/sysadmin/publicNoticeManage/publicNoticeReceiveList.action",
		height:$(".ui-layout-center").height()-$("#thisCrumbs").outerHeight()-$(".ui-tabs-nav").outerHeight()-100,
		datatype: "json",
		colModel: [
			{name:"id", index:"id",hidden:true,frozen : true,sortable:false},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			{name:"readtime",label:"阅读时间",formatter: function (val){if(val == null || val == ''){return '未读';}return val.replace('T', ' ');},width:150,sortable:true,align:'center'},
			{name:"publicNoticeTitle", index:"publicNoticeTitle",label:"标题",sortable:true,width:266},
			{name:"noticeFiles", formatter: formatterAttachFile, label:'附件', width:100,sortable:false, align: 'center'},
			{name:"noticeEditor",index:"noticeEditor",label:"发布者",sortable:true,width:100},
			{name:"editorDate",index:"editorDate",label:"发布时间",sortable:true,align:'center',width:100},
			{name:"organization.orgName",index:"organization.orgName",label:"所属区域",width:340,sortable:false}
		],
	  	multiselect:true,
		showColModelButton:false,
		loadComplete: function(){showDailyLogAttachFile();},
		ondblClickRow: viewDialog
	});

	$("#refreshOrgTree1_Receive").click(function(){
		$("#searchtxt_Receive").attr("value","请输入标题关键字条件");
	});

	//检索。。。。。。
	$("#searchByConditionButton_Receive").click(function(){
		search_Receive();
	});
	function searchPublicNotice(){
		$("#publicNoticeList_Receive").setGridParam({
            url:"${path}/sysadmin/publicNoticeManage/publicNoticeReceiveList.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchPublicNoticeReceiveForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
			 if (dataJson[data[i].name]) {
		      dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {dataJson[data[i].name] = data[i].value;
			}
		}
		$("#publicNoticeList_Receive").setPostData($.extend({},dataJson));
	    $("#publicNoticeList_Receive").trigger("reloadGrid");
	}

	$("#search_Receive").click(function(event){
		$("#publicNotice_ReceiveDialog").createDialog({
			width:550,
			height:200,
			title:'通知通报查询-请输入查询条件',
			url:'${path}/sysadmin/publicNoticeManage/dispatch.action?mode=searchReceive',
			buttons: {
				"查询" : function(event){
					searchPublicNotice();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#readReceive").click(function(){
		var selectedIdLast = getSelectedId();
		if(selectedIdLast==null || selectedIdLast=='' || selectedIdLast.length==0){
			$.messageBox({level:'warn',message:"请选择一条数据进行操作！"});
	 		return;
		 }
		if(selectedIdLast.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行阅读！"});
	 		return;
		}
			viewDialog(selectedIdLast);
	});

	 function getSelectedId(){
	    var selectedIdLast ;
	    var selectedIds = $("#publicNoticeList_Receive").jqGrid("getGridParam", "selarrrow");
	   return selectedIds;
	}
	$("#refresh_Receive").click(function(){
		$("#searchtxt_Receive").attr("value","请输入标题关键字条件");
		search_Receive();
	});
 });
</script>