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
				<input class="basic-input" type="text" value="请输入关键字" id="searchByKeyName"
					class="searchtxt" onblur="value=(this.value=='')?'请输入关键字':this.value;"
					onfocus="value=(this.value=='请输入关键字')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchKeyWord">
			<a id="searchKeyWord" href="javascript:void(0)"><span>高级搜索</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="addKeyWord">
			 <a href="javascript:;" class="nav-dropdownBtn"><span>新增关键字</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
			 <div class="nav-sub-buttons">
				<a id="addText" href="javascript:void(0)"><span>文本关键字</span></a>
				<a id="addImage" href="javascript:void(0)"><span>图片关键字</span></a>
				<a id="addVoice" href="javascript:void(0)"><span>语音关键字</span></a>
				<a id="addNews" href="javascript:void(0)"><span>图文关键字</span></a>
			</div>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateKeyWord">
			<a id="update" href="javascript:;"><span>修改</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteKeyWord">
			<a id="delete" href="javascript:;"><span>删除</span> </a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
		<table id="keyWordList">
		</table>
		<div id="keyWordListPager"></div>
	</div>
	
</div>
 
	<div id="keyWordMaintanceDialog"></div>
<script type="text/javascript">
<pop:formatterProperty name="sourceTypeDict" domainName="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE" />

