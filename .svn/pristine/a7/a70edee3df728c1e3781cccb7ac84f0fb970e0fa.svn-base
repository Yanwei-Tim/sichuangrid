<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
				搜索：<input class="basic-input" type="text" value="请输入角色名称" id="searchRoleByCondition" maxlength="18" class="searchtxt" style="width:180px;" onblur="value=(this.value=='')?'请输入角色名称':this.value;" onfocus="value=(this.value=='请输入角色名称')?'':this.value;"/>
				<button id="refreshSearchRoleKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:void(0);" id="searchRoleByConditionButton"><span>搜索</span></a>
	</div>
</div>


<div id="roleList">
   <div>
   	 <table id="roleTable"></table>
   </div>
</div>


<script type="text/javascript" >
$(function(){
	$("#refreshSearchRoleKey").click(function(){
		$("#searchRoleByCondition").attr("value","请输入角色名称");
	});
	$("#searchRoleByConditionButton").click(function(){
		var condition = $("#searchRoleByCondition").val();
		if(condition == '请输入角色名称'){ 
			condition = "";
		}
		$("#roleTable").setGridParam({
			url: "${path}/sysadmin/userManage/prepareRoleTable.action?mode=add",
			datatype: "json",
			page:1
		});
		var initParam = {
				 "organizationId":function(){ 
					  return $('#organizationId').val()
					  },
			        "user.id" : function(){
				        if($('#userId').val()!=''){
				        	return $('#userId').val();
				        }else{
					        return 0;
					   }
			        }
		};
		initParam = $.extend(initParam,{"userRole":condition});
		$("#roleTable").setPostData(initParam);
		$("#roleTable").trigger("reloadGrid");
	});	
	  $("#roleTable").jqGridFunction({
		  url: "${path}/sysadmin/userManage/prepareRoleTable.action?mode=add",
		  datatype: "json",
		  postData: {
			  "organizationId":function(){ 
				  return $('#organizationId').val()
				  },
		        "user.id" : function(){
			        if($('#userId').val()!=''){
			        	return $('#userId').val();
			        }else{
				        return 0;
				   }
		        }
	        },
		 colModel:[
		          {name:"id",index:"id",hidden:true},
		   	      {name:"roleName",index:'roleName',label:'岗位名称',sortable:false,width:155},
		   	      {name:"description",index:"description",label:'岗位描述',sortable:false,width:265}
				],
		multiselect:true,
		loadComplete:selectRolesForList,
		sortname:'roleName',
		sortorder:'desc',
		rowNum : 500,
		viewrecords:true,
		width:540,
		height: 300,
		jsonReader:{
			repeatitems:false,
			id:"0"
		}
	   });
});
function fillSelectRolesInputer(idDiv,realIdDiv){
 	var selectedIds =  $("#roleTable").jqGrid("getGridParam", "selarrrow");
 	var optionsValue = "" ;
 	var options = "";
	for (var i = 0 ; i<selectedIds.length; i++ ){
		var rowData = $("#roleTable").jqGrid("getRowData",selectedIds[i]); 
		var dataId = rowData.id;
		var roleName = rowData.roleName;
		options += "<option id='role_"+dataId+"' selected='selected' value='"+dataId+"' ></option>";
		 if(optionsValue==null||optionsValue==""){
			 optionsValue="["+roleName+"]";
		 }else{
			 optionsValue=optionsValue+",["+roleName+"]"; 
		 }
	}
	$("#"+idDiv).empty().append(optionsValue);
	$("#"+realIdDiv).empty().append(options);
}
function selectRolesForList(){
	var roles=$("#roleIds").find("option");
	 for (var index=0;index<roles.length;index++){
    	 $("#roleTable").setSelection(roles[index].value,true);
     }
}
</script>