TQ.pmOutboxList = function (dfop){
	$(document).ready(function(){
		mouseOverOrOut();
		getOutboxPlatformMessageList(1,$("#nowPage").val());
		$(".issueLeft div").delegate(".issueList li .issueTitle1","click",function(){
			if($(this).closest("li").hasClass("current")){
				return false;
			}
			$("#platformMessageDetail").show();
			$(this).closest("li").addClass("current").siblings().removeClass("current");
			$(".issueList li input:checkbox").attr("checked",false);
			$(this).find("input").attr("checked",true);
			$("#platformMessageDetail").html('<div class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>');
			$.ajax({
				async : true,
				url : PATH+"/interactive/outboxPlatformMessageManage/getOutboxPlatformMessageById.action",
				data :{"platformMessage.id":$(this).closest("li").attr("id") },
				success : function(data) {
					$(".issueList li.current input:checkbox").attr("checked", true);
					$("#platformMessageDetail").html(data);
				}
			 });
		});

		if( $(".issueList li").length == 0 ){
			$("#platformMessageDetail").html('');
		}
		
		//发送消息
		$("#add").click(function(event){
			$("#outboxPlatformMessageDialog").createDialog({
				width: 800,
				height: 480,
				title:'发平台消息',
				url:PATH+'/interaction/platformMessage/outbox/maintainPlatformMessageOutBoxDlg.jsp',
				buttons: {
					"发送" : function(event){
					      $("#maintainForm").submit();
				   },
				   "保存至草稿箱" : function(){
					   $("#isDraft").attr("value","1");
					   $("#maintainForm").submit();    
				   },
				   "关闭" : function(event){
					   $(this).dialog("close");
					   document.title = $("#pmOutboxManagement").text();
				   }
				}
			});
		});
		//转发消息
		$("#forward").click(function(event){
			var id="";
			$(".box").each(function(){
				if($(this).attr("checked")=="checked")
				 id+=$(this).val()+",";
			})
			var selectedId = id.substring(0,id.length-1).split(",");
			if(selectedId==null || selectedId==''){
				$.messageBox({level:'warn',message:"请选择一条需要转发的消息！"});
				 return;
			}
			if(selectedId.length>1){
				$.messageBox({level:'warn',message:"只能选择一条信息进行转发！"});
				 return;
			}
			
			$("#outboxPlatformMessageDialog").createDialog({
				width: 800,
				height: 500,
				title:'转发平台消息',
				url:PATH+'/interactive/outboxPlatformMessageManage/dispatch.action?mode=outboxForward&platformMessage.id='+selectedId,
				buttons: {
					"发送" : function(event){
					      $("#maintainForm").submit();
				   },
				   "关闭" : function(){
				         $(this).dialog("close");
				         document.title = $("#pmOutboxManagement").text();
				   }
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
					$.messageBox({level:'warn',message:"您只能选择一条信息进行删除！"});
					 return;
				}
			$.confirm({
				title:"确认删除",
				message:"该信息删除后将无法恢复,您确认删除信息吗?",
				width: 300,
				okFunc: function(){
					deletePlatformMessage(selectedId);
				}
			 });
		});

		//批量删除
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
					deletePlatformMessage(selectedId);
				}
			 });
		});
		
		function deletePlatformMessage(id){
			$.ajax({
				url:PATH+'/interactive/outboxPlatformMessageManage/deletePlatformMessageById.action?deleteIds='+ id,
				success:function(data){
					if(data){
						$.messageBox({ message:"成功删除该消息!"});
						reloadOutboxPlatformMessage();
						return;
					}
		            $.messageBox({
						message:data,
						level: "error"
					});
		        }
			});
		}

		//刷新
		$("#reload").click(function(){
			reloadOutboxPlatformMessage();
			$("#clearSearchCondition").click();
		});

		$("#clearSearchCondition").click(function(){
			$("#fastSearchCondition").val('请输入标题或内容');
		});

		//查询
		$("#fastSearchButton").click(function(){
			var fastSearchCondition = $("#fastSearchCondition").val();
			if(fastSearchCondition==''||fastSearchCondition=='请输入标题或内容'){
				return ;
			}
			searchOutboxPlatformMessage({"searchPlatformMessageVo.fastSearchCondition":fastSearchCondition},1,$("#nowPage").val());
		});
		
		//高级查询
		$("#search").click(function(event){
			$("#outboxPlatformMessageDialog").createDialog({
				width: 600,
				height: 200,
				title:'发件箱查询-请输入查询条件',
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
						"searchPlatformMessageVo.isDraft":0
						};
					searchOutboxPlatformMessage(searchCondition,1,$("#nowPage").val());
			   		$(this).dialog("close");
				   },
				   "关闭" : function(){
				        $(this).dialog("close");
				        document.title = $("#pmOutboxManagement").text();
				   }
				}
			});
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
				getOutboxPlatformMessageList(currentPage,$(".nowPage").val());
			}
		}
		});
	$(function(){
		$(".News_tips_click").click(function(){
			$(".News_tips_main").toggle("show")
		})
	})

	function searchOutboxPlatformMessage(searchCondition,page,rows){
		$("#platformMessageDetail").show();
		var pageData =$.extend({"searchPlatformMessageVo.isDraft":0,"page":page,"rows":rows,"sidx":"id","sord":"desc"},searchCondition);
		var pageUrl = PATH+'/interactive/searchPlatformMessage/searchOutboxPlatformMessage.action' ;
		$.ajax({
			async : true,
			url :pageUrl,
			data :pageData,
			success : function(data) {
				$("#outboxPlatformMessageList").empty().html(data);
				$(".issueList li .issueTitle1").eq(0).trigger("click");
				$("#issueOutboxListTotal").html("<lable>"+$("#records").val()+"</lable>");
				bindPager();
				if(0 == $(".issueList li").length){
					$("#platformMessageDetail").empty();
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

	$("#nowPage").change(function(){
		var rows = $("#nowPage").val();
		var page=  $("#currentPage").val();
		getOutboxPlatformMessageList(page,rows);
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
		getOutboxPlatformMessageList(page,rows);
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
		getOutboxPlatformMessageList(pages,rows);
	});
}