$(document).ready(function(){
	var currentOrgId=getCurrentOrgId();
	//快速检索
	$("#refreshSearchKey").click(function(){
		$("#searchByKeyName").attr("value","请输入关键字");
		loadKeyWord({"keyWord.org.id" : currentOrgId});
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchByKeyName=$("#searchByKeyName").val();
		if(searchByKeyName != null && "请输入关键字" != searchByKeyName){
			var queryObj = {
				"keyWord.org.id" : currentOrgId,
				"keyWord.keyName" : searchByKeyName
			};
			loadKeyWord(queryObj);
		}
    });
	//高级搜索
	$("#searchKeyWord").click(function()
	{
		$("#keyWordMaintanceDialog").createDialog({
			width: 650,
			height: 300,
			title:'微信关键字查询-请输入查询条件',
			url:'${path}/keyWordManage/dispatch.action?mode=search&keyWord.org.id='+currentOrgId,
			buttons: {
				"查询" : function(event){
					var sourceType=$("#search_sourceType").val();
					if($("#search_sourceType").val()=="请选择")
						sourceType="";
					var weChatUserId=$("#search_weChatUserId").val();
					if($("#search_weChatUserId").val()=="请选择微信号")
						weChatUserId="";
					var keyName=$("#search_keyName").val();
					if(keyName==""||keyName==null)
						keyName="";
					var postData = {
							"keyWord.org.id" : currentOrgId,
							"keyWord.keyName" : keyName,
							"keyWord.weChatUserId" : weChatUserId,
							"keyWord.sourceTypeDict.id" : sourceType
					};
					$(this).dialog("close");
					loadKeyWord(postData);
					
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
		loadKeyWord({"keyWord.org.id" : currentOrgId});
		
	});
	//加载数据
	function loadKeyWord(queryObj)
	{
		$("#keyWordList").setGridParam({
			url :'${path}/keyWordManage/findkeyWord.action',
			datatype: "json",
			page:1
		});
		$("#keyWordList").setPostData(queryObj);
		$("#keyWordList").trigger("reloadGrid");
	}
	//添加文本	
	$("#addText").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/keyWordManage/validataSourceOrWeChatUser.action?sourceType=1&keyWord.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#keyWordMaintanceDialog").createDialog({
							width: 700,
							height: 350,
							title:'新增文本关键字',
							url:'${path}/keyWordManage/dispatch.action?mode=add&sourceType=1&keyWord.org.id='+currentOrgId,
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
	//添加图片	
	$("#addImage").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/keyWordManage/validataSourceOrWeChatUser.action?sourceType=2&keyWord.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#keyWordMaintanceDialog").createDialog({
							width: 700,
							height: 350,
							title:'新增图片关键字',
							url:'${path}/keyWordManage/dispatch.action?mode=add&sourceType=2&keyWord.org.id='+currentOrgId,
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
		//添加图文	
	$("#addNews").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/keyWordManage/validataSourceOrWeChatUser.action?sourceType=3&keyWord.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#keyWordMaintanceDialog").createDialog({
							width: 700,
							height: 500,
							title:'新增图文关键字',
							url:'${path}/keyWordManage/dispatch.action?mode=add&sourceType=3&keyWord.org.id='+currentOrgId,
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
	//添加语音
	$("#addVoice").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/keyWordManage/validataSourceOrWeChatUser.action?sourceType=4&keyWord.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#keyWordMaintanceDialog").createDialog({
							width: 700,
							height: 350,
							title:'新增语音关键字',
							url:'${path}/keyWordManage/dispatch.action?mode=add&sourceType=4&keyWord.org.id='+currentOrgId,
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


	
//删除关键词
	$("#delete").click(function(){
		var selectedIds = $("#keyWordList").jqGrid("getGridParam", "selarrrow");
		if(null==selectedIds||""==selectedIds){
			$.messageBox({level:"warn",message:"请选择一条数据或是多条数据再进行操作！"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"删除该关键词后，信息则不能自动回复，确认删除？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:"${path}/keyWordManage/deleteKeyWord.action?keyWordIdS="+selectedIds,
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#keyWordList").trigger("reloadGrid");
						$.messageBox({message:"关键词删除成功！"});
					}
				});
			}
		});
	});	
	
//修改关键词
$("#update").click(function(event){
		var selectedId = $("#keyWordList").jqGrid("getGridParam", "selarrrow");
		if(selectedId==null||selectedId.length!=1){
				$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
				return;
		}
		var source =  $("#keyWordList").getRowData(selectedId);
		var type=source.sourceTypeDict
		$("#keyWordMaintanceDialog").createDialog({
			width: 700,
			height: 500,
			title:'修改'+type+'关键字',
			url:'${path}/keyWordManage/dispatch.action?mode=edit&keyWord.id='+selectedId,
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
	
	
	
	
	//列表显示
		$("#keyWordList").jqGridFunction({
			url :'${path}/keyWordManage/findkeyWord.action',
			datatype: "json",
			postData : {
				"keyWord.org.id" : getCurrentOrgId()
			},
			sortname:"createDate",
			multiselect:true,
			shrinkToFit:true,
		
			colModel : [ {name : "id", index : "id",hidden : true,sortable : false},
			             {name : "keyName", index : 'keyName',sortable : true,label : '关键字',align : 'center',width : 95},
			             {name : "keyRemark",index : 'keyRemark',sortable : false,label : '描述',align : 'center',width : 90}, 
			             {name : "weChatUserId",index : 'weChatUserId',sortable : true,label : '微信号',align : 'center',width : 120}, 
			             {name : "sourceId",index : 'sourceId',sortable : true,label : '素材编号',align : 'center',width : 90}, 
			             {name : "sourceTypeDict",index : 'sourceTypeDict.id',sortable : true,label : '素材类型',align : 'center',formatter:sourceTypeDictFormatter, width : 90 },
			             {name : "sourceDescription",index : 'sourceDescription',sortable : true,label : '素材总描述',align : 'center',width : 140},
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建人',align : 'center',width : 105}, 
			             {name : "createDate",index : 'createDate',sortable : true,label : '创建时间',align : 'center',width : 140},
			             {name : "updateUser",index : 'updateUser',sortable : true,label : '修改人',align : 'center',width : 100},
			             {name : "updateDate",index : 'updateDate',sortable : true,label : '修改时间',align : 'center',width : 140}
			             ]
		});
	
	});
</script>