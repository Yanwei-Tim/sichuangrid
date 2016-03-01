<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<%request.setAttribute("moduleName",request.getParameter("moduleName"));%>
<%request.setAttribute("lowerCaseModuleName",request.getParameter("moduleName").substring(0,1).toLowerCase()+request.getParameter("moduleName").substring(1));%>
<%request.setAttribute("moduleCName",request.getParameter("moduleCName"));%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<jsp:include page="/common/orgSelectedComponent.jsp"/>
		<pop:JugePermissionTag ename="add${moduleName}">
			<a id="add" href="javascript:void(0)"><span>新增</span></a>
		</pop:JugePermissionTag> 
		<pop:JugePermissionTag ename="update${moduleName}">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag> 
		<pop:JugePermissionTag ename="view${moduleName}">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag> 
		<pop:JugePermissionTag ename="delete${moduleName}">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag> 
		<pop:JugePermissionTag ename="search${moduleName}">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag> 
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a> 
		<pop:JugePermissionTag ename="export${moduleName}">
			<a id="export" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag> 
		
		<a id="mantanceServiceMember" href="javascript:void(0)"><span>维护成员信息</span></a>
		<a id="maintainServiceObject" href="javascript:void(0)"><span>维护服务对象信息</span></a>
		<a id="maintainServiceLog" href="javascript:void(0)"><span>维护服务记录</span></a>
		<div style="float: right; white-space: nowrap;display: block;">
			<select id="displayLevel" name="displayLevel" class="S_object">
				<option value="true" selected="selected">本级</option>
				<option value="false">本级及下辖</option>
			</select>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="${lowerCaseModuleName}List"></table>
		<div id="${lowerCaseModuleName}ListPager"></div>
	</div>
	<div id="${lowerCaseModuleName}Dialog"></div>
	
</div>
<div style="display: none;">
	<pop:JugePermissionTag ename="${lowerCaseModuleName}Management">
		<span id="title">
			<s:property value="#request.name" />
		</span>
	</pop:JugePermissionTag>
</div>
<script type="text/javascript">

