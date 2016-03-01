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
			<div class="ui-widget autosearch" style="display: none" >
					<input class="basic-input searchtxt" type="text" value="请输入分组名称" id="searchByGroupName"
						 onblur="value=(this.value=='')?'请输入分组名称':this.value;"
						onfocus="value=(this.value=='请输入分组名称')?'':this.value;" />
					<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
	  </div>
	  
	  <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	<pop:JugePermissionTag ename="addSmsAccount">
		<a id="addSmsAccount" href="javascript:void(0)"><span>添加账号</span> </a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="updateSmsAccount">
		<a id="updateSmsAccount" href="javascript:void(0)"><span>修改账号</span> </a>
	</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="smsAccountList">
		</table>
		<div id="smsAccountListPager"></div>
	</div>
</div>
<div id="smsAccountMaintanceDialog"></div>

<script type="text/javascript">
var currentOrgId=getCurrentOrgId();
$(function() {
	//快速检索(重置)
	$("#refreshSearchKey").click(function(){
		$("#searchByGroupName").attr("value","请输入分组名称");
		loadListData({"smsAccount.org.id" : getCurrentOrgId()});
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchByGroupName=$("#searchByGroupName").val();
		if(searchByGroupName != null && "请输入分组名称" != searchByGroupName){
			var queryObj = {
					"smsAccount.org.id" : getCurrentOrgId(),
		            //"weChatGroup.name":searchByGroupName
			};
			loadListData(queryObj);
		}
    });
	//刷新
	$("#refresh").click(function()
	{
		loadListData({"smsAccount.org.id" : getCurrentOrgId()});
	});
	
	$("#addSmsAccount").click(function()
	{
		$("#smsAccountMaintanceDialog").createDialog({
			width: 500,
			height: 250,
			title:'短信账户',
			url:'${path}/smsAccountManage/dispatch.action?mode=add',
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(event){
		        	$(this).dialog("close");
		   		}
			}
		});
	})
	$("#updateSmsAccount").click(function()
	{
		var selectedId = $("#smsAccountList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null||selectedId.length!=1){
				$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
				return;
		}
		$("#smsAccountMaintanceDialog").createDialog({
			width: 500,
			height: 250,
			title:'短信账户',
			url:'${path}/smsAccountManage/dispatch.action?mode=update&id='+selectedId,
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(event){
		        	$(this).dialog("close");
		   		}
			}
		});
	})
	//加载数据
	function loadListData(queryObj)
	{
		$("#smsAccountList").setGridParam({
			url :'${path}/smsAccountManage/findSmsAccount.action',
			datatype: "json",
			page:1
		});
		$("#smsAccountList").setPostData(queryObj);
		$("#smsAccountList").trigger("reloadGrid");
	}
		//列表显示
		$("#smsAccountList").jqGridFunction({
			url :'${path}/smsAccountManage/findSmsAccount.action',
			datatype: "json",
			postData : {
				"smsAccount.org.id" : getCurrentOrgId()
			},
			sortname:"updateDate",
			multiselect:true,
			shrinkToFit:true,
			colModel : [ {name : "id", index : "id",label : 'id',frozen:true,hidden : true,sortable : false},
			             {name : "org.orgName", index : 'orgName',sortable : false,label : '组织名',align : 'center',width : 95},
			             {name : "name",index : 'name',sortable : true,label : '短信账户名',align : 'center',width : 90}, 
			             {name : "pwd",index : 'pwd',sortable : true,label : '短信账户名密码',align : 'center',width : 120}, 
			             {name : "callbackPwd",index : 'callbackPwd',sortable : false,label : '短信状态通知密码',align : 'center',width : 200},
			             {name : "contentSuffix",index : 'contentSuffix',sortable : false,label : '短信内容后缀',align : 'center',width : 80},
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建用户',align : 'center',width : 50},
			             {name : "createDate",index : 'createDate',sortable : true,label : '创建时间',align : 'center',width : 50},
			             {name : "updateUser",index : 'updateUser',sortable : true,label : '修改用户',align : 'center',width : 50},
			             {name : "updateDate",index : 'updateDate',sortable : true,label : '修改时间',align : 'center',width : 90}, 
			             ]
		});

})
	
</script>