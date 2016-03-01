<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
ul#selectzones {margin: 0; padding: 0;}
ul#selectzone {margin: 0; padding: 0;}
ul#selectzones li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
ul#selectzones span.ui-icon {float: left; margin: 0 4px;}
.defaultButton{background:url(${resource_path}/resource/images/button.jpg) no-repeat;border:0;width:54px;height:24px;line-height:24px;text-align:center;color:#2C6EA2;}
</style>
<div id="dialog-form" title="岗位维护" class="container container_24">
	 <form id="maintainForm" method="post"	action="" >
	 	<input type="hidden" name="useInLevelId" value="${roles[0].useInLevel.id}" id="useInlevel"/>
		<div class="grid_3 lable-right">
			<label class="form-lbl">所属部门：</label>
		</div>
		<div class="grid_6">
	    	<input id="userOrganization" type="text" name="user.organization.orgName" class="form-txt" />
	    	<input id="userOrganizationId" type="hidden" name="orgId" class="form-txt" />
		</div>
	  	<div class="grid_3 lable-right">
			<label class="form-lbl">岗位名称：</label>
		</div>
		<div class="grid_7">
			<select name="roleName" class="form-select" id="useInl">
		   		<s:iterator value="roles" id="role">
		   			<option value='<s:property value="id" />'><s:property value="roleName" /></option>
		   		</s:iterator>
			</select>
		</div>
   		<div class="grid_2 lable-right" >
  			<input type="button" value="查 询" id="search_users" style="width: 50px;height: 25px;"/>
  		</div>
	</form>
	<div class='clearLine'></div>
	<div style="width:100%;">
	   	 <table id="roleTable" ></table>
	   	 <div id="roleTablePager"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	var self=$("#userOrganization");
	var selfId=self.attr("id");
	var defaultOption={
		store:new Ext.data.SimpleStore({fields:[],data:[[]]}),
	    editable:false, //禁止手写及联想功能
	    mode: 'local',
	    triggerAction:'all',
	    name:'org',
	    fieldLabel : '组织机构',
	    maxHeight: 250,
	    tpl: "<div id="+selfId+"-tree"+" style='width:100%;overflow:auto;'>"+"</div>", //html代码
	    selectedClass:'',
	    onSelect:Ext.emptyFn,
	    //emptyText:'请选择...',
	    listWidth:180,
	    inputName:"orgId",
	    allOrg:false,
	    url:false
	};
	function style(){
		$("#"+selfId+"-tree").parent().parent().remove();
		self.width(self.width()-25);
		$("#"+selfId+"tree").bgiframe();
	};
	style();
	var comboWithTooltip = new Ext.form.ComboBox(defaultOption);
	comboWithTooltip.applyTo(selfId);
	var url;
	if(defaultOption.url){
		url = defaultOption.url;
	}else{
		url = "/sysadmin/orgManage/firstLoadExtTreeData.action";
	}
	var tree ;
	$("#"+selfId+"-tree").height(defaultOption.maxHeight).width(defaultOption.listWidth);
	$.ajax({
		url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+'<s:property value="#parameters.orgId"/>',
		success:function(data){
			tree = $("#"+selfId+"-tree").initAdministrativeTree({shouldJugeMultizones:true,allOrg:defaultOption.allOrg,url:url});
			tree.on("click",function(node,e) {
		        comboWithTooltip.setRawValue(node.text);
		        comboWithTooltip.collapse();
		        $("input[name='"+defaultOption.inputName+"']").val(node.id);
			});
			$.searchChild(tree,data);
		}
	});

	$("#roleTable").jqGridFunction({
		 datatype: "local",
		 height:290,
		 width:670,
		 colModel:[
		          {name:"id",index:"id",hidden:true},
		          {name:"userName",index:'userName',label:'用户名'},
		      	  {name:"name",index:"name",label:'用户姓名'},
		      	  {name:"organization.orgName", index:"orgInternalCode", width:200,label:"所属网格"},
			      {name:"roles",index:"roles",label:'所在岗位',sortable:false,formatter:getRoleNames,align:'center'}
				],
		multiselect:true,
		onSelectRow:selectRow
	});
	$("#search_users").click(function(){
		$.ajax({
			async:false,
			url:"${path}/sysadmin/roleManage/isTrueSelected.action",
			data:{
				'orgId':$("#userOrganizationId").val(),
				'useInLevelId':$("#useInlevel").val()
			},
			success:function(responseData){
				if(!responseData){
					$.messageBox({
						message:"选中岗位所在层级不存在其他岗位名称！",
						level: "error"
					 });
					 return;
				}else{
					$("#roleTable").setGridParam({
						url:"${path}/sysadmin/roleManage/findUsersList.action",
						datatype:'json',
						page:1
					});
					$("#roleTable").setPostData({
						'orgId':$("#userOrganizationId").val(),
						'roleId':$("#useInl").val()
					});
					$("#roleTable").trigger("reloadGrid");
			    }
			}
		});
	});
});

function getRoleNames(el,options,rowData){
    var j=0;
    var roleNames="";
    if(rowData.roles!=null && rowData.roles.length >0){
    	var n = rowData.roles.length;
        for(var m=0;m<n;m++){
        	j++;
            if(j!=n){
                roleNames+=rowData.roles[m].roleName+",";
            }else{
            	roleNames+=rowData.roles[m].roleName;
            }
        }
    }else{
    	roleNames="系统管理员"
    }
    return roleNames;
}

function selectRow(){
	var selectedCounts = getActualjqGridMultiSelectCount("roleTable");
	var count = $("#roleTable").jqGrid("getGridParam","reccount");
	if(selectedCounts == count&&selectedCounts!=0){
		jqGridMultiSelectState("roleTable", true);
	}else{
		jqGridMultiSelectState("roleTable", false);
	}
}
</script>