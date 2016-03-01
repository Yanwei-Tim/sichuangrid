<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
   
<div class="ui-corner-all" id="nav">

	<input type="text" value="请输入目录名" name="searchText" id="searchText" style="width:175px;left:300px;" class="searchtxt"  maxlength="18" onblur="value=(this.value=='')?'请输入目录名':this.value;" onfocus="value=(this.value=='请输入目录名')?'':this.value;"/>
	
    <button id="search" class="ui-icon ui-icon-refresh searchbtnico" style="border:0;background-color:transparent; position:absolute;top:10px;left:155px;cursor:pointer;outline: none;"></button>
   <span class="lineBetween"></span>
<pop:JugePermissionTag
	ename="adddirectorySetting">
	<a id="add" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>新增子目录 </span></a>
</pop:JugePermissionTag>
	<a id="reload" href="javascript:void(0)"><span><strong
		class="ui-ico-refresh"></strong>刷新</span></a>
</div>
<div style="clear: both;" align="center"></div>
<div style="width: 100%;">
<table id="directorySettingTree"></table>
<input type="hidden" id="directorySetting-parent-id" /></div>
<div id="directorySettingDailog"></div>
<script type="text/javascript">
$("#directorySettingTree").delegate(".deleteDirectory","click",function(){
	var deleteId=$(this).attr("deleteId");
	var directory = $("#directorySettingTree").getRowData(deleteId);
	if(directory.directoryType==1){
		$.messageBox({level:'warn',
			message:'该目录为公共子目录，无法删除！'});
		return ;
	}
	if(directory.count>0){
		$.messageBox({level:'warn',
			message:'该目录下含有子目录，无法删除<br>请先将子目录删除后，再重新删除该目录。'});
		return ;
	}else{
		$.confirm({
			title:"确认删除",
			message:"目录信息删除后，将无法恢复,您确认删除该目录信息吗?",
			width: 400,
			okFunc: function(){deleteDirectorySetting(directory.id);}
		});
	}
});

	 function deleteDirectorySetting(selectedId){

			if(selectedId==null||selectedId==''){
				 return;
			}
			var rowData=  $("#directorySettingTree").getRowData(selectedId);
			$.ajax({
				async :false,
				url:'${path}/resourcePool/directorySettingManage/deleteDirectorySetting.action?directorySetting.id='+rowData.encryptId,
				success:function(isDelete){
					if(isDelete == true){
						//$("#directorySettingTree").delTreeNode(selectedId);
						$.messageBox({ message:"成功删除目录信息!"});
						$("#directorySettingTree").trigger("reloadGrid");
						return;
					}
		            $.messageBox({message:isDelete,level: "error"});
		        }
			});
		}

	$("#directorySettingTree").delegate(".updateDirecory","click",function(){
		var updateId=$(this).attr("updateId");
		var directory =  $("#directorySettingTree").getRowData(updateId);
		updateDirectorySetting(directory);
	});
	
	 function updateDirectorySetting(data){
	 		if(data.id==999){
	 			$.messageBox({level:'warn',
					message:'个人目录无法修改！'});
				return ;
	 		}
		 	if(data.id==-1){
		 		$.messageBox({level:'warn',
					message:'本级目录无法修改！'});
				return ;
		 	}
			$("#directorySettingDailog").createDialog({
				width:550,
				height:180,
				title:'修改资料目录',
				url:"${path}/resourcePool/directorySettingManage/dispatchdirectoryTrunk.action?mode=edit&directorySetting.id="+data.encryptId,
				buttons :{
					"保存" : function(){
						$("#resourceDirectory-form").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}
		 
	 $("#directorySettingTree").delegate(".up","click",function(){
			var upId=$(this).attr("upId");
			var directory =  $("#directorySettingTree").getRowData(upId);
	 	    $.ajax({
				async :false,
				url:'${path}/resourcePool/directorySettingManage/upDirectorySetting.action?moveMode=up&directorySetting.id='+directory.encryptId+'&directorySetting.parentPersonalDirectory.id='+directory.parentId+'&directorySetting.indexId'+directory.indexid,
				success:function(isMove){
					if(isMove == true){
						$("#directorySettingTree").trigger("reloadGrid");
						return;
					}
		            $.messageBox({message:isMove,level: "error"});
		        }
			});
		});
		
	 $("#directorySettingTree").delegate(".downDirectory","click",function(){
		 var downId=$(this).attr("downId");
		 var directory =  $("#directorySettingTree").getRowData(downId);
	 	    $.ajax({
					async :false,
					url:'${path}/resourcePool/directorySettingManage/upDirectorySetting.action?moveMode=down&directorySetting.id='+directory.encryptId+'&directorySetting.parentPersonalDirectory.id='+directory.parentId+'&directorySetting.indexId'+directory.indexid,
					success:function(isMove){
						if(isMove == true){
							$("#directorySettingTree").trigger("reloadGrid");
							return;
						}
			            $.messageBox({message:isMove,level: "error"});
			        }
				});
	 });
	
	
$(document).ready(function(){
	var height =500;
	var width = 100;
	 $("#directorySettingTree").jqTreeGrid({
		 url: "${path}/resourcePool/directorySettingManage/findDirectorySettingForList.action",
		 height:height,
		 width:width,
		 colModel:[		   		
		   		{name:"name",label:"资料库目录",width:100,sortable:false},
		   		{name:"",label:"操作",width:30,sortable:false ,formatter:operateFormatter},
		   		{name:"",label:"移动",width:30,sortable:false ,formatter:operateFormove},
		   		{name:"id",hidden:true,key:true,sortable:false},
		   	 	{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		   		{name:"parentId",label:"parentId",sortable:false,hidden:true},
		   		{name:"level",label:"level",sortable:false,hidden:true},
		   		{name:"count",label:"count",sortable:false,hidden:true},
		   		{name:"indexid",label:"indexid",sortable:false,hidden:true},
		   		{name:"directoryType",label:"directoryType",sortable:false,hidden:true}
		   	 ],
	   	 treeReader : {
	   	   	level_field: "level",
			parent_id_field: "parentId",
			leaf_field: "leaf",
			expanded_field: "expanded"
	   	 },
		  loadComplete:afterLoad,
		  //ondblClickRow: doubleClickTable,
		  onSelectRow:selectRow
	   });

	 $("#reload").click(function(){
		 $("#searchText").attr("value","请输入目录名");
		 $("#directorySettingTree").trigger("reloadGrid");
		 $("#directorySettingTree").resetSelection();
		});
	 $("#search").click(function(){
		 $("#searchText").val("");
     });

	 function operateFormatter(el, options, rowData){
			if(rowData.id==0||rowData.name=='公共目录'||rowData.parentId==-1){
				return '';
			}
			return '<div align="center"><img id="'+rowData.id+'_delete" deleteId="'+rowData.id+'" class="deleteDirectory"  title="删除"  src="${resource_path}/resource/system/images/directorySettingManagement/delete.png">&nbsp;&nbsp;<img  id="'+rowData.id+'_update" updateId="'+rowData.id+'" class="updateDirecory" title="修改"  src="${resource_path}/resource/system/images/directorySettingManagement/edit.png"></div>';
		}
  function operateFormove(el, options, rowData){
 	 if(rowData.id==0||rowData.name=='公共目录'||rowData.parentId==-1){
				return '';
			}
			return '<div align="center"><img id="'+rowData.id+'_up" class="up" upId="'+rowData.id+'" title="上移"   src="${resource_path}/resource/system/images/directorySettingManagement/up.png" >&nbsp;&nbsp;<img  id="'+rowData.id+'_down" class="downDirectory" downId="'+rowData.id+'" title="下移"  src="${resource_path}/resource/system/images/directorySettingManagement/down.png"></div>';
  }
	
	 function afterLoad(){
			var treeList=$("#directorySettingTree").find(".tree-wrap-ltr");
			for(var i=0;i<treeList.length;i++){
				$(treeList[i]).prependTo($(treeList[i]).parent().parent().children("td")[0]);
			}
		}
	 function doubleClickTable(){
			if($("#directorySettingTree").getGridParam("selrow")!=0){
				viewDirectory($("#directorySettingTree").getGridParam("selrow"));
			}
			
		}
		


	 	 $("#searchText").autocomplete({
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
						url:PATH+"/resourcePool/searchDirectorySetting/searchListParentNodeIds.action?directorySetting.id="+ui.item.id,
						success:function(data){
							$("#directorySettingTree").expandAppointRow(data);
						}
					});
	 				$("#directorySettingTree").setSelection(ui.item.id);
	 		}
	 	});

	
	 function selectRow(){
		 var selectedId = $("#directorySettingTree").getGridParam("selrow");
			var directory =  $("#directorySettingTree").getRowData(selectedId);
	}
	 $("#add").click(function(){
			var selectedId = $("#directorySettingTree").getGridParam("selrow");
			var parentDirect =  $("#directorySettingTree").getRowData(selectedId);
			if(selectedId==null){
				$.messageBox({level:'warn',
					message:'未选中父目录，无法新增！'});
				return ;
			}
			if(parentDirect.directoryType=='1'){
				$.messageBox({level:'warn',
					message:'该目录为公共子目录，无法新增！'});
				return ;
			}
			homeDirectory(parentDirect);
		});
	 function homeDirectory(parentDirect){
			$("#directorySettingDailog").createDialog({
				width:550,
				height:180,
				title:'新增资料库目录',
				url:"${path}/resourcePool/directorySettingManage/dispatchdirectoryTrunk.action?mode=add&parentDirect.id="+parentDirect.id+"&parentDirect.level="+parentDirect.level+"&parentDirect.directoryType="+parentDirect.directoryType,
				buttons :{
					"保存并关闭" : function(){
						$("#isSubmit").val("true");
						$("#resourceDirectory-form").submit();
					},
					/* "保存并继续" : function(){
						$("#isSubmit").val("false");
						$("#resourceDirectory-form").submit();
					}, */
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}

		
});
      
</script>