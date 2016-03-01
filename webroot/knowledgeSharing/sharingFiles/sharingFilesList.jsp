<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript" src="${resource_path }/resource/system/js/myProfileTreeManage.js"></script>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />

<div class="center-left">
	<div class="orgObjContent">
		<div id="publicDirectoryTree"></div>
	</div>
</div>

<div class="center-right">
	<div class="content" >
	  <div class="ui-corner-all" id="nav">
			<select id="searchType" name="" style="height:25px;width:100px" class="S_object">
				<option value="0" selected="selected">全部</option>
		 		<option value="1">上级共享的资料</option>
		 		<option value="2">本级共享的资料</option>
		 		<option value="3">下级共享的资料</option>
			</select>
			<input type="text" value="请输入文件名" name="searchText" id="searchText" style="width:150px;left:310px;" class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'请输入文件名':this.value;" onfocus="value=(this.value=='请输入文件名')?'':this.value;"/>
			<button id="refreshsearchText" class="ui-icon ui-icon-refresh searchbtnico" style="border:0;background-color:transparent; position:absolute;top:10px;left:260px;cursor:pointer;outline: none;"></button>
			<a id="fastSearch" href="javascript:void(0)"><span>搜索</span></a>
			<pop:JugePermissionTag ename="searchMyProfile">
			<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		    <span class="lineBetween"></span>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		</div>
		
		<div style="clear: both;"></div>
		<div style="width:100%;" >
			<table id="shareFileList"> </table>
			<div id="shareFileListPager" ></div>
		</div>
			
	</div>
	<div id="shareFileDailog"></div>
</div>

