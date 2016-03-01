<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<s:include value="/common/orgSelectedComponent.jsp" />
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入菜单名称" id="searchByMenuName"
					class="searchtxt" onblur="value=(this.value=='')?'请输入菜单名称':this.value;"
					onfocus="value=(this.value=='请输入菜单名称')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchKeyWord">
			<a id="searchWeChatMenu" href="javascript:void(0)"><span>高级搜索</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="addWeChatMenu">
			<a id="addWeChatMenu" href="javascript:void(0)"><span>新增菜单</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateMenu">
			<a id="updateWeChatMenu" href="javascript:void(0)"><span>修改菜单</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateWeChatMenu">
		
			 <a href="javascript:;" class="nav-dropdownBtn"><span>绑定素材</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
			 <div class="nav-sub-buttons">
				<a id="addText" href="javascript:void(0)"><span>文本素材</span></a>
				<a id="addImage" href="javascript:void(0)"><span>图片素材</span></a>
				<a id="addVoice" href="javascript:void(0)"><span>语音素材</span></a>
				<a id="addNews" href="javascript:void(0)"><span>图文素材</span></a>
				
			</div>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="weChatMenuList">
		</table>
		<div id="weChatMenuListPager"></div>
	</div>
</div>
<div id="weChatMenuMaintanceDialog"></div>
<script type="text/javascript">
<pop:formatterProperty name="sourceTypeDict" domainName="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE" />
$(document).ready(function(){
	var currentOrgId=getCurrentOrgId();
	//快速检索
	$("#refreshSearchKey").click(function(){
		$("#searchByMenuName").attr("value","请输入菜单名称");
		loadWeChatMenu({	"weChatMenu.org.id" :currentOrgId,
			            "weChatMenu.isLeaf" : 1
			       });
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchByMenuName=$("#searchByMenuName").val();
		if(searchByMenuName != null && "请输入菜单名称" != searchByMenuName){
			var queryObj = {
					"weChatMenu.org.id" :currentOrgId,
		            "weChatMenu.isLeaf" : 1,
		            "weChatMenu.menuName":searchByMenuName
			};
			loadWeChatMenu(queryObj);
		}
    });
	//新增菜单 
	$("#addWeChatMenu").click(function(){
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			$("#weChatMenuMaintanceDialog").createDialog({
				width: 1250,
				height: 560,
				title:'新增菜单',
				url:'${path}/weChatMenuManage/dispatch.action?weChatMenu.org.id='+currentOrgId+'&mode=addWeChatMenu',
				buttons: {
			   		"创建" : function(event){
			   			$("#maintainForm").submit();
			   		},
			   		"关闭" : function(event){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
	})
	//修改菜单
	$("#updateWeChatMenu").click(function(){
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var selectedIds = $("#weChatMenuList").jqGrid("getGridParam", "selarrrow");
			if(null==selectedIds||selectedIds.length!=1){
				$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		 		return;
			}
			rowdata=$("#weChatMenuList").jqGrid('getRowData',selectedIds);
			var weChatUserId=rowdata["weChatUserId"];
			$("#weChatMenuMaintanceDialog").createDialog({
				width: 1250,
				height: 560,
				title:'修改菜单',
				url:'${path}/weChatMenuManage/dispatch.action?weChatMenu.org.id='+currentOrgId+'&mode=updateWeChatMenu&weChatMenu.weChatUserId='+weChatUserId,
				buttons: {
			   		"修改" : function(event){
			   			$("#maintainForm").submit();
			   		},
			   		"关闭" : function(event){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
	})
	//绑定文本素材
	$("#addText").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var selectedIds = $("#weChatMenuList").jqGrid("getGridParam", "selarrrow");
			if(null==selectedIds||selectedIds.length!=1){
				$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
		 		return;
			}
			rowdata=$("#weChatMenuList").jqGrid('getRowData',selectedIds);
			if(rowdata["menuType"]=="网页"){
				$.messageBox({level:"warn",message:"网页类型的菜单不能绑定素材！"});
		 		return;
			}
			var urls='${path}/weChatMenuManage/validataSourceOrWeChatUser.action?sourceType=1&weChatMenu.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#weChatMenuMaintanceDialog").createDialog({
							width: 700,
							height: 350,
							title:'绑定文本素材',
							url:'${path}/weChatMenuManage/dispatch.action?mode=add&sourceType=1&weChatMenu.org.id='+currentOrgId+'&weChatMenu.id='+selectedIds,
							buttons: {
						   		"保存" : function(event){
						   			$("#maintainForm").submit();
						   		},
						   		"关闭" : function(event){
						        	$(this).dialog("close");
						   		}
							}
						});
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
		     		$.messageBox({message:"提交错误",level: "error"});
		      	}
			});
				
		}
	})
	
	//绑定图片素材
$("#addImage").click(function()
{
	if (currentOrgId==null||currentOrgId==""){
		$.notice({level:'warn',
				message:'请先选择一个部门'});
	}else{
		var selectedIds = $("#weChatMenuList").jqGrid("getGridParam", "selarrrow");
		if(null==selectedIds||selectedIds.length!=1){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		rowdata=$("#weChatMenuList").jqGrid('getRowData',selectedIds);
		if(rowdata["menuType"]=="网页"){
			$.messageBox({level:"warn",message:"网页类型的菜单不能绑定素材！"});
	 		return;
		}
		var urls='${path}/weChatMenuManage/validataSourceOrWeChatUser.action?sourceType=2&weChatMenu.org.id='+currentOrgId;
		$.ajax({
			url: urls,
			success : function(data) {
				if(data!="true"&&data!=true){
					$.messageBox({message:data,level: "error"});
					return false;
				}
				else{
					$("#weChatMenuMaintanceDialog").createDialog({
						width: 700,
						height: 350,
						title:'绑定图片素材',
						url:'${path}/weChatMenuManage/dispatch.action?mode=add&sourceType=2&weChatMenu.org.id='+currentOrgId+'&weChatMenu.id='+selectedIds,
						buttons: {
					   		"保存" : function(event){
					   			$("#maintainForm").submit();
					   		},
					   		"关闭" : function(event){
					        	$(this).dialog("close");
					   		}
						}
					});
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
	     		$.messageBox({message:"提交错误",level: "error"});
	      	}
		});
	}
})
	
	//绑定图文素材
$("#addNews").click(function()
{
	if (currentOrgId==null||currentOrgId==""){
		$.notice({level:'warn',
				message:'请先选择一个部门'});
	}else{
		var selectedIds = $("#weChatMenuList").jqGrid("getGridParam", "selarrrow");
		if(null==selectedIds||selectedIds.length!=1){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		rowdata=$("#weChatMenuList").jqGrid('getRowData',selectedIds);
		if(rowdata["menuType"]=="网页"){
			$.messageBox({level:"warn",message:"网页类型的菜单不能绑定素材！"});
	 		return;
		}
		var urls='${path}/weChatMenuManage/validataSourceOrWeChatUser.action?sourceType=3&weChatMenu.org.id='+currentOrgId;
		$.ajax({
			url: urls,
			success : function(data) {
				if(data!="true"&&data!=true){
					$.messageBox({message:data,level: "error"});
					return false;
				}
				else{
					$("#weChatMenuMaintanceDialog").createDialog({
						width: 700,
						height: 450,
						title:'绑定图文素材',
						url:'${path}/weChatMenuManage/dispatch.action?mode=add&sourceType=3&weChatMenu.org.id='+currentOrgId+'&weChatMenu.id='+selectedIds,
						buttons: {
					   		"保存" : function(event){
					   			$("#maintainForm").submit();
					   		},
					   		"关闭" : function(event){
					        	$(this).dialog("close");
					   		}
						}
					});
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
	     		$.messageBox({message:"提交错误",level: "error"});
	      	}
		});
	}
})	

//绑定语音素材
$("#addVoice").click(function()
{
	if (currentOrgId==null||currentOrgId==""){
		$.notice({level:'warn',
				message:'请先选择一个部门'});
	}else{
		var selectedIds = $("#weChatMenuList").jqGrid("getGridParam", "selarrrow");
		if(null==selectedIds||selectedIds.length!=1){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		rowdata=$("#weChatMenuList").jqGrid('getRowData',selectedIds);
		if(rowdata["menuType"]=="网页"){
			$.messageBox({level:"warn",message:"网页类型的菜单不能绑定素材！"});
	 		return;
		}
		var urls='${path}/weChatMenuManage/validataSourceOrWeChatUser.action?sourceType=4&weChatMenu.org.id='+currentOrgId;
		$.ajax({
			url: urls,
			success : function(data) {
				if(data!="true"&&data!=true){
					$.messageBox({message:data,level: "error"});
					return false;
				}
				else{
					$("#weChatMenuMaintanceDialog").createDialog({
						width: 700,
						height: 350,
						title:'绑定语音素材',
						url:'${path}/weChatMenuManage/dispatch.action?mode=add&sourceType=4&weChatMenu.org.id='+currentOrgId+'&weChatMenu.id='+selectedIds,
						buttons: {
					   		"保存" : function(event){
					   			$("#maintainForm").submit();
					   		},
					   		"关闭" : function(event){
					        	$(this).dialog("close");
					   		}
						}
					});
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
	     		$.messageBox({message:"提交错误",level: "error"});
	      	}
		});
	}
})
	//高级搜索
	$("#searchWeChatMenu").click(function()
	{
		$("#weChatMenuMaintanceDialog").createDialog({
			width: 650,
			height: 300,
			title:'微信菜单查询-请输入查询条件',
			url:'${path}/weChatMenuManage/dispatch.action?mode=search&weChatMenu.org.id='+currentOrgId,
			buttons: {
				"查询" : function(event){
					var sourceType=$("#search_sourceType").val();
					if($("#search_sourceType").val()=="请选择")
						sourceType="";
					var weChatUserId=$("#search_weChatUserId").val();
					if($("#search_weChatUserId").val()=="请选择微信号")
						weChatUserId="";
					var menuName=$("#search_menuName").val();
					if(menuName==""||menuName==null)
						menuName="";
					var menuType=$("#search_menuType").val();
					if(menuType=="null")
						menuType="";
				
					var postData = {
							"weChatMenu.org.id" : currentOrgId,
							"weChatMenu.isLeaf" : 1,
							"weChatMenu.menuName" : menuName,
							"weChatMenu.weChatUserId" : weChatUserId,
							"weChatMenu.sourceTypeDict.id" : sourceType,
							"weChatMenu.menuType" : menuType
					};
					$(this).dialog("close");
					loadWeChatMenu(postData);
					
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	//刷新
	$("#refresh").click(function()
	{
		loadWeChatMenu({
			"weChatMenu.org.id" :currentOrgId,
            "weChatMenu.isLeaf" : 1
            });
		
	});
	//加载数据
	function loadWeChatMenu(queryObj)
	{
		$("#weChatMenuList").setGridParam({
			url :'${path}/weChatMenuManage/findWeChatMenu.action',
			datatype: "json",
			page:1
		});
		$("#weChatMenuList").setPostData(queryObj);
		$("#weChatMenuList").trigger("reloadGrid");
	}

	//格式化菜单
	var menuType=function(el,options,rowData){
		if(rowData["menuType"]==1){
			return "单击";
		}
		if(rowData["menuType"]==2){
			return "网页";
		}
		return rowData["menuType"];
	}
	//格式化菜单层级
	var rankType=function(el,options,rowData){
		if(rowData["rank"]==1)
			return "一级菜单";
		if(rowData["rank"]==2)
			return "二级菜单 ";
		return rowData["rank"];
	}
	
	//列表显示
		$("#weChatMenuList").jqGridFunction({
			url :'${path}/weChatMenuManage/findWeChatMenu.action',
			datatype: "json",
			postData : {
				"weChatMenu.org.id" : getCurrentOrgId(),
				"weChatMenu.isLeaf" : 1
			
			},
			sortname:"updateDate",
			multiselect:true,
			shrinkToFit:true,
			colModel : [ {name : "id", index : "id",hidden : true,sortable : false},
			             {name : "menuName", index : 'menuName',sortable : true,label : '菜单名称',align : 'center',width : 95},
			             {name : "menuType",index : 'menuType',sortable : true,label : '菜单类型',formatter:menuType,align : 'center',width : 90}, 
			             {name : "rank",index : 'rank',sortable : true,label : '层级',formatter:rankType,align : 'center',width : 90}, 
			             {name : "url",index : 'url',sortable : true,label : '链接地址',align : 'center',width : 90}, 
			             {name : "weChatUserId",index : 'weChatUserId',sortable : true,label : '微信号',align : 'center',width : 120}, 
			             {name : "sourceID",index : 'sourceID',sortable : true,label : '素材编号',align : 'center',width : 50}, 
			             {name : "sourceTypeDict",index : 'sourceTypeDict.id',sortable : true,label : '素材类型',align : 'center',formatter:sourceTypeDictFormatter, width : 90 },
			             {name : "sourceDescription",index : 'sourceDescription',sortable : true,label : '素材总描述',align : 'center',width : 140},
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建人',align : 'center',width : 90}, 
			             {name : "createDate",index : 'createDate',sortable : true,label : '创建时间',align : 'center',width : 140},
			             {name : "updateUser",index : 'updateUser',sortable : true,label : '修改人',align : 'center',width : 90},
			             {name : "updateDate",index : 'updateDate',sortable : true,label : '修改时间',align : 'center',width : 140}
			             ]
		});
	
	});
</script>