<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript" src="${resource_path }/resource/system/js/myProfileTreeManage.js"></script>
<div class="center-left">
     <div>
     	<div class="ui-widget autosearch">
			<input type="text" value="请输入文件名" name="autocompleteText" id="autocompleteText" style="width:153px;height:25px;top:0px;border:1px solid #A8BECE;background:#fff;" class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'请输入文件名':this.value;" onfocus="value=(this.value=='请输入文件名')?'':this.value;"/>
		   <button id="refreshOrgTree" class="ui-icon ui-icon-refresh searchbtnico" style="border:0;width:16px;height:16px;background-color:transparent; position:absolute;top:7px;left:135px;cursor:pointer;outline: none;background:url(/resource/system/js/jqueryui/default/images/ui-icons_0078ae_256x240.png) no-repeat -64px -80px;"></button>
      	</div>
    </div>
        <br>
	<div class="orgObjContent" style="height:500px">
		<div id="myProfileDirectoryTree"></div>
	</div>
</div>

<div class="center-right">
	<div class="content" >
	  <div class="ui-corner-all" id="nav">
	     <div class="btnbanner btnbannerData">
				<select id="searchType" name="" style="height:25px;width:100px" class="S_object">
					<option value="0" selected="selected">全部</option>
		 			<option value="1">仅限自己查看</option>
		 			<option value="2">开放给他人查看</option>
				</select>
			
			<input type="text" value="请输入文件名" name="searchText" id="searchText" style="top:-10px;width:150px;left:300px;" class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'请输入文件名':this.value;" onfocus="value=(this.value=='请输入文件名')?'':this.value;"/>
				<button id="refreshsearchText"
					class="ui-icon ui-icon-refresh searchbtnico"
					style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 260px; cursor: pointer; outline: none;"></button>

	       <a id="fastSearch" href="javascript:void(0)"><span>搜索</span></a>
	       <pop:JugePermissionTag ename="searchMyProfile">
				<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		    <span class="lineBetween"></span>
			<pop:JugePermissionTag ename="addMyProfile">
				<a id="add" href="javascript:void(0)"><span>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteMyProfile">
				<a id="delete"  href="javascript:void(0)"><span>批量删除</span></a>
			</pop:JugePermissionTag>
				<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
			<pop:JugePermissionTag ename="profileSharing">
				<a id="sharing" href="javascript:void(0)"><span>资料共享</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="profileCancelSharing">
				<a id="cancelSharing" href="javascript:void(0)"><span>取消共享</span></a>
			</pop:JugePermissionTag>
		</div>
		</div>
		
		<div style="clear: both;"></div>
		<div style="width:100%;" >
			<table id="myProfileList"> </table>
			<div id="myProfileListPager" ></div>
		</div>
			
	</div>
	<div id="myProfileDailog"></div>
	<div id="fileSharingDailog"></div>
	<div id="ObjDialog"></div>
</div>

