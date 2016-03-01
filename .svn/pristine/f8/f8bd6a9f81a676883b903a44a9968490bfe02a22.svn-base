<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	.ui-tabs-panel{background:#fff!important;}
</style>
<div class="content">
	<div class="ui_issueSearch">
		<form>
		<!--  <input type="button" id="globalOrgBtn" value="中国" ></input>
    <span class="lineBetween firstDOM"></span>-->
			<!-- <input type="text" class="issueSearch" value="请输入关键字"/>
			<a href="javascript:;" class="searchBtn">search</a> -->
		</form>
	</div>
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="searchDoneMyIssue">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
			<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="publicltyCassDoneMyIssue">
			<a id="publicltyCass" href="javascript:void(0)"><span>宣传案例</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="topDoneIssue">
			<a id="topDoneIssue" href="javascript:void(0)"><span>置顶</span></a>
		</pop:JugePermissionTag>
	</div>
	<div style="clear: both;"></div>

	<div class="issueProgram clearfix">
		<div class="issueLeft">
			<div class="issueData"></div>
			<div id="issueDonePager" class="issuePagination"></div>
		</div>
		<div class="issueRight" id="issueDoneRight"></div>
	</div>

	<div id="issueDialog"></div>
	<div id="noticeDialog"></div>
</div>

<script type="text/javascript">
$(function(){
	issueRightHeight();
	mouseOverOrOut();
	setButtonStatusBySelection();
	if($("#currentOrgId").val()!= null){
		onOrgChanged();
	}
	$("#refresh").click(function(event){
		reloadIssueDone();
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
//	$("#evaluate").click(function(event){
//		if (hasRowSelected() && $("#evaluate").isButtonEnabled()){
//			if('已完成' != $(".issueList li.current a.handleLink").html()){
//				$.messageBox({level:'warn',message:"该事件未办结，不能评价！"});
	//			return;
//			}
//			var issueId = $(".issueList li.current").attr("id");
	//		$("#issueDialog").createDialog({
	//			width: 700,
	//			height: 400,
	//			title: "事件评价",
	//			url: "${path}/issues/issueManage/dispatchIssueEvaluate.action?issueEvaluate.issue.id="+issueId,
	//			buttons: {
	//		   		"评价" : function(event){
	//		   			$("#issueEvaluateForm").submit();
	//	   			},
	//		   		"关闭" : function(){
	//		        	$(this).dialog("close");
	//		   		}
	//			}
	//		});
	//	}else{
	//		$.messageBox({level:'warn',message:"没有可评价的数据！"});
	//		return;
	//	}
//	});
	$("#search").click(function(event){
		$("#issueDialog").createDialog({
			width: 750,
			height: 400,
			title: "我的已办事项查询-请输入查询条件",
			url: "${path}/issues/issueManage/dispatch.action?mode=search&keyId="+USER_ORG_ID,
			buttons: {
		   		"查询" : function(event){
		   			if($("#searchIssueForm").valid()){
		   				searchDoneIssues();
			        	$(this).dialog("close");
		   			}
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	function searchDoneIssues(){
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
	    var pageUrl = "${path}/issues/searchIssue/searchDoneIssues.action";
	    var pageData = $.extend({"page":1,"rows":5,"searchIssueVo.sortField":"issueId","searchIssueVo.order":"desc","sourceType":<s:property value="@com.tianque.domain.property.IssueSourceType@TENCENT_INPUT"/>},searchIssueVo);
		$.ajax({
			async : true,
			url : pageUrl,
			data:pageData,
			success : function(data) {
				$("div.issueData").empty().html(data);
				$(".issueList li").eq(0).trigger("click");
				$("#issueDonePager").data("loadOnce",true);
				$("#issueDoneListTotal").html("<lable>"+$("#issueDoneRecords").val()+"</lable>");
				bindPager(pageUrl,pageData);
				if(0 == $(".issueList li").length){
					//$("#issueDoneRight").empty();
					$("#issueDoneRight").html("<div>暂无事件！</div>");
				}
			}
		 });
	}

	function setConditionImportant(){
		//是否重点关注事件
		var conditionImportant = $("#conditionImportant").val();
		if($("#conditionImportant").val()==""){
			$("#conditionImportant").attr("disabled","disabled");
		}
	}

	$("#publicltyCass").click(function(event){
		if (!hasRowSelected()){
			$.messageBox({level:'warn',message:"没有可设为宣传案例的事件！"});
			return;
		}
		if($(".issueList li.current").attr("publicltycass")==1){
			$.messageBox({message:"该事件处理已经被设为宣传案例!",level:"warn"});
			return;
		}
		if(!canDoPublicltyCass()){
			$.messageBox({level:'warn',message:"该事件未完成不能设为宣传案例！"});
			return;
		}
		$.confirm({
			title:"系统提示",
			message:"是否要将该事件处理设置为宣传案例?",
			width:400,
			okFunc:publicltyCass
		});
		
	});


	$(".issueLeft div").delegate(".issueList li","click",function(){
		$(this).addClass("current").siblings().removeClass("current");
		$(".issueList li input:checkbox").attr("checked",false);
		$(".issueList li.current input:checkbox").attr("checked",true);
		setButtonStatusBySelection();
		$.messageBox({message:'事件内容加载中，请稍候……'});
		$.ajax({
			async : true,
			url : "${path}/issues/issueManage/viewSubDetailByIssueId.action",
			data :{"keyId":$(this).attr("id")},
			success : function(data) {
				$("#issueDoneRight").empty().html(data);
				$.messageBox("close");
			}
		 });
	});

	//置顶操作
	$("#topDoneIssue").click(function(event){
		if (hasRowSelected() && $("#topDoneIssue").isButtonEnabled()){
			$.ajax({
				url:"/issues/issueManage/topDoneIssue.action",
				data:{
					"topIssue.issueId":$(".issueList li.current").attr("id"),
					"topIssue.issueTag":'<s:property value="@com.tianque.issue.constants.IssueTag@DONE_ISSUE"/>'
				},
				success:function(responseData){
					if (responseData==true){
						$.messageBox({message:$("#topDoneIssue").text()+"成功!"});
					    reloadIssue();
					}else{
						$.messageBox({level:'error',message:$("#topDoneIssue").text()+"失败!"});
					}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可"+$(this).text()+"的事件！"});
			return;
		}
	});

});



function canDoPublicltyCass(){
	if($(".issueList li.current input:checkbox").attr("checked") != "checked"){
		return false;
	}else{
		return '已完成' == $(".issueList li.current a.handleLink").html();
	}
}

function setButtonStatusBySelection(){
	if (hasRowSelected()){
		setButtonStatusBySelectedIssueState();
	}
}


function hasRowSelected(){
	if($(".issueList li.current input:checkbox").attr("checked") == "checked"){
		return true;
	}
	return false;
}

function setButtonStatusBySelectedIssueState(){
	$("#view").buttonEnable();
	$("#evaluate").buttonEnable();
	
	if($(".issueList li.current").attr("topState")=='<s:property value="@com.tianque.issue.domain.TopIssue@TOP" />'){
		$("#topDoneIssue").html("<span>取消置顶</span>");
	}else{
		$("#topDoneIssue").html("<span>置顶</span>");
	}
}

function publicltyCass(){
	var selectedId = $(".issueList li.current").attr("id");
	$.ajax({
		url:'${path}/issues/issueManage/publicltyCass.action',
		data:{
			"keyId":selectedId
		},
		success:function(data){
			if (data.issueId){
				$.messageBox({message:"该事件处理已设置为宣传案例!"});
				reloadIssueDone();
			}else{
			 	$.messageBox({message:data});
			}
		}
	});
}
function onOrgChanged(){
	var pageUrl = "${path}/issues/issueManage/findMyDone.action";
	var pageData = {"keyId":USER_ORG_ID,"sourceType":<s:property value="@com.tianque.domain.property.IssueSourceType@TENCENT_INPUT"/>,"page":1,"rows":5,"sidx":"issueId","sord":"desc"};
	$.ajax({
		async : true,
		url : pageUrl,
		data :pageData,
		success : function(data) {
			$("div.issueData").empty().html(data);
			$(".issueList li").eq(0).trigger("click");
			$("#issueDonePager").data("loadOnce",true);
			$("#issueDoneListTotal").html("<lable>"+$("#issueDoneRecords").val()+"</lable>");
			bindPager(pageUrl,pageData);
			if(0 == $(".issueList li").length){
				$("#issueDoneRight").empty();
			}
		}
	 });
}
function bindPager(pageUrl,pageData){
	$("#issueDonePager").empty().tabPaging({
	    total:$("#issueDoneRecords").attr("value"),
	    perpage:5,
	    page: 1,
	    onSelect: function(page) {
	    	if($("#issueDonePager").data("loadOnce")==true){
	    		$("#issueDonePager").data("loadOnce",false);
				return false;
			}
	    	pageData.page = page;
	    	$.ajax({
				async : true,
				url : pageUrl,
				data : pageData,
				success : function(data) {
					$("div.issueData").empty().html(data);
					$(".issueList li").eq(0).trigger("click");
				}
			 });
	    }
	});
}

function reloadIssueDone(){
	onOrgChanged();
}
</script>