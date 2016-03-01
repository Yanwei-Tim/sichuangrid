<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="ServiceTeam" name="moduleName"/>
</jsp:include>


<div class="content" >
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="userState">
				<select id="teamTypeId" name="teamType.id" class="basic-input" >
					<pop:OptionTag name="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM" />
				</select>
			</div>
		</div>
	</div>
	<div class="">
		<table id="serviceTeamForIntroduceList"> </table>
		<div id="serviceTeamForIntroduceListPager"></div>
	</div>
	<div id="serviceTeamDialog"></div>
	<div style="display: none;">
		<pop:JugePermissionTag ename="serviceTeamManagement">
		<span id="title"><s:property value="#request.name"/></span>
		</pop:JugePermissionTag>
	</div>
</div>
<input type="hidden" id="records"/>
<script type="text/javascript">
<pop:formatterProperty name="teamType" domainName="@com.tianque.plugin.serviceTeam.util.Constants$ServiceTeamPropertyTypes@TEAM" />
var currentOrgId = "<s:property value='#parameters.currentOrgId'/>";
var notRun = new Array();
var run = new Array();
$(document).ready(function(){
	$("#teamTypeId").hide();
	//是否进入了网格
	if(isGrid()){
		$("#displayLevel").hide();
	}
	
	//如果是县级以上用户不显示对象和记录数量
	var colModel=[
		        {name:"id",index:"id",sortable:false,hidden:true},
		        {name:"organization.id",index:"organization.id",sortable:false,hidden:true},
		       // {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter_ServiceTeam,width:80,align:"center"},
		        {name:'teamName',index:"teamName",label:'团队名称',sortable:true,width:200,formatter:nameFont_ServiceTeam},
		        {name:'teamType',index:'teamType',label:'团队类别',sortable:true,formatter:teamTypeFormatter,width:120},
		        {name:'teamMembers',index:'teamMembers',label:'成员数',sortable:true,width:70,align:"center",formatter:viewMemberFormatter},
		        {name:'buildDate',label:'成立时间',sortable:false,width:80,align:"center"},
		        {name:'logOut',hidden:true,sortable:false,index:"logOut"},
		        {name:'org.orgName',index:'org.orgName',label:'所属区域(网格)',sortable:false,width:350,align:"center"}
			];
	if(isDistrictDownOrganization()){
		colModel.insertAt({name:'serviceObject',label:'服务对象',sortable:false,formatter:viewObjectFormatter,width:150,align:"center"},6);
        colModel.insertAt({name:'teamRecords',label:'服务记录',sortable:false,width:80,align:"center",formatter:serviceRecordsCountFormatter},7);
	}
	
		$("#serviceTeamForIntroduceList").jqGridFunction({
			datatype: "local",
		   	colModel:colModel,
			multiselect:true,
			showColModelButton:false,
			ondblClickRow:viewDialog,
			loadComplete: function(data){if(afterLoad){afterLoad();}}
		});
		
	//团队列表
	
	$("#serviceTeamForIntroduceList").jqGrid('setFrozenColumns');
	$("#serviceTeamForIntroduceList").closest(".ui-jqgrid-bdiv").css({ "height" : "378" });
	if(currentOrgId!="" && currentOrgId!=null){
		getTeamList(currentOrgId);
	}
	
	//绑定列表切换事件--团队类型
	$("#teamTypeId").change(changeList);
	
	//绑定列表切换事件--层级
	$("#displayLevel").change(changeList);
	
	
	
});

