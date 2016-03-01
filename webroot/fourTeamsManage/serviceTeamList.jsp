<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<input type="hidden" id="teamType" value="<s:property value='#parameters.teamType'/>">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
		<select id="ScreeningFourteams" class="basic-input" >
			<option  value="sameLevel" selected>本级</option>
			<option id="jurisdiction" value="jurisdiction" >直辖下辖</option>
			<option id="jurisdictionAll" value="jurisdictionAll" >下辖全部</option>
		</select>
		<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		<pop:JugePermissionTag ename="addPioneerServiceTeam,addConvenienceServiceTeam,addCommunityServiceTeam,addGridServiceTeam">
		<a id="addPioneerServiceTeam" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="managePioneerTeamMember">
		<a id="manageTeamMember" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>维护成员</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deletePioneerServiceTeam,deleteConvenienceServiceTeam,deleteCommunityServiceTeam,deleteGridServiceTeam">
		<a id="deletePioneerServiceTeam" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>批量删除</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="teamManagementList">
		</table>
		<div id="teamManagementListPager"></div>
	</div>
	<div id="teamManagementDialog"></div>
	<div id="addMemberDialog"></div>
	<div id="noticeDialog"></div>
	<div id="teamManagementMaintanceDialog"></div>
</div>
<script type="text/javascript">
if(isGrid()){
	$("#jurisdiction").hide();
	$("#jurisdictionAll").hide();
}
<pop:formatterProperty name="competentDepartment" domainName="@com.tianque.domain.property.PropertyTypes@FOURTEAMS_COMPETENT_DEPARTMENT" />
var orgId=getCurrentOrgId();
function operatorFormatter(el,options,rowData){
	return "<a href='javascript:viewTeamMenber("+rowData.id+")'><span>"+rowData.memberNumber+"</span></a>";
}

function operatorFormatters(el,options,rowData){
	return "<a href='javascript:updateFourteams(\""+rowData.encryptId+"\")'><span>修改</span></a> |<a href='javascript:deleteFourteams(\""+rowData.id+"\")'><span>删除</span></a>";
	
}
function viewFourTeams(el,options,rowData){
	return "<a href='javascript:viewFourTeamsOperator(\""+rowData.encryptId+"\")'><span>"+rowData.names+"</span></a>"
}
function updateFourteams(id){
	if(id==null || id==undefined){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
	$("#addMemberDialog").createDialog({
		width: 600,
		height: 500,
		title:'修改队伍',
		url:PATH+'/fourTeamsManage/maintainAddList.action',
		postData:{
			"fourTeams.teamType":$("#teamType").val(),
			"fourTeams.id":id,
			"mode":"edit"
		},
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
function deleteFourteams(id){
	var fourteams=$("#teamManagementList").getRowData(id);
	if(id==null || id==undefined){
		$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
 		return;
	}
	$.confirm({
		title:"确认删除",
		message:"删除后数据无法回复，确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:PATH+"/fourTeamsManage/deleteFourTeams.action",
				type:"post",
				data:{
					"ids":fourteams.encryptId,
					"parentId":fourteams.parentTeamId
				},
				success:function(data){
					if(data!=true){
						$.messageBox({
							message : data,
							level : 'error'
						});
						return;
					}
					$("#teamManagementList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除该队伍信息!"});
			    }
		    });
	   }
 	});
}

function viewTeamMenber(selectedId){
	$("#teamManagementDialog").createDialog({
		width: 850,
		height: 600,
		title:'查看成员信息',
		url:'/fourTeamsManage/viewManageFourTeamMember.action?id='+selectedId,
		buttons: {
			"关闭" : function(){
	        	$(this).dialog("close");
	        	$("#teamManagementList").trigger("reloadGrid");
	   		}
		}
	});
}

function viewFourTeamsOperator(id){
	if(id==null || id==undefined){
		$.messageBox({level:"warn",message:"查看失败，请重试！"});
 		return;
	}
	$("#addMemberDialog").createDialog({
		width: 600,
		height: 500,
		title:'查看队伍',
		url:PATH+'/fourTeamsManage/maintainAddList.action',
		postData:{
			"fourTeams.teamType":$("#teamType").val(),
			"fourTeams.id":id,
			"mode":"view"
		},
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
function callback(){
    var dfop = {
    		colModel:[
    					{name:"id",index:'id',hidden:true,key:true,frozen:true},
    					{name:"encryptId",index:'encryptId',hidden:true,frozen:true},
    					{name:"departementId",index:'departementId',hidden:true,frozen:true},
    					{name:"parentTeamId",index:'parentTeamId',hidden:true,frozen:true},
    					{name:"operator", index:'id', label:'操作',formatter:operatorFormatters,width:80,frozen:true,sortable:false,align:'center' },
    					{name:"organization.orgName",index:"organization.orgName",label:'所属网格',sortable:false,width:220,align:"center"},
    					{name:'names', index:'names',label:'队伍名称', formatter:viewFourTeams,width:260, sortable:true, align:'center', hidden:false}, 	
    					{name:'memberNumber',index:'memberNumber',label:'成员数量',formatter:operatorFormatter,sortable:true,width:70,align:"center"},
    					{name:'departementName',index:'departementName',label:'组建部门',sortable:true,width:200,align:"center"},
    					{name:'competentDepartment.id',index:'competentDepartment',label:'主管部门',sortable:true,width:200,align:"center",hidden:true,formatter:competentDepartmentFormatter},
    					{name:'serviceArea',index:'serviceArea',label:'服务范围',sortable:true,width:270,align:"center",hidden:true},
    					{name:'comments', index:'comments',label:'备注', width:300, sortable:true, align:'center', hidden:true}	
    				]
    
    }
    TQ.teamManagementList(dfop)
  
}

$.cacheScript({
    url:'/resource/getScript/fourTeamsManage/serviceTeamList.js',
    callback: callback
})

</script>