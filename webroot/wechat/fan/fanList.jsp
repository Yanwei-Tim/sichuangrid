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
					<input class="basic-input" type="text" value="请输入粉丝昵称" id="searchByNickName"
						class="searchtxt" onblur="value=(this.value=='')?'请输入粉丝昵称':this.value;"
						onfocus="value=(this.value=='请输入粉丝昵称')?'':this.value;" />
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
	  </div>
	  <a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	  <pop:JugePermissionTag ename="searchFan">
			<a id="searchFan" href="javascript:void(0)"><span>高级搜索</span> </a>
	  </pop:JugePermissionTag>
	  <pop:JugePermissionTag ename="updateFan">
			<a id="updateFan" href="javascript:void(0)"><span>修改备注</span> </a>
	  </pop:JugePermissionTag>
	  <pop:JugePermissionTag ename="removeFan">
			<a id="removeFan" href="javascript:void(0)"><span>移动粉丝</span> </a>
	  </pop:JugePermissionTag>
	  <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="fanList">
		</table>
		<div id="fanListPager"></div>
	</div>
</div>
<div id="fanMaintanceDialog"></div>
<script type="text/javascript">
var currentOrgId=getCurrentOrgId();

$(function() {
	//快速检索(重置)
	$("#refreshSearchKey").click(function(){
		$("#searchByNickName").attr("value","请输入粉丝昵称");
		loadFan({"fan.org.id" : getCurrentOrgId()});
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchByNickName=$("#searchByNickName").val();
		if(searchByNickName != null && "请输入粉丝昵称" != searchByNickName){
			var queryObj = {
					"fan.org.id" : getCurrentOrgId(),
		            "fan.nickName":searchByNickName
			};
			loadFan(queryObj);
		}
    });
	//刷新
	$("#refresh").click(function()
	{
		loadFan({"fan.org.id" : getCurrentOrgId()});
	});
	
	//高级搜索
	$("#searchFan").click(function()
	{
		$("#fanMaintanceDialog").createDialog({
			width: 650,
			height: 300,
			title:'微信粉丝查询-请输入查询条件',
			url:'${path}/fanManage/dispatch.action?mode=search&fan.org.id='+currentOrgId,
			buttons: {
				"查询" : function(event){
					var weChatUserId=$("#search_weChatUserId").val();
					if($("#search_weChatUserId").val()=="请选择微信号")
						weChatUserId="";
					var groupId=$("#search_groupId").val();
					if($("#search_groupId").val()=="请选择所属分组")
						groupId="";
					var nickName=$("#search_nickName").val();
					var name=$("#search_name").val();
					var postData = {
							"fan.org.id" : currentOrgId,
							"fan.groupId" : groupId,
							"fan.nickName" : nickName,
							"fan.name" : name,
							"fan.weChatUserId" : weChatUserId
					};
					$(this).dialog("close");
					loadFan(postData);
					
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	//修改昵称
	$("#updateFan").click(function(event){
			var selectedId = $("#fanList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null||selectedId.length!=1){
					$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
					return;
			}
			$("#fanMaintanceDialog").createDialog({
				width: 700,
				height: 300,
				title:'修改昵称',
				url:'${path}/fanManage/dispatch.action?mode=edit&fan.fanId='+selectedId,
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
	//移动粉丝
	$("#removeFan").click(function(){
		var selectedId = $("#fanList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null||selectedId.length==0){
				$.messageBox({level:"warn",message:"请至少选择一条数据再进行操作！"});
				return;
		}
		var rowDataTemp=$("#fanList").jqGrid('getRowData',selectedId[0]);
		for (i = 0; i < selectedId.length; i++) {
			   var rowdata=$("#fanList").jqGrid('getRowData',selectedId[i]); 
			   if(rowdata["weChatUserId"]!=rowDataTemp["weChatUserId"])
			   {
				   $.messageBox({level:"warn",message:"移动粉丝只能针对同一微信号进行操作！"});
			 		return;  
			   } 
		 }
		$("#fanMaintanceDialog").createDialog({
			width: 700,
			height: 200,
			title:'移动粉丝',
			url:'${path}/fanManage/dispatch.action?mode=removeFan&fanIds='+selectedId+'&fan.weChatUserId='+rowDataTemp["weChatUserId"],
			buttons: {
		   		"保存" : function(event){
			   		$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
		  
	})
	//加载数据
	function loadFan(queryObj)
	{
		$("#fanList").setGridParam({
			url :'${path}/fanManage/findFan.action',
			datatype: "json",
			page:1
		});
		$("#fanList").setPostData(queryObj);
		$("#fanList").trigger("reloadGrid");
	}
		//列表显示
		$("#fanList").jqGridFunction({
			url :'${path}/fanManage/findFan.action',
			datatype: "json",
			postData : {
				"fan.org.id" : getCurrentOrgId()
			},
			sortname:"updateDate",
			multiselect:true,
			shrinkToFit:true,
			colModel : [ {name : "fanId", index : "fanId", label : 'id',frozen:true,hidden : true,sortable : false},
			             {name : "openId",index : 'openId',sortable : true,label : '粉丝号',align : 'center',width : 180}, 
			             {name : "name", index : 'name',sortable : true,label : '粉丝昵称',align : 'center',width : 95},
			             {name : "nickName",index : 'nickName',sortable : true,label : '备注名',align : 'center',width : 100},
			             {name : "groupName",index : 'groupName',sortable : false,label : '所属群组',align : 'center',width : 80},
			             {name : "weChatUserId",index : 'weChatUserId',sortable : true,label : '公众号',align : 'center',width : 120}, 
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建人',align : 'center',width : 90}, 
			             {name : "createDate",index : 'createDate',sortable : true,label : '创建时间',align : 'center',width : 120},
			             {name : "updateUser",index : 'updateUser',sortable : true,label : '修改人',align : 'center',width : 90},
			             {name : "updateDate",index : 'updateDate',sortable : true,label : '修改时间',align : 'center',width : 120}
			             ]
		});

})

	
</script>