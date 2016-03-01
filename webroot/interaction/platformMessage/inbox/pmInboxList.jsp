<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-cornRr-all" id="nav">
	<div style="float: left;">
		<select id="inboxPltformMessageType" class="basic-input">
			<option  value="" selected>全部</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@GENERAL_MESSAGE" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@GENERAL_MESSAGE_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SYSTEM_MESSAGE" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SYSTEM_MESSAGE_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NEED_DO_ISSUE_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NEED_DO_ISSUE_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SERIOUS_ISSUE_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SERIOUS_ISSUE_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ISSUE_FEEDBACK_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ISSUE_FEEDBACK_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@DAILYDIRECTORY_START_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@DAILYDIRECTORY_START_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_REPORT_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_REPORT_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_RUSHTO_REPORT_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_RUSHTO_REPORT_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_BACK_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@STATED_REPORT_BACK_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_REPORT_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_REPORT_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_BACK_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@UN_STATED_REPORT_BACK_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SIGN_FILE_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SIGN_FILE_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SHARED_FILE_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@SHARED_FILE_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_REPORT_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_REPORT_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_RUSHTO_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_RUSHTO_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_BACK_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@EVALUATE_BACK_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@RECTIFICATIVE_PERSON_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@RECTIFICATIVE_PERSON_REMIND_DisplayName" />
			</option>
			
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NURTURES_WOMEN_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@NURTURES_WOMEN_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@FLOATINGPOPULATION_PEOPLE_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@FLOATINGPOPULATION_PEOPLE_REMIND_DisplayName" />
			</option>
			
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@POSITIVEINFO_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@POSITIVEINFO_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@IDLEYOUTH_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@IDLEYOUTH_REMIND_DisplayName" />
			</option>
			<option  value="{'searchPlatformMessageVo.messageType':<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ELDERLY_PEOPLE_REMIND" />}">
				<s:property value="@com.tianque.platformMessage.constants.PlatformMessageType@ELDERLY_PEOPLE_REMIND_DisplayName" />
			</option>
		</select>
	</div>
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
			<input class="basic-input"  value="请输入标题或内容" id="fastSearchCondition"  
			class="searchtxt" onblur="value=(this.value=='')?'请输入标题或内容':this.value;" 
			onfocus="value=(this.value=='请输入标题或内容')?'':this.value;"/>
			<button id="clearSearchCondition" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	</div>
	<pop:JugePermissionTag ename="searchPmInbox">
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
	</pop:JugePermissionTag>
	<span class="lineBetween "></span>
	<pop:JugePermissionTag ename="restorePmInbox">
		<a id="reply" href="javascript:void(0)"><span>回复</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="forwardPmInbox">
		<a id="forward" href="javascript:void(0)"><span>转发</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deletePmInbox">
		<a id="delete" href="javascript:void(0)"><span>删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteAllPmInbox">
		<a id="deleteAll" href="javascript:void(0)"><span>批量删除</span></a>
	</pop:JugePermissionTag>
	<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
	<div class="News_tips" style="position:absolute;right:20px;top:10px;z-index:3;"><div class="News_tips_click"><img src="${resource_path}/resource/images/messageRemind.gif"  ><span style="color:red;">注意</span></div>
		<div class="News_tips_main" style="width:200px;height:150px;background:#ABD0FF;position:absolute;right:10px;top:20px;display:none;">
			【注意】：<br/>
			1、系统消息有效期为30天，30天后将自动删除超期消息,请及时查看下载；<br/>
			2、个人消息有效期为30天，30天后将自动删除超期消息,请及时查看下载；<br/>
	</div>
	</div>
</div>

