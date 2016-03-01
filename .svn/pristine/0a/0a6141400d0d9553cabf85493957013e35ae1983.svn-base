<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript" src="/resource/external/jPlayer/js/jquery.jplayer.min.js"></script>
<style>
.jp-jplayer{width:0 !important;height:0 !important;}
.jp-audio{background:url(/resource/system/images/jplayer/bg.png) no-repeat;overflow:hidden;width:90px;height:29px;line-height:27px;padding-left:15px;margin: 3px auto 0;}
.jp-audio a{float:left;}
.jp-pause{display:none;}
.jp-time{float:right;color:#333;}
.jp-current-time{display:none;}
.sourceImg{height: 30px;}
</style>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<s:include value="/common/orgSelectedComponent.jsp" />
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入素材描述" id="searchSourceDescription"
					class="searchtxt" onblur="value=(this.value=='')?'请输入素材描述':this.value;"
					onfocus="value=(this.value=='请输入素材描述')?'':this.value;" />
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:void(0)" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchWeChatSource">
			<a id="searchWeChatSource" href="javascript:void(0)"><span>高级搜索</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="addSource">
			 <a href="javascript:;" class="nav-dropdownBtn"><span>新增素材</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
			 <div class="nav-sub-buttons">
				<a id="addText" href="javascript:void(0)"><span>文本素材</span></a>
				<a id="addImage" href="javascript:void(0)"><span>图片素材</span></a>
				<a id="addVoice" href="javascript:void(0)"><span>语音素材</span></a>
				<a id="addNews" href="javascript:void(0)"><span>图文素材</span></a>
			</div>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateSource">
			<a id="update" href="javascript:;"><span>修改</span> </a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteSource">
			<a id="delete" href="javascript:;"><span>删除</span> </a>
		</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%">
	<div id="jquery_jplayer_1" class="jp-jplayer"></div>
		<table id="weChatSourceList">
		</table>
		<div id="weChatSourceListPager"></div>
	</div>
	
</div>
</div>
	<div id="WeChatSourceMaintanceDialog"></div>
	
<script type="text/javascript">
 <pop:formatterProperty name="sourceTypeDict" domainName="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE" />

 $(document).ready(function(){
	var currentOrgId=getCurrentOrgId();
	//快速检索
	$("#refreshSearchKey").click(function(){
		$("#searchSourceDescription").attr("value","请输入素材描述");
		loadWeChatSource({"weChatSource.org.id" : getCurrentOrgId()});
	});
	//快速检索
	$("#fastSearchButton").click(function(){
		var searchSourceDescription=$("#searchSourceDescription").val();
		if(searchSourceDescription != null && "请输入素材描述" != searchSourceDescription){
			var queryObj = {
				"weChatSource.org.id" : currentOrgId,
				"weChatSource.sourceDescription" : searchSourceDescription
			};
			loadWeChatSource(queryObj);
		}
    });
	//高级搜索
	$("#searchWeChatSource").click(function()
	{
		$("#WeChatSourceMaintanceDialog").createDialog({
			width: 650,
			height: 300,
			title:'素材查询-请输入查询条件',
			url:'${path}/weChatSourceManage/dispatch.action?mode=search&weChatSource.org.id='+currentOrgId,
			buttons: {
				"查询" : function(event){
					var sourceType=$("#search_sourceType").val();
					if($("#search_sourceType").val()=="请选择")
						sourceType="";
					var weChatUserId=$("#search_weChatUserId").val();
					if($("#search_weChatUserId").val()=="请选择微信号")
						weChatUserId="";
					var sourceDescription=$("#search_sourceDescription").val();
					if(sourceDescription==""||sourceDescription==null)
						sourceDescription="";
					var postData = {
							"weChatSource.org.id" : currentOrgId,
							"weChatSource.sourceDescription" : sourceDescription,
							"weChatSource.weChatUserId" : weChatUserId,
							"weChatSource.sourceTypeDict.id" : sourceType
					};
					$(this).dialog("close");
					loadWeChatSource(postData);
					
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
		loadWeChatSource({"weChatSource.org.id" : currentOrgId});
		
	});
	//加载数据
	function loadWeChatSource(queryObj)
	{
		$("#weChatSourceList").setGridParam({
			url :'${path}/weChatSourceManage/findWeChatSource.action',
			datatype: "json",
			page:1
		});
		$("#weChatSourceList").setPostData(queryObj);
		$("#weChatSourceList").trigger("reloadGrid");
	}
	//添加文本素材
	$("#addText").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/weChatSourceManage/validataSourceOrWeChatUser.action?weChatSource.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#WeChatSourceMaintanceDialog").createDialog({
							width: 700,
							height: 400,
							title:'新增文本素材',
							url:'${path}/weChatSourceManage/dispatch.action?mode=addSource&sourceType=1&weChatSource.org.id='+currentOrgId,
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
	//添加图片素材
	$("#addImage").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/weChatSourceManage/validataSourceOrWeChatUser.action?weChatSource.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#WeChatSourceMaintanceDialog").createDialog({
							width: 700,
							height: 350,
							title:'新增图片素材',
							url:'${path}/weChatSourceManage/dispatch.action?mode=addSource&sourceType=2&weChatSource.org.id='+currentOrgId,
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
	
	//添加语音素材
	$("#addVoice").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/weChatSourceManage/validataSourceOrWeChatUser.action?weChatSource.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#WeChatSourceMaintanceDialog").createDialog({
							width: 700,
							height: 350,
							title:'新增语音素材',
							url:'${path}/weChatSourceManage/dispatch.action?mode=addSource&sourceType=4&weChatSource.org.id='+currentOrgId,
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
	//添加图文素材
	$("#addNews").click(function()
	{
		if (currentOrgId==null||currentOrgId==""){
			$.notice({level:'warn',
					message:'请先选择一个部门'});
		}else{
			var urls='${path}/weChatSourceManage/validataSourceOrWeChatUser.action?weChatSource.org.id='+currentOrgId;
			$.ajax({
				url: urls,
				success : function(data) {
					if(data!="true"&&data!=true){
						$.messageBox({message:data,level: "error"});
						return false;
					}
					else{
						$("#WeChatSourceMaintanceDialog").createDialog({
							width: 700,
							height: 540,
							title:'新增图文素材',
							url:'${path}/weChatSourceManage/dispatch.action?mode=addSource&sourceType=3&weChatSource.org.id='+currentOrgId,
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
	
	//修改素材
	$("#update").click(function()
	{
		var selectedIds = $("#weChatSourceList").jqGrid("getGridParam", "selarrrow");
		if(null==selectedIds||selectedIds.length!=1){
			$.messageBox({level:"warn",message:"请选择一条数据再进行操作！"});
	 		return;
		}
		var source =  $("#weChatSourceList").getRowData(selectedIds);
		var type=source.sourceTypeDict
		$("#WeChatSourceMaintanceDialog").createDialog({
			width: 700,
			height: 500,
			title:'修改'+type+'素材',
			url:'${path}/weChatSourceManage/dispatch.action?mode=updateSource&weChatSource.id='+selectedIds,
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
	
	//删除素材
	$("#delete").click(function(){
		var selectedIds = $("#weChatSourceList").jqGrid("getGridParam", "selarrrow");
		if(null==selectedIds||""==selectedIds){
			$.messageBox({level:"warn",message:"请选择一条数据或是多条数据再进行操作！"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"删除该素材后，关键字则不能绑定此素材，确认删除？",
			width: 300,
			okFunc: function(){
				$.ajax({
					url:"${path}/weChatSourceManage/deleteWeChatSource.action?weChatSourceIds="+selectedIds,
					success:function(data){
						if(data!=true && data!="true" ){
							$.messageBox({message:data,level:"error"});
							return ;
						}
						$("#weChatSourceList").trigger("reloadGrid");
						$.messageBox({message:"素材删除成功！"});
					}
				});
			}
		});
	});	
	 

	
	function viewAttachFile(el,options,rowData){
		if(rowData.sourceAttachmentList.length>0 && rowData.sourceAttachmentList[0].extFileName=='jpg'){
			return "<a href='javascript:;'  onclick='viewFile("+rowData.sourceAttachmentList[0].attachmentId+")' ><img class='sourceImg' src='"+rowData.sourceAttachmentList[0].fileActualUrl+"' /></a>";
		}else if(rowData.sourceAttachmentList.length>0){
			return '<div id="jp_container_1" class="jp-audio"><a href="javascript:;" class="jp-play" data-type="'+rowData.sourceAttachmentList[0].extFileName+'" data-media="'+rowData.sourceAttachmentList[0].fileActualUrl+'"><img src="/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="/resource/system/images/jplayer/stop.gif" /></a><div class="jp-time"><div class="jp-current-time"></div><div class="jp-duration"></div></div></div>';
		}else{
			return '';
		}
	}
	//列表显示
		$("#weChatSourceList").jqGridFunction({
			url :'${path}/weChatSourceManage/findWeChatSource.action',
			datatype: "json",
			postData : {
				"weChatSource.org.id" : getCurrentOrgId()
			},
			sortname:"updateDate",
			multiselect:true,
			shrinkToFit:true,
			
			colModel : [ {name : "id", index : "id",frozen:true,hidden : true},
			            {name : "weChatUserId", index : 'weChatUserId',sortable : true,label : '微信号',align : 'center',width : 170},
			            {name : "id", index : "id",hidden : false,label : '素材编号',align : 'center',width:120,sortable : true},
			            {name : "sourceTypeDict", index : 'sourceTypeDict.id', formatter:sourceTypeDictFormatter, sortable : true,label : '素材类型',align : 'center',width : 100},
			            
			            {name : "sourceDescription", index : 'sourceDescription',sortable : true,label : '素材描述',align : 'center',width : 150},

			            {name : "content",index : 'content',sortable : false,label : '文本内容',align : 'center',width : 300},   
			            {name : "sourceAttachmentList", index : 'sourceAttachmentList',sortable : false,label : '附件',align : 'center',formatter:viewAttachFile,width : 250},
			           
			             {name : "title",index : 'title',sortable : false,label : '图文标题',align : 'center',width : 150}, 
			             {name : "description",index : 'description',sortable : false,label : '图文描述',align : 'center',width : 150}, 
			             {name : "url",index : 'url',sortable : false,label : '图文消息跳转链接 ',align : 'center',width : 140},
			             
			             {name : "createUser",index : 'createUser',sortable : true,label : '创建人',align : 'center',width:100}, 
			             {name : "createDate",index : '',sortable : true,label : '创建时间',align : 'center',width:150},
			             {name : "updateUser",index : 'updateUser',sortable : true,label : '修改人',align : 'center',width:100},
			             {name : "updateDate",index : 'updateDate',sortable : true,label : '修改时间',align : 'center',width:150
			        }]
		});
		$("#jquery_jplayer_1").jPlayer({
			ready: function () {
				
			},
			swfPath: "/resource/external/jPlayer/js",
			solution: "flash, html",
			supplied: "mp3,m4a,flv",
			wmode: "window",
			keyEnabled: true
		});
		$("#weChatSourceList").delegate(".jp-play", "click", function(){
			var media=$(this).data("media");
			var type=$(this).data("type");
			$("#jquery_jplayer_1").jPlayer( "clearMedia" );
			var box=$(this).closest(".jp-audio");
			$(this).hide().next().show();
			$("#jquery_jplayer_1").jPlayer("setMedia", {
				"mp3": media
			}).jPlayer("play");
		})
		$("#weChatSourceList").delegate(".jp-pause", "click", function(){
			$(this).hide().prev().show();
			$("#jquery_jplayer_1").jPlayer("stop");
		})
	
	});
 
 function viewFile(attachmentId){
		 $("#WeChatSourceMaintanceDialog").createDialog({
			title:"查看附件",
			width: 550,
			height: 550,
			url:'${path}/weChatSourceManage/dispatch.action?mode=playMedia&weChatSourceAttachment.attachmentId='+attachmentId,
			buttons: {
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		}); 
	}
	
</script>