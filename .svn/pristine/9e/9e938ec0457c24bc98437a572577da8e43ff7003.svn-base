<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="viewPrimaryOrgMemer" class="container container_24">
<input type="hidden" id="myGroupId" name="myGroup.id" value="${contacterId }" />
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
		    <input type="text" value="请输入姓名或拼音" name="searchText" value="请输入姓名或拼音" id="searchText" 
		    maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入姓名或拼音':this.value;"
		    onfocus="value=(this.value=='请输入姓名或拼音')?'':this.value;"/>
			<button id="refreshSearchKeys" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a id="fastSearchButton" href="javascript:;"><span>检索</span></a>
		<c:if test="${mode=='myGroupEditMember'}">
			<a id="addMember" href="javascript:;"><span>新增</span></a>
			<a id="deleteMember" href="javascript:;"><span>批量删除</span></a>
		</c:if>
		<a id="refresh" href="javascript:;"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="mygroupMemberList"></table>
		<div id="mygroupMemberListPager"></div>
	</div>
	
<div id="addMemberDialog"></div>
  
</div>
<script type="text/javascript">
var currentOrgId = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getOrgLevel().getId()'/>";
$("#refreshSearchKeys").click(function(){
	$("#searchText").attr("value","请输入姓名或拼音");
});
$(document).ready(function(){
	$("#mygroupMemberList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	         {name:"id",index:"id",sortable:false,hidden:true,frozen:true},
	         {name:"fromUser.organization.orgLevel.id",index:"fromUser.organization.orgLevel.id",hidden:true,sortable:false,hidedlg:true},
	         {name:'name',index:'name',label:'姓名',width:200,sortable:true},
	         {name:'mobileNumber',index:'mobileNumber',label:'联系手机',sortable:true,width:100,align:'center',formatter:operatorFormatterMobile},
	         {name:'fromUser.workPhone',index:'fromUser.workPhone',label:'联系电话',sortable:true,width:100,align:'center'},
	         {name:'remark',index:'remark',label:'备注',width:300,sortable:true}
		],
		scrollrow:true,
		height:400,
		onSelectAll:function(aRowids,status){ },
		multiselect:true
	});
	onDataInit();
});

function onDataInit(){
	$("#mygroupMemberList").setGridParam({
		url:"${path}/contact/myGroupManage/findMyGroupHasContacters.action?myGroup.id="+$("#myGroupId").val()+"&myGroup.belongClass=workContact",
		datatype: "json",
		page:1
	});
	$("#mygroupMemberList").trigger("reloadGrid");
}

function operatorFormatterMobile(el,options,rowData){
	var data = rowData.fromUser.organization.orgLevel.id;
	if(currentOrgId<data){
		return "";
	}else{
		return rowData.mobileNumber;
	}
}

$("#addMember").click(function(){
	$("#addMemberDialog").createDialog({
		width:650,
		height:470,
		title:'编辑群组联系人',
		url:"${path}/contact/myGroupManage/dispatchMyGroupHasContacter.action?mode=edit&myGroup.id="+$("#myGroupId").val(),
		buttons :{
			"保存" : function(){
				$("#myGroupHasContacter-Form").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
});

$("#deleteMember").click(function(){
	var selectId =  $("#mygroupMemberList").jqGrid("getGridParam", "selarrrow");
	if(selectId==null || selectId=='' || selectId==undefined){
		$.messageBox({level:'warn',message:"请选择一条数据进行维护！"});
		return;	
	}
	
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?联系人删除后，联系人将不会存在该群组中，确认删除？",
		okFunc: function(){
			$.ajax({
				url:'${path}/contact/myGroupManage/deleteMyGroupContater.action?myGroup.id='+$("#myGroupId").val()+"&delContacterIds="+selectId,
				success:function(data){
					if(data == true){
						$.messageBox({ message:"成功删除联系人信息!"});
						$("#mygroupMemberList").trigger("reloadGrid");
						return;
					}
		            $.messageBox({
						message:data,
						level: "error"
					});
		        }
			});
	   }
 	});
});

$("#refresh").click(function(){
	onDataInit();
	$("#searchText").attr("value","请输入姓名或拼音");
});

$("#fastSearchButton").click(function(){
	var name = $("#searchText").val();
	if(name=='请输入姓名或拼音'){
		return;
	} 
	$("#mygroupMemberList").setGridParam({
		url:'${path}/contact/myGroupManage/findMyGroupHasContacters.action?myGroup.name='+encodeURIComponent(name)+'&myGroup.id='+$("#myGroupId").val()+'&myGroup.belongClass=workContact',
		datatype: "json",
		page:1
	});
	$("#mygroupMemberList").trigger("reloadGrid");
});
</script>