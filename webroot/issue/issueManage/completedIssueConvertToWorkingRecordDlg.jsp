<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/includes/baseInclude.jsp" />
<style type="text/css">
	.issueTableList{border-top:1px solid #ccc;border-right: 1px solid #ccc;width:100%;}
	.issueTableList td{background:#EFEFF0;height:18px; padding:0 0 0 10px;border:1px solid #ccc;border-top:none;border-right:none;border-collapse:collapse;line-height:20px; word-break:break-all; word-warp:break-word;}
	.issueTableList .issueTitle{width:15%;font:bold 12px/20px "microsoft yahei"; color:#000; padding:0 0 0 5px;}
	.issueTableList .text{width:25%;}
	.issueTableList .issueContable{width:auto;}
</style>
<s:action name="findDailyYearsForCompletedIssue" var="getdailyYear" namespace="/hotModuel/daily/dailyYearManage" 
		  executeResult="false" />
<s:action name="findPropertyDictByDomainName" var="propertyDict" namespace="/sysadmin/propertyManage" 
		  executeResult="false" >
		  <s:param name="propertyDomain.domainName" value="@com.tianque.domain.property.PropertyTypes@WORKINGRECORD_TYPE"></s:param>
		  <s:param name="internalIds" value="@com.tianque.domain.property.WorkingRecordTypes@DOCUMENT"></s:param>
		  </s:action>
<div class="center-left" style="overflow:hidden;padding-left: 20px;top:13px;">
	<div class="propertyDomainList" style="overflow:hidden;width:186px;">
		请选择台账目录：
		<select id="dailyYear" name="dailyYear">
		<s:iterator value="#getdailyYear.dailyYears">
			<option value="${id}">${name}</option>
		</s:iterator>
		</select>
	</div>
	<div class="orgTree">
		<div id="dailyDirectoryTree"></div>
	</div>
</div>
<div class="center-right" >
	<div class="container container_24">
		<form id="workingRecordForm" method="post" action="${path}/workingRecord/workingRecordManage/dispatchBusiness.action?dialogName=workingRecordDialog">
		    <input type="hidden" id="mode" name="mode" value="${mode}"/>
		    <input type="hidden" id="dailyDirectoryId" name="workingRecord.dailyDirectory.id" />
			<div class="grid_4 lable-right"><em class="form-req">*</em><label class="form-lbl">类型：</label></div>
			<div class="grid_20">
			 	<input type="text"   value="${propertyDict.propertyDicts[0].displayName}" readonly class="form-txt"/>
			 	<input type="hidden" name="workingRecord.dailyLogType.id" value="${propertyDict.propertyDicts[0].id}" />
			</div>
			<select id="attachFileNames" name="attachFiles" multiple="multiple" style="display:none;"></select>
			<%--已办结事项附件的id --%>
			<select id="issueAttachFileId" name="issueAttachFileIds" multiple="multiple" style="display:none;"></select>
			<div id="includeDiv">
				<div class="grid_4 lable-right">
				    <em class="form-req">*</em><label class="form-lbl">文档名称：</label>
				</div>
				<div class="grid_20">
					<input type="text" name="workingRecord.name" class='form-txt {required:true,maxlength:50,exculdeParticalChar:true,messages:{required:"请输入名称",maxlength:$.format("名称不能多于{0}个字符"),exculdeParticalChar:"不能输入非法字符"}}' value="${issue.subject}" style="width:99%"/>
				</div>
				<div class="grid_4 lable-right">
					<label class="form-lbl">文档内容：</label>
				</div>
				<input type="hidden" name="workingRecord.content" id="issueToWorkingRecordContent" value="" />
				<div class="grid_20 heightAuto" id="allIssueContent"> 
					<div >
						<table class="issueTableList">
							<tr>
								<td class="issueTitle">来源人</td><td class=issueContable>${issue.sourcePerson }</td>
								<td class="issueTitle">手机号</td><td class="issueContable">${issue.sourceMobile }</td>
							</tr>
							<tr>
								<td class="issueTitle">来源方式</td><td class="issueContable">${issue.sourceKind.displayName }</td>
								<td class="issueTitle">发生时间</td><td class="issueContable">${issue.occurDateString}</td>
					       	</tr>
					        <tr>
					        	<!--  <td class="issueTitle">服务单号</td><td class="issueContable">${issue.serialNumber }</td>-->
					        	<td class="issueTitle">涉及人数</td><td class="issueContable" colspan="3">${issue.relatePeopleCount}</td>
					       	</tr>
					       	<tr>
					        	<td class="issueTitle">当事人</td><td class="issueContable">${issue.mainCharacters}</td>
					       	 	<td class="issueTitle">事件规模</td><td class="issueContable"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}" /></td>
					       	</tr>
					       	<tr>
					        	<td class="issueTitle">发生地点</td><td class="issueContable" colspan="5">${issue.occurLocation }</td>
					       	</tr>
					       	<tr>
					        	<td class="issueTitle">事件简述</td><td class="issueContable" colspan="5">${issue.issueContent }</td>
					       	</tr>
						</table>
						<c:if test="${issueEvaluate.id!=null}">
							<div class="recordEnd clearfix">
								<h3>事件评价：</h3>
								<div class="mattersAssign clearfix">
									<div class="matterContent"><span class="time">评价时间：<s:date name="#request.issueEvaluate.evaluateTime" format="yyyy-MM-dd" /></span></div>
									<div class="grid_3">评价等级：</div><div id="evaluateStar"></div>
									<div class='clearLine'></div>
									<div class="evaluate">评价内容：${issueEvaluate.evaluateContent}</div>
							</div>
						 </div>
						 <script>$('#evaluateStar').raty({readOnly:true,start:"${issueEvaluate.evaluateType}"});</script>
						 </c:if>
						 <div class="dealRecord recordList clearfix">
							<h3 >处理记录：</h3>
							<div class="recordContent clearfix">
								<ul  id="issueTable">
									<s:subset source="issueDealLogs" start="0">
										<s:iterator status="index">
											<li>
												<dl class="recordData clearfix">
													<dd class="time">
														<s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" />
														<s:property value="dealOrg.orgName"/><s:property value="dealUserName"/>
														<s:property value="dealDescription"/>
													</dd>
												</dl>
												<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
							                        <div class="smallText"><span>办理意见:</span><span>${content }</span></div>
					                    		</c:if>
											</li>
										</s:iterator>
									</s:subset>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="grid_4 lable-right"></div>
			<div class="grid_20" style="width:80%;">
			<div id="custom-queue" style="height:80px;overflow:auto;overflow-x: hidden;border:1px solid #7F9DB9;"></div>
			<input id="custom_file_upload"  name="uploadFile" type="file" style="padding-left:20px;width:70px;" />
		</div>
	</div>
	
</div>

<script type="text/javascript">

var dailyDirectoryTree;

$(document).ready(function(){
	
	$(".orgTree").height(300);
	
	function fillFile(){
		<c:if test="${issueAttachFiles!=null && fn:length(issueAttachFiles)>0}">
			<s:iterator value="issueAttachFiles">
				$("#custom-queue").addUploadFileValue({
			          id:"<s:property value='id'/>",
			          filename:"<s:property value='fileName'/>",
			          link:"${path }/issues/issueManage/downLoadAttachFile.action?keyId=<s:property value='id'/>",
			          onRemove:function(id){removeAttachById(id);}
				});
	        	$("<option>").val("<s:property value='id'/>").attr("selected",true).appendTo($("#issueAttachFileId"));
	        </s:iterator>
		</c:if>
	}
	function removeAttachById(id){
		$("#issueAttachFileId").find("option[value="+id+"]").remove();
	}
	$('#custom_file_upload').uploadFile({
		queueID:"custom-queue",
		selectInputId:"attachFileNames"
	});
	fillFile();
	$("#dailyYear").change(function(){
		if($(this).val() == null || $(this).val() == ""){
			$("#dailyDirectoryTree").append("没有可以显示的台账目录！");
		}else{
			dailyDirectoryTree=$("#dailyDirectoryTree").initDailydirectoryTree({
				'dailyYearId':$(this).val(),
				afterNodeExpanded:function(){
				var node = dailyDirectoryTree.getRootNode().firstChild.lastChild;
				if(node.text=='报表上报'){
					$.removeNode(dailyDirectoryTree,node.id);
				   }
				}
			});
			$.addClick(dailyDirectoryTree,function(node){$("#dailyDirectoryId").val(node.id);});
		}
	});

	$("#dailyYear").change();
	
	$("#issueToWorkingRecordContent").val($("#allIssueContent").html());
	
	$("#workingRecordForm").formValidate({
		submitHandler: function(form) {
		if($("#dailyYear").val()==null||$("#dailyYear").val()==''){
			$.messageBox({message:"台账目录不存在!",level: "warn"});
			return false;
		}
		var selectedNode = $.getSelectedNode(dailyDirectoryTree);
		if(!selectedNode.attributes.leaf){
			$.messageBox({message:"请选择底层目录!",level : "warn"});
			return false;
		}
		$(form).ajaxSubmit({
	   		success: function(data){
        			if(!data||!data.id){
          	 			$.messageBox({message:"转为台账失败!",level: "error"});
           			return;
				}
        			$.messageBox({message:"转为台账成功!"});
        			$("#issueDialog").dialog("close");
  	   		},
      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
  	   		     alert("提交错误");
      	   	}
     	  	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});

});

</script>