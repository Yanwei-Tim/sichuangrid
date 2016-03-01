<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<%@ include file="/issue/historical/docking.jsp"%>

<div class="content">
	<div class="ui-corner-all" id="nav" style="z-index: 1001">
		<pop:JugePermissionTag ename="updateIssue">
			<a id="update" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteIssue">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchIssue">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
		<pop:JugePermissionTag ename="normalIssue,yellowCardIssue,redCardIssue,cancleSuperviseIssue">
		<a href="javascript:;" class="nav-dropdownBtn" ><span>督办</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="normalIssue">
				<a id="normalIssue" href="javascript:void(0)"><span>普通督办</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="yellowCardIssue">
				<a id="yellowCardIssue" href="javascript:void(0)"><span>黄牌督办</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="redCardIssue">
				<a id="redCardIssue" href="javascript:void(0)"><span>红牌督办</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="cancleSuperviseIssue">
				<a id="cancleSupervise" href="javascript:void(0)"><span>取消督办</span></a>
			</pop:JugePermissionTag>
		</div>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="urgentIssue">
			<a id="urgent" href="javascript:void(0)"><span>加急</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="commandIssue">
			<a id="command" href="javascript:void(0)"><span>领导批示</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="historicalIssue">
			<a id="historicalIssue" href="javascript:void(0)"><span>历史遗留</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="topNeedDoIssue">
			<a id="topIssue" href="javascript:void(0)"><span>置顶</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="printIssue">
			<a id="printIssue" href="javascript:void(0)"><span>打印</span></a>
		</pop:JugePermissionTag>
		<strong>${issue.subject } </strong>
			<s:if test="!'view'.equals(mode)">
    			<a href="javascript:void(0)" class="jqsubgridButton" id="reportToTop" style="visibility: hidden;"><span class="yijian">上报指挥中心</span></a>
    			<a href="javascript:void(0)" class="jqsubgridButton" id="readTop" style="visibility: hidden;"><span class="yijian">阅读</span></a>
        		<a href="javascript:void(0)" class="jqsubgridButton" id="conceptTop" style="visibility: hidden;"><span class="yijian">受理</span></a>
       		    <a href="javascript:void(0)" class="jqsubgridButton" id="dealTop" style="visibility:hidden;"><span class="yijian">办理</span></a>
		  </s:if>
				
	</div>
	<div class="issueProgram clearfix">
		<div class="issueLeft">
			<div class="issueData"></div>
			<div id="pager" class="issuePagination">
			</div>
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
	setButton();
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
		setButton();
		$("#issueRight").html('<div class="tipsbox"><p id="loading" class="loadbox"><img src="/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>');
		$.ajax({
			async : true,
			url : "${path}/issues/issueManage/viewSubDetail.action?mode=doAction&keyType=myIssue",
			data :{"keyId":$(this).attr("issueStepId")},
			success : function(data) {
				$("#issueRight").html(data);
				$.messageBox("close");
			}
		 });
	});

	/*if( $(".issueList li").length == 0 ){
		$("#issueRight").html("<div>暂无事件！</div>");
	}*/


	$("#add").click(function(event){
		$("#issueDialog").createDialog({
			width:840,
			height:570,
			title:'新增事件处理信息',
			url:'${path}/issues/issue4yiwuManage/dispatch.action?dailogName=issueDialog&mode=add&keyId='+$("#currentOrgId").val(),
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
		if (hasRowSelected()){
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
		if (hasRowSelected()){
			var selectedId = $(".issueList li.current").attr("issueStepId");
			$("#issueDialog").createDialog({
				width:840,
				height:570,
				title: '修改事件处理信息',
				url:'/issues/issue4yiwuManage/dispatch.action?dailogName=issueDialog&mode=edit&keyId='+selectedId,
				buttons: {
			   		"保存" : function(event){
						$("#issueMaintainForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可修改的数据！"});
			return;
		}
	});

	$("#view").click(function(event){
		if (hasRowSelected()){
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
			width: 750,
			height: 400,
			title: "我的待办事项查询-请输入查询条件",
			url: "${path}/issues/issue4yiwuManage/dispatch.action?mode=search&keyId="+USER_ORG_ID,
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
		var pageData = $.extend({"page":1,"rows":5,"searchIssueVo.sortField":"issueId","searchIssueVo.order":"desc","sourceType":<s:property value="@com.tianque.domain.property.IssueSourceType@TENCENT_INPUT"/>},searchIssueVo);
		$.ajax({
			async : true,
			url : pageUrl,
			data: pageData,
			success : function(data) {
				$("div.issueData").empty().html(data);
				$(".issueList li").eq(0).trigger("click");
				$("#pager").data("loadOnce",true);
				$("#issueListTotal").html("<lable>"+$("#records").val()+"</lable>");
				bindPager(pageUrl,pageData);
				if(0 == $(".issueList li").length){
					$("#issueRight").html("<div>暂无事件！</div>");
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

	$("#normalIssue").click(function(event){
		if (hasRowSelected()){
			var selectedIssue = $(".issueList li.current");
			var supervisionstate = selectedIssue.attr("supervisionstate");
			if(supervisionstate >= <s:property value="@com.tianque.issue.state.IssueState@NORMAL_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件已被普通督办或正处于更高级别的督办中！"});
				return;
			}
			var	selectedId = selectedIssue.attr("issueStepId");
			superviseIssueForYiWu(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@NORMAL_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#yellowCardIssue").click(function(event){
		if (hasRowSelected()){
			var selectedIssue = $(".issueList li.current");
			var supervisionstate = selectedIssue.attr("supervisionstate");
			if(supervisionstate >= <s:property value="@com.tianque.issue.state.IssueState@YELLOW_CARD_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件已被黄牌督办或正处于更高级别的督办中！"});
				return;
			}
			var	selectedId = selectedIssue.attr("issueStepId");
			superviseIssueForYiWu(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@YELLOW_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#redCardIssue").click(function(event){
		if (hasRowSelected()){
			var selectedIssue = $(".issueList li.current");
			var supervisionstate = selectedIssue.attr("supervisionstate");
			if(supervisionstate == <s:property value="@com.tianque.issue.state.IssueState@RED_CARD_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件已被红牌督办！"});
				return;
			}
			var	selectedId = selectedIssue.attr("issueStepId");
			superviseIssueForYiWu(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@RED_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#cancleSupervise").click(function(event){
		if (hasRowSelected() ){
			var selectedIssue = $(".issueList li.current");
			var supervisionstate = selectedIssue.attr("supervisionstate");
			if(supervisionstate == <s:property value="@com.tianque.issue.state.IssueState@NO_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件未被督办！"});
				return;
			}
			var	selectedId = selectedIssue.attr("issueStepId");
			$.confirm({
				title:"系统提示",
				message:"是否确定要取消对该事件处理的督办!",
				width:400,
				okFunc:function(){
					cancelSuperviseForYiWu(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_SUPERVISE.code"/>,'<pop:token ajax="true" />');
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
		if (hasRowSelected()){
			if('待受理' == $(".issueList li.current a.handleLink").html()){
				$.messageBox({level:'warn',message:"该事件还未受理，不能设为历史遗留！"});
				return;
			}
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
		if (hasRowSelected()){
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

	//加急事件
	$("#urgent").click(function(event){
		bindUrgent(<s:property value="@com.tianque.issue.state.IssueOperate@URGENT.code"/>);
	});

	//取消加急事件
	$("#cancelUrgent").click(function(){
			bindCancleUrgent(<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_URGENT.code"/>,'<pop:token ajax="true" />');
		});
	//打印
	$("#printIssue").click(function(event){
		if(!hasRowSelected()){
			$.messageBox({level:'warn',message:"没有可作打印的数据！"});
			return ;
		}
		var selectedId = $(".issueList li.current").attr("issueStepId");
		$("#issueDialog").createDialog({
			width: 900,
			height: 600,
			title: '打印派单',
			url:'${path}/issues/issueManage/viewSubDetail.action?mode=print&keyType=myIssue&keyId='+selectedId,
			buttons: {
		   		"打印" : function(event){
					$("#issueAccordingPrint").printArea();
	   			},
	   			"导出word" : function(event){
					exportWord();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	
});

function setButton(){
	setSuperviseButtons();
	setTopButton();
	setUrgentButtons(<s:property value="@com.tianque.issue.state.IssueOperate@URGENT.code"/>,<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_URGENT.code"/>,'<pop:token ajax="true" />');
}
function exportWord(){
	if(!hasRowSelected()){
		$.messageBox({level:'warn',message:"没有可作打印的数据！"});
		return ;
	}
	var selectedId = $(".issueList li.current").attr("issueStepId");
	this.location="${path}/issues/issueManage/viewSubDetail.action?mode=printWord&keyType=myIssue&keyId="+selectedId;
}
function setTopButton(){
	if($(".issueList li.current").attr("topState")=='<s:property value="@com.tianque.issue.domain.TopIssue@TOP" />'){
		$("#topIssue").html("<span>取消置顶</span>");
	}else{
		$("#topIssue").html("<span>置顶</span>");
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
	var issuesKeyId = $(".issueList li.current").attr("id");
	$("#issueDialog").createDialog({
		width:720,
		height:550,
		title:'办理',
		url:'${path}/issues/issueManage/dispatchDeal.action?mode=deal&keyId='+issueStepId+'&issuesKeyId='+issuesKeyId,
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
	var pageData = {"keyId":USER_ORG_ID,"sourceType":<s:property value="@com.tianque.domain.property.IssueSourceType@TENCENT_INPUT"/>,"issueType":$("#issueType").val(),"page":1,"rows":5,"sidx":"issueId","sord":"desc"};
	$.ajax({
		async : true,
		url : pageUrl,
		data :pageData,
		success : function(data) {
			$("div.issueData").empty().html(data);
			$(".issueList li").eq(0).trigger("click");
			$("#pager").data("loadOnce",true);
			$("#issueListTotal").html("<lable>"+$("#records").val()+"</lable>");
			bindPager(pageUrl,pageData);
			if(0 == $(".issueList li").length){
				$("#issueRight").html("<div>暂无事件！</div>");
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


