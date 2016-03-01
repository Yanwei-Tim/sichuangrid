<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<@s.include value="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入微信昵称" id="searchByCondition" class="searchtxt" onblur="value=(this.value=='')?'请输入微信昵称':this.value;" onfocus="value=(this.value=='请输入微信昵称')?'':this.value;"/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
	
		<@pop.JugePermissionTag ename="addTencentUser">
		<a id="addTencentUser" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
		
			<!--<a href="javascript:;" class="nav-dropdownBtn"><span>发送消息</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
			<div class="nav-sub-buttons">
				<a id="sendTextMessage" href="javascript:void(0)"><span>文本</span></a>
				<a id="sendImageMessage" href="javascript:void(0)"><span>图片</span></a>
				<a id="sendNewsMessage" href="javascript:void(0)"><span>图文</span></a>
				<a id="sendVoiceMessage" href="javascript:void(0)"><span>语音</span></a>
			</div>-->
		</@pop.JugePermissionTag>
		<@pop.JugePermissionTag ename="subscribeInfo">
		 <a href="javascript:;" class="nav-dropdownBtn"><span>关注回复</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
			 <div class="nav-sub-buttons">
				<a id="autoText" href="javascript:void(0)"><span>文本</span></a>
				<a id="autoImage" href="javascript:void(0)"><span>图片</span></a>
				<a id="autoVoice" href="javascript:void(0)"><span>语音</span></a>
				<a id="autoNews" href="javascript:void(0)"><span>图文</span></a>
			</div>
		</@pop.JugePermissionTag>
			<a id="reloadTencentUser" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
		
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="tencentUserList"></table>
		<div id="tencentUserListPager"></div>
	</div>
	<div id="tencentUserDialog"></div>
	<div id="tencentUserSearchDialog"></div>
		<div id="replyMessageDlg"></div>
</div>

<script type="text/javascript">
var currentOrgId=getCurrentOrgId();
<@pop.formatterProperty name="sourceTypeDict" domainName="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE" />

 function operatorFormatter(el,options,rowData){
	return "<@pop.JugePermissionTag ename='updateTencentUser'><a href='javascript:;' onclick='updateOperator(event,"+rowData.tencentUserId+")'><span>修改</span></a></@pop.JugePermissionTag> | <@pop.JugePermissionTag ename='deleteTencentUser'><a href='javascript:;' onclick='deleteOperator(event,"+rowData.tencentUserId+")'><span>删除</span></a></@pop.JugePermissionTag>";
} 
function stateColor(el,options,rowData){
		var state = rowData.state;
		if(state=='' || state == undefined || state == null)
			return '<span><span style="color:#d6d6d6;">██</span>待机</span>';
		else
			return '<span><span style="color:#99cc00;">██</span>当前登录</span>';
	}
function isAppend(el,options,rowData){
		var isAppendKeyWord = rowData.isAppendKeyWord;
		if(isAppendKeyWord=='1')
			return '是';
		else if(isAppendKeyWord=='2')
			return '否';
		else
		    return '';
}

