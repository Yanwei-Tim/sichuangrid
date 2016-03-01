<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<style type="text/css">
	.ui-tabs-panel{background:#fff!important;}
</style>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="searchMyCompletedIssue">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()" >
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</s:if>
		<pop:JugePermissionTag ename="evaluateIssue">
			<a id="evaluate" href="javascript:void(0)"><span>评价</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="topCompletedIssue">
			<a id="topCompletedIssue" href="javascript:void(0)"><span>置顶</span></a>
		</pop:JugePermissionTag>
		
		<!--fateson add  先隐藏调  -->
		<%-- 
		<pop:JugePermissionTag ename="convertToWorkingRecord">
			<a id="convertToWorkingRecord" href="javascript:void(0)"><span>转为台帐</span></a>
		</pop:JugePermissionTag>
		 --%>
		
		
	</div>
	<div style="clear: both;"></div>

	<div class="issueProgram clearfix">
		<div class="issueLeft">
			<div class="issueData"></div>
			<div id="issueMyCompletedPager" class="issuePagination"></div>
		</div>
		<div class="issueRight" id="issueMyCompletedRight"></div>
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
				$("#issueMyCompletedRight").empty().html(data);
				$.messageBox("close");
			}
		 });
	});

	$("#refresh").click(function(event){
		reloadIssue();
	});

	$("#evaluate").click(function(event){
		if (hasRowSelected() && $("#evaluate").isButtonEnabled()){
			var issueId = $(".issueList li.current").attr("id");
			$("#issueDialog").createDialog({
				width: 700,
				height: 400,
				title: "事件评价",
				url: "${path}/issues/issueManage/dispatchIssueEvaluate.action?issueEvaluate.issue.id="+issueId,
				buttons: {
			   		"评价" : function(event){
			   			$("#issueEvaluateForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可评价的数据！"});
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
		var status=<s:property value="@com.tianque.issue.state.IssueState@COMPLETE"/>
		$("#issueDialog").createDialog({
			width: 750,
			height: 400,
			title: "我的已办结事项查询-请输入查询条件",
			url: "${path}/issues/issueManage/dispatch.action?mode=search&keyId="+USER_ORG_ID+'&issue.status='+status+'&tag=completed',
			buttons: {
		   		"查询" : function(event){
		   			if($("#searchIssueForm").valid()){
		   				searchMyCompleteIssues();
			        	$(this).dialog("close");
		   			}
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	function searchMyCompleteIssues(){
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
		var pageUrl = "${path}/issues/searchIssue/searchMyCompletedIssues.action";
		var pageData = $.extend({"page":1,"rows":5,"searchIssueVo.sortField":"issueId","searchIssueVo.order":"desc","sourceType":<s:property value="@com.tianque.domain.property.IssueSourceType@TENCENT_INPUT"/>},searchIssueVo);
		$.ajax({
			async : true,
			url : pageUrl,
			data:pageData,
			success : function(data) {
				$("div.issueData").empty().html(data);
				$(".issueList li").eq(0).trigger("click");
				$("#issueMyCompletedPager").data("loadOnce",true);
				bindPager(pageUrl,pageData);
				if(0 == $(".issueList li").length){
					//$("#issueMyCompletedRight").empty();
					$("#issueMyCompletedRight").html("<div>暂无事件！</div>");
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

	$("#delete").click(function(event){
		if (hasRowSelected() && $("#delete").isButtonEnabled()){
			$.confirm({
				title:"确认删除",
				message:"该事件处理信息删除后,将无法恢复,您确认删除该事件处理信息吗?",
				width:400,
				okFunc:function(){
					removeIssueByIssueId($(".issueList li.current").attr("id"));
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可删除的数据！"});
			return;
		}
	});

	//置顶操作
	$("#topCompletedIssue").click(function(event){
		if (hasRowSelected() && $("#topCompletedIssue").isButtonEnabled()){
			$.ajax({
				url:"/issues/issueManage/topCompletedIssue.action",
				data:{
					"topIssue.issueId":$(".issueList li.current").attr("id"),
					"topIssue.issueTag":'<s:property value="@com.tianque.issue.constants.IssueTag@COMPLETED_ISSUE"/>'
				},
				success:function(responseData){
					if (responseData==true){
						$.messageBox({message:$("#topCompletedIssue").text()+"成功!"});
					    reloadIssue();
					}else{
						$.messageBox({level:'error',message:$("#topCompletedIssue").text()+"失败!"});
					}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可"+$(this).text()+"的事件！"});
			return;
		}
	});
	//转为工作台帐
	$("#convertToWorkingRecord").click(function(event){
		if (hasRowSelected() && $("#convertToWorkingRecord").isButtonEnabled()){
			var selectedId = $(".issueList li.current").attr("id");
			$("#issueDialog").createDialog({
				width: 800,
				height: 480,
				title: "转为台帐",
				url: "${path}/issues/issueManage/dispatch.action?mode=convertToWorkingRecord&keyId="+selectedId,
				buttons: {
					"保存" : function(event){
		   				$("#workingRecordForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
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
	if (hasRowSelected()){
		$("#delete").buttonEnable();
		$("#evaluate").buttonEnable();
		$("#view").buttonEnable();
		if($(".issueList li.current").attr("topState")=='<s:property value="@com.tianque.issue.domain.TopIssue@TOP" />'){
			$("#topCompletedIssue").html("<span>取消置顶</span>");
		}else{
			$("#topCompletedIssue").html("<span>置顶</span>");
		}
	}
}

function hasRowSelected(){
	if($(".issueList li.current input:checkbox").attr("checked") == "checked"){
		return true;
	}
	return false;
}

function onOrgChanged(){
	var pageUrl="${path}/issues/issueManage/findMyCompleted.action";
	var pageData= {"keyId":USER_ORG_ID,"sourceType":<s:property value="@com.tianque.domain.property.IssueSourceType@TENCENT_INPUT"/>,"page":1,"rows":5,"sidx":"issueId","sord":"desc"};
	$.ajax({
		async : true,
		url : pageUrl,
		data :pageData,
		success : function(data) {
			$("div.issueData").empty().html(data);
			$(".issueList li").eq(0).trigger("click");
			$("#issueMyCompletedPager").data("loadOnce",true);
			$("#issueCompletedListTotal").html("<lable>"+$("#issueMyCompletedRecords").val()+"</lable>");
			bindPager(pageUrl,pageData);
			if(0 == $(".issueList li").length){
				$("#issueMyCompletedRight").empty();
			}
		}
	 });
}
function bindPager(pageUrl,pageData){
	$("#issueMyCompletedPager").empty().tabPaging({
	    total:$("#issueMyCompletedRecords").attr("value"),
	    perpage:5,
	    page: 1,
	    onSelect: function(page) {
	    	if($("#issueMyCompletedPager").data("loadOnce")==true){
	    		$("#issueMyCompletedPager").data("loadOnce",false);
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