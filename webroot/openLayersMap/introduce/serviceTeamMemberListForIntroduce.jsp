<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp">
	<jsp:param value="ServiceTeamMember" name="moduleName"/>
</jsp:include>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width:100%;" >
		<table id="serviceTeamMemberForIntroduceList"></table>
		<div id="serviceTeamMemberForIntroduceListPager"></div>
	</div>
	<div id="_serviceTeamMemberDialog"></div>
	<div id="_serviceTeamMemberLogoutDialog"></div>
</div>
<div style="display: none;">
	
</div>
<script type="text/javascript">
<pop:formatterProperty name="gender" domainName="@com.tianque.domain.property.PropertyTypes@GENDER" />
var currentOrgId = "<s:property value='#parameters.currentOrgId'/>";
var dialogWidth=820;
var dialogHeight=610;


$(document).ready(function(){
    
	//是否进入了网格
	if(isGrid()){
		$("#serviceTeamMemberVo_orgScope").hide();
	}
	//服务成员列表
	$("#serviceTeamMemberForIntroduceList").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colNames:['id','orgId','姓名','性别','联系手机','职位','联系电话','出生年份','所属区域(网格)'],
		colModel:[
			{name:"baseId",index:"baseId",key:true,sortable:true,hidden:true},
			{name:"organization.id",index:"organization.id",sortable:false,hidden:true},
			//{name:"operation",index:"id.id",formatter:operateFormatter_ServiceTeam_Member,sortable:false,width:80,align:"center"},
			{name:'name',index:"name",width:100,formatter:nameFont_ServiceTeamMember,frozen:true,sortable:true},
			{name:'gender',index:'gender',width:80,formatter:genderFormatter,sortable:true,align:"center"},
			{name:'mobile',index:'mobile',sortable:true,width:110,align:"center"},
			{name:'job',index:'job',sortable:true,width:150},
			{name:'homePhone',index:'homePhone',sortable:true,width:110},
			{name:"birthday",index:"birthday",sortable:true,width:80,align:"center"},
			{name:"org.orgName",index:"org.orgName",sortable:false,width:350}
		],
		multiselect:true,
		showColModelButton:false,
		sortname:'baseId',
		ondblClickRow:viewServiceMember
	});
	$("#serviceTeamMemberForIntroduceList").jqGrid('setFrozenColumns');
	$("#serviceTeamMemberForIntroduceList").closest(".ui-jqgrid-bdiv").css({ "height" : "400" });
	loadTeamMembers();
	
	
	//获取成员列表行数
	function countSelectedIds(){
		var selectedIds = $("#serviceTeamMemberForIntroduceList").jqGrid("getGridParam", "selarrrow");
		return null == selectedIds ? 0 :selectedIds.length;
	}
	//修改服务成员
	$("#update").click(function(){
		if(countSelectedIds() > 1) {
			$.messageBox({level:'warn',message:"请选择一条服务成员信息进行修改！"});
			return;
		}
		var selectedId = $("#serviceTeamMemberForIntroduceList").jqGrid("getGridParam", "selrow");
		if(null == selectedId){
			$.messageBox({level:'warn',message:"请选择需要修改的服务成员信息！"});
			return;
		}
		updateOperator(selectedId);
	});

	//删除按钮事件
	$("#delete").click(function(){
		if(countSelectedIds() != 1) {
			$.messageBox({level:'warn',message:"请选择一条服务成员信息进行删除！"});
			return;
		}
		var selectedId = $("#serviceTeamMemberForIntroduceList").jqGrid("getGridParam", "selrow");
		deleteOperator(selectedId);
	});
	
	
	
	//加载服务成员
	function loadTeamMembers() {
		var listParam = null;
		listParam = {
			'serviceTeamMemberVo.orgScope':"<s:property value='@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE'/>",
			'serviceTeamMemberVo.org.Id':currentOrgId
		}
		loadData(listParam);
	}
	//加载服务成员数据
	function loadData(listParam) {
		$("#serviceTeamMemberForIntroduceList").setGridParam({
			url:'${path}/plugin/serviceTeam/serviceTeamMember/findServiceTeamMemberBases.action',
			datatype: "json",
			page:1
		});
		$("#serviceTeamMemberForIntroduceList").setPostData(listParam);
		$("#serviceTeamMemberForIntroduceList").trigger("reloadGrid");
	}
	//获取选中列的ID
	function getSelectedIds(){
		var selectedIds = $("#serviceTeamMemberForIntroduceList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}

});
//修改服务成员对话框
function updateOperator(selectedId){
	$("#_serviceTeamMemberDialog").createDialog({
		title:'修改服务成员',
		width: 680,
		height: 280,
		url:'${path}/plugin/serviceTeam/serviceTeamMember/dispatch.action?mode=edit&dailogName=_serviceTeamMemberDialog&serviceTeamMemberBase.id='+selectedId,
		buttons: {
			"保存并关闭" : function(event){
	   			$("#serviceTeamMemberForm").submit();
   			},
   			"关闭" : function(){
		        	$(this).dialog("close");
				}
		}
	});
}
//删除服务成员
function deleteOperator(selectedId){
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/plugin/serviceTeam/serviceTeamMember/deleteServiceTeamMember.action?mode=delete&selectedIds='+selectedId,
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除服务成员!"});
						$("#serviceTeamMemberForIntroduceList").trigger("reloadGrid");
					}else{
					$.messageBox({level:'warn',message:data});
					}
				}
			});
		}
	});
}
	//查看服务成员基本信息
	function viewServiceMember(baseId){
		$("#_serviceTeamMemberDialog").createDialog({
			title:"查看成员基本信息",
			width: 600,
			height: 320,
			url:'${path}/plugin/serviceTeam/serviceTeamMember/viewServiceMember.action?mode=view&dailogName=_serviceTeamMemberDialog&serviceTeamMemberBase.id='+baseId,
			buttons: {
				"关闭" : function(){
		        $(this).dialog("close");
		  		 }
			}
		});
	}	
//获得列表数据数量
function getTableNum(tableid){
  var num=$("#"+tableid+" tbody tr").size();
  return num-1;
}	

	
</script>