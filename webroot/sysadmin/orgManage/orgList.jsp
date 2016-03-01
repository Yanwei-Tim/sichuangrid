<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/includes/baseInclude.jsp" />

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>

<div class="center-left">
	<div id="refreshOrgTreeDiv">
		<div class="ui-widget">
			<input id="org-tree-autocomplete" type="text" value=""/>
			<button id="refreshOrgTree" class="ui-icon ui-icon-refresh"></button>
		</div>
	</div>
	<div class="orgTree">
		<div id="orgTree"></div>
	</div>
</div>
<div class="center-right">
	<div class="content">
		<div id="content-top">
			<div class="ui-corner-all" id="nav">
				<pop:JugePermissionTag ename="addOrganization">
					<a id="add" href="javascript:;"><span>新增</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="updateOrganization">
					<a id="update" href="javascript:void(0)"><span>修改</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="deleteGridOrganization">
					<a id="delete" href="javascript:void(0)"><span>删除</span></a>
				</pop:JugePermissionTag>
					<pop:JugePermissionTag ename="importOrgs">
						<a id="importOrgs" href="javascript:void(0)"><span>导入</span></a>
					</pop:JugePermissionTag>
					
				<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('admin')">
					<a id="doJob" href="javascript:void(0)"><span>执行变更</span></a>
					<a id="compare" href="javascript:void(0)"><span>组织机构对比</span></a>
				</s:if>
				<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()">
					<a id="newMarge" href="javascript:void(0)"><span>合并</span></a>
					<a id="transfers" href="javascript:void(0)"><span>迁移</span></a>
				</s:if>
				
			</div>
			<div style="clear:both"></div>
			<div class="container container_24 zt_grid_0722" >
				<input type="hidden" value="" id="orgDetail-orgId" />
				<input type="hidden" value="" id="orgDetail-orgLevel-internalId" />
				<div class="grid_4">
					<label >部门名称：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-orgName" />
				</div>
				<div class="grid_4">
					<label>联系电话：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-contactWay" />
				</div>
				<div style="clear:both"></div>
				<div class="grid_4">
					<label>部门类型：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-orgType" />
				</div>
				<div class="grid_4">
					<label>部门级别：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-orgLevel" />
				</div>
				<div style="clear:both"></div>
				<div class="grid_4">
					<label>备注：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-remark" />
				</div>
				<div class="grid_4">
					<label>部门编号：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgDetail-departmentNo" />
				</div>
				<div style="clear:both"></div>
			</div>
			<div style="clear:both"></div>
			<pop:JugePermissionTag ename="moveOrganization" >
				<div class="ui-corner-all" id="nav">
					<a id="toPrevious" href="javascript:void(0)"><span>上移</span></a>
					<a id="toNext" href="javascript:void(0)"><span>下移</span></a>
					<a id="toFirst" href="javascript:void(0)"><span>到顶</span></a>
					<a id="toEnd" href="javascript:void(0)"><span>到底</span></a>
				</div>
			</pop:JugePermissionTag>
			<div style="clear:both"></div>
		</div>
		<div style="width: 100%">
			<table id="orgChildren-dataGrid"></table>
			<div id="orgChildren-dataGridPager"></div>
		</div>
	</div>
</div>
<div id="org-dailog"></div>
<s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getOrganizationTypeProperties" ignoreContextParams="true">
	<s:param name="propertyDomain.domainName" value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_TYPE">
	</s:param>
</s:action>
<s:action name="findPropertyDictByDomainName" namespace="/sysadmin/propertyManage" var="getOrganizationLevelProperties" ignoreContextParams="true">
	<s:param name="propertyDomain.domainName" value="@com.tianque.domain.property.PropertyTypes@ORGANIZATION_LEVEL">
	</s:param>
</s:action>
<script type="text/javascript" >
var orglevel = '<s:property value="#getFullOrgById.organization.orgLevel.internalId"/>';
var tree;
function toggleButtons(){
	var rowId = $("#orgChildren-dataGrid").jqGrid('getGridParam','selrow');
}

function isOperate(){
	if($("#orgDetail-orgLevel-internalId").val()<=orglevel){
		if(orglevel<='<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>'
					&&$("#orgDetail-orgLevel-internalId").val()>'<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>'){
			return false;
		}
		return true;
	}else{
		var isAdmin = '<s:property value="#loginAction.user.admin"/>';
		var userName = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/>';
		if(userName=='admin' || isAdmin=='true'){
			return true;
		}else{
			return false;
		}
	}
}

