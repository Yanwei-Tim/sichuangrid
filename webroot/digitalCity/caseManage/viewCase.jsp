<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="jqsubgrid" style="position:relative;overflow:hidden;zoom:1;height:auto;">
<pop:JugePermissionTag ename="dealCityCase">
	<s:if test="!'view'.equals(managementMode)">
		<div style="line-height:22px;height:22px;position:absolute;top:0px;right:0px;">
		    <a href="javascript:void(0)" class="jqsubgridButton" id="reportToTop"><span class="yijian"></span>上报指挥中心</a>
		    <a href="javascript:void(0)" class="jqsubgridButton" id="readTop"><span class="yijian"></span>阅读</a>
            <a href="javascript:void(0)" class="jqsubgridButton" id="conceptTop"><span class="yijian"></span>受理</a>
            <a href="javascript:void(0)" class="jqsubgridButton" id="dealTop" ><span class="yijian"></span>办理</a>
        </div>
    </s:if>
</pop:JugePermissionTag>
	<div class="container_24">
		<div class="grid_24 heightAuto">
			<div class="grid_3 lable-right heightAuto"  ><b>事件名称：</b></div>
			<div class="grid_20 heightAuto"><s:property value="issue.subject"/></div>
		</div>
        <div class="grid_24 heightAuto">
            <div class="grid_3 lable-right heightAuto"><b>所属网格：</b></div>
            <div class="grid_5 heightAuto"><s:property value="issue.occurOrg.orgName"/></div>
            <div class="grid_3 lable-right heightAuto"><b>录入时间：</b></div>
            <div class="grid_5 heightAuto"><s:date name="issue.createDate"  format="yyyy年MM月dd HH:mm:ss" /></div>
            <div class="grid_3 lable-right heightAuto"><b>结束时间：</b></div>
            <div class="grid_5 heightAuto"><s:property value="issue.endDate"/></div>
        </div>
        <div class="grid_24 heightAuto">
            <div class="grid_3 lable-right heightAuto"><b>主要人物：</b></div>
            <div class="grid_21 heightAuto"><s:property value="issue.mainCharacters"/></div>
        </div>
        <div class="grid_24 heightAuto">
            <div class="grid_3 lable-right"><b>发生地点：</b></div>
            <div class="grid_20 heightAuto"><s:property value="issue.occurLocation"/></div>
        </div>
        
        <div class="grid_24 heightAuto">
            <div class="grid_3 lable-right heightAuto"><b>事件类型：</b></div>
            <div class="grid_20 heightAuto"><s:property value="issueTypeDisplayName"/></div>
        </div>
        
        <div class="grid_24 heightAuto issueSmalltext">
            <div class="grid_3 lable-right heightAuto"><b>事件简述：</b></div>
            <div class="grid_20  heightAuto grid-message">
	            	<div class="grid_24 heightAuto">
	            		<s:property value="issue.issueContent" />
	            	</div>
            </div>
        </div>
        
        <div class="grid_24 process heightAuto">
            <div class="grid_3 lable-right heightAuto"><b>处理过程：</b></div>
            <div class="grid_20 process heightAuto">
           		<s:subset source="issueLogs" start="0">
                    <s:iterator status="index">
                    	<div class="messageInfo ui-widget-border-on ui-widget-border">
	                        <div class="grid_24 heightAuto">
	                            <div class="grid_3 heightAuto"><s:property value="dealOrg.orgName"/></div>
	                            <div class="grid_3 ui-widget-color heightAuto"><b><s:property value="dealUserName"/></b></div>
	                            <div class="grid_5 heightAuto"><span class="phone"><s:property value="mobile"/></span></div>
	                            <div class="grid_1 heightAuto accordred">于</div>
	                            <div class="grid_6 heightAuto"><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></div>
	                            <div class="grid_6 heightAuto"><span class="accordred"><s:property value="dealDescription"/></span><s:if test="content != null && !content.equals('')">&nbsp;&nbsp;&nbsp;&nbsp;并留言:</s:if></div>
	                        </div>
	                    	<s:if test="(content != null && !content.equals('')) || (issueLogAttachFilesMap[id] != null && issueLogAttachFilesMap[id].size()>0 )">
	                        <div class="grid_24 grid-message heightAuto">
	                            <div class="grid_24 heightAuto">
	                                 <em>${content }</em>
	                            </div>
	                     		<s:if test="issueLogAttachFilesMap[id]!=null && issueLogAttachFilesMap[id].size > 0">
		                        <div class="grid_24 filetype heightAuto">
			                    	<div class="grid-accessory">
										<span class="filetype-clip"></span>附件：
		                        <s:iterator value="issueLogAttachFilesMap[id]">
		                            <a href="${path }/issue/issueManage/downLoadActualFile.action?fileId=<s:property value="id"/>" ><s:property value="fileName"/></a>;
		                        </s:iterator>
		                        	</div>
		                        </div>
		                    	</s:if>
	                        </div>
                    		</s:if>
                    	</div>
                    </s:iterator>
                </s:subset>
            </div>
        </div>
	</div>
<pop:JugePermissionTag ename="dealCityCase">
	<s:if test="!'view'.equals(managementMode)">
		<div style="line-height:22px;height:22px;position:absolute;bottom:10px;right:0px;">
		    <a href="javascript:void(0)" class="jqsubgridButton" id="reportToBottom"><span class="yijian"></span>上报指挥中心</a>
			<a href="javascript:void(0)" class="jqsubgridButton" id="readBottom"><span class="yijian"></span>阅读</a>
	        <a href="javascript:void(0)" class="jqsubgridButton"  id="conceptBottom"><span class="yijian"></span>受理</a>
	        <a href="javascript:void(0)" class="jqsubgridButton" id="dealBottom"><span class="yijian"></span>办理</a>
	    </div>
    </s:if>
</pop:JugePermissionTag>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#allLogs").hide();
	$("#tttt").val("test");
    $("#showMoreLogs").click(function(){
        $("#allLogs").show();
        $("#hrefMore").hide();
    });
	$("#readTop,#readBottom").click(function(event){
	  	readIssue(${id});
	});
	$("#conceptTop,#conceptBottom").click(function(event){
		conceptIssue(${id});
	});
	$("#dealTop,#dealBottom").click(function(event){
		dealIssue(${id});
	});
	$("#reportToTop,#reportToBottom").click(function(event){
		reportToIssue(${id});
	});
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

</script>