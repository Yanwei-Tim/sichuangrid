TQ.pmInboxList = function (dfop){
	//列表
	$(document).ready(function(){

		getMessageByUser();
		
		mouseOverOrOut();
		
		getInboxPlatformMessageList(1,$(".nowPage").val());
		
		$(".issueLeft div").delegate(".issueList li .issueTitle1","click",function(){
			if($(this).hasClass("current")){
				return false;
			}
			$("#inboxPlatformMessageDetail").show();
			$(this).closest("li").addClass("current").siblings().removeClass("current");
			$(".issueList li input:checkbox").attr("checked",false);
			$(this).find("input").attr("checked",true);
			if(isSystem==0){
				if( $("#systemIssueList li").length == 0 ){
					$("#inboxPlatformMessageDetail").html('');
					return;
				}
			}else{
				if( $("#issueSelfList li").length == 0 ){
					$("#inboxPlatformMessageDetail").html('');
					return;
				}
			}
			$("#inboxPlatformMessageDetail").html('<div class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>');
			$.ajax({
				async : true,
				url : PATH+"/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=view",
				data :{"platformMessage.id":$(this).closest("li").attr("id") },
				success : function(data) {
					$(".issueList li.current input:checkbox").attr("checked", true);
					$("#inboxPlatformMessageDetail").html(data);
					$.messageBox("close");
				}
			 });
			$(this).closest("li").find("a.handleLink").html("已阅读");
			$(this).closest("li").removeClass("unreadTip");
		});
		//回复
		$("#reply").click(function(){
			var id="";
			if(isSystem==0){
				$(".box").each(function(){
					if($(this).attr("checked")=="checked")
					 id+=$(this).val()+",";
				})
			}else if(isSystem==1){
				$(".boxs").each(function(){
					if($(this).attr("checked")=="checked")
					 id+=$(this).val()+",";
				})
			}
			if(id==null || id==''){
				$.messageBox({level:'warn',message:"请选择一条需要回复的消息！"});
				 return;
			}
			var selectedId = id.substring(0,id.length-1).split(",");
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一条信息进行回复！"});
				 return;
			} 
			var pm=null;
			if(isSystem==0){
					$.messageBox({level:'warn',message:"系统消息不能回复！"});
					return;
			}else{
				 pm = $("#issueSelfList li.current");
				if(pm.attr("sendtype")==dfop.systermSned){
					$.messageBox({level:'warn',message:"系统消息不能回复！"});
					return;
				}
			}
			replyPlatformMessage(selectedId);
		});


		//转发消息
		$("#forward").click(function(event){
			var id="";
			if(isSystem==0){
				$(".box").each(function(){
					if($(this).attr("checked")=="checked")
					 id+=$(this).val()+",";
				})
			}else if(isSystem==1){
				$(".boxs").each(function(){
					if($(this).attr("checked")=="checked")
					 id+=$(this).val()+",";
				})
			}
			if(id==null || id==''){
				$.messageBox({level:'warn',message:"请选择一条需要转发的消息！"});
				 return;
			}
			var selectedId = id.substring(0,id.length-1).split(",");
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一条信息进行转发！"});
				 return;
			} 
			
			
			var pm=null;
			if(isSystem==0){
					$.messageBox({level:'warn',message:"系统消息不能转发！"});
					return;
			}else{
				 pm = $("#issueSelfList li.current");
				if(pm.attr("sendtype")==dfop.systermSned){
					$.messageBox({level:'warn',message:"系统消息不能转发！"});
					return;
				}
			}
			$("#inboxPlatformMessageDialog").createDialog({
				width: 800,
				height: 500,
				title:'转发平台消息',
				url:PATH+'/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=inboxForward&platformMessage.id='+selectedId,
				buttons: {
					"发送" : function(event){
						  $("#maintainForm").submit();
				   },
				   "关闭" : function(){
				         $(this).dialog("close");
				         document.title = $("#pmInboxManagement").text();
				   }
				}
			});
		});

		//标记为已读
		$("#stopRead").click(function(){
			var selectedIds = setCompVal();
			if(selectedIds.length < 1){
			$.messageBox({level:'warn',message:"请选择要标记为已读的消息！"});
			  return;
			}
			var readed = false ;
			for(var i = 0 ; i < selectedIds.length ; i++){
				if($("#personelMessageList").getRowData(selectedIds[i]).readState=='是'){
					readed = true ;
				}else{
					readed = false
				}
			}
			if(readed){
				$.messageBox({level:'warn',message:"已读的消息不能再次标记为已读！"});
				return;
			}
			$.ajax({
				async: false,
				url: "${path }/interactive/inboxPlatformMessageManage/markReadPlatformMessageById.action?ids="+selectedIds,
				success:function(responseData){
					$.messageBox({ message:"成功标记消息为已读!"});
					getMessageByUser();
				}
			});
		});

		//删除
		$("#delete").click(function(){
			var id="";
			if(isSystem==0){
				$(".box").each(function(){
					if($(this).attr("checked")=="checked")
					 id+=$(this).val()+",";
				})
			}else if(isSystem==1){
				$(".boxs").each(function(){
					if($(this).attr("checked")=="checked")
					 id+=$(this).val()+",";
				})
			}
			if(id==null || id==''){
				$.messageBox({level:'warn',message:"没有可删除的消息！"});
				 return;
			}
			var selectedId = id.substring(0,id.length-1).split(",");
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一条信息进行删除！"});
				 return;
			}
			$.confirm({
				title:"确认删除",
				message:"该消息删除后将无法恢复,您确认删消信息吗?",
				width: 300,
				okFunc: function(){
					deleteInboxPlatMessage(selectedId);
				}
			});

		});

		$("#clearSearchCondition").click(function(){
			$("#fastSearchCondition").val('请输入标题或内容');
		});

		//快速搜索
		$("#fastSearchButton").click(function(){
			var fastSearchCondition = $("#fastSearchCondition").val();
			if($.trim(fastSearchCondition)==''||fastSearchCondition=='请输入标题或内容'){
				return ;
			}
			if(isSystem==0){
				searchInboxPlatformMessage({"searchPlatformMessageVo.fastSearchCondition":fastSearchCondition},1,$("#nowPage").val());
			}else{
				searchSelfInboxPlatformMessage({"searchPlatformMessageVo.fastSearchCondition":fastSearchCondition},1,$("#nowPageSelf").val());
			}
			
		});

		//高级搜索
		$("#search").click(function(event){
			$("#inboxPlatformMessageDialog").createDialog({
				width: 550,
				height: 250,
				title:'收件箱查询-请输入查询条件',
				url:PATH+'/interaction/platformMessage/inbox/searchPlatformMessageInboxDlg.jsp',
				buttons: {
					"查询" : function(event){
						if(isSystem==0){
							searchInboxPlatformMessage($("#searchInboxPlaformMessageForm").serializeObject(),1,$("#nowPage").val());
						}else{
							searchSelfInboxPlatformMessage($("#searchInboxPlaformMessageForm").serializeObject(),1,$("#nowPageSelf").val());
						}
						   	$(this).dialog("close");
				   },
				   "关闭" : function(){
				        	$(this).dialog("close");
				   }
				}
			});
		});

		//刷新
		$("#reload").click(function(){
			reloadInboxPlatformMessage();
			$("#clearSearchCondition").click();
		});

		//消息类型过滤
		$("#inboxPltformMessageType").change(function(){
			var searchCondition = {};
			if($(this).val()!=''){
				searchCondition = eval("("+$(this).val()+")");
			}else{
				if(isSystem==0){
					getInboxPlatformMessageList(1,$("#nowPage").val());
				}else if(isSystem=1){
					getInboxPlatformSelfMessageList(1,$("#nowPageSelf").val());
				}
			}
			if(isSystem==0){
				searchInboxPlatformMessage(searchCondition,1,$("#nowPage").val());
			}else if(isSystem==1){
				searchSelfInboxPlatformMessage(searchCondition,1,$("#nowPageSelf").val());
			}
		});
		
	});
	

	$('#currentPage').keydown(function(e){
		if(e.keyCode==13){
			var currentPage = $("#currentPage").val();
			if(currentPage == "" || currentPage ==0){
				$("#currentPage").val(1);
				currentPage = $("#currentPage").val();
			}
			var patrn=/^[A-Za-z]+$/;
			if(!patrn.exec(currentPage.replace(/[ ]/g,""))){
		 		getInboxPlatformMessageList(currentPage,$(".nowPage").val());
			}
		}
	});
		
	$('#currentPageSelf').keydown(function(e){
		if(e.keyCode==13){
			var currentPage = $("#currentPageSelf").val();
			if(currentPage == "" || currentPage ==0){
				$("#currentPage").val(1);
				currentPage = $("#currentPage").val();
			}
			var patrn=/^[A-Za-z]+$/;
			if(!patrn.exec(currentPage.replace(/[ ]/g,""))){
				getInboxPlatformSelfMessageList(currentPage,$(".nowPageSelf").val());
			}
		}
	});
		
	$(function(){
		$(".News_tips_click").click(function(){
			$(".News_tips_main").toggle("show")
		})
	})
	var isSystem = 0;


	function getSelfMessage(){
		getInboxPlatformSelfMessageList(1,$("#nowPageSelf").val());
	}

	function reloadInboxPlatformMessage() {
		if(isSystem==0){
			getInboxPlatformMessageList(1,$("#nowPage").val());
		}else{
			getInboxPlatformSelfMessageList(1,$("#nowPage").val());
		}
		$("#fastSearchCondition").attr("value","请输入标题或内容");
	}

	//<%-- 收件箱查询  系统消息--%>
	function searchInboxPlatformMessage(searchCondition,page,rows){
		$("#inboxPlatformMessageDetail").show();
		var pageUrl = PATH+"/interactive/searchPlatformMessage/searchInboxPlatformMessage.action";
		var pageData =$.extend({"isAdmin":"1","page":page,"rows":rows,"sidx":"id","sord":"desc","mode":"searchInboxSystemMessage"},searchCondition);
		$.ajax({
			async : true,
			url : pageUrl,
			data :pageData,
			success : function(data) {
				$("#inboxPlatformMessageList").empty().html(data);
				$("#systemIssueList li .issueTitle1").eq(0).trigger("click");
				$("#issueInboxListTotal").html("<lable>"+$("#records").val()+"</lable>");
				bindPager();
				if(0 == $("#systemIssueList li").length){
					$("#inboxPlatformMessageDetail").empty();
				}
			}
		 });
	}
	
	//<%--个人消息--%>
	function searchSelfInboxPlatformMessage(searchCondition,page,rows){
		$("#inboxPlatformMessageDetail").show();
		var pageUrl = PATH+"/interactive/searchPlatformMessage/searchInboxPlatformMessage.action";
		var pageData =$.extend({"isAdmin":"0","page":page,"rows":rows,"sidx":"id","sord":"desc","mode":"searchInboxSelfMessage"},searchCondition);
		$.ajax({
			async : true,
			url : pageUrl,
			data :pageData,
			success : function(data) {
				$("#inboxPlatformSelfMessageList").empty().html(data);
				$("#issueSelfList li .issueTitle1").eq(0).trigger("click");
				$("#issueInboxListTotal").html("<lable>"+$("#records").val()+"</lable>");
				bindPagerSelf();
				if(0 == $("#issueSelfList li").length){
					$("#inboxPlatformMessageDetail").empty();
				}
			}
		 });
	}
	
	//<%-- 删除收件箱消息 --%>
	function deleteInboxPlatMessage(id){
		$.ajax({
			url:PATH+'/interactive/inboxPlatformMessageManage/deleteInboxPlatformMessageByIds.action?ids='+ id,
			success:function(data){
				if(data){
					$.messageBox({ message:"成功删除消息!"});
					getMessageByUser();
					if(isSystem==0){
						reloadInboxPlatformMessage();
					}else{
						getSelfMessage();
					}
					return;
				}
	            $.messageBox({message:data,level: "error"});
	        }
		});
	}

	//<%--回复平台信息--%>
	function replyPlatformMessage(id){
		$("#inboxPlatformMessageDialog").createDialog({
			width: 600,
			height: 500,
			title:'回复平台消息',
			url:PATH+'/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=reply&platformMessage.id='+id,
			buttons: {
				"回复" : function(event){
				     $("#maintainForm").submit();
			   },
			   "关闭" : function(){
				   	$(this).dialog("close");
				    getMessageByUser();
				    document.title = $("#pmInboxManagement").text();
			   }
			}
		});
	}

	function hasRowSelected(){
		if($(".issueList li.current input:checkbox").attr("checked") == "checked"){
			return true;
		}
		return false;
	}

	$(function(){
		$(".issueProgram_Tab_list li").click(function(){
			$(".issueProgram_Tab_show").hide().eq($(this).index()).show();
			$(this).parent().find("span").removeClass("OOn").eq($(this).index()).addClass("OOn");
		}).eq(0).click();
	})

	$("#deleteAll").click(function(){
		var id="";
		if(isSystem==0){
			$(".box").each(function(){
				if($(this).attr("checked")=="checked")
				 id+=$(this).val()+",";
			})
		}else if(isSystem==1){
			$(".boxs").each(function(){
				if($(this).attr("checked")=="checked")
				 id+=$(this).val()+",";
			})
		}
		
		if(id==null || id==''){
			$.messageBox({level:'warn',message:"没有可删除的消息！"});
			 return;
		}
		var selectedId = id.substring(0,id.length-1).split(",");
		$.confirm({
			title:"确认删除",
			message:"该信息删除后将无法恢复,您确认删除信息吗?",
			width: 300,
			okFunc: function(){
				deleteInboxPlatMessage(selectedId);
			}
		 });
		
	});

	$("#nowPage").change(function(){
		var rows = $("#nowPage").val();
		var page=  $("#currentPage").val();
			getInboxPlatformMessageList(page,rows);
	});

	$("#upperPage").click(function(){
		var rows = $("#nowPage").val();
		var page=  $("#currentPage").val()-1;
		if(page==0){
			return;
		}
		if(page<1){
			page=1;
		}
			getInboxPlatformMessageList(page,rows);
	});
	$("#nextPage").click(function(){
		var rows = $("#nowPage").val();
		var page=  $("#currentPage").val();
		var allCount= $("#records").val();
		var nowPage = (allCount-0)%(rows-0)==0?(allCount-0)/(rows-0):(allCount-0)/(rows-0)+1;
		if(page==Math.floor(nowPage)){
			return;
		}
		var pages=(page-0)+1;
			getInboxPlatformMessageList(pages,rows);
	});

	$("#nowPageSelf").change(function(){
		var rows = $("#nowPageSelf").val();
		var page=  $("#currentPageSelf").val();
		getInboxPlatformSelfMessageList(page,rows);
	});

	$("#upperPageSelf").click(function(){
		var rows = $("#nowPageSelf").val();
		var page=  $("#currentPageSelf").val()-1;
		if(page==0){
			return;
		}
		if(page<1){
			page=1;
		}
		getInboxPlatformSelfMessageList(page,rows);
	});
	$("#nextPageSelf").click(function(){
		var rows = $("#nowPageSelf").val();
		var page=  $("#currentPageSelf").val();
		var allCount= $("#recordsSelf").val();
		var nowPage = (allCount-0)%(rows-0)==0?(allCount-0)/(rows-0):(allCount-0)/(rows-0)+1;
		if(page==Math.floor(nowPage)){
			return;
		}
		var pages=(page-0)+1;
		getInboxPlatformSelfMessageList(pages,rows);
	});


}