TQ.inboxList = function (dfop){

	clearInterval(window.getInboxListTimer);
	window.getInboxListTimer=setInterval("getInboxList({'inbox.org.id' :getCurrentOrgId()})",150000);

	$(document).ready(function(){
		$("#playMessageSound").hide();
		$("#inboxList").jqGridFunction({
			datatype: "local",
			sortname:"inbox_id",
			multiselect:true,
		   	colModel:[
		   	    {name:'inboxId',index:'inboxId',hidden:true,sortable:false},
		   	    {name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
		   	    {name:'msgId',index:'msgId',label:'信息id（隐藏）',hidden:true,sortable:false},
		   	    {name:'fromUserName',index:'from_User_Name',label:'消息发送者openid（隐藏）',hidden:true,sortable:false},
		   	    {name:'msgType',index:"msg_Type",label:'消息类型（隐藏）',hidden:true,sortable:false},
		   	    {name:'toUserName',index:'to_User_Name',label:'公众平台账号（隐藏）',hidden:true,sortable:false},
		   	    {name:'weChatUserName',index:'weChat_user_name',label:'公众号',sortable:false,width:100},
		   	    {name:'groupName',index:'group_name',label:'所属群组',sortable:false,width:100},
		   	    {name:'createUser',index:'create_User',label:'发信人',sortable:false,width:100},
		   	    {name:'dealStateName',index:'deal_State',label:'受理状态',sortable:false,formatter:formatterSeachIssue,width:100},
		   	    {name:'content',index:'content',label:'内容',sortable:false},
		   	    {name:'createTime',index:"create_Time",label:'创建时间',sortable:false,width:150},
		   	    {name:'inboxAttachments',label:'附件',sortable:false,align:'center',formatter:viewAttachFile,width:300},
		   	    {name:'count',label:'回复',sortable:false,formatter:formatterSeachRM,align:'center',width:100}
			],
			shrinkToFit:true,
			showColModelButton:false,
			loadComplete:loadFiles
		});
		getInboxList({
			"inbox.org.id" :getCurrentOrgId()
		});
		// 刷新
		$("#reload").click(function() {
			getInboxList({
				"inbox.org.id" :getCurrentOrgId()
			});
		});
		
		$("#reply").click(function(){
			var allValue = getSelectedIds();
			if(allValue.length ==0||allValue.length>1){
				$.messageBox({level : 'warn',message:"请选择一条数据，再进行回复！"});
				return;
			}
			var encryptIds=deleteOperatorByEncrypt("inbox",allValue,"encryptId");
			$("#inboxDialog").createDialog({
				title:"回复消息",
				width: 640,
				height: 350,
				url:PATH+'/weChat/inbox/dispatch.action?mode=replyMessage&inbox.inboxId='+encryptIds,
				buttons: {
					"保存" : function(event){
			   			$("#replyForm").submit();
					},
					"关闭" : function(event){
						$(this).dialog("close");
					}
				}
			});
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
		$("#inboxList").delegate(".jp-play", "click", function(){
			var media=$(this).data("media");
			var type=$(this).data("type");
			$("#jquery_jplayer_1").jPlayer( "clearMedia" );
			var box=$(this).closest(".jp-audio");
			$(this).hide().next().show();
			$("#jquery_jplayer_1").jPlayer("setMedia", {
				"mp3": media
			}).jPlayer("play");
		});
		$("#inboxList").delegate(".jp-pause", "click", function(){
			$(this).hide().prev().show();
			$("#jquery_jplayer_1").jPlayer("stop");
		});
	});
	
	

	$("#refreshSearchKey").click(function(){
		$("#searchByCondition").attr("value","请输入发信人");
	});

	//搜索
	 $("#fastSearchButton").click(function(){
		 var createUser=$("#searchByCondition").val();
		 if(searchByCondition!=null&&"请输入发信人"!=searchByCondition){
			 var queryObj = {
				"inbox.org.id" : getCurrentOrgId(),
				"inbox.createUser" : createUser
			};
			getInboxList(queryObj);
		}
	});

	 //高级搜索
	 $("#search").click(function(event){
		$("#inboxDialog").createDialog({
				width:550,
				height:250,
				title:"收件箱查询-请输入查询条件",
				url:PATH+'/hotModuel/inbox/searchInbox.ftl?operateMode=search',
				buttons:{
					"查询" : function(event) {
						searchInbox();
						$(this).dialog("close");
					},
					"关闭" : function() {
						$(this).dialog("close");
					}
				}
		});
	 });
	 
	function searchInbox(){
		var queryObj = {
			"inbox.org.id" : getCurrentOrgId(),
			"inbox.createUser" : $("#createUser").val(),
			"inbox.content" : $("#content").val(),
			"inbox.startCreateTime" : $("#startCreateTime").val(),
			"inbox.endCreateTime" : $("#endCreateTime").val(),
			"inbox.dealState" : $("#dealState").val()
			};
		getInboxList(queryObj);
	 }
	function getSelectedIds(){
			var selectedIds = $("#inboxList").jqGrid("getGridParam", "selarrrow");
			return selectedIds;
	}
	 // 删除
	$("#delete").click(function(event) {
			var allValue = getSelectedIds();
				if(allValue.length ==0){
					$.messageBox({level : 'warn',message:"请选择一条或多条记录，再进行删除！"});
					return;
				}
			$.confirm({
				message:"是否真的删除选中信息？",
				okFunc: function(){
					deleteInboxById(event,allValue);
				}
			});
	});

	//删除主程序
	function deleteInboxById(event,allValue){
	var encryptIds=deleteOperatorByEncrypt("inbox",allValue,"encryptId");
		$.ajax({
			url: PATH
			+ '/weChat/inbox/deleteInboxById.action?inboxIds='
			+ encryptIds,
			success : function(res) {
				if (res) {
					var idArr = allValue.toString().split(",");
					for(var i = 0; i < idArr.length; i++) {
						$("#inboxList")
						.delRowData(idArr[i]);
					}
					$.messageBox({message : "删除成功!"});
					return;
				}
				$.messageBox({message : "删除失败!"});
			}
		});
	}

	 // 事件受理
	$("#EventsToAccept").click(function(event,rowData) {
			var allValue = getSelectedIds();
			if(allValue.length ==0){
				$.messageBox({level : 'warn',message:"请选择一条或多条记录，再进行受理！"});
				return;
			}
			var inbox;
			var fromUserName;
			var dealStateName;
			for(var i=0,len=allValue.length; i<len; i++){
				inbox =  $("#inboxList").getRowData(allValue[i]);
				if(i==0){
				fromUserName=inbox.fromUserName;
				}
				dealStateName=inbox.dealStateName;
				if(dealStateName!='未受理'){
					$.messageBox({level : 'warn',message:"该数据已受理，请再重新选择！"});
					return;
				}
				if(fromUserName!=inbox.fromUserName){
					$.messageBox({level : 'warn',message:"请选择同一个发信人，再进行事件受理！"});
					return;
				}	
			}
			var encryptIds=deleteOperatorByEncrypt("inbox",allValue,"encryptId");
			$("#issueDialog").createDialog({
				width:840,
				height:570,
				title: '转为事件处理',
				url: PATH+ "/issues/issueManage/dispatch.action?mode=eventsToAccept&keyId=<@s.property value='@com.tianque.core.util.ThreadVariable@getOrganization().id'/>&inboxIds="+encryptIds,
				
				buttons: {
			   		"保存" : function(event){
						$("#issueMaintainForm").submit();					
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		} 
				}
			});

	});

	//列表显示
	function getInboxList(obj){
		playMusic();
		$("#inboxList").setGridParam({
			url:PATH+'/weChat/inbox/findInboxs.action',
			datatype: "json",
			page:1,
			mytype:"post"
		});
		$("#inboxList").setPostData(obj);
		$("#inboxList").trigger("reloadGrid"); 
	}

	function playMusic(){
		$.ajax({
			url: PATH+'/weChat/inbox/playMessageSound.action',
			success : function(data) {
				if(data=="true"){
					var div = document.getElementById('div1');
				    div.innerHTML = '<embed src="${resource_path}/resource/music/您有新的消息需要处理.wav" loop="0" autostart="true" hidden="true"></embed>';
				    var emb = document.getElementsByTagName('EMBED')[0];
				    if (emb) {
				        div = document.getElementById('div2');
				        div.innerHTML = 'loading: '+emb.src;
				        setTimeout(function(){div.innerHTML='';},1000);
			    	}
				}
			}
		});
	}
	//查看回复
	function viewReplyMessage(selectedId){
	var inbox=$("#inboxList").getRowData(selectedId);
		$("#inboxDialog").createDialog({
			width: 600,
			height: 240,
			title: '回复记录',
			url:PATH+'/weChat/inbox/dispatch.action?mode=viewReplyMessage&inboxId='+inbox.encryptId,
			buttons: {
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});	
	}
	//加载文件
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			var popMoreData = [];
			$.each($("#inboxList").data($(n).attr("serviceRecordId")),function(ind, fn){
				popMoreData[ind]={id:fn.inboxAttachmentId, url:PATH+'/weChat/inbox/downloadInboxAttachment.action?attachmentId='+fn.inboxAttachmentId, text:fn.fileName,clickFun:function(){}};
			});
			$(n).popUpMore({data: popMoreData});
		});
	}
	//附件
	function formatterAttachFile(el,options,rowData){
		if(rowData.inboxAttachments.length>0){
			$("#inboxList").data(String(rowData.inboxId), rowData.inboxAttachments);
			return "<div style='clear:both' align='center'><a href='javascript:;' id='inbox_"+rowData.inboxId+"' style='color:#666666' class='popUpMore' serviceRecordId='"+rowData.inboxId+"' >附件>></a></div>";
		}
		return "";
	}
	function viewAttachFile(el,options,rowData){
		if(rowData.inboxAttachments.length>0 && rowData.inboxAttachments[0].extFileName=='jpg'){
			return "<a href='javascript:;' onclick='viewFile("+rowData.inboxAttachments[0].inboxAttachmentId+")' ><img src='${resource_path}/resource/system/images/viewImg.png' /></a>";
		}else if(rowData.inboxAttachments.length>0){
			return '<div id="jp_container_1" class="jp-audio"><a href="javascript:;" class="jp-play" data-type="'+rowData.inboxAttachments[0].extFileName+'" data-media="'+rowData.inboxAttachments[0].fileActualUrl+'"><img src="${resource_path}/resource/system/images/jplayer/play.png" /></a><a href="javascript:;" class="jp-pause" tabindex="1"><img src="${resource_path}/resource/system/images/jplayer/stop.gif" /></a><div class="jp-time"><div class="jp-current-time"></div><div class="jp-duration"></div></div></div>';
		}else{
			return '';
		}
	}


	function formatterSeachIssue(el,options,rowData){
		if(rowData.dealStateName=='已受理'){
			$("#inboxList").data(String(rowData.inboxId), rowData.dealStateName);
			return "<a href='javascript:;' onclick='inspectionOperator("+rowData.inboxId+")' id='inbox_"+rowData.inboxId+"' style='color:red;text-decoration:none;' class='popUpMore' inboxId='"+rowData.inboxId+"' >已受理</a>";
		}
		return "未受理";
	}
	//查询回复
	function formatterSeachRM(el,options,rowData){
		var count=rowData.count;
		if(rowData.count!=0){
			$("#inboxList").data(String(rowData.inboxId), rowData.dealStateName);
			return "<div style='clear:both' align='center'><a href='javascript:;' onclick='viewReplyMessage("+rowData.inboxId+")' id='inbox_"+rowData.inboxId+"' style='color:red;text-decoration:none;' >"+count+"</a><div>";
		}
		return " ";
	}

	function inspectionOperator(inboxId){
	var inbox=$("#inboxList").getRowData(selectedId);
			$("#issueDialog").createDialog({
				width: 850,
				height: 430,
				title: "查看事件详情",
				url: PATH+"/issues/issueManage/dispatch.action?mode=viewInbox&keyId="+inboxId+"&inboxId="+inbox.encryptId,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
	}

	function viewFile(inboxAttachmentId){
		$("#inboxDialog").createDialog({
			title:"查看附件",
			width: 450,
			height: 480,
			url:PATH+'/weChat/inbox/dispatch.action?mode=playMedia&attachmentId='+inboxAttachmentId,
			buttons: {
				"关闭" : function(event){
					$(this).dialog("close");
				}
			}
		});
	}
	
}