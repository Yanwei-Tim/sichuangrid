<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content" >
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
			<div class="userState">
				<select id="displayLevel" name="displayLevel" class="basic-input" >
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>" selected="selected">仅显示本级</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">所有下辖</option>
					<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
				</select>
				<select id="teamClazzId" name="teamClazzId" class="basic-input" >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
				</select>
			</div>
			<pop:JugePermissionTag ename="searchServiceTeam">
			<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
			</pop:JugePermissionTag>
		</div>
		<span class="lineBetween "></span>
		<pop:JugePermissionTag ename="addServiceTeam">
		<a id="add" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteServiceTeam">
		<a id="delete"  href="javascript:void(0)"><span>批量删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="exportServiceTeam">
		<a id="export" href="javascript:void(0)"><span>导出</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="logoutServiceTeam">
		<a id="logOut" href="javascript:void(0)"><span>注销</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="serviceRecordManagement">
		<a id="maintainServiceTeamMembers" href="javascript:void(0)"><span>维护成员信息</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div class="">
		<table id="_serviceTeamList"> </table>
		<div id="_serviceTeamListPager"></div>
	</div>
	<div id="_serviceTeamDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
	<div style="display: none;">
		<pop:JugePermissionTag ename="serviceTeamManagement">
		<span id="title"><s:property value="#request.name"/></span>
		</pop:JugePermissionTag>
	</div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="teamClazz" domainName="@com.tianque.domain.property.PropertyTypes@TEAMCLAZZ" />