<div class="issueProgram clearfix" id="issHeight">
<div class="issueProgram_Tab">
	<ul class="issueProgram_Tab_list">
	<li><a href="javascript:;" onclick="getInboxPlatformMessageList(1,15)"><span class="messageType">系统消息</span></a></li>
	<li><a href="javascript:;" onclick="getInboxPlatformSelfMessageList(1,15)"><span class="messageType">个人消息</span></a></li>
	</ul>
	<div class="issueLeft issueProgram_Tab_show"  style="height:480px;">
		<%--系统消息列表 --%>
		<div class="issueData" id="inboxPlatformMessageList" ></div>
		<%--消息列表分页区 --%>
		<div id="pager" class="issuePagination" style="display:block;height:25px;background:#ccc;line-height:25px;font-size:10px;">
				<a id="upperPage"  href="javascript:;"><<</a>
				<input  id="currentPage" type="text" style="width:20px;height:15px;" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
				<span id="totalPage"></span>
				<a id="nextPage"  href="javascript:;">>></a>
				<select id="nowPage" style="width:55px;height:20px;" >
				<option value="15" selected>15</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="50">50</option>
				<option value="80">80</option>
				<option value="100">100</option>
				</select>
				<span id="allPage">共0页</span>
				<span style="margin-left:2px;" id="totalDataNums">共0条</span>
		</div>
	</div>
	<div class="issueLeft issueProgram_Tab_show" style="height:480px;">
		<%--个人消息列表 --%>
		<div class="issueData" id="inboxPlatformSelfMessageList"></div>
		<%--消息列表分页区 --%>
		<div id="pagerSelf" class="issuePagination" style="display:block;height:25px;background:#ccc;line-height:25px;font-size:10px;">
				<a id="upperPageSelf"  href="javascript:;"><<</a>
				<input  id="currentPageSelf" type="text" style="width:20px;height:15px;" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
				<span id="totalPageSelf"></span>
				<a id="nextPageSelf"  href="javascript:;">>></a>
				<select id="nowPageSelf" style="width:55px;height:20px;" >
				<option value="15" selected>15</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="50">50</option>
				<option value="80">80</option>
				<option value="100">100</option>
				</select>
				<span id="allPageSelf">共0页</span>
				<span style="margin-left:2px;" id="totalDataNumsSelf">共0条</span>
		</div>
	</div>
	<%--消息详细区 --%>
	<div class="issueRight" id="inboxPlatformMessageDetail"></div>
