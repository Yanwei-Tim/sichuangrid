<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-corner-all" id="nav">
		
		<pop:JugePermissionTag ename="searchIssue">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		
		</div>
		
	</div>
	<div class="issueProgram clearfix">
		<div class="issueLeft">
			<div class="issueData"></div>
			<div id="pager" class="issuePagination"></div>
		</div>
		<div class="issueRight" id="issueRight"></div>
	</div>
	<div id="issueDialog"></div>
	<div id="noticeDialog"></div>
	<input type="hidden" id="issueType" value="<s:property value='#parameters.issueType[0]'/>" />
</div>

<script type="text/javascript">
var issueDialogWidth=850;
var issueDialogHeight=470;
$(function(){
	issueRightHeight();
	mouseOverOrOut();
	setButtonStatusBySelection();
	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}
	$(".issueLeft div").delegate(".issueList li","click",function(){
		if($(this).hasClass("current")){
			return false;
		}
		$(this).addClass("current").siblings().removeClass("current");
		$(".issueList li input:checkbox").attr("checked",false);
		$(this).find("input").attr("checked",true);
		setButtonStatusBySelection();
		$("#issueRight").html('<div class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>');
		$.ajax({
			async : true,
			url : "${path}/issues/issueManage/viewSubDetail.action?mode=doAction",
			data :{"keyId":$(this).attr("issueStepId")},
			success : function(data) {
				$(".issueList li.current input:checkbox").attr("checked", true);
				$("#issueRight").html(data);
				$.messageBox("close");
			}
		 });
	});

	if( $(".issueList li").length == 0 ){
		$.messageBox({message:'事件内容加载中，请稍候……'});
		$("#issueRight").html('<div class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>');
		$.ajax({
			async : true,
			url : "${path}/issues/issueManage/viewSubDetail.action?mode=doEmptyAction",
			data :{"keyId":$(this).attr("issueStepId")},
			success : function(data) {
				$("#issueRight").html(data);
				$.messageBox("close");
			}
		 });
	}


	$("#add").click(function(event){
		$("#issueDialog").createDialog({
			width:issueDialogWidth,
			height:issueDialogHeight,
			title:'新增事件处理信息',
			url:'${path}/issues/issueManage/dispatch.action?mode=add&keyId='+$("#currentOrgId").val(),
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

	$("#delete").click(function(event){
		if (hasRowSelected() && $("#delete").isButtonEnabled()){
			$.confirm({
				title:"确认删除",
				message:"该事件处理信息删除后,将无法恢复,您确认删除该事件处理信息吗?",
				width:400,
				okFunc:function(){
					removeIssue($(".issueList li.current").attr("id"));
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可删除的数据！"});
			return;
		}
	});

	$("#refresh").click(function(event){
		reloadIssue();
	});

	$("#update").click(function(event){
		if (hasRowSelected() && $("#update").isButtonEnabled()){
			var selectedId = $(".issueList li.current").attr("issueStepId");
			editIssue(selectedId);
		}else{
			$.messageBox({level:'warn',message:"没有可修改的数据！"});
			return;
		}
	});

	$("#view").click(function(event){
		if (hasRowSelected() && $("#view").isButtonEnabled()){
			var selectedId = $(".issueList li.current").attr("id");
			$("#issueDialog").createDialog({
				width: 700,
				height: 450,
				title: "查看事件详情",
				url: "${path}/issues/issueManage/dispatch.action?mode=view&keyId="+selectedId,
				buttons: {
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可查看的数据！"});
			return;
		}
	});


	$("#search").click(function(event){
		var status= <s:property value="@com.tianque.issue.state.IssueState@DEALING"/>
		$("#issueDialog").createDialog({
			width: 700,
			height: 400,
			title: "我的待办事项查询-请输入查询条件",
			url: "${path}/issues/issueManage/dispatch.action?mode=search&keyId="+$("#currentOrgId").val()+'&status='+status,
			buttons: {
		   		"查询" : function(event){
					if($("#searchIssueForm").valid()){
		   				searchIssue();
			        	$(this).dialog("close");
		   			}
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	function searchIssue(){
		setConditionImportant();
		var data=$("#searchIssueForm").serializeArray();
		var searchIssueVo={};
		for(i=0;i<data.length;i++){
			 if (searchIssueVo[data[i].name]) {
	           searchIssueVo[data[i].name]=searchIssueVo[data[i].name]+","+data[i].value;
			} else {
	            searchIssueVo[data[i].name] = data[i].value;
			}
		}
		var pageUrl = "${path}/issues/searchIssue/searchIssue.action";
		var pageData = $.extend({"page":1,"rows":5,"searchIssueVo.sortField":"issueId","searchIssueVo.order":"desc"},searchIssueVo);
		$.ajax({
			async : true,
			url : pageUrl,
			data: pageData,
			success : function(data) {
				$("div.issueData").empty().html(data);
				$(".issueList li").eq(0).trigger("click");
				$("#pager").data("loadOnce",true);
				bindPager(pageUrl,pageData);
				if(0 == $(".issueList li").length){
					$("#issueRight").empty();
				}
			}
		 });
	}

	function setConditionImportant(){
		//是否重大事件
		var conditionImportant = $("#conditionImportant").val();
		if($("#conditionImportant").val()==""){
			$("#conditionImportant").attr("disabled","disabled");
		}
	}

	$("#normalIssue").click(function(event){
		if (hasRowSelected() && $("#normalIssue").isButtonEnabled()){
			var	selectedId = $(".issueList li.current").attr("issueStepId");
			superviseIssue(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@NORMAL_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#yellowCardIssue").click(function(event){
		if (hasRowSelected() && $("#yellowCardIssue").isButtonEnabled()){
			var	selectedId = $(".issueList li.current").attr("issueStepId");
			superviseIssue(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@YELLOW_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#redCardIssue").click(function(event){
		if (hasRowSelected() && $("#redCardIssue").isButtonEnabled()){
			var	selectedId = $(".issueList li.current").attr("issueStepId");
			superviseIssue(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@RED_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#cancleSupervise").click(function(event){
		if (hasRowSelected() && $("#cancleSupervise").isButtonEnabled()){
			var	selectedId = $(".issueList li.current").attr("issueStepId");
			$.confirm({
				title:"系统提示",
				message:"是否确定要取消对该事件处理的督办!",
				width:400,
				okFunc:function(){
					cancelSupervise(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_SUPERVISE.code"/,'<pop:token ajax="true" />'>);
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可取消督办的数据！"});
			return;
		}
	});

	$("#command").click(function(event){
		if (hasRowSelected() && $("#command").isButtonEnabled()){
			var	selectedId = $(".issueList li.current").attr("issueStepId");
			$("#issueDialog").createDialog({
				width: 600,
				height: 400,
				title: '领导批示',
				url:'${path}/issues/issueManage/dispatchDeal.action?dealCode=<s:property value="@com.tianque.issue.state.IssueOperate@INSTRUCT.code"/>&keyId='+selectedId,
				buttons: {
			   		"确定" : function(event){
		   				$("#singleContentDealForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可作领导批示的数据！"});
			return;
		}
	});


	$("#historicalIssue").click(function(event){
		if (hasRowSelected() && $("#historicalIssue").isButtonEnabled()){
			$.confirm({
				title:"系统提示",
				message:"是否将该事件设置为历史遗留?",
				width:400,
				okFunc:function(){
					var	selectedId = $(".issueList li.current").attr("issueStepId");
					settingIssueHistorical(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@HISTORIC.code"/>,'<pop:token ajax="true" />');
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可作历史遗留的数据！"});
			return;
		}
	});

	//置顶操作
	$("#topIssue").click(function(event){
		if (hasRowSelected() && $("#topIssue").isButtonEnabled()){
			$.ajax({
				url:"/issues/issueManage/topNeedDoIssue.action",
				data:{
					"topIssue.issueId":$(".issueList li.current").attr("id"),
					"topIssue.issueTag":'<s:property value="@com.tianque.issue.constants.IssueTag@NEEDDO_ISSUE"/>'
				},
				success:function(responseData){
					if (responseData==true){
						$.messageBox({message:$("#topIssue").text()+"成功!"});
					    reloadIssue();
					}else{
						$.messageBox({level:'error',message:$("#topIssue").text()+"失败!"});
					}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可"+$(this).text()+"的事件！"});
			return;
		}
	});	


});

function setButtonStatusBySelection(){
	if(hasRowSelected()){
		$("#update").buttonEnable();
		$("#delete").buttonEnable();
		$("#command").buttonEnable();
		$("#view").buttonEnable();
		var	selectedId = $(".issueList li.current").attr("issueStepId");
		resetUrgentButton(hasRowSelected(),selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@URGENT.code"/>,<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_URGENT.code"/>,'<pop:token ajax="true" />');
		setSuperviseButtons();
		setHistoricalIssueButton();
		setTopButton();
	}
}

function setTopButton(){
	if($(".issueList li.current").attr("topState")=='<s:property value="@com.tianque.issue.domain.TopIssue@TOP" />'){
		$("#topIssue").html("<span>取消置顶</span>");
	}else{
		$("#topIssue").html("<span>置顶</span>");
	}
}

function setHistoricalIssueButton(){
	if('待受理' == $(".issueList li.current a.handleLink").html()){
		$("#historicalIssue").buttonDisable();
	}else{
		$("#historicalIssue").buttonEnable();
	}
}

function hasRowSelected(){
	if($(".issueList li.current input:checkbox").attr("checked") == "checked"){
		return true;
	}
	return false;
}

function dealIssue(issueStepId){
	if(issueStepId==null){
 		return;
	}
	$("#issueDialog").createDialog({
		width:680,
		height:440,
		title:'办理',
		url:'${path}/issues/issueManage/dispatchDeal.action?mode=deal&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function simpleDealIssue(issueStepId,dealType){
	if(issueStepId==null){
 		return;
	}
	var dealTitil = "受理";
	if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@READ.code'/>){
		dealTitil = "阅读";
	}else if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@REPORT_TO.code'/>){
		dealTitil = "上报指挥中心";
	}
	$("#issueDialog").createDialog({
		width:350,
		height:200,
		title:dealTitil,
		url:'${path}/issues/issueManage/dispatchDeal.action?dealCode='+dealType+'&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function onOrgChanged(){
	var pageUrl = "${path}/issues/issueManage/findMyNeedDo.action";
	var pageData = {"keyId":USER_ORG_ID,"issueType":$("#issueType").val(),"page":1,"rows":5,"sidx":"issueId","sord":"desc"};
	$.ajax({
		async : true,
		url : pageUrl,
		data :pageData,
		success : function(data) {
			$("div.issueData").empty().html(data);
			$(".issueList li").eq(0).trigger("click");
			$("#pager").data("loadOnce",true);
			bindPager(pageUrl,pageData);
			if(0 == $(".issueList li").length){
				$("#issueRight").empty();
			}
		}
	 });

}

function bindPager(pageUrl,pageData){
	$("#pager").empty().tabPaging({
	    total:$("#records").attr("value"),
	    perpage:5,
	    page: 1,
	    onSelect: function(page) {
	    	if($("#pager").data("loadOnce")==true){
	    		$("#pager").data("loadOnce",false);
				return false;
			}
	    	pageData.page=page;
	    	$.ajax({
				async : true,
				url : pageUrl,
				data :pageData,
				success : function(data) {
					$("div.issueData").empty().html(data);
					$(".issueList li").eq(0).trigger("click");
				}
			 });
	    }
	});
}

function reloadIssue(){
	onOrgChanged();
}


</script>


