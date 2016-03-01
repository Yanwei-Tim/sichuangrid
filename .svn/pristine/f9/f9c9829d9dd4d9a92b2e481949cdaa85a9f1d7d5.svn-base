<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="issueContentInfo">

 <table class="newTableList">
	<tbody>
        <tr>
			<td class="title">发生时间</td><td class="contable">${issue.occurDateString}</td>
		</tr>
		<tr>	
			<td class="title">办理截止时间</td><td class="contable"><s:date format="yyyy-MM-dd" name="operation.dealDeadline"></s:date></td>
       	</tr>
       	<tr>
			<td class="title">事件规模</td><td class="contable">
       	 	<pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}" /></td>
       	</tr>
		<tr>
        	<td class="title">涉及人数</td><td class="contable">${issue.relatePeopleCount}</td>
       	</tr>
		<tr>
        	<td class="title">主要当事人</td><td class="contable">${issue.mainCharacters}</td>
       	</tr>
      	<tr>
       		<td class="title">发生地点</td><td class="contable">${issue.occurLocation }</td>
       	</tr>
       	<tr>
       		<td class="title">是否重大事件</td>
       		<td class="contable">
       			<c:choose>
				    <c:when test="${issue.important == true }">
				     	是
				    </c:when>
				    <c:otherwise>
				    	否
				    </c:otherwise>
				</c:choose>
			</td>
       	</tr>
       	<tr>
       		<td class="title">是否紧急事件</td>
       		<td class="contable">
       			<c:choose>
				    <c:when test="${issue.isEmergency == true }">
				     	是
				    </c:when>
				    <c:otherwise>
				    	否
				    </c:otherwise>
				</c:choose>
       		</td>
       	</tr>
       	<!-- 	<p> -->
		<%-- 		${issue.issueContent } --%>
		<!-- 	</p> -->
       	<tr>
       		<c:if test="${issueAttachFiles!=null && fn:length(issueAttachFiles)>0}">
			<p>
				<td class="title"><div class="grid-accessory"><span class="filetype-clip"></span>附件：</td>
				<td class="contable">	
				<c:forEach items="${issueAttachFiles}" var="issueAttachFile">
			    	<a href="${path }/issues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}" style="color: red">${issueAttachFile.fileName}</a>;
					<div class="clear"></div>
				</c:forEach>
				</td>
			</p>
			</c:if>
       	</tr>
       	<tr>
       		<c:if test="${ issue.remark!=null || not empty  issue.remark}">
				<p>备注：${issue.remark}</p>
			</c:if>
       	</tr>
       	<tr>
       		<c:if test="${issueEvaluate.id!=null }">
			<div class="recordEnd clearfix">
			<h3>事件验证：</h3>
			<div class="mattersAssign clearfix">
			<div class="matterContent">
				<span class="time">验证时间：<s:date name="#request.issueEvaluate.evaluateTime" format="yyyy-MM-dd" /></span>
			</div>
			<div class="grid_4">验证等级：</div>
			<div class="grid_19">
				<c:choose>
				    <c:when test="${issueEvaluate.evaluateType == 1 }">
				     	不满意
				    </c:when>
				    <c:when test="${issueEvaluate.evaluateType == 2 }">
				     	基本满意
				    </c:when>
				    <c:when test="${issueEvaluate.evaluateType == 3 }">
				     	满意
				    </c:when>
				</c:choose>
			</div>
			<div class='clearLine'></div>
			<div class="evaluate">验证内容：${issueEvaluate.evaluateContent }</div>
			</div>
			</div>
			</c:if>
       	</tr>
	</tbody>
</table>
</div>


	
	



<div class="dealRecord recordList clearfix">
	<h3 class="processRecord">处理记录
		<div class="processBtnList">
			<a href="javascript:;" class="cur" id="textView"><span class="text"></span>文字视图</a>
			<a href="javascript:;" id="chartView"><span class="chart"></span>图表视图</a>
		</div>
	</h3>
		<div class="recordContent clearfix">
			<ul style="display:none;" id="issueTable">
				<s:subset source="issueDealLogs" start="0">
					<s:iterator status="index">
						<li id="2012-12-11">
							<dl class="recordData clearfix">
								<dd class="time"><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></dd>
								<dd class="place"><s:property value="dealOrg.orgName"/>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="dealUserName"/></dd>
								<dd class="handle"><s:property value="dealDescription"/></dd>
							</dl>
							<c:if test="${(content != null && not empty  content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0) }">
		                        <div class="smallText">
										<span>办理意见:</span><span>${content }</span>
								</div>
								<c:if test="${issueLogAttachFiles[id] != null && fn:length(issueLogAttachFiles[id])>0}">
			                    	<div class="smallText">
										<span class="filetype-clip"></span>附件：
										<c:forEach items="${issueLogAttachFiles[id]}" var="issueAttachFile">
			                                <a href="/issues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}">${issueAttachFile.fileName}
			                                </a>
			                            </c:forEach>
		                        	</div>
	                   			</c:if>
                    		</c:if>
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
	//处理，受理等按钮显示
	$("#dealTop").removeAttr("style");
	$("#conceptTop").removeAttr("style");
	$("#readTop").removeAttr("style");
	$("#reportToTop").removeAttr("style");
	<c:if test="${issueEvaluate.id!=null}">
		//$('#starView').raty({
		// 	readOnly:true,
		//	start:"${issueEvaluate.evaluateType}"
		// });
	</c:if>
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
	$("#issueTable").show();
	$("#issueGraph").hide();

	function bindOperationAction(){
		$("#readTop,#readBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>);
		});
		$("#conceptTop,#conceptBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>);
		});
		//由于该页面是动态的，使用click会重复绑定事件,所有使用以下方法
		$("#dealTop,#dealBottom").die().live("click",function(event){
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
    	typeDescs[actualIndex]=typeDescs[actualIndex]+"<s:property value="#type.issueTypeName+','" escape="false"/>";
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