//获取选中列的ID
function getSelectedIds(){
	var selectedIds = $("#serviceTeamForIntroduceList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
	
//列表过滤功能(层级和类型)
function changeList(){
	$("#serviceTeamForIntroduceList").setGridParam({
		url:'${path}/plugin/serviceTeam/serviceTeamManage/findServiceTeams.action',
		datatype: "json",
		page:1,
		mtype:"post"
	});
	$("#serviceTeamForIntroduceList").setPostData({
		"serviceTeamVo.displayLevel":$("#displayLevel").val(),
		"serviceTeamVo.teamType.id":$("#teamTypeId").val(),
		"serviceTeamVo.org.id":currentOrgId,
		"serviceTeamVo.logOut":0
	});
    $("#serviceTeamForIntroduceList").trigger("reloadGrid");
}

//加载完成后执行
function afterLoad(){
	isEmphasisFormatter();
	changeColor();
}
	
//改变颜色
function changeColor(){
	if(notRun==null||notRun.length==0){
		return;
	}
	for(var i=0;i<notRun.length;i++){
		var rowData=$("#serviceTeamForIntroduceList").getRowData(notRun[i]);
		$("#"+notRun[i]).css('background','red');
		$("#serviceTeamForIntroduceList").setSelection(notRun[i]);
	}
	notRun.splice(0,notRun.length);
}

//已解散团队样式更改
function isEmphasisFormatter(){
	var idCollection = new Array();
	idCollection=$("#serviceTeamForIntroduceList").getDataIDs();
	for(var i=0;i<idCollection.length;i++){
		var ent =  $("#serviceTeamForIntroduceList").getRowData(idCollection[i]);
		if(ent.logOut==1){
			$("#"+idCollection[i]+"").css('color','#778899');
		}
	}
}

//获取团队列表数据
function getTeamList(orgId){
	$("#serviceTeamForIntroduceList").setGridParam({
		url:'${path}/plugin/serviceTeam/serviceTeamManage/findServiceTeams.action',
		datatype: "json",
		page:1
	});
	$("#serviceTeamForIntroduceList").setPostData({
		"serviceTeamVo.org.id":orgId,
		"serviceTeamVo.displayLevel":"<s:property value='@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE'/>",
		"serviceTeamVo.logOut":0,
		"serviceTeamVo.teamType.id":$("#teamTypeId").val()
	});
	$("#serviceTeamForIntroduceList").trigger("reloadGrid");
}

//修改团队
function updateOperator(selectedId){
	var serviceTeam =  $("#serviceTeamForIntroduceList").getRowData(selectedId);
	if(serviceTeam.logOut==1 || serviceTeam.logOut=='1'){
		$.messageBox({level:'warn',message:"已解散的服务团队不可再修改！"});
		return;
	}
	$("#serviceTeamDialog").createDialog({
		title:'修改服务团队',
		width: 600,
		height: 320,
		url:'${path}/plugin/serviceTeam/serviceTeamManage/dispatch.action?mode=edit&dailogName=serviceTeamDialog&serviceTeam.id='+selectedId,
		buttons: {
			"保存并关闭" : function(event){
				$("#serviceTeamForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

//删除团队
function deleteOperator(selectedId){
	var serviceTeam =  $("#serviceTeamForIntroduceList").getRowData(selectedId);
	if(serviceTeam.logOut==1 || serviceTeam.logOut=='1'){
		$.messageBox({level:'warn',message:"已解散的服务团队不可再删除！"});
		return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/plugin/serviceTeam/serviceTeamManage/deleteServiceTeam.action?mode=delete&selectedIds='+selectedId,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除服务团队!"});
						$("#serviceTeamForIntroduceList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"请清空组织成员，服务对象，服务记录后再删除团队!",
							level:"warn"
						});
					}
				}
			});
		}
	});
}

//查看团队信息
function viewDialog(id){
	var serviceTeam =  $("#serviceTeamForIntroduceList").getRowData(id);
	$("#serviceTeamDialog").createDialog({
		width:650,
		height:450,
		title:"查看服务团队",
		url:'${path}/plugin/serviceTeam/serviceTeamManage/dispatch.action?mode=view&dailogName=serviceTeamDialog&serviceTeam.id='+id+'&serviceTeam.logOut='+serviceTeam.logOut,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

	
//查看团队所属成员数
function viewMemberFormatter(el, options, rowData){
	if(rowData.teamMembers==0 || rowData.teamMembers=="0"){
		return rowData.teamMembers;
	}else{
		return "<a href='javascript:void(0)' onclick='viewMemberList("+rowData.id+")'>"+rowData.teamMembers+"</a>";
	}
}

//显示团队所属记录数
function serviceRecordsCountFormatter(el, options, rowData){
	var records=0;
	$.ajax({
		async: false,
		url:'${path}/plugin/serviceTeam/serviceTeamManage/serviceRecordsCount.action?serviceTeam.id='+rowData.id,
		success:function(data){
			records=data;
		}
	});
	if(null==records){
		records = 0;
	}
	return records;
}

//查看团队下成员列表
function viewMemberList(id){
	$("#serviceTeamDialog").createDialog({
		title:"查看在职成员列表",
		width: 600,
		height: 360,
		url:'${path}/plugin/serviceTeam/serviceTeamManage/dispatch.action?mode=viewMemberList&dailogName=serviceTeamDialog&serviceTeamVo.id='+id+'&serviceTeamMember.onDuty=1&serviceTeamVo.org.id='+currentOrgId,
		buttons: {
			"关闭" : function(){
	        $(this).dialog("close");
	  		 }
		}
	});
}

//查看团队所属对象数
function viewObjectFormatter(el, options, rowData){
	var objects=0;
	$.ajax({
		async: false,
		url:'${path}/plugin/serviceTeam/serviceTeamManage/serviceObjectsCount.action?serviceTeamVo.id='+rowData.id,
		success:function(data){
			objects=data;
		}
	});
	if(null==objects[0]){
		objects[0]=0;
	}
	if(null==objects[4]){
		objects[4]=0;
	}
	return "人口："+objects[0]+"，其他："+objects[4];
}
</script>