<pop:formatterProperty name="teamType" domainName="@com.tianque.domain.property.PropertyTypes@BASICORGTYPE" />
var dialogWidth=850;
var dialogHeight=590;
var isgridBol = false;
var currentOrgId=getCurrentOrgId();
var notRun = new Array();
var run = new Array();
function onOrgChanged(orgId,isgrid){
	currentOrgId=orgId;
	isgridBol = isgrid;
	$("#_serviceTeamList").setGridParam({
		url:'${path}/baseinfo/serviceTeamManage/serviceManageTeamList.action',
		datatype: "json",
		page:1
	});
	$("#_serviceTeamList").setPostData({
		"organizationId":orgId,
		"displayLevel":$("#displayLevel").val(),
		"teamClazzId":$("#teamClazzId").val(),
		"serviceManageTeam.logOut":0
	});
	$("#_serviceTeamList").trigger("reloadGrid");
}
function checkedOrgId(orgID){
	if(orgID != USER_ORG_ID){
		$.messageBox({
			message:"当前用户只能操作所在层级的组织！",
			level: "warn"
		 });
		return false;
	}else{
		return true;
	}
}
function operateFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='updateServiceTeam'> <a href='javascript:;' onclick='updateOperator(event,"+rowData.id+");'><span>修改</span></a> |</pop:JugePermissionTag> <pop:JugePermissionTag ename='deleteServiceTeam'>  <a href='javascript:;' onclick='deleteOperator(event,"+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
}
function nameFont(el,options,rowData){
	return "<a href='javascript:;' <pop:JugePermissionTag ename='viewServiceTeam'> onclick='viewDialog("+rowData.id+")' </pop:JugePermissionTag> >"+rowData.name+"</a>";
}
function viewObjectFormatter(el, options, rowData){
	return "人员:"+rowData.objectFromPopulationCount+"&nbsp;&nbsp;其他:"+rowData.objectFromLocationCount;
}
function viewTeamFormatter(el, options, rowData){
	return "<a href='javascript:;' onclick='overviewTeam(event,"+rowData.id+");'><U><font color='#6633FF'>详细</font></U></a>";
}
function viewMemberFormatter(el, options, rowData){
	if(rowData.memberNums==0){
		return "0";
	}else{
		return "<a href='javascript:;' onclick='viewMember(event,"+rowData.id+");'><U><font color='#6633FF'>"+rowData.memberNums+"</font></U></a>";
	}
}
function viewDialog(id){
	var serviceTeam =  $("#_serviceTeamList").getRowData(id);
	$("#_serviceTeamDialog").createDialog({
		width:650,
		height:450,
		title:"查看服务团队",
		url:'${path}/baseinfo/serviceTeamManage/serviceTeamDispatch.action?mode=view&dailogName=_serviceTeamDialog&serviceManageTeam.id='+id+'&serviceManageTeam.logOut='+serviceTeam.logOut,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function updateOperator(event,selectedId){
	var serviceTeam =  $("#_serviceTeamList").getRowData(selectedId);
	if(serviceTeam.logOut==1 || serviceTeam.logOut=='1'){
		$.messageBox({level:'warn',message:"已注销的服务团队不可再修改！"});
		return;
	}
	if(!checkedOrgId(getCurrentOrgId())){
		return;
	}
	$("#_serviceTeamDialog").createDialog({
		title:'修改服务团队',
		width: 600,
		height: 320,
		url:'${path}/baseinfo/serviceTeamManage/serviceTeamDispatch.action?mode=edit&dailogName=_serviceTeamDialog&serviceManageTeam.id='+selectedId,
		buttons: {
			"保存并关闭" : function(event){
				$("#serviceTeamForm").submit();
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
function deleteOperator(event,selectedIds){
	if(!checkedOrgId(getCurrentOrgId())){
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/baseinfo/serviceTeamManage/deleteServiceTeam.action?mode=delete&selectedIds='+selectedIds,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除服务团队!"});
						$("#_serviceTeamList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"请清空组织成员，服务对象，服务记录后再删除组织!",
							level:"warn"
						});
					}
				}
			});
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
$(document).ready(function(){
	$("#_serviceTeamList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"id",index:"id",sortable:false,hidden:true},
	        {name:"organization.id",index:"organization.id",sortable:false,hidden:true},
	        {name:"teamClazz.id",index:"teamClazz.id",sortable:false,hidden:true},
	        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:80,align:"center"},
	        {name:'name',index:"name",label:'团队名称',sortable:true,width:200,formatter:nameFont},
	        {name:'teamClazz',index:'teamClazz',label:'所属团队',sortable:true,formatter:teamClazzFormatter,width:120},
	        {name:'teamType',index:'teamType',label:'团队类别',sortable:true,formatter:teamTypeFormatter,width:120},
	        {name:'memberNums',index:'memberNums',label:'成员数',sortable:true,width:80,align:"center",formatter:viewMemberFormatter},
	        {name:'serviceObject',label:'服务对象',sortable:false,formatter:viewObjectFormatter,width:150,align:"center"},
	        {name:'teamOverview',label:'团队概况',formatter:viewTeamFormatter,sortable:false,width:80,align:"center"},
	        {name:"logOut",index:"logOut",sortable:false,hidden:true,width:50},
	        {name:"objectFromLocationCount",sortable:false,hidden:true,width:50},
	        {name:"objectFromPopulationCount",sortable:false,hidden:true,width:50},
	        {name:'organization.orgName',index:'organization.orgName',label:'所属区域(网格)',sortable:false,width:200,align:"center"}
		],
		multiselect:true,
		showColModelButton:false,
		ondblClickRow:viewDialog,
		loadComplete: function(data){if(afterLoad){afterLoad();}}
	});
	$("#_serviceTeamList").jqGrid('setFrozenColumns');
	if(getCurrentOrgId()!="" && getCurrentOrgId()!=null){
		onOrgChanged(getCurrentOrgId(),isGrid());
	}
	$("#search").click(function(event){
		$("#_serviceTeamDialog").createDialog({
			title:"服务团队查询-请输入查询条件",
			width: 600,
			height: 240,
			url:'${path}/baseinfo/serviceTeamManage/serviceTeamDispatch.action?mode=search&dailogName=_serviceTeamDialog&teamClazzId='+$("#teamClazzId").val(),
			buttons: {
				"查询" : function(event){
					searchServiceTeam();
					$(this).dialog("close");
				},
				"关闭" : function(){
		        	$(this).dialog("close");
				}
			}

		});
	});
	$("#add").click(function(event){
		if(!checkedOrgId(getCurrentOrgId())){
			return;
		}
		$("#_serviceTeamDialog").createDialog({
			title:"新增服务团队",
			width: 600,
			height: 320,
			url:'${path}/baseinfo/serviceTeamManage/serviceTeamDispatch.action?mode=add&dailogName=_serviceTeamDialog',
			buttons: {
				"保存并关闭" : function(event){
		   			$("#serviceTeamForm").submit();
		   			$("#isSubmit").val("true");
				},
				"保存并继续" : function(event){
		   			$("#serviceTeamForm").submit();
		   			$("#isSubmit").val("false");
				}
				<pop:JugePermissionTag ename="addServiceTeamMember">
				,
				"添加成员" : function(event){
					$("#appendMember").val("true");
					$("#serviceTeamForm").submit();
				}
				</pop:JugePermissionTag>
				
			}
		});
	});
	$("#update").click(function(event){
		var selectedId = getSelectedIds();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条数据再进行修改！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行修改！"});
	 		return;
		}
		updateOperator(event,selectedId);
	});
	$("#delete").click(function(){
		var selectedIds = $("#_serviceTeamList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds==""){
			$.messageBox({level:'warn',message:"请选择一条或多条数据进行删除！"});
	 		return;
		}
		deleteOperator(event,selectedIds);
	});
	$("#view").click(function(){
		var selectedId = getSelectedIds();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条数据再进行查看！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条数据进行查看！"});
	 		return;
		}
		viewDialog(selectedId);
	});
	$("#export").click(function(event){
		if($("#_serviceTeamList").getGridParam("records")>0){
			var postData = $("#_serviceTeamList").getPostData();
			$("#_serviceTeamList").setPostData($.extend(postData,{"organizationId":getCurrentOrgId(),"searchServiceManageTeamVo.displayLevel":$("#displayLevel").val(),"searchServiceManageTeamVo.logOut":0}));
			$("#_serviceTeamDialog").createDialog({
				width: 250,
				height: 200,
				title:"导出服务团队信息",
				url:'${path}/common/exportExcel.jsp',
				postData:{
					gridName:'_serviceTeamList',
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
			$.messageBox({level:'warn',message:"没有可导出的数据！"});
			return;
		}
	});
	$("#logOut").click(function(event){
		var allValue = getSelectedIds();
		var logOut=new Array();
		var temp=new Array();
		if(allValue==null || allValue==""){
			$.messageBox({level:'warn',message:"请选择一条或多条数据进行注销！"});
	 		return;
		}
		for(var i=0;i<allValue.length;i++){
			var rowData = $("#_serviceTeamList").getRowData(allValue[i]);
			if(rowData.logOut==0 || rowData.logOut=="0"){
				logOut.push(allValue[i]);
			}else{
				temp.push(allValue[i]);
			}
		}
		allValue=logOut;
		if(allValue==null||allValue.length==0){
			$.messageBox({level:'warn',message:"没有可注销的数据！"});
			return;
		}
		$("#_serviceTeamDialog").createDialog({
			width:450,
			height:210,
			title:'注销提示',
			modal:true,
			url:'${path}/baseinfo/serviceTeamManage/serviceTeam/logoutConditionDlg.jsp?selectedIds='+run+'&logOut=1&dailogName=_serviceTeamDialog',
			buttons: {
			   "保存" : function(event){
				   $("#logoutForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});
	$("#maintainServiceTeamMembers").click(function(event){
		var selectedId = getSelectedIds();
		if(selectedId==null || selectedId==""){
			$.messageBox({level:'warn',message:"请选择一条未注销团队维护成员信息！"});
	 		return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能对一条未注销团队维护成员信息！"});
	 		return;
		}
		var serviceTeam =  $("#_serviceTeamList").getRowData(selectedId);
		if(!checkedOrgId(serviceTeam["organization.id"])){
			return;
		}
		if(serviceTeam.logOut==1 || serviceTeam.logOut=='1'){
			$.messageBox({level:'warn',message:"已注销团队不可再维护成员信息！"});
			return;
		}
		$("#_serviceTeamDialog").createDialog({
			width: 850,
			height: 600,
			title:'维护成员信息',
			url:'/baseinfo/serviceTeamMemberManage/dispatchOperate.action?mode=list&dailogName=_serviceTeamDialog&organizationId='+getCurrentOrgId()+'&teamId='+selectedId+'&teamClazzId='+serviceTeam["teamClazz.id"],
			buttons: {
				"关闭" : function(){
		        	$(this).dialog("close");
		        	$("#_serviceTeamList").trigger("reloadGrid");
		   		}
			}
		});
	});
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId(),isGrid());
	});
	function afterLoad(){
		isEmphasisFormatter();
		changeColor();
	}
	function changeColor(){
		if(notRun==null||notRun.length==0){
			return;
		}
		for(var i=0;i<notRun.length;i++){
			var rowData=$("#_serviceTeamList").getRowData(notRun[i]);
			$("#"+notRun[i]).css('background','red');
			$("#_serviceTeamList").setSelection(notRun[i]);
		}
		notRun.splice(0,notRun.length);
	}

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#_serviceTeamList").getDataIDs();
		for(var i=0;i<idCollection.length;i++){
			var ent =  $("#_serviceTeamList").getRowData(idCollection[i]);
			if(ent.logOut==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}
	function getSelectedIds(){
		var selectedIds = $("#_serviceTeamList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}
	function getSelectedIdLast(){
		var selectedId="";
		var selectedIds = $("#_serviceTeamList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}

	//切换本级及下辖功能
	$("#displayLevel").change(function(event){
		currentOrgId=getCurrentOrgId()
		$("#_serviceTeamList").setGridParam({
			url:'${path}/baseinfo/serviceTeamManage/serviceManageTeamList.action?displayLevel='+$("#displayLevel").val(),
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#_serviceTeamList").setPostData({
			"organizationId":currentOrgId,
			"teamClazzId":$("#teamClazzId").val(),
			"serviceManageTeam.logOut":0
		});
	    $("#_serviceTeamList").trigger("reloadGrid");
	});

	//切换组织类别
	$("#teamClazzId").change(function(event){
		currentOrgId=getCurrentOrgId();
		$("#_serviceTeamList").setGridParam({
			url:'${path}/baseinfo/serviceTeamManage/serviceManageTeamList.action?teamClazzId='+$("#teamClazzId").val(),
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#_serviceTeamList").setPostData({
			"organizationId":currentOrgId,
			"displayLevel":$("#displayLevel").val(),
			"serviceManageTeam.logOut":0
		});
	    $("#_serviceTeamList").trigger("reloadGrid");
	});

	function searchServiceTeam(){
		$("#_serviceTeamList").setGridParam({
			url:"${path}/baseinfo/searchServiceManageTeam/findServiceManageTeamsByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#searchServiceTeamForm").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
 			if (dataJson[data[i].name]) {
				dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				dataJson[data[i].name] = data[i].value;
			}

		}
		$("#_serviceTeamList").setPostData(
			$.extend({
				"organizationId":getCurrentOrgId(),
				"searchServiceManageTeamVo.displayLevel":$("#displayLevel").val(),
				"searchServiceManageTeamVo.logOut":$("#conditionLogOutState").val()
			},dataJson));
	    $("#_serviceTeamList").trigger("reloadGrid");
	}
});
function overviewTeam(event,id){
	$("#_serviceTeamDialog").createDialog({
		width:550,
		height:300,
		title:"服务团队概况",
		url:'${path}/baseinfo/serviceTeamManage/serviceTeamDispatch.action?mode=overview&dailogName=_serviceTeamDialog&serviceManageTeam.id='+id,
		buttons: {
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
function viewMember(event,id){
	var serviceTeam =  $("#_serviceTeamList").getRowData(id);
	$("#_serviceTeamDialog").createDialog({
		width:650,
		height:400,
		title:"团队成员列表",
		url:'${path}/baseinfo/serviceTeamMemberManage/dispatchOperate.action?mode=view&dailogName=_serviceTeamDialog&onDuty=true&teamId='+id+'&teamClazzId='+serviceTeam["teamClazz.id"],
		buttons: {
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
</script>