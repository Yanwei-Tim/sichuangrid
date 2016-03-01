<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="issueContentInfo">
<h1 class="issueReTitle"><span>${issue.subject } </span>
	
</h1>
<ul class="issueContentInfoList">
	<!--
		<li><span>服务单号</span>${issue.serialNumber }</li><li><span>来源方式</span>${issue.sourceKind.displayName }</li>
		<li><span>涉及人数</span>${issue.relatePeopleCount}</li>
	 -->
	<li><span>申请人名称：</span>${approvalItem.name}</li>
	<li><span>常住地址：</span>${approvalItem.currentAddress }</li>
	<li><span>申请时间：</span>${issue.occurDateString}</li>
	<li><span>手机号：</span>${approvalItem.mobileNumber }</li>
</ul>
</div>
<div class="instruction clearfix">
	<p>
		
	</p>
	<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
		<p>
		<div class="grid-accessory"><span class="filetype-clip"></span>附件：
			<s:iterator value="issueAttachFiles">
			    <a href="${path }/issues/issueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>" ><s:property value="fileName"/></a>;
			</s:iterator>
		</div>
		</p>
	</s:if>
	<s:if test="issue.remark!=null || issue.remark.equals('')">
		<p>备注：${issue.remark}</p>
	</s:if>
</div>
<s:if test="issueEvaluate.id!=null">
	<div class="recordEnd clearfix">
		<h3>事件评价：</h3>
		<div class="mattersAssign clearfix">
			<div class="matterContent">
				<span class="time">评价时间：<s:date name="#request.issueEvaluate.evaluateTime" format="yyyy-MM-dd" /></span>
			</div>
			<div class="grid_4">评价等级：</div>
			<div class="grid_19"><div id="starView"></div></div>
			<div class='clearLine'></div>
			<div class="evaluate">评价内容：${issueEvaluate.evaluateContent }</div>
		</div>
	</div>
</s:if>

<div class="dealRecord recordList clearfix">
	<h3 class="processRecord">处理记录
		<div class="processBtnList">
			<a href="javascript:;" class="cur" id="chartView"><span class="chart"></span>图表视图</a>
			<a href="javascript:;" id="textView"><span class="text"></span>文字视图</a>
		</div>
	</h3>
		<div class="recordContent clearfix">
			<ul style="display:none;" id="issueTable">
				<s:subset source="issueDealLogs" start="0">
					<s:iterator status="index">
						<li id="2012-12-11">
							<dl class="recordData clearfix">
								<dd class="time"><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></dd>
								<dd class="place"><s:property value="dealOrg.orgName"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:if test="dealUserName.equals('admin')">系统消息</s:if><s:property value="dealUserName"/></dd>
								<dd class="handle"><s:property value="dealDescription"/></dd>
							</dl>
							<s:if test="(content != null && !content.equals('')) || (issueLogAttachFilesMap[id] != null && issueLogAttachFilesMap[id].size()>0 )">
		                        <div class="smallText">
										<span>并留言:</span><span>${content }</span>
								</div>
		                        <s:if test="issueLogAttachFiles[id]!=null && issueLogAttachFiles[id].size > 0">
			                    	<div class="smallText">
										<span class="filetype-clip"></span>附件：
			                        <s:iterator value="issueLogAttachFiles[id]">
			                            <a href="${path }/issues/issueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>" ><img  src="${resource_path}/resource/images/fujian.jpg"/><s:property value="fileName"/></a>;
			                        </s:iterator>
		                        	</div>
	                   			</s:if>
                    		</s:if>
						</li>
					</s:iterator>
				</s:subset>
			</ul>
			<div id="issueGraph">
			    <s:include value="/issue/issueMap/raphael.jsp" ></s:include>
		    </div>
		</div>

</div>