function isAdd(){
	if($("#orgDetail-orgLevel-internalId").val()=='<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>'){
		return true;
	}else{
		var isAdmin = '<s:property value="#loginAction.user.admin"/>';
		var userName = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/>';
		if(userName=='admin' || isAdmin=='true'){
			return true;
		}else{
			return false;
		}
	}
}

//当节点失去焦点后，失效维护按钮
function toggleMaintainsButtons(){
	var node = $.getSelectedNode(tree);
	if($("#orgDetail-orgId").val()&&$("#orgDetail-orgId").val()!=""){
		if(node.attributes.orgTypeInternalId==0&&node.attributes.orgLevelInternalId!=0){
			$("#add").buttonEnable();
		}else{
			$("#add").buttonDisable();
		}
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
	}else{
		$("#add").buttonDisable();
	}
}

function isNode(){
	return $.getSelectedNode(tree).hasChildNodes();
}

function updateOrgDetail(data){
	$("#org-tree-autocomplete").val(data.orgName);
	if(data==""){
		$("#orgDetail-orgInternalCode").val("");
		$("#orgDetail-orgName").val("");
		$("#orgDetail-orgType").val("");
		$("#orgDetail-orgLevel").val("");
		$("#orgDetail-contactWay").val("");
		$("#orgDetail-orgId").val("");
		$("#orgDetail-remark").val("");
		$("#orgDetail-departmentNo").val("");
	}else{
		$("#orgDetail-orgInternalCode").val(data.orgInternalCode);
		$("#orgDetail-orgName").val(data.orgName);
		$("#orgDetail-orgType").val(data.orgType.displayName);
		$("#orgDetail-orgLevel").val(data.orgLevel.displayName);
		$("#orgDetail-orgLevel-internalId").val(data.orgLevel.internalId);
		if(data.contactWay!=null){
			$("#orgDetail-contactWay").val(data.contactWay);
		}else{
			$("#orgDetail-contactWay").val("");
		}
		$("#orgDetail-orgId").val(data.id);
		if(data.remark!=null){
			$("#orgDetail-remark").val(data.remark);
		}else{
			$("#orgDetail-remark").val("");
		}
		if(data.departmentNo!=null){
			$("#orgDetail-departmentNo").val(data.departmentNo);
		}else{
			$("#orgDetail-departmentNo").val("");
		}
		if(isNode()){
			$("#marge").buttonDisable();
		}else{
			$("#marge").buttonEnable();
		}
	}
}
var orgTypeData = new Array();
var orgTypeInternalIdArray = new Array();
<c:forEach items="${getOrganizationTypeProperties.propertyDicts}" var="propertyDict">
orgTypeData[${propertyDict.id}]='${propertyDict.displayName}';
orgTypeInternalIdArray[${propertyDict.id}]='${propertyDict.internalId}';
</c:forEach>

var orgTypeCurrent = '<s:property value="#getFullOrgById.organization.orgType.internalId"/>';

var orgType = function(el,options,rowData){
	if(rowData["orgType.id"]){
		return orgTypeData[rowData["orgType.id"]];
	}
	if(!rowData.orgType){
		return "";
	}
	return orgTypeData[rowData.orgType.id];
}

var orgLevelData = new Array();
<c:forEach items="${getOrganizationLevelProperties.propertyDicts}" var="propertyDict">
orgLevelData[${propertyDict.id}]='${propertyDict.displayName}';
</c:forEach>