$(document).ready(function(){
	loadList();
	onOrgChanged({"tencentUser.organization.id" : currentOrgId});
	$("#reloadTencentUser").click(function(event){
			onOrgChanged({"tencentUser.organization.id" : currentOrgId});
	});
	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入微信昵称");
		onOrgChanged({"tencentUser.organization.id" : currentOrgId});
	});
	//搜索
	$("#fastSearchButton").click(function(){
		fastSearchTencentUser();
    });
    //高级搜索
	$("#search").click(function(){
	   searchTencentUser();
	});	
	 //新增
	$("#addTencentUser").click(function(event){
		addTencentUser();
	});
	
 //被关注自动回复文本
	$("#autoText").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var selectedId = $("#tencentUserList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null||selectedId.length!=1){
					$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
					return;
			}
			var urls='${path}/tencentUserManage/validataSource.action?sourceType=1&tencentUser.organization.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#replyMessageDlg").createDialog({
							width: 700,
							height: 300,
							title:'被关注自动回复--文本',
							url:'${path}/tencentUserManage/dispatch.action?mode=autoMessage&sourceType=1&tencentUser.organization.id='+currentOrgId+'&tencentUser.tencentUserId='+selectedId,
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
	
	//被关注自动回复图片
	$("#autoImage").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
		   var selectedId = $("#tencentUserList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null||selectedId.length!=1){
					$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
					return;
			}  
			var urls='${path}/tencentUserManage/validataSource.action?sourceType=2&tencentUser.organization.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#replyMessageDlg").createDialog({
							width: 700,
							height: 300,
							title:'被关注自动回复--图片',
							url:'${path}/tencentUserManage/dispatch.action?mode=autoMessage&sourceType=2&tencentUser.organization.id='+currentOrgId+'&tencentUser.tencentUserId='+selectedId,
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
	//被关注自动回复图文
	$("#autoNews").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
		   var selectedId = $("#tencentUserList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null||selectedId.length!=1){
					$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
					return;
			}  
			var urls='${path}/tencentUserManage/validataSource.action?sourceType=3&tencentUser.organization.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#replyMessageDlg").createDialog({
							width: 700,
							height: 450,
							title:'被关注自动回复--图文',
							url:'${path}/tencentUserManage/dispatch.action?mode=autoMessage&sourceType=3&tencentUser.organization.id='+currentOrgId+'&tencentUser.tencentUserId='+selectedId,
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
	//被关注自动回复语音
	$("#autoVoice").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
		   var selectedId = $("#tencentUserList").jqGrid("getGridParam", "selarrrow");
			if(selectedId==null||selectedId.length!=1){
					$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
					return;
			}  
			var urls='${path}/tencentUserManage/validataSource.action?sourceType=4&tencentUser.organization.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#replyMessageDlg").createDialog({
							width: 700,
							height: 300,
							title:'被关注自动回复--语音',
							url:'${path}/tencentUserManage/dispatch.action?mode=autoMessage&sourceType=4&tencentUser.organization.id='+currentOrgId+'&tencentUser.tencentUserId='+selectedId,
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
	
	
	
	
	
		//发送消息(文本)
	$("#sendTextMessage").click(function(event){
     	var allValue = getSelectedIds();
		if(allValue.length ==0||allValue.length>1){
			$.messageBox({level : 'warn',message:"请选择一个微信号，再进行发送！"});
			return;
		}
		$("#replyMessageDlg").createDialog({
			width: 800,
			height: 300,
			title:'发送平台消息-文本',
			url:'${path}/tencentUserManage/dispatch.action?mode=sendTextMessage&tencentUser.tencentUserId='+allValue,
			buttons: {
				"发送" : function(event){
				      $("#maintainForm").submit();
			   },
			   "取消" : function(){
			         $(this).dialog("close");
			   }
			}
		});
	});
	//发送消息(图片)
	$("#sendImageMessage").click(function(event){
	var allValue = getSelectedIds();
		if(allValue.length ==0||allValue.length>1){
			$.messageBox({level : 'warn',message:"请选择一个微信号，再进行发送！"});
			return;
		}
		$("#replyMessageDlg").createDialog({
			width: 800,
			height: 200,
			title:'发送平台消息-图片',
			url:'${path}/tencentUserManage/dispatch.action?mode=sendImageMessage&tencentUser.tencentUserId='+allValue,
			buttons: {
				"发送" : function(event){
				      $("#maintainForm").submit();
			   },
			   "取消" : function(){
			         $(this).dialog("close");
			   }
			}
		});
	});
	//发送消息(图文)
	$("#sendNewsMessage").click(function(event){
	var allValue = getSelectedIds();
		if(allValue.length ==0||allValue.length>1){
			$.messageBox({level : 'warn',message:"请选择一个微信号，再进行发送！"});
			return;
		}
		$("#replyMessageDlg").createDialog({
			width: 800,
			height: 510,
			title:'发送平台消息-图文',
			url:'${path}/tencentUserManage/dispatch.action?mode=sendNewsMessage&tencentUser.tencentUserId='+allValue,
			buttons: {
				"发送" : function(event){
				      $("#maintainForm").submit();
			   },
			   "取消" : function(){
			         $(this).dialog("close");
			   }
			}
		});
	});
	//发送消息(语音)
	$("#sendVoiceMessage").click(function(event){
	var allValue = getSelectedIds();
		if(allValue.length ==0||allValue.length>1){
			$.messageBox({level : 'warn',message:"请选择一个微信号，再进行发送！"});
			return;
		}
		$("#replyMessageDlg").createDialog({
			width: 800,
			height: 200,
			title:'发送平台消息-语音',
			url:'${path}/tencentUserManage/dispatch.action?mode=sendVoiceMessage&tencentUser.tencentUserId='+allValue,
			buttons: {
				"发送" : function(event){
				      $("#maintainForm").submit();
			   },
			   "取消" : function(){
			         $(this).dialog("close");
			   }
			}
		});
	});
	
	function getSelectedIds(){
		var selectedIds = $("#tencentUserList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
     }

	function loadList(){
		$("#tencentUserList").jqGridFunction({
			datatype: "local",
			sortname: "CREATE_DATE",
			multiselect:true,
			colModel:[
				{name:"tencentUserId", index:"TENCENT_USER_ID",sortable:false,frozen:true,hidden:true },
				{name:"state", index:"TENCENT_USER_ID",sortable:false,hidden:true, frozen:true},
				{name:"operator", index:'TENCENT_USER_ID',label:'操作',formatter:operatorFormatter,width:80,frozen:true,sortable:false,align:'center'},
				{name:"stateOperator",index:"TENCENT_USER_ID",label:"状态",formatter:stateColor,sortable:false,width:150,align:"center"},
				{name:"weChatUserId",index:"weChatUser_Id",label:"微信连接号",sortable:false,width:150,align:"center"},
				{name:"appId",index:"APP_ID",label:"appID",sortable:false,width:150,align:"center"},
				{name:"appSecret",index:"APP_SECRET",label:"appsecret",sortable:false,width:150,align:"center"},
			    {name:'name',index:"NAME",label:'微信昵称',sortable:true,width:150,align:"center"},
			    {name:'organization.orgName',index:"ORG_ID",label:'所属部门',sortable:true,width:150,align:"center"},
			    {name : "sourceId",index : 'sourceId',sortable : false,label : '素材编号',align : 'center',width : 90}, 
			    {name : "sourceTypeDict",index : 'sourceTypeDict.id',sortable : false,label : '素材类型',align : 'center',formatter:sourceTypeDictFormatter, width : 90 },
			    {name : "sourceDescription",index : 'sourceDescription',sortable : false,label : '素材总描述',align : 'center',width : 140},
			    {name : "isAppendKeyWord",index : 'isAppendKeyWord',sortable : false, formatter:isAppend,label : '是否追加关键字',align : 'center',width : 140},
			 
			    
			    {name:'createDate',index:"CREATE_DATE",label:'创建时间',sortable:true,width:150,align:"center"}
			]
		});
	
	}
	//列表显示
	function onOrgChanged(obj){
		$("#tencentUserList").setGridParam({
			url:'${path}/tencentUserManage/getTencentUserList.action',
			datatype: "json",
			page:1
		});
		$("#tencentUserList").setPostData(obj);
		$("#tencentUserList").trigger("reloadGrid");
	}
	//快速搜索
	function fastSearchTencentUser(){
		var searchByCondition=$("#searchByCondition").val();
		if(searchByCondition != null && "请输入微信昵称" != searchByCondition){
			var queryObj = {
				"tencentUser.organization.id" : currentOrgId,
				"tencentUser.name" : searchByCondition
			};
			onOrgChanged(queryObj);
		}
	}
	//高级搜索
	 function searchTencentUser(){
			$("#tencentUserSearchDialog").createDialog({
				width: 500,
				height: 250,
				title:'微信账号查询-请输入查询条件',
				url:'${path}/tencentUserManage/dispatch.action?mode=search',
				buttons: {
					"查询" : function(event){
						var postData = {
							"tencentUser.organization.id" : currentOrgId,
							"tencentUser.appId" : $("#tencentUser_appId").val(),
							"tencentUser.appSecret" : $("#tencentUser_appSecret").val(),
							"tencentUser.code" : $("#tencentUser_code").val(),
							"tencentUser.name" : $("#tencentUser_name").val()
						};
						onOrgChanged(postData);
						$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	}
	//添加微信号
	function addTencentUser(){
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			$("#tencentUserDialog").createDialog({
				title:"新增微信账号",
				width: 600,
				height: 300,
				url:'${path}/tencentUserManage/dispatch.action?mode=add&tencentUser.organization.id='+currentOrgId,
				buttons: {
					"保存并关闭" : function(event){
						$("#tencentUserForm").submit();
					},
					"关闭" : function(event){
						$(this).dialog("close");
					}
				}
			});
		}
	}
	
	
});
//修改微信号
	function updateOperator(event,selectedId){
		$("#tencentUserDialog").createDialog({
	    	title:'修改微信账号',
	    	width:600,
			height:430,
			postData:{
				"tencentUser.organization.id":currentOrgId,
				"tencentUser.tencentUserId":selectedId,
				"mode":"edit"
			},
			url:'${path}/tencentUserManage/dispatch.action',
			buttons: {
				"保存" : function(event){
					$("#tencentUserForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}
	//删除微信号
	function deleteOperator(event,rowIds){
		var rowData = $("#tencentUserList").getRowData(rowIds);
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function(){
				$.ajax({
					url:"${path}/tencentUserManage/deleteTencentUser.action?idsStr="+rowIds,
					success:function(data){
						$("#tencentUserList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功删除所选微信账号信息!"});
				    }
			    });
		    }
	 	});
	}
	
	
	
	
	
</script>