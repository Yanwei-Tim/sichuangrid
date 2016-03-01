<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style type="text/css">
	.ui-tabs-panel{background:#fff!important;}
</style>
<div class="content">
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" value="请输入事项编号或事项名称" id="searchByCondition" name="searchtxt" maxlength="18" class="searchtxt" onblur="value=(this.value=='')?'请输入事项编号或事项名称':this.value;" onfocus="value=(this.value=='请输入事项编号或事项名称')?'':this.value;"/>
            	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
            </div>
			<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		</div>
        <span class="lineBetween"></span>
        <a id="update" href="javascript:void(0)"><span>修改</span></a>
        <a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div class="issueProgram clearfix">
		<div class="issueLeft">
			<div class="issueData"></div>
			<div id="pager" class="issuePagination"></div>
		</div>
		<div class="issueRight" id="issueRight"></div>
	</div>
	<div id="itemDialog"></div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	issueRightHeight();
	selectApprovalItem();

});

$("#refresh").click(function(event){
	$("#searchByCondition").attr("value","请输入事项编号或事项名称");
	selectApprovalItem();
});

$("#update").click(function(event){
	var selectedId = $(".issueList li").attr("id");
	if(selectedId==null){
		$.messageBox({level:"warn",message:"没有可修改的数据！"});
		return;
	}
	$("#itemDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'修改申请事项',
		url:'${path}/approval/approvalItemManage/dispath.action?mode=edit&id='+selectedId,
		buttons: {
			"确定" : function(event){
				$("#maintainForm").submit();
			},
			"关闭" : function(){
	    	$(this).dialog("close");
			}
		}
	});
});

$(".issueLeft div").delegate(".issueList li","click",function(){
	$(this).addClass("current").siblings().removeClass("current");
	$(".issueList li input:checkbox").attr("checked",false);
	$(".issueList li.current input:checkbox").attr("checked",true);
	$.messageBox({message:'事件内容加载中，请稍候……'});
	$.ajax({
		async : true,
		url : "${path}/issues/issueManage/viewApprovalItemDetail.action?mode=doAction",
		data :{"approvalNumber":$(this).attr("approvalNumber"),"id":$(this).attr("id")},
		success : function(data) {
			$(".issueList li.current input:checkbox").attr("checked", true);
			$("#issueRight").empty().html(data);
			$.messageBox("close");
		}
	 });
});

function selectApprovalItem(){
	var pageUrl = "${path}/approval/approvalItemManage/findApprovalItemPage.action";
	var pageData = {"approvalItemVo.status":"<s:property value='@com.tianque.approval.domain.property.ApprovalItemStatus@AUDIT_DOES_NOT_PASS'/>","page":1,"rows":5,"sidx":"id","sord":"desc"};
	$.ajax({
		async : true,
		url : pageUrl,
		data :pageData,
		success : function(data) {
			$("div.issueData").html(data);
			$("#issueDidNotPassListTotal").html("<lable>"+$("#records").val()+"</lable>");
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
	    	pageData.page=page;
	    	$.ajax({
				async : true,
				url : pageUrl,
				data :pageData,
				success : function(data) {
					$("div.issueData").html(data);
					$(".issueList li").eq(0).trigger("click");
				}
			 });
	    }
	});
}
$("#refreshSearchKey").click(function(){$("#searchByCondition").attr("value","请输入事项编号或事项名称");});
$("#fastSearchButton").click(function(){
	search();
});

function search(){
	var condition = $("#searchByCondition").val();
	if(condition == '请输入事项编号或事项名称') return;
	var initParam = {
			"searchtxt":condition,
			"approvalItemVo.status":"<s:property value='@com.tianque.approval.domain.property.ApprovalItemStatus@AUDIT_DOES_NOT_PASS'/>",
			"page":1,"rows":5,"sidx":"id","sord":"desc"
	};

	var pageUrl = "${path}/approval/approvalItemManage/findApprovalItemPage.action";
	$.ajax({
		async : true,
		url : pageUrl,
		data :initParam,
		success : function(data) {
			$("div.issueData").html(data);
			$("#issueDidNotPassListTotal").html("<lable>"+$("#records").val()+"</lable>");
			bindPager(pageUrl,initParam);
			if(0 == $(".issueList li").length){
				$("#issueRight").empty();
			}
		}
	 });
}

</script>
