<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<input id="ids" type="hidden" value="${ids }"/>
<div style="width:100%;height:480px;" >
			<table id="shareFileList"> </table>
</div>
<div id="myProfileDailog"></div>

<script type="text/javascript">	
var resourcePoolType=0;
$(document).ready(function(){
	loadList();
});

function nameFont(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewMyProfile'><a href='javascript:viewMyprofile("+rowData.id+")'>"+rowData.name+"</a></pop:JugePermissionTag>";
}
function loadList(){
	$("#shareFileList").jqGridFunction({	 
		 datatype: "json",
		 height:150,
		 url:'${path}/resourcePool/sharingFilesManage/findSharingFilesListByIds.action?ids='+$("#ids").val(),
		 colModel:[	
		        {name:"id",index:"id",sortable:false,hidden:true},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		   		{name:"name",label:"名称",width:160,sortable:true,align:'center',formatter:nameFont},
		   		{name:"releaseUnit",label:"发文单位（作者）",width:150,sortable:true,align:'center'},
		   		{name:"releaseTime",label:"（发文）时间",align:"center",width:100,sortable:true ,hidden:true,formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
		   		{name:"organization.orgName",label:"共享部门",width:120,align:'center',sortable:false },
		   		{name:"shareUserRealName",label:"共享用户",width:150,align:'center',sortable:false ,hidden:false},
		   		{name:"shareDate",label:"共享时间",align:"center",width:100,sortable:true,formatter:'date',formatoptions:{newformat: 'Y-m-d'} },
		   		{name:'myProfileAttachFile',label:"附件",sortable:false,width:80,align:'center',formatter:formatterAttachFile}
		   	 ],
		  loadComplete:afterLoad,
		  ondblClickRow: viewMyprofile
	   });
}
function viewMyprofile(id){
	var widthWhenAdd=650;
	var heightWhenAdd=300;
	var rowData=  $("#shareFileList").getRowData(id);
	
	$("#myProfileDailog").createDialog({
		width:widthWhenAdd,
		height:heightWhenAdd,
		title:'查看信息',
		url:'${path}/resourcePool/myProfileManage/dispatch.action?mode=view&internalId='+resourcePoolType+'&id='+rowData.encryptId,
		buttons :{
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
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
function formatterAttachFile(el,options,rowData){

	if(rowData.myProfileAttachFile.length>0){
	$("#shareFileList").data( "'"+rowData.id+"'", rowData.myProfileAttachFile);
		return "<div style='clear:both' align='center'><a href='javascript:;' id='myProfile_"+rowData.id+"' style='color:#666666' class='popUpMore' resourcePoolId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}
</script>