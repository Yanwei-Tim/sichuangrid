<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-cornRr-all" id="nav">
	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
			<input class="basic-input"  value="请输入标题或内容" id="fastSearchCondition"  
			class="searchtxt" onblur="value=(this.value=='')?'请输入标题或内容':this.value;" 
			onfocus="value=(this.value=='请输入标题或内容')?'':this.value;"/>
			<button id="clearSearchCondition" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
	</div>
	<pop:JugePermissionTag ename="searchDraftBox">
		<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
	</pop:JugePermissionTag>
	<span class="lineBetween "></span>
	<pop:JugePermissionTag ename="sendDraftBox">
		<a id="sendDraftBox" href="javascript:void(0)"><span>发送</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteDraftBox">
		<a id="delete" href="javascript:void(0)"><span>删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteAllDraftBox">
		<a id="deleteAll" href="javascript:void(0)"><span>批量删除</span></a>
	</pop:JugePermissionTag>
	<a id="reload"  href="javascript:void(0)"><span>刷新</span></a>
</div>
<div class="issueProgram clearfix">
	<div class="issueLeft" style="height:480px;">
		<%--消息列表 --%>
		<div class="issueData" id="inboxPlatformMessageList"></div>
		<%--消息列表分页区 --%>
		<div id="pager" class="issuePagination" style="display:block;height:25px;background:#ccc;line-height:25px;font-size:10px;">
				<a id="upperPage" class="pave" href="javascript:;"><<</a>
				<input  id="currentPage" type="text" style="width:20px;height:15px;" value="" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')">
				<span id="totalPage"></span>
				<a id="nextPage" class="next" href="javascript:;">>></a>
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
	<%--消息详细区 --%>
	<div class="issueRight" id="draftboxPlatformMessageDetail"></div>
</div>
<div id="outboxPlatformMessageDialog"></div>
<script type="text/javascript">
function reloadOutboxPlatformMessage() {
	var rows = $("#nowPage").val();
	getDraftboxPlatformMessageList(1,rows);
}
function getDraftboxPlatformMessageList(page,rows){
	$("#draftboxPlatformMessageDetail").show();
	var pageUrl = PATH+"/interactive/outboxPlatformMessageManage/findOutboxPlatformMessageByUserId.action";
	var pageData = {"isDraft":1,"page":page,"rows":rows,"sidx":"id","sord":"desc","mode":"draft"};
	$.ajax({
		async : true,
		url : pageUrl,
		data :pageData,
		success : function(data) {
			$("#inboxPlatformMessageList").empty().html(data);
			$(".issueList li .issueTitle1").eq(0).trigger("click");
			$("#issueInboxListTotal").html("<lable>"+$("#records").val()+"</lable>");
			bindPager();
			if(0 == $(".issueList li").length){
				$("#draftboxPlatformMessageDetail").empty();
			}
		}
	 });	
}
function bindPager(){
	$("#totalDataNums").html("共"+$("#records").val()+"条");
	$("#currentPage").attr("value",$("#page").val());
	var records = $("#records").val();
	var nowPage = $("#nowPage").val();
	var allPage = (records-0)%(nowPage-0)==0?(records-0)/(nowPage-0):(records-0)/(nowPage-0)+1;
	$("#allPage").html("共"+Math.floor(allPage)+"页");
}

function callback(){
    var dfop = {
    		
    }
    TQ.draftBoxList(dfop)
}

$.cacheScript({
    url:'/resource/getScript/interaction/platformMessage/draftbox/draftBoxList.js',
    callback: callback
})
</script>