var orgLevel = function(el,options,rowData){
	if(rowData["orgLevel.id"]){
		return orgLevelData[rowData["orgLevel.id"]];
	}
	if(!rowData.orgLevel){
		return "";
	}
	return orgLevelData[rowData.orgLevel.id];
}
$(function(){

	var centerHeight=$("div.ui-layout-center").outerHeight();
	$(".orgTree").height(centerHeight-70);

	$("#importOrgs").click(function(event){
		$("#org-dailog").createDialog({
			width: 400,
			height: 220,
			title:'数据导入',
			url:PATH+'/sysadmin/orgManage/importOrg.jsp?dataType=orgData&dialog=org-dailog&startRow=6&templates=ORGANIZATION&listName=orgChildren-dataGrid',
			buttons:{
				"提交" : function(event){
			      $("#mForm").submit();
			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			},
			shouldEmptyHtml:false
		});
	});

	//双击行操作
	function doubleClickTable(rowid){
		$.createDialog({
			width:680,
			height:300,
			title:'查看部门详细',
			url:PATH+'/sysadmin/orgManage/toOrgDetailJsp.action?organization.id='+ rowid,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	function afterChangNode(node){
		var nodeId = node.attributes.id;
		$.get(PATH+"/sysadmin/orgManage/ajaxOrganization.action", {"organization.id":nodeId},function(data){
			updateOrgDetail(data);
			$("#orgChildren-dataGrid").setGridParam({
				url:PATH+"/sysadmin/orgManage/ajaxOrgPage.action"+'?parentId='+nodeId,
				datatype:'json'
			});
			$("#orgChildren-dataGrid").trigger("reloadGrid");
		});
	}
	function stringFormatter(str){
		if(str==undefined){
			return "";
		}
		return str;
	}
	$("#org-tree-autocomplete").autocomplete({
		source: function(request, response) {
			$.ajax({
				url: PATH+"/sysadmin/orgManage/ajaxFindOrganizationsByOrgName.action",
				data:{"organization.orgName":function(){return request.term;}},
				success: function(data) {
					response($.map(data, function(item) {
						return {
							label: item.orgName+","+stringFormatter(item.contactWay),
							value: item.orgName,
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
				url:PATH+"/sysadmin/orgManage/ajaxSearchParentNodeIds.action?organization.id="+ui.item.id,
				success:function(data){
					$.searchChild(tree,data);
				}
			});
		}
	});
	if(orgTypeCurrent == '1'){
		tree=$("#orgTree").initFunctionalTree();
	}else{
		tree=$("#orgTree").initTree();
	}

	$.addClick(tree,afterChangNode);



	$("#orgChildren-dataGrid").jqGridFunction({
	   	url:PATH+'/sysadmin/orgManage/ajaxOrgPage.action',
		datatype: "local",
	   	colNames:['id','子部门名称',  '子部门类型','子部门级别','子部门联系电话', '备注'],
	   	colModel:[
	   		{name:'id',index:'id',hidden:true,sortable:false},
	   		{name:'orgName',index:'orgName',sortable:true},
	   		{name:'orgType.id',index:'orgType.id',sortable:true,formatter:orgType},
	   		{name:'orgLevel.id',index:'orgLevel.id', sortable:true,formatter:orgLevel},
	   		{name:'contactWay',index:'contactWay', sortable:true},
	   		{name:'remark',index:'remark', sortable:false}
	   	],
	    loadComplete: function(){toggleButtons();},
		ondblClickRow: doubleClickTable,
	    onSelectRow:function(){toggleButtons();},
	    rowNum:-1,
	    pager:false,
	    shrinkToFit:true,
	    showColModelButton:false,
	    height:$(".ui-layout-west").height()-$(".path").height()-$("#content-top").height()-80
	});

	$("#refreshOrgTree").click(function(){
		$.selectRootNode(tree);
	    $("#org-tree-autocomplete").val("");
	    $("#orgChildren-dataGrid").setGridParam({url:PATH+"/sysadmin/orgManage/ajaxOrgPage.action"});
	    $("#orgChildren-dataGrid").trigger("reloadGrid");
	});

/////为按钮添加事件
	$('#toEnd').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toEnd(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id":$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"last",
					"organization.parentOrg.id":$("#orgDetail-orgId").val()
				},
				function(){$.toEndNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});
	$('#toFirst').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toFirst(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id":$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"first",
					"organization.parentOrg.id":$("#orgDetail-orgId").val()
				},
				function(){$.toFirstNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});
	$('#toPrevious').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toPrevious(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id" : $("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"before",
					"organization.parentOrg.id":$("#orgDetail-orgId").val(),
					"referOrgId":$("#orgChildren-dataGrid").getSelectedRowDom().prev().attr("id")
				},
				function(){$.toPreviousNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});
	$('#toNext').click(function(){
		if($("#orgChildren-dataGrid").jqGrid('getGridParam','selrow')){
			$("#orgChildren-dataGrid").toNext(
				PATH+"/sysadmin/orgManage/ajaxMoveOrganization.action",
				{
					"organization.id" : $("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'),
					position:"after",
					"organization.parentOrg.id":$("#orgDetail-orgId").val(),
					"referOrgId":$("#orgChildren-dataGrid").getSelectedRowDom().next().attr("id")
				},
				function(){$.toNextNode(tree,$("#orgChildren-dataGrid").jqGrid('getGridParam','selrow'));toggleButtons();}
			);
		}else{
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
	});


	$("#add").click(function(){
		if($("#orgDetail-orgType").val()=='职能部门'){
			$.messageBox({level:"warn",message:"职能部门下不能再新增部门！"});
			return;
		};
		if(isAdd()){
			if($("#orgDetail-orgLevel").val()!='片组片格'){
				$("#org-dailog").createDialog({
					width:680,
					height:300,
					title:'新建部门',
					url:PATH+'/sysadmin/orgManage/orgCreateJsp.action?organization.parentOrg.id='+$("#orgDetail-orgId").val(),
					buttons :{
						"保存" : function(){
							$("#org-create-form").submit();
						},
						"关闭" : function(){
							$(this).dialog("close");
						}
					}
				});
			}else{
				$.messageBox({level:"warn",message:"网格层级不能再新增部门！"});
		 		return;
			}
		}else{
			$.messageBox({level:"warn",message:"您没有操作权限，请联系系统管理员！"});
	 		return;
		}
	});

	$("#marge").click(function(){
		$("#org-dailog").createDialog({
			width:280,
			height:400,
			title:'部门合并',
			url:PATH+'/sysadmin/orgManage/mergeTree.jsp?orgId='+$("#orgDetail-orgId").val(),
			buttons :{
				"保存" : function(){
					closeDialog();
					$("#maintainOrgForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	
	$("#update").click(function(){
		if(isOperate()){
			$("#org-dailog").createDialog({
				width:680,
				height:300,
				title:'修改部门',
				url:PATH+'/sysadmin/orgManage/toUpdateOrgJsp.action?mode=edit&organization.id='+$("#orgDetail-orgId").val(),
				buttons :{
					"保存" : function(){
						$("#org-update-form").submit();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		}else{
			$.messageBox({level:"warn",message:"您没有操作权限，请联系系统管理员！"});
	 		return;
		}
	});

	$("#delete").click(function(){
		if(isOperate()){
		$.confirm({
			title:"删除部门",
			message:"部门一经删除，无法恢复，确定删除此部门吗？",
			okFunc: function() {
				$.ajax({
					url:PATH+'/sysadmin/orgManage/checkDeleteOrgById.action?organization.id='+$("#orgDetail-orgId").val(),
					success:function(data){
						if(data != true){
							$.messageBox({
								level:"warn",
								message:data
							});
							return;
						}
						$.ajax({
							url:PATH+'/sysadmin/orgManage/ajaxDeleteById.action?organization.id='+$("#orgDetail-orgId").val(),
							success:function(data){
								if(data == true){
									$.messageBox({
										message:"部门删除成功！"
									});
									var childNode = $.getSelectedNode(tree);
									var parentNode = childNode.parentNode;
									parentNode.removeChild(childNode);
								}else{
									$.messageBox({
										level:"warn",
										message:data
									});
									$("#delete").buttonEnable();
								}
							},error:function(){
								$.messageBox({
									level:"warn",
									message:"不能删除此部门，此部门已被引用"
								});
								$("#delete").buttonEnable();
							}
						});
					}
				});
			}
		});
		}else{
			$.messageBox({level:"warn",message:"您没有操作权限，请联系系统管理员！"});
	 		return;
		}
	});

	$("#deletegrid").click(function(){
        var node = $.getSelectedNode(tree);
	    if($.getSelectedNode(tree).attributes.orgLevelInternalId != <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>){
	       $.confirm({
			title:"提示",
			message:"只能删除网格"
			})
			return;
	   }
		 //判断网格下面是否还有用户
	    $.ajax({
			url:PATH+'/sysadmin/orgManage/checkIsExistUserById.action?organization.id='+$("#orgDetail-orgId").val(),
			success:function(data){
		    	if(data != true){
					$.messageBox({
						message:data
					});
				}else{
					if(isOperate()){
						$.confirm({
							title:"删除部门",
							message:"部门一经删除，无法恢复，确定删除此部门吗？",
							okFunc: function() {
								$.ajax({
									url:PATH+'/sysadmin/orgManage/ajaxDeletegridById.action?organization.id='+$("#orgDetail-orgId").val(),
									success:function(data){
										if(data == true){
											$.messageBox({
												message:"部门删除成功！"
											});
											var childNode = $.getSelectedNode(tree);
											var parentNode = childNode.parentNode;
											parentNode.removeChild(childNode);
									    }
							         }
								});
							}
						});
					}else{
						$.messageBox({level:"warn",message:"您没有操作权限，请联系系统管理员！"});
				 		return;
					}
				}
	         }
		});  
	});
	
	
	
	/**组织机构迁移*/
	$("#transfers").click(function(){
        var node = $.getSelectedNode(tree);
	    if(node.attributes.orgLevelInternalId >= 40){
	       $.confirm({
			title:"提示",
			message:"请选择市级以下部门进行迁移"
			})
			return;
	   }
	   
	   $("#org-dailog").createDialog({
			width : 1010,
			height : 600,
			title : '迁移',
			url : PATH + "/orgchange/orgChangeInfoManage/dispatch.action?operate=transfers&id=" + $("#orgDetail-orgId").val(),
			buttons : {
				"取消" : function(){
					$(this).dialog("close");
				},
				"保存":function(){
					var operate="transfer";
					if(!addTransferAdnMergeFun(operate,this)){
						return;
					}
				}
			}
		});
	});


	/**执行job操作*/
	$("#doJob").click(function(){
		$("#org-dailog").createDialog({
			width : 1010,
			height : 450,
			title : '变更信息列表',
			url : PATH + "/orgchange/orgChangeInfoManage/dispatch.action?mode=doJob",
			buttons : {
				"关闭":function(){
					$(this).dialog('close');
				}
			}
		});
	});
	
	$("#compare").click(function(){
		$("#org-dailog").createDialog({
			width : 800,
			height : 600,
			title : '组织机构信息对比列表',
			url : PATH + "/orgchange/orgChangeInfoManage/dispatch.action?mode=compare",
			buttons : {
				"关闭":function(){
					$(this).dialog('close');
				}
			}
		});
	});
	
	/**组织机构合并*/
	$("#newMarge").click(function(){
	    
        var node = $.getSelectedNode(tree);
	    if(node.attributes.orgLevelInternalId >= 40){
	       $.confirm({
			title:"提示",
			message:"请选择市级以下部门进行合并"
			})
			return;
	   }
	   
	   $("#org-dailog").createDialog({
			width : 1010,
			height : 600,
			title : '合并',
			url : PATH + "/orgchange/orgChangeInfoManage/dispatch.action?operate=meger&id=" + $("#orgDetail-orgId").val(),
			buttons : {
				"取消" : function(){
					$(this).dialog("close");
				},
				"保存":function(){
					var operate="meger";
					if(!addTransferAdnMergeFun(operate,this)){
						return;
					}
				}
			}
		});
	});
	
	function addTransferAdnMergeFun(operate,dialog){
		$.confirm({
			title:"确认保存",
			message:"此操作一旦保存将不可撤销，确认继续？",
			okFunc: function() {
				var row=$("#orgChildren-dataGrid-TransfersAndMerge").getRowData();
				if(row.length!=0){
					var idsStr=getOrgIdStr(row);
					$.ajax({
						url:PATH+"/orgchange/orgChangeInfoManage/addOrgChangeInfo.action?operate="+operate+"&organization.id=" + $("#orgDetail-orgId").val()+"&orgIds="+idsStr,
						success:function(data){
							if(data!=true){
								$.messageBox({message:data});
							}else{
								$.messageBox({message:"组织机构操作成功！"});
							}
						}
					});
				}
				$(dialog).dialog("close");
			}
		});
		return false;
	}
	//得到以","组装的orgId字符串
	function getOrgIdStr(row){
			var ids="";
			for(var i=0;i<row.length;i++){
				ids+=row[i]["id"]+",";
			}
			return ids;
	}
	
	
});

</script>
