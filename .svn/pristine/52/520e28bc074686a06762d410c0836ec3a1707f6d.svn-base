
//绑定按钮文本
function setUrgentButtons(dealCode,cancelCode,token){
	if($(".issueList li.current").attr("urgent")=='1'){
		$("#urgent").html('<span>取消加急</span>');
		$("#urgent").attr("id","cancelUrgent");
		$("#cancelUrgent").unbind('click').bind('click',function(){
				bindCancleUrgent(cancelCode,token);
			});
		
	}else{
		$("#cancelUrgent").html('<span>加急</span>');
		$("#cancelUrgent").attr("id","urgent");
		$("#urgent").unbind('click').bind('click',function(){
			bindUrgent(dealCode);
		});
	}
}

//绑定加急事件
function bindUrgent(dealCode){
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	if (hasRowSelected()){
		bindUrgentById(dealCode,selectedId)
	}else{
		$.messageBox({level:'warn',message:"没有可加急的事件！"});
	}
}
//绑定加急事件
function bindUrgentById(dealCode,selectedId){
	if (isNullObject(selectedId)){
		$.messageBox({level:'warn',message:"没有可加急的事件！"});
	}else{
			$("#issueDialog").createDialog({
				width:600,
				height:400,
				title:'加急',
				url:'/issues/issueManage/dispatchDeal.action?dealCode='+dealCode+'&keyId='+selectedId,
				buttons: {
					"确定" : function(event){
						$("#singleContentDealForm").submit();
					  $("#urgent").find("span").html("取消加急");
					  var urgenButton=$("#urgent").attr("id","urgent");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	}
}
//绑定取消加急事件
function bindCancleUrgent(cancelCode,token){
	if (hasRowSelected()){
		var	keyId = $(".issueList li.current").attr("issueStepId");
		bindCancleUrgentById(keyId,cancelCode,token);
	}else{
		$.messageBox({level:'warn',message:"没有可取消加急的事件！"});
	}
}

//绑定取消加急事件
function bindCancleUrgentById(keyId,cancelCode,token){
	if (!isNullObject(keyId)){
		$.confirm({
			title:"系统提示",
			message:"是否确定要取消对该事件处理的加急!",
			width:400,
			okFunc:function(){
				$.ajax({
					url:"/issues/issueManage/dealIssue.action",
					data:{
						"keyId":keyId,
						"dealCode":cancelCode,
						"struts.token":token
					},
					success:function(data){
						if (data != null && data.issueStepId){
							$.messageBox({message:"已经成功取消该事件处理的加急!"});
							$("#urgent").find("span").html("加急");
							var urgenButton=$("#cancelUrgent").attr("id","urgent");
						}else{
							$.messageBox({message:data,level:"error"});
						}
						reloadIssue();
					}
				});
			}
		});
	}
}


/********************************/
function setUrgentButton(){
	resetUrgentButtonState();
}

//设置加急/取消加急按钮
function resetUrgentButton(rowSelected,keyId,urgentCode,cancelCode,token){
	if (rowSelected ){
		if (isNullObject(keyId)){
			return ;
		}else{
			if(isUrgented($("#issueList"))){
				bindCancelIssueUrgentEvent(keyId,cancelCode,token);
			}else{
				bindUrgentIssueEvent(keyId,urgentCode);
			}
		}
	}else{
		$.messageBox({level:'warn',message:"请选择一条事件后再操作！"});
	}
}

//加急
function bindUrgentIssueEvent(keyId,urgentCode){
	var urgenButton=$("#urgent");
	if (urgenButton==null || typeof(urgenButton)=="undefined"){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-jiaji"></strong>加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		$("#issueDialog").createDialog({
			width:600,
			height:400,
			title:'加急',
			url:'/issues/issueManage/dispatchDeal.action?dealCode='+urgentCode+'&keyId='+keyId,
			buttons: {
				"确定" : function(event){
					$("#singleContentDealForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
}

//取消加急
function bindCancelIssueUrgentEvent(keyId,cancelCode,token){
	var urgenButton=$("#urgent");
	if (isNullObject(urgenButton)){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-qxjj"></strong>取消加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		if (!isNullObject(keyId)){
			$.confirm({
				title:"系统提示",
				message:"是否确定要取消对该事件处理的加急!",
				width:400,
				okFunc:function(){
					$.ajax({
						url:"/issues/issueManage/dealIssue.action",
						data:{
							"keyId":keyId,
							"dealCode":cancelCode,
							"struts.token":token
						},
						success:function(data){
							if (data != null && data.issueStepId){
								$.messageBox({message:"已经成功取消该事件处理的加急!"});
							}else{
								$.messageBox({message:data,level:"error"});
							}
							reloadIssue();
						}
					});
				}
			});
		}
	});
}

function resetUrgentButtonState(){
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	if (isNullObject(selectedId)){
		$("#urgent").buttonDisable();
	}else{
		$("#urgent").buttonEnable();
		if(isUrgented($("#issueList"))){
			bindCancelUrgentEvent();
		}else{
			bindUrgentEvent();
		}
	}
}

function isUrgented(listGrid){
	var selectedIssue = {urgent:$(".issueList li.current").attr("urgent")};
	return selectedIssue.urgent == 1 || (selectedIssue.urgent!=undefined && selectedIssue.urgent.indexOf("immediate.gif")!=-1);
}

function bindCancelUrgentEvent(){
	var urgenButton=$("#urgent");
	if (isNullObject(urgenButton)){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-qxjj"></strong>取消加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		var	selectedId = $(".issueList li.current").attr("issueStepId");
		if (isNullObject(selectedId)){
			return;
		}
		$.confirm({
			title:"系统提示",
			message:"是否确定要取消对该事件处理的加急!",
			width:400,
			okFunc:cancelUrgent
		});
	});
}

function bindUrgentEvent(){
	var urgenButton=$("#urgent");
	if (urgenButton==null || typeof(urgenButton)=="undefined"){
		return;
	}
	urgenButton.html('<span><strong class="ui-ico-jiaji"></strong>加急</span>');
	urgenButton.unbind("click");
	urgenButton.click(function(event){
		urgentIssue();
	});
}

function cancelUrgent(){
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	if (isNullObject(selectedId) || $("#urgent").attr("disabled")=="true"){
		return;
	}
	$.ajax({
		url:"/issue/issueManage/cancelUrgentIssue.action",
		data:{
			"step.id":selectedId
		},
		success:function(data){
			if (data.issueLogId){
			    $.messageBox({message:"已经成功取消该事件处理的加急!"});
			    resetUrgentButtonState();
			}else{
				$.messageBox({message:data,level:"error"});
				resetUrgentButtonState();
			}
			reloadIssue();
		}
	});
}

function urgentIssue(){
	var	selectedId = $(".issueList li.current").attr("issueStepId");
	if (isNullObject(selectedId) || $("#urgent").attr("disabled")=="true"){
		return;
	}
	$("#issueDialog").createDialog({
		width:690,
		height:430,
		title:'加急',
		url:'/issue/issueManage/dispatch.action?mode=urgent&step.id='+selectedId,
		buttons: {
			"确定" : function(event){
				$("#urgentIssueForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}