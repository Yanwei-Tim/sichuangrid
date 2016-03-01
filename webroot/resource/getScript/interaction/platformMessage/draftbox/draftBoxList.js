TQ.draftBoxList = function (dfop){
	//列表
	$(document).ready(function(){

		getMessageByUser();
		
		mouseOverOrOut();
		
		getDraftboxPlatformMessageList(1,$("#nowPage").val());
		
		$(".issueLeft div").delegate(".issueList li .issueTitle1","click",function(){
			if($(this).closest("li").hasClass("current")){
				return false;
			}
			$("#draftboxPlatformMessageDetail").show();
			$(this).closest("li").addClass("current").siblings().removeClass("current");
			$(".issueList li input:checkbox").attr("checked",false);
			$(this).find("input").attr("checked",true);
			$("#draftboxPlatformMessageDetail").html('<div class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>');
			$.ajax({
				async : true,
				url : PATH+"/interactive/outboxPlatformMessageManage/getOutboxPlatformMessageById.action",
				data :{"platformMessage.id":$(this).closest("li").attr("id")},
				success : function(data) {
					$(".issueList li.current input:checkbox").attr("checked", true);
					$("#draftboxPlatformMessageDetail").html(data);
					$.messageBox("close");
				}
			 });
			$(this).closest("li").find("a.handleLink").html("已阅读");
			$(this).closest("li").removeClass("unreadTip");
		});
		if( $(".issueList li").length == 0 ){
			$("#draftboxPlatformMessageDetail").html('');
		}

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
			$(".box").each(function(){
				if($(this).attr("checked")=="checked")
				 id+=$(this).val()+",";
			})
			var selectedId = id.substring(0,id.length-1).split(",");
			if(selectedId==null || selectedId==''){
				$.messageBox({level:'warn',message:"没有可删除的消息！"});
				 return;
			}
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
		
		$("#deleteAll").click(function(){
			var id="";
			$(".box").each(function(){
				if($(this).attr("checked")=="checked")
				 id+=$(this).val()+",";
			})
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

		$("#clearSearchCondition").click(function(){
			$("#fastSearchCondition").val('请输入标题或内容');
		});

		//快速搜索
		$("#fastSearchButton").click(function(){
			var fastSearchCondition = $("#fastSearchCondition").val();
			if($.trim(fastSearchCondition)==''||fastSearchCondition=='请输入标题或内容'){
				return ;
			}
			searchOutboxPlatformMessage({"searchPlatformMessageVo.fastSearchCondition":fastSearchCondition});
		});

		
		//刷新
		$("#reload").click(function(){
			reloadOutboxPlatformMessage();
			$("#clearSearchCondition").click();
		});
		
	});

	//高级搜索
	$("#search").click(function(event){
		$("#inboxPlatformMessageDialog").createDialog({
			width: 550,
			height: 250,
			title:'收件箱查询-请输入查询条件',
			url:PATH+'/interaction/platformMessage/outbox/searchPlatformMessageOutBoxDlg.jsp',
			buttons: {
				"查询" : function(event){
					var title=$("#title").val();
					var content=$("#content").val();
					var startTime=$("#startTime").val();
					var endTime=$("#endTime").val();
					var receiverNames=$("#receiverNames").val();
					var hasAttach=$("#hasAttach").val();
					var searchCondition = {
						"searchPlatformMessageVo.title" : title,
						"searchPlatformMessageVo.content" : content,
						"searchPlatformMessageVo.sendTimeStart":startTime,
						"searchPlatformMessageVo.sendTimeEnd":endTime,
						"searchPlatformMessageVo.receiverNames":receiverNames,
						"searchPlatformMessageVo.hasAttach":hasAttach,
						"searchPlatformMessageVo.isDraft":1
						};
					searchOutboxPlatformMessage(searchCondition);
			   		$(this).dialog("close");
			   },
			   "关闭" : function(){
			        	$(this).dialog("close");
			        	document.title = $("#draftBoxManagement").text();
			   }
			}
		});
	});
	
	//<%-- 草稿箱查询--%>
	function searchOutboxPlatformMessage(searchCondition){
		$("#draftboxPlatformMessageDetail").show();
		var pageData =$.extend({"searchPlatformMessageVo.isDraft":1,"page":1,"rows":15,"sidx":"id","sord":"desc","mode":"draft"},searchCondition);
		var pageUrl = PATH+'/interactive/searchPlatformMessage/searchOutboxPlatformMessage.action' ;
		$.ajax({
			async : true,
			url :pageUrl,
			data :pageData,
			success : function(data) {
				$("#inboxPlatformMessageList").empty().html(data);
				$(".issueList li .issueTitle1").eq(0).trigger("click");
				$("#issueOutboxListTotal").html("<lable>"+$("#records").val()+"</lable>");
				if(0 == $(".issueList li").length){
					$("#draftboxPlatformMessageDetail").empty();
				}
			}
		 });
	}
	
	$('#currentPage').keydown(function(e){
		if(e.keyCode==13){
			var currentPage = $("#currentPage").val();
			if(currentPage == "" || currentPage ==0){
				$("#currentPage").val(1);
				currentPage = $("#currentPage").val();
			}
			var patrn=/^[A-Za-z]+$/;
			if(!patrn.exec(currentPage.replace(/[ ]/g,""))){
				getDraftboxPlatformMessageList(currentPage,$(".nowPage").val());
			}
		}
		});

	//<%-- 删除收件箱消息 --%>
	function deleteInboxPlatMessage(id){
		$.ajax({
			url:PATH+'/interactive/outboxPlatformMessageManage/deletePlatformMessageById.action?deleteIds='+ id,
			success:function(data){
				if(data){
					$.messageBox({ message:"成功删除草稿消息!"});
					getMessageByUser();
					reloadOutboxPlatformMessage();
					return;
				}
	            $.messageBox({message:data,level: "error"});
	        }
		});
	}

	$("#sendDraftBox").click(function(){
		var id="";
		$(".box").each(function(){
			if($(this).attr("checked")=="checked")
			 id+=$(this).val()+",";
		})
		var selectedId = id.substring(0,id.length-1).split(",");
		if(selectedId==null || selectedId==''){
			$.messageBox({level:'warn',message:"请选择一条需要发送的消息！"});
			 return;
		}
		if(selectedId.length>1){
			$.messageBox({level:'warn',message:"只能选择一条信息进行发送！"});
			 return;
		}

		$("#outboxPlatformMessageDialog").createDialog({
			width: 800,
			height: 480,
			title:'发平台消息',
			url:PATH+'/interactive/outboxPlatformMessageManage/dispatch.action?mode=draftboxForward&platformMessage.id='+selectedId,
			buttons: {
				"发送" : function(event){
				      $("#maintainForm").submit();
			   },
			   "关闭" : function(event){
				   $(this).dialog("close");
				   document.title = $("#draftBoxManagement").text();
			   }
			}
		});
	});

	function hasRowSelected(){
		if($(".issueList li.current input:checkbox").attr("checked") == "checked"){
			return true;
		}
		return false;
	}


	$("#nowPage").change(function(){
		var rows = $("#nowPage").val();
		var page=  $("#currentPage").val();
		getDraftboxPlatformMessageList(page,rows);
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
		getDraftboxPlatformMessageList(page,rows);
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
		getDraftboxPlatformMessageList(pages,rows);
	});

}