<script type="text/javascript">	
var resourcePoolType=0;
var innerTitle;
var tree;
function viewShareFile(selectedId){
	var widthWhenAdd=650;
	var heightWhenAdd=300;
	var rowData=  $("#shareFileList").getRowData(selectedId);
	$("#shareFileDailog").createDialog({
		width:widthWhenAdd,
		height:heightWhenAdd,
		title:'查看'+innerTitle+'信息',
		url:'${path}/resourcePool/sharingFilesManage/dispatch.action?mode=view&id='+rowData.encryptId,
		buttons :{
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
$(document).ready(function(){
	function layoutFun(){
		clearTimeout(window._fileListTimer);
		$(".orgObjContent").height($(".ui-layout-center").height()-$("#thisCrumbs").height()-10)
	}
	layoutFun();
	$(window).resize(function(){
		window._fileListTimer=setTimeout(function(){
			layoutFun();
		},200);
	});
	function nameFont(el,options,rowData){
		return "<a href='javascript:viewShareFile("+rowData.id+")'>"+rowData.name+"</a>";
	}
	
	$("#refreshsearchText").click(function(){
		$("#searchText").val("");
	});
	$("#refresh").click(function(){
		$("#searchText").attr("value","请输入文件名");
		reloadList();
	});
	$("#searchType").change(function(){
		reloadList();
	});
	function formatterAttachFile(el,options,rowData){

		if(rowData.myProfileAttachFile.length>0){
		$("#shareFileList").data( "'"+rowData.id+"'", rowData.myProfileAttachFile);
			return "<div style='clear:both' align='center'><a href='javascript:;' id='myProfile_"+rowData.id+"' style='color:#666666' class='popUpMore' resourcePoolId='"+rowData.id+"' >附件>></a></div>";
		}
		return "";
	}
	function afterLoad(){
		$(".tip-yellowsimple").remove();
		$.each($(".popUpMore"), function(i, n){
			var selectedId = $(n).attr("resourcepoolid");
			$.ajax({
				url:"${path}/resourcePool/myProfileManage/findMyProfileAttachFileByMyProfileId.action?resourcePoolTypeId="+selectedId,
				success:function(attachFileList){
					var popMoreData = [];
					if(null!=attachFileList){
						for(var j = 0; j < attachFileList.length; j++){
							popMoreData[j]={id:attachFileList[j].id, url:'${path}/resourcePool/myProfileManage/downloadMyProfileAttachFile.action?myProfileAttachFile.id='+attachFileList[j].id, text:attachFileList[j].fileName,clickFun:function(){}};
						}
					}
					$(n).popUpMore({data: popMoreData});
				}
			});
		});
	}

	
	var centerHeight=$("div.ui-layout-center").height();
	$(".orgTree").height(centerHeight-62);
	$(".center-right").height(centerHeight-30);

	tree=$("#publicDirectoryTree").initMyProfileTree({
		url:'/resourcePool/directorySettingManage/firstPublicDirectory.action',
		dataUrl:PATH+'/resourcePool/directorySettingManage/firstPublicDirectory.action'
	});
	$.addClick(tree,afterDirectoryChangNode);
	 $("#shareFileList").jqGridFunction({	 
		 multiselect:false,
		 url:'${path}/resourcePool/sharingFilesManage/findSharingFilesList.action?resourcePoolTypeId=0&searchType='+$("#searchType").val(),
		 colModel:[	
		        {name:"id",index:"id",sortable:false,hidden:true},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		   		{name:"name",label:"名称",width:160,sortable:true,formatter:nameFont},
		   		{name:"releaseUnit",label:"发文单位（作者）",width:150,sortable:true},
		   		{name:"releaseTime",label:"（发文）时间",align:"center",width:100,sortable:true ,hidden:true,formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
		   		{name:"organization.orgName",label:"共享部门",width:120,sortable:false },
		   		{name:"shareUserRealName",label:"共享用户",width:150,sortable:false ,hidden:false},
		   		{name:"shareDate",label:"共享时间",align:"center",width:100,sortable:true,formatter:'date',formatoptions:{newformat: 'Y-m-d'} },
		   		{name:'myProfileAttachFile',label:"附件",sortable:false,width:80,formatter:formatterAttachFile}
		   	 ],
		  loadComplete:afterLoad,
		  <pop:JugePermissionTag ename="viewMyProfile">
		  ondblClickRow: doubleClickTable,
		  </pop:JugePermissionTag>
		  onSelectRow:selectRow
	   });
	 $("#shareFileList").trigger("reloadGrid");
	 
	 $("#fastSearch").click(function(){
		 if($("#searchText").val()==""||$("#searchText").val()==null||$("#searchText").val()=='请输入文件名'){
				return;
			}
		 $("#shareFileList").setGridParam({
				url:'${path}/resourcePool/sharingFilesManage/fastSearchSharingFiles.action',
				datatype:'json'
			});
			$("#shareFileList").setPostData({
				"resourcePoolTypeId":resourcePoolType,
		    	"searchType":$("#searchType").val(),
		    	"searchText":$("#searchText").val()
		   	});
			$("#shareFileList").trigger("reloadGrid");
	 });
	 
	 function searchDispatch(){
	    	var data=$("#sharingFile-form").serializeArray();
			$("#shareFileList").setGridParam({
					url:'${path}/resourcePool/sharingFilesManage/searchSharingFiles.action',
					datatype: "json",
					page:1,
					mtype:"post"
				});
				$("#shareFileList").setPostData(data);
			    $("#shareFileList").trigger("reloadGrid");
	    }
	 
	 $("#search").click(function(){
		 var widthWhenAdd=650;
			var heightWhenAdd=350;
			$("#shareFileDailog").createDialog({
				width:widthWhenAdd,
				height:heightWhenAdd,
				title:'共享资料查询-请输入查询条件',
				url:'${path}/resourcePool/sharingFilesManage/dispatch.action?mode=search&resourcePoolTypeId='+resourcePoolType+'&searchType='+$("#searchType").val(),
				buttons :{
					"查询" : function(){
						searchDispatch();
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	 });
	 
});
function doubleClickTable(rowid){
	viewShareFile(rowid);
}
function selectRow(){
	
}
function afterDirectoryChangNode(node){
	$(".tip-yellowsimple").remove();
	resourcePoolType = node.id;
	innerTitle=node.text;
	
	if(resourcePoolType=="publicDirectoryTree-root"){
		resourcePoolType=0;
	}
	$("#shareFileList").setGridParam({
		url:'${path}/resourcePool/sharingFilesManage/findSharingFilesList.action',
		datatype:'json'
	});
	$("#shareFileList").setPostData({
		"resourcePoolTypeId":resourcePoolType,
    	"searchType":$("#searchType").val()
   	});
	$("#shareFileList").trigger("reloadGrid");
}

function reloadList(){
	$("#shareFileList").setGridParam({
		url:"${path}/resourcePool/sharingFilesManage/findSharingFilesList.action",
		datatype: "json",
		postData: {
			'resourcePoolTypeId':resourcePoolType,
			'searchType':$("#searchType").val()
			}
	});
	$("#shareFileList").trigger("reloadGrid");
}

</script>