<script type="text/javascript">
$(document).ready(function(){
	<s:if test="issueEvaluate.id!=null">
		$('#starView').raty({
		 	readOnly:true,
			start:"${issueEvaluate.evaluateType}"
		 });
	</s:if>
	bindOperationAction();
	setReadOperateVisiabled(false);
	setConcepteOperateVisiabled(false);
	setDealOperateVisiabled(false);
	setReportToOperateVisiabled(false);

	<s:if test="@com.tianque.issue.state.IssueOperate@COMPLETE in canDoList">
		setDealOperateVisiabled(true);
	</s:if>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@ASSIGN in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@GIVETO in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@COMMENT in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@SUBMIT in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
	<s:elseif test="@com.tianque.issue.state.IssueOperate@BACK in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>

	<s:if test="@com.tianque.issue.state.IssueOperate@REPORT_TO in canDoList">
		setReportToOperateVisiabled(true);
	</s:if>
	<s:if test="@com.tianque.issue.state.IssueOperate@CONCEPT in canDoList ">
		setConcepteOperateVisiabled(true);
	</s:if>
	<s:if test="@com.tianque.issue.state.IssueOperate@READ in canDoList">
		setReadOperateVisiabled(true);
	</s:if>

	renderIssueType();

	function bindOperationAction(){
		$("#readTop,#readBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>);
		});
		$("#conceptTop,#conceptBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>);
		});
		$("#dealTop,#dealBottom").click(function(event){
			dealIssue(${keyId});
		});
		$("#reportToTop,#reportToBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@REPORT_TO.code"/>);
		});
	}


	$(".processBtnList a").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
	});
	$("#chartView").click(function(){
		$("#issueGraph").show();
		$("#issueTable").hide();
	});
	$("#textView").click(function(){
		$("#issueTable").show();
		$("#issueGraph").hide();
	});
});

function setUiVisiable(ui,visiable){
	if (visiable){
		ui.show();
	}else{
		ui.hide();
	}
}

function setReadOperateVisiabled(visiabled){
	setUiVisiable($("#readTop"),visiabled);
	setUiVisiable($("#readBottom"),visiabled);
}

function setConcepteOperateVisiabled(visiabled){
	setUiVisiable($("#conceptTop"),visiabled);
	setUiVisiable($("#conceptBottom"),visiabled);
}

function setDealOperateVisiabled(visiabled){
	setUiVisiable($("#dealTop"),visiabled);
	setUiVisiable($("#dealBottom"),visiabled);
}

function setReportToOperateVisiabled(visiabled){
	setUiVisiable($("#reportToTop"),visiabled);
	setUiVisiable($("#reportToBottom"),visiabled);
}

function setGiveToOperateVisiabled(visiabled){
	setUiVisiable($("#giveToTop"),visiabled);
	setUiVisiable($("#giveToBottom"),visiabled);
}

function getTypeDescById(indexs,id){
	for (var index=0;index<indexs.length;index++){
		if (indexs[index]==id) return index;
	}
	return indexs.length;
}


function renderIssueType(){
	var typeDescs=new Array();
	var descIndexs=new Array();
	var typeDesc='';
	var actualIndex;
    <s:iterator value="issue.issueTypes" var="type" >
	    actualIndex=getTypeDescById(descIndexs,<s:property value="#type.issueTypeDomain.id"/>);
    	if (actualIndex==typeDescs.length){
    		descIndexs[actualIndex]=<s:property value="#type.issueTypeDomain.id"/>;
    		typeDescs[actualIndex]='<s:property value="#type.issueTypeDomain.domainName" escape="false"/>'+':';
        }
    	typeDescs[actualIndex]=typeDescs[actualIndex]+'<s:property value="#type.issueTypeName" escape="false"/>'+',';
	</s:iterator>

	for (var index=0;index<typeDescs.length;index++){
		if (index>0){
			typeDesc=typeDesc+'<br>';
		}
		typeDesc=typeDesc+typeDescs[index];
	}
	$("#issueTypesLabel").html(typeDesc);
}


</script>