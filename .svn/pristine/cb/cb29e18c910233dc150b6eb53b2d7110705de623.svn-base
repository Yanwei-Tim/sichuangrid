
function updateIssue(){
	var selectedId = $("#issueList").getGridParam("selrow");
	if(selectedId == null){
 		return;
	}
	var rowData = $("#issueList").getRowData(selectedId);
	$("#issueDialog").createDialog({
		width:840,
		height:570,
		title: '修改事件处理信息',
		url:'/issue/issueManage/dispatch.action?issueMode=edit&issueNew.id='+rowData.issueId+'&issueLogId='+rowData.issueLogId+'&organization.id='+$("#currentOrgId").val(),
		buttons: {
	   		"保存" : function(event){
   				$("#issueMaintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function editIssue(keyId){
	$("#issueDialog").createDialog({
		width:840,
		height:570,
		title: '修改事件处理信息',
		url:'/issues/issueManage/dispatch.action?mode=editIssueForTab&keyId='+keyId,
		buttons: {
	   		"保存" : function(event){
				$("#issueMaintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}


function deleteIssue(){
	var	selectedId = $("#issueList").getGridParam("selrow");
	var rowData = $("#issueList").getRowData(selectedId);
	if(null == selectedId){
		return;
	}
	$.ajax({
		url:"/issue/issueManage/deleteIssueById.action",
		data:{
			"issueNew.id":rowData.issueId
		},
		success:function(responseData){
			if (responseData>0){
			    $.messageBox({message:"已经成功删除该事件处理信息!"});
			    getMessageByUser();
			}else{
				$.messageBox({level:'error',message:"找不到要删除的事件处理信息，不能删除!"});
			}
		}
	});
}

function removeIssue(keyId){
	$.ajax({
		url:"/issues/issueManage/deleteIssue.action",
		data:{
			"keyId":keyId,
			"viewType":$("#jurisdictionsViewType").val()
		},
		success:function(responseData){
			if (responseData==true){
				$.messageBox({message:"已经成功删除该事件处理信息!"});
				setTimeout(reloadIssue,1000);
				getMessageByUser();
			}else{
				$.messageBox({level:'error',message:"删除事件失败!"});
			}
		}
	});
}

function removeIssueByIssueId(issueId){
	$.ajax({
		url:"/issues/issueManage/deleteIssue.action",
		data:{
			"keyId":issueId
		},
		success:function(responseData){
			if (responseData==true){
			    $.messageBox({message:"已经成功删除该事件!"});
			    setTimeout(reloadIssue,1000);
			    getMessageByUser();
			}else{
				$.messageBox({level:'error',message:"删除事件失败!"});
			}
		}
	});
}

function settingIssueHistorical(keyId,type,token){
	if (!isNullObject(keyId)){
		$.ajax({
			url:'/issues/issueManage/dealIssue.action',
			data:{
				"keyId":keyId,
				"dealCode":type,
				"struts.token":token
			},
			success:function(data){
				if (data.issueStepId>0){			
					$.messageBox({message:"成功设置该事件的历史遗留状态!"});
					setTimeout(reloadIssue,1000);
					getMessageByUser();
				}else{
				 	$.messageBox({level:"error",message:data});
				}
			}
		});
	}
}

function historicalIssue(){
	var selectedId= $("#issueList").getGridParam("selrow");
	if(null ==selectedId ){
		return;
	}
		$.ajax({
		url:'/issue/issueManage/historicalIssue.action',
		data:{
			"step.id":selectedId
		},
		success:function(data){
			if (data>0){			
				$.messageBox({message:"该事件处理已设置为历史遗留!"});
                $(".message").click();
			}else{
			 	$.messageBox({message:data});
			}
		}
	});
}

function issueRightHeight(){
	function getHeight(){
		var tempHeight=$(".ui-layout-center").height()-$("#thisCrumbs").height()-$("#nav").height()-$(".ui-tabs .ui-tabs-nav li").height()
		var centerLayerHeight=tempHeight-15;
		var issueListHeight=tempHeight-$(".issueLeft .issueTitle").height()-65;
		$(".issueProgram").height(centerLayerHeight);
		$(".issueLeft").height(centerLayerHeight);
		$(".issueLeft .issueList").height(issueListHeight);
		$(".issueRight").height(centerLayerHeight);
	}
	getHeight();
	
	$(window).resize(function(){
		clearTimeout(window._issueRightTimer);
		window._issueRightTimer=setTimeout(function(){getHeight()},300);
	})
}

function mouseOverOrOut(){
	$(".issueLeft div").delegate(".issueList li","mouseover",function(){
		$(this).addClass("hoverCur").siblings().removeClass("hoverCur");
	});

	$(".issueLeft div").delegate(".issueList li","mouseout",function(){
		$(this).removeClass("hoverCur");
	});
}
function initIssueList(){
	$(".issueList li a.handleLink").each(function(){
		if($(this).html()==110){
			$(this).html("待受理").addClass("backlog");
		}else if($(this).html()==140){
			$(this).html("待阅读").addClass("state-read");
		}else if($(this).html()==500){
			$(this).html("已办");
		}else if($(this).html()==1000){
			$(this).html("已完成");
		}else{
			$(this).html("办理中").addClass("state-proceed");
		}
	});	
}
