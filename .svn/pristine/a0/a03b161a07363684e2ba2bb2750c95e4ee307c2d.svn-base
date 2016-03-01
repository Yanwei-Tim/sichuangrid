<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<style type="text/css">
#input_involvedPersonnel{display:none !important;}
#holder_involvedPersonnel{height:22px;}
#jSIPContainer ul{height:150px !important;}
#issueMaintainForm{display:inline;}
#dialog-form{width:100%;}
.issuePeopleList{padding:5px 10px;margin:5px 20px;background:#eee;overflow:hidden;}
.issuePeopleList #addPeopleItem{color:blue;padding:0 0 0 5px;}
.dlg_innerBox label input{margin-right:5px;vertical-align: middle;}
.dlg_innerBox .delItemBox{display:none;}
.dlg_innerBox .delItemBox a{padding:0 10px;color:#f60;}
</style>
   <div id="issueEdit" class="container container_24">
		<div id=tabs>
			<ul>
				<li><a href="${path}/issues/issueManage/dispatch.action?mode=editIssueProcessRecord&keyId=<s:property value='issue.id'/>">修改处理过程</a> </li>
				<li><a href="${path}/issues/issueManage/dispatch.action?mode=edit&keyId=<s:property value='keyId'/>">修改事件处理信息</a> </li>
			</ul>
   		</div>
  </div>
<script>
$(function() {	
	$("div.ui-dialog-buttonset button:first").hide();
	$("#tabs").css("class","ui-dialog-content ui-widget-content");
	$("#issueEdit #tabs").tabs({
		cache:true,
		select:function(event,ui){
			if(ui.index==0){
				$(ui.panel).load(ui.tab.href);
				$("div.ui-dialog-buttonset button:first").hide();
			}
			if(ui.index==1&&$("#isShowSubmit").val()!="true"){
				$("div.ui-dialog-buttonset button:first").show();
			}
		}
	});
});
</script>