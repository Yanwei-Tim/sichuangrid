<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="center-left">
	<div>
		<div class="ui-widget">
			<input id="org_autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh" ></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>

<div class="center-right">
	<div class="content">
		<div class="ui-corner-all" id="nav">
			<input type="hidden" id="permissionId" value=${(moduleTable.permission.id)?if_exists}/>
			<div>
				<@pop.JugePermissionTag ename='addModuleTable'>
					<a id="add"  href="javascript:void(0)"><span>新增</span></a>
				</@pop.JugePermissionTag>
				<@pop.JugePermissionTag ename='enableModuleTable'>
					<a id="stopModuleTable"  href="javascript:void(0)"><span>全部禁用</span></a>
					<a id="startModuleTable"  href="javascript:void(0)"><span>全部启用</span></a>
				</@pop.JugePermissionTag>
				<a id="refresh"  href="javascript:void(0)"><span>刷新</span></a>
			</div>
		</div>
		<div style="clear: both;"></div>
		<div style="width: 100%">
			<table id="moduleTableList"> </table>
			<div id="moduleTableListPager"></div>
		</div>
		<div id="moduleTableDialog"></div>
	</div>
</div>

<script type="text/javascript">
var tree;
$(document).ready(function(){
	var centerHeight=$(".ui-layout-center").height();
	$(".orgTree").height(centerHeight-70);
	$("#org_autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: "${path}/sysadmin/permissionManage/findPermissionsByPermissionName.action",
				data:{"name":request.term},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.cname+"-"+item.moduleName,
							value: item.cname,
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
			$("#user_autocomplete").removeAttr("userId");
			$("#user_autocomplete").val("");
			$.ajax({
				url:PATH+"/sysadmin/permissionManage/findPermissionsNodeId.action?id="+ui.item.id,
				success:function(data){
					if(data.indexOf("/")==0){
						data = data.substring(1)
					}
				 	var yes = "orgTree-root/" + data ;
					$.searchChild(tree,yes);
				}
			});
		}
	});
	//再这里调用orgTreeManage.js中的方法,初始化了树start
	tree=$("#orgTree").initPermissionTree();

	//绑定tree的点击事件
	$.addClick(tree,afterChangNode);
	
	$("#moduleTableList").jqGridFunction({
		datatype: "local",
		sortname:"executeLevel",
		sortorder:"ASC",
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true,sortable:false},
	   	    {name:'active',index:'active',hidden:true,sortable:false},
	   	    {name:'permission.ename',index:'ename',hidden:true,sortable:false},
	   	    {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter_ModuleTable,width:60,align:"center"},
	   	    {name:"enableModuleTable",index:"enableModuleTable",label:"启/禁用",sortable:false,formatter:enableModuleTable,width:60,align:"center"},
	   	    {name:'beanName',label:'Bean名称' ,width:100,align:'center'},
	   	    {name:'tableName',label:'业务表名' ,width:80,align:'center'},
	   	    {name:'orgIdColumn',label:'网格字段' ,width:80,align:'center'},
	   	    {name:'orgCodeColumn',label:'内部码字段' ,width:'80px',align:'center'},
	   	    {name:'countScript',label:'统计语句'},
	   	    {name:'deleteScript',label:'删除语句'},
	   	    {name:'updateScript',label:'更新语句'}	   	    
		],
		shrinkToFit:true,
		showColModelButton:false
	});
	
	$("#add").click(function(){
		$("#moduleTableDialog").createDialog({
			title:"新增业务表信息",
			width: 750,
			height: 450,
			url:'${path}/orgchange/moduleTableManage/dispatch.action?mode=add&moduleTable.permission.id='+$("#permissionId").val(),
			buttons: {
				"保存" : function(event){
		   			$("#moduleTableForm").submit();
				},
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	});
	
	$("#refresh").click(function(){
		getModuleTableList({"moduleTable.permission.id":$("#permissionId").val()});
	});
	
	$("#stopModuleTable").click(function(){
		var permissionId = $("#permissionId").val();
		if(permissionId == 'orgTree-root'){
			permissionId = "";
		}
		$.confirm({
		title:"确认禁用",
		message:"确定要全部禁用吗？",
		okFunc: function(){
				$.ajax({
				url:'${path}/orgchange/moduleTableManage/stopModuleTable.action',
				datatype:'json',
				type:"post",
				data:{
					permissionId:permissionId
				},
				success:function(data){
					   $.messageBox({message:"禁用成功!"});
					   $("#moduleTableList").trigger("reloadGrid"); 
					}
				}); 
			}
		});
	});
	
	$("#startModuleTable").click(function(){
		var permissionId = $("#permissionId").val();
		if(permissionId == 'orgTree-root'){
			permissionId = "";
		}
		$.confirm({
		title:"确认启用",
		message:"确定要全部启用吗？",
		okFunc: function(){
				$.ajax({
				url:'${path}/orgchange/moduleTableManage/startModuleTable.action',
				datatype:'json',
				type:"post",
				data:{
					permissionId:permissionId
				},
				success:function(data){
					   $.messageBox({message:"启用成功!"});
					   $("#moduleTableList").trigger("reloadGrid"); 
					}
				}); 
			}
		});
	});
	
});


//列表显示
function getModuleTableList(obj){
	$("#moduleTableList").setGridParam({
		url:'${path}/orgchange/moduleTableManage/findModuleTables.action',
		datatype: "json",
		page:1,
		mytype:"post"
	});
	$("#moduleTableList").setPostData(obj);
	$("#moduleTableList").trigger("reloadGrid"); 
}

//点击进行具体模块表的数据显示
function afterChangNode(node) {
	var id = node.attributes.id;
	$("#permissionId").val(id);
	getModuleTableList({"moduleTable.permission.id":$("#permissionId").val()});
}

//修改
function updateModuleTable(selectedId){
	$("#moduleTableDialog").createDialog({
		title:"修改业务表信息",
		width: 650,
		height: 450,
		url:'${path}/orgchange/moduleTableManage/dispatch.action?mode=update&moduleTable.id='+selectedId+'&moduleTable.permission.id='+$("#permissionId").val(),
		buttons: {
			"保存" : function(event){
		   		$("#moduleTableForm").submit();
			},
			"关闭" : function(event){
				$(this).dialog("close");
			}
		}
	});
}
//删除
function deleteModuleTable(selectedId){
	var moduleTable =  $("#moduleTableList").getRowData(selectedId);
	var ename=moduleTable["permission.ename"];
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?",
		okFunc: function(){
			$.ajax({
				url:'${path}/orgchange/moduleTableManage/deleteModuleTable.action?mode=delete&moduleTable.id='+selectedId+'&moduleTable.permission.ename='+ename+'&count='+$("#moduleTableList").getGridParam("records"),
				success:function(data){
					if(data>0){
					    $.messageBox({message:"成功删除!"});
						$("#moduleTableList").trigger("reloadGrid");
					}else{
						$.messageBox({
							message:"删除出错!",
							level:"warn"
						});
					}
				}
			});
		}
	});
}
//操作
function operateFormatter_ModuleTable(el,options,rowData){
	return "<@pop.JugePermissionTag ename='updateModuleTable'><a href='javascript:' onclick='updateModuleTable("+rowData.id+")'><span>修改</span></a></@pop.JugePermissionTag><@pop.JugePermissionTag ename='deleteModuleTable'> | <a href='javascript:;' onclick='deleteModuleTable("+rowData.id+")'><span>删除</span></a></@pop.JugePermissionTag>";
}
function enableModuleTable(el,options,rowData){
	var enableStatus = rowData.active;
	if(enableStatus=='false' || enableStatus==false){
		return "<@pop.JugePermissionTag ename='enableModuleTable'><a href='javascript:' onclick='startModuleFunc("+rowData.id+")'>启用</a></@pop.JugePermissionTag>";
	}else{
		return "<@pop.JugePermissionTag ename='enableModuleTable'><a href='javascript:' onclick='stopModuleFunc("+rowData.id+")'>禁用</a></@pop.JugePermissionTag>";
	}
}

//启用
function startModuleFunc(selectIds){
	if(selectIds==null || selectIds==""){
		$.messageBox({level:'warn',message:"请至少选中一条信息进行启用"});
	}
	$.ajax({
		url:'${path}/orgchange/moduleTableManage/startModuleTable.action',
		datatype:'json',
		type:"post",
		data:{
			moduleId:selectIds
		},
		success:function(data){
			$.messageBox({message:"启用成功!"});
			$("#moduleTableList").trigger("reloadGrid"); 
			}
		}); 
}
//停用
function stopModuleFunc(selectIds){
	if(selectIds==null || selectIds==""){
		$.messageBox({level:'warn',message:"请至少选中一条信息进行启用"});
	}
	$.ajax({
		url:'${path}/orgchange/moduleTableManage/stopModuleTable.action',
		datatype:'json',
		type:"post",
		data:{
			moduleId:selectIds
		},
		success:function(data){
			$.messageBox({message:"停用成功!"});
			$("#moduleTableList").trigger("reloadGrid"); 
			}
		}); 
}

//是否主表
function isMainTableFormatter(el,options,rowData){
	if(rowData.isMainTable==1){
		return "是";
	}else{
		return "否";
	}
}
</script>