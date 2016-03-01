<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/baseinfo/commonPopulation/jsFunctionInclude.jsp" />
<div class="content">
     <div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
				<s:include value="/common/orgSelectedComponent.jsp" />
				<div class="ui-widget autosearch">
					<input class="basic-input" type="text" value="请输入分组名称" id="searchByGroupName"
						class="searchtxt" onblur="value=(this.value=='')?'请输入分组名称':this.value;"
						onfocus="value=(this.value=='请输入分组名称')?'':this.value;" />
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
	  </div>
	  <a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	  <pop:JugePermissionTag ename="searchWeChatGroup">
			<a id="searchWeChatGroup" href="javascript:void(0)"><span>高级搜索</span> </a>
	  </pop:JugePermissionTag>
	  <pop:JugePermissionTag ename="addWeChatGroup">
			<a id="addWeChatGroup" href="javascript:void(0)"><span>新增分组</span> </a>
	  </pop:JugePermissionTag>
	  <pop:JugePermissionTag ename="updateWeChatGroup">
			<a id="updateWeChatGroup" href="javascript:void(0)"><span>修改分组</span> </a>
	  </pop:JugePermissionTag>
	  
	  <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="weChatGroupList">
		</table>
		<div id="weChatGroupListPager"></div>
	</div>
</div>
<div id="weChatGroupMaintanceDialog"></div>
<script type="text/javascript">
var currentOrgId=getCurrentOrgId();
$(function() {
	//快速检索(重置)
	$("#refreshSearchKey").click(function(){
		$("#searchByGroupName").attr("value","请输入分组名称");
		loadWeChatGroup({	"weChatGroup.org.id" : getCurrentOrgId()});
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchByGroupName=$("#searchByGroupName").val();
		if(searchByGroupName != null && "请输入分组名称" != searchByGroupName){
			var queryObj = {
					"weChatGroup.org.id" : getCurrentOrgId(),
		            "weChatGroup.name":searchByGroupName
			};
			loadWeChatGroup(queryObj);
		}
    });
	//高级搜索
	$("#searchWeChatGroup").click(function()
	{
		$("#weChatGroupMaintanceDialog").createDialog({
			width: 650,
			height: 200,
			title:'微信分组查询-请输入查询条件',
			url:'${path}/weChatGroupManage/dispatch.action?mode=search&weChatGroup.org.id='+currentOrgId,
			buttons: {
				"查询" : function(event){
					var weChatUserId=$("#search_weChatUserId").val();
					if($("#search_weChatUserId").val()=="请选择微信号")
						weChatUserId="";
					var groupName=$("#search_groupName").val();
					var postData = {
							"weChatGroup.org.id" : currentOrgId,
							"weChatGroup.name" : groupName,
							"weChatGroup.weChatUserId" : weChatUserId
					};
					$(this).dialog("close");
					loadWeChatGroup(postData);
					
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
		loadWeChatGroup({"weChatGroup.org.id" : getCurrentOrgId()});
	});
	
	//新增分组
	$("#addWeChatGroup").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/weChatGroupManage/validataWeChatUserId.action?weChatGroup.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#weChatGroupMaintanceDialog").createDialog({
							width: 700,
							height: 200,
							title:'新增微信分组',
							url:'${path}/weChatGroupManage/dispatch.action?mode=add&weChatGroup.org.id='+currentOrgId,
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
	//修改分組
	$("#updateWeChatGroup").click(function(event){
			var selectedId = $("#weChatGroupList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null||selectedId.length!=1){
					$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
					return;
			}
		   var rowdata=$("#weChatGroupList").jqGrid('getRowData',selectedId); 
		   if(rowdata["name"]=="未分组"&&rowdata["groupId"]==0){
			   $.messageBox({level:"warn",message:"不能修改未分组信息"});
				return;
			}
		   if(rowdata["name"]=="黑名单"&&rowdata["groupId"]==1){
			   $.messageBox({level:"warn",message:"不能修改黑名单信息"});
				return;
			}
		   if(rowdata["name"]=="星标组"&&rowdata["groupId"]==2){
			   $.messageBox({level:"warn",message:"不能修改星标组信息"});
				return;
			}
			$("#weChatGroupMaintanceDialog").createDialog({
				width: 700,
				height: 200,
				title:'修改分组',
				url:'${path}/weChatGroupManage/dispatch.action?mode=edit&weChatGroup.weChatGroupId='+selectedId,
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
	//加载数据
	function loadWeChatGroup(queryObj)
	{
		$("#weChatGroupList").setGridParam({
			url :'${path}/weChatGroupManage/findWeChatGroup.action',
			datatype: "json",
			page:1
		});
		$("#weChatGroupList").setPostData(queryObj);
		$("#weChatGroupList").trigger("reloadGrid");
	}
		//列表显示
		$("#weChatGroupList").jqGridFunction({
			url :'${path}/weChatGroupManage/findWeChatGroup.action',
			datatype: "json",
			postData : {
				"weChatGroup.org.id" : getCurrentOrgId()
			},
			sortname:"updateDate",
			multiselect:true,
			shrinkToFit:true,
			colModel : [ {name : "weChatGroupId", index : "weChatGroupId",label : 'id',frozen:true,hidden : true,sortable : false},
			             {name : "name", index : 'name',sortable : true,label : '分组名称',align : 'center',width : 95},
			             {name : "groupId",index : 'groupId',sortable : true,label : '分组ID',align : 'center',width : 90}, 
			             {name : "weChatUserId",index : 'weChatUserId',sortable : true,label : '微信号',align : 'center',width : 120}, 
			             {name : "countFan",index : 'countFan',sortable : true,label : '粉丝数',align : 'center',width : 50},
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建人',align : 'center',width : 90}, 
			             {name : "createDate",index : 'createDate',sortable : true,label : '创建时间',align : 'center',width : 140},
			             {name : "updateUser",index : 'updateUser',sortable : true,label : '修改人',align : 'center',width : 90},
			             {name : "updateDate",index : 'updateDate',sortable : true,label : '修改时间',align : 'center',width : 140}
			             ]
		});

})

	
</script>