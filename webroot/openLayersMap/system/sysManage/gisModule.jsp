<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/openLayersMap/includes/jsInclude.jsp" />
<style>
	#propertiesDomainYear li.select a{color: #000;font-weight: bold;}
	input.button{border: 3px solid #817A7A;font-weight: normal;color: #000000;height: 25px;background: #999999;}
	input.columnSelect,input#function-iconUrl{background: #ffffff!important;}
	fieldset.fieldset{border: 1px solid #DBDBDB;padding-bottom: 5px;margin-top: 5px;}
	fieldset.fieldset legend{background-color:#EFF5FF;text-align:center;font-family:arial;font-weight:bold;}
	.functionIncludeDiv{overflow:hidden;overflow-y:auto;height:390px;border-top: 1px solid;border-bottom: 1px solid;margin-top: 5px;}
</style>
<div class="center-left">
		<ul id="propertiesDomainYear">
			<s:iterator value="gisModuleVoList" id="moduleVo">
		    	<li><a href="javascript:void(0)" class="daily" name="<s:property value="id"/>"><s:property value="moduleName"/></a><span></span></li>
			</s:iterator>
		</ul>
</div>

<div class="center-right" >
	<div class="content">
		<div id="content-top">
			<div class="ui-corner-all" id="nav">
			<s:if test="userName=='admin'">
				<pop:JugePermissionTag ename="addGisModule">
					<a id="add" href="javascript:void(0)"><span>新增</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="updateGisModule">
					<a id="update" href="javascript:void(0)"><span>修改</span></a>
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="deleteGisModule">
					<a id="delete" href="javascript:void(0)"><span>删除</span></a>
				</pop:JugePermissionTag>
			</s:if>
				<pop:JugePermissionTag ename="viewGisModule">
					<a id="view" href="javascript:void(0)"><span>查看</span></a>
				</pop:JugePermissionTag>
			</div>
			<div style="clear:both"></div>
			<div class="container container_24" >
				<input type="hidden" id="gisIsSystemAttribute" value="">
				<div class="grid_4 lable-right">
					<label >模块名称：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="gisModuleName" value='' />
				</div>
				
				<div class="grid_4 lable-right">
					<label>表名/类型名称：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="gisTableName" value=''  />
				</div>
				<div style="clear:both"></div>
				
				<div class="grid_4 lable-right">
					<label>所属网格字段：</label>
				</div>
				<div class="grid_8">
					<input type="text" readonly="readonly" id="orgFiled" value='' />
				</div>
				
				<div class="grid_4 lable-right">
					<label>是否允许创建子模块：</label>
				</div>
				<div class="grid_8">
					<label id="isHasSonClass"></label>
				</div>
				
			</div>
			<div style="clear:both"></div>
				<div class="ui-corner-all" id="nav">
				<s:if test="userName=='admin'">
					<pop:JugePermissionTag ename="addGisModuleType">
						<a id="class-add" href="javascript:void(0)"><span>新增</span></a>
					</pop:JugePermissionTag>
					<pop:JugePermissionTag ename="updateGisModuleType">
						<a id="class-update" href="javascript:void(0)"><span>修改</span></a>
					</pop:JugePermissionTag>
					<pop:JugePermissionTag ename="deleteGisModuleType">
						<a id="class-delete" href="javascript:void(0)"><span>删除</span></a>
					</pop:JugePermissionTag>
				</s:if>
					<pop:JugePermissionTag ename="viewGisModuleType">
						<a id="class-view" href="javascript:void(0)"><span>查看</span></a>
					</pop:JugePermissionTag>
				</div>
			<div style="clear:both"></div>
		</div>
		<div style="width: 100%;">
			<table id="class-typeManageList"></table>
			<div id="class-typeManageListPager"></div>
		</div>
	</div>
</div>

<div id="editGisTypeManageDiv"></div>
<div id="dailyTrunkDirectoryDailog"></div>
<div id="dailySubDirectoryMaintanceDialog"></div>
<div id="moduleDialog"></div>
<div id="iconDialog"></div>
<div id="columnSelectDialog"></div>

<script type="text/javascript">
var dialogWidth=800;
var dialogHeight=600;
	function getGisModuleVoById(gisModuleVoId){
		if(gisModuleVoId!=null&&gisModuleVoId!=""){
			$.ajax({
				url:"/gis/twoDimensionMapModuleManage/getGisModuleVoById.action?gisModuleVo.id="+gisModuleVoId,
				success:function(data){
					if(data!=null&&data!=""){
						$("#gisIsSystemAttribute").val(data.isSystemAttribute);
						if(data.moduleName!=null&&data.moduleName!=""){
							$("#gisModuleName").val(data.moduleName);
						}else{
							$("#gisModuleName").val("");
						}
						if(data.tableName!=null&&data.tableName!=""){
							$("#gisTableName").val(data.tableName);
						}else{
							$("#gisTableName").val("");
						}
						if(data.orgFiled!=null&&data.orgFiled!=""){
							$("#orgFiled").val(data.orgFiled);
						}else{
							$("#orgFiled").val("");
						}
	
						$("#class-typeManageList").setGridParam({
							url:"${path }/gis/gisTypeManage/findGisTypeManageForPageByInnerType.action",
							datatype: "json",
							page:1
						});
						$("#class-typeManageList").setPostData({
							"gisTypeManage.innerKey":$("#gisTableName").val()
					   	});
						$("#class-typeManageList").trigger("reloadGrid");
						
						if(data.isHasSonClass){
							$("#isHasSonClass").html("是");
							$("#class-add").buttonEnable();
						}else{
							$("#isHasSonClass").html("否");
							$("#class-add").buttonDisable();
						}
					}else{
						$.messageBox({message:"模块数据加载失败!"});
					}
				}
			});
		}
	}
	$(function(){
		
		$("#propertiesDomainYear li a").bind("click", function(){
			$("#propertiesDomainYear li").removeClass("select");
			getGisModuleVoById($(this).attr("name"));
			$(this).parent().addClass("select");
		});
		
		$("#propertiesDomainYear").find("a").each(function(){//设置字符长度
			var str=$(this).html();
			$(this).html(str.substring(0,5));
		});
		if($("#propertiesDomainYear li a").length>0){
	 		$("#propertiesDomainYear li a:first").click();
	 	}else{
	 		getGisModuleVoById(0);
	 	}
		var centerHeight=$("div.ui-layout-center").outerHeight();
		$("#propertiesDomainYear").height(centerHeight);
		
		//图标格式化
		function viewIconFormatter(el, options, rowData){
			if(rowData.isViewIcon==0){
				return "<em style='color:#ff6600;;'>无效</em>";
			}else{
				return "有效";
			}
		}
		
		initModuleButton();
		
		function initModuleButton(){
			$("#add").buttonEnable();  
			$("#delete").buttonEnable();
			$("#view").buttonEnable();
			$("#update").buttonEnable();
		}
		
		$("#add").click(function(){		
			$("#moduleDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"新增模块信息",
				url:"${path}/gis/twoDimensionMapModuleManage/dispatch.action?mode=add",
				buttons: {
			   		"保存" : function(event){
			   			$("#maintainForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		
		$("#delete").click(function(){
			var id;
			$("#propertiesDomainYear li").each(function(){
				if($(this).hasClass("select")){
					id=$(this).find("a").attr("name");
				}
			});
			if(id==null){
				return;
			}
			$.confirm({
					title:"确认删除",
					message:'<div style="height:60px"><br>确认删除?</div>',
					okFunc: function() {
						$.ajax({
							url:"${path}/gis/twoDimensionMapModuleManage/deleteModule.action?id="+id+"&gisTypeManage.innerKey="+$("#gisTableName").val(),
							success:function(data){
								if(data){
								    $.messageBox({message:"模块删除成功!"});
								    $("#propertiesDomainYear .select").remove();//销毁
									if($("#propertiesDomainYear li a:first").length>0){
										   $("#propertiesDomainYear li a:first").click();
									}else{
											dispatchDailyTrunkDirectory(-1);
									}
								}else{
									$.messageBox({message:"删除模块失败!"});
								}
						}
					});
				}
			});
		});
		
		$("#view").click(function(){
			var id;
			$("#propertiesDomainYear li").each(function(){
				if($(this).hasClass("select")){
					id=$(this).find("a").attr("name");
				}
			});
			if(id==null){
				return;
			}
			
			$("#moduleDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"查看模块信息",
				url:"${path}/gis/twoDimensionMapModuleManage/dispatch.action?mode=view&gisModuleVo.id="+id,
				buttons: {
				   "关闭" : function(){
				        $(this).dialog("close");
				   }
				}
			});
		});
		
		$("#update").click(function(){
			var id;
			$("#propertiesDomainYear li").each(function(){
				if($(this).hasClass("select")){
					id=$(this).find("a").attr("name");
				}
			});
			if(id==null){
				return;
			}
			$("#moduleDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:"修改模块信息",
				url:"${path}/gis/twoDimensionMapModuleManage/dispatch.action?mode=edit&gisModuleVo.id="+id,
				buttons :{
					"保存" : function(){
						$("#maintainForm").submit();
						$("#propertiesDomainYear .select a").click();
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
		});
		
		
		function getSelectedIds(){
			var selectedIds = $("#class-typeManageList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
		}
		
		function getSelectedIdLast(){
			var selectedId;
			var selectedIds = $("#class-typeManageList").jqGrid("getGridParam", "selarrrow");
			for(var i=0;i<selectedIds.length;i++){
				selectedId = selectedIds[i];
			}
			return selectedId;
		}
		
		$("#class-typeManageList").jqGridFunction({
			height:420,
			datatype: "local",
			rightClick:true,
			colModel:[
		    	{name:"id", index:"id", hidden:true},
	  			{name:"name", index:"name",label:"类型名称",width:200,align:"center"},
	  			{name:"tableName", index:"tableName",label:"表名",width:200,align:"center"},
	  			{name:"key", index:"key", label:"类型key", width:150,align:"center"}
		  	],
		  	rightClickOption:{
		   		items:[
						<pop:JugePermissionTag ename="addGisModuleType">
						{text: "新增", icon: "/resource/system/js/contextmenu/css/images/icons/add.png", alias: "add", action: function(){
							$("#class-add").click();
						}},
						</pop:JugePermissionTag>
						<pop:JugePermissionTag ename="viewGisModuleType">
						{text: "查看", icon: "/resource/system/js/contextmenu/css/images/icons/ico6.gif", alias: "view", action: function(){
							$("#class-view").click();
						}},
						</pop:JugePermissionTag>
						<pop:JugePermissionTag ename="updateGisModuleType">
						{text: "修改", icon: "/resource/system/js/contextmenu/css/images/icons/edit.png", alias: "edit", action: function(){
							$("#class-update").click();
						}},
						</pop:JugePermissionTag>
						<pop:JugePermissionTag ename="deleteGisModuleType">
						{text: "删除", icon: "/resource/system/js/contextmenu/css/images/icons/del.png", alias: "del", action: function(){
							$("#class-delete").click();
						}}
						</pop:JugePermissionTag>
					],
				onShow:function(menu){
					if($(this).hasClass("ui-jqgrid")){
						$(menu).find("[title=查看]").hide();
						$(menu).find("[title=修改]").hide();
						$(menu).find("[title=删除]").hide();
					}else{
						$(menu).find(".b-m-item").show();
					}
				}
			},
		  	multiselect:true,
		  	sortname: 'enableBind',	
		  	onSelectAll:onSelectAllFunc,
		  	ondblClickRow:viewGisTypeManageFunc,
	    	loadComplete:loadCompleteFunc,
			onSelectRow:onSelectRowFunc,
		    height:$(".ui-layout-west").height()-$(".path").height()-$("#content-top").height()-106
		});

		function loadCompleteFunc(){
			disableButtonSonClass();
		}
		function onSelectRowFunc(){
			var selectedIds = getSelectedIds();
			if(selectedIds.length>0){
				$("#class-add").buttonDisable();
				enableButtonSonClass();
 				if(selectedIds.length>1){
					$("#class-update").buttonDisable();
					$("#class-view").buttonDisable();
 				}
			}else{
				$("#class-add").buttonEnable();
				disableButtonSonClass();
			}
		}
		
		function onSelectAllFunc(){
			var selectedIds = getSelectedIds();
			if(selectedIds!=null && selectedIds.length>0){
				$("#class-delete").buttonEnable();
				$("#class-add").buttonDisable();
			}else{
				$("#class-add").buttonEnable();
				disableButtonSonClass();
			}
		}
		function disableButtonSonClass(){
			$("#class-update").buttonDisable();
			$("#class-delete").buttonDisable();
			$("#class-view").buttonDisable();
		}
		function enableButtonSonClass(){
			$("#class-update").buttonEnable();
			$("#class-delete").buttonEnable();
			$("#class-view").buttonEnable();
		}

		$("#class-add").click(function(){
			if ($("#class-add").attr("class") == "huise disable"){
				 return;
			}
			$("#editGisTypeManageDiv").createDialog({
			    width: dialogWidth,
			    height: dialogHeight,
			    title:'新增子类',
			    url:'${path}/gis/gisTypeManage/dispatch.action?mode=add&gisTypeManage.checked=true&gisTypeManage.innerKey='+$("#gisTableName").val(),
			    buttons: {
				    "保存" : function(){
			    		$("#maintainFormA").submit();
			    	},
			        "关闭" : function(){
			            $(this).dialog("close");
			        }
			    }
			});
		})
		$("#class-update").click(function(){
			if ($("#class-update").attr("class") == "huise disable"){
				 return;
			}
			var selectedId = getSelectedIdLast();
			if(selectedId==null){
				 return;
			}
			$("#editGisTypeManageDiv").createDialog({
			    width: dialogWidth,
			    height: dialogHeight,
			    title:'修改',
			    url:'${path}/gis/gisTypeManage/dispatch.action?mode=edit&gisTypeManage.id='+selectedId,
			    buttons: {
				    "保存" : function(){
			    		$("#maintainFormA").submit();
			    	},
			        "关闭" : function(){
			            $(this).dialog("close");
			        }
			    }
			});
		})
		
		$("#class-delete").click(function(){
			if ($("#class-delete").attr("class") == "huise disable"){
				 return;
			}
			var ids = getSelectedIds();
			if(ids==null || ids.length<1){
				 return;
			}
			$.confirm({
				title:"确认删除",
				message:'<div style="height:60px"><br>确认删除！</div>',
				okFunc: function() {
					$.ajax({
						url:"${path }/gis/gisTypeManage/deleteGisTypeManage.action?ids="+ids,
						success:function(data){
							if(data==true){
								$("#class-typeManageList").trigger("reloadGrid");
							    $.messageBox({message:"已经成功删除该信息!"});
							}else{
								$.messageBox({message:data,level: "error"	});
							}
							
						}
					});
				}
			});
		})
		
		/*$("#class-bind").click(function(){
			if ($("#class-bind").attr("class") == "huise disable"){
				 return;
			}
			var ids = getSelectedIds();
			if(ids==null || ids.length<1){
				 return;
			}
			$.ajax({
				url:"${path}/gis/gisTypeManage/changeCheckedOfGisTypeManage.action",
				data:{
					"gisTypeManage.checked":true,
					"ids":ids.toString()
				},
				success:function(responseData){
					$("#class-typeManageList").trigger("reloadGrid");
					$("#class-add").buttonEnable();
				}
			});
		})
		
		$("#class-unbind").click(function(){
			if ($("#class-unbind").attr("class") == "huise disable"){
				 return;
			}
			var ids = getSelectedIds();
			if(ids==null || ids.length<1){
				 return;
			}
			$.ajax({
				url:"${path}/gis/gisTypeManage/changeCheckedOfGisTypeManage.action",
				data:{
					"gisTypeManage.checked":false,
					"ids":ids.toString()
				},
				success:function(responseData){
					$("#class-typeManageList").trigger("reloadGrid");
					$("#class-add").buttonEnable();
				}
			});
		})*/
		
		$("#class-view").click(function(){
			if ($("#class-view").attr("class") == "huise disable"){
				 return;
			}
			viewGisTypeManageFunc();
		})
		function viewGisTypeManageFunc(){
			var selectedId = getSelectedIdLast();
			if(selectedId==null || selectedId.length<1){
				 return;
			}
			$("#editGisTypeManageDiv").createDialog({
			    width: dialogWidth,
			    height: dialogHeight+20,
			    title:'查看',
			    url:'${path}/gis/gisTypeManage/dispatch.action?mode=view&gisTypeManage.id='+selectedId,
			    buttons: {
			        "关闭" : function(){
			            $(this).dialog("close");
			        }
			    }
			});
		}
		
	})
</script>