</div>
<div id="inboxPlatformMessageDialog"></div>
</div>
<script type="text/javascript">
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
			url : "${path}/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=view",
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
			if(pm.attr("sendtype")=='<s:property value="@com.tianque.platformMessage.constants.PlatformMessageSendType@SYSTERM_SNED" />'){
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
			if(pm.attr("sendtype")=='<s:property value="@com.tianque.platformMessage.constants.PlatformMessageSendType@SYSTERM_SNED" />'){
				$.messageBox({level:'warn',message:"系统消息不能转发！"});
				return;
			}
		}
		$("#inboxPlatformMessageDialog").createDialog({
			width: 800,
			height: 500,
			title:'转发平台消息',
			url:'${path}/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=inboxForward&platformMessage.id='+selectedId,
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
			url:'${path}/interaction/platformMessage/inbox/searchPlatformMessageInboxDlg.jsp',
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


<%-- 收件箱查询  系统消息--%>
function searchInboxPlatformMessage(searchCondition,page,rows){
	$("#inboxPlatformMessageDetail").show();
	var pageUrl = "${path}/interactive/searchPlatformMessage/searchInboxPlatformMessage.action";
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
<%--个人消息--%>
function searchSelfInboxPlatformMessage(searchCondition,page,rows){
	$("#inboxPlatformMessageDetail").show();
	var pageUrl = "${path}/interactive/searchPlatformMessage/searchInboxPlatformMessage.action";
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
<%-- 删除收件箱消息 --%>
function deleteInboxPlatMessage(id){
	$.ajax({
		url:'${path}/interactive/inboxPlatformMessageManage/deleteInboxPlatformMessageByIds.action?ids='+ id,
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

<%--回复平台信息--%>
function replyPlatformMessage(id){
	$("#inboxPlatformMessageDialog").createDialog({
		width: 600,
		height: 500,
		title:'回复平台消息',
		url:'${path}/interactive/inboxPlatformMessageManage/getInboxPlatformMessageById.action?mode=reply&platformMessage.id='+id,
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

//查询得到系统消息
function getInboxPlatformMessageList(page,rows){
	searchConditionClear();
	isSystem=0;
	$("#inboxPlatformMessageDetail").show();
	var pageUrl = "${path}/interactive/inboxPlatformMessageManage/findInboxPlatformMessageByUserId.action";
	var pageData = {"isAdmin":"1","page":page,"rows":rows,"sidx":"id","sord":"desc"};
	$.ajax({
		async : true,
		url : pageUrl,
		data :pageData,
		success : function(data) {
			$("#inboxPlatformMessageList").empty().html(data);
			$(".issueList li .issueTitle1").eq(0).trigger("click");
			$("#issueInboxListTotal").html("<lable>"+$("#records").val()+"</lable>");
			bindPager();
			if(0 == $("#systemIssueList li").length){
				$("#inboxPlatformMessageDetail").empty();
			}
		}
	 });	
}
function searchConditionClear(){
	$("#fastSearchCondition").attr("value","请输入标题或内容");
	$("#inboxPltformMessageType").find("option[value='']").attr("selected",true);
}
//查询得到个人消息
function getInboxPlatformSelfMessageList(page,rows){
	$("#inboxPlatformMessageDetail").show();
	searchConditionClear();
	isSystem=1;
	var pageUrl = "${path}/interactive/inboxPlatformMessageManage/findInboxPlatformSelfMessageByUserId.action";
	var pageData = {"isAdmin":"0","page":page,"rows":rows,"sidx":"id","sord":"desc"};
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

function bindPager(){
	if($("#records").val()==undefined){
		$("#records").attr("value",0);
	}
	if($("#page").val()==undefined){
		$("#page").attr("value",0);
	}
	$("#totalDataNums").html("共"+$("#records").val()+"条");
	$("#currentPage").attr("value",$("#page").val());
	var records = $("#records").val();
	var nowPage = $("#nowPage").val();
	var allPage = (records-0)%(nowPage-0)==0?(records-0)/(nowPage-0):(records-0)/(nowPage-0)+1;
	$("#allPage").html("共"+Math.floor(allPage)+"页");
}

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


function bindPagerSelf(){
	if($("#recordsSelf").val()==undefined){
		$("#recordsSelf").attr("value",0);
	}
	if($("#pageSelf").val()==undefined){
		$("#pageSelf").attr("value",0);
	}
	$("#totalDataNumsSelf").html("共"+$("#recordsSelf").val()+"条");
	$("#currentPageSelf").attr("value",$("#pageSelf").val());
	var records = $("#recordsSelf").val();
	var nowPage = $("#nowPageSelf").val();
	var allPage = (records-0)%(nowPage-0)==0?(records-0)/(nowPage-0):(records-0)/(nowPage-0)+1;
	$("#allPageSelf").html("共"+Math.floor(allPage)+"页");
}

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

</script>
<style>
	.issueProgram_Tab .issueProgram_Tab_list{position:absolute;top:0px;left:0;}
	.issueProgram_Tab .issueProgram_Tab_list li{float:left;}
	.issueProgram_Tab .issueLeft{top:30px;display:none;}
	
	.issueProgram_Tab ul li .messageType{background: -moz-linear-gradient(top, #C6D6F8, #cccccc);
				 background: -webkit-gradient(linear, left top, left bottom, from(white), to(white));
				 filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='white', EndColorStr='white');
				 background-image: linear-gradient(top, #C6D6F8,#cccccc );
				 width:147px;
				 height:30px;
				 line-height:30px;
				 text-align:center;
				 display:block;
				 border:1px solid #fff;
				 border-radiue:8px 8px 0 0;
	 }
	.issueProgram_Tab ul li .OOn{background: -moz-linear-gradient(top, #C6D6F8, #84C9F8);
				 background: -webkit-gradient(linear, left top, left bottom, from(#C6D6F8), to(#cccccc));
				 filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='#C6D6F8', EndColorStr='#cccccc');
				 background-image: linear-gradient(top, #C6D6F8,#84C9F8 );
				 width:147px;
				 height:30px;
				 line-height:30px;
				 text-align:center;
				 display:block;
				 border:1px solid #fff;
				 border-radiue:8px 8px 0 0; 
	 }

</style>