<script type="text/javascript">	
var resourcePoolType=0;
var innerTitle;
var tree;
function deleteMyprofile(ids){
	
	if(ids==null||ids==''){
		$.messageBox({message:"请选择所要删除的记录!",level:"warn"});
		 return;
	}
	var selectedIds=deleteOperatorByEncrypt('myProfile',ids,'encryptId');
	$.ajax({
		async :false,
		url:'${path}/resourcePool/myProfileManage/deleteMyProfile.action',
		type:'post',
		data:{
			'ids':selectedIds
			},
		success:function(isDelete){
			if(isDelete == true){
				$("#myProfileList").trigger("reloadGrid");
				$.messageBox({ message:"成功删除文件信息!"});
				return;
			}
            $.messageBox({message:isDelete,level: "error"});
        }
	});
}
function updateMypro(event,ids){
	var widthWhenAdd=750;
	var heightWhenAdd=450;
	if(ids==null||ids==''|| ids.length>1){
		return;
	}
	var rowData=  $("#myProfileList").getRowData(ids);
	$("#myProfileDailog").createDialog({
		width:widthWhenAdd,
		height:heightWhenAdd,
		title:'修改'+innerTitle+'信息',
		url:'${path}/resourcePool/myProfileManage/dispatch.action?mode=edit&internalId='+resourcePoolType+'&id='+rowData.encryptId,
		buttons :{
			"保存并关闭" : function(){
				$("#myProfile-form").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}

function deleteMypro(event,id){
	$.confirm({
		title:"确认删除",
		message:"信息删除后，将无法恢复,您确认删除该条信息吗?",
		width: 400,
		okFunc: function(){deleteMyprofile(id);}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function viewMyprofile(id){
	var widthWhenAdd=650;
	var heightWhenAdd=300;
	var rowData=  $("#myProfileList").getRowData(id);
	
	$("#myProfileDailog").createDialog({
		width:widthWhenAdd,
		height:heightWhenAdd,
		title:'查看'+innerTitle+'信息',
		url:'${path}/resourcePool/myProfileManage/dispatch.action?mode=view&internalId='+resourcePoolType+'&id='+rowData.encryptId,
		buttons :{
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function nameFont(el,options,rowData){
	return "<pop:JugePermissionTag ename='viewMyProfile'><a href='javascript:viewMyprofile("+rowData.id+")'>"+rowData.name+"</a></pop:JugePermissionTag>";
}
function operateFormatter(el, options, rowData){
	return "<pop:JugePermissionTag ename='updateMyProfile'><a href='javascript:;' onclick='updateMypro(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteMyProfile'><a href='javascript:;' onclick='deleteMypro(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}
function formatterAttachFile(el,options,rowData){

	if(rowData.myProfileAttachFile.length>0){
	$("#myProfileList").data( "'"+rowData.id+"'", rowData.myProfileAttachFile);
		return "<div style='clear:both' align='center'><a href='javascript:;' id='myProfile_"+rowData.id+"' style='color:#666666' class='popUpMore' resourcePoolId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}

function shareStateFont(el,options,rowData){
	
	if(rowData.shareState==<s:property value="@com.tianque.resourcePool.domain.MyProfile@shareState_YES"/>){
		return "已共享";
	}
	if(rowData.shareState==<s:property value="@com.tianque.resourcePool.domain.MyProfile@shareState_NO"/>){
		return "未共享";
	}
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
$(document).ready(function(){
	function layoutFun(){
		clearTimeout(window._fileListTimer);
		$(".orgObjContent").height($(".ui-layout-center").height()-$("#thisCrumbs").height()-30)
	}
	layoutFun();
	$(window).resize(function(){
		window._fileListTimer=setTimeout(function(){
			layoutFun();
		},200);
	});
	
	$("#refreshsearchText").click(function(){
		$("#searchText").val("");
	})
	$("#refreshOrgTree").click(function(){
		$("#autocompleteText").val("");
	})
	$("#searchType").change(function(){
		reloadList();
	});
	$("#fastSearch").click(function(){
		if($("#searchText").val()==""||$("#searchText").val()==null||$("#searchText").val()=='请输入文件名'){
			return;
		}
		$("#myProfileList").setGridParam({
			url:'${path}/resourcePool/myProfileManage/fastSearchMyProfile.action',
			datatype:'json'
		});
		$("#myProfileList").setPostData({
			"resourcePoolTypeId":resourcePoolType,
	    	"searchType":$("#searchType").val(),
	    	"searchText":$("#searchText").val()
	   	});
		$("#myProfileList").trigger("reloadGrid");
	});
	$("#refresh").click(function(){
		$("#searchText").attr("value","请输入文件名");
		reloadList();
	});
    function searchDispatch(){
    	var data=$("#myProfile-form").serializeArray();
		$("#myProfileList").setGridParam({
				url:'${path}/resourcePool/myProfileManage/searchMyProfile.action',
				datatype: "json",
				page:1,
				mtype:"post"
			});
			$("#myProfileList").setPostData(data);
		    $("#myProfileList").trigger("reloadGrid");
    }
	$("#search").click(function(){
		
		var widthWhenAdd=650;
		var heightWhenAdd=350;
		$("#myProfileDailog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'我的资料查询-请输入查询条件',
			url:'${path}/resourcePool/myProfileManage/dispatch.action?mode=search&resourcePoolTypeId='+resourcePoolType+'&searchType='+$("#searchType").val(),
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
	$("#add").click(function(){
		if(resourcePoolType==0){
			$.messageBox({level:'warn',
				message:'该资料类型为根目录类型，不能新增！'});
			return ;
		}
		var widthWhenAdd=750;
		var heightWhenAdd=450;
		$("#myProfileDailog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'新增'+innerTitle+'信息',
			url:'${path}/resourcePool/myProfileManage/dispatch.action?mode=add&internalId='+resourcePoolType+'&myProfile.resourcePoolType.id='+resourcePoolType,
			buttons :{
				"保存并关闭" : function(){
					$("#myProfile-form").submit();
					$("#isSubmit").val("true");
				},
				"保存并继续" : function(){
					$("#myProfile-form").submit();
					$("#isSubmit").val("false");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		
	});


	$("#delete").click(function(){
		var ids=getSelectRows();
		if(ids.length==0){
			$.messageBox({level:"warn",
				message:'没有选中任何文件，可供删除！'});
			return ;
		}else{
			$.confirm({
				title:"确认删除",
				message:"信息删除后，将无法恢复,您确认删除所选中的信息吗?",
				width: 400,
				okFunc: function(){deleteMyprofile(ids);}
			});
		}
	});
	
	
	
	$("#cancelSharing").click(function(){
		var selectedIds = getSelectRows();
		var cancelShare = 0;
		var share = 0;
		if(selectedIds.length==0){
			$.messageBox({level:'warn',
				message:'没有选中任何文件，可供取消共享！'});
			return ;
		}
		for(var i = 0;i<selectedIds.length;i++){
			var row=$("#myProfileList").getRowData(selectedIds[i]);
		   if(row.shareState == "已共享" ){
		   		cancelShare++;
		   	}else{
		   		share++;
		   	}
		}
		if(cancelShare==0&&share>0){
			$.messageBox({level:'warn',
				message:'只有共享过的文件才能取消共享！'});
			return ;
		}
		if(cancelShare>0&&share>0){
			$.messageBox({level:'warn',
				message:'选中的文件含有未共享的文件不能取消共享！'});
			return ;
		}else{
			$.confirm({
				title:"确认取消共享",
				message:"您确认取消资料共享吗?",
				width: 400,
				okFunc: function(){cancelSharing(selectedIds);}
			});
		}
		
		
	});
	function cancelSharing(selectedIds){
		if(selectedIds==null||selectedIds==''){
			 return;
		}
		var allValue=deleteOperatorByEncrypt('myProfile',selectedIds,'encryptId');
		$.ajax({
			async :false,
			url:'${path}/resourcePool/myProfileManage/cancelSharingMyProfile.action',
			type:'post',
			data:{
				'ids':allValue
				},
			success:function(isDelete){
				if(isDelete == true){
					$("#myProfileList").trigger("reloadGrid");
					$.messageBox({ message:"成功取消文件共享!"});
					return;
				}
		        $.messageBox({message:isDelete,level: "error"});
		    }
		});
	}
	$("#sharing").click(function(){
		var selectedIds =getSelectRows();
		var len = selectedIds.length;
		if(selectedIds.length==0){
			$.messageBox({level:'warn',
				message:'没有选中任何文件，可供共享！'});
			return ;
		}
		var cancelShare = 0;
		var share = 0;
		for(var i = 0;i<len;i++){
			var row=$("#myProfileList").getRowData(selectedIds[i]);
		   if(row.shareState == 2||row.shareState == "2" ){
		   		cancelShare++;
		   	}else{
		   		share++;
		   	}
		}
		
		if(cancelShare>0&&share==0){
			$.messageBox({level:'warn',
				message:'只有未共享过的文件才能共享！'});
			return ;
		}
		if(cancelShare>0&&share>0){
			$.messageBox({level:'warn',
				message:'选中的文件含有共享过的文件不能再共享！'});
			return ;
		}
		var allValue=deleteOperatorByEncrypt('myProfile',selectedIds,'encryptId');
		$("#fileSharingDailog").createDialog({
			width:650,
			height:500,
			title:'共享资料',
			url:'${path}/resourcePool/myProfileManage/dispatch.action?mode=sharing&ids='+allValue+"&identification=myProfile",
			buttons :{
				"保存" : function(){
					$("#fileSharing-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
				
		});
		
	});
	function getSelectRows(){
		return $("#myProfileList").jqGrid("getGridParam", "selarrrow");
	}


	function addFileWhenSelectRow(selectedId){
		if(selectedId==null){
			return;
		}
		var rowData = $("#myProfileList").getRowData(selectedId);
		$.ajax({
			url:"${path}/resourcePool/myProfileManage/findMyProfileAttachFileByMyProfileId.action?resourcePoolTypeId="+selectedId,
			success:function(attachFileList){
				var popMoreData = [];
				if(null!=attachFileList){
					for(var j = 0; j < attachFileList.length; j++){
						popMoreData[j]={id:attachFileList[j].id, url:'${path}/resourcePool/myProfileManage/downloadResourcePoolAttachFile.action?myProfileAttachFile.id='+attachFileList[j].id, text:attachFileList[j].fileName,clickFun:function(){}};
					}
				}
				$("#myProfile_"+rowData.id).popUpMore({data: popMoreData});
			}
		});
	}
	
	var centerHeight=$("div.ui-layout-center").height();
	$(".orgTree").height(centerHeight-62);
	$(".center-right").height(centerHeight-30);
	tree=$("#myProfileDirectoryTree").initMyProfileTree();
	
	$.addClick(tree,afterChangNode);
	
	
	 $("#myProfileList").jqGridFunction({	 
		 multiselect:true,
		 url:'${path}/resourcePool/myProfileManage/findMyProfileForList.action?resourcePoolTypeId=0&searchType='+$("#searchType").val(),
		 colModel:[	
		        {name:"id",index:"id",sortable:false,hidden:true},
		        {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		        {name:"name", index:"operate",label:"操作",width:80,align:"center",sortable:false,formatter:operateFormatter,frozen : true},
		   		{name:"name",label:"名称",width:180,sortable:true,formatter:nameFont},
		   		{name:"resourcePoolType.name",label:"目录",width:180,sortable:true},
		   		{name:"releaseUnit",label:"发文单位(作者)",width:180,hidden:false,sortable:true},
		   		{name:"createDate",label:"创建时间",width:150,align:"center",sortable:true ,formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
		   		//{name:'shareStateStr',label:"是否共享",align:"center",sortable:false,width:80},
		   		{name:"shareState",label:"是否共享",width:80,align:"center",sortable:true,formatter:shareStateFont},
		   		{name:'myProfileAttachFile',label:"附件",sortable:false,width:80,formatter:formatterAttachFile},
		   		{name:'releaseTime',label:"发文时间",sortable:false,width:80,formatter:'date',formatoptions:{newformat: 'Y-m-d'},hidden:true},
		   		{name:'documentSubject',label:"文件主题",sortable:false,width:80,hidden:true},
		   		{name:'organization.orgName',label:"创建部门",sortable:false,width:80,hidden:true}
		   	 ],
		  loadComplete:afterLoad,
		  <pop:JugePermissionTag ename="viewMyProfile">
		  ondblClickRow: doubleClickTable,
		  </pop:JugePermissionTag>
		  onSelectAll:true,
		  onSelectRow:selectRow
	   });
	 $("#myProfileList").trigger("reloadGrid");
	 
	 $("#autocompleteText").autocomplete({
			source: function(request, response) {
				$.ajax({
					url: PATH+"/resourcePool/searchDirectorySetting/searchDirectorySetting.action",
					data:{"directorySetting.name":function(){return request.term;}},
					success: function(data) {
						response($.map(data, function(item) {
							return {
								label: item.name+",",
								value: item.name,
								id: item.id
							}
						}))
					},
					error : function(){
						$.messageBox({
							message:"搜索失败，请重新登入！",
							level:"error"
						});
					}
				
				})
			},
		select: function(event, ui) {	
				$.ajax({
					url:PATH+"/resourcePool/searchDirectorySetting/searchParentNodeIds.action?directorySetting.id="+ui.item.id,
					success:function(data){
						$.searchChild(tree,data);
					}
			});
		}
	});


	
	 
});
function doubleClickTable(rowid){
	viewMyprofile(rowid);
}
function selectRow(){
}
function afterChangNode(node){
	$(".tip-yellowsimple").remove();
	resourcePoolType = node.id;
	innerTitle=node.text;
	$("#autocompleteText").val(innerTitle);
	if(resourcePoolType=="myProfileDirectoryTree-root"){
		resourcePoolType=0;
	}else{
	}
	
	$("#myProfileList").setGridParam({
		url:'${path}/resourcePool/myProfileManage/findMyProfileForList.action',
		datatype:'json',
		postData: {
			'resourcePoolTypeId':resourcePoolType,
			'searchType':$("#searchType").val()
			}
	});
	
	if('4' == resourcePoolType ||
			'5' == resourcePoolType ||
				'6' == resourcePoolType){
		var dataModel = [	
		 		        {name:"id",index:"id",sortable:false,hidden:true},
		 		      	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
				        {name:"name", index:"operate",label:"操作",width:80,align:"center",sortable:false,formatter:operateFormatter,frozen : true},
				   		{name:"name",label:"名称",width:180,sortable:true,formatter:nameFont},
				   		{name:"resourcePoolType.name",label:"目录",width:180,sortable:true},
				   		{name:"releaseUnit",label:"发文单位(作者)",width:180,hidden:false,sortable:true},
				   		{name:"createDate",label:"创建时间",width:150,align:"center",sortable:true ,formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
				   		//{name:'shareStateStr',label:"是否共享",align:"center",sortable:false,width:80},
				   		{name:"shareState",label:"是否共享",width:80,align:"center",sortable:true,formatter:shareStateFont},
				   		{name:'myProfileAttachFile',label:"附件",sortable:false,width:80,formatter:formatterAttachFile},
				   		{name:'releaseTime',label:"发文时间",sortable:false,width:80,formatter:'date',formatoptions:{newformat: 'Y-m-d'},hidden:true},
				   		//{name:'documentSubject',label:"文件主题",sortable:false,width:80,hidden:true},
				   		{name:'organization.orgName',label:"创建部门",sortable:false,width:80,hidden:true}
				   	 ];
	}else{
		var dataModel = [	
			 		        {name:"id",index:"id",sortable:false,hidden:true},
			 		       	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
					        {name:"name", index:"operate",label:"操作",width:80,align:"center",sortable:false,formatter:operateFormatter,frozen : true},
					   		{name:"name",label:"名称",width:180,sortable:true,formatter:nameFont},
					   		{name:"resourcePoolType.name",label:"目录",width:180,sortable:true},
					   		{name:"releaseUnit",label:"发文单位(作者)",width:180,hidden:false,sortable:true},
					   		{name:"createDate",label:"创建时间",width:150,align:"center",sortable:true ,formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
					   		//{name:'shareStateStr',label:"是否共享",align:"center",sortable:false,width:80},
					   		{name:"shareState",label:"是否共享",width:80,align:"center",sortable:true,formatter:shareStateFont},
					   		{name:'myProfileAttachFile',label:"附件",sortable:false,width:80,formatter:formatterAttachFile},
					   		{name:'releaseTime',label:"发文时间",sortable:false,width:80,formatter:'date',formatoptions:{newformat: 'Y-m-d'},hidden:true},
					   		{name:'documentSubject',label:"文件主题",sortable:false,width:80,hidden:true},
					   		{name:'organization.orgName',label:"创建部门",sortable:false,width:80,hidden:true}
					   	 ];
	}
	
	redrawJqGrid(dataModel,resourcePoolType,$("#searchType").val());
	$("#myProfileList").trigger("reloadGrid");
}
/**
 * 重新绘制JqGrid
 */
function redrawJqGrid(dataModel,resourcePoolType,searchType){
	jQuery('#myProfileList').GridUnload();
	$("#myProfileList").jqGridFunction({	 
		 multiselect:true,
		 url:'${path}/resourcePool/myProfileManage/findMyProfileForList.action?resourcePoolTypeId='+resourcePoolType+'&searchType='+searchType,
		 colModel:dataModel,
		  loadComplete:afterLoad,
		  <pop:JugePermissionTag ename="viewMyProfile">
		  ondblClickRow: doubleClickTable,
		  </pop:JugePermissionTag>
		  onSelectAll:true,
		  onSelectRow:selectRow
	   });
}
	
function reloadList(){
	$("#myProfileList").setGridParam({
		url:'${path}/resourcePool/myProfileManage/findMyProfileForList.action',
		datatype:'json',
		postData: {
			'resourcePoolTypeId':resourcePoolType,
			'searchType':$("#searchType").val()
			}
	});
	$("#myProfileList").trigger("reloadGrid");
}
function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#myProfileList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}


</script>