var dialogWidth=1090;
var dialogHeight=590;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();
function onOrgChanged(orgId,isgrid){
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#${lowerCaseModuleName}List").setGridParam({
		url:'${path}/baseinfo/serviceTeamManage/serviceManageTeamList.action?displayLevel=true',
		datatype: "json",
		page:1
	});
	$("#${lowerCaseModuleName}List").setPostData({
		"organizationId":orgId,
		"displayLevel":$("#displayLevel").val(),
		"teamClazz":'<s:property value="#request.lowerCaseModuleName" />'
	});
	$("#${lowerCaseModuleName}List").trigger("reloadGrid");

}
$(document).ready(function(){
	$("#${lowerCaseModuleName}List").jqSubGrid({
		subGridPostData:function(data){return {"teamId":data.id,orgId:data["organization.id"]}},
        subGridUrl:"${path}/baseinfo/serviceTeamMember/findServiceTeamMembersForTeam.action",
		datatype: "local",
		//colNames:['id','organization.id','队伍名称','队伍类别','所属区域(网格)','成员数','成立时间','服务对象','服务记录'],
		colNames:colHead,
	   	colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:"organization.id",index:"organization.id",hidden:true,frozen:true},
	        {name:'name',index:"name",width:50},
	        {name:'teamType',formatter:teamTypeFormatter,index:'teamType',width:50,frozen:true},
	        {name:'organization.orgName',index:'organization.orgName',sortable:false,width:150},
	        {name:'memberNums',index:'memberNums',sortable:false,width:50},
	        {name:'establishDate',index:'establishDate',width:50,frozen:true},
	        {name:'serviceObject',index:'id',formatter:viewObjectFormatter,sortable:false,width:50,align:"center"},
	        {name:'serviceRecord',index:'id',formatter:viewRecordFormatter,sortable:false,width:60,align:"center"}
		],
		multiselect:true,
		onSelectAll:function(data){if(toggleButtonState){toggleButtonState();}},
		<pop:JugePermissionTag ename="view${lowerCaseModuleName}">
    	ondblClickRow: function(data){if(doubleClickTable){doubleClickTable(data);}},
	    </pop:JugePermissionTag>
	    onSelectRow: function(data){if(toggleButtonState){toggleButtonState();}},
		loadComplete: function(data){if(afterLoad){afterLoad();}}
	});

	
	function doubleClickTable(data){
		viewDialog(data);
	}
	jQuery("#${lowerCaseModuleName}List").jqGrid('setFrozenColumns');
	
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	
	function viewObjectFormatter(el, options, rowData){
		return "<a href='javascript:viewObjectDialog("+rowData.id+")'><U><font color='#6633FF'>详细</font></U></a>";
	}
	function viewRecordFormatter(el, options, rowData){
		return "<a href='javascript:viewRecordDialog("+rowData.id+")'><U><font color='#6633FF'>详细</font></U></a>";
	}
	

	$("#add").click(function(event){
		if(!checkedOrgId(getCurrentOrgId())){
			return;}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			title:"新增${moduleCName}",
			width: 600,
			height: 400,
			url:'${path}/baseinfo/serviceTeamManage/${lowerCaseModuleName}Dispatch.action?mode=add&dailogName=${lowerCaseModuleName}Dialog',
			buttons: {
				"保存并关闭" : function(event){
					$("#${lowerCaseModuleName}Form").submit();
				},
				"关闭" : function(){
		        	$(this).dialog("close");
				}
			}
		});
		
	  });

	$("#update").click(function(event){
		if($(this).attr("disabled")=="disabled"){
			return;}
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var serviceTeam =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(!checkedOrgId(serviceTeam["organization.id"])){
			return;}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			title:'修改${moduleCName}',
			width: 600,
			height: 400,
			url:'${path}/baseinfo/serviceTeamManage/${lowerCaseModuleName}Dispatch.action?mode=edit&dailogName=${lowerCaseModuleName}Dialog&serviceManageTeam.id='+selectedId,
			buttons: {
				"保存并关闭" : function(event){
				$("#${lowerCaseModuleName}Form").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
		}
		});
	});

	$("#view").click(function(){
		if($(this).attr("disabled")=="disabled"){
			return;}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:"查看${moduleCName}",
			url:'${path}/baseinfo/serviceTeamManage/${lowerCaseModuleName}Dispatch.action?mode=view&dailogName=${lowerCaseModuleName}Dialog&serviceManageTeam.id='+selectedId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});

	$("#delete").click(function(){
		if($(this).attr("disabled")=="disabled"){
			return;}
		
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			var serviceTeam =  $("#${lowerCaseModuleName}List").getRowData(selectedIds[i]);
			if(!checkedOrgId(serviceTeam["organization.id"])){
				return;
			}
		}
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?",
			okFunc: function(){
				$.ajax({
					url:'${path}/baseinfo/serviceTeamManage/deleteServiceTeam.action?mode=delete&selectedIds='+selectedIds,
					success:function(data){
						if(data>0){
						    $.messageBox({message:"成功删除"+data+"个${moduleCName}!"});
							$("#${lowerCaseModuleName}List").trigger("reloadGrid");
						    disableButtons();
						    checkExport();
						}else{
							$.messageBox({message:"请清空组织成员，服务对象，服务记录后再删除组织!"});
						}
					}
				});
			}
		});
	});

	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});

	$("#search").click(function(event){
		$("#${lowerCaseModuleName}Dialog").createDialog({
			title:"查询${moduleCName}",
			width: 600,
			height: 240,
			url:'${path}/baseinfo/serviceTeamManage/${lowerCaseModuleName}Dispatch.action?mode=search&dailogName=${lowerCaseModuleName}Dialog',
			buttons: {
				"查询" : function(event){
					//var str = $("#searchAutonomyTeamForm").serialize();
					search${moduleName}();
					$(this).dialog("close");
				},
				"关闭" : function(){
		        	$(this).dialog("close");
				}
			}
		
		});
	});

	//导出功能
	$("#export").click(function(event){
		if($("#${lowerCaseModuleName}List").getGridParam("records")>0){
			var postData = $("#${lowerCaseModuleName}List").getPostData();
			//alert(postData.searchServiceManageTeamVo);
			$("#${lowerCaseModuleName}List").setPostData($.extend(postData,{"displayLevel":$("#displayLevel").val(),"searchServiceManageTeamVo.displayLevel":$("#displayLevel").val(),"searchServiceManageTeamVo.teamClazz":'<s:property value="#request.lowerCaseModuleName" />',"teamName":"${moduleCName}清单"}));
			$("#${lowerCaseModuleName}Dialog").createDialog({
				width: 250,
				height: 200,
				title:"导出${moduleCName}信息",//"+document.title+"
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'${lowerCaseModuleName}List',
					downloadUrl:'${path}/baseinfo/searchServiceManageTeam/downloadSearchTeam.action'
				},
				buttons: {
		   			"导出" : function(event){
						$("#exceldownload").submit();
		        		$(this).dialog("close");
	   				},
		   			"关闭" : function(){
		        		$(this).dialog("close");
		   			}
				},
				shouldEmptyHtml:false
			});
		}else{
			return;
		}
	});
	
	$("#mantanceServiceMember").click(function(event){
		if($(this).attr("disabled")=="disabled"){
			return;}
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var serviceTeam =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(!checkedOrgId(serviceTeam["organization.id"])){
			return;}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 840,
			height: 600,
			title:'维护成员信息',             
			url:'/baseinfo/serviceTeamMember/dispatchOperate.action?mode=list&dailogName=${lowerCaseModuleName}Dialog&organizationId='+getCurrentOrgId()+'&teamId='+selectedId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	
	$("#maintainServiceObject").click(function(event){
		if($(this).attr("disabled")=="disabled"){
			return;}
		var selectedId =  getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		var serviceTeam =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(!checkedOrgId(serviceTeam["organization.id"])){
			return;}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 840,
			height: 600,
			title:'维护服务对象信息',             
			url:"${path}/baseinfo/serviceTeam/serviceObject/dispatchOperate.action?mode=show&dailogName=${lowerCaseModuleName}Dialog&serviceObject.serviceTeamId="+selectedId+"&orgId"+getCurrentOrgId(),
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#maintainServiceLog").click(function(event){
		if($(this).attr("disabled")=="disabled"){
			return;}
		var selectedId =  getSelectedIdLast();
		if(null == selectedId){
	 		return;
		}
		var serviceTeam =  $("#${lowerCaseModuleName}List").getRowData(selectedId);
		if(!checkedOrgId(serviceTeam["organization.id"])){
			return;}
		$("#${lowerCaseModuleName}Dialog").createDialog({
			width: 900,
			height: 615,
			title:'维护服务记录信息',             
			url:"${path}/baseinfo/serviceRecordManage/dispatchForTeam.action?mode=page&serviceRecord.manageTeam.id="+selectedId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	function toggleButtonState(){
	  	var selectedIds=$("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
	  	var selectedRowCount=selectedIds.length;
	  	setDeleteButtonEnabled(selectedRowCount>0);
	  	setOtherButtonEnabled(selectedRowCount==1);
	}
	function setDeleteButtonEnabled(enabled){
		if (enabled){
			$("#delete").buttonEnable();
		}else{
			$("#delete").buttonDisable();
		}
	}
	function setOtherButtonEnabled(enabled){
		if (enabled){
			$("#update").buttonEnable();
			$("#view").buttonEnable();
			$("#mantanceServiceMember").buttonEnable();
			$("#maintainServiceObject").buttonEnable();
			$("#maintainServiceLog").buttonEnable();
		}else{
			$("#update").buttonDisable();
			$("#view").buttonDisable();
			$("#mantanceServiceMember").buttonDisable();
			$("#maintainServiceObject").buttonDisable();
			$("#maintainServiceLog").buttonDisable();
		}
	}
	function afterLoad(){
		disableButtons();
		checkExport();
	}
	function disableButtons(){
	  	setDeleteButtonEnabled(false);
	  	setOtherButtonEnabled(false);
	}
	function checkExport(){
		if($("#${lowerCaseModuleName}List").getGridParam("records")>0
				|| $("#${lowerCaseModuleName}List").getGridParam("selrow")!=null){
			$("#export").buttonEnable();
		}else{
			$("#export").buttonDisable();

		}
	}

	function getSelectedIdLast(){
		var selectedId="";
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function checkedOrgId(orgID){
		if(orgID != USER_ORG_ID){
			$.messageBox({
				message:"当前用户只能操作所在层级的组织！",
				level: "error"
			 });
			return false;
		}else{
			return true;
			}
	}
	
	//切换本级及下辖功能
	$("#displayLevel").change(function(event){
		currentOrgId=getCurrentOrgId()
		$("#${lowerCaseModuleName}List").setGridParam({
			url:'${path}/baseinfo/serviceTeamManage/serviceManageTeamList.action?displayLevel='+$("#displayLevel").val(),
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#${lowerCaseModuleName}List").setPostData({
			"organizationId":currentOrgId,
			"teamClazz":'<s:property value="#request.lowerCaseModuleName" />'
		});
	    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
	   // setDefaultButtonState();
	});
	
	function search${moduleName}(){
		$("#${lowerCaseModuleName}List").setGridParam({			  
			url:"${path}/baseinfo/searchServiceManageTeam/findServiceManageTeamsByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#search${moduleName}Form").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
 			if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}
			
		}
		$("#${lowerCaseModuleName}List").setPostData($.extend({"organizationId":getCurrentOrgId(),"searchServiceManageTeamVo.displayLevel":$("#displayLevel").val()},dataJson));
	    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
	   // setDefaultButtonState();
	}

});

/*
 *查看该组织的服务对象
 */
function viewObjectDialog(teamId){
	$("#${lowerCaseModuleName}Dialog").createDialog({
		title:"查看服务对象",
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/baseinfo/serviceTeam/serviceObject/dispatchOperate.action?mode=page&dailogName=${lowerCaseModuleName}Dialog&orgId='+getCurrentOrgId()+'&serviceObject.serviceTeamId='+teamId,
		buttons: {
			"关闭" : function(){
	        	$(this).dialog("close");
			}
		}
	});
	//$("#nav").css("display","none");
}
/*
 *查看该组织的服务记录
 */
function viewRecordDialog(teamId){
	$("#${lowerCaseModuleName}Dialog").createDialog({
		title:"查看服务记录",
		width: dialogWidth,
		height: dialogHeight,
		url:'${path}/baseinfo/searchServiceRecordManage/prepareDispatch.action?mode=page&fromSource=team&searchServiceRecordVo.teamId='+teamId,
		buttons: {
			"关闭" : function(){
	        	$(this).dialog("close");
			}
		}
	});
}

</script>